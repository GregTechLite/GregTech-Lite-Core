/**
 * GNU LGPL 3.0
 *
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
 */
package gregtechlite.gtlitecore.integration.jei.group

import gregtech.api.GregTechAPI
import gregtech.api.items.materialitem.MetaPrefixItem
import gregtech.api.metatileentity.ITieredMetaTileEntity
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.extension.unzipSubBlocks
import gregtechlite.gtlitecore.api.extension.unzipSubVariants
import gregtechlite.gtlitecore.mixins.hooks.Implemented
import mezz.jei.api.ICollapsibleGroupRegistry

@Implemented(at = ["https://github.com/MCTian-mi/SussyPatches/blob/main/src/main/java/dev/tianmi/sussypatches/common/helper/CollapsibleGroups.java"])
object GTCollapsibleGroups
{
    internal fun registerGroup(registry: ICollapsibleGroupRegistry)
    {
        buildPrefixGroups(registry)
        buildMachineGroups(registry)
        buildBatteryBufferGroups(registry)
        registry.addGroup("cable", MetaBlocks.CABLES.unzipSubBlocks())
        registry.addGroup("item_pipe", MetaBlocks.ITEM_PIPES.unzipSubBlocks())
        registry.addGroup("fluid_pipe", MetaBlocks.FLUID_PIPES.unzipSubBlocks())
        registry.addGroup("ore", MetaBlocks.ORES.unzipSubBlocks())
        registry.addGroup("frame", MetaBlocks.FRAME_BLOCKS.unzipSubVariants())
        registry.addGroup("lamp", (MetaBlocks.LAMPS.values + MetaBlocks.BORDERLESS_LAMPS.values).unzipSubBlocks())
        registry.addGroup("material_block", MetaBlocks.COMPRESSED_BLOCKS.unzipSubVariants())
    }

    private fun buildPrefixGroups(registry: ICollapsibleGroupRegistry)
    {
        MetaItems.ITEMS
            .filterIsInstance<MetaPrefixItem>()
            .flatMap { item -> item.allItems.map { item.orePrefix to it.stack() } }
            .groupBy({ it.first }, { it.second })
            .forEach { prefix, stacks -> registry.addGroup("prefix.${prefix.name}", stacks) }
    }

    private fun buildMachineGroups(registry: ICollapsibleGroupRegistry)
    {
        GregTechAPI.mteManager.registries.forEach { mteRegistry ->
            mteRegistry.filterIsInstance<ITieredMetaTileEntity>()
                .filter { it.tierlessTooltipKey != it.metaName }
                .groupBy { it.tierlessTooltipKey }
                .forEach { (key, list) ->
                    registry.newGroup("${MOD_ID}:$key", "${MOD_ID}.jei.group.${key.substringAfter('.')}")
                        .add(*list.sortedBy { it.tier }.map { (it as MetaTileEntity).stack() }.toTypedArray()).build()
                }
        }
    }

    private fun buildBatteryBufferGroups(registry: ICollapsibleGroupRegistry)
    {
        MetaTileEntities.BATTERY_BUFFER
            .flatMap { it.filterNotNull() }
            .groupBy { it.metaName.substringAfterLast('.') }
            .forEach { (key, list) ->
                val _key = "machine.battery_buffer.$key"
                registry.newGroup("${MOD_ID}:${_key}", "${MOD_ID}.jei.group.${_key}")
                    .add(*list.sortedBy { it.tier }.map { it.stack() }.toTypedArray()).build()
            }
    }
}