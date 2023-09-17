package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.*;
import com.mcreater.fxfluent.controls.skin.FluentCheckBoxSkin;
import com.mcreater.fxfluent.controls.value.StateMap;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Skin;
import javafx.scene.layout.CornerRadii;

import static com.mcreater.fxfluent.controls.value.ControlMaps.CheckBox.*;

public class FluentCheckBox extends CheckBox implements CornerRadiusable, Backgroundable, Foregroundable, Borderable, Glyphable {
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
        return new CornerRadii(5);
    }

    public StateMap getBackgroundRemap() {

        return isSelected() || isIndeterminate() ? BG_PRESSED_KEY_MAP : BG_KEY_MAP;
    }

    public StateMap getBorderRemap() {
        return isSelected() || isIndeterminate() ? BRD_PRESSED_KEY_MAP : BRD_KEY_MAP;
    }

    public StateMap getForegroundRemap() {
        return FG_KEY_MAP;
    }

    public StateMap getGlyphRemap() {
        return GLY_KEY_MAP;
    }
}
