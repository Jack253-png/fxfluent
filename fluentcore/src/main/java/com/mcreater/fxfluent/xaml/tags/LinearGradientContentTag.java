package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.brush.LinearGradientColorBrush;
import com.mcreater.fxfluent.xaml.XAMLHelper;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class LinearGradientContentTag extends SimpleContentTag<LinearGradientColorBrush> {
    public LinearGradientColorBrush toObject() {
        String startPoint = element.attributeValue("StartPoint");
        String endPoint = element.attributeValue("EndPoint");
        List<Stop> stops = new Vector<>();
        element.element("LinearGradientBrush.GradientStops").elements().forEach(element -> {
            double offset = Double.parseDouble(element.attributeValue("Offset"));
            double opacity = Double.parseDouble(Optional.ofNullable(element.attributeValue("Opacity")).orElse("1.0"));
            Color color = XAMLHelper.parseAnyColor(dict, element.attributeValue("Color"), opacity);
            stops.add(new Stop(offset, color));
        });

        return new LinearGradientColorBrush(new LinearGradient(
                0, 0, 0, 3, true, CycleMethod.NO_CYCLE,
                stops
        ));
    }
}
