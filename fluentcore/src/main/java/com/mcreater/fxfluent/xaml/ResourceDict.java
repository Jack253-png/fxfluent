package com.mcreater.fxfluent.xaml;

import com.mcreater.fxfluent.xaml.tags.AbstractContentTag;
import com.mcreater.fxfluent.xaml.tags.StaticResourceRedirectContentTag;

import java.util.Vector;

public class ResourceDict extends Vector<AbstractContentTag<?>> {
    public static ResourceDict createEmpty() {
        return new ResourceDict();
    }

    public <T> AbstractContentTag<T> foundTag(String key, Class<T> clazz) {
        for (AbstractContentTag<?> abstractContentTag : this) {
            if (abstractContentTag.getKey().equals(key)) {
                return abstractContentTag instanceof StaticResourceRedirectContentTag ?
                        foundTag(((StaticResourceRedirectContentTag) abstractContentTag).toObject(), clazz) :  // Redirect tags
                        (AbstractContentTag<T>) abstractContentTag;
            }
        }
        return null;
    }
}
