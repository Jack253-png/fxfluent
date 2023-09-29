package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.Dictable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.abstractions.Themeable;
import com.mcreater.fxfluent.controls.skin.FluentLabelSkin;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.controls.value.StateMap;
import com.mcreater.fxfluent.xaml.ResourceDict;
import com.mcreater.fxfluent.xaml.XAMLManager;
import com.mcreater.fxfluent.xaml.style.AppColorTheme;
import javafx.scene.Node;
import javafx.scene.control.Label;

import static com.mcreater.fxfluent.brush.AbstractColorBrush.find;

public class FluentLabel extends Label implements Foregroundable, Dictable, Themeable {
    private AppColorTheme theme = AppColorTheme.SYSTEM;
    public FluentLabel() {
        this.init();
    }

    public FluentLabel(String var1) {
        super(var1);
        this.init();
    }

    public FluentLabel(String var1, Node var2) {
        super(var1, var2);
        this.init();
    }

    private void init() {
        getStyleClass().add("fluent-label");
    }

    protected FluentLabelSkin createDefaultSkin() {
        return new FluentLabelSkin(this);
    }

    public StateMap getForegroundRemap() {
        return new StateMap() {{
            put(StateType.NONE, d -> find("TextFillColorPrimaryBrush", d));
        }};
    }

    public ResourceDict getResourceDict() {
        return XAMLManager.getDict(theme);
    }

    public void onUpdateTheme(AppColorTheme theme) {
        this.theme = theme;
        ((FluentLabelSkin) getSkin()).implUpdate();
    }
}
