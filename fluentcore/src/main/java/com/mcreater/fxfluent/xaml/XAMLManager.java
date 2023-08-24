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
    public static final Map<String, ResourceDict> registeredContents = new HashMap<>();
    public static final ResourceDict globalRegisteredContents = ResourceDict.createEmpty("{Global}");
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
            root.elements("ResourceDictionary.ThemeDictionaries").forEach(e -> {
                e.elements("ResourceDictionary").forEach(en -> {
                    String id = en.attributeValue("Key");
                    if (!registeredContents.containsKey(id)) registeredContents.put(id, ResourceDict.createEmpty(id));

                    en.elements().forEach(element -> {
                        AbstractContentTag<?> contentTag = AbstractContentTag.create(registeredContents.get(id), element);
                        if (contentTag != null) registeredContents.get(id).add(contentTag);
                    });
                });
            });

            root.elements("String").forEach(element -> globalRegisteredContents.add(AbstractContentTag.create(globalRegisteredContents, element)));
        }
    }
}
