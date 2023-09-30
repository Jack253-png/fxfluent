package com.mcreater.fxfluent.controls.value

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.xaml.ResourceDict
import java.util.function.Function


open class StateMap : HashMap<StateType?, Function<ResourceDict?, AbstractColorBrush?>?>()