package com.mcreater.fxfluent.controls.skin;

import com.mcreater.fxfluent.brush.AbstractColorBrush;
import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.controls.FluentLabel;
import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.util.BrushUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.scene.control.skin.LabelSkin;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FluentLabelSkin extends LabelSkin {
    private final AnimatedValue<AbstractColorBrush> foregroundColor = new AnimatedValue<>(new SolidColorBrush(Color.TRANSPARENT), Duration.millis(42));
    private final FluentLabel label;
    public FluentLabelSkin(FluentLabel label) {
        super(label);
        this.label = label;

        SystemThemeLoop.addListener(a -> this.updateComponents());
        foregroundColor.property.addListener((NewValueListener<AbstractColorBrush>) newValue ->
                newValue.accept(this.label, BrushUtil.textFill())
        );
    }

    private void updateComponents() {

    }
}
