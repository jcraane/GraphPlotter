package dev.jamiecraane.domain.mathfunctions

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlin.math.ln

class Logarithmic : MathematicalFunction {
    override fun evaluate(x: Double): Double = ln(x)

    override val label: String
        get() = "Logarithmic"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        Text(text = "Logarithmic log(x)")
    }
}
