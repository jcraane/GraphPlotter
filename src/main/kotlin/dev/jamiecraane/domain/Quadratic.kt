package dev.jamiecraane.domain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.pow

class Quadratic : MathematicalFunction {
    private var a: Float = 1f
    private var b: Float = 0f
    private var c: Float = 0f

    override fun evaluate(x: Float): Float = (a * x).pow(2f) + (b * x) + c

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        Column() {
            Text(text = "Quadratic (y = (a*x)^2 + b*x + c)")

            Spacer(modifier = Modifier.size(4.dp))

            Row() {
                Text(text = "a: ")
                FloatInput(a) {
                    this@Quadratic.a = it
                    propertiesChanged()
                }
            }

            Row() {
                Text(text = "b: ")
                FloatInput(b) {
                    this@Quadratic.b = it
                    propertiesChanged()
                }
            }

            Row() {
                Text(text = "c: ")
                FloatInput(c) {
                    this@Quadratic.c = it
                    propertiesChanged()
                }
            }
        }
    }
}
