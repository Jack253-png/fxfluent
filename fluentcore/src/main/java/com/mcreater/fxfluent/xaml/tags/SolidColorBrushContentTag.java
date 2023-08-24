package com.mcreater.fxfluent.xaml.tags;

import javafx.scene.paint.Color;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolidColorBrushContentTag extends SimpleContentTag<Supplier<Color>> {
    private static final Pattern RESOURCE_REF_PATTERN = Pattern.compile("\\{StaticResource (?<refkey>.*)}");

    public Supplier<Color> toObject() {
        String content = getElement().attributeValue("Color");
        if (content == null) return null;

        Matcher matcher = RESOURCE_REF_PATTERN.matcher(content);
        if (matcher.find()) {
            ColorContentTag cntag = dict.foundTag(matcher.group("refkey"), ColorContentTag.class);
            return cntag != null ? cntag::toObject : () -> null;
        }
        else return () -> Color.valueOf(content);
    }
}