package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.syslib.Win32UiShellLib;
import com.mcreater.fxfluent.util.DefaultFontPatcher;
import javafx.scene.text.Font;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Hello world!");
        Win32UiShellLib.INSTANCE.HelloWorld();
        System.out.println(Win32UiShellLib.INSTANCE.GetThemeIsDark());
        System.out.println(UiShellWrapper.GetSystemCompositionColor());

        DefaultFontPatcher.patch(new Font(24));
        MainApp.launch(args);
    }
}