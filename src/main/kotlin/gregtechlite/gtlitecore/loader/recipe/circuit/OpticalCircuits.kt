package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Glowstone
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Potash
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.SodiumBicarbonate
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.SterlingSilver
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.ZincSulfide
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.ENGRAVED_LAPOTRON_CHIP
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_WIRE
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LASER_CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.PLASMA_CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumStrontiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthStrontiumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CeriumCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CeriumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DielectricFormationMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FranciumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FranciumCaesiumCadmiumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GalliumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GalliumNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GalliumTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GermaniumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GermaniumTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeliumNeon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrobromicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumGalliumManganate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadScandiumTantalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightQuarks
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumNiobate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LutetiumManganeseGermanium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ManganeseDifluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NdYAG
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhosphorylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyethyleneTerephthalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumManganate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RubidiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScandiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SiliconTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumPentoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumBariumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WoodsGlass
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ALL_OPTICAL_CASCADE_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOHRIUM_DOPED_CERIUM_CARBONATE_BOULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOHRIUM_DOPED_CERIUM_CARBONATE_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIELECTRIC_MIRROR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EMPTY_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HELIUM_NEON_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ND_YAG_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_ASSEMBLY_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_COMPUTER_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_FIBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_IMC_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_LASER_CONTROL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_MAINFRAME_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_PROCESSOR_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTOELECTRONIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PERIODICALLY_POLED_OPTICAL_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PHASE_CHANGE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_TRANSISTOR

internal object OpticalCircuits
{

    // @formatter:off

    fun init()
    {
        circuitBoardRecipes()
        smdRecipes()
        circuitComponentsRecipes()
        systemOnChipRecipes()
        circuitRecipes()
    }

