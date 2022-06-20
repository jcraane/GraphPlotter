package dev.jamiecraane.domain

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun FloatInput(initialValue: Float = 0f, valueChanged: (Float) -> Unit) {
    var value by remember { mutableStateOf(initialValue.toString()) }

    TextField(value = value, onValueChange = {
        value = it

        try {
            valueChanged(it.toFloat())
        } catch (e: Exception) {
            // Invalid input, do nothing
        }
    })
}
