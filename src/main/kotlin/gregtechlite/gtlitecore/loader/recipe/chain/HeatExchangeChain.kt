package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.unification.material.Materials.DistilledWater
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.HOT_COOLANT_TURBINE_FUELS
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SUPERCRITICAL_FLUID_TURBINE_FUELS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSteam
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedSodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedSteam
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK

internal object HeatExchangeChain
{

    // @formatter:off

    fun init()
    {
        // 1L Water/DistilledWater -> 160L Steam

        // Superheated Steam
        // 320L SuperheatedSteam -> 320L Steam -> 2L DistilledWater
        HOT_COOLANT_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SuperheatedSteam.getFluid(320))
            .fluidOutputs(DistilledWater.getFluid(2))
            .EUt(V[MV])
            .duration(10 * TICK)
            .buildAndRegister()

        // Superheated Eutatic Sodium Potassium
        HOT_COOLANT_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SuperheatedSodiumPotassiumEutatic.getFluid(L))
            .fluidOutputs(SodiumPotassiumEutatic.getFluid(L))
            .EUt(V[HV])
            .duration(2 * SECOND + 16 * TICK)
            .buildAndRegister()

        // Superheated Eutatic Lead Bismuth
        HOT_COOLANT_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SuperheatedLeadBismuthEutatic.getFluid(L))
            .fluidOutputs(LeadBismuthEutatic.getFluid(L))
            .EUt(V[HV])
            .duration(2 * SECOND + 18 * TICK)
            .buildAndRegister()

        // Superheated Lithium Sodium Potassium Fluorides
        HOT_COOLANT_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SuperheatedLithiumSodiumPotassiumFluorides.getFluid(L))
            .fluidOutputs(LithiumSodiumPotassiumFluorides.getFluid(L))
            .EUt(V[EV])
            .duration(4 * SECOND + 12 * TICK)
            .buildAndRegister()

        // Superheated Lithium Beryllium Fluorides
        HOT_COOLANT_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SuperheatedLithiumBerylliumFluorides.getFluid(L))
            .fluidOutputs(LithiumBerylliumFluorides.getFluid(L))
            .EUt(V[EV])
            .duration(4 * SECOND + 8 * TICK)
            .buildAndRegister()

        // Supercritical Steam
        // 640L SupercriticalSteam -> 640L Steam -> 4L Distilled Water
        SUPERCRITICAL_FLUID_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SupercriticalSteam.getFluid(640))
            .fluidOutputs(DistilledWater.getFluid(4))
            .EUt(V[EV])
            .duration(10 * TICK)
            .buildAndRegister()

        // Supercritical Eutatic Sodium Potassium
        SUPERCRITICAL_FLUID_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SupercriticalSodiumPotassiumEutatic.getFluid(L))
            .fluidOutputs(SodiumPotassiumEutatic.getFluid(L))
            .EUt(V[IV])
            .duration(2 * SECOND + 12 * TICK)
            .buildAndRegister()

        // Supercritical Eutatic Lead Bismuth
        SUPERCRITICAL_FLUID_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SupercriticalLeadBismuthEutatic.getFluid(L))
            .fluidOutputs(LeadBismuthEutatic.getFluid(L))
            .EUt(V[IV])
            .duration(2 * SECOND + 18 * TICK)
            .buildAndRegister()

        // Supercritical Lithium Sodium Potassium Fluorides
        SUPERCRITICAL_FLUID_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SupercriticalLithiumSodiumPotassiumFluorides.getFluid(L))
            .fluidOutputs(LithiumSodiumPotassiumFluorides.getFluid(L))
            .EUt(V[LuV])
            .duration(4 * SECOND + 14 * TICK)
            .buildAndRegister()

        // Supercritical Lithium Beryllium Fluorides
        SUPERCRITICAL_FLUID_TURBINE_FUELS.recipeBuilder()
            .fluidInputs(SupercriticalLithiumBerylliumFluorides.getFluid(L))
            .fluidOutputs(LithiumBerylliumFluorides.getFluid(L))
            .EUt(V[LuV])
            .duration(4 * SECOND + 9 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}