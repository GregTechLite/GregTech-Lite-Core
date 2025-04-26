package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AminooxyaceticAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GrapeJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotatoJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RedWine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vodka
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Yeast
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GRAPE_JUICE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.POTATO_JUICE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RED_WINE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VODKA
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class WinesChain
{

    companion object
    {

        fun init()
        {
            // Grape Juice
            MIXER_RECIPES.recipeBuilder()
                .input("fruitGrape")
                .fluidInputs(Water.getFluid(750))
                .fluidOutputs(GrapeJuice.getFluid(750))
                .EUt(4)
                .duration(6 * SECOND + 8 * TICK)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.GLASS_BOTTLE))
                .fluidInputs(GrapeJuice.getFluid(250))
                .output(GRAPE_JUICE)
                .EUt(4)
                .duration(10 * TICK)
                .buildAndRegister()

            // Potato Juice
            MIXER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.POTATO))
                .fluidInputs(Water.getFluid(750))
                .fluidOutputs(PotatoJuice.getFluid(750))
                .EUt(4)
                .duration(6 * SECOND + 8 * TICK)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.GLASS_BOTTLE))
                .fluidInputs(PotatoJuice.getFluid(250))
                .output(POTATO_JUICE)
                .EUt(4)
                .duration(10 * TICK)
                .buildAndRegister()

            // Red Wine
            FERMENTING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Yeast)
                .fluidInputs(GrapeJuice.getFluid(100))
                .fluidOutputs(RedWine.getFluid(100))
                .EUt(2)
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            FERMENTING_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Yeast)
                .fluidInputs(GrapeJuice.getFluid(100))
                .fluidInputs(AminooxyaceticAcid.getFluid(50))
                .fluidOutputs(RedWine.getFluid(400))
                .EUt(2)
                .duration(15 * TICK)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.GLASS_BOTTLE))
                .fluidInputs(RedWine.getFluid(250))
                .output(RED_WINE)
                .EUt(4)
                .duration(10 * TICK)
                .buildAndRegister()

            // Vodka
            DISTILLERY_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(PotatoJuice.getFluid(50))
                .fluidOutputs(Vodka.getFluid(25))
                .EUt(VH[LV].toLong())
                .duration(16 * TICK)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.GLASS_BOTTLE))
                .fluidInputs(Vodka.getFluid(250))
                .output(VODKA)
                .EUt(4)
                .duration(10 * TICK)
                .buildAndRegister()

        }

    }

}