package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.PhosphoricAcid
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.SaltWater
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tennessine
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Alumina
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmorphousBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aniline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthChalcogenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthTelluride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumSelenide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumSulfide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumCeriumCobaltIndium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CeriumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromiumGermaniumTellurideMagnetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CobaltAluminate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cyclooctadiene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DegenerateRhenium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ErbiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthylenediaminePyrocatechol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorobenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HafniumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrobromicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IndiumPhosphate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumNiobate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LutetiumManganeseGermanium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MercuryCadmiumTelluride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NDifluorophenylpyrrole
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PedotTMA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phenylsodium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosphine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phosphorene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhotopolymerSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Plutonium244
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlutoniumPhosphide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PlutoniumTrihydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PraseodymiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilverChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilverOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilverPerchlorate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilverTetrafluoroborate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumFluoride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPerchlorate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumSeaborgate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuccinicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumHafniumSeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetraethylammoniumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TetramethylammoniumHydroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumBariumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumRoentgeniumChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TrichlorocyclopentadienylTitanium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trichloroethylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zylon
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOSE_EINSTEIN_CONDENSATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONDENSATE_CONTAINMENT_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ESR_COMPUTATION_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITATION_MAINTAINER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXOTIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HELIUM_NEON_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.INFINITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RYDBERG_SPINOR_ARRAY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_ASSEMBLY_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_COMPUTER_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_MAINFRAME_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_PROCESSOR_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPIN_TRANSFER_TORQUE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOPOLOGICAL_INSULATOR_TUBE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTRA_HIGHLY_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.X_RAY_WAVEGUIDE

