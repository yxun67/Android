@echo off
echo ========================================
echo NotePad 应用修复和运行脚本
echo ========================================
echo.

echo [1/5] 卸载旧版本应用...
adb uninstall com.example.android.notepad
echo.

echo [2/5] 清理项目...
call gradlew.bat clean
echo.

echo [3/5] 重新构建项目...
call gradlew.bat assembleDebug
echo.

echo [4/5] 安装应用...
call gradlew.bat installDebug
echo.

echo [5/5] 启动日志监控...
echo 应用已安装，正在监控日志...
echo 请在设备上启动应用
echo 按 Ctrl+C 停止日志监控
echo.
adb logcat -c
adb logcat | findstr /I "notepad AndroidRuntime Exception"
