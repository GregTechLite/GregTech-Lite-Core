package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.AcidicOsmiumSolution
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.AmmoniumChloride
import gregtech.api.unification.material.Materials.AquaRegia
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.InertMetalMixture
import gregtech.api.unification.material.Materials.IridiumChloride
import gregtech.api.unification.material.Materials.IridiumMetalResidue
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.OsmiumTetroxide
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.PalladiumRaw
import gregtech.api.unification.material.Materials.PlatinumGroupSludge
import gregtech.api.unification.material.Materials.PlatinumRaw
import gregtech.api.unification.material.Materials.PlatinumSludgeResidue
import gregtech.api.unification.material.Materials.RarestMetalMixture
import gregtech.api.unification.material.Materials.RhodiumSulfate
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.RutheniumTetroxide
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumBisulfate
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfurTrioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.SulfuricCopperSolution
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.crushedPurified
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumHexachloropalladate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmmoniumHexachloroplatinate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azurite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cuprite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FormicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexachloroplatinicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylFormate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OsmiumTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ozone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlatinumGroupConcentrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlatinumGroupResidue
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PurifiedPlatinumGroupConcentrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RhodiumTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RutheniumTrichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumChlorate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SulfurDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tenorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThionylChloride
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler

internal object PlatinumGroupProcessing
{

    // @formatter:off

    fun init()
    {
        removeVanillaRecipes()
        platinumGroupSludgeProcess()
        platinumPalladiumProcess()
        rutheniumRhodiumProcess()
        iridiumOsmiumProcess()
    }

    private fun removeVanillaRecipes()
    {
        // Removal PGS (Platinum Group Sludge) centrifuging recipe.
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
            arrayOf(OreDictUnifier.get(dust, PlatinumGroupSludge, 6)),
            arrayOf(AquaRegia.getFluid(1200)))

