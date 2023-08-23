package com.mcreater.fxfluent.xaml;

import java.io.File;
import java.io.IOException;

public class XAMLLoader {
    public static void testLoad() throws IOException {
        File file = new File("D:\\mods\\xaml-ui\\microsoft-ui-xaml-main\\dev\\CommonStyles\\Common_themeresources_any.xaml");
        XAMLManager.addFile(file);
        XAMLManager.parse();
        XAMLManager.registeredContents.forEach((key, con) -> {
            System.out.printf("%s: \n\n", key);
            con.forEach(a -> System.out.printf("Type %s (name %s): %s\n", a.getClass().getSimpleName(), a.getKey(), a.toObject()));
        });
    }
}
