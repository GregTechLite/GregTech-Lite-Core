package gregtechlite.gtlitecore.loader.recipe.producer

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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.GREENHOUSE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenhouseGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Latex
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RainbowSap
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resin
import gregtechlite.gtlitecore.common.block.adapter.LeaveBlock
import gregtechlite.gtlitecore.common.block.adapter.LogBlock
import gregtechlite.gtlitecore.common.block.adapter.SaplingBlock
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.APRICOT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ARTICHOKE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ARTICHOKE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AUBERGINE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AUBERGINE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BANANA
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BASIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BASIL_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BEAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BEAN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACKBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_CURRANT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_PEPPER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_PEPPER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLUEBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COCONUT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_CHERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CORN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CORN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COTTON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COTTON_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRANBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CUCUMBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CUCUMBER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELDERBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GARLIC_BULB
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GARLIC_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSERADISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSERADISH_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LEMON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LIME
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LINGONBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MANGO
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NUTMEG
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OLIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ONION
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ONION_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ORANGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OREGANO
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OREGANO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEA
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEA_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RASPBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RED_CURRANT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOYBEAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOY_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STRAWBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOMATO
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOMATO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_CURRANT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_GRAPE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_GRAPE_SEED
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

internal object GreenhouseRecipeProducer
{
    // @formatter:off

    /**
     * A sample frontend for Greenhouse Recipes.
     *
     * One recipe of greenhouse has 4 recipe types:
     * - Used Air as Greenhouse Gas
     * - Used Greenhouse Gas as Greenhouse Gas
     * - Used Air as Greenhouse Gas and used Fertilizer
     * - Used Greenhouse Gas and used Fertilizer
     */
    private sealed class RecipeFrontend(open val gas: FluidStack,
                                        open val fertilizer: Int,
                                        open val log: Int,
                                        open val leaves: Int,
                                        open val saplings: Int,
                                        open val duration: Int)
    {
        data object AirBasic                : RecipeFrontend(Air.getFluid(1000), 0, 4, 8, 1, 12)
        data object GreenhouseGasBasic      : RecipeFrontend(GreenhouseGas.getFluid(1000), 0, 6, 12, 2, 6)
        data object AirFertilized           : RecipeFrontend(Air.getFluid(1000), 4, 6, 12, 2, 6)
        data object GreenhouseGasFertilized : RecipeFrontend(GreenhouseGas.getFluid(1000), 4, 8, 16, 4, 3)

    }

    fun produce()
    {
        // Greenhouse Gas
        FLUID_HEATER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Air.getFluid(100))
            .fluidOutputs(GreenhouseGas.getFluid(100))
            .EUt(VA[MV])
            .duration(1 * SECOND + 5 * TICK)
            .buildAndRegister()

        // region Vanilla Trees Recipes
        addGreenhouseRecipes(ItemStack(Blocks.SAPLING, 1, 0), // Oak Sapling
            ItemStack(Blocks.LOG, 1, 0), // Oak Logs
            ItemStack(Blocks.LEAVES, 1, 0), // Oak Leaves
            ItemStack(Items.APPLE), // Apple
            Resin)

        addGreenhouseRecipes(ItemStack(Blocks.SAPLING, 1, 1), // Spruce Sapling
            ItemStack(Blocks.LOG, 1, 1), // Spruce Logs
            ItemStack(Blocks.LEAVES, 1, 1), // Spruce Leaves
            ItemStack(Items.APPLE), // Apple
            Resin)

        addGreenhouseRecipes(ItemStack(Blocks.SAPLING, 1, 2), // Birch Sapling
            ItemStack(Blocks.LOG, 1, 2), // Birch Logs
            ItemStack(Blocks.LEAVES, 1, 2), // Birch Leaves
            ItemStack(Items.APPLE), // Apple
            Resin)

