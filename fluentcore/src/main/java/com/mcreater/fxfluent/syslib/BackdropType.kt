package com.mcreater.fxfluent.syslib

import com.mcreater.fxfluent.util.SystemUtil

enum class BackdropType(val id: Int) {
    DISABLED(0),
    BLUR(1),
    ACRYLIC(2),
    MICA(3),
    MICA_ALT(4);

    companion object {
        val default: BackdropType
            get() = if (SystemUtil.isWindows()) {
                if (Win32UiShellLib.INSTANCE.implIsWindows11().booleanValue()) MICA else ACRYLIC
            } else BLUR
    }
}