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
