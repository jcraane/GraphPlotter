package dev.jamiecraane.domain.mathfunctions

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlin.math.E
import kotlin.math.pow

class Exponential : MathematicalFunction {
    override fun evaluate(x: Double): Double = E.pow(x)

    override val label: String
        get() = "Exponential"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        Text(text = "Exponential E^x")
    }
}
