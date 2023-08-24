package com.mcreater.fxfluent.xaml.tags;

import javafx.scene.paint.Color;

public class ColorContentTag extends SimpleContentTag<Color> {
    public Color toObject() {
        String content = element.getData().toString();
        Color color = Color.TRANSPARENT;
        if (content.startsWith("#") && content.length() == 9) {
            int a = Integer.parseInt(content.substring(1, 3), 16);
            int r = Integer.parseInt(content.substring(3, 5), 16);
            int g = Integer.parseInt(content.substring(5, 7), 16);
            int b = Integer.parseInt(content.substring(7, 9), 16);
            color = new Color(r / 255D, g / 255D, b / 255D, a / 255D);
        }

        if (!element.elements().isEmpty()) return null;
        else return color;
    }
}
