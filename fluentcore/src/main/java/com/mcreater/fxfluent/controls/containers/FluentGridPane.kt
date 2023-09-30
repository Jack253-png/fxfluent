package com.mcreater.fxfluent.controls.containers

import com.mcreater.fxfluent.controls.abstractions.SystemThemeListenable
import javafx.scene.Node
import javafx.scene.layout.GridPane


open class FluentGridPane : GridPane, SystemThemeListenable {
    constructor() {
        init()
    }

    constructor(vararg var1: Node?) {
        children.addAll(*var1)
        init()
    }

    override fun getNode(): Collection<Node> {
        return children
    }
}

