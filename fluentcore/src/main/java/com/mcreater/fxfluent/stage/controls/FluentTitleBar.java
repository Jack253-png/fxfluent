package com.mcreater.fxfluent.stage.controls;

import com.mcreater.fxfluent.controls.icon.FluentIcon;
import com.mcreater.fxfluent.stage.FluentStage;
import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class FluentTitleBar extends AnchorPane {
    public FluentTitleBar(FluentStage stage) {
        setPrefHeight(50);
        setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.004), CornerRadii.EMPTY, Insets.EMPTY)));
        FluentStage.WindowMovement.getInstance().windowMove(this, stage);

        FluentCloseButton closeButton = new FluentCloseButton();
        closeButton.setOnAction(actionEvent -> stage.close());
        closeButton.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor(closeButton, 0D);

        FluentWindowButton maximizeButton = new FluentWindowButton();
        maximizeButton.setOnAction(actionEvent -> stage.setMaximized(!stage.isMaximized()));
        maximizeButton.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor(maximizeButton, closeButton.getPrefWidth());
        final FluentIcon[] iconMax = {new FluentIcon('\uE922')};
        maximizeButton.setGraphic(iconMax[0]);
        stage.maximizedProperty().addListener((NewValueListener<Boolean>) t1 -> {
            iconMax[0] = new FluentIcon(t1 ? '\uE923' : '\uE922');
            maximizeButton.setGraphic(iconMax[0]);
        });
        iconMax[0].setTextFill(UiShellWrapper.GetSystemIsDark() ? Color.WHITE : Color.BLACK);

        FluentWindowButton minimizeButton = new FluentWindowButton();
        minimizeButton.setOnAction(actionEvent -> stage.setIconified(true));
        minimizeButton.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor(minimizeButton, maximizeButton.getPrefWidth() + closeButton.getPrefWidth());
        FluentIcon iconMin = new FluentIcon('\uE921');
        minimizeButton.setGraphic(iconMin);
        iconMin.setTextFill(UiShellWrapper.GetSystemIsDark() ? Color.WHITE : Color.BLACK);

        if (stage.getStyle() == StageStyle.TRANSPARENT || stage.getStyle() == StageStyle.UNDECORATED) getChildren().addAll(closeButton, maximizeButton, minimizeButton);
    }
}
