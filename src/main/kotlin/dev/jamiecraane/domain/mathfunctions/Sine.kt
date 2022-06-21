package dev.jamiecraane.domain.mathfunctions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jamiecraane.domain.FloatInput
import dev.jamiecraane.domain.SliderInput
import kotlin.math.PI
import kotlin.math.sin

class Sine : BaseSineCos() {
    override fun evaluate(x: Double): Double = amplitude * sin(((2 * PI) / period) * (x + phaseShift)) + verticalShift

    override val label: String
        get() = "Sine"
}
