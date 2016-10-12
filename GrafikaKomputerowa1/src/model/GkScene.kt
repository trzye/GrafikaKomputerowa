package model

import javafx.scene.shape.Polygon

data class GkScene(val cubes : Set<GkCube>){

    val polygons = createQuadrilaterals()

    private fun createQuadrilaterals() : List<GkPolygon>{
        val list = emptyList<GkPolygon>().toMutableList()
        cubes.forEach { cube -> list.addAll(cube.createQuadrilaterals())}
        return list.toList()
    }

}