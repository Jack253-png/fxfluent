package com.mcreater.fxfluent.tray

import java.awt.AWTException
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import java.awt.TrayIcon.MessageType
import java.net.URL

class TrayManager {
    companion object {
        private var trayIcon: TrayIcon? = null
        @Throws(AWTException::class)
        fun init(url: URL?, name: String?, des: String?) {
            val tray = SystemTray.getSystemTray()
            val image = Toolkit.getDefaultToolkit().createImage(url)
            trayIcon = TrayIcon(image, name)
            trayIcon!!.setImageAutoSize(true)
            trayIcon!!.setToolTip(des)
            tray.add(trayIcon)
        }

        fun display(cap: String?, text: String?, type: MessageType?) {
            trayIcon!!.displayMessage(cap, text, type)
        }
    }
}