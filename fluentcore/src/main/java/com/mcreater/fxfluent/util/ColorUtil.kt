package com.mcreater.fxfluent.util

import javafx.scene.paint.Color

class ColorUtil {
    companion object {
        @JvmStatic
        fun parseXamlColor(content: String?): Color {
            var color: Color = Color.TRANSPARENT
            if (content!!.startsWith("#") && content.length == 9) color = Color.valueOf(
                "#" + content.substring(3) + content.substring(
                    1,
                    3
                )
            )
            else if (content.startsWith("#") && content.length == 7) color = Color.valueOf(content)
            return color
        }
    }
}