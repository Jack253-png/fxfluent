package com.mcreater.fxfluent.xaml;

import java.io.IOException;

public class XAMLLoader {
    public static void testLoad() throws IOException {
        XAMLManager.addFileFromClasspath("xaml/Common_themeresources_any.xaml");
        XAMLManager.addFileFromClasspath("xaml/Button_themeresources.xaml");
        XAMLManager.parse();
        XAMLManager.registeredContents.forEach((key, con) -> {
            System.out.printf("%s: \n\n", key);
            con.forEach(a -> System.out.printf("Type %s (name %s): %s\n", a.getClass().getSimpleName(), a.getKey(), a.toObject()));
        });
    }
}
