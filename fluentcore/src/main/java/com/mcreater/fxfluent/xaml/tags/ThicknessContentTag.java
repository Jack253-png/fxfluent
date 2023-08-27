package com.mcreater.fxfluent.xaml.tags;

import javafx.geometry.Insets;

public class ThicknessContentTag extends SimpleContentTag<Insets> {

    public Insets toObject() {
        String s = element.getData().toString();
        try {
            return new Insets(Integer.parseInt(s));
        }
        catch (Exception ignored) {}

        try {
            String[] sl = s.split(",");
            return new Insets(Integer.parseInt(sl[0]), Integer.parseInt(sl[1]), Integer.parseInt(sl[2]), Integer.parseInt(sl[3]));
        }
        catch (Exception ignored) {}
        return Insets.EMPTY;
    }

    public String toString() {
        return String.format("Insets(%.2f, %.2f, %.2f, %.2f)", toObject().getTop(), toObject().getRight(), toObject().getBottom(), toObject().getLeft());
    }
}
