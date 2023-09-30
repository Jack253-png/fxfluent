package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentHyperlinkButton
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.state.StateUtil.Companion.genState
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil.Companion.backgroundFill
import com.mcreater.fxfluent.util.BrushUtil.Companion.textFill
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.sun.javafx.scene.control.skin.HyperlinkSkin
import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.geometry.Insets
import javafx.scene.paint.Color
import javafx.util.Duration
import java.util.stream.Stream


class FluentHyperlinkButtonSkin(private val button: FluentHyperlinkButton) : HyperlinkSkin(
    button
) {
    private val state: ObjectProperty<StateType?> = SimpleObjectProperty(null)
    private val backgroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val foregroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(42.0))

    init {
        Stream.of(
            button.hoverProperty(),
            button.pressedProperty(),
            button.focusedProperty(),
            button.disabledProperty()
        ).forEach { a: ReadOnlyBooleanProperty ->
            a.addListener { _: ObservableValue<out Boolean?>?, _: Boolean?, _: Boolean? ->
                updateState()
            }
        }
        state.addListener(NewValueListener { updateComponents(it!!) })
        val cornerRadii = button.cornerRadii
        backgroundColor.property.addListener(
            NewValueListener { it.accept(button, backgroundFill(cornerRadii)) }
        )
        foregroundColor.property.addListener(
            NewValueListener { it.accept(button, textFill()) }
        )
        updateState()
        updateComponents(state.get()!!)
        button.padding = Insets(2.0, 11.0, 2.0, 11.0)
    }

    private fun updateState() {
        state.set(genState(button.isDisabled, button.isHover, button.isPressed, button.isFocused))
    }

    private fun updateComponents(type: StateType) {
        backgroundColor.updateValue(button.backgroundRemap[type]!!.apply(button.resourceDict)!!)
        foregroundColor.updateValue(button.foregroundRemap[type]!!.apply(button.resourceDict)!!)
    }

    fun implUpdate() {
        updateComponents(state.get()!!)
    }
}