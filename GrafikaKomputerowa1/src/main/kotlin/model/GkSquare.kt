package model

import bsp.GkAlphaDirection
import javafx.scene.paint.Color

data class GkSquare(val downLeftPoint: GkPoint,
                    val downRightPoint: GkPoint,
                    val upperLeftPoint: GkPoint,
                    val upperRightPoint: GkPoint,
                    val alphaDirection: GkAlphaDirection,
                    val fillColor: Color = Color(0.3, 0.3, 0.3, 0.3),
                    val strokeColor: Color = fillColor) {

    fun createQuadrilateral(): GkPolygon {
        return GkPolygon(listOf(downLeftPoint, downRightPoint, upperRightPoint, upperLeftPoint), alphaDirection, fillColor, strokeColor)
    }

}