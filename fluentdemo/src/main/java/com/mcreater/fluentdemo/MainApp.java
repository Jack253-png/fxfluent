package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.stage.FluentStage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public void start(Stage primaryStage) {
        FluentStage stage = new FluentStage(StageStyle.TRANSPARENT);
        stage.setTitle("Test");
        stage.setContent(new UIGalleryPane());
        stage.show();
        stage.applyBackdropType();
    }

    public static void launch(String... args) {
        Application.launch(args);
    }
}
