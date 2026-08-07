/**
 * GNU LGPL 3.0
 * Copyright (C) MCTian-mi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package gregtechlite.gtlitecore.integration.jei.group

import gregtech.api.items.materialitem.MetaPrefixItem
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.unification.ore.OrePrefix
import gregtech.common.items.MetaItems
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.mixins.hooks.Implemented
import mezz.jei.api.ICollapsibleGroupRegistry
import net.minecraft.item.ItemStack

@Implemented(at = ["https://github.com/MCTian-mi/SussyPatches/blob/main/src/main/java/dev/tianmi/sussypatches/common/helper/CollapsibleGroups.java"])
object GTCollapsibleGroups
{
    internal fun registerGroup(registry: ICollapsibleGroupRegistry)
    {
        buildPrefixGroups(registry)
    }

    private fun buildPrefixGroups(registry: ICollapsibleGroupRegistry)
    {
        MetaItems.ITEMS
            .filterIsInstance<MetaPrefixItem>()
            .flatMap { item -> item.allItems.map { item.orePrefix to it.stack() } }
            .groupBy({ it.first }, { it.second })
            .forEach { prefix, stacks -> registry.addGroup("prefix.${prefix.name}", stacks) }
    }

    private fun ICollapsibleGroupRegistry.addGroup(id: String, valueItems: Array<MetaItem<*>.MetaValueItem?>): Unit
        = addGroup(id, valueItems.mapNotNull { it?.stack() })

    private fun ICollapsibleGroupRegistry.addGroup(id: String, stacks: Collection<ItemStack>): Unit
        = newGroup("${MOD_ID}:$id", "${MOD_ID}.jei.group.$id").add(*stacks.toTypedArray()).build()
}