package com.mcreater.fxfluent.controls.value;

import com.mcreater.fxfluent.controls.state.StateType;

import static com.mcreater.fxfluent.brush.AbstractColorBrush.find;

public class ControlMaps {
    public static class Button {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("ButtonBackground"));
            put(StateType.FOCUS, () -> find("ButtonBackground"));
            put(StateType.HOVER, () -> find("ButtonBackgroundPointerOver"));
            put(StateType.PRESS, () -> find("ButtonBackgroundPressed"));
            put(StateType.DISABLE, () -> find("ButtonBackgroundDisabled"));
        }};
        public static final StateMap BG_ACCENT_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("AccentButtonBackground"));
            put(StateType.FOCUS, () -> find("AccentButtonBackground"));
            put(StateType.HOVER, () -> find("AccentButtonBackgroundPointerOver"));
            put(StateType.PRESS, () -> find("AccentButtonBackgroundPressed"));
            put(StateType.DISABLE, () -> find("AccentButtonBackgroundDisabled"));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("ButtonForeground"));
            put(StateType.FOCUS, () -> find("ButtonForeground"));
            put(StateType.HOVER, () -> find("ButtonForegroundPointerOver"));
            put(StateType.PRESS, () -> find("ButtonForegroundPressed"));
            put(StateType.DISABLE, () -> find("ButtonForegroundDisabled"));
        }};
        public static final StateMap FG_ACCENT_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("AccentButtonForeground"));
            put(StateType.FOCUS, () -> find("AccentButtonForeground"));
            put(StateType.HOVER, () -> find("AccentButtonForegroundPointerOver"));
            put(StateType.PRESS, () -> find("AccentButtonForegroundPressed"));
            put(StateType.DISABLE, () -> find("AccentButtonForegroundDisabled"));
        }};
        public static final StateMap BRD_BOTTOM_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("ButtonBorderBrush"));
            put(StateType.FOCUS, () -> find("ButtonBorderBrush"));
            put(StateType.HOVER, () -> find("ButtonBorderBrushPointerOver"));
            put(StateType.PRESS, () -> find("ButtonBorderBrushPressed"));
            put(StateType.DISABLE, () -> find("ButtonBorderBrushDisabled"));
        }};
        public static final StateMap BRD_BOTTOM_ACCENT_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("AccentButtonBorderBrush"));
            put(StateType.FOCUS, () -> find("AccentButtonBorderBrush"));
            put(StateType.HOVER, () -> find("AccentButtonBorderBrushPointerOver"));
            put(StateType.PRESS, () -> find("AccentButtonBorderBrushPressed"));
            put(StateType.DISABLE, () -> find("AccentButtonBorderBrushDisabled"));
        }};
    }
    public static class CheckBox {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("CheckBoxCheckBackgroundFillUnchecked"));
            put(StateType.FOCUS, () -> find("CheckBoxCheckBackgroundFillUnchecked"));
            put(StateType.HOVER, () -> find("CheckBoxCheckBackgroundFillUncheckedPointerOver"));
            put(StateType.PRESS, () -> find("CheckBoxCheckBackgroundFillUncheckedPressed"));
            put(StateType.DISABLE, () -> find("CheckBoxCheckBackgroundFillUncheckedDisabled"));
        }};
        public static final StateMap BG_PRESSED_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("CheckBoxCheckBackgroundFillChecked"));
            put(StateType.FOCUS, () -> find("CheckBoxCheckBackgroundFillChecked"));
            put(StateType.HOVER, () -> find("CheckBoxCheckBackgroundFillCheckedPointerOver"));
            put(StateType.PRESS, () -> find("CheckBoxCheckBackgroundFillCheckedPressed"));
            put(StateType.DISABLE, () -> find("CheckBoxCheckBackgroundFillCheckedDisabled"));
        }};
        public static final StateMap BRD_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("CheckBoxCheckBackgroundStrokeUnchecked"));
            put(StateType.FOCUS, () -> find("CheckBoxCheckBackgroundStrokeUnchecked"));
            put(StateType.HOVER, () -> find("CheckBoxCheckBackgroundStrokeUncheckedPointerOver"));
            put(StateType.PRESS, () -> find("CheckBoxCheckBackgroundStrokeUncheckedPressed"));
            put(StateType.DISABLE, () -> find("CheckBoxCheckBackgroundStrokeUncheckedDisabled"));
        }};
        public static final StateMap BRD_PRESSED_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("CheckBoxCheckBackgroundStrokeChecked"));
            put(StateType.FOCUS, () -> find("CheckBoxCheckBackgroundStrokeChecked"));
            put(StateType.HOVER, () -> find("CheckBoxCheckBackgroundStrokeCheckedPointerOver"));
            put(StateType.PRESS, () -> find("CheckBoxCheckBackgroundStrokeCheckedPressed"));
            put(StateType.DISABLE, () -> find("CheckBoxCheckBackgroundStrokeCheckedDisabled"));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("CheckBoxForegroundUnchecked"));
            put(StateType.FOCUS, () -> find("CheckBoxForegroundUnchecked"));
            put(StateType.HOVER, () -> find("CheckBoxForegroundUncheckedPointerOver"));
            put(StateType.PRESS, () -> find("CheckBoxForegroundUncheckedPressed"));
            put(StateType.DISABLE, () -> find("CheckBoxForegroundUncheckedDisabled"));
        }};
        public static final StateMap GLY_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("CheckBoxCheckGlyphForegroundUnchecked"));
            put(StateType.FOCUS, () -> find("CheckBoxCheckGlyphForegroundUnchecked"));
            put(StateType.HOVER, () -> find("CheckBoxCheckGlyphForegroundUncheckedPointerOver"));
            put(StateType.PRESS, () -> find("CheckBoxCheckGlyphForegroundUncheckedPressed"));
            put(StateType.DISABLE, () -> find("CheckBoxCheckGlyphForegroundUncheckedDisabled"));
        }};
    }
    public static class HyperLinkButton {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("HyperlinkButtonBackground"));
            put(StateType.FOCUS, () -> find("HyperlinkButtonBackground"));
            put(StateType.HOVER, () -> find("HyperlinkButtonBackgroundPointerOver"));
            put(StateType.PRESS, () -> find("HyperlinkButtonBackgroundPressed"));
            put(StateType.DISABLE, () -> find("HyperlinkButtonBackgroundDisabled"));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("HyperlinkButtonForeground"));
            put(StateType.FOCUS, () -> find("HyperlinkButtonForeground"));
            put(StateType.HOVER, () -> find("HyperlinkButtonForegroundPointerOver"));
            put(StateType.PRESS, () -> find("HyperlinkButtonForegroundPressed"));
            put(StateType.DISABLE, () -> find("HyperlinkButtonForegroundDisabled"));
        }};
    }

    public static class ProgressBar {
        public static final StateMap BG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("SystemFillColorNeutralBrush"));
            put(StateType.HOVER, () -> find("ControlFillColorTransparentBrush"));
        }};
        public static final StateMap FG_KEY_MAP = new StateMap() {{
            put(StateType.NONE, () -> find("AccentFillColorDefaultBrush"));
            put(StateType.FOCUS, () -> find("SystemFillColorCautionBrush"));
            put(StateType.HOVER, () -> find("SystemFillColorCriticalBrush"));
        }};
    }
}
