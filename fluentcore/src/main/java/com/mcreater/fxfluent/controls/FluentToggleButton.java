package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.Backgroundable;
import com.mcreater.fxfluent.controls.abstractions.Borderable;
import com.mcreater.fxfluent.controls.abstractions.CornerRadiusable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentToggleButtonSkin;
import com.mcreater.fxfluent.controls.value.StateMap;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.CornerRadii;

import static com.mcreater.fxfluent.controls.value.ControlMaps.Button.*;

public class FluentToggleButton extends ToggleButton implements CornerRadiusable, Backgroundable, Foregroundable, Borderable {
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

    public CornerRadii getCornerRadii() {
        return new CornerRadii(4);
    }

    public StateMap getBackgroundRemap() {
        return isSelected() ? BG_ACCENT_KEY_MAP : BG_KEY_MAP;
    }

    public StateMap getBorderRemap() {
        return isSelected() ? BRD_BOTTOM_ACCENT_KEY_MAP : BRD_BOTTOM_KEY_MAP;
    }

    public StateMap getForegroundRemap() {
        return isSelected() ? FG_ACCENT_KEY_MAP : FG_KEY_MAP;
    }
}
