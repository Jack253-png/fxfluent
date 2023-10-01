package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.controls.FluentProgressBar
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.util.BrushUtil
import com.mcreater.fxfluent.util.ControlUtil
import com.mcreater.fxfluent.util.interpolatables.Interpolators
import com.mcreater.fxfluent.util.listeners.NewValueListener
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.binding.Bindings
import javafx.beans.value.ObservableValue
import javafx.geometry.Insets
import javafx.scene.control.skin.ProgressBarSkin
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.util.Duration

open class FluentProgressBarSkin(private val bar: FluentProgressBar) : ProgressBarSkin(bar) {
    private val internalBar = StackPane()
    private val internalBarClip = StackPane()
    private var normalIndeterminateAnimation: Timeline? = null

    init {
        bar.progressProperty().addListener(NewValueListener { updateComponents() })
        bar.indeterminateStateProperty().addListener(NewValueListener { updateComponents() })
        bar.indeterminateProperty().addListener(NewValueListener { updateComponents() })
        bar.widthProperty().addListener(NewValueListener { updateComponents() })
        bar.layoutXProperty().addListener(NewValueListener { updateComponents() })
        internalBar.translateX = -50.0
        internalBarClip.background = Background(
            BackgroundFill(
                Color.BLACK,
                CornerRadii(5.0),
                Insets.EMPTY
            )
        )
        bar.clip = internalBarClip
        bar.clipProperty().bind(
            Bindings.createObjectBinding(
                { internalBarClip })
        )
        children.addAll(internalBar)
        updateComponents()
        getBar()!!.opacity = 0.0
        this.track!!.prefHeight = 1.0
    }

    private fun getBar(): StackPane? {
        return ControlUtil.findControlInSkin(this, "bar") as StackPane?
    }

    private val track: StackPane?
        get() = ControlUtil.findControlInSkin(this, "track") as StackPane?

    private fun updateComponents() {
        var fg = StateType.NONE
        if (bar.isIndeterminate) {
            when (bar.indeterminateState) {
                FluentProgressBar.IndeterminateState.PAUSE -> fg = StateType.FOCUS
                FluentProgressBar.IndeterminateState.ERROR -> fg = StateType.HOVER
                FluentProgressBar.IndeterminateState.NORMAL -> {}
            }
        }
        bar.backgroundRemap[if (!bar.isIndeterminate) StateType.NONE else StateType.HOVER]!!.apply(bar.resourceDict)!!
            .accept(
                this.track,
                BrushUtil.backgroundFill(CornerRadii.EMPTY)
            )
        bar.foregroundRemap[fg]!!.apply(bar.resourceDict)!!.accept(
            internalBar,
            BrushUtil.backgroundFill(CornerRadii(3.0))
        )
        if (normalIndeterminateAnimation != null) {
            normalIndeterminateAnimation!!.stop()
            internalBar.translateX = 0.0
            internalBar.prefWidth = -1.0
            internalBar.scaleX = 1.0
        }
        if (bar.isIndeterminate) {
            if (bar.indeterminateState === FluentProgressBar.IndeterminateState.NORMAL) {
                normalIndeterminateAnimation = Timeline(
                    KeyFrame(
                        Duration.ZERO,
                        KeyValue(
                            internalBar.translateXProperty(),
                            -60,
                            Interpolators.sinusoidalEaseboth
                        ),
                        KeyValue(
                            internalBar.scaleXProperty(),
                            1,
                            Interpolators.sinusoidalEaseboth
                        )
                    ),
                    KeyFrame(
                        Duration.millis(1000.0),
                        KeyValue(
                            internalBar.translateXProperty(),
                            bar.width,
                            Interpolators.sinusoidalEaseboth
                        ),
                        KeyValue(
                            internalBar.scaleXProperty(),
                            1,
                            Interpolators.sinusoidalEaseboth
                        )
                    ),
                    KeyFrame(
                        Duration.millis(1000.0000000001),
                        KeyValue(
                            internalBar.translateXProperty(),
                            -60 * 1.5,
                            Interpolators.sinusoidalEaseboth
                        ),
                        KeyValue(
                            internalBar.scaleXProperty(),
                            1.5,
                            Interpolators.sinusoidalEaseboth
                        )
                    ),
                    KeyFrame(
                        Duration.millis(2250.0),
                        KeyValue(
                            internalBar.translateXProperty(),
                            bar.width + 60,
                            Interpolators.sinusoidalEaseboth
                        )
                    ),
                    KeyFrame(
                        Duration.millis(2600.0),
                        KeyValue(
                            internalBar.translateXProperty(),
                            bar.width + 60,
                            Interpolators.sinusoidalEaseboth
                        )
                    )
                )
                normalIndeterminateAnimation!!.isAutoReverse = false
                normalIndeterminateAnimation!!.cycleCount = Timeline.INDEFINITE
                normalIndeterminateAnimation!!.playFromStart()
            } else {
                normalIndeterminateAnimation = Timeline(
                    KeyFrame(
                        Duration.ZERO,
                        KeyValue(
                            internalBar.prefWidthProperty(),
                            60,
                            Interpolators.sinusoidalEaseboth
                        )
                    ),
                    KeyFrame(
                        Duration.millis(25.0),
                        KeyValue(
                            internalBar.prefWidthProperty(),
                            bar.width,
                            Interpolators.sinusoidalEaseboth
                        )
                    ),
                    KeyFrame(
                        Duration.millis(75.0),
                        KeyValue(
                            internalBar.prefWidthProperty(),
                            0,
                            Interpolators.sinusoidalEaseboth
                        )
                    ),
                    KeyFrame(
                        Duration.millis(350.0),
                        KeyValue(
                            internalBar.prefWidthProperty(),
                            bar.width,
                            Interpolators.sinusoidalEaseboth
                        )
                    )
                )
                internalBar.prefWidthProperty()
                    .addListener { _: ObservableValue<out Number?>?, _: Number?, _: Number? ->
                        val x = snappedLeftInset()
                        val y = snappedTopInset()
                        layoutInternalBar(x, y)
                    }
                normalIndeterminateAnimation!!.isAutoReverse = false
                normalIndeterminateAnimation!!.cycleCount = 1
                normalIndeterminateAnimation!!.playFromStart()
            }
        }
    }

    private fun layoutInternalBar(x: Double, y: Double) {
        if (!bar.isIndeterminate || bar.indeterminateState === FluentProgressBar.IndeterminateState.NORMAL) internalBar.resizeRelocate(
            x,
            y,
            getBar()!!.width,
            3.0
        ) else internalBar.resizeRelocate(x, y, internalBar.prefWidth, 3.0)
    }

    override fun layoutChildren(
        x: Double, y: Double,
        w: Double, h: Double
    ) {
        super.layoutChildren(x, y, w, h)
        this.track!!.resizeRelocate(x, y + 1, w, 1.0)
        internalBarClip.resizeRelocate(0.0, 0.0, w, 3.0)
        layoutInternalBar(x, y)
    }

    fun implUpdate() {
        updateComponents()
    }
}