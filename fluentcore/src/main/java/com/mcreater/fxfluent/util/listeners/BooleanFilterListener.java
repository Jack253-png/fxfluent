package com.mcreater.fxfluent.util.listeners;

public interface BooleanFilterListener extends NewValueListener<Boolean>{
    default void changed(Boolean t1) {
        if (t1) onChanged();
    }

    void onChanged();
}
