package com.mcreater.fxfluent.brush;

import javafx.animation.Interpolatable;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;

import java.util.function.BiConsumer;

public abstract class AbstractColorBrush implements Interpolatable<AbstractColorBrush>, BiConsumer<Region, BiConsumer<Region, AbstractColorBrush>> {
    public AbstractColorBrush interpolate(AbstractColorBrush abstractColorBrush, double v) {
        if (abstractColorBrush instanceof SolidColorBrush && this instanceof SolidColorBrush) {
            return new SolidColorBrush(toSolidColorBrush().getPaint().interpolate(abstractColorBrush.toSolidColorBrush().getPaint(), v));
        }
        else return null;
    }

    private SolidColorBrush toSolidColorBrush() {
        return (SolidColorBrush) this;
    }

    public abstract Paint getPaint();
    public abstract BorderWidths getBorderWidths();
    public void accept(Region region, BiConsumer<Region, AbstractColorBrush> regionAbstractColorBrushBiConsumer) {
        regionAbstractColorBrushBiConsumer.accept(region, this);
    }
}
