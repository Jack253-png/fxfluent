package com.mcreater.fxfluent.controls.skin

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.brush.SolidColorBrush
import com.mcreater.fxfluent.controls.FluentTooltip
import com.mcreater.fxfluent.controls.abstractions.SkinUpdatable
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.mcreater.fxfluent.xaml.XamlManager
import javafx.geometry.Insets
import javafx.scene.control.Labeled
import javafx.scene.control.skin.TooltipSkin
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.util.Duration

class FluentTooltipSkin(private val control: FluentTooltip?) : TooltipSkin(control), SkinUpdatable {
    private val backgroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    private val foregroundColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(42.0))
    private val borderColor =
        AnimatedValue<AbstractColorBrush>(SolidColorBrush(Color.TRANSPARENT), Duration.millis(83.0))
    init {
        val cornerRadii = CornerRadii(6.0)
        backgroundColor.property.addListener(
            NewValueListener {
                control?.accessBackground(
                    Background(
                    BackgroundFill(
                        it?.getPaint(),
                        cornerRadii,
                        Insets.EMPTY
                    )
                )
                )
            }
        )
        foregroundColor.property.addListener(
            NewValueListener {
                (node as Labeled).textFill = it.getPaint()
            }
        )
        borderColor.property.addListener(
            NewValueListener {
                control?.accessBorder(
                    Border(
                        BorderStroke(
                            it.getPaint(),
                            BorderStrokeStyle.SOLID,
                            cornerRadii,
                            BorderWidths(1.0),
                            Insets.EMPTY
                        )
                    )
                )
            }
        )
        updateComponents()
        control!!.font = Font(16.0)
    }
    private fun updateComponents() {
        backgroundColor.updateValue(XamlManager.find("SolidBackgroundFillColorBaseBrush", control!!.resourceDict))
        foregroundColor.updateValue(XamlManager.find("ToolTipForegroundBrush", control.resourceDict))
        borderColor.updateValue(XamlManager.find("ToolTipBorderBrush", control.resourceDict))
    }
    override fun implUpdate() {
        updateComponents()
    }
}