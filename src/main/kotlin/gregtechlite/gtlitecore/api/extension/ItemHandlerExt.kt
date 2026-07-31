package gregtechlite.gtlitecore.api.extension

import gregtech.api.util.GTHashMaps
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandlerModifiable

fun IItemHandlerModifiable.collapseInventorySlotContents()
{
    // Gather a snapshot of the provided inventory.
    val inventoryContents = GTHashMaps.fromItemHandler(this, true)
    val inventoryItemContents = arrayListOf<ItemStack>()

    // Populate the list of item stacks in the inventory with apportioned item stacks, for easy replacement.
    for (e in inventoryContents.object2IntEntrySet())
    {
        val stack = e.key
        var count = e.intValue
        val maxStackSize = stack.maxStackSize
        while (count >= maxStackSize)
        {
            val copy = stack.copy(maxStackSize)
            inventoryItemContents.add(copy)
            count -= maxStackSize
        }
        if (count > 0)
        {
            val copy = stack.copy(count)
            inventoryItemContents.add(copy)
        }
    }

    for (i in 0 ..< getSlots())
    {
        val stackToMove: ItemStack
        // Ensure that we are not exceeding the List size when attempting to populate items.
        if (i >= inventoryItemContents.size)
        {
            stackToMove = ItemStack.EMPTY
        }
        else
        {
            stackToMove = inventoryItemContents[i]
        }
        // Populate the slots.
        setStackInSlot(i, stackToMove)
    }
}