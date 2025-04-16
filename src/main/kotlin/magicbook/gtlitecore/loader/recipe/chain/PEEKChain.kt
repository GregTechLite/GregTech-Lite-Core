package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Acetone
import gregtech.api.unification.material.Materials.AntimonyTrifluoride
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.FluoroantimonicAcid
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Difluorobenzophenone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorobenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorotoluene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hydroquinone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polyetheretherketone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resorcinol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumFluoride
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class PEEKChain
{

    companion object
    {

        fun init()
        {
            // C6H5F + H2SbF7 + CH4 -> SbF3 + C7H7F + 4HF
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Fluorobenzene.getFluid(1000))
                .fluidInputs(FluoroantimonicAcid.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .output(dust, AntimonyTrifluoride, 4)
                .fluidOutputs(Fluorotoluene.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(4000))
                .EUt(VA[HV].toLong())
                .duration(7 * SECOND)
                .buildAndRegister()

            // C6H5F + C7H7F + 6Cl + H2O -> (FC6H4)2CO + 6HCl
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Zinc)
                .fluidInputs(Fluorobenzene.getFluid(1000))
                .fluidInputs(Fluorotoluene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Water.getFluid(1000))
                .output(dust, Difluorobenzophenone, 24)
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // C3H6 + C6H6 + 3O -> C6H4(OH)2 + C6H6O2 + C3H6O
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(Hydroquinone.getFluid(1000))
                .fluidOutputs(Resorcinol.getFluid(1000))
                .fluidOutputs(Acetone.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // (FC6H4)2CO + Na2CO3 + C6H4(OH)2 -> C20H12O3 + 2NaF + CO2 + H2O
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Difluorobenzophenone, 24)
                .input(dust, SodaAsh, 6)
                .fluidInputs(Hydroquinone.getFluid(1000))
                .output(dust, SodiumFluoride, 4)
                .fluidOutputs(Polyetheretherketone.getFluid(L * 18))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[ZPM].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // Advanced recipes for PEEK, used C6H5F to skip (FC6H4)2CO
            // Na2CO3 + C6H4(OH)2 + 2C6H5F -> C20H12O3 + 2NaF + 2H2O
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, SodaAsh, 6)
                .fluidInputs(Hydroquinone.getFluid(1000))
                .fluidInputs(Fluorobenzene.getFluid(2000))
                .output(dust, SodiumFluoride, 4)
                .fluidOutputs(Polyetheretherketone.getFluid(L * 18))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[UHV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

        }

    }

}