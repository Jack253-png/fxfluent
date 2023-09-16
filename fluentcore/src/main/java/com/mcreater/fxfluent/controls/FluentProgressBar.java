package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.controls.abstractions.Backgroundable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentProgressBarSkin;
import com.mcreater.fxfluent.controls.state.StateType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Skin;

import java.util.HashMap;
import java.util.Map;

import static com.mcreater.fxfluent.brush.AbstractColorBrush.find;

public class FluentProgressBar extends ProgressBar implements Backgroundable, Foregroundable {
    public enum IndeterminateState {
        NORMAL, PAUSE, ERROR
    }
    private final ObjectProperty<IndeterminateState> indeterminateState = new SimpleObjectProperty<>(IndeterminateState.NORMAL);
    public ObjectProperty<IndeterminateState> indeterminateState() {
        return indeterminateState;
    }

    public IndeterminateState getIndeterminateState() {
        return indeterminateState().get();
    }
    public void setIndeterminateState(IndeterminateState state) {
        indeterminateState().set(state);
    }

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

    public Map<StateType, AbstractColorBrush> getBackgroundRemap() {
        return new HashMap<StateType, AbstractColorBrush>() {{
            put(StateType.NONE, find("SystemFillColorNeutralBrush"));
        }};
    }

    public Map<StateType, AbstractColorBrush> getForegroundRemap() {
        return new HashMap<StateType, AbstractColorBrush>() {{
            put(StateType.NONE, find("AccentFillColorDefaultBrush"));
            put(StateType.FOCUS, find("SystemFillColorCautionBrush"));
            put(StateType.HOVER, find("SystemFillColorCriticalBrush"));
        }};
    }
}
