package com.mcreater.fxfluent.controls.abstractions;

import com.mcreater.fxfluent.xaml.ResourceDict;
import com.mcreater.fxfluent.xaml.XAMLManager;

public interface Dictable {
    default ResourceDict getResourceDict() {
        return XAMLManager.getCurrentDict();
    }
}
