package gregtechlite.gtlitecore.common.item

import gregtech.api.GTValues.M
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VNF
import gregtech.api.items.metaitem.ElectricStats
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.items.metaitem.StandardMetaItem
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.MaterialStack
import gregtech.api.unification.stack.RecyclingData
import gregtech.api.util.RandomPotionEffect
import gregtech.api.util.SmallDigits
import gregtech.client.utils.TooltipHelper
import gregtech.common.creativetab.GTCreativeTabs
import gregtech.common.items.behaviors.TooltipBehavior
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.client.event.TextAnimations
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.item.behavior.CircuitPatternBehavior
import gregtechlite.gtlitecore.common.item.behavior.FoodBehavior
import gregtechlite.gtlitecore.common.item.behavior.HaloRenderItemBehavior
import gregtechlite.gtlitecore.common.item.behavior.StructureWriterBehavior
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.init.MobEffects
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import org.lwjgl.input.Keyboard

object GTLiteMetaItems
{

    // @formatter:off
        
    private lateinit var META_ITEMS: MetaItem<*>

    lateinit var LOGO_CORE: MetaItem<*>.MetaValueItem
    lateinit var LOGO_MACHINE: MetaItem<*>.MetaValueItem
    lateinit var LOGO_DECORATION: MetaItem<*>.MetaValueItem
    lateinit var LOGO_FOOD: MetaItem<*>.MetaValueItem

    lateinit var SHAPE_MOLD_SCREW: MetaItem<*>.MetaValueItem
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

    lateinit var ELECTRIC_MOTOR_MAX: MetaItem<*>.MetaValueItem
    lateinit var ELECTRIC_PUMP_MAX: MetaItem<*>.MetaValueItem
    lateinit var CONVEYOR_MODULE_MAX: MetaItem<*>.MetaValueItem
    lateinit var ELECTRIC_PISTON_MAX: MetaItem<*>.MetaValueItem
    lateinit var ROBOT_ARM_MAX: MetaItem<*>.MetaValueItem
    lateinit var FIELD_GENERATOR_MAX: MetaItem<*>.MetaValueItem
    lateinit var EMITTER_MAX: MetaItem<*>.MetaValueItem
    lateinit var SENSOR_MAX: MetaItem<*>.MetaValueItem
    lateinit var AIR_VENT: MetaItem<*>.MetaValueItem
    lateinit var DRAIN: MetaItem<*>.MetaValueItem
    lateinit var SOLAR_PANEL_UHV: MetaItem<*>.MetaValueItem
    lateinit var SOLAR_PANEL_UEV: MetaItem<*>.MetaValueItem
    lateinit var SOLAR_PANEL_UIV: MetaItem<*>.MetaValueItem
    lateinit var SOLAR_PANEL_UXV: MetaItem<*>.MetaValueItem
    lateinit var SOLAR_PANEL_OpV: MetaItem<*>.MetaValueItem
    lateinit var SOLAR_PANEL_MAX: MetaItem<*>.MetaValueItem

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
    lateinit var COMPONENT_GRINDER_HALKONITE_STEEL: MetaItem<*>.MetaValueItem

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
    lateinit var STREPTOCOCCUS_PYOGENES_PETRI_DISH: MetaItem<*>.MetaValueItem
    lateinit var ESCHERICHIA_COLI_PETRI_DISH: MetaItem<*>.MetaValueItem

    lateinit var MEMORY_CARD_BASE: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_ZOMBIE: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_SKELETON: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_CREEPER: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_SPIDER: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_SLIME: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_WITCH: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_GUARDIAN: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_ENDERMAN: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_WITHER_SKELETON: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_BLAZE: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_GHAST: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_SHULKER: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_WITHER: MetaItem<*>.MetaValueItem
    lateinit var MEMORY_CARD_ENDER_DRAGON: MetaItem<*>.MetaValueItem

    lateinit var CATALYST_BASE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_RUBBER_POLYMER: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ADVANCED_RUBBER_POLYMER: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_PLASTIC_POLYMER: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ADVANCED_PLASTIC_POLYMER: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ULTIMATE_PLASTIC_POLYMER: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_PLATINUM_GROUP: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_RADIOACTIVE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ADVANCED_RADIOACTIVE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ULTIMATE_RADIOACTIVE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_TITANIUM_TUNGSTEN_INDIUM: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ADHESION_PROMOTER: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_RARE_EARTH_GROUP: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_RARE_METAL_GROUP: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_NAQUADAH: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_RAW_INTELLIGENCE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_BIOLOGICAL_INTELLIGENCE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_HIGH_EXPLOSIVE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_TEMPORAL_HARMONY: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_STELLAR_CORE: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ARTIFICIAL_GEM: MetaItem<*>.MetaValueItem
    lateinit var CATALYST_ORGANIC_DYE: MetaItem<*>.MetaValueItem

    lateinit var FUEL_ROD_EMPTY: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_HULL_SMALL_NEUTRONIUM: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_HULL_MEDIUM_NEUTRONIUM: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_HULL_LARGE_NEUTRONIUM: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_HULL_SMALL_INFINITY: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_HULL_MEDIUM_INFINITY: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_HULL_LARGE_INFINITY: MetaItem<*>.MetaValueItem

    lateinit var BATTERY_UHV_NEUTRONIUM: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_UEV_NEUTRONIUM: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_UIV_NEUTRONIUM: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_UXV_INFINITY: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_OpV_INFINITY: MetaItem<*>.MetaValueItem
    lateinit var BATTERY_MAX_INFINITY: MetaItem<*>.MetaValueItem

    lateinit var STRUCTURE_WRITER: MetaItem<*>.MetaValueItem

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
    lateinit var EMPTY_LASER: MetaItem<*>.MetaValueItem
    lateinit var HELIUM_NEON_LASER: MetaItem<*>.MetaValueItem
    lateinit var ND_YAG_LASER: MetaItem<*>.MetaValueItem

    lateinit var OPTICAL_LASER_CONTROL_UNIT: MetaItem<*>.MetaValueItem
    lateinit var OPTICAL_FIBER: MetaItem<*>.MetaValueItem
    lateinit var OPTICAL_IMC_UNIT: MetaItem<*>.MetaValueItem
    lateinit var OPTOELECTRONIC_SYSTEM_ON_CHIP: MetaItem<*>.MetaValueItem
    lateinit var ESR_COMPUTATION_UNIT: MetaItem<*>.MetaValueItem
    lateinit var TOPOLOGICAL_INSULATOR_TUBE: MetaItem<*>.MetaValueItem
    lateinit var CONDENSATE_CONTAINMENT_UNIT: MetaItem<*>.MetaValueItem
    lateinit var BOSE_EINSTEIN_CONDENSATE: MetaItem<*>.MetaValueItem
    lateinit var EXCITATION_MAINTAINER: MetaItem<*>.MetaValueItem
    lateinit var X_RAY_WAVEGUIDE: MetaItem<*>.MetaValueItem
    lateinit var RYDBERG_SPINOR_ARRAY: MetaItem<*>.MetaValueItem
    lateinit var EXOTIC_SYSTEM_ON_CHIP: MetaItem<*>.MetaValueItem
    lateinit var SCINTILLATOR_CRYSTAL: MetaItem<*>.MetaValueItem
    lateinit var SCINTILLATOR: MetaItem<*>.MetaValueItem
    lateinit var NUCLEAR_CLOCK: MetaItem<*>.MetaValueItem
    lateinit var CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT: MetaItem<*>.MetaValueItem
    lateinit var MANIFOLD_OSCILLATORY_POWER_CELL: MetaItem<*>.MetaValueItem
    lateinit var CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT: MetaItem<*>.MetaValueItem
    lateinit var COSMIC_INFORMATION_MODULE: MetaItem<*>.MetaValueItem
    lateinit var HOLOGRAPHIC_INFORMATION_IMC: MetaItem<*>.MetaValueItem
    lateinit var TIME_DILATION_CONTAINMENT_CELL: MetaItem<*>.MetaValueItem
    lateinit var CONTAINED_RN_SINGULARITY: MetaItem<*>.MetaValueItem
    lateinit var CONTAINED_KN_SINGULARITY: MetaItem<*>.MetaValueItem
    lateinit var CONTAINED_KERR_SINGULARITY: MetaItem<*>.MetaValueItem
    lateinit var CONTAINED_HIGH_DENSITY_PROTONIC_MATTER: MetaItem<*>.MetaValueItem
    lateinit var CONTAINED_EXOTIC_MATTER: MetaItem<*>.MetaValueItem
    lateinit var MICROWORMHOLE_GENERATOR: MetaItem<*>.MetaValueItem
    lateinit var MACROWORMHOLE_GENERATOR: MetaItem<*>.MetaValueItem
    lateinit var STABILIZED_WORMHOLE_GENERATOR: MetaItem<*>.MetaValueItem
    lateinit var RECURSIVELY_FOLDED_NEGATIVE_SPACE: MetaItem<*>.MetaValueItem
    lateinit var EIGENFOLDED_SPACETIME_MANIFOLD: MetaItem<*>.MetaValueItem
    lateinit var RELATIVISTIC_HEAT_CAPACITY: MetaItem<*>.MetaValueItem
    lateinit var SUPRACAUSAL_SPACETIME_CONDENSER: MetaItem<*>.MetaValueItem
    lateinit var SPACETIME_LIGHT_CONE_STABILIZATION_MODULE: MetaItem<*>.MetaValueItem
    lateinit var TOPOLOGICAL_MANIPULATOR_UNIT: MetaItem<*>.MetaValueItem
    lateinit var GRAVITON_TRANSDUCER: MetaItem<*>.MetaValueItem
    lateinit var QUANTUM_SPINORIAL_MEMORY_SYSTEM: MetaItem<*>.MetaValueItem

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
    lateinit var PHASE_CHANGE_RAM_CHIP: MetaItem<*>.MetaValueItem
    lateinit var ALL_OPTICAL_CASCADE_NOR_CHIP: MetaItem<*>.MetaValueItem
    lateinit var BOHRIUM_DOPED_CERIUM_CARBONATE_BOULE: MetaItem<*>.MetaValueItem
    lateinit var BOHRIUM_DOPED_CERIUM_CARBONATE_WAFER: MetaItem<*>.MetaValueItem
    lateinit var PERIODICALLY_POLED_OPTICAL_CHIP: MetaItem<*>.MetaValueItem
    lateinit var SPIN_TRANSFER_TORQUE_RAM_CHIP: MetaItem<*>.MetaValueItem
    lateinit var MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP: MetaItem<*>.MetaValueItem
    lateinit var EXOTIC_ATOM_RESTRICT_CPU_WAFER: MetaItem<*>.MetaValueItem
    lateinit var EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER: MetaItem<*>.MetaValueItem
    lateinit var EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP: MetaItem<*>.MetaValueItem
    lateinit var AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP: MetaItem<*>.MetaValueItem
    lateinit var EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP: MetaItem<*>.MetaValueItem
    lateinit var SPIN_FOAM_ANNIHILATED_RAM_CHIP: MetaItem<*>.MetaValueItem
    lateinit var TEMPORAL_SCALE_WORLDSHEET_LOGICAL_GATE_CHIP: MetaItem<*>.MetaValueItem

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
    lateinit var WRAP_PRAM_CHIP: MetaItem<*>.MetaValueItem
    lateinit var WRAP_ACNOR_CHIP: MetaItem<*>.MetaValueItem

