package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.capability.IControllable
import gregtech.api.capability.IWorkable
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.util.GTUtility
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos

abstract class ExtendableMultiblockBase<T: ExtendableMultiblockBase<T>>(metaTileEntityId: ResourceLocation) :
    MultiblockWithDisplayBase(metaTileEntityId), IWorkable, IControllable
{
    protected val additionalStructureManager: AdditionalStructureManager<T> = AdditionalStructureManager(this)

    fun removeAdditional(pos: BlockPos) {
        additionalStructureManager.remove(pos)
    }

    fun addAdditional(additionalMultiblockBase: AdditionalMultiblockBase<T>) {
        additionalStructureManager.add(additionalMultiblockBase)
    }

    override fun writeToNBT(data: NBTTagCompound?): NBTTagCompound?
    {
        data?.setTag("Additional", additionalStructureManager.serialize())
        return super.writeToNBT(data)
    }

    override fun readFromNBT(data: NBTTagCompound?) {
        super.readFromNBT(data)
        data?.getCompoundTag("Additional")?.let {
            additionalStructureManager.deserialize(it)
                .map { pos -> GTUtility.getMetaTileEntity(world, pos) }
                .filterIsInstance<AdditionalMultiblockBase<T>>()
                .forEach { additionalMultiblockBase ->
                    additionalMultiblockBase.connect(this)
                }
        }
    }
}