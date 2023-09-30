package com.mcreater.fxfluent.util

import com.mcreater.fxfluent.util.ReflectUtil.Companion.openModuleToAllUnnamed
import javafx.scene.text.Font

class DefaultFontPatcher {
    companion object {
        @JvmStatic
        @Throws(NoSuchFieldException::class, IllegalAccessException::class)
        fun patch(font: Font?) {
            if (!openModuleToAllUnnamed(
                    Font::class.java.module,
                    "javafx.scene.text"
                )
            ) {
                println("Patch failed")
                return
            }
            val field = Font::class.java.getDeclaredField("DEFAULT")
            field.setAccessible(true)
            field[null] = font
        }
    }
}