package gregtechlite.gtlitecore.common.metatileentity

import gregtech.api.GTValues.*
import gregtech.api.block.machines.MachineItemBlock
import gregtech.api.capability.impl.PropertyFluidFilter
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.material.Materials.*
import gregtech.api.util.GTUtility.*
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine
import gregtech.common.metatileentities.storage.MetaTileEntityBuffer
import gregtech.common.metatileentities.storage.MetaTileEntityCrate
import gregtech.common.metatileentities.storage.MetaTileEntityDrum
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicator
import gregtechlite.gtlitecore.api.gui.indicator.SteamProgressBarIndicators
import gregtechlite.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity
import gregtechlite.gtlitecore.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity
import gregtechlite.gtlitecore.api.metatileentity.SimpleSteamMachineMetaTileEntity
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.TurbineCasing
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockAntimatterForge
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockBedrockDrillingRig
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockCVDUnit
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockCatalyticReformer
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockComponentAssemblyLine
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockCosmicRayDetector
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockCrystallizationCrucible
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockEnergyInfuser
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockFusionReactor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockLaserInducedCVDUnit
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockMiningDroneAirport
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockNanoForge
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockNanoscaleFabricator
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockPCBFactory
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockPlasmaEnhancedCVDUnit
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockQuantumForceTransformer
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockSonicator
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockSpaceElevator
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockStellarForge
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockAdvancedFusionReactor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockAlloyBlastSmelter
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockChemicalPlant
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockCircuitAssemblyLine
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockCryogenicFreezer
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockElectricImplosionCompressor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockIndustrialCokeOven
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockArcFurnace
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockAssembler
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockAutoclave
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockBender
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockBioReactor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockBrewery
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockBurnerReactor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockCentrifuge
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockCryogenicReactor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockCutter
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockDistillery
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockElectrolyzer
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockElectromagnet
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockExtractor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockExtruder
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockFluidSolidifier
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockFoodProcessor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockForgeHammer
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockGasCollector
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockLaserEngraver
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockMacerator
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockMassFabricator
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockMixer
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockOreWasher
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockPacker
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockReplicator
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockRockBreaker
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockSifter
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockWiremill
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockVolcanus
import gregtechlite.gtlitecore.common.metatileentity.multiblock.generator.MultiblockAntimatterGenerator
import gregtechlite.gtlitecore.common.metatileentity.multiblock.generator.MultiblockNaquadahReactor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.generator.MultiblockRocketEngine
import gregtechlite.gtlitecore.common.metatileentity.multiblock.generator.MultiblockNuclearReactor
import gregtechlite.gtlitecore.common.metatileentity.multiblock.generator.MultiblockSteamEngine
import gregtechlite.gtlitecore.common.metatileentity.multiblock.module.MultiblockSpaceAssembler
import gregtechlite.gtlitecore.common.metatileentity.multiblock.module.MultiblockSpacePump
import gregtechlite.gtlitecore.common.metatileentity.multiblock.primitive.MultiblockCoagulationTank
import gregtechlite.gtlitecore.common.metatileentity.multiblock.primitive.MultiblockAdvancedPrimitiveBlastFurnace
import gregtechlite.gtlitecore.common.metatileentity.multiblock.steam.SteamMultiblockAlloySmelter
import gregtechlite.gtlitecore.common.metatileentity.multiblock.steam.SteamMultiblockCompressor
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineAdvancedEnergyHatch
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineAdvancedLaserHatch
import gregtechlite.gtlitecore.common.metatileentity.part.PartMachineAdvancedMultiFluidHatch
import gregtechlite.gtlitecore.common.metatileentity.single.MachineMobExtractor
import gregtechlite.gtlitecore.common.metatileentity.single.MachineSapCollector
import gregtechlite.gtlitecore.common.metatileentity.single.SteamMachineSapCollector
import gregtechlite.gtlitecore.common.metatileentity.storage.MetaTileEntityBridge
import gregtechlite.gtlitecore.common.metatileentity.storage.MetaTileEntityExtender
import gregtechlite.gtlitecore.common.metatileentity.storage.MetaTileEntityPlasticCan
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.items.CapabilityItemHandler

object GTLiteMetaTileEntities
{

    // @formatter:off

