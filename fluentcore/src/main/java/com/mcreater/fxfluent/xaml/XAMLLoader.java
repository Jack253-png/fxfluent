package com.mcreater.fxfluent.xaml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class XAMLLoader {
    public static void testLoad() throws IOException {
        Map map = new Gson().fromJson(new InputStreamReader(XAMLLoader.class.getClassLoader().getResourceAsStream("xaml/indexes/resources.json")), Map.class);
        ((List<String>) map.get("paths")).forEach(XAMLManager::addFileFromClasspath);

//        XAMLManager.addFileFromClasspath("xaml/Common_themeresources_any.xaml");

        XAMLManager.parse();

        JsonObject object = new JsonObject();
        XAMLManager.getAll().forEach((key, con) -> {
            JsonObject objs = new JsonObject();
            con.forEach(abstractContentTag -> objs.add(abstractContentTag.getKey(), new JsonPrimitive(abstractContentTag.toObject().toString())));
            object.add(key, objs);
        });

        new GsonBuilder().setPrettyPrinting().create().toJson(object, new FileWriter("test.json"));
    }
}
