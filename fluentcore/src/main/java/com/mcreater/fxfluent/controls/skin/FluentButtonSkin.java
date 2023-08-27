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

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentButtonSkin extends ButtonSkin {
    private static final Map<StateType, String> BG_KEY_MAP = new HashMap<StateType, String>() {{
        put(StateType.NONE, "ButtonBackground");
        put(StateType.FOCUS, "ButtonBackground");
        put(StateType.HOVER, "ButtonBackgroundPointerOver");
        put(StateType.PRESS, "ButtonBackgroundPressed");
        put(StateType.DISABLE, "ButtonBackgroundDisabled");
    }};
    private static final Map<StateType, String> BG_ACCENT_KEY_MAP = new HashMap<StateType, String>() {{
        put(StateType.NONE, "AccentButtonBackground");
        put(StateType.FOCUS, "AccentButtonBackground");
        put(StateType.HOVER, "AccentButtonBackgroundPointerOver");
        put(StateType.PRESS, "AccentButtonBackgroundPressed");
        put(StateType.DISABLE, "AccentButtonBackgroundDisabled");
    }};
    private static final Map<StateType, String> FG_KEY_MAP = new HashMap<StateType, String>() {{
        put(StateType.NONE, "ButtonForeground");
        put(StateType.FOCUS, "ButtonForeground");
        put(StateType.HOVER, "ButtonForegroundPointerOver");
        put(StateType.PRESS, "ButtonForegroundPressed");
        put(StateType.DISABLE, "ButtonForegroundDisabled");
    }};
    private static final Map<StateType, String> FG_ACCENT_KEY_MAP = new HashMap<StateType, String>() {{
        put(StateType.NONE, "AccentButtonForeground");
        put(StateType.FOCUS, "AccentButtonForeground");
        put(StateType.HOVER, "AccentButtonForegroundPointerOver");
        put(StateType.PRESS, "AccentButtonForegroundPressed");
        put(StateType.DISABLE, "AccentButtonForegroundDisabled");
    }};
    private static final Map<StateType, String> BRD_BOTTOM_KEY_MAP = new HashMap<StateType, String>() {{
        put(StateType.NONE, "ControlStrokeColorSecondaryBrush");
        put(StateType.FOCUS, "ControlStrokeColorSecondaryBrush");
        put(StateType.HOVER, "ControlStrokeColorSecondaryBrush");
        put(StateType.PRESS, "ControlStrokeColorDefaultBrush");
        put(StateType.DISABLE, "ControlStrokeColorDefaultBrush");
    }};
    private static final Map<StateType, String> BRD_BOTTOM_ACCENT_KEY_MAP = new HashMap<StateType, String>() {{
        put(StateType.NONE, "ControlStrokeColorOnAccentSecondary");
        put(StateType.FOCUS, "ControlStrokeColorOnAccentSecondary");
        put(StateType.HOVER, "ControlStrokeColorOnAccentSecondary");
        put(StateType.PRESS, "AccentButtonBorderBrushPressed");
        put(StateType.DISABLE, "AccentButtonBorderBrushDisabled");
    }};
    private final FluentButton button;
    private final ObjectProperty<StateType> state = new SimpleObjectProperty<>(null);
    private final AnimatedValue<Color> backgroundColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(83));
    private final AnimatedValue<Color> foregroundColor = new AnimatedValue<>(Color.TRANSPARENT, Duration.millis(83));

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

        CornerRadii cornerRadii = new CornerRadii(4);

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
                (button.isDefaultButton() ? BG_ACCENT_KEY_MAP : BG_KEY_MAP).get(type)).getColor()
        );
        foregroundColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush((button.isDefaultButton() ? FG_ACCENT_KEY_MAP : FG_KEY_MAP).get(type)).getColor());
        Map<StateType, String> lft = button.isDefaultButton() ? BRD_BOTTOM_ACCENT_KEY_MAP : BRD_BOTTOM_KEY_MAP;
        Stream.of(
                upBorderColor, leftBorderColor, rightBorderColor
        ).forEach(a -> a.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(lft.get(StateType.PRESS)).getColor()));
        downBorderColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(lft.get(type)).getColor());
    }
}
