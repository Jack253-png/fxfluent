package com.mcreater.fxfluent.util;

import javafx.scene.Node;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;

public class ControlUtil {
    public static Region findControlInSkin(SkinBase<?> skin, String s) {
        final Node[] r = {null};
        skin.getChildren().forEach(node -> {
            if (node.getStyleClass().contains(s)) {
                r[0] = node;
            }
        });
        return (Region) r[0];
    }
}
