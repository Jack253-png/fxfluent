package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.Dictable
import com.mcreater.fxfluent.controls.abstractions.Foregroundable
import com.mcreater.fxfluent.controls.abstractions.Themeable
import com.mcreater.fxfluent.controls.skin.FluentLabelSkin
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager
import com.mcreater.fxfluent.xaml.XamlManager.Companion.getDict
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.Node
import javafx.scene.control.Label
import java.util.function.Function


class FluentLabel : Label, Foregroundable, Dictable, Themeable {
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
        styleClass.add("fluent-label")
    }

    override fun createDefaultSkin(): FluentLabelSkin {
        return FluentLabelSkin(this)
    }

    override val foregroundRemap: StateMap
        get() = object : StateMap() {
            init {
                put(StateType.NONE,
                    Function { d: ResourceDict? ->
                        XamlManager.find(
                            "TextFillColorPrimaryBrush",
                            d
                        )
                    })
            }
        }
    override val resourceDict: ResourceDict
        get() {
            return getDict((theme)!!)
        }

    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as FluentLabelSkin).implUpdate()
    }
}

