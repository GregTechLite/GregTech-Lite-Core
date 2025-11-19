package gregtechlite.gtlitecore.api.extension

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.MetaOreDictItem
import net.minecraft.item.ItemStack

fun MetaItem<*>.MetaValueItem.stack(): ItemStack
    = getStack(1)

fun MetaItem<*>.MetaValueItem.getStack(count: Int): ItemStack
    = getStackForm(count)

fun MetaItem<*>.MetaValueItem.addOreDicts(vararg oreDictNames: String): MetaItem<*>.MetaValueItem
{
    for (oreDictName in oreDictNames)
        addOreDict(oreDictName)
    return this
}

fun MetaOreDictItem.OreDictValueItem.stack(): ItemStack
    = getStack(1)

fun MetaOreDictItem.OreDictValueItem.getStack(count: Int): ItemStack
    = getItemStack(count)