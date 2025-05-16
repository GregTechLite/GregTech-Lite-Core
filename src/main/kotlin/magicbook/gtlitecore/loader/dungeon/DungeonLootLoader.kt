package magicbook.gtlitecore.loader.dungeon

import gregtech.loaders.dungeon.ChestGenHooks
import magicbook.gtlitecore.common.item.GTLiteMetaItems
import magicbook.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.world.storage.loot.LootTableList

@Suppress("MISSING_DEPENDENCY_CLASS")
class DungeonLootLoader
{

    companion object
    {

        @JvmStatic
        fun init()
        {

            if (GTLiteConfigHolder.worldgen.addLoot)
            {
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.GRAPE_JUICE.stackForm, 8, 16, 16)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.RED_WINE.stackForm, 4, 8, 8)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.VINEGAR.stackForm, 12, 24, 18)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.POTATO_JUICE.stackForm, 6, 12, 14)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.VODKA.stackForm, 4, 8, 8)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.COFFEE_CUP.stackForm, 1, 4, 6)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.ORANGE_JUICE.stackForm, 3, 15, 10)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.ETIRPS.stackForm, 2, 8, 8)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.SPARKLING_WATER.stackForm, 4, 16, 12)
                ChestGenHooks.addItem(LootTableList.CHESTS_SPAWN_BONUS_CHEST, GTLiteMetaItems.CRANBERRY_ETIRPS.stackForm, 5, 15, 10)
            }

        }

    }

}