package com.mcreater.fxfluent.controls.state;

public class StateUtil {
    public static StateType genState(boolean disabled, boolean hovered, boolean pressed, boolean focused) {
        return disabled ? StateType.DISABLED : (pressed ? StateType.PRESSED : (hovered ? StateType.HOVER : (focused ? StateType.FOCUS : StateType.NONE)));
    }
}
