package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.controls.FluentButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UIGalleryPane extends GridPane {
    public UIGalleryPane() {
        setPadding(new Insets(10));

        VBox buttons = new VBox();
        buttons.getChildren().add(new Label("按钮"));
        buttons.getChildren().add(new FluentButton("正常的按钮"));
        FluentButton disabledButton = new FluentButton("禁用的按钮");
        disabledButton.setDisable(true);
        buttons.getChildren().add(disabledButton);
        FluentButton defaultButton = new FluentButton("系统主题色按钮");
        defaultButton.setDefaultButton(true);
        buttons.getChildren().add(defaultButton);
        add(buttons, 0, 0);

        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
