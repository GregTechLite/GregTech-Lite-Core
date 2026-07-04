package gregtechlite.gtlitecore.common.item

import gregtech.api.items.metaitem.MetaOreDictItem
import gregtech.api.items.metaitem.MetaOreDictItem.OreDictValueItem
import gregtech.api.unification.material.info.MaterialIconSet
import gregtech.api.unification.material.info.MaterialIconSet.DULL
import gregtech.api.unification.material.info.MaterialIconSet.FINE
import gregtech.api.unification.material.info.MaterialIconSet.ROUGH
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.crushed
import gregtech.api.unification.ore.OrePrefix.crushedCentrifuged
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.gemChipped
import gregtech.api.unification.ore.OrePrefix.gemFlawed
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.round
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.NANOPARTICLES
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.ORGANIC
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet.WAX
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
    lateinit var SOYBEAN_MEAL: OreDictValueItem
    lateinit var PLANT_PROTEIN: OreDictValueItem
    lateinit var EGGSHELL: OreDictValueItem
    lateinit var WET_EGG_WHITE_CAKE: OreDictValueItem

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
        CORN_KERNEL = item(1, "corn_kernel", 0xFFEA70, DULL, gemChipped)
        CORN_COB = item(2, "corn_cob", 0xF2D434, DULL, gemFlawed)
        BARE_CORN_KERNEL = item(3, "bare_corn_kernel", 0xFECB60, DULL, gemChipped)
        SMALL_GREEN_COFFEE = item(4, "small_green_coffee", 0x3B220D, DULL, round)
        LARGE_GREEN_COFFEE = item(5, "large_green_coffee", 0x3B220D, DULL, gemChipped)
        FERMENTED_SMALL_COFFEE = item(6, "fermented_small_coffee", 0x756452, DULL, round)
        FERMENTED_LARGE_COFFEE = item(7, "fermented_large_coffee", 0x756452, DULL, gemChipped)
        DRIED_SMALL_COFFEE = item(8, "dried_small_coffee", 0x8C6842, DULL, round)
        DRIED_LARGE_COFFEE = item(9, "dried_large_coffee", 0x8C6842, DULL, gemChipped)
        ROASTED_SMALL_COFFEE = item(10, "roasted_small_coffee", 0x1A1612, DULL, round)
        ROASTED_LARGE_COFFEE = item(11, "roasted_large_coffee", 0x1A1612, DULL, gemChipped)
        GROUND_COFFEE = item(12, "ground_coffee", 0x1A1612, DULL, dust)
        GRAHAM_CRACKER_DOUGH = item(13, "graham_cracker_dough", 0xF0C25D, ORGANIC, crushed)
        GRAHAM_CRACKER_CHUNK = item(14, "graham_cracker_chunk", 0xFFE1A1, ORGANIC, plateDense)
        GRAHAM_CRACKER_SLICE = item(15, "graham_cracker_slice", 0xFFE1A1, ORGANIC, plate)
        ANIMAL_FAT = item(16, "animal_fat", 0xFFF200, ORGANIC, ingot, "C57H110O6")
        ZEST_DUST = item(17, "zest", 0xD8FF4A, FINE, dust)
        POPPY_DUST = item(18, "poppy", 0x940801, ROUGH, dust)
        APPLE_PULP = item(19, "apple_pulp", 0xCCCC99, NANOPARTICLES, dust)
        HARD_APPLE_CANDY_CHUNK = item(20, "hard_apple_candy_chunk", 0x78E32B, ORGANIC, plateDense)
        HARD_APPLE_CANDY_PLATE = item(21, "hard_apple_candy", 0x78E32B, ORGANIC, plate)
        HARD_APPLE_CANDY_DUST = item(22, "hard_apple_candy", 0x78E32B, NANOPARTICLES, dust)
        SOYBEAN_MEAL = item(23, "soybean_meal", 0x629046, NANOPARTICLES, dustSmall)
        PLANT_PROTEIN = item(24, "plant_protein", 0xFFFFFF, ORGANIC, dustSmall)
        EGGSHELL = item(25, "eggshell", 0xDFCE9B, NANOPARTICLES, dustSmall)
        WET_EGG_WHITE_CAKE = item(26, "wet_egg_white_cake", 0xFFFBF0, DULL, crushedCentrifuged)
    }

    private fun item(id: Int, name: String, color: Int, iconSet: MaterialIconSet, prefix: OrePrefix,
                     formula: String? = null)
        = ORE_DICT_ITEMS.addOreDictItem(id, name, color, iconSet, prefix, formula)

    // @formatter:on

}