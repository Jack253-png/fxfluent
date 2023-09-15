package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.brush.AcrylicInAppFillColorBrush;
import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.controls.FluentCheckBox;
import com.mcreater.fxfluent.controls.FluentHyperlinkButton;
import com.mcreater.fxfluent.controls.FluentToggleButton;
import com.mcreater.fxfluent.stage.FluentStage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

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
        add(bar, 0, 1);
        add(button, 0, 2);
        add(box2, 0, 3);
        add(hyperlink, 0, 4);
        hyperlink.setBorder(new Border(new BorderStroke(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
                new LinearGradient(
                        0, 0, 0, 3, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, Color.TRANSPARENT), new Stop(0.33, Color.rgb(0, 0, 0, 35/255D)), new Stop(1, Color.rgb(0, 0, 0, 15/255D))
                ),
                Color.TRANSPARENT,
                BorderStrokeStyle.SOLID,
                BorderStrokeStyle.SOLID,
                BorderStrokeStyle.SOLID,
                BorderStrokeStyle.SOLID,
                new CornerRadii(6),
                new BorderWidths(3),
                Insets.EMPTY
        )));

        // setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
