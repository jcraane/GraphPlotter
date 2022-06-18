// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.VectorProperty
import dev.jamiecraane.extensions.toScreenX
import dev.jamiecraane.extensions.toScreenY

@Composable
@Preview
fun App() {
    MaterialTheme {
        var coordinates by remember { mutableStateOf("-2,-2;0,2;2,-2;-2,-2") }
        val coordinateList: List<Pair<Int, Int>> by remember(coordinates) {
            val list = if (coordinates.isNotBlank()) {
                try {
                    coordinates.split(";")
                        .map { coordinate ->
                            val xy = coordinate.split(",")
                            xy.first().toInt() to xy.last().toInt()
                        }
                } catch (e: Exception) {
                    emptyList()
                }
            } else {
                emptyList()
            }

            mutableStateOf(list)
        }

        Column() {
            Row() {
                Text(text = "Coordinaten: x,y;x,y")
                TextField(value = coordinates, onValueChange = {
                    coordinates = it
                })
            }

            Spacer(modifier = Modifier.height(16.dp))


            GraphPaper(coordinates = coordinateList)
        }
    }
}

@Composable
fun GraphPaper(
    modifier: Modifier = Modifier,
    numberOfBoxes: Int = 20,
    coordinates: List<Pair<Int, Int>>
) {
    println(coordinates)
    val gridColor = Color(0xFFBDBDBD)
    val halfNumberOfboxes = numberOfBoxes / 2

    Canvas(modifier = modifier.size(600.dp).background(Color(0xFFFFF4D5))) {
        val gridSize = this.size.width / numberOfBoxes
        val midX = this.size.width / 2
        val midY = this.size.height / 2

        gridLines(midX, midY, halfNumberOfboxes, gridColor)

        coordinates.forEachIndexed { index, xy ->
            drawCircle(Color.Blue, 5f, Offset(xy.first.toScreenX(midX, gridSize), xy.second.toScreenY(midY, gridSize)))
            if (index > 0) {
                val prevXy = coordinates[index - 1]
                drawLine(Color.Blue, Offset(prevXy.first.toScreenX(midX, gridSize), prevXy.second.toScreenY(midY, gridSize)), Offset(xy.first
                    .toScreenX(midX, gridSize), xy.second.toScreenY(midY, gridSize)))
            }
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
        val strokeWidth = if (lineX == midX.toInt()) {
            4f
        } else {
            Stroke.HairlineWidth
        }
        drawLine(gridColor, start = Offset(lineX.toFloat(), 0f), end = Offset(lineX.toFloat(), this.size.height), strokeWidth)
    }

    // horizontal lines
    val horizontalSpace = ((midY / (halfNumberOfboxes))).roundToInt()
    for (lineY in 0..this.size.width.toInt() step horizontalSpace) {
        val strokeWidth = if (lineY == midX.toInt()) {
            4f
        } else {
            Stroke.HairlineWidth
        }
        drawLine(gridColor, start = Offset(0f, lineY.toFloat()), end = Offset(this.size.width, lineY.toFloat()), strokeWidth)
    }
}

private fun line(x: Int) = x
private fun parabool(x: Int) = x * x

fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(size = DpSize(800.dp, 800.dp))) {
        App()
    }
}
