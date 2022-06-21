package dev.jamiecraane.domain.mathfunctions

import androidx.compose.runtime.Composable
import kotlin.math.abs

class Absolute : MathematicalFunction {
    override fun evaluate(x: Double): Double = abs(x)

    override val label: String
        get() = "Absolute"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        // Nothing to configure
    }
}
