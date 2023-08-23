package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.util.DefaultFontPatcher;
import javafx.scene.text.Font;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        DefaultFontPatcher.patch(new Font(24));
        MainApp.launch(args);
    }
}