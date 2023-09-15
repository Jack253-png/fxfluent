package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.xaml.XAMLHelper;

public class SolidColorBrushContentTag extends SimpleContentTag<SolidColorBrush> {
    public SolidColorBrush toObject() {
        String content = getElement().attributeValue("Color");
        if (content == null) return null;
        String opacity = getElement().attributeValue("Opacity");
        double opacityD = opacity == null ? 1.0 : Double.parseDouble(opacity);

        return new SolidColorBrush(XAMLHelper.parseAnyColor(dict, content, opacityD));
    }
}
