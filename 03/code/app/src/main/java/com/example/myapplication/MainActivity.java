package com.example.myapplication;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tvTestText;
    private Button btnShowDialog;
    private ListView lvAnimals;
    private ActionMode actionMode;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> animalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListView();
        setupActionMode();
        setupDialog();
    }

    private void initViews() {
        tvTestText = findViewById(R.id.tv_test_text);
        btnShowDialog = findViewById(R.id.btn_show_dialog);
        lvAnimals = findViewById(R.id.lv_animals);
    }

    private void setupListView() {
        // 创建动物数据
        animalList = new ArrayList<>();
        
        String[] animalNames = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
        int[] animalImages = {
            R.drawable.lion,
            R.drawable.tiger, 
            R.drawable.monkey,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.elephant
        };

        for (int i = 0; i < animalNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", animalNames[i]);
            map.put("image", animalImages[i]);
            animalList.add(map);
        }

        // 创建SimpleAdapter
        adapter = new SimpleAdapter(
            this,
            animalList,
            R.layout.list_item,
            new String[]{"name", "image"},
            new int[]{R.id.tv_animal_name, R.id.iv_animal}
        );

        lvAnimals.setAdapter(adapter);

        // 设置ListView点击事件
        lvAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String animalName = (String) animalList.get(position).get("name");
                
                // 显示Toast
                Toast.makeText(MainActivity.this, "选中了: " + animalName, Toast.LENGTH_SHORT).show();
                
                // 发送通知
                sendNotification(animalName);
            }
        });
    }

    private void setupActionMode() {
        // 为TextView设置长按监听器，启动ActionMode
        tvTestText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionMode != null) {
                    return false;
                }
                
                actionMode = startActionMode(actionModeCallback);
                v.setSelected(true);
                return true;
            }
        });
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
            mode.setTitle("文本设置");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            
            if (itemId == R.id.font_small) {
                tvTestText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                Toast.makeText(MainActivity.this, "设置为小字体(10sp)", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.font_medium) {
                tvTestText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                Toast.makeText(MainActivity.this, "设置为中字体(16sp)", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.font_large) {
                tvTestText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                Toast.makeText(MainActivity.this, "设置为大字体(20sp)", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.normal_menu_item) {
                Toast.makeText(MainActivity.this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.font_red) {
                tvTestText.setTextColor(Color.RED);
                Toast.makeText(MainActivity.this, "设置为红色字体", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.font_black) {
                tvTestText.setTextColor(Color.BLACK);
                Toast.makeText(MainActivity.this, "设置为黑色字体", Toast.LENGTH_SHORT).show();
            }
            
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            tvTestText.setSelected(false);
        }
    };

    private void setupDialog() {
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        // 创建自定义布局的AlertDialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        EditText etUsername = dialogView.findViewById(R.id.et_username);
        EditText etPassword = dialogView.findViewById(R.id.et_password);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnSignin = dialogView.findViewById(R.id.btn_signin);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        
        AlertDialog dialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录成功！用户名: " + username, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    private void sendNotification(String animalName) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        
        // 创建通知渠道（Android 8.0及以上需要）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                "animal_channel",
                "Animal Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // 创建通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "animal_channel")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("动物选择")
            .setContentText("您选择了: " + animalName)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }
}