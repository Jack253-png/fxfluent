package com.mcreater.fxfluent.xaml.tags;

public class StaticResourceRedirectContentTag extends SimpleContentTag<String> {
    public String toObject() {
        return element.attributeValue("ResourceKey");
    }
}