package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErbiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

class AlloySmelterRecipes
{

    companion object
    {

        fun init()
        {
            // Er-doped ZBLAN Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(ingot, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(dust, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(ingot, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(dust, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Pr-doped ZBLAN Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(ingot, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(dust, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(ingot, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(dust, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}