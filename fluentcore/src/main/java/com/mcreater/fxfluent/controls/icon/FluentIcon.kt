package com.mcreater.fxfluent.controls.icon

import javafx.scene.control.Label
import javafx.scene.text.Font

class FluentIcon(chr: Char?, size: Double?): Label() {
    init {
        font = Font.loadFont(
            FluentIcon::class.java.getClassLoader().getResourceAsStream("fonts/Segoe Fluent Icons.ttf"),
            size!!
        )
        text = String(charArrayOf(chr!!))
    }
    constructor(chr: Char?) : this(chr, 10.0)
}