package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.EnderAir
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.LiquidAir
import gregtech.api.unification.material.Materials.LiquidEnderAir
import gregtech.api.unification.material.Materials.LiquidNetherAir
import gregtech.api.unification.material.Materials.NetherAir
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.ingotHot
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BATH_CONDENSER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class BathCondenserRecipes
{

    companion object
    {

        fun init()
        {
            // Water -> Ice
            BATH_CONDENSER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Water.getFluid(100))
                .fluidOutputs(Ice.getFluid(100))
                .EUt(VA[ULV].toLong())
                .duration(2 * TICK)
                .buildAndRegister()

            // Oxygen -> Liquid Oxygen
            BATH_CONDENSER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Oxygen.getFluid(100))
                .fluidOutputs(Oxygen.getFluid(FluidStorageKeys.LIQUID, 100))
                .EUt(VA[EV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Helium -> Liquid Helium
            BATH_CONDENSER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Helium.getFluid(100))
                .fluidOutputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .EUt(VA[EV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            for (fluidStack in arrayOf(
                Water.getFluid(100),
                DistilledWater.getFluid(100),
                Ice.getFluid(50),
                Oxygen.getFluid(FluidStorageKeys.LIQUID, 25),
                Helium.getFluid(FluidStorageKeys.LIQUID, 10)))
            {
                // Air -> Liquid Air
                BATH_CONDENSER_RECIPES.recipeBuilder()
                    .fluidInputs(Air.getFluid(500))
                    .fluidInputs(fluidStack)
                    .fluidOutputs(LiquidAir.getFluid(500))
                    .EUt(VA[HV].toLong())
                    .duration(2 * SECOND)
                    .buildAndRegister()

                // Nether Air -> Liquid Air
                BATH_CONDENSER_RECIPES.recipeBuilder()
                    .fluidInputs(NetherAir.getFluid(500))
                    .fluidInputs(fluidStack)
                    .fluidOutputs(LiquidNetherAir.getFluid(500))
                    .EUt(VA[EV].toLong())
                    .duration(2 * SECOND)
                    .buildAndRegister()

                // Ender Air -> Liquid Ender Air
                BATH_CONDENSER_RECIPES.recipeBuilder()
                    .fluidInputs(EnderAir.getFluid(500))
                    .fluidInputs(fluidStack)
                    .fluidOutputs(LiquidEnderAir.getFluid(500))
                    .EUt(VA[IV].toLong())
                    .duration(2 * SECOND)
                    .buildAndRegister()
            }

            // Hot Silicon and Kanthal ingot coolant.
            GTRecipeHandler.removeRecipesByInputs(CHEMICAL_BATH_RECIPES,
                arrayOf(OreDictUnifier.get(ingotHot, Silicon)),
                arrayOf(Water.getFluid(100)))
            GTRecipeHandler.removeRecipesByInputs(CHEMICAL_BATH_RECIPES,
                arrayOf(OreDictUnifier.get(ingotHot, Silicon)),
                arrayOf(DistilledWater.getFluid(100)))

            GTRecipeHandler.removeRecipesByInputs(CHEMICAL_BATH_RECIPES,
                arrayOf(OreDictUnifier.get(ingotHot, Kanthal)),
                arrayOf(Water.getFluid(100)))
            GTRecipeHandler.removeRecipesByInputs(CHEMICAL_BATH_RECIPES,
                arrayOf(OreDictUnifier.get(ingotHot, Kanthal)),
                arrayOf(DistilledWater.getFluid(100)))

            for (fluidStack in arrayOf(
                Water.getFluid(100),
                DistilledWater.getFluid(100),
                Ice.getFluid(50),
                Oxygen.getFluid(FluidStorageKeys.LIQUID, 25),
                Helium.getFluid(FluidStorageKeys.LIQUID, 10)))
            {
                BATH_CONDENSER_RECIPES.recipeBuilder()
                    .input(ingotHot, Silicon)
                    .fluidInputs(fluidStack)
                    .output(ingot, Silicon)
                    .EUt(VA[MV].toLong())
                    .duration(5 * SECOND)
                    .buildAndRegister()

                BATH_CONDENSER_RECIPES.recipeBuilder()
                    .input(ingotHot, Kanthal)
                    .fluidInputs(fluidStack)
                    .output(ingot, Kanthal)
                    .EUt(VA[MV].toLong())
                    .duration(10 * SECOND)
                    .buildAndRegister()
            }


        }

    }

}