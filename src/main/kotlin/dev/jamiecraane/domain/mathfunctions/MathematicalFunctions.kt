package dev.jamiecraane.domain.mathfunctions

import androidx.compose.runtime.Composable
import kotlin.math.pow

//todo add combo picker to select a function. Add all of the following functions: https://www.mathsisfun.com/sets/functions-common.html
interface MathematicalFunction {
    fun evaluate(x: Float): Float

    val label: String

    /**
     * @param propertiesChanged Called if the internal properties of the function changed.
     */
    @Composable
    fun drawConfigPane(propertiesChanged: () -> Unit)
}