    @JvmField
    var collectorTankSizeFunction = java.util.function.Function<Int, Int> { tier ->
        when
        {
            tier <= LV -> 16000
            tier == MV -> 24000
            tier == HV -> 32000
            else -> 64000
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- */

    // Single Machines
    lateinit var POLISHER: Array<SimpleMachineMetaTileEntity>
    lateinit var SLICER: Array<SimpleMachineMetaTileEntity>
    lateinit var TOOL_CASTER: Array<SimpleMachineMetaTileEntity>
    lateinit var LOOM: Array<SimpleMachineMetaTileEntity>
    lateinit var LAMINATOR: Array<SimpleMachineMetaTileEntity>
    lateinit var CHEMICAL_DEHYDRATOR: Array<SimpleMachineMetaTileEntity>
    lateinit var STEAM_VULCANIZING_PRESS: Array<SimpleSteamMachineMetaTileEntity>
    lateinit var VULCANIZING_PRESS: Array<SimpleMachineMetaTileEntity>
    lateinit var STEAM_VACUUM_CHAMBER: Array<SimpleSteamMachineMetaTileEntity>
    lateinit var VACUUM_CHAMBER: Array<SimpleMachineMetaTileEntity>
    lateinit var STEAM_SAP_COLLECTOR: Array<PseudoMultiSteamMachineMetaTileEntity>
    lateinit var SAP_COLLECTOR: Array<PseudoMultiMachineMetaTileEntity>
    lateinit var GREENHOUSE: Array<SimpleMachineMetaTileEntity>
    lateinit var BIO_REACTOR: Array<SimpleMachineMetaTileEntity>
    lateinit var STEAM_ROASTER: Array<SimpleSteamMachineMetaTileEntity>
    lateinit var ROASTER: Array<SimpleMachineMetaTileEntity>
    lateinit var BURNER_REACTOR: Array<SimpleMachineMetaTileEntity>
    lateinit var BATH_CONDENSER: Array<SimpleMachineMetaTileEntity>
    lateinit var CRYOGENIC_REACTOR: Array<SimpleMachineMetaTileEntity>
    lateinit var MASS_FABRICATOR: Array<SimpleMachineMetaTileEntity>
    lateinit var REPLICATOR: Array<SimpleMachineMetaTileEntity>
    lateinit var FOOD_PROCESSOR: Array<SimpleMachineMetaTileEntity>
    lateinit var MULTICOOKER: Array<SimpleMachineMetaTileEntity>
    lateinit var MOB_EXTRACTOR: Array<SimpleMachineMetaTileEntity>
    lateinit var BIO_SIMULATOR: Array<SimpleMachineMetaTileEntity>
    lateinit var ROCKET_ENGINE: Array<SimpleGeneratorMetaTileEntity>
    lateinit var NAQUADAH_REACTOR: Array<SimpleGeneratorMetaTileEntity>

    // Several single MTEs.
    lateinit var IRON_DRUM: MetaTileEntityDrum
    lateinit var COPPER_DRUM: MetaTileEntityDrum
    lateinit var LEAD_DRUM: MetaTileEntityDrum
    lateinit var CHROME_DRUM: MetaTileEntityDrum
    lateinit var TUNGSTEN_DRUM: MetaTileEntityDrum
    lateinit var IRIDIUM_DRUM: MetaTileEntityDrum

    lateinit var PE_CAN: MetaTileEntityPlasticCan
    lateinit var PTFE_CAN: MetaTileEntityPlasticCan
    lateinit var PBI_CAN: MetaTileEntityPlasticCan
    lateinit var KEVLAR_CAN: MetaTileEntityPlasticCan

    lateinit var IRON_CRATE: MetaTileEntityCrate
    lateinit var COPPER_CRATE: MetaTileEntityCrate
    lateinit var SILVER_CRATE: MetaTileEntityCrate
    lateinit var GOLD_CRATE: MetaTileEntityCrate
    lateinit var DIAMOND_CRATE: MetaTileEntityCrate

    lateinit var INVENTORY_BRIDGE: MetaTileEntityBridge
    lateinit var TANK_BRIDGE: MetaTileEntityBridge
    lateinit var INVENTORY_TANK_BRIDGE: MetaTileEntityBridge
    lateinit var UNIVERSAL_BRIDGE: MetaTileEntityBridge

    lateinit var INVENTORY_EXTENDER: MetaTileEntityExtender
    lateinit var TANK_EXTENDER: MetaTileEntityExtender
    lateinit var INVENTORY_TANK_EXTENDER: MetaTileEntityExtender
    lateinit var UNIVERSAL_EXTENDER: MetaTileEntityExtender

    lateinit var BUFFER: Array<MetaTileEntityBuffer>

    // Multiblock Parts.
    lateinit var ENERGY_HATCH_4A: Array<PartMachineAdvancedEnergyHatch>
    lateinit var DYNAMO_HATCH_4A: Array<PartMachineAdvancedEnergyHatch>
    lateinit var ENERGY_HATCH_16A: Array<PartMachineAdvancedEnergyHatch>
    lateinit var DYNAMO_HATCH_16A: Array<PartMachineAdvancedEnergyHatch>
    lateinit var SUBSTATION_ENERGY_HATCH_64A: Array<PartMachineAdvancedEnergyHatch>
    lateinit var SUBSTATION_DYNAMO_HATCH_64A: Array<PartMachineAdvancedEnergyHatch>

    lateinit var LASER_INPUT_HATCH_16384: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_OUTPUT_HATCH_16384: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_INPUT_HATCH_65536: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_OUTPUT_HATCH_65536: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_INPUT_HATCH_262144: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_OUTPUT_HATCH_262144: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_INPUT_HATCH_1048576: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_OUTPUT_HATCH_1048576: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_INPUT_HATCH_4194304: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_OUTPUT_HATCH_4194304: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_INPUT_HATCH_16777216: Array<PartMachineAdvancedLaserHatch>
    lateinit var LASER_OUTPUT_HATCH_16777216: Array<PartMachineAdvancedLaserHatch>

    lateinit var QUADRUPLE_FLUID_IMPORT_HATCH: Array<PartMachineAdvancedMultiFluidHatch>
    lateinit var QUADRUPLE_FLUID_EXPORT_HATCH: Array<PartMachineAdvancedMultiFluidHatch>
    lateinit var NONUPLE_FLUID_IMPORT_HATCH: Array<PartMachineAdvancedMultiFluidHatch>
    lateinit var NONUPLE_FLUID_EXPORT_HATCH: Array<PartMachineAdvancedMultiFluidHatch>

    // Multiblock Machines.
    lateinit var COAGULATION_TANK: MultiblockCoagulationTank
    lateinit var LARGE_STEAM_COMPRESSOR: SteamMultiblockCompressor
    lateinit var LARGE_STEAM_ALLOY_SMELTER: SteamMultiblockAlloySmelter
    lateinit var STEAM_ENGINE: MultiblockSteamEngine
    lateinit var INDUSTRIAL_PRIMITIVE_BLAST_FURNACE: MultiblockAdvancedPrimitiveBlastFurnace
    lateinit var NUCLEAR_REACTOR: MultiblockNuclearReactor
    lateinit var HOT_COOLANT_TURBINE: MetaTileEntityLargeTurbine
    lateinit var SUPERCRITICAL_FLUID_TURBINE: MetaTileEntityLargeTurbine
    lateinit var MINING_DRONE_AIRPORT: MultiblockMiningDroneAirport

    lateinit var CATALYTIC_REFORMER: MultiblockCatalyticReformer
    lateinit var CVD_UNIT: MultiblockCVDUnit
    lateinit var CRYSTALLIZATION_CRUCIBLE: MultiblockCrystallizationCrucible
    lateinit var NANOSCALE_FABRICATOR: MultiblockNanoscaleFabricator
    lateinit var SONICATOR: MultiblockSonicator
    lateinit var LASER_INDUCED_CVD_UNIT: MultiblockLaserInducedCVDUnit
    lateinit var BEDROCK_DRILLING_RIG: MultiblockBedrockDrillingRig
    lateinit var FUSION_REACTOR_MK4: MultiblockFusionReactor
    lateinit var FUSION_REACTOR_MK5: MultiblockFusionReactor
    lateinit var ADVANCED_FUSION_REACTOR: MultiblockAdvancedFusionReactor
    lateinit var COMPONENT_ASSEMBLY_LINE: MultiblockComponentAssemblyLine
    lateinit var COSMIC_RAY_DETECTOR: MultiblockCosmicRayDetector
    lateinit var STELLAR_FORGE: MultiblockStellarForge
    lateinit var PLASMA_ENHANCED_CVD_UNIT: MultiblockPlasmaEnhancedCVDUnit
    lateinit var PCB_FACTORY: MultiblockPCBFactory
    lateinit var NANO_FORGE: MultiblockNanoForge
    lateinit var QUANTUM_FORCE_TRANSFORMER: MultiblockQuantumForceTransformer
    lateinit var ANTIMATTER_FORGE: MultiblockAntimatterForge
    lateinit var ANTIMATTER_GENERATOR: MultiblockAntimatterGenerator
    lateinit var SPACE_ELEVATOR: MultiblockSpaceElevator
    lateinit var SPACE_ASSEMBLER_MK1: MultiblockSpaceAssembler
    lateinit var SPACE_ASSEMBLER_MK2: MultiblockSpaceAssembler
    lateinit var SPACE_ASSEMBLER_MK3: MultiblockSpaceAssembler

    lateinit var SPACE_PUMP_MK1: MultiblockSpacePump
    lateinit var SPACE_PUMP_MK2: MultiblockSpacePump
    lateinit var SPACE_PUMP_MK3: MultiblockSpacePump

    lateinit var ENERGY_INFUSER: MultiblockEnergyInfuser

    lateinit var LARGE_FORGE_HAMMER: MultiblockForgeHammer
    lateinit var LARGE_BENDER: MultiblockBender
    lateinit var LARGE_CUTTER: MultiblockCutter
    lateinit var LARGE_EXTRUDER: MultiblockExtruder
    lateinit var LARGE_WIREMILL: MultiblockWiremill
    lateinit var LARGE_MIXER: MultiblockMixer
    lateinit var LARGE_EXTRACTOR: MultiblockExtractor
    lateinit var LARGE_ASSEMBLER: MultiblockAssembler
    lateinit var LARGE_LASER_ENGRAVER: MultiblockLaserEngraver
    lateinit var LARGE_FLUID_SOLIDIFIER: MultiblockFluidSolidifier
    lateinit var LARGE_BREWERY: MultiblockBrewery
    lateinit var LARGE_AUTOCLAVE: MultiblockAutoclave
    lateinit var LARGE_ARC_FURNACE: MultiblockArcFurnace
    lateinit var LARGE_MACERATOR: MultiblockMacerator
    lateinit var LARGE_CENTRIFUGE: MultiblockCentrifuge
    lateinit var LARGE_SIFTER: MultiblockSifter
    lateinit var LARGE_ELECTROLYZER: MultiblockElectrolyzer
    lateinit var LARGE_ORE_WASHER: MultiblockOreWasher
    lateinit var LARGE_ELECTROMAGNET: MultiblockElectromagnet
    lateinit var LARGE_DISTILLERY: MultiblockDistillery
    lateinit var LARGE_BIO_REACTOR: MultiblockBioReactor
    lateinit var LARGE_PACKER: MultiblockPacker
    lateinit var LARGE_GAS_COLLECTOR: MultiblockGasCollector
    lateinit var LARGE_ROCK_BREAKER: MultiblockRockBreaker
    lateinit var LARGE_BURNER_REACTOR: MultiblockBurnerReactor
    lateinit var LARGE_CRYOGENIC_REACTOR: MultiblockCryogenicReactor
    lateinit var ELECTRIC_IMPLOSION_COMPRESSOR: MultiblockElectricImplosionCompressor
    lateinit var ALLOY_BLAST_SMELTER: MultiblockAlloyBlastSmelter
    lateinit var VOLCANUS: MultiblockVolcanus
    lateinit var CRYOGENIC_FREEZER: MultiblockCryogenicFreezer
    lateinit var CHEMICAL_PLANT: MultiblockChemicalPlant
    lateinit var INDUSTRIAL_COKE_OVEN: MultiblockIndustrialCokeOven
    lateinit var LARGE_MASS_FABRICATOR: MultiblockMassFabricator
    lateinit var LARGE_REPLICATOR: MultiblockReplicator
    lateinit var CIRCUIT_ASSEMBLY_LINE: MultiblockCircuitAssemblyLine
    lateinit var LARGE_FOOD_PROCESSOR: MultiblockFoodProcessor
    lateinit var LARGE_ROCKET_ENGINE: MultiblockRocketEngine
    lateinit var LARGE_NAQUADAH_REACTOR: MultiblockNaquadahReactor

    fun preInit()
    {
        MachineItemBlock.addCreativeTab(GTLiteCreativeTabs.TAB_MACHINE)
    }

    @JvmStatic
    fun init()
    {
        // 1-2000: Simple Machines

        // 1-15: Polisher (LV-OpV)
        POLISHER = register(3, V.size - 1, "polisher",
                            GTLiteRecipeMaps.POLISHER_RECIPES,
                            GTLiteTextures.POLISHER_OVERLAY, true,
                            hvCappedTankSizeFunction)

        // 16-30: Slicer (LV-OpV)
        SLICER = register(18, V.size - 1, "slicer",
                          GTLiteRecipeMaps.SLICER_RECIPES,
                          GTLiteTextures.SLICER_OVERLAY, true,
                          hvCappedTankSizeFunction)

        // 31-45: Tool Caster (LV-IV)
        TOOL_CASTER = register(33, 0..4) {
            SimpleMachineMetaTileEntity(GTLiteMod.id("tool_caster.${VN[it + 1].lowercase()}"),
                                        GTLiteRecipeMaps.TOOL_CASTER_RECIPES,
                                        GTLiteTextures.TOOL_CASTER_OVERLAY, it + 1,
                                        true, defaultTankSizeFunction)
        }

        // 46-60: Loom (LV-OpV)
        LOOM = register(48, V.size - 1, "loom",
                        GTLiteRecipeMaps.LOOM_RECIPES,
                        GTLiteTextures.LOOM_OVERLAY, true,
                        genericGeneratorTankSizeFunction)

        // 61-75: Laminator (LV-OpV)
        LAMINATOR = register(63, V.size - 1, "laminator",
                             GTLiteRecipeMaps.LAMINATOR_RECIPES,
                             GTLiteTextures.LAMINATOR_OVERLAY, true,
                             largeTankSizeFunction)

        // 76-90: Chemical Dehydrator (LV-OpV)
        CHEMICAL_DEHYDRATOR = register(78, V.size - 1, "chemical_dehydrator",
                                       GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES,
                                       GTLiteTextures.CHEMICAL_DEHYDRATOR_OVERLAY, true,
                                       defaultTankSizeFunction)

        // 91-105: Vulcanizing Press (ULV-OpV)
        STEAM_VULCANIZING_PRESS = register(91, "vulcanizing_press",
                                           GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                                           SteamProgressBarIndicators.ARROW_MULTIPLE,
                                           GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true)

        VULCANIZING_PRESS = register(93, V.size - 1, "vulcanizing_press",
                                     GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                                     GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true,
                                     genericGeneratorTankSizeFunction)

        // 106-120: Vacuum Chamber (ULV-OpV)
        STEAM_VACUUM_CHAMBER = register(106, "vacuum_chamber",
                                        GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                                        SteamProgressBarIndicators.COMPRESS,
                                        Textures.GAS_COLLECTOR_OVERLAY, false
        )

        VACUUM_CHAMBER = register(108, V.size - 1, "vacuum_chamber",
                                  GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                                  Textures.GAS_COLLECTOR_OVERLAY, true,
                                  genericGeneratorTankSizeFunction)

        // 121-135: Sap Collector (ULV-IV)
        STEAM_SAP_COLLECTOR = arrayOf(
            register(121, SteamMachineSapCollector(GTLiteMod.id("sap_collector.bronze"), false)),
            register(122, SteamMachineSapCollector(GTLiteMod.id("sap_collector.steel"), true)))

        SAP_COLLECTOR = register(123, 0..4) {
            MachineSapCollector(GTLiteMod.id("sap_collector.${VN[it + 1].lowercase()}"), it + 1)
        }

        // 136-150: Greenhouse (LV-OpV)
        GREENHOUSE = register(138, V.size - 1, "greenhouse",
                              GTLiteRecipeMaps.GREENHOUSE_RECIPES,
                              Textures.FERMENTER_OVERLAY, true,
                              defaultTankSizeFunction)

        // 151-165: Bio Reactor (LV-OpV)
        BIO_REACTOR = register(153, V.size - 1, "bio_reactor",
                               GTLiteRecipeMaps.BIO_REACTOR_RECIPES,
                               GTLiteTextures.BIO_REACTOR_OVERLAY, true,
                               steamGeneratorTankSizeFunction)

        // 166-180: Roaster (ULV-OpV)
        STEAM_ROASTER = register(166, "roaster",
                                 GTLiteRecipeMaps.ROASTER_RECIPES,
                                 SteamProgressBarIndicators.ARROW,
                                 GTLiteTextures.ROASTER_OVERLAY, true)

        ROASTER = register(168, V.size - 1, "roaster",
                           GTLiteRecipeMaps.ROASTER_RECIPES,
                           GTLiteTextures.ROASTER_OVERLAY, true,
                           defaultTankSizeFunction)

        // 181-195: Burner Reactor (LV-OpV)
        BURNER_REACTOR = register(183, V.size - 1, "burner_reactor",
                                  GTLiteRecipeMaps.BURNER_REACTOR_RECIPES,
                                  GTLiteTextures.BURNER_REACTOR_OVERLAY, true,
                                  defaultTankSizeFunction)

        // 196-210: Bath Condenser (LV-OpV)
        BATH_CONDENSER = register(198, V.size - 1, "bath_condenser",
                                  GTLiteRecipeMaps.BATH_CONDENSER_RECIPES,
                                  GTLiteTextures.BATH_CONDENSER_OVERLAY, true,
                                  defaultTankSizeFunction)

        // 211-225: Cryogenic Reactor (LV-OpV)
        CRYOGENIC_REACTOR = register(213, V.size - 1, "cryogenic_reactor",
                                     GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES,
                                     GTLiteTextures.CRYOGENIC_REACTOR_OVERLAY, true,
                                     defaultTankSizeFunction)

        // 226-240: Mass Fabricator (LV-OpV)
        MASS_FABRICATOR = register(228, V.size - 1, "mass_fabricator",
                                   RecipeMaps.MASS_FABRICATOR_RECIPES,
                                   Textures.MASS_FABRICATOR_OVERLAY, true,
                                   largeTankSizeFunction)

        // 241-255: Replicator (LV-OpV)
        REPLICATOR = register(243, V.size - 1, "replicator",
                              RecipeMaps.REPLICATOR_RECIPES,
                              Textures.REPLICATOR_OVERLAY, true,
                              largeTankSizeFunction)

        // 256-270: Food Processor (LV-OpV)
        FOOD_PROCESSOR = register(258, V.size - 1, "food_processor",
                                  GTLiteRecipeMaps.FOOD_PROCESSOR_RECIPES,
                                  GTLiteTextures.FOOD_PROCESSOR_OVERLAY, true,
                                  genericGeneratorTankSizeFunction)

        // 271-285: Multicooker (LV-OpV)
        MULTICOOKER = register(273, V.size - 1, "multicooker",
                               GTLiteRecipeMaps.MULTICOOKER_RECIPES,
                               GTLiteTextures.MULTICOOKER_OVERLAY, true,
                               genericGeneratorTankSizeFunction)

        // 286-300: Mob Extractor (LV-OpV)
        MOB_EXTRACTOR = register(288, 0..12) {
            MachineMobExtractor(GTLiteMod.id("mob_extractor.${VN[it + 1].lowercase()}"),
                                GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES,
                                GTLiteTextures.MOB_EXTRACTOR_OVERLAY, it + 1, false,
                                largeTankSizeFunction)
        }

        // 301-315: Bio Simulator (LV-IV)
        BIO_SIMULATOR = register(303, 6, "bio_simulator",
                                 GTLiteRecipeMaps.BIO_SIMULATOR_RECIPES,
                                 GTLiteTextures.BIO_SIMULATOR_OVERLAY, true,
                                 largeTankSizeFunction)

        // 316-330: Rocket Engine (HV-EV)
        ROCKET_ENGINE = register(320, 0..2) {
            SimpleGeneratorMetaTileEntity(GTLiteMod.id("rocket_engine.${VN[it + HV].lowercase()}"),
                                          GTLiteRecipeMaps.ROCKET_ENGINE_FUELS,
                                          GTLiteTextures.ROCKET_ENGINE_OVERLAY, it + HV,
                                          genericGeneratorTankSizeFunction)
        }

        // 331-345: Naquadah Reactor (IV-UV)
        NAQUADAH_REACTOR = register(337, 0..3) {
            SimpleGeneratorMetaTileEntity(GTLiteMod.id("naquadah_reactor.${VN[it + IV].lowercase()}"),
                                          GTLiteRecipeMaps.NAQUADAH_REACTOR_FUELS,
                                          GTLiteTextures.NAQUADAH_REACTOR_OVERLAY, it + IV,
                                          genericGeneratorTankSizeFunction)
        }

        // -------------------------------------------------------------------------------------------------------------
        // 2001-4000: Miscellaneous Single MTEs

        // 2001-2050: Drums and Crates
        IRON_DRUM = register(2001, MetaTileEntityDrum(GTLiteMod.id("drum.iron"),
            PropertyFluidFilter(1811, true, true,
            false, false), false, Iron.materialRGB, 12_000))

        COPPER_DRUM = register(2002, MetaTileEntityDrum(GTLiteMod.id("drum.copper"),
            Copper, 16_000))

        LEAD_DRUM = register(2003, MetaTileEntityDrum(GTLiteMod.id("drum.lead"),
            Lead, 24_000))

        CHROME_DRUM = register(2004, MetaTileEntityDrum(GTLiteMod.id("drum.chrome"),
            Chrome, 96_000))

        TUNGSTEN_DRUM = register(2005, MetaTileEntityDrum(GTLiteMod.id("drum.tungsten"),
            Tungsten, 768_000))

        IRIDIUM_DRUM = register(2006, MetaTileEntityDrum(GTLiteMod.id("drum.iridium"),
            Iridium, 1_536_000))

        // ...

        PE_CAN = register(2016, MetaTileEntityPlasticCan(GTLiteMod.id("plastic_can.polyethylene"),
            Polyethylene, 64_000)
        )

        PTFE_CAN = register(2017, MetaTileEntityPlasticCan(GTLiteMod.id("plastic_can.polytetrafluoroethylene"),
            Polytetrafluoroethylene, 128_000))

        PBI_CAN = register(2018, MetaTileEntityPlasticCan(GTLiteMod.id("plastic_can.polybenzimidazole"),
            Polybenzimidazole, 256_000))

        KEVLAR_CAN = register(2019, MetaTileEntityPlasticCan(GTLiteMod.id("plastic_can.kevlar"),
            Kevlar, 512_000))

        // ...

        IRON_CRATE = register(2026, MetaTileEntityCrate(GTLiteMod.id("crate.iron"),
            Iron, 45, 9))

        COPPER_CRATE = register(2027, MetaTileEntityCrate(GTLiteMod.id("crate.copper"),
            Copper, 36, 9))

        SILVER_CRATE = register(2028, MetaTileEntityCrate(GTLiteMod.id("crate.silver"),
            Silver, 63, 9))

        GOLD_CRATE = register(2029, MetaTileEntityCrate(GTLiteMod.id("crate.gold"),
            Gold, 81, 9))

        DIAMOND_CRATE = register(2030, MetaTileEntityCrate(GTLiteMod.id("crate.diamond"),
            Diamond, 100, 10))

        // ...

        // 2051-2060: Import/Export Proxies
        INVENTORY_BRIDGE = register(2051, MetaTileEntityBridge(GTLiteMod.id("bridge.inventory"),
            { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY },
            GTLiteTextures.INVENTORY_BRIDGE, Steel))

        TANK_BRIDGE = register(2052, MetaTileEntityBridge(GTLiteMod.id("bridge.tank"),
            { c -> c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
            GTLiteTextures.TANK_BRIDGE, Steel))

        INVENTORY_TANK_BRIDGE = register(2053, MetaTileEntityBridge(GTLiteMod.id("bridge.inventory_tank"),
            { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
            GTLiteTextures.INVENTORY_TANK_BRIDGE, Steel))

        UNIVERSAL_BRIDGE = register(2054, MetaTileEntityBridge(GTLiteMod.id("bridge.universal"),
            { true },
            GTLiteTextures.UNIVERSAL_BRIDGE, Aluminium))

        INVENTORY_EXTENDER = register(2055, MetaTileEntityExtender(GTLiteMod.id("extender.inventory"),
            { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY },
            GTLiteTextures.INVENTORY_EXTENDER, Steel))

        TANK_EXTENDER = register(2056, MetaTileEntityExtender(GTLiteMod.id("extender.tank"),
            { c -> c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
            GTLiteTextures.TANK_EXTENDER, Steel))

        INVENTORY_TANK_EXTENDER = register(2057, MetaTileEntityExtender(GTLiteMod.id("extender.inventory_tank"),
            { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
            GTLiteTextures.INVENTORY_TANK_EXTENDER, Steel))

        UNIVERSAL_EXTENDER = register(2058, MetaTileEntityExtender(GTLiteMod.id("extender.universal"),
            { true },
            GTLiteTextures.UNIVERSAL_EXTENDER, Aluminium))

        // 2061-2065: Advanced Buffers (EV-IV)
        BUFFER = register(2061, 0..1) {
            MetaTileEntityBuffer(GTLiteMod.id("buffer.${VN[it + EV].lowercase()}"), it + EV)
        }

        // -------------------------------------------------------------------------------------------------------------
        // 4001-10000: Multiblock Parts

        // 4001-5000: (Wireless/Substation) Energy/Dynamo Hatches and Laser Target/Source Hatches.

        // 4001-4004: ULV-HV 4A Energy Hatches.
        ENERGY_HATCH_4A = register(4001, 0..3) {
            PartMachineAdvancedEnergyHatch(GTLiteMod.id("energy_hatch.input_4a.${VN[it].lowercase()}"),
                                           it, 4, false)
        }

        // 4005-4008: ULV-HV 4A Dynamo Hatches
        DYNAMO_HATCH_4A = register(4005, 0..3) {
            PartMachineAdvancedEnergyHatch(GTLiteMod.id("energy_hatch.output_4a.${VN[it].lowercase()}"),
                                           it, 4, true)
        }

        // 4009-4013: ULV-EV 16A Energy Hatches.
        ENERGY_HATCH_16A = register(4009, 0..4) {
            PartMachineAdvancedEnergyHatch(GTLiteMod.id("energy_hatch.input_16a.${VN[it].lowercase()}"),
                                           it, 16, false)
        }

        // 4014-4018: ULV-EV 16A Dynamo Hatches.
        DYNAMO_HATCH_16A = register(4014, 0..4) {
            PartMachineAdvancedEnergyHatch(GTLiteMod.id("energy_hatch.output_16a.${VN[it].lowercase()}"),
                                           it, 16, true)
        }

        // 4019-4023 : ULV-EV 64A Substation Energy Hatches.
        SUBSTATION_ENERGY_HATCH_64A = register(4019, 0..4) {
            PartMachineAdvancedEnergyHatch(GTLiteMod.id("substation_hatch.input_64a.${VN[it].lowercase()}"),
                                           it, 64, false)
        }

        // 4024-4028: ULV-EV 64A Substation Dynamo Hatches.
        SUBSTATION_DYNAMO_HATCH_64A = register(4024, 0..4) {
            PartMachineAdvancedEnergyHatch(GTLiteMod.id("substation_hatch.output_64a.${VN[it].lowercase()}"),
                                           it, 64, true)
        }

        // 4031-4140: IV-OpV Hi-Amp Laser Target/Source Hatches.
        LASER_INPUT_HATCH_16384 = register(4031, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.target_16384a.${VN[it + IV].lowercase()}"),
                                          it + IV, 16_384, false)
        }

        LASER_OUTPUT_HATCH_16384 = register(4040, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.source_16384a.${VN[it + IV].lowercase()}"),
                                          it + IV, 16_384, true)
        }

        LASER_INPUT_HATCH_65536 = register(4049, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.target_65536a.${VN[it + IV].lowercase()}"),
                                          it + IV, 65_536, false)
        }

        LASER_OUTPUT_HATCH_65536 = register(4058, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.source_65536a.${VN[it + IV].lowercase()}"),
                                          it + IV, 65_536, true)
        }

        LASER_INPUT_HATCH_262144 = register(4067, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.target_262144a.${VN[it + IV].lowercase()}"),
                                          it + IV, 262_144, false)
        }

        LASER_OUTPUT_HATCH_262144 = register(4076, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.source_262144a.${VN[it + IV].lowercase()}"),
                                          it + IV, 262_144, true)
        }

        LASER_INPUT_HATCH_1048576 = register(4085, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.target_1048576a.${VN[it + IV].lowercase()}"),
                                          it + IV, 1_048_576, false)
        }

