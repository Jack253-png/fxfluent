package com.mcreater.fxfluent.util;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.mcreater.fxfluent.util.NumberUtil.lim;

public class ColorUtil {
    public static Color parseXamlColor(String content) {
        Color color = Color.TRANSPARENT;
        if (content.startsWith("#") && content.length() == 9) color = Color.valueOf("#" + content.substring(3) + content.substring(1, 3));
        else if (content.startsWith("#") && content.length() == 7) {
            color = Color.valueOf(content);
        }
        return color;
    }

    public static VBox testColorTranslate(Color color, Color expected) {
        VBox box = new VBox();
        // SystemAccentColorLight3
        Color newclr = Color.hsb(
                lim(color.getHue()*0.9699+0.05696, 0, 360),
                lim(color.getSaturation()*0.9707-0.2644, 0, 1),
                lim(color.getBrightness()*0.38+0.6379, 0, 1),
                1
        );
        box.getChildren().add(new Rectangle(120, 120, color));
        box.getChildren().add(new Rectangle(120, 120, newclr));
        box.getChildren().add(new Rectangle(120, 120, expected));

        printhsb("original", color);
        printhsb("expected", expected);
        printhsb("calc", newclr);
        System.out.println();

        return box;
    }
    public static void printhsb(String clr, Color newclr2) {
        System.out.printf("%s: hsb(%.2f, %.2f, %.2f)\n", clr, newclr2.getHue(), newclr2.getSaturation() * 100, newclr2.getBrightness() * 100);
    }
}
