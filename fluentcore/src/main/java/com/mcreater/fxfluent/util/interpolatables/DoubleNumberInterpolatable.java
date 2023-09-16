package com.mcreater.fxfluent.util.interpolatables;

import javafx.animation.Interpolatable;
import javafx.util.Pair;

public class DoubleNumberInterpolatable extends Pair<Double, Double> implements Interpolatable<DoubleNumberInterpolatable> {
    public DoubleNumberInterpolatable(Double aDouble, Double aDouble2) {
        super(aDouble, aDouble2);
    }

    public DoubleNumberInterpolatable interpolate(DoubleNumberInterpolatable d, double v) {
        return new DoubleNumberInterpolatable(
                getKey() - (d.getKey() - getKey()) * v,
                getValue() - (d.getValue() - getValue()) * v
        );
    }
}
