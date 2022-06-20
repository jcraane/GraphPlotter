package dev.jamiecraane.domain

import java.lang.Math.pow
import kotlin.math.pow

/**
 * Simple line function.
 */
fun line(x: Float, a: Float = 0f) = x + a

/**
 * Quadratic function in the form of: ax^2 + bx + c
 */
fun quadratic(x: Float, a: Float = 1f, b: Float = 0f, c: Float = 0f) = (a * x).pow(2f) + (b * x) + c
