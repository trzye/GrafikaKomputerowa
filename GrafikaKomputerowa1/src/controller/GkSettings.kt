package controller

import model.GkPoint

object GkSettings {
    const val WINDOW_SIZE = 750
    const val PERSPECTIVE_DISTANCE = WINDOW_SIZE
    const val ZOOM = WINDOW_SIZE / 25
    const val ROTATE = 0.01
    const val STEP = ZOOM / 2
    val CAMERA_POINT = GkPoint(0, 0, -PERSPECTIVE_DISTANCE)
    var scale = 1.0
}