    lateinit var WRAP_NONLINEAR_CHEMICAL_OSCILLATOR: MetaItem<*>.MetaValueItem
    lateinit var WRAP_OPTICAL_LASER_CONTROL_UNIT: MetaItem<*>.MetaValueItem
    lateinit var WRAP_ESR_COMPUTATION_UNIT: MetaItem<*>.MetaValueItem

    lateinit var WRAP_ENGRAVED_LAPOTRON_CHIP: MetaItem<*>.MetaValueItem
    lateinit var WRAP_ENGRAVED_DIAMOND_CHIP: MetaItem<*>.MetaValueItem
    lateinit var WRAP_ENGRAVED_RUBY_CHIP: MetaItem<*>.MetaValueItem
    lateinit var WRAP_ENGRAVED_SAPPHIRE_CHIP: MetaItem<*>.MetaValueItem

    lateinit var WRAP_CRYSTAL_CPU: MetaItem<*>.MetaValueItem
    lateinit var WRAP_CRYSTAL_SOC: MetaItem<*>.MetaValueItem
    lateinit var WRAP_NEURO_PROCESSOR: MetaItem<*>.MetaValueItem
    lateinit var WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP: MetaItem<*>.MetaValueItem
    lateinit var WRAP_EXOTIC_SYSTEM_ON_CHIP: MetaItem<*>.MetaValueItem
    lateinit var WRAP_HOLOGRAPHIC_INFORMATION_IMC: MetaItem<*>.MetaValueItem

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
    lateinit var CARBON_ALLOTROPE_MIXTURE: MetaItem<*>.MetaValueItem
    lateinit var GRAPHENE_ALIGNED_CNT: MetaItem<*>.MetaValueItem
    lateinit var QUANTUM_ANOMALY: MetaItem<*>.MetaValueItem
    lateinit var RAW_TESSERACT: MetaItem<*>.MetaValueItem
    lateinit var ENERGISED_TESSERACT: MetaItem<*>.MetaValueItem
    lateinit var STABLE_ADHESIVE: MetaItem<*>.MetaValueItem
    lateinit var SUPERCONDUCTOR_COMPOSITE: MetaItem<*>.MetaValueItem
    lateinit var NAQUADRIA_SUPERSOLID: MetaItem<*>.MetaValueItem
    lateinit var TIMEPIECE: MetaItem<*>.MetaValueItem
    lateinit var ZENITH_STAR: MetaItem<*>.MetaValueItem
    lateinit var PHONONIC_SEED_CRYSTAL: MetaItem<*>.MetaValueItem
    lateinit var NANOSILICON_CATHODE: MetaItem<*>.MetaValueItem
    lateinit var HIGHLY_DENSE_POLYMER_PLATE: MetaItem<*>.MetaValueItem
    lateinit var GRAVITON_SHARD: MetaItem<*>.MetaValueItem
    lateinit var NEUTRONIUM_SPHERE: MetaItem<*>.MetaValueItem
    lateinit var TRIPLET_NEUTRONIUM_SPHERE: MetaItem<*>.MetaValueItem
    lateinit var PEEK_POLYAMIDE_FOIL: MetaItem<*>.MetaValueItem
    lateinit var POLYMER_INSULATOR_FOIL: MetaItem<*>.MetaValueItem

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
    lateinit var BLUEBERRY: MetaItem<*>.MetaValueItem
    lateinit var BLACKBERRY: MetaItem<*>.MetaValueItem
    lateinit var RASPBERRY: MetaItem<*>.MetaValueItem
    lateinit var STRAWBERRY: MetaItem<*>.MetaValueItem
    lateinit var RED_CURRANT: MetaItem<*>.MetaValueItem
    lateinit var BLACK_CURRANT: MetaItem<*>.MetaValueItem
    lateinit var WHITE_CURRANT: MetaItem<*>.MetaValueItem
    lateinit var LINGONBERRY: MetaItem<*>.MetaValueItem
    lateinit var ELDERBERRY: MetaItem<*>.MetaValueItem
    lateinit var CRANBERRY: MetaItem<*>.MetaValueItem

    lateinit var CLAY_BOWL: MetaItem<*>.MetaValueItem
    lateinit var CERAMIC_BOWL: MetaItem<*>.MetaValueItem
    lateinit var DIRTY_CERAMIC_BOWL: MetaItem<*>.MetaValueItem
    lateinit var PAPER_CONE: MetaItem<*>.MetaValueItem
    lateinit var CLAY_CUP: MetaItem<*>.MetaValueItem
    lateinit var CERAMIC_CUP: MetaItem<*>.MetaValueItem
    lateinit var PLASTIC_BOTTLE: MetaItem<*>.MetaValueItem

    lateinit var GRAPE_JUICE: MetaItem<*>.MetaValueItem
    lateinit var RED_WINE: MetaItem<*>.MetaValueItem
    lateinit var VINEGAR: MetaItem<*>.MetaValueItem
    lateinit var POTATO_JUICE: MetaItem<*>.MetaValueItem
    lateinit var VODKA: MetaItem<*>.MetaValueItem
    lateinit var COFFEE_CUP: MetaItem<*>.MetaValueItem
    lateinit var ORANGE_JUICE: MetaItem<*>.MetaValueItem
    lateinit var ETIRPS: MetaItem<*>.MetaValueItem
    lateinit var SPARKLING_WATER: MetaItem<*>.MetaValueItem
    lateinit var CRANBERRY_ETIRPS: MetaItem<*>.MetaValueItem

    lateinit var POLENTA: MetaItem<*>.MetaValueItem

    lateinit var GRAHAM_CRACKER: MetaItem<*>.MetaValueItem

    lateinit var GOLDEN_STRAWBERRY: MetaItem<*>.MetaValueItem
    lateinit var MOON_BERRY: MetaItem<*>.MetaValueItem
    lateinit var HARD_APPLE_CANDY: MetaItem<*>.MetaValueItem

