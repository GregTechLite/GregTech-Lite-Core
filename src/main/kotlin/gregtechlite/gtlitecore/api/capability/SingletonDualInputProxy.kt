package gregtechlite.gtlitecore.api.capability

import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import java.util.Optional

interface SingletonDualInputProxy
{
    /**
     * @return Returns `true` if new items and/or fluids have been inserted into the
     *         inventories since the last call which will trigger a new recipe check.
     */
    fun shouldUpdate(): Boolean

    fun inventories(): Iterator<SingletonDualInputInventory>

    fun getFirstNonEmptyInventory(): Optional<SingletonDualInputInventory>

    fun supportFluids(): Boolean

    /**
     * Misc items in extra slots, e.g. lens, shape molds, e.t.c.
     */
    fun getSharedItems(): Array<ItemStack>

    fun getAllItems(): Array<ItemStack>
    {
        val result = arrayListOf<ItemStack>()
        for (item in getSharedItems())
            result.add(item)

        val iterator = inventories()
        while (iterator.hasNext())
        {
            for (item in iterator.next().getItemInputs())
                result.add(item)
        }
        return result.toTypedArray()
    }

    fun getAllFluids(): Array<FluidStack>
    {
        val result = arrayListOf<FluidStack>()
        val iterator = inventories()
        while (iterator.hasNext())
        {
            for (fluid in iterator.next().getFluidInputs())
                result.add(fluid)
        }
        return result.toTypedArray()
    }
}
