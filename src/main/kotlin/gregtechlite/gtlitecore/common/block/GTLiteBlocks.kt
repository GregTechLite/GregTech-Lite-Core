package gregtechlite.gtlitecore.common.block

import com.morphismmc.morphismlib.client.Games
import com.morphismmc.morphismlib.util.Unchecks
import gregtech.api.GregTechAPI
import gregtech.api.block.VariantActiveBlock
import gregtech.api.block.VariantBlock
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.variant.BlockVariantType
import gregtechlite.gtlitecore.api.block.variant.VariantBlockFactory
import gregtechlite.gtlitecore.api.collection.fastObjectHashMapOf
import gregtechlite.gtlitecore.api.collection.immutableMapOf
import gregtechlite.gtlitecore.api.collection.treeMapOf
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.ComponentAssemblyCasing
import gregtechlite.gtlitecore.common.block.variant.Crucible
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.Manipulator
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.NuclearReactorCore
import gregtechlite.gtlitecore.common.block.variant.PrimitiveCasing
import gregtechlite.gtlitecore.common.block.variant.ShieldingCore
import gregtechlite.gtlitecore.common.block.variant.TurbineCasing
import gregtechlite.gtlitecore.common.block.variant.WireCoil
import gregtechlite.gtlitecore.common.block.variant.aerospace.AccelerationTrack
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.component.ConveyorCasing
import gregtechlite.gtlitecore.common.block.variant.component.EmitterCasing
import gregtechlite.gtlitecore.common.block.variant.component.FieldGenCasing
import gregtechlite.gtlitecore.common.block.variant.component.MotorCasing
import gregtechlite.gtlitecore.common.block.variant.component.PistonCasing
import gregtechlite.gtlitecore.common.block.variant.component.ProcessorCasing
import gregtechlite.gtlitecore.common.block.variant.component.PumpCasing
import gregtechlite.gtlitecore.common.block.variant.component.RobotArmCasing
import gregtechlite.gtlitecore.common.block.variant.component.SensorCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCryostat
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionDivertor
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionVacuum
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.block.variant.science.SpacetimeCompressionFieldGenerator
import gregtechlite.gtlitecore.common.block.variant.science.StabilizationFieldGenerator
import gregtechlite.gtlitecore.common.block.variant.science.TimeAccelerationFieldGenerator
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.worldgen.generator.plant.WorldGeneratorBerryManager
import gregtechlite.gtlitecore.common.worldgen.generator.plant.WorldGeneratorCropManager
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeManager
import gregtechlite.gtlitecore.common.worldgen.generator.tree.WorldGeneratorTreeRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockFalling
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockSlab
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.IProperty
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.util.IStringSerializable
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*
import gregtech.api.unification.material.Material as GTMaterial

@Suppress("Deprecation")
object GTLiteBlocks
{
    // @formatter:off

    // Stones and its variant blocks.
    @JvmField
    val STONES = EnumMap<GTLiteStoneVariantBlock.StoneVariant, GTLiteStoneVariantBlock>(
        GTLiteStoneVariantBlock.StoneVariant::class.java)

    // Tree related blocks.
    @JvmField
    val LEAVES = ArrayList<GTLiteLeaveBlock>()
    @JvmField
    val LOGS = ArrayList<GTLiteLogBlock>()
    @JvmField
    val PLANKS = ArrayList<GTLitePlankBlock>()
    @JvmField
    val SAPLINGS = ArrayList<GTLiteSaplingBlock>()

    // Wooden slabs.
    lateinit var WOOD_SLABS: GTLiteWoodSlabBlock
    lateinit var DOUBLE_WOOD_SLABS: GTLiteWoodSlabBlock

    // Wooden stairs.
    lateinit var BANANA_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var ORANGE_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var MANGO_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var APRICOT_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var LEMON_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var LIME_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var OLIVE_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var NUTMEG_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var COCONUT_WOOD_STAIR: GTLiteWoodStairBlock
    lateinit var RAINBOW_WOOD_STAIR: GTLiteWoodStairBlock

    // Wooden fences.
    lateinit var BANANA_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var ORANGE_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var MANGO_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var APRICOT_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var LEMON_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var LIME_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var OLIVE_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var NUTMEG_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var COCONUT_WOOD_FENCE: GTLiteWoodFenceBlock
    lateinit var RAINBOW_WOOD_FENCE: GTLiteWoodFenceBlock

    // Wooden fence gates.
    lateinit var BANANA_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var ORANGE_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var MANGO_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var APRICOT_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var LEMON_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var LIME_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var OLIVE_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var NUTMEG_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var COCONUT_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock
    lateinit var RAINBOW_WOOD_FENCE_GATE: GTLiteWoodFenceGateBlock

