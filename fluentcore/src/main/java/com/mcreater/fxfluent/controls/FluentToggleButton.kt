package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.*
import com.mcreater.fxfluent.controls.skin.FluentToggleButtonSkin
import com.mcreater.fxfluent.controls.value.ControlMaps.Button.Companion.BG_ACCENT_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.Button.Companion.BG_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.Button.Companion.BRD_BOTTOM_ACCENT_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.Button.Companion.BRD_BOTTOM_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.Button.Companion.FG_ACCENT_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.Button.Companion.FG_KEY_MAP
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager.Companion.getDict
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.Node
import javafx.scene.control.Skin
import javafx.scene.control.ToggleButton
import javafx.scene.layout.CornerRadii


open class FluentToggleButton : ToggleButton, CornerRadiusable, Backgroundable, Foregroundable,
    Borderable, Dictable, Themeable {
    private var theme: AppColorTheme? = AppColorTheme.SYSTEM

    constructor() {
        init()
    }

    constructor(var1: String?) : super(var1) {
        init()
    }

    constructor(var1: String?, var2: Node?) : super(var1, var2) {
        init()
    }

    private fun init() {
        styleClass.add("fluent-toggle-button")
    }

    override fun getUserAgentStylesheet(): String {
        return FluentToggleButton::class.java.getClassLoader().getResource("css/FluentToggleButton.css")?.toString() ?: ""
    }

    override fun createDefaultSkin(): Skin<*> {
        return FluentToggleButtonSkin(this)
    }

    override val cornerRadii: CornerRadii
        get() = CornerRadii(4.0)
    override val backgroundRemap: StateMap
        get() = if (isSelected) BG_ACCENT_KEY_MAP else BG_KEY_MAP
    override val borderRemap: StateMap
        get() = if (isSelected) BRD_BOTTOM_ACCENT_KEY_MAP else BRD_BOTTOM_KEY_MAP
    override val foregroundRemap: StateMap
        get() = if (isSelected) FG_ACCENT_KEY_MAP else FG_KEY_MAP
    override val resourceDict: ResourceDict
        get() = getDict(theme!!)

    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as SkinUpdatable).implUpdate()
    }
}

