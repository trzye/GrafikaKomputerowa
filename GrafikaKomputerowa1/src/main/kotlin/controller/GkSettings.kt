package controller

import bsp.GkAlphaDirection
import model.GkPlane
import model.GkPoint

object GkSettings {
    const val WINDOW_SIZE = 750
    const val PERSPECTIVE_DISTANCE = WINDOW_SIZE
    const val ZOOM = WINDOW_SIZE / 25
    const val ROTATE = 0.01
    const val STEP = ZOOM / 2
    val CAMERA_POINT = GkPoint(0, 0, -PERSPECTIVE_DISTANCE)
    private val CAMERA_PLANE_POINT_ONE = GkPoint(WINDOW_SIZE/2, 0, -PERSPECTIVE_DISTANCE + 1) //plus 1 for rendering reasons
    private val CAMERA_PLANE_POINT_TWO = GkPoint(0, 0, -PERSPECTIVE_DISTANCE + 1)
    private val CAMERA_PLANE_POINT_THREE = GkPoint(0, WINDOW_SIZE/2, -PERSPECTIVE_DISTANCE + 1)
    val CAMERA_PLANE = GkPlane(CAMERA_PLANE_POINT_ONE, CAMERA_PLANE_POINT_TWO, CAMERA_PLANE_POINT_THREE, GkAlphaDirection.BACK)
    var scale = 1.0
}
