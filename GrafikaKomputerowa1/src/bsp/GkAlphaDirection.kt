package bsp

enum class GkAlphaDirection(val multiplier: Double) {
    FRONT(-1.0),
    LEFT(-1.0),
    FLOOR(-1.0),
    BACK(1.0),
    RIGHT(1.0),
    TOP(1.0)
}