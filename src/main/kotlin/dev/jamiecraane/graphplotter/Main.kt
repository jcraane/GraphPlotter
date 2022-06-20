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
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import dev.jamiecraane.extensions.toScreenX
import dev.jamiecraane.extensions.toScreenY
import org.jetbrains.skia.Font
import org.jetbrains.skia.Typeface

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
    coordinates: List<Pair<Int, Int>>,
) {
    println(coordinates)
    val gridColor = Color(0xFFBDBDBD)

    Canvas(modifier = modifier.size(600.dp).background(Color(0xFFFFF4D5))) {
        val gridSize = this.size.width / numberOfBoxes
        val midX = this.size.width / 2
        val midY = this.size.height / 2

        gridLines(midX, midY, numberOfBoxes, gridColor)

        coordinates.forEachIndexed { index, xy ->
            drawCircle(Color.Blue, 5f, Offset(xy.first.toScreenX(midX, gridSize), xy.second.toScreenY(midY, gridSize)))
            if (index > 0) {
                val prevXy = coordinates[index - 1]
                drawLine(
                    Color.Blue, Offset(prevXy.first.toScreenX(midX, gridSize), prevXy.second.toScreenY(midY, gridSize)), Offset(
                        xy.first
                            .toScreenX(midX, gridSize), xy.second.toScreenY(midY, gridSize)
                    )
                )
            }
        }
    }
}

private fun DrawScope.gridLines(
    midX: Float,
    midY: Float,
    numberOfBoxes: Int,
    gridColor: Color,
) {
    val halfNumberOfboxes = numberOfBoxes / 2
    val labels = (0..numberOfBoxes).toList().map { it - halfNumberOfboxes }
    // vertical lines
    val verticalSpace = (midX / (halfNumberOfboxes)).roundToInt()
    var indexX = 0
    for (lineX in 0..this.size.width.toInt() step verticalSpace) {
        val isMiddle = lineX == midX.toInt()
        val strokeWidth = if (isMiddle) {
            4f
        } else {
            Stroke.HairlineWidth
        }

        drawLine(gridColor, start = Offset(lineX.toFloat(), 0f), end = Offset(lineX.toFloat(), this.size.height), strokeWidth)


        this.drawIntoCanvas {
            it.nativeCanvas.drawString(
                "${labels[indexX]},0", lineX.toFloat() - 18, midY + 30, Font(Typeface.makeDefault(), 24f),
                NativePaint()
            )
        }

        indexX++
    }

    // horizontal lines
    var indexY = 0
    val horizontalSpace = ((midY / (halfNumberOfboxes))).roundToInt()
    for (lineY in 0..this.size.width.toInt() step horizontalSpace) {
        val isMiddle = lineY == midX.toInt()
        val strokeWidth = if (isMiddle) {
            4f
        } else {
            Stroke.HairlineWidth
        }
        drawLine(gridColor, start = Offset(0f, lineY.toFloat()), end = Offset(this.size.width, lineY.toFloat()), strokeWidth)

        this.drawIntoCanvas {
            if (isMiddle.not()) {
                it.nativeCanvas.drawString(
                    "0,${labels[indexY]}", midX - 18, lineY.toFloat() + 22, Font(Typeface.makeDefault(), 24f),
                    NativePaint()
                )
            }
        }

        indexY++
    }
}

private fun line(x: Int) = x
private fun parabool(x: Int) = x * x

fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(size = DpSize(800.dp, 800.dp))) {
        App()
    }
}
