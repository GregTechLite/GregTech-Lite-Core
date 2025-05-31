package magicbook.gtlitecore.common.metatileentity

import gregtech.api.GTValues
import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VN
import gregtech.api.block.machines.MachineItemBlock
import gregtech.api.capability.impl.PropertyFluidFilter
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.util.GTUtility
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine
import gregtech.common.metatileentities.storage.MetaTileEntityBuffer
import gregtech.common.metatileentities.storage.MetaTileEntityCrate
import gregtech.common.metatileentities.storage.MetaTileEntityDrum
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicator
import magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicators
import magicbook.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity
import magicbook.gtlitecore.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity
import magicbook.gtlitecore.api.metatileentity.SimpleSteamMachineMetaTileEntity
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.gtliteId
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing01
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing02
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityAntimatterForge
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityBedrockDrillingRig
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCVDUnit
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCatalyticReformer
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityComponentAssemblyLine
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCosmicRayDetector
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCrystallizationCrucible
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityEnergyInfuser
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityFusionReactors
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityLaserInducedCVDUnit
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityMiningDroneAirport
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityNanoForge
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityNanoscaleFabricator
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityPCBFactory
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityPlasmaEnhancedCVDUnit
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityQuantumForceTransformer
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntitySonicator
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntitySpaceElevator
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityStellarForge
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityAdvancedFusionReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityAlloyBlastSmelter
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityChemicalPlant
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityCircuitAssemblyLine
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityCryogenicFreezer
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityElectricImplosionCompressor
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityIndustrialCokeOven
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeArcFurnace
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeAssembler
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeAutoclave
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeBender
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeBioReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeBrewery
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeBurnerReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeCentrifuge
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeCryogenicReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeCutter
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeDistillery
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeElectrolyzer
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeElectromagnet
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeExtractor
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeExtruder
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeFluidSolidifier
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeFoodProcessor
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeForgeHammer
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeGasCollector
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeLaserEngraver
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeMacerator
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeMassFabricator
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeMixer
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeOreWasher
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargePacker
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeReplicator
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeRockBreaker
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeSifter
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityLargeWiremill
import magicbook.gtlitecore.common.metatileentity.multiblock.advanced.MetaTileEntityVolcanus
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntityAntimatterGenerator
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntityLargeNaquadahReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntityLargeRocketEngine
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntityNuclearReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntitySteamEngine
import magicbook.gtlitecore.common.metatileentity.multiblock.module.MetaTileEntitySpaceAssembler
import magicbook.gtlitecore.common.metatileentity.multiblock.module.MetaTileEntitySpacePump
import magicbook.gtlitecore.common.metatileentity.multiblock.primitive.MetaTileEntityCoagulationTank
import magicbook.gtlitecore.common.metatileentity.multiblock.primitive.MetaTileEntityIndustrialPrimitiveBlastFurnace
import magicbook.gtlitecore.common.metatileentity.multiblock.steam.MetaTileEntitySteamAlloySmelter
import magicbook.gtlitecore.common.metatileentity.multiblock.steam.MetaTileEntitySteamCompressor
import magicbook.gtlitecore.common.metatileentity.part.MetaTileEntityAdvancedEnergyHatch
import magicbook.gtlitecore.common.metatileentity.part.MetaTileEntityAdvancedLaserHatch
import magicbook.gtlitecore.common.metatileentity.part.MetaTileEntityAdvancedMultiFluidHatch
import magicbook.gtlitecore.common.metatileentity.single.MetaTileEntityMobExtractor
import magicbook.gtlitecore.common.metatileentity.single.MetaTileEntitySapCollector
import magicbook.gtlitecore.common.metatileentity.single.MetaTileEntitySteamSapCollector
import magicbook.gtlitecore.common.metatileentity.storage.MetaTileEntityBridge
import magicbook.gtlitecore.common.metatileentity.storage.MetaTileEntityExtender
import magicbook.gtlitecore.common.metatileentity.storage.MetaTileEntityPlasticCan
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.items.CapabilityItemHandler

class GTLiteMetaTileEntities
{

