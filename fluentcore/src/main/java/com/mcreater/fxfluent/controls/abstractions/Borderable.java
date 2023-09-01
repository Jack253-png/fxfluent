package com.mcreater.fxfluent.controls.abstractions;

import com.mcreater.fxfluent.controls.state.StateType;

import java.util.Map;

public interface Borderable {
    Map<StateType, String> getBorderRemap();
}
