package com.mcreater.fxfluent.util;

import javafx.scene.text.Font;

import java.lang.reflect.Field;

public class DefaultFontPatcher {
    public static void patch(Font font) throws NoSuchFieldException, IllegalAccessException {
        System.setProperty("prism.lcdtext", "false");
        Field field = Font.class.getDeclaredField("DEFAULT");
        field.setAccessible(true);
        field.set(null, font);
    }
}
