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

    public static VBox testColorTranslate(Color color) {
        VBox box = new VBox();
        // AccentFillColorSelectedTextBackgroundBrush color
        // SystemAccentColorLight2
        Color newclr = Color.hsb(
                lim(color.getHue()/203*205, 0, 360),
                lim(color.getSaturation()/29*22+(20/100D), 0, 1),
                lim(color.getBrightness()/26*28-(14/100D), 0, 1),
                1
        );
        // SystemAccentColorDark1
        Color newclr2 = Color.hsb(
                lim(color.getHue()/284*280+5, 0, 360), //9
                lim(color.getSaturation()-(30/100D), 0, 1),
                lim(color.getBrightness()/3+(73/100D), 0, 1), //46
                1
        );
        box.getChildren().add(new Rectangle(120, 120, color));
        box.getChildren().add(new Rectangle(120, 120, newclr));
        box.getChildren().add(new Rectangle(120, 120, newclr2));

        printhsb("original", color);
        printhsb("dark calc", newclr2);

        return box;
    }
    public static void printhsb(String clr, Color newclr2) {
        System.out.printf("%s: hsb(%.2f, %.2f, %.2f)\n", clr, newclr2.getHue(), newclr2.getSaturation() * 100, newclr2.getBrightness() * 100);
    }
}
