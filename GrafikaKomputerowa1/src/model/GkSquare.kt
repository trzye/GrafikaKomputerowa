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
                xToWindow(downLeftPoint.perspectiveX()), yToWindow(downLeftPoint.perspectiveY()),
                xToWindow(downRightPoint.perspectiveX()), yToWindow(downRightPoint.perspectiveY()),
                xToWindow(upperRightPoint.perspectiveX()),yToWindow(upperRightPoint.perspectiveY()),
                xToWindow(upperLeftPoint.perspectiveX()), yToWindow(upperLeftPoint.perspectiveY())
        )
    }

    private fun yToWindow(y : Double) = -y

    private fun xToWindow(x : Double) = Settings.WINDOW_SIZE/2 + x

    private fun createAllEdges() : Set<GkEdge>{
        return setOf(
                GkEdge(downLeftPoint, downRightPoint),
                GkEdge(upperLeftPoint, upperRightPoint),
                GkEdge(downLeftPoint, upperLeftPoint),
                GkEdge(downRightPoint, upperRightPoint)
        )
    }

}