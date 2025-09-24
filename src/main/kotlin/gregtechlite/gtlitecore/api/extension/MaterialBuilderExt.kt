package gregtechlite.gtlitecore.api.extension

import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import kotlin.math.pow

// region Functional Utilities

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

// endregion

// region DSL Contexts

fun Material.Builder.toolProp(harvestSpeed: Float, attackDamage: Float, durability: Int, harvestLevel: Int): Material.Builder
{
    toolStats(MaterialToolProperty.Builder.of(harvestSpeed, attackDamage, durability, harvestLevel).build())
    return this
}

fun Material.Builder.toolProp(harvestSpeed: Float, attackDamage: Float, durability: Int, harvestLevel: Int,
                              builder: MaterialToolProperty.Builder.() -> Unit): Material.Builder
{
    toolStats(MaterialToolProperty.Builder.of(harvestSpeed, attackDamage, durability, harvestLevel).apply(builder).build())
    return this
}

// endregion

// region Simplifactions

fun Material.Builder.blastProp(temperature: Int, gasTier: BlastProperty.GasTier): Material.Builder
{
    blast { b ->
        b.temp(temperature, gasTier)
    }
    return this
}

fun Material.Builder.blastProp(temperature: Int, gasTier: BlastProperty.GasTier,
                               blastEUtOverride: Int, blastDurationOverride: Int): Material.Builder
{
    blast { b ->
        b.temp(temperature, gasTier)
            .blastStats(blastEUtOverride, blastDurationOverride)
    }
    return this
}

fun Material.Builder.blastProp(temperature: Int, gasTier: BlastProperty.GasTier,
                               blastEUtOverride: Int, blastDurationOverride: Int,
                               freezeEUtOverride: Int, freezeDurationOverride: Int): Material.Builder
{
    blast { b ->
        b.temp(temperature, gasTier)
            .blastStats(blastEUtOverride, blastDurationOverride)
            .vacuumStats(freezeEUtOverride, freezeDurationOverride)
    }
    return this
}

fun Material.Builder.cableProp(vol: Long, amp: Int, loss: Int, isSuperconductor: Boolean = false): Material.Builder
{
    cableProperties(vol, amp, loss, isSuperconductor)
    return this
}

fun Material.Builder.itemPipeProp(priority: Int, transferRate: Float): Material.Builder
{
    itemPipeProperties(priority, transferRate)
    return this
}

fun Material.Builder.fluidPipeProp(maxTemperature: Int, transferRate: Int,
                                   gasProof: Boolean = false, acidProof: Boolean = false,
                                   cryoProof: Boolean = false, plasmaProof: Boolean = false): Material.Builder
{
    fluidPipeProperties(maxTemperature, transferRate, gasProof, acidProof, cryoProof, plasmaProof)
    return this
}

fun Material.Builder.rotorProp(speed: Float, damage: Float, durability: Int): Material.Builder
{
    rotorStats(speed, damage, durability)
    return this
}

// endregion