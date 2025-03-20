package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Deuterium
import gregtech.api.unification.material.Materials.EnderAir
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Helium3
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.LiquidAir
import gregtech.api.unification.material.Materials.LiquidEnderAir
import gregtech.api.unification.material.Materials.LiquidNetherAir
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.NetherAir
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfurTrioxide
import gregtech.api.unification.material.Materials.Tritium
import gregtech.api.unification.material.Materials.Xenon
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_EV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_HV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_MV
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LARGE_GAS_COLLECTOR_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class GasCollectorRecipes
{

    companion object
    {

        fun init()
        {
            // Air
            LARGE_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .notConsumable(ELECTRIC_PUMP_LV)
                .fluidOutputs(Air.getFluid(2500))
                .fluidOutputs(Nitrogen.getFluid(1600))
                .fluidOutputs(CarbonDioxide.getFluid(1200))
                .fluidOutputs(Helium.getFluid(450))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Nether Air
            LARGE_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .notConsumable(ELECTRIC_PUMP_MV)
                .fluidOutputs(NetherAir.getFluid(3000))
                .fluidOutputs(SulfurDioxide.getFluid(1800))
                .fluidOutputs(HydrogenSulfide.getFluid(1400))
                .fluidOutputs(Neon.getFluid(600))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Ender Air
            LARGE_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .notConsumable(ELECTRIC_PUMP_HV)
                .fluidOutputs(EnderAir.getFluid(3500))
                .fluidOutputs(NitrogenDioxide.getFluid(2200))
                .fluidOutputs(Deuterium.getFluid(1600))
                .fluidOutputs(Tritium.getFluid(1100))
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Liquid Air
            LARGE_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .notConsumable(ELECTRIC_PUMP_HV)
                .fluidOutputs(LiquidAir.getFluid(3400))
                .fluidOutputs(Nitrogen.getFluid(2800))
                .fluidOutputs(Helium.getFluid(1400))
                .fluidOutputs(Argon.getFluid(700))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Liquid Nether Air
            LARGE_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .notConsumable(ELECTRIC_PUMP_EV)
                .fluidOutputs(LiquidNetherAir.getFluid(4800))
                .fluidOutputs(SulfurTrioxide.getFluid(3500))
                .fluidOutputs(Helium3.getFluid(2600))
                .fluidOutputs(Krypton.getFluid(1200))
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Liquid Ender Air
            LARGE_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .notConsumable(ELECTRIC_PUMP_IV)
                .fluidOutputs(LiquidEnderAir.getFluid(6400))
                .fluidOutputs(Helium.getFluid(4000))
                .fluidOutputs(Xenon.getFluid(2100))
                .fluidOutputs(Radon.getFluid(1500))
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}