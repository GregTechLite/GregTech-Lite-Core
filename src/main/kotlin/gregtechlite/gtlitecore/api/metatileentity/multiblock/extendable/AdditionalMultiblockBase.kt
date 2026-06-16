package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.capability.IControllable
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import net.minecraft.util.ResourceLocation

abstract class AdditionalMultiblockBase<T : ExtendableMultiblockBase<T>>(metaTileEntityId: ResourceLocation)
    : MultiblockWithDisplayBase(metaTileEntityId), IControllable
{
    protected var mainController: ExtendableMultiblockBase<T>? = null

    override fun hasMaintenanceMechanics() = false
    override fun isWorkingEnabled() = isStructureFormed && isConnected()

    protected fun isConnected() = mainController != null && mainController!!.isWorkingEnabled

    fun connect(controller: ExtendableMultiblockBase<T>)
    {
        mainController = controller
    }

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    override fun invalidateStructure()
    {
        mainController?.removeAdditional(pos)
    }
}