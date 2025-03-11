package magicbook.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.OreProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.UnificationEntry
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class OreRecipeHandler
{

    companion object
    {

        fun init()
        {
            // Callback original registrate of ore processing handler and post new processing handler
            // from new recipe handler container.
            OrePrefix.crushedPurified.addProcessingHandler(PropertyKey.ORE, this::processCrushedPurified)
            // ===========================================================================================
        }

        private fun processCrushedPurified(purifiedPrefix: OrePrefix, material: Material, property: OreProperty)
        {
            val crushedCentrifugedStack: ItemStack = OreDictUnifier.get(OrePrefix.crushedCentrifuged, material)
            val dustStack = OreDictUnifier.get(OrePrefix.dustPure, material)
            val byproductMaterial = property.getOreByProduct(1, material)
            val byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial)
            // Hamming crushedPurifiedX -> dustPureX.
            RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(purifiedPrefix, material)
                .outputs(dustStack)
                .EUt(VH[LV].toLong())
                .duration(10 * TICK)
                .buildAndRegister()
            // Macerating crushedPurifiedX -> dustPureX + (dustX: byproduct).
            RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(purifiedPrefix, material)
                .outputs(dustStack)
                .chancedOutput(byproductStack, 1400, 850)
                .duration(20 * SECOND)
                .buildAndRegister()
            // Hand-hamming crushedPurifiedX -> dustPureX.
            ModHandler.addShapelessRecipe(String.format("purified_ore_to_dust_%s", material), dustStack,
                'h', UnificationEntry(purifiedPrefix, material))
            // Thermal centrifuging crushedPurifiedX -> crushedCentrifugedX + (dustX: byproduct).
            if (!crushedCentrifugedStack.isEmpty)
            {
                RecipeMaps.THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                    .input(purifiedPrefix, material)
                    .outputs(crushedCentrifugedStack)
                    .chancedOutput(OrePrefix.dust, byproductMaterial, 3333, 0)
                    .buildAndRegister()
            }
            // Gem sifting, we merged gemSolitary and sort it at this stage.
            // We modified sifter recipe map slots at GTLiteRecipeMaps#postRecipeMaps().
            if (material.hasProperty(PropertyKey.GEM))
            {
                val solitaryStack = OreDictUnifier.get(GTLiteOrePrefix.gemSolitary, material)
                val exquisiteStack = OreDictUnifier.get(OrePrefix.gemExquisite, material)
                val flawlessStack = OreDictUnifier.get(OrePrefix.gemFlawless, material)
                val gemStack = OreDictUnifier.get(OrePrefix.gem, material)
                val flawedStack = OreDictUnifier.get(OrePrefix.gemFlawed, material)
                val chippedStack = OreDictUnifier.get(OrePrefix.gemChipped, material)

                if (material.hasFlag(MaterialFlags.HIGH_SIFTER_OUTPUT))
                {
                    RecipeMaps.SIFTER_RECIPES.recipeBuilder()
                        .input(purifiedPrefix, material)
                        .chancedOutput(solitaryStack, 100, 50)
                        .chancedOutput(exquisiteStack, 500, 150)
                        .chancedOutput(flawlessStack, 1500, 200)
                        .chancedOutput(gemStack, 5000, 1000)
                        .chancedOutput(flawedStack, 2000, 500)
                        .chancedOutput(chippedStack, 3000, 350)
                        .chancedOutput(dustStack, 2500, 500)
                        .EUt(VH[LV].toLong())
                        .duration(20 * SECOND)
                        .buildAndRegister()
                }
                else
                {
                    RecipeMaps.SIFTER_RECIPES.recipeBuilder()
                        .input(purifiedPrefix, material)
                        .chancedOutput(solitaryStack, 50, 25)
                        .chancedOutput(exquisiteStack, 300, 100)
                        .chancedOutput(flawlessStack, 1000, 150)
                        .chancedOutput(gemStack, 3500, 500)
                        .chancedOutput(flawedStack, 2500, 300)
                        .chancedOutput(chippedStack, 3500, 400)
                        .chancedOutput(dustStack, 5000, 750)
                        .EUt(VH[LV].toLong())
                        .duration(20 * SECOND)
                        .buildAndRegister()
                }
            }
            processMetalSmelting(purifiedPrefix, material, property)
        }

        /**
         * Transformed from [gregtech.loaders.recipe.handlers.OreRecipeHandler.processMetalSmelting].
         */
        private fun processMetalSmelting(crushedPrefix: OrePrefix, material: Material, property: OreProperty)
        {
            val smeltingResult = if (property.directSmeltResult != null) property.directSmeltResult else material
            if (smeltingResult!!.hasProperty(PropertyKey.INGOT))
            {
                val ingotStack: ItemStack = OreDictUnifier.get(OrePrefix.ingot, smeltingResult)
                if (!ingotStack.isEmpty && doesMaterialUseNormalFurnace(smeltingResult))
                {
                    ModHandler.addSmeltingRecipe(UnificationEntry(crushedPrefix, material), ingotStack, 0.5f)
                }
            }
        }

        /**
         * Transformed from [gregtech.loaders.recipe.handlers.OreRecipeHandler.doesMaterialUseNormalFurnace].
         */
        private fun doesMaterialUseNormalFurnace(material: Material)
            = !material.hasProperty(PropertyKey.BLAST)
    }

}