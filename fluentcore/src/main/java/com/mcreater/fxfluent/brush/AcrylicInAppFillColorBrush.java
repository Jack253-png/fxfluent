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
    private static List<Map.Entry<Region, Map<String, Object>>> APPLIED_CONTROLS = new Vector<>();
    static {
        new Thread("Widget blur thread") {
            public void run() {
                while (true) {
                    APPLIED_CONTROLS.forEach(regionMapEntry -> {
                        try {
                            Region reg = regionMapEntry.getKey();
                            if (reg.getRotate() != 0) throw new Exception("Not supported for rotation");

                            Bounds boundsInScene = reg.localToScene(reg.getBoundsInLocal());
                            reg.setBackground(new Background(
                                    new BackgroundImage(
                                            ImageUtil.snapshot(
                                                    reg.getScene(),
                                                    boundsInScene,
                                                    new Color(1, 1, 1, 0.1),
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
                    });

                    /*try {
                        Thread.sleep(1);
                    }
                    catch (InterruptedException ignored) {}*/
                }
            }
        }.start();
    }
    public void accept(Region region, Map<String, Object> stringObjectMap) {
        APPLIED_CONTROLS.add(new AbstractMap.SimpleEntry<>(region, stringObjectMap));
    }
}
