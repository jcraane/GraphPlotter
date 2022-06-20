package dev.jamiecraane.domain

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Quadratic : MathematicalFunction {
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
                    Slider(modifier = Modifier.width(200.dp), value = (a + 10) / 20, steps = 20, onValueChange = {
                        a = (it * 20) - 10
                        propertiesChanged()
                    })
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
                    Slider(modifier = Modifier.width(200.dp), value = (b + 10) / 20, steps = 20, onValueChange = {
                        b = (it * 20) - 10
                        propertiesChanged()
                    })
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
                    Slider(modifier = Modifier.width(200.dp), value = (c + 10) / 20, steps = 20, onValueChange = {
                        c = (it * 20) - 10
                        propertiesChanged()
                    })
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
