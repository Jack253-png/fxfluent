package com.mcreater.fxfluent.xaml.tags

import com.mcreater.fxfluent.xaml.ResourceDict
import org.dom4j.Element
import java.util.*
import java.util.function.BiFunction

interface AbstractContentTag<T> {

    fun writeElement(element: Element?): SimpleContentTag<T>
    fun setResourceDict(dict: ResourceDict?): SimpleContentTag<T>
    fun element(): Element
    fun getKey(): String {
        return element().attributeValue("Key")
    }

    fun toObject(): T?
    companion object {
        @JvmStatic
        val converters: HashMap<String?, BiFunction<ResourceDict?, Element?, AbstractContentTag<*>?>?> =
            object : HashMap<String?, BiFunction<ResourceDict?, Element?, AbstractContentTag<*>?>?>() {
                init {
                    put("Color",
                        BiFunction { dict: ResourceDict?, a: Element? ->
                            ColorContentTag().writeElement(
                                a!!
                            ).setResourceDict(dict!!)
                        })
                    put("SolidColorBrush",
                        BiFunction { dict: ResourceDict?, a: Element? ->
                            SolidColorBrushContentTag().writeElement(
                                a!!
                            ).setResourceDict(dict!!)
                        })
                    put("LinearGradientBrush",
                        BiFunction { dict: ResourceDict?, a: Element? ->
                            LinearGradientBrushContentTag().writeElement(
                                a!!
                            ).setResourceDict(dict!!)
                        })
                    put("StaticResource",
                        BiFunction { dict: ResourceDict?, a: Element? ->
                            StaticResourceRedirectContentTag().writeElement(
                                a!!
                            ).setResourceDict(dict!!)
                        })
                    put("String",
                        BiFunction { dict: ResourceDict?, a: Element? ->
                            StringContentTag().writeElement(
                                a!!
                            ).setResourceDict(dict!!)
                        })
                    put("Thickness",
                        BiFunction { dict: ResourceDict?, a: Element? ->
                            ThicknessContentTag().writeElement(
                                a!!
                            ).setResourceDict(dict!!)
                        })
                }
            }

        @JvmStatic
        fun register(key: String, function: BiFunction<ResourceDict?, Element?, AbstractContentTag<*>?>?) {
            converters[key] = function
        }

        @JvmStatic
        fun create(dict: ResourceDict?, element: Element): AbstractContentTag<*>? {
            return Optional.ofNullable(converters[element.name])
                .map { it.apply(dict, element) }
                .orElse(null)
        }
    }
}