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

class Pow3 : MathematicalFunction {
    private var a: Float = 1f

    override fun evaluate(x: Float): Float = (x).pow(3) - (a * x)

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        var useSlider by remember { mutableStateOf(false) }

        Column() {
            Text(text = "Quadratic (y = (x)^3 - (a*x)")

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
                        this@Pow3.a = it
                        propertiesChanged()
                    }
                }
            }
        }
    }
}
