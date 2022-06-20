package dev.jamiecraane.domain

import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SliderInput(
    value: Float,
    valueChanged: (Float) -> Unit,
) {
    val numberOfSteps = 20
    val halfNumberOfSteps = remember { numberOfSteps / 2 }

    Slider(modifier = Modifier.width(200.dp), value = (value + halfNumberOfSteps) / numberOfSteps, steps = numberOfSteps, onValueChange = {
        val newValue = (it * numberOfSteps) - halfNumberOfSteps
        valueChanged(newValue)
    })
}
