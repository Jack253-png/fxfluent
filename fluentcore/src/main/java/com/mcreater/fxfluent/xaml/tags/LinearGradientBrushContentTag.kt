package com.mcreater.fxfluent.xaml.tags

import com.mcreater.fxfluent.brush.LinearGradientColorBrush
import com.mcreater.fxfluent.xaml.XamlHelper.Companion.parseAnyColor
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import org.dom4j.Element
import java.util.*
import java.util.function.Consumer


class LinearGradientBrushContentTag : SimpleContentTag<LinearGradientColorBrush?>() {
    override fun toObject(): LinearGradientColorBrush {
        val startPoint = element().attributeValue("StartPoint")
        val endPoint = element().attributeValue("EndPoint")
        val stops: MutableList<Stop> = Vector()
        element().element("LinearGradientBrush.GradientStops").elements().forEach(Consumer { element: Element ->
            val offset = element.attributeValue("Offset").toDouble()
            val opacity =
                Optional.ofNullable(element.attributeValue("Opacity")).orElse("1.0").toDouble()
            val color =
                parseAnyColor(dict, element.attributeValue("Color"), opacity)
            stops.add(Stop(offset, color))
        })
        if (stops.stream().noneMatch { stop: Stop -> stop.offset == 0.0 }) {
            stops.add(0, Stop(0.0, Color.TRANSPARENT))
        }
        return LinearGradientColorBrush(
            LinearGradient(startPoint.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()[0].toDouble(),
                startPoint.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toDouble(),
                endPoint.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()[0].toDouble(),
                endPoint.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toDouble(),
                true,
                CycleMethod.NO_CYCLE,
                stops
            )
        )
    }
}
