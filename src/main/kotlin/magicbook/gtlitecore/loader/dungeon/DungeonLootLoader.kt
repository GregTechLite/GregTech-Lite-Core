package magicbook.gtlitecore.loader.dungeon

import gregtech.loaders.dungeon.ChestGenHooks
import magicbook.gtlitecore.common.item.GTLiteMetaItems
import net.minecraft.world.storage.loot.LootTableList

@Suppress("MISSING_DEPENDENCY_CLASS")
class DungeonLootLoader
{

    companion object
    {

        @JvmStatic
        fun init()
        {
            ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.GRAPE_JUICE.stackForm, 8, 16, 16)
            ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.RED_WINE.stackForm, 4, 8, 8)
            ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.VINEGAR.stackForm, 12, 24, 18)
            ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.POTATO_JUICE.stackForm, 6, 12, 14)
            ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.VODKA.stackForm, 4, 8, 8)
        }

    }

}