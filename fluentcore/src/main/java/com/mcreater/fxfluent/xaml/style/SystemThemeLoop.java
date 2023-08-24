package com.mcreater.fxfluent.xaml.style;

import com.mcreater.fxfluent.syslib.UiShellWrapper;
import javafx.application.Platform;

import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

public class SystemThemeLoop {
    private static AppColorTheme theme = AppColorTheme.LIGHT;
    private static List<Consumer<AppColorTheme>> listeners = new Vector<>();
    public static AppColorTheme getTheme() {
        return theme;
    }
    public static void addListener(Consumer<AppColorTheme> consumer) {
        listeners.add(consumer);
    }

    static {
        new Thread("System theme detect thread") {
            public void run() {
                while (true) {
                    AppColorTheme theme1 = UiShellWrapper.GetSystemIsDark() ? AppColorTheme.DARK : AppColorTheme.LIGHT;
                    if (theme1 != theme) {
                        theme = theme1;
                        listeners.forEach(a -> Platform.runLater(() -> a.accept(theme)));
                    }

                    try {
                        Thread.sleep(10);
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
