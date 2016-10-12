package controller

import javafx.scene.Cursor
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.stage.Screen
import model.GkCube
import model.GkPoint
import model.GkScene
import model.GkSquare
import java.awt.Robot

class GkController(val group: Group, val scene: Scene) {

    val gkScene = initGkScene()

    fun init() {
        renderScene()
        setListeners()
    }

    private fun renderScene() {
        group.children.clear()
        gkScene.polygons.forEach { polygon ->
            polygon.refresh()
            group.children.add(polygon)
        }
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
            key ->
            when(key.code){
                KeyCode.W -> stepForward()
            KeyCode.S -> stepBack()
            KeyCode.A -> stepLeft()
            KeyCode.D -> stepRight()
            KeyCode.R -> zoomIn()
            KeyCode.F -> zoomOut()
        }
            renderScene()
        }
        scene.setOnMouseMoved {
            movement ->
            if(mouseX != null) {
                if (mouseX!! - movement.x < 0)
                    rotateHorizontal(Settings.ROTATE)
                if (mouseX!! - movement.x > 0)
                    rotateHorizontal(-Settings.ROTATE)
            }
            mouseX = movement.x
            scene.cursor = Cursor.NONE
            if((movement.sceneX.toInt() < 100) || (movement.sceneX.toInt() > Settings.WINDOW_SIZE - 100))
                Robot().mouseMove(screenWidth/2, screenHeight/2)
            renderScene()
        }
    }

    var screenWidth = Screen.getPrimary().bounds.width.toInt()
    var screenHeight = Screen.getPrimary().bounds.height.toInt()

    var mouseX : Double? = null


    private fun stepBack() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point3d -> point3d.z += Settings.STEP } }

    }

    private fun stepForward() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point3d -> point3d.z -= Settings.STEP } }
    }

    private fun rotateHorizontal(rotation: Double) {
        gkScene.polygons.forEach { polygon ->
            polygon.points3d.forEach { point ->
                val centerX = 0
                val centerZ = -Settings.WINDOW_SIZE
                val newPointX = centerX + (point.x - centerX) * Math.cos(rotation) - (point.z - centerZ) * Math.sin(rotation)
                val newPointZ = centerZ + (point.x - centerX) * Math.sin(rotation) + (point.z - centerZ) * Math.cos(rotation)
                point.x = newPointX
                point.z = newPointZ
            }
        }
    }

    private fun stepRight() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point -> point.x-=Settings.STEP } }
    }

    private fun stepLeft() {
        gkScene.polygons.forEach { polygon -> polygon.points3d.forEach { point -> point.x+=Settings.STEP } }
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


