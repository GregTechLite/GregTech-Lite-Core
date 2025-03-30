package magicbook.gtlitecore.api;

import gregtech.api.creativetab.BaseCreativeTab;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import magicbook.gtlitecore.api.block.IBlockTier;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.command.ICommandManager;
import magicbook.gtlitecore.api.module.IModuleManager;
import magicbook.gtlitecore.api.utils.Mods;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockConveyorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockEmitterCasing;
import magicbook.gtlitecore.common.block.blocks.BlockFieldGenCasing;
import magicbook.gtlitecore.common.block.blocks.BlockMotorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockPistonCasing;
import magicbook.gtlitecore.common.block.blocks.BlockProcessorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockPumpCasing;
import magicbook.gtlitecore.common.block.blocks.BlockRobotArmCasing;
import magicbook.gtlitecore.common.block.blocks.BlockSensorCasing;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;

public class GTLiteAPI
{
    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ API Objects +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    // Will always be available.
    public static Object instance;
    // Will be available at the Construction stage.
    public static IModuleManager moduleManager;
    // Will be available at the Server-Starting stage.
    public static ICommandManager commandManager;

    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- Creative Tabs -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    // Total CreativeTabs of gtlitecore, this is default settings for many contents in
    // the mod, but if item, block or other contents satisfied descriptions of the
    // following CreativeTabs, then should use consistent CreativeTabs.
    public static final BaseCreativeTab TAB_GTLITE = new BaseCreativeTab("gtlite",
            () -> GTLiteMetaTileEntities.PE_CAN.getStackForm(), false);

    // Machines (consists of single machines and multiblock machines) for gtlitecore,
    // which copied from gregtech's machine creative tabs.
    public static final BaseCreativeTab TAB_GTLITE_MACHINE = new BaseCreativeTab("gtlite.machine",
            () -> GTLiteMetaTileEntities.LARGE_CUTTER.getStackForm(), false);

    // Decorations for gtlitecore which consists of all stone blocks and its variants,
    // trees, planks, logs and other tree related blocks, and decorative blocks.
    public static final BaseCreativeTab TAB_GTLITE_DECORATION = new BaseCreativeTab("gtlite.decoration",
            () -> Mods.GregTechLiteCore.getItem("stone_cobble", 1), false);

    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ BlockState Maps +-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_MOTOR_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_PISTON_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_PUMP_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_CONVEYOR_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_ROBOT_ARM_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_EMITTER_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_SENSOR_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_FIELD_GEN_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_PROCESSOR_CASING = new Object2ObjectOpenHashMap<>();

    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_CLEANROOM_CASING = new Object2ObjectOpenHashMap<>();
    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    public static void init()
    {
        for (BlockMotorCasing.MotorCasingTier tier : BlockMotorCasing.MotorCasingTier.values())
        {
            MAP_MOTOR_CASING.put(GTLiteMetaBlocks.MOTOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockPistonCasing.PistonCasingTier tier : BlockPistonCasing.PistonCasingTier.values())
        {
            MAP_PISTON_CASING.put(GTLiteMetaBlocks.PISTON_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockPumpCasing.PumpCasingTier tier : BlockPumpCasing.PumpCasingTier.values())
        {
            MAP_PUMP_CASING.put(GTLiteMetaBlocks.PUMP_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockConveyorCasing.ConveyorCasingTier tier : BlockConveyorCasing.ConveyorCasingTier.values())
        {
            MAP_CONVEYOR_CASING.put(GTLiteMetaBlocks.CONVEYOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockRobotArmCasing.RobotArmCasingTier tier : BlockRobotArmCasing.RobotArmCasingTier.values())
        {
            MAP_ROBOT_ARM_CASING.put(GTLiteMetaBlocks.ROBOT_ARM_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockEmitterCasing.EmitterCasingTier tier : BlockEmitterCasing.EmitterCasingTier.values())
        {
            MAP_EMITTER_CASING.put(GTLiteMetaBlocks.EMITTER_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockSensorCasing.SensorCasingTier tier : BlockSensorCasing.SensorCasingTier.values())
        {
            MAP_SENSOR_CASING.put(GTLiteMetaBlocks.SENSOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockFieldGenCasing.FieldGenCasingTier tier : BlockFieldGenCasing.FieldGenCasingTier.values())
        {
            MAP_FIELD_GEN_CASING.put(GTLiteMetaBlocks.FIELD_GEN_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        for (BlockProcessorCasing.ProcessorCasingTier tier : BlockProcessorCasing.ProcessorCasingTier.values())
        {
            MAP_PROCESSOR_CASING.put(GTLiteMetaBlocks.PROCESSOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        MAP_CLEANROOM_CASING.put(MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.FILTER_CASING),
                new WrappedIntTier(BlockCleanroomCasing.CasingType.FILTER_CASING, 1));

        MAP_CLEANROOM_CASING.put(MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE),
                new WrappedIntTier(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE, 2));

    }

}
