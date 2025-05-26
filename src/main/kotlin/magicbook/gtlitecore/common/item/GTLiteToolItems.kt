package magicbook.gtlitecore.common.item

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.items.toolitem.ItemGTTool
import gregtech.api.items.toolitem.ToolHelper
import gregtech.common.items.ToolItems
import gregtech.common.items.tool.BlockRotatingBehavior
import gregtech.common.items.tool.EntityDamageBehavior
import gregtech.common.items.tool.GrassPathBehavior
import gregtech.common.items.tool.RotateRailBehavior
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.common.item.behavior.FlintAndSteelToolBehavior
import net.minecraft.enchantment.EnumEnchantmentType
import net.minecraft.entity.monster.EntityGolem
import net.minecraft.init.SoundEvents

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
        lateinit var COMBINATION_WRENCH: IGTTool
        lateinit var UNIVERSAL_SPADE: IGTTool
        lateinit var FLINT_AND_STEEL: IGTTool
        lateinit var CLUB: IGTTool

        fun registerTools()
        {
            // Rolling pin is used for process soft material to plate or stick (with knife), macerate some
            // plants or others to get pulp, or make dough.
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

            // Combination wrench has many functions, consists of hard hammer and wrench, the original idea
            // of this tool is from GregTech 5 Unofficial.
            COMBINATION_WRENCH = ToolItems.register(ItemGTTool.Builder
                .of(GTLiteValues.MODID, "combination_wrench")
                .toolStats { stat ->
                    stat.blockBreaking()
                        .crafting()
                        .sneakBypassUse()
                        .attackDamage(1.0F)
                        .attackSpeed(-2.8F)
                        .behaviors(BlockRotatingBehavior.INSTANCE, EntityDamageBehavior(3.0F, EntityGolem::class.java))
                }
                .oreDict("toolWrench")
                .secondaryOreDicts("toolHammer", "craftingToolWrench", "craftingToolHardHammer")
                .toolClasses("wrench", "hammer")
                .sound(GTSoundEvents.WRENCH_TOOL, true))

            // Universal spade has many functions, consists of crowbar, spade and saw, the original idea
            // of this tool is from GregTech 6 and Gregicality Legacy.
            UNIVERSAL_SPADE = ToolItems.register(ItemGTTool.Builder
                .of(GTLiteValues.MODID, "universal_spade")
                .toolStats { stat ->
                    stat.blockBreaking()
                        .crafting()
                        .sneakBypassUse()
                        .attackDamage(3.0F)
                        .attackSpeed(-2.4F)
                        .behaviors(GrassPathBehavior.INSTANCE, RotateRailBehavior.INSTANCE)
                }
                .oreDict("toolShovel")
                .secondaryOreDicts("toolCrowbar", "toolSpade", "toolSaw", "craftingToolSaw")
                .toolClasses("crowbar", "shovel", "saw")
                .sound(SoundEvents.ENTITY_ITEM_BREAK))

            // Flint And Steel is extended version of vanilla items, it is another choice with Gregtech
            // lighter (for lighter, it consumed some chemistry gases, but this is just vanilla usage).
            FLINT_AND_STEEL = ToolItems.register(ItemGTTool.Builder
                .of(GTLiteValues.MODID, "flint_and_steel")
                .toolStats { stat ->
                    stat.behaviors(FlintAndSteelToolBehavior.INSTANCE)
                }
                .oreDict("toolFlintAndSteel")
                .toolClasses("flint_and_steel"))

            // Club is like sword but has higher damage and lower durability, the original idea of this
            // tool is from Gregtech 6.
            CLUB = ToolItems.register(ItemGTTool.Builder
                .of(GTLiteValues.MODID, "club")
                .toolStats { stat ->
                    stat.attacking()
                        .attackDamage(12F)
                        .attackSpeed(-3.6F)
                        .damagePerAction(2)
                        .durabilityMultiplier(0.5F)
                        .behaviors(EntityDamageBehavior(4.0F, EntityGolem::class.java))
                }
                .oreDict("toolClub")
                .toolClasses("club")
                .sound(SoundEvents.BLOCK_PISTON_CONTRACT))

        }

        // Add tool symbols to original tools.
        fun addToolSymbols()
        {
            ToolHelper.registerToolSymbol('i', ToolItems.BUTCHERY_KNIFE)
        }

    }

}