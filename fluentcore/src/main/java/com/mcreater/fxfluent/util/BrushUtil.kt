package com.mcreater.fxfluent.util

import com.mcreater.fxfluent.brush.AbstractColorBrush
import javafx.geometry.Insets
import javafx.scene.control.Labeled
import javafx.scene.layout.*
import javafx.scene.paint.Color
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Consumer

class BrushUtil {
    enum class BorderOrientation {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }
    companion object {
        @JvmStatic
        fun backgroundFill(cornerRadii: CornerRadii?): BiConsumer<Region, AbstractColorBrush> {
            return BiConsumer { region: Region, color: AbstractColorBrush ->
                region.background = Background(
                    BackgroundFill(
                        color.getPaint(),
                        cornerRadii,
                        Insets.EMPTY
                    )
                )
            }
        }

        @JvmStatic
        fun textFill(): BiConsumer<Region, AbstractColorBrush> {
            return BiConsumer { region: Region?, color: AbstractColorBrush ->
                if (region is Labeled) region.textFill = color.getPaint()
            }
        }

        @JvmStatic
        fun borderFill(
            orientation: BorderOrientation?,
            cornerRadii: CornerRadii?
        ): BiConsumer<Region, AbstractColorBrush> {
            return BiConsumer { region: Region, color: AbstractColorBrush ->
                val insets = Insets.EMPTY
                if (region.border == null || region.border.strokes.size == 0) {
                    region.border = Border(
                        BorderStroke(
                            if (orientation == null || orientation == BorderOrientation.TOP) color.getPaint() else Color.TRANSPARENT,
                            if (orientation == null || orientation == BorderOrientation.RIGHT) color.getPaint() else Color.TRANSPARENT,
                            if (orientation == null || orientation == BorderOrientation.BOTTOM) color.getPaint() else Color.TRANSPARENT,
                            if (orientation == null || orientation == BorderOrientation.LEFT) color.getPaint() else Color.TRANSPARENT,
                            BorderStrokeStyle.SOLID,
                            BorderStrokeStyle.SOLID,
                            BorderStrokeStyle.SOLID,
                            BorderStrokeStyle.SOLID,
                            cornerRadii,
                            BorderWidths(1.0),
                            insets
                        )
                    )
                } else {
                    val strokes: MutableList<BorderStroke> =
                        Vector()
                    region.border.strokes
                        .forEach(Consumer { borderStroke: BorderStroke ->
                            strokes.add(
                                BorderStroke(
                                    if (orientation == null || orientation == BorderOrientation.TOP) color.getPaint() else borderStroke.topStroke,
                                    if (orientation == null || orientation == BorderOrientation.RIGHT) color.getPaint() else borderStroke.rightStroke,
                                    if (orientation == null || orientation == BorderOrientation.BOTTOM) color.getPaint() else borderStroke.bottomStroke,
                                    if (orientation == null || orientation == BorderOrientation.LEFT) color.getPaint() else borderStroke.leftStroke,
                                    BorderStrokeStyle.SOLID,
                                    BorderStrokeStyle.SOLID,
                                    BorderStrokeStyle.SOLID,
                                    BorderStrokeStyle.SOLID,
                                    cornerRadii,
                                    BorderWidths(1.0),
                                    insets
                                )
                            )
                        })
                    region.border = Border(strokes, Vector())
                }
            }
        }
    }
}