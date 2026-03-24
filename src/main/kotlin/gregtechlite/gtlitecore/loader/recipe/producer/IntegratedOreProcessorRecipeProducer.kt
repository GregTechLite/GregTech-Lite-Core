package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.ORE_WASHER_RECIPES
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.recipes.RecipeMaps.THERMAL_CENTRIFUGE_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.SodiumPersulfate
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.ore
import gregtech.api.unification.ore.OrePrefix.oreAndesite
import gregtech.api.unification.ore.OrePrefix.oreBasalt
import gregtech.api.unification.ore.OrePrefix.oreBlackgranite
import gregtech.api.unification.ore.OrePrefix.oreDiorite
import gregtech.api.unification.ore.OrePrefix.oreEndstone
import gregtech.api.unification.ore.OrePrefix.oreGranite
import gregtech.api.unification.ore.OrePrefix.oreMarble
import gregtech.api.unification.ore.OrePrefix.oreNetherrack
import gregtech.api.unification.ore.OrePrefix.oreRedSand
import gregtech.api.unification.ore.OrePrefix.oreRedgranite
import gregtech.api.unification.ore.OrePrefix.oreSand
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.util.OreProcessorRecipeWrapper
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreBlueSchist
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreGreenSchist
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreKimberlite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreKomatiite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreLimestone
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreQuartzite
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreShale
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.oreSlate
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary

internal object IntegratedOreProcessorRecipeProducer
{

    // @formatter:off

    val orePrefixes = mutableListOf(ore, oreNetherrack, oreEndstone)

    init
    {
        // Only register stoneTypes ore processing when the unique stoneTypes config enabled.
        if (ConfigHolder.worldgen.allUniqueStoneTypes)
        {
            orePrefixes.addAll(listOf(oreGranite, oreDiorite, oreAndesite, oreBlackgranite, oreRedgranite, oreMarble,
                                      oreBasalt, oreSand, oreRedSand, oreLimestone, oreKomatiite, oreGreenSchist,
                                      oreBlueSchist, oreKimberlite, oreQuartzite, oreSlate, oreShale))
        }
    }

    fun produce()
    {
        // Macerator -> Ore Washer -> Thermal Centrifuge -> Macerator
        initRecipe(1, listOf(
            OreProcessorOps(MACERATOR_RECIPES),
            OreProcessorOps(ORE_WASHER_RECIPES, listOf(DistilledWater.getFluid(Int.MAX_VALUE))),
            OreProcessorOps(THERMAL_CENTRIFUGE_RECIPES),
            OreProcessorOps(MACERATOR_RECIPES)))

        // Macerator -> Ore Washer -> Macerator -> Centrifuge
        initRecipe(2, listOf(
            OreProcessorOps(MACERATOR_RECIPES),
            OreProcessorOps(ORE_WASHER_RECIPES, listOf(DistilledWater.getFluid(Int.MAX_VALUE))),
            OreProcessorOps(MACERATOR_RECIPES),
            OreProcessorOps(CENTRIFUGE_RECIPES)))

        // Macerator -> Macerator -> Centrifuge
        initRecipe(3, listOf(
            OreProcessorOps(MACERATOR_RECIPES),
            OreProcessorOps(MACERATOR_RECIPES),
            OreProcessorOps(CENTRIFUGE_RECIPES)))

        // Macerator -> Ore Washer -> Sifter
        initRecipe(4, listOf(
            OreProcessorOps(MACERATOR_RECIPES),
            OreProcessorOps(ORE_WASHER_RECIPES, listOf(DistilledWater.getFluid(Int.MAX_VALUE))),
            OreProcessorOps(SIFTER_RECIPES, extraItems = listOf(IntCircuitIngredient.getIntegratedCircuit(1)))))

        // Macerator -> Chemical Bath -> Macerator -> Centrifuge
        for (fluidMaterial in listOf(DistilledWater, Mercury, SodiumPersulfate)) {
            initRecipe(5, listOf(
                    OreProcessorOps(MACERATOR_RECIPES),
                    OreProcessorOps(CHEMICAL_BATH_RECIPES, listOf(fluidMaterial.getFluid(Int.MAX_VALUE))),
                    OreProcessorOps(MACERATOR_RECIPES),
                    OreProcessorOps(CENTRIFUGE_RECIPES)
            ), listOf(fluidMaterial.getFluid(100)))
        }

        // TODO: Should we need some useless cycle?
    }

    private fun <T : RecipeBuilder<T>> initRecipe(circuit: Int, ops: List<OreProcessorOps<T>>, extraFluids: List<FluidStack>? = null)
    {
        val oreItems = mutableListOf<ItemStack>()
        for (name in OreDictionary.getOreNames())
        {
            if (name.startsWith("ore"))
            {
                val itemStack = OreDictUnifier.get(name)
                if (OreDictUnifier.getMaterial(itemStack) != null)
                    oreItems.add(itemStack)
            }
        }

        for (item in oreItems)
        {
            val inputItem = OreDictUnifier.getPrefix(item)?.let { item.copy(10000 * getOreProductMultiplier(it)) }
            var output: List<ItemStack>? = listOf(inputItem!!.copy())

            for (op in ops)
            {
                if (output == null)
                    break

                output = OreProcessorRecipeWrapper.doRecipe(output, op.recipeMap, op.extraFluids, op.extraItems)
            }

            if (output == null || output.isEmpty() || output.size == 1 && output[0].isItemEqual(item))
                continue // No corresponding recipe tree for it

            val material = OreDictUnifier.getMaterial(item)?.material

            for (prefix in orePrefixes)
            {
                val itemStack = OreDictUnifier.get(prefix, material)
                if (itemStack.isEmpty)
                    continue // This stone type does not exist ore

                itemStack.count = 1

                val builder = INTEGRATED_ORE_PROCESSOR_RECIPES.recipeBuilder()
                    .circuitMeta(circuit)
                    .inputs(itemStack)
                    .fluidInputs(DistilledWater.getFluid(100), Lubricant.getFluid(5))

                if (extraFluids != null)
                {
                    for (fluid in extraFluids)
                    {
                        builder.fluidInputs(fluid)
                    }
                }

                for (outputItem in output)
                {
                    val chancedCount = outputItem.count * getOreProductMultiplier(prefix)
                    if (chancedCount < 10000)
                    {
                        val outputItemStack = outputItem.copy(1)
                        builder.chancedOutput(outputItemStack, chancedCount, 0)
                    }
                    else
                    {
                        val outputItemStack = outputItem.copy((chancedCount + 5000) / 10000)
                        builder.outputs(outputItemStack)
                    }
                }

                builder.EUt(VA[LV])
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }
        }
    }

    private fun getOreProductMultiplier(prefix: OrePrefix): Int = when (prefix)
    {
        oreNetherrack -> 2
        oreEndstone   -> 4
        else          -> 1
    }

    data class OreProcessorOps<T : RecipeBuilder<T>>(
        val recipeMap: RecipeMap<T>,
        val extraFluids: List<FluidStack>? = null,
        val extraItems: List<ItemStack>? = null)

    // @formatter:on

}
