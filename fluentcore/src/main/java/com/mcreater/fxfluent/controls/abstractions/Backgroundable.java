package com.mcreater.fxfluent.controls.abstractions;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.controls.state.StateType;

import java.util.Map;

public interface Backgroundable {
    Map<StateType, AbstractColorBrush> getBackgroundRemap();
}
