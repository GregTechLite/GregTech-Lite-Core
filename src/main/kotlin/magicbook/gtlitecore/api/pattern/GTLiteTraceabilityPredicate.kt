package magicbook.gtlitecore.api.pattern

import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtech.api.pattern.TraceabilityPredicate
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.pattern.predicate.TierTraceabilityPredicate
import net.minecraft.block.state.IBlockState
import java.util.function.Supplier

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
         * @since 1.0.0
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
         * @since 1.0.0
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

    }

}