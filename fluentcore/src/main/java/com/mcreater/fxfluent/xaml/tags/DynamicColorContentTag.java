package com.mcreater.fxfluent.xaml.tags;

import javafx.scene.paint.Color;

import java.util.function.Supplier;

public class DynamicColorContentTag extends ColorContentTag {
    private final Supplier<Color> consumer;
    private final String key;
    public DynamicColorContentTag(String key, Supplier<Color> consumer) {
        this.consumer = consumer;
        this.key = key;
    }
    public Color toObject() {
        return consumer.get();
    }

    public String getKey() {
        return key;
    }
}
