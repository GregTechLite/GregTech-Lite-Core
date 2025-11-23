package gregtechlite.gtlitecore.common.item

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.items.toolitem.ItemGTTool
import gregtech.api.items.toolitem.ToolHelper
import gregtech.common.items.ToolItems
import gregtech.common.items.tool.BlockRotatingBehavior
import gregtech.common.items.tool.EntityDamageBehavior
import gregtech.common.items.tool.GrassPathBehavior
import gregtech.common.items.tool.RotateRailBehavior
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.extension.toolDefinition
import gregtechlite.gtlitecore.common.item.behavior.FlintAndSteelToolBehavior
import net.minecraft.entity.monster.EntityGolem
import net.minecraft.init.SoundEvents

/**
 * GTCEu format Tools Addition.
 *
 * We add some new tools for recipe ingredient or player interactions for QoL, consists of:
 *
 * - **Rolling Pin** is used for process soft material to plate or stick (sometimes with knife, for example: clay, wood),
 *   macerate some plants or others to get pulp, or make dough.
 * - **Combination Wrench** has many functions, consists of Hard Hammer and Wrench, the original idea of this tool is
 *   from [GT5 Unofficial](https://github.com/GTNewHorizons/GT5-Unofficial).
 * - **Universal Spade** has many functions, consists of Crowbar, Spade and Saw, the original idea of this tool is from
 *   [GT6](https://github.com/GregTech6/gregtech6) and [GCYL](https://github.com/GregTechCEu/gregicality-legacy).
 * - **Flint And Steel** is extended version of vanilla items, it is another choice with GTCEu lighter (for lighter, it
 *   consumed some chemistry gases, but this is just vanilla usage).
 * - **Club** is like Sword but has higher damage and lower durability (just like what elder did in the Stone Age), the
 *   original idea of this tool is from [GT6](https://github.com/GregTech6/gregtech6).
 *
 * Here is the table for all tool characters which be usable in recipe characters,
 * so all developers should avoid use these character.
 *
 * | Character | Tool Type             | Ore Dictionaries                                 |
 * |-----------|-----------------------|--------------------------------------------------|
 * | `s`       | Saw                   | `toolSaw`, `craftingToolSaw`                     |
 * | `h`       | Hammer (Forge Hammer) | `toolHammer`, `craftingToolHardHammer`           |
 * | `r`       | Mallet (Soft Hammer)  | `toolMallet`, `craftingToolSoftHammer`           |
 * | `w`       | Wrench                | `toolWrench`, `craftingToolWrench`               |
 * | `f`       | File                  | `toolFile`, `craftingToolFile`                   |
 * | `c`       | Crowbar               | `toolCrowbar`, `craftingToolCrowbar`             |
 * | `d`       | Screwdriver           | `toolScrewdriver`, `craftingToolScrewdriver`     |
 * | `m`       | Mortar                | `toolMortar`, `craftingToolMortar`               |
 * | `x`       | Wire Cutter           | `toolWireCutter`, `craftingToolWireCutter`       |
 * | `k`       | Knife                 | `toolKnife`, `craftingKnife`                     |
 * | `p`       | Rolling Pin           | `toolRollingPin`, `craftingToolRollingPin`       |
 * | `i`       | Butchery Knife        | `toolButcheryKnife`, `craftingToolButcheryKnife` |
 *
 * For all tool ore dictionaries, if it required to use in recipe ingredients,
 * used `toolX` at first to make it compatible with disposable tools (even for GrS).
 *
 * TODO: When add meat strips and bacon, used knife or butchery knife for those recipes (but why GTCEu add crafting()
 *       mark to the butchery knife? Because of this feature, we add a tool character for it, so if this is unneeded,
 *       then should remove the additional tool character register).
 */
object GTLiteToolItems
{

    lateinit var ROLLING_PIN: IGTTool
    lateinit var COMBINATION_WRENCH: IGTTool
    lateinit var UNIVERSAL_SPADE: IGTTool
    lateinit var FLINT_AND_STEEL: IGTTool
    lateinit var CLUB: IGTTool

    internal fun registerTools()
    {
        ROLLING_PIN = tool("rolling_pin") {
            toolDefinition {
                crafting()
                cannotAttack()
                attackSpeed(-2.5f)
            }
            oreDict("toolRollingPin")
            secondaryOreDicts("craftingToolRollingPin")
            toolClasses("rolling_pin")
            symbol('p')
            sound(GTSoundEvents.PLUNGER_TOOL)
        }

        COMBINATION_WRENCH = tool("combination_wrench") {
            toolDefinition {
                blockBreaking()
                crafting()
                sneakBypassUse()
                attackDamage(1.0F)
                attackSpeed(-2.8F)
                behaviors(BlockRotatingBehavior.INSTANCE,
                          EntityDamageBehavior(3.0F, EntityGolem::class.java))
            }
            oreDict("toolWrench")
            secondaryOreDicts("toolHammer", "craftingToolWrench", "craftingToolHardHammer")
            toolClasses("wrench", "hammer")
            sound(GTSoundEvents.WRENCH_TOOL, true)
        }

        UNIVERSAL_SPADE = tool("universal_spade") {
            toolDefinition {
                blockBreaking()
                crafting()
                sneakBypassUse()
                attackDamage(3.0F)
                attackSpeed(-2.4F)
                behaviors(GrassPathBehavior.INSTANCE, RotateRailBehavior.INSTANCE)
            }
            oreDict("toolShovel")
            secondaryOreDicts("toolCrowbar", "toolSpade", "toolSaw", "craftingToolSaw")
            toolClasses("crowbar", "shovel", "saw")
            sound(SoundEvents.ENTITY_ITEM_BREAK)
        }

        FLINT_AND_STEEL = tool("flint_and_steel") {
            toolDefinition {
                behaviors(FlintAndSteelToolBehavior.Companion.INSTANCE)
            }
            oreDict("toolFlintAndSteel")
            toolClasses("flint_and_steel")
        }

        CLUB = tool("club") {
            toolDefinition {
                attacking()
                attackDamage(12F)
                attackSpeed(-3.6F)
                damagePerAction(2)
                durabilityMultiplier(0.5F)
                behaviors(EntityDamageBehavior(4.0F, EntityGolem::class.java))
            }
            oreDict("toolClub")
            toolClasses("club")
            sound(SoundEvents.BLOCK_PISTON_CONTRACT)
        }

    }

    fun addToolSymbols()
    {
        ToolHelper.registerToolSymbol('i', ToolItems.BUTCHERY_KNIFE)
    }

    private fun tool(id: String, action: ItemGTTool.Builder.() -> Unit)
        = ToolItems.register(ItemGTTool.Builder.of(MOD_ID, id).apply(action))

}