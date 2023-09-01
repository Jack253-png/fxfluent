package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.Backgroundable;
import com.mcreater.fxfluent.controls.abstractions.Borderable;
import com.mcreater.fxfluent.controls.abstractions.CornerRadiusable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentCheckBoxSkin;
import com.mcreater.fxfluent.controls.state.StateType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Skin;
import javafx.scene.layout.CornerRadii;

import java.util.Map;

import static com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.*;

public class FluentCheckBox extends CheckBox implements CornerRadiusable, Backgroundable, Foregroundable, Borderable {
    public FluentCheckBox() {
        init();
    }

    public FluentCheckBox(String c) {
        super(c);
        init();
    }

    private void init() {
        getStyleClass().add("fluent-check-box");
    }

    public String getUserAgentStylesheet() {
        return FluentButton.class.getClassLoader().getResource("css/FluentCheckBox.css").toString();
    }

    protected Skin<?> createDefaultSkin() {
        return new FluentCheckBoxSkin(this);
    }

    public CornerRadii getCornerRadii() {
        return new CornerRadii(6);
    }

    public Map<StateType, String> getBackgroundRemap() {
        return isSelected() ? BG_PRESSED_KEY_MAP : BG_KEY_MAP;
    }

    public Map<StateType, String> getBorderRemap() {
        return isSelected() ? BRD_PRESSED_KEY_MAP : BRD_KEY_MAP;
    }

    public Map<StateType, String> getForegroundRemap() {
        return FG_KEY_MAP;
    }
}
