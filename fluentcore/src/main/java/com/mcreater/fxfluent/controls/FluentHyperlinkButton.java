package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.*;
import com.mcreater.fxfluent.controls.skin.FluentHyperlinkButtonSkin;
import com.mcreater.fxfluent.controls.value.StateMap;
import com.mcreater.fxfluent.xaml.ResourceDict;
import com.mcreater.fxfluent.xaml.XamlManager;
import com.mcreater.fxfluent.xaml.style.AppColorTheme;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Skin;
import javafx.scene.layout.CornerRadii;

import static com.mcreater.fxfluent.controls.value.ControlMaps.HyperLinkButton.BG_KEY_MAP;
import static com.mcreater.fxfluent.controls.value.ControlMaps.HyperLinkButton.FG_KEY_MAP;

public class FluentHyperlinkButton extends Hyperlink implements CornerRadiusable, Backgroundable, Foregroundable, Dictable, Themeable {
    private AppColorTheme theme = AppColorTheme.SYSTEM;
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

    public ResourceDict getResourceDict() {
        return XamlManager.getDict(theme);
    }

    public void onUpdateTheme(AppColorTheme theme) {
        this.theme = theme;
        ((FluentHyperlinkButtonSkin) getSkin()).implUpdate();
    }
}
