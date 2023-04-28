package com.mcreater.fluentdemo.brush;

import javafx.scene.paint.Color;

import static com.mcreater.fluentdemo.util.ColorUtil.isLight;
import static com.mcreater.fluentdemo.util.MathUtil.limit;

public class TextFillColorTertiaryBrush implements TextFillBrush {
    public Color convert(Color base) {
        return Color.hsb(base.getHue(), base.getSaturation(), limit(base.getBrightness() - (!isLight(base) ? -0.54 : 0.4), 1, 0));
    }
}
