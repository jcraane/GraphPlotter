package dev.jamiecraane.ui.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import dev.jamiecraane.domain.Coordinates
import dev.jamiecraane.extensions.toScreenX
import dev.jamiecraane.extensions.toScreenY
import dev.jamiecraane.ui.theme.gridLine
import dev.jamiecraane.ui.theme.paper
import org.jetbrains.skia.Font
import org.jetbrains.skia.Typeface
import kotlin.math.roundToInt

/**
 * @param drawPoints Draw dots in the exact coordinates.
 */
@Composable
fun GraphPaper(
    modifier: Modifier = Modifier,
    numberOfBoxes: Int = 20,
    coordinates: Coordinates,
    drawPoints: Boolean = true,
) {
    val gridColor = gridLine

    Canvas(modifier = modifier.size(600.dp).background(paper)) {
        val gridSize = this.size.width / numberOfBoxes
        val midX = this.size.width / 2
        val midY = this.size.height / 2

        gridLines(midX, midY, numberOfBoxes, gridColor)

        coordinates.forEachIndexed { index, xy ->
            if (drawPoints) {
                drawCircle(Color.Blue, 5f, Offset(xy.x.toScreenX(midX, gridSize), xy.y.toScreenY(midY, gridSize)))
            }

            if (index > 0) {
                val prevXy = coordinates.coordinates[index - 1]
                drawLine(
                    color = Color.Blue, start = Offset(prevXy.x.toScreenX(midX, gridSize), prevXy.y.toScreenY(midY, gridSize)), end = Offset(
                        xy.x.toScreenX(midX, gridSize), xy.y.toScreenY(midY, gridSize)), strokeWidth = 2f
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
    val labelsX = (0..numberOfBoxes).toList().map { it - halfNumberOfboxes }
    val labelsY = labelsX.reversed()

    // vertical lines
    val verticalSpace = (midX / (halfNumberOfboxes)).roundToInt()
    for ((indexX, lineX) in (0..this.size.width.toInt() step verticalSpace).withIndex()) {
        val isMiddle = lineX == midX.toInt()
        val strokeWidth = getStrokeWidth(isMiddle)

        drawLine(gridColor, start = Offset(lineX.toFloat(), 0f), end = Offset(lineX.toFloat(), this.size.height), strokeWidth)

        this.drawIntoCanvas {
            it.nativeCanvas.drawString(
                "${labelsX[indexX]}", lineX.toFloat() + 6, midY + 30, Font(Typeface.makeDefault(), 24f),
                NativePaint()
            )
        }

    }

    // horizontal lines
    val horizontalSpace = ((midY / (halfNumberOfboxes))).roundToInt()
    for ((indexY, lineY) in (0..this.size.width.toInt() step horizontalSpace).withIndex()) {
        val isMiddle = lineY == midX.toInt()
        val strokeWidth = getStrokeWidth(isMiddle)
        drawLine(gridColor, start = Offset(0f, lineY.toFloat()), end = Offset(this.size.width, lineY.toFloat()), strokeWidth)

        this.drawIntoCanvas {
            if (isMiddle.not()) {
                it.nativeCanvas.drawString(
                    "${labelsY[indexY]}", midX + 8, lineY.toFloat() + 22, Font(Typeface.makeDefault(), 24f),
                    NativePaint()
                )
            }
        }

    }
}

private fun getStrokeWidth(isMiddle: Boolean): Float = if (isMiddle) {
    4f
} else {
    Stroke.HairlineWidth
}
