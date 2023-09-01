package com.mcreater.fxfluent.util;

public class MathUtil {
    public static double triCompute(double orig, double triArg, double dlbArg, double slgArg, double n) {
        return orig*orig*orig*triArg + orig*orig*dlbArg + orig*slgArg + n;
    }
}
