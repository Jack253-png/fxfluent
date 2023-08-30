package com.mcreater.fluentdemo;

import com.mcreater.fxfluent.brush.AcrylicInAppFillColorBrush;
import com.mcreater.fxfluent.stage.FluentStage;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public void start(Stage primaryStage) {
        FluentStage stage = new FluentStage(StageStyle.TRANSPARENT);
        stage.setTitle("FxFluent 控件库展示");
        Region cov = new Region();
        Pane pane = new Pane(new UIGalleryPane(stage), cov);
        cov.prefWidthProperty().bind(stage.widthProperty());
        cov.prefHeightProperty().bind(stage.heightProperty());
        new AcrylicInAppFillColorBrush().accept(cov, null);

        stage.setContent(pane);
        stage.getIcons().add(new Image(MainApp.class.getClassLoader().getResourceAsStream("icons/TitlebarLogo.png")));
        stage.setDisableBackdrop(true);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
        stage.applyBackdropType();
    }

    public static void launch(String... args) {
        Application.launch(args);
    }
}
