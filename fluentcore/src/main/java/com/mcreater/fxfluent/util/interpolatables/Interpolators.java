package com.mcreater.fxfluent.util.interpolatables;

import javafx.animation.Interpolator;

public class Interpolators {
    public static Interpolator LINEAR = new Interpolator() {
        protected double curve(double v) {
            return v;
        }
    };
    public static Interpolator QUADRATIC_EASEIN = new Interpolator() {
        protected double curve(double v) {
            return v * v;
        }
    };
    public static Interpolator QUADRATIC_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            return 1 - v * v;
        }
    };
    public static Interpolator QUADRATIC_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            double i1 = v * 2;
            if (i1 < 1) return i1 * i1 / 2;
            double i2 = i1 - 1;
            return - (i2 * (i2 - 2) - 1) / 2;
        }
    };
    public static Interpolator SINUSOIDAL_EASEIN = new Interpolator() {
        protected double curve(double v) {
            return 1 - Math.cos(v * (Math.PI / 2));
        }
    };
    public static Interpolator SINUSOIDAL_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            return Math.sin(v * (Math.PI / 2));
        }
    };
    public static Interpolator SINUSOIDAL_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            return - (Math.cos(Math.PI * v) - 1) / 2;
        }
    };
    public static Interpolator EXPONENTIAL_EASEIN = new Interpolator() {
        protected double curve(double v) {
            if (v == 0) return 0;
            return Math.pow(2, 10 * (v - 1));
        }
    };

    public static Interpolator EXPONENTIAL_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            if (v == 1) return 1;
            return (-Math.pow(2, -10 * v) + 1);
        }
    };

    public static Interpolator EXPONENTIAL_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            if (v == 0) return 0;
            if (v == 1) return 1;
            double i1 = v * 2;
            if (i1 < 1) return Math.pow(2, 10 * (i1 - 1)) / 2;
            return (-Math.pow(2, -10 * (i1 - 1)) + 2) / 2;
        }
    };

    public static Interpolator CIRCULAR_EASEIN = new Interpolator() {
        protected double curve(double v) {
            return - (Math.sqrt(1 - v * v) - 1);
        }
    };

    public static Interpolator CIRCULAR_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            double i1 = v - 1;
            return Math.sqrt(1 - i1 * i1);
        }
    };

    public static Interpolator CIRCULAR_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            double i1 = v * 2;
            if (i1 < 1) return - (Math.sqrt(1 - i1 * i1) - 1) / 2;
            double i2 = i1 - 2;
            return (Math.sqrt(1 - i2 * i2) + 1) / 2;
        }
    };

    public static Interpolator CUBIC_EASEIN = new Interpolator() {
        protected double curve(double v) {
            return v * v * v;
        }
    };

    public static Interpolator CUBIC_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            double i1 = v - 1;
            return i1 * i1 * i1 + 1;
        }
    };

    public static Interpolator CUBIC_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            double i1 = v * 2;
            if (i1 < 1) return i1 * i1 * i1 / 2;
            double i2 = i1 - 2;
            return (i2 * i2 * i2 + 2) / 2;
        }
    };

    public static Interpolator QUARTIC_EASEIN = new Interpolator() {
        protected double curve(double v) {
            return v * v * v * v;
        }
    };

    public static Interpolator QUARTIC_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            double i1 = v - 1;
            return - (i1 * i1 * i1 * i1 - 1);
        }
    };

    public static Interpolator QUARTIC_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            double i1 = v * 2;
            if (i1 < 1) return i1 * i1 * i1 * i1 / 2;
            double i2 = i1 - 2;
            return - (i2 * i2 * i2 * i2 - 2) / 2;
        }
    };

    public static Interpolator QUINTIC_EASEIN = new Interpolator() {
        protected double curve(double v) {
            return v * v * v * v * v;
        }
    };

    public static Interpolator QUINTIC_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            double i1 = v - 1;
            return i1 * i1 * i1 * i1 * i1 + 1;
        }
    };

    public static Interpolator QUINTIC_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            double i1 = v * 2;
            if (i1 < 1) return i1 * i1 * i1 * i1 * i1 / 2;
            double i2 = i1 - 2;
            return (i2 * i2 * i2 * i2 * i2 + 2) / 2;
        }
    };

    public static Interpolator ELASTIC_EASEIN = new Interpolator() {
        protected double curve(double t) {
            double p;
            double a = 1;
            if (t == 0) return 0;
            if (t == 1) return 1;
            p = 0.3;
            double s;
            s = p / (2 * Math.PI) * Math.asin(1 / a);

            double i1 = t - 1;
            return -(a * Math.pow(2, 10 * i1) * Math.sin((i1 - s) * (2 * Math.PI) / p));
        }
    };

    public static Interpolator ELASTIC_EASEOUT = new Interpolator() {
        protected double curve(double t) {
            double p;
            double a = 1;
            if (t == 0) return 0;
            if (t == 1) return 1;
            p = 0.3;
            double s;
            s = p / (2 * Math.PI) * Math.asin(1 / a);
            return a * Math.pow(2, -10 * t) * Math.sin((t - s) * (2 * Math.PI) / p);
        }
    };

    public static Interpolator ELASTIC_EASEBOTH = new Interpolator() {
        protected double curve(double t) {
            double p;
            double a = 1;
            if (t == 0) return 0;
            double i1 = t * 2;
            if (i1 == 2) return 1;
            p = (0.3 * 1.5);
            double s;
            s = p / (2 * Math.PI) * Math.asin(1 / a);

            if (i1 < 1) {
                double i2 = i1 - 1;
                return -0.5 * (a * Math.pow(2, 10 * i2) * Math.sin((i2 - s) * (2 * Math.PI) / p));
            }
            double i3 = i1 - 1;
            return a * Math.pow(2, -10 * i3) * Math.sin((i3 - s) * (2 * Math.PI) / p) * 0.5;
        }
    };

    public static Interpolator BACK_EASEIN = new Interpolator() {
        protected double curve(double v) {
            double s = 1.70158;
            return v * v * ((s + 1) * v - s);
        }
    };

    public static Interpolator BACK_EASEOUT = new Interpolator() {
        protected double curve(double v) {
            double s = 1.70158;
            double i1 = v - 1;
            return (i1 * i1 * ((s + 1) * i1 + s) + 1);
        }


    };

    public static Interpolator BACK_EASEBOTH = new Interpolator() {
        protected double curve(double v) {
            double s = 1.70158;
            double i1 = v * 2;
            double i2 = s * 1.525;
            if (i1 < 1) return (i1 * i1 * ((i2 + 1) * i1 - i2)) / 2;

            double i3 = i1 - 2;
            double i4 = s * 1.525;
            return (i3 * i3 * ((i4 + 1) * i3 + i4) + 2) / 2;
        }
    };
}
