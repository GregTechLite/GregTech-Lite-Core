package gregtechlite.gtlitecore.integration.jei.group

import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.api.extension.stack
import mezz.jei.api.ICollapsibleGroupRegistry
import net.minecraft.item.ItemStack

fun ICollapsibleGroupRegistry.addGroup(id: String, valueItems: Array<MetaItem<*>.MetaValueItem?>): Unit
    = addGroup(id, valueItems.mapNotNull { it?.stack() })

fun ICollapsibleGroupRegistry.addGroup(id: String, stacks: Collection<ItemStack>): Unit
    = newGroup("${MOD_ID}:$id", "${MOD_ID}.jei.group.$id").add(*stacks.toTypedArray()).build()

inline fun <reified T> ICollapsibleGroupRegistry.addCasingGroup(id: String) where T : Enum<T>, T : BlockVariant, T : StateTier
    = addGroup(id, enumValues<T>().sortedWith(StateTier.COMPARATOR).map { it.stack })