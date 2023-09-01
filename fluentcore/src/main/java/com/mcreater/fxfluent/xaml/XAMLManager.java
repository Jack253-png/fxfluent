package com.mcreater.fxfluent.xaml;

import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.xaml.style.AppColorTheme;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import com.mcreater.fxfluent.xaml.tags.AbstractContentTag;
import com.mcreater.fxfluent.xaml.tags.DynamicColorContentTag;
import javafx.scene.paint.Color;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;

import static com.mcreater.fxfluent.util.NumberUtil.lim;

public class XAMLManager {
    private static final List<InputStream> files = new Vector<>();
    private static final Map<String, ResourceDict> registeredContents = new HashMap<>();
    private static final ResourceDict globalRegisteredContents = ResourceDict.createEmpty("{Global}");
    static {
        try {
            XAMLLoader.loadAll();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
        registerAccent();

        files.forEach(file -> {
            try {
                parse(new SAXReader().read(file));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }
    private static void registerAccent() {
        globalRegisteredContents.add(new DynamicColorContentTag("SystemAccentColor", UiShellWrapper::GetSystemCompositionColor));
        globalRegisteredContents.add(new DynamicColorContentTag("SystemAccentColorDark1", () -> {
            Color color = UiShellWrapper.GetSystemCompositionColor();
            return Color.hsb(
                    lim(color.getHue()/203*205, 0, 360),
                    lim(color.getSaturation()/29*22+(20/100D), 0, 1),
                    lim(color.getBrightness()/26*28-(14/100D), 0, 1),
                    1
            );
        }));
        globalRegisteredContents.add(new DynamicColorContentTag("SystemAccentColorLight2", () -> {
            Color color = UiShellWrapper.GetSystemCompositionColor();
            return Color.hsb(
                    lim(color.getHue()/284*280+5, 0, 360), //9
                    lim(color.getSaturation()-(30/100D), 0, 1),
                    lim(color.getBrightness()/3+(73/100D), 0, 1), //46
                    1
            );
        }));
    }

    private static void parse(Document document) {
        Element root = document.getRootElement();
        if (root.getName().equals("ResourceDictionary")) {
            root.elements("ResourceDictionary.ThemeDictionaries")
                    .forEach(themedict -> themedict.elements("ResourceDictionary").forEach(dicts -> dicts.elements().forEach(subEle -> operateDict(dicts.attributeValue("Key"), subEle))));

            root.elements().stream().filter(a -> a.getName().equals("String")).forEach(element -> globalRegisteredContents.add(AbstractContentTag.create(globalRegisteredContents, element)));
        }
    }

    private static void operateDict(String dict, Element subEle) {
        if (!registeredContents.containsKey(dict)) registeredContents.put(dict, ResourceDict.createEmpty(dict));
        Optional.ofNullable(AbstractContentTag.create(registeredContents.get(dict), subEle)).ifPresent(a -> registeredContents.get(dict).add(a));
    }

    public static ResourceDict getCurrentDict() {
        String key = SystemThemeLoop.getTheme() == AppColorTheme.LIGHT ? "Light" : "Default";
        return registeredContents.getOrDefault(key, ResourceDict.createEmpty(key + " (Empty fallback)"));
    }

    public static ResourceDict getGlobal() {
        return globalRegisteredContents;
    }

    public static Map<String, ResourceDict> getAll() {
        return registeredContents;
    }
}
