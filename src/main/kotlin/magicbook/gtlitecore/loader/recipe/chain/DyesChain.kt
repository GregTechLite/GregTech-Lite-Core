package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.ChromiumTrioxide
import gregtech.api.unification.material.Materials.CobaltOxide
import gregtech.api.unification.material.Materials.DilutedSulfuricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.NitrationMixture
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BurntSienna
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiaminostilbenedisulfonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Erythrosine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorescein
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadChromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ManganeseMonoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nitrotoluene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhthalicAnhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resorcinol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sienna
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumHypochlorite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class DyesChain
{

    companion object
    {

        fun init()
        {
            siennaProcess() // Brown & Red
            leadAcidateProcess() // Yellow & White
            cobaltAluminateProcess() // Blue
            dsdaProcess() // White
            fluoresceinErythrosineProcess() // Yellow & Red
        }

        private fun siennaProcess()
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

        private fun leadAcidateProcess()
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

        private fun cobaltAluminateProcess()
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

        private fun dsdaProcess()
        {
            // C7H8 + 2(HNO3)(H2SO4) + H2SO4 -> C7H7NO2 + 3H2SO4
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Nitrotoluene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // NaClO + C7H7NO2 + 2H2SO4 -> 0.5C14H14N2O6S2 + NaCl + 4H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Zinc)
                .input(dust, SodiumHypochlorite, 3)
                .fluidInputs(Nitrotoluene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .output(dust, DiaminostilbenedisulfonicAcid, 19) // 38 / 2
                .output(dust, Salt, 2)
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[EV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()
        }

        private fun fluoresceinErythrosineProcess()
        {
            // C6H4(CO)2O + 2C6H6O2 -> C20H12O5 + 2H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Resorcinol.getFluid(2000))
                .output(dust, Fluorescein, 37)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(11 * SECOND + 5 * TICK)
                .buildAndRegister()

            // C20H12O5 + 2NaOH + 4I -> C20H6O5Na2I4 + 4H + 2H2O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Fluorescein, 37)
                .input(dust, SodiumHydroxide, 6)
                .input(dust, Iodine, 4)
                .output(dust, Erythrosine, 37)
                .fluidOutputs(Hydrogen.getFluid(4000))
                .fluidOutputs(Steam.getFluid(2000))
                .EUt(VHA[HV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

    }

}