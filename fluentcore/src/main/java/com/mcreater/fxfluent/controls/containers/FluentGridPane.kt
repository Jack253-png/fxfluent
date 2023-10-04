package com.mcreater.fxfluent.controls.containers

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.abstractions.SystemThemeListenable
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.mcreater.fxfluent.xaml.XamlManager
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.application.Platform
import javafx.scene.Node
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.util.Duration


open class FluentGridPane : GridPane, SystemThemeListenable {
    private val backgroundColor: AnimatedValue<AbstractColorBrush> = AnimatedValue(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    var appColorThemeOverride: AppColorTheme = AppColorTheme.SYSTEM
        set(value) {
            field = value
            Platform.runLater { implUpdate(field) }
        }
    constructor() {
        initialize()
    }

    constructor(vararg var1: Node?) {
        children.addAll(*var1)
        initialize()
    }

    private fun initialize() {
        super.init()
        backgroundColor.property.addListener (NewValueListener<AbstractColorBrush> {
            it.accept(this, BrushUtil.backgroundFill(CornerRadii(4.0)))
        })
    }

    override fun filterTheme(input: AppColorTheme): AppColorTheme {
        return if (appColorThemeOverride == AppColorTheme.SYSTEM) input else appColorThemeOverride
    }

    override fun getNode(): Collection<Node> {
        return children
    }

    override fun onChange(input: AppColorTheme) {
        backgroundColor.updateValue(XamlManager.find("SolidBackgroundFillColorBaseBrush", input))
    }
}

