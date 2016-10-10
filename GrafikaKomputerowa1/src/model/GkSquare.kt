package model

import controller.Settings
import javafx.scene.shape.Polygon

data class GkSquare(val downLeftPoint : GkPoint,
                    val downRightPoint : GkPoint,
                    val upperLeftPoint: GkPoint,
                    val upperRightPoint: GkPoint) {

    val edges = createAllEdges()

    fun createQuadrilateral(): Polygon {
        return Polygon(
                Settings.WINDOW_SIZE/2 + downLeftPoint.perspectiveX(),- Settings.WINDOW_SIZE/2 + Settings.WINDOW_SIZE - downLeftPoint.perspectiveY(),
                Settings.WINDOW_SIZE/2 +  downRightPoint.perspectiveX(), -Settings.WINDOW_SIZE/2 + Settings.WINDOW_SIZE - downRightPoint.perspectiveY(),
                Settings.WINDOW_SIZE/2 +  upperRightPoint.perspectiveX(),-Settings.WINDOW_SIZE/2 + Settings.WINDOW_SIZE - upperRightPoint.perspectiveY(),
                Settings.WINDOW_SIZE/2 +  upperLeftPoint.perspectiveX(),-Settings.WINDOW_SIZE/2 + Settings.WINDOW_SIZE - upperLeftPoint.perspectiveY()
        )
    }

    private fun createAllEdges() : Set<GkEdge>{
        return setOf(
                GkEdge(downLeftPoint, downRightPoint),
                GkEdge(upperLeftPoint, upperRightPoint),
                GkEdge(downLeftPoint, upperLeftPoint),
                GkEdge(downRightPoint, upperRightPoint)
        )
    }

}