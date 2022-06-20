package dev.jamiecraane.domain.mathfunctions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dev.jamiecraane.domain.FloatInput
import dev.jamiecraane.domain.SliderInput

class Quadratic() : MathematicalFunction {
    private var a: Float = 1f
    private var b: Float = 0f
    private var c: Float = 0f

    override fun evaluate(x: Float): Float = (a * x).pow(2f) + (b * x) + c

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        var useSlider by remember { mutableStateOf(false) }

        Column() {
            Text(text = "Quadratic (y = (a*x)^2 + b*x + c)")

            Row {
                Text(text = "Use slider: ")
                Checkbox(checked = useSlider, onCheckedChange = {
                    useSlider = it
                })
            }

            Spacer(modifier = Modifier.size(4.dp))

            Row() {
                Text(text = "a: ")

                if (useSlider) {
                    SliderInput(a) {
                        a = it
                        propertiesChanged()
                    }
                } else {
                    FloatInput(a) {
                        this@Quadratic.a = it
                        propertiesChanged()
                    }
                }
            }

            Row() {
                Text(text = "b: ")

                if (useSlider) {
                    SliderInput(b) {
                        b = it
                        propertiesChanged()
                    }
                } else {
                    FloatInput(b) {
                        this@Quadratic.b = it
                        propertiesChanged()
                    }
                }
            }

            Row() {
                Text(text = "c: ")

                if (useSlider) {
                    SliderInput(c) {
                        c = it
                        propertiesChanged()
                    }
                } else {
                    FloatInput(c) {
                        this@Quadratic.c = it
                        propertiesChanged()
                    }
                }
            }
        }
    }


}
