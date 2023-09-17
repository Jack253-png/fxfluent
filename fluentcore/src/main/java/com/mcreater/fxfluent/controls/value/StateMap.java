package com.mcreater.fxfluent.controls.value;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.controls.state.StateType;

import java.util.HashMap;
import java.util.function.Supplier;

public class StateMap extends HashMap<StateType, Supplier<AbstractColorBrush>> {}