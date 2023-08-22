package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.util.NativeUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Test");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();

        System.out.println(NativeUtil.getWindowHandle(primaryStage));
    }

    public static void launch(String... args) {
        Application.launch(args);
    }
}
