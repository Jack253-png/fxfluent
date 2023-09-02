package com.mcreater.fxfluent.controls.icon;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class FluentIcon extends Label {
    private static final Font FLUENT_ICON_FONT = Font.loadFont(FluentIcon.class.getClassLoader().getResourceAsStream("fonts/Segoe Fluent Icons.ttf"), 10);
    private static final Font FLUENT_ICON_FONT2 = Font.loadFont(FluentIcon.class.getClassLoader().getResourceAsStream("fonts/Segoe Fluent Icons.ttf"), 14);
    public FluentIcon(char c) {
        this(c, false);
    }
    public FluentIcon(char c, boolean isB) {
        super(String.valueOf(c));
        setFont(isB ? FLUENT_ICON_FONT2 : FLUENT_ICON_FONT);
    }
}
