package com.mcreater.fxfluent.controls.internals;

import com.mcreater.fxfluent.controls.skin.FluentCheckBoxSkin;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class CheckBoxCheckMark extends Pane {
    private Line line1;
    private double line1StartX;
    private double line1StartY;
    private double line1EndX;
    private double line1EndY;
    private Line line2;
    private double line2StartX;
    private double line2StartY;
    private double line2EndX;
    private double line2EndY;
    private Line intermin;
    private Timeline inAnimation = new Timeline();
    private Timeline outAnimation = new Timeline();
    private final FluentCheckBoxSkin skin;
    public CheckBoxCheckMark(FluentCheckBoxSkin skin) {
        this.skin = skin;
        skin.getInternalBox().widthProperty().addListener((NewValueListener<Number>) t1 -> updateSize(skin.getInternalBox()));
        skin.getInternalBox().heightProperty().addListener((NewValueListener<Number>) t1 -> updateSize(skin.getInternalBox()));

        this.skin.getSkinnable().indeterminateProperty().addListener((NewValueListener<Boolean>) t1 -> this.onUpdateComponent());
    }

    private Paint getFill() {
        return this.skin.getMarkColor();
    }

    public void startInAnimate() {
        outAnimation.stop();
        inAnimation = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(
                                line1.startXProperty(),
                                line1StartX
                        ),
                        new KeyValue(
                                line1.startYProperty(),
                                line1StartY
                        ),
                        new KeyValue(
                                line1.endXProperty(),
                                line1StartX
                        ),
                        new KeyValue(
                                line1.endYProperty(),
                                line1StartY
                        ),
                        new KeyValue(
                                line2.startXProperty(),
                                line2StartX
                        ),
                        new KeyValue(
                                line2.startYProperty(),
                                line2StartY
                        ),
                        new KeyValue(
                                line2.endXProperty(),
                                line2StartX
                        ),
                        new KeyValue(
                                line2.endYProperty(),
                                line2StartY
                        ),
                        new KeyValue(
                                line1.strokeProperty(),
                                Color.TRANSPARENT
                        ),
                        new KeyValue(
                                line2.strokeProperty(),
                                Color.TRANSPARENT
                        )
                ),
                new KeyFrame(
                        Duration.millis(10),
                        new KeyValue(
                                line1.strokeProperty(),
                                getFill()
                        )
                ),
                new KeyFrame(
                        Duration.millis(210),
                        new KeyValue(
                                line1.endXProperty(),
                                line1EndX
                        ),
                        new KeyValue(
                                line1.endYProperty(),
                                line1EndY
                        ),
                        new KeyValue(
                                line2.strokeProperty(),
                                Color.TRANSPARENT
                        )
                ),
                new KeyFrame(
                        Duration.millis(220),
                        new KeyValue(
                                line2.strokeProperty(),
                                getFill()
                        )
                ),
                new KeyFrame(
                        Duration.millis(420),
                        new KeyValue(
                                line2.endXProperty(),
                                line2EndX
                        ),
                        new KeyValue(
                                line2.endYProperty(),
                                line2EndY
                        )
                )
        );
        inAnimation.setAutoReverse(false);
        inAnimation.setCycleCount(1);
        inAnimation.playFromStart();
    }

    public void startOutAnimate() {
        inAnimation.stop();
        outAnimation = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(
                                line1.strokeProperty(),
                                getFill()
                        ),
                        new KeyValue(
                                line2.strokeProperty(),
                                getFill()
                        )
                ),
                new KeyFrame(
                        Duration.millis(10),
                        new KeyValue(
                                line1.strokeProperty(),
                                Color.TRANSPARENT
                        ),
                        new KeyValue(
                                line2.strokeProperty(),
                                Color.TRANSPARENT
                        )
                )
        );
        outAnimation.setAutoReverse(false);
        outAnimation.setCycleCount(1);
        outAnimation.playFromStart();
    }

    private void updateSize(StackPane internal) {
        if (getPrefWidth() <= 0 || getPrefHeight() <= 0) {
            setPrefSize(internal.getWidth(), internal.getHeight());
            getChildren().clear();
            line1StartX = 4;
            line1StartY = internal.getHeight() / 2;
            line1EndX = internal.getWidth() / 2 - 2;
            line1EndY = internal.getHeight() - 6;
            line1 = new Line(line1StartX, line1StartY, line1EndX, line1EndY);
            line1.setStroke(Color.TRANSPARENT);
            line1.setStrokeWidth(1.25);
            getChildren().add(line1);
            line2StartX = line1EndX;
            line2StartY = line1EndY;
            line2EndX = internal.getWidth() - 5;
            line2EndY = 6;
            line2 = new Line(line2StartX, line2StartY, line2EndX, line2EndY);
            line2.setStroke(Color.TRANSPARENT);
            line2.setStrokeWidth(1.25);
            getChildren().add(line2);
            intermin = new Line(internal.getWidth() / 4, internal.getHeight() / 2, internal.getWidth() / 4 * 3, internal.getHeight() / 2);
            intermin.setStroke(Color.TRANSPARENT);
            intermin.setStrokeWidth(1.75);
            getChildren().add(intermin);

            onUpdateComponent();
        }
    }

    public void onUpdateComponent() {
        line1.setStroke(skin.getSkinnable().isSelected() ? getFill() : Color.TRANSPARENT);
        line2.setStroke(skin.getSkinnable().isSelected() ? getFill() : Color.TRANSPARENT);
        intermin.setStroke(skin.getSkinnable().isIndeterminate() ? getFill() : Color.TRANSPARENT);
    }
}
