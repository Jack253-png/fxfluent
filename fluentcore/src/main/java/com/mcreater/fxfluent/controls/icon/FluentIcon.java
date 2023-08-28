package com.mcreater.fxfluent.controls.icon;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class FluentIcon extends Label {
    private static final Font FLUENT_ICON_FONT = Font.loadFont(FluentIcon.class.getClassLoader().getResourceAsStream("fonts/Segoe Fluent Icons.ttf"), 10);
    public FluentIcon(char c) {
        super(String.valueOf(c));
        setFont(FLUENT_ICON_FONT);
    }
}
