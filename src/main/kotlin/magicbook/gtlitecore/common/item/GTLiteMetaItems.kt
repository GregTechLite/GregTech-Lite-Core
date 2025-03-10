package magicbook.gtlitecore.common.item

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import magicbook.gtlitecore.api.GTLiteAPI
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

@Suppress("MISSING_DEPENDENCY_CLASS", "MISSING_DEPENDENCY_SUPERCLASS")
class GTLiteMetaItems
{

    companion object
    {

        private lateinit var GTLITE_ITEMS: MetaItem<*>

        lateinit var SLICER_BLADE_FLAT: MetaItem<*>.MetaValueItem
        lateinit var SLICER_BLADE_STRIPES: MetaItem<*>.MetaValueItem
        lateinit var SLICER_BLADE_OCTAGONAL: MetaItem<*>.MetaValueItem

        @JvmStatic
        fun init()
        {
            GTLITE_ITEMS = StandardMetaItem()
            (GTLITE_ITEMS as? Item)?.setRegistryName("gtlite_meta_item")
            (GTLITE_ITEMS as? StandardMetaItem)?.setCreativeTab(GTLiteAPI.TAB_GTLITE as CreativeTabs)
        }

        @JvmStatic
        fun register()
        {
            // 0-10 Logos of gtlitecore.

            // 11-100: Shape Molds & Extruders.

            // 11-40: Common Steel Molds & Extruders addition.

            // 41-50: Slicer Blades (based on Steel Extruders).
            SLICER_BLADE_FLAT = GTLITE_ITEMS.addItem(41, "shape.slicer_blade.flat")
            SLICER_BLADE_STRIPES = GTLITE_ITEMS.addItem(42, "shape.slicer_blade.stripes")
            SLICER_BLADE_OCTAGONAL = GTLITE_ITEMS.addItem(43, "shape.slicer_blade.octagonal")

            // 51-70: Vanadium Steel Molds & Extruders.


        }

    }

}