package model

import javafx.scene.paint.Color

data class GkSquare(val downLeftPoint : GkPoint,
                    val downRightPoint : GkPoint,
                    val upperLeftPoint: GkPoint,
                    val upperRightPoint: GkPoint,
                    val fillColor : Color = Color(0.3,0.3,0.3,0.3),
                    val strokeColor : Color = Color.BLACK) {

    fun createQuadrilateral(): GkPolygon {
        return GkPolygon(listOf(downLeftPoint, downRightPoint, upperRightPoint, upperLeftPoint), fillColor, strokeColor)
    }

}