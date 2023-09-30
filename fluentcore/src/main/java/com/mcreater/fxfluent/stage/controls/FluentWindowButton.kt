package com.mcreater.fxfluent.stage.controls

import com.mcreater.fxfluent.controls.FluentButton
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop
import javafx.scene.control.Button

class FluentWindowButton: Button() {
    init {
        init()
    }
    private fun init() {
        styleClass.add("fluent-window-button")
        setPrefSize(48.0, 34.0)
        setMaxSize(48.0, 34.0)
        setMinSize(48.0, 34.0)
    }

    override fun getUserAgentStylesheet(): String {
        return FluentButton::class.java.getClassLoader()
            .getResource(if (SystemThemeLoop.isDark()) "css/FluentWindowButtonDark.css" else "css/FluentWindowButtonLight.css")
            ?.toString() ?: ""
    }
}