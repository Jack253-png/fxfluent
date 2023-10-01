package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentSlider
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil
import com.mcreater.fxfluent.util.ControlUtil.Companion.findControlInSkin
import com.mcreater.fxfluent.util.listeners.NewValueListener
import javafx.geometry.Orientation
import javafx.scene.control.skin.SliderSkin
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.util.Duration
import kotlin.math.max

open class FluentSliderSkin(private val control: FluentSlider) : SliderSkin(control) {
    private val backgroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val trackColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private var trackTop: StackPane? = null
    private val track
        get() = findControlInSkin(this, "track")
    private val thumb
        get() = findControlInSkin(this, "thumb")
    private val tickLine
        get() = findControlInSkin(this, "axis")
    init {
        trackTop = StackPane()
        backgroundColor.property.addListener(
            NewValueListener { it.accept(track, BrushUtil.backgroundFill(CornerRadii(8.0))) }
        )
        trackColor.property.addListener(
            NewValueListener { it.accept(trackTop, BrushUtil.backgroundFill(CornerRadii(8.0))) }
        )
        children.add(2, trackTop)
        updateComponents()
    }
    private fun updateComponents() {
        backgroundColor.updateValue(control.backgroundRemap?.get(if (control.isDisable) StateType.DISABLE else StateType.NONE)?.apply(control.resourceDict)!!)
        trackColor.updateValue(control.backgroundRemap?.get(StateType.FOCUS)?.apply(control.resourceDict)!!)
    }
    fun implUpdate() {
        updateComponents()
    }

    override fun layoutChildren(x: Double, y: Double, w: Double, h: Double) {
        super.layoutChildren(x, y, w, h)

        val thumbWidth = snapSizeX(thumb!!.prefWidth(-1.0))
        val thumbHeight = snapSizeY(thumb!!.prefHeight(-1.0))
        val trackRadius: Double =
            (if (track!!.background == null) 0 else if (track!!.background.fills.size > 0) track!!.background.fills[0].radii.topLeftHorizontalRadius else 0).toDouble()

        if (skinnable.orientation == Orientation.HORIZONTAL) {
            val tickLineHeight: Double = (if (control.isShowTickMarks) tickLine!!.prefHeight(-1.0) else 0).toDouble()
            val trackHeight = snapSizeY(track!!.prefHeight(-1.0))
            val trackAreaHeight = max(trackHeight, thumbHeight)
            val totalHeightNeeded: Double = trackAreaHeight + (if (control.isShowTickMarks) 2 + tickLineHeight else 0).toDouble()
            val startY = y + (h - totalHeightNeeded) / 2 // center slider in available height vertically

            val trackLength = snapSizeX(w - thumbWidth)
            val trackStart = snapPositionX(x + thumbWidth / 2)
            val trackTop: Double = (startY + (trackAreaHeight - trackHeight) / 2).toInt().toDouble()
            this.trackTop?.resizeRelocate(
                (trackStart - trackRadius).toInt().toDouble(),
                trackTop,
                (trackLength + trackRadius + trackRadius).toInt().toDouble() * (control.value / control.max),
                trackHeight
            )
        }
        else {
            val tickLineWidth: Double = if (control.isShowTickMarks) tickLine!!.prefWidth(-1.0) else 0.0
            val trackWidth = snapSizeX(track!!.prefWidth(-1.0))
            val trackAreaWidth = max(trackWidth, thumbWidth)
            val totalWidthNeeded: Double = trackAreaWidth + (if (control.isShowTickMarks) 2 + tickLineWidth else 0).toDouble()
            val startX = x + (w - totalWidthNeeded) / 2 // center slider in available width horizontally

            val trackLength = snapSizeY(h - thumbHeight)
            val trackStart = snapPositionY(y + thumbHeight / 2)
            val trackLeft = (startX + (trackAreaWidth - trackWidth) / 2).toInt().toDouble()

            this.trackTop?.resizeRelocate(
                trackLeft,
                (trackStart - trackRadius).toInt().toDouble(),
                trackWidth,
                (trackLength + trackRadius + trackRadius).toInt().toDouble() * (control.value / control.max)
            )
        }
    }
}