package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.util.ColorUtil;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.mcreater.fxfluent.util.ColorUtil.printhsb;

public class UIGalleryPane extends GridPane {
    public UIGalleryPane() {
        setPadding(new Insets(10));

        VBox buttons = new VBox();
        buttons.getChildren().add(new Label("按钮"));
        buttons.getChildren().add(new FluentButton("正常的按钮"));
        FluentButton disabledButton = new FluentButton("禁用的按钮");
        disabledButton.setDisable(true);
        buttons.getChildren().add(disabledButton);
        FluentButton defaultButton = new FluentButton("默认样式的按钮");
        defaultButton.setDefaultButton(true);
        buttons.getChildren().add(defaultButton);
        add(buttons, 0, 0);

        buttons.setSpacing(10);
        add(ColorUtil.testColorTranslate(Color.rgb(37, 130, 146)), 1, 0);
        printhsb("dark orig", Color.rgb(113, 212, 219));
        add(ColorUtil.testColorTranslate(Color.rgb(0, 120, 212)), 2, 0);
        printhsb("dark orig", Color.rgb(76, 194, 255));
        add(ColorUtil.testColorTranslate(Color.rgb(226, 36, 26)), 3, 0);
        printhsb("dark orig", Color.rgb(243, 128, 100));
        add(ColorUtil.testColorTranslate(Color.rgb(169, 77, 193)), 4, 0);
        printhsb("dark orig", Color.rgb(219, 159, 229));

        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
