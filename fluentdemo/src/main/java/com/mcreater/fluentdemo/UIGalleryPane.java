package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.controls.FluentToggleButton;
import com.mcreater.fxfluent.stage.FluentStage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UIGalleryPane extends GridPane {
    public UIGalleryPane(FluentStage stage) {
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
        buttons.setSpacing(15);
        add(buttons, 0, 0);
        ScrollBar bar = new ScrollBar();
        FluentToggleButton button = new FluentToggleButton("Test");
        add(bar, 0, 1);
        add(button, 0, 2);

        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
