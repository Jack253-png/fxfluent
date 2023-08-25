package com.mcreater.fxfluent.xaml.tags;

import javafx.scene.paint.Color;

import static com.mcreater.fxfluent.util.ColorUtil.parseXamlColor;

public class ColorContentTag extends SimpleContentTag<Color> {
    public Color toObject() {
        String content = element.getData().toString();

        if (!element.elements().isEmpty()) return null;
        else return parseXamlColor(content);
    }
}
