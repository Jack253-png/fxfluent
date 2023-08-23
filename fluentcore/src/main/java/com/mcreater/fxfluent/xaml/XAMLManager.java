package com.mcreater.fxfluent.xaml;

import com.mcreater.fxfluent.xaml.tags.AbstractContentTag;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class XAMLManager {
    private static final List<File> files = new Vector<>();
    public static final Map<String, List<AbstractContentTag<?>>> registeredContents = new HashMap<>();
    public static void addFile(File file) {
        files.add(file);
    }

    public static void parse() {
        registeredContents.clear();
        files.forEach(file -> {
            try {
                parse(new SAXReader().read(file));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }

    private static void parse(Document document) {
        document.getRootElement().elements("ResourceDictionary.ThemeDictionaries").forEach(e -> {
            e.elements("ResourceDictionary").forEach(en -> {
                String id = en.attributeValue("Key");
                if (!registeredContents.containsKey(id)) registeredContents.put(id, new Vector<>());

                en.elements().forEach(element -> {
                    AbstractContentTag<?> contentTag = AbstractContentTag.create(element);
                    if (contentTag != null) registeredContents.get(id).add(contentTag);
                });
            });
        });
    }
}
