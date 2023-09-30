package com.mcreater.fxfluent.controls.value

import com.mcreater.fxfluent.brush.AbstractColorBrush
import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.xaml.ResourceDict
import java.util.function.Function


class ControlMaps {
    class Button {
        companion object {
            val BG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBackground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBackground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBackgroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBackgroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBackgroundDisabled",
                                d
                            )
                        })
                }
            }
            val BG_ACCENT_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBackground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBackground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBackgroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBackgroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBackgroundDisabled",
                                d
                            )
                        })
                }
            }
            val FG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonForeground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonForeground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonForegroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonForegroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonForegroundDisabled",
                                d
                            )
                        })
                }
            }
            val FG_ACCENT_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonForeground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonForeground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonForegroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonForegroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonForegroundDisabled",
                                d
                            )
                        })
                }
            }
            val BRD_BOTTOM_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBorderBrushPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBorderBrushPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ButtonBorderBrushDisabled",
                                d
                            )
                        })
                }
            }
            val BRD_BOTTOM_ACCENT_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBorderBrushPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBorderBrushPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentButtonBorderBrushDisabled",
                                d
                            )
                        })
                }
            }
        }
    }

    class CheckBox {
        companion object {
            val BG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillUncheckedDisabled",
                                d
                            )
                        })
                }
            }
            val BG_PRESSED_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillChecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillChecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillCheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillCheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundFillCheckedDisabled",
                                d
                            )
                        })
                }
            }
            val BRD_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeUncheckedDisabled",
                                d
                            )
                        })
                }
            }
            val BRD_PRESSED_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeChecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeChecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeCheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeCheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckBackgroundStrokeCheckedDisabled",
                                d
                            )
                        })
                }
            }
            val FG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxForegroundUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxForegroundUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxForegroundUncheckedDisabled",
                                d
                            )
                        })
                }
            }
            val GLY_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckGlyphForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckGlyphForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckGlyphForegroundUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckGlyphForegroundUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "CheckBoxCheckGlyphForegroundUncheckedDisabled",
                                d
                            )
                        })
                }
            }
        }
    }

    class HyperLinkButton {
        companion object {
            val BG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonBackground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonBackground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonBackgroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonBackgroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonBackgroundDisabled",
                                d
                            )
                        })
                }
            }
            val FG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonForeground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonForeground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonForegroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonForegroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "HyperlinkButtonForegroundDisabled",
                                d
                            )
                        })
                }
            }
        }
    }

    class ProgressBar {
        companion object {
            val BG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "SystemFillColorNeutralBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "ControlFillColorTransparentBrush",
                                d
                            )
                        })
                }
            }
            val FG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "AccentFillColorDefaultBrush",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "SystemFillColorCautionBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            AbstractColorBrush.find(
                                "SystemFillColorCriticalBrush",
                                d
                            )
                        })
                }
            }
        }
    }
}
