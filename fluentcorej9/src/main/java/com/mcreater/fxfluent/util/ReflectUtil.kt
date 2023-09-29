package com.mcreater.fxfluent.util

class ReflectUtil {
    companion object {
        @JvmStatic
        fun openModuleToAllUnnamed(targetModule: Module?, vararg packageNames: String?): Boolean {
            try {
                val method =
                    Module::class.java.getDeclaredMethod("implAddOpensToAllUnnamed", String::class.java)
                method.setAccessible(true)
                for (p in packageNames) method.invoke(targetModule, p)
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                println("Open module failed, please check your command args and add these args: \n--add-opens\njava.base/java.lang=ALL-UNNAMED")
            }
            return false
        }

        @JvmStatic
        fun exportModuleToAllUnnamed(targetModule: Module?, vararg packageNames: String?): Boolean {
            try {
                val method =
                    Module::class.java.getDeclaredMethod("implAddExportsToAllUnnamed", String::class.java)
                method.setAccessible(true)
                for (p in packageNames) method.invoke(targetModule, p)
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                println("Open module failed, please check your command args and add these args: \n--add-opens\njava.base/java.lang=ALL-UNNAMED")
            }
            return false
        }
    }
}