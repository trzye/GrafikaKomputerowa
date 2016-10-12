package model

import controller.Settings

data class GkPoint(var x : Double, var y : Double, var z : Double, var rotHz : Double? = null, var rotLz : Double? = null){

    constructor(x : Int, y : Int, z : Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    var x2d = perspectiveX()

    var y2d = perspectiveY()

    private fun perspectiveX()  = ( x * Settings.PERSPECTIVE_DISTANCE) / ( z + Settings.PERSPECTIVE_DISTANCE) * Settings.SCALE
    private fun perspectiveY()  = ( y * Settings.PERSPECTIVE_DISTANCE) / ( z + Settings.PERSPECTIVE_DISTANCE) * Settings.SCALE

    fun refresh() : GkPoint{
        x2d = perspectiveX()
        y2d = perspectiveY()
        return this
    }

}