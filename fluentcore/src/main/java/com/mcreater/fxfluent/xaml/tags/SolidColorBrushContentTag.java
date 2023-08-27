package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.brush.SolidColorBrush;
import javafx.scene.paint.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mcreater.fxfluent.util.ColorUtil.parseXamlColor;

public class SolidColorBrushContentTag extends SimpleContentTag<SolidColorBrush> {
    private static final Pattern RESOURCE_REF_PATTERN = Pattern.compile("\\{StaticResource (?<refkey>.*)}");
    private static final Pattern RESOURCE_REF_PATTERN2 = Pattern.compile("\\{ThemeResource (?<refkey>.*)}");

    public SolidColorBrush toObject() {
        String content = getElement().attributeValue("Color");
        if (content == null) return null;
        String opacity = getElement().attributeValue("Opacity");
        double opacityD = opacity == null ? 1.0 : Double.parseDouble(opacity);

        Matcher matcher = RESOURCE_REF_PATTERN.matcher(content);
        Matcher matcher1 = RESOURCE_REF_PATTERN2.matcher(content);
        Matcher result;
        boolean matchf = matcher.find();
        boolean match1f = matcher1.find();
        result = matchf ? matcher : (match1f ? matcher1 : null);
        Color color;
        if (matchf || match1f) {
            ColorContentTag cntag = dict.foundTag(result.group("refkey"), ColorContentTag.class);
            color = cntag != null ? cntag.toObject() : Color.TRANSPARENT;
        }
        else color = parseXamlColor(content);

        return new SolidColorBrush(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity() * opacityD));
    }
}
