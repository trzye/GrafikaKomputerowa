package model

data class GkSquare(val downLeftPoint : GkPoint,
                    val downRightPoint : GkPoint,
                    val upperLeftPoint: GkPoint,
                    val upperRightPoint: GkPoint) {

    fun createQuadrilateral(): GkPolygon {
        return GkPolygon(listOf(downLeftPoint, downRightPoint, upperRightPoint, upperLeftPoint))
    }

}