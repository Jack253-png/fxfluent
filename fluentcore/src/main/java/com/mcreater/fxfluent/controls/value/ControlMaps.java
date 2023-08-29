package com.mcreater.fxfluent.controls.value;

import com.mcreater.fxfluent.controls.state.StateType;

import java.util.HashMap;
import java.util.Map;

public class ControlMaps {
    public static class Buttons {
        public static final Map<StateType, String> BG_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "ButtonBackground");
            put(StateType.FOCUS, "ButtonBackground");
            put(StateType.HOVER, "ButtonBackgroundPointerOver");
            put(StateType.PRESS, "ButtonBackgroundPressed");
            put(StateType.DISABLE, "ButtonBackgroundDisabled");
        }};
        public static final Map<StateType, String> BG_ACCENT_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "AccentButtonBackground");
            put(StateType.FOCUS, "AccentButtonBackground");
            put(StateType.HOVER, "AccentButtonBackgroundPointerOver");
            put(StateType.PRESS, "AccentButtonBackgroundPressed");
            put(StateType.DISABLE, "AccentButtonBackgroundDisabled");
        }};
        public static final Map<StateType, String> FG_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "ButtonForeground");
            put(StateType.FOCUS, "ButtonForeground");
            put(StateType.HOVER, "ButtonForegroundPointerOver");
            put(StateType.PRESS, "ButtonForegroundPressed");
            put(StateType.DISABLE, "ButtonForegroundDisabled");
        }};
        public static final Map<StateType, String> FG_ACCENT_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "AccentButtonForeground");
            put(StateType.FOCUS, "AccentButtonForeground");
            put(StateType.HOVER, "AccentButtonForegroundPointerOver");
            put(StateType.PRESS, "AccentButtonForegroundPressed");
            put(StateType.DISABLE, "AccentButtonForegroundDisabled");
        }};
        public static final Map<StateType, String> BRD_BOTTOM_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "ControlStrokeColorSecondaryBrush");
            put(StateType.FOCUS, "ControlStrokeColorSecondaryBrush");
            put(StateType.HOVER, "ControlStrokeColorSecondaryBrush");
            put(StateType.PRESS, "ControlStrokeColorDefaultBrush");
            put(StateType.DISABLE, "ControlStrokeColorDefaultBrush");
        }};
        public static final Map<StateType, String> BRD_BOTTOM_ACCENT_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "ControlStrokeColorOnAccentSecondary");
            put(StateType.FOCUS, "ControlStrokeColorOnAccentSecondary");
            put(StateType.HOVER, "ControlStrokeColorOnAccentSecondary");
            put(StateType.PRESS, "AccentButtonBorderBrushPressed");
            put(StateType.DISABLE, "AccentButtonBorderBrushDisabled");
        }};
    }
}
