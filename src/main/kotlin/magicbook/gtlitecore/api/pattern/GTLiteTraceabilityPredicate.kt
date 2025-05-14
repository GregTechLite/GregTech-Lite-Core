package magicbook.gtlitecore.api.pattern

import gregtech.api.GTValues
import gregtech.api.block.VariantActiveBlock
import gregtech.api.capability.GregtechCapabilities
import gregtech.api.capability.IEnergyContainer
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.pattern.TraceabilityPredicate
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.pattern.predicate.TierTraceabilityPredicate
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.getCandidates
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import java.util.*
import java.util.function.Supplier

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteTraceabilityPredicate
{

    companion object
    {

        @JvmStatic
        var MOTOR_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_MOTOR_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_MOTOR_CASING[s] as WrappedIntTier).getIntTier() },
            "MotorCasing", null)
        }

        @JvmStatic
        var PISTON_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_PISTON_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_PISTON_CASING[s] as WrappedIntTier).getIntTier() },
            "PistonCasing", null)
        }

        @JvmStatic
        var PUMP_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_PUMP_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_PUMP_CASING[s] as WrappedIntTier).getIntTier() },
            "PumpCasing", null)
        }

        @JvmStatic
        var CONVEYOR_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_CONVEYOR_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_CONVEYOR_CASING[s] as WrappedIntTier).getIntTier() },
            "ConveyorCasing", null)
        }

        @JvmStatic
        var ROBOT_ARM_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_ROBOT_ARM_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_ROBOT_ARM_CASING[s] as WrappedIntTier).getIntTier() },
            "RobotArmCasing", null)
        }

        @JvmStatic
        var EMITTER_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_EMITTER_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_EMITTER_CASING[s] as WrappedIntTier).getIntTier() },
            "EmitterCasing", null)
        }

        @JvmStatic
        var SENSOR_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_SENSOR_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_SENSOR_CASING[s] as WrappedIntTier).getIntTier() },
            "SensorCasing", null)
        }

        @JvmStatic
        var FIELD_GEN_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_FIELD_GEN_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_FIELD_GEN_CASING[s] as WrappedIntTier).getIntTier() },
            "FieldGenCasing", null)
        }

        @JvmStatic
        var PROCESSOR_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_PROCESSOR_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_PROCESSOR_CASING[s] as WrappedIntTier).getIntTier() },
            "ProcessorCasing", null) }

        @JvmStatic
        var BOROSILICATE_GLASS = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_BOROSILICATE_GLASS,
            Comparator.comparing { s -> (GTLiteAPI.MAP_BOROSILICATE_GLASS[s] as WrappedIntTier).getIntTier() },
            "BorosilicateGlass", null) }

        @JvmStatic
        var FUSION_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_FUSION_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_FUSION_CASING[s] as WrappedIntTier).getIntTier() },
            "FusionCasing", null) }

        @JvmStatic
        var FUSION_COIL = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_FUSION_COIL,
            Comparator.comparing { s -> (GTLiteAPI.MAP_FUSION_COIL[s] as WrappedIntTier).getIntTier() },
            "FusionCoil", null) }

        @JvmStatic
        var CRYOSTAT = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_CRYOSTAT,
            Comparator.comparing { s -> (GTLiteAPI.MAP_CRYOSTAT[s] as WrappedIntTier).getIntTier() },
            "Cryostat", null) }

        @JvmStatic
        var DIVERTOR = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_DIVERTOR,
            Comparator.comparing { s -> (GTLiteAPI.MAP_DIVERTOR[s] as WrappedIntTier).getIntTier() },
            "Divertor", null) }

        @JvmStatic
        var VACUUM = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_VACUUM,
            Comparator.comparing { s -> (GTLiteAPI.MAP_VACUUM[s] as WrappedIntTier).getIntTier() },
            "Vacuum", null) }

        @JvmStatic
        var COMPONENT_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_COMPONENT_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_COMPONENT_CASING[s] as WrappedIntTier).getIntTier() },
            "ComponentAssemblyCasing", null) }

        @JvmStatic
        var NUCLEAR_REACTOR_CORE = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_NUCLEAR_REACTOR_CORE,
            Comparator.comparing { s -> (GTLiteAPI.MAP_NUCLEAR_REACTOR_CORE[s] as WrappedIntTier).getIntTier() },
            "NuclearReactorCore", null) }

        // Overriden of original Cleanroom Casing by tiered stats predicate.
        @JvmField
        var CLEANROOM_CASING = Supplier { TierTraceabilityPredicate(GTLiteAPI.MAP_CLEANROOM_CASING,
            Comparator.comparing { s -> (GTLiteAPI.MAP_CLEANROOM_CASING[s] as WrappedIntTier).getIntTier() },
            "CleanroomCasing", null) }

        /**
         * The scalable indicator predicate used in `state(symbol, ... )` part.
         *
         * This predicate implement a counter for length of repeatable layer in multiblock
         * structure. If the block state in target indexed state is air, then increment the
         * pattern context tag `length` by +1. For example, there is a structure pattern in
         * default MTE `createStructurePattern()` method overriden:
         * ```
         * aisle("CCC", "CCC", "CCC")
         * aisle("CCC", "CIC", "CCC").setRepeatable(..., ...) // (startLayer, endLayer)
         * aisle("CCC", "CSC", "CCC")
         * ```
         * - Layer 1 (repeat once):
         * ```
         * aisle("CCC", "CCC", "CCC")
         * aisle("CCC", "CIC", "CCC") // I: air, so "length" +1.
         * aisle("CCC", "CSC", "CCC")
         * ```
         * - Layer 2 (repeat twice):
         * ```
         * aisle("CCC", "CCC", "CCC")
         * aisle("CCC", "CIC", "CCC") // I: air, and then
         * aisle("CCC", "CIC", "CCC") // this I is also air, so "length" +2.
         * aisle("CCC", "CSC", "CCC")
         * ```
         * - ... (repeat n)
         *
         * In other words, this predicate is generalization of a part-function-same methods
         * in distillation tower multiblock predicates.
         *
         * @author Magic_Sweepy
         *
         * @see gregtech.api.metatileentity.multiblock.MultiblockControllerBase.createStructurePattern
         * @see gregtech.api.metatileentity.multiblock.MultiblockControllerBase.states
         * @see gregtech.api.pattern.FactoryBlockPattern.setRepeatable
         */
        @JvmStatic
        fun scaleIndicatorPredicate() = scaleIndicatorPredicate("length")

        @JvmStatic
        fun scaleIndicatorPredicate(contextName: String) = TraceabilityPredicate { blockWorldState ->
            if (TraceabilityPredicate.AIR.test(blockWorldState))
            {
                blockWorldState.matchContext.increment(contextName, 1)
                return@TraceabilityPredicate true
            }
            else
            {
                return@TraceabilityPredicate false
            }
        }

        /**
         * The scalable indicator predicate used in `state(symbol, ... )` part.
         *
         * This predicate implement a counter for length of repeatable layer in multiblock
         * structure. If the block state in target indexed state is `state`, then increment the
         * pattern context tag `length` by +1. For example, there is a structure pattern in
         * default MTE `createStructurePattern()` method overriden:
         * ```
         * aisle("CCC", "CCC", "CCC")
         * aisle("CCC", "CIC", "CCC").setRepeatable(..., ...) // (startLayer, endLayer)
         * aisle("CCC", "CSC", "CCC")
         * ```
         * - Layer 1 (repeat once):
         * ```
         * aisle("CCC", "CCC", "CCC")
         * aisle("CCC", "CIC", "CCC") // I: state, so "length" +1.
         * aisle("CCC", "CSC", "CCC")
         * ```
         * - Layer 2 (repeat twice):
         * ```
         * aisle("CCC", "CCC", "CCC")
         * aisle("CCC", "CIC", "CCC") // I: state, and then
         * aisle("CCC", "CIC", "CCC") // this I is also state, so "length" +2.
         * aisle("CCC", "CSC", "CCC")
         * ```
         * - ... (repeat n)
         *
         * In other words, this predicate is generalization of a part-function-same methods
         * in distillation tower multiblock predicates.
         *
         * @author Magic_Sweepy
         *
         * @see gregtech.api.metatileentity.multiblock.MultiblockControllerBase.createStructurePattern
         * @see gregtech.api.metatileentity.multiblock.MultiblockControllerBase.states
         * @see gregtech.api.pattern.FactoryBlockPattern.setRepeatable
         */
        @JvmStatic
        fun scaleIndicatorPredicate(state: IBlockState) = scaleIndicatorPredicate("length")

        @JvmStatic
        fun scaleIndicatorPredicate(state: IBlockState, contextName: String) = TraceabilityPredicate { blockWorldState ->
            if (MultiblockControllerBase.states(state).test(blockWorldState))
            {
                blockWorldState.matchContext.increment(contextName, 1)
                return@TraceabilityPredicate true
            }
            else
            {
                return@TraceabilityPredicate false
            }
        }

        /**
         * Energy output hatches predicate with [voltageTier] restrict.
         *
         * @param voltageTier Maximum voltage tier of the energy output hatches allowed.
         *
         * @author Magic_Sweepy
         */
        @JvmStatic
        fun energyOutputPredicate(voltageTier: Int): TraceabilityPredicate
        {
            val abilities = MultiblockAbility.REGISTRY[MultiblockAbility.OUTPUT_ENERGY]
            abilities!!.addAll(MultiblockAbility.REGISTRY[MultiblockAbility.SUBSTATION_OUTPUT_ENERGY]!!)
            return MultiblockWithDisplayBase.metaTileEntities(*abilities
                .filter { mte ->
                    val container = mte.getCapability<IEnergyContainer>(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null)
                    container != null && container.outputVoltage <= GTValues.V[voltageTier]
                }.toTypedArray())
                .addTooltip("gregtech.multiblock.pattern.error.limited.1", GTValues.VN[GTValues.LV])
                .addTooltip("gregtech.multiblock.pattern.error.limited.0", GTValues.VN[voltageTier])
        }

        /**
         * Optional predicate of [MultiblockControllerBase.states], allowed to check states with
         * two blocks: one block and air.
         *
         * @param symbol        Symbol to get a `BlockInfo` in `formStructure()`.
         * @param allowedStates Allowed state of states checking.
         * @return              The `blockMatcher` param in `FactoryBlockPattern`.
         *
         * @author Gate Guardian
         */
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
        }.also { getCandidates(*allowedStates) }

    }

}