        addGreenhouseRecipes(ItemStack(Blocks.SAPLING, 1, 3), // Jungle Sapling
            ItemStack(Blocks.LOG, 1, 3), // Jungle Logs
            ItemStack(Blocks.LEAVES, 1, 3), // Jungle Leaves
            ItemStack(Items.DYE, 1, 3), // Coco
            Resin)

        addGreenhouseRecipes(ItemStack(Blocks.SAPLING, 1, 4), // Acacia Sapling
            ItemStack(Blocks.LOG2, 1, 0), // Acacia Logs
            ItemStack(Blocks.LEAVES2, 1, 0), // Acacia Leaves
            ItemStack(Items.APPLE), // Apple
            Resin)

        addGreenhouseRecipes(ItemStack(Blocks.SAPLING, 1, 5), // Dark Oak Sapling
            ItemStack(Blocks.LOG2, 1, 1), // Dark Oak Logs
            ItemStack(Blocks.LEAVES2, 1, 1), // Dark Oak Leaves
            ItemStack(Items.APPLE), // Apple
            Resin)

        addGreenhouseRecipes(ItemStack(MetaBlocks.RUBBER_SAPLING as Block, 1, 0), // Rubber Sapling
            ItemStack(MetaBlocks.RUBBER_LOG as Block, 1, 0), // Rubber Logs
            ItemStack(MetaBlocks.RUBBER_LEAVES as Block, 1, 0), // Rubber Leaves
            STICKY_RESIN.stackForm, // Sticky Resin
            Latex)

        // endregion

        // region Mod Additional Trees Recipes
        addGreenhouseRecipes(SaplingBlock.BANANA.stack, LogBlock.BANANA.stack,
                             LeaveBlock.BANANA.stack, BANANA.stackForm)

        addGreenhouseRecipes(SaplingBlock.ORANGE.stack, LogBlock.ORANGE.stack,
                             LeaveBlock.ORANGE.stack, ORANGE.stackForm)

        addGreenhouseRecipes(SaplingBlock.MANGO.stack, LogBlock.MANGO.stack,
                             LeaveBlock.MANGO.stack, MANGO.stackForm)

        addGreenhouseRecipes(SaplingBlock.APRICOT.stack, LogBlock.APRICOT.stack,
                             LeaveBlock.APRICOT.stack, APRICOT.stackForm)

        addGreenhouseRecipes(SaplingBlock.LEMON.stack, LogBlock.LEMON.stack,
                             LeaveBlock.LEMON.stack, LEMON.stackForm)

        addGreenhouseRecipes(SaplingBlock.LIME.stack, LogBlock.LIME.stack,
                             LeaveBlock.LIME.stack, LIME.stackForm)

        addGreenhouseRecipes(SaplingBlock.OLIVE.stack, LogBlock.OLIVE.stack,
                             LeaveBlock.OLIVE.stack, OLIVE.stackForm)

        addGreenhouseRecipes(SaplingBlock.NUTMEG.stack, LogBlock.NUTMEG.stack,
                             LeaveBlock.NUTMEG.stack, NUTMEG.stackForm)

        addGreenhouseRecipes(SaplingBlock.COCONUT.stack, LogBlock.COCONUT.stack,
                             LeaveBlock.COCONUT.stack, COCONUT.stackForm)

        addGreenhouseRecipes(SaplingBlock.RAINBOW.stack, LogBlock.RAINBOW.stack,
                             LeaveBlock.RAINBOW.stack, null, RainbowSap)

        // endregion

        // region Vanilla Crops Recipes

        // Wheat
        addGreenhouseRecipes(ItemStack(Items.WHEAT_SEEDS, 6),
            ItemStack(Items.WHEAT, 12),
            ItemStack(Items.WHEAT_SEEDS, 6))

        // Pumpkin
        addGreenhouseRecipes(ItemStack(Items.PUMPKIN_SEEDS, 6),
            ItemStack(Blocks.PUMPKIN, 12),
            ItemStack(Items.PUMPKIN_SEEDS, 6))

