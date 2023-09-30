package com.mcreater.fxfluent.util

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
        fun getWindowHandle(window: Window): Long {
            val internalStage = window.impl_getPeer()
            val platformWindow =
                Class.forName("com.sun.javafx.tk.quantum.WindowStage").getDeclaredField("platformWindow")
            platformWindow.isAccessible = true
            val platformWindowInstance = platformWindow[internalStage] as com.sun.glass.ui.Window
            val getNativeHandle = com.sun.glass.ui.Window::class.java.getDeclaredMethod("getNativeHandle")
            getNativeHandle.isAccessible = true
            return getNativeHandle.invoke(platformWindowInstance) as Long
        }
    }
}