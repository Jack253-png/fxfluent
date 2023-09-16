package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentButtonSkin extends ButtonSkin {
    private final FluentButton button;
    private final ObjectProperty<StateType> state = new SimpleObjectProperty<>(null);
    private final AnimatedValue<AbstractColorBrush> backgroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> foregroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(42));

    private final AnimatedValue<AbstractColorBrush> upBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> downBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> leftBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> rightBorderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    public FluentButtonSkin(FluentButton control) {
        super(control);
        button = control;
        Stream.of(
                control.hoverProperty(),
                control.pressedProperty(),
                control.focusedProperty(),
                control.disabledProperty()
        ).forEach(a -> a.addListener(this::updateState));

        SystemThemeLoop.addListener(a -> this.updateComponents(state.get()));
        button.defaultButtonProperty().addListener((NewValueListener<Boolean>) t1 -> FluentButtonSkin.this.updateComponents(state.get()));

        CornerRadii cornerRadii = control.getCornerRadii();

        state.addListener((NewValueListener<StateType>) FluentButtonSkin.this::updateComponents);
        backgroundColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.backgroundFill(cornerRadii))
        );
        foregroundColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.textFill())
        );
        upBorderColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.TOP, cornerRadii))
        );
        downBorderColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.BOTTOM, cornerRadii))
        );
        leftBorderColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.LEFT, cornerRadii))
        );
        rightBorderColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
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
        backgroundColor.updateValue(button.getBackgroundRemap().get(type));
        foregroundColor.updateValue(button.getForegroundRemap().get(type));
        Stream.of(
                upBorderColor, leftBorderColor, rightBorderColor
        ).forEach(a -> a.updateValue(button.getBorderRemap().get(StateType.PRESS)));
        downBorderColor.updateValue(button.getBorderRemap().get(type));
    }
}