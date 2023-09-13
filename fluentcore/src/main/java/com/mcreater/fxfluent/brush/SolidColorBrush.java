package com.mcreater.fxfluent.brush;

import javafx.animation.Interpolatable;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.function.BiConsumer;

public class SolidColorBrush implements BiConsumer<Region, BiConsumer<Region, Paint>>, Interpolatable<SolidColorBrush> {
    private final Color color;
    public SolidColorBrush(Color color) {
        this.color = color != null ? color : Color.TRANSPARENT;
    }

    public Color getColor() {
        return color;
    }

    public String toString() {
        return getColor().toString();
    }

    public void accept(Region region, BiConsumer<Region, Paint> colorConsumer) {
        colorConsumer.accept(region, color);
    }

    public SolidColorBrush interpolate(SolidColorBrush solidColorBrush, double v) {
        return new SolidColorBrush(color.interpolate(solidColorBrush.getColor(), v));
    }
}
