package com.mcreater.fluentdemo;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UIGalleryPane extends GridPane {

    public UIGalleryPane() {
        setPadding(new Insets(10));

        VBox buttons = new VBox();
        buttons.getChildren().add(new Label("按钮"));
        buttons.getChildren().add(new Button("正常的按钮"));
        Button disabledButton = new Button("禁用的按钮");
        disabledButton.setDisable(true);
        buttons.getChildren().add(disabledButton);
        Button defaultButton = new Button("默认样式的按钮");
        defaultButton.setDefaultButton(true);
        buttons.getChildren().add(defaultButton);
        add(buttons, 0, 0);
    }
}
