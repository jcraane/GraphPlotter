// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import dev.jamiecraane.domain.*
import dev.jamiecraane.domain.mathfunctions.*
import dev.jamiecraane.ui.graph.GraphPaper

@Composable
@Preview
fun App() {
    MaterialTheme {
        var coordinates by remember { mutableStateOf("-2,-2;0,2;2,-2;-2,-2") }
        var coordinateList: Coordinates by remember(coordinates) {
            mutableStateOf(Coordinates(coordinates))
        }
        var menuExpanded by remember { mutableStateOf(false) }
        var selectedFunctionIndex by remember { mutableStateOf(0) }
        val functions = remember { listOf(Line(), Quadratic(), Pow3(), SquareRoot(), Reciprocal(), Sine()) }

        Column(modifier = Modifier.padding(16.dp)) {
            Row() {
                Text(text = "Coordinaten: x,y;x,y")
                TextField(value = coordinates, onValueChange = {
                    coordinates = it
                })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row() {
                Column {
                    Box(modifier = Modifier.width(240.dp).wrapContentSize(Alignment.TopStart)) {
                        Text(
                            text = functions[selectedFunctionIndex].label,
                            modifier = Modifier.fillMaxWidth()
                                .clickable(onClick = {
                                    menuExpanded = true
                                })
                                .background(
                                    Color.Gray
                                )
                                .padding(8.dp)
                        )

                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }) {
                            functions.forEachIndexed { index, function ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedFunctionIndex = index
                                        menuExpanded = false
                                        coordinateList = Coordinates(generatePoints(functions[selectedFunctionIndex]))
                                    }
                                ) {
                                    Text(text = function.label)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    functions[selectedFunctionIndex].drawConfigPane {
                        coordinateList = Coordinates(generatePoints(functions[selectedFunctionIndex]))
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))
                GraphPaper(coordinates = coordinateList)
            }
        }
    }
}

/**
 * Generates a list of x (-5..5 (step 0.25) and y coordinates given the passed-in function.
 */
private fun generatePoints(function: MathematicalFunction) = generateSequence(-10f) { it + 0.25f }
    .takeWhile { it <= 10f }
    .map { x -> Coordinate2D(x, function.evaluate(x)) }
    .toList()

fun main() = application {
    Window(onCloseRequest = ::exitApplication, state = WindowState(size = DpSize(1000.dp, 800.dp))) {
        App()
    }
}
