package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.Dictable
import com.mcreater.fxfluent.controls.abstractions.SkinUpdatable
import com.mcreater.fxfluent.controls.abstractions.Themeable
import com.mcreater.fxfluent.controls.skin.FluentTooltipSkin
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.interpolatables.Interpolators
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.Node
import javafx.scene.control.Tooltip
import javafx.scene.effect.BlurType
import javafx.scene.effect.DropShadow
import javafx.scene.layout.Background
import javafx.scene.layout.Border
import javafx.scene.paint.Color
import javafx.util.Duration

open class FluentTooltip(str: String?) : Tooltip(str), Themeable, Dictable {
    private var theme: AppColorTheme? = AppColorTheme.SYSTEM
    private val animatedOpacity: AnimatedValue<Double> =
        AnimatedValue(0.0, Duration.millis(250.0), Interpolators.quinticEaseboth)
    init {
        init()
    }
    constructor(): this(null)
    fun showCentered(n: Node, x: Double, y: Double) {
        (skin as SkinUpdatable?)?.implUpdate()
        show(n, x - (this.width / 2), y)
        animatedOpacity.updateValue(1.0)
    }
    fun closeCentered() {
        animatedOpacity.updateValue(0.0)
    }

    private fun init() {
        styleClass.clear()
        styleClass.add("fluent-tooltip")
        maxWidth = 320.0
        style = "-fx-padding: 6px 6px 6px 8px;"
        // bridge.padding = Insets(9.0, 6.0, 9.0, 8.0)
        opacityProperty().bind(animatedOpacity.property)
        bridge.effect = DropShadow(
            BlurType.THREE_PASS_BOX,
            Color(0.0, 0.0, 0.0, 0.5),
            10.0, 0.0, 0.0, 3.0
        )
    }

    fun accessBackground(b: Background) {
        bridge.background = b
    }
    fun accessBorder(b: Border) {
        bridge.border = b
    }

    override fun createDefaultSkin(): FluentTooltipSkin {
        return FluentTooltipSkin(this)
    }

    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as SkinUpdatable).implUpdate()
    }
}