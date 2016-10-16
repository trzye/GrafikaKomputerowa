package model

data class GkVector(val x: Double, val y: Double, val z: Double) {
    constructor(a: GkPoint, b: GkPoint) : this(a.x - b.x, a.y - b.y, a.z - b.z)

    fun multiplyBy(other: GkVector) = GkVector(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
    )
}

