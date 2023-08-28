package com.mcreater.fxfluent.stage.controls;

import com.mcreater.fxfluent.controls.FluentButton;
import com.mcreater.fxfluent.controls.icon.FluentIcon;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.stream.Stream;

import static com.mcreater.fxfluent.controls.state.StateUtil.genState;

public class FluentCloseButton extends Button {
    private FluentIcon icon;
    public FluentCloseButton() {
        this.init();
    }

    private FluentCloseButton(String var1) {
        super(var1);
        this.init();
    }

    public FluentCloseButton(String var1, Node var2) {
        super(var1, var2);
        this.init();
    }
    private void init() {
        getStyleClass().add("fluent-close-button");
        setPrefSize(48, 34);
        setMaxSize(48, 34);
        setMinSize(48, 34);
        icon = new FluentIcon('\uE8BB');
        setGraphic(icon);
        icon.getStyleClass().add("fluent-icon");
        icon.setTextFill(SystemThemeLoop.isDark() ? Color.WHITE : Color.BLACK);

        Stream.of(
                hoverProperty(),
                pressedProperty(),
                focusedProperty(),
                disabledProperty()
        ).forEach(a -> a.addListener((NewValueListener<Boolean>) t1 -> {
            switch (genState(isDisabled(), isHover(), isPressed(), isFocused())) {
                case FOCUS:
                case NONE:
                    icon.setTextFill(SystemThemeLoop.isDark() ? Color.WHITE : Color.BLACK);
                    break;
                case HOVER:
                    icon.setTextFill(Color.WHITE);
                    break;
                case PRESS:
                case DISABLE:
                    icon.setTextFill(new Color(
                            1,
                            1,
                            1,
                            0.7
                    ));
                    break;
            }
        }));
    }
    public String getUserAgentStylesheet() {
        return FluentButton.class.getClassLoader().getResource(SystemThemeLoop.isDark() ? "css/FluentCloseButtonDark.css" : "css/FluentCloseButtonLight.css").toString();
    }
}
