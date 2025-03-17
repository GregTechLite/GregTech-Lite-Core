package magicbook.gtlitecore.api.utils

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.MetaTileEntityHolder
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.util.BlockInfo
import magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import java.util.*
import java.util.function.Supplier


@Suppress("MISSING_DEPENDENCY_CLASS", "MISSING_DEPENDENCY_SUPERCLASS")
class StructureUtility
{

    companion object
    {

        @JvmStatic
        fun getCandidates(vararg allowedStates: IBlockState) = Supplier {
            allowedStates.map { state -> BlockInfo(state, null) }.toTypedArray()
        }

        /**
         * Get [MetaTileEntityHolder] via [MetaTileEntity].
         */
        @JvmStatic
        fun getMetaTileEntity(mte: MetaTileEntity): MetaTileEntityHolder
        {
            val holder = MetaTileEntityHolder()
            holder.setMetaTileEntity(mte)
            holder.metaTileEntity.onPlacement()
            holder.metaTileEntity.frontFacing = EnumFacing.SOUTH
            return holder
        }

        /**
         * Get [MetaTileEntityHolder] via [MetaTileEntity] and set [placer] to its owner.
         */
        @JvmStatic
        fun getMetaTileEntity(mte: MetaTileEntity, placer: EntityLivingBase): MetaTileEntityHolder
        {
            val holder = MetaTileEntityHolder()
            holder.setMetaTileEntity(mte)
            holder.metaTileEntity.onPlacement(placer)
            holder.metaTileEntity.frontFacing = EnumFacing.SOUTH
            return holder
        }

        // =============================================================================================================
        @JvmStatic
        fun motorCasings() = GTLiteTraceabilityPredicate.MOTOR_CASING.get()

        @JvmStatic
        fun pistonCasings() = GTLiteTraceabilityPredicate.PISTON_CASING.get()

        @JvmStatic
        fun pumpCasings() = GTLiteTraceabilityPredicate.PUMP_CASING.get()

        @JvmStatic
        fun conveyorCasings() = GTLiteTraceabilityPredicate.CONVEYOR_CASING.get()

        @JvmStatic
        fun robotArmCasings() = GTLiteTraceabilityPredicate.ROBOT_ARM_CASING.get()

        @JvmStatic
        fun emitterCasings() = GTLiteTraceabilityPredicate.EMITTER_CASING.get()

        @JvmStatic
        fun sensorCasings() = GTLiteTraceabilityPredicate.SENSOR_CASING.get()

        @JvmStatic
        fun fieldGenCasings() = GTLiteTraceabilityPredicate.FIELD_GEN_CASING.get()

        @JvmStatic
        fun processorCasings() = GTLiteTraceabilityPredicate.PROCESSOR_CASING.get()
    }

}