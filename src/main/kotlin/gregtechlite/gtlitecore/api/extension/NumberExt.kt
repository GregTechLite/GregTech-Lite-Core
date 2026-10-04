package gregtechlite.gtlitecore.api.extension

import java.math.BigInteger
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.floor

fun Int.square(): Int = this * this

fun BigInteger.longValue(): Long = min(BigInteger.valueOf(Long.MAX_VALUE)).longValueExact()

fun BigInteger.formatting(): String
{
    // CEu why you not impl it in SmallDigits? :(
    val superscripts = mapOf('0' to '⁰', '1' to '¹', '2' to '²', '3' to '³', '4' to '⁴', '5' to '⁵', '6' to '⁶',
                             '7' to '⁷', '8' to '⁸', '9' to '⁹', '-' to '⁻', '+' to '⁺')

    fun Int.toSuperscript(): String = toString().map { superscripts[it] ?: it }.joinToString("")

    if (signum() == 0) return "0"

    val decimal = toBigDecimal()
    val exp = decimal.precision() - decimal.scale() - 1

    return "10${exp.toSuperscript()}"
}

/**
 * Triangle wave in `[0, 1]` for `x` already reduced into `[0, 1]`.
 */
val Float.triangle: Float
    get() = 1f - abs(2f * (this - 0.5f))

fun Float.fract(): Float = this - floor(this)

fun Double.toRadians(): Double = this / 180.0 * PI