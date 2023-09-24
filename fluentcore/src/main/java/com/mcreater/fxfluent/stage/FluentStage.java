package com.mcreater.fxfluent.stage;

import com.mcreater.fxfluent.controls.value.AnimatedValue;
import com.mcreater.fxfluent.stage.controls.FluentTitleBar;
import com.mcreater.fxfluent.syslib.UiShellWrapper;
import com.mcreater.fxfluent.util.NativeUtil;
import com.mcreater.fxfluent.util.listeners.NewValueListener;
import com.mcreater.fxfluent.xaml.style.AppColorTheme;
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FluentStage extends Stage {
    private UiShellWrapper.BackdropType backdropType;
    private Node content;
    private Pane sceneContent;
    private boolean lastApply = false;
    private boolean isDarkMode = UiShellWrapper.GetSystemIsDark();
    private boolean isFirstInit = true;
    private boolean disableBackdrop = false;
    private boolean disableBackground = false;
    private AppColorTheme colorThemeOverride = AppColorTheme.SYSTEM;

    public void setColorThemeOverride(AppColorTheme colorThemeOverride) {
        this.colorThemeOverride = colorThemeOverride;
        this.applyBackdropType();
    }

    public void setDisableBackdrop(boolean disableBackdrop) {
        this.disableBackdrop = disableBackdrop;
    }

    public void setDisableBackground(boolean disableBackground) {
        this.disableBackground = disableBackground;
    }

    private final AnimatedValue<Color> windowColor = new AnimatedValue<>(Color.rgb(243, 243, 243), Duration.millis(150));
    public FluentStage() {
        this(StageStyle.DECORATED);
    }
    public FluentStage(StageStyle stageStyle) {
        super(stageStyle);
        init();
    }
    private void init() {
        this.backdropType = UiShellWrapper.BackdropType.getDefault();
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
            isDarkMode = colorThemeOverride == AppColorTheme.SYSTEM ? UiShellWrapper.GetSystemIsDark() : (colorThemeOverride == AppColorTheme.DARK);
            if (!disableBackdrop) {
                lastApply = UiShellWrapper.ApplyBlur(NativeUtil.getWindowHandle(this), backdropType, isDarkMode);
            }
            this.updateScene();
            if (!isFirstInit) {
                setIconified(true);
                setIconified(false);
            }
            else isFirstInit = false;
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
        this.updateScene();
    }

    private FluentTitleBar buildTitleBar() {
        return new FluentTitleBar(this);
    }

    private Color getWindowBackground() {
        if (!disableBackground) {
            int i = isDarkMode ? 32 : 243;
            if (getStyle() != StageStyle.TRANSPARENT) {
                return Color.rgb(i, i, i, 1);
            } else {
                return Color.rgb(i, i, i, (lastApply) ? (UiShellWrapper.needBackground(backdropType) ? .65 : 0) : 1);
            }
        }
        else return Color.TRANSPARENT;
    }

    private void updateScene() {
        int i = isDarkMode ? 32 : 243;
        windowColor.updateValue(getWindowBackground());
        windowColor.property.addListener((observableValue, color, t1) -> FluentStage.this.sceneContent.setBackground(new Background(
                new BackgroundFill(
                        getWindowBackground(),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        )));
        FluentTitleBar titleBar = buildTitleBar();

        Region placeHolder = new Region();
        placeHolder.setPrefHeight(50 + 10);

        this.sceneContent = new Pane();
        this.sceneContent.getChildren().clear();
        this.sceneContent.getChildren().addAll(new VBox(placeHolder, this.content), titleBar);
        this.sceneContent.setBackground(new Background(
                new BackgroundFill(
                        getWindowBackground(),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(this.widthProperty());
        rectangle.heightProperty().bind(this.heightProperty());
        rectangle.setArcWidth(15);
        rectangle.setArcHeight(15);
        maximizedProperty().addListener((NewValueListener<Boolean>) t1 -> {
            rectangle.setArcWidth(t1 ? 0 : 15);
            rectangle.setArcHeight(t1 ? 0 : 15);
        });
        this.sceneContent.setClip(rectangle);
        titleBar.prefWidthProperty().bind(this.widthProperty());

        this.sceneContent.prefWidthProperty().bind(this.widthProperty());
        this.sceneContent.prefHeightProperty().bind(this.heightProperty());

        Scene scene = new Scene(this.sceneContent);
        scene.setFill(Color.TRANSPARENT);
        this.setScene(scene);
    }

   public static class WindowMovement {
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
