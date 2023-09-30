package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentToggleButton
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.state.StateUtil.Companion.genState
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil
import com.mcreater.fxfluent.util.BrushUtil.Companion.backgroundFill
import com.mcreater.fxfluent.util.BrushUtil.Companion.borderFill
import com.mcreater.fxfluent.util.BrushUtil.Companion.textFill
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.sun.javafx.scene.control.skin.ToggleButtonSkin
import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.geometry.Insets
import javafx.scene.paint.Color
import javafx.util.Duration
import java.util.stream.Stream


class FluentToggleButtonSkin(private val button: FluentToggleButton) : ToggleButtonSkin(
    button
) {
    private val state: ObjectProperty<StateType?> = SimpleObjectProperty(null)
    private val backgroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val foregroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(42.0))
    private val upBorderColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val downBorderColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val leftBorderColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val rightBorderColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))

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
        button.selectedProperty().addListener(NewValueListener {
            updateComponents(
                state.get()!!
            )
        })
        val cornerRadii = button.cornerRadii
        state.addListener(NewValueListener<StateType?> { updateComponents(it) })
        backgroundColor.property.addListener(
            NewValueListener { newValue: AbstractColorBrush ->
                newValue.accept(
                    button,
                    backgroundFill(cornerRadii)
                )
            } as NewValueListener<AbstractColorBrush>
        )
        foregroundColor.property.addListener(
            NewValueListener { newValue: AbstractColorBrush ->
                newValue.accept(
                    button,
                    textFill()
                )
            } as NewValueListener<AbstractColorBrush>
        )
        upBorderColor.property.addListener(
            NewValueListener { newValue: AbstractColorBrush ->
                newValue.accept(
                    button,
                    borderFill(BrushUtil.BorderOrientation.TOP, cornerRadii)
                )
            } as NewValueListener<AbstractColorBrush>
        )
        downBorderColor.property.addListener(
            NewValueListener { newValue: AbstractColorBrush ->
                newValue.accept(
                    button,
                    borderFill(BrushUtil.BorderOrientation.BOTTOM, cornerRadii)
                )
            } as NewValueListener<AbstractColorBrush>
        )
        leftBorderColor.property.addListener(
            NewValueListener { newValue: AbstractColorBrush ->
                newValue.accept(
                    button,
                    borderFill(BrushUtil.BorderOrientation.LEFT, cornerRadii)
                )
            } as NewValueListener<AbstractColorBrush>
        )
        rightBorderColor.property.addListener(
            NewValueListener { newValue: AbstractColorBrush ->
                newValue.accept(
                    button,
                    borderFill(BrushUtil.BorderOrientation.RIGHT, cornerRadii)
                )
            } as NewValueListener<AbstractColorBrush>
        )
        updateState()
        updateComponents(state.get()!!)
        button.padding = Insets(5.0, 11.0, 5.0, 11.0)
    }

    private fun updateState() {
        state.set(genState(button.isDisabled, button.isHover, button.isPressed, button.isFocused))
    }

    private fun updateComponents(type: StateType) {
        backgroundColor.updateValue(button.backgroundRemap[type]!!.apply(button.resourceDict)!!)
        foregroundColor.updateValue(button.foregroundRemap[type]!!.apply(button.resourceDict)!!)
        Stream.of(
            upBorderColor, leftBorderColor, rightBorderColor
        ).forEach { a: AnimatedValue<AbstractColorBrush> ->
            a.updateValue(
                button.borderRemap[StateType.PRESS]!!.apply(button.resourceDict)!!
            )
        }
        downBorderColor.updateValue(button.borderRemap[type]!!.apply(button.resourceDict)!!)
    }

    fun implUpdate() {
        updateComponents(state.get()!!)
    }
}
