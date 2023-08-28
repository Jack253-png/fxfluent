package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.tray.TrayManager;
import com.mcreater.fxfluent.util.DefaultFontPatcher;
import com.mcreater.fxfluent.xaml.XAMLLoader;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, AWTException {
        TrayManager.init(TrayManager.class.getClassLoader().getResource("icons/TitlebarLogo.png"), "FxFluent Gallery", "FxFluent Gallery");
        TrayManager.display("Hello world!", "test", TrayIcon.MessageType.INFO);

        SystemThemeLoop.load();
        XAMLLoader.testLoad();
        DefaultFontPatcher.patch(new Font(18));
        MainApp.launch(args);
    }
}