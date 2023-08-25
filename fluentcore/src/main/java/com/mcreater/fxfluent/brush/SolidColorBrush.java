package com.mcreater.fxfluent.brush;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.function.BiConsumer;

public class SolidColorBrush implements BiConsumer<Region, BiConsumer<Region, Color>> {
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

    public void accept(Region region, BiConsumer<Region, Color> colorConsumer) {
        colorConsumer.accept(region, color);
    }
}
