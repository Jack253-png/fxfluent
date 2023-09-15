package com.mcreater.fxfluent.xaml;

import com.mcreater.fxfluent.xaml.tags.AbstractContentTag;
import com.mcreater.fxfluent.xaml.tags.ColorContentTag;
import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mcreater.fxfluent.util.ColorUtil.parseXamlColor;

public class XAMLHelper {
    private static final Pattern RESOURCE_REF_PATTERN = Pattern.compile("\\{StaticResource (?<refkey>.*)}");
    private static final Pattern RESOURCE_REF_PATTERN2 = Pattern.compile("\\{ThemeResource (?<refkey>.*)}");
    public static <T extends AbstractContentTag<?>> T relativeTag(ResourceDict dict, String content, Class<T> clazz) {
        Matcher matcher = RESOURCE_REF_PATTERN.matcher(content);
        Matcher matcher1 = RESOURCE_REF_PATTERN2.matcher(content);
        Matcher result;
        boolean matchf = matcher.find();
        boolean match1f = matcher1.find();
        result = matchf ? matcher : (match1f ? matcher1 : null);
        if (matchf || match1f) return dict.foundTag(result.group("refkey"), clazz);
        else return null;
    }
    public static Color parseAnyColor(ResourceDict dict, String content, double opacity) {
        ColorContentTag tag = XAMLHelper.relativeTag(dict, content, ColorContentTag.class);
        return Optional.ofNullable(tag)
                .map(ColorContentTag::toObject)
                .map(color -> new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity() * opacity))
                .orElseGet(() -> {
                    Color color = parseXamlColor(content);
                    return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity() * opacity);
                });
    }
}
