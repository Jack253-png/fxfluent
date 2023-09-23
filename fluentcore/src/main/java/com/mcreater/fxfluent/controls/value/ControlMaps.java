package com.mcreater.fxfluent.controls.value;

import com.mcreater.fxfluent.controls.state.StateType;

import static com.mcreater.fxfluent.brush.AbstractColorBrush.find;

public class ControlMaps {
    public static class Button {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("ButtonBackground", d));
            put(StateType.FOCUS, d -> find("ButtonBackground", d));
            put(StateType.HOVER, d -> find("ButtonBackgroundPointerOver", d));
            put(StateType.PRESS, d -> find("ButtonBackgroundPressed", d));
            put(StateType.DISABLE, d -> find("ButtonBackgroundDisabled", d));
        }};
        public static final StateMap BG_ACCENT_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("AccentButtonBackground", d));
            put(StateType.FOCUS, d -> find("AccentButtonBackground", d));
            put(StateType.HOVER, d -> find("AccentButtonBackgroundPointerOver", d));
            put(StateType.PRESS, d -> find("AccentButtonBackgroundPressed", d));
            put(StateType.DISABLE, d -> find("AccentButtonBackgroundDisabled", d));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("ButtonForeground", d));
            put(StateType.FOCUS, d -> find("ButtonForeground", d));
            put(StateType.HOVER, d -> find("ButtonForegroundPointerOver", d));
            put(StateType.PRESS, d -> find("ButtonForegroundPressed", d));
            put(StateType.DISABLE, d -> find("ButtonForegroundDisabled", d));
        }};
        public static final StateMap FG_ACCENT_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("AccentButtonForeground", d));
            put(StateType.FOCUS, d -> find("AccentButtonForeground", d));
            put(StateType.HOVER, d -> find("AccentButtonForegroundPointerOver", d));
            put(StateType.PRESS, d -> find("AccentButtonForegroundPressed", d));
            put(StateType.DISABLE, d -> find("AccentButtonForegroundDisabled", d));
        }};
        public static final StateMap BRD_BOTTOM_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("ButtonBorderBrush", d));
            put(StateType.FOCUS, d -> find("ButtonBorderBrush", d));
            put(StateType.HOVER, d -> find("ButtonBorderBrushPointerOver", d));
            put(StateType.PRESS, d -> find("ButtonBorderBrushPressed", d));
            put(StateType.DISABLE, d -> find("ButtonBorderBrushDisabled", d));
        }};
        public static final StateMap BRD_BOTTOM_ACCENT_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("AccentButtonBorderBrush", d));
            put(StateType.FOCUS, d -> find("AccentButtonBorderBrush", d));
            put(StateType.HOVER, d -> find("AccentButtonBorderBrushPointerOver", d));
            put(StateType.PRESS, d -> find("AccentButtonBorderBrushPressed", d));
            put(StateType.DISABLE, d -> find("AccentButtonBorderBrushDisabled", d));
        }};
    }
    public static class CheckBox {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("CheckBoxCheckBackgroundFillUnchecked", d));
            put(StateType.FOCUS, d -> find("CheckBoxCheckBackgroundFillUnchecked", d));
            put(StateType.HOVER, d -> find("CheckBoxCheckBackgroundFillUncheckedPointerOver", d));
            put(StateType.PRESS, d -> find("CheckBoxCheckBackgroundFillUncheckedPressed", d));
            put(StateType.DISABLE, d -> find("CheckBoxCheckBackgroundFillUncheckedDisabled", d));
        }};
        public static final StateMap BG_PRESSED_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("CheckBoxCheckBackgroundFillChecked", d));
            put(StateType.FOCUS, d -> find("CheckBoxCheckBackgroundFillChecked", d));
            put(StateType.HOVER, d -> find("CheckBoxCheckBackgroundFillCheckedPointerOver", d));
            put(StateType.PRESS, d -> find("CheckBoxCheckBackgroundFillCheckedPressed", d));
            put(StateType.DISABLE, d -> find("CheckBoxCheckBackgroundFillCheckedDisabled", d));
        }};
        public static final StateMap BRD_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("CheckBoxCheckBackgroundStrokeUnchecked", d));
            put(StateType.FOCUS, d -> find("CheckBoxCheckBackgroundStrokeUnchecked", d));
            put(StateType.HOVER, d -> find("CheckBoxCheckBackgroundStrokeUncheckedPointerOver", d));
            put(StateType.PRESS, d -> find("CheckBoxCheckBackgroundStrokeUncheckedPressed", d));
            put(StateType.DISABLE, d -> find("CheckBoxCheckBackgroundStrokeUncheckedDisabled", d));
        }};
        public static final StateMap BRD_PRESSED_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("CheckBoxCheckBackgroundStrokeChecked", d));
            put(StateType.FOCUS, d -> find("CheckBoxCheckBackgroundStrokeChecked", d));
            put(StateType.HOVER, d -> find("CheckBoxCheckBackgroundStrokeCheckedPointerOver", d));
            put(StateType.PRESS, d -> find("CheckBoxCheckBackgroundStrokeCheckedPressed", d));
            put(StateType.DISABLE, d -> find("CheckBoxCheckBackgroundStrokeCheckedDisabled", d));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("CheckBoxForegroundUnchecked", d));
            put(StateType.FOCUS, d -> find("CheckBoxForegroundUnchecked", d));
            put(StateType.HOVER, d -> find("CheckBoxForegroundUncheckedPointerOver", d));
            put(StateType.PRESS, d -> find("CheckBoxForegroundUncheckedPressed", d));
            put(StateType.DISABLE, d -> find("CheckBoxForegroundUncheckedDisabled", d));
        }};
        public static final StateMap GLY_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("CheckBoxCheckGlyphForegroundUnchecked", d));
            put(StateType.FOCUS, d -> find("CheckBoxCheckGlyphForegroundUnchecked", d));
            put(StateType.HOVER, d -> find("CheckBoxCheckGlyphForegroundUncheckedPointerOver", d));
            put(StateType.PRESS, d -> find("CheckBoxCheckGlyphForegroundUncheckedPressed", d));
            put(StateType.DISABLE, d -> find("CheckBoxCheckGlyphForegroundUncheckedDisabled", d));
        }};
    }
    public static class HyperLinkButton {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("HyperlinkButtonBackground", d));
            put(StateType.FOCUS, d -> find("HyperlinkButtonBackground", d));
            put(StateType.HOVER, d -> find("HyperlinkButtonBackgroundPointerOver", d));
            put(StateType.PRESS, d -> find("HyperlinkButtonBackgroundPressed", d));
            put(StateType.DISABLE, d -> find("HyperlinkButtonBackgroundDisabled", d));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("HyperlinkButtonForeground", d));
            put(StateType.FOCUS, d -> find("HyperlinkButtonForeground", d));
            put(StateType.HOVER, d -> find("HyperlinkButtonForegroundPointerOver", d));
            put(StateType.PRESS, d -> find("HyperlinkButtonForegroundPressed", d));
            put(StateType.DISABLE, d -> find("HyperlinkButtonForegroundDisabled", d));
        }};
    }

    public static class ProgressBar {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("SystemFillColorNeutralBrush", d));
            put(StateType.HOVER, d -> find("ControlFillColorTransparentBrush", d));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, d -> find("AccentFillColorDefaultBrush", d));
            put(StateType.FOCUS, d -> find("SystemFillColorCautionBrush", d));
            put(StateType.HOVER, d -> find("SystemFillColorCriticalBrush", d));
        }};
    }
}
