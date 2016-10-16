package model

import bsp.BspTree
import java.util.*

data class GkScene(val cubes: Set<GkCube>) {

    private val tempPolygons = createTempPolygons()
    private val polygonsByTree = createPolygonsTree()
    val polygons: List<GkPolygon>
        get() = polygonsByTree.getPolygonsByDrawingQueue()

    val points = createAllPoints()

    private fun createAllPoints(): List<GkPoint> {
        val result = emptyList<GkPoint>().toMutableList()
        polygons.forEach {
            polygon ->
            polygon.points3d.forEach {
                point ->
                result.add(point)
            }
        }
        return result.toList()
    }

    private fun createTempPolygons(): List<GkPolygon> {
        val list = emptyList<GkPolygon>().toMutableList()
        cubes.forEach { cube -> list.addAll(cube.createQuadrilaterals()) }
        return list.toList()
    }


    private fun createPolygonsTree(): BspTree {
        val polygonsStack = Stack<GkPolygon>()
        tempPolygons.forEach { polygon -> polygonsStack.push(polygon) }
        return BspTree(polygonsStack)
    }

}