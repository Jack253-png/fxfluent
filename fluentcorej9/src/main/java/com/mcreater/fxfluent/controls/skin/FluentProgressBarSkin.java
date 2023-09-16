package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.controls.FluentProgressBar;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.ControlUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.scene.control.skin.ProgressBarSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;

public class FluentProgressBarSkin extends ProgressBarSkin {
    private FluentProgressBar bar;
    public FluentProgressBarSkin(FluentProgressBar progressBar) {
        super(progressBar);
        bar = progressBar;
        SystemThemeLoop.addListener(a -> this.updateComponents());
        progressBar.progressProperty().addListener((NewValueListener<Number>) t1 -> updateComponents());
        progressBar.indeterminateState().addListener((NewValueListener<FluentProgressBar.IndeterminateState>) t1 -> updateComponents());

        updateComponents();
        getBar().setPrefHeight(3);
        getTrack().setPrefHeight(1);
    }
    private StackPane getBar() {
        return (StackPane) ControlUtil.findControlInSkin(this, "bar");
    }
    private StackPane getTrack() {
        return (StackPane) ControlUtil.findControlInSkin(this, "track");
    }
    private void updateComponents() {
        StateType fg = StateType.NONE;
        if (bar.getProgress() < 0) {
            switch (bar.getIndeterminateState()) {
                case PAUSE:
                    fg = StateType.FOCUS;
                    break;
                case ERROR:
                    fg = StateType.HOVER;
                    break;
                default:
                case NORMAL:
                    break;
            }
        }
        bar.getBackgroundRemap().get(StateType.NONE).accept(
                getTrack(),
                BrushUtil.backgroundFill(CornerRadii.EMPTY)
        );
        bar.getForegroundRemap().get(fg).accept(
                getBar(),
                BrushUtil.backgroundFill(new CornerRadii(3))
        );
    }

    @Override
    protected void layoutChildren(final double x, final double y,
                                            final double w, final double h) {
        super.layoutChildren(x, y, w, h);
        getTrack().resizeRelocate(x, y + 1, w, 1);
    }
}