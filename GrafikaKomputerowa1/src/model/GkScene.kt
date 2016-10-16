package model

import bsp.BspTree
import controller.GkInput
import java.util.*

data class GkScene(val cubes : Set<GkCube>){

    private val tempPolygons = createTempPolygons()
    val polygonsByTree = createPolygonsTree()
    val polygons : List<GkPolygon>
        get() = polygonsByTree.getAllPolygons()

    private fun sort(polygons: List<GkPolygon>): List<GkPolygon> {
        return polygonsByTree.getAllPolygons()
    }

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

    private fun createTempPolygons() : List<GkPolygon>{
        val list = emptyList<GkPolygon>().toMutableList()
        cubes.forEach { cube -> list.addAll(cube.createQuadrilaterals())}
        return list.toList()
    }


    private fun createPolygonsTree(): BspTree {
//        val plane = GkPlane(tempPolygons.last())
//        val toSplit = tempPolygons.filter { polygon -> polygon.positionBy(plane) == GkPosition.CANT_STATE }
//        val result = tempPolygons.toMutableList()
//        result.removeAll(toSplit)
//        toSplit.forEach { polygon -> result.addAll(polygon.splitBy(plane)) }
//        result.forEach { polygon -> polygon.fill = Color.TRANSPARENT; polygon.stroke = Color.BLACK }
        val polygonsStack = Stack<GkPolygon>()
        tempPolygons.forEach { polygon -> polygonsStack.push(polygon) }
        val root = polygonsStack.find { x -> ((x.points3d[0] == GkInput.gkCube1.squareA.downLeftPoint)
        && (x.points3d[1] == GkInput.gkCube1.squareA.downRightPoint) && (x.points3d[2] == GkInput.gkCube1.squareA.upperRightPoint))}
        polygonsStack.remove(root)
        polygonsStack.push(root)
        return BspTree(polygonsStack)
    }

    private fun copyPolygons(result: List<GkPolygon>): List<GkPolygon> {
        val copies = emptyList<GkPolygon>().toMutableList()
        result.forEach { res -> copies.add(res.copy()) }
        return copies.toMutableList()
    }

}