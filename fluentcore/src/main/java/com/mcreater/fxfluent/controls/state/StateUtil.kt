package com.mcreater.fxfluent.controls.state

class StateUtil {
    companion object {
        @JvmStatic
        fun genState(disabled: Boolean, hovered: Boolean, pressed: Boolean, focused: Boolean): StateType {
            return if (disabled) StateType.DISABLE else if (pressed) StateType.PRESS else if (hovered) StateType.HOVER else if (focused) StateType.FOCUS else StateType.NONE
        }
    }
}