package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.XAMLManager;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Map;
import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentButtonSkin extends ButtonSkin {
    private final FluentButton button;
    private final ObjectProperty<StateType> state = new SimpleObjectProperty<>(null);
    private final AnimatedValue<Color> backgroundColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(83));
    private final AnimatedValue<Color> foregroundColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(42));

    private final AnimatedValue<Color> upBorderColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(83));
    private final AnimatedValue<Color> downBorderColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(83));
    private final AnimatedValue<Color> leftBorderColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(83));
    private final AnimatedValue<Color> rightBorderColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(83));
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
        backgroundColor.property.addListener((NewValueListener<Color>) newValue ->
                new SolidColorBrush(newValue)
                        .accept(this.button, BrushUtil.backgroundFill(cornerRadii))
        );
        foregroundColor.property.addListener((NewValueListener<Color>) newValue ->
                new SolidColorBrush(newValue)
                        .accept(this.button, BrushUtil.textFill())
        );
        upBorderColor.property.addListener((NewValueListener<Color>) newValue ->
                new SolidColorBrush(newValue)
                        .accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.TOP, cornerRadii, 1, Insets.EMPTY))
        );
        downBorderColor.property.addListener((NewValueListener<Color>) newValue ->
                new SolidColorBrush(newValue)
                        .accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.BOTTOM, cornerRadii, 1, Insets.EMPTY))
        );
        leftBorderColor.property.addListener((NewValueListener<Color>) newValue ->
                new SolidColorBrush(newValue)
                        .accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.LEFT, cornerRadii, 1, Insets.EMPTY))
        );
        rightBorderColor.property.addListener((NewValueListener<Color>) newValue ->
                new SolidColorBrush(newValue)
                        .accept(this.button, BrushUtil.borderFill(BrushUtil.BorderOrientation.RIGHT, cornerRadii, 1, Insets.EMPTY))
        );

        updateState(null, null, null);
        updateComponents(state.get());
        button.setPadding(new Insets(5, 11, 5, 11));
    }

    private void updateState(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        state.set(genState(button.isDisabled(), button.isHover(), button.isPressed(), button.isFocused()));
    }

    private void updateComponents(StateType type) {
        backgroundColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(
                (button.getBackgroundRemap()).get(type)).getColor()
        );
        foregroundColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush((button.getForegroundRemap()).get(type)).getColor());
        Map<StateType, String> lft = button.getBorderRemap();
        Stream.of(
                upBorderColor, leftBorderColor, rightBorderColor
        ).forEach(a -> a.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(lft.get(StateType.PRESS)).getColor()));
        downBorderColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(lft.get(type)).getColor());
    }
}
