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
