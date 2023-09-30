package com.mcreater.fxfluent.brush

import com.mcreater.fxfluent.util.ImageUtil.Companion.snapshot
import javafx.scene.layout.*
import javafx.scene.paint.Color
import java.util.*
import java.util.AbstractMap.SimpleEntry
import java.util.function.BiConsumer

class AcrylicInAppFillColorBrush : BiConsumer<Region, MutableMap<String, Any>?> {
    companion object {
        @JvmStatic
        private val controls: MutableList<Map.Entry<Region, Map<String, Any>?>> = Vector()
        @JvmStatic
        private val calcThreads: MutableList<Thread> = Vector()
    }

    override fun accept(region: Region, u: MutableMap<String, Any>?) {
        controls.add(SimpleEntry(region, u))
        val index = controls.size - 1
        calcThreads.add(object : Thread("Widget blur calc thread #$index") {
            override fun run() {
                while (controls.size > index) {
                    try {
                        val reg = controls[index].key
                        if (reg.rotate != 0.0) throw Exception("Not supported for rotation")
                        if (reg.scaleX != 1.0 || reg.scaleY != 1.0) throw Exception("Not supported for scaling")
                        val boundsInScene = reg.localToScene(reg.boundsInLocal)
                        reg.background = Background(
                            BackgroundImage(
                                snapshot(
                                    reg.scene,
                                    boundsInScene,
                                    Color(1.0, 1.0, 1.0, 0.35),
                                    { reg.opacity = 0.0 }
                                ) { reg.opacity = 1.0 },
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT
                            )
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        })
        calcThreads[calcThreads.size - 1].start()
    }
}