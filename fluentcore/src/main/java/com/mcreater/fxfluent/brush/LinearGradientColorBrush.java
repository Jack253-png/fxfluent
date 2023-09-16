package com.mcreater.fxfluent.brush;

import javafx.scene.paint.LinearGradient;

public class LinearGradientColorBrush extends AbstractColorBrush {
    private final LinearGradient gradient;
    public LinearGradientColorBrush(LinearGradient gradient) {
        this.gradient = gradient;
    }
    public LinearGradient getPaint() {
        return gradient;
    }
}