        // Melon
        addGreenhouseRecipes(ItemStack(Items.MELON_SEEDS, 6),
            ItemStack(Blocks.MELON_BLOCK, 12),
            ItemStack(Items.MELON_SEEDS, 6),
            ItemStack(Items.MELON))

        // Beetroot
        addGreenhouseRecipes(ItemStack(Items.BEETROOT_SEEDS, 6),
            ItemStack(Items.BEETROOT, 12),
            ItemStack(Items.BEETROOT_SEEDS, 6))

        // Coco
        addGreenhouseRecipes(ItemStack(Items.DYE, 6, 3),
            ItemStack(Items.DYE, 12, 3),
            ItemStack(Items.DYE, 6, 3))

        // endregion

        // region Mod Additional Crops Recipes

        // Coffee
        addGreenhouseRecipes(COFFEE_SEED.getStackForm(6),
            COFFEE_CHERRY.getStackForm(12),
            COFFEE_SEED.getStackForm(6))

        // Tomato
        addGreenhouseRecipes(TOMATO_SEED.getStackForm(6),
            TOMATO.getStackForm(12),
            TOMATO_SEED.getStackForm(6))

        // Onion
        addGreenhouseRecipes(ONION_SEED.getStackForm(6),
            ONION.getStackForm(12),
            ONION_SEED.getStackForm(6))

        // Cucumber
        addGreenhouseRecipes(CUCUMBER_SEED.getStackForm(6),
            CUCUMBER.getStackForm(12),
            CUCUMBER_SEED.getStackForm(6))

        // Grape
        addGreenhouseRecipes(GRAPE_SEED.getStackForm(6),
            GRAPE.getStackForm(12),
            GRAPE_SEED.getStackForm(6))

        // Soy
        addGreenhouseRecipes(SOY_SEED.getStackForm(6),
            SOYBEAN.getStackForm(12),
            SOY_SEED.getStackForm(6))

        // Bean
        addGreenhouseRecipes(BEAN_SEED.getStackForm(6),
            BEAN.getStackForm(12),
            BEAN_SEED.getStackForm(6))

        // Pea
        addGreenhouseRecipes(PEA_SEED.getStackForm(6),
            PEA.getStackForm(12),
            PEA_SEED.getStackForm(6))

        // Oregano
        addGreenhouseRecipes(OREGANO_SEED.getStackForm(6),
            OREGANO.getStackForm(12),
            OREGANO_SEED.getStackForm(6))

        // Horseradish
        addGreenhouseRecipes(HORSERADISH_SEED.getStackForm(6),
            HORSERADISH.getStackForm(12),
            HORSERADISH_SEED.getStackForm(6))

        // Garlic
        addGreenhouseRecipes(GARLIC_SEED.getStackForm(6),
            GARLIC_BULB.getStackForm(12),
            GARLIC_SEED.getStackForm(6))

        // Basil
        addGreenhouseRecipes(BASIL_SEED.getStackForm(6),
            BASIL.getStackForm(12),
            BASIL_SEED.getStackForm(6))

        // Aubergine
        addGreenhouseRecipes(AUBERGINE_SEED.getStackForm(6),
            AUBERGINE.getStackForm(12),
            AUBERGINE_SEED.getStackForm(6))

        // Corn
        addGreenhouseRecipes(CORN_SEED.getStackForm(6),
            CORN.getStackForm(12),
            CORN_SEED.getStackForm(6))

        // Artichoke
        addGreenhouseRecipes(ARTICHOKE_SEED.getStackForm(6),
            ARTICHOKE.getStackForm(12),
            ARTICHOKE_SEED.getStackForm(6))

        // Black pepper
        addGreenhouseRecipes(BLACK_PEPPER_SEED.getStackForm(6),
            BLACK_PEPPER.getStackForm(12),
            BLACK_PEPPER_SEED.getStackForm(6))

        // Rice
        addGreenhouseRecipes(RICE_SEED.getStackForm(6),
            RICE.getStackForm(12),
            RICE_SEED.getStackForm(6))

