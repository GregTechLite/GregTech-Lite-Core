package magicbook.gtlitecore.common.item

import gregtech.api.GTValues.M
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtech.api.unification.material.Materials
import gregtech.api.unification.stack.ItemMaterialInfo
import gregtech.api.unification.stack.MaterialStack
import magicbook.gtlitecore.api.GTLiteAPI
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

@Suppress("MISSING_DEPENDENCY_CLASS", "MISSING_DEPENDENCY_SUPERCLASS")
class GTLiteMetaItems
{

    companion object
    {

        private lateinit var GTLITE_ITEMS: MetaItem<*>

        lateinit var SHAPE_MOLD_ROD: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_MOLD_BOLT: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_MOLD_ROUND: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_MOLD_SCREW: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_MOLD_RING: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_MOLD_ROD_LONG: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_MOLD_TURBINE_BLADE: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_MOLD_DRILL_HEAD: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_EXTRUDER_ROUND: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_EXTRUDER_TURBINE_BLADE: MetaItem<*>.MetaValueItem
        lateinit var SHAPE_EXTRUDER_DRILL_HEAD: MetaItem<*>.MetaValueItem

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

            // 11-40: Common Steel Molds (11-25) & Extruders addition (26-40).
            SHAPE_MOLD_ROD = GTLITE_ITEMS.addItem(11, "shape.mold.rod")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_MOLD_BOLT = GTLITE_ITEMS.addItem(12, "shape.mold.bolt")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_MOLD_ROUND = GTLITE_ITEMS.addItem(13, "shape.mold.round")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_MOLD_SCREW = GTLITE_ITEMS.addItem(14, "shape.mold.screw")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_MOLD_RING = GTLITE_ITEMS.addItem(15, "shape.mold.ring")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_MOLD_ROD_LONG = GTLITE_ITEMS.addItem(16, "shape.mold.rod_long")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_MOLD_TURBINE_BLADE = GTLITE_ITEMS.addItem(17, "shape.mold.turbine_blade")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_MOLD_DRILL_HEAD = GTLITE_ITEMS.addItem(18, "shape.mold.drill_head")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_EXTRUDER_ROUND = GTLITE_ITEMS.addItem(26, "shape.extruder.round")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_EXTRUDER_TURBINE_BLADE = GTLITE_ITEMS.addItem(27, "shape.extruder.turbine_blade")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            SHAPE_EXTRUDER_DRILL_HEAD = GTLITE_ITEMS.addItem(28, "shape.extruder.drill_head")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.Steel, M * 4)))

            // 41-50: Slicer Blades (based on Steel Extruders).
            SLICER_BLADE_FLAT = GTLITE_ITEMS.addItem(41, "shape.slicer_blade.flat")
            SLICER_BLADE_STRIPES = GTLITE_ITEMS.addItem(42, "shape.slicer_blade.stripes")
            SLICER_BLADE_OCTAGONAL = GTLITE_ITEMS.addItem(43, "shape.slicer_blade.octagonal")

            // 51-70: Vanadium Steel Molds & Extruders.


        }

    }

}