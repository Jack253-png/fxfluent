package com.mcreater.fxfluent.util

import com.mcreater.fxfluent.util.ReflectUtil.Companion.openModuleToAllUnnamed
import com.sun.javafx.stage.WindowHelper
import javafx.application.Application
import javafx.stage.Stage
import javafx.stage.Window
import java.lang.reflect.InvocationTargetException

class NativeUtil {
    companion object {
        @JvmStatic
        @Throws(
            NoSuchFieldException::class,
            ClassNotFoundException::class,
            NoSuchMethodException::class,
            InvocationTargetException::class,
            IllegalAccessException::class
        )
        fun getWindowHandle(window: Window?): Long {
            if (!openModuleToAllUnnamed(
                    Stage::class.java.module,
                    "javafx.stage",
                    "com.sun.javafx.tk",
                    "com.sun.javafx.tk.quantum",
                    "com.sun.glass.ui"
                ) || !openModuleToAllUnnamed(
                    Application::class.java.module,
                    "com.sun.javafx.stage"
                )
            ) return -1
            val platformWindow =
                Class.forName("com.sun.javafx.tk.quantum.WindowStage").getDeclaredField("platformWindow")
            platformWindow.isAccessible = true
            val platformWindowInstance = platformWindow[WindowHelper.getPeer(window)] as com.sun.glass.ui.Window
            val getNativeHandle = com.sun.glass.ui.Window::class.java.getDeclaredMethod("getNativeHandle")
            getNativeHandle.isAccessible = true
            return getNativeHandle.invoke(platformWindowInstance) as Long
        }
    }
}