package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.brush.SolidColorBrush;
import javafx.scene.paint.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolidColorBrushContentTag extends SimpleContentTag<SolidColorBrush> {
    private static final Pattern RESOURCE_REF_PATTERN = Pattern.compile("\\{StaticResource (?<refkey>.*)}");

    public SolidColorBrush toObject() {
        String content = getElement().attributeValue("Color");
        if (content == null) return null;

        Matcher matcher = RESOURCE_REF_PATTERN.matcher(content);
        if (matcher.find()) {
            ColorContentTag cntag = dict.foundTag(matcher.group("refkey"), ColorContentTag.class);
            return new SolidColorBrush(cntag != null ? cntag.toObject() : null);
        }
        else {
            Color color = Color.TRANSPARENT;
            if (content.startsWith("#") && content.length() == 9) {
                int a = Integer.parseInt(content.substring(1, 3), 16);
                int r = Integer.parseInt(content.substring(3, 5), 16);
                int g = Integer.parseInt(content.substring(5, 7), 16);
                int b = Integer.parseInt(content.substring(7, 9), 16);
                color = new Color(r / 255D, g / 255D, b / 255D, a / 255D);
            }

            return new SolidColorBrush(color);
        }
    }
}
