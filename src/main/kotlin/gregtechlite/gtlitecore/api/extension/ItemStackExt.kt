package gregtechlite.gtlitecore.api.extension

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