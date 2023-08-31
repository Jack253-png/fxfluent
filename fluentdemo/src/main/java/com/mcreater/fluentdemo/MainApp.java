package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.stage.FluentStage;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public void start(Stage primaryStage) {
        FluentStage stage = new FluentStage(StageStyle.TRANSPARENT);
        stage.setTitle("FxFluent 控件库展示");
        /*Region cov = new Region();
        Pane pane = new Pane(new UIGalleryPane(stage), cov);
        cov.setPrefWidth(100);
        cov.setPrefHeight(100);
        cov.setLayoutY(125);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(
                                cov.layoutXProperty(),
                                0
                        )
                ),
                new KeyFrame(
                        Duration.millis(1500),
                        new KeyValue(
                                cov.layoutXProperty(),
                                200
                        )
                )
        );
        timeline.setCycleCount(-1);
        timeline.setAutoReverse(true);
        timeline.playFromStart();
        new AcrylicInAppFillColorBrush().accept(cov, null);
        stage.setContent(pane);*/

        stage.setContent(new UIGalleryPane(stage));
        stage.getIcons().add(new Image(MainApp.class.getClassLoader().getResourceAsStream("icons/TitlebarLogo.png")));
        stage.setDisableBackdrop(true);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
        stage.applyBackdropType();
    }

    public static void launch(String... args) {
        Application.launch(args);
    }
}
