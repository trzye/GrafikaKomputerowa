package model

data class GkLine(val x: GkOneDimensionLine, val y: GkOneDimensionLine, val z: GkOneDimensionLine) {

    constructor(pointA: GkPoint, pointB: GkPoint) : this(
            GkOneDimensionLine(pointA.x, pointB.x - pointA.x),
            GkOneDimensionLine(pointA.y, pointB.y - pointA.y),
            GkOneDimensionLine(pointA.z, pointB.z - pointA.z)
    )

    fun calculatePoint(t: Double): GkPoint {
        return GkPoint(
                x.calculatePoint(t),
                y.calculatePoint(t),
                z.calculatePoint(t)
        )
    }

}

