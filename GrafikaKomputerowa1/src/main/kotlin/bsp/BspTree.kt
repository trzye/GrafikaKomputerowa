package bsp

import bsp.GkPosition.*
import controller.GkSettings.CAMERA_POINT
import model.GkPlane
import model.GkPolygon
import java.util.*

class BspTree(polygons: Stack<GkPolygon>) {

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
                ON_PLANE -> frontPolygons.push(polygon)
                BEFORE_PLANE -> frontPolygons.push(polygon)
                BEHIND_PLANE -> backPolygons.push(polygon)
                CANT_STATE -> polygon.splitBy(plane).forEach { polygon -> polygons.push(polygon) }
            }
        }
        if (frontPolygons.isNotEmpty())
            front = BspTree(frontPolygons)
        else
            front = null
        if (backPolygons.isNotEmpty())
            back = BspTree(backPolygons)
        else
            back = null
    }


    fun getPolygonsByDrawingQueue(): List<GkPolygon> {
        val result = emptyList<GkPolygon>().toMutableList()
        if (CAMERA_POINT.positionBy(GkPlane(element)) == BEFORE_PLANE) {
            if (back != null)
                result.addAll(back.getPolygonsByDrawingQueue())
            result.add(element)
            if (front != null)
                result.addAll(front.getPolygonsByDrawingQueue())
        } else {
            if (front != null)
                result.addAll(front.getPolygonsByDrawingQueue())
            result.add(element)
            if (back != null)
                result.addAll(back.getPolygonsByDrawingQueue())
        }
        return result
    }
}