        LASER_OUTPUT_HATCH_1048576 = register(4094, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.source_1048576a.${VN[it + IV].lowercase()}"),
                                          it + IV, 1_048_576, true)
        }

        LASER_INPUT_HATCH_4194304 = register(4103, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.target_4194304a.${VN[it + IV].lowercase()}"),
                                          it + IV, 4_194_304, false)
        }

        LASER_OUTPUT_HATCH_4194304 = register(4112, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.source_4194304a.${VN[it + IV].lowercase()}"),
                                          it + IV, 4_194_304, true)
        }

        LASER_INPUT_HATCH_16777216 = register(4121, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.target_16777216a.${VN[it + IV].lowercase()}"),
                                          it + IV, 16_777_216, false)
        }

        LASER_OUTPUT_HATCH_16777216 = register(4130, 0..8) {
            PartMachineAdvancedLaserHatch(GTLiteMod.id("laser_hatch.source_16777216a.${VN[it + IV].lowercase()}"),
                                          it + IV, 16_777_216, true)
        }

        // TODO Wireless Energy/Dynamo Hatches

        // 5001-5100: Item Import/Export Buses and Fluid Import/Export Hatches

        // 5001-5004: ULV-HV Quadruple Fluid Import Hatches
        QUADRUPLE_FLUID_IMPORT_HATCH = register(5001, 0..3) {
            PartMachineAdvancedMultiFluidHatch(GTLiteMod.id("fluid_hatch.import_4x.${VN[it].lowercase()}"),
                                               it, 4, false)
        }

        // 5005-5008: ULV-HV Quadruple Fluid Export Hatches
        QUADRUPLE_FLUID_EXPORT_HATCH = register(5005, 0..3) {
            PartMachineAdvancedMultiFluidHatch(GTLiteMod.id("fluid_hatch.export_4x.${VN[it].lowercase()}"),
                                               it, 4, true)
        }

        // 5009-5012: ULV-HV Nonuple Fluid Import Hatches
        NONUPLE_FLUID_IMPORT_HATCH = register(5009, 0..3) {
            PartMachineAdvancedMultiFluidHatch(GTLiteMod.id("fluid_hatch.import_9x.${VN[it].lowercase()}"),
                                               it, 9, false)
        }

        // 5013-5016: ULV-HV Nonuple Fluid Export Hatches
        NONUPLE_FLUID_EXPORT_HATCH = register(5013, 0..3) {
            PartMachineAdvancedMultiFluidHatch(GTLiteMod.id("fluid_hatch.export_9x.${VN[it].lowercase()}"),
                                               it, 9, true)
        }

        // -------------------------------------------------------------------------------------------------------------
        // 10001-20000 Multiblock Machines
        COAGULATION_TANK = register(10001, MultiblockCoagulationTank(GTLiteMod.id("coagulation_tank")))
        LARGE_STEAM_COMPRESSOR = register(10002, SteamMultiblockCompressor(GTLiteMod.id("steam_compressor")))
        LARGE_STEAM_ALLOY_SMELTER = register(10003, SteamMultiblockAlloySmelter(GTLiteMod.id("steam_alloy_smelter")))
        STEAM_ENGINE = register(10004, MultiblockSteamEngine(GTLiteMod.id("steam_engine")))
        INDUSTRIAL_PRIMITIVE_BLAST_FURNACE = register(10005, MultiblockAdvancedPrimitiveBlastFurnace(GTLiteMod.id("industrial_primitive_blast_furnace")))
        MINING_DRONE_AIRPORT = register(10006, MultiblockMiningDroneAirport(GTLiteMod.id("mining_drone_airport")))

        // 10007 TODO Fracturing

        NUCLEAR_REACTOR = register(10008, MultiblockNuclearReactor(GTLiteMod.id("nuclear_reactor")))

        HOT_COOLANT_TURBINE = register(10009, MetaTileEntityLargeTurbine(GTLiteMod.id("large_turbine.hot_coolant"),
            GTLiteRecipeMaps.HOT_COOLANT_TURBINE_FUELS, EV,
            MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_TURBINE_CASING),
            MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX),
            Textures.STABLE_TITANIUM_CASING, true, Textures.LARGE_GAS_TURBINE_OVERLAY))

        SUPERCRITICAL_FLUID_TURBINE = register(10010, MetaTileEntityLargeTurbine(GTLiteMod.id("large_turbine.supercritical_fluid"),
            GTLiteRecipeMaps.SUPERCRITICAL_FLUID_TURBINE_FUELS, LuV,
            TurbineCasing.RHODIUM_PLATED_PALLADIUM_TURBINE.state,
            TurbineCasing.RHODIUM_PLATED_PALLADIUM_GEARBOX.state,
            GTLiteTextures.RHODIUM_PLATED_PALLADIUM_CASING, true, Textures.LARGE_GAS_TURBINE_OVERLAY))

        CATALYTIC_REFORMER = register(10011, MultiblockCatalyticReformer(GTLiteMod.id("catalytic_reformer")))
        CVD_UNIT = register(10012, MultiblockCVDUnit(GTLiteMod.id("cvd_unit")))
        CRYSTALLIZATION_CRUCIBLE = register(10013, MultiblockCrystallizationCrucible(GTLiteMod.id("crystallization_crucible")))
        NANOSCALE_FABRICATOR = register(10014, MultiblockNanoscaleFabricator(GTLiteMod.id("nanoscale_fabricator")))
        SONICATOR = register(10015, MultiblockSonicator(GTLiteMod.id("sonicator")))
        LASER_INDUCED_CVD_UNIT = register(10016, MultiblockLaserInducedCVDUnit(GTLiteMod.id("laser_induced_cvd_unit")))
        BEDROCK_DRILLING_RIG = register(10017, MultiblockBedrockDrillingRig(GTLiteMod.id("bedrock_drilling_rig")))
        FUSION_REACTOR_MK4 = register(10018, MultiblockFusionReactor(GTLiteMod.id("fusion_reactor.uhv"), UHV))
        FUSION_REACTOR_MK5 = register(10019, MultiblockFusionReactor(GTLiteMod.id("fusion_reactor.uev"), UEV))
        ADVANCED_FUSION_REACTOR = register(10020, MultiblockAdvancedFusionReactor(GTLiteMod.id("advanced_fusion_reactor")))
        COMPONENT_ASSEMBLY_LINE = register(10021, MultiblockComponentAssemblyLine(GTLiteMod.id("component_assembly_line")))
        COSMIC_RAY_DETECTOR = register(10022, MultiblockCosmicRayDetector(GTLiteMod.id("cosmic_ray_detector")))
        STELLAR_FORGE = register(10023, MultiblockStellarForge(GTLiteMod.id("stellar_forge")))
        PLASMA_ENHANCED_CVD_UNIT = register(10024, MultiblockPlasmaEnhancedCVDUnit(GTLiteMod.id("plasma_enhanced_cvd_unit")))
        PCB_FACTORY = register(10025, MultiblockPCBFactory(GTLiteMod.id("pcb_factory")))
        NANO_FORGE = register(10026, MultiblockNanoForge(GTLiteMod.id("nano_forge")))
        QUANTUM_FORCE_TRANSFORMER = register(10027, MultiblockQuantumForceTransformer(GTLiteMod.id("quantum_force_transformer")))
        ANTIMATTER_FORGE = register(10028, MultiblockAntimatterForge(GTLiteMod.id("antimatter_forge")))
        ANTIMATTER_GENERATOR = register(10029, MultiblockAntimatterGenerator(GTLiteMod.id("antimatter_generator")))
        SPACE_ELEVATOR = register(10030, MultiblockSpaceElevator(GTLiteMod.id("space_elevator")))
        SPACE_ASSEMBLER_MK1 = register(10031, MultiblockSpaceAssembler(GTLiteMod.id("space_assembler_module.mk1"), UHV, 1, 1))
        SPACE_ASSEMBLER_MK2 = register(10032, MultiblockSpaceAssembler(GTLiteMod.id("space_assembler_module.mk2"), UEV, 2, 3))
        SPACE_ASSEMBLER_MK3 = register(10033, MultiblockSpaceAssembler(GTLiteMod.id("space_assembler_module.mk3"), UIV, 3, 5))

        // 10034 TODO SPACE_MINER_MK1
        // 10035 TODO SPACE_MINER_MK2
        // 10036 TODO SPACE_MINER_MK3

        SPACE_PUMP_MK1 = register(10037, MultiblockSpacePump(GTLiteMod.id("space_pump_module.mk1"), UV, 1, 1))
        SPACE_PUMP_MK2 = register(10038, MultiblockSpacePump(GTLiteMod.id("space_pump_module.mk2"), UHV, 2, 2))
        SPACE_PUMP_MK3 = register(10039, MultiblockSpacePump(GTLiteMod.id("space_pump_module.mk3"), UEV, 3, 4))

        // 10040 TODO DYSON_SWARM_GROUND_UNIT

        ENERGY_INFUSER = register(10041, MultiblockEnergyInfuser(GTLiteMod.id("energy_infuser")))

        // ...

        LARGE_FORGE_HAMMER = register(10101, MultiblockForgeHammer(GTLiteMod.id("large_forge_hammer")))
        LARGE_BENDER = register(10102, MultiblockBender(GTLiteMod.id("large_bender")))
        LARGE_CUTTER = register(10103, MultiblockCutter(GTLiteMod.id("large_cutter")))
        LARGE_EXTRUDER = register(10104, MultiblockExtruder(GTLiteMod.id("large_extruder")))
        LARGE_WIREMILL = register(10105, MultiblockWiremill(GTLiteMod.id("large_wiremill")))
        LARGE_MIXER = register(10106, MultiblockMixer(GTLiteMod.id("large_mixer")))
        LARGE_EXTRACTOR = register(10107, MultiblockExtractor(GTLiteMod.id("large_extractor")))
        LARGE_ASSEMBLER = register(10108, MultiblockAssembler(GTLiteMod.id("large_assembler")))
        LARGE_LASER_ENGRAVER = register(10109, MultiblockLaserEngraver(GTLiteMod.id("large_laser_engraver")))
        LARGE_FLUID_SOLIDIFIER = register(10110, MultiblockFluidSolidifier(GTLiteMod.id("large_fluid_solidifier")))
        LARGE_BREWERY = register(10111, MultiblockBrewery(GTLiteMod.id("large_brewery")))
        LARGE_AUTOCLAVE = register(10112, MultiblockAutoclave(GTLiteMod.id("large_autoclave")))
        LARGE_ARC_FURNACE = register(10113, MultiblockArcFurnace(GTLiteMod.id("large_arc_furnace")))
        LARGE_MACERATOR = register(10114, MultiblockMacerator(GTLiteMod.id("large_macerator")))
        LARGE_CENTRIFUGE = register(10115, MultiblockCentrifuge(GTLiteMod.id("large_centrifuge")))
        LARGE_SIFTER = register(10116, MultiblockSifter(GTLiteMod.id("large_sifter")))
        LARGE_ELECTROLYZER = register(10117, MultiblockElectrolyzer(GTLiteMod.id("large_electrolyzer")))
        LARGE_ORE_WASHER = register(10118, MultiblockOreWasher(GTLiteMod.id("large_ore_washer")))
        LARGE_ELECTROMAGNET = register(10119, MultiblockElectromagnet(GTLiteMod.id("large_electromagnet")))
        LARGE_DISTILLERY = register(10120, MultiblockDistillery(GTLiteMod.id("large_distillery")))
        LARGE_BIO_REACTOR = register(10121, MultiblockBioReactor(GTLiteMod.id("large_bio_reactor")))
        LARGE_PACKER = register(10122, MultiblockPacker(GTLiteMod.id("large_packer")))
        LARGE_GAS_COLLECTOR = register(10123, MultiblockGasCollector(GTLiteMod.id("large_gas_collector")))
        LARGE_ROCK_BREAKER = register(10124, MultiblockRockBreaker(GTLiteMod.id("large_rock_breaker")))
        LARGE_BURNER_REACTOR = register(10125, MultiblockBurnerReactor(GTLiteMod.id("large_burner_reactor")))
        LARGE_CRYOGENIC_REACTOR = register(10126, MultiblockCryogenicReactor(GTLiteMod.id("large_cryogenic_reactor")))
        ELECTRIC_IMPLOSION_COMPRESSOR = register(10127, MultiblockElectricImplosionCompressor(GTLiteMod.id("electric_implosion_compressor")))
        ALLOY_BLAST_SMELTER = register(10128, MultiblockAlloyBlastSmelter(GTLiteMod.id("alloy_blast_smelter")))
        VOLCANUS = register(10129, MultiblockVolcanus(GTLiteMod.id("volcanus")))
        CRYOGENIC_FREEZER = register(10130, MultiblockCryogenicFreezer(GTLiteMod.id("cryogenic_freezer")))
        CHEMICAL_PLANT = register(10131, MultiblockChemicalPlant(GTLiteMod.id("chemical_plant")))
        INDUSTRIAL_COKE_OVEN = register(10132, MultiblockIndustrialCokeOven(GTLiteMod.id("industrial_coke_oven")))
        LARGE_MASS_FABRICATOR = register(10133, MultiblockMassFabricator(GTLiteMod.id("large_mass_fabricator")))
        LARGE_REPLICATOR = register(10134, MultiblockReplicator(GTLiteMod.id("large_replicator")))
        CIRCUIT_ASSEMBLY_LINE = register(10135, MultiblockCircuitAssemblyLine(GTLiteMod.id("circuit_assembly_line")))
        LARGE_FOOD_PROCESSOR = register(10136, MultiblockFoodProcessor(GTLiteMod.id("large_food_processor")))
        LARGE_ROCKET_ENGINE = register(10137, MultiblockRocketEngine(GTLiteMod.id("large_rocket_engine")))
        LARGE_NAQUADAH_REACTOR = register(10138, MultiblockNaquadahReactor(GTLiteMod.id("large_naquadah_reactor")))

    }

    // @formatter:on

}

