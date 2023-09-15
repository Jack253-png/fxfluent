package com.mcreater.fxfluent.brush;

import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;

public class SolidColorBrush extends AbstractColorBrush {
    private final Color color;
    public SolidColorBrush(Color color) {
        this.color = color != null ? color : Color.TRANSPARENT;
    }

    public String toString() {
        return getPaint().toString();
    }

    public Color getPaint() {
        return color;
    }

    public BorderWidths getBorderWidths() {
        return new BorderWidths(1);
    }
}
