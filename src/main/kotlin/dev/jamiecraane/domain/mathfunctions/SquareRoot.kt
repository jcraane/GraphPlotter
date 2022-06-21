package dev.jamiecraane.domain.mathfunctions

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlin.math.sqrt

class SquareRoot : MathematicalFunction {
    override fun evaluate(x: Float): Float = sqrt(x)

    override val label: String
        get() = "Square Root"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        // Nothing to configure
    }
}
