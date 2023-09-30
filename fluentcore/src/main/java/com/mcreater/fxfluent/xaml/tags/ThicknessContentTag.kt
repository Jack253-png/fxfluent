package com.mcreater.fxfluent.xaml.tags

import javafx.geometry.Insets

class ThicknessContentTag : SimpleContentTag<Insets>() {
    override fun toObject(): Insets {
        val s = element?.data.toString()
        try {
            return Insets(s.toInt().toDouble())
        } catch (ignored: Exception) {
        }

        try {
            val sl = s.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return Insets(
                sl[0].toInt().toDouble(),
                sl[1].toInt().toDouble(),
                sl[2].toInt().toDouble(),
                sl[3].toInt().toDouble()
            )
        } catch (ignored: Exception) {
        }
        return Insets.EMPTY
    }

    override fun toString(): String {
        return String.format(
            "Insets(%.2f, %.2f, %.2f, %.2f)",
            toObject().top,
            toObject().right,
            toObject().bottom,
            toObject().left
        )
    }
}