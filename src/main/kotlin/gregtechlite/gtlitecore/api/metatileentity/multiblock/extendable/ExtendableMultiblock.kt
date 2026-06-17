package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.capability.IMultiblockController
import net.minecraft.util.math.BlockPos

interface ExtendableMultiblock<T: ExtendableMultiblock<T>>: IMultiblockController
{
    fun getAdditionalStructureManager(): AdditionalStructureManager<T>

    fun isWorkingEnabled(): Boolean

    fun addAdditional(additionalMultiblockBase: AdditionalMultiblockBase<T>) {
        getAdditionalStructureManager().add(additionalMultiblockBase)
    }

    fun removeAdditional(pos: BlockPos)
    {
        getAdditionalStructureManager().remove(pos)
    }
}