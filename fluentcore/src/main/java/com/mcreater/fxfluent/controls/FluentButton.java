package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.controls.abstractions.Backgroundable;
import com.mcreater.fxfluent.controls.abstractions.Borderable;
import com.mcreater.fxfluent.controls.abstractions.CornerRadiusable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentButtonSkin;
import com.mcreater.fxfluent.controls.state.StateType;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.layout.CornerRadii;

import java.util.Map;

import static com.mcreater.fxfluent.controls.value.ControlMaps.Button.*;

public class FluentButton extends Button implements CornerRadiusable, Backgroundable, Foregroundable, Borderable {
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

    public Map<StateType, AbstractColorBrush> getBackgroundRemap() {
        return isDefaultButton() ? BG_ACCENT_KEY_MAP : BG_KEY_MAP;
    }

    public Map<StateType, AbstractColorBrush> getForegroundRemap() {
        return isDefaultButton() ? FG_ACCENT_KEY_MAP : FG_KEY_MAP;
    }

    public Map<StateType, AbstractColorBrush> getBorderRemap() {
        return isDefaultButton() ? BRD_BOTTOM_ACCENT_KEY_MAP : BRD_BOTTOM_KEY_MAP;
    }
}
