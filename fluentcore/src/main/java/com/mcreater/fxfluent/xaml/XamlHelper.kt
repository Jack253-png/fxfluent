package com.mcreater.fxfluent.xaml

import com.mcreater.fxfluent.util.ColorUtil.Companion.parseXamlColor
import com.mcreater.fxfluent.xaml.tags.AbstractContentTag
import com.mcreater.fxfluent.xaml.tags.ColorContentTag
import javafx.scene.paint.Color
import java.util.*
import java.util.regex.Pattern

class XamlHelper {
    companion object {
        @JvmStatic
        private val RESOURCE_REF_PATTERN = Pattern.compile("\\{(?<reftype>.*) (?<refkey>.*)}")
        @JvmStatic
        fun <T : AbstractContentTag<*>> relativeTag(dict: ResourceDict, content: String?, clazz: Class<T>?): T? {
            val mat = RESOURCE_REF_PATTERN.matcher(content!!)
            if (mat.find()) {
                val type = mat.group("reftype")
                val key = mat.group("refkey")

                if (type.equals("StaticResource").or(type.equals("ThemeResource"))) return dict.foundTag(key, clazz)
            }
            return null
        }

        @JvmStatic
        fun parseAnyColor(dict: ResourceDict?, content: String?, opacity: Double): Color {
            val tag: ColorContentTag? = relativeTag(dict!!, content, ColorContentTag::class.java)
            return Optional.ofNullable(tag)
                .map { it.toObject() }
                .map {
                    Color(
                        it.red,
                        it.green,
                        it.blue,
                        it.opacity * opacity
                    )
                }
                .orElseGet {
                    val color = parseXamlColor(content)
                    Color(color.red, color.green, color.blue, color.opacity * opacity)
                }
        }
    }
}