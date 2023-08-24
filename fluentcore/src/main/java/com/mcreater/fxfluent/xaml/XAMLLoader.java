package com.mcreater.fxfluent.xaml;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class XAMLLoader {
    public static void testLoad() throws IOException {
        Map map = new Gson().fromJson(new InputStreamReader(XAMLLoader.class.getClassLoader().getResourceAsStream("xaml/indexes/resources.json")), Map.class);
        ((List<String>) map.get("paths")).forEach(XAMLManager::addFileFromClasspath);

        // XAMLManager.addFileFromClasspath("xaml/Common_themeresources_any.xaml");

        XAMLManager.parse();
        XAMLManager.getAll().forEach((key, con) -> {
            System.out.printf("%s: \n\n", key);
            con.forEach(a -> {
                System.out.println(a.getKey());
                System.out.printf("Type %s (name %s): %s\n", a.getClass().getSimpleName(), a.getKey(), a.toObject());
            });
        });
        System.out.print("{Global}: \n\n");
        XAMLManager.getGlobal().forEach(a -> {
            System.out.println(a.getKey());
            System.out.printf("Type %s (name %s): %s\n", a.getClass().getSimpleName(), a.getKey(), a.toObject());
        });
    }
}
