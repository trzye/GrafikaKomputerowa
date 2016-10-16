package model

// x = a + b*t, zamiast x może być y lub z
data class GkOneDimensionLine(val a: Double, val b: Double) {
    fun calculatePoint(t: Double): Double = a + b * t
}