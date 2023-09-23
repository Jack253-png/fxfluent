package com.mcreater.fxfluent.controls;

import com.mcreater.fxfluent.controls.abstractions.Dictable;
import com.mcreater.fxfluent.controls.abstractions.Foregroundable;
import com.mcreater.fxfluent.controls.skin.FluentLabelSkin;
import com.mcreater.fxfluent.controls.value.StateMap;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class FluentLabel extends Label implements Foregroundable, Dictable {
    public FluentLabel() {
        this.init();
    }

    public FluentLabel(String var1) {
        super(var1);
        this.init();
    }

    public FluentLabel(String var1, Node var2) {
        super(var1, var2);
        this.init();
    }

    private void init() {
        getStyleClass().add("fluent-label");
    }

    protected FluentLabelSkin createDefaultSkin() {
        return new FluentLabelSkin(this);
    }

    public StateMap getForegroundRemap() {
        return null;
    }
}
