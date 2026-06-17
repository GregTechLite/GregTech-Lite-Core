package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.recipe

import gregtech.api.capability.IControllable
import gregtech.api.capability.IWorkable
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalStructureManager
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.ExtendableMultiblock
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation

abstract class RecipeMapExtendableMultiblock<T: RecipeMapExtendableMultiblock<T>>(metaTileEntityId: ResourceLocation, recipeMap: RecipeMap<*>)
    : RecipeMapMultiblockController(metaTileEntityId, recipeMap), IWorkable, IControllable, ExtendableMultiblock<T>
{
    protected val additionalStructureManager: AdditionalStructureManager<T> = AdditionalStructureManager(this)
    override fun getAdditionalStructureManager(): AdditionalStructureManager<T> = additionalStructureManager

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