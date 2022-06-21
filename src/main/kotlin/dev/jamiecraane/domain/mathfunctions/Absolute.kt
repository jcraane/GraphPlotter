package dev.jamiecraane.domain.mathfunctions

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlin.math.abs

class Absolute : MathematicalFunction {
    override fun evaluate(x: Double): Double = abs(x)

    override val label: String
        get() = "Absolute value"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        Text(text = "$label |x|")
    }
}