    // Miscellaneous blocks.
    lateinit var DUST_BLOCK: Block
    lateinit var DIMENSION_DISPLAY_OVERWORLD: BlockDimensionDisplay
    lateinit var DIMENSION_DISPLAY_NETHER: BlockDimensionDisplay
    lateinit var DIMENSION_DISPLAY_END: BlockDimensionDisplay
    lateinit var NAQUADRIA_CHARGE: BlockNaquadriaCharge
    lateinit var TARANIUM_CHARGE: BlockTaraniumCharge
    lateinit var LEPTONIC_CHARGE: BlockLeptonicCharge
    lateinit var QUANTUM_CHROMODYNAMIC_CHARGE: BlockQuantumChromodynamicCharge

    // GT format material blocks.
    @JvmField
    val SHEETED_FRAMES = hashMapOf<GTMaterial, BlockSheetedFrame>()
    @JvmField
    val SHEETED_FRAME_BLOCKS = arrayListOf<BlockSheetedFrame>()
    @JvmField
    val METAL_WALLS = hashMapOf<GTMaterial, BlockMetalWall>()
    @JvmField
    val METAL_WALL_BLOCKS = arrayListOf<BlockMetalWall>()

    // Meta blocks.
    lateinit var MOTOR_CASING: VariantBlock<MotorCasing>
    lateinit var PISTON_CASING: VariantBlock<PistonCasing>
    lateinit var PUMP_CASING: VariantBlock<PumpCasing>
    lateinit var CONVEYOR_CASING: VariantBlock<ConveyorCasing>
    lateinit var ROBOT_ARM_CASING: VariantBlock<RobotArmCasing>
    lateinit var EMITTER_CASING: VariantBlock<EmitterCasing>
    lateinit var SENSOR_CASING: VariantBlock<SensorCasing>
    lateinit var FIELD_GEN_CASING: VariantBlock<FieldGenCasing>
    lateinit var PROCESSOR_CASING: VariantBlock<ProcessorCasing>

    lateinit var PRIMITIVE_CASING: VariantBlock<PrimitiveCasing>
    lateinit var METAL_CASING_01: VariantBlock<MetalCasing.Enum01>
    lateinit var METAL_CASING_02: VariantBlock<MetalCasing.Enum02>
    lateinit var METAL_CASING_03: VariantBlock<MetalCasing.Enum03>
    lateinit var BOILER_CASING_01: VariantBlock<BoilerCasing>
    lateinit var MULTIBLOCK_CASING_01: VariantBlock<MultiblockCasing>
    lateinit var ACTIVE_UNIQUE_CASING_01: VariantActiveBlock<ActiveUniqueCasing>
    lateinit var TURBINE_CASING_01: VariantBlock<TurbineCasing.Enum01>
    lateinit var TURBINE_CASING_02: VariantBlock<TurbineCasing.Enum02>

    lateinit var FUSION_CASING: VariantActiveBlock<FusionCasing>
    lateinit var FUSION_COIL: VariantActiveBlock<FusionCoil>
    lateinit var FUSION_CRYOSTAT: VariantActiveBlock<FusionCryostat>
    lateinit var FUSION_DIVERTOR: VariantActiveBlock<FusionDivertor>
    lateinit var FUSION_VACUUM: VariantActiveBlock<FusionVacuum>

    lateinit var SCIENCE_CASING_01: VariantBlock<ScienceCasing>
    lateinit var SPACETIME_COMPRESSION_FIELD_GENERATOR: VariantBlock<SpacetimeCompressionFieldGenerator>
    lateinit var TIME_ACCELERATION_FIELD_GENERATOR: VariantBlock<TimeAccelerationFieldGenerator>
    lateinit var STABILIZATION_FIELD_GENERATOR: VariantBlock<StabilizationFieldGenerator>

    lateinit var AEROSPACE_CASING: VariantBlock<AerospaceCasing>
    lateinit var ACCELERATION_TRACK: VariantActiveBlock<AccelerationTrack>
    lateinit var WIRE_COIL: VariantActiveBlock<WireCoil>
    lateinit var COMPONENT_ASSEMBLY_CASING: VariantBlock<ComponentAssemblyCasing>
    lateinit var CRUCIBLE: VariantBlock<Crucible>
    lateinit var NUCLEAR_REACTOR_CORE_01: VariantActiveBlock<NuclearReactorCore.Enum01>
    lateinit var NUCLEAR_REACTOR_CORE_02: VariantActiveBlock<NuclearReactorCore.Enum02>
    lateinit var MANIPULATOR: VariantActiveBlock<Manipulator>
    lateinit var SHIELDING_CORE: VariantActiveBlock<ShieldingCore>
    lateinit var TRANSPARENT_CASING_01: VariantBlock<GlassCasing.Enum01>
    lateinit var TRANSPARENT_CASING_02: VariantBlock<GlassCasing.Enum02>
    lateinit var TRANSPARENT_CASING_03: VariantBlock<GlassCasing.Enum03>

