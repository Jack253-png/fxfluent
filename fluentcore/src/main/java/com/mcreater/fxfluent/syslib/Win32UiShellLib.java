package com.mcreater.fxfluent.syslib;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;

public interface Win32UiShellLib extends Library {
    Win32UiShellLib INSTANCE = Native.load("UiShell", Win32UiShellLib.class);
    void HelloWorld();
    WinDef.DWORD GetCompositionColor();
}