internal object SpintronicCircuits
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
        // Spintronic Board
        CVD_RECIPES.recipeBuilder()
            .input(plate, CarbonNanotube)
            .input(foil, Phosphorene, 64)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(SPINTRONIC_BOARD)
            .EUt(VA[UEV])
            .duration(2 * SECOND)
            .temperature(3580)
            .buildAndRegister()

        // Infinite Circuit Board
        for (etchingLiquid in arrayOf(
            // SodiumPersulfate.getFluid(64000),
            // Iron3Chloride.getFluid(32000), 16000, 8000
            TetramethylammoniumHydroxide.getFluid(64000),
            EthylenediaminePyrocatechol.getFluid(32000)
        ))
        {
            CHEMICAL_RECIPES.recipeBuilder()
                .input(SPINTRONIC_BOARD)
                .input(foil, Dubnium, 64)
                .fluidInputs(etchingLiquid)
                .output(INFINITE_CIRCUIT_BOARD)
                .EUt(VA[IV])
                .duration(2 * MINUTE)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }
    }

    private fun smdRecipes()
    {
        // Cd + 2Te + 2Hg -> Hg2CdTe2
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, Cadmium)
            .input(dust, Tellurium, 2)
            .fluidInputs(Mercury.getFluid(2000))
            .output(dust, MercuryCadmiumTelluride, 5)
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Spintronic SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(foil, AmorphousBoronNitride)
            .input(wireFine, MercuryCadmiumTelluride, 16)
            .fluidInputs(Kevlar.getFluid(L))
            .output(SPINTRONIC_SMD_TRANSISTOR, 32)
            .EUt(VA[UHV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Add another recipes for InPO4 because this is a byproduct in Nq processing.
        // In + H3PO4 -> InPO4 + 3H
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Indium)
            .fluidInputs(PhosphoricAcid.getFluid(1000))
            .output(dust, IndiumPhosphate, 6)
            .fluidOutputs(Hydrogen.getFluid(3000))
            .EUt(VA[HV])
            .duration(2 * SECOND + 16 * TICK)
            .buildAndRegister()

        // CsOH + CeO2 + 2CoAl2O4 + 10InPO4 + 9H -> CsCeCo2In10 + 2Al2O3 + 10PO4 + 5H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, CaesiumHydroxide, 3)
            .input(dust, CeriumOxide, 3)
            .input(dust, CobaltAluminate, 14)
            .input(dust, IndiumPhosphate, 60)
            .fluidInputs(Hydrogen.getFluid(9000))
            .output(dust, CaesiumCeriumCobaltIndium, 14)
            .output(dust, Alumina, 10)
            .output(dust, Phosphorus, 10)
            .fluidOutputs(Oxygen.getFluid(40000))
            .fluidOutputs(Water.getFluid(5000))
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Spintronic SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(dust, DegenerateRhenium)
            .input(wireFine, CaesiumCeriumCobaltIndium, 8)
            .fluidInputs(Kevlar.getFluid(L * 2))
            .output(SPINTRONIC_SMD_RESISTOR, 32)
            .EUt(VA[UHV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // 12TaC + 3HfC + SgC -> Ta12Hf3SgC16
        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(dust, TantalumCarbide, 24)
            .input(dust, HafniumCarbide, 6)
            .input(dust, SeaborgiumCarbide, 2)
            .output(ingotHot, TantalumHafniumSeaborgiumCarbide, 32)
            .EUt(VA[UHV])
            .duration(1 * MINUTE + 12 * SECOND)
            .buildAndRegister()

        // Spintronic SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(foil, TantalumHafniumSeaborgiumCarbide)
            .input(foil, MetastableFlerovium, 2)
            .fluidInputs(Kevlar.getFluid(L / 2))
            .output(SPINTRONIC_SMD_CAPACITOR, 32)
            .EUt(VA[UHV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // 2NaOH + Sg + 6F + 2H2O -> Na2SgO4 + 6HF
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, SodiumHydroxide, 6)
            .input(dust, Seaborgium)
            .fluidInputs(Fluorine.getFluid(6000))
            .fluidInputs(Water.getFluid(2000))
            .output(dust, SodiumSeaborgate, 7)
            .fluidOutputs(HydrofluoricAcid.getFluid(6000))
            .EUt(VA[UV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // Spintronic SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(dust, ActiniumSuperhydride)
            .input(wireFine, SodiumSeaborgate, 8)
            .fluidInputs(Kevlar.getFluid(L * 2))
            .output(SPINTRONIC_SMD_DIODE, 64)
            .EUt(VA[UHV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Tl + Rg + 3Cl -> TlRgCl3
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Thallium)
            .input(dust, Roentgenium)
            .fluidInputs(Chlorine.getFluid(3000))
            .output(dust, ThalliumRoentgeniumChloride, 5)
            .EUt(VA[UHV])
            .duration(24 * SECOND)
            .buildAndRegister()

        // Spintronic SMD Inductor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(ring, ThalliumRoentgeniumChloride)
            .input(wireFine, Moscovium, 8)
            .fluidInputs(Kevlar.getFluid(L))
            .output(SPINTRONIC_SMD_INDUCTOR, 32)
            .EUt(VA[UHV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

    }

    private fun circuitComponentsRecipes()
    {
        // Topological Insulator Tube
        CANNER_RECIPES.recipeBuilder()
            .input(wireFine, MercuryCadmiumTelluride, 16)
            .input(spring, CarbonNanotube)
            .output(TOPOLOGICAL_INSULATOR_TUBE)
            .EUt(VA[IV])
            .duration(1 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Condensate Containment Unit
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(FIELD_GENERATOR_IV)
            .input(HELIUM_NEON_LASER)
            .input(plate, Trinium, 2)
            .input(cableGtSingle, Europium, 2)
            .inputs(GlassCasing.PMMA.getStack(2))
            .output(CONDENSATE_CONTAINMENT_UNIT)
            .EUt(VA[UV])
            .duration(4 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Bose-Einstein Condensate Containment Unit
        CANNER_RECIPES.recipeBuilder()
            .input(CONDENSATE_CONTAINMENT_UNIT)
            .input(dust, Rubidium, 8)
            .output(BOSE_EINSTEIN_CONDENSATE)
            .EUt(VA[UV])
            .duration(1 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // 2Bi2Te3 + 3Sb + 3S -> 3BiSbTe2S + Bi
        MOLECULAR_BEAM_RECIPES.recipeBuilder()
            .notConsumable(plate, CadmiumSulfide)
            .input(dust, BismuthTelluride, 10)
            .input(dust, Antimony, 3)
            .input(dust, Sulfur, 3)
            .output(dust, BismuthChalcogenide, 15)
            .output(dust, Bismuth)
            .EUt(VA[UV])
            .duration(4 * SECOND)
            .temperature(4876)
            .buildAndRegister()

        // Pu244 + 3H -> PuH3
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Plutonium244)
            .fluidInputs(Hydrogen.getFluid(3000))
            .output(dust, PlutoniumTrihydride, 4)
            .EUt(VA[IV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // Pu + PH3 -> PuP + 6H
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, PlutoniumTrihydride, 4)
            .fluidInputs(Phosphine.getFluid(1000))
            .output(dust, PlutoniumPhosphide, 2)
            .fluidOutputs(Hydrogen.getFluid(6000))
            .EUt(VA[LuV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // ESR Computation Unit
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(plate, PlutoniumPhosphide, 2)
            .input(plate, LutetiumManganeseGermanium)
            .input(foil, BismuthChalcogenide, 4)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(BOSE_EINSTEIN_CONDENSATE)
            .input(wireFine, ThalliumRoentgeniumChloride, 24)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(ESR_COMPUTATION_UNIT)
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // STTRAM
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(ADVANCED_RAM_CHIP)
            .input(plate, ErbiumDopedZBLANGlass, 2)
            .input(plate, PraseodymiumDopedZBLANGlass, 2)
            .input(foil, CaesiumCeriumCobaltIndium, 8)
            .input(wireFine, PedotTMA, 16)
            .output(SPIN_TRANSFER_TORQUE_RAM_CHIP, 4)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // MINAND
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(NAND_MEMORY_CHIP)
            .input(foil, SeaborgiumCarbide, 4)
            .input(foil, ThalliumBariumCalciumCuprate, 4)
            .input(springSmall, ChromiumGermaniumTellurideMagnetic, 2)
            .input(wireFine, FullerenePolymerMatrix, 8)
            .output(MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP, 4)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

    }

    private fun systemOnChipRecipes()
    {
        // Excitation Maintainer
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(CONDENSATE_CONTAINMENT_UNIT)
            .input(plate, MetastableHassium)
            .input(lens, LithiumNiobate)
            .input(screw, VanadiumGallium, 4)
            .fluidInputs(FreeElectronGas.getFluid(16000))
            .output(EXCITATION_MAINTAINER)
            .EUt(VA[UHV])
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // TiO2 + C2HCl3 + C8H12 -> (C5H5)2Cl3Ti + H2O2 + H (drop)
        CVD_RECIPES.recipeBuilder()
            .input(dust, Rutile, 3)
            .fluidInputs(Trichloroethylene.getFluid(1000))
            .fluidInputs(Cyclooctadiene.getFluid(1000))
            .output(dust, TrichlorocyclopentadienylTitanium, 24)
            .fluidOutputs(HydrogenPeroxide.getFluid(1000))
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .temperature(1442)
            .buildAndRegister()

        // 2NaClO4 + Ag2O -> 2AgClO4 + Na2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, SodiumPerchlorate, 12)
            .input(dust, SilverOxide, 3)
            .output(dust, SilverPerchlorate, 12)
            .output(dust, SodiumOxide, 3)
            .EUt(VA[MV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C4H6O2 + C6H5NH2 + 2HF -> C10H7F2N + 2H2O + 4H
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, SuccinicAcid, 12)
            .fluidInputs(Aniline.getFluid(1000))
            .fluidInputs(HydrofluoricAcid.getFluid(2000))
            .fluidOutputs(NDifluorophenylpyrrole.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(4000))
            .fluidOutputs(Ice.getFluid(2000))
            .EUt(VA[IV])
            .duration(4 * SECOND + 5 * TICK)
            .buildAndRegister()

        // HBr + NH3 + 4C2H4 -> N(CH2CH3)4Br
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(HydrobromicAcid.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidInputs(Ethylene.getFluid(4000))
            .fluidOutputs(TetraethylammoniumBromide.getFluid(1000))
            .EUt(VA[IV])
            .duration(2 * SECOND + 15 * TICK)
            .buildAndRegister()

        // Na + C6H5F -> C6H5Na + NaF
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Sodium)
            .fluidInputs(Fluorobenzene.getFluid(1000))
            .output(dust, SodiumFluoride, 2)
            .fluidOutputs(Phenylsodium.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 3(C5H5)2Cl3Ti + 2AgBF4 + 2AgClO4 + 6C10H7F2N + 2N(CH2CH3)4Br + 8C6H5Na
        //         + 2HCl + 42H2O -> C149H97N10O2(TiBF20) + 4AgCl + 2NaBr + 6(NaCl)(H2O)
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, TrichlorocyclopentadienylTitanium, 64)
            .input(dust, TrichlorocyclopentadienylTitanium, 8)
            .input(dust, SilverTetrafluoroborate, 12)
            .input(dust, SilverPerchlorate, 12)
            .fluidInputs(NDifluorophenylpyrrole.getFluid(6000))
            .fluidInputs(TetraethylammoniumBromide.getFluid(2000))
            .fluidInputs(Phenylsodium.getFluid(8000))
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .fluidInputs(Ice.getFluid(42000))
            .output(dust, SilverChloride, 8)
            .output(dust, SodiumBromide, 4)
            .fluidOutputs(PhotopolymerSolution.getFluid(4000))
            .fluidOutputs(SaltWater.getFluid(6000))
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // X-Ray Waveguide
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(wireFine, FullerenePolymerMatrix)
            .fluidInputs(PhotopolymerSolution.getFluid(100))
            .output(X_RAY_WAVEGUIDE)
            .EUt(VA[UHV])
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Rydberg Spinor Array
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(plate, Graphene)
            .input(EXCITATION_MAINTAINER)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 2)
            .input(X_RAY_WAVEGUIDE)
            .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 8)
            .input(screw, Neutronium, 4)
            .fluidInputs(Zylon.getFluid(L))
            .fluidInputs(Xenon.getFluid(250))
            .fluidInputs(CadmiumSelenide.getFluid(L))
            .output(RYDBERG_SPINOR_ARRAY)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .tier(3)
            .buildAndRegister()

        // Advanced recipes for Rydberg Spinor Array
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(plate, SeaborgiumDopedCarbonNanotube)
            .input(EXCITATION_MAINTAINER, 2)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 4)
            .input(X_RAY_WAVEGUIDE, 2)
            .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 16)
            .input(screw, CosmicNeutronium, 8)
            .fluidInputs(Zylon.getFluid(L * 4))
            .fluidInputs(Xenon.getPlasma(250))
            .fluidInputs(CadmiumSelenide.getFluid(L * 2))
            .output(RYDBERG_SPINOR_ARRAY, 4)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .tier(4)
            .buildAndRegister()

        // Ultimate recipes for Rydberg Spinor Array
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(plate, FullereneSuperconductor)
            .input(EXCITATION_MAINTAINER, 4)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 8)
            .input(X_RAY_WAVEGUIDE, 4)
            .input(wireGtSingle, VibraniumTritaniumActiniumIronSuperhydride, 32)
            .input(screw, Infinity, 16)
            .fluidInputs(Zylon.getFluid(L * 8))
            .fluidInputs(Xenon.getPlasma(500))
            .fluidInputs(CadmiumSelenide.getFluid(L * 4))
            .output(RYDBERG_SPINOR_ARRAY, 16)
            .EUt(VA[UIV])
            .duration(2 * SECOND + 10 * TICK)
            .tier(5)
            .buildAndRegister()

        // Exotic System On Chip
        FORMING_PRESS_RECIPES.recipeBuilder()
            .input(RYDBERG_SPINOR_ARRAY)
            .input(MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP, 2)
            .input(FEMTO_PIC_CHIP, 4)
            .input(springSmall, HeavyQuarkDegenerateMatter, 2)
            .input(foil, Tennessine, 8)
            .input(bolt, Rhugnor, 4)
            .output(EXOTIC_SYSTEM_ON_CHIP, 2)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun circuitRecipes()
    {
        // Spintronic Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ESR_COMPUTATION_UNIT)
            .input(CRYSTAL_SYSTEM_ON_CHIP)
            .input(SPINTRONIC_SMD_RESISTOR, 16)
            .input(SPINTRONIC_SMD_CAPACITOR, 16)
            .input(SPINTRONIC_SMD_TRANSISTOR, 16)
            .input(wireFine, CarbonNanotube, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 4)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ESR_COMPUTATION_UNIT)
            .input(CRYSTAL_SYSTEM_ON_CHIP)
            .input(COSMIC_SMD_RESISTOR, 4)
            .input(COSMIC_SMD_CAPACITOR, 4)
            .input(COSMIC_SMD_TRANSISTOR, 4)
            .input(wireFine, CarbonNanotube, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 4)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ESR_COMPUTATION_UNIT)
            .input(CRYSTAL_SYSTEM_ON_CHIP)
            .input(SUPRACAUSAL_SMD_RESISTOR)
            .input(SUPRACAUSAL_SMD_CAPACITOR)
            .input(SUPRACAUSAL_SMD_TRANSISTOR)
            .input(wireFine, CarbonNanotube, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 4)
            .EUt(VA[UEV])
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(ESR_COMPUTATION_UNIT)
            .input(EXOTIC_SYSTEM_ON_CHIP)
            .input(wireFine, CarbonNanotube, 8)
            .input(bolt, Vibranium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 8)
            .EUt(VA[UIV])
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Spintronic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_PROCESSOR_UHV, 4)
            .input(SPINTRONIC_SMD_CAPACITOR, 16)
            .input(SPINTRONIC_SMD_INDUCTOR, 16)
            .input(ULTRA_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(wireFine, CarbonNanotube, 16)
            .input("foilAnyAdvancedSyntheticRubber", 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 4))
            .output(SPINTRONIC_ASSEMBLY_UEV, 3)
            .EUt(VA[UEV])
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(SPINTRONIC_PROCESSOR_UHV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_PROCESSOR_UHV, 4)
            .input(COSMIC_SMD_CAPACITOR, 4)
            .input(COSMIC_SMD_INDUCTOR, 4)
            .input(ULTRA_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(wireFine, CarbonNanotube, 16)
            .input("foilAnyAdvancedSyntheticRubber", 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 4))
            .output(SPINTRONIC_ASSEMBLY_UEV, 3)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .stationResearch { r ->
                r.researchStack(SPINTRONIC_PROCESSOR_UHV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_PROCESSOR_UHV, 4)
            .input(SUPRACAUSAL_SMD_CAPACITOR)
            .input(SUPRACAUSAL_SMD_INDUCTOR)
            .input(ULTRA_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(wireFine, CarbonNanotube, 16)
            .input("foilAnyAdvancedSyntheticRubber", 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 8))
            .output(SPINTRONIC_ASSEMBLY_UEV, 3)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .stationResearch { r ->
                r.researchStack(SPINTRONIC_PROCESSOR_UHV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Spintronic Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_ASSEMBLY_UEV, 3)
            .input(SPINTRONIC_SMD_DIODE, 16)
            .input(MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP, 32)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 64)
            .input(wireFine, CarbonNanotube, 32)
            .input(foil, Fullerene, 48)
            .input(plate, Bohrium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(Zylon.getFluid(L * 8))
            .fluidInputs(Adamantium.getFluid(L * 4))
            .output(SPINTRONIC_COMPUTER_UIV, 2)
            .EUt(VA[UEV])
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(SPINTRONIC_ASSEMBLY_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_ASSEMBLY_UEV, 3)
            .input(COSMIC_SMD_DIODE, 4)
            .input(MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP, 32)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 64)
            .input(wireFine, CarbonNanotube, 32)
            .input(foil, Fullerene, 48)
            .input(plate, Bohrium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(Zylon.getFluid(L * 8))
            .fluidInputs(Adamantium.getFluid(L * 4))
            .output(SPINTRONIC_COMPUTER_UIV, 2)
            .EUt(VA[UEV])
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(SPINTRONIC_ASSEMBLY_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(INFINITE_CIRCUIT_BOARD)
            .input(SPINTRONIC_ASSEMBLY_UEV, 3)
            .input(SUPRACAUSAL_SMD_DIODE)
            .input(MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP, 32)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 64)
            .input(wireFine, CarbonNanotube, 32)
            .input(foil, Fullerene, 48)
            .input(plate, Bohrium, 8)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(Zylon.getFluid(L * 8))
            .fluidInputs(Adamantium.getFluid(L * 4))
            .output(SPINTRONIC_COMPUTER_UIV, 2)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .stationResearch { r ->
                r.researchStack(SPINTRONIC_ASSEMBLY_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Spintronic Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Vibranium)
            .input(SPINTRONIC_COMPUTER_UIV, 2)
            .input(SPINTRONIC_SMD_DIODE, 64)
            .input(SPINTRONIC_SMD_CAPACITOR, 64)
            .input(SPINTRONIC_SMD_TRANSISTOR, 64)
            .input(SPINTRONIC_SMD_RESISTOR, 64)
            .input(SPINTRONIC_SMD_INDUCTOR, 64)
            .input(foil, Fullerene, 64)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 48)
            .input(wireGtDouble, FullereneSuperconductor, 16)
            .input(plate, Bohrium, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(Fullerene.getFluid(L * 32))
            .fluidInputs(Zylon.getFluid(L * 16))
            .fluidInputs(Adamantium.getFluid(L * 8))
            .output(SPINTRONIC_MAINFRAME_UXV)
            .EUt(VA[UIV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch { r -> r
                .researchStack(SPINTRONIC_COMPUTER_UIV.stackForm)
                .EUt(VA[UIV])
                .CWUt(96)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Vibranium)
            .input(SPINTRONIC_COMPUTER_UIV, 2)
            .input(COSMIC_SMD_DIODE, 16)
            .input(COSMIC_SMD_CAPACITOR, 16)
            .input(COSMIC_SMD_TRANSISTOR, 16)
            .input(COSMIC_SMD_RESISTOR, 16)
            .input(COSMIC_SMD_INDUCTOR, 16)
            .input(foil, Fullerene, 64)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 48)
            .input(wireGtDouble, FullereneSuperconductor, 16)
            .input(plate, Bohrium, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(Fullerene.getFluid(L * 32))
            .fluidInputs(Zylon.getFluid(L * 16))
            .fluidInputs(Adamantium.getFluid(L * 8))
            .output(SPINTRONIC_MAINFRAME_UXV)
            .EUt(VA[UIV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch { r -> r
                .researchStack(SPINTRONIC_COMPUTER_UIV.stackForm)
                .EUt(VA[UIV])
                .CWUt(96)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Vibranium)
            .input(SPINTRONIC_COMPUTER_UIV, 2)
            .input(SUPRACAUSAL_SMD_DIODE, 4)
            .input(SUPRACAUSAL_SMD_CAPACITOR, 4)
            .input(SUPRACAUSAL_SMD_TRANSISTOR, 4)
            .input(SUPRACAUSAL_SMD_RESISTOR, 4)
            .input(SUPRACAUSAL_SMD_INDUCTOR, 4)
            .input(foil, Fullerene, 64)
            .input(SPIN_TRANSFER_TORQUE_RAM_CHIP, 48)
            .input(wireGtDouble, FullereneSuperconductor, 16)
            .input(plate, Bohrium, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(Fullerene.getFluid(L * 32))
            .fluidInputs(Zylon.getFluid(L * 16))
            .fluidInputs(Adamantium.getFluid(L * 8))
            .output(SPINTRONIC_MAINFRAME_UXV)
            .EUt(VA[UIV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch { r -> r
                .researchStack(SPINTRONIC_COMPUTER_UIV.stackForm)
                .EUt(VA[UIV])
                .CWUt(96)
            }
            .buildAndRegister()

    }

    // @formatter:on

}