package gregtechlite.gtlitecore.api.extension

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.MetaOreDictItem
import gregtech.api.metatileentity.MetaTileEntity
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

// region Item Stack Copying

/**
 * Copies the [ItemStack] with new stack size.
 *
 * @param count The new stack size of the [ItemStack].
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
 * @param meta  The metadata of the [ItemStack].
 * @param count The new stack size of the [ItemStack].
 */
fun ItemStack.copy(meta: Int, count: Int = 1): ItemStack
{
    val stack = copy()
    stack.itemDamage = meta
    stack.count = count
    return stack
}

// endregion

// region Item Stack Shortcut

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

fun MetaItem<*>.MetaValueItem.stack(): ItemStack = getStack()

fun MetaItem<*>.MetaValueItem.getStack(count: Int = 1): ItemStack = getStackForm(count)

fun MetaItem<*>.MetaValueItem.addOreDicts(vararg oreDictNames: String): MetaItem<*>.MetaValueItem
{
    for (oreDictName in oreDictNames)
        addOreDict(oreDictName)
    return this
}

fun MetaOreDictItem.OreDictValueItem.stack(): ItemStack = getStack(1)

fun MetaOreDictItem.OreDictValueItem.getStack(count: Int): ItemStack = getItemStack(count)

fun Block.stack(): ItemStack = getStack(1)

fun Block.getStack(count: Int = 1, meta: Int = 0)
    = ItemStack(this, count, meta)

fun MetaTileEntity.stack(): ItemStack = getStack()

fun MetaTileEntity.getStack(count: Int = 1) = stackForm.copy(count)

// endregion

// region Item Stack Convert

/**
 * Transformed a [IBlockState] to [ItemStack] with [amount].
 */
fun IBlockState.toItem(amount: Int = 1): ItemStack
    = ItemStack(block, amount, block.getMetaFromState(this))

// endregion