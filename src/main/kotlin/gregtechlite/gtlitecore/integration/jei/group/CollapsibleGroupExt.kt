package gregtechlite.gtlitecore.integration.jei.group

import gregtech.api.items.metaitem.MetaItem
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.extension.stack
import mezz.jei.api.ICollapsibleGroupRegistry
import net.minecraft.item.ItemStack

fun ICollapsibleGroupRegistry.addGroup(id: String, valueItems: Array<MetaItem<*>.MetaValueItem?>): Unit
    = addGroup(id, valueItems.mapNotNull { it?.stack() })

fun ICollapsibleGroupRegistry.addGroup(id: String, stacks: Collection<ItemStack>): Unit
    = newGroup("${MOD_ID}:$id", "${MOD_ID}.jei.group.$id").add(*stacks.toTypedArray()).build()