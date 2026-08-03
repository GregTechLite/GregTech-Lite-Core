package gregtechlite.gtlitecore.api.extension

import com.morphismmc.morphismlib.util.I18nUtil
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.FluidProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.MaterialToolProperty
import gregtech.api.unification.material.properties.PropertyKey
import kotlin.math.pow

// region Material Properties

fun Material.addIngot() = setProperty(PropertyKey.INGOT, IngotProperty())

fun Material.addDust() = setProperty(PropertyKey.DUST, DustProperty())

fun Material.addLiquid() = setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))

fun Material.addGas() = setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.GAS, FluidBuilder()))

fun Material.addPlasma() = getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())

fun Material.addLiquidAndPlasma()
{
    setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))
    getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())
}

fun Material.addGasAndPlasma()
{
    setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.GAS, FluidBuilder()))
    getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())
}

fun Material.setFormula(formulaKey: String, defaultKey: String = formulaKey): Material = apply {
    setFormula(I18nUtil.format(formulaKey, defaultKey))
}

// endregion

// region Material Builders

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

fun Material.Builder.colorAverage(vararg inputs: Material): Material.Builder
    = colorAverage(*inputs.map { it.materialRGB }.toIntArray())

fun Material.Builder.liquid(builder: FluidBuilder.() -> Unit): Material.Builder = apply {
    liquid(FluidBuilder().apply(builder))
}

fun Material.Builder.gas(builder: FluidBuilder.() -> Unit): Material.Builder = apply {
    gas(FluidBuilder().apply(builder))
}

fun Material.Builder.plasma(builder: FluidBuilder.() -> Unit): Material.Builder = apply {
    plasma(FluidBuilder().apply(builder))
}

fun Material.Builder.toolProp(harvestSpeed: Float, attackDamage: Float, durability: Int,
                              harvestLevel: Int): Material.Builder = apply {
    toolStats(MaterialToolProperty.Builder.of(harvestSpeed, attackDamage, durability, harvestLevel).build())
}

fun Material.Builder.toolProp(harvestSpeed: Float, attackDamage: Float, durability: Int, harvestLevel: Int,
                              builder: MaterialToolProperty.Builder.() -> Unit): Material.Builder = apply {
    toolStats(MaterialToolProperty.Builder.of(harvestSpeed, attackDamage, durability, harvestLevel).apply(builder).build())
}

fun Material.Builder.blastProp(temperature: Int, gasTier: BlastProperty.GasTier): Material.Builder = apply {
    blast { it.temp(temperature, gasTier) }
}

fun Material.Builder.blastProp(temperature: Int, gasTier: BlastProperty.GasTier, blastEUtOverride: Int,
                               blastDurationOverride: Int): Material.Builder = apply {
    blast { it.temp(temperature, gasTier).blastStats(blastEUtOverride, blastDurationOverride) }
}

fun Material.Builder.blastProp(temperature: Int, gasTier: BlastProperty.GasTier, blastEUtOverride: Int,
                               blastDurationOverride: Int, freezeEUtOverride: Int,
                               freezeDurationOverride: Int): Material.Builder = apply {
    blast {
        it.temp(temperature, gasTier)
            .blastStats(blastEUtOverride, blastDurationOverride)
            .vacuumStats(freezeEUtOverride, freezeDurationOverride)
    }
}

fun Material.Builder.cableProp(vol: Long, amp: Int, loss: Int, isSuperconductor: Boolean = false): Material.Builder
    = apply { cableProperties(vol, amp, loss, isSuperconductor) }

fun Material.Builder.itemPipeProp(priority: Int, transferRate: Float): Material.Builder
    = apply { itemPipeProperties(priority, transferRate) }

fun Material.Builder.fluidPipeProp(maxTemperature: Int, transferRate: Int,
                                   gasProof: Boolean = false, acidProof: Boolean = false,
                                   cryoProof: Boolean = false, plasmaProof: Boolean = false): Material.Builder
    = apply { fluidPipeProperties(maxTemperature, transferRate, gasProof, acidProof, cryoProof, plasmaProof) }

fun Material.Builder.rotorProp(speed: Float, damage: Float, durability: Int): Material.Builder = apply {
    rotorStats(speed, damage, durability)
}

// endregion