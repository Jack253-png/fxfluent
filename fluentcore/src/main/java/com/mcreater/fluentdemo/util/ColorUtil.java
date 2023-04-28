package com.mcreater.fluentdemo.util;

import javafx.scene.paint.Color;

public class ColorUtil {
    public static boolean isLight(Color color) {
        return color.getBrightness() >= 0.5;
    }
}
