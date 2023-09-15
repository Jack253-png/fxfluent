package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.controls.FluentHyperlinkButton;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.XAMLManager;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.skin.HyperlinkSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentHyperlinkButtonSkin extends HyperlinkSkin {
    private final FluentHyperlinkButton button;
    private final ObjectProperty<StateType> state = new SimpleObjectProperty<>(null);
    private final AnimatedValue<AbstractColorBrush> backgroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(83));
    private final AnimatedValue<AbstractColorBrush> foregroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(42));
    public FluentHyperlinkButtonSkin(FluentHyperlinkButton control) {
        super(control);
        button = control;
        Stream.of(
                control.hoverProperty(),
                control.pressedProperty(),
                control.focusedProperty(),
                control.disabledProperty()
        ).forEach(a -> a.addListener(this::updateState));

        SystemThemeLoop.addListener(a -> this.updateComponents(state.get()));
        state.addListener((NewValueListener<StateType>) FluentHyperlinkButtonSkin.this::updateComponents);
        CornerRadii cornerRadii = control.getCornerRadii();
        backgroundColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.backgroundFill(cornerRadii))
        );
        foregroundColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.button, BrushUtil.textFill())
        );

        updateState(null, null, null);
        updateComponents(state.get());
        button.setPadding(new Insets(2, 11, 2, 11));
    }

    private void updateState(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        state.set(genState(button.isDisabled(), button.isHover(), button.isPressed(), button.isFocused()));
    }

    private void updateComponents(StateType type) {
        backgroundColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(
                (button.getBackgroundRemap()).get(type))
        );
        foregroundColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush((button.getForegroundRemap()).get(type)));
    }
}