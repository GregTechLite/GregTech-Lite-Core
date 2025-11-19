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
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.MaterialStack
import gregtech.api.unification.stack.RecyclingData
import gregtech.api.util.SmallDigits
import gregtech.client.utils.TooltipHelper
import gregtech.common.creativetab.GTCreativeTabs
import gregtech.common.items.behaviors.TooltipBehavior
import gregtechlite.gtlitecore.client.event.TextAnimations
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AIR_VENT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ALL_OPTICAL_CASCADE_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AMPLITUDE_DUALITY_DISTURBANCE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ATTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ATTO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_LARGE_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_LARGE_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_MEDIUM_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_MEDIUM_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_SMALL_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_SMALL_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_MAX_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_OpV_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UEV_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UHV_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UIV_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UXV_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOHRIUM_DOPED_CERIUM_CARBONATE_BOULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOHRIUM_DOPED_CERIUM_CARBONATE_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BOSE_EINSTEIN_CONDENSATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BREVIBACTERIUM_FLAVUM_PETRI_DISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BZ_REACTION_CHAMBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CARBON_ALLOTROPE_MIXTURE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_BUTCHERY_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_CROWBAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_EMPTY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_FILE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_HARD_HAMMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_MORTAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_ROLLING_PIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SAW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SCREWDRIVER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SOFT_MALLET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WIRE_CUTTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WRENCH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADHESION_PROMOTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADVANCED_PLASTIC_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADVANCED_RADIOACTIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ADVANCED_RUBBER_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ARTIFICIAL_GEM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_BASE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_BIOLOGICAL_INTELLIGENCE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_HIGH_EXPLOSIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_NAQUADAH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ORGANIC_DYE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_PLASTIC_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_PLATINUM_GROUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RADIOACTIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RARE_EARTH_GROUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RARE_METAL_GROUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RAW_INTELLIGENCE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_RUBBER_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_STELLAR_CORE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_TEMPORAL_HARMONY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_TITANIUM_TUNGSTEN_INDIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ULTIMATE_PLASTIC_POLYMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CATALYST_ULTIMATE_RADIOACTIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CIRCUIT_PATTERN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLOSED_TIMELIKE_CURVE_COMPUTATIONAL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLOSED_TIMELIKE_CURVE_GUIDANCE_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COMPONENT_GRINDER_BORON_NITRIDE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COMPONENT_GRINDER_HALKONITE_STEEL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONDENSATE_CONTAINMENT_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_EXOTIC_MATTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_HIGH_DENSITY_PROTONIC_MATTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_KERR_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_KN_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONTAINED_RN_SINGULARITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONVEYOR_MODULE_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_ASSEMBLY_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_COMPUTER_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_INFORMATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_MAINFRAME_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_PROCESSOR_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_ADAMANTIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_COSMIC_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_VIBRANIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_INTERFACE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_INTERFACE_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_SOC_SOCKET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CUPRIAVIDUS_NECATOR_PETRI_DISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIAMOND_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIELECTRIC_MIRROR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIRTY_PETRI_DISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_BUTCHERY_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_CROWBAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_FILE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_HARD_HAMMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_MORTAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_ROLLING_PIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_SAW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_SCREWDRIVER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_SOFT_MALLET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_WIRE_CUTTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_WRENCH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DRAIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EIGENFOLDED_SPACETIME_MANIFOLD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_MOTOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PISTON_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PUMP_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_SIGNAL_PETRI_DISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EMITTER_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EMPTY_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENERGISED_TESSERACT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ESCHERICHIA_COLI_PETRI_DISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ESR_COMPUTATION_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EUROPIUM_DOPED_CUBIC_ZIRCONIA_BOULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EUROPIUM_DOPED_CUBIC_ZIRCONIA_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITATION_MAINTAINER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITATION_SPECTRUM_COMPOSITE_LOGICAL_GATE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITED_EXOTIC_ATOM_RESTRICT_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXCITED_EXOTIC_ATOM_RESTRICT_CPU_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXOTIC_ATOM_RESTRICT_CPU_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EXOTIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FIELD_GENERATOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FUEL_ROD_EMPTY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_ASSEMBLY_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_COMPUTER_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_MAINFRAME_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_PROCESSOR_ZPM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPHENE_ALIGNED_CNT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAVITON_SHARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAVITON_TRANSDUCER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_BOULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HELIUM_NEON_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HIGHLY_DENSE_POLYMER_PLATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HOLOGRAPHIC_INFORMATION_IMC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.INFINITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_CORE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_DECORATION
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_FOOD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_MACHINE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MACROWORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETIC_DOMAIN_WALL_INVERSION_NAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETRON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MANIFOLD_OSCILLATORY_POWER_CELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_BASE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_BLAZE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_CREEPER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_ENDERMAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_ENDER_DRAGON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_GHAST
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_GUARDIAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_SHULKER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_SKELETON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_SLIME
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_SPIDER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_WITCH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_WITHER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_WITHER_SKELETON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_ZOMBIE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_PLATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_PLATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_PULP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICROWORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_EV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_HV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_LuV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_MV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_ZPM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MUD_BALL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANOSILICON_CATHODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NAQUADRIA_SUPERSOLID
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ND_YAG_LASER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NEUTRONIUM_SPHERE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NONLINEAR_CHEMICAL_OSCILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NUCLEAR_CLOCK
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_ASSEMBLY_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_COMPUTER_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_FIBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_IMC_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_LASER_CONTROL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_MAINFRAME_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_PROCESSOR_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTOELECTRONIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEEK_POLYAMIDE_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PERIODICALLY_POLED_OPTICAL_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PHASE_CHANGE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PHONONIC_SEED_CRYSTAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_SPINORIAL_MEMORY_SYSTEM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RAW_TESSERACT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RECURSIVELY_FOLDED_NEGATIVE_SPACE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RELATIVISTIC_HEAT_CAPACITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ROBOT_ARM_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RUBY_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RYDBERG_SPINOR_ARRAY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAND_DUST
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAPPHIRE_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SCINTILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SCINTILLATOR_CRYSTAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SENSOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_ROUND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_SCREW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_FLAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_OCTAGONAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_STRIPES
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPACETIME_LIGHT_CONE_STABILIZATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_ASSEMBLY_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_COMPUTER_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_MAINFRAME_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_PROCESSOR_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPIN_FOAM_ANNIHILATED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPIN_TRANSFER_TORQUE_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STABILIZED_WORMHOLE_GENERATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STABLE_ADHESIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STREPTOCOCCUS_PYOGENES_PETRI_DISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STRUCTURE_WRITER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPERCONDUCTOR_COMPOSITE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_ASSEMBLY_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_COMPUTER_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_MAINFRAME_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_PROCESSOR_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_SPACETIME_CONDENSER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TEMPORAL_SCALE_WORLDSHEET_LOGICAL_GATE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TIMEPIECE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TIME_DILATION_CONTAINMENT_CELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOPOLOGICAL_INSULATOR_TUBE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOPOLOGICAL_MANIPULATOR_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TRIPLET_NEUTRONIUM_SPHERE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTIMATE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTRA_HIGHLY_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTRA_HIGHLY_ADVANCED_SOC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VACUUM_TUBE_COMPONENT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ACNOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADDRAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_APIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ARAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_BASIC_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_EV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_HV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_LuV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_MV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_ULV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_ZPM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COATED_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_INFORMATION_MODULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_CPU
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_INTERFACE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_SOC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EARCPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ELITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_LAPOTRON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EPOXY_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ESCLG_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ESR_COMPUTATION_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EXOTIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EXTREME_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_FIBER_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_FPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOD_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HIGHLY_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HOLOGRAPHIC_INFORMATION_IMC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ILC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_INFINITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_LPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_MINAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_MULTILAYER_FIBER_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NANO_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NEURO_PROCESSOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NONLINEAR_CHEMICAL_OSCILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_LASER_CONTROL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PHENOLIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PLASTIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PLASTIC_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PRAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_QUBIT_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SIMPLE_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_STTRAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_UHASOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_UHPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ULPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ULTIMATE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_WETWARE_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_WETWARE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.X_RAY_WAVEGUIDE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ZENITH_STAR
import gregtechlite.gtlitecore.common.item.behavior.CircuitPatternBehavior
import gregtechlite.gtlitecore.common.item.behavior.HaloRenderItemBehavior
import gregtechlite.gtlitecore.common.item.behavior.StructureWriterBehavior
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.EnumRarity
import org.lwjgl.input.Keyboard

