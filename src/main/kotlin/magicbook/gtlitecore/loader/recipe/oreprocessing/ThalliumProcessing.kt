package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Galena
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.ingot
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Iron3Sulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumSulfate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

/**
 * Produces Thallium from Pyrite or Galena.
 * - Main Products: Thallium
 * - Side Products: None
 *
 * A raw product estimations for these Ore Processing:
 * - 3 Pyrite -> 2 Thallium
 * - 4 Galena -> 2 Thallium
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class ThalliumProcessing
{

    companion object
    {

        fun init()
        {
            // FeS2 -> Fe2(SO4)3 + S
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Pyrite)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Pyrite, 2)
                .output(dust, Iron3Sulfate)
                .output(dust, Sulfur)
                .EUt(VA[ULV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // FeS2 + 6O -> Fe + Tl2SO4 + SO2
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Pyrite, 3)
                .fluidInputs(Oxygen.getFluid(6000))
                .output(ingot, Iron)
                .output(dust, ThalliumSulfate, 7)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // PbS + 3O -> PbO + 1/3Ag + SO2
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Galena)),
                arrayOf(Oxygen.getFluid(3000)))

            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Galena)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, Massicot)
                .output(dustTiny, Silver, 6)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // 2PbS + 6O -> 2Pb + Tl2SO4 + SO2
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Galena, 4)
                .fluidInputs(Oxygen.getFluid(6000))
                .output(ingot, Lead, 2)
                .output(dust, ThalliumSulfate, 7)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Tl2SO4 + H2O -> 2Tl + H2SO4 + O
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, ThalliumSulfate, 7)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, Thallium, 2)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(VHA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}