package gregtechlite.gtlitecore.api.capability.handler

import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class SingletonDualInputHandler(@JvmField var inputItems: Array<ItemStack>,
                                @JvmField var inputFluid: Array<FluidStack>)
{
    companion object
    {
        @JvmField
        val EMPTY_ITEMS: Array<ItemStack> = emptyArray()

        @JvmField
        val EMPTY_FLUIDS: Array<FluidStack> = emptyArray()
    }

    constructor() : this(EMPTY_ITEMS, EMPTY_FLUIDS)
}