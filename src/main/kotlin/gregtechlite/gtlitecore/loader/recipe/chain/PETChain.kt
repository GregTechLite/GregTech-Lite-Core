package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Water
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimethylTerephthalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneGlycol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylparatoluate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaToluicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaXylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyethyleneTerephthalate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object PETChain
{

    // @formatter:off

    fun init()
    {
        // C6H4(CH3)2 + 2O -> C8H8O2 + 2H
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(ParaXylene.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(2000))
            .fluidOutputs(ParaToluicAcid.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // C8H8O2 + CH4O -> C9H10O2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(ParaToluicAcid.getFluid(1000))
            .fluidInputs(Methanol.getFluid(1000))
            .fluidOutputs(Methylparatoluate.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C9H10O2 + CO2 -> C10H10O4
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Methylparatoluate.getFluid(1000))
            .fluidInputs(CarbonDioxide.getFluid(1000))
            .fluidOutputs(DimethylTerephthalate.getFluid(1000))
            .EUt(VA[EV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C10H10O4 + C2H6O2 -> 2C10H6O4 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(DimethylTerephthalate.getFluid(2592))
            .fluidInputs(EthyleneGlycol.getFluid(1000))
            .fluidOutputs(PolyethyleneTerephthalate.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[UV])
            .duration(4 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}