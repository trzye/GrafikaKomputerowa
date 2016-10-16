package bsp

import controller.GkSettings
import javafx.scene.shape.Polygon
import model.GkPlane
import model.GkPoint
import model.GkPolygon
import java.util.*
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

class BspTree private @Inject constructor(polygons: Stack<GkPolygon>, val parent: BspTree?) {

    constructor(polygons: Stack<GkPolygon>) : this(polygons, null)

    val element: GkPolygon
    val front: BspTree?
    val back: BspTree?

    init {
        element = polygons.pop()
        val plane = GkPlane(element)
        val frontPolygons = Stack<GkPolygon>()
        val backPolygons = Stack<GkPolygon>()
        while (polygons.isNotEmpty()) {
            val polygon = polygons.pop().copy()
            when (polygon.positionBy(plane)) {
                GkPosition.ON_PLANE -> frontPolygons.push(polygon)
                GkPosition.BEFORE_PLANE -> frontPolygons.push(polygon)
                GkPosition.BEHIND_PLANE -> backPolygons.push(polygon)
                GkPosition.CANT_STATE -> polygon.splitBy(plane).forEach { polygon -> polygons.push(polygon) }
            }
        }
        if (frontPolygons.isNotEmpty())
            front = BspTree(polygons = frontPolygons, parent = this)
        else
            front = null
        if (backPolygons.isNotEmpty())
            back = BspTree(polygons = backPolygons, parent = this)
        else
            back = null
    }

    val myPoint = GkPoint(0, 0, -GkSettings.PERSPECTIVE_DISTANCE)

    fun getAllPolygons(): List<GkPolygon> {
        val result = emptyList<GkPolygon>().toMutableList()
        if (myPoint.positionBy(GkPlane(element)) == GkPosition.BEFORE_PLANE) {
            if (back != null)
                result.addAll(back.getAllPolygons())
            result.add(element)
            if (front != null)
                result.addAll(front.getAllPolygons())
        } else {
            if (front != null)
                result.addAll(front.getAllPolygons())
            result.add(element)
            if (back != null)
                result.addAll(back.getAllPolygons())
        }
        return result
    }
}


