package dev.jamiecraane.domain.mathfunctions

import androidx.compose.runtime.Composable
import kotlin.math.sin

class Sine : MathematicalFunction {
    override fun evaluate(x: Float): Float = sin(x)

    override val label: String
        get() = "Sine"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        // Nothing to configure
    }
}