        // Remove PtCl2/PdCl2 (RawPlatinum/Palladium) electrolysis/chemical reaction decomposition.
        GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
            OreDictUnifier.get(dust, PlatinumRaw, 3))
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, PalladiumRaw, 5)),
            arrayOf(HydrochloricAcid.getFluid(1000)))

        // Remove Inert Metal Residue (IMR) decompose to RuO4/RhSO4 reaction.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, InertMetalMixture, 6)),
            arrayOf(SulfuricAcid.getFluid(1500)))

        // Remove RuO4 decompose to Ru reaction.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, RutheniumTetroxide, 5),
                OreDictUnifier.get(dust, Carbon, 2)))

        // Remove RhSO4 electrolysis decomposition.
        GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
            RhodiumSulfate.getFluid(1000))

        // Remove Acidic Osmium Solution distillation.
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            AcidicOsmiumSolution.getFluid(2000))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(AcidicOsmiumSolution.getFluid(400)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(AcidicOsmiumSolution.getFluid(400)))

        // Remove OsO4 chemical reaction.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, OsmiumTetroxide, 5)),
            arrayOf(Hydrogen.getFluid(8000)))

        // Remove Rarest Metal Mixture (RMM) -> Ir/Os residue/solution reaction.
        GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES,
            arrayOf(OreDictUnifier.get(dust, RarestMetalMixture, 7)),
            arrayOf(HydrochloricAcid.getFluid(4000)))

        // Remove Iridium Metal Residue (IMR) -> IrCl3 centrifuging.
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
            OreDictUnifier.get(dust, IridiumMetalResidue, 5))
    }

    private fun platinumGroupSludgeProcess()
    {
        // Copper group ore addition of PGS dust.
        CHEMICAL_RECIPES.recipeBuilder()
            .input(crushedPurified, Azurite)
            .fluidInputs(NitricAcid.getFluid(100))
            .output(dust, PlatinumGroupSludge, 2)
            .fluidOutputs(SulfuricCopperSolution.getFluid(1000))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        CHEMICAL_RECIPES.recipeBuilder()
            .input(crushedPurified, Tenorite)
            .fluidInputs(NitricAcid.getFluid(100))
            .output(dust, PlatinumGroupSludge, 2)
            .fluidOutputs(SulfuricCopperSolution.getFluid(1000))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        CHEMICAL_RECIPES.recipeBuilder()
            .input(crushedPurified, Cuprite)
            .fluidInputs(NitricAcid.getFluid(100))
            .output(dust, PlatinumGroupSludge, 2)
            .fluidOutputs(SulfuricCopperSolution.getFluid(1000))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // (PGS) + 3(HCl)2(HNO3) -> RuRhIr2Os(HNO3)3 + AuPtPd(HCl)6
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, PlatinumGroupSludge, 3)
            .fluidInputs(AquaRegia.getFluid(9000))
            .output(dust, PlatinumGroupResidue)
            .fluidOutputs(PlatinumGroupConcentrate.getFluid(1000))
            .EUt(VHA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // AuPtPd(HCl)6 -> H2PtPdCl6 + Au + 4H (lost)
        CENTRIFUGE_RECIPES.recipeBuilder()
            .fluidInputs(PlatinumGroupConcentrate.getFluid(1000))
            .output(dust, PlatinumSludgeResidue)
            .fluidOutputs(PurifiedPlatinumGroupConcentrate.getFluid(1000))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // H2PtPdCl6 + 2NH4Cl -> 0.625 (NH4)2PtCl6 + 0.375 (NH4)2PdCl6 + 2HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, AmmoniumChloride, 4)
            .fluidInputs(PurifiedPlatinumGroupConcentrate.getFluid(1000))
            .fluidOutputs(AmmoniumHexachloroplatinate.getFluid(625))
            .fluidOutputs(AmmoniumHexachloropalladate.getFluid(375))
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // RuRhIr2Os(HNO3)3 + 3NaHSO4 -> RhRu + Ir2Os + 3NaNO3 + 3H2SO4
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, PlatinumGroupResidue)
            .fluidInputs(SodiumBisulfate.getFluid(L * 21)) // Retrim it to dust material amount convert.
            .output(dust, InertMetalMixture)
            .output(dust, RarestMetalMixture)
            .output(dust, SodiumNitrate, 5)
            .fluidOutputs(SulfuricAcid.getFluid(3000))
            .EUt(VHA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // NaHSO4-NaNO3 raw cycling the sodium (3Na) by both decomposition.

        // Add a recipe of NH4Cl because NH4Cl used for react H2PtPdCl6 and
        // get Ammonium Hexachloro-components.
        // HCl + NH3 -> NH4Cl
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .output(dust, AmmoniumChloride, 2)
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // Another recipe of NaNO3, prepare for other chemistry processing.
        // NaOH + HNO3 -> NaNO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(NitricAcid.getFluid(1000))
            .output(dust, SodiumNitrate, 5)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun platinumPalladiumProcess()
    {
        // (NH4)2PtCl6 -> H2PtCl6 + 2NH3 (cycling with HNO3)
        ELECTROLYZER_RECIPES.recipeBuilder()
            .fluidInputs(AmmoniumHexachloroplatinate.getFluid(1000))
            .fluidOutputs(HexachloroplatinicAcid.getFluid(1000))
            .fluidOutputs(Ammonia.getFluid(2000))
            .EUt(VA[LV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // H2PtCl6 decompose to Pt dust by electrolysis reaction.

        // (NH4)2PdCl6 + 2H -> PdCl2 + 2NH3 (cycling with HNO3) + 4HCl (cycling with AquaRegia)
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(AmmoniumHexachloropalladate.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(2000))
            .output(dust, PalladiumRaw, 3)
            .fluidOutputs(Ammonia.getFluid(2000))
            .fluidOutputs(HydrochloricAcid.getFluid(4000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // CH3OH + CO -> HCO2CH3
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, SodiumHydroxide)
            .fluidInputs(Methanol.getFluid(1000))
            .fluidInputs(CarbonMonoxide.getFluid(1000))
            .fluidOutputs(MethylFormate.getFluid(1000))
            .EUt(VA[LV])
            .duration(16 * TICK)
            .buildAndRegister()

        // HCO2CH3 + H2O -> HCOOH + CH3OH (353K)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(MethylFormate.getFluid(1000))
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(FormicAcid.getFluid(1000))
            .fluidOutputs(Methanol.getFluid(1000))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // PdCl2 + HCOOH -> Pd + 2HCl (cycling with Aqua Regia) + CO2
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PalladiumRaw, 3)
            .fluidInputs(FormicAcid.getFluid(1000))
            .output(dust, Palladium)
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .EUt(VA[LV])
            .duration(12 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    private fun rutheniumRhodiumProcess()
    {
        // CH4 + 8Cl -> CCl4 + 4HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(Methane.getFluid(1000))
            .fluidInputs(Chlorine.getFluid(8000))
            .fluidOutputs(CarbonTetrachloride.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(4000))
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // 2RhRu + 2CCl4 + 3H2SO4 -> 2RuCl3 + Rh2(SO4)3 + 2HCl + CH4 + C (lost)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, InertMetalMixture, 6)
            .fluidInputs(CarbonTetrachloride.getFluid(2000))
            .fluidInputs(SulfuricAcid.getFluid(3000))
            .output(dust, RutheniumTrichloride, 8)
            .fluidOutputs(RhodiumSulfate.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .fluidOutputs(Methane.getFluid(1000))
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 2Na + 2O -> Na2O2
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Sodium, 2)
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, SodiumPeroxide, 4)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // 6Na + 2O3 -> 3Na2O2
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Sodium, 6)
            .fluidInputs(Ozone.getFluid(2000))
            .output(dust, SodiumPeroxide, 12)
            .EUt(VA[MV])
            .duration(1 * SECOND + 10 * TICK)
            .buildAndRegister()

        // RuCl3 + 2Na2O2 + Cl -> RuO4 + 4NaCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, RutheniumTrichloride, 4)
            .input(dust, SodiumPeroxide, 8)
            .fluidInputs(Chlorine.getFluid(1000))
            .output(dust, RutheniumTetroxide, 5)
            .output(dust, Salt, 8)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // RuO4 + 2C -> Ru + 2CO
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, RutheniumTetroxide, 5)
            .input(dust, Carbon, 2)
            .output(dust, Ruthenium)
            .fluidOutputs(CarbonDioxide.getFluid(2000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Rh2(SO4)3 + 3H2O -> Rh2O3 + 3H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(RhodiumSulfate.getFluid(1000))
            .fluidInputs(Water.getFluid(3000))
            .output(dust, RhodiumTrioxide, 5)
            .fluidOutputs(SulfuricAcid.getFluid(3000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Rh2O3 decompose to Rh dust by electrolysis reaction.
    }

    private fun iridiumOsmiumProcess()
    {
        // Ir2Os + 2NaClO3 + O -> Ir2O3 + OsO4 + 2NaCl
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, RarestMetalMixture, 12)
            .input(dust, SodiumChlorate, 10)
            .fluidInputs(Oxygen.getFluid(1000))
            .output(dust, IridiumMetalResidue, 5)
            .output(dust, OsmiumTetroxide, 5)
            .output(dust, Salt, 4)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Ir2O3 + 6HCl -> 2IrCl3 + 3H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, IridiumMetalResidue, 5)
            .fluidInputs(HydrochloricAcid.getFluid(6000))
            .output(dust, IridiumChloride, 8)
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VHA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // IrCl3 decompose to Ir dust by original processing.
        // IrCl3 + 3H -> Ir + 3HCl.

        // S + 2Cl -> SCl2
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Sulfur)
            .fluidInputs(Chlorine.getFluid(2000))
            .fluidOutputs(SulfurDichloride.getFluid(1000))
            .EUt(VA[MV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // SO3 + SCl2 -> SOCl2 + SO2
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(SulfurTrioxide.getFluid(1000))
            .fluidInputs(SulfurDichloride.getFluid(1000))
            .fluidOutputs(ThionylChloride.getFluid(1000))
            .fluidOutputs(SulfurDioxide.getFluid(1000))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // OsO4 + 2SOCl2 -> OsCl4 + 2SO3
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, OsmiumTetroxide, 5)
            .fluidInputs(ThionylChloride.getFluid(2000))
            .output(dust, OsmiumTetrachloride, 5)
            .fluidOutputs(SulfurTrioxide.getFluid(2000))
            .EUt(VHA[HV])
            .duration(100)
            .buildAndRegister()

        // OsO4 decompose to Os dust by original processing.
    }

    // @formatter:on

}