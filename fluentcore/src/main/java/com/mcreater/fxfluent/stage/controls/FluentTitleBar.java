package com.mcreater.fxfluent.stage.controls;

import com.mcreater.fxfluent.controls.icon.FluentIcon;
import com.mcreater.fxfluent.stage.FluentStage;
import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

public class FluentTitleBar extends AnchorPane {
    public FluentTitleBar(FluentStage stage) {
        setPrefHeight(50);
        setMinHeight(50);
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
            iconMax[0].setTextFill(UiShellWrapper.GetSystemIsDark() ? Color.WHITE : Color.BLACK);
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

        ImageView view = new ImageView(stage.getIcons().size() > 0 ? stage.getIcons().get(0) : null);
        view.setFitWidth(16);
        view.setFitHeight(16);
        stage.getIcons().addListener((ListChangeListener<Image>) change -> {
            if (stage.getIcons().size() > 0) view.setImage(stage.getIcons().get(0));
        });
        AnchorPane.setTopAnchor(view, (50 - 16) / 2D);
        AnchorPane.setLeftAnchor(view, 16D);

        Label windowTitle = new Label(stage.getTitle());
        windowTitle.setFont(new Font(14));
        stage.titleProperty().addListener((NewValueListener<String>) windowTitle::setText);
        AnchorPane.setTopAnchor(windowTitle, (50 - 20) / 2D);
        AnchorPane.setLeftAnchor(windowTitle, 16D + 16D + 16D);
        windowTitle.maxWidthProperty().bind(widthProperty().divide(3).multiply(2));
        windowTitle.setTextFill(SystemThemeLoop.isDark() ? Color.WHITE : Color.BLACK);

        if (stage.getStyle() == StageStyle.TRANSPARENT || stage.getStyle() == StageStyle.UNDECORATED) getChildren().addAll(closeButton, maximizeButton, minimizeButton);
        getChildren().addAll(view, windowTitle);
    }
}
