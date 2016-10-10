package model

import controller.Settings
import javafx.scene.shape.Line

data class GkEdge(val startPoint : GkPoint, val endPoint : GkPoint){
    fun generateLine() = Line(
            startPoint.perspectiveX() + Settings.WINDOW_SIZE/2,
            Settings.WINDOW_SIZE - startPoint.perspectiveY(),
            endPoint.perspectiveX() + Settings.WINDOW_SIZE/2,
            Settings.WINDOW_SIZE - endPoint.perspectiveY()
    )
}