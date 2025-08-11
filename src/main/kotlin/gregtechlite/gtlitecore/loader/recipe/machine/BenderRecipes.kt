package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_EMPTY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FUEL_ROD_EMPTY

internal object BenderRecipes
{

    // @formatter:off

    fun init()
    {
        // Casting Mold (Empty)
        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(plate, VanadiumSteel, 4)
            .output(CASTING_MOLD_EMPTY)
            .EUt(12) // LV
            .duration(9 * SECOND)
            .buildAndRegister()

        // Empty Fuel Rod
        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, Iron)
            .output(FUEL_ROD_EMPTY)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, WroughtIron)
            .output(FUEL_ROD_EMPTY)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, Steel)
            .output(FUEL_ROD_EMPTY, 2)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, VanadiumSteel)
            .output(FUEL_ROD_EMPTY, 4)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, BlueSteel)
            .output(FUEL_ROD_EMPTY, 8)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, HSSG)
            .output(FUEL_ROD_EMPTY, 16)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, HSSE)
            .output(FUEL_ROD_EMPTY, 32)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        BENDER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(plateDouble, HSSS)
            .output(FUEL_ROD_EMPTY, 64)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}