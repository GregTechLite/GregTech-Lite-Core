package gregtechlite.gtlitecore.api.capability.handler

import gregtechlite.gtlitecore.api.extension.copy
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandlerModifiable

/**
 * A dynamic instance for [gregtech.api.capability.impl.NotifiableItemStackHandler].
 *
 * Every read / write is forwarded to [stacks] itself.
 *
 * @param stacks The backing stack list.
 */
class DynamicNotifiableItemStackHandler(private val stacks: MutableList<ItemStack>) : IItemHandlerModifiable
{
    override fun getSlots(): Int = stacks.size

    override fun getStackInSlot(slot: Int): ItemStack
        = if (slot in stacks.indices) stacks[slot] else ItemStack.EMPTY

    override fun setStackInSlot(slot: Int, stack: ItemStack)
    {
        if (slot in stacks.indices)
            stacks[slot] = stack
    }

    override fun getSlotLimit(slot: Int): Int = 64

    override fun isItemValid(slot: Int, stack: ItemStack): Boolean = true

    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack
    {
        if (stack.isEmpty || slot !in stacks.indices)
            return stack

        val limit = getSlotLimit(slot)
        val existedStack = stacks[slot]

        if (!existedStack.isEmpty && !ItemStack.areItemsEqual(existedStack, stack)) return stack
        if (!existedStack.isEmpty && !ItemStack.areItemStackTagsEqual(existedStack, stack)) return stack

        val currentAmount = if (existedStack.isEmpty) 0 else existedStack.count
        val acceptAmount = minOf(limit - currentAmount, stack.count)
        if (acceptAmount <= 0) return stack

        if (!simulate)
        {
            if (existedStack.isEmpty)
            {
                stacks[slot] = stack.copy(acceptAmount)
            }
            else
            {
                existedStack.grow(acceptAmount)
            }
        }

        if (acceptAmount >= stack.count)
            return ItemStack.EMPTY
        return stack.copy(stack.count - acceptAmount)
    }

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack
    {
        if (amount <= 0 || slot !in stacks.indices)
            return ItemStack.EMPTY

        val existedStack = stacks[slot]
        if (existedStack.isEmpty) return ItemStack.EMPTY

        val extractAmount = minOf(amount, existedStack.count)
        if (!simulate)
        {
            if (extractAmount >= existedStack.count)
                stacks[slot] = ItemStack.EMPTY
            else
                existedStack.shrink(extractAmount)
        }
        return existedStack.copy(extractAmount)
    }

    fun isEmpty(): Boolean = stacks.all { it.isEmpty }
}
