package magicbook.gtlitecore.common.item

import gregtech.api.items.metaitem.MetaOreDictItem
import gregtech.api.items.metaitem.MetaOreDictItem.OreDictValueItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtech.api.unification.material.info.MaterialIconSet
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.crushed
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gemChipped
import gregtech.api.unification.ore.OrePrefix.gemFlawed
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.round
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.unification.material.infos.GTLiteMaterialIconSet.Companion.ORGANIC
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
        lateinit var ANIMAL_FAT: OreDictValueItem

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
                0xFFEA70, DULL, gemChipped)

            CORN_COB = GTLITE_OREDICT_ITEMS.addOreDictItem(2, "corn_cob",
                0xF2D434, DULL, gemFlawed)

            BARE_CORN_KERNEL = GTLITE_OREDICT_ITEMS.addOreDictItem(3, "bare_corn_kernel",
                0xFECB60, DULL, gemChipped)

            SMALL_GREEN_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(4, "small_green_coffee",
                0x3B220D, DULL, round)

            LARGE_GREEN_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(5, "large_green_coffee",
                0x3B220D, DULL, gemChipped)

            FERMENTED_SMALL_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(6, "fermented_small_coffee",
                0x756452, DULL, round)

            FERMENTED_LARGE_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(7, "fermented_large_coffee",
                0x756452, DULL, gemChipped)

            DRIED_SMALL_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(8, "dried_small_coffee",
                0x8C6842, DULL, round)

            DRIED_LARGE_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(9, "dried_large_coffee",
                0x8C6842, DULL, gemChipped)

            ROASTED_SMALL_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(10, "roasted_small_coffee",
                0x1A1612, DULL, round)

            ROASTED_LARGE_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(11, "roasted_large_coffee",
                0x1A1612, DULL, gemChipped)

            GROUND_COFFEE = GTLITE_OREDICT_ITEMS.addOreDictItem(12, "ground_coffee",
                0x1A1612, ORGANIC, dust)

            GRAHAM_CRACKER_DOUGH = GTLITE_OREDICT_ITEMS.addOreDictItem(13, "graham_cracker_dough",
                0xF0C25D, ORGANIC, crushed)

            GRAHAM_CRACKER_CHUNK = GTLITE_OREDICT_ITEMS.addOreDictItem(14, "graham_cracker_chunk",
                0xFFE1A1, DULL, plateDense)

            GRAHAM_CRACKER_SLICE = GTLITE_OREDICT_ITEMS.addOreDictItem(15, "graham_cracker_slice",
                0xFFE1A1, DULL, plate)

            ANIMAL_FAT = GTLITE_OREDICT_ITEMS.addOreDictItem(16, "animal_fat",
                0xFFF200, ORGANIC, ingot, "C57H110O6")

        }

    }

}