package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentSlider
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.controls.state.StateUtil
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.BrushUtil
import com.mcreater.fxfluent.util.ControlUtil.Companion.findControlInSkin
import com.mcreater.fxfluent.util.interpolatables.Interpolators
import com.mcreater.fxfluent.util.listeners.NewValueListener
import javafx.beans.property.ObjectProperty
import javafx.beans.property.ReadOnlyBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.geometry.Orientation
import javafx.scene.control.Tooltip
import javafx.scene.control.skin.SliderSkin
import javafx.scene.layout.BorderWidths
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration
import java.util.stream.Stream
import kotlin.math.max

open class FluentSliderSkin(private val control: FluentSlider) : SliderSkin(control) {
    private val stateThumb: ObjectProperty<StateType?> = SimpleObjectProperty(null)
    private val stateTrack: ObjectProperty<StateType?> = SimpleObjectProperty(null)
    private val backgroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val trackColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val thumbInternalSize =
        AnimatedValue(5.0, Duration.millis(83.0), Interpolators.sinusoidalEaseboth)
    private var trackTop: StackPane? = null
    private var thumbInternal: Circle? = null
    private val track
        get() = findControlInSkin(this, "track")
    private val thumb: StackPane?
        get() = findControlInSkin(this, "thumb") as StackPane?
    private val tickLine
        get() = findControlInSkin(this, "axis")
    init {
        Stream.of(
            thumb!!.hoverProperty(),
            thumb!!.pressedProperty(),
            thumb!!.focusedProperty(),
            thumb!!.disabledProperty()
        ).forEach { a: ReadOnlyBooleanProperty ->
            a.addListener { _: ObservableValue<out Boolean?>?, _: Boolean?, _: Boolean? ->
                updateState()
            }
        }
        Stream.of(
            control.hoverProperty(),
            control.pressedProperty(),
            control.focusedProperty(),
            control.disabledProperty()
        ).forEach { a: ReadOnlyBooleanProperty ->
            a.addListener { _: ObservableValue<out Boolean?>?, _: Boolean?, _: Boolean? ->
                updateState()
            }
        }
        trackTop = StackPane()
        stateTrack.addListener(NewValueListener { updateComponentsTrack(it!!) })
        stateThumb.addListener(NewValueListener { updateComponentsThumb(it!!) })
        backgroundColor.property.addListener(
            NewValueListener { it.accept(track, BrushUtil.backgroundFill(CornerRadii(8.0))) }
        )
        trackColor.property.addListener(
            NewValueListener { it.accept(trackTop, BrushUtil.backgroundFill(CornerRadii(8.0))) }
        )
        thumbInternalSize.property.addListener(NewValueListener { thumbInternal!!.radius = it })
        thumbInternal = Circle(0.0, 0.0, 5.0, Color.BLACK)
        thumb!!.children.add(thumbInternal)
        thumb!!.prefWidth = 20.0
        thumb!!.prefHeight = 20.0
        children.add(2, trackTop)
        updateState()
        updateComponents(stateTrack.get()!!, stateThumb.get()!!)
        Tooltip.install(thumb, Tooltip("Test"))
    }
    private fun updateState() {
        stateThumb.set(StateUtil.genState(thumb!!.isDisabled, thumb!!.isHover, thumb!!.isPressed, thumb!!.isFocused))
        stateTrack.set(StateUtil.genState(control.isDisabled, control.isHover, control.isPressed, control.isFocused))
    }
    private fun updateComponentsTrack(stateTrack: StateType) {
        BrushUtil.backgroundFill(CornerRadii(16.0)).accept(track, control.backgroundRemap?.get(stateTrack)?.apply(control.resourceDict)!!)
        trackColor.updateValue(control.foregroundRemap?.get(stateTrack)?.apply(control.resourceDict)!!)
    }
    private fun updateComponentsThumb(stateThumb: StateType) {
        thumbInternal?.fill = control.thumbRemap?.get(stateThumb)?.apply(control.resourceDict)!!.getPaint()
        BrushUtil.borderFill(null, CornerRadii(16.0), BorderWidths(0.5)).accept(thumb, control.thumbBorder?.apply(control.resourceDict)!!)
        BrushUtil.backgroundFill(CornerRadii(16.0)).accept(thumb, control.thumbOuter?.apply(control.resourceDict))
        thumbInternalSize.updateValue(
            if (stateThumb == StateType.HOVER) 7.0 else if (stateThumb == StateType.PRESS) 4.0 else 5.0
        )
    }
    private fun updateComponents(stateTrack: StateType, stateThumb: StateType) {
        updateComponentsTrack(stateTrack)
        updateComponentsThumb(stateThumb)
    }
    fun implUpdate() {
        updateComponents(stateTrack.get()!!, stateThumb.get()!!)
    }

    override fun layoutChildren(x: Double, y: Double, w: Double, h: Double) {
        super.layoutChildren(x, y, w, h)

        val thumbWidth = snapSizeX(thumb!!.prefWidth(-1.0))
        val thumbHeight = snapSizeY(thumb!!.prefHeight(-1.0))
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
                (trackStart).toInt().toDouble(),
                trackTop,
                (trackLength).toInt().toDouble() * (control.value / control.max),
                trackHeight
            )
            track!!.resizeRelocate(
                (trackStart).toInt().toDouble(),
                trackTop,
                (trackLength).toInt().toDouble(),
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
                (trackStart).toInt().toDouble(),
                trackWidth,
                (trackLength).toInt().toDouble() * (control.value / control.max)
            )
            track!!.resizeRelocate(
                trackLeft,
                (trackStart).toInt().toDouble(),
                trackWidth,
                (trackLength).toInt().toDouble()
            )
        }
    }
}