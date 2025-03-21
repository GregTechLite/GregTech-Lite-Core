package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.builders.BlastRecipeBuilder
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.BlastProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.loaders.recipe.CraftingComponent
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialFlags
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import kotlin.math.max


@Suppress("MISSING_DEPENDENCY_CLASS")
open class AlloyBlastRecipeProducer
{

    companion object
    {

        val DEFAULT_PRODUCER = AlloyBlastRecipeProducer()

    }

    fun produce(material: Material, property: BlastProperty)
    {
        // Do not generate for disabled materials.
        if (material.hasFlag(GTLiteMaterialFlags.NO_ALLOY_BLAST_RECIPES)) return
        // Ignore non-alloys material again.
        val componentAmount = material.materialComponents.size
        if (componentAmount < 2) return

        val output = material.fluid

        val builder: RecipeBuilder<BlastRecipeBuilder> = createBuilder(property, material)

        val outputAmount: Int = this.addInputs(material, builder)
        if (outputAmount <= 0)
            return

        this.buildRecipes(property, output, outputAmount, componentAmount, builder)
    }

    open fun createBuilder(property: BlastProperty, material: Material): BlastRecipeBuilder
    {
        val builder = GTLiteRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()

        var duration = property.durationOverride
        if (duration < 0)
            duration = max(1.0,
                (material.mass * property.blastTemperature / 100L).toInt().toDouble()).toInt()
        builder.duration(duration)

        var eUt = property.eUtOverride
        if (eUt < 0) eUt = GTValues.VA[GTValues.MV]
        builder.EUt(eUt.toLong())

        return builder.blastFurnaceTemp(property.blastTemperature)
    }

    open fun addInputs(material: Material, builder: RecipeBuilder<BlastRecipeBuilder>): Int
    {
        // Calculate the output amount and add inputs.
        var outputAmount = 0
        var fluidAmount = 0
        for (ms in material.materialComponents)
        {
            val msMat = ms.material
            val msAmount = ms.amount.toInt()
            if (msMat.hasProperty(PropertyKey.DUST))
            {
                builder.input(OrePrefix.dust, msMat, msAmount)
            }
            else if (msMat.hasProperty(PropertyKey.FLUID))
            {
                if (fluidAmount >= 2) // More than 2 fluids won't fit in the machine.
                    return -1
                fluidAmount++
                // Assume all fluids have 1000mB/mol, since other quantities should be as an item input.
                builder.fluidInputs(msMat.getFluid(1000 * msAmount))
            }
            else
            {
                return -1 // No fluid or item property means no valid recipe.
            }
            outputAmount += msAmount
        }
        return outputAmount
    }

    open fun buildRecipes(property: BlastProperty, output: Fluid,
                             outputAmount: Int, componentAmount: Int,
                             builder: RecipeBuilder<BlastRecipeBuilder>)
    {
        // Add the fluid output with the correct amount.
        builder.fluidOutputs(FluidStack(output, GTValues.L * outputAmount))
        // Apply ABS duration reduction: 3/4.
        val duration = builder.duration * outputAmount * 3 / 4
        // Build the Gas recipe if it exists.
        if (property.gasTier != null)
        {
            val builderGas: RecipeBuilder<BlastRecipeBuilder> = builder.copy()
            val gas = CraftingComponent.EBF_GASES[property.gasTier]
            builderGas.circuitMeta(this.getGasCircuitNumber(componentAmount))
                .fluidInputs(FluidStack(gas, (gas as FluidStack).amount * outputAmount))
                .duration((duration * 0.67).toInt())
                .buildAndRegister()
        }
        // Build the non-gas recipe.
        builder.circuitMeta(this.getCircuitNumber(componentAmount))
            .duration(duration)
            .buildAndRegister()
    }

    open fun getCircuitNumber(componentAmount: Int) = componentAmount

    open fun getGasCircuitNumber(componentAmount: Int) = componentAmount + 10

}