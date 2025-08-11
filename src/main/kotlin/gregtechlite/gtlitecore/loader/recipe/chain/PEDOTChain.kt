package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Styrene
import gregtech.api.unification.material.Materials.SulfurTrioxide
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diacetyl
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Edot
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneGlycol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotPSS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotTMA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polystyrene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolystyreneSulfonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SulfurDichloride
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object PEDOTChain
{

    // @formatter:off

    fun init()
    {
        edotProcess()
        pedotProcess()
        polystyreneSulfonateProcess()
    }

    private fun edotProcess()
    {
        // 1,4-C4H10O2 -> C4H6O2 + 4H
        // This C4H6O2 is not γ-Butyrolactone, they have same formula.
        ELECTROLYZER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Butanediol.getFluid(1000))
            .output(dust, Carbon, 4)
            .fluidOutputs(Hydrogen.getFluid(10000))
            .fluidOutputs(Oxygen.getFluid(2000))
            .EUt(VHA[MV])
            .duration(4 * SECOND + 16 * TICK)
            .buildAndRegister()

        ELECTROLYZER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(Butanediol.getFluid(1000))
            .fluidOutputs(Diacetyl.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(4000))
            .EUt(VA[MV])
            .duration(4 * SECOND + 16 * TICK)
            .buildAndRegister()

        // C4H6O2 + C2H6O2 + SCl2 -> C6H6O2S + 2HCl + 2H2O (lost)
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Diacetyl.getFluid(1000))
            .fluidInputs(EthyleneGlycol.getFluid(1000))
            .fluidInputs(SulfurDichloride.getFluid(1000))
            .fluidOutputs(Edot.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun polystyreneSulfonateProcess()
    {
        // C8H8 -> ...-[-C8H8-C8H8-C8H8-]-...
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Styrene.getFluid(L))
            .fluidInputs(Air.getFluid(1000))
            .fluidOutputs(Polystyrene.getFluid(L))
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Styrene.getFluid(L))
            .fluidInputs(Oxygen.getFluid(1000))
            .fluidOutputs(Polystyrene.getFluid(216))
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .fluidInputs(Styrene.getFluid(2160))
            .fluidInputs(Air.getFluid(7500))
            .fluidInputs(TitaniumTetrachloride.getFluid(100))
            .fluidOutputs(Polystyrene.getFluid(3240))
            .EUt(VA[LV])
            .duration(40 * SECOND)
            .buildAndRegister()

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .fluidInputs(Styrene.getFluid(2160))
            .fluidInputs(Oxygen.getFluid(7500))
            .fluidInputs(TitaniumTetrachloride.getFluid(100))
            .fluidOutputs(Polystyrene.getFluid(4320))
            .EUt(VA[LV])
            .duration(40 * SECOND)
            .buildAndRegister()

        // C8H8 + SO3 -> C8H8SO3
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Polystyrene.getFluid(L))
            .fluidInputs(SulfurTrioxide.getFluid(1000))
            .fluidOutputs(PolystyreneSulfonate.getFluid(L))
            .EUt(VA[HV])
            .duration(8 * SECOND)
            .buildAndRegister()
    }

    private fun pedotProcess()
    {
        // C6H6O2S + C8H8SO3 -> (C6H6O2S)(C8H8SO3)
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Edot.getFluid(1000))
            .fluidInputs(PolystyreneSulfonate.getFluid(L))
            .fluidOutputs(PedotPSS.getFluid(L * 9))
            .EUt(VA[LuV])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // C6H6O2S + C5H8O2 -> (C6H6O2S)(C5H8O2)
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Edot.getFluid(1000))
            .fluidInputs(Polymethylmethacrylate.getFluid(L))
            .fluidOutputs(PedotTMA.getFluid(L * 9))
            .EUt(VA[ZPM])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}