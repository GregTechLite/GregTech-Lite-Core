package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GregTechAPI
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.info.MaterialFlags.FLAMMABLE
import gregtech.api.unification.material.info.MaterialFlags.NO_SMELTING
import gregtech.api.unification.material.info.MaterialFlags.NO_UNIFICATION
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES
import kotlin.math.max

object EntrodynamicallyPhaseChangerRecipeProducer
{
    // @formatter:off

    fun produce()
    {
        val handledVariants = collectHandledVariants()
        GregTechAPI.materialManager.registeredMaterials.forEach { material ->
            if (!material.hasProperty(PropertyKey.BLAST)) return@forEach
            if (!material.hasProperty(PropertyKey.INGOT)) return@forEach
            if (material.hasAnyOfFlags(FLAMMABLE, NO_SMELTING, NO_UNIFICATION)) return@forEach

            val dustStack = OreDictUnifier.get(dust, material)
            val ingotStack = OreDictUnifier.get(ingot, material)
            if (dustStack.isEmpty || ingotStack.isEmpty) return@forEach

            val blastProperty = material.getProperty(PropertyKey.BLAST) ?: return@forEach

            var blastTemp = material.blastTemperature
            if (blastTemp <= 0)
                blastTemp += 1200

            var duration = blastProperty.durationOverride
            if (duration <= 0)
                duration = max(1, material.mass * blastTemp / 50).toInt()

            var eut = blastProperty.eUtOverride
            if (eut <= 0)
                eut = VA[MV]

            if ((material.resourceLocation.toString() to 1) !in handledVariants)
            {
                TOPOLOGICAL_ORDER_CHANGING_RECIPES.addRecipe {
                    circuitMeta(1)
                    input(dust, material)
                    outputs(ingotStack)
                    EUt(eut)
                    duration((duration * 0.5).toInt())
                    blastFurnaceTemp(blastTemp)
                }
            }

            if (material.hasFluid())
            {
                if ((material.resourceLocation.toString() to 2) !in handledVariants)
                {
                    TOPOLOGICAL_ORDER_CHANGING_RECIPES.addRecipe {
                        circuitMeta(2)
                        input(dust, material)
                        fluidOutputs(material.getFluid(L))
                        EUt(eut)
                        duration((duration * 0.5).toInt())
                        blastFurnaceTemp(blastTemp)
                    }
                }

                if (material.getFluid(FluidStorageKeys.PLASMA) != null
                    && (material.resourceLocation.toString() to 3) !in handledVariants)
                {
                    TOPOLOGICAL_ORDER_CHANGING_RECIPES.addRecipe {
                        circuitMeta(3)
                        input(dust, material)
                        fluidOutputs(material.getPlasma(L))
                        EUt(eut)
                        duration((duration * 0.5).toInt())
                        blastFurnaceTemp(blastTemp)
                    }
                }

                if ((material.resourceLocation.toString() to 4) !in handledVariants)
                {
                    TOPOLOGICAL_ORDER_CHANGING_RECIPES.addRecipe {
                        circuitMeta(4)
                        inputs(ingotStack)
                        fluidOutputs(material.getFluid(L))
                        EUt(eut)
                        duration((duration * 0.5).toInt())
                        blastFurnaceTemp(blastTemp)
                    }
                }

                if (material.getFluid(FluidStorageKeys.PLASMA) != null
                    && (material.resourceLocation.toString() to 5) !in handledVariants)
                {
                    TOPOLOGICAL_ORDER_CHANGING_RECIPES.addRecipe {
                        circuitMeta(5)
                        inputs(ingotStack)
                        fluidOutputs(material.getPlasma(L))
                        EUt(eut)
                        duration((duration * 0.5).toInt())
                        blastFurnaceTemp(blastTemp)
                    }
                }
            }
        }
    }

    private fun collectHandledVariants(): Set<Pair<String, Int>>
    {
        val handledVariants = mutableSetOf<Pair<String, Int>>()
        for (recipe in TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeList)
        {
            var circuit = -1
            val materials = mutableSetOf<String>()

            for (input in recipe.inputs)
            {
                if (input is IntCircuitIngredient)
                {
                    circuit = input.inputStacks.firstOrNull()
                        ?.let { IntCircuitIngredient.getCircuitConfiguration(it) } ?: -1
                    continue
                }

                for (stack in input.inputStacks)
                {
                    val prefix = OreDictUnifier.getPrefix(stack) ?: continue
                    if (prefix != dust && prefix != ingot) continue
                    OreDictUnifier.getMaterial(stack)?.let { materials.add(it.material.resourceLocation.toString()) }
                }
            }

            if (circuit >= 0)
                materials.forEach { handledVariants.add(it to circuit) }
        }
        return handledVariants
    }

    // @formatter:on
}
