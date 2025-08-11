package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chlorobenzene
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.PhosphorusPentoxide
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.TricalciumPhosphate
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.gem
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SONICATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackPhosphorus
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BluePhosphorus
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NMethylPyrrolidone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosphine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosphorene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphoreneSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphorusTrichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphorylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RedPhosphorus
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Triphenylphosphine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VioletPhosphorus
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhitePhosphorus
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Wollastonite
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object PhosphorusChain
{

    // @formatter:off

    fun init()
    {
        phosphorusProcess()
        phosphoryChlorideProcess()
        triphenylphosphineProcess()
        phosphoreneProcess()
        phosphineProcess()
    }

    private fun phosphorusProcess()
    {
        // 2Ca3(PO4)2 + 6SiO2 + 5C -> 6CaSiO3 + 5CO2 + P4 (white)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, TricalciumPhosphate, 10)
            .input(dust, SiliconDioxide, 18)
            .input(dust, Carbon, 5)
            .output(dust, Wollastonite, 30)
            .output(gem, WhitePhosphorus)
            .fluidOutputs(CarbonDioxide.getFluid(5000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Advanced recipes for White Phosphorus.
        // P4O10 + 10C -> P4 (white) + 10CO
        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(dust, PhosphorusPentoxide, 14)
            .input(dust, Carbon, 10)
            .output(gem, WhitePhosphorus)
            .fluidOutputs(CarbonMonoxide.getFluid(10000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // P4 (white) + Ar -> P4 (red)
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, WhitePhosphorus)
            .fluidInputs(Argon.getFluid(50))
            .output(gem, RedPhosphorus)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // P4 (white) + 2Pb -> P4 (violet)
        CHEMICAL_BATH_RECIPES.recipeBuilder() // Hitoff reaction.
            .input(dust, WhitePhosphorus)
            .fluidInputs(Lead.getFluid(L * 2))
            .output(gem, VioletPhosphorus)
            .EUt(VA[HV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // P4 (white) -> P4 (black)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(gem, WhitePhosphorus)
            .output(gem, BlackPhosphorus)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // P4 (black) -> P4 (blue)
        MOLECULAR_BEAM_RECIPES.recipeBuilder()
            .notConsumable(foil, Gold)
            .input(gem, BlackPhosphorus)
            .output(dust, BluePhosphorus)
            .EUt(VA[ZPM])
            .duration(5 * SECOND)
            .temperature(4800)
            .buildAndRegister()

    }

    private fun phosphoryChlorideProcess()
    {
        // P4 (white) + 12Cl -> 4PCl3
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, WhitePhosphorus)
            .fluidInputs(Chlorine.getFluid(12000))
            .fluidOutputs(PhosphorusTrichloride.getFluid(4000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 2P4 (red) + 24Cl -> 8PCl3
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, RedPhosphorus, 2)
            .fluidInputs(Chlorine.getFluid(24000))
            .fluidOutputs(PhosphorusTrichloride.getFluid(8000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 2P4 (black) + 24Cl -> 8PCl3
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, BlackPhosphorus, 2)
            .fluidInputs(Chlorine.getFluid(24000))
            .fluidOutputs(PhosphorusTrichloride.getFluid(8000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 4P4 (violet) + 48Cl -> 16PCl3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .input(dust, VioletPhosphorus, 4)
            .fluidInputs(Chlorine.getFluid(48000))
            .fluidOutputs(PhosphorusTrichloride.getFluid(16000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 4P4 (blue) + 48Cl -> 16PCl3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .input(dust, BluePhosphorus, 4)
            .fluidInputs(Chlorine.getFluid(48000))
            .fluidOutputs(PhosphorusTrichloride.getFluid(16000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // PCl3 + O -> POCl3
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(PhosphorusTrichloride.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(1000))
            .fluidOutputs(PhosphorylChloride.getFluid(1000))
            .EUt(VA[HV])
            .duration(6 * SECOND)
            .buildAndRegister()

    }

    private fun triphenylphosphineProcess()
    {
        // 6Na + PCl3 + 3C6H5Cl -> (C6H5)3P + 6NaCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Sodium)
            .fluidInputs(PhosphorusTrichloride.getFluid(1000))
            .fluidInputs(Chlorobenzene.getFluid(3000))
            .output(dust, Triphenylphosphine, 34)
            .output(dust, Salt, 12)
            .EUt(VA[IV])
            .duration(8 * SECOND)
            .buildAndRegister()
    }

    private fun phosphoreneProcess()
    {
        // 2Na(OH) + 8P4 (black) + 0.2H2O + 0.8C5H9NO -> (P4)8(C5H9NO)(Na(OH))2·(H2O)
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .input(dust, SodiumHydroxide, 6)
            .input(gem, BlackPhosphorus, 8)
            .fluidInputs(DistilledWater.getFluid(200))
            .fluidInputs(NMethylPyrrolidone.getFluid(800))
            .fluidOutputs(PhosphoreneSolution.getFluid(1000))
            .EUt(VA[IV])
            .duration(30 * SECOND)
            .buildAndRegister()

        // 1/8(P4)8(C5H9NO)(Na(OH))2·(H2O) -> P4 + 1/8C5H9NO (cycle)
        SONICATION_RECIPES.recipeBuilder()
            .fluidInputs(PhosphoreneSolution.getFluid(125))
            .fluidInputs(Argon.getFluid(100))
            .output(foil, Phosphorene, 4)
            .fluidOutputs(NMethylPyrrolidone.getFluid(100))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    private fun phosphineProcess()
    {
        // P4 + 6H2O -> 4PH3 + 6O (lost)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, WhitePhosphorus)
            .fluidInputs(Water.getFluid(6000))
            .fluidOutputs(Phosphine.getFluid(4000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}