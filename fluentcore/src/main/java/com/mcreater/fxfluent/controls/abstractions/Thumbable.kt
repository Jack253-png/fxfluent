package com.mcreater.fxfluent.controls.abstractions

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.controls.value.StateMap
import com.mcreater.fxfluent.xaml.ResourceDict
import java.util.function.Function

interface Thumbable {
    val thumbRemap: StateMap?
    val thumbBorder: Function<ResourceDict?, AbstractColorBrush?>?
    val thumbOuter: Function<ResourceDict?, AbstractColorBrush?>?
}