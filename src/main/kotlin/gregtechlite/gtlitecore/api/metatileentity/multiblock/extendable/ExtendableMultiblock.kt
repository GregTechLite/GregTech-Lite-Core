package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.capability.IMultiblockController
import net.minecraft.util.math.BlockPos

interface ExtendableMultiblock<T: ExtendableMultiblock<T>>: IMultiblockController
{
    var additionalStructureManager: AdditionalStructureManager<T>
    val maintenanceProblem: Byte

    fun getPos(): BlockPos

    fun highlightController()

    fun isWorkingEnabled(): Boolean

    fun addAdditional(additionalMultiblockBase: AdditionalMultiblockBase<T>)
    {
        additionalStructureManager.add(additionalMultiblockBase)
    }

    fun removeAdditional(pos: BlockPos)
    {
        additionalStructureManager.remove(pos)
    }
}