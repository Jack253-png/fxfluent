package com.mcreater.fxfluent.brush;

import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.LinearGradient;

public class LinearGradientColorBrush extends AbstractColorBrush {
    private final LinearGradient gradient;
    public LinearGradientColorBrush(LinearGradient gradient) {
        this.gradient = gradient;
    }
    public LinearGradient getPaint() {
        return gradient;
    }

    public BorderWidths getBorderWidths() {
        return new BorderWidths(
                Math.abs(gradient.getStartY() - gradient.getEndY()),
                Math.abs(gradient.getStartX() - gradient.getEndX()),
                Math.abs(gradient.getStartY() - gradient.getEndY()),
                Math.abs(gradient.getStartX() - gradient.getEndX())
        );
    }
}
