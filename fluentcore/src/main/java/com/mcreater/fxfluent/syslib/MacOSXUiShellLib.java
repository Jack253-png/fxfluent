package com.mcreater.fxfluent.syslib;

import ca.weblite.objc.NSObject;
import com.sun.jna.Pointer;

public class MacOSXUiShellLib {
    public static void test(long peer) {
        NSObject object = new NSObject(new Pointer(peer));

    }
}
