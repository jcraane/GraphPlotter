package dev.jamiecraane.domain.mathfunctions

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class Reciprocal : MathematicalFunction {
    override fun evaluate(x: Float): Float = 1/x

    override val label: String
        get() = "Reciprocal"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        // Nothing to configure
    }
}
