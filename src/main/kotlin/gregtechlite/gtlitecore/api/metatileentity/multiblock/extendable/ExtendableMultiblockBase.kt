package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.capability.IControllable
import gregtech.api.capability.IWorkable
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.util.GTUtility
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation

abstract class ExtendableMultiblockBase<T: ExtendableMultiblockBase<T>>(metaTileEntityId: ResourceLocation)
    : MultiblockWithDisplayBase(metaTileEntityId), IWorkable, IControllable, ExtendableMultiblock<T>
{
    override var additionalStructureManager: AdditionalStructureManager<T> = AdditionalStructureManager(this)

    override fun <A> getAbilities(ability: MultiblockAbility<A>): List<A>
    {
        return super.getAbilities(ability).also { it.addAll(additionalStructureManager.getAbilities(ability)) }
    }

    override fun writeToNBT(data: NBTTagCompound?): NBTTagCompound?
    {
        data?.setTag("Additional", additionalStructureManager.serialize())
        return super.writeToNBT(data)
    }

    override fun readFromNBT(data: NBTTagCompound?)
    {
        super.readFromNBT(data)
        data?.getCompoundTag("Additional")?.let {
            additionalStructureManager.deserialize(it)
                .map { pos -> GTUtility.getMetaTileEntity(world, pos) }
                .filterIsInstance<AdditionalMultiblockBase<T>>()
                .forEach { additionalMultiblockBase -> additionalMultiblockBase.connect(this) }
        }
    }
}