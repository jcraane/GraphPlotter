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

abstract class BaseSineCos : MathematicalFunction {
    protected var amplitude: Float = 1f
        private set
    protected var period: Float = 5f
        private set
    protected var phaseShift: Float = 0f
        private set
    protected var verticalShift: Float = 0f
        private set

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
                        this@BaseSineCos.amplitude = it
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
                        this@BaseSineCos.period = it
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
                        this@BaseSineCos.phaseShift = it
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
                        this@BaseSineCos.verticalShift = it
                        propertiesChanged()
                    }
                }
            }
        }

    }

}
