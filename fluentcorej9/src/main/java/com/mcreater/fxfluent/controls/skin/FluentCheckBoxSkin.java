package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.controls.FluentCheckBox;
import com.mcreater.fxfluent.controls.internals.CheckBoxCheckMark;
import com.mcreater.fxfluent.controls.state.StateType;
import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.ControlUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.XAMLManager;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.skin.CheckBoxSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentCheckBoxSkin extends CheckBoxSkin {
    private final FluentCheckBox control;
    private final ObjectProperty<StateType> state = new SimpleObjectProperty<>(null);
    private final AnimatedValue<AbstractColorBrush> backgroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(42));
    private final AnimatedValue<AbstractColorBrush> borderColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(42));
    private final AnimatedValue<AbstractColorBrush> textColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(42));
    private final CheckBoxCheckMark mark;
    public FluentCheckBoxSkin(FluentCheckBox control) {
        super(control);
        this.control = control;
        Stream.of(
                this.control.hoverProperty(),
                this.control.pressedProperty(),
                this.control.focusedProperty(),
                this.control.disabledProperty()
        ).forEach(a -> a.addListener(this::updateState));
        state.addListener((NewValueListener<StateType>) FluentCheckBoxSkin.this::updateComponents);
        this.control.selectedProperty().addListener((NewValueListener<Boolean>) t1 -> FluentCheckBoxSkin.this.updateComponents(state.get()));
        this.control.indeterminateProperty().addListener((NewValueListener<Boolean>) t1 -> FluentCheckBoxSkin.this.updateComponents(state.get()));

        SystemThemeLoop.addListener(a -> this.updateComponents(state.get()));
        control.allowIndeterminateProperty().addListener((NewValueListener<Boolean>) t1 -> FluentCheckBoxSkin.this.updateComponents(state.get()));

        CornerRadii cornerRadii = control.getCornerRadii();

        backgroundColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(getInternalBox(), BrushUtil.backgroundFill(cornerRadii))
        );
        textColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.control, BrushUtil.textFill())
        );
        borderColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(getInternalBox(), BrushUtil.borderFill(
                                null,
                                cornerRadii
                ))
        );

        mark = new CheckBoxCheckMark(this);
        getInternalBox().getChildren().add(mark);
        control.selectedProperty().addListener((NewValueListener<Boolean>) t1 -> {
            if (t1) mark.startInAnimate();
            else mark.startOutAnimate();
        });

        updateState(null, null, null);
        updateComponents(state.get());
    }

    public StackPane getInternalBox() {
        return (StackPane) ControlUtil.findControlInSkin(this, "box");
    }

    private void updateState(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        state.set(genState(control.isDisabled(), control.isHover(), control.isPressed(), control.isFocused()));
    }

    private void updateComponents(StateType type) {
        backgroundColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(
                (this.control.getBackgroundRemap()).get(type))
        );
        borderColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(
                (this.control.getBorderRemap()).get(type))
        );
        textColor.updateValue(XAMLManager.getCurrentDict().foundSolidColorBrush(
                this.control.getForegroundRemap().get(type)
        ));
    }

    public Color getMarkColor() {
        return XAMLManager.getCurrentDict().foundSolidColorBrush(
                this.control.getGlyphRemap().get(state.get())
        ).getPaint();
    }
}