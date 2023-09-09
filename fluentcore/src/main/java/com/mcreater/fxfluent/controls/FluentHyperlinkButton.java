package com.mcreater.fxfluent.controls;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;

public class FluentHyperlinkButton extends Hyperlink {
    public FluentHyperlinkButton() {
        init();
    }

    public FluentHyperlinkButton(String var1) {
        super(var1);
        init();
    }

    public FluentHyperlinkButton(String var1, Node var2) {
        super(var1, var2);
        init();
    }

    private void init() {
        getStyleClass().add("fluent-hyperlink-button");
    }
    public String getUserAgentStylesheet() {
        return FluentToggleButton.class.getClassLoader().getResource("css/FluentHyperlinkButton.css").toString();
    }
}
