package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.unification.material.Materials.DistilledWater
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.HIGH_PRESSURE_STEAM_FUELS
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SUPERCRITICAL_STEAM_FUELS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LeadBismuthEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumBerylliumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumSodiumPotassiumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPotassiumEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SupercriticalLeadBismuthEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SupercriticalLithiumBerylliumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SupercriticalLithiumSodiumPotassiumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SupercriticalSodiumPotassiumEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SupercriticalSteam
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheatedLeadBismuthEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheatedLithiumBerylliumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheatedLithiumSodiumPotassiumFluorides
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheatedSodiumPotassiumEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheatedSteam
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class HeatExchangeChain
{

    companion object
    {

        fun init()
        {
            // 1L Water/DistilledWater -> 160L Steam

            // Superheated Steam
            // 320L SuperheatedSteam -> 320L Steam -> 2L DistilledWater
            HIGH_PRESSURE_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SuperheatedSteam.getFluid(320))
                .fluidOutputs(DistilledWater.getFluid(2))
                .EUt(V[MV])
                .duration(10 * TICK)
                .buildAndRegister()

            // Superheated Eutatic Sodium Potassium
            HIGH_PRESSURE_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SuperheatedSodiumPotassiumEutatic.getFluid(L))
                .fluidOutputs(SodiumPotassiumEutatic.getFluid(L))
                .EUt(V[HV])
                .duration(2 * SECOND + 16 * TICK)
                .buildAndRegister()

            // Superheated Eutatic Lead Bismuth
            HIGH_PRESSURE_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SuperheatedLeadBismuthEutatic.getFluid(L))
                .fluidOutputs(LeadBismuthEutatic.getFluid(L))
                .EUt(V[HV])
                .duration(2 * SECOND + 18 * TICK)
                .buildAndRegister()

            // Superheated Lithium Sodium Potassium Fluorides
            HIGH_PRESSURE_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SuperheatedLithiumSodiumPotassiumFluorides.getFluid(L))
                .fluidOutputs(LithiumSodiumPotassiumFluorides.getFluid(L))
                .EUt(V[EV])
                .duration(4 * SECOND + 12 * TICK)
                .buildAndRegister()

            // Superheated Lithium Beryllium Fluorides
            HIGH_PRESSURE_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SuperheatedLithiumBerylliumFluorides.getFluid(L))
                .fluidOutputs(LithiumBerylliumFluorides.getFluid(L))
                .EUt(V[EV])
                .duration(4 * SECOND + 8 * TICK)
                .buildAndRegister()

            // Supercritical Steam
            // 640L SupercriticalSteam -> 640L Steam -> 4L Distilled Water
            SUPERCRITICAL_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SupercriticalSteam.getFluid(640))
                .fluidOutputs(DistilledWater.getFluid(4))
                .EUt(V[EV])
                .duration(10 * TICK)
                .buildAndRegister()

            // Supercritical Eutatic Sodium Potassium
            SUPERCRITICAL_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SupercriticalSodiumPotassiumEutatic.getFluid(L))
                .fluidOutputs(SodiumPotassiumEutatic.getFluid(L))
                .EUt(V[IV])
                .duration(2 * SECOND + 12 * TICK)
                .buildAndRegister()

            // Supercritical Eutatic Lead Bismuth
            SUPERCRITICAL_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SupercriticalLeadBismuthEutatic.getFluid(L))
                .fluidOutputs(LeadBismuthEutatic.getFluid(L))
                .EUt(V[IV])
                .duration(2 * SECOND + 18 * TICK)
                .buildAndRegister()

            // Supercritical Lithium Sodium Potassium Fluorides
            SUPERCRITICAL_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SupercriticalLithiumSodiumPotassiumFluorides.getFluid(L))
                .fluidOutputs(LithiumSodiumPotassiumFluorides.getFluid(L))
                .EUt(V[LuV])
                .duration(4 * SECOND + 14 * TICK)
                .buildAndRegister()

            // Supercritical Lithium Beryllium Fluorides
            SUPERCRITICAL_STEAM_FUELS.recipeBuilder()
                .fluidInputs(SupercriticalLithiumBerylliumFluorides.getFluid(L))
                .fluidOutputs(LithiumBerylliumFluorides.getFluid(L))
                .EUt(V[LuV])
                .duration(4 * SECOND + 9 * TICK)
                .buildAndRegister()

        }

    }

}