package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthStrontiumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Strontianite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tenorite
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.extension.EUt

internal object BSCCOChain
{

    // @formatter:off

    fun init()
    {
        // Bi2O3 + 2SrCO3 + CaCO3 + 2CuO -> Bi2Sr2CaCu2O8 (Loss of O and CO2)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, BismuthTrioxide, 5)
            .input(dust, Strontianite, 10)
            .input(dust, Calcite, 5)
            .input(dust, Tenorite, 4)
            .output(dust, BismuthStrontiumCalciumCuprate, 15)
            .EUt(VA[ZPM])
            .duration(1 * MINUTE)
            .buildAndRegister()
    }

    // @formatter:on

}