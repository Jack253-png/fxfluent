package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.Backgroundable
import com.mcreater.fxfluent.controls.abstractions.Dictable
import com.mcreater.fxfluent.controls.abstractions.Themeable
import com.mcreater.fxfluent.controls.skin.FluentSliderSkin
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.control.Slider

open class FluentSlider: Slider, Backgroundable, Themeable, Dictable {
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
        get() = object : StateMap() {
            init {
                put(
                    StateType.NONE
                ) { d: ResourceDict? ->
                    XamlManager.find(
                        "ControlStrongFillColorDefaultBrush",
                        d
                    )
                }
                put(StateType.DISABLE
                ) { d: ResourceDict? ->
                    XamlManager.find(
                        "ControlStrongFillColorDisabledBrush",
                        d
                    )
                }
                put(StateType.FOCUS
                ) { d: ResourceDict? ->
                    XamlManager.find(
                        "AccentTextFillColorTertiaryBrush",
                        d
                    )
                }
                put(StateType.HOVER
                ) { d: ResourceDict? ->
                    XamlManager.find(
                        "SolidBackgroundFillColorQuarternaryBrush",
                        d
                    )
                }
                put(StateType.PRESS
                ) { d: ResourceDict? ->
                    XamlManager.find(
                        "ControlStrokeColorSecondary",
                        d
                    )
                }
            }
        }
    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as FluentSliderSkin).implUpdate()
    }
}