    fun init()
    { 
        META_ITEMS = object : StandardMetaItem()
        {
            override fun createItemModelPath(metaValueItem: MetaItem<*>.MetaValueItem, postfix: String): ResourceLocation
                = GTLiteMod.id(formatModelPath(metaValueItem) + postfix)
        }

        META_ITEMS.setRegistryName("gtlite_meta_item")
        META_ITEMS.setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)
    }
        
    @JvmStatic
    fun register()
    {
        // 0-10 Logos of gtlitecore.
        LOGO_CORE = item(1, "gtlite_logo.core")
            .setInvisible()
        LOGO_MACHINE = item(2, "gtlite_logo.machine")
            .setInvisible()
        LOGO_DECORATION = item(3, "gtlite_logo.decoration")
            .setInvisible()
        LOGO_FOOD = item(4, "gtlite_logo.food")
            .setInvisible()

        // 11-85: Shape Molds & Extruders.

        // 11-40: Common Steel Molds (11-25) & Extruders addition (26-40).

        SHAPE_MOLD_SCREW = item(14, "shape.mold.screw")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.Steel, M * 4)))

        SHAPE_MOLD_TURBINE_BLADE = item(17, "shape.mold.turbine_blade")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.Steel, M * 4)))

        SHAPE_MOLD_DRILL_HEAD = item(18, "shape.mold.drill_head")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.Steel, M * 4)))

        SHAPE_EXTRUDER_ROUND = item(26, "shape.extruder.round")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.Steel, M * 4)))

        SHAPE_EXTRUDER_TURBINE_BLADE = item(27, "shape.extruder.turbine_blade")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.Steel, M * 4)))

        SHAPE_EXTRUDER_DRILL_HEAD = item(28, "shape.extruder.drill_head")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.Steel, M * 4)))

        // 41-50: Slicer Blades (based on Steel Extruders).
        SLICER_BLADE_FLAT = item(41, "shape.slicer_blade.flat")
        SLICER_BLADE_STRIPES = item(42, "shape.slicer_blade.stripes")
        SLICER_BLADE_OCTAGONAL = item(43, "shape.slicer_blade.octagonal")

        // 51-70: Vanadium Steel Molds & Extruders.
        CASTING_MOLD_EMPTY = item(51, "shape.mold.vanadium_steel.empty")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_SAW = item(52, "shape.mold.vanadium_steel.saw")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_HARD_HAMMER = item(53, "shape.mold.vanadium_steel.hard_hammer")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_SOFT_MALLET = item(54, "shape.mold.vanadium_steel.soft_mallet")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_WRENCH = item(55, "shape.mold.vanadium_steel.wrench")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_FILE = item(56, "shape.mold.vanadium_steel.file")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_CROWBAR = item(57, "shape.mold.vanadium_steel.crowbar")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_SCREWDRIVER = item(58, "shape.mold.vanadium_steel.screwdriver")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_MORTAR = item(59, "shape.mold.vanadium_steel.mortar")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_WIRE_CUTTER = item(60, "shape.mold.vanadium_steel.wire_cutter")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_KNIFE = item(61, "shape.mold.vanadium_steel.knife")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_BUTCHERY_KNIFE = item(62, "shape.mold.vanadium_steel.butchery_knife")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        CASTING_MOLD_ROLLING_PIN = item(63, "shape.mold.vanadium_steel.rolling_pin")
            .setRecyclingData(RecyclingData(MaterialStack(Materials.VanadiumSteel, M * 4)))

        // 86-100: Credits
        CREDIT_ADAMANTIUM = item(86, "credit.adamantium")
            .setRarity(EnumRarity.EPIC)
        CREDIT_VIBRANIUM = item(87, "credit.vibranium")
            .setRarity(EnumRarity.EPIC)
        CREDIT_COSMIC_NEUTRONIUM = item(88, "credit.cosmic_neutronium")
            .addComponents(HaloRenderItemBehavior(10, 0x33FFFFFF, {{ GTLiteTextures.HALO_NOISE }}, true))
            .setRarity(EnumRarity.EPIC)
        CREDIT_INFINITY = item(89, "credit.infinity")
            .addComponents(HaloRenderItemBehavior(10, 0xFF000000.toInt(), {{ GTLiteTextures.HALO }}, true))
            .setRarity(EnumRarity.EPIC)

        // 101-106: Voltage Coils.
        VOLTAGE_COIL_UHV = item(101, "voltage_coil.uhv")
        VOLTAGE_COIL_UEV = item(102, "voltage_coil.uev")
        VOLTAGE_COIL_UIV = item(103, "voltage_coil.uiv")
        VOLTAGE_COIL_UXV = item(104, "voltage_coil.uxv")
        VOLTAGE_COIL_OpV = item(105, "voltage_coil.opv")
        VOLTAGE_COIL_MAX = item(106, "voltage_coil.max")

        // 107-200: Covers.
        ELECTRIC_MOTOR_MAX = item(107, "electric.motor.max")

        ELECTRIC_PUMP_MAX = item(108, "electric.pump.max")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.electric.pump.tooltip"))
                lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", 1280 * 64 * 64 * 4 / 20))
            })

        CONVEYOR_MODULE_MAX = item(109, "conveyor.module.max")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.conveyor.module.tooltip"))
                lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate_stacks", 16))
            })

        ELECTRIC_PISTON_MAX = item(110, "electric.piston.max")

        ROBOT_ARM_MAX = item(111, "robot.arm.max")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.robot.arm.tooltip"))
                lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate_stacks", 16))
            })

        FIELD_GENERATOR_MAX = item(112, "field.generator.max")
        EMITTER_MAX = item(113, "emitter.max")
        SENSOR_MAX = item(114, "sensor.max")

        AIR_VENT = item(115, "cover.air_vent")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.air_vent.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.air_vent.tooltip.2", 100))
            })

        DRAIN = item(116, "cover.drain")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.drain.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.drain.tooltip.2", 500))
            })

        SOLAR_PANEL_UHV = item(117, "cover.solar_panel.uhv")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                lines.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UHV], VNF[UHV]))
            })

        SOLAR_PANEL_UEV = item(118, "cover.solar_panel.uev")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                lines.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UEV], VNF[UEV]))
            })

        SOLAR_PANEL_UIV = item(119, "cover.solar_panel.uiv")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                lines.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UIV], VNF[UIV]))
            })

        SOLAR_PANEL_UXV = item(120, "cover.solar_panel.uxv")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                lines.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UXV], VNF[UXV]))
            })

        SOLAR_PANEL_OpV = item(121, "cover.solar_panel.opv")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                lines.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[OpV], VNF[OpV]))
            })

        SOLAR_PANEL_MAX = item(122, "cover.solar_panel.max")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                lines.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                lines.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[MAX], VNF[MAX]))
            })

        // 201-210: Boards and Circuit Boards.
        GOOWARE_BOARD = item(201, "board.gooware")
        OPTICAL_BOARD = item(202, "board.optical")
        SPINTRONIC_BOARD = item(203, "board.spintronic")

        ULTIMATE_CIRCUIT_BOARD = item(206, "circuit_board.ultimate")
        PERFECT_CIRCUIT_BOARD = item(207, "circuit_board.perfect")
        INFINITE_CIRCUIT_BOARD = item(208, "circuit_board.infinite")

        // 211-250: SMDs.
        GOOWARE_SMD_TRANSISTOR = item(211, "component.gooware_smd.transistor")
        GOOWARE_SMD_RESISTOR = item(212, "component.gooware_smd.resistor")
        GOOWARE_SMD_CAPACITOR = item(213, "component.gooware_smd.capacitor")
        GOOWARE_SMD_DIODE = item(214, "component.gooware_smd.diode")
        GOOWARE_SMD_INDUCTOR = item(215, "component.gooware_smd.inductor")
        OPTICAL_SMD_TRANSISTOR = item(216, "component.optical_smd.transistor")
        OPTICAL_SMD_RESISTOR = item(217, "component.optical_smd.resistor")
        OPTICAL_SMD_CAPACITOR = item(218, "component.optical_smd.capacitor")
        OPTICAL_SMD_DIODE = item(219, "component.optical_smd.diode")
        OPTICAL_SMD_INDUCTOR = item(220, "component.optical_smd.inductor")
        SPINTRONIC_SMD_TRANSISTOR = item(221, "component.spintronic_smd.transistor")
        SPINTRONIC_SMD_RESISTOR = item(222, "component.spintronic_smd.resistor")
        SPINTRONIC_SMD_CAPACITOR = item(223, "component.spintronic_smd.capacitor")
        SPINTRONIC_SMD_DIODE = item(224, "component.spintronic_smd.diode")
        SPINTRONIC_SMD_INDUCTOR = item(225, "component.spintronic_smd.inductor")
        COSMIC_SMD_TRANSISTOR = item(226, "component.cosmic_smd.transistor")
        COSMIC_SMD_RESISTOR = item(227, "component.cosmic_smd.resistor")
        COSMIC_SMD_CAPACITOR = item(228, "component.cosmic_smd.capacitor")
        COSMIC_SMD_DIODE = item(229, "component.cosmic_smd.diode")
        COSMIC_SMD_INDUCTOR = item(230, "component.cosmic_smd.inductor")
        SUPRACAUSAL_SMD_TRANSISTOR = item(231, "component.supracausal_smd.transistor")
        SUPRACAUSAL_SMD_RESISTOR = item(232, "component.supracausal_smd.resistor")
        SUPRACAUSAL_SMD_CAPACITOR = item(233, "component.supracausal_smd.capacitor")
        SUPRACAUSAL_SMD_DIODE = item(234, "component.supracausal_smd.diode")
        SUPRACAUSAL_SMD_INDUCTOR = item(235, "component.supracausal_smd.inductor")

        // 251-300: Circuits.
        GOOWARE_PROCESSOR_ZPM = item(251, "circuit.gooware_processor")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ZPM)
        GOOWARE_ASSEMBLY_UV = item(252, "circuit.gooware_assembly")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV)
        GOOWARE_COMPUTER_UHV = item(253, "circuit.gooware_computer")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV)
        GOOWARE_MAINFRAME_UEV = item(254, "circuit.gooware_mainframe")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)

        OPTICAL_PROCESSOR_UV = item(255, "circuit.optical_processor")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV)
        OPTICAL_ASSEMBLY_UHV = item(256, "circuit.optical_assembly")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV)
        OPTICAL_COMPUTER_UEV = item(257, "circuit.optical_computer")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)
        OPTICAL_MAINFRAME_UIV = item(258, "circuit.optical_mainframe")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)

        SPINTRONIC_PROCESSOR_UHV = item(259, "circuit.spintronic_processor")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV)
        SPINTRONIC_ASSEMBLY_UEV = item(260, "circuit.spintronic_assembly")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)
        SPINTRONIC_COMPUTER_UIV = item(261, "circuit.spintronic_computer")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
        SPINTRONIC_MAINFRAME_UXV = item(262, "circuit.spintronic_mainframe")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV)

        COSMIC_PROCESSOR_UEV = item(263, "circuit.cosmic_processor")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.cosmic_processor.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_processor.tooltip.2"))
            })

        COSMIC_ASSEMBLY_UIV = item(264, "circuit.cosmic_assembly")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.cosmic_assembly.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_assembly.tooltip.2"))
            })

        COSMIC_COMPUTER_UXV = item(265, "circuit.cosmic_computer")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.cosmic_computer.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_computer.tooltip.2"))
            })

        COSMIC_MAINFRAME_OpV = item(266, "circuit.cosmic_mainframe")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.cosmic_mainframe.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_mainframe.tooltip.2"))
            })

        SUPRACAUSAL_PROCESSOR_UIV = item(267, "circuit.supracausal_processor")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.supracausal_processor.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_RAINBOW("metaitem.circuit.supracausal_processor.tooltip.2"))
            })

        SUPRACAUSAL_ASSEMBLY_UXV = item(268, "circuit.supracausal_assembly")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.supracausal_assembly.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_RAINBOW("metaitem.circuit.supracausal_assembly.tooltip.2"))
            })

        SUPRACAUSAL_COMPUTER_OpV = item(269, "circuit.supracausal_computer")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.supracausal_computer.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_RAINBOW("metaitem.circuit.supracausal_computer.tooltip.2"))
            })

        SUPRACAUSAL_MAINFRAME_MAX = item(270, "circuit.supracausal_mainframe")
            .setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MAX)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.supracausal_mainframe.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_RAINBOW_BOLD("metaitem.circuit.supracausal_mainframe.tooltip.2"))
            })

        // 301-500: Covers and Cover Components.

        MINING_DRONE_LV = item(321, "mining_drone.lv")
            .setMaxStackSize(16)

        MINING_DRONE_MV = item(322, "mining_drone.mv")
            .setMaxStackSize(16)

        MINING_DRONE_HV = item(323, "mining_drone.hv")
            .setMaxStackSize(16)

        MINING_DRONE_EV = item(324, "mining_drone.ev")
            .setMaxStackSize(16)

        MINING_DRONE_IV = item(325, "mining_drone.iv")
            .setMaxStackSize(16)

        MINING_DRONE_LuV = item(326, "mining_drone.luv")
            .setMaxStackSize(16)

        MINING_DRONE_ZPM = item(327, "mining_drone.zpm")
            .setMaxStackSize(16)

        MINING_DRONE_UV = item(328, "mining_drone.uv")
            .setMaxStackSize(16)

        MINING_DRONE_UHV = item(329, "mining_drone.uhv")
            .setMaxStackSize(16)

        MINING_DRONE_UEV = item(330, "mining_drone.uev")
            .setMaxStackSize(16)

        MINING_DRONE_UIV = item(331, "mining_drone.uiv")
            .setMaxStackSize(16)

        MINING_DRONE_UXV = item(332, "mining_drone.uxv")
            .setMaxStackSize(16)

        MINING_DRONE_OpV = item(333, "mining_drone.opv")
            .setMaxStackSize(16)

        MINING_DRONE_MAX = item(334, "mining_drone.max")
            .setMaxStackSize(16)

        // 501-600: Tool Components.
        COMPONENT_GRINDER_BORON_NITRIDE = item(501, "tool.component.grinder.boron_nitride")
        COMPONENT_GRINDER_HALKONITE_STEEL = item(502, "tool.component.grinder.halkonite_steel")

        // 601-700: Tools.
        DISPOSABLE_SAW = item(601, "tool.disposable.saw")
            .addOreDict("toolSaw")
            .addOreDict("craftingToolSaw")

        DISPOSABLE_HARD_HAMMER = item(602, "tool.disposable.hard_hammer")
            .addOreDict("toolHammer")
            .addOreDict("craftingToolHardHammer")

        DISPOSABLE_SOFT_MALLET = item(603, "tool.disposable.soft_mallet")
            .addOreDict("toolMallet")
            .addOreDict("craftingToolSoftHammer")

        DISPOSABLE_WRENCH = item(604, "tool.disposable.wrench")
            .addOreDict("toolWrench")
            .addOreDict("craftingToolWrench")

        DISPOSABLE_FILE = item(605, "tool.disposable.file")
            .addOreDict("toolFile")
            .addOreDict("craftingToolFile")

        DISPOSABLE_CROWBAR = item(606, "tool.disposable.crowbar")
            .addOreDict("toolCrowbar")
            .addOreDict("craftingToolCrowbar")

        DISPOSABLE_SCREWDRIVER = item(607, "tool.disposable.screwdriver")
            .addOreDict("toolScrewdriver")
            .addOreDict("craftingToolScrewdriver")

        DISPOSABLE_MORTAR = item(608, "tool.disposable.mortar")
            .addOreDict("toolMortar")
            .addOreDict("craftingToolMortar")

        DISPOSABLE_WIRE_CUTTER = item(609, "tool.disposable.wire_cutter")
            .addOreDict("toolWireCutter")
            .addOreDict("craftingToolWireCutter")

        DISPOSABLE_KNIFE = item(610, "tool.disposable.knife")
            .addOreDict("toolKnife")
            .addOreDict("craftingToolKnife")

        DISPOSABLE_BUTCHERY_KNIFE = item(611, "tool.disposable.butchery_knife")
            .addOreDict("toolButcheryKnife")
            .addOreDict("craftingToolButcheryKnife")

        DISPOSABLE_ROLLING_PIN = item(612, "tool.disposable.rolling_pin")
            .addOreDict("toolRollingPin")
            .addOreDict("craftingToolRollingPin")

        // ... (613-620)

        CIRCUIT_PATTERN = item(621, "tool.circuit_pattern")
            .addComponents(CircuitPatternBehavior())

        MAGNETRON = item(622, "tool.magnetron")

        // ...

        DIRTY_PETRI_DISH = item(631, "tool.petri_dish.dirty")
        BREVIBACTERIUM_FLAVUM_PETRI_DISH = item(632, "tool.petri_dish.brevibacterium_flavum")
        CUPRIAVIDUS_NECATOR_PETRI_DISH = item(633, "tool.petri_dish.cupriavidus_necator")
        ELECTRIC_SIGNAL_PETRI_DISH = item(634, "tool.petri_dish.electric_signal")
        STREPTOCOCCUS_PYOGENES_PETRI_DISH = item(635, "tool.petri_dish.streptococcus_pyogenes")
        ESCHERICHIA_COLI_PETRI_DISH = item(636, "tool.petri_dish.escherichia_coli")

        // ...

        MEMORY_CARD_BASE = item(641, "tool.memory_card.base")
        MEMORY_CARD_ZOMBIE = item(642, "tool.memory_card.zombie")
        MEMORY_CARD_SKELETON = item(643, "tool.memory_card.skeleton")
        MEMORY_CARD_CREEPER = item(644, "tool.memory_card.creeper")
        MEMORY_CARD_SPIDER = item(645, "tool.memory_card.spider")
        MEMORY_CARD_SLIME = item(646, "tool.memory_card.slime")
        MEMORY_CARD_WITCH = item(647, "tool.memory_card.witch")
        MEMORY_CARD_GUARDIAN = item(648, "tool.memory_card.guardian")
        MEMORY_CARD_ENDERMAN = item(649, "tool.memory_card.enderman")
        MEMORY_CARD_WITHER_SKELETON = item(650, "tool.memory_card.wither_skeleton")
        MEMORY_CARD_BLAZE = item(651, "tool.memory_card.blaze")
        MEMORY_CARD_GHAST = item(652, "tool.memory_card.ghast")
        MEMORY_CARD_SHULKER = item(653, "tool.memory_card.shulker")
        MEMORY_CARD_WITHER = item(654, "tool.memory_card.wither")
        MEMORY_CARD_ENDER_DRAGON = item(655, "tool.memory_card.ender_dragon")

        // ...
        CATALYST_BASE = item(671, "tool.catalyst.base")
            .addOreDict("catalystBase")

        CATALYST_RUBBER_POLYMER = item(672, "tool.catalyst.rubber_polymer")
            .addOreDict("catalystRubberPolymer")

        CATALYST_ADVANCED_RUBBER_POLYMER = item(673, "tool.catalyst.advanced_rubber_polymer")
            .addOreDict("catalystAdvancedRubberPolymer")

        CATALYST_PLASTIC_POLYMER = item(674, "tool.catalyst.plastic_polymer")
            .addOreDict("catalystPlasticPolymer")

        CATALYST_ADVANCED_PLASTIC_POLYMER = item(675, "tool.catalyst.advanced_plastic_polymer")
            .addOreDict("catalystAdvancedPlasticPolymer")

        CATALYST_ULTIMATE_PLASTIC_POLYMER = item(676, "tool.catalyst.ultimate_plastic_polymer")
            .addOreDict("catalystUltimatePlasticPolymer")

        CATALYST_PLATINUM_GROUP = item(677, "tool.catalyst.platinum_group")
            .addOreDict("catalystPlatinumGroup")

        CATALYST_RADIOACTIVE = item(678, "tool.catalyst.radioactive")
            .addOreDict("catalystRadioactive")

        CATALYST_ADVANCED_RADIOACTIVE = item(679, "tool.catalyst.advanced_radioactive")
            .addOreDict("catalystAdvancedRadioactive")

        CATALYST_ULTIMATE_RADIOACTIVE = item(680, "tool.catalyst.ultimate_radioactive")
            .addOreDict("catalystUltimateRadioactive")

        CATALYST_TITANIUM_TUNGSTEN_INDIUM = item(681, "tool.catalyst.titanium_tungsten_indium")
            .addOreDict("catalystTitaniumTungstenIndium")

        CATALYST_ADHESION_PROMOTER = item(682, "tool.catalyst.adhesion_promoter")
            .addOreDict("catalystAdhesionPromoter")

        CATALYST_RARE_EARTH_GROUP = item(683, "tool.catalyst.rare_earth_group")
            .addOreDict("catalystRareEarthGroup")

        CATALYST_RARE_METAL_GROUP = item(684, "tool.catalyst.rare_metal_group")
            .addOreDict("catalystRareMetalGroup")

        CATALYST_NAQUADAH = item(685, "tool.catalyst.naquadah")
            .addOreDict("catalystNaquadah")

        CATALYST_RAW_INTELLIGENCE = item(686, "tool.catalyst.raw_intelligence")
            .addOreDict("catalystRawIntelligence")

        CATALYST_BIOLOGICAL_INTELLIGENCE = item(687, "tool.catalyst.biological_intelligence")
            .addOreDict("catalystBiologicalIntelligence")

        CATALYST_HIGH_EXPLOSIVE = item(688, "tool.catalyst.high_explosive")
            .addOreDict("catalystHighExplosive")

        CATALYST_TEMPORAL_HARMONY = item(689, "tool.catalyst.temporal_harmony")
            .addOreDict("catalystTemporalHarmony")

        CATALYST_STELLAR_CORE = item(690, "tool.catalyst.stellar_core")
            .addOreDict("catalystStellarCore")

        CATALYST_ARTIFICIAL_GEM = item(691, "tool.catalyst.artificial_gem")
            .addOreDict("catalystArtificialGem")

        CATALYST_ORGANIC_DYE = item(692, "tool.catalyst.organic_dye")
            .addOreDict("catalystOrganicDye")

        // 701-800: Batteries.
        FUEL_ROD_EMPTY = item(701, "fuel_rod.empty")
        BATTERY_HULL_SMALL_NEUTRONIUM = item(702, "battery.hull.uhv")
        BATTERY_HULL_MEDIUM_NEUTRONIUM = item(703, "battery.hull.uev")
        BATTERY_HULL_LARGE_NEUTRONIUM = item(704, "battery.hull.uiv")
        BATTERY_HULL_SMALL_INFINITY = item(705, "battery.hull.uxv")
        BATTERY_HULL_MEDIUM_INFINITY = item(706, "battery.hull.opv")
        BATTERY_HULL_LARGE_INFINITY = item(707, "battery.hull.max")

        // ...

        BATTERY_UHV_NEUTRONIUM = item(711, "battery.uhv.neutronium")
            .addComponents(ElectricStats.createRechargeableBattery(10_485_760_000L, UHV))
            .setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.UHV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS as CreativeTabs)

        BATTERY_UEV_NEUTRONIUM = item(712, "battery.uev.neutronium")
            .addComponents(ElectricStats.createRechargeableBattery(41_943_040_000L, UEV))
            .setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.UEV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_UIV_NEUTRONIUM = item(713, "battery.uiv.neutronium")
            .addComponents(ElectricStats.createRechargeableBattery(167_772_160_000L, UIV))
            .setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.UIV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_UXV_INFINITY = item(714, "battery.uxv.infinity")
            .addComponents(ElectricStats.createRechargeableBattery(671_088_640_000L, UXV))
            .setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.UXV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_OpV_INFINITY = item(715, "battery.opv.infinity")
            .addComponents(ElectricStats.createRechargeableBattery(2_684_354_560_000L, OpV))
            .setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.OpV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_MAX_INFINITY = item(716, "battery.max.infinity")
            .addComponents(ElectricStats.createRechargeableBattery(10_737_418_240_000L, MAX))
            .setUnificationData(OrePrefix.battery, MarkerMaterials.Tier.MAX)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        // 1001-1100: Debug Tools
        STRUCTURE_WRITER = item(1001, "debug.structure_writer")
            .addComponents(StructureWriterBehavior)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.1"))
                lines.add(TooltipHelper.BLINKING_CYAN.toString() + I18n.format("metaitem.debug.structure_writer.tooltip.2"))

                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
                {
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.3"))
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.4"))
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.5"))
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.6"))
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.7"))
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.8"))
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.9"))
                    lines.add(I18n.format("metaitem.debug.structure_writer.tooltip.10"))
                }
                else
                {
                    lines.add(I18n.format("gregtech.tooltip.hold_shift"))
                }
            })
        .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        // 1101-2000: Circuit Components.
        VACUUM_TUBE_COMPONENT = item(1101, "circuit.component.vacuum_tube_component")
        DIELECTRIC_MIRROR = item(1102, "circuit.component.dielectric_mirror")
        ENGRAVED_DIAMOND_CHIP = item(1103, "circuit.component.engraved_diamond_chip")
        ENGRAVED_RUBY_CHIP = item(1104, "circuit.component.engraved_ruby_chip")
        ENGRAVED_SAPPHIRE_CHIP = item(1105, "circuit.component.engraved_sapphire_chip")

        DIAMOND_MODULATOR = item(1111, "circuit.component.diamond_modulator")
        RUBY_MODULATOR = item(1112, "circuit.component.ruby_modulator")
        SAPPHIRE_MODULATOR = item(1113, "circuit.component.sapphire_modulator")
        CRYSTAL_SOC_SOCKET = item(1114, "circuit.component.crystal_system_on_chip_socket")
        BZ_REACTION_CHAMBER = item(1115, "circuit.component.bz_reaction_chamber")
        NONLINEAR_CHEMICAL_OSCILLATOR = item(1116, "circuit.component.nonlinear_chemical_oscillator")
        EMPTY_LASER = item(1117, "circuit.component.laser.empty")
        HELIUM_NEON_LASER = item(1118, "circuit.component.laser.helium_neon")
        ND_YAG_LASER = item(1119, "circuit.component.laser.nd_yag")

        // 1120-1125 for other lasers if it is existed in the future.

        OPTICAL_LASER_CONTROL_UNIT = item(1126, "circuit.component.optical_laser_control_unit")
        OPTICAL_FIBER = item(1127, "circuit.component.optical_fiber")
        OPTICAL_IMC_UNIT = item(1128, "circuit.component.optical_imc_chip")
        OPTOELECTRONIC_SYSTEM_ON_CHIP = item(1129, "circuit.component.optoelectronic_system_on_chip")
        ESR_COMPUTATION_UNIT = item(1130, "circuit.component.esr_computation_unit")
        TOPOLOGICAL_INSULATOR_TUBE = item(1131, "circuit.component.topological_insulator_tube")
        CONDENSATE_CONTAINMENT_UNIT = item(1132, "circuit.component.condensate_containment_unit")
        BOSE_EINSTEIN_CONDENSATE = item(1133, "circuit.component.bose_einstein_condensate")
        EXCITATION_MAINTAINER = item(1134, "circuit.component.excitation_maintainer")
        X_RAY_WAVEGUIDE = item(1135, "circuit.component.x_ray_waveguide")
        RYDBERG_SPINOR_ARRAY = item(1136, "circuit.component.rydberg_spinor_array")
        EXOTIC_SYSTEM_ON_CHIP = item(1137, "circuit.component.exotic_system_on_chip")
        SCINTILLATOR_CRYSTAL = item(1138, "circuit.component.scintillator_crystal")
        SCINTILLATOR = item(1139, "circuit.component.scintillator")
        NUCLEAR_CLOCK = item(1140, "circuit.component.nuclear_clock")
        CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT = item(1141, "circuit.component.closed_timelike_curve_guidance_unit")
        MANIFOLD_OSCILLATORY_POWER_CELL = item(1142, "circuit.component.manifold_oscillatory_power_cell")
        CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT = item(1143, "circuit.component.closed_timelike_curve_computational_unit")
        COSMIC_INFORMATION_MODULE = item(1144, "circuit.component.cosmic_information_module")
        HOLOGRAPHIC_INFORMATION_IMC = item(1145, "circuit.component.holographic_information_imc")
        TIME_DILATION_CONTAINMENT_CELL = item(1146, "circuit.component.time_dilation_containment_cell")
        CONTAINED_RN_SINGULARITY = item(1147, "circuit.component.contained_reissner_nordstrom_singularity")
        CONTAINED_KN_SINGULARITY = item(1148, "circuit.component.contained_kerr_newmann_singularity")
        CONTAINED_KERR_SINGULARITY = item(1149, "circuit.component.contained_kerr_singularity")
        CONTAINED_HIGH_DENSITY_PROTONIC_MATTER = item(1150, "circuit.component.contained_high_density_protonic_matter")
        CONTAINED_EXOTIC_MATTER = item(1151, "circuit.component.contained_exotic_matter")
        MICROWORMHOLE_GENERATOR = item(1152, "circuit.component.microwormhole_generator")
        MACROWORMHOLE_GENERATOR = item(1153, "circuit.component.macrowormhole_generator")
        STABILIZED_WORMHOLE_GENERATOR = item(1154, "circuit.component.stabilized_wormhole_generator")
        RECURSIVELY_FOLDED_NEGATIVE_SPACE = item(1155, "circuit.component.recursively_folded_negative_space")
        EIGENFOLDED_SPACETIME_MANIFOLD = item(1156, "circuit.component.eigenfolded_spacetime_manifold")
        RELATIVISTIC_HEAT_CAPACITY = item(1157, "circuit.component.relativistic_heat_capacity")
        SUPRACAUSAL_SPACETIME_CONDENSER = item(1158, "circuit.component.supracausal_spacetime_condenser")
        SPACETIME_LIGHT_CONE_STABILIZATION_MODULE = item(1159, "circuit.component.spacetime_light_cone_stabilization_module")
        TOPOLOGICAL_MANIPULATOR_UNIT = item(1160, "circuit.component.topological_manipulator_unit")
        GRAVITON_TRANSDUCER = item(1161, "circuit.component.graviton_transducer")
        QUANTUM_SPINORIAL_MEMORY_SYSTEM = item(1162, "circuit.component.quantum_spinorial_memory_system")

        // 2001-2500: Boules and Wafers.
        HASSIUM_BOULE = item(2001, "boule.hassium")

        HASSIUM_WAFER = item(2006, "wafer.hassium")

        NANO_PIC_WAFER = item(2011, "wafer.nano_power_integrated_circuit")
        NANO_PIC_CHIP = item(2012, "wafer.chip.nano_power_integrated_circuit")
        PICO_PIC_WAFER = item(2013, "wafer.pico_power_integrated_circuit")
        PICO_PIC_CHIP = item(2014, "wafer.chip.pico_power_integrated_circuit")
        FEMTO_PIC_WAFER = item(2015, "wafer.femto_power_integrated_circuit")
        FEMTO_PIC_CHIP = item(2016, "wafer.chip.femto_power_integrated_circuit")
        ATTO_PIC_WAFER = item(2017, "wafer.atto_power_integrated_circuit")
        ATTO_PIC_CHIP = item(2018, "wafer.chip.atto_power_integrated_circuit")
        ADVANCED_RAM_WAFER = item(2019, "wafer.advanced_random_access_memory")
        ADVANCED_RAM_CHIP = item(2020, "wafer.chip.advanced_random_access_memory")
        ULTRA_HIGHLY_ADVANCED_SOC_WAFER = item(2021, "wafer.ultra_highly_advanced_system_on_chip")
        ULTRA_HIGHLY_ADVANCED_SOC_CHIP = item(2022, "wafer.chip.ultra_highly_advanced_system_on_chip")

        EUROPIUM_DOPED_CUBIC_ZIRCONIA_BOULE = item(2401, "boule.cubic_zirconia.europium")
        EUROPIUM_DOPED_CUBIC_ZIRCONIA_WAFER = item(2402, "wafer.cubic_zirconia.europium")
        CRYSTAL_INTERFACE_WAFER = item(2403, "wafer.crystal_interface")
        CRYSTAL_INTERFACE_CHIP = item(2404, "wafer.chip.crystal_interface")
        PHASE_CHANGE_RAM_CHIP = item(2405, "wafer.chip.phase_change_ram")
        ALL_OPTICAL_CASCADE_NOR_CHIP = item(2406, "wafer.chip.all_optical_cascade_nor")
        BOHRIUM_DOPED_CERIUM_CARBONATE_BOULE = item(2407, "boule.cerium_carbonate.bohrium")
        BOHRIUM_DOPED_CERIUM_CARBONATE_WAFER = item(2408, "wafer.cerium_carbonate.bohrium")
        PERIODICALLY_POLED_OPTICAL_CHIP = item(2409, "wafer.chip.periodically_poled_optical_chip")
        SPIN_TRANSFER_TORQUE_RAM_CHIP = item(2410, "wafer.chip.spin_transfer_torque_ram")
        MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP = item(2411, "wafer.chip.magnetic_domain_wall_inversion_nand")
        EXOTIC_ATOM_RESTRICT_CPU_WAFER = item(2412, "wafer.exotic_atom_restrict_central_processing_unit")
        EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER = item(2413, "wafer.excited_exotic_atom_restrict_central_processing_unit")
        EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP = item(2414, "wafer.chip.excited_exotic_atom_restrict_central_processing_unit")
        AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP = item(2415, "wafer.chip.amplitude_duality_disturbance_ram")
        EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP = item(2416, "wafer.chip.excitation_spectrum_composite_logical_gate")
        SPIN_FOAM_ANNIHILATED_RAM_CHIP = item(2417, "wafer.chip.spin_foam_annihilated_ram")
        TEMPORAL_SCALE_WORLDSHEET_LOGICAL_GATE_CHIP = item(2418, "wafer.chip.temporal_scale_worldsheet_logical_gate")

        // 2500-3000: ...

        // 3001-5000: Wrap Components
        WRAP_COATED_BOARD = item(3001, "wrap.board.coated")
        WRAP_PHENOLIC_BOARD = item(3002, "wrap.board.phenolic")
        WRAP_PLASTIC_BOARD = item(3003, "wrap.board.plastic")
        WRAP_EPOXY_BOARD = item(3004, "wrap.board.epoxy")
        WRAP_FIBER_BOARD = item(3005, "wrap.board.fiber_reinforced")
        WRAP_MULTILAYER_FIBER_BOARD = item(3006, "wrap.board.multilayer_fiber_reinforced")
        WRAP_WETWARE_BOARD = item(3007, "wrap.board.wetware")
        WRAP_GOOWARE_BOARD = item(3008, "wrap.board.gooware")
        WRAP_OPTICAL_BOARD = item(3009, "wrap.board.optical")
        WRAP_SPINTRONIC_BOARD = item(3010, "wrap.board.spintronic")
        WRAP_BASIC_CIRCUIT_BOARD = item(3011, "wrap.circuit_board.basic")
        WRAP_GOOD_CIRCUIT_BOARD = item(3012, "wrap.circuit_board.good")
        WRAP_PLASTIC_CIRCUIT_BOARD = item(3013, "wrap.circuit_board.plastic")
        WRAP_ADVANCED_CIRCUIT_BOARD = item(3014, "wrap.circuit_board.advanced")
        WRAP_EXTREME_CIRCUIT_BOARD = item(3015, "wrap.circuit_board.extreme")
        WRAP_ELITE_CIRCUIT_BOARD = item(3016, "wrap.circuit_board.elite")
        WRAP_WETWARE_CIRCUIT_BOARD = item(3017, "wrap.circuit_board.wetware")
        WRAP_ULTIMATE_CIRCUIT_BOARD = item(3018, "wrap.circuit_board.ultimate")
        WRAP_PERFECT_CIRCUIT_BOARD = item(3019, "wrap.circuit_board.perfect")
        WRAP_INFINITE_CIRCUIT_BOARD = item(3020, "wrap.circuit_board.infinite")
        WRAP_SMD_TRANSISTOR = item(3021, "wrap.component.smd.transistor")
        WRAP_SMD_RESISTOR = item(3022, "wrap.component.smd.resistor")
        WRAP_SMD_CAPACITOR = item(3023, "wrap.component.smd.capacitor")
        WRAP_SMD_DIODE = item(3024, "wrap.component.smd.diode")
        WRAP_SMD_INDUCTOR = item(3025, "wrap.component.smd.inductor")
        WRAP_ADVANCED_SMD_TRANSISTOR = item(3026, "wrap.component.advanced_smd.transistor")
        WRAP_ADVANCED_SMD_RESISTOR = item(3027, "wrap.component.advanced_smd.resistor")
        WRAP_ADVANCED_SMD_CAPACITOR = item(3028, "wrap.component.advanced_smd.capacitor")
        WRAP_ADVANCED_SMD_DIODE = item(3029, "wrap.component.advanced_smd.diode")
        WRAP_ADVANCED_SMD_INDUCTOR = item(3030, "wrap.component.advanced_smd.inductor")
        WRAP_GOOWARE_SMD_TRANSISTOR = item(3031, "wrap.component.gooware_smd.transistor")
        WRAP_GOOWARE_SMD_RESISTOR = item(3032, "wrap.component.gooware_smd.resistor")
        WRAP_GOOWARE_SMD_CAPACITOR = item(3033, "wrap.component.gooware_smd.capacitor")
        WRAP_GOOWARE_SMD_DIODE = item(3034, "wrap.component.gooware_smd.diode")
        WRAP_GOOWARE_SMD_INDUCTOR = item(3035, "wrap.component.gooware_smd.inductor")
        WRAP_OPTICAL_SMD_TRANSISTOR = item(3036, "wrap.component.optical_smd.transistor")
        WRAP_OPTICAL_SMD_RESISTOR = item(3037, "wrap.component.optical_smd.resistor")
        WRAP_OPTICAL_SMD_CAPACITOR = item(3038, "wrap.component.optical_smd.capacitor")
        WRAP_OPTICAL_SMD_DIODE = item(3039, "wrap.component.optical_smd.diode")
        WRAP_OPTICAL_SMD_INDUCTOR = item(3040, "wrap.component.optical_smd.inductor")
        WRAP_SPINTRONIC_SMD_TRANSISTOR = item(3041, "wrap.component.spintronic_smd.transistor")
        WRAP_SPINTRONIC_SMD_RESISTOR = item(3042, "wrap.component.spintronic_smd.resistor")
        WRAP_SPINTRONIC_SMD_CAPACITOR = item(3043, "wrap.component.spintronic_smd.capacitor")
        WRAP_SPINTRONIC_SMD_DIODE = item(3044, "wrap.component.spintronic_smd.diode")
        WRAP_SPINTRONIC_SMD_INDUCTOR = item(3045, "wrap.component.spintronic_smd.inductor")
        WRAP_COSMIC_SMD_TRANSISTOR = item(3046, "wrap.component.cosmic_smd.transistor")
        WRAP_COSMIC_SMD_RESISTOR = item(3047, "wrap.component.cosmic_smd.resistor")
        WRAP_COSMIC_SMD_CAPACITOR = item(3048, "wrap.component.cosmic_smd.capacitor")
        WRAP_COSMIC_SMD_DIODE = item(3049, "wrap.component.cosmic_smd.diode")
        WRAP_COSMIC_SMD_INDUCTOR = item(3050, "wrap.component.cosmic_smd.inductor")
        WRAP_SUPRACAUSAL_SMD_TRANSISTOR = item(3051, "wrap.component.supracausal_smd.transistor")
        WRAP_SUPRACAUSAL_SMD_RESISTOR = item(3052, "wrap.component.supracausal_smd.resistor")
        WRAP_SUPRACAUSAL_SMD_CAPACITOR = item(3053, "wrap.component.supracausal_smd.capacitor")
        WRAP_SUPRACAUSAL_SMD_DIODE = item(3054, "wrap.component.supracausal_smd.diode")
        WRAP_SUPRACAUSAL_SMD_INDUCTOR = item(3055, "wrap.component.supracausal_smd.inductor")
        WRAP_CPU_CHIP = item(3056, "wrap.wafer.chip.central_processing_unit")
        WRAP_RAM_CHIP = item(3057, "wrap.wafer.chip.random_access_memory")
        WRAP_ILC_CHIP = item(3058, "wrap.wafer.chip.integrated_logic_circuit")
        WRAP_NANO_CPU_CHIP = item(3059, "wrap.wafer.chip.nano_central_processing_unit")
        WRAP_QUBIT_CPU_CHIP = item(3060, "wrap.wafer.chip.qubit_central_processing_unit")
        WRAP_SIMPLE_SOC_CHIP = item(3061,  "wrap.wafer.chip.simple_system_on_chip")
        WRAP_SOC_CHIP = item(3062, "wrap.wafer.chip.system_on_chip")
        WRAP_ADVANCED_SOC_CHIP = item(3063, "wrap.wafer.chip.advanced_system_on_chip")
        WRAP_HIGHLY_ADVANCED_SOC_CHIP = item(3064, "wrap.wafer.chip.highly_advanced_system_on_chip")
        WRAP_NAND_CHIP = item(3065, "wrap.wafer.chip.nand_memory_chip")
        WRAP_NOR_CHIP = item(3066, "wrap.wafer.chip.nor_memory_chip")
        WRAP_ULPIC_CHIP = item(3067, "wrap.wafer.chip.ultra_low_power_integrated_circuit")
        WRAP_LPIC_CHIP = item(3068, "wrap.wafer.chip.low_power_integrated_circuit")
        WRAP_PIC_CHIP = item(3069, "wrap.wafer.chip.power_integrated_circuit")
        WRAP_HPIC_CHIP = item(3070, "wrap.wafer.chip.high_power_integrated_circuit")
        WRAP_UHPIC_CHIP = item(3071, "wrap.wafer.chip.ultra_high_power_integrated_circuit")
        WRAP_NPIC_CHIP = item(3072, "wrap.wafer.chip.nano_power_integrated_circuit")
        WRAP_PPIC_CHIP = item(3073, "wrap.wafer.chip.pico_power_integrated_circuit")
        WRAP_FPIC_CHIP = item(3074, "wrap.wafer.chip.femto_power_integrated_circuit")
        WRAP_APIC_CHIP = item(3075, "wrap.wafer.chip.atto_power_integrated_circuit")
        WRAP_ARAM_CHIP = item(3076, "wrap.wafer.chip.advanced_random_access_memory")
        WRAP_UHASOC_CHIP = item(3077, "wrap.wafer.chip.ultra_highly_advanced_system_on_chip")
        WRAP_CRYSTAL_INTERFACE_CHIP = item(3078, "wrap.wafer.chip.crystal_interface")
        WRAP_PRAM_CHIP = item(3079, "wrap.wafer.chip.phase_change_ram")
        WRAP_ACNOR_CHIP = item(3080, "wrap.wafer.chip.all_optical_cascade_nor")

        WRAP_ENGRAVED_LAPOTRON_CHIP = item(3091, "wrap.circuit.component.engraved_lapotron_chip")
        WRAP_ENGRAVED_DIAMOND_CHIP = item(3092, "wrap.circuit.component.engraved_diamond_chip")
        WRAP_ENGRAVED_RUBY_CHIP = item(3093, "wrap.circuit.component.engraved_ruby_chip")
        WRAP_ENGRAVED_SAPPHIRE_CHIP = item(3094, "wrap.circuit.component.engraved_sapphire_chip")

        WRAP_CRYSTAL_CPU = item(3101, "wrap.crystal.central_processing_unit")
        WRAP_CRYSTAL_SOC = item(3102, "wrap.crystal.system_on_chip")
        WRAP_NEURO_PROCESSOR = item(3103, "wrap.circuit.component.neuro_processor")
        WRAP_NONLINEAR_CHEMICAL_OSCILLATOR = item(3104, "wrap.circuit.component.nonlinear_chemical_oscillator")
        WRAP_OPTICAL_LASER_CONTROL_UNIT = item(3105, "wrap.circuit.component.optical_laser_control_unit")
        WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP = item(3106, "wrap.circuit.component.optoelectronic_system_on_chip")
        WRAP_ESR_COMPUTATION_UNIT = item(3107, "wrap.circuit.component.esr_computation_unit")
        WRAP_EXOTIC_SYSTEM_ON_CHIP = item(3108, "wrap.circuit.component.exotic_system_on_chip")
        WRAP_HOLOGRAPHIC_INFORMATION_IMC = item(3109, "wrap.circuit.component.holographic_information_imc")

        WRAP_CIRCUIT_ULV = item(3201, "wrap.circuit.generic.ulv")
        WRAP_CIRCUIT_LV = item(3202, "wrap.circuit.generic.lv")
        WRAP_CIRCUIT_MV = item(3203, "wrap.circuit.generic.mv")
        WRAP_CIRCUIT_HV = item(3204, "wrap.circuit.generic.hv")
        WRAP_CIRCUIT_EV = item(3205, "wrap.circuit.generic.ev")
        WRAP_CIRCUIT_IV = item(3206, "wrap.circuit.generic.iv")
        WRAP_CIRCUIT_LuV = item(3207, "wrap.circuit.generic.luv")
        WRAP_CIRCUIT_ZPM = item(3208, "wrap.circuit.generic.zpm")
        WRAP_CIRCUIT_UV = item(3209, "wrap.circuit.generic.uv")
        WRAP_CIRCUIT_UHV = item(3210, "wrap.circuit.generic.uhv")
        WRAP_CIRCUIT_UEV = item(3211, "wrap.circuit.generic.uev")
        WRAP_CIRCUIT_UIV = item(3212, "wrap.circuit.generic.uiv")
        WRAP_CIRCUIT_UXV = item(3213, "wrap.circuit.generic.uxv")
        WRAP_CIRCUIT_OpV = item(3214, "wrap.circuit.generic.opv")
        WRAP_CIRCUIT_MAX = item(3215, "wrap.circuit.generic.max")

        // 5001-8000: Miscellaneous Materials.
        MICA_PULP = item(5001, "material.dust.gelatinous_mica_pulp")
            .addOreDict("dustMicaGelatinous")

        MICA_PLATE = item(5002, "material.plate.mica_mineral_wool")
            .addOreDict("plateMicaMineralWool")

        MICA_INSULATOR_PLATE = item(5003, "material.plate.mica_insulator")
            .addOreDict("plateMicaInsulator")

        MICA_INSULATOR_FOIL = item(5004, "material.foil.mica_insulator")

        SAND_DUST = item(5005, "material.dust.sand")
            .addOreDict("dustSand")

        MUD_BALL = item(5006, "material.dust.mud_ball")
            .addOreDict("dustMudBall")

        CARBON_ALLOTROPE_MIXTURE = item(5007, "material.mixture.carbon_allotrope")
            .addComponents(TooltipBehavior { lines ->
                lines.add("§eC" + SmallDigits.toSmallDownNumbers("96"))
            })

        GRAPHENE_ALIGNED_CNT = item(5008, "material.plate.graphene_aligned_cnt")
            .addComponents(TooltipBehavior { lines ->
                lines.add(
                    "§e(C" + SmallDigits.toSmallDownNumbers("6") + "H" + SmallDigits.toSmallDownNumbers("4") + ")"
                            + SmallDigits.toSmallDownNumbers("7") + "C" + SmallDigits.toSmallDownNumbers("12")
                )
            })

        QUANTUM_ANOMALY = item(5009, "material.particle.quantum_anomaly")
        RAW_TESSERACT = item(5010, "material.particle.raw_tesseract")
        ENERGISED_TESSERACT = item(5011, "material.particle.energised_tesseract")
        STABLE_ADHESIVE = item(5012, "material.particle.hyperstable_self_healing_adhesive")
        SUPERCONDUCTOR_COMPOSITE = item(5013, "material.particle.superconductor_rare_earth_composite")
        NAQUADRIA_SUPERSOLID = item(5014, "material.particle.black_body_naquadria_supersolid")
        TIMEPIECE = item(5015, "material.particle.timepiece")
        ZENITH_STAR = item(5016, "material.gem.zenith_star")
        PHONONIC_SEED_CRYSTAL = item(5017, "material.seed_crystal.phononic")
        NANOSILICON_CATHODE = item(5018, "material.foil.nanosilicon_cathode")
            .addOreDict("foilNanosiliconCathode")
        HIGHLY_DENSE_POLYMER_PLATE = item(5019, "material.plate.highly_dense_polymer_plate")
            .addOreDict("plateHighlyDensePolymer")
        GRAVITON_SHARD = item(5020, "material.gem.graviton_shard")
            .addComponents(HaloRenderItemBehavior(10, 0xFF000000.toInt(), {{ GTLiteTextures.HALO }}, true))
            .addOreDict("gemGravitonShard")
        NEUTRONIUM_SPHERE = item(5021, "material.gem.neutronium")
            .addOreDict("sphereNeutronium")
        TRIPLET_NEUTRONIUM_SPHERE = item(5022, "material.gem.triplet_neutronium")
            .addOreDict("sphereTripletNeutronium")
        PEEK_POLYAMIDE_FOIL = item(5023, "material.foil.peek_polyamide")
            .addOreDict("foilPolyetheretherketonePolyamide")
        POLYMER_INSULATOR_FOIL = item(5024, "material.foil.polymer_insulator")
            .addOreDict("foilPolymerInsulator")

        // 9001-10000: Miscellaneous Foods.

        // 9001-9200: Fruits
        BANANA = item(9001, "food.fruit.banana")
            .addComponents(FoodBehavior(2, 1f)
                .setEatingDuration(3 * SECOND))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBanana")
            .addOreDict("cropBanana")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ORANGE = item(9002, "food.fruit.orange")
            .addComponents(FoodBehavior(2, 1f)
                .setEatingDuration(2 * SECOND + 10 * TICK))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitOrange")
            .addOreDict("cropOrange")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        MANGO = item(9003, "food.fruit.mango")
            .addComponents(FoodBehavior(2, 1f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitMango")
            .addOreDict("cropMango")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        APRICOT = item(9004, "food.fruit.apricot")
            .addComponents(FoodBehavior(2, 1f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitApricot")
            .addOreDict("cropApricot")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        LEMON = item(9005, "food.fruit.lemon")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitLemon")
            .addOreDict("cropLemon")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        LIME = item(9006, "food.fruit.lime")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitLime")
            .addOreDict("cropLime")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        OLIVE = item(9007, "food.fruit.olive")
            .addComponents(FoodBehavior(2, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitOlive")
            .addOreDict("cropOlive")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        NUTMEG = item(9008, "food.fruit.nutmeg")
            .addOreDict("cropNutmeg")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COCONUT = item(9009, "food.fruit.coconut")
            .addOreDict("cropCoconut")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // 9201-9300: Seeds and Crops
        COFFEE_SEED = item(9201, "crop.seed.coffee")
            .addOreDict("seedCoffee")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        TOMATO_SEED = item(9202, "crop.seed.tomato")
            .addOreDict("seedTomato")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ONION_SEED = item(9203, "crop.seed.onion")
            .addOreDict("seedOnion")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CUCUMBER_SEED = item(9204, "crop.seed.cucumber")
            .addOreDict("seedCucumber")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GRAPE_SEED = item(9205, "crop.seed.grape")
            .addOreDict("seedGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        SOY_SEED = item(9206, "crop.seed.soy")
            .addOreDict("seedSoy")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BEAN_SEED = item(9207, "crop.seed.bean")
            .addOreDict("seedBean")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        PEA_SEED = item(9208, "crop.seed.pea")
            .addOreDict("seedPea")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        OREGANO_SEED = item(9209, "crop.seed.oregano")
            .addOreDict("seedOregano")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        HORSERADISH_SEED = item(9210, "crop.seed.horseradish")
            .addOreDict("seedHorseradish")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GARLIC_SEED = item(9211, "crop.seed.garlic")
            .addOreDict("seedGarlic")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BASIL_SEED = item(9212, "crop.seed.basil")
            .addOreDict("seedBasil")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        AUBERGINE_SEED = item(9213, "crop.seed.aubergine")
            .addOreDict("seedAubergine")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CORN_SEED = item(9214, "crop.seed.corn")
            .addOreDict("seedCorn")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ARTICHOKE_SEED = item(9215, "crop.seed.artichoke")
            .addOreDict("seedArtichoke")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACK_PEPPER_SEED = item(9216, "crop.seed.black_pepper")
            .addOreDict("seedBlackPepper")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RICE_SEED = item(9217, "crop.seed.rice")
            .addOreDict("seedRice")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        WHITE_GRAPE_SEED = item(9218, "crop.seed.white_grape")
            .addOreDict("seedWhiteGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COTTON_SEED = item(9219, "crop.seed.cotton")
            .addOreDict("seedCotton")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COFFEE_CHERRY = item(9251, "crop.coffee_cherry")
            .addOreDict("cropCoffee")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        TOMATO = item(9252, "crop.tomato")
            .addComponents(FoodBehavior(3, 0.5f)
                .setEatingDuration(3 * SECOND + 12 * TICK))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitTomato")
            .addOreDict("cropTomato")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ONION = item(9253, "crop.onion")
            .addComponents(FoodBehavior(3, 0.33f)
                .setEatingDuration(6 * SECOND + 8 * TICK))
            .addOreDict("foodAny")
            .addOreDict("vegetableAny")
            .addOreDict("vegetableOnion")
            .addOreDict("cropOnion")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CUCUMBER = item(9254, "crop.cucumber")
            .addComponents(FoodBehavior(2, 0.5f)
                .setEatingDuration(3 * SECOND + 4 * TICK))
            .addOreDict("foodAny")
            .addOreDict("vegetableAny")
            .addOreDict("vegetableCucumber")
            .addOreDict("cropCucumber")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GRAPE = item(9255, "crop.grape")
            .addComponents(FoodBehavior(1, 1f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitGrape")
            .addOreDict("cropGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        SOYBEAN = item(9256, "crop.soybean")
            .addOreDict("cropSoy")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BEAN = item(9257, "crop.bean")
            .addOreDict("cropBean")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        PEA = item(9258, "crop.pea")
            .addOreDict("cropPea")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        OREGANO = item(9259, "crop.oregano")
            .addOreDict("cropOregano")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        HORSERADISH = item(9260, "crop.horseradish")
            .addOreDict("cropHorseradish")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GARLIC_BULB = item(9261, "crop.garlic_bulb")
            .addOreDict("cropGarlic")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BASIL = item(9262, "crop.basil")
            .addOreDict("cropBasil")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        AUBERGINE = item(9263, "crop.aubergine")
            .addOreDict("cropAubergine")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CORN = item(9264, "crop.corn")
            .addOreDict("cropCorn")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ARTICHOKE = item(9265, "crop.artichoke")
            .addOreDict("cropArtichoke")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACK_PEPPER = item(9266, "crop.black_pepper")
            .addOreDict("cropBlackPepper")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RICE = item(9267, "crop.rice")
            .addOreDict("cropRice")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        WHITE_GRAPE = item(9268, "crop.white_grape")
            .addComponents(FoodBehavior(1, 1f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitGrape")
            .addOreDict("fruitWhiteGrape")
            .addOreDict("cropWhiteGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COTTON = item(9269, "crop.cotton")
            .addOreDict("cropCotton")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLUEBERRY = item(9270, "crop.blueberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitBlueberry")
            .addOreDict("cropBlueberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACKBERRY = item(9271, "crop.blackberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitBlackberry")
            .addOreDict("cropBlackberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RASPBERRY = item(9272, "crop.raspberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitRaspberry")
            .addOreDict("cropRaspberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        STRAWBERRY = item(9273, "crop.strawberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitStrawberry")
            .addOreDict("cropStrawberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RED_CURRANT = item(9274, "crop.red_currant")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitRedCurrant")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACK_CURRANT = item(9275, "crop.black_currant")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitBlackCurrant")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        WHITE_CURRANT = item(9276, "crop.white_currant")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitWhiteCurrant")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        LINGONBERRY = item(9277, "crop.lingonberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitLingonberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ELDERBERRY = item(9278, "crop.elderberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitElderberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CRANBERRY = item(9279, "crop.cranberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDict("foodAny")
            .addOreDict("fruitAny")
            .addOreDict("fruitBerry")
            .addOreDict("fruitCranberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // ...

        // 9401-9450: Tableware
        CLAY_BOWL = item(9401, "food.tableware.component.clay_bowl")
        CERAMIC_BOWL = item(9402, "food.tableware.ceramic_bowl")
        DIRTY_CERAMIC_BOWL = item(9403, "food.tableware.dirty_ceramic_bowl")
        PAPER_CONE = item(9404, "food.tableware.paper_cone")
        CLAY_CUP = item(9405, "food.tableware.component.clay_cup")
        CERAMIC_CUP = item(9406, "food.tableware.ceramic_cup")
        PLASTIC_BOTTLE = item(9407, "food.tableware.plastic_bottle")

        // 9451-9500: Drinks
        GRAPE_JUICE = item(9451, "food.drink.grape_juice")
            .addComponents(FoodBehavior(3, 0.2F, true, true, ItemStack(Items.GLASS_BOTTLE)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RED_WINE = item(9452, "food.drink.red_wine")
            .addComponents(FoodBehavior(4, 0.7f, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.NAUSEA, 30 * SECOND, 0, 100 - 60),
                RandomPotionEffect(MobEffects.RESISTANCE, 20 * SECOND, 0, 100 - 40))
                .setEatingDuration(8 * SECOND + 16 * TICK))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        VINEGAR = item(9453, "food.drink.vinegar")
            .addComponents(FoodBehavior(2, 0.5f, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.RESISTANCE, 10 * SECOND, 0, 100 - 30)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        POTATO_JUICE = item(9454, "food.drink.potato_juice")
            .addComponents(FoodBehavior(4, 0.4F, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.NAUSEA, 25 * SECOND, 0, 100 - 80)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        VODKA = item(9455, "food.drink.vodka")
            .addComponents(FoodBehavior(4, 0.8F, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.NAUSEA, 20 * SECOND, 0, 100 - 80),
                RandomPotionEffect(MobEffects.RESISTANCE, 40 * SECOND, 2, 100 - 80))
                .setEatingDuration(6 * SECOND + 10 * TICK))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COFFEE_CUP = item(9456, "food.drink.coffee_cup")
            .addComponents(FoodBehavior(8, 0.4F, true, true, CERAMIC_CUP.stackForm,
                RandomPotionEffect(MobEffects.REGENERATION, 60, 1, 0),
                RandomPotionEffect(MobEffects.SPEED, 1800, 2, 0)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ORANGE_JUICE = item(9457, "food.drink.orange_juice")
            .addComponents(FoodBehavior(3, 0.3F, true, true, ItemStack(Items.GLASS_BOTTLE)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ETIRPS = item(9458, "food.drink.etirps")
            .addComponents(FoodBehavior(2, 0.4f, true, true, PLASTIC_BOTTLE.stackForm,
                RandomPotionEffect(MobEffects.SPEED, 1 * MINUTE, 2, 0)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        SPARKLING_WATER = item(9459, "food.drink.sparkling_water")
            .addComponents(FoodBehavior(2, 0.3F, true, true, PLASTIC_BOTTLE.stackForm,
                RandomPotionEffect(MobEffects.SPEED, 30 * SECOND, 1, 0)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CRANBERRY_ETIRPS = item(9460, "food.drink.cranberry_etirps")
            .addComponents(FoodBehavior(3, 0.4f, true, true, PLASTIC_BOTTLE.stackForm,
                RandomPotionEffect(MobEffects.SPEED, 1200, 2, 0),
                RandomPotionEffect(MobEffects.REGENERATION, 200, 1, 100 - 80)))

        // 9501-9550: Congees and Soups
        POLENTA = item(9501, "food.polenta")
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.food.polenta.tooltip.1"))
                lines.add(I18n.format("metaitem.food.polenta.tooltip.2"))
            })
            .addComponents(FoodBehavior(6, 0.4F)
                .setReturnStack(DIRTY_CERAMIC_BOWL)
                .setPotionEffects(RandomPotionEffect(MobEffects.SATURATION, 1 * SECOND, 0, 100 - 50)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // 9551-9600: Noodles
        GRAHAM_CRACKER = item(9551, "food.graham_cracker")
            .addComponents(FoodBehavior(2, 0.6F))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // 9601-9650: Fruit Products
        GOLDEN_STRAWBERRY = item(9601, "food.golden_strawberry")
            .addComponents(FoodBehavior(8, 9.6F)
                .setEatingDuration(5 * SECOND)
                .setPotionEffects(RandomPotionEffect(MobEffects.INSTANT_HEALTH, 1 * TICK, 3, 100 - 100),
                    RandomPotionEffect(MobEffects.REGENERATION, 20 * SECOND, 10, 100 - 100),
                    RandomPotionEffect(MobEffects.RESISTANCE, 2 * MINUTE + 30 * SECOND, 3, 100 - 100),
                    RandomPotionEffect(MobEffects.ABSORPTION, 1 * MINUTE, 6, 100 - 100)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        MOON_BERRY = item(9602, "food.moon_berry")
            .addComponents(FoodBehavior(16, 19.2F)
                .setEatingDuration(5 * SECOND)
                .setPotionEffects(RandomPotionEffect(MobEffects.INSTANT_HEALTH, 1 * TICK, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.REGENERATION, 2 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.RESISTANCE, 10 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.ABSORPTION, 5 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.STRENGTH, 8 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.FIRE_RESISTANCE, 20 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.WATER_BREATHING, 20 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.HASTE, 15 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.SATURATION, 8 * MINUTE, 127, 100 - 100),
                    RandomPotionEffect(MobEffects.LUCK, 10 * MINUTE, 127, 100 - 100)))
            .addComponents(HaloRenderItemBehavior(10, 0xFFFFFFFF.toInt(), {{ GTLiteTextures.HALO }}, true))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        HARD_APPLE_CANDY = item(9603, "food.hard_apple_candy")
            .addComponents(FoodBehavior(3, 0.5f)
                .setEatingDuration(1 * SECOND + 4 * TICK)
                .setPotionEffects(RandomPotionEffect(MobEffects.REGENERATION, 1 * MINUTE, 1, 50)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

    }

    // region MetaItem Factory Methods
        
    /**
     * @param id              The meta value of the meta item.
     * @param unlocalizedName The unlocalized name of the meta item.
     */
    private fun item(id: Int, unlocalizedName: String) = META_ITEMS.addItem(id, unlocalizedName)
    
    // endregion
        
    // @formatter:on
        
}