    fun init()
    {
        // Initialized stones and its variant blocks.
        for (variant: GTLiteStoneVariantBlock.StoneVariant in GTLiteStoneVariantBlock.StoneVariant.entries)
            STONES[variant] = GTLiteStoneVariantBlock(variant)

        // Initialized trees world generator features.
        WorldGeneratorTreeManager.init()

        // Initialized tree related blocks.
        for (i in 0..(WorldGeneratorTreeRegistry.generators.size - 1) / 4)
        {
            val leaves = GTLiteLeaveBlock(i)
            leaves.setRegistryName("leaves_$i")
        }
        for (i in 0..(WorldGeneratorTreeRegistry.generators.size - 1) / 4)
        {
            val log = GTLiteLogBlock(i)
            log.setRegistryName("log_$i")
        }
        for (i in 0..(WorldGeneratorTreeRegistry.generators.size - 1) / 8)
        {
            val sapling = GTLiteSaplingBlock(i)
            sapling.setRegistryName("sapling_$i")
        }
        for (i in 0..(WorldGeneratorTreeRegistry.generators.size - 1) / 16)
        {
            val planks = GTLitePlankBlock(i)
            planks.setRegistryName("planks_$i")
        }

        // Initialized crops and berries world generator features.
        WorldGeneratorCropManager.init()
        WorldGeneratorBerryManager.init()

        // Setup tree related blocks to its world generator features.
        WorldGeneratorTreeRegistry.generators.forEach { it.setupBlocks() }

        // Initialized wooden slabs.
        WOOD_SLABS = GTLiteWoodSlabBlock.Half()
        WOOD_SLABS.setRegistryName("wood_slab")
        DOUBLE_WOOD_SLABS = GTLiteWoodSlabBlock.Double()
        DOUBLE_WOOD_SLABS.setRegistryName("double_wood_slab")

        // Initialized wooden stairs.
        BANANA_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(0))
        BANANA_WOOD_STAIR.setRegistryName("wood_stair_banana")
        BANANA_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.banana")

