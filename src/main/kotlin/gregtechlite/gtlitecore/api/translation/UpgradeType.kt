package gregtechlite.gtlitecore.api.translation

/**
 * Used for mark block attributes which be upgradeable.
 *
 * @see MultiblockTooltipDSL.durationInfo
 * @see MultiblockTooltipDSL.parallelInfo
 */
enum class UpgradeType
{

    VOLTAGE_TIER,

    WIRE_COIL,

    MOTOR_CASING,
    PISTON_CASING,
    PUMP_CASING,
    CONVEYOR_CASING,
    ROBOT_ARM_CASING,
    EMITTER_CASING,
    SENSOR_CASING,
    FIELD_GEN_CASING,
    PROCESSOR_CASING,

    BOROSILICATE_GLASS

}