package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentSlider
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil
import com.mcreater.fxfluent.util.ControlUtil
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.sun.javafx.scene.control.skin.SliderSkin
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.util.Duration

open class FluentSliderSkin(private val control: FluentSlider) : SliderSkin(control) {
    private val backgroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val track
        get() = ControlUtil.findControlInSkin(this, "track")
    init {
        backgroundColor.property.addListener(
            NewValueListener { it.accept(track, BrushUtil.backgroundFill(CornerRadii(4.0))) }
        )
    }
    private fun updateComponents() {
        backgroundColor.updateValue(control.backgroundRemap?.get(if (control.isDisable) StateType.FOCUS else StateType.NONE)?.apply(control.resourceDict)!!)
    }
    fun implUpdate() {
        updateComponents()
    }
}