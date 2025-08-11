package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Acetone
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AcetoneCyanohydrin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenCyanide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object PMMAChain
{

    // @formatter:off

    fun init()
    {
        // H + NH3 + CH4 -> HCN + 4H + 3H (lost)
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Hydrogen.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidInputs(Methane.getFluid(1000))
            .fluidOutputs(HydrogenCyanide.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(4000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 3CH4 + 3NH3 + 8O -> 3HCN + 8H2O + H (lost)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .notConsumable(dust, Platinum)
            .fluidInputs(Methane.getFluid(3000))
            .fluidInputs(Ammonia.getFluid(3000))
            .fluidInputs(Oxygen.getFluid(1000))
            .fluidOutputs(HydrogenCyanide.getFluid(3000))
            .fluidOutputs(Steam.getFluid(8000))
            .EUt(VA[HV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // (CH3)2CO + HCN -> C4H7NO
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Acetone.getFluid(1000))
            .fluidInputs(HydrogenCyanide.getFluid(1000))
            .fluidOutputs(AcetoneCyanohydrin.getFluid(2000))
            .EUt(VH[HV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C4H7NO + CH3OH -> C5H8O2 + NH3
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(AcetoneCyanohydrin.getFluid(2000))
            .fluidInputs(Methanol.getFluid(1000))
            .fluidOutputs(Polymethylmethacrylate.getFluid(L * 4))
            .fluidOutputs(Ammonia.getFluid(1000))
            .EUt(VH[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}