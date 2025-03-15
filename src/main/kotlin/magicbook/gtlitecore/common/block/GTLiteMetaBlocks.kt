package magicbook.gtlitecore.common.block

import com.google.common.collect.ImmutableMap
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.client.model.ItemModelHelper
import magicbook.gtlitecore.client.model.ItemModelHelper.registerItemModel
import magicbook.gtlitecore.client.model.ItemModelHelper.registerItemModelWithOverride
import magicbook.gtlitecore.common.block.blocks.BlockConveyorCasing
import magicbook.gtlitecore.common.block.blocks.BlockEmitterCasing
import magicbook.gtlitecore.common.block.blocks.BlockFieldGenCasing
import magicbook.gtlitecore.common.block.blocks.BlockMotorCasing
import magicbook.gtlitecore.common.block.blocks.BlockPistonCasing
import magicbook.gtlitecore.common.block.blocks.BlockPrimitiveCasing
import magicbook.gtlitecore.common.block.blocks.BlockProcessorCasing
import magicbook.gtlitecore.common.block.blocks.BlockPumpCasing
import magicbook.gtlitecore.common.block.blocks.BlockRobotArmCasing
import magicbook.gtlitecore.common.block.blocks.BlockSensorCasing
import magicbook.gtlitecore.common.block.blocks.GTLiteLeaveVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLiteLogVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLitePlankVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLiteSaplingVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLiteStoneVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLiteWoodFenceGateVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLiteWoodFenceVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLiteWoodSlabVariantBlock
import magicbook.gtlitecore.common.block.blocks.GTLiteWoodStairVariantBlock
import magicbook.gtlitecore.common.worldgen.trees.AbstractTree
import magicbook.gtlitecore.common.worldgen.trees.WorldGenTrees
import net.minecraft.block.Block
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockSlab
import net.minecraft.block.properties.IProperty
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*


