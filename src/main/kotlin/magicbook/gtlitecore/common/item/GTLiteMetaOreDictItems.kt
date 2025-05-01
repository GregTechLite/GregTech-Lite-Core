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
        }

    }

}