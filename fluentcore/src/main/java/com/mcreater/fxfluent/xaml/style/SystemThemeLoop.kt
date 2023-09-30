package com.mcreater.fxfluent.xaml.style

import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.getSystemCompositionColor
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.getSystemIsDark
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.getSystemTransparencyEnabled
import javafx.application.Platform
import java.util.*
import java.util.function.Consumer

class SystemThemeLoop {
    companion object {
        @JvmStatic
        var theme = if (getSystemIsDark()) AppColorTheme.DARK else AppColorTheme.LIGHT
        @JvmStatic
        var systemAccentColor = getSystemCompositionColor()

        @JvmStatic
        var transparencyEnabled = false
        @JvmStatic
        private val listeners: MutableList<Consumer<AppColorTheme>> = Vector()

        @JvmStatic
        fun isDark(): Boolean {
            return theme == AppColorTheme.DARK
        }

        @JvmStatic
        fun addListener(consumer: Consumer<AppColorTheme>) {
            listeners.add(consumer)
        }
        init {
            val thr = Thread {
                while (true) {
                    val theme1 = if (getSystemIsDark()) AppColorTheme.DARK else AppColorTheme.LIGHT
                    if (theme1 != theme) {
                        theme = theme1
                        listeners.forEach {
                            Platform.runLater { it.accept(theme) }
                        }
                    }
                    val systemAccentColor2 = getSystemCompositionColor()
                    if (systemAccentColor2 != systemAccentColor) {
                        systemAccentColor = systemAccentColor2
                        listeners.forEach {
                            Platform.runLater { it.accept(theme) }
                        }
                    }
                    val tra = getSystemTransparencyEnabled()
                    if (tra != transparencyEnabled) {
                        transparencyEnabled = tra
                        listeners.forEach {
                            Platform.runLater { it.accept(theme) }
                        }
                    }
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
            thr.name = "System theme detect thread"
            thr.start()
        }
    }
}