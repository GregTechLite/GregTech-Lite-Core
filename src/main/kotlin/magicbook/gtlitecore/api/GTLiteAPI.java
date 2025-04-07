package magicbook.gtlitecore.api;

import gregtech.api.creativetab.BaseCreativeTab;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import magicbook.gtlitecore.api.block.IBlockTier;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.command.ICommandManager;
import magicbook.gtlitecore.api.module.IModuleManager;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockConveyorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockEmitterCasing;
import magicbook.gtlitecore.common.block.blocks.BlockFieldGenCasing;
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing02;
import magicbook.gtlitecore.common.block.blocks.BlockMotorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockPistonCasing;
import magicbook.gtlitecore.common.block.blocks.BlockProcessorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockPumpCasing;
import magicbook.gtlitecore.common.block.blocks.BlockRobotArmCasing;
import magicbook.gtlitecore.common.block.blocks.BlockSensorCasing;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
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
            () -> GTLiteMetaItems.LOGO_CORE.getStackForm(), false);

    // Machines (consists of single machines and multiblock machines) for gtlitecore,
    // which copied from gregtech's machine creative tabs.
    public static final BaseCreativeTab TAB_GTLITE_MACHINE = new BaseCreativeTab("gtlite.machine",
            () -> GTLiteMetaItems.LOGO_MACHINE.getStackForm(), false);

    // Decorations for gtlitecore which consists of all stone blocks and its variants,
    // trees, planks, logs and other tree related blocks, and decorative blocks.
    public static final BaseCreativeTab TAB_GTLITE_DECORATION = new BaseCreativeTab("gtlite.decoration",
            () -> GTLiteMetaItems.LOGO_DECORATION.getStackForm(), false);

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

    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_BOROSILICATE_GLASS = new Object2ObjectOpenHashMap<>();

    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_CLEANROOM_CASING = new Object2ObjectOpenHashMap<>();
    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    public static void init()
    {
        // motorCasings
        for (BlockMotorCasing.MotorCasingTier tier : BlockMotorCasing.MotorCasingTier.values())
        {
            MAP_MOTOR_CASING.put(GTLiteMetaBlocks.MOTOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // pistonCasings
        for (BlockPistonCasing.PistonCasingTier tier : BlockPistonCasing.PistonCasingTier.values())
        {
            MAP_PISTON_CASING.put(GTLiteMetaBlocks.PISTON_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // pumpCasings
        for (BlockPumpCasing.PumpCasingTier tier : BlockPumpCasing.PumpCasingTier.values())
        {
            MAP_PUMP_CASING.put(GTLiteMetaBlocks.PUMP_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // conveyorCasings
        for (BlockConveyorCasing.ConveyorCasingTier tier : BlockConveyorCasing.ConveyorCasingTier.values())
        {
            MAP_CONVEYOR_CASING.put(GTLiteMetaBlocks.CONVEYOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // robotArmCasings
        for (BlockRobotArmCasing.RobotArmCasingTier tier : BlockRobotArmCasing.RobotArmCasingTier.values())
        {
            MAP_ROBOT_ARM_CASING.put(GTLiteMetaBlocks.ROBOT_ARM_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // emitterCasings
        for (BlockEmitterCasing.EmitterCasingTier tier : BlockEmitterCasing.EmitterCasingTier.values())
        {
            MAP_EMITTER_CASING.put(GTLiteMetaBlocks.EMITTER_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // sensorCasings
        for (BlockSensorCasing.SensorCasingTier tier : BlockSensorCasing.SensorCasingTier.values())
        {
            MAP_SENSOR_CASING.put(GTLiteMetaBlocks.SENSOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // fieldGenCasings
        for (BlockFieldGenCasing.FieldGenCasingTier tier : BlockFieldGenCasing.FieldGenCasingTier.values())
        {
            MAP_FIELD_GEN_CASING.put(GTLiteMetaBlocks.FIELD_GEN_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // processorCasings
        for (BlockProcessorCasing.ProcessorCasingTier tier : BlockProcessorCasing.ProcessorCasingTier.values())
        {
            MAP_PROCESSOR_CASING.put(GTLiteMetaBlocks.PROCESSOR_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // borosilicateGlasses
        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.BOROSILICATE, 1));

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.TITANIUM_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.TITANIUM_BOROSILICATE, 2));

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.TUNGSTEN_STEEL_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.TUNGSTEN_STEEL_BOROSILICATE, 3));

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.RHODIUM_PLATED_PALLADIUM_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.RHODIUM_PLATED_PALLADIUM_BOROSILICATE, 4));

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.OSMIRIDIUM_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.OSMIRIDIUM_BOROSILICATE, 5));

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.TRITANIUM_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.TRITANIUM_BOROSILICATE, 6));

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.NEUTRONIUM_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.NEUTRONIUM_BOROSILICATE, 7));

        // TODO UHV+ Borosilicates.

        // cleanroomCasings
        MAP_CLEANROOM_CASING.put(MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.FILTER_CASING),
                new WrappedIntTier(BlockCleanroomCasing.CasingType.FILTER_CASING, 1));

        MAP_CLEANROOM_CASING.put(MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE),
                new WrappedIntTier(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE, 2));

    }

}
