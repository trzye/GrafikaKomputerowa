package controller

import model.GkScene

class GkMovement(val gkScene : GkScene) {

    fun stepBack() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point3d -> point3d.z += GkSettings.STEP } }
    }

    fun stepForward() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point3d -> point3d.z -= GkSettings.STEP } }
    }

    fun rotateHorizontal(rotation: Double) {
        gkScene.polygons.forEach { polygon ->
            polygon.points3d.forEach { point ->
                val centerX = 0
                val centerZ = -GkSettings.WINDOW_SIZE
                val newPointX = centerX + (point.x - centerX) * Math.cos(rotation) - (point.z - centerZ) * Math.sin(rotation)
                val newPointZ = centerZ + (point.x - centerX) * Math.sin(rotation) + (point.z - centerZ) * Math.cos(rotation)
                point.x = newPointX
                point.z = newPointZ
            }
        }
    }

    fun stepRight() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point -> point.x -= GkSettings.STEP } }
    }

    fun stepLeft() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point -> point.x += GkSettings.STEP } }
    }

    fun zoomIn() {
        GkSettings.scale += 0.1
    }

    fun zoomOut() {
        GkSettings.scale -= 0.1
        if (GkSettings.scale < 1)
            GkSettings.scale = 1.0
    }
}
