package magicbook.gtlitecore.loader.recipe.chain.food

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Coffee
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CERAMIC_CUP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COFFEE_CHERRY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COFFEE_CUP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COFFEE_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PAPER_CONE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.DRIED_LARGE_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.DRIED_SMALL_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.FERMENTED_LARGE_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.FERMENTED_SMALL_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.GROUND_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.LARGE_GREEN_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.ROASTED_LARGE_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.ROASTED_SMALL_COFFEE
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.SMALL_GREEN_COFFEE
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class CoffeeChain
{

    companion object
    {

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
                .EUt(VA[LV].toLong())
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
                .outputs(LARGE_GREEN_COFFEE.getItemStack(8))
                .outputs(SMALL_GREEN_COFFEE.getItemStack(16))
                .chancedOutput(LARGE_GREEN_COFFEE.getItemStack(2), 4500, 1250)
                .chancedOutput(SMALL_GREEN_COFFEE.getItemStack(6), 6500, 2500)
                .EUt(VA[LV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Large/Small Green Coffee -> Fermented Large/Small Coffee
            FERMENTING_RECIPES.recipeBuilder()
                .inputs(LARGE_GREEN_COFFEE.itemStack)
                .fluidInputs(Water.getFluid(500))
                .outputs(FERMENTED_LARGE_COFFEE.itemStack)
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            FERMENTING_RECIPES.recipeBuilder()
                .inputs(SMALL_GREEN_COFFEE.itemStack)
                .fluidInputs(Water.getFluid(250))
                .outputs(FERMENTED_SMALL_COFFEE.itemStack)
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Fermented Large/Small Coffee -> Dried Large/Small Coffee
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(FERMENTED_LARGE_COFFEE.getItemStack(8))
                .outputs(DRIED_LARGE_COFFEE.getItemStack(8))
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(FERMENTED_SMALL_COFFEE.getItemStack(16))
                .outputs(DRIED_SMALL_COFFEE.getItemStack(16))
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Dried Large/Small Coffee -> Roasted Large/Small Coffee
            ROASTER_RECIPES.recipeBuilder()
                .inputs(DRIED_LARGE_COFFEE.getItemStack(8))
                .outputs(ROASTED_LARGE_COFFEE.getItemStack(8))
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND + 10 * TICK)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .inputs(DRIED_SMALL_COFFEE.getItemStack(16))
                .outputs(ROASTED_SMALL_COFFEE.getItemStack(16))
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Roasted Large/Small Coffee -> Ground Coffee
            MACERATOR_RECIPES.recipeBuilder()
                .inputs(ROASTED_LARGE_COFFEE.itemStack)
                .outputs(GROUND_COFFEE.getItemStack(2))
                .EUt(7) // ULV
                .duration(10 * TICK)
                .buildAndRegister()

            MACERATOR_RECIPES.recipeBuilder()
                .inputs(ROASTED_SMALL_COFFEE.itemStack)
                .outputs(GROUND_COFFEE.itemStack)
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
                .inputs(GROUND_COFFEE.itemStack)
                .input(PAPER_CONE)
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dust, Paper)
                .fluidOutputs(Coffee.getFluid(100))
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .input(CERAMIC_CUP)
                .fluidInputs(Coffee.getFluid(100))
                .output(COFFEE_CUP)
                .EUt(VH[LV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

        }

    }

}