package com.mcreater.fxfluent.brush

import javafx.scene.paint.Color


class SolidColorBrush(color: Color?) : AbstractColorBrush() {
    private val color: Color

    init {
        this.color = color ?: Color.TRANSPARENT
    }

    override fun toString(): String {
        return getPaint().toString()
    }

    override fun getPaint(): Color? {
        return color
    }
}

