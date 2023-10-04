package com.mcreater.fxfluent.util

import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.control.SkinBase
import javafx.scene.layout.Region

class ControlUtil {
    companion object {
        @JvmStatic
        fun findControlInSkin(skin: SkinBase<*>, s: String?): Region? {
            val r = arrayOf<Node?>(null)
            skin.children.forEach {
                if (it.styleClass.contains(s)) r[0] = it
            }
            return r[0] as Region?
        }
        @JvmStatic
        fun displayPos(node: Node): Point2D {
            return node.localToScene(0.0, 0.0).add(Point2D(node.scene.window.x, node.scene.window.y))
        }
    }
}