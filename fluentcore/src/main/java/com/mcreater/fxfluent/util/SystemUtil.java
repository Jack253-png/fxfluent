package com.mcreater.fxfluent.util;

public class SystemUtil {
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("osx");
    }

    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }
    public static void startGCLoop() {
        new Thread(() -> {
            while (true) {
                System.gc();

                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {

                }
            }
        }).start();
    }
}
