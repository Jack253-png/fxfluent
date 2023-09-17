package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.Backgroundable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentProgressBarSkin;
import com.mcreater.fxfluent.controls.value.StateMap;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Skin;

import static com.mcreater.fxfluent.controls.value.ControlMaps.ProgressBar.BG_KEY_MAP;
import static com.mcreater.fxfluent.controls.value.ControlMaps.ProgressBar.FG_KEY_MAP;

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

    public StateMap getBackgroundRemap() {
        return BG_KEY_MAP;
    }

    public StateMap getForegroundRemap() {
        return FG_KEY_MAP;
    }
}