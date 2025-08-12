package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.ENGRAVED_CRYSTAL_CHIP
import gregtech.common.items.MetaItems.RAW_CRYSTAL_CHIP
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aegirine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Forsterite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jade
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Prasiolite

object EntrodynamicallyPhaseChangerRecipes
{

    // @formatter:off

    fun init()
    {

        // region Fusion Reactor Recipes

        // Argon Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Argon.getFluid(1000))
            .fluidOutputs(Argon.getPlasma(1000))
            .EUt(VA[LuV])
            .duration(18 * TICK)
            .blastFurnaceTemp(4600)
            .buildAndRegister()

        // Bismuth Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Bismuth)
            .fluidOutputs(Bismuth.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Bismuth)
            .fluidOutputs(Bismuth.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Bismuth)
            .fluidOutputs(Bismuth.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Bismuth)
            .fluidOutputs(Bismuth.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Boron Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Boron)
            .fluidOutputs(Boron.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Boron)
            .fluidOutputs(Boron.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Calcium Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Calcium)
            .fluidOutputs(Calcium.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Calcium)
            .fluidOutputs(Calcium.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Calcium)
            .fluidOutputs(Calcium.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Calcium)
            .fluidOutputs(Calcium.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Helium Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Helium.getFluid(1000))
            .fluidOutputs(Helium.getPlasma(1000))
            .EUt(VA[IV])
            .duration(5 * TICK)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Iron Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Iron)
            .fluidOutputs(Iron.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Iron)
            .fluidOutputs(Iron.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Iron)
            .fluidOutputs(Iron.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Iron)
            .fluidOutputs(Iron.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Krypton Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Krypton.getFluid(1000))
            .fluidOutputs(Krypton.getPlasma(1000))
            .EUt(VA[ZPM])
            .duration(18 * TICK)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Lead Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Lead)
            .fluidOutputs(Lead.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Lead)
            .fluidOutputs(Lead.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Lead)
            .fluidOutputs(Lead.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Lead)
            .fluidOutputs(Lead.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Neon Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Neon.getFluid(1000))
            .fluidOutputs(Neon.getPlasma(1000))
            .EUt(VA[LuV])
            .duration(1 * SECOND + 12 * TICK)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Nickel Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Nickel)
            .fluidOutputs(Nickel.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Nickel)
            .fluidOutputs(Nickel.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Nickel)
            .fluidOutputs(Nickel.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Nickel)
            .fluidOutputs(Nickel.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Nitrogen Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Nitrogen.getFluid(1000))
            .fluidOutputs(Nitrogen.getPlasma(1000))
            .EUt(VA[LuV])
            .duration(8 * TICK)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Oxygen Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Oxygen.getFluid(1000))
            .fluidOutputs(Oxygen.getPlasma(1000))
            .EUt(VA[IV])
            .duration(16 * TICK)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Radon Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Radon.getFluid(1000))
            .fluidOutputs(Radon.getPlasma(1000))
            .EUt(VA[ZPM])
            .duration(16 * TICK)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Rubidium Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Rubidium)
            .fluidOutputs(Rubidium.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Rubidium)
            .fluidOutputs(Rubidium.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Rubidium)
            .fluidOutputs(Rubidium.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Rubidium)
            .fluidOutputs(Rubidium.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Silver Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Silver)
            .fluidOutputs(Silver.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1235)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Silver)
            .fluidOutputs(Silver.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1235)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Silver)
            .fluidOutputs(Silver.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1235)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Silver)
            .fluidOutputs(Silver.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1235)
            .buildAndRegister()

        // Sulfur Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Sulfur)
            .fluidOutputs(Sulfur.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Sulfur)
            .fluidOutputs(Sulfur.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Sulfur)
            .fluidOutputs(Sulfur.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Sulfur)
            .fluidOutputs(Sulfur.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Thorium
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Thorium)
            .fluidOutputs(Thorium.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(2023)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Thorium)
            .fluidOutputs(Thorium.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(2023)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Thorium)
            .fluidOutputs(Thorium.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(2023)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Thorium)
            .fluidOutputs(Thorium.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(2023)
            .buildAndRegister()

        // Tin
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Tin)
            .fluidOutputs(Tin.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Tin)
            .fluidOutputs(Tin.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Tin)
            .fluidOutputs(Tin.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Tin)
            .fluidOutputs(Tin.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Xenon Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Xenon.getFluid(1000))
            .fluidOutputs(Xenon.getPlasma(1000))
            .EUt(VA[UV])
            .duration(8 * TICK)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // Zinc Plasma
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Zinc)
            .fluidOutputs(Zinc.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Zinc)
            .fluidOutputs(Zinc.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(ingot, Zinc)
            .fluidOutputs(Zinc.getFluid(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ingot, Zinc)
            .fluidOutputs(Zinc.getPlasma(L))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .blastFurnaceTemp(1200)
            .buildAndRegister()

        // endregion

        // region Fluid Heater Recipes

        // TODO

        // endregion

        // region Unduplicated EBF Recipes

        // Engraved Crystal Chip
        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, Emerald)
            .input(RAW_CRYSTAL_CHIP)
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(22 * SECOND + 10 * TICK)
            .blastFurnaceTemp(5000)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, Olivine)
            .input(RAW_CRYSTAL_CHIP)
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(22 * SECOND + 10 * TICK)
            .blastFurnaceTemp(5000)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, Forsterite)
            .input(RAW_CRYSTAL_CHIP)
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(22 * SECOND + 10 * TICK)
            .blastFurnaceTemp(5000)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, Aegirine)
            .input(RAW_CRYSTAL_CHIP)
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(22 * SECOND + 10 * TICK)
            .blastFurnaceTemp(5000)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, Jade)
            .input(RAW_CRYSTAL_CHIP)
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(22 * SECOND + 10 * TICK)
            .blastFurnaceTemp(5000)
            .buildAndRegister()

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, Prasiolite)
            .input(RAW_CRYSTAL_CHIP)
            .output(ENGRAVED_CRYSTAL_CHIP)
            .EUt(VA[HV])
            .duration(22 * SECOND + 10 * TICK)
            .blastFurnaceTemp(5000)
            .buildAndRegister()

        // endregion

    }

    // @formatter:on

}