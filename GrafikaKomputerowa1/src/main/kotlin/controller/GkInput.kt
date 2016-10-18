package controller

import bsp.GkAlphaDirection
import javafx.scene.paint.Color
import model.GkCube
import model.GkPoint
import model.GkScene
import model.GkSquare

fun createCube(Ax : Int, Az : Int, Bx : Int, Bz : Int, Cx : Int, Cz : Int, Dx : Int, Dz : Int, H : Int) : GkCube{
    val alpha: Int = - GkSettings.WINDOW_SIZE / 3
    return GkCube(
            squareA =
            GkSquare(
                    downLeftPoint = GkPoint(x = Ax, y = 0 + alpha, z = Az),
                    downRightPoint = GkPoint(x = Bx, y = 0 + alpha, z = Bz),
                    upperLeftPoint = GkPoint(x = Ax, y = H + alpha, z = Az),
                    upperRightPoint = GkPoint(x = Bx, y = H + alpha, z = Bz),
                    alphaDirection = GkAlphaDirection.FRONT,
                    fillColor = Color.RED
            ),
            squareB =
            GkSquare(
                    downLeftPoint = GkPoint(x = Cx, y = 0 + alpha, z = Cz),
                    downRightPoint = GkPoint(x = Dx, y = 0 + alpha, z = Dz),
                    upperLeftPoint = GkPoint(x = Cx, y = H + alpha, z = Cz),
                    upperRightPoint = GkPoint(x = Dx, y = H + alpha, z = Dz),
                    alphaDirection = GkAlphaDirection.BACK,
                    fillColor = Color.ORANGE
            ),
            color = Color.TRANSPARENT
    )
}

object GkInput {

    private val gkCube1 = createCube(100, 125, 300, 125, 100, 200, 300, 200, 100)
    private val gkCube2 = createCube(100, 450, 300, 350, 200, 600, 400, 500, 200)
    private val gkCube3 = createCube(-100, 650, 100, 650, -100, 450, 100, 450, 300)
    private val gkCube4 = createCube(-275, 300, -200, 250, -175, 500, -100, 450, 200)

    val gkScene = GkScene(setOf(gkCube1, gkCube2, gkCube3, gkCube4))
}