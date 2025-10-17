package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.PalladiumRaw
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PalladiumAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PalladiumNitrate

internal object PalladiumAcetateChain
{

    // @formatter:off

    fun init()
    {
        // Pd + 2HNO3 -> Pd(NO3)2 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Palladium)
            .fluidInputs(NitricAcid.getFluid(2000))
            .output(dust, PalladiumNitrate, 9)
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // PdCl2 + 2HNO3 -> Pd(NO3)2 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, PalladiumRaw, 3)
            .fluidInputs(NitricAcid.getFluid(2000))
            .output(dust, PalladiumNitrate, 9)
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Pd(NO3)2 + 2CH3COOH -> Pd(CH3COOH)2 + 2HNO3
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PalladiumNitrate, 9)
            .fluidInputs(AceticAcid.getFluid(2000))
            .output(dust, PalladiumAcetate, 15)
            .fluidOutputs(NitricAcid.getFluid(2000))
            .EUt(VA[EV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}