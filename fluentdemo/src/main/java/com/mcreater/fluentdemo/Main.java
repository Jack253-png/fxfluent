package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.syslib.Win32UiShellLib;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Win32UiShellLib.INSTANCE.HelloWorld();
        System.out.println(UiShellWrapper.GetSystemCompositionColor());
        MainApp.launch(args);
    }
}