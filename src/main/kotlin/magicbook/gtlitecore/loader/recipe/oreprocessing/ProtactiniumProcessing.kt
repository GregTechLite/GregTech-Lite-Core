package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AminooxyaceticAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiethylEther
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThoriumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UranylNitrate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

/**
 * Produces Protactinium and Thorium by UO2(NO3)2.
 * - Main Products: Protactinium, Thorium.
 * - Side Products: None.
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class ProtactiniumProcessing
{

    companion object
    {

        fun init()
        {
            // UO2(NO3)2 + (C2H5)2O -> 0.5UO2 + Pa + 0.75ThO2 + 2C2H5NO3
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, UranylNitrate, 10)
                .fluidInputs(DiethylEther.getFluid(1000))
                .output(dust, Uraninite)
                .output(dust, Protactinium)
                .output(dustSmall, ThoriumDioxide, 3)
                .fluidOutputs(AminooxyaceticAcid.getFluid(1000))
                .EUt(VA[ZPM].toLong())
                .duration(14 * SECOND)
                .buildAndRegister()

        }

    }

}