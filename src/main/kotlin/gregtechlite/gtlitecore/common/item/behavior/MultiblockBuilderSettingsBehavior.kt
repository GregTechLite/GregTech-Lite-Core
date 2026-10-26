@file:Suppress("UnstableApiUsage", "Deprecation")
package gregtechlite.gtlitecore.common.item.behavior

import com.cleanroommc.modularui.api.IPanelHandler
import com.cleanroommc.modularui.api.drawable.IDrawable
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.drawable.Rectangle
import com.cleanroommc.modularui.factory.HandGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.screen.UISettings
import com.cleanroommc.modularui.utils.BooleanConsumer
import com.cleanroommc.modularui.utils.Color
import com.cleanroommc.modularui.utils.MouseData
import com.cleanroommc.modularui.value.sync.BooleanSyncValue
import com.cleanroommc.modularui.value.sync.IntSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.ButtonWidget
import com.cleanroommc.modularui.widgets.ListWidget
import com.cleanroommc.modularui.widgets.ToggleButton
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widget.Widget
import gregtech.api.items.gui.ItemUIFactory
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtech.api.mui.GTGuiTextures
import gregtech.api.mui.GTGuis
import gregtech.api.mui.factory.MetaItemGuiFactory
import gregtech.common.mui.widget.GTTextFieldWidget
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.pattern.MultiblockBuildSetting
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import java.util.function.BooleanSupplier
import java.util.function.IntConsumer
import java.util.function.IntSupplier

