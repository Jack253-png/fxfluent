package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.xaml.ResourceDict;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface AbstractContentTag<T> {
    Map<String, Function<Element, AbstractContentTag<?>>> converters = new HashMap<>();
    AbstractContentTag<T> writeElement(Element element);
    AbstractContentTag<T> setResourceDict(ResourceDict dict);
    Element getElement();
    default String getKey() {
        return getElement().attributeValue("Key");
    }
    T toObject();
    static void register(String key, Function<Element, AbstractContentTag<?>> function) {
        converters.put(key, function);
    }
    static AbstractContentTag<?> create(ResourceDict dict, Element element) {
        if (converters.isEmpty()) {
            register("Color", a -> new ColorContentTag().writeElement(a).setResourceDict(dict));
            register("SolidColorBrush", a -> new SolidColorBrushContentTag().writeElement(a).setResourceDict(dict));
            register("StaticResource", a -> new StaticResourceRedirectContentTag().writeElement(a).setResourceDict(dict));
            register("String", a -> new StringContentTag().writeElement(a).setResourceDict(dict));
        }

        return Optional.ofNullable(converters.get(element.getName()))
                .map(function -> function.apply(element))
                .orElse(null);
    }
}
