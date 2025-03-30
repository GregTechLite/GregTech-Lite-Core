package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.ChromiumTrioxide
import gregtech.api.unification.material.Materials.CobaltOxide
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BurntSienna
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadChromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ManganeseMonoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sienna
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class DyesChain
{

    companion object
    {

        fun init()
        {
            siennaProcessing() // Brown & Red
            leadAcidateProcessing() // Yellow & White
            cobaltAluminateProcessing() // Blue
        }

        private fun siennaProcessing()
        {

            // Mn + O -> MnO
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Manganese)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, ManganeseMonoxide, 2)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Mn + 2O -> MnO2
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Manganese)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, Pyrolusite, 3)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // MnO + Fe2O3 -> (MnO)(Fe2O3)
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, ManganeseMonoxide)
                .input(dust, BandedIron)
                .output(dust, Sienna, 2)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // (MnO)(Fe2O3) (Brown) -> (MnO)(Fe2O3) (Red)
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(dust, Sienna),
                OreDictUnifier.get(dust, BurntSienna))

        }

        private fun leadAcidateProcessing()
        {
            // PbO + CrO3 -> PbCrO4
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Massicot, 2)
                .input(dust, ChromiumTrioxide, 4)
                .output(dust, LeadChromate, 6)
                .EUt(VA[MV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // PbO + 2HNO3 -> Pb(NO3)2 + H2O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Massicot, 2)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust, LeadNitrate, 9)
                .fluidOutputs(Steam.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()
        }

        private fun cobaltAluminateProcessing()
        {
            // Al2O3 + CoO -> CoAl2O4
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Alumina, 5)
                .input(dust, CobaltOxide, 2)
                .output(dust, CobaltAluminate, 7)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}