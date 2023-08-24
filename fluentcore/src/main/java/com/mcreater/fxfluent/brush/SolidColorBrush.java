package com.mcreater.fxfluent.brush;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.function.BiConsumer;

public class SolidColorBrush implements BiConsumer<Region, CornerRadii> {
    private final Color color;
    public SolidColorBrush(Color color) {
        this.color = color != null ? color : Color.TRANSPARENT;
    }

    public Color getColor() {
        return color;
    }

    public void accept(Region region, CornerRadii cornerRadii) {
        region.setBackground(new Background(
                new BackgroundFill(
                        color,
                        cornerRadii,
                        Insets.EMPTY
                )
        ));
    }

    public String toString() {
        return getColor().toString();
    }
}
