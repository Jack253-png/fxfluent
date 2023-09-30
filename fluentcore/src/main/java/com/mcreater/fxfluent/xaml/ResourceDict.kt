package com.mcreater.fxfluent.xaml

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.xaml.tags.*
import javafx.scene.paint.Color
import java.util.*

class ResourceDict(name: String?): Vector<AbstractContentTag<out Any>>() {
    companion object {
        @JvmStatic
        fun createEmpty(name: String?): ResourceDict {
            return ResourceDict(name)
        }
    }
    var name: String? = null
    init {
        this.name = name
    }

    fun <T : AbstractContentTag<*>?> foundTag(key: String, clazz: Class<T>?): T? {
        for (abstractContentTag in this) {
            if (abstractContentTag.getKey() == key) {
                return if (abstractContentTag is StaticResourceRedirectContentTag) foundTag(
                    abstractContentTag.toObject(),
                    clazz
                ) else  // Redirect tags
                    abstractContentTag as T
            }
        }
        return if (this === XamlManager.getGlobal()) null else XamlManager.getGlobal().foundTag(key, clazz)
    }

    fun findColorBrush(key: String): AbstractColorBrush {
        try {
            return foundTag(key, SolidColorBrushContentTag::class.java)!!.toObject()
        } catch (ignored: Exception) {
        }
        try {
            return foundTag(key, LinearGradientBrushContentTag::class.java)!!.toObject()
        } catch (ignored: Exception) {
        }
        return try {
            SolidColorBrush(foundTag(key, ColorContentTag::class.java)!!.toObject())
        } catch (e: Exception) {
            SolidColorBrush(Color.TRANSPARENT)
        }
    }
}