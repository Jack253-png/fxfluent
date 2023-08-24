package com.mcreater.fxfluent.xaml;

import com.mcreater.fxfluent.xaml.tags.AbstractContentTag;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class XAMLManager {
    private static final List<InputStream> files = new Vector<>();
    private static final Map<String, ResourceDict> registeredContents = new HashMap<>();
    private static final ResourceDict globalRegisteredContents = ResourceDict.createEmpty("{Global}");
    public static void addFile(File file) throws IOException {
        files.add(Files.newInputStream(file.toPath()));
    }

    public static void addFileFromClasspath(String path) {
        files.add(XAMLManager.class.getClassLoader().getResourceAsStream(path));
    }

    public static void parse() {
        registeredContents.values().forEach(ResourceDict::clear);
        registeredContents.clear();
        globalRegisteredContents.clear();
        files.forEach(file -> {
            try {
                parse(new SAXReader().read(file));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }

    private static void parse(Document document) {
        Element root = document.getRootElement();
        if (root.getName().equals("ResourceDictionary")) {
            List<Element> e = root.elements("ResourceDictionary.ThemeDictionaries");
            if (e.size() > 0) {
                e.get(0).elements("ResourceDictionary").forEach(en -> {
                    String id = en.attributeValue("Key");

                    if (!registeredContents.containsKey(id)) registeredContents.put(id, ResourceDict.createEmpty(id));
                });

                registeredContents.keySet().forEach(keyi -> {
                    e.get(0).elements("ResourceDictionary").forEach(en -> {
                        String id = en.attributeValue("Key");
                        if (id.equals(keyi)) {
                            ResourceDict dict = registeredContents.get(id);
                            en.elements().forEach(element -> {
                                AbstractContentTag<?> contentTag = AbstractContentTag.create(dict, element);
                                if (contentTag != null) dict.add(contentTag);
                            });
                        }
                    });
                });
            }
            root.elements().stream().filter(a -> a.getName().equals("String")).forEach(element -> globalRegisteredContents.add(AbstractContentTag.create(globalRegisteredContents, element)));
        }
    }

    public static ResourceDict getCurrentDict() {
        return registeredContents.getOrDefault("Light", ResourceDict.createEmpty("Light"));
    }

    public static ResourceDict getGlobal() {
        return globalRegisteredContents;
    }

    public static Map<String, ResourceDict> getAll() {
        return registeredContents;
    }
}
