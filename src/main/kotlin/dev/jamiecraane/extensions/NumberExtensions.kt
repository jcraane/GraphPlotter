package dev.jamiecraane.extensions

fun Int.toScreenX(midPoint: Float, gridSize: Float) = midPoint + (this * gridSize)
fun Float.toScreenX(midPoint: Float, gridSize: Float) = midPoint + (this * gridSize)
fun Double.toScreenX(midPoint: Float, gridSize: Float) = (midPoint + (this * gridSize)).toFloat()
fun Int.toScreenY(midPoint: Float, gridSize: Float) = midPoint - (this * gridSize)
fun Float.toScreenY(midPoint: Float, gridSize: Float) = midPoint - (this * gridSize)
fun Double.toScreenY(midPoint: Float, gridSize: Float) = (midPoint - (this * gridSize)).toFloat()
