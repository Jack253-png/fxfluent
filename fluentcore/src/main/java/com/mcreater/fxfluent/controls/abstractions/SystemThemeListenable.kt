package com.mcreater.fxfluent.controls.abstractions

import com.mcreater.fxfluent.xaml.style.AppColorTheme
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop.Companion.addListener
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop.Companion.theme
import javafx.scene.Node
import javafx.scene.layout.Pane
import java.util.function.Consumer

interface SystemThemeListenable {
    fun getNode(): Collection<Node?>
    fun filterTheme(input: AppColorTheme): AppColorTheme
    fun onChange(input: AppColorTheme)
    fun implUpdate(it: AppColorTheme) {
        getNode().forEach(object : Consumer<Node?> {
            override fun accept(n: Node?) {
                if (n is Themeable) n.onUpdateTheme(filterTheme(it))
                if (n is Pane) n.children.forEach(this)
            }
        })
        onChange(it)
    }
    fun init() {
        addListener { implUpdate(it) }
        implUpdate(theme)
    }
}