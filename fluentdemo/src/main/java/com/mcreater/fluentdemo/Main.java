package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.syslib.Win32UiShellLib;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Win32UiShellLib.INSTANCE.HelloWorld();
        System.out.println(new Color(Win32UiShellLib.INSTANCE.GetCompositionColor().intValue()));
        MainApp.launch(args);
    }
}