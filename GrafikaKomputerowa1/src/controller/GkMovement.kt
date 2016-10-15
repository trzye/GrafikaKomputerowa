package controller

import model.GkScene

class GkMovement(val gkScene : GkScene) {

    fun stepBack() {
        gkScene.points.forEach { point3d -> point3d.z += GkSettings.STEP }
    }

    fun stepForward() {
        gkScene.points.forEach { point3d -> point3d.z -= GkSettings.STEP }
    }

    fun rotateHorizontal(rotation: Double) {
            gkScene.points.forEach { point ->
                val centerX = 0
                val centerZ = -GkSettings.WINDOW_SIZE
                val newPointX = centerX + (point.x - centerX) * Math.cos(rotation) - (point.z - centerZ) * Math.sin(rotation)
                val newPointZ = centerZ + (point.x - centerX) * Math.sin(rotation) + (point.z - centerZ) * Math.cos(rotation)
                point.x = newPointX
                point.z = newPointZ
            }
    }

    fun stepRight() {
        gkScene.points.forEach { point -> point.x -= GkSettings.STEP }
    }

    fun stepLeft() {
        gkScene.points.forEach { point -> point.x += GkSettings.STEP }
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
