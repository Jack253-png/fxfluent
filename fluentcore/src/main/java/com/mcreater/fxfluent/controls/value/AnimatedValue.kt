package com.mcreater.fxfluent.controls.value

import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.util.Duration


class AnimatedValue<T>(start: T, duration: Duration) {
    val property: ObjectProperty<T> = SimpleObjectProperty()
    private var timeline: Timeline? = null
    private val duration: Duration

    init {
        property.set(start)
        this.duration = duration
    }

    fun setValue(value: T) {
        if (timeline != null) timeline!!.stop()
        property.set(value)
    }

    fun updateValue(value: T) {
        if (timeline != null) timeline!!.stop()
        timeline = Timeline(
            KeyFrame(
                Duration.ZERO,
                KeyValue(
                    property,
                    property.get()
                )
            ),
            KeyFrame(
                duration,
                KeyValue(
                    property,
                    value
                )
            )
        )
        timeline!!.cycleCount = 1
        timeline!!.isAutoReverse = false
        timeline!!.delay = Duration.ZERO
        timeline!!.playFromStart()
    }
}
