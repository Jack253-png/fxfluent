package com.mcreater.fxfluent.util

import java.util.*

class SystemUtil {
    companion object {
        @JvmStatic
        fun isWindows(): Boolean {
            return System.getProperty("os.name").lowercase(Locale.getDefault()).contains("windows")
        }

        @JvmStatic
        fun isMac(): Boolean {
            return System.getProperty("os.name").lowercase(Locale.getDefault())
                .contains("mac") || System.getProperty("os.name").lowercase(Locale.getDefault()).contains("osx")
        }

        @JvmStatic
        fun isLinux(): Boolean {
            return System.getProperty("os.name").lowercase(Locale.getDefault()).contains("linux")
        }
    }
}