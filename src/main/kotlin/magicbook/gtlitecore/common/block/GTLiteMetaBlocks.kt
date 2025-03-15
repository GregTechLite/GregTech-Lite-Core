package magicbook.gtlitecore.common.block

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
import magicbook.gtlitecore.common.worldgen.trees.AbstractTree
import magicbook.gtlitecore.common.worldgen.trees.WorldGenTrees
import net.minecraft.block.Block
import net.minecraft.block.BlockLog
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
        }

    }

}