package com.mcreater.fxfluent.xaml.tags

import com.mcreater.fxfluent.util.ColorUtil.Companion.parseXamlColor
import javafx.scene.paint.Color


open class ColorContentTag : SimpleContentTag<Color>() {
    override fun toObject(): Color {
        val content = element?.data.toString()
        return if (element?.elements()?.isNotEmpty()!!) Color.TRANSPARENT else parseXamlColor(content)
    }
}

