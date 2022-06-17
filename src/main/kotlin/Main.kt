// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kotlin.math.roundToInt

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World2!") }
    val canvasSize = 600.dp

    MaterialTheme {
        GraphPaper()
    }
}

@Composable
fun GraphPaper(
    modifier: Modifier = Modifier,
    numberOfBoxes: Int = 20,
) {
    val gridColor = Color(0xFFBDBDBD)
    val halfNumberOfboxes = numberOfBoxes / 2

    Canvas(modifier = modifier.size(600.dp).background(Color(0xFFFFF4D5))) {
        val gridSize = this.size.width / numberOfBoxes
        val midX = this.size.width / 2
        val midY = this.size.height / 2

        gridLines(midX, midY, halfNumberOfboxes, gridColor)

        for (i in -10..10) {
            drawCircle(Color.Blue, 5f, Offset(i.toScreenX(midX, gridSize), parabool(i).toScreenY(midY, gridSize)))
            drawCircle(Color.Red, 5f, Offset(i.toScreenX(midX, gridSize), line(i).toScreenY(midY, gridSize)))
        }
    }
}

private fun DrawScope.gridLines(
    midX: Float,
    midY: Float,
    halfNumberOfboxes: Int,
    gridColor: Color,
) {
    // vertical lines
    val verticalSpace = (midX / (halfNumberOfboxes)).roundToInt()
    for (lineX in 0..this.size.width.toInt() step verticalSpace) {
        drawLine(gridColor, start = Offset(lineX.toFloat(), 0f), end = Offset(lineX.toFloat(), this.size.height))
    }

    // horizontal lines
    val horizontalSpace = ((midY / (halfNumberOfboxes))).roundToInt()
    for (lineY in 0..this.size.width.toInt() step horizontalSpace) {
        drawLine(gridColor, start = Offset(0f, lineY.toFloat()), end = Offset(this.size.width, lineY.toFloat()))
    }
}

private fun Int.toScreenX(midPoint: Float, gridSize: Float) = midPoint + (this * gridSize)
private fun Float.toScreenX(midPoint: Float, gridSize: Float) = midPoint + (this * gridSize)
private fun Int.toScreenY(midPoint: Float, gridSize: Float) = midPoint - (this * gridSize)
private fun Float.toScreenY(midPoint: Float, gridSize: Float) = midPoint - (this * gridSize)
private fun line(x: Int) = x
private fun parabool(x: Int) = x * x

fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(size = DpSize(800.dp, 800.dp))) {
        App()
    }
}
