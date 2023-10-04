package com.mcreater.fxfluent.stage.controls

import com.mcreater.fxfluent.controls.icon.FluentIcon
import com.mcreater.fxfluent.stage.FluentStage
import com.mcreater.fxfluent.syslib.UiShellWrapper.Companion.getSystemIsDark
import com.mcreater.fxfluent.util.listeners.NewValueListener
import com.mcreater.fxfluent.xaml.style.SystemThemeLoop
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Screen
import javafx.stage.StageStyle

class FluentTitleBar(stage: FluentStage): AnchorPane() {
    private var lastx = -1.0;
    private var lasty = -1.0;
    private var lastw = -1.0;
    private var lasth = -1.0;
    var buttonmax: FluentWindowButton? = null
    init {
        prefHeight = 50.0
        minHeight = 50.0
        background = Background(BackgroundFill(Color(0.0, 0.0, 0.0, 0.004), CornerRadii.EMPTY, Insets.EMPTY))
        FluentStage.WindowMovement.instance.windowMove(this, stage)

        val closeButton = FluentCloseButton()
        closeButton.onAction = EventHandler { stage.close() }
        closeButton.alignment = Pos.CENTER
        setRightAnchor(closeButton, 0.0)

        val maximizeButton = FluentWindowButton()
        buttonmax = maximizeButton
        lastx = stage.x
        lasty = stage.y
        lastw = stage.width
        lasth = stage.height
        val iconMax = arrayOf(FluentIcon('\uE922'))
        maximizeButton.graphic = iconMax[0]
        stage.maximizedProperty().addListener(NewValueListener { t1: Boolean ->

        })
        iconMax[0].textFill = if (getSystemIsDark()) Color.WHITE else Color.BLACK
        maximizeButton.onAction = EventHandler {
            stage.maximizedover = !stage.maximizedover
            if (stage.maximizedover) {
                lastx = stage.x
                lasty = stage.y
                lastw = stage.width
                lasth = stage.height
            }
            val wid = Screen.getPrimary().visualBounds.width
            val hei = Screen.getPrimary().visualBounds.height
            stage.minWidth = if (stage.maximizedover) wid else lastw
            stage.maxWidth = if (stage.maximizedover) wid else lastw
            stage.minHeight = if (stage.maximizedover) hei else lasth
            stage.maxHeight = if (stage.maximizedover) hei else lasth
            stage.x = if (stage.maximizedover) 0.0 else lastx
            stage.y = if (stage.maximizedover) 0.0 else lasty
            // stage.isMaximized = !stage.isMaximized
            iconMax[0] = FluentIcon(if (stage.maximizedover) '\uE923' else '\uE922')
            iconMax[0].textFill = if (getSystemIsDark()) Color.WHITE else Color.BLACK
            maximizeButton.graphic = iconMax[0]
        }
        maximizeButton.alignment = Pos.CENTER
        setRightAnchor(maximizeButton, closeButton.prefWidth)

        val minimizeButton = FluentWindowButton()
        minimizeButton.onAction = EventHandler {
            stage.isIconified = true
        }
        minimizeButton.alignment = Pos.CENTER
        setRightAnchor(minimizeButton, maximizeButton.prefWidth + closeButton.prefWidth)
        val iconMin = FluentIcon('\uE921')
        minimizeButton.graphic = iconMin
        iconMin.textFill = if (getSystemIsDark()) Color.WHITE else Color.BLACK

        val view = ImageView(if (!stage.icons.isEmpty()) stage.icons[0] else null)
        view.fitWidth = 16.0
        view.fitHeight = 16.0
        stage.icons.addListener(ListChangeListener {
            if (!stage.icons.isEmpty()) view.image = stage.icons[0]
        })
        setTopAnchor(view, (50 - 16) / 2.0)
        setLeftAnchor(view, 16.0)

        val windowTitle = Label(stage.title)
        windowTitle.font = Font(14.0)
        stage.titleProperty().addListener(NewValueListener { value: String? ->
            windowTitle.text = value
        })
        setTopAnchor(windowTitle, (50 - 20) / 2.0)
        setLeftAnchor(windowTitle, 16.0 + 16.0 + 16.0)
        windowTitle.maxWidthProperty().bind(widthProperty().divide(3).multiply(2))
        windowTitle.textFill = if (SystemThemeLoop.isDark()) Color.WHITE else Color.BLACK

        if (stage.style == StageStyle.TRANSPARENT || stage.style == StageStyle.UNDECORATED) children.addAll(
            closeButton,
            maximizeButton,
            minimizeButton
        )
        children.addAll(view, windowTitle)
    }
    fun onMaxi() {

    }
}