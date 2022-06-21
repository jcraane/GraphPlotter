package dev.jamiecraane.domain.mathfunctions

import androidx.compose.runtime.Composable
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Cosine : BaseSineCos() {
    override fun evaluate(x: Double): Double = amplitude * cos(((2 * PI) / period) * (x + phaseShift)) + verticalShift

    override val label: String
        get() = "Cosine"
}