        ORANGE_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(1))
        ORANGE_WOOD_STAIR.setRegistryName("wood_stair_orange")
        ORANGE_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.orange")

        MANGO_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(2))
        MANGO_WOOD_STAIR.setRegistryName("wood_stair_mango")
        MANGO_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.mango")

        APRICOT_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(3))
        APRICOT_WOOD_STAIR.setRegistryName("wood_stair_apricot")
        APRICOT_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.apricot")

        LEMON_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(4))
        LEMON_WOOD_STAIR.setRegistryName("wood_stair_lemon")
        LEMON_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.lemon")

        LIME_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(5))
        LIME_WOOD_STAIR.setRegistryName("wood_stair_lime")
        LIME_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.lime")

        OLIVE_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(6))
        OLIVE_WOOD_STAIR.setRegistryName("wood_stair_olive")
        OLIVE_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.olive")

        NUTMEG_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(7))
        NUTMEG_WOOD_STAIR.setRegistryName("wood_stair_nutmeg")
        NUTMEG_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.nutmeg")

        COCONUT_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(8))
        COCONUT_WOOD_STAIR.setRegistryName("wood_stair_coconut")
        COCONUT_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.coconut")

        RAINBOW_WOOD_STAIR = GTLiteWoodStairBlock(PLANKS[0].getStateFromMeta(9))
        RAINBOW_WOOD_STAIR.setRegistryName("wood_stair_rainbow")
        RAINBOW_WOOD_STAIR.setTranslationKey("gtlitecore.wood_stair.rainbow")

        // Initialized wooden fences.
        BANANA_WOOD_FENCE = GTLiteWoodFenceBlock()
        BANANA_WOOD_FENCE.setRegistryName("wood_fence_banana")
        BANANA_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.banana")

        ORANGE_WOOD_FENCE = GTLiteWoodFenceBlock()
        ORANGE_WOOD_FENCE.setRegistryName("wood_fence_orange")
        ORANGE_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.orange")

        MANGO_WOOD_FENCE = GTLiteWoodFenceBlock()
        MANGO_WOOD_FENCE.setRegistryName("wood_fence_mango")
        MANGO_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.mango")

        APRICOT_WOOD_FENCE = GTLiteWoodFenceBlock()
        APRICOT_WOOD_FENCE.setRegistryName("wood_fence_apricot")
        APRICOT_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.apricot")

        LEMON_WOOD_FENCE = GTLiteWoodFenceBlock()
        LEMON_WOOD_FENCE.setRegistryName("wood_fence_lemon")
        LEMON_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.lemon")

        LIME_WOOD_FENCE = GTLiteWoodFenceBlock()
        LIME_WOOD_FENCE.setRegistryName("wood_fence_lime")
        LIME_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.lime")

        OLIVE_WOOD_FENCE = GTLiteWoodFenceBlock()
        OLIVE_WOOD_FENCE.setRegistryName("wood_fence_olive")
        OLIVE_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.olive")

        NUTMEG_WOOD_FENCE = GTLiteWoodFenceBlock()
        NUTMEG_WOOD_FENCE.setRegistryName("wood_fence_nutmeg")
        NUTMEG_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.nutmeg")

        COCONUT_WOOD_FENCE = GTLiteWoodFenceBlock()
        COCONUT_WOOD_FENCE.setRegistryName("wood_fence_coconut")
        COCONUT_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.coconut")

        RAINBOW_WOOD_FENCE = GTLiteWoodFenceBlock()
        RAINBOW_WOOD_FENCE.setRegistryName("wood_fence_rainbow")
        RAINBOW_WOOD_FENCE.setTranslationKey("gtlitecore.wood_fence.rainbow")

        // Initialized wooden fence gates.
        BANANA_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        BANANA_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_banana")
        BANANA_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.banana")

        ORANGE_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        ORANGE_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_orange")
        ORANGE_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.orange")

        MANGO_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        MANGO_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_mango")
        MANGO_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.mango")

        APRICOT_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        APRICOT_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_apricot")
        APRICOT_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.apricot")

        LEMON_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        LEMON_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_lemon")
        LEMON_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.lemon")

        LIME_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        LIME_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_lime")
        LIME_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.lime")

        OLIVE_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        OLIVE_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_olive")
        OLIVE_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.olive")

        NUTMEG_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        NUTMEG_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_nutmeg")
        NUTMEG_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.nutmeg")

        COCONUT_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        COCONUT_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_coconut")
        COCONUT_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.coconut")

        RAINBOW_WOOD_FENCE_GATE = GTLiteWoodFenceGateBlock()
        RAINBOW_WOOD_FENCE_GATE.setRegistryName("wood_fence_gate_rainbow")
        RAINBOW_WOOD_FENCE_GATE.setTranslationKey("gtlitecore.wood_fence_gate.rainbow")

        // Initialized miscellaneous blocks.
        DUST_BLOCK = BlockFalling(Material.SAND).apply {
            setRegistryName("dust_block")
            setTranslationKey("$MOD_ID.dust_block")
            setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)
            setHardness(0.4F)
            setResistance(0.4F)
            setSoundType(SoundType.SAND)
            setHarvestLevel("shovel", 0)
        }

        DIMENSION_DISPLAY_OVERWORLD = BlockDimensionDisplay("Ow")
        DIMENSION_DISPLAY_OVERWORLD.setRegistryName("dimension_display_overworld")
        DIMENSION_DISPLAY_OVERWORLD.setTranslationKey("gtlitecore.dimension_display.overworld")
        DIMENSION_DISPLAY_OVERWORLD.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)

        DIMENSION_DISPLAY_NETHER = BlockDimensionDisplay("Ne")
        DIMENSION_DISPLAY_NETHER.setRegistryName("dimension_display_nether")
        DIMENSION_DISPLAY_NETHER.setTranslationKey("gtlitecore.dimension_display.nether")
        DIMENSION_DISPLAY_NETHER.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)

        DIMENSION_DISPLAY_END = BlockDimensionDisplay("ED")
        DIMENSION_DISPLAY_END.setRegistryName("dimension_display_end")
        DIMENSION_DISPLAY_END.setTranslationKey("gtlitecore.dimension_display.end")
        DIMENSION_DISPLAY_END.setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)

        NAQUADRIA_CHARGE = BlockNaquadriaCharge()
        NAQUADRIA_CHARGE.setTranslationKey("gtlitecore.naquadria_charge")
        NAQUADRIA_CHARGE.setRegistryName("naquadria_charge")
        NAQUADRIA_CHARGE.setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)

        TARANIUM_CHARGE = BlockTaraniumCharge()
        TARANIUM_CHARGE.setTranslationKey("gtlitecore.taranium_charge")
        TARANIUM_CHARGE.setRegistryName("taranium_charge")
        TARANIUM_CHARGE.setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)

        LEPTONIC_CHARGE = BlockLeptonicCharge()
        LEPTONIC_CHARGE.setTranslationKey("gtlitecore.leptonic_charge")
        LEPTONIC_CHARGE.setRegistryName("leptonic_charge")
        LEPTONIC_CHARGE.setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)

        QUANTUM_CHROMODYNAMIC_CHARGE = BlockQuantumChromodynamicCharge()
        QUANTUM_CHROMODYNAMIC_CHARGE.setTranslationKey("gtlitecore.quantum_chromodynamic_charge")
        QUANTUM_CHROMODYNAMIC_CHARGE.setRegistryName("quantum_chromodynamic_charge")
        QUANTUM_CHROMODYNAMIC_CHARGE.setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)

        // Initialized sheeted frame blocks.
        createGeneratedBlock({ m -> m.hasProperty(PropertyKey.DUST) && m.hasFlag(MaterialFlags.GENERATE_FRAME) },
            this::createSheetedFrameBlock)

        // Initialized metal wall blocks.
        createGeneratedBlock({ m -> m.hasFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_BOLT_SCREW) },
            this::createMetalWallBlock)

        // Initialized meta blocks.
        MOTOR_CASING = simpleBlock("motor_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        PISTON_CASING = simpleBlock("piston_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        PUMP_CASING = simpleBlock("pump_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        CONVEYOR_CASING = simpleBlock("conveyor_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        ROBOT_ARM_CASING = simpleBlock("robot_arm_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        EMITTER_CASING = simpleBlock("emitter_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        SENSOR_CASING = simpleBlock("sensor_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        FIELD_GEN_CASING = simpleBlock("field_gen_casing", BlockVariantType.METAL_CUTOUT_BLOCK)
        PROCESSOR_CASING = simpleBlock("processor_casing", BlockVariantType.METAL_CUTOUT_BLOCK)

        PRIMITIVE_CASING = simpleBlock("primitive_casing", BlockVariantType.METAL_BLOCK)

        METAL_CASING_01 = simpleBlock("metal_casing_01", BlockVariantType.METAL_BLOCK)
        METAL_CASING_02 = simpleBlock("metal_casing_02", BlockVariantType.METAL_BLOCK)
        METAL_CASING_03 = simpleBlock("metal_casing_03", BlockVariantType.METAL_BLOCK)

        BOILER_CASING_01 = simpleBlock("boiler_casing_01", BlockVariantType.METAL_BLOCK)
        MULTIBLOCK_CASING_01 = simpleBlock("multiblock_casing_01", BlockVariantType.METAL_BLOCK)
        ACTIVE_UNIQUE_CASING_01 = simpleBlock("active_unique_casing_01", BlockVariantType.METAL_ACTIVE_BLOCK)
        TURBINE_CASING_01 = simpleBlock("turbine_casing_01", BlockVariantType.METAL_BLOCK)
        TURBINE_CASING_02 = simpleBlock("turbine_casing_02", BlockVariantType.METAL_BLOCK)

        FUSION_CASING = simpleBlock("fusion_casing", BlockVariantType.METAL_ACTIVE_BLOCK)
        FUSION_COIL = simpleBlock("fusion_coil", BlockVariantType.METAL_ACTIVE_BLOCK)
        FUSION_CRYOSTAT = simpleBlock("fusion_cryostat", BlockVariantType.METAL_ACTIVE_BLOCK)
        FUSION_DIVERTOR = simpleBlock("fusion_divertor", BlockVariantType.METAL_ACTIVE_BLOCK)
        FUSION_VACUUM = simpleBlock("fusion_vacuum", BlockVariantType.METAL_ACTIVE_BLOCK)

        SCIENCE_CASING_01 = simpleBlock("science_casing_01", BlockVariantType.METAL_BLOCK)
        SPACETIME_COMPRESSION_FIELD_GENERATOR = simpleBlock("spacetime_compression_field_generator", BlockVariantType.METAL_BLOCK)
        TIME_ACCELERATION_FIELD_GENERATOR = simpleBlock("time_acceleration_field_generator", BlockVariantType.METAL_BLOCK)
        STABILIZATION_FIELD_GENERATOR = simpleBlock("stabilization_field_generator", BlockVariantType.METAL_BLOCK)

        AEROSPACE_CASING = simpleBlock("aerospace_casing", BlockVariantType.METAL_BLOCK)
        ACCELERATION_TRACK = simpleBlock("acceleration_track", BlockVariantType.METAL_ACTIVE_BLOCK)

        WIRE_COIL = simpleBlock("wire_coil", BlockVariantType.WIRE_COIL_BLOCK)
        COMPONENT_ASSEMBLY_CASING = simpleBlock("component_assembly_casing", BlockVariantType.METAL_BLOCK)
        CRUCIBLE = simpleBlock("crucible", BlockVariantType.CRUCIBLE_BLOCK, 4.0f, 8.0f)
        NUCLEAR_REACTOR_CORE_01 = simpleBlock("nuclear_reactor_core_01", BlockVariantType.METAL_ACTIVE_BLOCK, 4.0f, 8.0f)
        NUCLEAR_REACTOR_CORE_02 = simpleBlock("nuclear_reactor_core_02", BlockVariantType.METAL_ACTIVE_BLOCK, 4.0f, 8.0f)
        MANIPULATOR = simpleBlock("manipulator", BlockVariantType.METAL_ACTIVE_BLOCK)
        SHIELDING_CORE = simpleBlock("shielding_core", BlockVariantType.METAL_ACTIVE_BLOCK)

        TRANSPARENT_CASING_01 = simpleBlock("glass_casing_01", BlockVariantType.TRANSPARENT_BLOCK, 5.0f, 5.0f)
        TRANSPARENT_CASING_02 = simpleBlock("glass_casing_02", BlockVariantType.TRANSPARENT_BLOCK, 5.0f, 5.0f)
        TRANSPARENT_CASING_03 = simpleBlock("glass_casing_03", BlockVariantType.TRANSPARENT_BLOCK, 5.0f, 5.0f)
    }

    @SideOnly(Side.CLIENT)
    @JvmStatic
    fun registerItemModels()
    {
        // Initialized stones and its variant blocks.
        STONES.values.forEach(::registerItemModel)

        // Initialized tree related blocks.
        LEAVES.forEach(::registerItemModel)
        SAPLINGS.forEach(::registerItemModel)
        SAPLINGS.forEach { block -> registerItemModel(block)
            (0 .. 7).forEach { meta ->
                ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(block), meta shl 1,
                    ModelResourceLocation("${block.registryName}_$meta", "inventory"))
            }
        }

        LOGS.forEach { block ->
            registerItemModelWithOverride(block, mapOf(BlockLog.LOG_AXIS to BlockLog.EnumAxis.Y))
        }

        PLANKS.forEach(::registerItemModel)

        // Initialized wooden slabs.
        registerItemModelWithOverride(WOOD_SLABS,
            immutableMapOf(BlockSlab.HALF to BlockSlab.EnumBlockHalf.BOTTOM))

        // Initialized wooden stairs.
        setModelLocation(BANANA_WOOD_STAIR)
        setModelLocation(ORANGE_WOOD_STAIR)
        setModelLocation(MANGO_WOOD_STAIR)
        setModelLocation(APRICOT_WOOD_STAIR)
        setModelLocation(LEMON_WOOD_STAIR)
        setModelLocation(LIME_WOOD_STAIR)
        setModelLocation(OLIVE_WOOD_STAIR)
        setModelLocation(NUTMEG_WOOD_STAIR)
        setModelLocation(COCONUT_WOOD_STAIR)
        setModelLocation(RAINBOW_WOOD_STAIR)

        // Initialized wooden fences.
        setModelLocation(BANANA_WOOD_FENCE)
        setModelLocation(ORANGE_WOOD_FENCE)
        setModelLocation(MANGO_WOOD_FENCE)
        setModelLocation(APRICOT_WOOD_FENCE)
        setModelLocation(LEMON_WOOD_FENCE)
        setModelLocation(LIME_WOOD_FENCE)
        setModelLocation(OLIVE_WOOD_FENCE)
        setModelLocation(NUTMEG_WOOD_FENCE)
        setModelLocation(COCONUT_WOOD_FENCE)
        setModelLocation(RAINBOW_WOOD_FENCE)

        // Initialized wooden fence gates.
        setModelLocation(BANANA_WOOD_FENCE_GATE)
        setModelLocation(ORANGE_WOOD_FENCE_GATE)
        setModelLocation(MANGO_WOOD_FENCE_GATE)
        setModelLocation(APRICOT_WOOD_FENCE_GATE)
        setModelLocation(LEMON_WOOD_FENCE_GATE)
        setModelLocation(LIME_WOOD_FENCE_GATE)
        setModelLocation(OLIVE_WOOD_FENCE_GATE)
        setModelLocation(NUTMEG_WOOD_FENCE_GATE)
        setModelLocation(COCONUT_WOOD_FENCE_GATE)
        setModelLocation(RAINBOW_WOOD_FENCE_GATE)

        // Initialized miscellaneous blocks.
        setModelLocation(DUST_BLOCK)
        setModelLocation(DIMENSION_DISPLAY_OVERWORLD)
        setModelLocation(DIMENSION_DISPLAY_NETHER)
        setModelLocation(DIMENSION_DISPLAY_END)

        registerItemModel(NAQUADRIA_CHARGE)
        registerItemModel(TARANIUM_CHARGE)
        registerItemModel(LEPTONIC_CHARGE)
        registerItemModel(QUANTUM_CHROMODYNAMIC_CHARGE)

        // Initialized meta blocks.
        registerItemModel(MOTOR_CASING)
        registerItemModel(PISTON_CASING)
        registerItemModel(PUMP_CASING)
        registerItemModel(CONVEYOR_CASING)
        registerItemModel(ROBOT_ARM_CASING)
        registerItemModel(EMITTER_CASING)
        registerItemModel(SENSOR_CASING)
        registerItemModel(FIELD_GEN_CASING)
        registerItemModel(PROCESSOR_CASING)

        registerItemModel(PRIMITIVE_CASING)
        registerItemModel(METAL_CASING_01)
        registerItemModel(METAL_CASING_02)
        registerItemModel(METAL_CASING_03)
        registerItemModel(MULTIBLOCK_CASING_01)
        registerItemModel(BOILER_CASING_01)
        registerItemModel(TURBINE_CASING_01)
        registerItemModel(TURBINE_CASING_02)
        registerItemModel(SCIENCE_CASING_01)
        registerItemModel(SPACETIME_COMPRESSION_FIELD_GENERATOR)
        registerItemModel(TIME_ACCELERATION_FIELD_GENERATOR)
        registerItemModel(STABILIZATION_FIELD_GENERATOR)
        registerItemModel(AEROSPACE_CASING)
        registerItemModel(CRUCIBLE)
        registerItemModel(COMPONENT_ASSEMBLY_CASING)
        registerItemModel(TRANSPARENT_CASING_01)
        registerItemModel(TRANSPARENT_CASING_02)
        registerItemModel(TRANSPARENT_CASING_03)

        ACTIVE_UNIQUE_CASING_01.onModelRegister()
        FUSION_CASING.onModelRegister()
        FUSION_COIL.onModelRegister()
        FUSION_CRYOSTAT.onModelRegister()
        FUSION_DIVERTOR.onModelRegister()
        FUSION_VACUUM.onModelRegister()

        ACCELERATION_TRACK.onModelRegister()
        WIRE_COIL.onModelRegister()
        NUCLEAR_REACTOR_CORE_01.onModelRegister()
        NUCLEAR_REACTOR_CORE_02.onModelRegister()
        MANIPULATOR.onModelRegister()
        SHIELDING_CORE.onModelRegister()

        SHEETED_FRAMES.values.distinct().forEach(BlockSheetedFrame::onModelRegister)
        METAL_WALLS.values.distinct().forEach(BlockMetalWall::onModelRegister)
    }

    @SideOnly(Side.CLIENT)
    @JvmStatic
    fun registerColors()
    {
        LEAVES.forEach(GTLiteLeaveBlock::registerColors)
        val blockColors = Games.blockColors()
        val itemColors = Games.itemColors()

        SHEETED_FRAME_BLOCKS.forEach { block ->
            blockColors.registerBlockColorHandler({ state, _, _, _ ->
                block.getGTMaterial(block.getMetaFromState(state)).materialRGB
            }, block)
            itemColors.registerItemColorHandler({ stack, _ ->
                block.getGTMaterial(stack.metadata).materialRGB
            }, block)
        }

        METAL_WALL_BLOCKS.forEach { block ->
            blockColors.registerBlockColorHandler({ state, _, _, _ ->
                block.getGtMaterial(block.getMetaFromState(state)).materialRGB
            }, block)
            itemColors.registerItemColorHandler({ stack, _ ->
                block.getGtMaterial(stack.metadata).materialRGB
            }, block)
        }

    }

    @JvmStatic
    fun setFireInfos()
    {
        LEAVES.forEach { setFireInfo(it, 30, 60) }
        LOGS.forEach { setFireInfo(it, 5, 5) }
        PLANKS.forEach { setFireInfo(it) }

        setFireInfo(WOOD_SLABS)
        setFireInfo(DOUBLE_WOOD_SLABS)

        setFireInfo(BANANA_WOOD_STAIR)
        setFireInfo(ORANGE_WOOD_STAIR)
        setFireInfo(MANGO_WOOD_STAIR)
        setFireInfo(APRICOT_WOOD_STAIR)
        setFireInfo(LEMON_WOOD_STAIR)
        setFireInfo(LIME_WOOD_STAIR)
        setFireInfo(OLIVE_WOOD_STAIR)
        setFireInfo(NUTMEG_WOOD_STAIR)
        setFireInfo(COCONUT_WOOD_STAIR)
        setFireInfo(RAINBOW_WOOD_STAIR)

        setFireInfo(BANANA_WOOD_FENCE)
        setFireInfo(ORANGE_WOOD_FENCE)
        setFireInfo(MANGO_WOOD_FENCE)
        setFireInfo(APRICOT_WOOD_FENCE)
        setFireInfo(LEMON_WOOD_FENCE)
        setFireInfo(LIME_WOOD_FENCE)
        setFireInfo(OLIVE_WOOD_FENCE)
        setFireInfo(NUTMEG_WOOD_FENCE)
        setFireInfo(COCONUT_WOOD_FENCE)
        setFireInfo(RAINBOW_WOOD_FENCE)

        setFireInfo(BANANA_WOOD_FENCE_GATE)
        setFireInfo(ORANGE_WOOD_FENCE_GATE)
        setFireInfo(MANGO_WOOD_FENCE_GATE)
        setFireInfo(APRICOT_WOOD_FENCE_GATE)
        setFireInfo(LEMON_WOOD_FENCE_GATE)
        setFireInfo(LIME_WOOD_FENCE_GATE)
        setFireInfo(OLIVE_WOOD_FENCE_GATE)
        setFireInfo(NUTMEG_WOOD_FENCE_GATE)
        setFireInfo(COCONUT_WOOD_FENCE_GATE)
        setFireInfo(RAINBOW_WOOD_FENCE_GATE)
    }

    // region Common Block Factory Methods

    /**
     * Set [ModelResourceLocation] to a common block.
     */
    private fun <T : Block> setModelLocation(block: T)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
            ModelResourceLocation(checkNotNull(block.registryName), "inventory"))
    }

    /**
     * Set fire info for a common block.
     */
    private fun <T : Block> setFireInfo(block: T, encouragement: Int = 5, flammability: Int = 20)
    {
        Blocks.FIRE.setFireInfo(block, encouragement, flammability)
    }

    // endregion

    // region MetaBlock Factory Methods

    /**
     * Create a simple [VariantBlock] with its type.
     *
     * @param name The serialized name for the registry name of the block.
     * @param type The registry type of the block.
     * @return     Simple [VariantBlock] which be initialized.
     */
    private inline fun <reified T, R> simpleBlock(name: String,
                                                  type: BlockVariantType,
                                                  hardness: Float = 5.0f,
                                                  resistance: Float = 10.0f): R where T : Enum<T>,
                                                                                      T : IStringSerializable,
                                                                                      R : VariantBlock<T>
    {
        val block = VariantBlockFactory.make<T>(type)
        block.setRegistryName(name)
            .setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)
            .setTranslationKey("$MOD_ID.$name")
            .setHardness(hardness)
            .setResistance(resistance)
        return Unchecks.cast(block)
    }

    // endregion

    // region Material Block Factory Methods

    private fun createGeneratedBlock(materialPredicate: (GTMaterial) -> Boolean,
                                     blockGenerator: (Array<GTMaterial>, Int) -> Unit)
    {
        val blocksToGenerate: NavigableMap<Int, Array<GTMaterial>> = treeMapOf()
        GregTechAPI.materialManager.registeredMaterials.forEach { material ->
            if (materialPredicate(material))
            {
                val materialId = material.id
                val metaBlockId = materialId / 4
                val subBlockId = materialId % 4

                val materials = blocksToGenerate.getOrPut(metaBlockId) {
                    Array(4) { Materials.NULL }
                }
                materials[subBlockId] = material
            }
        }
        blocksToGenerate.forEach { k, v -> blockGenerator(v, k) }
    }

    private fun createSheetedFrameBlock(materials: Array<GTMaterial>, index: Int)
    {
        val block = BlockSheetedFrame(materials).apply {
            this.registryName = GTLiteMod.id("meta_block_sheeted_frame_$index")
        }
        materials.forEach { SHEETED_FRAMES[it] = block }
        SHEETED_FRAME_BLOCKS.add(block)
    }

    private fun createMetalWallBlock(materials: Array<GTMaterial>, index: Int)
    {
        val block = BlockMetalWall.create(materials)
        block.registryName = GTLiteMod.id("meta_block_wall_gt_$index")
        materials.forEach { METAL_WALLS[it] = block }
        METAL_WALL_BLOCKS.add(block)
    }

    // endregion

    // region Block Model Factory Methods

    @SideOnly(Side.CLIENT)
    private fun registerItemModel(block: Block)
    {
        for (state in block.blockState.validStates)
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                block.getMetaFromState(state),
                ModelResourceLocation(checkNotNull(block.registryName),
                    MetaBlocks.statePropertiesToString(state.properties)))
        }
    }

    @SideOnly(Side.CLIENT)
    private fun registerItemModelWithOverride(block: Block,
                                              stateOverrides: Map<IProperty<*>, Comparable<*>>)
    {
        for (state in block.blockState.validStates)
        {
            val stringProperties = fastObjectHashMapOf(state.properties)
            stringProperties.putAll(stateOverrides)
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                block.getMetaFromState(state),
                ModelResourceLocation(checkNotNull(block.registryName),
                    MetaBlocks.statePropertiesToString(stringProperties)))
        }
    }

    // endregion

    // @formatter:on
}