    companion object
    {
        // Single Machines
        val POLISHER = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val SLICER = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val TOOL_CASTER = arrayOfNulls<SimpleMachineMetaTileEntity>(5)
        val LOOM = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val LAMINATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val CHEMICAL_DEHYDRATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val STEAM_VULCANIZING_PRESS = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val VULCANIZING_PRESS = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val STEAM_VACUUM_CHAMBER = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val VACUUM_CHAMBER = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val STEAM_SAP_COLLECTOR = arrayOfNulls<PseudoMultiSteamMachineMetaTileEntity>(2)
        val SAP_COLLECTOR = arrayOfNulls<PseudoMultiMachineMetaTileEntity>(5)
        val GREENHOUSE = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val BIO_REACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val STEAM_ROASTER = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val ROASTER = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val BURNER_REACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val BATH_CONDENSER = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val CRYOGENIC_REACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val MASS_FABRICATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val REPLICATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val FOOD_PROCESSOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val MULTICOOKER = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val MOB_EXTRACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(V.size - 1)
        val BIO_SIMULATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(6)
        val ROCKET_ENGINE = arrayOfNulls<SimpleGeneratorMetaTileEntity>(3)
        val NAQUADAH_REACTOR = arrayOfNulls<SimpleGeneratorMetaTileEntity>(4)

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

        val BUFFER = arrayOfNulls<MetaTileEntityBuffer>(3)

        // Multiblock Parts.
        val ENERGY_HATCH_4A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(4)
        val DYNAMO_HATCH_4A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(4)
        val ENERGY_HATCH_16A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)
        val DYNAMO_HATCH_16A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)
        val SUBSTATION_ENERGY_HATCH_64A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)
        val SUBSTATION_DYNAMO_HATCH_64A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)

        val QUADRUPLE_FLUID_IMPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)
        val QUADRUPLE_FLUID_EXPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)
        val NONUPLE_FLUID_IMPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)
        val NONUPLE_FLUID_EXPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)

        val LASER_INPUT_HATCH_16384 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_OUTPUT_HATCH_16384 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_INPUT_HATCH_65536 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_OUTPUT_HATCH_65536 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_INPUT_HATCH_262144 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_OUTPUT_HATCH_262144 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_INPUT_HATCH_1048576 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_OUTPUT_HATCH_1048576 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_INPUT_HATCH_4194304 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_OUTPUT_HATCH_4194304 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_INPUT_HATCH_16777216 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)
        val LASER_OUTPUT_HATCH_16777216 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10)

        // Multiblock Machines.
        lateinit var COAGULATION_TANK: MetaTileEntityCoagulationTank
        lateinit var STEAM_COMPRESSOR: MetaTileEntitySteamCompressor
        lateinit var STEAM_ALLOY_SMELTER: MetaTileEntitySteamAlloySmelter
        lateinit var STEAM_ENGINE: MetaTileEntitySteamEngine
        lateinit var INDUSTRIAL_PRIMITIVE_BLAST_FURNACE: MetaTileEntityIndustrialPrimitiveBlastFurnace
        lateinit var NUCLEAR_REACTOR: MetaTileEntityNuclearReactor
        lateinit var HOT_COOLANT_TURBINE: MetaTileEntityLargeTurbine
        lateinit var SUPERCRITICAL_FLUID_TURBINE: MetaTileEntityLargeTurbine
        lateinit var MINING_DRONE_AIRPORT: MetaTileEntityMiningDroneAirport

        lateinit var CATALYTIC_REFORMER: MetaTileEntityCatalyticReformer
        lateinit var CVD_UNIT: MetaTileEntityCVDUnit
        lateinit var CRYSTALLIZATION_CRUCIBLE: MetaTileEntityCrystallizationCrucible
        lateinit var NANOSCALE_FABRICATOR: MetaTileEntityNanoscaleFabricator
        lateinit var SONICATOR: MetaTileEntitySonicator
        lateinit var LASER_INDUCED_CVD_UNIT: MetaTileEntityLaserInducedCVDUnit
        lateinit var BEDROCK_DRILLING_RIG: MetaTileEntityBedrockDrillingRig
        lateinit var FUSION_REACTOR_MK4: MetaTileEntityFusionReactors
        lateinit var FUSION_REACTOR_MK5: MetaTileEntityFusionReactors
        lateinit var ADVANCED_FUSION_REACTOR: MetaTileEntityAdvancedFusionReactor
        lateinit var COMPONENT_ASSEMBLY_LINE: MetaTileEntityComponentAssemblyLine
        lateinit var COSMIC_RAY_DETECTOR: MetaTileEntityCosmicRayDetector
        lateinit var STELLAR_FORGE: MetaTileEntityStellarForge
        lateinit var PLASMA_ENHANCED_CVD_UNIT: MetaTileEntityPlasmaEnhancedCVDUnit
        lateinit var PCB_FACTORY: MetaTileEntityPCBFactory
        lateinit var NANO_FORGE: MetaTileEntityNanoForge
        lateinit var QUANTUM_FORCE_TRANSFORMER: MetaTileEntityQuantumForceTransformer
        lateinit var ANTIMATTER_FORGE: MetaTileEntityAntimatterForge
        lateinit var ANTIMATTER_GENERATOR: MetaTileEntityAntimatterGenerator
        lateinit var SPACE_ELEVATOR: MetaTileEntitySpaceElevator
        lateinit var SPACE_ASSEMBLER_MK1: MetaTileEntitySpaceAssembler
        lateinit var SPACE_ASSEMBLER_MK2: MetaTileEntitySpaceAssembler
        lateinit var SPACE_ASSEMBLER_MK3: MetaTileEntitySpaceAssembler

        lateinit var SPACE_PUMP_MK1: MetaTileEntitySpacePump
        lateinit var SPACE_PUMP_MK2: MetaTileEntitySpacePump
        lateinit var SPACE_PUMP_MK3: MetaTileEntitySpacePump

        lateinit var ENERGY_INFUSER: MetaTileEntityEnergyInfuser

        lateinit var LARGE_FORGE_HAMMER: MetaTileEntityLargeForgeHammer
        lateinit var LARGE_BENDER: MetaTileEntityLargeBender
        lateinit var LARGE_CUTTER: MetaTileEntityLargeCutter
        lateinit var LARGE_EXTRUDER: MetaTileEntityLargeExtruder
        lateinit var LARGE_WIREMILL: MetaTileEntityLargeWiremill
        lateinit var LARGE_MIXER: MetaTileEntityLargeMixer
        lateinit var LARGE_EXTRACTOR: MetaTileEntityLargeExtractor
        lateinit var LARGE_ASSEMBLER: MetaTileEntityLargeAssembler
        lateinit var LARGE_LASER_ENGRAVER: MetaTileEntityLargeLaserEngraver
        lateinit var LARGE_FLUID_SOLIDIFIER: MetaTileEntityLargeFluidSolidifier
        lateinit var LARGE_BREWERY: MetaTileEntityLargeBrewery
        lateinit var LARGE_AUTOCLAVE: MetaTileEntityLargeAutoclave
        lateinit var LARGE_ARC_FURNACE: MetaTileEntityLargeArcFurnace
        lateinit var LARGE_MACERATOR: MetaTileEntityLargeMacerator
        lateinit var LARGE_CENTRIFUGE: MetaTileEntityLargeCentrifuge
        lateinit var LARGE_SIFTER: MetaTileEntityLargeSifter
        lateinit var LARGE_ELECTROLYZER: MetaTileEntityLargeElectrolyzer
        lateinit var LARGE_ORE_WASHER: MetaTileEntityLargeOreWasher
        lateinit var LARGE_ELECTROMAGNET: MetaTileEntityLargeElectromagnet
        lateinit var LARGE_DISTILLERY: MetaTileEntityLargeDistillery
        lateinit var LARGE_BIO_REACTOR: MetaTileEntityLargeBioReactor
        lateinit var LARGE_PACKER: MetaTileEntityLargePacker
        lateinit var LARGE_GAS_COLLECTOR: MetaTileEntityLargeGasCollector
        lateinit var LARGE_ROCK_BREAKER: MetaTileEntityLargeRockBreaker
        lateinit var LARGE_BURNER_REACTOR: MetaTileEntityLargeBurnerReactor
        lateinit var LARGE_CRYOGENIC_REACTOR: MetaTileEntityLargeCryogenicReactor
        lateinit var ELECTRIC_IMPLOSION_COMPRESSOR: MetaTileEntityElectricImplosionCompressor
        lateinit var ALLOY_BLAST_SMELTER: MetaTileEntityAlloyBlastSmelter
        lateinit var VOLCANUS: MetaTileEntityVolcanus
        lateinit var CRYOGENIC_FREEZER: MetaTileEntityCryogenicFreezer
        lateinit var CHEMICAL_PLANT: MetaTileEntityChemicalPlant
        lateinit var INDUSTRIAL_COKE_OVEN: MetaTileEntityIndustrialCokeOven
        lateinit var LARGE_MASS_FABRICATOR: MetaTileEntityLargeMassFabricator
        lateinit var LARGE_REPLICATOR: MetaTileEntityLargeReplicator
        lateinit var CIRCUIT_ASSEMBLY_LINE: MetaTileEntityCircuitAssemblyLine
        lateinit var LARGE_FOOD_PROCESSOR: MetaTileEntityLargeFoodProcessor
        lateinit var LARGE_ROCKET_ENGINE: MetaTileEntityLargeRocketEngine
        lateinit var LARGE_NAQUADAH_REACTOR: MetaTileEntityLargeNaquadahReactor

        @JvmStatic
        fun preInit()
        {
            MachineItemBlock.addCreativeTab(GTLiteAPI.TAB_GTLITE_MACHINE)
        }

        @JvmStatic
        fun init()
        {
            // 1-2000: Simple Machines

            // 1-15: Polisher
            MetaTileEntities.registerSimpleMetaTileEntity(POLISHER, 3, // 1-2 for Steam Machines.
                "polisher", GTLiteRecipeMaps.POLISHER_RECIPES,
                GTLiteTextures.POLISHER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.hvCappedTankSizeFunction)

            // 16-30: Slicer
            MetaTileEntities.registerSimpleMetaTileEntity(SLICER, 18, // 16-17 for Steam Machines.
                "slicer", GTLiteRecipeMaps.SLICER_RECIPES,
                GTLiteTextures.SLICER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.hvCappedTankSizeFunction)

            // 31-45: Tool Caster
            for (tier in 0..4) // LV-IV
            {
                TOOL_CASTER[tier] = MetaTileEntities.registerMetaTileEntity(33 + tier, // 31-32 for Steam Machines.
                    SimpleMachineMetaTileEntity(gtliteId("tool_cast.${VN[tier + 1].lowercase()}"),
                        GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                        tier + 1, true, GTUtility.defaultTankSizeFunction))
            }

            // 46-60: Loom
            MetaTileEntities.registerSimpleMetaTileEntity(LOOM, 48, // 46-47 for Steam Machines.
                "loom", GTLiteRecipeMaps.LOOM_RECIPES,
                GTLiteTextures.LOOM_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 61-75: Laminator
            MetaTileEntities.registerSimpleMetaTileEntity(LAMINATOR, 63, // 61-62 for Steam Machines.
                "laminator", GTLiteRecipeMaps.LAMINATOR_RECIPES,
                GTLiteTextures.LAMINATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 76-90: Chemical Dehydrator
            MetaTileEntities.registerSimpleMetaTileEntity(CHEMICAL_DEHYDRATOR, 78, // 76-77 for Steam Machines.
                "chemical_dehydrator", GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES,
                GTLiteTextures.CHEMICAL_DEHYDRATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 91-105: Vulcanizing Press
            registerSteamMetaTileEntity(STEAM_VULCANIZING_PRESS, 91,
                "vulcanizing_press", GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                SteamProgressBarIndicators.ARROW_MULTIPLE,
                GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true)

            MetaTileEntities.registerSimpleMetaTileEntity(VULCANIZING_PRESS, 93,
                "vulcanizing_press", GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 106-120: Vacuum Chamber
            registerSteamMetaTileEntity(STEAM_VACUUM_CHAMBER, 106,
                "vacuum_chamber", GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                SteamProgressBarIndicators.COMPRESS,
                Textures.GAS_COLLECTOR_OVERLAY, false)

            MetaTileEntities.registerSimpleMetaTileEntity(VACUUM_CHAMBER, 108,
                "vacuum_chamber", GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                Textures.GAS_COLLECTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 121-135: Sap Collector
            STEAM_SAP_COLLECTOR[0] = MetaTileEntities.registerMetaTileEntity(121,
                MetaTileEntitySteamSapCollector(gtliteId("sap_collector.bronze"), false))
            STEAM_SAP_COLLECTOR[1] = MetaTileEntities.registerMetaTileEntity(122,
                MetaTileEntitySteamSapCollector(gtliteId("sap_collector.steel"), true))

            for (tier in 0..4) // LV-IV
            {
                SAP_COLLECTOR[tier] = MetaTileEntities.registerMetaTileEntity(123 + tier,
                    MetaTileEntitySapCollector(gtliteId("sap_collector.${VN[tier + 1].lowercase()}"), tier))
            }

            // 136-150: Greenhouse
            MetaTileEntities.registerSimpleMetaTileEntity(GREENHOUSE, 138, // 136-137 for Steam Machines.
                "greenhouse", GTLiteRecipeMaps.GREENHOUSE_RECIPES,
                Textures.FERMENTER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 151-165: Bio Reactor
            MetaTileEntities.registerSimpleMetaTileEntity(BIO_REACTOR, 153, // 151-152 for Steam Machines.
                "bio_reactor", GTLiteRecipeMaps.BIO_REACTOR_RECIPES,
                GTLiteTextures.BIO_REACTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.steamGeneratorTankSizeFunction)

            // 166-180: Roaster
            registerSteamMetaTileEntity(STEAM_ROASTER, 166,
                "roaster", GTLiteRecipeMaps.ROASTER_RECIPES,
                SteamProgressBarIndicators.ARROW,
                GTLiteTextures.ROASTER_OVERLAY, true)

            MetaTileEntities.registerSimpleMetaTileEntity(ROASTER, 168,
                "roaster", GTLiteRecipeMaps.ROASTER_RECIPES,
                GTLiteTextures.ROASTER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 181-195: Burner Reactor
            MetaTileEntities.registerSimpleMetaTileEntity(BURNER_REACTOR, 183, // 181-182 for Steam Machines.
                "burner_reactor", GTLiteRecipeMaps.BURNER_REACTOR_RECIPES,
                GTLiteTextures.BURNER_REACTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 196-210: Bath Condenser
            MetaTileEntities.registerSimpleMetaTileEntity(BATH_CONDENSER, 198, // 196-197 for Steam Machines.
                "bath_condenser", GTLiteRecipeMaps.BATH_CONDENSER_RECIPES,
                GTLiteTextures.BATH_CONDENSER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 211-225: Cryogenic Reactor
            MetaTileEntities.registerSimpleMetaTileEntity(CRYOGENIC_REACTOR, 213, // 211-212 for Steam Machines.
                "cryogenic_reactor", GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES,
                GTLiteTextures.CRYOGENIC_REACTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 226-240: Mass Fabricator
            MetaTileEntities.registerSimpleMetaTileEntity(MASS_FABRICATOR, 228, // 226-227 for Steam Machines.
                "mass_fabricator", RecipeMaps.MASS_FABRICATOR_RECIPES,
                Textures.MASS_FABRICATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 241-255: Replicator
            MetaTileEntities.registerSimpleMetaTileEntity(REPLICATOR, 243, // 241-242 for Steam Machines.
                "replicator", RecipeMaps.REPLICATOR_RECIPES,
                Textures.REPLICATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 256-270: Food Processor
            MetaTileEntities.registerSimpleMetaTileEntity(FOOD_PROCESSOR, 258, // 256-257 for Steam Machines.
                "food_processor", GTLiteRecipeMaps.FOOD_PROCESSOR_RECIPES,
                GTLiteTextures.FOOD_PROCESSOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 271-285: Multicooker
            MetaTileEntities.registerSimpleMetaTileEntity(MULTICOOKER, 273, // 271-272 for Steam Machines.
                "multicooker", GTLiteRecipeMaps.MULTICOOKER_RECIPES,
                GTLiteTextures.MULTICOOKER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 286-300: Mob Extractor
            for (tier in 0..13) // LV-OpV
            {
                MOB_EXTRACTOR[tier] = MetaTileEntities.registerMetaTileEntity(288 + tier, // 286-287 for Steam Machines.
                    MetaTileEntityMobExtractor(gtliteId("mob_extractor.${VN[tier + 1].lowercase()}"),
                        GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES, GTLiteTextures.MOB_EXTRACTOR_OVERLAY, tier + 1,
                        false, GTUtility.largeTankSizeFunction))
            }

            // 301-315: Bio Simulator
            MetaTileEntities.registerSimpleMetaTileEntity(BIO_SIMULATOR, 303, // 301-302 for Steam Machines.
                "bio_simulator", GTLiteRecipeMaps.BIO_SIMULATOR_RECIPES,
                GTLiteTextures.BIO_SIMULATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 316-330: Rocket Engine
            for (tier in 0..2) // HV-EV
            {
                ROCKET_ENGINE[tier] = MetaTileEntities.registerMetaTileEntity(320 + tier, // 316-317 for Steam Machines, 318-319 for LV-MV Machines.
                    SimpleGeneratorMetaTileEntity(gtliteId("rocket_engine.${VN[tier + HV].lowercase()}"),
                        GTLiteRecipeMaps.ROCKET_ENGINE_FUELS, GTLiteTextures.ROCKET_ENGINE_OVERLAY,
                        tier + HV, GTUtility.genericGeneratorTankSizeFunction))
            }

            // 331-345: Naquadah Reactor
            for (tier in 0..3) // IV-UV
            {
                NAQUADAH_REACTOR[tier] = MetaTileEntities.registerMetaTileEntity(337 + tier, // 331-332 for Steam Machines, 333-336 for LV-EV Machines.
                    SimpleGeneratorMetaTileEntity(gtliteId("naquadah_reactor.${VN[tier + IV].lowercase()}"),
                        GTLiteRecipeMaps.NAQUADAH_REACTOR_FUELS, GTLiteTextures.NAQUADAH_REACTOR_OVERLAY,
                        tier + IV, GTUtility.genericGeneratorTankSizeFunction))
            }

            // ---------------------------------------------------------------------------------------------------------
            // 2001-4000: Several single MTEs.

            // 2001-2050: Drums and Crates
            IRON_DRUM = MetaTileEntities.registerMetaTileEntity(2001,
                MetaTileEntityDrum(gtliteId("drum.iron"),
                    PropertyFluidFilter(1811, true, true, false, false),
                    false, Iron.materialRGB, 12_000))

            COPPER_DRUM = MetaTileEntities.registerMetaTileEntity(2002,
                MetaTileEntityDrum(gtliteId("drum.copper"), Copper, 16_000))

            LEAD_DRUM = MetaTileEntities.registerMetaTileEntity(2003,
                MetaTileEntityDrum(gtliteId("drum.lead"), Lead, 24_000))

            CHROME_DRUM = MetaTileEntities.registerMetaTileEntity(2004,
                MetaTileEntityDrum(gtliteId("drum.chrome"), Chrome, 96_000))

            TUNGSTEN_DRUM = MetaTileEntities.registerMetaTileEntity(2005,
                MetaTileEntityDrum(gtliteId("drum.tungsten"), Tungsten, 768_000))

            IRIDIUM_DRUM = MetaTileEntities.registerMetaTileEntity(2006,
                MetaTileEntityDrum(gtliteId("drum.iridium"), Iridium, 1_536_000))

            // ...

            PE_CAN = MetaTileEntities.registerMetaTileEntity(2016,
                MetaTileEntityPlasticCan(gtliteId("plastic_can.polyethylene"), Polyethylene, 64_000))

            PTFE_CAN = MetaTileEntities.registerMetaTileEntity(2017,
                MetaTileEntityPlasticCan(gtliteId("plastic_can.polytetrafluoroethylene"), Polytetrafluoroethylene, 128_000))

            PBI_CAN = MetaTileEntities.registerMetaTileEntity(2018,
                MetaTileEntityPlasticCan(gtliteId("plastic_can.polybenzimidazole"), Polybenzimidazole, 256_000))

            KEVLAR_CAN = MetaTileEntities.registerMetaTileEntity(2019,
                MetaTileEntityPlasticCan(gtliteId("plastic_can.kevlar"), Kevlar, 512_000))

            // ...

            IRON_CRATE = MetaTileEntities.registerMetaTileEntity(2026,
                MetaTileEntityCrate(gtliteId("crate.iron"), Iron, 45, 9))

            COPPER_CRATE = MetaTileEntities.registerMetaTileEntity(2027,
                MetaTileEntityCrate(gtliteId("crate.copper"), Copper, 36, 9))

            SILVER_CRATE = MetaTileEntities.registerMetaTileEntity(2028,
                MetaTileEntityCrate(gtliteId("crate.silver"), Silver, 63, 9))

            GOLD_CRATE = MetaTileEntities.registerMetaTileEntity(2029,
                MetaTileEntityCrate(gtliteId("crate.gold"), Gold, 81, 9))

            DIAMOND_CRATE = MetaTileEntities.registerMetaTileEntity(2030,
                MetaTileEntityCrate(gtliteId("crate.diamond"), Diamond, 100, 10))

            // ...

            // 2051-2060: Import/Export Proxies
            INVENTORY_BRIDGE = MetaTileEntities.registerMetaTileEntity(2051,
                MetaTileEntityBridge(gtliteId("bridge.inventory"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_BRIDGE, Steel))

            TANK_BRIDGE = MetaTileEntities.registerMetaTileEntity(2052,
                MetaTileEntityBridge(gtliteId("bridge.tank"),
                    { c -> c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.TANK_BRIDGE, Steel))

            INVENTORY_TANK_BRIDGE = MetaTileEntities.registerMetaTileEntity(2053,
                MetaTileEntityBridge(gtliteId("bridge.inventory_tank"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_TANK_BRIDGE, Steel))

            UNIVERSAL_BRIDGE = MetaTileEntities.registerMetaTileEntity(2054,
                MetaTileEntityBridge(gtliteId("bridge.universal"),
                    { true },
                    GTLiteTextures.UNIVERSAL_BRIDGE, Aluminium))

            INVENTORY_EXTENDER = MetaTileEntities.registerMetaTileEntity(2055,
                MetaTileEntityExtender(gtliteId("extender.inventory"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_EXTENDER, Materials.Steel))

            TANK_EXTENDER = MetaTileEntities.registerMetaTileEntity(2056,
                MetaTileEntityExtender(gtliteId("extender.tank"),
                    { c -> c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.TANK_EXTENDER, Steel))

            INVENTORY_TANK_EXTENDER = MetaTileEntities.registerMetaTileEntity(2057,
                MetaTileEntityExtender(gtliteId("extender.inventory_tank"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_TANK_EXTENDER, Steel))

            UNIVERSAL_EXTENDER = MetaTileEntities.registerMetaTileEntity(2058,
                MetaTileEntityExtender(gtliteId("extender.universal"),
                    { true },
                    GTLiteTextures.UNIVERSAL_EXTENDER, Aluminium))

            // 2061-2065: Advanced Buffers
            for (tier in 0..1) // EV-IV
            {
                BUFFER[tier] = MetaTileEntities.registerMetaTileEntity(2061 + tier,
                    MetaTileEntityBuffer(gtliteId("buffer.${VN[tier + EV].lowercase()}"), tier + EV))
            }

            // ---------------------------------------------------------------------------------------------------------
            // 4001-10000: Multiblock Parts

            // 4001-5000: (Wireless/Substation) Energy/Dynamo Hatches and Laser Target/Source Hatches.

            // 4001-4004: ULV-HV 4A Energy Hatches.
            for (tier in 0..3)
            {
                ENERGY_HATCH_4A[tier] = MetaTileEntities.registerMetaTileEntity(4001 + tier,
                    MetaTileEntityAdvancedEnergyHatch(gtliteId("energy_hatch.input_4a.${VN[tier].lowercase()}"),
                        tier, 4, false))
            }

            // 4005-4008: ULV-HV 4A Dynamo Hatches
            for (tier in 0..3)
            {
                DYNAMO_HATCH_4A[tier] = MetaTileEntities.registerMetaTileEntity(4005 + tier,
                    MetaTileEntityAdvancedEnergyHatch(gtliteId("energy_hatch.output_4a.${VN[tier].lowercase()}"),
                        tier, 4, true))
            }

            // 4009-4013: ULV-EV 16A Energy Hatches.
            for (tier in 0..4)
            {
                ENERGY_HATCH_16A[tier] = MetaTileEntities.registerMetaTileEntity(4009 + tier,
                    MetaTileEntityAdvancedEnergyHatch(gtliteId("energy_hatch.input_16a.${VN[tier].lowercase()}"),
                        tier, 16, false))
            }

            // 4014-4018: ULV-EV 16A Dynamo Hatches.
            for (tier in 0..4)
            {
                DYNAMO_HATCH_16A[tier] = MetaTileEntities.registerMetaTileEntity(4014 + tier,
                    MetaTileEntityAdvancedEnergyHatch(gtliteId("energy_hatch.output_16a.${VN[tier].lowercase()}"),
                        tier, 16, true))
            }

            // 4019-4023 : ULV-EV 64A Substation Energy Hatches.
            for (tier in 0..4)
            {
                SUBSTATION_ENERGY_HATCH_64A[tier] = MetaTileEntities.registerMetaTileEntity(4019 + tier,
                    MetaTileEntityAdvancedEnergyHatch(gtliteId("substation_hatch.input_64a.${VN[tier].lowercase()}"),
                        tier, 64, false))
            }

            // 4024-4028: ULV-EV 64A Substation Dynamo Hatches.
            for (tier in 0..4)
            {
                SUBSTATION_DYNAMO_HATCH_64A[tier] = MetaTileEntities.registerMetaTileEntity(4024 + tier,
                    MetaTileEntityAdvancedEnergyHatch(gtliteId("substation_hatch.output_64a.${VN[tier].lowercase()}"),
                        tier, 64, true))
            }

            // 4031-4140: IV-OpV Hi-Amp Laser Target/Source Hatches.
            for (tier in 0..8)
            {
                val voltageTier = tier + IV
                val voltageName = VN[voltageTier].lowercase()

                LASER_INPUT_HATCH_16384[tier] = MetaTileEntities.registerMetaTileEntity(4031 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.target_16384a.${voltageName}"),
                        voltageTier, 16_384, false))

                LASER_OUTPUT_HATCH_16384[tier] = MetaTileEntities.registerMetaTileEntity(4040 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.source_16384a.${voltageName}"),
                        voltageTier, 16_384, true))

                LASER_INPUT_HATCH_65536[tier] = MetaTileEntities.registerMetaTileEntity(4049 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.target_65536a.${voltageName}"),
                        voltageTier, 65_536, false))

                LASER_OUTPUT_HATCH_65536[tier] = MetaTileEntities.registerMetaTileEntity(4058 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.source_65536a.${voltageName}"),
                        voltageTier, 65_536, true))

                LASER_INPUT_HATCH_262144[tier] = MetaTileEntities.registerMetaTileEntity(4067 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.target_262144a.${voltageName}"),
                        voltageTier, 262_144, false))

                LASER_OUTPUT_HATCH_262144[tier] = MetaTileEntities.registerMetaTileEntity(4076 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.source_262144a.${voltageName}"),
                        voltageTier, 262_144, true))

                LASER_INPUT_HATCH_1048576[tier] = MetaTileEntities.registerMetaTileEntity(4085 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.target_1048576a.${voltageName}"),
                        voltageTier, 1_048_576, false))

                LASER_OUTPUT_HATCH_1048576[tier] = MetaTileEntities.registerMetaTileEntity(4094 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.source_1048576a.${voltageName}"),
                        voltageTier, 1_048_576, true))

                LASER_INPUT_HATCH_4194304[tier] = MetaTileEntities.registerMetaTileEntity(4103 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.target_4194304a.${voltageName}"),
                        voltageTier, 4_194_304, false))

                LASER_OUTPUT_HATCH_4194304[tier] = MetaTileEntities.registerMetaTileEntity(4112 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.source_4194304a.${voltageName}"),
                        voltageTier, 4_194_304, true))

                LASER_INPUT_HATCH_16777216[tier] = MetaTileEntities.registerMetaTileEntity(4121 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.target_16777216a.${voltageName}"),
                        voltageTier, 16_777_216, false))

                LASER_OUTPUT_HATCH_16777216[tier] = MetaTileEntities.registerMetaTileEntity(4130 + tier,
                    MetaTileEntityAdvancedLaserHatch(gtliteId("laser_hatch.source_16777216a.${voltageName}"),
                        voltageTier, 16_777_216, true))
            }

            // TODO Wireless Energy/Dynamo Hatches

            // 5001-5100: Item Import/Export Buses and Fluid Import/Export Hatches

            // 5001-5004: ULV-HV Quadruple Fluid Import Hatches
            for (tier in 0..3)
            {
                QUADRUPLE_FLUID_IMPORT_HATCH[tier] = MetaTileEntities.registerMetaTileEntity(5001 + tier,
                    MetaTileEntityAdvancedMultiFluidHatch(gtliteId("fluid_hatch.import_4x.${VN[tier].lowercase()}"),
                        tier, 4, false))
            }

            // 5005-5008: ULV-HV Quadruple Fluid Export Hatches
            for (tier in 0..3)
            {
                QUADRUPLE_FLUID_EXPORT_HATCH[tier] = MetaTileEntities.registerMetaTileEntity(5005 + tier,
                    MetaTileEntityAdvancedMultiFluidHatch(gtliteId("fluid_hatch.export_4x.${VN[tier].lowercase()}"),
                        tier, 4, true))
            }

            // 5009-5012: ULV-HV Nonuple Fluid Import Hatches
            for (tier in 0..3)
            {
                NONUPLE_FLUID_IMPORT_HATCH[tier] = MetaTileEntities.registerMetaTileEntity(5009 + tier,
                    MetaTileEntityAdvancedMultiFluidHatch(gtliteId("fluid_hatch.import_9x.${VN[tier].lowercase()}"),
                        tier, 9, false))
            }

            // 5013-5016: ULV-HV Nonuple Fluid Export Hatches
            for (tier in 0..3)
            {
                NONUPLE_FLUID_EXPORT_HATCH[tier] = MetaTileEntities.registerMetaTileEntity(5013 + tier,
                    MetaTileEntityAdvancedMultiFluidHatch(gtliteId("fluid_hatch.export_9x.${VN[tier].lowercase()}"),
                        tier, 9, true))
            }

            // ---------------------------------------------------------------------------------------------------------
            // 10001-20000 Multiblock Machines

            COAGULATION_TANK = MetaTileEntities.registerMetaTileEntity(10001,
                MetaTileEntityCoagulationTank(gtliteId("coagulation_tank")))

            STEAM_COMPRESSOR = MetaTileEntities.registerMetaTileEntity(10002,
                MetaTileEntitySteamCompressor(gtliteId("steam_compressor")))

            STEAM_ALLOY_SMELTER = MetaTileEntities.registerMetaTileEntity(10003,
                MetaTileEntitySteamAlloySmelter(gtliteId("steam_alloy_smelter")))

            STEAM_ENGINE = MetaTileEntities.registerMetaTileEntity(10004,
                MetaTileEntitySteamEngine(gtliteId("steam_engine")))

            INDUSTRIAL_PRIMITIVE_BLAST_FURNACE = MetaTileEntities.registerMetaTileEntity(10005,
                MetaTileEntityIndustrialPrimitiveBlastFurnace(gtliteId("industrial_primitive_blast_furnace")))

            MINING_DRONE_AIRPORT = MetaTileEntities.registerMetaTileEntity(10006,
                MetaTileEntityMiningDroneAirport(gtliteId("mining_drone_airport")))

            // 10007 ...

            NUCLEAR_REACTOR = MetaTileEntities.registerMetaTileEntity(10008,
                MetaTileEntityNuclearReactor(gtliteId("nuclear_reactor")))

            HOT_COOLANT_TURBINE = MetaTileEntities.registerMetaTileEntity(10009,
                MetaTileEntityLargeTurbine(gtliteId("large_turbine.hot_coolant"),
                    GTLiteRecipeMaps.HOT_COOLANT_TURBINE_FUELS, EV,
                    MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_TURBINE_CASING),
                    MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX),
                    Textures.STABLE_TITANIUM_CASING, true, Textures.LARGE_GAS_TURBINE_OVERLAY))

            SUPERCRITICAL_FLUID_TURBINE = MetaTileEntities.registerMetaTileEntity(10010,
                MetaTileEntityLargeTurbine(gtliteId("large_turbine.supercritical_fluid"),
                    GTLiteRecipeMaps.SUPERCRITICAL_FLUID_TURBINE_FUELS, LuV,
                    GTLiteMetaBlocks.TURBINE_CASING_01.getState(BlockTurbineCasing01.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_TURBINE),
                    GTLiteMetaBlocks.TURBINE_CASING_02.getState(BlockTurbineCasing02.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_GEARBOX),
                    GTLiteTextures.RHODIUM_PLATED_PALLADIUM_CASING, true, Textures.LARGE_GAS_TURBINE_OVERLAY))

            CATALYTIC_REFORMER = MetaTileEntities.registerMetaTileEntity(10011,
                MetaTileEntityCatalyticReformer(gtliteId("catalytic_reformer")))

            CVD_UNIT = MetaTileEntities.registerMetaTileEntity(10012,
                MetaTileEntityCVDUnit(gtliteId("cvd_unit")))

            CRYSTALLIZATION_CRUCIBLE = MetaTileEntities.registerMetaTileEntity(10013,
                MetaTileEntityCrystallizationCrucible(gtliteId("crystallization_crucible")))

            NANOSCALE_FABRICATOR = MetaTileEntities.registerMetaTileEntity(10014,
                MetaTileEntityNanoscaleFabricator(gtliteId("nanoscale_fabricator")))

            SONICATOR = MetaTileEntities.registerMetaTileEntity(10015,
                MetaTileEntitySonicator(gtliteId("sonicator")))

            LASER_INDUCED_CVD_UNIT = MetaTileEntities.registerMetaTileEntity(10016,
                MetaTileEntityLaserInducedCVDUnit(gtliteId("laser_induced_cvd_unit")))

            BEDROCK_DRILLING_RIG = MetaTileEntities.registerMetaTileEntity(10017,
                MetaTileEntityBedrockDrillingRig(gtliteId("bedrock_drilling_rig")))

            FUSION_REACTOR_MK4 = MetaTileEntities.registerMetaTileEntity(10018,
                MetaTileEntityFusionReactors(gtliteId("fusion_reactor.uhv"), GTValues.UHV))

            FUSION_REACTOR_MK5 = MetaTileEntities.registerMetaTileEntity(10019,
                MetaTileEntityFusionReactors(gtliteId("fusion_reactor.uev"), GTValues.UEV))

            ADVANCED_FUSION_REACTOR = MetaTileEntities.registerMetaTileEntity(10020,
                MetaTileEntityAdvancedFusionReactor(gtliteId("advanced_fusion_reactor")))

            COMPONENT_ASSEMBLY_LINE = MetaTileEntities.registerMetaTileEntity(10021,
                MetaTileEntityComponentAssemblyLine(gtliteId("component_assembly_line")))

            COSMIC_RAY_DETECTOR = MetaTileEntities.registerMetaTileEntity(10022,
                MetaTileEntityCosmicRayDetector(gtliteId("cosmic_ray_detector")))

            STELLAR_FORGE = MetaTileEntities.registerMetaTileEntity(10023,
                MetaTileEntityStellarForge(gtliteId("stellar_forge")))

            PLASMA_ENHANCED_CVD_UNIT = MetaTileEntities.registerMetaTileEntity(10024,
                MetaTileEntityPlasmaEnhancedCVDUnit(gtliteId("plasma_enhanced_cvd_unit")))

            PCB_FACTORY = MetaTileEntities.registerMetaTileEntity(10025,
                MetaTileEntityPCBFactory(gtliteId("pcb_factory")))

            NANO_FORGE = MetaTileEntities.registerMetaTileEntity(10026,
                MetaTileEntityNanoForge(gtliteId("nano_forge")))

            QUANTUM_FORCE_TRANSFORMER = MetaTileEntities.registerMetaTileEntity(10027,
                MetaTileEntityQuantumForceTransformer(gtliteId("quantum_force_transformer")))

            ANTIMATTER_FORGE = MetaTileEntities.registerMetaTileEntity(10028,
                MetaTileEntityAntimatterForge(gtliteId("antimatter_forge")))

            ANTIMATTER_GENERATOR = MetaTileEntities.registerMetaTileEntity(10029,
                MetaTileEntityAntimatterGenerator(gtliteId("antimatter_generator")))

            SPACE_ELEVATOR = MetaTileEntities.registerMetaTileEntity(10030,
                MetaTileEntitySpaceElevator(gtliteId("space_elevator")))

            SPACE_ASSEMBLER_MK1 = MetaTileEntities.registerMetaTileEntity(10031,
                MetaTileEntitySpaceAssembler(gtliteId("space_assembler_module.mk1"),
                    UHV, 1, 1))

            SPACE_ASSEMBLER_MK2 = MetaTileEntities.registerMetaTileEntity(10032,
                MetaTileEntitySpaceAssembler(gtliteId("space_assembler_module.mk2"),
                    UEV, 2, 3))

            SPACE_ASSEMBLER_MK3 = MetaTileEntities.registerMetaTileEntity(10033,
                MetaTileEntitySpaceAssembler(gtliteId("space_assembler_module.mk3"),
                    UIV, 3, 5))

            // SPACE_MINER_MK1 10034
            // SPACE_MINER_MK2 10035
            // SPACE_MINER_MK3 10036

            SPACE_PUMP_MK1 = MetaTileEntities.registerMetaTileEntity(10037,
                MetaTileEntitySpacePump(gtliteId("space_pump_module.mk1"),
                    UV, 1, 1))

            SPACE_PUMP_MK2 = MetaTileEntities.registerMetaTileEntity(10038,
                MetaTileEntitySpacePump(gtliteId("space_pump_module.mk2"),
                    UHV, 2, 2))

            SPACE_PUMP_MK3 = MetaTileEntities.registerMetaTileEntity(10039,
                MetaTileEntitySpacePump(gtliteId("space_pump_module.mk3"),
                    UEV, 3, 4))

            // DYSON_SWARM_GROUND_UNIT 10040

            ENERGY_INFUSER = MetaTileEntities.registerMetaTileEntity(10041,
                MetaTileEntityEnergyInfuser(gtliteId("energy_infuser")))

            // ...

            LARGE_FORGE_HAMMER = MetaTileEntities.registerMetaTileEntity(10101,
                MetaTileEntityLargeForgeHammer(gtliteId("large_forge_hammer")))

            LARGE_BENDER = MetaTileEntities.registerMetaTileEntity(10102,
                MetaTileEntityLargeBender(gtliteId("large_bender")))

            LARGE_CUTTER = MetaTileEntities.registerMetaTileEntity(10103,
                MetaTileEntityLargeCutter(gtliteId("large_cutter")))

            LARGE_EXTRUDER = MetaTileEntities.registerMetaTileEntity(10104,
                MetaTileEntityLargeExtruder(gtliteId("large_extruder")))

            LARGE_WIREMILL = MetaTileEntities.registerMetaTileEntity(10105,
                MetaTileEntityLargeWiremill(gtliteId("large_wiremill")))

            LARGE_MIXER = MetaTileEntities.registerMetaTileEntity(10106,
                MetaTileEntityLargeMixer(gtliteId("large_mixer")))

            LARGE_EXTRACTOR = MetaTileEntities.registerMetaTileEntity(10107,
                MetaTileEntityLargeExtractor(gtliteId("large_extractor")))

            LARGE_ASSEMBLER = MetaTileEntities.registerMetaTileEntity(10108,
                MetaTileEntityLargeAssembler(gtliteId("large_assembler")))

            LARGE_LASER_ENGRAVER = MetaTileEntities.registerMetaTileEntity(10109,
                MetaTileEntityLargeLaserEngraver(gtliteId("large_laser_engraver")))

            LARGE_FLUID_SOLIDIFIER = MetaTileEntities.registerMetaTileEntity(10110,
                MetaTileEntityLargeFluidSolidifier(gtliteId("large_fluid_solidifier")))

            LARGE_BREWERY = MetaTileEntities.registerMetaTileEntity(10111,
                MetaTileEntityLargeBrewery(gtliteId("large_brewery")))

            LARGE_AUTOCLAVE = MetaTileEntities.registerMetaTileEntity(10112,
                MetaTileEntityLargeAutoclave(gtliteId("large_autoclave")))

            LARGE_ARC_FURNACE = MetaTileEntities.registerMetaTileEntity(10113,
                MetaTileEntityLargeArcFurnace(gtliteId("large_arc_furnace")))

            LARGE_MACERATOR = MetaTileEntities.registerMetaTileEntity(10114,
                MetaTileEntityLargeMacerator(gtliteId("large_macerator")))

            LARGE_CENTRIFUGE = MetaTileEntities.registerMetaTileEntity(10115,
                MetaTileEntityLargeCentrifuge(gtliteId("large_centrifuge")))

            LARGE_SIFTER = MetaTileEntities.registerMetaTileEntity(10116,
                MetaTileEntityLargeSifter(gtliteId("large_sifter")))

            LARGE_ELECTROLYZER = MetaTileEntities.registerMetaTileEntity(10117,
                MetaTileEntityLargeElectrolyzer(gtliteId("large_electrolyzer")))

            LARGE_ORE_WASHER = MetaTileEntities.registerMetaTileEntity(10118,
                MetaTileEntityLargeOreWasher(gtliteId("large_ore_washer")))

            LARGE_ELECTROMAGNET = MetaTileEntities.registerMetaTileEntity(10119,
                MetaTileEntityLargeElectromagnet(gtliteId("large_electromagnet")))

            LARGE_DISTILLERY = MetaTileEntities.registerMetaTileEntity(10120,
                MetaTileEntityLargeDistillery(gtliteId("large_distillery")))

            LARGE_BIO_REACTOR = MetaTileEntities.registerMetaTileEntity(10121,
                MetaTileEntityLargeBioReactor(gtliteId("large_bio_reactor")))

            LARGE_PACKER = MetaTileEntities.registerMetaTileEntity(10122,
                MetaTileEntityLargePacker(gtliteId("large_packer")))

            LARGE_GAS_COLLECTOR = MetaTileEntities.registerMetaTileEntity(10123,
                MetaTileEntityLargeGasCollector(gtliteId("large_gas_collector")))

            LARGE_ROCK_BREAKER = MetaTileEntities.registerMetaTileEntity(10124,
                MetaTileEntityLargeRockBreaker(gtliteId("large_rock_breaker")))

            LARGE_BURNER_REACTOR = MetaTileEntities.registerMetaTileEntity(10125,
                MetaTileEntityLargeBurnerReactor(gtliteId("large_burner_reactor")))

            LARGE_CRYOGENIC_REACTOR = MetaTileEntities.registerMetaTileEntity(10126,
                MetaTileEntityLargeCryogenicReactor(gtliteId("large_cryogenic_reactor")))

            ELECTRIC_IMPLOSION_COMPRESSOR = MetaTileEntities.registerMetaTileEntity(10127,
                MetaTileEntityElectricImplosionCompressor(gtliteId("electric_implosion_compressor")))

            ALLOY_BLAST_SMELTER = MetaTileEntities.registerMetaTileEntity(10128,
                MetaTileEntityAlloyBlastSmelter(gtliteId("alloy_blast_smelter")))

            VOLCANUS = MetaTileEntities.registerMetaTileEntity(10129,
                MetaTileEntityVolcanus(gtliteId("volcanus")))

            CRYOGENIC_FREEZER = MetaTileEntities.registerMetaTileEntity(10130,
                MetaTileEntityCryogenicFreezer(gtliteId("cryogenic_freezer")))

            CHEMICAL_PLANT = MetaTileEntities.registerMetaTileEntity(10131,
                MetaTileEntityChemicalPlant(gtliteId("chemical_plant")))

            INDUSTRIAL_COKE_OVEN = MetaTileEntities.registerMetaTileEntity(10132,
                MetaTileEntityIndustrialCokeOven(gtliteId("industrial_coke_oven")))

            LARGE_MASS_FABRICATOR = MetaTileEntities.registerMetaTileEntity(10133,
                MetaTileEntityLargeMassFabricator(gtliteId("large_mass_fabricator")))

            LARGE_REPLICATOR = MetaTileEntities.registerMetaTileEntity(10134,
                MetaTileEntityLargeReplicator(gtliteId("large_replicator")))

            CIRCUIT_ASSEMBLY_LINE = MetaTileEntities.registerMetaTileEntity(10135,
                MetaTileEntityCircuitAssemblyLine(gtliteId("circuit_assembly_line")))

            LARGE_FOOD_PROCESSOR = MetaTileEntities.registerMetaTileEntity(10136,
                MetaTileEntityLargeFoodProcessor(gtliteId("large_food_processor")))

            LARGE_ROCKET_ENGINE = MetaTileEntities.registerMetaTileEntity(10137,
                MetaTileEntityLargeRocketEngine(gtliteId("large_rocket_engine")))

            LARGE_NAQUADAH_REACTOR = MetaTileEntities.registerMetaTileEntity(10138,
                MetaTileEntityLargeNaquadahReactor(gtliteId("large_naquadah_reactor")))

            // ADVANCED_ASSEMBLY_LINE

        }

        @JvmStatic
        fun postInit()
        {
            POLISHER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            SLICER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            TOOL_CASTER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LOOM.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LAMINATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            CHEMICAL_DEHYDRATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            STEAM_VULCANIZING_PRESS.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            VULCANIZING_PRESS.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            STEAM_VACUUM_CHAMBER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            VACUUM_CHAMBER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            STEAM_SAP_COLLECTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            SAP_COLLECTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            GREENHOUSE.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            BIO_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            STEAM_ROASTER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            ROASTER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            BURNER_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            BATH_CONDENSER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            CRYOGENIC_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            MASS_FABRICATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            REPLICATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            FOOD_PROCESSOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            MULTICOOKER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            MOB_EXTRACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            BIO_SIMULATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            ROCKET_ENGINE.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            NAQUADAH_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }

            IRON_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            COPPER_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LEAD_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            CHROME_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            TUNGSTEN_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            IRIDIUM_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)

            PE_CAN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            PTFE_CAN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            PBI_CAN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            KEVLAR_CAN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)

            INVENTORY_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            TANK_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            INVENTORY_TANK_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            UNIVERSAL_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)

            INVENTORY_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            TANK_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            INVENTORY_TANK_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            UNIVERSAL_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)

            BUFFER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }

            ENERGY_HATCH_4A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            DYNAMO_HATCH_4A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            ENERGY_HATCH_16A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            DYNAMO_HATCH_16A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            SUBSTATION_ENERGY_HATCH_64A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            SUBSTATION_DYNAMO_HATCH_64A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }

            LASER_INPUT_HATCH_16384.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_OUTPUT_HATCH_16384.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_INPUT_HATCH_65536.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_OUTPUT_HATCH_65536.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_INPUT_HATCH_262144.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_OUTPUT_HATCH_262144.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_INPUT_HATCH_1048576.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_OUTPUT_HATCH_1048576.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_INPUT_HATCH_4194304.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_OUTPUT_HATCH_4194304.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_INPUT_HATCH_16777216.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            LASER_OUTPUT_HATCH_16777216.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }

            QUADRUPLE_FLUID_IMPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            QUADRUPLE_FLUID_EXPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            NONUPLE_FLUID_IMPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }
            NONUPLE_FLUID_EXPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE) }

            COAGULATION_TANK.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            STEAM_COMPRESSOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            STEAM_ALLOY_SMELTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            STEAM_ENGINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            MINING_DRONE_AIRPORT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            NUCLEAR_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            HOT_COOLANT_TURBINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            SUPERCRITICAL_FLUID_TURBINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)

            CATALYTIC_REFORMER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            CVD_UNIT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            CRYSTALLIZATION_CRUCIBLE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            NANOSCALE_FABRICATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            SONICATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LASER_INDUCED_CVD_UNIT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            BEDROCK_DRILLING_RIG.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            FUSION_REACTOR_MK4.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            FUSION_REACTOR_MK5.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            ADVANCED_FUSION_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            COMPONENT_ASSEMBLY_LINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            COSMIC_RAY_DETECTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            STELLAR_FORGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            PLASMA_ENHANCED_CVD_UNIT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            PCB_FACTORY.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            NANO_FORGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            QUANTUM_FORCE_TRANSFORMER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            ANTIMATTER_FORGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            ANTIMATTER_GENERATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            SPACE_ELEVATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            SPACE_ASSEMBLER_MK1.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            SPACE_ASSEMBLER_MK2.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            SPACE_ASSEMBLER_MK3.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)

            LARGE_FORGE_HAMMER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_BENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_CUTTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_EXTRUDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_WIREMILL.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_MIXER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_EXTRACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_ASSEMBLER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_LASER_ENGRAVER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_FLUID_SOLIDIFIER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_BREWERY.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_AUTOCLAVE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_ARC_FURNACE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_MACERATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_CENTRIFUGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_SIFTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_ELECTROLYZER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_ORE_WASHER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_ELECTROMAGNET.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_DISTILLERY.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_PACKER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_GAS_COLLECTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_ROCK_BREAKER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_BURNER_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_CRYOGENIC_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            ELECTRIC_IMPLOSION_COMPRESSOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            ALLOY_BLAST_SMELTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            VOLCANUS.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            CRYOGENIC_FREEZER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            CHEMICAL_PLANT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            INDUSTRIAL_COKE_OVEN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_MASS_FABRICATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            CIRCUIT_ASSEMBLY_LINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_FOOD_PROCESSOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_ROCKET_ENGINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
            LARGE_NAQUADAH_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE)
        }

        private fun registerSteamMetaTileEntity(machines: Array<SimpleSteamMachineMetaTileEntity?>, startId: Int,
                                                name: String, recipeMap: RecipeMap<*>,
                                                progressBarIndicator: SteamProgressBarIndicator,
                                                texture: ICubeRenderer, isBricked: Boolean)
        {
            machines[0] = MetaTileEntities.registerMetaTileEntity(startId,
                SimpleSteamMachineMetaTileEntity(GTLiteUtility.gtliteId(String.format("%s.bronze", name)),
                    recipeMap, progressBarIndicator, texture, isBricked, false))
            machines[1] = MetaTileEntities.registerMetaTileEntity(startId + 1,
                SimpleSteamMachineMetaTileEntity(GTLiteUtility.gtliteId(String.format("%s.steel", name)),
                    recipeMap, progressBarIndicator, texture, isBricked, true))
        }

    }

}