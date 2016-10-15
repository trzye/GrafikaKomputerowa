package model

data class GkLine(val x: OneDimensionLine, val y: OneDimensionLine, val z: OneDimensionLine) {

    constructor(pointA : GkPoint, pointB : GkPoint) : this(
            OneDimensionLine(pointA.x, pointB.x - pointA.x),
            OneDimensionLine(pointA.y, pointB.y - pointA.y),
            OneDimensionLine(pointA.z, pointB.z - pointA.z)
    )

    fun calculatePoint(t: Double): GkPoint {
        return GkPoint(
                x.a + x.b * t,
                y.a + y.b * t,
                z.a + z.b * t
        )
    }

}

// x = a + b*t
data class OneDimensionLine(val a: Double,val b: Double)
