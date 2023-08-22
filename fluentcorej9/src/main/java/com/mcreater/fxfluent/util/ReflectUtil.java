package com.mcreater.fxfluent.util;

import java.lang.reflect.Method;

public class ReflectUtil {
    public static boolean openModuleToAllUnnamed(Module targetModule, String... packageNames) {
        try {
            Method method = Module.class.getDeclaredMethod("implAddOpensToAllUnnamed", String.class);
            method.setAccessible(true);
            for (String p : packageNames) method.invoke(targetModule, p);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Open module failed, please check your command args and add these args: \n--add-opens\njava.base/java.lang=ALL-UNNAMED");
        }
        return false;
    }

    public static boolean exportModuleToAllUnnamed(Module targetModule, String... packageNames) {
        try {
            Method method = Module.class.getDeclaredMethod("implAddExportsToAllUnnamed", String.class);
            method.setAccessible(true);
            for (String p : packageNames) method.invoke(targetModule, p);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Open module failed, please check your command args and add these args: \n--add-opens\njava.base/java.lang=ALL-UNNAMED");
        }
        return false;
    }
}
