package dev.jamiecraane.domain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import java.lang.Math.pow
import kotlin.math.pow

interface MathematicalFunction {
    fun evaluate(x: Float): Float

    @Composable
    fun drawConfigPane()
}

class Line(val a: Float = 0f) : MathematicalFunction {
    override fun evaluate(x: Float) = x + a

    @Composable
    override fun drawConfigPane() {
        Column() {
            Text(text = "Line (y = a + x)")
            Row() {
                Text(text = "a: ")

            }
        }
    }

}

/**
 * Simple line function.
 */


/**
 * Quadratic function in the form of: ax^2 + bx + c
 */
fun quadratic(x: Float, a: Float = 1f, b: Float = 0f, c: Float = 0f) = (a * x).pow(2f) + (b * x) + c
