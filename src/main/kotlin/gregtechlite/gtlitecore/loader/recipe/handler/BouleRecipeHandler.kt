package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.info.MaterialFlags.CRYSTALLIZABLE
import gregtech.api.unification.material.properties.GemProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.gemExquisite
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.duration
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags.GENERATE_BOULE
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.boule
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.seedCrystal
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

@Suppress("unused")
object BouleRecipeHandler
{

    // @formatter:off

    fun init()
    {
        gem.addProcessingHandler(PropertyKey.GEM, ::processCrystallizer)
    }

    private fun processCrystallizer(gemPrefix: OrePrefix, material: Material, property: GemProperty)
    {

        // If material has correspondenced disable flag, then do not generate crystallization.
        if (material.hasFlag(DISABLE_CRYSTALLIZATION)) return
        if (!material.hasFlag(CRYSTALLIZABLE) && !material.hasFlag(GENERATE_BOULE)) return

        // If material has too many components, then do not generate crystallization.
        if (material.materialComponents.size > CRYSTALLIZATION_RECIPES.maxInputs - 1
            + CRYSTALLIZATION_RECIPES.maxFluidInputs) return

        var componentAmount = 0
        var temperature = 0
        val inputs = arrayListOf<ItemStack>()
        val fluidInputs = arrayListOf<FluidStack>()

        for (materialStack in material.materialComponents)
        {
            val component= materialStack.material
            val amount = materialStack.amount.toInt()

            if (component.isSolid || component.hasProperty(PropertyKey.DUST))
            {
                componentAmount += amount
                temperature += component.blastTemperature * amount

                if (inputs.size > CRYSTALLIZATION_RECIPES.maxInputs - 1)
                    return

                inputs.add(OreDictUnifier.get(dust, component, amount))
            }
            else if (component.hasProperty(PropertyKey.FLUID))
            {
                componentAmount += amount

                if (fluidInputs.size > CRYSTALLIZATION_RECIPES.maxFluidInputs)
                    return

                fluidInputs.add(component.getFluid(amount * 1000))
            }

            // If materials with not BlastProperty, then set it blastTemperature to 1200K.
            if (!component.hasProperty(PropertyKey.BLAST))
                temperature += 1200 * amount
        }

        if (componentAmount == 0) return

        temperature /= componentAmount

        val builder = CRYSTALLIZATION_RECIPES.recipeBuilder()
            .blastFurnaceTemp(temperature)
            .EUt(VA[if (temperature <= 2800) HV else EV])

        if (componentAmount == 2)
        {
            for (stack in inputs) stack.count *= 2
            for (fluid in fluidInputs) fluid.amount *= 2
            componentAmount = 1
            builder.duration(material.mass * 8)
        }
        else if (componentAmount % 4 != 0)
        {
            for (stack in inputs) stack.count *= 4
            for (fluid in fluidInputs) fluid.amount *= 4
            builder.duration(material.mass * 16)
        }
        else
        {
            componentAmount /= 4
            builder.duration(material.mass * 4)
        }

        builder.input(seedCrystal, material, componentAmount)
            .output(boule, material, componentAmount);

        if (inputs.isNotEmpty()) builder.inputStacks(inputs)
        if (fluidInputs.isNotEmpty()) builder.fluidInputs(*fluidInputs.toTypedArray())

        builder.buildAndRegister()

        CUTTER_RECIPES.recipeBuilder()
            .input(boule, material)
            .output(gemExquisite, material)
            .output(seedCrystal, material)
            .EUt(VA[LV])
            .duration(material.mass * 4)
            .buildAndRegister()

        AUTOCLAVE_RECIPES.recipeBuilder()
            .input(gemExquisite, material)
            .fluidInputs(DistilledWater.getFluid(8000))
            .output(seedCrystal, material)
            .EUt(VA[HV])
            .duration(material.mass * 9)
            .buildAndRegister()
    }

    // @formatter:on

}