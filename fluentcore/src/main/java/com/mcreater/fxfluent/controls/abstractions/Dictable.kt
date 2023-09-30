package com.mcreater.fxfluent.controls.abstractions

import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager.Companion.getCurrentDict


interface Dictable {
    val resourceDict: ResourceDict?
        get() = getCurrentDict()
}

