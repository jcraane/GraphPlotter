package dev.jamiecraane.domain.mathfunctions

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class Reciprocal : MathematicalFunction {
    override fun evaluate(x: Double): Double = 1/x

    override val label: String
        get() = "Reciprocal"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        Text(text = "$label 1/x")
    }
}
