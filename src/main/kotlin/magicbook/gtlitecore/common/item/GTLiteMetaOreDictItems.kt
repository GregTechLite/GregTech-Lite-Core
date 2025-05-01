package magicbook.gtlitecore.common.item

import gregtech.api.items.metaitem.MetaOreDictItem
import gregtech.api.items.metaitem.MetaOreDictItem.OreDictValueItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtech.api.unification.material.info.MaterialIconSet
import gregtech.api.unification.ore.OrePrefix
import magicbook.gtlitecore.api.GTLiteAPI
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

@Suppress("MISSING_DEPENDENCY_CLASS", "MISSING_DEPENDENCY_SUPERCLASS")
class GTLiteMetaOreDictItems
{

    companion object
    {

        private lateinit var GTLITE_OREDICT_ITEMS: MetaOreDictItem

        lateinit var CORN_KERNEL: OreDictValueItem
        lateinit var CORN_COB: OreDictValueItem
        lateinit var BARE_CORN_KERNEL: OreDictValueItem
        lateinit var SMALL_GREEN_COFFEE: OreDictValueItem
        lateinit var LARGE_GREEN_COFFEE: OreDictValueItem
        lateinit var FERMENTED_SMALL_COFFEE: OreDictValueItem
        lateinit var FERMENTED_LARGE_COFFEE: OreDictValueItem
        lateinit var DRIED_SMALL_COFFEE: OreDictValueItem
        lateinit var DRIED_LARGE_COFFEE: OreDictValueItem
        lateinit var ROASTED_SMALL_COFFEE: OreDictValueItem
        lateinit var ROASTED_LARGE_COFFEE: OreDictValueItem
        lateinit var GROUND_COFFEE: OreDictValueItem
        lateinit var GRAHAM_CRACKER_DOUGH: OreDictValueItem
        lateinit var GRAHAM_CRACKER_CHUNK: OreDictValueItem
        lateinit var GRAHAM_CRACKER_SLICE: OreDictValueItem

        @JvmStatic
        fun init()
        {
            GTLITE_OREDICT_ITEMS = MetaOreDictItem(0)
            (GTLITE_OREDICT_ITEMS as? Item)?.setRegistryName("gtlite_meta_oredict_item")
            (GTLITE_OREDICT_ITEMS as? StandardMetaItem)?.setCreativeTabs(GTLiteAPI.TAB_GTLITE as CreativeTabs)
        }

        @JvmStatic
        fun register()
        {
            CORN_KERNEL = GTLITE_OREDICT_ITEMS.addOreDictItem(1, "corn_kernel",
                0xFFEA70, MaterialIconSet.DULL, OrePrefix.gemChipped)

            CORN_COB = GTLITE_OREDICT_ITEMS.addOreDictItem(2, "corn_cob",
                0xF2D434, MaterialIconSet.DULL, OrePrefix.gemFlawed)

            BARE_CORN_KERNEL = GTLITE_OREDICT_ITEMS.addOreDictItem(3, "bare_corn_kernel",
                0xFECB60, MaterialIconSet.DULL, OrePrefix.gemChipped)

            SMALL_GREEN_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(4, "small_green_coffee",
                0x3B220D, MaterialIconSet.DULL, OrePrefix.round)

            LARGE_GREEN_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(5, "large_green_coffee",
                0x3B220D, MaterialIconSet.DULL, OrePrefix.gemChipped)

            FERMENTED_SMALL_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(6, "fermented_small_coffee",
                0x756452, MaterialIconSet.DULL, OrePrefix.round)

            FERMENTED_LARGE_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(7, "fermented_large_coffee",
                0x756452, MaterialIconSet.DULL, OrePrefix.gemChipped)

            DRIED_SMALL_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(8, "dried_small_coffee",
                0x8C6842, MaterialIconSet.DULL, OrePrefix.round)

            DRIED_LARGE_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(9, "dried_large_coffee",
                0x8C6842, MaterialIconSet.DULL, OrePrefix.gemChipped)

            ROASTED_SMALL_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(10, "roasted_small_coffee",
                0x1A1612, MaterialIconSet.DULL, OrePrefix.round)

            ROASTED_LARGE_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(11, "roasted_large_coffee",
                0x1A1612, MaterialIconSet.DULL, OrePrefix.gemChipped)

            GROUND_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(12, "ground_coffee",
                0x1A1612, MaterialIconSet.DULL, OrePrefix.dust)

            GRAHAM_CRACKER_DOUGH = GTLITE_OREDICT_ITEMS.addOreDictItem(13, "graham_cracker_dough",
                0xF0C25D, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged)

            GRAHAM_CRACKER_CHUNK = GTLITE_OREDICT_ITEMS.addOreDictItem(14, "graham_cracker_chunk",
                0xFFE1A1, MaterialIconSet.DULL, OrePrefix.plateDense)

            GRAHAM_CRACKER_SLICE = GTLITE_OREDICT_ITEMS.addOreDictItem(15, "graham_cracker_slice",
                0xFFE1A1, MaterialIconSet.DULL, OrePrefix.plate)
        }

    }

}