// @formatter:off

private fun <T : MetaTileEntity> register(id: Int, mte: T) : T
{
    return MetaTileEntities.registerMetaTileEntity(id, mte).apply {
        addAdditionalCreativeTabs(GTLiteCreativeTabs.TAB_MACHINE)
    }
}

private inline fun <reified T : MetaTileEntity> register(id: Int, range: IntRange, mte: (Int) -> T): Array<T>
{
    return range.map { register(id + it, mte(it)) }.toTypedArray()
}

@Suppress("SameParameterValue")
private fun register(startId: Int,
                     length: Int,
                     name: String,
                     map: RecipeMap<*>,
                     texture: ICubeRenderer,
                     hasFrontFacing: Boolean,
                     tankScalingFunction: java.util.function.Function<Int, Int>) : Array<SimpleMachineMetaTileEntity>
{
    val machines = arrayOfNulls<SimpleMachineMetaTileEntity>(length)
    MetaTileEntities.registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing,
        GTLiteMod::id, tankScalingFunction)
    return machines.filterNotNull()
        .onEach { it.addAdditionalCreativeTabs(GTLiteCreativeTabs.TAB_MACHINE) }
        .toTypedArray()
}

private fun register(startId: Int,
                     name: String,
                     recipeMap: RecipeMap<*>,
                     progressBarIndicator: SteamProgressBarIndicator,
                     texture: ICubeRenderer,
                     isBricked: Boolean) : Array<SimpleSteamMachineMetaTileEntity>
{
    return arrayOf(
        register(startId, SimpleSteamMachineMetaTileEntity(GTLiteMod.id("$name.bronze"),
            recipeMap, progressBarIndicator, texture, isBricked, false)),
        register(startId + 1, SimpleSteamMachineMetaTileEntity(GTLiteMod.id("$name.steel"),
            recipeMap, progressBarIndicator, texture, isBricked, true)))
}

// @formatter:on