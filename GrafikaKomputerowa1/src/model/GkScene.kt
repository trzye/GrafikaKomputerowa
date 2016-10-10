package model

import javafx.scene.shape.Line
import javafx.scene.shape.Polygon

data class GkScene(val cubes : Set<GkCube>){

    val points = createAllPoints()

    private fun createAllPoints(): Set<GkPoint> {
        val allPoints = emptySet<GkPoint>().toMutableSet()
        cubes.forEach {
            cube -> cube.edges.forEach {
                edge -> allPoints.addAll(setOf(edge.startPoint, edge.endPoint))
            }
        }
        return allPoints
    }

    fun generateAllLines() : Set<Line> {
        val lines = mutableSetOf<Line>()
        cubes.forEach { cube -> lines.addAll(cube.generateAllLines()) }
        return lines
    }

    fun createQuadrilaterals() : List<Polygon>{
        val list = emptyList<Polygon>().toMutableList()
        cubes.forEach { cube -> list.addAll(cube.createQuadrilaterals())}
        return list.toList()
    }

}