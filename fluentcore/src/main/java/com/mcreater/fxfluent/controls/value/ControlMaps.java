package com.mcreater.fxfluent.controls.value;

import com.mcreater.fxfluent.controls.state.StateType;

import java.util.HashMap;
import java.util.Map;

public class ControlMaps {
    public static class ToggleButton {
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

    public static class Button {
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
    public static class CheckBox {
        public static final Map<StateType, String> BG_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "CheckBoxCheckBackgroundFillUnchecked");
            put(StateType.FOCUS, "CheckBoxCheckBackgroundFillUnchecked");
            put(StateType.HOVER, "CheckBoxCheckBackgroundFillUncheckedPointerOver");
            put(StateType.PRESS, "CheckBoxCheckBackgroundFillUncheckedPressed");
            put(StateType.DISABLE, "CheckBoxCheckBackgroundFillUncheckedDisabled");
        }};
        public static final Map<StateType, String> BG_PRESSED_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "CheckBoxCheckBackgroundFillChecked");
            put(StateType.FOCUS, "CheckBoxCheckBackgroundFillChecked");
            put(StateType.HOVER, "CheckBoxCheckBackgroundFillCheckedPointerOver");
            put(StateType.PRESS, "CheckBoxCheckBackgroundFillCheckedPressed");
            put(StateType.DISABLE, "CheckBoxCheckBackgroundFillCheckedDisabled");
        }};
        public static final Map<StateType, String> BRD_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "CheckBoxCheckBackgroundStrokeUnchecked");
            put(StateType.FOCUS, "CheckBoxCheckBackgroundStrokeUnchecked");
            put(StateType.HOVER, "CheckBoxCheckBackgroundStrokeUncheckedPointerOver");
            put(StateType.PRESS, "CheckBoxCheckBackgroundStrokeUncheckedPressed");
            put(StateType.DISABLE, "CheckBoxCheckBackgroundStrokeUncheckedDisabled");
        }};
        public static final Map<StateType, String> BRD_PRESSED_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "CheckBoxCheckBackgroundStrokeChecked");
            put(StateType.FOCUS, "CheckBoxCheckBackgroundStrokeChecked");
            put(StateType.HOVER, "CheckBoxCheckBackgroundStrokeCheckedPointerOver");
            put(StateType.PRESS, "CheckBoxCheckBackgroundStrokeCheckedPressed");
            put(StateType.DISABLE, "CheckBoxCheckBackgroundStrokeCheckedDisabled");
        }};
        public static final Map<StateType, String> FG_KEY_MAP = new HashMap<StateType, String>() {{
            put(StateType.NONE, "CheckBoxForegroundUnchecked");
            put(StateType.FOCUS, "CheckBoxForegroundUnchecked");
            put(StateType.HOVER, "CheckBoxForegroundUncheckedPointerOver");
            put(StateType.PRESS, "CheckBoxForegroundUncheckedPressed");
            put(StateType.DISABLE, "CheckBoxForegroundUncheckedDisabled");
        }};
    }
}
