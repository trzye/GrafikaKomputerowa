package model

import bsp.GkAlphaDirection
import bsp.GkPosition
import bsp.GkPosition.*
import controller.GkSettings
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon

class GkPolygon(val points3d: List<GkPoint>, val alphaDirection: GkAlphaDirection,
                val fillColor: Color = Color(0.3, 0.3, 0.3, 0.3),
                val strokeColor: Color = Color.BLACK) : Polygon() {

    init {
        if (points3d.size > 2) {
            points3d.forEach { point3d -> points.addAll(xToWindow(point3d.x2d), yToWindow(point3d.y2d)) }
            fill = fillColor
            stroke = strokeColor
        } else {
            throw IllegalArgumentException("[Given ${points3d.size} points] Polygon need at least 3 points to exists!")
        }
    }

    private fun yToWindow(y: Double) = -y

    private fun xToWindow(x: Double) = GkSettings.WINDOW_SIZE / 2 + x

    fun refresh() {
        points.clear()
        points3d.forEach { point3d -> points.addAll(xToWindow(point3d.refresh().x2d), yToWindow(point3d.refresh().y2d)) }
    }

    fun positionBy(plane: GkPlane): GkPosition {
        if (points3d.count { p -> p.positionBy(plane) == BEHIND_PLANE } == 0)
            return BEFORE_PLANE
        if (points3d.count { p -> p.positionBy(plane) == BEFORE_PLANE } == 0)
            return BEHIND_PLANE
        return CANT_STATE
    }

    fun splitBy(plane: GkPlane): List<GkPolygon> {
        if (positionBy(plane) == CANT_STATE) {
            val sortedPoints = getListWhereBehindPointsAreAtTheBeginning(plane)
            val lastBehindIndex = sortedPoints.indexOfLast { p -> p.positionBy(plane) == BEHIND_PLANE }
            val pointA1 = sortedPoints[lastBehindIndex]
            val pointA2 = sortedPoints.first()
            val pointC1 = sortedPoints[lastBehindIndex + 1]
            val pointC2 = sortedPoints.last()
            val pointB1: GkPoint = plane.getIntersectionPoint(GkLine(pointA1, pointC1))
            val pointB2: GkPoint = plane.getIntersectionPoint(GkLine(pointA2, pointC2))
            val listOne = sortedPoints.subList(0, lastBehindIndex + 1).toMutableList()
            listOne.addAll(listOf(pointB1, pointB2))
            val listTwo = sortedPoints.subList(lastBehindIndex + 1, sortedPoints.lastIndex + 1).toMutableList()
            listTwo.addAll(listOf(pointB2, pointB1))
            return listOf(
                    GkPolygon(listOne, alphaDirection, fillColor, strokeColor),
                    GkPolygon(listTwo, alphaDirection, fillColor, strokeColor))
        }
        throw IllegalArgumentException("Can't split polygon $points3d by plane $plane")
    }

    private fun getListWhereBehindPointsAreAtTheBeginning(plane: GkPlane): MutableList<GkPoint> {
        val sortedPoints = points3d.toMutableList()
        while ((sortedPoints.first().positionBy(plane) != BEHIND_PLANE)
                || (sortedPoints.last().positionBy(plane) == BEHIND_PLANE)) {
            val removed = sortedPoints.removeAt(sortedPoints.lastIndex)
            sortedPoints.add(0, removed)
        }
        return sortedPoints
    }

    fun copy(): GkPolygon {
        val points = emptyList<GkPoint>().toMutableList()
        points3d.forEach { point -> points.add(point.copy()) }
        val copy = GkPolygon(points, alphaDirection, fillColor, strokeColor)
        return copy
    }
}

