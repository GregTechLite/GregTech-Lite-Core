package gregtechlite.gtlitecore.common.item

import gregtech.api.items.metaitem.MetaOreDictItem
import gregtech.api.items.metaitem.MetaOreDictItem.OreDictValueItem
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.FINE
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtech.api.unification.ore.OrePrefix.crushed
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gemChipped
import gregtech.api.unification.ore.OrePrefix.gemFlawed
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.round
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.NANOPARTICLES
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.ORGANIC
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs

object GTLiteMetaOreDictItems
{

    // @formatter:off

    private lateinit var ORE_DICT_ITEMS: MetaOreDictItem

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
    lateinit var ZEST_DUST: OreDictValueItem
    lateinit var POPPY_DUST: OreDictValueItem
    lateinit var APPLE_PULP: OreDictValueItem
    lateinit var HARD_APPLE_CANDY_CHUNK: OreDictValueItem
    lateinit var HARD_APPLE_CANDY_PLATE: OreDictValueItem
    lateinit var HARD_APPLE_CANDY_DUST: OreDictValueItem

    @JvmStatic
    fun init()
    {
        ORE_DICT_ITEMS = MetaOreDictItem(0)
        ORE_DICT_ITEMS.setRegistryName("gtlite_meta_oredict_item")
        ORE_DICT_ITEMS.setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)
    }

    @JvmStatic
    fun register()
    {
        CORN_KERNEL = ORE_DICT_ITEMS.addOreDictItem(1, "corn_kernel", 0xFFEA70, DULL, gemChipped)
        CORN_COB = ORE_DICT_ITEMS.addOreDictItem(2, "corn_cob", 0xF2D434, DULL, gemFlawed)
        BARE_CORN_KERNEL = ORE_DICT_ITEMS.addOreDictItem(3, "bare_corn_kernel", 0xFECB60, DULL, gemChipped)
        SMALL_GREEN_COFFEE = ORE_DICT_ITEMS.addOreDictItem(4, "small_green_coffee", 0x3B220D, DULL, round)
        LARGE_GREEN_COFFEE = ORE_DICT_ITEMS.addOreDictItem(5, "large_green_coffee", 0x3B220D, DULL, gemChipped)
        FERMENTED_SMALL_COFFEE = ORE_DICT_ITEMS.addOreDictItem(6, "fermented_small_coffee", 0x756452, DULL, round)
        FERMENTED_LARGE_COFFEE = ORE_DICT_ITEMS.addOreDictItem(7, "fermented_large_coffee", 0x756452, DULL, gemChipped)
        DRIED_SMALL_COFFEE = ORE_DICT_ITEMS.addOreDictItem(8, "dried_small_coffee", 0x8C6842, DULL, round)
        DRIED_LARGE_COFFEE = ORE_DICT_ITEMS.addOreDictItem(9, "dried_large_coffee", 0x8C6842, DULL, gemChipped)
        ROASTED_SMALL_COFFEE = ORE_DICT_ITEMS.addOreDictItem(10, "roasted_small_coffee", 0x1A1612, DULL, round)
        ROASTED_LARGE_COFFEE = ORE_DICT_ITEMS.addOreDictItem(11, "roasted_large_coffee", 0x1A1612, DULL, gemChipped)
        GROUND_COFFEE = ORE_DICT_ITEMS.addOreDictItem(12, "ground_coffee", 0x1A1612, DULL, dust)
        GRAHAM_CRACKER_DOUGH = ORE_DICT_ITEMS.addOreDictItem(13, "graham_cracker_dough", 0xF0C25D, ORGANIC, crushed)
        GRAHAM_CRACKER_CHUNK = ORE_DICT_ITEMS.addOreDictItem(14, "graham_cracker_chunk", 0xFFE1A1, ORGANIC, plateDense)
        GRAHAM_CRACKER_SLICE = ORE_DICT_ITEMS.addOreDictItem(15, "graham_cracker_slice", 0xFFE1A1, ORGANIC, plate)
        ANIMAL_FAT = ORE_DICT_ITEMS.addOreDictItem(16, "animal_fat", 0xFFF200, ORGANIC, ingot, "C57H110O6")
        ZEST_DUST = ORE_DICT_ITEMS.addOreDictItem(17, "zest", 0xD8FF4A, FINE, dust)
        POPPY_DUST = ORE_DICT_ITEMS.addOreDictItem(18, "poppy", 0x940801, ROUGH, dust)
        APPLE_PULP = ORE_DICT_ITEMS.addOreDictItem(19, "apple_pulp", 0xCCCC99, NANOPARTICLES, dust)
        HARD_APPLE_CANDY_CHUNK = ORE_DICT_ITEMS.addOreDictItem(20, "hard_apple_candy_chunk", 0x78E32B, ORGANIC, plateDense)
        HARD_APPLE_CANDY_PLATE = ORE_DICT_ITEMS.addOreDictItem(21, "hard_apple_candy", 0x78E32B, ORGANIC, plate)
        HARD_APPLE_CANDY_DUST = ORE_DICT_ITEMS.addOreDictItem(22, "hard_apple_candy", 0x78E32B, NANOPARTICLES, dust)

    }

    // @formatter:on

}