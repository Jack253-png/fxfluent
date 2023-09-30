package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.brush.LinearGradientColorBrush;
import com.mcreater.fxfluent.xaml.XamlHelper;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class LinearGradientBrushContentTag extends SimpleContentTag<LinearGradientColorBrush> {
    public LinearGradientColorBrush toObject() {
        String startPoint = element().attributeValue("StartPoint");
        String endPoint = element().attributeValue("EndPoint");
        List<Stop> stops = new Vector<>();
        element().element("LinearGradientBrush.GradientStops").elements().forEach(element -> {
            double offset = Double.parseDouble(element.attributeValue("Offset"));
            double opacity = Double.parseDouble(Optional.ofNullable(element.attributeValue("Opacity")).orElse("1.0"));
            Color color = XamlHelper.parseAnyColor(getDict(), element.attributeValue("Color"), opacity);
            stops.add(new Stop(offset, color));
        });
        if (stops.stream().noneMatch(stop -> stop.getOffset() == 0)) {
            stops.add(0, new Stop(0, Color.TRANSPARENT));
        }

        return new LinearGradientColorBrush(new LinearGradient(
                Double.parseDouble(startPoint.split(",")[0]),
                Double.parseDouble(startPoint.split(",")[1]),
                Double.parseDouble(endPoint.split(",")[0]),
                Double.parseDouble(endPoint.split(",")[1]),
                true,
                CycleMethod.NO_CYCLE,
                stops
        ));
    }
}
