package com.mcreater.fluentdemo

import com.mcreater.fxfluent.brush.AcrylicInAppFillColorBrush
import com.mcreater.fxfluent.controls.*
import com.mcreater.fxfluent.controls.containers.FluentGridPane
import javafx.geometry.Insets
import javafx.scene.control.ScrollBar
import javafx.scene.layout.VBox

class UIGalleryPane : FluentGridPane() {
    init {
        padding = Insets(10.0)
        val buttons = VBox()
        buttons.children.add(FluentLabel("按钮"))
        buttons.children.add(FluentButton("正常的按钮"))
        val disabledButton = FluentButton("禁用的按钮")
        disabledButton.isDisable = true
        buttons.children.add(disabledButton)
        val defaultButton = FluentButton("系统主题色按钮")
        defaultButton.isDefaultButton = true

        buttons.children.add(defaultButton)
        buttons.spacing = 15.0
        add(buttons, 0, 0)
        val bar = ScrollBar()
        val box2 = FluentCheckBox("test")
        box2.isAllowIndeterminate = true
        AcrylicInAppFillColorBrush().accept(bar, null)
        val hyperlink = FluentHyperlinkButton("test")
        val button = FluentToggleButton("Test")
        val progressBar = FluentProgressBar()
        progressBar.prefWidth = 150.0
        add(bar, 0, 1)
        add(button, 0, 2)
        add(box2, 0, 3)
        add(hyperlink, 0, 4)
        add(progressBar, 0, 5)
    }
}