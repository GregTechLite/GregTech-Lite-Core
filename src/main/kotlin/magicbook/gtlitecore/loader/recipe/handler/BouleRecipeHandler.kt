package magicbook.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.GemProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialFlags
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class BouleRecipeHandler
{

    companion object
    {

        fun init()
        {
            OrePrefix.gem.addProcessingHandler(PropertyKey.GEM, BouleRecipeHandler::processCrystallizer)
        }

        private fun processCrystallizer(gemPrefix: OrePrefix, material: Material, property: GemProperty)
        {

            // If material has correspondenced disable flag, then do not generate crystallization.
            if (material.hasFlags(GTLiteMaterialFlags.DISABLE_CRYSTALLIZATION)) return
            if (!material.hasFlags(MaterialFlags.CRYSTALLIZABLE) && !material.hasFlags(GTLiteMaterialFlags.GENERATE_BOULE)) return

            // If material has too many components, then do not generate crystallization.
            if (material.materialComponents.size > GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.maxInputs - 1
                + GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.maxFluidInputs) return

            var componentAmount = 0
            var temperature = 0
            val inputs = arrayListOf<ItemStack>()
            val fluidInputs = arrayListOf<FluidStack>()

            for (ms in material.materialComponents)
            {
                val component= ms.material
                val amount = ms.amount.toInt()
                if (component.isSolid || component.hasProperty(PropertyKey.DUST))
                {
                    componentAmount += amount
                    temperature += component.blastTemperature * amount
                    if (inputs.size > GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.maxInputs - 1)
                        return
                    inputs.add(OreDictUnifier.get(OrePrefix.dust, component, amount))
                }
                else if (component.hasProperty(PropertyKey.FLUID))
                {
                    componentAmount += amount
                    if (fluidInputs.size > GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.maxFluidInputs)
                        return
                    fluidInputs.add(component.getFluid(amount * 1000))
                }
                // If materials with not BlastProperty, then set it blastTemperature to 1200K.
                if (!component.hasProperty(PropertyKey.BLAST))
                    temperature += 1200 * amount
            }

            if (componentAmount == 0) return

            temperature /= componentAmount

            val builder = GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES.recipeBuilder()
                .blastFurnaceTemp(temperature)
                .EUt(VA[if (temperature <= 2800) HV else EV].toLong())

            if (componentAmount == 2)
            {
                for (stack in inputs) stack.count *= 2
                for (fluid in fluidInputs) fluid.amount *= 2
                componentAmount = 1
                builder.duration(material.mass.toInt() * 8)
            }
            else if (componentAmount % 4 != 0)
            {
                for (stack in inputs) stack.count *= 4
                for (fluid in fluidInputs) fluid.amount *= 4
                builder.duration(material.mass.toInt() * 16)
            }
            else
            {
                componentAmount /= 4
                builder.duration(material.mass.toInt() * 4)
            }

            builder.input(GTLiteOrePrefix.seedCrystal, material, componentAmount)
                .output(GTLiteOrePrefix.boule, material, componentAmount);

            if (inputs.isNotEmpty()) builder.inputStacks(inputs)
            if (fluidInputs.isNotEmpty()) builder.fluidInputs(*fluidInputs.toTypedArray())

            builder.buildAndRegister()

            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(GTLiteOrePrefix.boule, material)
                .output(OrePrefix.gemExquisite, material)
                .output(GTLiteOrePrefix.seedCrystal, material)
                .EUt(VA[LV].toLong())
                .duration(material.mass.toInt() * 4)
                .buildAndRegister()

            RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(OrePrefix.gemExquisite, material)
                .fluidInputs(Materials.DistilledWater.getFluid(8000))
                .output(GTLiteOrePrefix.seedCrystal, material)
                .EUt(VA[HV].toLong())
                .duration(material.mass.toInt() * 9)
                .buildAndRegister()

        }

    }

}