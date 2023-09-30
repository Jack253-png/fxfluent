package com.mcreater.fxfluent.xaml.tags;

import com.mcreater.fxfluent.xaml.ResourceDict;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public interface AbstractContentTag<T> {
    Map<String, BiFunction<ResourceDict, Element, AbstractContentTag<?>>> converters = new HashMap<String, BiFunction<ResourceDict, Element, AbstractContentTag<?>>>() {{
        put("Color", (dict, a) -> new ColorContentTag().writeElement(a).setResourceDict(dict));
        put("SolidColorBrush", (dict, a) -> new SolidColorBrushContentTag().writeElement(a).setResourceDict(dict));
        put("LinearGradientBrush", (dict, a) -> new LinearGradientBrushContentTag().writeElement(a).setResourceDict(dict));
        put("StaticResource", (dict, a) -> new StaticResourceRedirectContentTag().writeElement(a).setResourceDict(dict));
        put("String", (dict, a) -> new StringContentTag().writeElement(a).setResourceDict(dict));
        put("Thickness", (dict, a) -> new ThicknessContentTag().writeElement(a).setResourceDict(dict));
    }};
    AbstractContentTag<T> writeElement(Element element);
    AbstractContentTag<T> setResourceDict(ResourceDict dict);
    Element element();
    default String getKey() {
        return element().attributeValue("Key");
    }
    T toObject();
    static void register(String key, BiFunction<ResourceDict, Element, AbstractContentTag<?>> function) {
        converters.put(key, function);
    }
    static AbstractContentTag<?> create(ResourceDict dict, Element element) {
        AbstractContentTag<?> tag = Optional.ofNullable(converters.get(element.getName()))
                .map(function -> function.apply(dict, element))
                .orElse(null);
        Object obj = tag != null ? tag.toObject() : null;
        return tag;
    }
}
