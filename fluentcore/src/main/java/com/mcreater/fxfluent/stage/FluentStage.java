package com.mcreater.fxfluent.stage;

import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.util.NativeUtil;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FluentStage extends Stage {
    private UiShellWrapper.BackdropType backdropType;
    private Node content;
    private VBox sceneContent;
    private boolean lastApply = false;
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
            lastApply = UiShellWrapper.ApplyBlur(NativeUtil.getWindowHandle(this), backdropType);
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
        return box;
    }

    private Scene buildScene() {
        this.sceneContent = new VBox();
        this.sceneContent.getChildren().clear();
        this.sceneContent.getChildren().addAll(buildTitleBar(), this.content);
        this.sceneContent.setBackground(new Background(
                new BackgroundFill(
                        UiShellWrapper.needBackground(backdropType) ?
                                Color.rgb(243, 243, 243, lastApply ? .65 : 0) :
                                Color.TRANSPARENT,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

//      this.sceneContent.setBackground(new Background(new BackgroundFill(Color.rgb(32, 32, 32, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(this.sceneContent);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }
}