@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteMetaBlocks
{

    companion object
    {

        // Enum map of all variant stones in gtlitecore, same as GregTech StoneVariantBlock.
        @JvmField
        val STONES: EnumMap<GTLiteStoneVariantBlock.StoneVariant, GTLiteStoneVariantBlock>
            = EnumMap(GTLiteStoneVariantBlock.StoneVariant::class.java)

        // Tree components, some components at AbstractTree and WorldGenModule.
        @JvmField
        val LEAVES: MutableList<GTLiteLeaveVariantBlock> = ArrayList()

        @JvmField
        val LOGS: MutableList<GTLiteLogVariantBlock> = ArrayList()

        @JvmField
        val PLANKS: MutableList<GTLitePlankVariantBlock> = ArrayList()

        @JvmField
        val SAPLINGS: MutableList<GTLiteSaplingVariantBlock> = ArrayList()

        lateinit var WOOD_SLABS: GTLiteWoodSlabVariantBlock
        lateinit var DOUBLE_WOOD_SLABS: GTLiteWoodSlabVariantBlock

        lateinit var BANANA_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var ORANGE_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var MANGO_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var APRICOT_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var LEMON_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var LIME_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var OLIVE_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var NUTMEG_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var COCONUT_WOOD_STAIR: GTLiteWoodStairVariantBlock
        lateinit var RAINBOW_WOOD_STAIR: GTLiteWoodStairVariantBlock

        lateinit var BANANA_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var ORANGE_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var MANGO_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var APRICOT_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var LEMON_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var LIME_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var OLIVE_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var NUTMEG_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var COCONUT_WOOD_FENCE: GTLiteWoodFenceVariantBlock
        lateinit var RAINBOW_WOOD_FENCE: GTLiteWoodFenceVariantBlock

        lateinit var BANANA_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var ORANGE_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var MANGO_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var APRICOT_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var LEMON_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var LIME_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var OLIVE_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var NUTMEG_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var COCONUT_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        lateinit var RAINBOW_WOOD_FENCE_GATE: GTLiteWoodFenceGateVariantBlock
        
        lateinit var MOTOR_CASING: BlockMotorCasing
        lateinit var PISTON_CASING: BlockPistonCasing
        lateinit var PUMP_CASING: BlockPumpCasing
        lateinit var CONVEYOR_CASING: BlockConveyorCasing
        lateinit var ROBOT_ARM_CASING: BlockRobotArmCasing
        lateinit var EMITTER_CASING: BlockEmitterCasing
        lateinit var SENSOR_CASING: BlockSensorCasing
        lateinit var FIELD_GEN_CASING: BlockFieldGenCasing
        lateinit var PROCESSOR_CASING: BlockProcessorCasing

        lateinit var PRIMITIVE_CASING: BlockPrimitiveCasing

        @JvmStatic
        fun init()
        {
            // Various stones initialization.
            for (variant: GTLiteStoneVariantBlock.StoneVariant in GTLiteStoneVariantBlock.StoneVariant.entries)
                STONES[variant] = GTLiteStoneVariantBlock(variant)

            // Various trees initialization.
            WorldGenTrees.init() // Setup worldgen module components.
            for (i in 0..(AbstractTree.trees.size - 1) / 4)
            {
                val leaves = GTLiteLeaveVariantBlock(i)
                leaves.setRegistryName("gtlite_leaves_$i")
            }
            for (i in 0..(AbstractTree.trees.size - 1) / 4)
            {
                val log = GTLiteLogVariantBlock(i)
                log.setRegistryName("gtlite_log_$i")
            }
            for (i in 0..(AbstractTree.trees.size - 1) / 8)
            {
                val sapling = GTLiteSaplingVariantBlock(i)
                sapling.setRegistryName("gtlite_sapling_$i")
            }
            for (i in 0..(AbstractTree.trees.size - 1) / 16)
            {
                val planks = GTLitePlankVariantBlock(i)
                planks.setRegistryName("gtlite_planks_$i")
            }
            // TODO Crops, Berries?

            // Setup blocks for AbstractTree componetns.
            AbstractTree.trees.forEach { it.setupBlocks() }

            // Wooden slabs.
            WOOD_SLABS = GTLiteWoodSlabVariantBlock.Half()
            WOOD_SLABS.setRegistryName("gtlite_wood_slab")
            DOUBLE_WOOD_SLABS = GTLiteWoodSlabVariantBlock.Double()
            DOUBLE_WOOD_SLABS.setRegistryName("gtlite_double_wood_slab")

            // Wooden stairs.
            BANANA_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(0))
            (BANANA_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_banana")
            (BANANA_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.banana")

            ORANGE_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(1))
            (ORANGE_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_orange")
            (ORANGE_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.orange")

            MANGO_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(2))
            (MANGO_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_mango")
            (MANGO_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.mango")

            APRICOT_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(3))
            (APRICOT_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_apricot")
            (APRICOT_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.apricot")

            LEMON_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(4))
            (LEMON_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_lemon")
            (LEMON_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.lemon")

            LIME_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(5))
            (LIME_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_lime")
            (LIME_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.lime")

            OLIVE_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(6))
            (OLIVE_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_olive")
            (OLIVE_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.olive")

            NUTMEG_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(7))
            (NUTMEG_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_nutmeg")
            (NUTMEG_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.nutmeg")

            COCONUT_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(8))
            (COCONUT_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_coconut")
            (COCONUT_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.coconut")

            RAINBOW_WOOD_STAIR = GTLiteWoodStairVariantBlock(PLANKS[0].getStateFromMeta(9))
            (RAINBOW_WOOD_STAIR as? Block)?.setRegistryName("gtlite_wood_stair_rainbow")
            (RAINBOW_WOOD_STAIR as? Block)?.setTranslationKey("gtlite_wood_stair.rainbow")

            // Wooden fences.
            BANANA_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (BANANA_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_banana")
            (BANANA_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.banana")

            ORANGE_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (ORANGE_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_orange")
            (ORANGE_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.orange")

            MANGO_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (MANGO_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_mango")
            (MANGO_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.mango")

            APRICOT_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (APRICOT_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_apricot")
            (APRICOT_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.apricot")

            LEMON_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (LEMON_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_lemon")
            (LEMON_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.lemon")

            LIME_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (LIME_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_lime")
            (LIME_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.lime")

            OLIVE_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (OLIVE_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_olive")
            (OLIVE_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.olive")

            NUTMEG_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (NUTMEG_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_nutmeg")
            (NUTMEG_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.nutmeg")

            COCONUT_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (COCONUT_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_coconut")
            (COCONUT_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.coconut")

            RAINBOW_WOOD_FENCE = GTLiteWoodFenceVariantBlock()
            (RAINBOW_WOOD_FENCE as? Block)?.setRegistryName("gtlite_wood_fence_rainbow")
            (RAINBOW_WOOD_FENCE as? Block)?.setTranslationKey("gtlite_wood_fence.rainbow")

            // Wooden fence gates.
            BANANA_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (BANANA_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_banana")
            (BANANA_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.banana")

            ORANGE_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (ORANGE_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_orange")
            (ORANGE_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.orange")

            MANGO_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (MANGO_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_mango")
            (MANGO_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.mango")

            APRICOT_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (APRICOT_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_apricot")
            (APRICOT_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.apricot")

            LEMON_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (LEMON_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_lemon")
            (LEMON_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.lemon")

            LIME_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (LIME_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_lime")
            (LIME_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.lime")

            OLIVE_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (OLIVE_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_olive")
            (OLIVE_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.olive")

            NUTMEG_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (NUTMEG_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_nutmeg")
            (NUTMEG_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.nutmeg")

            COCONUT_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (COCONUT_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_coconut")
            (COCONUT_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.coconut")

            RAINBOW_WOOD_FENCE_GATE = GTLiteWoodFenceGateVariantBlock()
            (RAINBOW_WOOD_FENCE_GATE as? Block)?.setRegistryName("gtlite_wood_fence_gate_rainbow")
            (RAINBOW_WOOD_FENCE_GATE as? Block)?.setTranslationKey("gtlite_wood_fence_gate.rainbow")

            // Component casings initialization.
            MOTOR_CASING = BlockMotorCasing()
            (MOTOR_CASING as? Block)?.setRegistryName("motor_casing")
            (MOTOR_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            PISTON_CASING = BlockPistonCasing()
            (PISTON_CASING as? Block)?.setRegistryName("piston_casing")
            (PISTON_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            PUMP_CASING = BlockPumpCasing()
            (PUMP_CASING as? Block)?.setRegistryName("pump_casing")
            (PUMP_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            CONVEYOR_CASING = BlockConveyorCasing()
            (CONVEYOR_CASING as? Block)?.setRegistryName("conveyor_casing")
            (CONVEYOR_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            ROBOT_ARM_CASING = BlockRobotArmCasing()
            (ROBOT_ARM_CASING as? Block)?.setRegistryName("robot_arm_casing")
            (ROBOT_ARM_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            EMITTER_CASING = BlockEmitterCasing()
            (EMITTER_CASING as? Block)?.setRegistryName("emitter_casing")
            (EMITTER_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            SENSOR_CASING = BlockSensorCasing()
            (SENSOR_CASING as? Block)?.setRegistryName("sensor_casing")
            (SENSOR_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            FIELD_GEN_CASING = BlockFieldGenCasing()
            (FIELD_GEN_CASING as? Block)?.setRegistryName("field_gen_casing")
            (FIELD_GEN_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            PROCESSOR_CASING = BlockProcessorCasing()
            (PROCESSOR_CASING as? Block)?.setRegistryName("processor_casing")
            (PROCESSOR_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            PRIMITIVE_CASING = BlockPrimitiveCasing()
            (PRIMITIVE_CASING as? Block)?.setRegistryName("primitive_casing")
            (PRIMITIVE_CASING as? Block)?.setCreativeTab(GTLiteAPI.TAB_GTLITE)

            // Initialized Blocks.FIRE#setFireInfo().
            setFireInfos()
        }

        @SideOnly(Side.CLIENT)
        @JvmStatic
        fun registerItemModels()
        {
            // Various stones.
            for (block in STONES.values)
                registerItemModel(block)
            // Various tree components.
            LEAVES.forEach(ItemModelHelper::registerItemModel)
            SAPLINGS.forEach(ItemModelHelper::registerItemModel)
            SAPLINGS.forEach { s ->
                registerItemModel(s)
                (0 .. 7).forEach { v ->
                    ModelLoader.setCustomModelResourceLocation(
                        Item.getItemFromBlock(s),
                        v shl 1,
                        ModelResourceLocation(s.registryName!!.toString()
                            + "_$v", "inventory")
                    )
                }
            }
            LOGS.forEach { l ->
                registerItemModelWithOverride(l, mapOf(BlockLog.LOG_AXIS to BlockLog.EnumAxis.Y))
            }
            PLANKS.forEach(ItemModelHelper::registerItemModel)

            // Wooden slabs.
            registerItemModelWithOverride(WOOD_SLABS,
                ImmutableMap.of<IProperty<*>, Comparable<*>>(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM))

            // Wooden stairs.
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BANANA_WOOD_STAIR), 0,
                ModelResourceLocation(BANANA_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ORANGE_WOOD_STAIR), 0,
                ModelResourceLocation(ORANGE_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MANGO_WOOD_STAIR), 0,
                ModelResourceLocation(MANGO_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APRICOT_WOOD_STAIR), 0,
                ModelResourceLocation(APRICOT_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LEMON_WOOD_STAIR), 0,
                ModelResourceLocation(LEMON_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LIME_WOOD_STAIR), 0,
                ModelResourceLocation(LIME_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(OLIVE_WOOD_STAIR), 0,
                ModelResourceLocation(OLIVE_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(NUTMEG_WOOD_STAIR), 0,
                ModelResourceLocation(NUTMEG_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(COCONUT_WOOD_STAIR), 0,
                ModelResourceLocation(COCONUT_WOOD_STAIR.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RAINBOW_WOOD_STAIR), 0,
                ModelResourceLocation(RAINBOW_WOOD_STAIR.registryName!!, "inventory"))

            // Wooden fences.
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BANANA_WOOD_FENCE), 0,
                ModelResourceLocation(BANANA_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ORANGE_WOOD_FENCE), 0,
                ModelResourceLocation(ORANGE_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MANGO_WOOD_FENCE), 0,
                ModelResourceLocation(MANGO_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APRICOT_WOOD_FENCE), 0,
                ModelResourceLocation(APRICOT_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LEMON_WOOD_FENCE), 0,
                ModelResourceLocation(LEMON_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LIME_WOOD_FENCE), 0,
                ModelResourceLocation(LIME_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(OLIVE_WOOD_FENCE), 0,
                ModelResourceLocation(OLIVE_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(NUTMEG_WOOD_FENCE), 0,
                ModelResourceLocation(NUTMEG_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(COCONUT_WOOD_FENCE), 0,
                ModelResourceLocation(COCONUT_WOOD_FENCE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RAINBOW_WOOD_FENCE), 0,
                ModelResourceLocation(RAINBOW_WOOD_FENCE.registryName!!, "inventory"))

            // Wooden fence gates.
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BANANA_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(BANANA_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ORANGE_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(ORANGE_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MANGO_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(MANGO_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(APRICOT_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(APRICOT_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LEMON_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(LEMON_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(LIME_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(LIME_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(OLIVE_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(OLIVE_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(NUTMEG_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(NUTMEG_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(COCONUT_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(COCONUT_WOOD_FENCE_GATE.registryName!!, "inventory"))
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RAINBOW_WOOD_FENCE_GATE), 0,
                ModelResourceLocation(RAINBOW_WOOD_FENCE_GATE.registryName!!, "inventory"))

            // Common variant blocks.
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
        }

        @SideOnly(Side.CLIENT)
        @JvmStatic
        fun registerColors()
        {
            LEAVES.forEach(GTLiteLeaveVariantBlock::registerColors)
        }

        @JvmStatic
        fun setFireInfos()
        {
            LEAVES.forEach { Blocks.FIRE.setFireInfo(it, 30, 60) }
            LOGS.forEach { Blocks.FIRE.setFireInfo(it, 5, 5) }
            PLANKS.forEach { Blocks.FIRE.setFireInfo(it, 5, 20) }

            Blocks.FIRE.setFireInfo(WOOD_SLABS, 5, 20)
            Blocks.FIRE.setFireInfo(DOUBLE_WOOD_SLABS, 5, 20)

            Blocks.FIRE.setFireInfo(BANANA_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(ORANGE_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(MANGO_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(APRICOT_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(LEMON_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(LIME_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(OLIVE_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(NUTMEG_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(COCONUT_WOOD_STAIR, 5, 20)
            Blocks.FIRE.setFireInfo(RAINBOW_WOOD_STAIR, 5, 20)

            Blocks.FIRE.setFireInfo(BANANA_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(ORANGE_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(MANGO_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(APRICOT_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(LEMON_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(LIME_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(OLIVE_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(NUTMEG_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(COCONUT_WOOD_FENCE, 5, 20)
            Blocks.FIRE.setFireInfo(RAINBOW_WOOD_FENCE, 5, 20)

            Blocks.FIRE.setFireInfo(BANANA_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(ORANGE_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(MANGO_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(APRICOT_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(LEMON_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(LIME_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(OLIVE_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(NUTMEG_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(COCONUT_WOOD_FENCE_GATE, 5, 20)
            Blocks.FIRE.setFireInfo(RAINBOW_WOOD_FENCE_GATE, 5, 20)

        }

    }

}