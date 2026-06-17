package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.capability.IControllable
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import net.minecraft.util.ResourceLocation

abstract class AdditionalMultiblockBase<T : ExtendableMultiblock<T>>(metaTileEntityId: ResourceLocation)
    : MultiblockWithDisplayBase(metaTileEntityId), IControllable
{
    protected var mainController: ExtendableMultiblock<T>? = null

    override fun hasMaintenanceMechanics() = false
    override fun isWorkingEnabled() = isStructureFormed && isConnected()

    protected fun isConnected() = mainController != null && mainController!!.isWorkingEnabled()

    fun connect(controller: ExtendableMultiblock<T>)
    {
        mainController?.removeAdditional(pos)
        mainController = controller
        mainController?.addAdditional(this)
    }
}