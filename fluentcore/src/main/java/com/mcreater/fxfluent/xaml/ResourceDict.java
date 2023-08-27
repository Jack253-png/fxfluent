package com.mcreater.fxfluent.xaml;

import com.mcreater.fxfluent.brush.SolidColorBrush;
import com.mcreater.fxfluent.xaml.tags.AbstractContentTag;
import com.mcreater.fxfluent.xaml.tags.ColorContentTag;
import com.mcreater.fxfluent.xaml.tags.SolidColorBrushContentTag;
import com.mcreater.fxfluent.xaml.tags.StaticResourceRedirectContentTag;

import java.util.Vector;

public class ResourceDict extends Vector<AbstractContentTag<?>> {
    public final String name;
    private ResourceDict(String name) {
        this.name = name;
    }
    public static ResourceDict createEmpty(String name) {
        return new ResourceDict(name);
    }

    public <T extends AbstractContentTag<?>> T foundTag(String key, Class<T> clazz) {
        for (AbstractContentTag<?> abstractContentTag : this) {
            if (abstractContentTag.getKey().equals(key)) {
                return abstractContentTag instanceof StaticResourceRedirectContentTag ?
                        foundTag(((StaticResourceRedirectContentTag) abstractContentTag).toObject(), clazz) :  // Redirect tags
                        (T) abstractContentTag;
            }
        }
        return this == XAMLManager.getGlobal() ? null : XAMLManager.getGlobal().foundTag(key, clazz);
    }

    public SolidColorBrush foundSolidColorBrush(String key) {
        try {
            return foundTag(key, SolidColorBrushContentTag.class).toObject();
        }
        catch (Exception e) {
            return new SolidColorBrush(foundTag(key, ColorContentTag.class).toObject());
        }
    }
}