object GTLiteMetaItem1
{
        
    internal lateinit var META_ITEMS_1: MetaItem<*>

    fun init()
    { 
        META_ITEMS_1 = GTLiteMetaItem()
        META_ITEMS_1.setRegistryName("gtlite_meta_item_1")
        META_ITEMS_1.setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)
    }
        
    @JvmStatic
    fun register()
    {
        // region 0-10: Mod Logos
        LOGO_CORE = item(1, "gtlite_logo.core")
            .setInvisible()
        LOGO_MACHINE = item(2, "gtlite_logo.machine")
            .setInvisible()
        LOGO_DECORATION = item(3, "gtlite_logo.decoration")
            .setInvisible()
        LOGO_FOOD = item(4, "gtlite_logo.food")
            .setInvisible()

        // endregion

        // region 11-85: Shape Molds & Extruders

        // 11-40: Common Steel Molds (11-25) & Extruders addition (26-40)

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

        // 41-50: Slicer Blades (based on Steel Extruders)
        SLICER_BLADE_FLAT = item(41, "shape.slicer_blade.flat")
        SLICER_BLADE_STRIPES = item(42, "shape.slicer_blade.stripes")
        SLICER_BLADE_OCTAGONAL = item(43, "shape.slicer_blade.octagonal")

        // 51-70: Vanadium Steel Molds & Extruders
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

        // endregion

        // region 86-100: Credits
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

        // endregion

        // region 101-106: Voltage Coils
        VOLTAGE_COIL_UHV = item(101, "voltage_coil.uhv")
        VOLTAGE_COIL_UEV = item(102, "voltage_coil.uev")
        VOLTAGE_COIL_UIV = item(103, "voltage_coil.uiv")
        VOLTAGE_COIL_UXV = item(104, "voltage_coil.uxv")
        VOLTAGE_COIL_OpV = item(105, "voltage_coil.opv")
        VOLTAGE_COIL_MAX = item(106, "voltage_coil.max")

        // endregion

        // region 107-200: Covers
        ELECTRIC_MOTOR_MAX = item(107, "electric.motor.max")

        ELECTRIC_PUMP_MAX = item(108, "electric.pump.max")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.electric.pump.tooltip"))
                it.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", 1280 * 64 * 64 * 4 / 20))
            })

        CONVEYOR_MODULE_MAX = item(109, "conveyor.module.max")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.conveyor.module.tooltip"))
                it.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate_stacks", 16))
            })

        ELECTRIC_PISTON_MAX = item(110, "electric.piston.max")

        ROBOT_ARM_MAX = item(111, "robot.arm.max")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.robot.arm.tooltip"))
                it.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate_stacks", 16))
            })

        FIELD_GENERATOR_MAX = item(112, "field.generator.max")
        EMITTER_MAX = item(113, "emitter.max")
        SENSOR_MAX = item(114, "sensor.max")

        AIR_VENT = item(115, "cover.air_vent")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.air_vent.tooltip.1"))
                it.add(I18n.format("metaitem.cover.air_vent.tooltip.2", 100))
            })

        DRAIN = item(116, "cover.drain")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.drain.tooltip.1"))
                it.add(I18n.format("metaitem.cover.drain.tooltip.2", 500))
            })

        SOLAR_PANEL_UHV = item(117, "cover.solar_panel.uhv")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                it.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UHV], VNF[UHV]))
            })

        SOLAR_PANEL_UEV = item(118, "cover.solar_panel.uev")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                it.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UEV], VNF[UEV]))
            })

        SOLAR_PANEL_UIV = item(119, "cover.solar_panel.uiv")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                it.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UIV], VNF[UIV]))
            })

        SOLAR_PANEL_UXV = item(120, "cover.solar_panel.uxv")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                it.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[UXV], VNF[UXV]))
            })

        SOLAR_PANEL_OpV = item(121, "cover.solar_panel.opv")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                it.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[OpV], VNF[OpV]))
            })

        SOLAR_PANEL_MAX = item(122, "cover.solar_panel.max")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.1"))
                it.add(I18n.format("metaitem.cover.solar.panel.tooltip.2"))
                it.add(I18n.format("gregtech.universal.tooltip.voltage_out", V[MAX], VNF[MAX]))
            })

        // endregion

        // region 201-210: Boards & Circuit Boards
        GOOWARE_BOARD = item(201, "board.gooware")
        OPTICAL_BOARD = item(202, "board.optical")
        SPINTRONIC_BOARD = item(203, "board.spintronic")

        ULTIMATE_CIRCUIT_BOARD = item(206, "circuit_board.ultimate")
        PERFECT_CIRCUIT_BOARD = item(207, "circuit_board.perfect")
        INFINITE_CIRCUIT_BOARD = item(208, "circuit_board.infinite")

        // endregion

        // region 211-250: SMDs
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

        // endregion

        // region 251-300: Circuits
        GOOWARE_PROCESSOR_ZPM = item(251, "circuit.gooware_processor")
            .setUnificationData(OrePrefix.circuit, Tier.ZPM)
        GOOWARE_ASSEMBLY_UV = item(252, "circuit.gooware_assembly")
            .setUnificationData(OrePrefix.circuit, Tier.UV)
        GOOWARE_COMPUTER_UHV = item(253, "circuit.gooware_computer")
            .setUnificationData(OrePrefix.circuit, Tier.UHV)
        GOOWARE_MAINFRAME_UEV = item(254, "circuit.gooware_mainframe")
            .setUnificationData(OrePrefix.circuit, Tier.UEV)

        OPTICAL_PROCESSOR_UV = item(255, "circuit.optical_processor")
            .setUnificationData(OrePrefix.circuit, Tier.UV)
        OPTICAL_ASSEMBLY_UHV = item(256, "circuit.optical_assembly")
            .setUnificationData(OrePrefix.circuit, Tier.UHV)
        OPTICAL_COMPUTER_UEV = item(257, "circuit.optical_computer")
            .setUnificationData(OrePrefix.circuit, Tier.UEV)
        OPTICAL_MAINFRAME_UIV = item(258, "circuit.optical_mainframe")
            .setUnificationData(OrePrefix.circuit, Tier.UIV)

        SPINTRONIC_PROCESSOR_UHV = item(259, "circuit.spintronic_processor")
            .setUnificationData(OrePrefix.circuit, Tier.UHV)
        SPINTRONIC_ASSEMBLY_UEV = item(260, "circuit.spintronic_assembly")
            .setUnificationData(OrePrefix.circuit, Tier.UEV)
        SPINTRONIC_COMPUTER_UIV = item(261, "circuit.spintronic_computer")
            .setUnificationData(OrePrefix.circuit, Tier.UIV)
        SPINTRONIC_MAINFRAME_UXV = item(262, "circuit.spintronic_mainframe")
            .setUnificationData(OrePrefix.circuit, Tier.UXV)

        COSMIC_PROCESSOR_UEV = item(263, "circuit.cosmic_processor")
            .setUnificationData(OrePrefix.circuit, Tier.UEV)
            .addComponents(TooltipBehavior { lines ->
                lines.add(I18n.format("metaitem.circuit.cosmic_processor.tooltip.1"))
                lines.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_processor.tooltip.2"))
            })

        COSMIC_ASSEMBLY_UIV = item(264, "circuit.cosmic_assembly")
            .setUnificationData(OrePrefix.circuit, Tier.UIV)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.circuit.cosmic_assembly.tooltip.1"))
                it.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_assembly.tooltip.2"))
            })

        COSMIC_COMPUTER_UXV = item(265, "circuit.cosmic_computer")
            .setUnificationData(OrePrefix.circuit, Tier.UXV)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.circuit.cosmic_computer.tooltip.1"))
                it.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_computer.tooltip.2"))
            })

        COSMIC_MAINFRAME_OpV = item(266, "circuit.cosmic_mainframe")
            .setUnificationData(OrePrefix.circuit, Tier.OpV)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.circuit.cosmic_mainframe.tooltip.1"))
                it.add(TextAnimations.GRADIENT_PURPLE_RED("metaitem.circuit.cosmic_mainframe.tooltip.2"))
            })

        SUPRACAUSAL_PROCESSOR_UIV = item(267, "circuit.supracausal_processor")
            .setUnificationData(OrePrefix.circuit, Tier.UIV)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.circuit.supracausal_processor.tooltip.1"))
                it.add(TextAnimations.GRADIENT_RAINBOW("metaitem.circuit.supracausal_processor.tooltip.2"))
            })

        SUPRACAUSAL_ASSEMBLY_UXV = item(268, "circuit.supracausal_assembly")
            .setUnificationData(OrePrefix.circuit, Tier.UXV)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.circuit.supracausal_assembly.tooltip.1"))
                it.add(TextAnimations.GRADIENT_RAINBOW("metaitem.circuit.supracausal_assembly.tooltip.2"))
            })

        SUPRACAUSAL_COMPUTER_OpV = item(269, "circuit.supracausal_computer")
            .setUnificationData(OrePrefix.circuit, Tier.OpV)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.circuit.supracausal_computer.tooltip.1"))
                it.add(TextAnimations.GRADIENT_RAINBOW("metaitem.circuit.supracausal_computer.tooltip.2"))
            })

        SUPRACAUSAL_MAINFRAME_MAX = item(270, "circuit.supracausal_mainframe")
            .setUnificationData(OrePrefix.circuit, Tier.MAX)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.circuit.supracausal_mainframe.tooltip.1"))
                it.add(TextAnimations.GRADIENT_RAINBOW_BOLD("metaitem.circuit.supracausal_mainframe.tooltip.2"))
            })

        // endregion

        // region 301-500: Covers & Cover Components
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

        // endregion

        // region 501-600: Tool Components
        COMPONENT_GRINDER_BORON_NITRIDE = item(501, "tool.component.grinder.boron_nitride")
        COMPONENT_GRINDER_HALKONITE_STEEL = item(502, "tool.component.grinder.halkonite_steel")

        // endregion

        // region 601-700: Tools
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

        // ...

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

        // endregion

        // region 701-800: Batteries
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
            .setUnificationData(OrePrefix.battery, Tier.UHV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS as CreativeTabs)

        BATTERY_UEV_NEUTRONIUM = item(712, "battery.uev.neutronium")
            .addComponents(ElectricStats.createRechargeableBattery(41_943_040_000L, UEV))
            .setUnificationData(OrePrefix.battery, Tier.UEV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_UIV_NEUTRONIUM = item(713, "battery.uiv.neutronium")
            .addComponents(ElectricStats.createRechargeableBattery(167_772_160_000L, UIV))
            .setUnificationData(OrePrefix.battery, Tier.UIV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_UXV_INFINITY = item(714, "battery.uxv.infinity")
            .addComponents(ElectricStats.createRechargeableBattery(671_088_640_000L, UXV))
            .setUnificationData(OrePrefix.battery, Tier.UXV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_OpV_INFINITY = item(715, "battery.opv.infinity")
            .addComponents(ElectricStats.createRechargeableBattery(2_684_354_560_000L, OpV))
            .setUnificationData(OrePrefix.battery, Tier.OpV)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        BATTERY_MAX_INFINITY = item(716, "battery.max.infinity")
            .addComponents(ElectricStats.createRechargeableBattery(10_737_418_240_000L, MAX))
            .setUnificationData(OrePrefix.battery, Tier.MAX)
            .setModelAmount(8)
            .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        // endregion

        // region 1001-1100: Debug Tools
        STRUCTURE_WRITER = item(1001, "debug.structure_writer")
            .addComponents(StructureWriterBehavior)
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.debug.structure_writer.tooltip.1"))
                it.add(TooltipHelper.BLINKING_CYAN.toString() + I18n.format("metaitem.debug.structure_writer.tooltip.2"))

                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
                {
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.3"))
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.4"))
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.5"))
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.6"))
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.7"))
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.8"))
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.9"))
                    it.add(I18n.format("metaitem.debug.structure_writer.tooltip.10"))
                }
                else
                {
                    it.add(I18n.format("gregtech.tooltip.hold_shift"))
                }
            })
        .setCreativeTabs(GTCreativeTabs.TAB_GREGTECH_TOOLS)

        // endregion

        // region 1101-2000: Circuit Components
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

        // endregion

        // region 2001-2500: Boules & Wafers
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

        // endregion

        // 2500-3000: ...

        // region 3001-5000: Wrap Components
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
        WRAP_STTRAM_CHIP = item(3081, "wrap.wafer.chip.spin_transfer_torque_ram")
        WRAP_MINAND_CHIP = item(3082, "wrap.wafer.chip.magnetic_domain_wall_inversion_nand")
        WRAP_EARCPU_CHIP = item(3083, "wrap.wafer.chip.excited_exotic_atom_restrict_central_processing_unit")
        WRAP_ADDRAM_CHIP = item(3084, "wrap.wafer.chip.amplitude_duality_disturbance_ram")
        WRAP_ESCLG_CHIP = item(3085, "wrap.wafer.chip.excitation_spectrum_composite_logical_gate")

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
        WRAP_COSMIC_INFORMATION_MODULE = item(3109, "wrap.circuit.component.cosmic_information_module")
        WRAP_HOLOGRAPHIC_INFORMATION_IMC = item(3110, "wrap.circuit.component.holographic_information_imc")

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

        // endregion

        // region 5001-8000: Miscellaneous Material Parts
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
            .addComponents(TooltipBehavior {
                it.add("§eC" + SmallDigits.toSmallDownNumbers("96"))
            })

        GRAPHENE_ALIGNED_CNT = item(5008, "material.plate.graphene_aligned_cnt")
            .addComponents(TooltipBehavior {
                it.add(
                    "§e(C" + SmallDigits.toSmallDownNumbers("6") + "H" + SmallDigits.toSmallDownNumbers("4") + ")"
                            + SmallDigits.toSmallDownNumbers("7") + "C" + SmallDigits.toSmallDownNumbers("12"))
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

        // endregion
    }

    private fun item(id: Int, name: String) = META_ITEMS_1.addItem(id, name)

}