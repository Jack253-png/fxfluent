package com.mcreater.fxfluent.xaml.tags;

import javafx.scene.paint.Color;

public class ColorContentTag extends SimpleContentTag<Color> {
    public Color toObject() {
        if (!element.elements().isEmpty()) return null;
        else return Color.valueOf(element.getData().toString());
    }
}
