package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.stage.FluentStage;
import com.mcreater.fxfluent.syslib.UiShellWrapper;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public void start(Stage primaryStage) throws Exception {
        FluentStage stage = new FluentStage(StageStyle.TRANSPARENT);
        stage.setTitle("Test");
        stage.setContent(new UIGalleryPane());
        stage.show();
        stage.setBackdropType(UiShellWrapper.BackdropType.ACRYLIC);
        stage.applyBackdropType();
    }

    public static void launch(String... args) {
        Application.launch(args);
    }
}