object MultiblockBuilderSettingsBehavior : IItemBehaviour, ItemUIFactory
{
    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack>
    {
        if (!world.isRemote)
            MetaItemGuiFactory.open(player, hand)
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand))
    }

    override fun addInformation(itemStack: ItemStack, lines: MutableList<String>)
    {
        lines.add(I18n.format("item.gtlitecore.multiblock_builder.tooltip.configure"))
    }

    override fun buildUI(guiData: HandGuiData, syncManager: PanelSyncManager, settings: UISettings): ModularPanel
    {
        val stack = guiData.usedItemStack
        val panel = GTGuis.createPanel(stack, 176, 200 - 18)
            .background(GTGuiTextures.BACKGROUND)

        val tierMax = maxOf(BlockAttributeRegistry.maxAttributeSize(), GTLiteAPI.COIL_TIER.attributeSize).coerceIn(1, 15)
        val tiersPanel = syncManager.syncedPanel("multiblock_builder_tiers", true) { _, _ -> makeTierPanel(stack) }

        val column = Flow.column()
            .margin(7, 8)
            .widthRel(1f)
            .coverChildrenHeight()
            .child(IKey.lang("item.gtlitecore.multiblock_builder.setting.title").asWidget()
                       .marginBottom(4))

        column.child(numericRow("block_tier", createSyncValue(stack, MultiblockBuildSetting.TIER, 0, tierMax),
            0, tierMax, tierPopupButton(tiersPanel)))

        column.child(numericRow("repeat_count", createSyncValue(stack, MultiblockBuildSetting.REPEAT_COUNT, 0,
            MultiblockBuildSetting.MAX_REPEAT_COUNT), 0, MultiblockBuildSetting.MAX_REPEAT_COUNT))

        column.child(numericRow("additional_structure", createSyncValue(stack,
            MultiblockBuildSetting.ADDITIONAL_STRUCTURE_COUNT,
            MultiblockBuildSetting.DEFAULT_ADDITIONAL_STRUCTURES,
            MultiblockBuildSetting.MAX_ADDITIONAL_STRUCTURES),
            0, MultiblockBuildSetting.MAX_ADDITIONAL_STRUCTURES))

        column.child(Rectangle()
                         .color(0xFF555555.toInt())
                         .asWidget()
                         .widthRel(1f)
                         .height(1)
                         .marginTop(4)
                         .marginBottom(6))

        column.child(toggleRow("no_hatch_mode", createBoolSyncValue(stack, MultiblockBuildSetting.NO_HATCH_MODE)))
        column.child(toggleRow("replace_mode" , createBoolSyncValue(stack, MultiblockBuildSetting.REPLACE_MODE )))
        column.child(toggleRow("appeng_mode"  , createBoolSyncValue(stack, MultiblockBuildSetting.APPENG_MODE  )))
        column.child(toggleRow("mirror_mode"  , createBoolSyncValue(stack, MultiblockBuildSetting.MIRROR_MODE  )))

        return panel.child(column)
    }

    private fun tierPopupButton(tiersPanel: IPanelHandler): ButtonWidget<*> = ButtonWidget()
        .size(18)
        .background(GTGuiTextures.BUTTON)
        .overlay(GTGuiTextures.FILTER_SETTINGS_OVERLAY.asIcon()
                     .size(16))
        .addTooltipLine(IKey.lang("item.gtlitecore.multiblock_builder.setting.parts.tooltip"))
        .onMousePressed {
            if (tiersPanel.isPanelOpen)
                tiersPanel.closePanel()
            else
                tiersPanel.openPanel()
            return@onMousePressed true
        }

    private fun makeTierPanel(stack: ItemStack): ModularPanel
    {
        val panel = GTGuis.createPopupPanel("multiblock_builder_tiers", 250, 180)
            .disablePanelsBelow(true)
            .background(GTGuiTextures.BACKGROUND)

        panel.child(IKey.lang("item.gtlitecore.multiblock_builder.setting.parts").asWidget()
                        .pos(8, 6))

        val partList = RegistryTierList()
            .pos(7, 20)
            .width(236)
            .height(152)

        BlockAttributeRegistry.all()
            .filter { it.attributeSize > 1 && (it.name.endsWith("_tier") || it.name == "CoilType") }
            .sortedBy { it.name }
            .forEach {
                partList.child(registryTierRow(it, stack))
            }

        panel.child(partList)
        return panel
    }

    private fun numericRow(labelKey: String, value: IntSyncValue, min: Int, max: Int,
                           parentWidget: Widget<*>? = null): Flow
    {
        val row = Flow.row()
            .widthRel(1f)
            .coverChildrenHeight()
            .marginBottom(2)
            .child(IKey.lang("item.gtlitecore.multiblock_builder.setting.$labelKey").asWidget()
                       .width(74))
            .child(ButtonWidget()
                .size(18)
                .overlay(IKey.str("-").color(Color.WHITE.main))
                .onMousePressed {
                    value.setIntValue((value.intValue - increment(it)).coerceIn(min, max), true, true)
                    return@onMousePressed true
                })
            .child(GTTextFieldWidget()
                .width(30)
                .height(18)
                .setNumbers(min, max)
                .value(value)
                .setTextColor(Color.WHITE.main)
                .background(GTGuiTextures.DISPLAY))
            .child(ButtonWidget()
                .size(18)
                .overlay(IKey.str("+").color(Color.WHITE.main))
                .onMousePressed {
                    value.setIntValue((value.intValue + increment(it)).coerceIn(min, max), true, true)
                    return@onMousePressed true
                })

        parentWidget?.let { row.child(it) }
        return row
    }

    private fun registryTierRow(registry: BlockAttributeRegistry<*>, stack: ItemStack): Flow
    {
        val max = registry.attributeSize.coerceIn(1, 15)
        val value = createRegistryTierSyncValue(stack, registry.name, max)

        return Flow.row()
            .width(224)
            .height(20)
            .marginBottom(2)
            .child(IKey.lang(registryLangKey(registry)).asWidget()
                       .width(140))
            .child(ButtonWidget()
                .size(18)
                .overlay(IKey.str("-").color(Color.WHITE.main))
                .onMousePressed {
                    value.setIntValue((value.intValue - increment(it)).coerceIn(0, max), true, true)
                    return@onMousePressed true
                })
            .child(GTTextFieldWidget()
                .width(30)
                .height(18)
                .setNumbers(0, max)
                .value(value)
                .setTextColor(Color.WHITE.main)
                .background(GTGuiTextures.DISPLAY))
            .child(ButtonWidget()
                .size(18)
                .overlay(IKey.str("+").color(Color.WHITE.main))
                .onMousePressed {
                    value.setIntValue((value.intValue + increment(it)).coerceIn(0, max), true, true)
                    return@onMousePressed true
                })
            .child(Widget()
                       .size(18))
    }

    private fun createRegistryTierSyncValue(stack: ItemStack, registryName: String, max: Int): IntSyncValue
        = IntSyncValue(
            IntSupplier { MultiblockBuildSetting.getTier(stack, registryName).coerceIn(0, max) },
            IntConsumer { MultiblockBuildSetting.setTier(stack, registryName, it) })

    private fun registryLangKey(registry: BlockAttributeRegistry<*>): String
        = "item.gtlitecore.multiblock_builder.registry.${registry.name.lowercase()}"

    private fun toggleRow(labelKey: String, value: BooleanSyncValue, icon: IDrawable? = null): Flow
    {
        val button = ToggleButton()
            .size(18)
            .value(value)
            .overlay(false, GTLiteMuiTextures.BUTTON_TICK_CROSS[0])
            .overlay(true, GTLiteMuiTextures.BUTTON_TICK_CROSS[1])
            .addTooltip(false, IKey.lang("item.gtlitecore.multiblock_builder.setting.$labelKey.disabled"))
            .addTooltip(true, IKey.lang("item.gtlitecore.multiblock_builder.setting.$labelKey.enabled"))

        icon?.let { button.overlay(it) }

        return Flow.row()
            .widthRel(1f)
            .coverChildrenHeight()
            .marginBottom(2)
            .child(IKey.lang("item.gtlitecore.multiblock_builder.setting.$labelKey").asWidget()
                       .width(140))
            .child(button)
    }

    private fun createSyncValue(stack: ItemStack, key: String, defaultValue: Int, max: Int): IntSyncValue
        = IntSyncValue(
            IntSupplier { MultiblockBuildSetting.get(stack, key, defaultValue).coerceIn(0, max) },
            IntConsumer { MultiblockBuildSetting.set(stack, key, it) })

    private fun createBoolSyncValue(stack: ItemStack, key: String): BooleanSyncValue
        = BooleanSyncValue(
            BooleanSupplier { MultiblockBuildSetting.get(stack, key, 0) == 1 },
            BooleanConsumer { MultiblockBuildSetting.set(stack, key, if (it) 1 else 0) })

    private fun increment(mouseButton: Int): Int
    {
        val data = MouseData.create(mouseButton)
        var amount = 1
        if (data.shift)
            amount *= 4
        if (data.ctrl)
            amount *= 16
        if (data.alt)
            amount *= 64
        return amount
    }

    private class RegistryTierList : ListWidget<IWidget, RegistryTierList>()
    {
        override fun getThis(): RegistryTierList = this
    }
}
