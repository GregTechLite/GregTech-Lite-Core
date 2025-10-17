package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.IMPLOSION_RECIPES
import gregtech.api.recipes.RecipeMaps.LATHE_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.AmmoniumChloride
import gregtech.api.unification.material.Materials.Borax
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.gemExquisite
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.toolHeadBuzzSaw
import gregtech.common.items.MetaItems
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmorphousBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Borazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronTrichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronTrifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicHeterodiamond
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diborane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Heterodiamond
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexagonalBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumHydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumTetrafluoroborate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trichloroborazine

internal object BoronNitrideChain
{

    // @formatter:off

    fun init()
    {
        boronTrioxideProcess()
        borazineProcess()
        boronNitrideProcess()
    }

    private fun boronTrioxideProcess()
    {
        // Na2B4O7·10H2O + 2HCl -> 4H3BO3 + 2NaCl + 5H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Borax, 23)
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .output(dust, BoricAcid, 16)
            .output(dust, Salt, 4)
            .fluidOutputs(Water.getFluid(5000))
            .EUt(VA[MV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // 2H3BO3 -> B2O3 + 3H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .input(dust, BoricAcid, 8)
            .output(dust, BoronTrioxide, 5)
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun borazineProcess()
    {
        // B2O3 + 6HF -> 2BF3 + 3H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, BoricAcid, 5)
            .fluidInputs(HydrofluoricAcid.getFluid(6000))
            .fluidOutputs(BoronTrifluoride.getFluid(2000))
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VA[HV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // Li + H -> LiH
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Lithium)
            .fluidInputs(Hydrogen.getFluid(1000))
            .output(ingot, LithiumHydride, 2)
            .EUt(VA[HV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // 8BF3 + 6LiH -> B2H6 + 6LiBF4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, LithiumHydride, 12)
            .fluidInputs(BoronTrifluoride.getFluid(8000))
            .output(dust, LithiumTetrafluoroborate, 36)
            .fluidOutputs(Diborane.getFluid(1000))
            .EUt(VA[HV])
            .duration(32 * SECOND)
            .buildAndRegister()

        // LiBF4 -> BF3 + LiH (cycle)
        // This reaction cycle both BF3 and LiH with above reactions,
        // we can see that 8BF3 + 6LiH -> 6LiBF4 -> 6BF3 + 6LiH, and 2BF3 to be B2H6,
        // it means LiH is cycle and BF3 is part cycle.
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .input(dust, LithiumTetrafluoroborate, 6)
            .output(dust, LithiumHydride, 2)
            .fluidOutputs(BoronTrifluoride.getFluid(1000))
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // 3B2H6 + 6NH3 -> 2B3H6N3 + 24H
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Diborane.getFluid(3000))
            .fluidInputs(Ammonia.getFluid(6000))
            .fluidOutputs(Borazine.getFluid(2000))
            .fluidOutputs(Hydrogen.getFluid(24000))
            .EUt(VA[LuV])
            .duration(20 * SECOND)
            .buildAndRegister()
    }

    private fun boronNitrideProcess()
    {
        // 2B2O3 + 3CCl4 -> 4BCl3 + 3CO2
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, BoronTrioxide, 10)
            .fluidInputs(CarbonTetrachloride.getFluid(3000))
            .fluidOutputs(BoronTrichloride.getFluid(4000))
            .fluidOutputs(CarbonDioxide.getFluid(3000))
            .EUt(VA[HV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // 3BCl3 + 3NH4Cl -> B3Cl3H3N3 + 9HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, AmmoniumChloride, 6) // Special amount.
            .fluidInputs(BoronTrichloride.getFluid(3000))
            .fluidOutputs(Trichloroborazine.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(9000))
            .EUt(VA[IV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // B3H6N3 + 3O -> 3h-BN + 3H2O
        CVD_RECIPES.recipeBuilder()
            .fluidInputs(Borazine.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(3000))
            .output(gem, HexagonalBoronNitride, 6)
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .temperature(1300)
            .buildAndRegister()

        // Advanced recipes for h-BN, used Nickel foil as catalyst bed,
        // and B + N -> h-BN via molecular beaming.
        MOLECULAR_BEAM_RECIPES.recipeBuilder()
            .input(foil, Nickel, 8)
            .input(dust, Boron)
            .fluidInputs(Nitrogen.getFluid(1000))
            .output(gem, HexagonalBoronNitride, 2)
            .EUt(VA[UEV])
            .duration(4 * SECOND)
            .temperature(2900)
            .buildAndRegister()

        // h-BN -> c-BN
        CVD_RECIPES.recipeBuilder()
            .input(dust, HexagonalBoronNitride, 1)
            .output(gem, CubicBoronNitride, 1)
            .EUt(VA[UV])
            .duration(5 * SECOND)
            .temperature(3501)
            .buildAndRegister()

        // B3Cl3H3N3 -> 3a-BN + 3HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Caesium)
            .fluidInputs(Trichloroborazine.getFluid(1000))
            .output(dust, AmorphousBoronNitride, 6)
            .fluidOutputs(HydrochloricAcid.getFluid(3000))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .buildAndRegister()

        // c-BN + C -> BCN
        IMPLOSION_RECIPES.recipeBuilder()
            .input(dust, CubicBoronNitride)
            .input(dust, Carbon)
            .output(gem, Heterodiamond, 2)
            .EUt(VA[LV])
            .duration(1 * SECOND)
            .explosives(32)
            .buildAndRegister()

        IMPLOSION_RECIPES.recipeBuilder()
            .input(dust, CubicBoronNitride)
            .input(dust, Carbon)
            .output(gem, Heterodiamond, 2)
            .EUt(VA[LV])
            .duration(1 * SECOND)
            .explosives(MetaItems.DYNAMITE.getStackForm(16))
            .buildAndRegister()

        // BCN + C -> c-BC2N
        CVD_RECIPES.recipeBuilder()
            .input(dust, Heterodiamond)
            .input(dust, Carbon)
            .output(gem, CubicHeterodiamond)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .temperature(2200)
            .buildAndRegister()

        // Because c-BN is a gem material, but it has tool properties, we should ensure
        // the compatibility with its material parts and tool parts.

        // Allowed gemExquisite lathing to c-BN buzz saw.
        LATHE_RECIPES.recipeBuilder()
            .input(gemExquisite, CubicBoronNitride)
            .output(toolHeadBuzzSaw, CubicBoronNitride)
            .EUt(56) // MV
            .duration(2 * SECOND + 8 * TICK) // same as gear lathing to buzz saw.
            .buildAndRegister()

        // Crystallized stick and plate gluing to gear.
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, CubicBoronNitride, 4)
            .input(stick, CubicBoronNitride, 4)
            .fluidInputs(Glue.getFluid(250))
            .output(gear, CubicBoronNitride)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}