package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Glue
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import gregtech.common.items.MetaItems.STICKY_RESIN
import gregtech.loaders.WoodTypeEntry
import gregtech.loaders.recipe.WoodRecipeLoader
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SAP_COLLECTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resin
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.Mods
import net.minecraft.init.Blocks

@Suppress("MISSING_DEPENDENCY_CLASS")
class WoodRecipeLoader
{

    companion object
    {

        private var DEFAULT_ENTRIES: MutableList<WoodTypeEntry>? = null
        
        fun init()
        {
            // Sap collector compatibilities.
            registerWoodSapCollections()
            registerWoodSapProcessings()

            for (entry in getDefaultEntries())
            {
                WoodRecipeLoader.registerWoodTypeRecipe(entry)
            }

            // Stairs. TODO Why GregTech WoodTypeEntry cannot generate these recipes? idk...
            ModHandler.addShapedRecipe(true, "banana_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_banana", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 0))

            ModHandler.addShapedRecipe(true, "orange_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_orange", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 1))

            ModHandler.addShapedRecipe(true, "mango_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_mango", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 2))

            ModHandler.addShapedRecipe(true, "apricot_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_apricot", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 3))

            ModHandler.addShapedRecipe(true, "lemon_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_lemon", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 4))

            ModHandler.addShapedRecipe(true, "lime_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_lime", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 5))

            ModHandler.addShapedRecipe(true, "olive_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_olive", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 6))

            ModHandler.addShapedRecipe(true, "nutmeg_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_nutmeg", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 7))

            ModHandler.addShapedRecipe(true, "coconut_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_coconut", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 8))

            ModHandler.addShapedRecipe(true, "rainbow_wooden_stair", Mods.GregTechLiteCore.getItem("gtlite_wood_stair_rainbow", 4),
                "B  ", "BB ", "BBB",
                'B', Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 9))

        }

        fun initUnificationInfos()
        {
            for (entry in getDefaultEntries())
            {
                WoodRecipeLoader.registerWoodUnificationInfo(entry)
            }
        }

        private fun registerWoodSapCollections()
        {
            // Vanilla logs.
            SAP_COLLECTOR_RECIPES.recipeBuilder()
                .notConsumable(DistilledWater.getFluid(10))
                .fluidOutputs(Resin.getFluid(100))
                .EUt(VA[ULV].toLong())
                .duration(1 * SECOND)
                .blockStates("common",
                    arrayListOf(Blocks.LOG.getStateFromMeta(0),
                                Blocks.LOG.getStateFromMeta(1),
                                Blocks.LOG.getStateFromMeta(2),
                                Blocks.LOG.getStateFromMeta(3),
                                Blocks.LOG2.getStateFromMeta(0),
                                Blocks.LOG2.getStateFromMeta(1)))
                .buildAndRegister()
        }

        private fun registerWoodSapProcessings()
        {
            // Deleted raw rubber processings.

            // Resin processing.
            DISTILLERY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Resin.getFluid(100))
                .fluidOutputs(Glue.getFluid(75))
                .EUt(VA[LV].toLong())
                .duration(15 * TICK)
                .buildAndRegister()

            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BALL)
                .fluidInputs(Resin.getFluid(250))
                .output(STICKY_RESIN)
                .EUt(2) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // other processing see: magicbook.gtlitecore.loaders.recipe.circuit.ElectronicCircuits#circuitBoardRecipes().
        }

        // Hint: meta of log_(n) is 4x (0, 4, 8, 12) and then used log_(n+1).
        private fun getDefaultEntries(): MutableList<WoodTypeEntry>
        {
            // See: GATrees#Init() and GAMetaBlocks#Init().
            // Used Mods#GetItem() or Mods#GetMetaItem() to resolve conflicts of lateinit var.
            return DEFAULT_ENTRIES ?: run {
                mutableListOf(
                    // Banana Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "banana").apply {
                        log(Mods.GregTechLiteCore.getItem("gtlite_log_0", 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getItem("gtlite_planks_0", 1), null)
                        slab(Mods.GregTechLiteCore.getItem("gtlite_wood_slab", 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_banana", 1), "banana_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_banana", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_banana", 1), null)
                    }.build(),
                    // Orange Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "orange").apply {
                        log(Mods.GregTechLiteCore.getMetaItem("gtlite_log_0", 4, 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 1, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 1, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_orange", 1), "orange_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_orange", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_orange", 1), null)
                    }.build(),
                    // Mango Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "mango").apply {
                        log(Mods.GregTechLiteCore.getMetaItem("gtlite_log_0", 8, 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 2, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 2, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_mango", 1), "mango_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_mango", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_mango", 1), null)
                    }.build(),
                    // Apricot Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "apricot").apply {
                        log(Mods.GregTechLiteCore.getMetaItem("gtlite_log_0", 12, 1))
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 3, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 3, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_apricot", 1), "apricot_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_apricot", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_apricot", 1), null)
                    }.build(),
                    // Lemon Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "lemon").apply {
                        log(Mods.GregTechLiteCore.getItem("gtlite_log_1", 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 4, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 4, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_lemon", 1), "lemon_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_lemon", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_lemon", 1), null)
                    }.build(),
                    // Lime Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "lime").apply {
                        log(Mods.GregTechLiteCore.getMetaItem("gtlite_log_1", 4, 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 5, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 5, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_lime", 1), "lime_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_lime", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_lime", 1), null)
                    }.build(),
                    // Olive Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "olive").apply {
                        log(Mods.GregTechLiteCore.getMetaItem("gtlite_log_1", 8, 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 6, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 6, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_olive", 1), "olive_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_olive", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_olive", 1), null)
                    }.build(),
                    // Nutmeg Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "nutmeg").apply {
                        log(Mods.GregTechLiteCore.getMetaItem("gtlite_log_1", 12, 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 7, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 7, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_nutmeg", 1), "nutmeg_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_nutmeg", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_nutmeg", 1), null)
                    }.build(),
                    // Coconut Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "coconut").apply {
                        log(Mods.GregTechLiteCore.getItem("gtlite_log_2", 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 8, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 8, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_coconut", 1), "coconut_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_coconut", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_coconut", 1), null)
                    }.build(),
                    // Rainbow Tree.
                    WoodTypeEntry.Builder(GTLiteValues.MODID, "rainbow").apply {
                        log(Mods.GregTechLiteCore.getMetaItem("gtlite_log_2", 4, 1))
                            .removeCharcoalRecipe()
                        planks(Mods.GregTechLiteCore.getMetaItem("gtlite_planks_0", 9, 1), null)
                        slab(Mods.GregTechLiteCore.getMetaItem("gtlite_wood_slab", 9, 1), null)
                        stairs(Mods.GregTechLiteCore.getItem("gtlite_wood_stair_rainbow", 1), "rainbow_wooden_stair")
                        fence(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_rainbow", 1), null)
                        fenceGate(Mods.GregTechLiteCore.getItem("gtlite_wood_fence_gate_rainbow", 1), null)
                    }.build(),
                ).also { DEFAULT_ENTRIES = it }
            }

        }

    }

}