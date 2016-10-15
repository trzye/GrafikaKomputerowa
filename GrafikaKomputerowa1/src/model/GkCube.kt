package model

import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Polygon

data class GkCube(val squareA : GkSquare, val squareB : GkSquare, val color: Paint = Color.BLACK){

    fun createQuadrilaterals() : List<GkPolygon>{
        val qb = squareB.createQuadrilateral()
        val qa = squareA.createQuadrilateral()
        qb.fill = color
        qa.fill = Color.rgb(100, 100, 100, 0.5)
        qb.stroke = Color.BLACK
        qa.stroke = Color.BLACK

        val q1 = GkSquare(squareA.downLeftPoint, squareA.downRightPoint, squareB.downLeftPoint, squareB.downRightPoint,
                Color.AQUA, Color.RED).createQuadrilateral()
        q1.fill = color
        q1.stroke = Color.BLACK

        val q4 = GkSquare(squareA.upperLeftPoint, squareA.upperRightPoint, squareB.upperLeftPoint, squareB.upperRightPoint).createQuadrilateral()
        q4.fill = color
        q4.stroke = Color.BLACK

        val q2 = GkSquare(squareA.downLeftPoint, squareA.upperLeftPoint, squareB.downLeftPoint, squareB.upperLeftPoint).createQuadrilateral()
        q2.fill = color
        q2.stroke = Color.BLACK

        val q3 = GkSquare(squareA.downRightPoint, squareA.upperRightPoint, squareB.downRightPoint, squareB.upperRightPoint).createQuadrilateral()
        q3.fill = color
        q3.stroke = Color.BLACK

        return listOf(
                qb,
                q1,
                q2,
                q3,
                q4,
                qa
        )
    }
}


