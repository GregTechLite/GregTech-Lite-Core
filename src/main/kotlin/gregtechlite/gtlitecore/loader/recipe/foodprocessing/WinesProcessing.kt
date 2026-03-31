package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AminooxyaceticAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GrapeJuice
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotatoJuice
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RedWine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vodka
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Yeast
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE_JUICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POTATO_JUICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RED_WINE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VODKA
import net.minecraft.init.Items.GLASS_BOTTLE
import net.minecraft.init.Items.POTATO

internal object WinesProcessing
{

    // @formatter:off

    fun init()
    {
        // Grape Juice
        MIXER_RECIPES.addRecipe {
            input("fruitGrape")
            fluidInputs(Water.getFluid(750))
            fluidOutputs(GrapeJuice.getFluid(750))
            EUt(4)
            duration(6 * SECOND + 8 * TICK)
        }

        CANNER_RECIPES.addRecipe {
            inputs(GLASS_BOTTLE)
            fluidInputs(GrapeJuice.getFluid(250))
            output(GRAPE_JUICE)
            EUt(4)
            duration(10 * TICK)
        }

        // Potato Juice
        MIXER_RECIPES.addRecipe {
            inputs(POTATO)
            fluidInputs(Water.getFluid(750))
            fluidOutputs(PotatoJuice.getFluid(750))
            EUt(4)
            duration(6 * SECOND + 8 * TICK)
        }

        CANNER_RECIPES.addRecipe {
            inputs(GLASS_BOTTLE)
            fluidInputs(PotatoJuice.getFluid(250))
            output(POTATO_JUICE)
            EUt(4)
            duration(10 * TICK)
        }

        // Red Wine
        FERMENTING_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Yeast)
            fluidInputs(GrapeJuice.getFluid(100))
            fluidOutputs(RedWine.getFluid(100))
            EUt(2)
            duration(2 * SECOND + 10 * TICK)
        }

        FERMENTING_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Yeast)
            fluidInputs(GrapeJuice.getFluid(100))
            fluidInputs(AminooxyaceticAcid.getFluid(50))
            fluidOutputs(RedWine.getFluid(400))
            EUt(2)
            duration(15 * TICK)
        }

        CANNER_RECIPES.addRecipe {
            inputs(GLASS_BOTTLE)
            fluidInputs(RedWine.getFluid(250))
            output(RED_WINE)
            EUt(4)
            duration(10 * TICK)
        }

        // Vodka
        DISTILLERY_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(PotatoJuice.getFluid(50))
            fluidOutputs(Vodka.getFluid(25))
            EUt(VH[LV])
            duration(16 * TICK)
        }

        CANNER_RECIPES.addRecipe {
            inputs(GLASS_BOTTLE)
            fluidInputs(Vodka.getFluid(250))
            output(VODKA)
            EUt(4)
            duration(10 * TICK)
        }
    }

    // @formatter:on

}