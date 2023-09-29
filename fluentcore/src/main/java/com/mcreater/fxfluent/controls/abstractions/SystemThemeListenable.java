package com.mcreater.fxfluent.controls.abstractions;

import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.function.Consumer;

public interface SystemThemeListenable {
    Collection<Node> getNode();
    default void init() {
        SystemThemeLoop.addListener(appColorTheme -> getNode().forEach(new Consumer<Node>() {
            public void accept(Node node) {
                if (node instanceof Themeable) ((Themeable) node).onUpdateTheme(appColorTheme);
                if (node instanceof Pane) ((Pane) node).getChildren().forEach(this);
            }
        }));
    }
}
