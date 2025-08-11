package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.NitrousOxide
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UIV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.SENSOR_UIV
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Borazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromaticGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HarmonicPhononMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumHexaboride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutroniumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpatiallyEnlargedFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TachyonRichTemporalFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_HIGH_DENSITY_PROTONIC_MATTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_KN_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_RN_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_INFORMATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EIGENFOLDED_SPACETIME_MANIFOLD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAVITON_TRANSDUCER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETRON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MANIFOLD_OSCILLATORY_POWER_CELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICROWORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANOSILICON_CATHODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NUCLEAR_CLOCK
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_SPINORIAL_MEMORY_SYSTEM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RECURSIVELY_FOLDED_NEGATIVE_SPACE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RELATIVISTIC_HEAT_CAPACITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RYDBERG_SPINOR_ARRAY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPACETIME_LIGHT_CONE_STABILIZATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STABILIZED_WORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SPACETIME_CONDENSER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOPOLOGICAL_INSULATOR_TUBE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOPOLOGICAL_MANIPULATOR_UNIT

internal object SupracausalCircuits
{

    // @formatter:off

    fun init()
    {
        circuitBoardRecipes()
        smdRecipes()
        circuitComponentsRecipes()
        circuitRecipes()
    }

    private fun circuitBoardRecipes()
    {
        // 4B3H6N3 + La2O3 -> 2LaB6 + 8NH3 + 3NO
        CVD_RECIPES.recipeBuilder()
            .input(dust, LanthanumOxide, 5)
            .fluidInputs(Borazine.getFluid(4000))
            .output(gem, LanthanumHexaboride, 14)
            .fluidOutputs(Ammonia.getFluid(8000))
            .fluidOutputs(NitrousOxide.getFluid(3000))
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .temperature(2073)
            .buildAndRegister()

        // Relativistic Heat Capacity
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(pipeTinyFluid, HarmonicPhononMatter)
            .input(dust, LanthanumHexaboride, 64)
            .input(foil, NeutroniumDopedCarbonNanotube, 32)
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 8000))
            .output(RELATIVISTIC_HEAT_CAPACITY, 8)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Supracausal Spacetime Condenser
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(COSMIC_INFORMATION_MODULE)
            .input(plate, Periodicium, 4)
            .input(plate, SpaceTime, 8)
            .input(STABILIZED_WORMHOLE_GENERATOR)
            .input(RELATIVISTIC_HEAT_CAPACITY, 2)
            .input(foil, Eternity, 24)
            .input(wireFine, Creon, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(TranscendentMetal.getFluid(L * 16))
            .fluidInputs(UUMatter.getFluid(8000))
            .output(SUPRACAUSAL_SPACETIME_CONDENSER)
            .EUt(VA[UEV])
            .duration(2 * SECOND + 10 * TICK)
            .stationResearch { r ->
                r.researchStack(STABILIZED_WORMHOLE_GENERATOR.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // Topological Manipulator Unit
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(plate, QuantumchromodynamicallyConfinedMatter, 2)
            .input(CONTAINED_KN_SINGULARITY)
            .input(EMITTER_UIV)
            .input(screw, SpaceTime, 8)
            .fluidInputs(TachyonRichTemporalFluid.getFluid(1000))
            .output(TOPOLOGICAL_MANIPULATOR_UNIT, 2)
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Graviton Transducer
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(MICROWORMHOLE_GENERATOR)
            .input(MAGNETRON, 2)
            .input(CONTAINED_RN_SINGULARITY)
            .input(SENSOR_UIV)
            .input(foil, MagMatter, 8)
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(1000))
            .output(GRAVITON_TRANSDUCER, 2)
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Quantum Spinorial Memory System
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(RYDBERG_SPINOR_ARRAY)
            .input(gear, WhiteDwarfMatter, 2)
            .input(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER)
            .input(ELECTRIC_PUMP_UIV)
            .input(wireFine, Eternity, 8)
            .fluidInputs(PrimordialMatter.getFluid(500))
            .output(QUANTUM_SPINORIAL_MEMORY_SYSTEM, 2)
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Spacetime Light Cone Stabilization Module
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
            .input(SUPRACAUSAL_SPACETIME_CONDENSER)
            .input(plate, CosmicFabric, 4)
            .input(NUCLEAR_CLOCK)
            .input(TOPOLOGICAL_MANIPULATOR_UNIT)
            .input(GRAVITON_TRANSDUCER)
            .input(EIGENFOLDED_SPACETIME_MANIFOLD)
            .input(QUANTUM_SPINORIAL_MEMORY_SYSTEM)
            .input(RECURSIVELY_FOLDED_NEGATIVE_SPACE, 2)
            .input(MANIFOLD_OSCILLATORY_POWER_CELL, 4)
            .input(wireGtSingle, SpaceTime, 16)
            .input(screw, BlackDwarfMatter, 8)
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
            .fluidInputs(Infinity.getFluid(L * 40))
            .fluidInputs(HarmonicPhononMatter.getFluid(L * 20))
            .fluidInputs(Eternity.getFluid(L * 4))
            .output(SPACETIME_LIGHT_CONE_STABILIZATION_MODULE, 2)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .tier(5)
            .buildAndRegister()
    }

    private fun smdRecipes()
    {
        // Supracausal SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(foil, QuantumchromodynamicallyConfinedMatter, 4)
            .input(foil, Mellion, 4)
            .input(wireFine, TranscendentMetal, 16)
            .input(wireFine, Hypogen, 16)
            .fluidInputs(CosmicFabric.getFluid(L))
            .output(SUPRACAUSAL_SMD_TRANSISTOR, 64)
            .EUt(VA[UIV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Supracausal SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(dust, Creon, 4)
            .input(wireFine, HarmonicPhononMatter, 16)
            .input(wireFine, HeavyQuarkDegenerateMatter, 16)
            .input(wireFine, ChromaticGlass, 16)
            .fluidInputs(CosmicFabric.getFluid(L * 2))
            .output(SUPRACAUSAL_SMD_RESISTOR, 64)
            .EUt(VA[UIV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Supracausal SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(foil, Abyssalloy, 4)
            .input(NANOSILICON_CATHODE, 4)
            .input(foil, Legendarium, 4)
            .input(foil, SuperheavyAlloyB, 4)
            .fluidInputs(CosmicFabric.getFluid(L / 2))
            .output(SUPRACAUSAL_SMD_CAPACITOR, 64)
            .EUt(VA[UIV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Supracausal SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(dust, BlackDwarfMatter, 4)
            .input(foil, Eternity, 16)
            .input(foil, WhiteDwarfMatter, 16)
            .input(foil, SpaceTime, 16)
            .fluidInputs(CosmicFabric.getFluid(L * 2))
            .output(SUPRACAUSAL_SMD_DIODE, 64)
            .EUt(VA[UIV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Supracausal SMD Inductor
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(9)
            .input(ring, MagMatter, 4)
            .input(wireFine, Universium, 16)
            .input(wireFine, MagnetohydrodynamicallyConstrainedStarMatter, 16)
            .input(wireFine, Periodicium , 16)
            .fluidInputs(CosmicFabric.getFluid(L * 2))
            .output(SUPRACAUSAL_SMD_INDUCTOR, 64)
            .EUt(VA[UIV])
            .duration(8 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun circuitComponentsRecipes()
    {

    }

    private fun circuitRecipes()
    {

    }

    // @formatter:on

}