package dev.jamiecraane.domain.mathfunctions

import androidx.compose.runtime.Composable
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class Tangent : MathematicalFunction {
    override fun evaluate(x: Double): Double = tan(x)

    override val label: String
        get() = "Tangent"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        // Nothing to configure
    }
}
