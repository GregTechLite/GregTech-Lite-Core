package gregtechlite.gtlitecore.api.pattern

import gregtech.api.GTValues
import gregtech.api.block.VariantActiveBlock
import gregtech.api.capability.GregtechCapabilities
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.PatternStringError
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.util.BlockInfo
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import java.util.*

object TraceabilityPredicates
{

    @JvmStatic
    val SNOW_LAYER = TraceabilityPredicate { bws -> GTUtility.isBlockSnow(bws.blockState) }

    // region Block Attribute Suitable Tiered Stats

    @JvmStatic
    fun motorCasings() = tierBlock(GTLiteAPI.MOTOR_CASING_TIER)

    @JvmStatic
    fun pistonCasings() = tierBlock(GTLiteAPI.PISTON_CASING_TIER)

    @JvmStatic
    fun pumpCasings() = tierBlock(GTLiteAPI.PUMP_CASING_TIER)

    @JvmStatic
    fun conveyorCasings() = tierBlock(GTLiteAPI.CONVEYOR_CASING_TIER)

    @JvmStatic
    fun robotArmCasings() = tierBlock(GTLiteAPI.ROBOT_ARM_CASING_TIER)

    @JvmStatic
    fun emitterCasings() = tierBlock(GTLiteAPI.EMITTER_CASING_TIER)

    @JvmStatic
    fun sensorCasings() = tierBlock(GTLiteAPI.SENSOR_CASING_TIER)

    @JvmStatic
    fun fieldGenCasings() = tierBlock(GTLiteAPI.FIELD_GEN_CASING_TIER)

    @JvmStatic
    fun processorCasings() = tierBlock(GTLiteAPI.PROCESSOR_CASING_TIER)

    @JvmStatic
    fun borosilicateGlasses() = tierBlock(GTLiteAPI.BOROSILICATE_GLASS_TIER)

    @JvmStatic
    fun fusionCasings() = tierBlock(GTLiteAPI.FUSION_CASING_TIER)

    @JvmStatic
    fun fusionCoils() = tierBlock(GTLiteAPI.FUSION_COIL_TIER)

    @JvmStatic
    fun cryostats() = tierBlock(GTLiteAPI.CRYOSTAT_TIER)

    @JvmStatic
    fun divertors() = tierBlock(GTLiteAPI.DIVERTOR_TIER)

    @JvmStatic
    fun fusionVacuums() = tierBlock(GTLiteAPI.VACUUM_TIER)

    @JvmStatic
    fun componentCasings() = tierBlock(GTLiteAPI.COMPONENT_CASING_TIER)

    @JvmStatic
    fun nuclearReactorCores() = tierBlock(GTLiteAPI.NUCLEAR_REACTOR_CORE_TIER)

    @JvmStatic
    fun manipulators() = tierBlock(GTLiteAPI.MANIPULATOR_TIER)

    @JvmStatic
    fun shieldingCores() = tierBlock(GTLiteAPI.SHIELDING_CORE_TIER)

    @JvmStatic
    fun accelerationTracks() = tierBlock(GTLiteAPI.ACCELERATION_TRACK_TIER)

    @JvmStatic
    fun eohSpacetimeFieldGens() = tierBlock(GTLiteAPI.EOH_SPACETIME_FIELD_GEN_TIER)

    @JvmStatic
    fun standardSpacetimeFieldGens() = tierBlock(GTLiteAPI.STANDARD_SPACETIME_FIELD_GEN_TIER)

    @JvmStatic
    fun eohTimeAccelerationFieldGens() = tierBlock(GTLiteAPI.EOH_TIME_ACCELERATION_FIELD_GEN_TIER)

    @JvmStatic
    fun standardTimeAccelerationFieldGens() = tierBlock(GTLiteAPI.STANDARD_TIME_ACCELERATION_FIELD_GEN_TIER)

    @JvmStatic
    fun eohStabilizationFieldGens() = tierBlock(GTLiteAPI.EOH_STABILIZATION_FIELD_GEN_TIER)

