package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.xaml.ResourceDict;
import org.dom4j.Element;

public abstract class SimpleContentTag<T> implements AbstractContentTag<T> {
    protected Element element;
    protected ResourceDict dict;
    public SimpleContentTag<T> writeElement(Element element) {
        this.element = element;
        return this;
    }

    public SimpleContentTag<T> setResourceDict(ResourceDict dict) {
        this.dict = dict;
        return this;
    }

    public Element getElement() {
        return element;
    }
}
