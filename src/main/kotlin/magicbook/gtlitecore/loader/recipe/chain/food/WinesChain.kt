package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Water
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GrapeJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RedWine
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GRAPE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GRAPE_JUICE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RED_WINE
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

            // Red Wine
            FERMENTING_RECIPES.recipeBuilder()
                .fluidInputs(GrapeJuice.getFluid(100))
                .fluidOutputs(RedWine.getFluid(100))
                .EUt(2)
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.GLASS_BOTTLE))
                .fluidInputs(RedWine.getFluid(250))
                .output(RED_WINE)
                .EUt(4)
                .duration(10 * TICK)
                .buildAndRegister()

        }

    }

}