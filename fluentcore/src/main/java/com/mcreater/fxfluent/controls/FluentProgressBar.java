package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.Backgroundable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentProgressBarSkin;
import com.mcreater.fxfluent.controls.state.StateType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Skin;

import java.util.HashMap;
import java.util.Map;

public class FluentProgressBar extends ProgressBar implements Backgroundable, Foregroundable {
    public FluentProgressBar() {
        init();
    }

    public FluentProgressBar(double progress) {
        super(progress);
        init();
    }

    private void init() {
        getStyleClass().add("fluent-progress-bar");
    }

    public String getUserAgentStylesheet() {
        return FluentProgressBar.class.getClassLoader().getResource("css/FluentProgressBar.css").toString();
    }

    protected Skin<?> createDefaultSkin() {
        return new FluentProgressBarSkin(this);
    }

    public Map<StateType, String> getBackgroundRemap() {
        return new HashMap<StateType, String>() {{
            put(StateType.NONE, "SystemFillColorNeutralBrush");
        }};
    }

    public Map<StateType, String> getForegroundRemap() {
        return new HashMap<StateType, String>() {{
            put(StateType.NONE, "AccentFillColorDefaultBrush");
        }};
    }
}
