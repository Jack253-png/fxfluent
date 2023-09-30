package com.mcreater.fxfluent.stage.controls

import com.mcreater.fxfluent.controls.FluentButton
import com.mcreater.fxfluent.controls.icon.FluentIcon
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.state.StateUtil
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop
import javafx.beans.property.ReadOnlyBooleanProperty
import javafx.scene.control.Button
import javafx.scene.paint.Color
import java.util.stream.Stream

class FluentCloseButton: Button() {
    init {
        init()
    }
    private var icon: FluentIcon? = null
    private fun init() {
        styleClass.add("fluent-close-button")
        setPrefSize(48.0, 34.0)
        setMaxSize(48.0, 34.0)
        setMinSize(48.0, 34.0)
        icon = FluentIcon('\uE8BB')
        graphic = icon
        icon!!.styleClass.add("fluent-icon")
        icon!!.textFill = if (SystemThemeLoop.isDark()) Color.WHITE else Color.BLACK
        Stream.of(
            hoverProperty(),
            pressedProperty(),
            focusedProperty(),
            disabledProperty()
        ).forEach { a: ReadOnlyBooleanProperty ->
            a.addListener(
                NewValueListener {
                    when (StateUtil.genState(isDisabled, isHover, isPressed, isFocused)) {
                        StateType.FOCUS, StateType.NONE -> icon!!.textFill =
                            if (SystemThemeLoop.isDark()) Color.WHITE else Color.BLACK

                        StateType.HOVER -> icon!!.textFill = Color.WHITE
                        StateType.PRESS, StateType.DISABLE -> icon!!.textFill = Color(
                            1.0,
                            1.0,
                            1.0,
                            0.7
                        )
                    }
                })
        }
    }

    override fun getUserAgentStylesheet(): String {
        return FluentButton::class.java.getClassLoader()
            .getResource(if (SystemThemeLoop.isDark()) "css/FluentCloseButtonDark.css" else "css/FluentCloseButtonLight.css")
            ?.toString() ?: ""
    }
}