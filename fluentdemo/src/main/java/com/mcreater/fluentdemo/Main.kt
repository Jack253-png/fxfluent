package com.mcreater.fluentdemo

import com.mcreater.fxfluent.util.DefaultFontPatcher
import javafx.application.Application
import javafx.scene.text.Font

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DefaultFontPatcher.patch(Font(18.0))
            Application.launch(MainApp::class.java, *args)
        }
    }
}