    private fun circuitBoardRecipes()
    {
        // Optical Board
        CVD_RECIPES.recipeBuilder()
            .input(plate, GalliumNitride)
            .input(foil, ThalliumBariumCalciumCuprate, 32)
            .fluidInputs(PolyethyleneTerephthalate.getFluid(L * 2))
            .output(OPTICAL_BOARD)
            .EUt(VA[UHV])
            .duration(2 * SECOND)
            .temperature(980)
            .buildAndRegister()

        // Optical Circuit Board
        for (etchingLiquid in arrayOf(
            // SodiumPersulfate.getFluid(32000),
            // Iron3Chloride.getFluid(16000) // 8000, 4000
            TetramethylammoniumHydroxide.getFluid(32000),
            EthylenediaminePyrocatechol.getFluid(16000)))
        {
            CHEMICAL_RECIPES.recipeBuilder()
                .input(OPTICAL_BOARD)
                .input(foil, Americium, 48)
                .fluidInputs(etchingLiquid)
                .output(PERFECT_CIRCUIT_BOARD)
                .EUt(VA[EV])
                .duration(1 * MINUTE + 45 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }
    }

    private fun smdRecipes()
    {
        // La2O3 + 2K2MnO4 + 2GaO2 -> 2LaGaMnO4 + 2K2O + 5O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, LanthanumOxide, 5)
            .input(dust, PotassiumManganate, 14)
            .input(dust, GalliumDioxide, 6)
            .output(dust, LanthanumGalliumManganate, 14)
            .output(dust, Potash, 6)
            .fluidOutputs(Oxygen.getFluid(5000))
            .EUt(VA[ZPM])
            .duration(14 * SECOND)
            .buildAndRegister()

        // La2O3 + 2K2MnO4 + Ga2O3 -> 2LaGaMnO4 + 2K2O + 4O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, LanthanumOxide, 5)
            .input(dust, PotassiumManganate, 14)
            .input(dust, GalliumTrioxide, 5)
            .output(dust, LanthanumGalliumManganate, 14)
            .output(dust, Potash, 6)
            .fluidOutputs(Oxygen.getFluid(4000))
            .EUt(VA[ZPM])
            .duration(7 * SECOND)
            .buildAndRegister()

        // BaTiO3 + SrO -> BaSrTiO4
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, BariumTitanate, 5)
            .input(dust, StrontiumOxide, 2)
            .output(dust, BariumStrontiumTitanate, 7)
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Optical SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(foil, LanthanumGalliumManganate)
            .input(wireFine, BariumStrontiumTitanate, 8)
            .fluidInputs(KaptonE.getFluid(L))
            .output(OPTICAL_SMD_TRANSISTOR, 32)
            .EUt(VA[UV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Optical SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(dust, Mendelevium)
            .input(wireFine, BismuthStrontiumCalciumCuprate, 4)
            .fluidInputs(KaptonE.getFluid(L * 2))
            .output(OPTICAL_SMD_RESISTOR, 32)
            .EUt(VA[UV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cs + Br -> CsBr
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Caesium)
            .fluidInputs(Bromine.getFluid(1000))
            .output(dust, CaesiumBromide, 2)
            .EUt(VA[HV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Fr + HBr -> FrBr + H
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Francium)
            .fluidInputs(HydrobromicAcid.getFluid(1000))
            .output(dust, FranciumBromide, 2)
            .fluidOutputs(Hydrogen.getFluid(1000))
            .EUt(VA[EV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // FrBr + CsBr + 2CdBr2 -> FrCsCd2Br6
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, FranciumBromide, 2)
            .input(dust, CaesiumBromide, 2)
            .input(dust, CadmiumBromide, 6)
            .output(dust, FranciumCaesiumCadmiumBromide, 10)
            .EUt(VA[UV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Optical SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(foil, FranciumCaesiumCadmiumBromide)
            .input(foil, RubidiumTitanate)
            .fluidInputs(KaptonE.getFluid(L / 2))
            .output(OPTICAL_SMD_CAPACITOR, 32)
            .EUt(VA[UV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Ta2O5 + Sc2O3 + 2Pb(NO3)2 -> 2PbScTaO3 + 2N2O4 + 6O
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, TantalumPentoxide, 7)
            .input(dust, ScandiumOxide, 5)
            .input(dust, LeadNitrate, 18)
            .output(dust, LeadScandiumTantalate, 12)
            .fluidOutputs(DinitrogenTetroxide.getFluid(2000))
            .fluidOutputs(Oxygen.getFluid(6000))
            .EUt(VA[ZPM])
            .duration(7 * SECOND + 15 * TICK)
            .buildAndRegister()

        // Tl + 2Ba + 2Ca + 3Cu + 10O -> TlBa2Ca2Cu3O10
        GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
            OreDictUnifier.get(foil, Thallium))
        GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
            OreDictUnifier.get(foil, Barium))
        GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
            OreDictUnifier.get(foil, Calcium))
        GTRecipeHandler.removeRecipesByInputs(ARC_FURNACE_RECIPES,
            OreDictUnifier.get(foil, Copper))

        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(foil, Thallium, 2)
            .input(foil, Barium, 2)
            .input(foil, Calcium, 2)
            .input(foil, Copper, 3)
            .fluidInputs(Oxygen.getFluid(5000))
            .output(ingotHot, ThalliumBariumCalciumCuprate, 9)
            .EUt(VA[ZPM])
            .duration(8 * SECOND)
            .buildAndRegister()

        // Optical SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(dust, LeadScandiumTantalate)
            .input(wireFine, ThalliumBariumCalciumCuprate, 4)
            .fluidInputs(KaptonE.getFluid(288))
            .output(OPTICAL_SMD_DIODE, 64)
            .EUt(VA[UV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Optical SMD Inductor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(ring, LutetiumManganeseGermanium)
            .input(wireFine, Curium, 4)
            .fluidInputs(KaptonE.getFluid(L))
            .output(OPTICAL_SMD_INDUCTOR, 32)
            .EUt(VA[UV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Ge + 4Cl -> GeCl4
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, Germanium)
            .fluidInputs(Chlorine.getFluid(4000))
            .fluidOutputs(GermaniumTetrachloride.getFluid(1000))
            .EUt(VA[HV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // GeO2 + 4HCl -> GeCl4 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, GermaniumDioxide, 3)
            .fluidInputs(HydrochloricAcid.getFluid(4000))
            .fluidOutputs(GermaniumTetrachloride.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Si + 4Cl -> SiCl4
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, Silicon)
            .fluidInputs(Chlorine.getFluid(4000))
            .fluidOutputs(SiliconTetrachloride.getFluid(1000))
            .EUt(VA[MV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // SiO2 + 4HCl -> SiCl4 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, SiliconDioxide, 3)
            .fluidInputs(HydrochloricAcid.getFluid(4000))
            .fluidOutputs(SiliconTetrachloride.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Optical Fiber
        LASER_CVD_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .notConsumable(SHAPE_EXTRUDER_WIRE)
            .fluidInputs(GermaniumTetrachloride.getFluid(250))
            .fluidInputs(PhosphorylChloride.getFluid(250))
            .fluidInputs(SiliconTetrachloride.getFluid(1000))
            .output(OPTICAL_FIBER, 8)
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .temperature(1800)
            .buildAndRegister()

        LASER_CVD_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .notConsumable(SHAPE_EXTRUDER_WIRE)
            .fluidInputs(GermaniumTetrachloride.getFluid(2000))
            .fluidInputs(PhosphorylChloride.getFluid(2000))
            .fluidInputs(SiliconTetrachloride.getFluid(8000))
            .output(OPTICAL_FIBER, 64)
            .EUt(VA[UV])
            .duration(2 * MINUTE)
            .temperature(1800)
            .buildAndRegister()

        PLASMA_CVD_RECIPES.recipeBuilder()
            .notConsumable(plate, Selenium)
            .notConsumable(SHAPE_EXTRUDER_WIRE)
            .fluidInputs(GermaniumTetrachloride.getFluid(4000))
            .fluidInputs(PhosphorylChloride.getFluid(4000))
            .fluidInputs(SiliconTetrachloride.getFluid(16000))
            .output(OPTICAL_FIBER, 64)
            .output(OPTICAL_FIBER, 64)
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .temperature(900)
            .buildAndRegister()
    }

    private fun circuitComponentsRecipes()
    {
        // Empty Laser
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .input(DIELECTRIC_MIRROR)
            .input(plate, SterlingSilver, 2)
            .input(ring, TungstenSteel, 2)
            .input(cableGtSingle, Platinum, 2)
            .fluidInputs(GSTGlass.getFluid(L * 2))
            .output(EMPTY_LASER)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Helium-Neon Laser
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Helium.getFluid(1000))
            .fluidInputs(Neon.getFluid(1000))
            .fluidOutputs(HeliumNeon.getFluid(1000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CANNER_RECIPES.recipeBuilder()
            .input(EMPTY_LASER)
            .fluidInputs(HeliumNeon.getFluid(1000))
            .output(HELIUM_NEON_LASER)
            .EUt(VA[HV])
            .duration(6 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Nd:YAG Laser
        CANNER_RECIPES.recipeBuilder()
            .input(EMPTY_LASER)
            .input(gem, NdYAG)
            .output(ND_YAG_LASER)
            .EUt(VA[HV])
            .duration(6 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Optical Laser Control Unit
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(PERFECT_CIRCUIT_BOARD)
            .input(HELIUM_NEON_LASER)
            .input(ND_YAG_LASER)
            .input(lens, Diamond, 2)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(OPTICAL_LASER_CONTROL_UNIT)
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // PRAM
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(ADVANCED_RAM_CHIP)
            .input(plate, GSTGlass, 2)
            .input(foil, Einsteinium, 8)
            .output(PHASE_CHANGE_RAM_CHIP, 4)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // ACNOR
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(NOR_MEMORY_CHIP)
            .input(foil, LithiumNiobate, 4)
            .input(wireFine, WoodsGlass, 4)
            .output(ALL_OPTICAL_CASCADE_NOR_CHIP, 4)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun systemOnChipRecipes()
    {
        // 2CeO2 + 3NaHCO3 -> Ce2(CO3)3 + 3NaOH + O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, CeriumOxide, 6)
            .input(dust, SodiumBicarbonate, 18)
            .output(dust, CeriumCarbonate, 14)
            .output(dust, SodiumHydroxide, 9)
            .fluidOutputs(Oxygen.getFluid(1000))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Ce2(CO3)3 + Bh -> Bh-doped Ce2(CO3)3 Boule
        CRYSTALLIZATION_RECIPES.recipeBuilder()
            .input(dust, CeriumCarbonate, 64)
            .input(dust, Bohrium, 8)
            .output(BOHRIUM_DOPED_CERIUM_CARBONATE_BOULE)
            .EUt(VA[EV])
            .duration(6 * SECOND)
            .blastFurnaceTemp(6000) // Naquadah
            .buildAndRegister()

        // Bh-doped Ce2(CO3)3 Boule -> Bh-doped Ce2(CO3)3 Wafer
        CUTTER_RECIPES.recipeBuilder()
            .input(BOHRIUM_DOPED_CERIUM_CARBONATE_BOULE)
            .fluidInputs(Lubricant.getFluid(100))
            .output(BOHRIUM_DOPED_CERIUM_CARBONATE_WAFER, 4)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Zn + S -> ZnS
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, Zinc)
            .input(dust, Sulfur)
            .output(dust, ZincSulfide, 2)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Mn + 2F -> MnF2
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Manganese)
            .fluidInputs(Fluorine.getFluid(2000))
            .output(dust, ManganeseDifluoride, 3)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // MnF2 + ZnS + Ta2O5 + TiO2 + C2H6O -> (MnF2)(ZnS)(Ta2O5)(TiO2)(C2H6O)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, ManganeseDifluoride, 3)
            .input(dust, ZincSulfide, 2)
            .input(dust, TantalumPentoxide, 7)
            .input(dust, Rutile, 3)
            .fluidInputs(Ethanol.getFluid(1000))
            .fluidOutputs(DielectricFormationMixture.getFluid(8000))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // (MnF2)(ZnS)(Ta2O5)(TiO2)(C2H6O) -> MnF2 + ZnS + Ta2O5 + TiO2 + C2H6O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .fluidInputs(DielectricFormationMixture.getFluid(8000))
            .output(dust, ManganeseDifluoride, 3)
            .output(dust, ZincSulfide, 2)
            .output(dust, TantalumPentoxide, 7)
            .output(dust, Rutile, 3)
            .fluidOutputs(Ethanol.getFluid(1000))
            .EUt(VA[MV])
            .duration(4 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Bh-doped Ce2(CO3)3 Wafer -> Periodically Poled Optical Chip
        CUTTER_RECIPES.recipeBuilder()
            .input(BOHRIUM_DOPED_CERIUM_CARBONATE_WAFER)
            .fluidInputs(DielectricFormationMixture.getFluid(200))
            .output(PERIODICALLY_POLED_OPTICAL_CHIP, 2)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Optical IMC Unit
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(lens, CubicSiliconNitride)
            .input(ENGRAVED_LAPOTRON_CHIP)
            .input(nanite, Glowstone)
            .input(PHASE_CHANGE_RAM_CHIP, 2)
            .fluidInputs(LightQuarks.getFluid(500))
            .output(OPTICAL_IMC_UNIT, 4)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Optoelectronic System on Chip
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(OPTICAL_IMC_UNIT, 2)
            .input(PERIODICALLY_POLED_OPTICAL_CHIP, 2)
            .input(ALL_OPTICAL_CASCADE_NOR_CHIP, 4)
            .input(springSmall, Moscovium)
            .input(OPTICAL_FIBER, 8)
            .output(OPTOELECTRONIC_SYSTEM_ON_CHIP, 2)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun circuitRecipes()
    {
        // Optical Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(OPTICAL_LASER_CONTROL_UNIT)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(OPTICAL_SMD_RESISTOR, 16)
            .input(OPTICAL_SMD_CAPACITOR, 16)
            .input(OPTICAL_SMD_TRANSISTOR, 16)
            .input(OPTICAL_FIBER, 8)
            .output(OPTICAL_PROCESSOR_UV, 4)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .solderMultiplier(1)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(OPTICAL_LASER_CONTROL_UNIT)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(SPINTRONIC_SMD_RESISTOR, 4)
            .input(SPINTRONIC_SMD_CAPACITOR, 4)
            .input(SPINTRONIC_SMD_TRANSISTOR, 4)
            .input(OPTICAL_FIBER, 8)
            .output(OPTICAL_PROCESSOR_UV, 4)
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .solderMultiplier(1)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(OPTICAL_LASER_CONTROL_UNIT)
            .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
            .input(COSMIC_SMD_RESISTOR)
            .input(COSMIC_SMD_CAPACITOR)
            .input(COSMIC_SMD_TRANSISTOR)
            .input(OPTICAL_FIBER, 8)
            .output(OPTICAL_PROCESSOR_UV, 4)
            .EUt(VA[UHV])
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .solderMultiplier(1)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(OPTICAL_LASER_CONTROL_UNIT)
            .input(OPTOELECTRONIC_SYSTEM_ON_CHIP)
            .input(OPTICAL_FIBER, 8)
            .input(bolt, Bedrockium, 8)
            .output(OPTICAL_PROCESSOR_UV, 8)
            .EUt(VA[UEV])
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .solderMultiplier(1)
            .buildAndRegister()

        // Optical Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 4)
            .input(OPTICAL_SMD_INDUCTOR, 16)
            .input(OPTICAL_SMD_CAPACITOR, 32)
            .input(HIGHLY_ADVANCED_SOC, 32)
            .input(OPTICAL_FIBER, 16)
            .output(OPTICAL_ASSEMBLY_UHV, 3)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .solderMultiplier(2)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 4)
            .input(SPINTRONIC_SMD_INDUCTOR, 4)
            .input(SPINTRONIC_SMD_CAPACITOR, 8)
            .input(HIGHLY_ADVANCED_SOC, 32)
            .input(OPTICAL_FIBER, 16)
            .output(OPTICAL_ASSEMBLY_UHV, 3)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .solderMultiplier(2)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 4)
            .input(COSMIC_SMD_INDUCTOR)
            .input(COSMIC_SMD_CAPACITOR, 2)
            .input(HIGHLY_ADVANCED_SOC, 32)
            .input(OPTICAL_FIBER, 16)
            .output(OPTICAL_ASSEMBLY_UHV, 3)
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .solderMultiplier(2)
            .buildAndRegister()

        // Optical Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_ASSEMBLY_UHV, 3)
            .input(OPTICAL_SMD_DIODE, 8)
            .input(ALL_OPTICAL_CASCADE_NOR_CHIP, 16)
            .input(PHASE_CHANGE_RAM_CHIP, 32)
            .input(OPTICAL_FIBER, 24)
            .input(foil, KaptonE, 32)
            .input(plate, Dubnium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .fluidInputs(Polyetheretherketone.getFluid(L * 4))
            .output(OPTICAL_COMPUTER_UEV, 2)
            .EUt(VA[UHV])
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(OPTICAL_ASSEMBLY_UHV.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_ASSEMBLY_UHV, 3)
            .input(SPINTRONIC_SMD_DIODE, 2)
            .input(ALL_OPTICAL_CASCADE_NOR_CHIP, 16)
            .input(PHASE_CHANGE_RAM_CHIP, 32)
            .input(OPTICAL_FIBER, 24)
            .input(foil, KaptonE, 32)
            .input(plate, Dubnium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .fluidInputs(Polyetheretherketone.getFluid(L * 4))
            .output(OPTICAL_COMPUTER_UEV, 2)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(OPTICAL_ASSEMBLY_UHV.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Optical Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium)
            .input(OPTICAL_COMPUTER_UEV, 2)
            .input(OPTICAL_SMD_DIODE, 64)
            .input(OPTICAL_SMD_CAPACITOR, 64)
            .input(OPTICAL_SMD_TRANSISTOR, 64)
            .input(OPTICAL_SMD_RESISTOR, 64)
            .input(OPTICAL_SMD_INDUCTOR, 64)
            .input(foil, KaptonE, 64)
            .input(PHASE_CHANGE_RAM_CHIP, 32)
            .input(wireGtDouble, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .input(plate, Dubnium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(KaptonE.getFluid(L * 32))
            .fluidInputs(Polyetheretherketone.getFluid(L * 8))
            .output(OPTICAL_MAINFRAME_UIV)
            .EUt(VA[UEV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch { r ->
                r.researchStack(OPTICAL_COMPUTER_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium)
            .input(OPTICAL_COMPUTER_UEV, 2)
            .input(SPINTRONIC_SMD_DIODE, 16)
            .input(SPINTRONIC_SMD_CAPACITOR, 16)
            .input(SPINTRONIC_SMD_TRANSISTOR, 16)
            .input(SPINTRONIC_SMD_RESISTOR, 16)
            .input(SPINTRONIC_SMD_INDUCTOR, 16)
            .input(foil, KaptonE, 64)
            .input(PHASE_CHANGE_RAM_CHIP, 32)
            .input(wireGtDouble, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .input(plate, Dubnium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(KaptonE.getFluid(L * 32))
            .fluidInputs(Polyetheretherketone.getFluid(L * 8))
            .output(OPTICAL_MAINFRAME_UIV)
            .EUt(VA[UEV])
            .duration(45 * SECOND)
            .stationResearch { r ->
                r.researchStack(OPTICAL_COMPUTER_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium)
            .input(OPTICAL_COMPUTER_UEV, 2)
            .input(COSMIC_SMD_DIODE, 4)
            .input(COSMIC_SMD_CAPACITOR, 4)
            .input(COSMIC_SMD_TRANSISTOR, 4)
            .input(COSMIC_SMD_RESISTOR, 4)
            .input(COSMIC_SMD_INDUCTOR, 4)
            .input(foil, KaptonE, 64)
            .input(PHASE_CHANGE_RAM_CHIP, 32)
            .input(wireGtDouble, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .input(plate, Dubnium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(KaptonE.getFluid(L * 32))
            .fluidInputs(Polyetheretherketone.getFluid(L * 8))
            .output(OPTICAL_MAINFRAME_UIV)
            .EUt(VA[UEV])
            .duration(25 * SECOND)
            .stationResearch { r ->
                r.researchStack(OPTICAL_COMPUTER_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium)
            .input(OPTICAL_COMPUTER_UEV, 2)
            .input(SUPRACAUSAL_SMD_DIODE)
            .input(SUPRACAUSAL_SMD_CAPACITOR)
            .input(SUPRACAUSAL_SMD_TRANSISTOR)
            .input(SUPRACAUSAL_SMD_RESISTOR)
            .input(SUPRACAUSAL_SMD_INDUCTOR)
            .input(foil, KaptonE, 64)
            .input(PHASE_CHANGE_RAM_CHIP, 32)
            .input(wireGtDouble, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .input(plate, Dubnium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(KaptonE.getFluid(L * 32))
            .fluidInputs(Polyetheretherketone.getFluid(L * 8))
            .output(OPTICAL_MAINFRAME_UIV)
            .EUt(VA[UEV])
            .duration(15 * SECOND)
            .stationResearch { r ->
                r.researchStack(OPTICAL_COMPUTER_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

    }

    // @formatter:on

}