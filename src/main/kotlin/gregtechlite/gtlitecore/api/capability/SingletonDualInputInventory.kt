package gregtechlite.gtlitecore.api.capability

import gregtech.api.capability.IMultipleTankHandler
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.items.IItemHandlerModifiable

interface SingletonDualInputInventory
{
    fun isEmpty(): Boolean

    fun getItemInputs(): Array<ItemStack>

    fun getFluidInputs(): Array<FluidStack>

    fun getConsumableItemHandler(): IItemHandlerModifiable

    fun getConsumableFluidHandler(): IMultipleTankHandler
}
