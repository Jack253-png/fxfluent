package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.controls.FluentProgressBar;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.ControlUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.animation.Timeline;
import javafx.scene.control.skin.ProgressBarSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;

public class FluentProgressBarSkin extends ProgressBarSkin {
    private final FluentProgressBar bar;
    private final StackPane internalBar = new StackPane();
    private final StackPane internalBarClip = new StackPane();
    private Timeline normalIndeterminateAnimation;
    public FluentProgressBarSkin(FluentProgressBar progressBar) {
        super(progressBar);
        bar = progressBar;
        SystemThemeLoop.addListener(a -> this.updateComponents());
        progressBar.progressProperty().addListener((NewValueListener<Number>) t1 -> updateComponents());
        progressBar.indeterminateState().addListener((NewValueListener<FluentProgressBar.IndeterminateState>) t1 -> updateComponents());
        progressBar.indeterminateProperty().addListener((NewValueListener<Boolean>) t1 -> updateComponents());
        internalBar.setTranslateX(-50);
        internalBar.setClip(internalBarClip);

        getChildren().add(internalBar);

        updateComponents();
        getBar().setOpacity(0);
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
        if (bar.isIndeterminate()) {
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
                internalBar,
                BrushUtil.backgroundFill(new CornerRadii(3))
        );

        if (normalIndeterminateAnimation != null) normalIndeterminateAnimation.stop();
        if (bar.isIndeterminate()) {

        }
    }

    protected void layoutChildren(final double x, final double y,
                                            final double w, final double h) {
        System.out.printf("%f, %f, %f, %f\n", x, y, w, h);
        super.layoutChildren(x, y, w, h);
        getTrack().resizeRelocate(x, y + 1, w, 1);
        internalBarClip.resizeRelocate(x, y, w, h);
        internalBar.resizeRelocate(x, y, getBar().getWidth(), 3);
    }
}