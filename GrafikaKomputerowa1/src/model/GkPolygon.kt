package model

import controller.Settings
import javafx.scene.shape.Polygon

class GkPolygon(val points3d: List<GkPoint>) : Polygon() {

    init {
        if (points3d.size > 2) {
            points3d.forEach { point3d -> points.addAll(xToWindow(point3d.x2d), yToWindow(point3d.y2d)) }
        } else {
            throw IllegalArgumentException("[Given ${points3d.size} points] Polygon need at least 3 points to exists!")
        }
    }

    private fun yToWindow(y: Double) = -y

    private fun xToWindow(x: Double) = Settings.WINDOW_SIZE / 2 + x

    fun refresh() {
        points.clear()
        points3d.forEach { point3d -> points.addAll(xToWindow(point3d.refresh().x2d), yToWindow(point3d.refresh().y2d)) }
    }
}