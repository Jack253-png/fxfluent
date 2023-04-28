package com.mcreater.fluentdemo.util;

public class MathUtil {
    public static double limit(Number n, int limit, int base) {
        if (n.doubleValue() <= base) return base;
        return Math.min(n.doubleValue(), limit);
    }
}
