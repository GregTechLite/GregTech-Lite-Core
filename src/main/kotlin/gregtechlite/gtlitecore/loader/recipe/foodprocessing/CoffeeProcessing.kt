package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Paper
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.PLANT_BALL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Coffee
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CERAMIC_CUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_CHERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_CUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PAPER_CONE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.DRIED_LARGE_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.DRIED_SMALL_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.FERMENTED_LARGE_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.FERMENTED_SMALL_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.GROUND_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.LARGE_GREEN_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.ROASTED_LARGE_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.ROASTED_SMALL_COFFEE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.SMALL_GREEN_COFFEE
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object CoffeeProcessing
{

    // @formatter:off

    fun init()
    {
        // Coffee cherry -> Coffee seed
        ModHandler.addShapelessRecipe("coffee_seed", COFFEE_SEED.stackForm,
            COFFEE_CHERRY.getStackForm(6))

        MACERATOR_RECIPES.recipeBuilder()
            .input(COFFEE_CHERRY)
            .output(COFFEE_SEED, 6)
            .chancedOutput(COFFEE_SEED, 2, 5000, 1500)
            .chancedOutput(COFFEE_SEED, 7500, 2000)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // Coffee cherry decomposition.
        // Corn cob decomposition.
        COMPRESSOR_RECIPES.recipeBuilder()
            .inputs(COFFEE_CHERRY.getStackForm(8))
            .output(PLANT_BALL)
            .EUt(2) // ULV
            .duration(15 * SECOND)
            .buildAndRegister()

        // Coffee seed -> Large Green Coffee, Small Green Coffee
        SIFTER_RECIPES.recipeBuilder()
            .input(COFFEE_SEED, 32)
            .outputs(LARGE_GREEN_COFFEE.getStack(8))
            .outputs(SMALL_GREEN_COFFEE.getStack(16))
            .chancedOutput(LARGE_GREEN_COFFEE.getStack(2), 4500, 1250)
            .chancedOutput(SMALL_GREEN_COFFEE.getStack(6), 6500, 2500)
            .EUt(VA[LV])
            .duration(25 * SECOND)
            .buildAndRegister()

        // Large/Small Green Coffee -> Fermented Large/Small Coffee
        FERMENTING_RECIPES.recipeBuilder()
            .inputs(LARGE_GREEN_COFFEE.stack())
            .fluidInputs(Water.getFluid(500))
            .outputs(FERMENTED_LARGE_COFFEE.stack())
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        FERMENTING_RECIPES.recipeBuilder()
            .inputs(SMALL_GREEN_COFFEE.stack())
            .fluidInputs(Water.getFluid(250))
            .outputs(FERMENTED_SMALL_COFFEE.stack())
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Fermented Large/Small Coffee -> Dried Large/Small Coffee
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .inputs(FERMENTED_LARGE_COFFEE.getStack(8))
            .outputs(DRIED_LARGE_COFFEE.getStack(8))
            .fluidOutputs(Water.getFluid(4000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .inputs(FERMENTED_SMALL_COFFEE.getStack(16))
            .outputs(DRIED_SMALL_COFFEE.getStack(16))
            .fluidOutputs(Water.getFluid(4000))
            .EUt(VA[LV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // Dried Large/Small Coffee -> Roasted Large/Small Coffee
        ROASTER_RECIPES.recipeBuilder()
            .inputs(DRIED_LARGE_COFFEE.getStack(8))
            .outputs(ROASTED_LARGE_COFFEE.getStack(8))
            .EUt(VA[LV])
            .duration(12 * SECOND + 10 * TICK)
            .buildAndRegister()

        ROASTER_RECIPES.recipeBuilder()
            .inputs(DRIED_SMALL_COFFEE.getStack(16))
            .outputs(ROASTED_SMALL_COFFEE.getStack(16))
            .EUt(VA[LV])
            .duration(12 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Roasted Large/Small Coffee -> Ground Coffee
        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ROASTED_LARGE_COFFEE.stack())
            .outputs(GROUND_COFFEE.getStack(2))
            .EUt(7) // ULV
            .duration(10 * TICK)
            .buildAndRegister()

        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ROASTED_SMALL_COFFEE.stack())
            .outputs(GROUND_COFFEE.stack())
            .EUt(7) // ULV
            .duration(10 * TICK)
            .buildAndRegister()

        // Paper Cone
        ModHandler.addShapedRecipe(true, "paper_cone", PAPER_CONE.getStackForm(4),
            "   ", "pPr", " k ",
            'P', ItemStack(Items.PAPER))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .inputs(ItemStack(Items.PAPER))
            .output(PAPER_CONE, 4)
            .EUt(7) // ULV
            .duration(5 * SECOND)
            .buildAndRegister()

        BURNER_REACTOR_RECIPES.recipeBuilder()
            .inputs(GROUND_COFFEE.stack())
            .input(PAPER_CONE)
            .fluidInputs(DistilledWater.getFluid(1000))
            .output(dust, Paper)
            .fluidOutputs(Coffee.getFluid(100))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        CANNER_RECIPES.recipeBuilder()
            .input(CERAMIC_CUP)
            .fluidInputs(Coffee.getFluid(100))
            .output(COFFEE_CUP)
            .EUt(VH[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}