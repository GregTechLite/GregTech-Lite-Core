package magicbook.gtlitecore.loader.recipe.circuit

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
import gregtech.api.unification.material.Materials.Phosphate
import gregtech.api.unification.material.Materials.PhosphoricAcid
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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MOLECULAR_BEAM_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SPACE_ASSEMBLER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmorphousBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aniline
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BismuthChalcogenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumSelenide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CadmiumSulfide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumCeriumCobaltIndium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CeriumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTellurideMagnetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CobaltAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cyclooctadiene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErbiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fluorobenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FreeElectronGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullereneSuperconductor
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HafniumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkDegenerateMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IndiumPhosphate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumNiobate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumManganeseGermanium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MercuryCadmiumTelluride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NDifluorophenylpyrrole
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PedotTMA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phenylsodium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phosphine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phosphorene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhotopolymerSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Plutonium244
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PlutoniumPhosphide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PlutoniumTrihydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Rhugnor
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaborgiumDopedCarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverPerchlorate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverTetrafluoroborate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPerchlorate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumSeaborgate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuccinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumHafniumSeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetraethylammoniumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetramethylammoniumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumBariumCalciumCuprate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumRoentgeniumChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TrichlorocyclopentadienylTitanium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trichloroethylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VibraniumTritaniumActiniumIronSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zylon
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BOSE_EINSTEIN_CONDENSATE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CONDENSATE_CONTAINMENT_UNIT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ESR_COMPUTATION_UNIT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.EXCITATION_MAINTAINER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.EXOTIC_SYSTEM_ON_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.FEMTO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.HELIUM_NEON_LASER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.INFINITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RYDBERG_SPINOR_ARRAY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_ASSEMBLY_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_COMPUTER_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_MAINFRAME_UXV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_PROCESSOR_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPIN_TRANSFER_TORQUE_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.TOPOLOGICAL_INSULATOR_TUBE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTRA_HIGHLY_ADVANCED_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.X_RAY_WAVEGUIDE

@Suppress("MISSING_DEPENDENCY_CLASS")
class SpintronicCircuits
{

    companion object
    {

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
                .EUt(VA[UEV].toLong())
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
                    .EUt(VA[IV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[HV].toLong())
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
                .output(dust, Phosphate, 50)
                .fluidOutputs(Water.getFluid(5000))
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UHV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // 12TaC + 3HfC + SgC -> Ta12Hf3SgC16
            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, TantalumCarbide, 24)
                .input(dust, HafniumCarbide, 6)
                .input(dust, SeaborgiumCarbide, 2)
                .output(ingotHot, TantalumHafniumSeaborgiumCarbide, 32)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 12 * SECOND)
                .buildAndRegister()

            // Spintronic SMD Capacitor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(foil, TantalumHafniumSeaborgiumCarbide)
                .input(foil, MetastableFlerovium, 2)
                .fluidInputs(Kevlar.getFluid(L / 2))
                .output(SPINTRONIC_SMD_CAPACITOR, 32)
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // Spintronic SMD Diode
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(dust, ActiniumSuperhydride)
                .input(wireFine, SodiumSeaborgate, 8)
                .fluidInputs(Kevlar.getFluid(L * 2))
                .output(SPINTRONIC_SMD_DIODE, 64)
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UHV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // Spintronic SMD Inductor
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ring, ThalliumRoentgeniumChloride)
                .input(wireFine, Moscovium, 8)
                .fluidInputs(Kevlar.getFluid(L))
                .output(SPINTRONIC_SMD_INDUCTOR, 32)
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[IV].toLong())
                .duration(1 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Condensate Containment Unit
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(FIELD_GENERATOR_IV)
                .input(HELIUM_NEON_LASER)
                .input(plate, Trinium, 2)
                .input(cableGtSingle, Europium, 2)
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.PMMA, 2))
                .output(CONDENSATE_CONTAINMENT_UNIT)
                .EUt(VA[UV].toLong())
                .duration(4 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Bose-Einstein Condensate Containment Unit
            CANNER_RECIPES.recipeBuilder()
                .input(CONDENSATE_CONTAINMENT_UNIT)
                .input(dust, Rubidium, 8)
                .output(BOSE_EINSTEIN_CONDENSATE)
                .EUt(VA[UV].toLong())
                .duration(1 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Bi + Sb + 2Te + S -> BiSbTe2S
            MOLECULAR_BEAM_RECIPES.recipeBuilder()
                .notConsumable(plate, CadmiumSulfide)
                .input(dust, Bismuth)
                .input(dust, Antimony)
                .input(dust, Tellurium, 2)
                .input(dust, Sulfur)
                .output(dust, BismuthChalcogenide, 5)
                .EUt(VA[UV].toLong())
                .duration(4 * SECOND)
                .temperature(4876)
                .buildAndRegister()

            // Pu244 + 3H -> PuH3
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Plutonium244)
                .fluidInputs(Hydrogen.getFluid(3000))
                .output(dust, PlutoniumTrihydride, 4)
                .EUt(VA[IV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // Pu + PH3 -> PuP + 6H
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, PlutoniumTrihydride, 4)
                .fluidInputs(Phosphine.getFluid(1000))
                .output(dust, PlutoniumPhosphide, 2)
                .fluidOutputs(Hydrogen.getFluid(6000))
                .EUt(VA[LuV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .temperature(1442)
                .buildAndRegister()

            // 2NaClO4 + Ag2O -> 2AgClO4 + Na2O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, SodiumPerchlorate, 12)
                .input(dust, SilverOxide, 3)
                .output(dust, SilverPerchlorate, 12)
                .output(dust, SodiumOxide, 3)
                .EUt(VA[MV].toLong())
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
                .EUt(VA[IV].toLong())
                .duration(4 * SECOND + 5 * TICK)
                .buildAndRegister()

            // HBr + NH3 + 4C2H4 -> N(CH2CH3)4Br
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Ethylene.getFluid(4000))
                .fluidOutputs(TetraethylammoniumBromide.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(2 * SECOND + 15 * TICK)
                .buildAndRegister()

            // Na + C6H5F -> C6H5Na + NaF
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium)
                .fluidInputs(Fluorobenzene.getFluid(1000))
                .output(dust, SodiumFluoride, 2)
                .fluidOutputs(Phenylsodium.getFluid(1000))
                .EUt(VA[HV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // X-Ray Waveguide
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(wireFine, FullerenePolymerMatrix)
                .fluidInputs(PhotopolymerSolution.getFluid(100))
                .output(X_RAY_WAVEGUIDE)
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SPINTRONIC_PROCESSOR_UHV.stackForm)
                        .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SPINTRONIC_PROCESSOR_UHV.stackForm)
                        .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(5 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SPINTRONIC_PROCESSOR_UHV.stackForm)
                        .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(40 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SPINTRONIC_ASSEMBLY_UEV.stackForm)
                        .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SPINTRONIC_ASSEMBLY_UEV.stackForm)
                        .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SPINTRONIC_ASSEMBLY_UEV.stackForm)
                        .EUt(VA[UEV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .stationResearch { r -> r
                    .researchStack(SPINTRONIC_COMPUTER_UIV.stackForm)
                    .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .stationResearch { r -> r
                    .researchStack(SPINTRONIC_COMPUTER_UIV.stackForm)
                    .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .stationResearch { r -> r
                    .researchStack(SPINTRONIC_COMPUTER_UIV.stackForm)
                    .EUt(VA[UIV].toLong())
                    .CWUt(96)
                }
                .buildAndRegister()

        }

    }

}