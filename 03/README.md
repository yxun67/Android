# Android界面组件实验

本项目实现了Android实验3的所有要求功能：

## 功能特性

### 1. 上下文操作模式(ActionMode)的上下文菜单
- 长按测试文本可以激活ActionMode
- 提供字体大小设置（小、中、大字体）
- 提供字体颜色设置（红色、黑色）
- 包含普通菜单项

### 2. XML定义菜单
- 使用XML文件定义上下文菜单
- 包含字体大小选项（10sp、16sp、20sp）
- 包含字体颜色选项（红色、黑色）
- 点击菜单项后显示Toast提示

### 3. 自定义布局的AlertDialog
- 仿照实验要求创建登录对话框
- 包含用户名和密码输入框
- 包含Cancel和Sign in按钮
- 橙色标题栏显示"ANDROID APP"

### 4. ListView的用法
- 使用SimpleAdapter显示动物列表
- 每个列表项包含动物图片和名称
- 点击列表项显示Toast提示
- 点击后发送通知，通知图标为应用程序图标，标题为列表项内容

## 项目结构

```
app/src/main/
├── java/com/example/myapplication/
│   └── MainActivity.java          # 主Activity，实现所有功能
├── res/
│   ├── layout/
│   │   ├── activity_main.xml      # 主布局文件
│   │   ├── dialog_custom.xml      # 自定义对话框布局
│   │   └── list_item.xml          # ListView项布局
│   ├── menu/
│   │   └── context_menu.xml       # 上下文菜单定义
│   └── drawable/                  # 动物图片资源
│       ├── cat.png
│       ├── dog.jpeg
│       ├── elephant.jpg
│       ├── lion.jpeg
│       ├── monkey.jpeg
│       └── tiger.jpg
└── AndroidManifest.xml            # 应用清单文件
```

## 使用说明

1. **测试上下文菜单**：长按顶部的测试文本，会弹出ActionMode菜单，可以设置字体大小和颜色
2. **测试自定义对话框**：点击"显示自定义对话框"按钮，会弹出登录对话框
3. **测试ListView**：点击动物列表中的任意项，会显示Toast提示并发送通知

## 技术要点

- 使用ActionMode实现上下文操作模式
- 使用XML定义菜单资源
- 使用AlertDialog.Builder创建自定义对话框
- 使用SimpleAdapter适配器显示ListView
- 使用NotificationCompat发送通知
- 支持Android 8.0+的通知渠道