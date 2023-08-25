package com.mcreater.fxfluent.controls.value;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;

public class AnimatedValue<T> {
    public final ObjectProperty<T> property = new SimpleObjectProperty<>();
    private Timeline timeline;
    private final Duration duration;
    public AnimatedValue(T start, Duration duration) {
        property.set(start);
        this.duration = duration;
    }

    public void setValue(T value) {
        if (timeline != null) timeline.stop();
        property.set(value);
    }

    public void updateValue(T value) {
        if (timeline != null) timeline.stop();
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(
                                property,
                                property.get()
                        )
                ),
                new KeyFrame(
                        duration,
                        new KeyValue(
                                property,
                                value
                        )
                )
        );
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.setDelay(Duration.ZERO);
        timeline.playFromStart();
    }
}
