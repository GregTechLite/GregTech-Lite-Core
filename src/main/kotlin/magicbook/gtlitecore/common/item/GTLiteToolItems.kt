package magicbook.gtlitecore.common.item

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.items.toolitem.ItemGTTool
import gregtech.api.items.toolitem.ToolHelper
import gregtech.common.items.ToolItems
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.utils.GTLiteValues

@Suppress("MISSING_DEPENDENCY_CLASS", "MISSING_DEPENDENCY_SUPERCLASS")
class GTLiteToolItems
{

    // TOOL CHAR LIST
    // 's' - toolSaw/craftingToolSaw
    // 'h' - toolHammer/craftingToolHardHammer
    // 'r' - toolMallet/craftingToolSoftHammer
    // 'w' - toolWrench/craftingToolWrench
    // 'f' - toolFile/craftingToolFile
    // 'c' - toolCrowbar/craftingToolCrowbar
    // 'd' - toolScrewdriver/craftingToolScrewdriver
    // 'm' - toolMortar/craftingToolMortar
    // 'x' - toolWireCutter/craftingToolWireCutter
    // 'k' - toolKnife/craftingKnife
    // 'p' - toolRollingPin/craftingToolRollingPin
    // 'i' - toolButcheryKnife/craftingToolButcheryKnife

    companion object
    {

        lateinit var ROLLING_PIN: IGTTool

        fun registerTools()
        {
            ROLLING_PIN = ToolItems.register(ItemGTTool.Builder
                .of(GTLiteValues.MODID, "rolling_pin")
                .toolStats { stat ->
                    stat.crafting()
                        .cannotAttack()
                        .attackSpeed(-2.5f)
                }
                .oreDict("toolRollingPin")
                .secondaryOreDicts("craftingToolRollingPin")
                .toolClasses("rolling_pin")
                .symbol('p')
                .sound(GTSoundEvents.PLUNGER_TOOL))
        }

        // Add tool symbols to original tools.
        fun addToolSymbols()
        {
            ToolHelper.registerToolSymbol('i', ToolItems.BUTCHERY_KNIFE)
        }

    }

}