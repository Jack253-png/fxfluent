package com.mcreater.fxfluent.xaml;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class XAMLLoader {
    public static void loadAll() throws IOException {
        Map map = new Gson().fromJson(new InputStreamReader(XAMLLoader.class.getClassLoader().getResourceAsStream("xaml/indexes/resources.json")), Map.class);
        ((List<String>) map.get("paths")).forEach(XAMLManager::addFileFromClasspath);
        XAMLManager.parse();
    }

    public static void load(String path) {
        XAMLManager.addFileFromClasspath(path);
        XAMLManager.parse();
    }
}
