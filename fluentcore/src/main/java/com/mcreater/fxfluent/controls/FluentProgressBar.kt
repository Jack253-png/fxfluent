package com.mcreater.fxfluent.controls

import com.mcreater.fxfluent.controls.abstractions.Backgroundable
import com.mcreater.fxfluent.controls.abstractions.Dictable
import com.mcreater.fxfluent.controls.abstractions.Foregroundable
import com.mcreater.fxfluent.controls.abstractions.Themeable
import com.mcreater.fxfluent.controls.skin.FluentProgressBarSkin
import com.mcreater.fxfluent.controls.value.ControlMaps.ProgressBar.Companion.BG_KEY_MAP
import com.mcreater.fxfluent.controls.value.ControlMaps.ProgressBar.Companion.FG_KEY_MAP
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager.Companion.getDict
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ProgressBar
import javafx.scene.control.Skin


class FluentProgressBar : ProgressBar, Backgroundable, Foregroundable, Dictable, Themeable {
    private var theme: AppColorTheme? = AppColorTheme.SYSTEM

    enum class IndeterminateState {
        NORMAL,
        PAUSE,
        ERROR
    }

    private val indeterminateState: ObjectProperty<IndeterminateState> = SimpleObjectProperty(IndeterminateState.NORMAL)
    fun indeterminateState(): ObjectProperty<IndeterminateState> {
        return indeterminateState
    }

    fun getIndeterminateState(): IndeterminateState {
        return indeterminateState().get()
    }

    fun setIndeterminateState(state: IndeterminateState) {
        indeterminateState().set(state)
    }

    constructor() {
        init()
    }

    constructor(progress: Double) : super(progress) {
        init()
    }

    private fun init() {
        styleClass.add("fluent-progress-bar")
    }

    override fun getUserAgentStylesheet(): String {
        return FluentProgressBar::class.java.getClassLoader().getResource("css/FluentProgressBar.css")?.toString() ?: ""
    }

    override fun createDefaultSkin(): Skin<*> {
        return FluentProgressBarSkin(this)
    }

    override val backgroundRemap: StateMap
        get() = BG_KEY_MAP
    override val foregroundRemap: StateMap
        get() = FG_KEY_MAP
    override val resourceDict: ResourceDict
        get() = getDict(theme!!)

    override fun onUpdateTheme(theme: AppColorTheme?) {
        this.theme = theme
        (skin as FluentProgressBarSkin).implUpdate()
    }
}