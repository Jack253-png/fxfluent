package com.mcreater.fluentdemo

import com.mcreater.fxfluent.stage.FluentStage
import com.mcreater.fxfluent.syslib.BackdropType
import javafx.application.Application
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.StageStyle

class MainApp : Application() {
    override fun start(primaryStage: Stage) {
        val stage = FluentStage(StageStyle.TRANSPARENT)
        stage.minWidth = 200.0
        stage.setContent(UIGalleryPane())
        stage.backdropType = BackdropType.ACRYLIC
        stage.icons.add(Image(MainApp::class.java.getClassLoader().getResourceAsStream("icons/TitlebarLogo.png")))
        // stage.disableBackdrop = true
        /*stage.width = Screen.getPrimary().visualBounds.width
        stage.height = Screen.getPrimary().visualBounds.height*/
        stage.title = "FxFluent Gallery"
        stage.show()
        stage.applyBackdropType()
    }
}