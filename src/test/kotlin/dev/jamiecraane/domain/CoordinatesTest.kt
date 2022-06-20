package dev.jamiecraane.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CoordinatesTest {
    @Test
    fun parseCoordinates() {
        Assertions.assertEquals(
            createCoordinates(Coordinate2D(0f, 0f), Coordinate2D(1f, 1f)), Coordinates("0,0;1,1")
        )
        Assertions.assertEquals(
            createCoordinates(Coordinate2D(0f, 0f), Coordinate2D(1f, 1f)), Coordinates("0,0;1,1;")
        )
        Assertions.assertEquals(
            createCoordinates(Coordinate2D(0f, 0f), Coordinate2D(1f, 1f)), Coordinates("0,0;1,1;2")
        )
        Assertions.assertEquals(
            createCoordinates(Coordinate2D(0f, 0f), Coordinate2D(1f, 1f)), Coordinates("0,0;1,1;2,")
        )
        Assertions.assertEquals(
            createCoordinates(Coordinate2D(0f, 0f), Coordinate2D(1f, 1f), Coordinate2D(2f, 2f)), Coordinates("0,0;1,1;2,2")
        )
    }

    private fun createCoordinates(vararg coordinate2D: Coordinate2D) = Coordinates(coordinate2D.toList())
}
