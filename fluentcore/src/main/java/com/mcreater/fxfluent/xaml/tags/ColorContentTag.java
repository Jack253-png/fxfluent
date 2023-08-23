package com.mcreater.fxfluent.xaml.tags;

import javafx.scene.paint.Color;
import org.dom4j.Element;

public class ColorContentTag implements AbstractContentTag<Color> {
    private Element element;
    public ColorContentTag writeElement(Element element) {
        this.element = element;
        return this;
    }

    public Element getElement() {
        return element;
    }

    public Color toObject() {
        if (!element.elements().isEmpty()) return null;
        else return Color.valueOf(element.getData().toString());
    }
}
