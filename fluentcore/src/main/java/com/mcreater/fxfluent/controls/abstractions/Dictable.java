package com.mcreater.fxfluent.controls.abstractions;

import com.mcreater.fxfluent.xaml.ResourceDict;
import com.mcreater.fxfluent.xaml.XamlManager;

public interface Dictable {
    default ResourceDict getResourceDict() {
        return XamlManager.getCurrentDict();
    }
}
