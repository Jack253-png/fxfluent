package com.mcreater.fxfluent.util.listeners

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue


fun interface NewValueListener<T> : ChangeListener<T> {
    override fun changed(observableValue: ObservableValue<out T>, t: T, t1: T) {
        changed(t1)
    }

    fun changed(t1: T)
}

