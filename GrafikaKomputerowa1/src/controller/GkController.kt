package controller

import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import model.GkCube
import model.GkPoint
import model.GkScene
import model.GkSquare

class GkController(val group: Group, val scene: Scene) {

    val gkScene = initGkScene()

    fun init() {
        renderScene()
        setListeners()
    }

    private fun renderScene() {
        group.children.clear()
        gkScene.createQuadrilaterals().forEach { quad -> group.children.add(quad) }
    }

    private fun initGkScene(): GkScene {
        val gkCube1 = GkCube(
                GkSquare(GkPoint(-100, -200, 100), GkPoint(100, -200, 100), GkPoint(-100, 200, 100), GkPoint(100, 200, 100)),
                GkSquare(GkPoint(-100, -200, 250), GkPoint(100, -200, 250), GkPoint(-100, 200, 250), GkPoint(100, 200, 250)),
                Color.TRANSPARENT
        )
        val gkCube2 = GkCube(
                GkSquare(GkPoint(110, -20, 110), GkPoint(110, -20, 110), GkPoint(150, 20, 110), GkPoint(150, 20, 110)),
                GkSquare(GkPoint(110, -20, 130), GkPoint(110, -20, 130), GkPoint(150, 20, 130), GkPoint(150, 20, 130)),
                Color.TRANSPARENT
        )
        return GkScene(setOf(gkCube1, gkCube2))
    }

    private fun setListeners() {
        scene.setOnKeyPressed {
            key -> when(key.code){
                KeyCode.W -> stepForward()
            KeyCode.S -> stepBack()
            KeyCode.A -> stepLeft()
            KeyCode.D -> stepRight()
            KeyCode.Q -> rotateLeft(-Settings.ROTATE)
            KeyCode.E -> rotateRight(-Settings.ROTATE)
            KeyCode.R -> zoomIn()
            KeyCode.F -> zoomOut()
        }
            renderScene()
        }
    }

    private fun stepBack() {
        gkScene.points.forEach {
            point -> point.z-=Settings.STEP
        }
    }

    private fun stepForward() {
        gkScene.points.forEach {
            point -> point.z+=Settings.STEP
        }
    }

    private fun rotateRight(rotation : Double) {
        rotate(rotation)
    }

    private fun rotate(rotation: Double) {
        gkScene.points.forEach {
            point ->
            val point2x = point.x
            val point2z = point.z
            val centerX = 0
            val centerZ = 500
            val x = rotation
            val newX = centerX + (point2x - centerX) * Math.cos(x) - (point2z - centerZ) * Math.sin(x)
            val newZ = centerZ + (point2x - centerX) * Math.sin(x) + (point2z - centerZ) * Math.cos(x)
            point.x = newX
            point.z = newZ
        }
    }

    private fun rotateLeft(rotation : Double) {
        rotate(-rotation)
    }

    private fun stepRight() {
        gkScene.points.forEach {
            point ->
            point.x-=Settings.STEP/10
        }
    }

    private fun stepLeft() {
        gkScene.points.forEach {
            point ->
            point.x+=Settings.STEP/10
        }
    }

    private fun zoomIn() {

    }

    private fun zoomOut() {
    }
}


