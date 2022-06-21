package dev.jamiecraane.domain

/**
 * Represents a logica 2D coordinate in a grid independent.
 */
data class Coordinate2D(val x: Double, val y: Double)

data class Coordinates(val coordinates: List<Coordinate2D>) : List<Coordinate2D> by coordinates {
    companion object {
        /**
         * Parse a list of coordinates in the form of x,y;x,y;x;y to CoordinateList
         */
        operator fun invoke(coordinates: String): Coordinates {
            val coordinateList = mutableListOf<Coordinate2D>()

            if (coordinates.isNotBlank()) {
                try {
                    coordinates.split(";")
                        .map { coordinate ->
                            val xy = coordinate.split(",")
                            if (xy.size == 2) {
                                coordinateList.add(Coordinate2D(xy.first().toDouble(), xy.last().toDouble()))
                            }
                        }
                } catch (e: Exception) {
                    // Ignore
                }
            }

            return Coordinates(coordinateList)
        }
    }
}
