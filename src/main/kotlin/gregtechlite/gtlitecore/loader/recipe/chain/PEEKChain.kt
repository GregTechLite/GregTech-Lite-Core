package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Acetone
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Difluorobenzophenone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorobenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorotoluene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydroquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resorcinol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumFluoride

internal object PEEKChain
{

    // @formatter:off

    fun init()
    {
        // C7H8 + 2F -> C7H7F + HF
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Toluene.getFluid(1000))
            .fluidInputs(Fluorine.getFluid(2000))
            .fluidOutputs(Fluorotoluene.getFluid(1000))
            .fluidOutputs(HydrofluoricAcid.getFluid(1000))
            .EUt(VA[HV])
            .duration(6 * SECOND)
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
            .EUt(VA[EV])
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
            .EUt(VA[EV])
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
            .EUt(VA[ZPM])
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
            .EUt(VA[UHV])
            .duration(24 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}