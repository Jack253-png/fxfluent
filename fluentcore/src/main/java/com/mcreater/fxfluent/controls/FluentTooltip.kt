package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.SkinUpdatable
import com.mcreater.fxfluent.controls.abstractions.Themeable
import com.mcreater.fxfluent.controls.skin.FluentTooltipSkin
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.scene.Node
import javafx.scene.control.Tooltip

open class FluentTooltip(str: String?) : Tooltip(str), Themeable {
    private var theme: AppColorTheme? = AppColorTheme.SYSTEM
    init {
        init()
    }
    constructor(): this(null)
    fun showCentered(n: Node, x: Double, y: Double) {
        show(n, x - (this.width / 4), y)
    }

    private fun init() {
        styleClass.add("fluent-tooltip")
    }

    override fun createDefaultSkin(): FluentTooltipSkin {
        return FluentTooltipSkin(this)
    }

    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as SkinUpdatable).implUpdate()
    }
}