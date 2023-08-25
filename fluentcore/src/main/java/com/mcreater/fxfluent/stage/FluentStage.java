package com.mcreater.fxfluent.stage;

import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.util.NativeUtil;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;

public class FluentStage extends Stage {
    private UiShellWrapper.BackdropType backdropType;
    private Node content;
    private VBox sceneContent;
    private boolean lastApply = false;
    private boolean isDarkMode = UiShellWrapper.GetSystemIsDark();
    private final AnimatedValue<Color> windowColor = new AnimatedValue<>(Color.rgb(243, 243, 243), Duration.millis(150));
    public FluentStage() {
        this(StageStyle.DECORATED);
    }
    public FluentStage(StageStyle stageStyle) {
        super(stageStyle);
        init();
    }
    private void init() {
        this.backdropType = UiShellWrapper.BackdropType.MICA;
        this.content = new Pane();
        this.sceneContent = new VBox();
        SystemThemeLoop.addListener(a -> this.applyBackdropType());
    }

    /**
     * Set backdrop type of this stage (Windows only)<br>设置该窗口的背景类型 (仅 Windows)
     * @param backdropType {@link UiShellWrapper.BackdropType}
     */
    public void setBackdropType(UiShellWrapper.BackdropType backdropType) {
        this.backdropType = backdropType;
    }

    /**
     * Apply backdrop type to this stage<br>向该窗口应用背景类型
     */
    public void applyBackdropType() {
        try {
            isDarkMode = UiShellWrapper.GetSystemIsDark();
            lastApply = UiShellWrapper.ApplyBlur(NativeUtil.getWindowHandle(this), backdropType, isDarkMode);
            this.setScene(buildScene());
        }
        catch (Exception e) {
            e.printStackTrace();
            UiShellWrapper.NativeWarn();
            lastApply = false;
        }
    }

    /**
     * Set content of this stage<br>设置该窗口的内容
     * @param content {@link Node}
     */
    public void setContent(Node content) {
        this.content = content;
        this.setScene(buildScene());
    }

    private Node buildTitleBar() {
        HBox box = new HBox();
        box.setPrefHeight(50);
        box.setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.004), CornerRadii.EMPTY, Insets.EMPTY)));
        WindowMovement.getInstance().windowMove(box, this);
        return box;
    }

    private Scene buildScene() {
        int i = isDarkMode ? 32 : 243;
        windowColor.updateValue(Color.rgb(i, i, i, lastApply ? .65 : 0));
        windowColor.property.addListener((observableValue, color, t1) -> FluentStage.this.sceneContent.setBackground(new Background(
                new BackgroundFill(
                        UiShellWrapper.needBackground(backdropType) ?
                                t1 :
                                Color.TRANSPARENT,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        )));
        this.sceneContent = new VBox();
        this.sceneContent.getChildren().clear();
        this.sceneContent.getChildren().addAll(buildTitleBar(), this.content);
        this.sceneContent.setBackground(new Background(
                new BackgroundFill(
                        UiShellWrapper.needBackground(backdropType) ?
                                Color.rgb(i, i, i, lastApply ? .65 : 0) :
                                Color.TRANSPARENT,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));
        Scene scene = new Scene(this.sceneContent);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

   private static class WindowMovement {
        double x1;
        double y1;
        double x_stage;
        double y_stage;

        public static WindowMovement getInstance() {
            return new WindowMovement();
        }

        private WindowMovement() {
        }

        public <V extends Region, K extends Stage> void windowMove(V listenedObject, K stage) {
            Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
            listenedObject.setOnMouseDragged(event -> {
                stage.setX(this.x_stage + event.getScreenX() - this.x1);
                stage.setY(this.y_stage + event.getScreenY() - this.y1);
            });
            listenedObject.setOnDragEntered(null);
            listenedObject.setOnMousePressed(event -> {
                this.x1 = event.getScreenX();
                this.y1 = event.getScreenY();
                this.x_stage = stage.getX();
                this.y_stage = stage.getY();
            });
        }
    }
}
