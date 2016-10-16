package controller

import bsp.GkPosition
import javafx.scene.Cursor
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.stage.Screen
import model.GkPolygon
import java.awt.Robot

class GkController(val group: Group, val scene: Scene) {

    val gkScene = GkInput.gkScene
    var lastMouseXPosition: Double? = null

    fun init() {
        renderScene()
        setListeners()
    }

    private fun renderScene() {
        group.children.clear()
        gkScene.polygons.forEach { polygon ->
            when(polygon.positionBy(GkSettings.CAMERA_PLANE)){
                GkPosition.ON_PLANE -> group.children.add(polygon)
                GkPosition.BEFORE_PLANE -> group.children.add(polygon)
                GkPosition.BEHIND_PLANE -> {} //don't render when behind scene
                GkPosition.CANT_STATE -> {group.children.add(polygon.copy().splitBy(GkSettings.CAMERA_PLANE).last())}
            }
        }
        group.children.forEach {
            polygon -> (polygon as GkPolygon).refresh()
        }
    }


    private fun setListeners() {
        val movement = GkMovement(gkScene)
        scene.setOnKeyPressed {
            key ->
            onKeyPressed(key, movement)
            renderScene()
        }
        scene.setOnMouseMoved {
            moveAction ->
            onMouseMoved(moveAction, movement)
            renderScene()
        }
    }

    private fun onMouseMoved(moveAction: MouseEvent, movement: GkMovement) {
        if (lastMouseXPosition != null) {
            if (lastMouseXPosition!! - moveAction.x < 0)
                movement.rotateHorizontal(GkSettings.ROTATE)
            if (lastMouseXPosition!! - moveAction.x > 0)
                movement.rotateHorizontal(-GkSettings.ROTATE)
        }
        lastMouseXPosition = moveAction.x
        scene.cursor = Cursor.NONE
        if (isOutOfActionArea(moveAction))
            setMousePositionToCenter()
    }

    private fun isOutOfActionArea(moveAction: MouseEvent): Boolean {
        return ((moveAction.sceneX.toInt() < GkSettings.WINDOW_SIZE / 8) ||
                (moveAction.sceneX.toInt() > GkSettings.WINDOW_SIZE - GkSettings.WINDOW_SIZE / 8))
    }

    private fun setMousePositionToCenter() {
        val screenWidth = Screen.getPrimary().bounds.width.toInt()
        val screenHeight = Screen.getPrimary().bounds.height.toInt()
        Robot().mouseMove(screenWidth / 2, screenHeight / 2)
    }

    private fun onKeyPressed(key: KeyEvent, movement: GkMovement) {
        when (key.code) {
            KeyCode.W -> movement.stepForward()
            KeyCode.S -> movement.stepBack()
            KeyCode.A -> movement.stepLeft()
            KeyCode.D -> movement.stepRight()
            KeyCode.R -> movement.zoomIn()
            KeyCode.F -> movement.zoomOut()
            KeyCode.Q -> movement.rotateHorizontal(-GkSettings.ROTATE)
            KeyCode.E -> movement.rotateHorizontal(GkSettings.ROTATE)
            else -> {
            }
        }
    }

}


