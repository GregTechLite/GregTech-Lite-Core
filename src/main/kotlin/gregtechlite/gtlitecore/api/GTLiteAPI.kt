package gregtechlite.gtlitecore.api

import gregtech.api.GregTechAPI
import gregtechlite.gtlitecore.api.advancement.AdvancementManager
import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry
import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry.Companion.registerBlockVariants
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.command.CommandManager
import gregtechlite.gtlitecore.api.module.ModuleManager
import gregtechlite.gtlitecore.api.network.NetworkHandler
import gregtechlite.gtlitecore.api.sound.SoundManager
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.ComponentAssemblyCasing
import gregtechlite.gtlitecore.common.block.variant.Crucible
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.Manipulator
import gregtechlite.gtlitecore.common.block.variant.NuclearReactorCore
import gregtechlite.gtlitecore.common.block.variant.ShieldingCore
import gregtechlite.gtlitecore.common.block.variant.WireCoil
import gregtechlite.gtlitecore.common.block.variant.aerospace.AccelerationTrack
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

object GTLiteAPI
{

    // @formatter:off

    // region API Objects

    // Will always be available.
    lateinit var instance: Any

    // Will be available at the Construction stage.
    lateinit var moduleManager: ModuleManager

    // Will be available at the Pre-Initialization stage
    lateinit var networkHandler: NetworkHandler

    // Will be available at the Server-Starting stage.
    lateinit var commandManager: CommandManager

    // Will be available at the Pre-Initialization stage.
    lateinit var advancementManager: AdvancementManager

    // Will be available at the Pre-Initialization stage.
    lateinit var soundManager: SoundManager

    // endregion

    // region Block Attribute Registries

    @JvmField
    val MOTOR_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("motor_casing_tier")

    @JvmField
    val PISTON_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("piston_casing_tier")

    @JvmField
    val PUMP_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("pump_casing_tier")

    @JvmField
    val CONVEYOR_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("conveyor_casing_tier")

    @JvmField
    val ROBOT_ARM_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("robot_arm_casing_tier")

    @JvmField
    val EMITTER_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("emitter_casing_tier")

    @JvmField
    val SENSOR_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("sensor_casing_tier")

    @JvmField
    val FIELD_GEN_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("field_gen_casing_tier")

    @JvmField
    val PROCESSOR_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("processor_casing_tier")

    @JvmField
    val BOROSILICATE_GLASS_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("borosilicate_glass_tier")

    @JvmField
    val FUSION_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("fusion_casing_tier")

    @JvmField
    val FUSION_COIL_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("fusion_coil_tier")

    @JvmField
    val CRYOSTAT_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("cryostat_tier")

    @JvmField
    val DIVERTOR_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("divertor_tier")

    @JvmField
    val VACUUM_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("vacuum_tier")

    @JvmField
    val COMPONENT_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("component_casing_tier")

    @JvmField
    val NUCLEAR_REACTOR_CORE_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("nuclear_reactor_core_tier")

    @JvmField
    val MANIPULATOR_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("manipulator_tier")

    @JvmField
    val SHIELDING_CORE_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("shielding_core_tier")

    @JvmField
    val ACCELERATION_TRACK_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("acceleration_track_tier")

    @JvmField
    val CLEANROOM_CASING_TIER: BlockAttributeRegistry<Int> = BlockAttributeRegistry.create("cleanroom_casing_tier")

    @JvmField
    val CRUCIBLE_STATS: BlockAttributeRegistry<Crucible> = BlockAttributeRegistry("crucible_stats", StateTier.COMPARATOR)

    // endregion

    fun init()
    {
        MOTOR_CASING_TIER.registerBlockVariants(MotorCasing::class)
        PISTON_CASING_TIER.registerBlockVariants(PistonCasing::class)
        PUMP_CASING_TIER.registerBlockVariants(PumpCasing::class)
        CONVEYOR_CASING_TIER.registerBlockVariants(ConveyorCasing::class)
        ROBOT_ARM_CASING_TIER.registerBlockVariants(RobotArmCasing::class)
        EMITTER_CASING_TIER.registerBlockVariants(EmitterCasing::class)
        SENSOR_CASING_TIER.registerBlockVariants(SensorCasing::class)
        FIELD_GEN_CASING_TIER.registerBlockVariants(FieldGenCasing::class)
        PROCESSOR_CASING_TIER.registerBlockVariants(ProcessorCasing::class)

        BOROSILICATE_GLASS_TIER.registerBlockVariants(GlassCasing.Enum01::class)

        FUSION_CASING_TIER.registerAll(GTFusionCasing.FUSION_CASING.state to 1,
                                       GTFusionCasing.FUSION_CASING_MK2.state to 2,
                                       GTFusionCasing.FUSION_CASING_MK3.state to 3,
                                       FusionCasing.MK4.state to 4,
                                       FusionCasing.MK5.state to 5)

        FUSION_COIL_TIER.registerAll(GTGlassCasing.FUSION_GLASS.state to 1,
                                     GTFusionCasing.SUPERCONDUCTOR_COIL.state to 2,
                                     GTFusionCasing.FUSION_COIL.state to 3,
                                     FusionCoil.ADVANCED.state to 4,
                                     FusionCoil.ULTIMATE.state to 5)

        CRYOSTAT_TIER.registerBlockVariants(FusionCryostat::class)
        DIVERTOR_TIER.registerBlockVariants(FusionDivertor::class)
        VACUUM_TIER.registerBlockVariants(FusionVacuum::class)

        COMPONENT_CASING_TIER.registerBlockVariants(ComponentAssemblyCasing::class)

        NUCLEAR_REACTOR_CORE_TIER.registerBlockVariants(NuclearReactorCore.Enum01::class)
        NUCLEAR_REACTOR_CORE_TIER.registerBlockVariants(NuclearReactorCore.Enum02::class)

        MANIPULATOR_TIER.registerBlockVariants(Manipulator::class)
        SHIELDING_CORE_TIER.registerBlockVariants(ShieldingCore::class)

        ACCELERATION_TRACK_TIER.registerBlockVariants(AccelerationTrack::class)

        CRUCIBLE_STATS.registerBlockVariants(Crucible::class)

        CLEANROOM_CASING_TIER.registerAll(GTCleanroomCasing.FILTER_CASING.state to 1,
                                          GTCleanroomCasing.FILTER_CASING_STERILE.state to 2)

        for (type in WireCoil.entries)
        {
            GregTechAPI.HEATING_COILS.put(type.state, type)
        }

    }

    // @formatter:on

}
