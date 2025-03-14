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

        lateinit var CASTING_MOLD_EMPTY: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_SAW: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_HARD_HAMMER: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_SOFT_MALLET: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_WRENCH: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_FILE: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_CROWBAR: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_SCREWDRIVER: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_MORTAR: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_WIRE_CUTTER: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_KNIFE: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_BUTCHERY_KNIFE: MetaItem<*>.MetaValueItem
        lateinit var CASTING_MOLD_ROLLING_PIN: MetaItem<*>.MetaValueItem

        lateinit var DISPOSABLE_SAW: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_HARD_HAMMER: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_SOFT_MALLET: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_WRENCH: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_FILE: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_CROWBAR: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_SCREWDRIVER: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_MORTAR: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_WIRE_CUTTER: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_KNIFE: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_BUTCHERY_KNIFE: MetaItem<*>.MetaValueItem
        lateinit var DISPOSABLE_ROLLING_PIN: MetaItem<*>.MetaValueItem

        lateinit var VACUUM_TUBE_COMPONENT: MetaItem<*>.MetaValueItem

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
            CASTING_MOLD_EMPTY = GTLITE_ITEMS.addItem(51, "shape.mold.vanadium_steel.empty")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_SAW = GTLITE_ITEMS.addItem(52, "shape.mold.vanadium_steel.saw")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_HARD_HAMMER = GTLITE_ITEMS.addItem(53, "shape.mold.vanadium_steel.hard_hammer")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_SOFT_MALLET = GTLITE_ITEMS.addItem(54, "shape.mold.vanadium_steel.soft_mallet")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_WRENCH = GTLITE_ITEMS.addItem(55, "shape.mold.vanadium_steel.wrench")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_FILE = GTLITE_ITEMS.addItem(56, "shape.mold.vanadium_steel.file")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_CROWBAR = GTLITE_ITEMS.addItem(57, "shape.mold.vanadium_steel.crowbar")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_SCREWDRIVER = GTLITE_ITEMS.addItem(58, "shape.mold.vanadium_steel.screwdriver")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_MORTAR = GTLITE_ITEMS.addItem(59, "shape.mold.vanadium_steel.mortar")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_WIRE_CUTTER = GTLITE_ITEMS.addItem(60, "shape.mold.vanadium_steel.wire_cutter")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_KNIFE = GTLITE_ITEMS.addItem(61, "shape.mold.vanadium_steel.knife")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_BUTCHERY_KNIFE = GTLITE_ITEMS.addItem(62, "shape.mold.vanadium_steel.butchery_knife")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            CASTING_MOLD_ROLLING_PIN = GTLITE_ITEMS.addItem(63, "shape.mold.vanadium_steel.rolling_pin")
                .setMaterialInfo(ItemMaterialInfo(MaterialStack(Materials.VanadiumSteel, M * 4)))

            // 101-200:....

            // 501-600: Tool Components.

            // 601-700: Tools.
            DISPOSABLE_SAW = GTLITE_ITEMS.addItem(601, "tool.disposable.saw")
                .addOreDict("toolSaw")
                .addOreDict("craftingToolSaw")

            DISPOSABLE_HARD_HAMMER = GTLITE_ITEMS.addItem(602, "tool.disposable.hard_hammer")
                .addOreDict("toolHammer")
                .addOreDict("craftingToolHardHammer")

            DISPOSABLE_SOFT_MALLET = GTLITE_ITEMS.addItem(603, "tool.disposable.soft_mallet")
                .addOreDict("toolMallet")
                .addOreDict("craftingToolSoftHammer")

            DISPOSABLE_WRENCH = GTLITE_ITEMS.addItem(604, "tool.disposable.wrench")
                .addOreDict("toolWrench")
                .addOreDict("craftingToolWrench")

            DISPOSABLE_FILE = GTLITE_ITEMS.addItem(605, "tool.disposable.file")
                .addOreDict("toolFile")
                .addOreDict("craftingToolFile")

            DISPOSABLE_CROWBAR = GTLITE_ITEMS.addItem(606, "tool.disposable.crowbar")
                .addOreDict("toolCrowbar")
                .addOreDict("craftingToolCrowbar")

            DISPOSABLE_SCREWDRIVER = GTLITE_ITEMS.addItem(607, "tool.disposable.screwdriver")
                .addOreDict("toolScrewdriver")
                .addOreDict("craftingToolScrewdriver")

            DISPOSABLE_MORTAR = GTLITE_ITEMS.addItem(608, "tool.disposable.mortar")
                .addOreDict("toolMortar")
                .addOreDict("craftingToolMortar")

            DISPOSABLE_WIRE_CUTTER = GTLITE_ITEMS.addItem(609, "tool.disposable.wire_cutter")
                .addOreDict("toolWireCutter")
                .addOreDict("craftingToolWireCutter")

            DISPOSABLE_KNIFE = GTLITE_ITEMS.addItem(610, "tool.disposable.knife")
                .addOreDict("toolKnife")
                .addOreDict("craftingToolKnife")

            DISPOSABLE_BUTCHERY_KNIFE = GTLITE_ITEMS.addItem(611, "tool.disposable.butchery_knife")
                .addOreDict("toolButcheryKnife")
                .addOreDict("craftingToolButcheryKnife")

            DISPOSABLE_ROLLING_PIN = GTLITE_ITEMS.addItem(612, "tool.disposable.rolling_pin")
                .addOreDict("toolRollingPin")
                .addOreDict("craftingToolRollingPin")

            // 701-800: Batteries.

            // 1001-1100: Circuits.

            // 1101-2000: Circuit Components.
            VACUUM_TUBE_COMPONENT = GTLITE_ITEMS.addItem(1101, "circuit.component.vacuum_tube_component")

            // 2001-2500: Boules and Wafers.

        }

    }

}