package gregtechlite.gtlitecore.api.extension

import gregtech.api.unification.material.Material
import kotlin.math.pow

/**
 * Get average RGB color without components of materials.
 */
fun Material.Builder.colorAverage(vararg inputs: Int): Material.Builder
{
    var red = 0
    var green = 0
    var blue = 0

    for (input in inputs)
    {
        // Make sure to account for opacity.
        red += (input - (input % (256.0.pow(2).toInt()))) shr (16 % 256)
        // Removes the last chunk, shifts it out, and removes the first chunk.
        green += ((input - (input % 256)) shr 8) % 256
        blue += input % 256
    }

    val divisor = inputs.size
    var result = blue / divisor
    result += (green / divisor) shl 8
    result += (red / divisor) shl 16

    color(result)
    return this
}

/**
 * Get average RGB color without components of materials.
 */
fun Material.Builder.colorAverage(vararg inputs: Material): Material.Builder
{
    var red = 0
    var green = 0
    var blue = 0

    for (input in inputs)
    {
        val inputRGB = input.materialRGB
        // Make sure to account for opacity.
        red += (inputRGB - (inputRGB % (256.0.pow(2).toInt()))) shr (16 % 256)
        // Removes the last chunk, shifts it out, and removes the first chunk.
        green += ((inputRGB - (inputRGB % 256)) shr 8) % 256
        blue += inputRGB % 256
    }

    val divisor = inputs.size
    var result = blue / divisor
    result += (green / divisor) shl 8
    result += (red / divisor) shl 16

    color(result)
    return this
}