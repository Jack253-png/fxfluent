package com.mcreater.fxfluent.syslib;

import com.mcreater.fxfluent.util.SystemUtil;
import javafx.scene.paint.Color;

public class UiShellWrapper {
    public static Color SYS_DEFAULT_COMPOSITION_COLOR = Color.rgb(0, 120, 212);
    public static Color GetSystemCompositionColor() {
        if (SystemUtil.isWindows()) {
            java.awt.Color color = new java.awt.Color(Win32UiShellLib.INSTANCE.GetCompositionColor().intValue());
            return Color.rgb(color.getRed(), color.getGreen(), color.getBlue(), 1.0);
        }
        return SYS_DEFAULT_COMPOSITION_COLOR;
    }
}
