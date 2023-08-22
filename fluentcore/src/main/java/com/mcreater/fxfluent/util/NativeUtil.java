package com.mcreater.fxfluent.util;

import com.sun.javafx.tk.TKStage;
import javafx.stage.Window;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NativeUtil {
    public static long getWindowHandle(Window window) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        @SuppressWarnings("all")
        TKStage internalStage = window.impl_getPeer();
        Field platformWindow = Class.forName("com.sun.javafx.tk.quantum.WindowStage").getDeclaredField("platformWindow");
        platformWindow.setAccessible(true);
        com.sun.glass.ui.Window platformWindowInstance = (com.sun.glass.ui.Window) platformWindow.get(internalStage);
        Method getNativeHandle = com.sun.glass.ui.Window.class.getDeclaredMethod("getNativeHandle");
        getNativeHandle.setAccessible(true);
        return (long) getNativeHandle.invoke(platformWindowInstance);
    }
}
