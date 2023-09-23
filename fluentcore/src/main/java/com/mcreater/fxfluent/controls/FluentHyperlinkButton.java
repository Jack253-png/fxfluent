package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.Backgroundable;
import com.mcreater.fxfluent.controls.abstractions.CornerRadiusable;
import com.mcreater.fxfluent.controls.abstractions.Dictable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentHyperlinkButtonSkin;
import com.mcreater.fxfluent.controls.value.StateMap;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Skin;
import javafx.scene.layout.CornerRadii;

import static com.mcreater.fxfluent.controls.value.ControlMaps.HyperLinkButton.BG_KEY_MAP;
import static com.mcreater.fxfluent.controls.value.ControlMaps.HyperLinkButton.FG_KEY_MAP;

public class FluentHyperlinkButton extends Hyperlink implements CornerRadiusable, Backgroundable, Foregroundable, Dictable {
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

    public StateMap getBackgroundRemap() {
        return BG_KEY_MAP;
    }

    public StateMap getForegroundRemap() {
        return FG_KEY_MAP;
    }

    protected Skin<?> createDefaultSkin() {
        return new FluentHyperlinkButtonSkin(this);
    }

    public CornerRadii getCornerRadii() {
        return new CornerRadii(6);
    }
}
