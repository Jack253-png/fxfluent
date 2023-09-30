package com.mcreater.fxfluent.controls.abstractions

import com.mcreater.fxfluent.xaml.style.AppColorTheme
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop.Companion.addListener
import javafx.scene.Node
import javafx.scene.layout.Pane
import java.util.function.Consumer

interface SystemThemeListenable {
    fun getNode(): Collection<Node?>
    fun init() {
        addListener { appColorTheme: AppColorTheme? ->
            getNode().forEach(object : Consumer<Node?> {
                override fun accept(node: Node?) {
                    if (node is Themeable) (node as Themeable).onUpdateTheme(appColorTheme)
                    if (node is Pane) node.children.forEach(this)
                }
            })
        }
    }
}