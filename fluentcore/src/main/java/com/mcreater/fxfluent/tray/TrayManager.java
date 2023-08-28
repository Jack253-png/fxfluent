package com.mcreater.fxfluent.tray;

import java.awt.*;
import java.net.URL;

public class TrayManager {
    private static TrayIcon trayIcon;
    public static void init(URL url, String name, String des) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage(url);

        trayIcon = new TrayIcon(image, name);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(des);
        tray.add(trayIcon);
    }

    public static void display(String cap, String text, TrayIcon.MessageType type) {
        trayIcon.displayMessage(cap, text, type);
    }
}
