package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.skin.FluentButtonSkin;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class FluentButton extends Button {
    private Timeline backgroundColor;
    private final ObjectProperty<Color> backgroundColorProperty = new SimpleObjectProperty<>();
    public FluentButton() {
        this.init();
    }

    public FluentButton(String var1) {
        super(var1);
        this.init();
    }

    public FluentButton(String var1, Node var2) {
        super(var1, var2);
        this.init();
    }
    private void init() {
        getStyleClass().add("fluent-button");
        setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(10),
                new BorderWidths(1)
        )));
    }
    public String getUserAgentStylesheet() {
        return FluentButton.class.getClassLoader().getResource("css/FluentButton.css").toString();
    }

    protected Skin<?> createDefaultSkin() {
        return new FluentButtonSkin(this);
    }
}
