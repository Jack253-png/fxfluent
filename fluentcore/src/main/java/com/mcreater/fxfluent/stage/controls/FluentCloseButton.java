package com.mcreater.fxfluent.stage.controls;

import com.mcreater.fxfluent.controls.FluentButton;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class FluentCloseButton extends Button {
    public FluentCloseButton() {
        this.init();
    }

    private FluentCloseButton(String var1) {
        super(var1);
        this.init();
    }

    public FluentCloseButton(String var1, Node var2) {
        super(var1, var2);
        this.init();
    }
    private void init() {
        getStyleClass().add("fluent-close-button");
        setPrefSize(48, 34);
    }
    public String getUserAgentStylesheet() {
        return FluentButton.class.getClassLoader().getResource("css/FluentCloseButton.css").toString();
    }
}
