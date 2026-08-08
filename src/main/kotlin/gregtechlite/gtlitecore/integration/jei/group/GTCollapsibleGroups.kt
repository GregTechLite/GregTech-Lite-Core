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
import gregtech.common.items.MetaItem1
import gregtech.common.items.MetaItems
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.extension.unzipSubBlocks
import gregtechlite.gtlitecore.api.extension.unzipSubVariants
import gregtechlite.gtlitecore.mixins.hooks.Implemented
import mezz.jei.api.ICollapsibleGroupRegistry
import net.minecraft.item.ItemStack

/**
 * This class references `CollapsibleGroups` class in SussyPatches by [MCTian-mi](https://github.com/MCTian-mi),
 * after obtaining permission, we adapted it with current GTCEu version.
 */
@Implemented(at = ["https://github.com/MCTian-mi/SussyPatches/blob/main/src/main/java/dev/tianmi/sussypatches/common/helper/CollapsibleGroups.java"])
object GTCollapsibleGroups
{
    internal fun registerGroup(registry: ICollapsibleGroupRegistry)
    {
        buildPrefixGroups(registry)
        buildMachineGroups(registry)
        buildBatteryBufferGroups(registry)
        buildStorageGroups(registry)

        registry.addGroup("cable", MetaBlocks.CABLES.unzipSubBlocks())
        registry.addGroup("item_pipe", MetaBlocks.ITEM_PIPES.unzipSubBlocks())
        registry.addGroup("fluid_pipe", MetaBlocks.FLUID_PIPES.unzipSubBlocks())
        registry.addGroup("ore", MetaBlocks.ORES.unzipSubBlocks())
        registry.addGroup("frame", MetaBlocks.FRAME_BLOCKS.unzipSubVariants())
        registry.addGroup("lamp", (MetaBlocks.LAMPS.values + MetaBlocks.BORDERLESS_LAMPS.values).unzipSubBlocks())
        registry.addGroup("material_block", MetaBlocks.COMPRESSED_BLOCKS.unzipSubVariants())
        registry.addGroup("hermetic_casing", MetaBlocks.HERMETIC_CASING.unzipSubBlocks())
        registry.addGroup("battery_block", MetaBlocks.BATTERY_BLOCK.unzipSubBlocks())
        registry.addGroup("warning_sign", listOf(MetaBlocks.WARNING_SIGN, MetaBlocks.WARNING_SIGN_1).unzipSubBlocks())
        registry.addGroup("metal_sheet", listOf(MetaBlocks.METAL_SHEET, MetaBlocks.LARGE_METAL_SHEET).unzipSubBlocks())
        registry.addGroup("studs", MetaBlocks.STUDS.unzipSubBlocks())
        registry.addGroup("machine_casing", MetaBlocks.MACHINE_CASING.unzipSubBlocks())

        registry.addGroupBy("mold", "shape.mold")
        registry.addGroupBy("extruder", "shape.extruder")
        registry.addGroupBy("spray", "spray")
        registry.addGroupBy("voltage_coil", "voltage_coil")
        registry.addGroupBy("electric_motor", "electric.motor")
        registry.addGroupBy("electric_pump", "electric.pump")
        registry.addGroupBy("conveyor_module", "conveyor.module")
        registry.addGroupBy("electric_piston", "electric.piston")
        registry.addGroupBy("robot_arm", "robot.arm")
        registry.addGroupBy("emitter", "emitter")
        registry.addGroupBy("sensor", "sensor")
        registry.addGroupBy("field_generator", "field.generator")
        registry.addGroupBy("fluid_regulator", "fluid.regulator")
        registry.addGroupBy("dye", "dye")
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

    private fun buildStorageGroups(registry: ICollapsibleGroupRegistry)
    {
        registry.addGroup("drum", arrayOf(MetaTileEntities.WOODEN_DRUM, MetaTileEntities.BRONZE_DRUM,
            MetaTileEntities.STEEL_DRUM, MetaTileEntities.ALUMINIUM_DRUM, MetaTileEntities.STAINLESS_STEEL_DRUM,
            MetaTileEntities.TITANIUM_DRUM, MetaTileEntities.TUNGSTENSTEEL_DRUM, MetaTileEntities.GOLD_DRUM)
            .map { it.stack() })
        registry.addGroup("crate", arrayOf(MetaTileEntities.WOODEN_CRATE, MetaTileEntities.BRONZE_CRATE,
            MetaTileEntities.STEEL_CRATE, MetaTileEntities.ALUMINIUM_CRATE, MetaTileEntities.STAINLESS_STEEL_CRATE,
            MetaTileEntities.TITANIUM_CRATE, MetaTileEntities.TUNGSTENSTEEL_CRATE).map { it.stack() })
    }

    private fun ICollapsibleGroupRegistry.addGroupBy(id: String, prefix: String = id) = addGroup(id, filterBy(prefix))

    private fun filterBy(prefix: String): List<ItemStack>
        = MetaItems.ITEMS.filterIsInstance<MetaItem1>().flatMap { it.allItems }.filter { it.unlocalizedName.startsWith(prefix) }.map { it.stack() }
}