package magicbook.gtlitecore.api;

import gregtech.api.GregTechAPI;
import gregtech.api.creativetab.BaseCreativeTab;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import magicbook.gtlitecore.api.advancement.IAdvancementManager;
import magicbook.gtlitecore.api.block.IBlockTier;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.command.ICommandManager;
import magicbook.gtlitecore.api.module.IModuleManager;
import magicbook.gtlitecore.api.network.INetworkHandler;
import magicbook.gtlitecore.api.sound.ISoundManager;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockComponentAssemblyCasing;
import magicbook.gtlitecore.common.block.blocks.BlockConveyorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockEmitterCasing;
import magicbook.gtlitecore.common.block.blocks.BlockFieldGenCasing;
import magicbook.gtlitecore.common.block.blocks.BlockFusionCasing01;
import magicbook.gtlitecore.common.block.blocks.BlockFusionCasing02;
import magicbook.gtlitecore.common.block.blocks.BlockFusionCasing03;
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing02;
import magicbook.gtlitecore.common.block.blocks.BlockMotorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockPistonCasing;
import magicbook.gtlitecore.common.block.blocks.BlockProcessorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockPumpCasing;
import magicbook.gtlitecore.common.block.blocks.BlockRobotArmCasing;
import magicbook.gtlitecore.common.block.blocks.BlockSensorCasing;
import magicbook.gtlitecore.common.block.blocks.BlockWireCoils;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import net.minecraft.block.state.IBlockState;

public class GTLiteAPI
{
    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ API Objects +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    // Will always be available.
    public static Object instance;
    // Will be available at the Construction stage.
    public static IModuleManager moduleManager;
    // Will be available at the Pre-Initialization stage
    public static INetworkHandler networkHandler;
    // Will be available at the Server-Starting stage.
    public static ICommandManager commandManager;
    // Will be available at the Pre-Initialization stage.
    public static IAdvancementManager advancementManager;
    // Will be available at the Pre-Initialization stage.
    public static ISoundManager soundManager;

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
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_FUSION_CASING = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_FUSION_COIL = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_CRYOSTAT = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_DIVERTOR = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_VACUUM = new Object2ObjectOpenHashMap<>();
    public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier> MAP_COMPONENT_CASING = new Object2ObjectOpenHashMap<>();

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

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.COSMIC_NEUTRONIUM_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.COSMIC_NEUTRONIUM_BOROSILICATE, 8));

        MAP_BOROSILICATE_GLASS.put(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getState(BlockGlassCasing02.GlassType.INFINITY_BOROSILICATE),
                new WrappedIntTier(BlockGlassCasing02.GlassType.INFINITY_BOROSILICATE, 9));

        // TODO UXV+ Borosilicates.

        // fusion related casings (fusion casing, coil, cryostat, divertor and vacuum)
        MAP_FUSION_CASING.put(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_CASING),
                new WrappedIntTier(BlockFusionCasing.CasingType.FUSION_CASING, 1));

        MAP_FUSION_CASING.put(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_CASING_MK2),
                new WrappedIntTier(BlockFusionCasing.CasingType.FUSION_CASING_MK2, 2));

        MAP_FUSION_CASING.put(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_CASING_MK3),
                new WrappedIntTier(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 3));

        MAP_FUSION_CASING.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK4),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK4, 4));

        MAP_FUSION_CASING.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK5),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK5, 5));

        MAP_FUSION_COIL.put(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS),
                new WrappedIntTier(BlockGlassCasing.CasingType.FUSION_GLASS, 1));

        MAP_FUSION_COIL.put(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL),
                new WrappedIntTier(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL, 2));

        MAP_FUSION_COIL.put(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_COIL),
                new WrappedIntTier(BlockFusionCasing.CasingType.FUSION_COIL, 3));

        MAP_FUSION_COIL.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.ADVANCED_FUSION_COIL),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.ADVANCED_FUSION_COIL, 4));

        MAP_FUSION_COIL.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.ULTIMATE_FUSION_COIL),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.ULTIMATE_FUSION_COIL, 5));

        MAP_CRYOSTAT.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK1),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK1, 1));

        MAP_CRYOSTAT.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK2),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK2, 2));

        MAP_CRYOSTAT.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK3),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK3, 3));

        MAP_CRYOSTAT.put(GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK4),
                new WrappedIntTier(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK4, 4));

        MAP_CRYOSTAT.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.CRYOSTAT_MK5),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.CRYOSTAT_MK5, 5));

        MAP_DIVERTOR.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK1),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK1, 1));

        MAP_DIVERTOR.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK2),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK2, 2));

        MAP_DIVERTOR.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK3),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK3, 3));

        MAP_DIVERTOR.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK4),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK4, 4));

        MAP_DIVERTOR.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK5),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK5, 5));

        MAP_VACUUM.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.VACUUM_MK1),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.VACUUM_MK1, 1));

        MAP_VACUUM.put(GTLiteMetaBlocks.FUSION_CASING_02.getState(BlockFusionCasing02.FusionCasingType.VACUUM_MK2),
                new WrappedIntTier(BlockFusionCasing02.FusionCasingType.VACUUM_MK2, 2));

        MAP_VACUUM.put(GTLiteMetaBlocks.FUSION_CASING_03.getState(BlockFusionCasing03.FusionCasingType.VACUUM_MK3),
                new WrappedIntTier(BlockFusionCasing03.FusionCasingType.VACUUM_MK3, 3));

        MAP_VACUUM.put(GTLiteMetaBlocks.FUSION_CASING_03.getState(BlockFusionCasing03.FusionCasingType.VACUUM_MK4),
                new WrappedIntTier(BlockFusionCasing03.FusionCasingType.VACUUM_MK4, 4));

        MAP_VACUUM.put(GTLiteMetaBlocks.FUSION_CASING_03.getState(BlockFusionCasing03.FusionCasingType.VACUUM_MK5),
                new WrappedIntTier(BlockFusionCasing03.FusionCasingType.VACUUM_MK5, 5));

        // componentCasings
        for (BlockComponentAssemblyCasing.ComponentCasingType tier : BlockComponentAssemblyCasing.ComponentCasingType.values())
        {
            MAP_COMPONENT_CASING.put(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getState(tier),
                    new WrappedIntTier(tier, tier.ordinal() + 1));
        }

        // cleanroomCasings
        MAP_CLEANROOM_CASING.put(MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.FILTER_CASING),
                new WrappedIntTier(BlockCleanroomCasing.CasingType.FILTER_CASING, 1));

        MAP_CLEANROOM_CASING.put(MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE),
                new WrappedIntTier(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE, 2));

        // heatingCoils
        for (BlockWireCoils.WireCoilType type : BlockWireCoils.WireCoilType.values())
        {
            GregTechAPI.HEATING_COILS.put(GTLiteMetaBlocks.WIRE_COIL.getState(type), type);
        }

    }

}
