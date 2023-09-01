package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.brush.AcrylicInAppFillColorBrush;
import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.controls.FluentCheckBox;
import com.mcreater.fxfluent.controls.FluentToggleButton;
import com.mcreater.fxfluent.stage.FluentStage;
import com.mcreater.fxfluent.util.ColorUtil;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static javafx.scene.paint.Color.rgb;

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
        new AcrylicInAppFillColorBrush().accept(bar, null);
        FluentToggleButton button = new FluentToggleButton("Test");
        add(bar, 0, 1);
        add(button, 0, 2);
        add(box2, 0, 3);

        add(ColorUtil.testColorTranslate(rgb(37, 130, 146), rgb(113, 212, 219)), 1, 0);
        add(ColorUtil.testColorTranslate(rgb(226, 36, 26), rgb(243, 128, 100)), 2, 0);
        add(ColorUtil.testColorTranslate(rgb(169, 77, 193), rgb(219, 159, 229)), 3, 0);
        add(ColorUtil.testColorTranslate(rgb(0, 120, 212), rgb(76, 194, 255)), 4, 0);

        // setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