    @JvmStatic
    fun standardStabilizationFieldGens() = tierBlock(GTLiteAPI.STANDARD_STABILIZATION_FIELD_GEN_TIER)

    @JvmStatic
    fun cleanroomCasings() = tierBlock(GTLiteAPI.CLEANROOM_CASING_TIER)

    @JvmStatic
    fun coils() = tierBlock(GTLiteAPI.COIL_TIER)

    // endregion

    // region Block Attribute Getter

    @JvmStatic
    fun tierBlock(registry: BlockAttributeRegistry<*>,
                  candidateFilter: (IBlockState) -> Boolean = { true },
                  errorKey: String = "gregtech.multiblock.pattern.error.casing") = TraceabilityPredicate().apply {
        common.add(GTLitePredicate(
            { worldState ->
                val blockState = worldState.blockState
                val context = worldState.matchContext
                registry.getAttribute(blockState)?.let {
                    if (context.getOrPut(registry.name, it) == it)
                    {
                        if (blockState.getBlock() is VariantActiveBlock<*>)
                        {
                            context.getOrCreate("VABlock") { LinkedList<BlockPos>() }.add(worldState.pos)
                        }
                        true
                    }
                    else
                    {
                        worldState.setError(PatternStringError(errorKey))
                        false
                    }
                } ?: false
            },
            lazy {
                registry.ascendingBlocks.filter(candidateFilter).map(::BlockInfo).toTypedArray()
            }::value, true
        ))
    }

    @JvmStatic
    fun <T> PatternMatchContext.getAttributeOrDefault(registry: BlockAttributeRegistry<T>, default: T): T
    {
        return getOrDefault(registry.name, default)
    }

    @JvmStatic
    fun <T : StateTier> PatternMatchContext.getTierOrDefault(registry: BlockAttributeRegistry<T>, default: Int): Int
    {
        return getOrDefault<T>(registry.name, null)?.tier ?: default
    }

    // endregion

    // region Specified Block Counter

    @JvmStatic
    fun airCounter() = counter("length", TraceabilityPredicate.AIR)

    @JvmStatic
    fun counter(name: String, delegate: TraceabilityPredicate) = object : TraceabilityPredicate(delegate)
    {

        override fun test(blockWorldState: BlockWorldState?) = super.test(blockWorldState).also {
            if (it) blockWorldState!!.matchContext.increment(name, 1)
        }

    }

    // endregion

    @JvmStatic
    fun energyOutputPredicate(voltageTier: Int): TraceabilityPredicate
    {
        val abilities = MultiblockAbility.REGISTRY[MultiblockAbility.OUTPUT_ENERGY]
        abilities!!.addAll(MultiblockAbility.REGISTRY[MultiblockAbility.SUBSTATION_OUTPUT_ENERGY]!!)
        return MultiblockWithDisplayBase.metaTileEntities(*abilities
                .filter { mte ->
                    val container = mte.getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)
                    container != null && container.outputVoltage <= GTValues.V[voltageTier]
                }.toTypedArray())
            .addTooltip("gregtech.multiblock.pattern.error.limited.1", GTValues.VN[GTValues.LV])
            .addTooltip("gregtech.multiblock.pattern.error.limited.0", GTValues.VN[voltageTier])
    }

    @JvmStatic
    fun optionalStates(symbol: String, vararg allowedStates: IBlockState) = TraceabilityPredicate { blockWorldState ->
        val state: IBlockState = blockWorldState.blockState
        if (state.block is VariantActiveBlock<*>)
        {
            blockWorldState.matchContext.getOrPut("VABlock", LinkedList<BlockPos>())
                .add(blockWorldState.pos)
        }
        if (allowedStates.contains(state))
            return@TraceabilityPredicate blockWorldState.matchContext.getOrPut(symbol, true)
        return@TraceabilityPredicate blockWorldState.matchContext.get<String>(symbol) == null
    }.also { allowedStates.map(::BlockInfo) }

}