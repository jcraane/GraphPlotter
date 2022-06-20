package dev.jamiecraane.domain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Line : MathematicalFunction {
    private var a: Float = 0f
    override fun evaluate(x: Float) = x + a

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        Column() {
            Text(text = "Line (y = a + x)")

            Spacer(modifier = Modifier.size(4.dp))

            Row() {
                Text(text = "a: ")
                FloatInput(a) {
                    println("CHANGED")
                    this@Line.a = it
                    propertiesChanged()
                }
            }
        }
    }
}
