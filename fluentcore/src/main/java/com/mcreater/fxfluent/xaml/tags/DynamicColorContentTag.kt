package com.mcreater.fxfluent.xaml.tags

import javafx.scene.paint.Color
import java.util.function.Supplier


class DynamicColorContentTag(private val key: String, private val consumer: Supplier<Color>) : ColorContentTag() {
    override fun toObject(): Color {
        return consumer.get()
    }

    override fun getKey(): String {
        return key
    }
}

