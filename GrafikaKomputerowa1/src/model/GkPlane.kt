package model

import bsp.GkAlphaDirection

data class GkPlane(val A : Double, val B : Double, val C : Double, val p : GkPoint){

    constructor(firstPoint : GkPoint, middlePoint : GkPoint, lastPoint : GkPoint, alpha: GkAlphaDirection) : this(
            GkVector(firstPoint, middlePoint).multiplyBy(GkVector(middlePoint, lastPoint)).x * alpha.multiplier,
            GkVector(firstPoint, middlePoint).multiplyBy(GkVector(middlePoint, lastPoint)).y * alpha.multiplier,
            GkVector(firstPoint, middlePoint).multiplyBy(GkVector(middlePoint, lastPoint)).z * alpha.multiplier,
            middlePoint
    )
    constructor(polygon : GkPolygon) : this(
            polygon.points3d[0],
            polygon.points3d[1],
            polygon.points3d[2],
            polygon.alphaDirection
    )
    val D : Double = -A*p.x + -B*p.y + -C*p.z

    fun getIntersectionPoint(line: GkLine): GkPoint {
        val t = -(A * line.x.a + B * line.y.a + C * line.z.a + D) / (A * line.x.b + B * line.y.b + C * line.z.b)
        return line.calculatePoint(t)
    }
}