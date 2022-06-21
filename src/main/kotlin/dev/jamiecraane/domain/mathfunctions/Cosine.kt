package dev.jamiecraane.domain.mathfunctions

import androidx.compose.runtime.Composable
import kotlin.math.cos
import kotlin.math.sin

class Cosine : MathematicalFunction {
    override fun evaluate(x: Double): Double = cos(x)

    override val label: String
        get() = "Cosine"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        // Nothing to configure
    }
}
