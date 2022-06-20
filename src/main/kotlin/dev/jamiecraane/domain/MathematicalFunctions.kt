package dev.jamiecraane.domain

import androidx.compose.runtime.Composable
import kotlin.math.pow

interface MathematicalFunction {
    fun evaluate(x: Float): Float

    /**
     * @param propertiesChanged Called if the internal properties of the function changed.
     */
    @Composable
    fun drawConfigPane(propertiesChanged: () -> Unit)
}

/**
 * Simple line function.
 */


/**
 * Quadratic function in the form of: ax^2 + bx + c
 */
fun quadratic(x: Float, a: Float = 1f, b: Float = 0f, c: Float = 0f) = (a * x).pow(2f) + (b * x) + c
