package com.mcreater.fxfluent.brush

import javafx.animation.Interpolatable
import javafx.scene.layout.Region
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Paint
import javafx.scene.paint.Stop
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Consumer
import kotlin.math.abs


abstract class AbstractColorBrush : Interpolatable<AbstractColorBrush?>,
    BiConsumer<Region?, BiConsumer<Region?, AbstractColorBrush?>> {
    override fun interpolate(abstractColorBrush: AbstractColorBrush?, v: Double): AbstractColorBrush? {
        if (v == 0.0) return this
        if (v == 1.0) return abstractColorBrush
        if (abstractColorBrush is SolidColorBrush && this is SolidColorBrush) {
            return SolidColorBrush(
                toSolidColorBrush().getPaint()!!.interpolate(
                    abstractColorBrush.getPaint(),
                    v
                )
            )
        }
        if (abstractColorBrush is LinearGradientColorBrush) {
            val end: LinearGradient = abstractColorBrush.getPaint()!!
            val start: LinearGradient = toLinearGradientColorBrush().getPaint()!!
            val stops: MutableList<Stop> = Vector()
            end.stops.forEach(Consumer { stop: Stop ->
                val offsetdelta = doubleArrayOf(
                    Double.POSITIVE_INFINITY,
                    Double.POSITIVE_INFINITY
                )
                val result = arrayOf(stop)
                start.stops
                    .forEach(Consumer { s: Stop ->
                        val noff = abs(stop.offset - s.offset)
                        if (noff < offsetdelta[0]) {
                            offsetdelta[0] = noff
                            offsetdelta[1] = stop.offset - s.offset
                            result[0] = s
                        }
                    })
                stops.add(
                    Stop(
                        result[0].offset + (stop.offset - result[0].offset) * v,
                        result[0].color.interpolate(stop.color, v)
                    )
                )
            })
            val startX = start.startX + (end.startX - start.startX) * v
            val startY = start.startY + (end.startY - start.startY) * v
            val endX = start.endX + (end.endX - start.endX) * v
            val endY = start.endY + (end.endY - start.endY) * v
            return LinearGradientColorBrush(
                LinearGradient(
                    startX, startY, endX, endY, true, CycleMethod.NO_CYCLE,
                    stops
                )
            )
        }
        return abstractColorBrush
    }

    private fun toSolidColorBrush(): SolidColorBrush {
        return this as SolidColorBrush
    }

    private fun toLinearGradientColorBrush(): LinearGradientColorBrush {
        try {
            return this as LinearGradientColorBrush
        } catch (ignored: Exception) {
        }
        return LinearGradientColorBrush(
            LinearGradient(
                0.0, 0.0, 0.0, 3.0, true, CycleMethod.NO_CYCLE,
                Stop(0.0, toSolidColorBrush().getPaint()), Stop(1.0, toSolidColorBrush().getPaint())
            )
        )
    }

    abstract fun getPaint(): Paint?

    override fun accept(region: Region?, regionAbstractColorBrushBiConsumer: BiConsumer<Region?, AbstractColorBrush?>) {
        regionAbstractColorBrushBiConsumer.accept(region, this)
    }
}

