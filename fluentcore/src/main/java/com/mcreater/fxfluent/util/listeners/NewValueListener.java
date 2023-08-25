package com.mcreater.fxfluent.util.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public interface NewValueListener<T> extends ChangeListener<T> {
    default void changed(ObservableValue<? extends T> observableValue, T t, T t1) {
        changed(t1);
    }
    void changed(T t1);
}
