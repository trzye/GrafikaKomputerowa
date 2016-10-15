package model

import javafx.scene.paint.Color
import java.util.*

data class GkScene(val cubes : Set<GkCube>){

    private val tempPolygons = createTempPolygons()
    val polygons = createPolygons()
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


    private fun createPolygons(): List<GkPolygon> {
        val plane = GkPlane(tempPolygons.last())
        val toSplit = tempPolygons.filter { polygon -> polygon.positionBy(plane) == GkPosition.CANT_STATE }
        val result = tempPolygons.toMutableList()
        result.removeAll(toSplit)
        toSplit.forEach { polygon -> result.addAll(polygon.splitBy(plane)) }
        result.forEach { polygon -> polygon.fill = Color.TRANSPARENT; polygon.stroke = Color.BLACK }
        return copyPolygons(result)
    }

    private fun copyPolygons(result: List<GkPolygon>): List<GkPolygon> {
        val copies = emptyList<GkPolygon>().toMutableList()
        result.forEach { res -> copies.add(res.copy()) }
        return copies.toMutableList()
    }

}