        // White grape
        addGreenhouseRecipes(WHITE_GRAPE_SEED.getStackForm(6),
            WHITE_GRAPE.getStackForm(12),
            WHITE_GRAPE_SEED.getStackForm(6))

        // Cotton
        addGreenhouseRecipes(COTTON_SEED.getStackForm(6),
            COTTON.getStackForm(12),
            COTTON_SEED.getStackForm(6))

        // Blueberry
        addGreenhouseRecipes(BLUEBERRY.getStackForm(6),
            BLUEBERRY.getStackForm(12),
            BLUEBERRY.getStackForm(6))

        // Blackberry
        addGreenhouseRecipes(BLACKBERRY.getStackForm(6),
            BLACKBERRY.getStackForm(12),
            BLACKBERRY.getStackForm(6))

        // Raspberry
        addGreenhouseRecipes(RASPBERRY.getStackForm(6),
            RASPBERRY.getStackForm(12),
            RASPBERRY.getStackForm(6))

        // Strawberry
        addGreenhouseRecipes(STRAWBERRY.getStackForm(6),
            STRAWBERRY.getStackForm(12),
            STRAWBERRY.getStackForm(6))

        // Red Currant
        addGreenhouseRecipes(RED_CURRANT.getStackForm(6),
            RED_CURRANT.getStackForm(12),
            RED_CURRANT.getStackForm(6))

        // Black Currant
        addGreenhouseRecipes(BLACK_CURRANT.getStackForm(6),
            BLACK_CURRANT.getStackForm(12),
            BLACK_CURRANT.getStackForm(6))

        // White Currant
        addGreenhouseRecipes(WHITE_CURRANT.getStackForm(6),
            WHITE_CURRANT.getStackForm(12),
            WHITE_CURRANT.getStackForm(6))

        // Lingonberry
        addGreenhouseRecipes(LINGONBERRY.getStackForm(6),
            LINGONBERRY.getStackForm(12),
            LINGONBERRY.getStackForm(6))

        // Elderberry
        addGreenhouseRecipes(ELDERBERRY.getStackForm(6),
            ELDERBERRY.getStackForm(12),
            ELDERBERRY.getStackForm(6))

        // Cranberry
        addGreenhouseRecipes(CRANBERRY.getStackForm(6),
            CRANBERRY.getStackForm(12),
            CRANBERRY.getStackForm(6))

        // endregion

    }

    private fun addGreenhouseRecipes(saplingStack: ItemStack,
                                     logStack: ItemStack,
                                     leavesStack: ItemStack,
                                     appleStack: ItemStack? = null,
                                     sap: Material? = null)
    {
        listOf(RecipeFrontend.AirBasic,
               RecipeFrontend.GreenhouseGasBasic,
               RecipeFrontend.AirFertilized,
               RecipeFrontend.GreenhouseGasFertilized)
            .forEach { frontend ->
                val builder = GREENHOUSE_RECIPES.recipeBuilder()
                    .notConsumable(saplingStack)
                    .notConsumable(frontend.gas)
                    .fluidInputs(Water.getFluid(1000))
                    .EUt(VA[LV])
                    .duration(frontend.duration * SECOND)

                if (frontend.fertilizer > 0)
                    builder.input(FERTILIZER, frontend.fertilizer)
                else
                    builder.circuitMeta(1)

                builder.outputs(logStack.copy(frontend.log),
                                leavesStack.copy(frontend.leaves),
                                saplingStack.copy(frontend.saplings))

                appleStack?.let { stack ->
                    when (frontend)
                    {
                        is RecipeFrontend.AirBasic -> builder.chancedOutput(stack, 2500, 250)
                        else -> builder.outputs(stack.copy(frontend.leaves / 8))
                    }
                }

                sap?.let {
                    builder.fluidOutputs(it.getFluid(250))
                }

                builder.buildAndRegister()
            }
    }

    // @formatter:on

}