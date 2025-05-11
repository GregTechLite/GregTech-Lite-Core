package magicbook.gtlitecore.common.metatileentity

import gregtech.api.GTValues
import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.ZPM
import gregtech.api.block.machines.MachineItemBlock
import gregtech.api.capability.impl.PropertyFluidFilter
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.material.Materials
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
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing01
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing02
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityBedrockDrillingRig
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCVDUnit
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCatalyticReformer
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityComponentAssemblyLine
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCosmicRayDetector
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCrystallizationCrucible
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityFusionReactors
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityLaserInducedCVDUnit
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityMiningDroneAirport
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityNanoscaleFabricator
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityPlasmaEnhancedCVDUnit
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntitySonicator
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
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntityLargeNaquadahReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntityLargeRocketEngine
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntityNuclearReactor
import magicbook.gtlitecore.common.metatileentity.multiblock.generator.MetaTileEntitySteamEngine
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
import net.minecraft.creativetab.CreativeTabs
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import java.util.*

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteMetaTileEntities
{

    companion object
    {

        // ======================================= GREGTECH METATILEENTITIES ID LIST =======================================

        // - Gregtech (Vanilla)                           1     - 1999
        // - Gregicality (Multiblocks & Science)          2000  - 3999
        // - Integration Modules of GregTech              4000  - 4499
        // - Gregic Addition CEu                          4500  - 8499
        // - GregTech Food Option                         8500  - 8999
        // - HtmlTech                                     9000  - 9499
        // - PCM's Ore Addon                              9500  - 9999
        // - GCM                                          10000 - 10099
        // - MechTech                                     10100 - 10499
        // - MultiblockTweaker                            10500 - 10999
        // - Gregtech Lite Core (THIS)                    14000 - 20000
        // - Integration Modules of Gregtech Lite Core    20001 - 25000
        // - CraftTweaker (MultiblockTweaker)             32000 - 32767

        // =================================================================================================================

        // Single machines.
        val POLISHER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val SLICER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val TOOL_CASTER = arrayOfNulls<SimpleMachineMetaTileEntity>(5)
        val LOOM = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val LAMINATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val CHEMICAL_DEHYDRATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val STEAM_VULCANIZING_PRESS = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val VULCANIZING_PRESS = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val STEAM_VACUUM_CHAMBER = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val VACUUM_CHAMBER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val STEAM_SAP_COLLECTOR = arrayOfNulls<PseudoMultiSteamMachineMetaTileEntity>(2)
        val SAP_COLLECTOR = arrayOfNulls<PseudoMultiMachineMetaTileEntity>(5)
        val GREENHOUSE = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val BIO_REACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val STEAM_ROASTER = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val ROASTER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val BURNER_REACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val BATH_CONDENSER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val CRYOGENIC_REACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val MASS_FABRICATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val REPLICATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val FOOD_PROCESSOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val MULTICOOKER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val MOB_EXTRACTOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val BIO_SIMULATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(6)
        val ROCKET_ENGINE = arrayOfNulls<SimpleGeneratorMetaTileEntity>(3)
        val NAQUADAH_REACTOR = arrayOfNulls<SimpleGeneratorMetaTileEntity>(4)

        lateinit var IRON_DRUM: MetaTileEntityDrum
        lateinit var COPPER_DRUM: MetaTileEntityDrum
        lateinit var LEAD_DRUM: MetaTileEntityDrum
        lateinit var CHROME_DRUM: MetaTileEntityDrum
        lateinit var TUNGSTEN_DRUM: MetaTileEntityDrum
        lateinit var IRIDIUM_DRUM: MetaTileEntityDrum
        // TODO More higher drum like Naquadah, Duranium and Neutronium?
        lateinit var PE_CAN: MetaTileEntityPlasticCan
        lateinit var PTFE_CAN: MetaTileEntityPlasticCan
        lateinit var PBI_CAN: MetaTileEntityPlasticCan

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

        @JvmField
        val BUFFER = arrayOfNulls<MetaTileEntityBuffer>(3)

        @JvmField
        val ENERGY_HATCH_4A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(4)
        @JvmField
        val DYNAMO_HATCH_4A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(4)
        @JvmField
        val ENERGY_HATCH_16A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)
        @JvmField
        val DYNAMO_HATCH_16A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)
        @JvmField
        val SUBSTATION_ENERGY_HATCH_64A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)
        @JvmField
        val SUBSTATION_DYNAMO_HATCH_64A = arrayOfNulls<MetaTileEntityAdvancedEnergyHatch>(5)

        @JvmField
        val QUADRUPLE_FLUID_IMPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)
        @JvmField
        val QUADRUPLE_FLUID_EXPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)
        @JvmField
        val NONUPLE_FLUID_IMPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)
        @JvmField
        val NONUPLE_FLUID_EXPORT_HATCH = arrayOfNulls<MetaTileEntityAdvancedMultiFluidHatch>(4)

        @JvmField
        val LASER_INPUT_HATCH_16384 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_OUTPUT_HATCH_16384 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_INPUT_HATCH_65536 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_OUTPUT_HATCH_65536 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_INPUT_HATCH_262144 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_OUTPUT_HATCH_262144 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_INPUT_HATCH_1048576 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_OUTPUT_HATCH_1048576 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_INPUT_HATCH_4194304 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_OUTPUT_HATCH_4194304 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_INPUT_HATCH_16777216 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV
        @JvmField
        val LASER_OUTPUT_HATCH_16777216 = arrayOfNulls<MetaTileEntityAdvancedLaserHatch>(10) // IV-OpV

        // Multiblock machines.
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
            MachineItemBlock.addCreativeTab(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
        }

        @JvmStatic
        fun init()
        {
            // 14001-14015: Polisher
            MetaTileEntities.registerSimpleMetaTileEntity(POLISHER, 14003, // 14001-14002 for Steam Machines.
                "polisher", GTLiteRecipeMaps.POLISHER_RECIPES,
                GTLiteTextures.POLISHER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.hvCappedTankSizeFunction)

            // 14016-14030: Slicer
            MetaTileEntities.registerSimpleMetaTileEntity(SLICER, 14018, // 14016-14017 for Steam Machines.
                "slicer", GTLiteRecipeMaps.SLICER_RECIPES,
                GTLiteTextures.SLICER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.hvCappedTankSizeFunction)

            // 14031-14045: Tool Caster
            TOOL_CASTER[0] = MetaTileEntities.registerMetaTileEntity(14033,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.lv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.LV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[1] = MetaTileEntities.registerMetaTileEntity(14034,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.mv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.MV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[2] = MetaTileEntities.registerMetaTileEntity(14035,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.hv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.HV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[3] = MetaTileEntities.registerMetaTileEntity(14036,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.ev"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.EV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[4] = MetaTileEntities.registerMetaTileEntity(14037,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.iv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.IV, true, GTUtility.defaultTankSizeFunction))

            // 14046-14060: Loom
            MetaTileEntities.registerSimpleMetaTileEntity(LOOM, 14048, // 14046-14047 for Steam Machines.
                "loom", GTLiteRecipeMaps.LOOM_RECIPES,
                GTLiteTextures.LOOM_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14061-14075: Laminator
            MetaTileEntities.registerSimpleMetaTileEntity(LAMINATOR, 14063, // 14061-14062 for Steam Machines.
                "laminator", GTLiteRecipeMaps.LAMINATOR_RECIPES,
                GTLiteTextures.LAMINATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 14076-14090: Chemical Dehydrator
            MetaTileEntities.registerSimpleMetaTileEntity(CHEMICAL_DEHYDRATOR, 14078, // 14076-14077 for Steam Machines.
                "chemical_dehydrator", GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES,
                GTLiteTextures.CHEMICAL_DEHYDRATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 14091-14105: Vulcanizing Press
            registerSteamMetaTileEntity(STEAM_VULCANIZING_PRESS, 14091,
                "vulcanizing_press", GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                SteamProgressBarIndicators.ARROW_MULTIPLE,
                GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true)

            MetaTileEntities.registerSimpleMetaTileEntity(VULCANIZING_PRESS, 14093,
                "vulcanizing_press", GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14106-14120: Vacuum Chamber
            registerSteamMetaTileEntity(STEAM_VACUUM_CHAMBER, 14106,
                "vacuum_chamber", GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                SteamProgressBarIndicators.COMPRESS,
                Textures.GAS_COLLECTOR_OVERLAY, false)

            MetaTileEntities.registerSimpleMetaTileEntity(VACUUM_CHAMBER, 14108,
                "vacuum_chamber", GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                Textures.GAS_COLLECTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14121-14135: Sap Collector
            STEAM_SAP_COLLECTOR[0] = MetaTileEntities.registerMetaTileEntity(14121,
                MetaTileEntitySteamSapCollector(GTLiteUtility.gtliteId("sap_collector.bronze"), false))

            STEAM_SAP_COLLECTOR[1] = MetaTileEntities.registerMetaTileEntity(14122,
                MetaTileEntitySteamSapCollector(GTLiteUtility.gtliteId("sap_collector.steel"), true))

            SAP_COLLECTOR[0] = MetaTileEntities.registerMetaTileEntity(14123,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.lv"), GTValues.LV))

            SAP_COLLECTOR[1] = MetaTileEntities.registerMetaTileEntity(14124,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.mv"), GTValues.MV))

            SAP_COLLECTOR[2] = MetaTileEntities.registerMetaTileEntity(14125,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.hv"), GTValues.HV))

            SAP_COLLECTOR[3] = MetaTileEntities.registerMetaTileEntity(14126,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.ev"), GTValues.EV))

            SAP_COLLECTOR[4] = MetaTileEntities.registerMetaTileEntity(14127,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.iv"), GTValues.IV))

            // 14136-14150: Greenhouse
            MetaTileEntities.registerSimpleMetaTileEntity(GREENHOUSE, 14138, // 14136-14137 for Steam Machines.
                "greenhouse", GTLiteRecipeMaps.GREENHOUSE_RECIPES,
                Textures.FERMENTER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 14151-14165: Bio Reactor
            MetaTileEntities.registerSimpleMetaTileEntity(BIO_REACTOR, 14153, // 14151-14152 for Steam Machines.
                "bio_reactor", GTLiteRecipeMaps.BIO_REACTOR_RECIPES,
                GTLiteTextures.BIO_REACTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.steamGeneratorTankSizeFunction)

            // 14166-14180: Roaster
            registerSteamMetaTileEntity(STEAM_ROASTER, 14166,
                "roaster", GTLiteRecipeMaps.ROASTER_RECIPES,
                SteamProgressBarIndicators.ARROW,
                GTLiteTextures.ROASTER_OVERLAY, true)

            MetaTileEntities.registerSimpleMetaTileEntity(ROASTER, 14168,
                "roaster", GTLiteRecipeMaps.ROASTER_RECIPES,
                GTLiteTextures.ROASTER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 14181-14195: Burner Reactor
            MetaTileEntities.registerSimpleMetaTileEntity(BURNER_REACTOR, 14183, // 14181-14182 for Steam Machines.
                "burner_reactor", GTLiteRecipeMaps.BURNER_REACTOR_RECIPES,
                GTLiteTextures.BURNER_REACTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 14196-14210: Bath Condenser
            MetaTileEntities.registerSimpleMetaTileEntity(BATH_CONDENSER, 14198, // 14196-14197 for Steam Machines.
                "bath_condenser", GTLiteRecipeMaps.BATH_CONDENSER_RECIPES,
                GTLiteTextures.BATH_CONDENSER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 14211-14225: Cryogenic Reactor
            MetaTileEntities.registerSimpleMetaTileEntity(CRYOGENIC_REACTOR, 14213, // 14211-14212 for Steam Machines.
                "cryogenic_reactor", GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES,
                GTLiteTextures.CRYOGENIC_REACTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 14226-14240: Mass Fabricator
            MetaTileEntities.registerSimpleMetaTileEntity(MASS_FABRICATOR, 14228, // 14226-14227 for Steam Machines.
                "mass_fabricator", RecipeMaps.MASS_FABRICATOR_RECIPES,
                Textures.MASS_FABRICATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 14241-14255: Replicator
            MetaTileEntities.registerSimpleMetaTileEntity(REPLICATOR, 14243, // 14241-14242 for Steam Machines.
                "replicator", RecipeMaps.REPLICATOR_RECIPES,
                Textures.REPLICATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 14256-14270: Food Processor
            MetaTileEntities.registerSimpleMetaTileEntity(FOOD_PROCESSOR, 14258, // 14256-14257 for Steam Machines.
                "food_processor", GTLiteRecipeMaps.FOOD_PROCESSOR_RECIPES,
                GTLiteTextures.FOOD_PROCESSOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14271-14285: Multicooker
            MetaTileEntities.registerSimpleMetaTileEntity(MULTICOOKER, 14273, // 14271-14272 for Steam Machines.
                "multicooker", GTLiteRecipeMaps.MULTICOOKER_RECIPES,
                GTLiteTextures.MULTICOOKER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14286-14300: Mob Extractor
            for (i in 1 .. GTValues.OpV)
            {
                val voltageName = GTValues.VN[i].lowercase(Locale.getDefault())
                MOB_EXTRACTOR[i] = MetaTileEntities.registerMetaTileEntity(14288 + i, // 14286-14287 for Steam Machines.
                    MetaTileEntityMobExtractor(GTLiteUtility.gtliteId("mob_extractor.$voltageName"), GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES,
                        GTLiteTextures.MOB_EXTRACTOR_OVERLAY, i, false, GTUtility.largeTankSizeFunction))
            }

            // 14301-14315: Bio Simulator
            MetaTileEntities.registerSimpleMetaTileEntity(BIO_SIMULATOR, 14303, // 14301-14302 for Steam Machines.
                "bio_simulator", GTLiteRecipeMaps.BIO_SIMULATOR_RECIPES,
                GTLiteTextures.BIO_SIMULATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

            // 14316-14330: Rocket Engine
            ROCKET_ENGINE[0] = MetaTileEntities.registerMetaTileEntity(14320, // 14316-14317 for Steam Machines, 14318-14319 for LV-MV Machines.
                SimpleGeneratorMetaTileEntity(GTLiteUtility.gtliteId("rocket_engine.hv"), GTLiteRecipeMaps.ROCKET_ENGINE_FUELS,
                    GTLiteTextures.ROCKET_ENGINE_OVERLAY, HV, GTUtility.genericGeneratorTankSizeFunction))

            ROCKET_ENGINE[1] = MetaTileEntities.registerMetaTileEntity(14321,
                SimpleGeneratorMetaTileEntity(GTLiteUtility.gtliteId("rocket_engine.ev"), GTLiteRecipeMaps.ROCKET_ENGINE_FUELS,
                    GTLiteTextures.ROCKET_ENGINE_OVERLAY, EV, GTUtility.genericGeneratorTankSizeFunction))

            ROCKET_ENGINE[2] = MetaTileEntities.registerMetaTileEntity(14322,
                SimpleGeneratorMetaTileEntity(GTLiteUtility.gtliteId("rocket_engine.iv"), GTLiteRecipeMaps.ROCKET_ENGINE_FUELS,
                    GTLiteTextures.ROCKET_ENGINE_OVERLAY, IV, GTUtility.genericGeneratorTankSizeFunction))

            // 14331-14345: Naquadah Reactor
            NAQUADAH_REACTOR[0] = MetaTileEntities.registerMetaTileEntity(14337, // 14331-14332 for Steam Machines, 14333-14336 for LV-EV Machines.
                SimpleGeneratorMetaTileEntity(GTLiteUtility.gtliteId("naquadah_reactor.iv"), GTLiteRecipeMaps.NAQUADAH_REACTOR_FUELS,
                    GTLiteTextures.NAQUADAH_REACTOR_OVERLAY, IV, GTUtility.genericGeneratorTankSizeFunction))

            NAQUADAH_REACTOR[1] = MetaTileEntities.registerMetaTileEntity(14338,
                SimpleGeneratorMetaTileEntity(GTLiteUtility.gtliteId("naquadah_reactor.luv"), GTLiteRecipeMaps.NAQUADAH_REACTOR_FUELS,
                    GTLiteTextures.NAQUADAH_REACTOR_OVERLAY, LuV, GTUtility.genericGeneratorTankSizeFunction))

            NAQUADAH_REACTOR[2] = MetaTileEntities.registerMetaTileEntity(14339,
                SimpleGeneratorMetaTileEntity(GTLiteUtility.gtliteId("naquadah_reactor.zpm"), GTLiteRecipeMaps.NAQUADAH_REACTOR_FUELS,
                    GTLiteTextures.NAQUADAH_REACTOR_OVERLAY, ZPM, GTUtility.genericGeneratorTankSizeFunction))

            NAQUADAH_REACTOR[3] = MetaTileEntities.registerMetaTileEntity(14340,
                SimpleGeneratorMetaTileEntity(GTLiteUtility.gtliteId("naquadah_reactor.uv"), GTLiteRecipeMaps.NAQUADAH_REACTOR_FUELS,
                    GTLiteTextures.NAQUADAH_REACTOR_OVERLAY, UV, GTUtility.genericGeneratorTankSizeFunction))

            // ...

            // 15001-15050: Drums and Crates.
            IRON_DRUM = MetaTileEntities.registerMetaTileEntity(15001,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.iron"),
                    PropertyFluidFilter(1811, true, true, false, false),
                    false, Materials.Iron.materialRGB, 12000))

            COPPER_DRUM = MetaTileEntities.registerMetaTileEntity(15002,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.copper"), Materials.Copper, 16000))

            LEAD_DRUM = MetaTileEntities.registerMetaTileEntity(15003,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.lead"), Materials.Lead, 24000))

            CHROME_DRUM = MetaTileEntities.registerMetaTileEntity(15004,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.chrome"), Materials.Chrome, 96000))

            TUNGSTEN_DRUM = MetaTileEntities.registerMetaTileEntity(15005,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.tungsten"), Materials.Tungsten, 768000))

            IRIDIUM_DRUM = MetaTileEntities.registerMetaTileEntity(15006,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.iridium"), Materials.Iridium, 1536000))

            // ...

            PE_CAN = MetaTileEntities.registerMetaTileEntity(15015,
                MetaTileEntityPlasticCan(GTLiteUtility.gtliteId("plastic_can.polyethylene"), Materials.Polyethylene, 64000))

            PTFE_CAN = MetaTileEntities.registerMetaTileEntity(15016,
                MetaTileEntityPlasticCan(GTLiteUtility.gtliteId("plastic_can.polytetrafluoroethylene"), Materials.Polytetrafluoroethylene, 128000))

            PBI_CAN = MetaTileEntities.registerMetaTileEntity(15017,
                MetaTileEntityPlasticCan(GTLiteUtility.gtliteId("plastic_can.polybenzimidazole"), Materials.Polybenzimidazole, 256000))

            // ...
            IRON_CRATE = MetaTileEntities.registerMetaTileEntity(15021,
                MetaTileEntityCrate(GTLiteUtility.gtliteId("crate.iron"), Materials.Iron, 45, 9))

            COPPER_CRATE = MetaTileEntities.registerMetaTileEntity(15022,
                MetaTileEntityCrate(GTLiteUtility.gtliteId("crate.copper"), Materials.Copper, 36, 9))

            SILVER_CRATE = MetaTileEntities.registerMetaTileEntity(15023,
                MetaTileEntityCrate(GTLiteUtility.gtliteId("crate.silver"), Materials.Silver, 63, 9))

            GOLD_CRATE = MetaTileEntities.registerMetaTileEntity(15024,
                MetaTileEntityCrate(GTLiteUtility.gtliteId("crate.gold"), Materials.Gold, 81, 9))

            DIAMOND_CRATE = MetaTileEntities.registerMetaTileEntity(15025,
                MetaTileEntityCrate(GTLiteUtility.gtliteId("crate.diamond"), Materials.Diamond, 100, 10))

            // ...

            // 15051-15060: I/O hatch proxies.
            INVENTORY_BRIDGE = MetaTileEntities.registerMetaTileEntity(15051,
                MetaTileEntityBridge(GTLiteUtility.gtliteId("bridge.inventory"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_BRIDGE, Materials.Steel))

            TANK_BRIDGE = MetaTileEntities.registerMetaTileEntity(15052,
                MetaTileEntityBridge(GTLiteUtility.gtliteId("bridge.tank"),
                    { c -> c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.TANK_BRIDGE, Materials.Steel))

            INVENTORY_TANK_BRIDGE = MetaTileEntities.registerMetaTileEntity(15053,
                MetaTileEntityBridge(GTLiteUtility.gtliteId("bridge.inventory_tank"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_TANK_BRIDGE, Materials.Steel))

            UNIVERSAL_BRIDGE = MetaTileEntities.registerMetaTileEntity(15054,
                MetaTileEntityBridge(GTLiteUtility.gtliteId("bridge.universal"),
                    { true },
                    GTLiteTextures.UNIVERSAL_BRIDGE, Materials.Aluminium))

            INVENTORY_EXTENDER = MetaTileEntities.registerMetaTileEntity(15055,
                MetaTileEntityExtender(GTLiteUtility.gtliteId("extender.inventory"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_EXTENDER, Materials.Steel))

            TANK_EXTENDER = MetaTileEntities.registerMetaTileEntity(15056,
                MetaTileEntityExtender(GTLiteUtility.gtliteId("extender.tank"),
                    { c -> c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.TANK_EXTENDER, Materials.Steel))

            INVENTORY_TANK_EXTENDER = MetaTileEntities.registerMetaTileEntity(15057,
                MetaTileEntityExtender(GTLiteUtility.gtliteId("extender.inventory_tank"),
                    { c -> c == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || c == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY },
                    GTLiteTextures.INVENTORY_TANK_EXTENDER, Materials.Steel))

            UNIVERSAL_EXTENDER = MetaTileEntities.registerMetaTileEntity(15058,
                MetaTileEntityExtender(GTLiteUtility.gtliteId("extender.universal"),
                    { true },
                    GTLiteTextures.UNIVERSAL_EXTENDER, Materials.Aluminium))

            // 15061-15075: Advanced buffers.
            BUFFER[0] = MetaTileEntities.registerMetaTileEntity(15061,
                MetaTileEntityBuffer(GTLiteUtility.gtliteId("buffer.ev"), GTValues.EV))
            BUFFER[1] = MetaTileEntities.registerMetaTileEntity(15062,
                MetaTileEntityBuffer(GTLiteUtility.gtliteId("buffer.iv"), GTValues.IV))

            // ...

            // 16001-17000: Energy/Dynamo Hatches.

            // 16001-16004: LV-HV 4A Energy Hatches.
            ENERGY_HATCH_4A[0] = MetaTileEntities.registerMetaTileEntity(16001,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_4a.ulv"), GTValues.ULV, 4, false))
            ENERGY_HATCH_4A[1] = MetaTileEntities.registerMetaTileEntity(16002,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_4a.lv"), GTValues.LV, 4, false))
            ENERGY_HATCH_4A[2] = MetaTileEntities.registerMetaTileEntity(16003,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_4a.mv"), GTValues.MV, 4, false))
            ENERGY_HATCH_4A[3] = MetaTileEntities.registerMetaTileEntity(16004,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_4a.hv"), GTValues.HV, 4, false))

            // 16005-16008: LV-HV 4A Dynamo Hatches.
            DYNAMO_HATCH_4A[0] = MetaTileEntities.registerMetaTileEntity(16005,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_4a.ulv"), GTValues.ULV, 4, true))
            DYNAMO_HATCH_4A[1] = MetaTileEntities.registerMetaTileEntity(16006,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_4a.lv"), GTValues.LV, 4, true))
            DYNAMO_HATCH_4A[2] = MetaTileEntities.registerMetaTileEntity(16007,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_4a.mv"), GTValues.MV, 4, true))
            DYNAMO_HATCH_4A[3] = MetaTileEntities.registerMetaTileEntity(16008,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_4a.hv"), GTValues.HV, 4, true))

            // 16009-16013: LV-EV 16A Energy Hatches.
            ENERGY_HATCH_16A[0] = MetaTileEntities.registerMetaTileEntity(16009,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_16a.ulv"), GTValues.ULV, 16, false))
            ENERGY_HATCH_16A[1] = MetaTileEntities.registerMetaTileEntity(16010,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_16a.lv"), GTValues.LV, 16, false))
            ENERGY_HATCH_16A[2] = MetaTileEntities.registerMetaTileEntity(16011,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_16a.mv"), GTValues.MV, 16, false))
            ENERGY_HATCH_16A[3] = MetaTileEntities.registerMetaTileEntity(16012,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_16a.hv"), GTValues.HV, 16, false))
            ENERGY_HATCH_16A[4] = MetaTileEntities.registerMetaTileEntity(16013,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.input_16a.ev"), GTValues.EV, 16, false))

            // 16014-16018: LV-EV 16A Dynamo Hatches.
            DYNAMO_HATCH_16A[0] = MetaTileEntities.registerMetaTileEntity(16014,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_16a.ulv"), GTValues.ULV, 16, true))
            DYNAMO_HATCH_16A[1] = MetaTileEntities.registerMetaTileEntity(16015,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_16a.lv"), GTValues.LV, 16, true))
            DYNAMO_HATCH_16A[2] = MetaTileEntities.registerMetaTileEntity(16016,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_16a.mv"), GTValues.MV, 16, true))
            DYNAMO_HATCH_16A[3] = MetaTileEntities.registerMetaTileEntity(16017,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_16a.hv"), GTValues.HV, 16, true))
            DYNAMO_HATCH_16A[4] = MetaTileEntities.registerMetaTileEntity(16018,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("energy_hatch.output_16a.ev"), GTValues.EV, 16, true))

            // 16019-16023 : LV-EV 64A Substation Energy Hatches.
            SUBSTATION_ENERGY_HATCH_64A[0] = MetaTileEntities.registerMetaTileEntity(16019,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.input_64a.ulv"), GTValues.ULV, 64, false))
            SUBSTATION_ENERGY_HATCH_64A[1] = MetaTileEntities.registerMetaTileEntity(16020,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.input_64a.lv"), GTValues.LV, 64, false))
            SUBSTATION_ENERGY_HATCH_64A[2] = MetaTileEntities.registerMetaTileEntity(16021,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.input_64a.mv"), GTValues.MV, 64, false))
            SUBSTATION_ENERGY_HATCH_64A[3] = MetaTileEntities.registerMetaTileEntity(16022,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.input_64a.hv"), GTValues.HV, 64, false))
            SUBSTATION_ENERGY_HATCH_64A[4] = MetaTileEntities.registerMetaTileEntity(16023,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.input_64a.ev"), GTValues.EV, 64, false))

            // 16024-16028: LV-EV 64A Substation Dynamo Hatches.
            SUBSTATION_DYNAMO_HATCH_64A[0] = MetaTileEntities.registerMetaTileEntity(16024,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.output_64a.ulv"), GTValues.ULV, 64, true))
            SUBSTATION_DYNAMO_HATCH_64A[1] = MetaTileEntities.registerMetaTileEntity(16025,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.output_64a.lv"), GTValues.LV, 64, true))
            SUBSTATION_DYNAMO_HATCH_64A[2] = MetaTileEntities.registerMetaTileEntity(16026,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.output_64a.mv"), GTValues.MV, 64, true))
            SUBSTATION_DYNAMO_HATCH_64A[3] = MetaTileEntities.registerMetaTileEntity(16027,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.output_64a.hv"), GTValues.HV, 64, true))
            SUBSTATION_DYNAMO_HATCH_64A[4] = MetaTileEntities.registerMetaTileEntity(16028,
                MetaTileEntityAdvancedEnergyHatch(GTLiteUtility.gtliteId("substation_hatch.output_64a.ev"), GTValues.EV, 64, true))

            // 16031-16140: IV-OpV Higher amperage laser hatches.
            for (i in 0 until 9)
            {
                val j = i + GTValues.IV
                val voltageName = GTValues.VN[j].lowercase(Locale.getDefault())
                LASER_INPUT_HATCH_16384[i] = MetaTileEntities.registerMetaTileEntity(16031 + i,
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.target_16384a.$voltageName"), j, 16384, false))
                LASER_OUTPUT_HATCH_16384[i] = MetaTileEntities.registerMetaTileEntity(16040 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.source_16384a.$voltageName"), j, 16384, true))
                LASER_INPUT_HATCH_65536[i] = MetaTileEntities.registerMetaTileEntity(16049 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.target_65536a.$voltageName"), j, 65536, false))
                LASER_OUTPUT_HATCH_65536[i] = MetaTileEntities.registerMetaTileEntity(16058 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.source_65536a.$voltageName"), j, 65536, true))
                LASER_INPUT_HATCH_262144[i] = MetaTileEntities.registerMetaTileEntity(16067 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.target_262144a.$voltageName"), j, 262144, false))
                LASER_OUTPUT_HATCH_262144[i] = MetaTileEntities.registerMetaTileEntity(16076 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.source_262144a.$voltageName"), j, 262144, true))
                LASER_INPUT_HATCH_1048576[i] = MetaTileEntities.registerMetaTileEntity(16085 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.target_1048576a.$voltageName"), j, 1048576, false))
                LASER_OUTPUT_HATCH_1048576[i] = MetaTileEntities.registerMetaTileEntity(16094 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.source_1048576a.$voltageName"), j, 1048576, true))
                LASER_INPUT_HATCH_4194304[i] = MetaTileEntities.registerMetaTileEntity(16103 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.target_4194304a.$voltageName"), j, 4194304, false))
                LASER_OUTPUT_HATCH_4194304[i] = MetaTileEntities.registerMetaTileEntity(16112 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.source_4194304a.$voltageName"), j, 4194304, true))
                LASER_INPUT_HATCH_16777216[i] = MetaTileEntities.registerMetaTileEntity(16121 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.target_16777216a.$voltageName"), j, 16777216, false))
                LASER_OUTPUT_HATCH_16777216[i] = MetaTileEntities.registerMetaTileEntity(16130 + i, // +9
                    MetaTileEntityAdvancedLaserHatch(GTLiteUtility.gtliteId("laser_hatch.source_16777216a.$voltageName"), j, 16777216, true))
            }

            // 17001-17100: Item/Fluid Hatches.
            QUADRUPLE_FLUID_IMPORT_HATCH[0] = MetaTileEntities.registerMetaTileEntity(17001,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_4x.ulv"), GTValues.ULV, 4, false))
            QUADRUPLE_FLUID_IMPORT_HATCH[1] = MetaTileEntities.registerMetaTileEntity(17002,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_4x.lv"), GTValues.LV, 4, false))
            QUADRUPLE_FLUID_IMPORT_HATCH[2] = MetaTileEntities.registerMetaTileEntity(17003,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_4x.mv"), GTValues.MV, 4, false))
            QUADRUPLE_FLUID_IMPORT_HATCH[3] = MetaTileEntities.registerMetaTileEntity(17004,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_4x.hv"), GTValues.HV, 4, false))

            QUADRUPLE_FLUID_EXPORT_HATCH[0] = MetaTileEntities.registerMetaTileEntity(17005,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_4x.ulv"), GTValues.ULV, 4, true))
            QUADRUPLE_FLUID_EXPORT_HATCH[1] = MetaTileEntities.registerMetaTileEntity(17006,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_4x.lv"), GTValues.LV, 4, true))
            QUADRUPLE_FLUID_EXPORT_HATCH[2] = MetaTileEntities.registerMetaTileEntity(17007,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_4x.mv"), GTValues.MV, 4, true))
            QUADRUPLE_FLUID_EXPORT_HATCH[3] = MetaTileEntities.registerMetaTileEntity(17008,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_4x.hv"), GTValues.HV, 4, true))

            NONUPLE_FLUID_IMPORT_HATCH[0] = MetaTileEntities.registerMetaTileEntity(17009,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_9x.ulv"), GTValues.ULV, 9, false))
            NONUPLE_FLUID_IMPORT_HATCH[1] = MetaTileEntities.registerMetaTileEntity(17010,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_9x.lv"), GTValues.LV, 9, false))
            NONUPLE_FLUID_IMPORT_HATCH[2] = MetaTileEntities.registerMetaTileEntity(17011,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_9x.mv"), GTValues.MV, 9, false))
            NONUPLE_FLUID_IMPORT_HATCH[3] = MetaTileEntities.registerMetaTileEntity(17012,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.import_9x.hv"), GTValues.HV, 9, false))

            NONUPLE_FLUID_EXPORT_HATCH[0] = MetaTileEntities.registerMetaTileEntity(17013,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_9x.ulv"), GTValues.ULV, 9, true))
            NONUPLE_FLUID_EXPORT_HATCH[1] = MetaTileEntities.registerMetaTileEntity(17014,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_9x.lv"), GTValues.LV, 9, true))
            NONUPLE_FLUID_EXPORT_HATCH[2] = MetaTileEntities.registerMetaTileEntity(17015,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_9x.mv"), GTValues.MV, 9, true))
            NONUPLE_FLUID_EXPORT_HATCH[3] = MetaTileEntities.registerMetaTileEntity(17016,
                MetaTileEntityAdvancedMultiFluidHatch(GTLiteUtility.gtliteId("fluid_hatch.export_9x.hv"), GTValues.HV, 9, true))

            // 18001-20000 Multiblocks
            COAGULATION_TANK = MetaTileEntities.registerMetaTileEntity(18001,
                MetaTileEntityCoagulationTank(GTLiteUtility.gtliteId("coagulation_tank")))

            STEAM_COMPRESSOR = MetaTileEntities.registerMetaTileEntity(18002,
                MetaTileEntitySteamCompressor(GTLiteUtility.gtliteId("steam_compressor")))

            STEAM_ALLOY_SMELTER = MetaTileEntities.registerMetaTileEntity(18003,
                MetaTileEntitySteamAlloySmelter(GTLiteUtility.gtliteId("steam_alloy_smelter")))

            STEAM_ENGINE = MetaTileEntities.registerMetaTileEntity(18004,
                MetaTileEntitySteamEngine(GTLiteUtility.gtliteId("steam_engine")))

            INDUSTRIAL_PRIMITIVE_BLAST_FURNACE = MetaTileEntities.registerMetaTileEntity(18005,
                MetaTileEntityIndustrialPrimitiveBlastFurnace(GTLiteUtility.gtliteId("industrial_primitive_blast_furnace")))

            MINING_DRONE_AIRPORT = MetaTileEntities.registerMetaTileEntity(18006,
                MetaTileEntityMiningDroneAirport(GTLiteUtility.gtliteId("mining_drone_airport")))

            // 18007 ...

            NUCLEAR_REACTOR = MetaTileEntities.registerMetaTileEntity(18008,
                MetaTileEntityNuclearReactor(GTLiteUtility.gtliteId("nuclear_reactor")))

            HOT_COOLANT_TURBINE = MetaTileEntities.registerMetaTileEntity(18009,
                MetaTileEntityLargeTurbine(GTLiteUtility.gtliteId("large_turbine.hot_coolant"), GTLiteRecipeMaps.HOT_COOLANT_TURBINE_FUELS, EV,
                    MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_TURBINE_CASING),
                    MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX),
                    Textures.STABLE_TITANIUM_CASING, true, Textures.LARGE_GAS_TURBINE_OVERLAY))

            SUPERCRITICAL_FLUID_TURBINE = MetaTileEntities.registerMetaTileEntity(18010,
                MetaTileEntityLargeTurbine(GTLiteUtility.gtliteId("large_turbine.supercritical_fluid"), GTLiteRecipeMaps.SUPERCRITICAL_FLUID_TURBINE_FUELS, LuV,
                    GTLiteMetaBlocks.TURBINE_CASING_01.getState(BlockTurbineCasing01.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_TURBINE),
                    GTLiteMetaBlocks.TURBINE_CASING_02.getState(BlockTurbineCasing02.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_GEARBOX),
                    GTLiteTextures.RHODIUM_PLATED_PALLADIUM_CASING, true, Textures.LARGE_GAS_TURBINE_OVERLAY))

            CATALYTIC_REFORMER = MetaTileEntities.registerMetaTileEntity(18011,
                MetaTileEntityCatalyticReformer(GTLiteUtility.gtliteId("catalytic_reformer")))

            CVD_UNIT = MetaTileEntities.registerMetaTileEntity(18012,
                MetaTileEntityCVDUnit(GTLiteUtility.gtliteId("cvd_unit")))

            CRYSTALLIZATION_CRUCIBLE = MetaTileEntities.registerMetaTileEntity(18013,
                MetaTileEntityCrystallizationCrucible(GTLiteUtility.gtliteId("crystallization_crucible")))

            NANOSCALE_FABRICATOR = MetaTileEntities.registerMetaTileEntity(18014,
                MetaTileEntityNanoscaleFabricator(GTLiteUtility.gtliteId("nanoscale_fabricator")))

            SONICATOR = MetaTileEntities.registerMetaTileEntity(18015,
                MetaTileEntitySonicator(GTLiteUtility.gtliteId("sonicator")))

            LASER_INDUCED_CVD_UNIT = MetaTileEntities.registerMetaTileEntity(18016,
                MetaTileEntityLaserInducedCVDUnit(GTLiteUtility.gtliteId("laser_induced_cvd_unit")))

            BEDROCK_DRILLING_RIG = MetaTileEntities.registerMetaTileEntity(18017,
                MetaTileEntityBedrockDrillingRig(GTLiteUtility.gtliteId("bedrock_drilling_rig")))

            FUSION_REACTOR_MK4 = MetaTileEntities.registerMetaTileEntity(18018,
                MetaTileEntityFusionReactors(GTLiteUtility.gtliteId("fusion_reactor.uhv"), GTValues.UHV))

            FUSION_REACTOR_MK5 = MetaTileEntities.registerMetaTileEntity(18019,
                MetaTileEntityFusionReactors(GTLiteUtility.gtliteId("fusion_reactor.uev"), GTValues.UEV))

            ADVANCED_FUSION_REACTOR = MetaTileEntities.registerMetaTileEntity(18020,
                MetaTileEntityAdvancedFusionReactor(GTLiteUtility.gtliteId("advanced_fusion_reactor")))

            COMPONENT_ASSEMBLY_LINE = MetaTileEntities.registerMetaTileEntity(18021,
                MetaTileEntityComponentAssemblyLine(GTLiteUtility.gtliteId("component_assembly_line")))

            COSMIC_RAY_DETECTOR = MetaTileEntities.registerMetaTileEntity(18022,
                MetaTileEntityCosmicRayDetector(GTLiteUtility.gtliteId("cosmic_ray_detector")))

            STELLAR_FORGE = MetaTileEntities.registerMetaTileEntity(18023,
                MetaTileEntityStellarForge(GTLiteUtility.gtliteId("stellar_forge")))

            PLASMA_ENHANCED_CVD_UNIT = MetaTileEntities.registerMetaTileEntity(18024,
                MetaTileEntityPlasmaEnhancedCVDUnit(GTLiteUtility.gtliteId("plasma_enhanced_cvd_unit")))

            LARGE_FORGE_HAMMER = MetaTileEntities.registerMetaTileEntity(18101,
                MetaTileEntityLargeForgeHammer(GTLiteUtility.gtliteId("large_forge_hammer")))

            LARGE_BENDER = MetaTileEntities.registerMetaTileEntity(18102,
                MetaTileEntityLargeBender(GTLiteUtility.gtliteId("large_bender")))

            LARGE_CUTTER = MetaTileEntities.registerMetaTileEntity(18103,
                MetaTileEntityLargeCutter(GTLiteUtility.gtliteId("large_cutter")))

            LARGE_EXTRUDER = MetaTileEntities.registerMetaTileEntity(18104,
                MetaTileEntityLargeExtruder(GTLiteUtility.gtliteId("large_extruder")))

            LARGE_WIREMILL = MetaTileEntities.registerMetaTileEntity(18105,
                MetaTileEntityLargeWiremill(GTLiteUtility.gtliteId("large_wiremill")))

            LARGE_MIXER = MetaTileEntities.registerMetaTileEntity(18106,
                MetaTileEntityLargeMixer(GTLiteUtility.gtliteId("large_mixer")))

            LARGE_EXTRACTOR = MetaTileEntities.registerMetaTileEntity(18107,
                MetaTileEntityLargeExtractor(GTLiteUtility.gtliteId("large_extractor")))

            LARGE_ASSEMBLER = MetaTileEntities.registerMetaTileEntity(18108,
                MetaTileEntityLargeAssembler(GTLiteUtility.gtliteId("large_assembler")))

            LARGE_LASER_ENGRAVER = MetaTileEntities.registerMetaTileEntity(18109,
                MetaTileEntityLargeLaserEngraver(GTLiteUtility.gtliteId("large_laser_engraver")))

            LARGE_FLUID_SOLIDIFIER = MetaTileEntities.registerMetaTileEntity(18110,
                MetaTileEntityLargeFluidSolidifier(GTLiteUtility.gtliteId("large_fluid_solidifier")))

            LARGE_BREWERY = MetaTileEntities.registerMetaTileEntity(18111,
                MetaTileEntityLargeBrewery(GTLiteUtility.gtliteId("large_brewery")))

            LARGE_AUTOCLAVE = MetaTileEntities.registerMetaTileEntity(18112,
                MetaTileEntityLargeAutoclave(GTLiteUtility.gtliteId("large_autoclave")))

            LARGE_ARC_FURNACE = MetaTileEntities.registerMetaTileEntity(18113,
                MetaTileEntityLargeArcFurnace(GTLiteUtility.gtliteId("large_arc_furnace")))

            LARGE_MACERATOR = MetaTileEntities.registerMetaTileEntity(18114,
                MetaTileEntityLargeMacerator(GTLiteUtility.gtliteId("large_macerator")))

            LARGE_CENTRIFUGE = MetaTileEntities.registerMetaTileEntity(18115,
                MetaTileEntityLargeCentrifuge(GTLiteUtility.gtliteId("large_centrifuge")))

            LARGE_SIFTER = MetaTileEntities.registerMetaTileEntity(18116,
                MetaTileEntityLargeSifter(GTLiteUtility.gtliteId("large_sifter")))

            LARGE_ELECTROLYZER = MetaTileEntities.registerMetaTileEntity(18117,
                MetaTileEntityLargeElectrolyzer(GTLiteUtility.gtliteId("large_electrolyzer")))

            LARGE_ORE_WASHER = MetaTileEntities.registerMetaTileEntity(18118,
                MetaTileEntityLargeOreWasher(GTLiteUtility.gtliteId("large_ore_washer")))

            LARGE_ELECTROMAGNET = MetaTileEntities.registerMetaTileEntity(18119,
                MetaTileEntityLargeElectromagnet(GTLiteUtility.gtliteId("large_electromagnet")))

            LARGE_DISTILLERY = MetaTileEntities.registerMetaTileEntity(18120,
                MetaTileEntityLargeDistillery(GTLiteUtility.gtliteId("large_distillery")))

            LARGE_BIO_REACTOR = MetaTileEntities.registerMetaTileEntity(18121,
                MetaTileEntityLargeBioReactor(GTLiteUtility.gtliteId("large_bio_reactor")))

            LARGE_PACKER = MetaTileEntities.registerMetaTileEntity(18122,
                MetaTileEntityLargePacker(GTLiteUtility.gtliteId("large_packer")))

            LARGE_GAS_COLLECTOR = MetaTileEntities.registerMetaTileEntity(18123,
                MetaTileEntityLargeGasCollector(GTLiteUtility.gtliteId("large_gas_collector")))

            LARGE_ROCK_BREAKER = MetaTileEntities.registerMetaTileEntity(18124,
                MetaTileEntityLargeRockBreaker(GTLiteUtility.gtliteId("large_rock_breaker")))

            LARGE_BURNER_REACTOR = MetaTileEntities.registerMetaTileEntity(18125,
                MetaTileEntityLargeBurnerReactor(GTLiteUtility.gtliteId("large_burner_reactor")))

            LARGE_CRYOGENIC_REACTOR = MetaTileEntities.registerMetaTileEntity(18126,
                MetaTileEntityLargeCryogenicReactor(GTLiteUtility.gtliteId("large_cryogenic_reactor")))

            ELECTRIC_IMPLOSION_COMPRESSOR = MetaTileEntities.registerMetaTileEntity(18127,
                MetaTileEntityElectricImplosionCompressor(GTLiteUtility.gtliteId("electric_implosion_compressor")))

            ALLOY_BLAST_SMELTER = MetaTileEntities.registerMetaTileEntity(18128,
                MetaTileEntityAlloyBlastSmelter(GTLiteUtility.gtliteId("alloy_blast_smelter")))

            VOLCANUS = MetaTileEntities.registerMetaTileEntity(18129,
                MetaTileEntityVolcanus(GTLiteUtility.gtliteId("volcanus")))

            CRYOGENIC_FREEZER = MetaTileEntities.registerMetaTileEntity(18130,
                MetaTileEntityCryogenicFreezer(GTLiteUtility.gtliteId("cryogenic_freezer")))

            CHEMICAL_PLANT = MetaTileEntities.registerMetaTileEntity(18131,
                MetaTileEntityChemicalPlant(GTLiteUtility.gtliteId("chemical_plant")))

            INDUSTRIAL_COKE_OVEN = MetaTileEntities.registerMetaTileEntity(18132,
                MetaTileEntityIndustrialCokeOven(GTLiteUtility.gtliteId("industrial_coke_oven")))

            LARGE_MASS_FABRICATOR = MetaTileEntities.registerMetaTileEntity(18133,
                MetaTileEntityLargeMassFabricator(GTLiteUtility.gtliteId("large_mass_fabricator")))

            LARGE_REPLICATOR = MetaTileEntities.registerMetaTileEntity(18134,
                MetaTileEntityLargeReplicator(GTLiteUtility.gtliteId("large_replicator")))

            CIRCUIT_ASSEMBLY_LINE = MetaTileEntities.registerMetaTileEntity(18135,
                MetaTileEntityCircuitAssemblyLine(GTLiteUtility.gtliteId("circuit_assembly_line")))

            LARGE_FOOD_PROCESSOR = MetaTileEntities.registerMetaTileEntity(18136,
                MetaTileEntityLargeFoodProcessor(GTLiteUtility.gtliteId("large_food_processor")))

            LARGE_ROCKET_ENGINE = MetaTileEntities.registerMetaTileEntity(18137,
                MetaTileEntityLargeRocketEngine(GTLiteUtility.gtliteId("large_rocket_engine")))

            LARGE_NAQUADAH_REACTOR = MetaTileEntities.registerMetaTileEntity(18138,
                MetaTileEntityLargeNaquadahReactor(GTLiteUtility.gtliteId("large_naquadah_reactor")))

            // ADVANCED_ASSEMBLY_LINE

        }

        @JvmStatic
        fun postInit()
        {
            POLISHER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            SLICER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            TOOL_CASTER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LOOM.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LAMINATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            CHEMICAL_DEHYDRATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            STEAM_VULCANIZING_PRESS.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            VULCANIZING_PRESS.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            STEAM_VACUUM_CHAMBER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            VACUUM_CHAMBER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            STEAM_SAP_COLLECTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            SAP_COLLECTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            GREENHOUSE.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            BIO_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            STEAM_ROASTER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            ROASTER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            BURNER_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            BATH_CONDENSER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            CRYOGENIC_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            MASS_FABRICATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            REPLICATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            FOOD_PROCESSOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            MULTICOOKER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            MOB_EXTRACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            BIO_SIMULATOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            ROCKET_ENGINE.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            NAQUADAH_REACTOR.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }

            IRON_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            COPPER_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LEAD_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            CHROME_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            TUNGSTEN_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            IRIDIUM_DRUM.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)

            PE_CAN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            PTFE_CAN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            PBI_CAN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)

            INVENTORY_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            TANK_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            INVENTORY_TANK_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            UNIVERSAL_BRIDGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)

            INVENTORY_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            TANK_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            INVENTORY_TANK_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            UNIVERSAL_EXTENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)

            BUFFER.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }

            ENERGY_HATCH_4A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            DYNAMO_HATCH_4A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            ENERGY_HATCH_16A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            DYNAMO_HATCH_16A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            SUBSTATION_ENERGY_HATCH_64A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            SUBSTATION_DYNAMO_HATCH_64A.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }

            LASER_INPUT_HATCH_16384.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_OUTPUT_HATCH_16384.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_INPUT_HATCH_65536.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_OUTPUT_HATCH_65536.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_INPUT_HATCH_262144.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_OUTPUT_HATCH_262144.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_INPUT_HATCH_1048576.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_OUTPUT_HATCH_1048576.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_INPUT_HATCH_4194304.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_OUTPUT_HATCH_4194304.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_INPUT_HATCH_16777216.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            LASER_OUTPUT_HATCH_16777216.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }

            QUADRUPLE_FLUID_IMPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            QUADRUPLE_FLUID_EXPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            NONUPLE_FLUID_IMPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }
            NONUPLE_FLUID_EXPORT_HATCH.filterNotNull().forEach { it.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs) }

            COAGULATION_TANK.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            STEAM_COMPRESSOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            STEAM_ALLOY_SMELTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            STEAM_ENGINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            MINING_DRONE_AIRPORT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            NUCLEAR_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            HOT_COOLANT_TURBINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            SUPERCRITICAL_FLUID_TURBINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)

            CATALYTIC_REFORMER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            CVD_UNIT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            CRYSTALLIZATION_CRUCIBLE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            NANOSCALE_FABRICATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            SONICATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LASER_INDUCED_CVD_UNIT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            BEDROCK_DRILLING_RIG.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            FUSION_REACTOR_MK4.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            FUSION_REACTOR_MK5.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            ADVANCED_FUSION_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            COMPONENT_ASSEMBLY_LINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            COSMIC_RAY_DETECTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            STELLAR_FORGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            PLASMA_ENHANCED_CVD_UNIT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)

            LARGE_FORGE_HAMMER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_BENDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_CUTTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_EXTRUDER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_WIREMILL.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_MIXER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_EXTRACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_ASSEMBLER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_LASER_ENGRAVER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_FLUID_SOLIDIFIER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_BREWERY.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_AUTOCLAVE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_ARC_FURNACE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_MACERATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_CENTRIFUGE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_SIFTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_ELECTROLYZER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_ORE_WASHER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_ELECTROMAGNET.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_DISTILLERY.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_PACKER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_GAS_COLLECTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_ROCK_BREAKER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_BURNER_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_CRYOGENIC_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            ELECTRIC_IMPLOSION_COMPRESSOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            ALLOY_BLAST_SMELTER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            VOLCANUS.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            CRYOGENIC_FREEZER.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            CHEMICAL_PLANT.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            INDUSTRIAL_COKE_OVEN.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_MASS_FABRICATOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            CIRCUIT_ASSEMBLY_LINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_FOOD_PROCESSOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_ROCKET_ENGINE.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
            LARGE_NAQUADAH_REACTOR.addAdditionalCreativeTabs(GTLiteAPI.TAB_GTLITE_MACHINE as CreativeTabs)
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