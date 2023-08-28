package com.mcreater.fxfluent.syslib;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;

public interface Win32UiShellLib extends Library {
    Win32UiShellLib INSTANCE = Native.load("UiShell", Win32UiShellLib.class);
    void NativeWarningReflect();
    WinDef.DWORD GetCompositionColor();
    WinDef.BOOL GetThemeIsDark();
    WinDef.BOOL GetTransparencyEnabled();
    WinDef.BOOL ApplyBlur(WinDef.HWND hwnd, WinDef.DWORD blurType, WinDef.BOOL isDark);
    WinDef.BOOL IsWindows11();
    void SetWindowIsDark(WinDef.HWND hwnd, WinDef.BOOL isDark);
    WinDef.BOOL SetWindowRadius(WinDef.HWND hwnd, WinDef.DWORD radius);
}
