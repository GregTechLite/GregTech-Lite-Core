package magicbook.gtlitecore.common.item

import gregtech.api.GTValues.M
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.ItemMaterialInfo
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.items.behaviors.TooltipBehavior
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.utils.AnimatedTextHandler
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.behavior.CircuitPatternBehavior
import magicbook.gtlitecore.common.item.behavior.FoodBehavior
import net.minecraft.client.resources.I18n
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

        lateinit var VOLTAGE_COIL_UHV: MetaItem<*>.MetaValueItem
        lateinit var VOLTAGE_COIL_UEV: MetaItem<*>.MetaValueItem
        lateinit var VOLTAGE_COIL_UIV: MetaItem<*>.MetaValueItem
        lateinit var VOLTAGE_COIL_UXV: MetaItem<*>.MetaValueItem
        lateinit var VOLTAGE_COIL_OpV: MetaItem<*>.MetaValueItem
        lateinit var VOLTAGE_COIL_MAX: MetaItem<*>.MetaValueItem

        lateinit var GOOWARE_BOARD: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_BOARD: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_BOARD: MetaItem<*>.MetaValueItem
        lateinit var ULTIMATE_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var PERFECT_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var INFINITE_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem

        lateinit var GOOWARE_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var GOOWARE_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var GOOWARE_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var GOOWARE_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var GOOWARE_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_SMD_INDUCTOR: MetaItem<*>.MetaValueItem

        lateinit var GOOWARE_PROCESSOR_ZPM: MetaItem<*>.MetaValueItem
        lateinit var GOOWARE_ASSEMBLY_UV: MetaItem<*>.MetaValueItem
        lateinit var GOOWARE_COMPUTER_UHV: MetaItem<*>.MetaValueItem
        lateinit var GOOWARE_MAINFRAME_UEV: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_PROCESSOR_UV: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_ASSEMBLY_UHV: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_COMPUTER_UEV: MetaItem<*>.MetaValueItem
        lateinit var OPTICAL_MAINFRAME_UIV: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_PROCESSOR_UHV: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_ASSEMBLY_UEV: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_COMPUTER_UIV: MetaItem<*>.MetaValueItem
        lateinit var SPINTRONIC_MAINFRAME_UXV: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_PROCESSOR_UEV: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_ASSEMBLY_UIV: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_COMPUTER_UXV: MetaItem<*>.MetaValueItem
        lateinit var COSMIC_MAINFRAME_OpV: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_PROCESSOR_UIV: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_ASSEMBLY_UXV: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_COMPUTER_OpV: MetaItem<*>.MetaValueItem
        lateinit var SUPRACAUSAL_MAINFRAME_MAX: MetaItem<*>.MetaValueItem

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

        lateinit var CIRCUIT_PATTERN: MetaItem<*>.MetaValueItem

        lateinit var VACUUM_TUBE_COMPONENT: MetaItem<*>.MetaValueItem

        lateinit var WRAP_COATED_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_PHENOLIC_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_PLASTIC_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_EPOXY_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_FIBER_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_MULTILAYER_FIBER_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_WETWARE_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_GOOWARE_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_OPTICAL_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SPINTRONIC_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_BASIC_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_GOOD_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_PLASTIC_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ADVANCED_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_EXTREME_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ELITE_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_WETWARE_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ULTIMATE_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_PERFECT_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_INFINITE_CIRCUIT_BOARD: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ADVANCED_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ADVANCED_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ADVANCED_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ADVANCED_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ADVANCED_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_GOOWARE_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_GOOWARE_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_GOOWARE_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_GOOWARE_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var WRAP_GOOWARE_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_OPTICAL_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_OPTICAL_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_OPTICAL_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_OPTICAL_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var WRAP_OPTICAL_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SPINTRONIC_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SPINTRONIC_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SPINTRONIC_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SPINTRONIC_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SPINTRONIC_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_COSMIC_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_COSMIC_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_COSMIC_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_COSMIC_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var WRAP_COSMIC_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SUPRACAUSAL_SMD_TRANSISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SUPRACAUSAL_SMD_RESISTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SUPRACAUSAL_SMD_CAPACITOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SUPRACAUSAL_SMD_DIODE: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SUPRACAUSAL_SMD_INDUCTOR: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CPU_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_RAM_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ILC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_NANO_CPU_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_QUBIT_CPU_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SIMPLE_SOC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_SOC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ADVANCED_SOC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_HIGHLY_ADVANCED_SOC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_NAND_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_NOR_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ULPIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_LPIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_PIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_HPIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_UHPIC_CHIP: MetaItem<*>.MetaValueItem

        lateinit var MICA_PULP: MetaItem<*>.MetaValueItem
        lateinit var MICA_PLATE: MetaItem<*>.MetaValueItem
        lateinit var MICA_INSULATOR_PLATE: MetaItem<*>.MetaValueItem
        lateinit var MICA_INSULATOR_FOIL: MetaItem<*>.MetaValueItem
        lateinit var SAND_DUST: MetaItem<*>.MetaValueItem

        lateinit var BANANA: MetaItem<*>.MetaValueItem
        lateinit var ORANGE: MetaItem<*>.MetaValueItem
        lateinit var MANGO: MetaItem<*>.MetaValueItem
        lateinit var APRICOT: MetaItem<*>.MetaValueItem
        lateinit var LEMON: MetaItem<*>.MetaValueItem
        lateinit var LIME: MetaItem<*>.MetaValueItem
        lateinit var OLIVE: MetaItem<*>.MetaValueItem
        lateinit var NUTMEG: MetaItem<*>.MetaValueItem
        lateinit var COCONUT: MetaItem<*>.MetaValueItem

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

            // 101-106: Voltage Coils.
            VOLTAGE_COIL_UHV = GTLITE_ITEMS.addItem(101, "voltage_coil.uhv")
            VOLTAGE_COIL_UEV = GTLITE_ITEMS.addItem(102, "voltage_coil.uev")
            VOLTAGE_COIL_UIV = GTLITE_ITEMS.addItem(103, "voltage_coil.uiv")
            VOLTAGE_COIL_UXV = GTLITE_ITEMS.addItem(104, "voltage_coil.uxv")
            VOLTAGE_COIL_OpV = GTLITE_ITEMS.addItem(105, "voltage_coil.opv")
            VOLTAGE_COIL_MAX = GTLITE_ITEMS.addItem(106, "voltage_coil.max")

            // 107-200: Covers.

            // 201-210: Boards and Circuit Boards.
            GOOWARE_BOARD = GTLITE_ITEMS.addItem(201, "board.gooware")
            OPTICAL_BOARD = GTLITE_ITEMS.addItem(202, "board.optical")
            SPINTRONIC_BOARD = GTLITE_ITEMS.addItem(203, "board.spintronic")

            ULTIMATE_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(206, "circuit_board.ultimate")
            PERFECT_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(207, "circuit_board.perfect")
            INFINITE_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(208, "circuit_board.infinite")

            // 211-250: SMDs.
            GOOWARE_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(211, "component.gooware_smd.transistor")
            GOOWARE_SMD_RESISTOR = GTLITE_ITEMS.addItem(212, "component.gooware_smd.resistor")
            GOOWARE_SMD_CAPACITOR = GTLITE_ITEMS.addItem(213, "component.gooware_smd.capacitor")
            GOOWARE_SMD_DIODE = GTLITE_ITEMS.addItem(214, "component.gooware_smd.diode")
            GOOWARE_SMD_INDUCTOR = GTLITE_ITEMS.addItem(215, "component.gooware_smd.inductor")
            OPTICAL_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(216, "component.optical_smd.transistor")
            OPTICAL_SMD_RESISTOR = GTLITE_ITEMS.addItem(217, "component.optical_smd.resistor")
            OPTICAL_SMD_CAPACITOR = GTLITE_ITEMS.addItem(218, "component.optical_smd.capacitor")
            OPTICAL_SMD_DIODE = GTLITE_ITEMS.addItem(219, "component.optical_smd.diode")
            OPTICAL_SMD_INDUCTOR = GTLITE_ITEMS.addItem(220, "component.optical_smd.inductor")
            SPINTRONIC_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(221, "component.spintronic_smd.transistor")
            SPINTRONIC_SMD_RESISTOR = GTLITE_ITEMS.addItem(222, "component.spintronic_smd.resistor")
            SPINTRONIC_SMD_CAPACITOR = GTLITE_ITEMS.addItem(223, "component.spintronic_smd.capacitor")
            SPINTRONIC_SMD_DIODE = GTLITE_ITEMS.addItem(224, "component.spintronic_smd.diode")
            SPINTRONIC_SMD_INDUCTOR = GTLITE_ITEMS.addItem(225, "component.spintronic_smd.inductor")
            COSMIC_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(226, "component.cosmic_smd.transistor")
            COSMIC_SMD_RESISTOR = GTLITE_ITEMS.addItem(227, "component.cosmic_smd.resistor")
            COSMIC_SMD_CAPACITOR = GTLITE_ITEMS.addItem(228, "component.cosmic_smd.capacitor")
            COSMIC_SMD_DIODE = GTLITE_ITEMS.addItem(229, "component.cosmic_smd.diode")
            COSMIC_SMD_INDUCTOR = GTLITE_ITEMS.addItem(230, "component.cosmic_smd.inductor")
            SUPRACAUSAL_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(231, "component.supracausal_smd.transistor")
            SUPRACAUSAL_SMD_RESISTOR = GTLITE_ITEMS.addItem(232, "component.supracausal_smd.resistor")
            SUPRACAUSAL_SMD_CAPACITOR = GTLITE_ITEMS.addItem(233, "component.supracausal_smd.capacitor")
            SUPRACAUSAL_SMD_DIODE = GTLITE_ITEMS.addItem(234, "component.supracausal_smd.diode")
            SUPRACAUSAL_SMD_INDUCTOR = GTLITE_ITEMS.addItem(235, "component.supracausal_smd.inductor")

            // 251-300: Circuits.
            GOOWARE_PROCESSOR_ZPM = GTLITE_ITEMS.addItem(251, "circuit.gooware_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ZPM)
            GOOWARE_ASSEMBLY_UV = GTLITE_ITEMS.addItem(252, "circuit.gooware_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV)
            GOOWARE_COMPUTER_UHV = GTLITE_ITEMS.addItem(253, "circuit.gooware_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV)
            GOOWARE_MAINFRAME_UEV = GTLITE_ITEMS.addItem(254, "circuit.gooware_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)

            OPTICAL_PROCESSOR_UV = GTLITE_ITEMS.addItem(255, "circuit.optical_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV)
            OPTICAL_ASSEMBLY_UHV = GTLITE_ITEMS.addItem(256, "circuit.optical_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV)
            OPTICAL_COMPUTER_UEV = GTLITE_ITEMS.addItem(257, "circuit.optical_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)
            OPTICAL_MAINFRAME_UIV = GTLITE_ITEMS.addItem(258, "circuit.optical_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)

            SPINTRONIC_PROCESSOR_UHV = GTLITE_ITEMS.addItem(259, "circuit.spintronic_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV)
            SPINTRONIC_ASSEMBLY_UEV = GTLITE_ITEMS.addItem(260, "circuit.spintronic_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)
            SPINTRONIC_COMPUTER_UIV = GTLITE_ITEMS.addItem(261, "circuit.spintronic_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
            SPINTRONIC_MAINFRAME_UXV = GTLITE_ITEMS.addItem(262, "circuit.spintronic_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV)

            COSMIC_PROCESSOR_UEV = GTLITE_ITEMS.addItem(263, "circuit.cosmic_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.cosmic_processor.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.cosmic_processor.tooltip.2",
                        1, 100, AnimatedTextHandler.DARK_PURPLE, AnimatedTextHandler.DARK_RED).get())
                })

            COSMIC_ASSEMBLY_UIV = GTLITE_ITEMS.addItem(264, "circuit.cosmic_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.cosmic_assembly.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.cosmic_assembly.tooltip.2",
                        1, 100, AnimatedTextHandler.DARK_PURPLE, AnimatedTextHandler.DARK_RED).get())
                })

            COSMIC_COMPUTER_UXV = GTLITE_ITEMS.addItem(265, "circuit.cosmic_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.cosmic_computer.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.cosmic_computer.tooltip.2",
                        1, 100, AnimatedTextHandler.DARK_PURPLE, AnimatedTextHandler.DARK_RED).get())
                })

            COSMIC_MAINFRAME_OpV = GTLITE_ITEMS.addItem(266, "circuit.cosmic_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.cosmic_mainframe.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.cosmic_mainframe.tooltip.2",
                        1, 100, AnimatedTextHandler.DARK_PURPLE, AnimatedTextHandler.DARK_RED).get())
                })

            SUPRACAUSAL_PROCESSOR_UIV = GTLITE_ITEMS.addItem(267, "circuit.supracausal_processor")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.supracausal_processor.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.supracausal_processor.tooltip.2",
                        1, 100, AnimatedTextHandler.GOLD, AnimatedTextHandler.YELLOW, AnimatedTextHandler.GREEN,
                        AnimatedTextHandler.AQUA, AnimatedTextHandler.BLUE, AnimatedTextHandler.LIGHT_PURPLE).get())
                })

            SUPRACAUSAL_ASSEMBLY_UXV = GTLITE_ITEMS.addItem(268, "circuit.supracausal_assembly")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.supracausal_assembly.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.supracausal_assembly.tooltip.2",
                        1, 100, AnimatedTextHandler.GOLD, AnimatedTextHandler.YELLOW, AnimatedTextHandler.GREEN,
                        AnimatedTextHandler.AQUA, AnimatedTextHandler.BLUE, AnimatedTextHandler.LIGHT_PURPLE).get())
                })

            SUPRACAUSAL_COMPUTER_OpV = GTLITE_ITEMS.addItem(269, "circuit.supracausal_computer")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.supracausal_computer.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.supracausal_computer.tooltip.2",
                        1, 100, AnimatedTextHandler.GOLD, AnimatedTextHandler.YELLOW, AnimatedTextHandler.GREEN,
                        AnimatedTextHandler.AQUA, AnimatedTextHandler.BLUE, AnimatedTextHandler.LIGHT_PURPLE).get())
                })

            SUPRACAUSAL_MAINFRAME_MAX = GTLITE_ITEMS.addItem(270, "circuit.supracausal_mainframe")
                .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MAX)
                .addComponents(TooltipBehavior { lines ->
                    lines.add(I18n.format("metaitem.circuit.supracausal_mainframe.tooltip.1"))
                    lines.add(AnimatedTextHandler.translatedAnimatedText("metaitem.circuit.supracausal_mainframe.tooltip.2",
                        1, 100, AnimatedTextHandler.RED + AnimatedTextHandler.BOLD,
                        AnimatedTextHandler.GOLD + AnimatedTextHandler.BOLD, AnimatedTextHandler.YELLOW + AnimatedTextHandler.BOLD,
                        AnimatedTextHandler.GREEN + AnimatedTextHandler.BOLD, AnimatedTextHandler.AQUA + AnimatedTextHandler.BOLD,
                        AnimatedTextHandler.BLUE + AnimatedTextHandler.BOLD, AnimatedTextHandler.LIGHT_PURPLE + AnimatedTextHandler.BOLD).get())
                })

            // 301-500: ...

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

            // ... (613-620)

            CIRCUIT_PATTERN = GTLITE_ITEMS.addItem(621, "tool.circuit_pattern")
                .addComponents(CircuitPatternBehavior())

            // 701-800: Batteries.

            // 1001-1100: ...

            // 1101-2000: Circuit Components.
            VACUUM_TUBE_COMPONENT = GTLITE_ITEMS.addItem(1101, "circuit.component.vacuum_tube_component")

            // 2001-2500: Boules and Wafers.

            // 3001-5000: Wrap Components
            WRAP_COATED_BOARD = GTLITE_ITEMS.addItem(3001, "wrap.board.coated")
            WRAP_PHENOLIC_BOARD = GTLITE_ITEMS.addItem(3002, "wrap.board.phenolic")
            WRAP_PLASTIC_BOARD = GTLITE_ITEMS.addItem(3003, "wrap.board.plastic")
            WRAP_EPOXY_BOARD = GTLITE_ITEMS.addItem(3004, "wrap.board.epoxy")
            WRAP_FIBER_BOARD = GTLITE_ITEMS.addItem(3005, "wrap.board.fiber_reinforced")
            WRAP_MULTILAYER_FIBER_BOARD = GTLITE_ITEMS.addItem(3006, "wrap.board.multilayer_fiber_reinforced")
            WRAP_WETWARE_BOARD = GTLITE_ITEMS.addItem(3007, "wrap.board.wetware")
            WRAP_GOOWARE_BOARD = GTLITE_ITEMS.addItem(3008, "wrap.board.gooware")
            WRAP_OPTICAL_BOARD = GTLITE_ITEMS.addItem(3009, "wrap.board.optical")
            WRAP_SPINTRONIC_BOARD = GTLITE_ITEMS.addItem(3010, "wrap.board.spintronic")
            WRAP_BASIC_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3011, "wrap.circuit_board.basic")
            WRAP_GOOD_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3012, "wrap.circuit_board.good")
            WRAP_PLASTIC_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3013, "wrap.circuit_board.plastic")
            WRAP_ADVANCED_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3014, "wrap.circuit_board.advanced")
            WRAP_EXTREME_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3015, "wrap.circuit_board.extreme")
            WRAP_ELITE_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3016, "wrap.circuit_board.elite")
            WRAP_WETWARE_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3017, "wrap.circuit_board.wetware")
            WRAP_ULTIMATE_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3018, "wrap.circuit_board.ultimate")
            WRAP_PERFECT_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3019, "wrap.circuit_board.perfect")
            WRAP_INFINITE_CIRCUIT_BOARD = GTLITE_ITEMS.addItem(3020, "wrap.circuit_board.infinite")
            WRAP_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(3021, "wrap.component.smd.transistor")
            WRAP_SMD_RESISTOR = GTLITE_ITEMS.addItem(3022, "wrap.component.smd.resistor")
            WRAP_SMD_CAPACITOR = GTLITE_ITEMS.addItem(3023, "wrap.component.smd.capacitor")
            WRAP_SMD_DIODE = GTLITE_ITEMS.addItem(3024, "wrap.component.smd.diode")
            WRAP_SMD_INDUCTOR = GTLITE_ITEMS.addItem(3025, "wrap.component.smd.inductor")
            WRAP_ADVANCED_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(3026, "wrap.component.advanced_smd.transistor")
            WRAP_ADVANCED_SMD_RESISTOR = GTLITE_ITEMS.addItem(3027, "wrap.component.advanced_smd.resistor")
            WRAP_ADVANCED_SMD_CAPACITOR = GTLITE_ITEMS.addItem(3028, "wrap.component.advanced_smd.capacitor")
            WRAP_ADVANCED_SMD_DIODE = GTLITE_ITEMS.addItem(3029, "wrap.component.advanced_smd.diode")
            WRAP_ADVANCED_SMD_INDUCTOR = GTLITE_ITEMS.addItem(3030, "wrap.component.advanced_smd.inductor")
            WRAP_GOOWARE_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(3031, "wrap.component.gooware_smd.transistor")
            WRAP_GOOWARE_SMD_RESISTOR = GTLITE_ITEMS.addItem(3032, "wrap.component.gooware_smd.resistor")
            WRAP_GOOWARE_SMD_CAPACITOR = GTLITE_ITEMS.addItem(3033, "wrap.component.gooware_smd.capacitor")
            WRAP_GOOWARE_SMD_DIODE = GTLITE_ITEMS.addItem(3034, "wrap.component.gooware_smd.diode")
            WRAP_GOOWARE_SMD_INDUCTOR = GTLITE_ITEMS.addItem(3035, "wrap.component.gooware_smd.inductor")
            WRAP_OPTICAL_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(3036, "wrap.component.optical_smd.transistor")
            WRAP_OPTICAL_SMD_RESISTOR = GTLITE_ITEMS.addItem(3037, "wrap.component.optical_smd.resistor")
            WRAP_OPTICAL_SMD_CAPACITOR = GTLITE_ITEMS.addItem(3038, "wrap.component.optical_smd.capacitor")
            WRAP_OPTICAL_SMD_DIODE = GTLITE_ITEMS.addItem(3039, "wrap.component.optical_smd.diode")
            WRAP_OPTICAL_SMD_INDUCTOR = GTLITE_ITEMS.addItem(3040, "wrap.component.optical_smd.inductor")
            WRAP_SPINTRONIC_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(3041, "wrap.component.spintronic_smd.transistor")
            WRAP_SPINTRONIC_SMD_RESISTOR = GTLITE_ITEMS.addItem(3042, "wrap.component.spintronic_smd.resistor")
            WRAP_SPINTRONIC_SMD_CAPACITOR = GTLITE_ITEMS.addItem(3043, "wrap.component.spintronic_smd.capacitor")
            WRAP_SPINTRONIC_SMD_DIODE = GTLITE_ITEMS.addItem(3044, "wrap.component.spintronic_smd.diode")
            WRAP_SPINTRONIC_SMD_INDUCTOR = GTLITE_ITEMS.addItem(3045, "wrap.component.spintronic_smd.inductor")
            WRAP_COSMIC_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(3046, "wrap.component.cosmic_smd.transistor")
            WRAP_COSMIC_SMD_RESISTOR = GTLITE_ITEMS.addItem(3047, "wrap.component.cosmic_smd.resistor")
            WRAP_COSMIC_SMD_CAPACITOR = GTLITE_ITEMS.addItem(3048, "wrap.component.cosmic_smd.capacitor")
            WRAP_COSMIC_SMD_DIODE = GTLITE_ITEMS.addItem(3049, "wrap.component.cosmic_smd.diode")
            WRAP_COSMIC_SMD_INDUCTOR = GTLITE_ITEMS.addItem(3050, "wrap.component.cosmic_smd.inductor")
            WRAP_SUPRACAUSAL_SMD_TRANSISTOR = GTLITE_ITEMS.addItem(3051, "wrap.component.supracausal_smd.transistor")
            WRAP_SUPRACAUSAL_SMD_RESISTOR = GTLITE_ITEMS.addItem(3052, "wrap.component.supracausal_smd.resistor")
            WRAP_SUPRACAUSAL_SMD_CAPACITOR = GTLITE_ITEMS.addItem(3053, "wrap.component.supracausal_smd.capacitor")
            WRAP_SUPRACAUSAL_SMD_DIODE = GTLITE_ITEMS.addItem(3054, "wrap.component.supracausal_smd.diode")
            WRAP_SUPRACAUSAL_SMD_INDUCTOR = GTLITE_ITEMS.addItem(3055, "wrap.component.supracausal_smd.inductor")
            WRAP_CPU_CHIP = GTLITE_ITEMS.addItem(3056, "wrap.wafer.chip.central_processing_unit")
            WRAP_RAM_CHIP = GTLITE_ITEMS.addItem(3057, "wrap.wafer.chip.random_access_memory")
            WRAP_ILC_CHIP = GTLITE_ITEMS.addItem(3058, "wrap.wafer.chip.integrated_logic_circuit")
            WRAP_NANO_CPU_CHIP = GTLITE_ITEMS.addItem(3059, "wrap.wafer.chip.nano_central_processing_unit")
            WRAP_QUBIT_CPU_CHIP = GTLITE_ITEMS.addItem(3060, "wrap.wafer.chip.qubit_central_processing_unit")
            WRAP_SIMPLE_SOC_CHIP = GTLITE_ITEMS.addItem(3061,  "wrap.wafer.chip.simple_system_on_chip")
            WRAP_SOC_CHIP = GTLITE_ITEMS.addItem(3062, "wrap.wafer.chip.system_on_chip")
            WRAP_ADVANCED_SOC_CHIP = GTLITE_ITEMS.addItem(3063, "wrap.wafer.chip.advanced_system_on_chip")
            WRAP_HIGHLY_ADVANCED_SOC_CHIP = GTLITE_ITEMS.addItem(3064, "wrap.wafer.chip.highly_advanced_system_on_chip")
            WRAP_NAND_CHIP = GTLITE_ITEMS.addItem(3065, "wrap.wafer.chip.nand_memory_chip")
            WRAP_NOR_CHIP = GTLITE_ITEMS.addItem(3066, "wrap.wafer.chip.nor_memory_chip")
            WRAP_ULPIC_CHIP = GTLITE_ITEMS.addItem(3067, "wrap.wafer.chip.ultra_low_power_integrated_circuit")
            WRAP_LPIC_CHIP = GTLITE_ITEMS.addItem(3068, "wrap.wafer.chip.low_power_integrated_circuit")
            WRAP_PIC_CHIP = GTLITE_ITEMS.addItem(3069, "wrap.wafer.chip.power_integrated_circuit")
            WRAP_HPIC_CHIP = GTLITE_ITEMS.addItem(3070, "wrap.wafer.chip.high_power_integrated_circuit")
            WRAP_UHPIC_CHIP = GTLITE_ITEMS.addItem(3071, "wrap.wafer.chip.ultra_high_power_integrated_circuit")

            // 5001-8000: Miscellaneous Materials.
            MICA_PULP = GTLITE_ITEMS.addItem(5001, "material.dust.gelatinous_mica_pulp")
                .addOreDict("dustMicaGelatinous")

            MICA_PLATE = GTLITE_ITEMS.addItem(5002, "material.plate.mica_mineral_wool")
                .addOreDict("plateMicaMineralWool")

            MICA_INSULATOR_PLATE = GTLITE_ITEMS.addItem(5003, "material.plate.mica_insulator")
                .addOreDict("plateMicaInsulator")

            MICA_INSULATOR_FOIL = GTLITE_ITEMS.addItem(5004, "material.foil.mica_insulator")

            SAND_DUST = GTLITE_ITEMS.addItem(5005, "material.dust.sand")
                .addOreDict("dustSand")

            // 9001-10000: Miscellaneous Foods.
            BANANA = GTLITE_ITEMS.addItem(9001, "food.fruit.banana")
                .addComponents(FoodBehavior(2, 1f)
                    .setEatingDuration(3 * SECOND))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitBanana")
                .addOreDict("cropBanana")

            ORANGE = GTLITE_ITEMS.addItem(9002, "food.fruit.orange")
                .addComponents(FoodBehavior(2, 1f)
                    .setEatingDuration(2 * SECOND + 10 * TICK))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitOrange")
                .addOreDict("cropOrange")

            MANGO = GTLITE_ITEMS.addItem(9003, "food.fruit.mango")
                .addComponents(FoodBehavior(2, 1f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitMango")
                .addOreDict("cropMango")

            APRICOT = GTLITE_ITEMS.addItem(9004, "food.fruit.apricot")
                .addComponents(FoodBehavior(2, 1f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitApricot")
                .addOreDict("cropApricot")

            LEMON = GTLITE_ITEMS.addItem(9005, "food.fruit.lemon")
                .addComponents(FoodBehavior(1, 0.5f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitLemon")
                .addOreDict("cropLemon")

            LIME = GTLITE_ITEMS.addItem(9006, "food.fruit.lime")
                .addComponents(FoodBehavior(1, 0.5f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitLime")
                .addOreDict("cropLime")

            OLIVE = GTLITE_ITEMS.addItem(9007, "food.fruit.olive")
                .addComponents(FoodBehavior(2, 0.5f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitOlive")
                .addOreDict("cropOlive")

            NUTMEG = GTLITE_ITEMS.addItem(9008, "food.fruit.nutmeg")
                .addOreDict("cropNutmeg")

            COCONUT = GTLITE_ITEMS.addItem(9009, "food.fruit.coconut")
                .addOreDict("cropCoconut")

        }

    }

}