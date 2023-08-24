package com.mcreater.fxfluent.controls.state;

public class StateUtil {
    public static StateType genState(boolean disabled, boolean hovered, boolean pressed, boolean focused) {
        return disabled ? StateType.DISABLE : (pressed ? StateType.PRESS : (hovered ? StateType.HOVER : (focused ? StateType.FOCUS : StateType.NONE)));
    }
}
