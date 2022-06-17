// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World2!") }
    val canvasSize = 600.dp

    MaterialTheme {
        Canvas(modifier = Modifier.size(600.dp).background(Color(0xFFDDDDDD))) {
            val gridSize = this.size.width / 20
            val midPoint = this.size.width / 2

            for (i in -10..10) {
                drawCircle(Color.Blue, 5f, Offset(i.toScreenX(midPoint, gridSize), parabool(i).toScreenY(midPoint, gridSize)))
                drawCircle(Color.Red, 5f, Offset(i.toScreenX(midPoint, gridSize), line(i).toScreenY(midPoint, gridSize)))
            }


//            drawCircle(Color.Blue, 5f, Offset(midPoint.toFloat(), midPoint.toFloat()))
//            drawCircle(Color.Blue, 2f, Offset(100f, 100f))
//            drawLine(Color.Blue, Offset(0.0f, 0.0f), Offset(100f, 100f))
        }

        /*Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }*/
    }
}

private fun Int.toScreenX(midPoint: Float, gridSize: Float) = midPoint + (this * gridSize)
private fun Int.toScreenY(midPoint: Float, gridSize: Float) = midPoint - (this * gridSize)
private fun line(x: Int) = x
private fun parabool(x: Int) = x * x

fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(size = DpSize(800.dp, 800.dp))) {
        App()
    }
}
