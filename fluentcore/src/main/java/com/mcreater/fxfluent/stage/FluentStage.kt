package com.mcreater.fxfluent.stage

import com.mcreater.fxfluent.controls.abstractions.SystemThemeListenable
import com.mcreater.fxfluent.controls.value.AnimatedValue
import com.mcreater.fxfluent.stage.controls.FluentTitleBar
import com.mcreater.fxfluent.syslib.BackdropType
import com.mcreater.fxfluent.syslib.BackdropType.Companion.default
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.applyBlur
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.getSystemIsDark
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.nativeWarn
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.needBackground
import com.mcreater.fxfluent.util.NativeUtil.Companion.getWindowHandle
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.mcreater.fxfluent.xaml.style.AppColorTheme
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.util.Duration

class FluentStage(style: StageStyle) : Stage(style) {
    private var isBottomLeft = false
    private var isRight = false
    private var isBottomRight = false
    private var isBottom = false
    private var isTopRight = false
    var maximizedover = false
    init {
        init()
    }
    constructor() : this(StageStyle.TRANSPARENT) {
        init()
    }

    private var content: Node? = null
    private var sceneContent: Pane? = null
    private var lastApply = false
    private var isDarkMode = getSystemIsDark()
    var backdropType: BackdropType? = null
    var disableBackdrop = false
    var disableBackground = false
    private var fluentTitleBar: FluentTitleBar? = null
    var colorThemeOverride = AppColorTheme.SYSTEM
        /**
         * Set backdrop type of this stage (Windows only)<br></br>设置该窗口的背景类型 (仅 Windows)
         * @param [BackdropType]
         */
        set(a) {
            field = a
            applyBackdropType()
        }

    private val windowColor = AnimatedValue(Color.rgb(243, 243, 243), Duration.millis(150.0))
    private fun init() {
        backdropType = default
        content = Pane()
        sceneContent = VBox()
        SystemThemeLoop.addListener { applyBackdropType() }
    }

    /**
     * Apply backdrop type to this stage<br></br>向该窗口应用背景类型
     */
    fun applyBackdropType() {
        try {
            isDarkMode =
                if (colorThemeOverride == AppColorTheme.SYSTEM) getSystemIsDark() else colorThemeOverride == AppColorTheme.DARK
            if (!disableBackdrop) {
                lastApply = applyBlur(
                    getWindowHandle(this),
                    backdropType!!, isDarkMode
                )
            }
            updateScene()
        } catch (e: Exception) {
            e.printStackTrace()
            nativeWarn()
            lastApply = false
        }
    }

    /**
     * Set content of this stage<br></br>设置该窗口的内容
     * @param content [Node]
     */
    fun setContent(content: Node?) {
        if (content is SystemThemeListenable) {
            this.content = content
            updateScene()
        }
    }

    private fun buildTitleBar(): FluentTitleBar {
        return FluentTitleBar(this)
    }

    private fun getWindowBackground(): Color {
        return if (!disableBackground) {
            val i = if (isDarkMode) 32 else 243
            if (style != StageStyle.TRANSPARENT) {
                Color.rgb(i, i, i, 1.0)
            } else {
                Color.rgb(i, i, i, if (lastApply) (if (needBackground(backdropType!!)) .65 else 0.0) else 1.0)
            }
        } else Color.TRANSPARENT
    }

