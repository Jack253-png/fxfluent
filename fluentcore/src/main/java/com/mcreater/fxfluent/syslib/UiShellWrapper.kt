package com.mcreater.fxfluent.syslib

import com.mcreater.fxfluent.util.SystemUtil
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinDef.*
import javafx.scene.paint.Color

class UiShellWrapper {
    companion object {
        @JvmStatic
        var SYS_DEFAULT_COMPOSITION_COLOR: Color = Color.rgb(0, 120, 212)
        @JvmStatic
        fun getSystemCompositionColor(): Color {
            if (SystemUtil.isWindows()) {
                val color = java.awt.Color(Win32UiShellLib.INSTANCE.implGetCompositionColor().toInt())
                return Color.rgb(color.red, color.green, color.blue, 1.0)
            }
            return SYS_DEFAULT_COMPOSITION_COLOR
        }
        @JvmStatic
        fun getSystemIsDark(): Boolean {
            return if (SystemUtil.isWindows()) {
                Win32UiShellLib.INSTANCE.implGetThemeIsDark().booleanValue()
            } else false
        }
        @JvmStatic
        fun systemThemeSupport(): Boolean {
            return SystemUtil.isWindows()
        }
        @JvmStatic
        fun getSystemTransparencyEnabled(): Boolean {
            return if (SystemUtil.isWindows()) {
                Win32UiShellLib.INSTANCE.implGetTransparencyEnabled().booleanValue()
            } else false
        }
        @JvmStatic
        fun applyBlur(windowHandle: Long, type: BackdropType, isDark: Boolean): Boolean {
            if (windowHandle == (-1).toLong()) return false;
            if (getSystemTransparencyEnabled()) {
                if (SystemUtil.isWindows()) {
                    return Win32UiShellLib.INSTANCE.implApplyBlur(
                        HWND(Pointer.createConstant(windowHandle)),
                        DWORD(type.id.toLong()),
                        BOOL(isDark)
                    ).booleanValue()
                }
            }
            return false
        }
        @JvmStatic
        fun hideBar(windowHandle: Long) {
            if (SystemUtil.isWindows()) {
                Win32UiShellLib.INSTANCE.implHideBar(HWND(Pointer(windowHandle)))
            }
        }
        @JvmStatic
        fun nativeWarn() {
            if (SystemUtil.isWindows()) Win32UiShellLib.INSTANCE.implNativeWarning()
        }
        @JvmStatic
        fun needBackground(type: BackdropType): Boolean {
            if (SystemUtil.isWindows()) {
                val iswin11 = Win32UiShellLib.INSTANCE.implIsWindows11().booleanValue()
                return !iswin11 || type == BackdropType.ACRYLIC || type == BackdropType.BLUR
            }
            return true
        }
        @JvmStatic
        fun setWindowIsDark(windowHandle: Long, isDark: Boolean) {
            if (SystemUtil.isWindows()) {
                Win32UiShellLib.INSTANCE.implSetWindowIsDark(
                    HWND(Pointer.createConstant(windowHandle)),
                    BOOL(isDark)
                )
            }
        }
    }
}