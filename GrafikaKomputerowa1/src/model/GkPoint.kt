package model

import controller.GkSettings

data class GkPoint(var x : Double, var y : Double, var z : Double){

    constructor(x : Int, y : Int, z : Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    var x2d = perspectiveX()

    var y2d = perspectiveY()

    private fun perspectiveX()  = ( x * GkSettings.PERSPECTIVE_DISTANCE) / ( z + GkSettings.PERSPECTIVE_DISTANCE) * GkSettings.scale
    private fun perspectiveY()  = ( y * GkSettings.PERSPECTIVE_DISTANCE) / ( z + GkSettings.PERSPECTIVE_DISTANCE) * GkSettings.scale

    fun refresh() : GkPoint{
        x2d = perspectiveX()
        y2d = perspectiveY()
        return this
    }

    fun positionByPlane(plane : GkPlane) : GkPosition {
        val res = plane.A * x + plane.B * y + plane.C * z + plane.D
        if(res > 0)
            return GkPosition.BEHIND_PLANE
        if(res < 0)
            return GkPosition.BEFORE_PLANE
        return GkPosition.ON_PLANE
    }
}

