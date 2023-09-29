package com.mcreater.fxfluent.xaml.style;

import com.mcreater.fxfluent.syslib.UiShellWrapper;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

public class SystemThemeLoop {
    private static AppColorTheme theme = UiShellWrapper.getSystemIsDark() ? AppColorTheme.DARK : AppColorTheme.LIGHT;
    private static AppColorTheme predictedTheme = AppColorTheme.SYSTEM;
    private static Color systemAccentColor = UiShellWrapper.getSystemCompositionColor();

    public static void setPredictedTheme(AppColorTheme predictedTheme) {
        SystemThemeLoop.predictedTheme = predictedTheme;
    }

    private static boolean transparencyEnabled = false;
    private static final List<Consumer<AppColorTheme>> listeners = new Vector<>();
    public static AppColorTheme getTheme() {
        return theme;
    }
    public static boolean isDark() {
        return theme == AppColorTheme.DARK;
    }
    public static void addListener(Consumer<AppColorTheme> consumer) {
        listeners.add(consumer);
    }

    static {
        new Thread("System theme detect thread") {
            public void run() {
                while (true) {
                    AppColorTheme theme1 = UiShellWrapper.getSystemIsDark() ? AppColorTheme.DARK : AppColorTheme.LIGHT;
                    if (predictedTheme == AppColorTheme.SYSTEM) {
                        if (theme1 != theme) {
                            theme = theme1;
                            listeners.forEach(a -> Platform.runLater(() -> a.accept(theme)));
                        }
                    }
                    else if (predictedTheme != theme) {
                        theme = predictedTheme;
                        listeners.forEach(a -> Platform.runLater(() -> a.accept(theme)));
                    }
                    Color systemAccentColor2 = UiShellWrapper.getSystemCompositionColor();
                    if (!systemAccentColor2.equals(systemAccentColor)) {
                        systemAccentColor = systemAccentColor2;
                        listeners.forEach(a -> Platform.runLater(() -> a.accept(theme)));
                    }

                    boolean tra = UiShellWrapper.getSystemTransparencyEnabled();
                    if (tra != transparencyEnabled) {
                        transparencyEnabled = tra;
                        listeners.forEach(a -> Platform.runLater(() -> a.accept(theme)));
                    }

                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    public static void load() {}
}
