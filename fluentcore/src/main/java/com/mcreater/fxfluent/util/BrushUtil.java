package com.mcreater.fxfluent.util;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import javafx.geometry.Insets;
import javafx.scene.control.Labeled;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Vector;
import java.util.function.BiConsumer;

public class BrushUtil {
    public enum BorderOrientation {
        TOP, BOTTOM, LEFT, RIGHT
    }

    public static BiConsumer<Region, AbstractColorBrush> backgroundFill(CornerRadii cornerRadii) {
        return (region, color) -> region.setBackground(new Background(new BackgroundFill(
                color.getPaint(),
                cornerRadii,
                Insets.EMPTY
        )));
    }

    public static BiConsumer<Region, AbstractColorBrush> textFill() {
        return (region, color) -> {
            if (region instanceof Labeled) ((Labeled) region).setTextFill(color.getPaint());
        };
    }
    public static BiConsumer<Region, AbstractColorBrush> borderFill(BorderOrientation orientation, CornerRadii cornerRadii) {
        return (region, color) -> {
            Insets insets = Insets.EMPTY;
            if (region.getBorder() == null || region.getBorder().getStrokes().size() == 0) {
                region.setBorder(new Border(new BorderStroke(
                        orientation == null || orientation == BorderOrientation.TOP ? color.getPaint() : Color.TRANSPARENT,
                        orientation == null || orientation == BorderOrientation.RIGHT ? color.getPaint() : Color.TRANSPARENT,
                        orientation == null || orientation == BorderOrientation.BOTTOM ? color.getPaint() : Color.TRANSPARENT,
                        orientation == null || orientation == BorderOrientation.LEFT ? color.getPaint() : Color.TRANSPARENT,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        cornerRadii,
                        new BorderWidths(1),
                        insets
                )));
            }
            else {
                List<BorderStroke> strokes = new Vector<>();
                region.getBorder().getStrokes().forEach(borderStroke -> strokes.add(new BorderStroke(
                        orientation == null || orientation == BorderOrientation.TOP ? color.getPaint() : borderStroke.getTopStroke(),
                        orientation == null || orientation == BorderOrientation.RIGHT ? color.getPaint() : borderStroke.getRightStroke(),
                        orientation == null || orientation == BorderOrientation.BOTTOM ? color.getPaint() : borderStroke.getBottomStroke(),
                        orientation == null || orientation == BorderOrientation.LEFT ? color.getPaint() : borderStroke.getLeftStroke(),
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        cornerRadii,
                        new BorderWidths(1),
                        insets
                )));
                region.setBorder(new Border(strokes, new Vector<>()));
            }
        };
    }
}
