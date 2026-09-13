package gregtechlite.gtlitecore.api.capability.handler

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable

/**
 * A dynamic instance for [gregtech.api.capability.impl.ItemHandlerList].
 *
 * All slot offsets are resolved on every call instead of computed once in constructor.
 *
 * @param handlers The backing handlers in slot order.
 */
class DynamicItemHandlerList(vararg handlers: IItemHandler) : IItemHandlerModifiable
{
    private val handlers: List<IItemHandler> = handlers.toList()

    private fun handlerFor(slot: Int): Pair<IItemHandler, Int>?
    {
        if (slot < 0) return null
        var remaining = slot
        for (handler in handlers)
        {
            val size = handler.slots
            if (remaining < size)
                return handler to remaining
            remaining -= size
        }
        return null
    }

    override fun getSlots(): Int = handlers.sumOf { it.slots }

    override fun getStackInSlot(slot: Int): ItemStack
        = handlerFor(slot)?.let { (handler, index) -> handler.getStackInSlot(index) } ?: ItemStack.EMPTY

    override fun setStackInSlot(slot: Int, stack: ItemStack)
    {
        val (handler, index) = handlerFor(slot) ?: return
        if (handler is IItemHandlerModifiable) handler.setStackInSlot(index, stack)
    }

    override fun getSlotLimit(slot: Int): Int
        = handlerFor(slot)?.let { (handler, index) -> handler.getSlotLimit(index) } ?: 0

    override fun isItemValid(slot: Int, stack: ItemStack): Boolean
        = handlerFor(slot)?.let { (handler, index) -> handler.isItemValid(index, stack) } ?: false

    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack
        = handlerFor(slot)?.let { (handler, index) -> handler.insertItem(index, stack, simulate) } ?: stack

    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack
        = handlerFor(slot)?.let { (handler, index) -> handler.extractItem(index, amount, simulate) } ?: ItemStack.EMPTY
}
