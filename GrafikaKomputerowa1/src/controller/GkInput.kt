package controller

import javafx.scene.paint.Color
import model.GkCube
import model.GkPoint
import model.GkScene
import model.GkSquare

object GkInput {

    private val gkCube1 = GkCube(
            squareA =
            GkSquare(
                    downLeftPoint = GkPoint(x = -100, y = -300, z = 200),
                    downRightPoint = GkPoint(x = 100, y = -300, z = 200),
                    upperLeftPoint = GkPoint(x = -100, y = -100, z = 200),
                    upperRightPoint = GkPoint(x = 100, y = -100, z = 200)
            ),
            squareB =
            GkSquare(
                    downLeftPoint = GkPoint(x = -100, y = -300, z = 400),
                    downRightPoint = GkPoint(x = 100, y = -300, z = 400),
                    upperLeftPoint = GkPoint(x = -100, y = -100, z = 400),
                    upperRightPoint = GkPoint(x = 100, y = -100, z = 400)
            ),
            color = Color.TRANSPARENT
    )
    private val gkCube2 = GkCube(
            squareA =
            GkSquare(
                    downLeftPoint = GkPoint(x = 200, y = -300, z = 300),
                    downRightPoint = GkPoint(x = 300, y = -300, z = 300),
                    upperLeftPoint = GkPoint(x = 200, y = -200, z = 300),
                    upperRightPoint = GkPoint(x = 300, y = -200, z = 300)
            ),
            squareB =
            GkSquare(
                    downLeftPoint = GkPoint(x = 200, y = -300, z = 500),
                    downRightPoint = GkPoint(x = 300, y = -300, z = 500),
                    upperLeftPoint = GkPoint(x = 200, y = -200, z = 500),
                    upperRightPoint = GkPoint(x = 300, y = -200, z = 500)
            ),
            color = Color.TRANSPARENT
    )

    val gkScene = GkScene(setOf(gkCube1, gkCube2))
}