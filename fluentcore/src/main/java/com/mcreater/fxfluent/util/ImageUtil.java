package com.mcreater.fxfluent.util;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static com.mcreater.fxfluent.util.NumberUtil.lim;

public class ImageUtil {
    private static final long seed = new Random().nextLong();
    private static Random random = new Random(seed);
    public static Image snapshot(Scene scene, Bounds bounds, Color coverColor, Runnable befSnap, Runnable aftSnap) {
        if (scene == null) return new WritableImage(1, 1);
        int radius = 32;
        int width = (int) scene.getWidth();
        int height = (int) scene.getHeight();
        if (width <= 0 || height <= 0) return new WritableImage(1, 1);
        AtomicReference<WritableImage> result = new AtomicReference<>();

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        Platform.runLater(() -> {
            befSnap.run();
            result.set(scene.getRoot().snapshot(parameters, null));
            aftSnap.run();
        });

        ImageView pane = new ImageView();
        pane.setFitWidth(width);
        pane.setFitHeight(height);
        pane.setEffect(new GaussianBlur(radius));
        while (result.get() == null) {}
        pane.setImage(result.get());

        AtomicReference<WritableImage> blurred = new AtomicReference<>();
        Platform.runLater(() -> blurred.set(pane.snapshot(parameters, null)));

        while (blurred.get() == null) {}

        int[] clrs = new int[width * height];
        int[] opc = new int[width * height];
        new WritableImage(blurred.get().getPixelReader(), radius, radius, width, height).getPixelReader().getPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), clrs, 0, width);

        random = new Random(seed);
        for (int index = 0; index < clrs.length; index++) {
            opc[index] = clrs[index] | 0xFF000000;
            int baser = (opc[index] >> 16) & 0xFF;
            int baseg = (opc[index] >> 8) & 0xFF;
            int baseb = opc[index] & 0xFF;
            double o = coverColor.getOpacity();
            int r = (int) (baser + (coverColor.getRed()*255 - baser) * o);
            int g = (int) (baseg + (coverColor.getGreen()*255 - baseg) * o);
            int b = (int) (baseb + (coverColor.getBlue()*255 - baseb) * o);

            r = lim(r + random.nextInt(-5, 5), 0, 255);
            g = lim(g + random.nextInt(-5, 5), 0, 255);
            b = lim(b + random.nextInt(-5, 5), 0, 255);

            opc[index] = ((0xFF) << 24) |
                         ((r & 0xFF) << 16) |
                         ((g & 0xFF) << 8)  |
                         ((b & 0xFF));
        }

        int bdX = (int) bounds.getMinX();
        int bdY = (int) bounds.getMinY();
        int bdWidth = (int) bounds.getWidth();
        int bdHeight = (int) bounds.getHeight();

        int[] res = new int[bdWidth * bdHeight];
        for (int x = bdX; x < bdX + bdWidth; x++) {
            for (int y = bdY; y < bdY + bdHeight; y++) {
                int interX = x - bdX;
                int interY = y - bdY;

                int idxArr = interY * bdWidth + interX;

                if (x < 0 || x >= width || y < 0 || y >= height) res[idxArr] = 0x0000000;
                else res[idxArr] = opc[y * width + x];
            }
        }

        WritableImage output = new WritableImage(bdWidth, bdHeight);
        output.getPixelWriter().setPixels(0, 0, bdWidth, bdHeight, PixelFormat.getIntArgbInstance(), res, 0, bdWidth);

        return output;
    }
}
