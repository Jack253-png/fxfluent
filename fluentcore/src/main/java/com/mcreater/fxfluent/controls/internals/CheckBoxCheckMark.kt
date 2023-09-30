package com.mcreater.fxfluent.controls.internals

import com.mcreater.fxfluent.controls.skin.FluentCheckBoxSkin
import com.mcreater.fxfluent.util.listeners.NewValueListener
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Line
import javafx.util.Duration


class CheckBoxCheckMark(private val skin: FluentCheckBoxSkin) : Pane() {
    private var line1: Line? = null
    private var line1StartX = 0.0
    private var line1StartY = 0.0
    private var line1EndX = 0.0
    private var line1EndY = 0.0
    private var line2: Line? = null
    private var line2StartX = 0.0
    private var line2StartY = 0.0
    private var line2EndX = 0.0
    private var line2EndY = 0.0
    private var intermin: Line? = null
    private var inAnimation = Timeline()
    private var outAnimation = Timeline()

    init {
        skin.internalBox.widthProperty().addListener(NewValueListener {
            updateSize(
                skin.internalBox
            )
        })
        skin.internalBox.heightProperty().addListener(NewValueListener {
            updateSize(
                skin.internalBox
            )
        })
        skin.skinnable.indeterminateProperty().addListener(NewValueListener { onUpdateComponent() })
    }

    private val fill: Paint
        get() = skin.markColor

    fun startInAnimate() {
        outAnimation.stop()
        inAnimation = Timeline(
            KeyFrame(
                Duration.ZERO,
                KeyValue(
                    line1!!.startXProperty(),
                    line1StartX
                ),
                KeyValue(
                    line1!!.startYProperty(),
                    line1StartY
                ),
                KeyValue(
                    line1!!.endXProperty(),
                    line1StartX
                ),
                KeyValue(
                    line1!!.endYProperty(),
                    line1StartY
                ),
                KeyValue(
                    line2!!.startXProperty(),
                    line2StartX
                ),
                KeyValue(
                    line2!!.startYProperty(),
                    line2StartY
                ),
                KeyValue(
                    line2!!.endXProperty(),
                    line2StartX
                ),
                KeyValue(
                    line2!!.endYProperty(),
                    line2StartY
                ),
                KeyValue(
                    line1!!.strokeProperty(),
                    Color.TRANSPARENT
                ),
                KeyValue(
                    line2!!.strokeProperty(),
                    Color.TRANSPARENT
                )
            ),
            KeyFrame(
                Duration.millis(10.0),
                KeyValue(
                    line1!!.strokeProperty(),
                    fill
                )
            ),
            KeyFrame(
                Duration.millis(210.0),
                KeyValue(
                    line1!!.endXProperty(),
                    line1EndX
                ),
                KeyValue(
                    line1!!.endYProperty(),
                    line1EndY
                ),
                KeyValue(
                    line2!!.strokeProperty(),
                    Color.TRANSPARENT
                )
            ),
            KeyFrame(
                Duration.millis(220.0),
                KeyValue(
                    line2!!.strokeProperty(),
                    fill
                )
            ),
            KeyFrame(
                Duration.millis(420.0),
                KeyValue(
                    line2!!.endXProperty(),
                    line2EndX
                ),
                KeyValue(
                    line2!!.endYProperty(),
                    line2EndY
                )
            )
        )
        inAnimation.isAutoReverse = false
        inAnimation.cycleCount = 1
        inAnimation.playFromStart()
    }

    fun startOutAnimate() {
        inAnimation.stop()
        outAnimation = Timeline(
            KeyFrame(
                Duration.ZERO,
                KeyValue(
                    line1!!.strokeProperty(),
                    fill
                ),
                KeyValue(
                    line2!!.strokeProperty(),
                    fill
                )
            ),
            KeyFrame(
                Duration.millis(10.0),
                KeyValue(
                    line1!!.strokeProperty(),
                    Color.TRANSPARENT
                ),
                KeyValue(
                    line2!!.strokeProperty(),
                    Color.TRANSPARENT
                )
            )
        )
        outAnimation.isAutoReverse = false
        outAnimation.cycleCount = 1
        outAnimation.playFromStart()
    }

    private fun updateSize(internal: StackPane) {
        if (prefWidth <= 0 || prefHeight <= 0) {
            setPrefSize(internal.width, internal.height)
            children.clear()
            line1StartX = 4.0
            line1StartY = internal.height / 2
            line1EndX = internal.width / 2 - 2
            line1EndY = internal.height - 6
            line1 = Line(line1StartX, line1StartY, line1EndX, line1EndY)
            line1!!.stroke = Color.TRANSPARENT
            line1!!.strokeWidth = 1.25
            children.add(line1)
            line2StartX = line1EndX
            line2StartY = line1EndY
            line2EndX = internal.width - 5
            line2EndY = 6.0
            line2 = Line(line2StartX, line2StartY, line2EndX, line2EndY)
            line2!!.stroke = Color.TRANSPARENT
            line2!!.strokeWidth = 1.25
            children.add(line2)
            intermin = Line(internal.width / 4, internal.height / 2, internal.width / 4 * 3, internal.height / 2)
            intermin!!.stroke = Color.TRANSPARENT
            intermin!!.strokeWidth = 1.75
            children.add(intermin)
            onUpdateComponent()
        }
    }

    fun onUpdateComponent() {
        line1!!.stroke = if (skin.skinnable.isSelected) fill else Color.TRANSPARENT
        line2!!.stroke = if (skin.skinnable.isSelected) fill else Color.TRANSPARENT
        intermin!!.stroke = if (skin.skinnable.isIndeterminate) fill else Color.TRANSPARENT
    }
}
