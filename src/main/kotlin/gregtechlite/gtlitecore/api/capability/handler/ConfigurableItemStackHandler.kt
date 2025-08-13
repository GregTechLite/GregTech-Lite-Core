package gregtechlite.gtlitecore.api.capability.handler

import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.metatileentity.MetaTileEntity
import gregtechlite.gtlitecore.api.extension.copy
import net.minecraft.item.ItemStack

/**
 * A capacity-configurable [gregtech.api.capability.impl.NotifiableItemStackHandler] for MTEs.
 *
 * This `ItemStackHandler` can modify the capacity of each slot in [mte] just like `NotifiableFluidTank`.
 */
open class ConfigurableItemStackHandler(mte: MetaTileEntity?,
                                        slotSize: Int,
                                        entityToNotify: MetaTileEntity?,
                                        isExport: Boolean,
                                        private val slotCapacity: () -> Int)
    : NotifiableItemStackHandler(mte, slotSize, entityToNotify, isExport)
{

    constructor(mte: MetaTileEntity?, slotSize: Int, entityToNotify: MetaTileEntity?, isExport: Boolean) : this(mte, slotSize, entityToNotify, isExport, { Int.MAX_VALUE })

    override fun getSlotLimit(slot: Int): Int = slotCapacity.invoke()

    override fun getStackLimit(slot: Int, stack: ItemStack): Int = getSlotLimit(slot)

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack
    {
        if (amount == 0) return ItemStack.EMPTY
        validateSlotIndex(slot)

        val existedStack = stacks[slot]
        if (existedStack.isEmpty) return ItemStack.EMPTY
        if (existedStack.count <= amount)
        {
            if (!simulate)
            {
                stacks[slot] = ItemStack.EMPTY
                onContentsChanged(slot)
            }
            return existedStack
        }
        else
        {
            if (!simulate)
            {
                stacks[slot] = existedStack.copy(existedStack.count - amount)
                onContentsChanged(slot)
            }
            return existedStack.copy(amount)
        }
    }

}