package com.mcreater.fxfluent.syslib;

import com.mcreater.fxfluent.util.SystemUtil;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import javafx.scene.paint.Color;

public class UiShellWrapper {
    /**
     * Backdrop type<br>背景类型
     */
    public enum BackdropType {
        DISABLED(0),
        BLUR(1),
        ACRYLIC(2),
        MICA(3),
        MICA_ALT(4);
        public final int id;
        BackdropType(int id) {
            this.id = id;
        }
    }
    public static Color SYS_DEFAULT_COMPOSITION_COLOR = Color.rgb(0, 120, 212);
    public static Color GetSystemCompositionColor() {
        if (SystemUtil.isWindows()) {
            java.awt.Color color = new java.awt.Color(Win32UiShellLib.INSTANCE.GetCompositionColor().intValue());
            return Color.rgb(color.getRed(), color.getGreen(), color.getBlue(), 1.0);
        }
        return SYS_DEFAULT_COMPOSITION_COLOR;
    }
    public static boolean GetSystemIsDark() {
        if (SystemUtil.isWindows()) {
            return Win32UiShellLib.INSTANCE.GetThemeIsDark().booleanValue();
        }
        return false;
    }
    public static boolean GetSystemTransparencyEnabled() {
        if (SystemUtil.isWindows()) {
            return Win32UiShellLib.INSTANCE.GetTransparencyEnabled().booleanValue();
        }
        return false;
    }
    public static boolean ApplyBlur(long windowHandle, BackdropType type) {
        if (GetSystemTransparencyEnabled()) {
            if (SystemUtil.isWindows()) {
                return Win32UiShellLib.INSTANCE.ApplyBlur(
                        new WinDef.HWND(Pointer.createConstant(windowHandle)),
                        new WinDef.DWORD(type.id)
                ).booleanValue();
            }
        }
        return false;
    }
    public static void NativeWarn() {
        if (SystemUtil.isWindows()) {
            Win32UiShellLib.INSTANCE.NativeWarningReflect();
        }
    }
    public static boolean needBackground(BackdropType type) {
        if (SystemUtil.isWindows()) {
            boolean iswin11 = Win32UiShellLib.INSTANCE.IsWindows11().booleanValue();
            return !iswin11 || (type == BackdropType.ACRYLIC || type == BackdropType.BLUR);
        }
        return true;
    }
}
