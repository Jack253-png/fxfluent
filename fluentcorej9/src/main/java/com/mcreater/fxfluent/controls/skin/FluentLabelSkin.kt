package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentLabel
import com.mcreater.fxfluent.controls.abstractions.SkinUpdatable
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil.Companion.textFill
import com.mcreater.fxfluent.util.listeners.NewValueListener
import javafx.scene.control.skin.LabelSkin
import javafx.scene.paint.Color
import javafx.util.Duration


open class FluentLabelSkin(private val label: FluentLabel) : LabelSkin(label), SkinUpdatable {
    private val foregroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(42.0))

    init {
        foregroundColor.property.addListener(
            NewValueListener { it.accept(label, textFill()) }
        )
    }

    private fun updateComponents() {
        foregroundColor.updateValue(
            label.foregroundRemap[StateType.NONE]!!.apply(label.resourceDict)!!
        )
    }

    override fun implUpdate() {
        updateComponents()
    }
}

