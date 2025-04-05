package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.SteelMagnetic
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicZirconia
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Prasiolite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class MagnetoResonaticChain
{

    companion object
    {

        fun init()
        {
            // Zr + 2O -> c-ZrO2
            CVD_RECIPES.recipeBuilder()
                .input(dust, Zirconium)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(gem, CubicZirconia)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .temperature(1132)
                .buildAndRegister()

            // 2Bi + 3Te -> Bi2Te3
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Tellurium, 3)
                .input(dust, Bismuth, 2)
                .output(dust, BismuthTelluride, 5)
                .EUt(VA[MV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister();

            // 4Bi2Te3 + 3(SiO2)5Fe + c-ZrO2 + Fe -> (Bi2Te3)4((SiO2)5Fe)3(ZrO2)Fe
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, BismuthTelluride, 4)
                .input(dust, Prasiolite, 3)
                .input(dust, CubicZirconia, 1)
                .input(dust, SteelMagnetic, 1)
                .output(dust, MagnetoResonatic, 9)
                .EUt(VA[EV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()
        }

    }

}