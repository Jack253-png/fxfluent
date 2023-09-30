package com.mcreater.fxfluent.util

import javafx.scene.text.Font

class DefaultFontPatcher {
    companion object {
        @JvmStatic
        @Throws(NoSuchFieldException::class, IllegalAccessException::class)
        fun patch(font: Font?) {
            val field = Font::class.java.getDeclaredField("DEFAULT")
            field.setAccessible(true)
            field[null] = font
        }
    }
}