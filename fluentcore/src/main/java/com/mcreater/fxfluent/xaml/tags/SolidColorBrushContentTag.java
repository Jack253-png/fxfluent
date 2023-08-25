package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.brush.SolidColorBrush;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mcreater.fxfluent.util.ColorUtil.parseXamlColor;

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
            return new SolidColorBrush(parseXamlColor(content));
        }
    }
}
