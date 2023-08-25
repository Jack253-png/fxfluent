package com.mcreater.fxfluent.util;

import com.mcreater.fxfluent.syslib.UiShellWrapper;
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

    public static BiConsumer<Region, Color> backgroundFill(CornerRadii cornerRadii) {
        return (region, color) -> region.setBackground(new Background(new BackgroundFill(
                color,
                cornerRadii,
                Insets.EMPTY
        )));
    }

    public static BiConsumer<Region, Color> textFill() {
        return (region, color) -> {
            if (region instanceof Labeled) ((Labeled) region).setTextFill(color);
        };
    }

    public static BiConsumer<Region, Color> borderFill(BorderOrientation orientation, CornerRadii cornerRadii, int borderWidths, Insets insets) {
        return (region, color) -> {
            if (region.getBorder() == null || region.getBorder().getStrokes().size() == 0) {
                region.setBorder(new Border(new BorderStroke(
                        orientation == BorderOrientation.TOP ? color : Color.TRANSPARENT,
                        orientation == BorderOrientation.RIGHT ? color : Color.TRANSPARENT,
                        orientation == BorderOrientation.BOTTOM ? color : Color.TRANSPARENT,
                        orientation == BorderOrientation.LEFT ? color : Color.TRANSPARENT,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        cornerRadii,
                        new BorderWidths(borderWidths),
                        insets
                )));
            }
            else {
                List<BorderStroke> strokes = new Vector<>();
                region.getBorder().getStrokes().forEach(borderStroke -> strokes.add(new BorderStroke(
                        orientation == BorderOrientation.TOP ? color : borderStroke.getTopStroke(),
                        orientation == BorderOrientation.RIGHT ? color : borderStroke.getRightStroke(),
                        orientation == BorderOrientation.BOTTOM ? color : borderStroke.getBottomStroke(),
                        orientation == BorderOrientation.LEFT ? color : borderStroke.getLeftStroke(),
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        BorderStrokeStyle.SOLID,
                        cornerRadii,
                        new BorderWidths(borderWidths / (UiShellWrapper.GetSystemIsDark() ? 2D : 1D)),
                        insets
                )));
                region.setBorder(new Border(strokes, new Vector<>()));
            }
        };
    }
}
