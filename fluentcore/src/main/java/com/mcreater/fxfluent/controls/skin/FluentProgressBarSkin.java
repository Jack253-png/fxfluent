package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.controls.FluentProgressBar;
import com.mcreater.fxfluent.util.ControlUtil;
import com.sun.javafx.scene.control.skin.ProgressBarSkin;
import javafx.scene.layout.StackPane;

public class FluentProgressBarSkin extends ProgressBarSkin {
    public FluentProgressBarSkin(FluentProgressBar progressBar) {
        super(progressBar);

        updateComponents();
    }
    private StackPane getBar() {
        return (StackPane) ControlUtil.findControlInSkin(this, "bar");
    }
    private StackPane getTrack() {
        return (StackPane) ControlUtil.findControlInSkin(this, "track");
    }
    private void updateComponents() {

    }
}
