package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthStrontiumCalciumCuprate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Strontianite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE

class BSCCOChain
{

    companion object
    {

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
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

    }

}