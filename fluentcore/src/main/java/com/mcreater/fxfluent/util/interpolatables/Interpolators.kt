package com.mcreater.fxfluent.util.interpolatables

import javafx.animation.Interpolator
import kotlin.math.*

class Interpolators {
    companion object {
        val linear: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return v
            }
        }
        
        val quadraticEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return v * v
            }
        }
        
        val quadraticEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return 1 - v * v
            }
        }
        
        val quadraticEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v * 2
                if (i1 < 1) return i1 * i1 / 2
                val i2 = i1 - 1
                return -(i2 * (i2 - 2) - 1) / 2
            }
        }
        
        val sinusoidalEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return 1 - cos(v * (Math.PI / 2))
            }
        }
        
        val sinusoidalEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return sin(v * (Math.PI / 2))
            }
        }

        val sinusoidalEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return -(cos(Math.PI * v) - 1) / 2
            }
        }
        
        val exponentialEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return if (v == 0.0) 0.0 else (2.0).pow(10 * (v - 1))
            }
        }

        
        val exponentialEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return if (v == 1.0) 1.0 else (-2.0).pow(-10 * v) + 1
            }
        }

        
        val exponentialEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                if (v == 0.0) return 0.0
                if (v == 1.0) return 1.0
                val i1 = v * 2
                return if (i1 < 1) (2.0).pow(10 * (i1 - 1)) / 2 else ((-2.0).pow(-10 * (i1 - 1)) + 2) / 2
            }
        }

        
        val circularEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return -(sqrt(1 - v * v) - 1)
            }
        }

        
        val circularEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v - 1
                return sqrt(1 - i1 * i1)
            }
        }

        
        val circularEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v * 2
                if (i1 < 1) return -(sqrt(1 - i1 * i1) - 1) / 2
                val i2 = i1 - 2
                return (sqrt(1 - i2 * i2) + 1) / 2
            }
        }

        
        val cubicEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return v * v * v
            }
        }
        
        val cubicEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v - 1
                return i1 * i1 * i1 + 1
            }
        }

        
        val cubicEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v * 2
                if (i1 < 1) return i1 * i1 * i1 / 2
                val i2 = i1 - 2
                return (i2 * i2 * i2 + 2) / 2
            }
        }

        
        val quarticEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return v * v * v * v
            }
        }

        
        val quarticEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v - 1
                return -(i1 * i1 * i1 * i1 - 1)
            }
        }

        
        val quarticEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v * 2
                if (i1 < 1) return i1 * i1 * i1 * i1 / 2
                val i2 = i1 - 2
                return -(i2 * i2 * i2 * i2 - 2) / 2
            }
        }

        
        val quinticEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return v * v * v * v * v
            }
        }

        
        val quinticEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v - 1
                return i1 * i1 * i1 * i1 * i1 + 1
            }
        }

        
        val quinticEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val i1 = v * 2
                if (i1 < 1) return i1 * i1 * i1 * i1 * i1 / 2
                val i2 = i1 - 2
                return (i2 * i2 * i2 * i2 * i2 + 2) / 2
            }
        }

        
        val elasticEasein: Interpolator = object : Interpolator() {
            override fun curve(t: Double): Double {
                val a = 1.0
                if (t == 0.0) return 0.0
                if (t == 1.0) return 1.0
                val p = 0.3
                val s: Double = p / (2 * Math.PI) * asin(1 / a)
                val i1 = t - 1
                return -(a * (2.0).pow(10 * i1) * sin((i1 - s) * (2 * Math.PI) / p))
            }
        }

        
        val elasticEaseout: Interpolator = object : Interpolator() {
            override fun curve(t: Double): Double {
                val a = 1.0
                if (t == 0.0) return 0.0
                if (t == 1.0) return 1.0
                val p = 0.3
                val s: Double = p / (2 * Math.PI) * asin(1 / a)
                return a * (2.0).pow(-10 * t) * sin((t - s) * (2 * Math.PI) / p)
            }
        }

        
        val elasticEaseboth: Interpolator = object : Interpolator() {
            override fun curve(t: Double): Double {
                val a = 1.0
                if (t == 0.0) return 0.0
                val i1 = t * 2
                if (i1 == 2.0) return 1.0
                val p: Double = 0.3 * 1.5
                val s: Double = p / (2 * Math.PI) * asin(1 / a)
                if (i1 < 1) {
                    val i2 = i1 - 1
                    return -0.5 * (a * (2.0).pow(10 * i2) * sin((i2 - s) * (2 * Math.PI) / p))
                }
                val i3 = i1 - 1
                return a * (2.0).pow(-10 * i3) * sin((i3 - s) * (2 * Math.PI) / p) * 0.5
            }
        }

        
        val backEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val s = 1.70158
                return v * v * ((s + 1) * v - s)
            }
        }

        
        val backEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val s = 1.70158
                val i1 = v - 1
                return i1 * i1 * ((s + 1) * i1 + s) + 1
            }
        }

        
        val backEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                val s = 1.70158
                val i1 = v * 2
                val i2 = s * 1.525
                if (i1 < 1) return i1 * i1 * ((i2 + 1) * i1 - i2) / 2
                val i3 = i1 - 2
                val i4 = s * 1.525
                return (i3 * i3 * ((i4 + 1) * i3 + i4) + 2) / 2
            }
        }

        
        val bounceEaseout: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                var i1 = v
                if (i1 < 1 / 2.75) return 7.5625 * i1 * i1
                if (i1 < 2 / 2.75) {
                    i1 -= 1.5 / 2.75
                    return 7.5625 * i1 * i1 + 0.75
                }
                if (i1 < 2.5 / 2.75) {
                    i1 -= 2.25 / 2.75
                    return 7.5625 * i1 * i1 + 0.9375
                }
                i1 -= 2.625 / 2.75
                return 7.5625 * i1 * i1 + 0.984375
            }
        }
        
        val bounceEasein: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return 1 - bounceEaseout.interpolate(0.0, 1.0, 1 - v)
            }
        }
        
        val bounceEaseboth: Interpolator = object : Interpolator() {
            override fun curve(v: Double): Double {
                return if (v < 0.5) bounceEasein.interpolate(
                    0.0,
                    1.0,
                    v * 2
                ) / 2 else 0.5 + bounceEaseout.interpolate(0.0, 1.0, v * 2 - 1) / 2
            }
        }
    }
}