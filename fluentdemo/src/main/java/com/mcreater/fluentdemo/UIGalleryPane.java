package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.brush.AcrylicInAppFillColorBrush;
import com.mcreater.fxfluent.controls.*;
import com.mcreater.fxfluent.stage.FluentStage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

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
        FluentCheckBox box2 = new FluentCheckBox("test");
        box2.setAllowIndeterminate(true);
        new AcrylicInAppFillColorBrush().accept(bar, null);
        FluentHyperlinkButton hyperlink = new FluentHyperlinkButton("test");
        FluentToggleButton button = new FluentToggleButton("Test");
        FluentProgressBar progressBar = new FluentProgressBar();
        Timer timer = new Timer(1000, e -> progressBar.setProgress((e.getWhen() % 100) / 100D));
        // timer.start();
        add(bar, 0, 1);
        add(button, 0, 2);
        add(box2, 0, 3);
        add(hyperlink, 0, 4);
        add(progressBar, 1, 1);

        // setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
