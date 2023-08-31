package com.mcreater.fxfluent.brush;

import com.mcreater.fxfluent.util.ImageUtil;
import javafx.geometry.Bounds;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.function.BiConsumer;

public class AcrylicInAppFillColorBrush implements BiConsumer<Region, Map<String, Object>> {
    private static final List<Map.Entry<Region, Map<String, Object>>> APPLIED_CONTROLS = new Vector<>();
    private static final List<Thread> calcThreads = new Vector<>();
    public void accept(Region region, Map<String, Object> stringObjectMap) {
        APPLIED_CONTROLS.add(new AbstractMap.SimpleEntry<>(region, stringObjectMap));
        int index = APPLIED_CONTROLS.size() - 1;
        calcThreads.add(new Thread("Widget blur calc thread #" + index) {
            public void run() {
                while (APPLIED_CONTROLS.size() > index) {
                    try {
                        Region reg = APPLIED_CONTROLS.get(index).getKey();
                        if (reg.getRotate() != 0) throw new Exception("Not supported for rotation");
                        if (reg.getScaleX() != 1 || reg.getScaleY() != 1) throw new Exception("Not supported for scaling");

                        Bounds boundsInScene = reg.localToScene(reg.getBoundsInLocal());
                        reg.setBackground(new Background(
                                new BackgroundImage(
                                        ImageUtil.snapshot(
                                                reg.getScene(),
                                                boundsInScene,
                                                new Color(1, 1, 1, 0.35),
                                                () -> reg.setOpacity(0),
                                                () -> reg.setOpacity(1)),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.DEFAULT,
                                        BackgroundSize.DEFAULT
                                )
                        ));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        calcThreads.get(calcThreads.size()-1).start();
    }
}
