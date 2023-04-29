package com.mcreater.fluentdemo;

import com.sun.javafx.tk.TKStage;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainApp extends Application {
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Test");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

        Module module = Stage.class.getModule();
        Method method = Module.class.getDeclaredMethod("implAddOpensToAllUnnamed", String.class);
        method.setAccessible(true);
        method.invoke(module, "javafx.stage");
        method.invoke(module, "com.sun.javafx.tk");
        method.invoke(module, "com.sun.javafx.tk.quantum");
        method.invoke(module, "com.sun.glass.ui");

        Field field = Window.class.getDeclaredField("peer");
        field.setAccessible(true);
        TKStage internalStage = (TKStage) field.get(primaryStage);
        Field platformWindow = Class.forName("com.sun.javafx.tk.quantum.WindowStage").getDeclaredField("platformWindow");
        platformWindow.setAccessible(true);
        com.sun.glass.ui.Window platformWindowInstance = (com.sun.glass.ui.Window) platformWindow.get(internalStage);
        Method getNativeHandle = com.sun.glass.ui.Window.class.getDeclaredMethod("getNativeHandle");
        getNativeHandle.setAccessible(true);
        long handle = (long) getNativeHandle.invoke(platformWindowInstance);
        System.out.println(handle);
    }

    public static void launch(String... args) {
        Application.launch(args);
    }
}
