package com.mcreater.fxfluent.util

import javafx.application.Platform
import javafx.geometry.Bounds
import javafx.scene.Scene
import javafx.scene.SnapshotParameters
import javafx.scene.effect.GaussianBlur
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.PixelFormat
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import java.util.concurrent.atomic.AtomicReference
import kotlin.random.Random

class ImageUtil {
    companion object {
        @JvmStatic
        val seed = Random.nextLong()
        @JvmStatic
        var random = Random(seed)
        @JvmStatic
        val emptyImage = WritableImage(1, 1)
        @JvmStatic
        fun snapshot(scene: Scene?, bounds: Bounds, coverColor: Color, befSnap: Runnable, aftSnap: Runnable): Image {
            if (scene == null) return emptyImage
            val radius = 32
            val width = scene.width.toInt()
            val height = scene.height.toInt()
            if (width <= 0 || height <= 0) return emptyImage
            val result = AtomicReference<WritableImage?>()

            val parameters = SnapshotParameters()
            parameters.fill = Color.TRANSPARENT

            Platform.runLater {
                befSnap.run()
                result.set(scene.root.snapshot(parameters, null))
                aftSnap.run()
            }

            val pane = ImageView()
            pane.fitWidth = width.toDouble()
            pane.fitHeight = height.toDouble()
            pane.effect = GaussianBlur(radius.toDouble())
            while (result.get() == null) {}
            pane.image = result.get()

            val blurred = AtomicReference<WritableImage?>()
            Platform.runLater { blurred.set(pane.snapshot(parameters, null)) }

            while (blurred.get() == null) {}

            val clrs = IntArray(width * height)
            val opc = IntArray(width * height)
            WritableImage(blurred.get()!!.pixelReader, radius, radius, width, height).pixelReader.getPixels(
                0,
                0,
                width,
                height,
                PixelFormat.getIntArgbInstance(),
                clrs,
                0,
                width
            )

            random = Random(seed)
            for (index in clrs.indices) {
                opc[index] = clrs[index] or -0x1000000
                val baser = opc[index] shr 16 and 0xFF
                val baseg = opc[index] shr 8 and 0xFF
                val baseb = opc[index] and 0xFF
                val o = coverColor.opacity
                var r = (baser + (coverColor.red * 255 - baser) * o).toInt()
                var g = (baseg + (coverColor.green * 255 - baseg) * o).toInt()
                var b = (baseb + (coverColor.blue * 255 - baseb) * o).toInt()
                r = NumberUtil.lim(r + random.nextInt(-5, 5), 0, 255)
                g = NumberUtil.lim(g + random.nextInt(-5, 5), 0, 255)
                b = NumberUtil.lim(b + random.nextInt(-5, 5), 0, 255)
                opc[index] = 0xFF shl 24 or
                        (r and 0xFF shl 16) or
                        (g and 0xFF shl 8) or (b and 0xFF)
            }

            val bdX = bounds.minX.toInt()
            val bdY = bounds.minY.toInt()
            val bdWidth = bounds.width.toInt()
            val bdHeight = bounds.height.toInt()

            val res = IntArray(bdWidth * bdHeight)
            for (x in bdX until bdX + bdWidth) {
                for (y in bdY until bdY + bdHeight) {
                    val interX = x - bdX
                    val interY = y - bdY
                    val idxArr = interY * bdWidth + interX
                    if (x < 0 || x >= width || y < 0 || y >= height) res[idxArr] = 0x0000000 else res[idxArr] =
                        opc[y * width + x]
                }
            }

            val output = WritableImage(bdWidth, bdHeight)
            output.pixelWriter.setPixels(0, 0, bdWidth, bdHeight, PixelFormat.getIntArgbInstance(), res, 0, bdWidth)

            return output
        }
    }
}