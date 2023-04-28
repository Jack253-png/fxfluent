package com.mcreater.fluentdemo;

import com.mcreater.fluentdemo.brush.TextFillColorPrimaryBrush;
import javafx.scene.paint.Color;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(new TextFillColorPrimaryBrush().convert(Color.WHITE));
    }
}