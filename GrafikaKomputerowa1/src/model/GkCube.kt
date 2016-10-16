package model

import bsp.GkAlphaDirection
import javafx.scene.paint.Color
import javafx.scene.paint.Paint

data class GkCube(val squareA: GkSquare, val squareB: GkSquare, val color: Paint = Color.BLACK) {

    fun createQuadrilaterals(): List<GkPolygon> {
        val back = squareB.createQuadrilateral()
        val front = squareA.createQuadrilateral()

        val floor = GkSquare(
                squareA.downLeftPoint, squareA.downRightPoint, squareB.downLeftPoint, squareB.downRightPoint,
                GkAlphaDirection.FLOOR, Color.BROWN).createQuadrilateral()

        val top = GkSquare(
                squareA.upperLeftPoint, squareA.upperRightPoint, squareB.upperLeftPoint, squareB.upperRightPoint,
                GkAlphaDirection.TOP, Color.YELLOW).createQuadrilateral()

        val left = GkSquare(squareA.downLeftPoint, squareA.upperLeftPoint, squareB.downLeftPoint, squareB.upperLeftPoint,
                GkAlphaDirection.LEFT, Color.GREEN).createQuadrilateral()

        val right = GkSquare(squareA.downRightPoint, squareA.upperRightPoint, squareB.downRightPoint, squareB.upperRightPoint,
                GkAlphaDirection.RIGHT, Color.BLUE).createQuadrilateral()

        return listOf(
                back,
                floor,
                left,
                right,
                top,
                front
        )
    }
}