    private fun updateScene() {
        windowColor.updateValue(getWindowBackground())
        windowColor.property.addListener { _: ObservableValue<out Color>?, _: Color?, _: Color? ->
            this@FluentStage.sceneContent?.background = Background(
                BackgroundFill(
                    getWindowBackground(),
                    CornerRadii.EMPTY,
                    Insets.EMPTY
                )
            )
        }
        val titleBar = buildTitleBar()
        fluentTitleBar = titleBar
        val placeHolder = Region()
        placeHolder.prefHeight = (50 + 10).toDouble()
        sceneContent = Pane()
        sceneContent!!.children.clear()
        if (style != StageStyle.DECORATED || style != StageStyle.UTILITY || style != StageStyle.UNIFIED) sceneContent!!.children.addAll(VBox(placeHolder, content), titleBar)
        else sceneContent!!.children.addAll(content)

        (content as Region).prefWidthProperty().bind(widthProperty())
        (content as Region).prefHeightProperty().bind(heightProperty().add(
            if (style != StageStyle.DECORATED || style != StageStyle.UTILITY || style != StageStyle.UNIFIED) -60 else 0
        ))

        sceneContent!!.background = Background(
            BackgroundFill(
                getWindowBackground(),
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )
        /*sceneContent?.border = Border(
            BorderStroke(
                Color.rgb(0, 0, 0, 0.5),
                BorderStrokeStyle.SOLID,
                CornerRadii(5.0),
                BorderWidths(1.0),
                Insets.EMPTY
            )
        )*/
        val rectangle = Rectangle()
        rectangle.widthProperty().bind(widthProperty())
        rectangle.heightProperty().bind(heightProperty())
        rectangle.arcWidth = 15.0
        rectangle.arcHeight = 15.0
        maximizedProperty().addListener (NewValueListener {
            rectangle.arcWidth = (if (isMaximized) 0 else 15).toDouble()
            rectangle.arcHeight = (if (isMaximized) 0 else 15).toDouble()
        })
        sceneContent!!.clip = rectangle
        titleBar.prefWidthProperty().bind(widthProperty())
        /*sceneContent!!.prefWidthProperty().bind(widthProperty())
        sceneContent!!.prefHeightProperty().bind(heightProperty())*/

        sceneContent!!.setOnMouseMoved { event: MouseEvent ->
            event.consume()
            val x = event.sceneX
            val y = event.sceneY
            val width: Double = this.width
            val height: Double = this.height
            var cursorType: Cursor = Cursor.DEFAULT
            isBottom = false
            isBottomRight = false
            isRight = false
            isBottomLeft = false
            isTopRight = false
            if (y >= height - 5) {
                if (x <= 5) {
                    isBottomLeft = true
                    cursorType = Cursor.SW_RESIZE
                }
                else if (x >= width - 5) {
                    isBottomRight = true
                    cursorType = Cursor.SE_RESIZE
                }
                else {
                    isBottom = true
                    cursorType = Cursor.S_RESIZE
                }
            }
            else if (y <= 5) {
                if (x >= width - 5) {
                    isTopRight = true
                    cursorType = Cursor.NE_RESIZE
                }
            }
            else {
                if (x >= width - 5) {
                    isRight = true
                    cursorType = Cursor.E_RESIZE
                }
            }
            sceneContent!!.cursor = cursorType
        }
        sceneContent!!.setOnMouseDragged { event: MouseEvent ->
            event.consume()
            if (!maximizedover) {
                val righted = isTopRight || isRight || isBottomRight
                val bottomed = isBottomLeft || isBottom || isBottomRight

                if (bottomed && event.sceneY >= minHeight && event.sceneY >= 60) this.height = event.sceneY
                if (righted && event.sceneX >= minWidth) this.width = event.sceneX
            }
        }

        val scene = Scene(sceneContent)
        scene.fill = Color.TRANSPARENT
        this.scene = scene
    }


    class WindowMovement private constructor() {
        private var x1 = 0.0
        private var y1 = 0.0
        private var x_stage = 0.0
        private var y_stage = 0.0
        fun <V : Region?, K : FluentStage?> windowMove(listenedObject: V, stage: K) {
            listenedObject!!.onMouseDragged = EventHandler { event: MouseEvent ->
                if (stage!!.maximizedover) {
                    stage.fluentTitleBar?.buttonmax?.onAction?.handle(ActionEvent())
                    return@EventHandler
                }
                stage.x = x_stage + event.screenX - x1
                stage.y = y_stage + event.screenY - y1
            }
            listenedObject.onDragEntered = null
            listenedObject.onMousePressed = EventHandler { event: MouseEvent ->
                if (stage!!.maximizedover) {
                    stage.fluentTitleBar?.buttonmax?.onAction?.handle(ActionEvent())
                    return@EventHandler
                }
                x1 = event.screenX
                y1 = event.screenY
                x_stage = stage.x
                y_stage = stage.y
            }
        }

        companion object {
            @JvmStatic
            val instance: WindowMovement
                get() = WindowMovement()
        }
    }

}