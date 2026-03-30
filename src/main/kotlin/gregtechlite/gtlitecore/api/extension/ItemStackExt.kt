package gregtechlite.gtlitecore.api.extension

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

/**
 * Copies the [ItemStack] with new stack size.
 *
 * @param count New stack size of copying ItemStack.
 * @return      Copied ItemStack with a [count].
 */
fun ItemStack.copy(count: Int = 1): ItemStack
{
    val stack = copy()
    stack.count = count
    return stack
}

/**
 * Copies the [ItemStack] with new stack size.
 *
 * @param meta  Metadata of ItemStack.
 * @param count New stack size of copying ItemStack.
 * @return      Copied ItemStack with a [count] and [meta].
 */
fun ItemStack.copy(meta: Int, count: Int = 1): ItemStack
{
    val stack = copy()
    stack.itemDamage = meta
    stack.count = count
    return stack
}

/**
 * Gets the [ItemStack] of the standard [Item].
 *
 * @param meta  The metadata of the [ItemStack].
 * @param count The stack size of the [ItemStack].
 */
fun Item.stack(meta: Int = 0): ItemStack = getStack(meta = meta)

/**
 * Gets the [ItemStack] of the standard [Item].
 *
 * @param count The stack size of the [ItemStack].
 * @param meta  The metadata of the [ItemStack].
 */
fun Item.getStack(count: Int = 1, meta: Int = 0): ItemStack {
    return ItemStack(this, count, meta)
}