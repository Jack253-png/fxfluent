package com.mcreater.fxfluent.controls.internals;

import com.mcreater.fxfluent.controls.skin.FluentCheckBoxSkin;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

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
    public CheckBoxCheckMark(FluentCheckBoxSkin skin) {
        skin.getInternalBox().widthProperty().addListener((NewValueListener<Number>) t1 -> updateSize(skin.getInternalBox()));
        skin.getInternalBox().heightProperty().addListener((NewValueListener<Number>) t1 -> updateSize(skin.getInternalBox()));
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
            line1.setStrokeWidth(1.25);
            getChildren().add(line1);
            line2StartX = internal.getWidth() / 2 - 2;
            line2StartY = internal.getHeight() - 6;
            line2EndX = internal.getWidth() - 4;
            line2EndY = 6;
            line2 = new Line(line2StartX, line2StartY, line2EndX, line2EndY);
            line2.setStrokeWidth(1.25);
            getChildren().add(line2);
        }
    }


}
