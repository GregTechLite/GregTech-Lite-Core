package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Water
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.FERTILIZER
import gregtech.common.items.MetaItems.STICKY_RESIN
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.GREENHOUSE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GreenhouseGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Latex
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RainbowSap
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resin
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.APRICOT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BANANA
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COCONUT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.LEMON
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.LIME
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MANGO
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NUTMEG
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OLIVE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ORANGE
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class GreenhouseRecipes
{

    companion object
    {

        fun init()
        {
            // Greenhouse Gas
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Air.getFluid(100))
                .fluidOutputs(GreenhouseGas.getFluid(100))
                .EUt(VA[MV].toLong())
                .duration(1 * SECOND + 5 * TICK)
                .buildAndRegister()

            // =========================================================================================================
            addGreenhouseGrowthing(
                ItemStack(Blocks.SAPLING, 1, 0), // Oak Sapling
                ItemStack(Blocks.LOG, 1, 0), // Oak Logs
                ItemStack(Blocks.LEAVES, 1, 0), // Oak Leaves
                ItemStack(Items.APPLE), // Apple
                Resin)

            addGreenhouseGrowthing(
                ItemStack(Blocks.SAPLING, 1, 1), // Spruce Sapling
                ItemStack(Blocks.LOG, 1, 1), // Spruce Logs
                ItemStack(Blocks.LEAVES, 1, 1), // Spruce Leaves
                ItemStack(Items.APPLE), // Apple
                Resin)

            addGreenhouseGrowthing(
                ItemStack(Blocks.SAPLING, 1, 2), // Birch Sapling
                ItemStack(Blocks.LOG, 1, 2), // Birch Logs
                ItemStack(Blocks.LEAVES, 1, 2), // Birch Leaves
                ItemStack(Items.APPLE), // Apple
                Resin)

            addGreenhouseGrowthing(
                ItemStack(Blocks.SAPLING, 1, 3), // Jungle Sapling
                ItemStack(Blocks.LOG, 1, 3), // Jungle Logs
                ItemStack(Blocks.LEAVES, 1, 3), // Jungle Leaves
                ItemStack(Items.DYE, 1, 3), // Coco
                Resin)

            addGreenhouseGrowthing(
                ItemStack(Blocks.SAPLING, 1, 4), // Acacia Sapling
                ItemStack(Blocks.LOG2, 1, 0), // Acacia Logs
                ItemStack(Blocks.LEAVES2, 1, 0), // Acacia Leaves
                ItemStack(Items.APPLE), // Apple
                Resin)

            addGreenhouseGrowthing(
                ItemStack(Blocks.SAPLING, 1, 5), // Dark Oak Sapling
                ItemStack(Blocks.LOG2, 1, 1), // Dark Oak Logs
                ItemStack(Blocks.LEAVES2, 1, 1), // Dark Oak Leaves
                ItemStack(Items.APPLE), // Apple
                Resin)

            addGreenhouseGrowthing(
                ItemStack(MetaBlocks.RUBBER_SAPLING as Block, 1, 0), // Rubber Sapling
                ItemStack(MetaBlocks.RUBBER_LOG as Block, 1, 0), // Rubber Logs
                ItemStack(MetaBlocks.RUBBER_LEAVES as Block, 1, 0), // Rubber Leaves
                STICKY_RESIN.stackForm, // Sticky Resin
                Latex)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getItem("gtlite_sapling_0"), // Banana Sapling
                Mods.GregTechLiteCore.getItem("gtlite_log_0"), // Banana Logs
                Mods.GregTechLiteCore.getItem("gtlite_leaves_0"), // Banana Leaves
                BANANA.stackForm) // Banana

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 2, 1), // Orange Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_0", 4, 1), // Orange Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_0", 4, 1), // Orange Leaves
                ORANGE.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 4, 1), // Mango Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_0", 8, 1), // Mango Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_0", 8, 1), // Mango Leaves
                MANGO.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 6, 1), // Apricot Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_0", 12, 1), // Apricot Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_0", 12, 1), // Apricot Leaves
                APRICOT.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 8, 1), // Lemon Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_1", 0, 1), // Lemon Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_1", 0, 1), // Lemon Leaves
                LEMON.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 10, 1), // Lime Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_1", 4, 1), // Lime Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_1", 4, 1), // Lime Leaves
                LIME.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 12, 1), // Olive Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_1", 8, 1), // Olive Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_1", 8, 1), // Olive Leaves
                OLIVE.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_0", 14, 1), // Nutmeg Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_1", 12, 1), // Nutmeg Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_1", 12, 1), // Nutmeg Leaves
                NUTMEG.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_1", 0, 1), // Coconut Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_2", 0, 1), // Coconut Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_2", 0, 1), // Coconut Leaves
                COCONUT.stackForm)

            addGreenhouseGrowthing(
                Mods.GregTechLiteCore.getMetaItem("gtlite_sapling_1", 2, 1), // Rainbow Sapling
                Mods.GregTechLiteCore.getMetaItem("gtlite_log_2", 4, 1), // Rainbow Logs
                Mods.GregTechLiteCore.getMetaItem("gtlite_leaves_2", 4, 1), // Coconut Leaves
                RainbowSap)

            // =========================================================================================================
            // TODO Crops

        }

        private fun addGreenhouseGrowthing(saplingStack: ItemStack, logStack: ItemStack,
                                           leaveStack: ItemStack)
        {
            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 4))
                .outputs(GTLiteUtility.copy(leaveStack, 8))
                .outputs(saplingStack)
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 8))
                .outputs(GTLiteUtility.copy(leaveStack, 16))
                .outputs(GTLiteUtility.copy(saplingStack, 4))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()
        }

        private fun addGreenhouseGrowthing(saplingStack: ItemStack, logStack: ItemStack,
                                           leaveStack: ItemStack, appleStack: ItemStack)
        {
            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 4))
                .outputs(GTLiteUtility.copy(leaveStack, 8))
                .outputs(saplingStack)
                .chancedOutput(appleStack, 2500, 250)
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .outputs(appleStack)
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .outputs(GTLiteUtility.copy(appleStack, 2))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 8))
                .outputs(GTLiteUtility.copy(leaveStack, 16))
                .outputs(GTLiteUtility.copy(saplingStack, 4))
                .outputs(GTLiteUtility.copy(appleStack, 4))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()
        }

        private fun addGreenhouseGrowthing(saplingStack: ItemStack, logStack: ItemStack,
                                           leaveStack: ItemStack, sap: Material)
        {
            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 4))
                .outputs(GTLiteUtility.copy(leaveStack, 8))
                .outputs(saplingStack)
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 8))
                .outputs(GTLiteUtility.copy(leaveStack, 16))
                .outputs(GTLiteUtility.copy(saplingStack, 4))
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()
        }

        private fun addGreenhouseGrowthing(saplingStack: ItemStack, logStack: ItemStack,
                                           leaveStack: ItemStack, appleStack: ItemStack,
                                           sap: Material)
        {
            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 4))
                .outputs(GTLiteUtility.copy(leaveStack, 8))
                .outputs(saplingStack)
                .chancedOutput(appleStack, 2500, 250)
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .outputs(appleStack)
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(Air.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 6))
                .outputs(GTLiteUtility.copy(leaveStack, 12))
                .outputs(GTLiteUtility.copy(saplingStack, 2))
                .outputs(GTLiteUtility.copy(appleStack, 2))
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            GREENHOUSE_RECIPES.recipeBuilder()
                .notConsumable(saplingStack)
                .input(FERTILIZER, 4)
                .notConsumable(GreenhouseGas.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTLiteUtility.copy(logStack, 8))
                .outputs(GTLiteUtility.copy(leaveStack, 16))
                .outputs(GTLiteUtility.copy(saplingStack, 4))
                .outputs(GTLiteUtility.copy(appleStack, 4))
                .fluidOutputs(sap.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()
        }

    }

}