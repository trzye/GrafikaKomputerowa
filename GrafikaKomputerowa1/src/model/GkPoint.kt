package model

import controller.Settings

data class GkPoint(var x : Double, var y : Double, var z : Double){

    constructor(x : Int, y : Int, z : Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    val niezmienna : String? = "costam"

    fun perspectiveX()  = ( x * Settings.PERSPECTIVE_DISTANCE) / ( z + Settings.PERSPECTIVE_DISTANCE) * Settings.SCALE
    fun perspectiveY()  = ( y * Settings.PERSPECTIVE_DISTANCE) / ( z + Settings.PERSPECTIVE_DISTANCE) * Settings.SCALE

}