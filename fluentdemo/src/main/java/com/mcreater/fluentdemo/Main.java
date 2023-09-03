package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.util.DefaultFontPatcher;
import com.mcreater.fxfluent.util.SystemUtil;
import javafx.scene.text.Font;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        SystemUtil.startGCLoop();
        DefaultFontPatcher.patch(new Font(18));
        MainApp.launch(args);
    }
}