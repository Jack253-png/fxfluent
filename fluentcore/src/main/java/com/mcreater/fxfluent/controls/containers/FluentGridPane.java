package com.mcreater.fxfluent.controls.containers;

import com.mcreater.fxfluent.controls.abstractions.SystemThemeListenable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.Collection;

public class FluentGridPane extends GridPane implements SystemThemeListenable {
    public FluentGridPane() {
        init();
    }

    public FluentGridPane(Node... var1) {
        this.getChildren().addAll(var1);
        init();
    }

    public Collection<Node> getNode() {
        return getChildren();
    }
}
