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

class Sine : MathematicalFunction {
    private var amplitude: Float = 1f
    private var period: Float = 5f
    private var phaseShift: Float = 0f
    private var verticalShift: Float = 0f
    override fun evaluate(x: Double): Double = amplitude * sin(((2 * PI) / period) * (x + phaseShift)) + verticalShift

    override val label: String
        get() = "Sine"

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        var useSlider by remember { mutableStateOf(false) }

        Column() {
            Text(text = "Sine (y = A sin(B(x + C)) + D) (B = 2π/B")

            Row {
                Text(text = "Use slider: ")
                Checkbox(checked = useSlider, onCheckedChange = {
                    useSlider = it
                })
            }

            Spacer(modifier = Modifier.size(4.dp))

            Row() {
                Text(text = "amplitude (A): ")

                if (useSlider) {
                    SliderInput(amplitude) {
                        amplitude = it
                        propertiesChanged()
                    }
                } else {
                    FloatInput(amplitude) {
                        this@Sine.amplitude = it
                        propertiesChanged()
                    }
                }
            }

            Row() {
                Text(text = "period (B): ")

                if (useSlider) {
                    SliderInput(period) {
                        period = it
                        propertiesChanged()
                    }
                } else {
                    FloatInput(period) {
                        this@Sine.period = it
                        propertiesChanged()
                    }
                }
            }

            Row() {
                Text(text = "phaseShift (C): ")

                if (useSlider) {
                    SliderInput(phaseShift) {
                        phaseShift = it
                        propertiesChanged()
                    }
                } else {
                    FloatInput(phaseShift) {
                        this@Sine.phaseShift = it
                        propertiesChanged()
                    }
                }
            }

            Row() {
                Text(text = "verticalShift (D): ")

                if (useSlider) {
                    SliderInput(verticalShift) {
                        verticalShift = it
                        propertiesChanged()
                    }
                } else {
                    FloatInput(verticalShift) {
                        this@Sine.verticalShift = it
                        propertiesChanged()
                    }
                }
            }
        }

    }
}
