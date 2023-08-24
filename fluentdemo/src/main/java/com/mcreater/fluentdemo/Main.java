package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.util.DefaultFontPatcher;
import com.mcreater.fxfluent.xaml.XAMLLoader;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.scene.text.Font;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        SystemThemeLoop.load();
        XAMLLoader.testLoad();
        DefaultFontPatcher.patch(new Font(24));
        MainApp.launch(args);
    }
}