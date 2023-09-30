package com.mcreater.fxfluent.controls.value

import com.mcreater.fxfluent.controls.state.StateType
import com.mcreater.fxfluent.xaml.ResourceDict
import com.mcreater.fxfluent.xaml.XamlManager
import java.util.function.Function


class ControlMaps {
    class Button {
        companion object {
            val BG_KEY_MAP: StateMap = object : StateMap() {
                init {
                    put(StateType.NONE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonBackground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonBackground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonBackgroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonBackgroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "AccentButtonBackground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonBackground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonBackgroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonBackgroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "ButtonForeground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonForeground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonForegroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonForegroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "AccentButtonForeground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonForeground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonForegroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonForegroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "ButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonBorderBrushPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "ButtonBorderBrushPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "AccentButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonBorderBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonBorderBrushPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "AccentButtonBorderBrushPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillChecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillChecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillCheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundFillCheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeChecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeChecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeCheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckBackgroundStrokeCheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "CheckBoxForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxForegroundUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxForegroundUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "CheckBoxCheckGlyphForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckGlyphForegroundUnchecked",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckGlyphForegroundUncheckedPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "CheckBoxCheckGlyphForegroundUncheckedPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "HyperlinkButtonBackground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "HyperlinkButtonBackground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "HyperlinkButtonBackgroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "HyperlinkButtonBackgroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "HyperlinkButtonForeground",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "HyperlinkButtonForeground",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "HyperlinkButtonForegroundPointerOver",
                                d
                            )
                        })
                    put(StateType.PRESS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "HyperlinkButtonForegroundPressed",
                                d
                            )
                        })
                    put(StateType.DISABLE,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "SystemFillColorNeutralBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
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
                            XamlManager.find(
                                "AccentFillColorDefaultBrush",
                                d
                            )
                        })
                    put(StateType.FOCUS,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "SystemFillColorCautionBrush",
                                d
                            )
                        })
                    put(StateType.HOVER,
                        Function { d: ResourceDict? ->
                            XamlManager.find(
                                "SystemFillColorCriticalBrush",
                                d
                            )
                        })
                }
            }
        }
    }
}
