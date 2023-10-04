package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentCheckBox
import com.mcreater.fxfluent.controls.abstractions.SkinUpdatable
import com.mcreater.fxfluent.controls.internals.CheckBoxCheckMark
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.state.StateUtil
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil.Companion.backgroundFill
import com.mcreater.fxfluent.util.BrushUtil.Companion.borderFill
import com.mcreater.fxfluent.util.BrushUtil.Companion.textFill
import com.mcreater.fxfluent.util.ControlUtil
import com.mcreater.fxfluent.util.listeners.NewValueListener
import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.scene.control.skin.CheckBoxSkin
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.util.Duration
import java.util.stream.Stream

open class FluentCheckBoxSkin(private val control: FluentCheckBox) : CheckBoxSkin(control), SkinUpdatable {
    private val state: ObjectProperty<StateType> = SimpleObjectProperty(null)
    private val backgroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(42.0))
    private val borderColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(42.0))
    private val textColor = AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(42.0))
    private val mark: CheckBoxCheckMark

    init {
        Stream.of(
            this.control.hoverProperty(),
            this.control.pressedProperty(),
            this.control.focusedProperty(),
            this.control.disabledProperty()
        ).forEach { a: ReadOnlyBooleanProperty ->
            a.addListener { _: ObservableValue<out Boolean?>?, _: Boolean?, _: Boolean? ->
                updateState()
            }
        }
        state.addListener(NewValueListener { updateComponents(it) })
        this.control.selectedProperty().addListener(NewValueListener { updateComponents(state.get()) })
        this.control.indeterminateProperty().addListener(NewValueListener { updateComponents(state.get()) })
        this.control.allowIndeterminateProperty().addListener(NewValueListener { updateComponents(state.get()) })
        val cornerRadii = control.cornerRadii
        backgroundColor.property.addListener(
            NewValueListener { it.accept(internalBox, backgroundFill(cornerRadii)) }
        )
        textColor.property.addListener(
            NewValueListener { it.accept(this.control, textFill()) }
        )
        borderColor.property.addListener(
            NewValueListener { it.accept(internalBox, borderFill(null, cornerRadii)) }
        )
        mark = CheckBoxCheckMark(this)
        internalBox!!.children.add(mark)
        control.selectedProperty()
            .addListener(NewValueListener { if (it) mark.startInAnimate() else mark.startOutAnimate() })
        updateState()
        updateComponents(state.get())
    }

    val internalBox: StackPane?
        get() = ControlUtil.findControlInSkin(this, "box") as StackPane?

    private fun updateState() {
        state.set(StateUtil.genState(control.isDisabled, control.isHover, control.isPressed, control.isFocused))
    }

    private fun updateComponents(type: StateType) {
        backgroundColor.updateValue(this.control.backgroundRemap[type]!!.apply(control.resourceDict)!!)
        borderColor.updateValue(this.control.borderRemap[type]!!.apply(control.resourceDict)!!)
        textColor.updateValue(this.control.foregroundRemap[type]!!.apply(control.resourceDict)!!)
    }

    val markColor: Paint
        get() = this.control.glyphRemap[state.get()]!!.apply(control.resourceDict)!!.getPaint()!!

    override fun implUpdate() {
        updateComponents(state.get())
    }
}