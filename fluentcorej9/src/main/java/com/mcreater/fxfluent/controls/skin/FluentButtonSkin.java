package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.xaml.XAMLManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentButtonSkin extends ButtonSkin {
    private final FluentButton button;
    private final ObjectProperty<StateType> state = new SimpleObjectProperty<>(null);
    private final AnimatedValue<Color> backgroundColor = new AnimatedValue<>(Color.WHITE, Duration.millis(167));
    public FluentButtonSkin(FluentButton control) {
        super(control);
        button = control;
        Stream.of(
                control.hoverProperty(),
                control.pressedProperty(),
                control.focusedProperty(),
                control.disabledProperty()
        ).forEach(a -> a.addListener(this::updateState));


        state.addListener((observable, oldValue, newValue) -> this.updateBackground());
        backgroundColor.property.addListener((observable, oldValue, newValue) -> new SolidColorBrush(newValue).accept(this.button, new CornerRadii(10)));

        updateState(null, null, null);
    }

    private void updateState(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        state.set(genState(button.isDisabled(), button.isHover(), button.isPressed(), button.isFocused()));
    }

    private void updateBackground() {
        String key = "";
        switch (state.get()) {
            case NONE:
            case FOCUS:
                key = "ButtonBackground";
                break;
            case HOVER:
                key = "ButtonBackgroundPointerOver";
                break;
            case PRESSED:
                key = "ButtonBackgroundPressed";
                break;
            case DISABLED:
                key = "ButtonBackgroundDisabled";
                break;
        }
        backgroundColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(key).getColor());
    }
}
