package dev.jamiecraane.domain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.lang.Math.pow
import kotlin.math.pow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

interface MathematicalFunction {
    fun evaluate(x: Float): Float

    /**
     * @param propertiesChanged Called if the internal properties of the function changed.
     */
    @Composable
    fun drawConfigPane(propertiesChanged: () -> Unit)
}

class Line() : MathematicalFunction {
    private var a: Float = 0f
    override fun evaluate(x: Float) = x + a

    @Composable
    override fun drawConfigPane(propertiesChanged: () -> Unit) {
        var a by remember { mutableStateOf("0") }

        Column() {
            Text(text = "Line (y = a + x)")
            Spacer(modifier = Modifier.size(4.dp))
            Row() {
                Text(text = "a: ")
                TextField(value = a, onValueChange = {
                    a = it
                    val converted = try {
                        it.toFloat()
                    } catch (e: Exception) {
                        0F
                    }

                    println("Changed")
                    this@Line.a = converted
                    propertiesChanged()
                })
            }
        }
    }
}

/**
 * Simple line function.
 */


/**
 * Quadratic function in the form of: ax^2 + bx + c
 */
fun quadratic(x: Float, a: Float = 1f, b: Float = 0f, c: Float = 0f) = (a * x).pow(2f) + (b * x) + c
