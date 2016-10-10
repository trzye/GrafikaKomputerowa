package model

import controller.Settings

data class GkPoint(var x : Double, var y : Double, var z : Double){

    constructor(x : Int, y : Int, z : Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    fun perspectiveX()  = ( x * Settings.DISTANCE ) / ( z + Settings.DISTANCE )
    fun perspectiveY()  = ( y * Settings.DISTANCE ) / ( z + Settings.DISTANCE )

}