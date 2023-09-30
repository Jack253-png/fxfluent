package com.mcreater.fxfluent.xaml

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.getSystemCompositionColor
import com.mcreater.fxfluent.util.NumberUtil.Companion.lim
import com.mcreater.fxfluent.xaml.ResourceDict.Companion.createEmpty
import com.mcreater.fxfluent.xaml.XamlLoader.Companion.loadAll
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop.Companion.theme
import com.mcreater.fxfluent.xaml.tags.AbstractContentTag
import com.mcreater.fxfluent.xaml.tags.DynamicColorContentTag
import javafx.scene.paint.Color
import org.dom4j.Document
import org.dom4j.DocumentException
import org.dom4j.Element
import org.dom4j.io.SAXReader
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.util.*
import java.util.function.Consumer

class XamlManager {
    companion object {
        @JvmStatic
        private val files: MutableList<InputStream> = Vector()
        @JvmStatic
        private val registeredContents: MutableMap<String, ResourceDict> = HashMap()
        @JvmStatic
        private val globalRegisteredContents = createEmpty("{Global}")
        init {
            try {
                loadAll()
            } 
            catch (e: IOException) {
                e.printStackTrace()
            }
        }

        @Throws(IOException::class)
        @JvmStatic
        fun addFile(file: File) {
            files.add(Files.newInputStream(file.toPath()))
        }

        @JvmStatic
        fun addFileFromClasspath(path: String?) {
            XamlManager::class.java.getClassLoader().getResourceAsStream(path)?.let { files.add(it) }
        }

        @JvmStatic
        fun parse() {
            registeredContents.values.forEach(Consumer { it.clear() })
            registeredContents.clear()
            globalRegisteredContents.clear()
            registerAccent()
            files.forEach(Consumer { file: InputStream? ->
                try {
                    parse(SAXReader().read(file))
                } catch (e: DocumentException) {
                    e.printStackTrace()
                }
            })
        }

        @JvmStatic
        private fun registerAccent() {
            globalRegisteredContents.add(
                DynamicColorContentTag("SystemAccentColor"
                ) { getSystemCompositionColor() }
            )
            globalRegisteredContents.add(DynamicColorContentTag("SystemAccentColorDark1") {
                val color = getSystemCompositionColor()
                Color.hsb(
                    lim(color.hue * 0.9992 + 0.4584, 0.0, 360.0),
                    lim(color.saturation * 0.8824 + 0.1139, 0.0, 1.0),
                    lim(color.brightness * 0.9767 - 0.08423, 0.0, 1.0),
                    1.0
                )
            })
            globalRegisteredContents.add(DynamicColorContentTag("SystemAccentColorDark2") {
                val color = getSystemCompositionColor()
                Color.hsb(
                    lim(color.hue + 1.495, 0.0, 360.0),
                    lim(color.saturation * 0.7728 + 0.2247, 0.0, 1.0),
                    lim(color.brightness * 0.854 - 0.1437, 0.0, 1.0),
                    1.0
                )
            })
            globalRegisteredContents.add(DynamicColorContentTag("SystemAccentColorDark3") {
                val color = getSystemCompositionColor()
                Color.hsb(
                    lim(color.hue * 1.005 + 0.32, 0.0, 360.0),
                    lim(color.saturation * 0.4227 + 0.5717, 0.0, 1.0),
                    lim(color.brightness * 0.7573 - 0.2348, 0.0, 1.0),
                    1.0
                )
            })
            globalRegisteredContents.add(DynamicColorContentTag("SystemAccentColorLight2") {
                val color = getSystemCompositionColor()
                Color.hsb(
                    lim(color.hue * 0.9699 + 0.05696, 0.0, 360.0),
                    lim(color.saturation * 0.9707 - 0.2644, 0.0, 1.0),
                    lim(color.brightness * 0.38 + 0.6379, 0.0, 1.0),
                    1.0
                )
            })
            globalRegisteredContents.add(DynamicColorContentTag("SystemAccentColorLight3") {
                val color = getSystemCompositionColor()
                Color.hsb(
                    lim(color.hue * 0.9488 + 4, 0.0, 360.0),  // +8.839
                    lim(color.saturation * 0.5576 - 0.09745, 0.0, 1.0),
                    lim(color.brightness * 0.2243 + 0.7938, 0.0, 1.0),
                    1.0
                )
            })
        }

        @JvmStatic
        private fun parse(document: Document) {
            val root = document.rootElement
            if (root.name == "ResourceDictionary") {
                root.elements("ResourceDictionary.ThemeDictionaries")
                    .forEach(Consumer { it2 ->
                        it2.elements("ResourceDictionary").forEach(
                            Consumer {
                                it.elements()
                                    .forEach(Consumer { subEle: Element ->
                                        operateDict(
                                            it.attributeValue("Key"),
                                            subEle
                                        )
                                    })
                            })
                    })
                root.elements().stream().filter { a: Element -> a.name == "String" }.forEach { element: Element? ->
                    globalRegisteredContents.add(
                        AbstractContentTag.create(globalRegisteredContents, element!!)!! as AbstractContentTag<out Any>
                    )
                }
            }
        }

        @JvmStatic
        private fun operateDict(dict: String, subEle: Element) {
            if (!registeredContents.containsKey(dict)) registeredContents[dict] =
                createEmpty(dict)
            Optional.ofNullable(AbstractContentTag.create(registeredContents[dict], subEle))
                .ifPresent { registeredContents[dict]!!.add(it as AbstractContentTag<out Any>) }
        }

        @JvmStatic
        fun getCurrentDict(): ResourceDict {
            return getDict(theme)
        }

        @JvmStatic
        fun getDict(t: AppColorTheme): ResourceDict {
            if (t === AppColorTheme.SYSTEM) return getCurrentDict()
            val key = if (t === AppColorTheme.LIGHT) "Light" else "Default"
            return registeredContents.getOrDefault(key, createEmpty("$key (Empty fallback)"))
        }

        @JvmStatic
        fun getGlobal(): ResourceDict {
            return globalRegisteredContents
        }

        @JvmStatic
        fun getAll(): Map<String, ResourceDict> {
            return registeredContents
        }
        @JvmStatic
        fun find(k: String?): AbstractColorBrush {
            return getCurrentDict().findColorBrush(k!!)
        }
        @JvmStatic
        fun find(k: String, dict: ResourceDict?): AbstractColorBrush {
            return dict!!.findColorBrush(k)
        }
    }
}