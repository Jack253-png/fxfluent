package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.*
import com.mcreater.fxfluent.controls.skin.FluentHyperlinkButtonSkin
import com.mcreater.fxfluent.controls.value.ControlMaps.HyperLinkButton.Companion.BG_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.HyperLinkButton.Companion.FG_KEY_MAP
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager.Companion.getDict
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.Node
import javafx.scene.control.Hyperlink
import javafx.scene.control.Skin
import javafx.scene.layout.CornerRadii


open class FluentHyperlinkButton : Hyperlink, CornerRadiusable, Backgroundable, Foregroundable, Dictable,
    Themeable {
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
        styleClass.add("fluent-hyperlink-button")
    }

    override fun getUserAgentStylesheet(): String {
        return FluentToggleButton::class.java.getClassLoader().getResource("css/FluentHyperlinkButton.css")?.toString() ?: ""
    }

    override val backgroundRemap: StateMap
        get() = BG_KEY_MAP
    override val foregroundRemap: StateMap
        get() = FG_KEY_MAP

    override fun createDefaultSkin(): Skin<*> {
        return FluentHyperlinkButtonSkin(this)
    }

    override val cornerRadii: CornerRadii
        get() = CornerRadii(6.0)
    override val resourceDict: ResourceDict
        get() = getDict(theme!!)

    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as SkinUpdatable).implUpdate()
    }
}

