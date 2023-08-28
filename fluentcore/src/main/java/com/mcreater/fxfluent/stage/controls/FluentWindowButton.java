package com.mcreater.fxfluent.stage.controls;

import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class FluentWindowButton extends Button {
    public FluentWindowButton() {
        this.init();
    }

    private FluentWindowButton(String var1) {
        super(var1);
        this.init();
    }

    public FluentWindowButton(String var1, Node var2) {
        super(var1, var2);
        this.init();
    }
    private void init() {
        getStyleClass().add("fluent-window-button");
        setPrefSize(48, 34);
        setMaxSize(48, 34);
        setMinSize(48, 34);
    }
    public String getUserAgentStylesheet() {
        return FluentButton.class.getClassLoader().getResource(SystemThemeLoop.isDark() ? "css/FluentWindowButtonDark.css" : "css/FluentWindowButtonLight.css").toString();
    }
}
