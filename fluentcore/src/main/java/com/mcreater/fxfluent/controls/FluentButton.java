package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.skin.FluentButtonSkin;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;

public class FluentButton extends Button {
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
    }
    public String getUserAgentStylesheet() {
        return FluentButton.class.getClassLoader().getResource("css/FluentButton.css").toString();
    }

    protected Skin<?> createDefaultSkin() {
        return new FluentButtonSkin(this);
    }
}
