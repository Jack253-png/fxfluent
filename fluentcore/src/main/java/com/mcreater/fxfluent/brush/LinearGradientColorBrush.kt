package com.mcreater.fxfluent.brush

import javafx.scene.paint.LinearGradient


class LinearGradientColorBrush(private val gradient: LinearGradient) : AbstractColorBrush() {
    override fun getPaint(): LinearGradient? {
        return gradient
    }
}

