package com.mcreater.fxfluent.syslib

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.platform.win32.WinDef.*

interface Win32UiShellLib : Library {
    companion object {
        @JvmField
        val INSTANCE: Win32UiShellLib = Native.load("UiShell", Win32UiShellLib::class.java)
    }
    fun implNativeWarning()
    fun implGetCompositionColor(): DWORD
    fun implGetThemeIsDark(): BOOL
    fun implGetTransparencyEnabled(): BOOL
    fun implApplyBlur(hwnd: HWND?, blurType: DWORD?, isDark: BOOL?): BOOL
    fun implIsWindows11(): BOOL
    fun implSetWindowIsDark(hwnd: HWND?, isDark: BOOL?)
    fun implSetWindowRadius(hwnd: HWND?, radius: DWORD?): BOOL
}