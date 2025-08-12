package gregtechlite.gtlitecore.common.item.behavior

import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.factory.HandGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.utils.Alignment
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widget.ParentWidget
import com.cleanroommc.modularui.widgets.ButtonWidget
import com.cleanroommc.modularui.widgets.layout.Column
import com.cleanroommc.modularui.widgets.layout.Row
import gregtech.api.items.gui.ItemUIFactory
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.mui.factory.MetaItemGuiFactory
import gregtech.api.util.KeyUtil
import gregtech.common.mui.widget.ScrollableTextWidget
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.pattern.JsonBlockPattern
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World

object StructureWriterBehavior : IItemBehaviour, ItemUIFactory
{

    fun isItemStructureWriter(stack: ItemStack?): Boolean
    {
        if (stack!!.isEmpty) return false
        val item = stack.item
        if (item is MetaItem<*>)
        {
            val valueItem = item.getItem(stack)
            return valueItem != null && valueItem.behaviours.contains(StructureWriterBehavior)
        }

        return false
    }

    @Suppress("UnstableApiUsage")
    override fun buildUI(guiData: HandGuiData, guiSyncManager: PanelSyncManager): ModularPanel
    {
        return GTGuis.createPanel(guiData.mainHandItem, 176, 120)
            .background(GTGuiTextures.BACKGROUND)
            .child(Column()
                    .margin(4)
                    .child(ParentWidget()
                            .widthRel(1f)
                            .heightRel(0.5f)
                            .background(GTGuiTextures.DISPLAY)
                            .child(ScrollableTextWidget()
                                    .full()
                                    .margin(4)
                                    .alignment(Alignment.TopLeft)
                                    .autoUpdate(true)
                                    .textBuilder {
                                        var x = 0
                                        var y = 0
                                        var z = 0

                                        if (getPos(guiData.mainHandItem) != null)
                                        {
                                            val blockPos = getPos(guiData.mainHandItem)!!
                                            x = 1 + blockPos[1].x - blockPos[0].x
                                            y = 1 + blockPos[1].y - blockPos[0].y
                                            z = 1 + blockPos[1].z - blockPos[0].z
                                        }

                                        it.add(KeyUtil.lang(TextFormatting.WHITE, "metaitem.debug.structure_writer.structure_scale", x, y, z))
                                    }))
                    .child(Row()
                            .widthRel(1f)
                            .height(22)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)
                            .childPadding(4)
                            .child(ButtonWidget()
                                    .expanded()
                                    .overlay(IKey.lang("metaitem.debug.structure_writer.export_to_log"))
                                    .onMouseTapped { exportLog(guiData) })
                            .child(ButtonWidget()
                                    .expanded()
                                    .overlay(IKey.lang("metaitem.debug.structure_writer.export_to_json"))
                                    .onMouseTapped { exportLog(guiData) }))
                    .child(Row()
                            .widthRel(1f)
                            .height(22)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)
                            .childPadding(4)
                            .child(ButtonWidget()
                                    .expanded()
                                    .overlay(IKey.lang("metaitem.debug.structure_writer.rotate_along_y_axis"))
                                    .onMouseTapped { exportLog(guiData) })
                            .child(ButtonWidget()
                                    .expanded()
                                    .overlay(IKey.lang("metaitem.debug.structure_writer.empty"))
                                    .onMouseTapped { exportLog(guiData) })))
    }

    override fun onItemUseFirst(player: EntityPlayer, world: World?, pos: BlockPos, side: EnumFacing?,
                                hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand, ): EnumActionResult?
    {
        val stack = player.getHeldItem(hand)
        if (!player.isSneaking)
        {
            this.addPos(stack, pos)
        }
        else
        {
            this.removePos(stack)
        }
        return EnumActionResult.SUCCESS
    }

    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack?>?
    {
        val stack = player.getHeldItem(hand)
        if (player.isSneaking)
        {
            this.removePos(stack)
        }
        else
        {
            if (!world.isRemote)
            {
                MetaItemGuiFactory.open(player, hand)
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack)
    }

    fun addPos(stack: ItemStack, pos: BlockPos)
    {
        val tag = stack.getOrCreateSubCompound("blueprint")

        if (!tag.hasKey("minX") || tag.getInteger("minX") > pos.x)
        {
            tag.setInteger("minX", pos.x)
        }
        if (!tag.hasKey("maxX") || tag.getInteger("maxX") < pos.x)
        {
            tag.setInteger("maxX", pos.x)
        }

        if (!tag.hasKey("minY") || tag.getInteger("minY") > pos.y)
        {
            tag.setInteger("minY", pos.y)
        }
        if (!tag.hasKey("maxY") || tag.getInteger("maxY") < pos.y)
        {
            tag.setInteger("maxY", pos.y)
        }

        if (!tag.hasKey("minZ") || tag.getInteger("minZ") > pos.z)
        {
            tag.setInteger("minZ", pos.z)
        }
        if (!tag.hasKey("maxZ") || tag.getInteger("maxZ") < pos.z)
        {
            tag.setInteger("maxZ", pos.z)
        }
    }

    fun removePos(stack: ItemStack)
    {
        val tag = stack.getOrCreateSubCompound("blueprint")
        tag.removeTag("minX")
        tag.removeTag("maxX")
        tag.removeTag("minY")
        tag.removeTag("maxY")
        tag.removeTag("minZ")
        tag.removeTag("maxZ")
    }

    private fun exportLog(guiData: HandGuiData): Boolean
    {
        if (getPos(guiData.mainHandItem) != null)
        {
            val blockPos = getPos(guiData.mainHandItem)!!
            val builder = StringBuilder()
            val blockPattern = JsonBlockPattern(guiData.player.world,
                blockPos[0].x, blockPos[0].y, blockPos[0].z,
                blockPos[1].x, blockPos[1].y, blockPos[1].z
            )
            for (i in 0 ..< blockPattern.blockPattern.size)
            {
                val strings = blockPattern.blockPattern[i]
                builder.append(".aisle(")
                for (string in strings)
                {
                    builder.append(String.format("\"%s\", ", string))
                }
                builder.append(")\n")
            }
            GTLiteLog.logger.info(builder.toString())
            return true
        }
        return false
    }

    fun getPos(stack: ItemStack?): Array<BlockPos>?
    {
        val tag = stack!!.getOrCreateSubCompound("blueprint")
        if (!tag.hasKey("minX")) return null
        return arrayOf(
            BlockPos(tag.getInteger("minX"), tag.getInteger("minY"), tag.getInteger("minZ")),
            BlockPos(tag.getInteger("maxX"), tag.getInteger("maxY"), tag.getInteger("maxZ"))
        )
    }

}