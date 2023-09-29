package com.mcreater.fxfluent.brush;

import com.mcreater.fxfluent.xaml.ResourceDict;
import com.mcreater.fxfluent.xaml.XAMLManager;
import javafx.animation.Interpolatable;
import javafx.scene.layout.Region;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;

import java.util.List;
import java.util.Vector;
import java.util.function.BiConsumer;

public abstract class AbstractColorBrush implements Interpolatable<AbstractColorBrush>, BiConsumer<Region, BiConsumer<Region, AbstractColorBrush>> {
    public AbstractColorBrush interpolate(AbstractColorBrush abstractColorBrush, double v) {
        if (v == 0) return this;
        if (v == 1) return abstractColorBrush;
        if (abstractColorBrush instanceof SolidColorBrush && this instanceof SolidColorBrush) {
            return new SolidColorBrush(toSolidColorBrush().getPaint().interpolate(abstractColorBrush.toSolidColorBrush().getPaint(), v));
        }
        if (abstractColorBrush instanceof LinearGradientColorBrush) {
            LinearGradient end = abstractColorBrush.toLinearGradientColorBrush().getPaint();
            LinearGradient start = this.toLinearGradientColorBrush().getPaint();
            List<Stop> stops = new Vector<>();
            end.getStops().forEach(stop -> {
                final double[] offsetdelta = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                final Stop[] result = {stop};
                start.getStops().forEach(s -> {
                    double noff = Math.abs(stop.getOffset() - s.getOffset());
                    if (noff < offsetdelta[0]) {
                        offsetdelta[0] = noff;
                        offsetdelta[1] = stop.getOffset() - s.getOffset();
                        result[0] = s;
                    }
                });
                stops.add(new Stop(
                        result[0].getOffset() + (stop.getOffset() - result[0].getOffset()) * v,
                        result[0].getColor().interpolate(stop.getColor(), v)
                ));
            });
            double startX = start.getStartX() + (end.getStartX() - start.getStartX()) * v;
            double startY = start.getStartY() + (end.getStartY() - start.getStartY()) * v;
            double endX = start.getEndX() + (end.getEndX() - start.getEndX()) * v;
            double endY = start.getEndY() + (end.getEndY() - start.getEndY()) * v;

            return new LinearGradientColorBrush(
                    new LinearGradient(
                            startX, startY, endX, endY, true, CycleMethod.NO_CYCLE,
                            stops
                    )
            );
        }
        return abstractColorBrush;
    }

    private SolidColorBrush toSolidColorBrush() {
        return (SolidColorBrush) this;
    }
    private LinearGradientColorBrush toLinearGradientColorBrush() {
        try {
            return (LinearGradientColorBrush) this;
        }
        catch (Exception ignored) {}

        return new LinearGradientColorBrush(
                new LinearGradient(
                        0, 0, 0, 3, true, CycleMethod.NO_CYCLE,
                        new Stop(0, toSolidColorBrush().getPaint()), new Stop(1, toSolidColorBrush().getPaint())
                )
        );
    }

    public abstract Paint getPaint();
    public void accept(Region region, BiConsumer<Region, AbstractColorBrush> regionAbstractColorBrushBiConsumer) {
        regionAbstractColorBrushBiConsumer.accept(region, this);
    }

    public static AbstractColorBrush find(String k) {
        return XAMLManager.getCurrentDict().findColorBrush(k);
    }

    public static AbstractColorBrush find(String k, ResourceDict dict) {
        return dict.findColorBrush(k);
    }
}
