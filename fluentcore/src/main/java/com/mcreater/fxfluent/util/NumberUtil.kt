package com.mcreater.fxfluent.util

import kotlin.math.max
import kotlin.math.min

class NumberUtil {
    companion object {
        @JvmStatic
        fun lim(res: Double, up: Double, down: Double): Double {
            var upit = up
            var downit = down
            if (downit > upit) {
                upit = downit.apply {downit = upit}
            }
            return max(downit, min(upit, res))
        }

        @JvmStatic
        fun lim(res: Int, up: Int, down: Int): Int {
            var upit = up
            var downit = down
            if (downit > upit) {
                upit = downit.apply {downit = upit}
            }
            return max(downit.toDouble(), min(upit.toDouble(), res.toDouble())).toInt()
        }
    }
}
