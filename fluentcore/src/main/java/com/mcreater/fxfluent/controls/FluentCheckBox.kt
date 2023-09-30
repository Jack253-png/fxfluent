package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.*
import com.mcreater.fxfluent.controls.skin.FluentCheckBoxSkin
import com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.Companion.BG_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.Companion.BG_PRESSED_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.Companion.BRD_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.Companion.BRD_PRESSED_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.Companion.FG_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.Companion.GLY_KEY_MAP
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager.Companion.getDict
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.control.CheckBox
import javafx.scene.control.Skin
import javafx.scene.layout.CornerRadii


class FluentCheckBox : CheckBox, CornerRadiusable, Backgroundable, Foregroundable, Borderable,
    Glyphable, Dictable, Themeable {
    private var theme: AppColorTheme? = AppColorTheme.SYSTEM

    constructor() {
        init()
    }

    constructor(c: String?) : super(c) {
        init()
    }

    private fun init() {
        styleClass.add("fluent-check-box")
    }

    override fun getUserAgentStylesheet(): String {
        return FluentButton::class.java.getClassLoader().getResource("css/FluentCheckBox.css")?.toString() ?: ""
    }

    override fun createDefaultSkin(): Skin<*> {
        return FluentCheckBoxSkin(this)
    }

    override val cornerRadii: CornerRadii
        get() = CornerRadii(5.0)
    override val backgroundRemap: StateMap
        get() = if (isSelected || isIndeterminate) BG_PRESSED_KEY_MAP else BG_KEY_MAP
    override val borderRemap: StateMap
        get() = if (isSelected || isIndeterminate) BRD_PRESSED_KEY_MAP else BRD_KEY_MAP
    override val foregroundRemap: StateMap
        get() = FG_KEY_MAP
    override val glyphRemap: StateMap
        get() = GLY_KEY_MAP
    override val resourceDict: ResourceDict
        get() = getDict(theme!!)

    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as FluentCheckBoxSkin).implUpdate()
    }
}

