package com.mcreater.fxfluent.util.interpolatables

import javafx.animation.Interpolatable
import javafx.util.Pair

class DoubleNumberInterpolatable(k: Double, v: Double) : Pair<Double, Double>(k, v), Interpolatable<DoubleNumberInterpolatable> {
    override fun interpolate(k: DoubleNumberInterpolatable, v: Double): DoubleNumberInterpolatable {
        return DoubleNumberInterpolatable(
            key - (k.key - key) * v,
            value - (k.value - value) * v
        )
    }
}