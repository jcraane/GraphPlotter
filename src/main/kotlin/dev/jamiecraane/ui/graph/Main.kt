// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dev.jamiecraane.domain.*
import dev.jamiecraane.ui.graph.GraphPaper

@Composable
@Preview
fun App() {
    MaterialTheme {
        var coordinates by remember { mutableStateOf("-2,-2;0,2;2,-2;-2,-2") }
        val coordinateList: Coordinates by remember(coordinates) {
            mutableStateOf(Coordinates(coordinates))
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Row() {
                Text(text = "Coordinaten: x,y;x,y")
                TextField(value = coordinates, onValueChange = {
                    coordinates = it
                })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row() {
                GraphPaper(coordinates = coordinateList)

                Spacer(modifier = Modifier.width(12.dp))

                val line = Line()
                line.drawConfigPane()
            }
//            GraphPaper(coordinates = coordinateList)
            /*val coor = generateSequence(-5f) { it + 0.25f }
                .takeWhile { it <= 5f }
                .map { x -> Coordinate2D(x, line(x)) }
                .toList()*/

           /* val coor = generateSequence(-5f) { it + 0.25f }
                .takeWhile { it <= 5f }
                .map { x -> Coordinate2D(x, quadratic(x = x, a = 0.5f, b = 0f, c = -5f)) }
                .toList()*/

//            GraphPaper(coordinates = coordinateList)

//            GraphPaper(coordinates = Coordinates(coor), drawPoints = false)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(size = DpSize(1000.dp, 800.dp))) {
        App()
    }
}
