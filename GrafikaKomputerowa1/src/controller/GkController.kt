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
        gkScene.createQuadrilaterals().forEach { quad ->
            group.children.add(quad)
        }
        println(gkScene)
    }

    private fun initGkScene(): GkScene {
        val gkCube1 = GkCube(
                squareA =
                GkSquare(
                        downLeftPoint   = GkPoint(x = -100,  y = -300,  z = 200),
                        downRightPoint  = GkPoint(x = 100,  y = -300,  z = 200),
                        upperLeftPoint  = GkPoint(x = -100,  y = -100,z = 200),
                        upperRightPoint = GkPoint(x = 100,  y = -100,z = 200)
                ),
                squareB =
                GkSquare(
                        downLeftPoint   = GkPoint(x = -100,  y = -300,  z = 400),
                        downRightPoint  = GkPoint(x = 100,  y = -300,  z = 400),
                        upperLeftPoint  = GkPoint(x = -100,  y = -100,z = 400),
                        upperRightPoint = GkPoint(x = 100,  y = -100,z = 400)
                ),
                color = Color.TRANSPARENT
        )
        val gkCube2 = GkCube(
                squareA =
                GkSquare(
                        downLeftPoint   = GkPoint(x = 200,  y = -300,  z = 300),
                        downRightPoint  = GkPoint(x = 300,  y = -300,  z = 300),
                        upperLeftPoint  = GkPoint(x = 200,  y = -200,z = 300),
                        upperRightPoint = GkPoint(x = 300,  y = -200,z = 300)
                ),
                squareB =
                GkSquare(
                        downLeftPoint   = GkPoint(x = 200,  y = -300,  z = 500),
                        downRightPoint  = GkPoint(x = 300,  y = -300,  z = 500),
                        upperLeftPoint  = GkPoint(x = 200,  y = -200,z = 500),
                        upperRightPoint = GkPoint(x = 300,  y = -200,z = 500)
                ),
                color = Color.TRANSPARENT
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
            point ->
            point.z+=Settings.STEP
            Settings.CAMERA_Z-=Settings.STEP
        }
    }

    private fun stepForward() {
        gkScene.points.forEach {
            point ->
            point.z-=Settings.STEP
            Settings.CAMERA_Z+=Settings.STEP
//            print("${point} X: ${point.perspectiveX()} Y: ${point.perspectiveY()}")
        }
    }

    private fun rotateRight(rotation : Double) {
        rotate(-rotation)
    }

    private fun rotate(rotation: Double) {
        gkScene.points.forEach {
            point ->
            val point2x = point.x
            val point2z = point.z
            val centerX = Settings.CAMERA_X
            val centerZ = -Settings.WINDOW_SIZE
            val x = rotation
            val newX = centerX + (point2x - centerX) * Math.cos(x) - (point2z - centerZ) * Math.sin(x)
            val newZ = centerZ + (point2x - centerX) * Math.sin(x) + (point2z - centerZ) * Math.cos(x)
            point.x = newX
            point.z = newZ
        }
    }

    private fun rotateLeft(rotation : Double) {
        rotate(rotation)
    }

    private fun stepRight() {
        gkScene.points.forEach {
            point ->
            point.x-=Settings.STEP
        }
    }

    private fun stepLeft() {
        gkScene.points.forEach {
            point ->
            point.x+=Settings.STEP
        }
    }

    private fun zoomIn() {
        Settings.SCALE += 0.1
    }

    private fun zoomOut() {
        Settings.SCALE -= 0.1
        if(Settings.SCALE < 1)
            Settings.SCALE = 1.0
    }
}


