package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.*;
import com.mcreater.fxfluent.controls.skin.FluentButtonSkin;
import com.mcreater.fxfluent.controls.value.StateMap;
import com.mcreater.fxfluent.xaml.ResourceDict;
import com.mcreater.fxfluent.xaml.XamlManager;
import com.mcreater.fxfluent.xaml.style.AppColorTheme;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.layout.CornerRadii;

import static com.mcreater.fxfluent.controls.value.ControlMaps.Button.Companion;

public class FluentButton extends Button implements CornerRadiusable, Backgroundable, Foregroundable, Borderable, Dictable, Themeable {
    private AppColorTheme theme = AppColorTheme.SYSTEM;
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

    public CornerRadii getCornerRadii() {
        return new CornerRadii(4);
    }

    public StateMap getBackgroundRemap() {
        return isDefaultButton() ? Companion.getBG_ACCENT_KEY_MAP() : Companion.getBG_KEY_MAP();
    }

    public StateMap getForegroundRemap() {
        return isDefaultButton() ? Companion.getFG_ACCENT_KEY_MAP() : Companion.getFG_KEY_MAP();
    }

    public StateMap getBorderRemap() {
        return isDefaultButton() ? Companion.getBRD_BOTTOM_ACCENT_KEY_MAP() : Companion.getBRD_BOTTOM_KEY_MAP();
    }

    public ResourceDict getResourceDict() {
        return XamlManager.getDict(theme);
    }

    public void onUpdateTheme(AppColorTheme theme) {
        this.theme = theme;
        ((FluentButtonSkin) getSkin()).implUpdate();
    }
}
