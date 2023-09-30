package com.mcreater.fxfluent.xaml.tags

import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.xaml.XamlHelper.Companion.parseAnyColor
import javafx.scene.paint.Color


class SolidColorBrushContentTag : SimpleContentTag<SolidColorBrush?>() {
    override fun toObject(): SolidColorBrush {
        val content = element!!.attributeValue("Color") ?: return SolidColorBrush(Color.TRANSPARENT)
        val opacity = element!!.attributeValue("Opacity")
        val opacityD = opacity?.toDouble() ?: 1.0
        return SolidColorBrush(parseAnyColor(dict, content, opacityD))
    }
}

