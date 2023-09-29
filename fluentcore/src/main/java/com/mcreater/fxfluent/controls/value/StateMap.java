package com.mcreater.fxfluent.controls.value;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.xaml.ResourceDict;

import java.util.HashMap;
import java.util.function.Function;

public class StateMap extends HashMap<StateType, Function<ResourceDict, AbstractColorBrush>> {}