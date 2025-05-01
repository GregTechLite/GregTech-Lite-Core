package magicbook.gtlitecore.common.item

import gregtech.api.GTValues.M
import gregtech.api.creativetab.BaseCreativeTab
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.ItemMaterialInfo
import gregtech.api.unification.stack.MaterialStack
import gregtech.api.util.RandomPotionEffect
import gregtech.common.items.behaviors.TooltipBehavior
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.utils.AnimatedTextHandler
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.item.behavior.CircuitPatternBehavior
import magicbook.gtlitecore.common.item.behavior.FoodBehavior
import magicbook.gtlitecore.common.item.behavior.HaloRenderItemBehavior
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.init.MobEffects
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS", "MISSING_DEPENDENCY_SUPERCLASS")
class GTLiteMetaItems
{

    companion object
    {

        private lateinit var GTLITE_ITEMS: MetaItem<*>

        lateinit var LOGO_CORE: MetaItem<*>.MetaValueItem
        lateinit var LOGO_MACHINE: MetaItem<*>.MetaValueItem
        lateinit var LOGO_DECORATION: MetaItem<*>.MetaValueItem
        lateinit var LOGO_FOOD: MetaItem<*>.MetaValueItem

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

        lateinit var CREDIT_ADAMANTIUM: MetaItem<*>.MetaValueItem
        lateinit var CREDIT_VIBRANIUM: MetaItem<*>.MetaValueItem
        lateinit var CREDIT_COSMIC_NEUTRONIUM: MetaItem<*>.MetaValueItem
        lateinit var CREDIT_INFINITY: MetaItem<*>.MetaValueItem

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

        lateinit var MINING_DRONE_LV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_MV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_HV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_EV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_IV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_LuV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_ZPM: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_UV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_UHV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_UEV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_UIV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_UXV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_OpV: MetaItem<*>.MetaValueItem
        lateinit var MINING_DRONE_MAX: MetaItem<*>.MetaValueItem

        lateinit var COMPONENT_GRINDER_BORON_NITRIDE: MetaItem<*>.MetaValueItem

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
        lateinit var MAGNETRON: MetaItem<*>.MetaValueItem

        lateinit var DIRTY_PETRI_DISH: MetaItem<*>.MetaValueItem
        lateinit var BREVIBACTERIUM_FLAVUM_PETRI_DISH: MetaItem<*>.MetaValueItem
        lateinit var CUPRIAVIDUS_NECATOR_PETRI_DISH: MetaItem<*>.MetaValueItem
        lateinit var ELECTRIC_SIGNAL_PETRI_DISH: MetaItem<*>.MetaValueItem

        lateinit var FUEL_ROD_EMPTY: MetaItem<*>.MetaValueItem

        lateinit var VACUUM_TUBE_COMPONENT: MetaItem<*>.MetaValueItem
        lateinit var DIELECTRIC_MIRROR: MetaItem<*>.MetaValueItem
        lateinit var ENGRAVED_DIAMOND_CHIP: MetaItem<*>.MetaValueItem
        lateinit var ENGRAVED_RUBY_CHIP: MetaItem<*>.MetaValueItem
        lateinit var ENGRAVED_SAPPHIRE_CHIP: MetaItem<*>.MetaValueItem

        lateinit var DIAMOND_MODULATOR: MetaItem<*>.MetaValueItem
        lateinit var RUBY_MODULATOR: MetaItem<*>.MetaValueItem
        lateinit var SAPPHIRE_MODULATOR: MetaItem<*>.MetaValueItem
        lateinit var CRYSTAL_SOC_SOCKET: MetaItem<*>.MetaValueItem
        lateinit var BZ_REACTION_CHAMBER: MetaItem<*>.MetaValueItem
        lateinit var NONLINEAR_CHEMICAL_OSCILLATOR: MetaItem<*>.MetaValueItem

        lateinit var HASSIUM_BOULE: MetaItem<*>.MetaValueItem

        lateinit var HASSIUM_WAFER: MetaItem<*>.MetaValueItem

        lateinit var NANO_PIC_WAFER: MetaItem<*>.MetaValueItem
        lateinit var NANO_PIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var PICO_PIC_WAFER: MetaItem<*>.MetaValueItem
        lateinit var PICO_PIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var FEMTO_PIC_WAFER: MetaItem<*>.MetaValueItem
        lateinit var FEMTO_PIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var ATTO_PIC_WAFER: MetaItem<*>.MetaValueItem
        lateinit var ATTO_PIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var ADVANCED_RAM_WAFER: MetaItem<*>.MetaValueItem
        lateinit var ADVANCED_RAM_CHIP: MetaItem<*>.MetaValueItem
        lateinit var ULTRA_HIGHLY_ADVANCED_SOC_WAFER: MetaItem<*>.MetaValueItem
        lateinit var ULTRA_HIGHLY_ADVANCED_SOC_CHIP: MetaItem<*>.MetaValueItem

        lateinit var EUROPIUM_DOPED_CUBIC_ZIRCONIA_BOULE: MetaItem<*>.MetaValueItem
        lateinit var EUROPIUM_DOPED_CUBIC_ZIRCONIA_WAFER: MetaItem<*>.MetaValueItem
        lateinit var CRYSTAL_INTERFACE_WAFER: MetaItem<*>.MetaValueItem
        lateinit var CRYSTAL_INTERFACE_CHIP: MetaItem<*>.MetaValueItem

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
        lateinit var WRAP_NPIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_PPIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_FPIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_APIC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ARAM_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_UHASOC_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CRYSTAL_INTERFACE_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_NONLINEAR_CHEMICAL_OSCILLATOR: MetaItem<*>.MetaValueItem

        lateinit var WRAP_ENGRAVED_LAPOTRON_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ENGRAVED_DIAMOND_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ENGRAVED_RUBY_CHIP: MetaItem<*>.MetaValueItem
        lateinit var WRAP_ENGRAVED_SAPPHIRE_CHIP: MetaItem<*>.MetaValueItem

        lateinit var WRAP_CRYSTAL_CPU: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CRYSTAL_SOC: MetaItem<*>.MetaValueItem
        lateinit var WRAP_NEURO_PROCESSOR: MetaItem<*>.MetaValueItem

        lateinit var WRAP_CIRCUIT_ULV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_LV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_MV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_HV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_EV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_IV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_LuV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_ZPM: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_UV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_UHV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_UEV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_UIV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_UXV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_OpV: MetaItem<*>.MetaValueItem
        lateinit var WRAP_CIRCUIT_MAX: MetaItem<*>.MetaValueItem

        lateinit var MICA_PULP: MetaItem<*>.MetaValueItem
        lateinit var MICA_PLATE: MetaItem<*>.MetaValueItem
        lateinit var MICA_INSULATOR_PLATE: MetaItem<*>.MetaValueItem
        lateinit var MICA_INSULATOR_FOIL: MetaItem<*>.MetaValueItem
        lateinit var SAND_DUST: MetaItem<*>.MetaValueItem
        lateinit var MUD_BALL: MetaItem<*>.MetaValueItem

        lateinit var BANANA: MetaItem<*>.MetaValueItem
        lateinit var ORANGE: MetaItem<*>.MetaValueItem
        lateinit var MANGO: MetaItem<*>.MetaValueItem
        lateinit var APRICOT: MetaItem<*>.MetaValueItem
        lateinit var LEMON: MetaItem<*>.MetaValueItem
        lateinit var LIME: MetaItem<*>.MetaValueItem
        lateinit var OLIVE: MetaItem<*>.MetaValueItem
        lateinit var NUTMEG: MetaItem<*>.MetaValueItem
        lateinit var COCONUT: MetaItem<*>.MetaValueItem

        lateinit var COFFEE_SEED: MetaItem<*>.MetaValueItem
        lateinit var TOMATO_SEED: MetaItem<*>.MetaValueItem
        lateinit var ONION_SEED: MetaItem<*>.MetaValueItem
        lateinit var CUCUMBER_SEED: MetaItem<*>.MetaValueItem
        lateinit var GRAPE_SEED: MetaItem<*>.MetaValueItem
        lateinit var SOY_SEED: MetaItem<*>.MetaValueItem
        lateinit var BEAN_SEED: MetaItem<*>.MetaValueItem
        lateinit var PEA_SEED: MetaItem<*>.MetaValueItem
        lateinit var OREGANO_SEED: MetaItem<*>.MetaValueItem
        lateinit var HORSERADISH_SEED: MetaItem<*>.MetaValueItem
        lateinit var GARLIC_SEED: MetaItem<*>.MetaValueItem
        lateinit var BASIL_SEED: MetaItem<*>.MetaValueItem
        lateinit var AUBERGINE_SEED: MetaItem<*>.MetaValueItem
        lateinit var CORN_SEED: MetaItem<*>.MetaValueItem
        lateinit var ARTICHOKE_SEED: MetaItem<*>.MetaValueItem
        lateinit var BLACK_PEPPER_SEED: MetaItem<*>.MetaValueItem
        lateinit var RICE_SEED: MetaItem<*>.MetaValueItem
        lateinit var WHITE_GRAPE_SEED: MetaItem<*>.MetaValueItem
        lateinit var COTTON_SEED: MetaItem<*>.MetaValueItem

        lateinit var COFFEE_CHERRY: MetaItem<*>.MetaValueItem
        lateinit var TOMATO: MetaItem<*>.MetaValueItem
        lateinit var ONION: MetaItem<*>.MetaValueItem
        lateinit var CUCUMBER: MetaItem<*>.MetaValueItem
        lateinit var GRAPE: MetaItem<*>.MetaValueItem
        lateinit var SOYBEAN: MetaItem<*>.MetaValueItem
        lateinit var BEAN: MetaItem<*>.MetaValueItem
        lateinit var PEA: MetaItem<*>.MetaValueItem
        lateinit var OREGANO: MetaItem<*>.MetaValueItem
        lateinit var HORSERADISH: MetaItem<*>.MetaValueItem
        lateinit var GARLIC_BULB: MetaItem<*>.MetaValueItem
        lateinit var BASIL: MetaItem<*>.MetaValueItem
        lateinit var AUBERGINE: MetaItem<*>.MetaValueItem
        lateinit var CORN: MetaItem<*>.MetaValueItem
        lateinit var ARTICHOKE: MetaItem<*>.MetaValueItem
        lateinit var BLACK_PEPPER: MetaItem<*>.MetaValueItem
        lateinit var RICE: MetaItem<*>.MetaValueItem
        lateinit var WHITE_GRAPE: MetaItem<*>.MetaValueItem
        lateinit var COTTON: MetaItem<*>.MetaValueItem

        lateinit var GRAPE_JUICE: MetaItem<*>.MetaValueItem
        lateinit var RED_WINE: MetaItem<*>.MetaValueItem
        lateinit var VINEGAR: MetaItem<*>.MetaValueItem
        lateinit var POTATO_JUICE: MetaItem<*>.MetaValueItem
        lateinit var VODKA: MetaItem<*>.MetaValueItem

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
            LOGO_CORE = GTLITE_ITEMS.addItem(1, "gtlite_logo.core")
                .setInvisible()
            LOGO_MACHINE = GTLITE_ITEMS.addItem(2, "gtlite_logo.machine")
                .setInvisible()
            LOGO_DECORATION = GTLITE_ITEMS.addItem(3, "gtlite_logo.decoration")
                .setInvisible()
            LOGO_FOOD = GTLITE_ITEMS.addItem(4, "gtlite_logo.food")
                .setInvisible()

            // 11-85: Shape Molds & Extruders.

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

            // 86-100: Credits
            CREDIT_ADAMANTIUM = GTLITE_ITEMS.addItem(86, "credit.adamantium")
                .setRarity(EnumRarity.EPIC)
            CREDIT_VIBRANIUM = GTLITE_ITEMS.addItem(87, "credit.vibranium")
                .setRarity(EnumRarity.EPIC)
            CREDIT_COSMIC_NEUTRONIUM = GTLITE_ITEMS.addItem(88, "credit.cosmic_neutronium")
                .addComponents(HaloRenderItemBehavior(10, 0x33FFFFFF, { GTLiteTextures.HALO_NOISE }, true))
                .setRarity(EnumRarity.EPIC)
            CREDIT_INFINITY = GTLITE_ITEMS.addItem(89, "credit.infinity")
                .addComponents(HaloRenderItemBehavior(10, 0xFF000000.toInt(), { GTLiteTextures.HALO }, true))
                .setRarity(EnumRarity.EPIC)

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

            // 301-500: Covers and Cover Components.

            MINING_DRONE_LV = GTLITE_ITEMS.addItem(321, "mining_drone.lv")
                .setMaxStackSize(16)

            MINING_DRONE_MV = GTLITE_ITEMS.addItem(322, "mining_drone.mv")
                .setMaxStackSize(16)

            MINING_DRONE_HV = GTLITE_ITEMS.addItem(323, "mining_drone.hv")
                .setMaxStackSize(16)

            MINING_DRONE_EV = GTLITE_ITEMS.addItem(324, "mining_drone.ev")
                .setMaxStackSize(16)

            MINING_DRONE_IV = GTLITE_ITEMS.addItem(325, "mining_drone.iv")
                .setMaxStackSize(16)

            MINING_DRONE_LuV = GTLITE_ITEMS.addItem(326, "mining_drone.luv")
                .setMaxStackSize(16)

            MINING_DRONE_ZPM = GTLITE_ITEMS.addItem(327, "mining_drone.zpm")
                .setMaxStackSize(16)

            MINING_DRONE_UV = GTLITE_ITEMS.addItem(328, "mining_drone.uv")
                .setMaxStackSize(16)

            MINING_DRONE_UHV = GTLITE_ITEMS.addItem(329, "mining_drone.uhv")
                .setMaxStackSize(16)

            MINING_DRONE_UEV = GTLITE_ITEMS.addItem(330, "mining_drone.uev")
                .setMaxStackSize(16)

            MINING_DRONE_UIV = GTLITE_ITEMS.addItem(331, "mining_drone.uiv")
                .setMaxStackSize(16)

            MINING_DRONE_UXV = GTLITE_ITEMS.addItem(332, "mining_drone.uxv")
                .setMaxStackSize(16)

            MINING_DRONE_OpV = GTLITE_ITEMS.addItem(333, "mining_drone.opv")
                .setMaxStackSize(16)

            MINING_DRONE_MAX = GTLITE_ITEMS.addItem(334, "mining_drone.max")
                .setMaxStackSize(16)

            // 501-600: Tool Components.
            COMPONENT_GRINDER_BORON_NITRIDE = GTLITE_ITEMS.addItem(501, "tool.component.grinder.boron_nitride")

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

            MAGNETRON = GTLITE_ITEMS.addItem(622, "tool.magnetron")

            // ...

            DIRTY_PETRI_DISH = GTLITE_ITEMS.addItem(631, "tool.petri_dish.dirty")
            BREVIBACTERIUM_FLAVUM_PETRI_DISH = GTLITE_ITEMS.addItem(632, "tool.petri_dish.brevibacterium_flavum")
            CUPRIAVIDUS_NECATOR_PETRI_DISH = GTLITE_ITEMS.addItem(633, "tool.petri_dish.cupriavidus_necator")
            ELECTRIC_SIGNAL_PETRI_DISH = GTLITE_ITEMS.addItem(634, "tool.petri_dish.electric_signal")

            // 701-800: Batteries.
            FUEL_ROD_EMPTY = GTLITE_ITEMS.addItem(701, "fuel_rod.empty")

            // 1001-1100: ...

            // 1101-2000: Circuit Components.
            VACUUM_TUBE_COMPONENT = GTLITE_ITEMS.addItem(1101, "circuit.component.vacuum_tube_component")
            DIELECTRIC_MIRROR = GTLITE_ITEMS.addItem(1102, "circuit.component.dielectric_mirror")
            ENGRAVED_DIAMOND_CHIP = GTLITE_ITEMS.addItem(1103, "circuit.component.engraved_diamond_chip")
            ENGRAVED_RUBY_CHIP = GTLITE_ITEMS.addItem(1104, "circuit.component.engraved_ruby_chip")
            ENGRAVED_SAPPHIRE_CHIP = GTLITE_ITEMS.addItem(1105, "circuit.component.engraved_sapphire_chip")

            DIAMOND_MODULATOR = GTLITE_ITEMS.addItem(1111, "circuit.component.diamond_modulator")
            RUBY_MODULATOR = GTLITE_ITEMS.addItem(1112, "circuit.component.ruby_modulator")
            SAPPHIRE_MODULATOR = GTLITE_ITEMS.addItem(1113, "circuit.component.sapphire_modulator")
            CRYSTAL_SOC_SOCKET = GTLITE_ITEMS.addItem(1114, "circuit.component.crystal_system_on_chip_socket")
            BZ_REACTION_CHAMBER = GTLITE_ITEMS.addItem(1115, "circuit.component.bz_reaction_chamber")
            NONLINEAR_CHEMICAL_OSCILLATOR = GTLITE_ITEMS.addItem(1116, "circuit.component.nonlinear_chemical_oscillator")

            // 2001-2500: Boules and Wafers.
            HASSIUM_BOULE = GTLITE_ITEMS.addItem(2001, "boule.hassium")

            HASSIUM_WAFER = GTLITE_ITEMS.addItem(2006, "wafer.hassium")

            NANO_PIC_WAFER = GTLITE_ITEMS.addItem(2011, "wafer.nano_power_integrated_circuit")
            NANO_PIC_CHIP = GTLITE_ITEMS.addItem(2012, "wafer.chip.nano_power_integrated_circuit")
            PICO_PIC_WAFER = GTLITE_ITEMS.addItem(2013, "wafer.pico_power_integrated_circuit")
            PICO_PIC_CHIP = GTLITE_ITEMS.addItem(2014, "wafer.chip.pico_power_integrated_circuit")
            FEMTO_PIC_WAFER = GTLITE_ITEMS.addItem(2015, "wafer.femto_power_integrated_circuit")
            FEMTO_PIC_CHIP = GTLITE_ITEMS.addItem(2016, "wafer.chip.femto_power_integrated_circuit")
            ATTO_PIC_WAFER = GTLITE_ITEMS.addItem(2017, "wafer.atto_power_integrated_circuit")
            ATTO_PIC_CHIP = GTLITE_ITEMS.addItem(2018, "wafer.chip.atto_power_integrated_circuit")
            ADVANCED_RAM_WAFER = GTLITE_ITEMS.addItem(2019, "wafer.advanced_random_access_memory")
            ADVANCED_RAM_CHIP = GTLITE_ITEMS.addItem(2020, "wafer.chip.advanced_random_access_memory")
            ULTRA_HIGHLY_ADVANCED_SOC_WAFER = GTLITE_ITEMS.addItem(2021, "wafer.ultra_highly_advanced_system_on_chip")
            ULTRA_HIGHLY_ADVANCED_SOC_CHIP = GTLITE_ITEMS.addItem(2022, "wafer.chip.ultra_highly_advanced_system_on_chip")

            EUROPIUM_DOPED_CUBIC_ZIRCONIA_BOULE = GTLITE_ITEMS.addItem(2401, "boule.cubic_zirconia.europium")
            EUROPIUM_DOPED_CUBIC_ZIRCONIA_WAFER = GTLITE_ITEMS.addItem(2402, "wafer.cubic_zirconia.europium")
            CRYSTAL_INTERFACE_WAFER = GTLITE_ITEMS.addItem(2403, "wafer.crystal_interface")
            CRYSTAL_INTERFACE_CHIP = GTLITE_ITEMS.addItem(2404, "wafer.chip.crystal_interface")

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
            WRAP_NPIC_CHIP = GTLITE_ITEMS.addItem(3072, "wrap.wafer.chip.nano_power_integrated_circuit")
            WRAP_PPIC_CHIP = GTLITE_ITEMS.addItem(3073, "wrap.wafer.chip.pico_power_integrated_circuit")
            WRAP_FPIC_CHIP = GTLITE_ITEMS.addItem(3074, "wrap.wafer.chip.femto_power_integrated_circuit")
            WRAP_APIC_CHIP = GTLITE_ITEMS.addItem(3075, "wrap.wafer.chip.atto_power_integrated_circuit")
            WRAP_ARAM_CHIP = GTLITE_ITEMS.addItem(3076, "wrap.wafer.chip.advanced_random_access_memory")
            WRAP_UHASOC_CHIP = GTLITE_ITEMS.addItem(3077, "wrap.wafer.chip.ultra_highly_advanced_system_on_chip")
            WRAP_CRYSTAL_INTERFACE_CHIP = GTLITE_ITEMS.addItem(3078, "wrap.wafer.chip.crystal_interface")

            WRAP_ENGRAVED_LAPOTRON_CHIP = GTLITE_ITEMS.addItem(3091, "wrap.circuit.component.engraved_lapotron_chip")
            WRAP_ENGRAVED_DIAMOND_CHIP = GTLITE_ITEMS.addItem(3092, "wrap.circuit.component.engraved_diamond_chip")
            WRAP_ENGRAVED_RUBY_CHIP = GTLITE_ITEMS.addItem(3093, "wrap.circuit.component.engraved_ruby_chip")
            WRAP_ENGRAVED_SAPPHIRE_CHIP = GTLITE_ITEMS.addItem(3094, "wrap.circuit.component.engraved_sapphire_chip")

            WRAP_CRYSTAL_CPU = GTLITE_ITEMS.addItem(3101, "wrap.crystal.central_processing_unit")
            WRAP_CRYSTAL_SOC = GTLITE_ITEMS.addItem(3102, "wrap.crystal.system_on_chip")
            WRAP_NEURO_PROCESSOR = GTLITE_ITEMS.addItem(3103, "wrap.circuit.component.neuro_processor")
            WRAP_NONLINEAR_CHEMICAL_OSCILLATOR = GTLITE_ITEMS.addItem(3104, "wrap.circuit.component.nonlinear_chemical_oscillator")

            WRAP_CIRCUIT_ULV = GTLITE_ITEMS.addItem(3201, "wrap.circuit.generic.ulv")
            WRAP_CIRCUIT_LV = GTLITE_ITEMS.addItem(3202, "wrap.circuit.generic.lv")
            WRAP_CIRCUIT_MV = GTLITE_ITEMS.addItem(3203, "wrap.circuit.generic.mv")
            WRAP_CIRCUIT_HV = GTLITE_ITEMS.addItem(3204, "wrap.circuit.generic.hv")
            WRAP_CIRCUIT_EV = GTLITE_ITEMS.addItem(3205, "wrap.circuit.generic.ev")
            WRAP_CIRCUIT_IV = GTLITE_ITEMS.addItem(3206, "wrap.circuit.generic.iv")
            WRAP_CIRCUIT_LuV = GTLITE_ITEMS.addItem(3207, "wrap.circuit.generic.luv")
            WRAP_CIRCUIT_ZPM = GTLITE_ITEMS.addItem(3208, "wrap.circuit.generic.zpm")
            WRAP_CIRCUIT_UV = GTLITE_ITEMS.addItem(3209, "wrap.circuit.generic.uv")
            WRAP_CIRCUIT_UHV = GTLITE_ITEMS.addItem(3210, "wrap.circuit.generic.uhv")
            WRAP_CIRCUIT_UEV = GTLITE_ITEMS.addItem(3211, "wrap.circuit.generic.uev")
            WRAP_CIRCUIT_UIV = GTLITE_ITEMS.addItem(3212, "wrap.circuit.generic.uiv")
            WRAP_CIRCUIT_UXV = GTLITE_ITEMS.addItem(3213, "wrap.circuit.generic.uxv")
            WRAP_CIRCUIT_OpV = GTLITE_ITEMS.addItem(3214, "wrap.circuit.generic.opv")
            WRAP_CIRCUIT_MAX = GTLITE_ITEMS.addItem(3215, "wrap.circuit.generic.max")

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

            MUD_BALL = GTLITE_ITEMS.addItem(5006, "material.dust.mud_ball")
                .addOreDict("dustMudBall")

            // 9001-10000: Miscellaneous Foods.

            // 9001-9200: Fruits
            BANANA = GTLITE_ITEMS.addItem(9001, "food.fruit.banana")
                .addComponents(FoodBehavior(2, 1f)
                    .setEatingDuration(3 * SECOND))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitBanana")
                .addOreDict("cropBanana")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            ORANGE = GTLITE_ITEMS.addItem(9002, "food.fruit.orange")
                .addComponents(FoodBehavior(2, 1f)
                    .setEatingDuration(2 * SECOND + 10 * TICK))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitOrange")
                .addOreDict("cropOrange")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            MANGO = GTLITE_ITEMS.addItem(9003, "food.fruit.mango")
                .addComponents(FoodBehavior(2, 1f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitMango")
                .addOreDict("cropMango")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            APRICOT = GTLITE_ITEMS.addItem(9004, "food.fruit.apricot")
                .addComponents(FoodBehavior(2, 1f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitApricot")
                .addOreDict("cropApricot")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            LEMON = GTLITE_ITEMS.addItem(9005, "food.fruit.lemon")
                .addComponents(FoodBehavior(1, 0.5f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitLemon")
                .addOreDict("cropLemon")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            LIME = GTLITE_ITEMS.addItem(9006, "food.fruit.lime")
                .addComponents(FoodBehavior(1, 0.5f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitLime")
                .addOreDict("cropLime")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            OLIVE = GTLITE_ITEMS.addItem(9007, "food.fruit.olive")
                .addComponents(FoodBehavior(2, 0.5f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitOlive")
                .addOreDict("cropOlive")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            NUTMEG = GTLITE_ITEMS.addItem(9008, "food.fruit.nutmeg")
                .addOreDict("cropNutmeg")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            COCONUT = GTLITE_ITEMS.addItem(9009, "food.fruit.coconut")
                .addOreDict("cropCoconut")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            // 9201-9300: Seeds and Crops
            COFFEE_SEED = GTLITE_ITEMS.addItem(9201, "crop.seed.coffee")
                .addOreDict("seedCoffee")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            TOMATO_SEED = GTLITE_ITEMS.addItem(9202, "crop.seed.tomato")
                .addOreDict("seedTomato")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            ONION_SEED = GTLITE_ITEMS.addItem(9203, "crop.seed.onion")
                .addOreDict("seedOnion")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            CUCUMBER_SEED = GTLITE_ITEMS.addItem(9204, "crop.seed.cucumber")
                .addOreDict("seedCucumber")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            GRAPE_SEED = GTLITE_ITEMS.addItem(9205, "crop.seed.grape")
                .addOreDict("seedGrape")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            SOY_SEED = GTLITE_ITEMS.addItem(9206, "crop.seed.soy")
                .addOreDict("seedSoy")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            BEAN_SEED = GTLITE_ITEMS.addItem(9207, "crop.seed.bean")
                .addOreDict("seedBean")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            PEA_SEED = GTLITE_ITEMS.addItem(9208, "crop.seed.pea")
                .addOreDict("seedPea")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            OREGANO_SEED = GTLITE_ITEMS.addItem(9209, "crop.seed.oregano")
                .addOreDict("seedOregano")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            HORSERADISH_SEED = GTLITE_ITEMS.addItem(9210, "crop.seed.horseradish")
                .addOreDict("seedHorseradish")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            GARLIC_SEED = GTLITE_ITEMS.addItem(9211, "crop.seed.garlic")
                .addOreDict("seedGarlic")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            BASIL_SEED = GTLITE_ITEMS.addItem(9212, "crop.seed.basil")
                .addOreDict("seedBasil")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            AUBERGINE_SEED = GTLITE_ITEMS.addItem(9213, "crop.seed.aubergine")
                .addOreDict("seedAubergine")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            CORN_SEED = GTLITE_ITEMS.addItem(9214, "crop.seed.corn")
                .addOreDict("seedCorn")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            ARTICHOKE_SEED = GTLITE_ITEMS.addItem(9215, "crop.seed.artichoke")
                .addOreDict("seedArtichoke")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            BLACK_PEPPER_SEED = GTLITE_ITEMS.addItem(9216, "crop.seed.black_pepper")
                .addOreDict("seedBlackPepper")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            RICE_SEED = GTLITE_ITEMS.addItem(9217, "crop.seed.rice")
                .addOreDict("seedRice")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            WHITE_GRAPE_SEED = GTLITE_ITEMS.addItem(9218, "crop.seed.white_grape")
                .addOreDict("seedWhiteGrape")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            COTTON_SEED = GTLITE_ITEMS.addItem(9219, "crop.seed.cotton")
                .addOreDict("seedCotton")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            COFFEE_CHERRY = GTLITE_ITEMS.addItem(9251, "crop.coffee_cherry")
                .addOreDict("cropCoffee")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            TOMATO = GTLITE_ITEMS.addItem(9252, "crop.tomato")
                .addComponents(FoodBehavior(3, 0.5f)
                    .setEatingDuration(3 * SECOND + 12 * TICK))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitTomato")
                .addOreDict("cropTomato")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            ONION = GTLITE_ITEMS.addItem(9253, "crop.onion")
                .addComponents(FoodBehavior(3, 0.33f)
                    .setEatingDuration(6 * SECOND + 8 * TICK))
                .addOreDict("foodAny")
                .addOreDict("vegetableAny")
                .addOreDict("vegetableOnion")
                .addOreDict("cropOnion")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            CUCUMBER = GTLITE_ITEMS.addItem(9254, "crop.cucumber")
                .addComponents(FoodBehavior(2, 0.5f)
                    .setEatingDuration(3 * SECOND + 4 * TICK))
                .addOreDict("foodAny")
                .addOreDict("vegetableAny")
                .addOreDict("vegetableCucumber")
                .addOreDict("cropCucumber")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            GRAPE = GTLITE_ITEMS.addItem(9255, "crop.grape")
                .addComponents(FoodBehavior(1, 1f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitGrape")
                .addOreDict("cropGrape")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            SOYBEAN = GTLITE_ITEMS.addItem(9256, "crop.soybean")
                .addOreDict("cropSoy")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            BEAN = GTLITE_ITEMS.addItem(9257, "crop.bean")
                .addOreDict("cropBean")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            PEA = GTLITE_ITEMS.addItem(9258, "crop.pea")
                .addOreDict("cropPea")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            OREGANO = GTLITE_ITEMS.addItem(9259, "crop.oregano")
                .addOreDict("cropOregano")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            HORSERADISH = GTLITE_ITEMS.addItem(9260, "crop.horseradish")
                .addOreDict("cropHorseradish")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            GARLIC_BULB = GTLITE_ITEMS.addItem(9261, "crop.garlic_bulb")
                .addOreDict("cropGarlic")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            BASIL = GTLITE_ITEMS.addItem(9262, "crop.basil")
                .addOreDict("cropBasil")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            AUBERGINE = GTLITE_ITEMS.addItem(9263, "crop.aubergine")
                .addOreDict("cropAubergine")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            CORN = GTLITE_ITEMS.addItem(9264, "crop.corn")
                .addOreDict("cropCorn")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            ARTICHOKE = GTLITE_ITEMS.addItem(9265, "crop.artichoke")
                .addOreDict("cropArtichoke")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            BLACK_PEPPER = GTLITE_ITEMS.addItem(9266, "crop.black_pepper")
                .addOreDict("cropBlackPepper")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            RICE = GTLITE_ITEMS.addItem(9267, "crop.rice")
                .addOreDict("cropRice")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            WHITE_GRAPE = GTLITE_ITEMS.addItem(9268, "crop.white_grape")
                .addComponents(FoodBehavior(1, 1f))
                .addOreDict("foodAny")
                .addOreDict("fruitAny")
                .addOreDict("fruitGrape")
                .addOreDict("fruitWhiteGrape")
                .addOreDict("cropWhiteGrape")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            COTTON = GTLITE_ITEMS.addItem(9269, "crop.cotton")
                .addOreDict("cropCotton")
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            // ...

            GRAPE_JUICE = GTLITE_ITEMS.addItem(9051, "food.drink.grape_juice")
                .addComponents(FoodBehavior(3, 0.2F, true, true, ItemStack(Items.GLASS_BOTTLE)))
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            RED_WINE = GTLITE_ITEMS.addItem(9052, "food.drink.red_wine")
                .addComponents(FoodBehavior(4, 0.7f, true, true, ItemStack(Items.GLASS_BOTTLE),
                    RandomPotionEffect(MobEffects.NAUSEA, 30 * SECOND, 0, 100 - 60),
                    RandomPotionEffect(MobEffects.RESISTANCE, 20 * SECOND, 0, 100 - 40))
                    .setEatingDuration(8 * SECOND + 16 * TICK))
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            VINEGAR = GTLITE_ITEMS.addItem(9053, "food.drink.vinegar")
                .addComponents(FoodBehavior(2, 0.5f, true, true, ItemStack(Items.GLASS_BOTTLE),
                    RandomPotionEffect(MobEffects.RESISTANCE, 10 * SECOND, 0, 100 - 30)))
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            POTATO_JUICE = GTLITE_ITEMS.addItem(9054, "food.drink.potato_juice")
                .addComponents(FoodBehavior(4, 0.4F, true, true, ItemStack(Items.GLASS_BOTTLE),
                    RandomPotionEffect(MobEffects.NAUSEA, 25 * SECOND, 0, 100 - 80)))
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

            VODKA = GTLITE_ITEMS.addItem(9055, "food.drink.vodka")
                .addComponents(FoodBehavior(4, 0.8F, true, true, ItemStack(Items.GLASS_BOTTLE),
                    RandomPotionEffect(MobEffects.NAUSEA, 20 * SECOND, 0, 100 - 80),
                    RandomPotionEffect(MobEffects.RESISTANCE, 40 * SECOND, 2, 100 - 80))
                    .setEatingDuration(6 * SECOND + 10 * TICK))
                .setCreativeTabs(GTLiteAPI.TAB_GTLITE_FOOD as CreativeTabs)

        }

    }

}