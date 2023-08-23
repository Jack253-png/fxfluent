package com.mcreater.fxfluent.xaml.tags;

import org.dom4j.Element;

public interface AbstractContentTag<T> {
    AbstractContentTag<T> writeElement(Element element);
    Element getElement();
    default String getKey() {
        return getElement().attributeValue("Key");
    }
    T toObject();
    static AbstractContentTag<?> create(Element element) {
        switch (element.getName()) {
            case "Color":
                return new ColorContentTag().writeElement(element);
            default:
                return null;
        }
    }
}
