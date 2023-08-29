package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.skin.FluentToggleButtonSkin;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;

public class FluentToggleButton extends ToggleButton {
    public FluentToggleButton() {
        init();
    }
    public FluentToggleButton(String var1) {
        super(var1);
        init();
    }
    public FluentToggleButton(String var1, Node var2) {
        super(var1, var2);
        init();
    }
    private void init() {
        getStyleClass().add("fluent-toggle-button");
    }

    public String getUserAgentStylesheet() {
        return FluentToggleButton.class.getClassLoader().getResource("css/FluentToggleButton.css").toString();
    }

    protected Skin<?> createDefaultSkin() {
        return new FluentToggleButtonSkin(this);
    }
}
