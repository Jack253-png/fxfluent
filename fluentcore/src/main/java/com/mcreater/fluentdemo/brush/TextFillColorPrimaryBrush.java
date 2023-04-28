package com.mcreater.fluentdemo.brush;

import javafx.scene.paint.Color;

import static com.mcreater.fluentdemo.util.ColorUtil.isLight;
import static com.mcreater.fluentdemo.util.MathUtil.limit;

public class TextFillColorPrimaryBrush implements TextFillBrush {
    public Color convert(Color base) {
        return Color.hsb(base.getHue(), base.getSaturation(), limit(base.getBrightness() - (!isLight(base) ? -0.1 : 0.01), 1, 0));
    }
}
