package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.controls.FluentProgressBar;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.ControlUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.skin.ProgressBarSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
        progressBar.widthProperty().addListener((NewValueListener<Number>) t1 -> updateComponents());
        progressBar.layoutXProperty().addListener((NewValueListener<Number>) t1 -> updateComponents());
        internalBar.setTranslateX(-50);
        internalBarClip.setBackground(new Background(
                new BackgroundFill(
                        Color.BLACK,
                        new CornerRadii(5),
                        Insets.EMPTY
                )
        ));
        progressBar.setClip(internalBarClip);
        progressBar.clipProperty().bind(Bindings.createObjectBinding(() -> internalBarClip));
        getChildren().addAll(internalBar);

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
        bar.getBackgroundRemap().get(!bar.isIndeterminate() ? StateType.NONE : StateType.HOVER).get().accept(
                getTrack(),
                BrushUtil.backgroundFill(CornerRadii.EMPTY)
        );
        bar.getForegroundRemap().get(fg).get().accept(
                internalBar,
                BrushUtil.backgroundFill(new CornerRadii(3))
        );

        if (normalIndeterminateAnimation != null) {
            normalIndeterminateAnimation.stop();
            internalBar.setTranslateX(0);
            internalBar.setPrefWidth(-1);
            internalBar.setScaleX(1);
        }
        if (bar.isIndeterminate()) {
            if (bar.getIndeterminateState() == FluentProgressBar.IndeterminateState.NORMAL) {
                normalIndeterminateAnimation = new Timeline(
                        new KeyFrame(
                                Duration.ZERO,
                                new KeyValue(
                                        internalBar.translateXProperty(),
                                        -60,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        internalBar.scaleXProperty(),
                                        1,
                                        Interpolator.EASE_BOTH
                                )
                        ),
                        new KeyFrame(
                                Duration.millis(1000),
                                new KeyValue(
                                        internalBar.translateXProperty(),
                                        bar.getWidth(),
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        internalBar.scaleXProperty(),
                                        1,
                                        Interpolator.EASE_BOTH
                                )
                        ),
                        new KeyFrame(
                                Duration.millis(1000.0000000001),
                                new KeyValue(
                                        internalBar.translateXProperty(),
                                        -60,
                                        Interpolator.EASE_BOTH
                                ),
                                new KeyValue(
                                        internalBar.scaleXProperty(),
                                        1.5,
                                        Interpolator.EASE_BOTH
                                )
                        ),
                        new KeyFrame(
                                Duration.millis(2050),
                                new KeyValue(
                                        internalBar.translateXProperty(),
                                        bar.getWidth() + 60,
                                        Interpolator.EASE_BOTH
                                )
                        )
                        /*new KeyFrame(
                                Duration.millis(2150),
                                new KeyValue(
                                        internalBar.translateXProperty(),
                                        bar.getWidth(),
                                        Interpolator.EASE_BOTH
                                )
                        )*/
                );
                normalIndeterminateAnimation.setAutoReverse(false);
                normalIndeterminateAnimation.setCycleCount(Timeline.INDEFINITE);
                normalIndeterminateAnimation.playFromStart();
            }
            else {
                normalIndeterminateAnimation = new Timeline(
                        new KeyFrame(
                                Duration.ZERO,
                                new KeyValue(
                                        internalBar.prefWidthProperty(),
                                        60,
                                        Interpolator.EASE_BOTH
                                )
                        ),
                        new KeyFrame(
                                Duration.millis(25),
                                new KeyValue(
                                        internalBar.prefWidthProperty(),
                                        bar.getWidth(),
                                        Interpolator.EASE_BOTH
                                )
                        ),
                        new KeyFrame(
                                Duration.millis(75),
                                new KeyValue(
                                        internalBar.prefWidthProperty(),
                                        0,
                                        Interpolator.EASE_BOTH
                                )
                        ),
                        new KeyFrame(
                                Duration.millis(350),
                                new KeyValue(
                                        internalBar.prefWidthProperty(),
                                        bar.getWidth(),
                                        Interpolator.EASE_BOTH
                                )
                        )
                );
                internalBar.prefWidthProperty().addListener((observable, a, w) -> {
                    final double x = snappedLeftInset();
                    final double y = snappedTopInset();

                    layoutInternalBar(x, y);
                });
                normalIndeterminateAnimation.setAutoReverse(false);
                normalIndeterminateAnimation.setCycleCount(1);
                normalIndeterminateAnimation.playFromStart();
            }
        }
    }
    private void layoutInternalBar(double x, double y) {
        if (!bar.isIndeterminate() || bar.getIndeterminateState() == FluentProgressBar.IndeterminateState.NORMAL) internalBar.resizeRelocate(x, y, getBar().getWidth(), 3);
        else internalBar.resizeRelocate(x, y, internalBar.getPrefWidth(), 3);
    }
    protected void layoutChildren(final double x, final double y,
                                  final double w, final double h) {
        super.layoutChildren(x, y, w, h);
        getTrack().resizeRelocate(x, y + 1, w, 1);
        internalBarClip.resizeRelocate(0, 0, w, 3);
        layoutInternalBar(x, y);
    }
}