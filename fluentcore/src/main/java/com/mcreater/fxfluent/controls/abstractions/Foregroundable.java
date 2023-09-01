package com.mcreater.fxfluent.controls.abstractions;

import com.mcreater.fxfluent.controls.state.StateType;

import java.util.Map;

public interface Foregroundable {
    Map<StateType, String> getForegroundRemap();
}
