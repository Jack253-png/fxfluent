package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.controls.abstractions.*
import com.mcreater.fxfluent.controls.skin.FluentSliderSkin
import com.mcreater.fxfluent.controls.value.ControlMaps.Slider.Companion.BG_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.Slider.Companion.FG_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.Slider.Companion.THB_KEY_MAP
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.control.Slider
import java.util.function.Function

open class FluentSlider: Slider, Backgroundable, Foregroundable, Thumbable, Themeable, Dictable {
    private var theme: AppColorTheme? = AppColorTheme.SYSTEM
    constructor() {
        init()
    }
    constructor(var1: Double, var3: Double, var5: Double) : super(var1, var3, var5) {
        init()
    }
    private fun init() {
        styleClass.add("fluent-slider")
    }

    override fun getUserAgentStylesheet(): String {
        return FluentButton::class.java.getClassLoader().getResource("css/FluentSlider.css")?.toString() ?: ""
    }

    override fun createDefaultSkin(): FluentSliderSkin {
        return FluentSliderSkin(this)
    }

    override val backgroundRemap: StateMap?
        get() = BG_KEY_MAP
    override val foregroundRemap: StateMap?
        get() = FG_KEY_MAP
    override val thumbRemap: StateMap?
        get() = THB_KEY_MAP
    override val thumbBorder: Function<ResourceDict?, AbstractColorBrush?>?
        get() = Function {
            XamlManager.find(
                "SliderThumbBorderBrush",
                it
            )
        }
    override val thumbOuter: Function<ResourceDict?, AbstractColorBrush?>?
        get() = Function {
            XamlManager.find(
                "SliderOuterThumbBackground",
                it
            )
        }
    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as FluentSliderSkin).implUpdate()
    }
}