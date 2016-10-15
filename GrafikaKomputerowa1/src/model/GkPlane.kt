package model

data class GkPlane(val A : Double, val B : Double, val C : Double, val p : GkPoint){
    constructor(firstPoint : GkPoint, middlePoint : GkPoint, lastPoint : GkPoint) : this(
            GkVector(firstPoint, middlePoint).multiplyBy(GkVector(middlePoint, lastPoint)).x,
            GkVector(firstPoint, middlePoint).multiplyBy(GkVector(middlePoint, lastPoint)).y,
            GkVector(firstPoint, middlePoint).multiplyBy(GkVector(middlePoint, lastPoint)).z,
            middlePoint
    )
    constructor(polygon : GkPolygon) : this(
            polygon.points3d[0],
            polygon.points3d[1],
            polygon.points3d[2]
    )
    val D : Double = -A*p.x + -B*p.y + -C*p.z

    fun getIntersectionPoint(line: GkLine): GkPoint {
        val t = -(A * line.x.a + B * line.y.a + C * line.z.a + D) / (A * line.x.b + B * line.y.b + C * line.z.b)
        return line.calculatePoint(t)
    }
}

fun main(args: Array<String>) {
    val pointA = GkPoint(1,2,3)
    val pointB = GkPoint(4,2,3)
    val pointC = GkPoint(1,10,3)
    val polygon = GkPolygon(listOf(pointA,pointB,pointC))
    val plane1 = GkPlane(polygon)
    val plane2 = GkPlane(pointA,pointB,pointC)
    assert(plane1 == plane2)
    assert(GkPoint(1,2,-1).positionBy(plane2) == GkPosition.BEFORE_PLANE)
    assert(GkPoint(1,2,10).positionBy(plane2) == GkPosition.BEHIND_PLANE)
}