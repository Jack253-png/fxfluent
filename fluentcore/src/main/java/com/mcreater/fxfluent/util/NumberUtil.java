package com.mcreater.fxfluent.util;

public class NumberUtil {
    public static double lim(double res, double up, double down) {
        double swap;
        if (down > up) {
            swap = up;
            up = down;
            down = swap;
        }

        return Math.max(down, Math.min(up, res));
    }

    public static int lim(int res, int up, int down) {
        int swap;
        if (down > up) {
            swap = up;
            up = down;
            down = swap;
        }

        return Math.max(down, Math.min(up, res));
    }
}
