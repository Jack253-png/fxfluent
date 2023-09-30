package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.controls.FluentToggleButton;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.sun.javafx.scene.control.skin.ToggleButtonSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentToggleButtonSkin extends ToggleButtonSkin {
    private final FluentToggleButton button;
    private final ObjectProperty<StateType> state = new SimpleObjectProperty<>(null);
    private final AnimatedValue<AbstractColorBrush> backgroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> foregroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(42));

    private final AnimatedValue<AbstractColorBrush> upBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> downBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> leftBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> rightBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    public FluentToggleButtonSkin(FluentToggleButton control) {
        super(control);
        button = control;

        Stream.of(
                control.hoverProperty(),
                control.pressedProperty(),
                control.focusedProperty(),
                control.disabledProperty()
        ).forEach(a -> a.addListener(this::updateState));

        button.selectedProperty().addListener((NewValueListener<Boolean>) t1 -> FluentToggleButtonSkin.this.updateComponents(state.get()));

        CornerRadii cornerRadii = control.getCornerRadii();

        state.addListener((NewValueListener<StateType>) FluentToggleButtonSkin.this::updateComponents);
        backgroundColor.getProperty().addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.backgroundFill(cornerRadii))
        );
        foregroundColor.getProperty().addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.textFill())
        );
        upBorderColor.getProperty().addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.TOP, cornerRadii))
        );
        downBorderColor.getProperty().addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.BOTTOM, cornerRadii))
        );
        leftBorderColor.getProperty().addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.LEFT, cornerRadii))
        );
        rightBorderColor.getProperty().addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.RIGHT, cornerRadii))
        );

        updateState(null, null, null);
        updateComponents(state.get());
        button.setPadding(new Insets(5, 11, 5, 11));
    }

    private void updateState(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        state.set(genState(button.isDisabled(), button.isHover(), button.isPressed(), button.isFocused()));
    }

    private void updateComponents(StateType type) {
        backgroundColor.updateValue(button.getBackgroundRemap().get(type).apply(button.getResourceDict()));
        foregroundColor.updateValue(button.getForegroundRemap().get(type).apply(button.getResourceDict()));
        Stream.of(
                upBorderColor, leftBorderColor, rightBorderColor
        ).forEach(a -> a.updateValue(button.getBorderRemap().get(StateType.PRESS).apply(button.getResourceDict())));
        downBorderColor.updateValue(button.getBorderRemap().get(type).apply(button.getResourceDict()));
    }

    public void implUpdate() {
        this.updateComponents(state.get());
    }
}
