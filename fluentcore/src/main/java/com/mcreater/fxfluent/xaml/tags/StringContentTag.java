package com.mcreater.fxfluent.xaml.tags;

public class StringContentTag extends SimpleContentTag<String> {
    public String toObject() {
        return element.getData().toString();
    }
}
