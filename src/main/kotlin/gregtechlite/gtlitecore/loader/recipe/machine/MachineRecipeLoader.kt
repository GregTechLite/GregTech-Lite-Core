package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.items.OreDictNames
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.BlackBronze
import gregtech.api.unification.material.Materials.BlackSteel
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.Brass
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CobaltBrass
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NeodymiumMagnetic
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Potin
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TreatedWood
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.UraniumRhodiumDinaquadide
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.material.Materials.Zircaloy4
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_HV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_MV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UEV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UIV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.COVER_ENDER_FLUID_LINK
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_EV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_EV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_HV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_EV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_HV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_MV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.EMITTER_EV
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LV
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.ENERGY_LAPOTRONIC_ORB
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.ROBOT_ARM_EV
import gregtech.common.items.MetaItems.ROBOT_ARM_HV
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LV
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_MV
import gregtech.common.items.MetaItems.ROBOT_ARM_UEV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UIV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.SENSOR_EV
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LuV
import gregtech.common.items.MetaItems.SENSOR_MV
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.SENSOR_ZPM
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.ULTIMATE_BATTERY
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER
import gregtech.common.items.MetaItems.VOLTAGE_COIL_HV
import gregtech.common.items.MetaItems.VOLTAGE_COIL_LuV
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import gregtech.common.metatileentities.MetaTileEntities.ACTIVE_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.ADVANCED_FLUID_DRILLING_RIG
import gregtech.common.metatileentities.MetaTileEntities.ALLOY_SMELTER
import gregtech.common.metatileentities.MetaTileEntities.ARC_FURNACE
import gregtech.common.metatileentities.MetaTileEntities.ASSEMBLER
import gregtech.common.metatileentities.MetaTileEntities.ASSEMBLY_LINE
import gregtech.common.metatileentities.MetaTileEntities.AUTOCLAVE
import gregtech.common.metatileentities.MetaTileEntities.BENDER
import gregtech.common.metatileentities.MetaTileEntities.BREWERY
import gregtech.common.metatileentities.MetaTileEntities.CENTRIFUGE
import gregtech.common.metatileentities.MetaTileEntities.CHARGER
import gregtech.common.metatileentities.MetaTileEntities.CIRCUIT_ASSEMBLER
import gregtech.common.metatileentities.MetaTileEntities.COMPRESSOR
import gregtech.common.metatileentities.MetaTileEntities.CUTTER
import gregtech.common.metatileentities.MetaTileEntities.DISTILLATION_TOWER
import gregtech.common.metatileentities.MetaTileEntities.DISTILLERY
import gregtech.common.metatileentities.MetaTileEntities.ELECTRIC_BLAST_FURNACE
import gregtech.common.metatileentities.MetaTileEntities.ELECTROLYZER
import gregtech.common.metatileentities.MetaTileEntities.ELECTROMAGNETIC_SEPARATOR
import gregtech.common.metatileentities.MetaTileEntities.EXTRACTOR
import gregtech.common.metatileentities.MetaTileEntities.EXTRUDER
import gregtech.common.metatileentities.MetaTileEntities.FERMENTER
import gregtech.common.metatileentities.MetaTileEntities.FLUID_SOLIDIFIER
import gregtech.common.metatileentities.MetaTileEntities.FORGE_HAMMER
import gregtech.common.metatileentities.MetaTileEntities.FORMING_PRESS
import gregtech.common.metatileentities.MetaTileEntities.FUSION_REACTOR
import gregtech.common.metatileentities.MetaTileEntities.GAS_COLLECTOR
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.common.metatileentities.MetaTileEntities.IMPLOSION_COMPRESSOR
import gregtech.common.metatileentities.MetaTileEntities.LARGE_CHEMICAL_REACTOR
import gregtech.common.metatileentities.MetaTileEntities.LASER_ENGRAVER
import gregtech.common.metatileentities.MetaTileEntities.LATHE
import gregtech.common.metatileentities.MetaTileEntities.MACERATOR
import gregtech.common.metatileentities.MetaTileEntities.MIXER
import gregtech.common.metatileentities.MetaTileEntities.ORE_WASHER
import gregtech.common.metatileentities.MetaTileEntities.PACKER
import gregtech.common.metatileentities.MetaTileEntities.POLARIZER
import gregtech.common.metatileentities.MetaTileEntities.POWER_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.PRIMITIVE_BLAST_FURNACE
import gregtech.common.metatileentities.MetaTileEntities.PYROLYSE_OVEN
import gregtech.common.metatileentities.MetaTileEntities.ROCK_BREAKER
import gregtech.common.metatileentities.MetaTileEntities.SCANNER
import gregtech.common.metatileentities.MetaTileEntities.SIFTER
import gregtech.common.metatileentities.MetaTileEntities.STEAM_ALLOY_SMELTER_BRONZE
import gregtech.common.metatileentities.MetaTileEntities.STEAM_COMPRESSOR_BRONZE
import gregtech.common.metatileentities.MetaTileEntities.SUBSTATION_ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.THERMAL_CENTRIFUGE
import gregtech.common.metatileentities.MetaTileEntities.VACUUM_FREEZER
import gregtech.common.metatileentities.MetaTileEntities.WIREMILL
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumBronze
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Antimatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BabbitAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumStrontiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CinobiteA243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EglinSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FranciumCaesiumCadmiumBromide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Grisium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyN
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA956
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Inconel625
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MaragingSteel250
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MolybdenumDisilicide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nitinol60
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Plutonium244
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PreciousMetalAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Protomatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ReneN5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ResonantStrangeMeson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Staballoy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Talonite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumBariumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tumbaga
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WatertightSteel
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFireboxCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.Manipulator
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.WireCoil
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ATTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UIV
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ACID_GENERATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ADVANCED_FUSION_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ALLOY_BLAST_SMELTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ANTIMATTER_FORGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ANTIMATTER_GENERATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BATH_CONDENSER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BEDROCK_DRILLING_RIG
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BIO_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BURNER_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CATALYTIC_REFORMER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CHEMICAL_PLANT
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CIRCUIT_ASSEMBLY_LINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.COAGULATION_TANK
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.COMPONENT_ASSEMBLY_LINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.COSMIC_RAY_DETECTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CRYOGENIC_FREEZER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CRYOGENIC_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CRYSTALLIZATION_CRUCIBLE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CVD_UNIT
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ELECTRIC_IMPLOSION_COMPRESSOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ENERGY_INFUSER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ENTRODYNAMICALLY_PHASE_CHANGER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.FOOD_PROCESSOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.FUSION_REACTOR_MK4
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.FUSION_REACTOR_MK5
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.HOT_COOLANT_TURBINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.HYDRAULIC_FRACKER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.INDUSTRIAL_COKE_OVEN
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ACID_GENERATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ARC_FURNACE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ASSEMBLER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_AUTOCLAVE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_BENDER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_BIO_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_BREWERY
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_BURNER_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_CENTRIFUGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_CRYOGENIC_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_CUTTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_DISTILLERY
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ELECTROLYZER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ELECTROMAGNET
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_EXTRACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_EXTRUDER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_FLUID_SOLIDIFIER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_FOOD_PROCESSOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_FORGE_HAMMER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_GAS_COLLECTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_LASER_ENGRAVER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_MACERATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_MASS_FABRICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_MIXER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_NAQUADAH_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ORE_WASHER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_PACKER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_REPLICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ROCKET_ENGINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_ROCK_BREAKER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_SIFTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_STEAM_ALLOY_SMELTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_STEAM_COMPRESSOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_TRANSFORMER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LARGE_WIREMILL
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INDUCED_CVD_UNIT
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_1048576
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.MASS_FABRICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.MINING_DRONE_AIRPORT
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.MULTICOOKER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NANOSCALE_FABRICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NANO_FORGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NAQUADAH_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NUCLEAR_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.PCB_FACTORY
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.PLASMA_ARC_TRANSMITTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.PLASMA_ENHANCED_CVD_UNIT
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.QUANTUM_FORCE_TRANSFORMER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.REPLICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ROASTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ROCKET_ENGINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SONICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SPACE_ASSEMBLER_MK1
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SPACE_ASSEMBLER_MK2
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SPACE_ASSEMBLER_MK3
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SPACE_ELEVATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SPACE_PUMP_MK1
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SPACE_PUMP_MK2
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SPACE_PUMP_MK3
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.STEAM_ENGINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.STELLAR_FORGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SUPERCRITICAL_FLUID_TURBINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.TOOL_CASTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.VACUUM_CHAMBER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.VOLCANUS

internal object MachineRecipeLoader
{

    // @formatter:off

    fun init()
    {

        // Coagulation Tank
        ModHandler.addShapedRecipe(true, "coagulation_tank", COAGULATION_TANK.stackForm,
            "PRP", "sQh", "PSP",
            'P', UnificationEntry(plate, TreatedWood),
            'Q', UnificationEntry(pipeLargeFluid, TreatedWood),
            'R', UnificationEntry(rotor, Steel),
            'S', UnificationEntry(screw, Steel))

        // Large Steam Compressor
        ModHandler.addShapedRecipe(true, "large_steam_compressor", LARGE_STEAM_COMPRESSOR.stackForm,
            "CPC", "GFG", "CPC",
            'C', GTMetalCasing.BRONZE_BRICKS.stack,
            'P', OreDictNames.craftingPiston,
            'F', STEAM_COMPRESSOR_BRONZE.stackForm,
            'G', UnificationEntry(gear, Potin))

        // Large Steam Alloy Smelter
        ModHandler.addShapedRecipe(true, "large_steam_alloy_smelter", LARGE_STEAM_ALLOY_SMELTER.stackForm,
            "PGP", "CFC", "PGP",
            'F', STEAM_ALLOY_SMELTER_BRONZE.stackForm,
            'G', UnificationEntry(gear, Brass),
            'P', UnificationEntry(pipeNormalFluid, Bronze),
            'C', GTMetalCasing.BRONZE_BRICKS.stack)

        // Steam Engine
        ModHandler.addShapedRecipe(true, "steam_engine", STEAM_ENGINE.stackForm,
            "FPF", "PCP", "SGS",
            'C', MetalCasing.BRASS.stack,
            'S', UnificationEntry(gearSmall, Bronze),
            'G', UnificationEntry(gear, Steel),
            'F', UnificationEntry(pipeSmallFluid, Potin),
            'P', UnificationEntry(plate, Brass))

        // Industrial Primitive Blast Furnace
        ModHandler.addShapedRecipe(true, "industrial_primitive_blast_furnace", INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.stackForm,
            "FBF", "BAB", "FBF",
            'F', PRIMITIVE_BLAST_FURNACE.stackForm,
            'B', GTFireboxCasing.STEEL_FIREBOX.stack,
            'A', OreDictUnifier.get(frameGt, Steel))

        // Mining Drone Airport
        ModHandler.addShapedRecipe(true, "mining_drone_airport", MINING_DRONE_AIRPORT.stackForm,
            "RDE", "CHC", "XWX",
            'H', HULL[LV].stackForm,
            'D', MINING_DRONE_LV,
            'R', ROBOT_ARM_LV,
            'E', EMITTER_LV,
            'C', CONVEYOR_MODULE_LV,
            'X', UnificationEntry(circuit, Tier.LV),
            'W', UnificationEntry(cableGtSingle, Tin))

        // Catalytic Reformer
        ModHandler.addShapedRecipe(true, "catalytic_reformer", CATALYTIC_REFORMER.stackForm,
            "MCM", "PHP", "MKM",
            'M', UnificationEntry(pipeNormalFluid, StainlessSteel),
            'C', UnificationEntry(circuit, Tier.HV),
            'P', ELECTRIC_PUMP_HV,
            'H', HULL[HV].stackForm,
            'K', UnificationEntry(cableGtDouble, Gold))

        // Large Forge Hammer
        ModHandler.addShapedRecipe(true, "large_forge_hammer", LARGE_FORGE_HAMMER.stackForm,
            "PXP", "FHC", "PXP",
            'C', COMPRESSOR[LV].stackForm,
            'F', FORGE_HAMMER[LV].stackForm,
            'H', HULL[LV].stackForm,
            'P', ELECTRIC_PISTON_LV,
            'X', UnificationEntry(circuit, Tier.MV))

        // Large Bender
        ModHandler.addShapedRecipe(true, "large_bender", LARGE_BENDER.stackForm,
            "GXG", "BHF", "PWP",
            'B', BENDER[EV].stackForm,
            'F', FORMING_PRESS[EV].stackForm,
            'G', UnificationEntry(gear, Titanium),
            'H', HULL[EV].stackForm,
            'P', UnificationEntry(plate, Titanium),
            'W', UnificationEntry(cableGtSingle, Aluminium),
            'X', UnificationEntry(circuit, Tier.EV))

        // Large Cutter
        ModHandler.addShapedRecipe(true, "large_cutter", LARGE_CUTTER.stackForm,
            "WGW", "CHL", "WXW",
            'C', CUTTER[EV].stackForm,
            'G', UnificationEntry(gear, MaragingSteel250),
            'H', HULL[EV].stackForm,
            'L', LATHE[EV].stackForm,
            'W', UnificationEntry(cableGtSingle, Aluminium),
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Extruder
        ModHandler.addShapedRecipe(true, "large_extruder", LARGE_EXTRUDER.stackForm,
            "AXA", "PEP", "AXA",
            'A', UnificationEntry(plate, Inconel625),
            'E', EXTRUDER[IV].stackForm,
            'P', ELECTRIC_PISTON_IV,
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Wiremill
        ModHandler.addShapedRecipe(true, "large_wiremill", LARGE_WIREMILL.stackForm,
            "MGM", "XWX", "MCM",
            'C', UnificationEntry(cableGtSingle, Platinum),
            'G', UnificationEntry(gear, BlueSteel),
            'M', ELECTRIC_MOTOR_IV,
            'W', WIREMILL[IV].stackForm,
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Mixer
        ModHandler.addShapedRecipe(true, "large_mixer", LARGE_MIXER.stackForm,
            "PGP", "MHM", "WXW",
            'G', UnificationEntry(gear, Staballoy),
            'H', HULL[EV].stackForm,
            'M', MIXER[EV].stackForm,
            'P', UnificationEntry(plate, Staballoy),
            'W', UnificationEntry(cableGtSingle, Aluminium),
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Extractor
        ModHandler.addShapedRecipe(true, "large_extractor", LARGE_EXTRACTOR.stackForm,
            "GXG", "PHI", "GEG",
            'E', EXTRACTOR[IV].stackForm,
            'G', UnificationEntry(gear, Talonite),
            'H', HULL[IV].stackForm,
            'I', ELECTRIC_PUMP_IV,
            'P', ELECTRIC_PISTON_IV,
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Assembler
        ModHandler.addShapedRecipe(true, "large_assembler", LARGE_ASSEMBLER.stackForm,
            "EXS", "RAR", "CXC",
            'A', ASSEMBLER[IV].stackForm,
            'C', CONVEYOR_MODULE_IV,
            'E', EMITTER_IV,
            'R', ROBOT_ARM_IV,
            'S', SENSOR_IV,
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Laser Engraver
        ModHandler.addShapedRecipe(true, "large_laser_engraver", LARGE_LASER_ENGRAVER.stackForm,
            "EXE", "LHL", "WXW",
            'E', EMITTER_IV,
            'H', HULL[IV].stackForm,
            'L', LASER_ENGRAVER[IV].stackForm,
            'W', UnificationEntry(cableGtSingle, Platinum),
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Fluid Solidifier
        ModHandler.addShapedRecipe(true, "large_fluid_solidifier", LARGE_FLUID_SOLIDIFIER.stackForm,
            "AXA", "PFP", "GXG",
            'A', UnificationEntry(plate, Steel),
            'F', FLUID_SOLIDIFIER[MV].stackForm,
            'G', UnificationEntry(gear, Steel),
            'P', ELECTRIC_PUMP_MV,
            'X', UnificationEntry(circuit, Tier.MV))

        // Large Brewery
        ModHandler.addShapedRecipe(true, "large_brewery", LARGE_BREWERY.stackForm,
            "PXP", "BHF", "MWM",
            'B', BREWERY[EV].stackForm,
            'F', FERMENTER[EV].stackForm,
            'H', HULL[EV].stackForm,
            'M', ELECTRIC_MOTOR_EV,
            'P', ELECTRIC_PUMP_EV,
            'W', UnificationEntry(cableGtSingle, Aluminium),
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Autoclave
        ModHandler.addShapedRecipe(true, "large_autoclave", LARGE_AUTOCLAVE.stackForm,
            "GXG", "PAP", "WVW",
            'A', AUTOCLAVE[IV].stackForm,
            'G', UnificationEntry(gear, WatertightSteel),
            'P', ELECTRIC_PUMP_IV,
            'V', VACUUM_CHAMBER[IV]!!.stackForm,
            'W', UnificationEntry(cableGtSingle, Platinum),
            'X', UnificationEntry(circuit, Tier.IV))

        // Large Arc Furnace
        ModHandler.addShapedRecipe(true, "large_arc_furnace", LARGE_ARC_FURNACE.stackForm,
            "PXP", "FUA", "GWG",
            'P', UnificationEntry(plateDense, Invar),
            'G', UnificationEntry(plate, Graphite),
            'F', ARC_FURNACE[HV].stackForm,
            'A', ALLOY_SMELTER[HV].stackForm,
            'U', ELECTRIC_PUMP_HV,
            'W', UnificationEntry(cableGtSingle, Gold),
            'X', UnificationEntry(circuit, Tier.EV))

        // Large Macerator
        ModHandler.addShapedRecipe(true, "large_macerator", LARGE_MACERATOR.stackForm,
            "PXP", "CMC", "DWD",
            'C', ELECTRIC_MOTOR_EV,
            'D', UnificationEntry(plate, TungstenCarbide),
            'M', MACERATOR[EV].stackForm,
            'P', ELECTRIC_PISTON_EV,
            'W', UnificationEntry(cableGtSingle, Aluminium),
            'X', UnificationEntry(circuit, Tier.EV))

        // Large Centrifuge
        ModHandler.addShapedRecipe(true, "large_centrifuge", LARGE_CENTRIFUGE.stackForm,
            "DXD", "CHT", "WGW",
            'C', CENTRIFUGE[HV].stackForm,
            'T', THERMAL_CENTRIFUGE[HV].stackForm,
            'H', HULL[HV].stackForm,
            'G', UnificationEntry(gear, RedSteel),
            'W', UnificationEntry(cableGtSingle, Gold),
            'D', UnificationEntry(plateDense, Tumbaga),
            'X', UnificationEntry(circuit, Tier.HV))

        // Large Sifter
        ModHandler.addShapedRecipe(true, "large_sifter", LARGE_SIFTER.stackForm,
            "PXP", "WSW", "GXG",
            'S', SIFTER[HV].stackForm,
            'W', UnificationEntry(cableGtSingle, Gold),
            'P', UnificationEntry(plate, EglinSteel),
            'G', UnificationEntry(gear, EglinSteel),
            'X', UnificationEntry(circuit, Tier.HV))

        // Large Electrolyzer
        ModHandler.addShapedRecipe(true, "large_electrolyzer", LARGE_ELECTROLYZER.stackForm,
            "DXD", "PEP", "WHW",
            'E', ELECTROLYZER[HV].stackForm,
            'H', UnificationEntry(pipeHugeFluid, Potin),
            'W', UnificationEntry(cableGtSingle, Gold),
            'P', ELECTRIC_PUMP_HV,
            'X', UnificationEntry(circuit, Tier.HV),
            'D', UnificationEntry(plateDense, Potin))

        // Large Ore Washer
        ModHandler.addShapedRecipe(true, "large_ore_washer", LARGE_ORE_WASHER.stackForm,
            "GXG", "POP", "WRW",
            'O', ORE_WASHER[EV].stackForm,
            'P', ELECTRIC_PUMP_EV,
            'R', UnificationEntry(rotor, Talonite),
            'W', UnificationEntry(cableGtSingle, Aluminium),
            'X', UnificationEntry(circuit, Tier.EV),
            'G', GTGlassCasing.TEMPERED_GLASS.stack)

        // Large Electromagnet
        ModHandler.addShapedRecipe(true, "large_electromagnet", LARGE_ELECTROMAGNET.stackForm,
            "AGA", "PHE", "WMW",
            'P', POLARIZER[MV].stackForm,
            'E', ELECTROMAGNETIC_SEPARATOR[MV].stackForm,
            'H', HULL[MV].stackForm,
            'M', CONVEYOR_MODULE_MV,
            'W', UnificationEntry(cableGtSingle, Copper),
            'A', UnificationEntry(plate, BabbitAlloy),
            'G', UnificationEntry(gear, BabbitAlloy))

        // Large Distillery
        ModHandler.addShapedRecipe(true, "large_distillery", LARGE_DISTILLERY.stackForm,
            "PXP", "DRD", "WXW",
            'R', DISTILLERY[IV].stackForm,
            'D', DISTILLATION_TOWER.stackForm,
            'X', UnificationEntry(circuit, Tier.IV),
            'W', UnificationEntry(cableGtSingle, Platinum),
            'P', ELECTRIC_PUMP_IV)

        // Large Bio Reactor
        ModHandler.addShapedRecipe(true, "large_bio_reactor", LARGE_BIO_REACTOR.stackForm,
            "UGU", "XRX", "WPW",
            'R', BIO_REACTOR[IV]!!.stackForm,
            'X', UnificationEntry(circuit, Tier.IV),
            'P', UnificationEntry(pipeLargeFluid, TungstenSteel),
            'W', UnificationEntry(cableGtSingle, Platinum),
            'U', ELECTRIC_PUMP_IV,
            'G', UnificationEntry(gear, Grisium))

        // Large Packer
        ModHandler.addShapedRecipe(true, "large_packer", LARGE_PACKER.stackForm,
            "RCR", "PHP", "WXW",
            'P', PACKER[HV].stackForm,
            'H', HULL[HV].stackForm,
            'W', UnificationEntry(cableGtSingle, Gold),
            'X', UnificationEntry(circuit, Tier.HV),
            'C', CONVEYOR_MODULE_HV,
            'R', ROBOT_ARM_HV)

        // Large Gas Collector
        ModHandler.addShapedRecipe(true, "large_gas_collector", LARGE_GAS_COLLECTOR.stackForm,
            "SXS", "PGP", "WXW",
            'G', GAS_COLLECTOR[EV].stackForm,
            'P', ELECTRIC_PUMP_EV,
            'X', UnificationEntry(circuit, Tier.IV),
            'S', UnificationEntry(spring, Tungsten),
            'W', UnificationEntry(cableGtSingle, Aluminium))

        // Large Rock Breaker
        ModHandler.addShapedRecipe(true, "large_rock_breaker", LARGE_ROCK_BREAKER.stackForm,
            "PXP", "RHR", "WXW",
            'P', ELECTRIC_PISTON_HV,
            'R', ROCK_BREAKER[HV].stackForm,
            'H', HULL[HV].stackForm,
            'X', UnificationEntry(circuit, Tier.HV),
            'W', UnificationEntry(cableGtDouble, Electrum))

        // Large Burner Reactor
        ModHandler.addShapedRecipe(true, "large_burner_reactor", LARGE_BURNER_REACTOR.stackForm,
            "PUP", "BXR", "WDW",
            'B', BURNER_REACTOR[IV]!!.stackForm,
            'R', ROASTER[IV]!!.stackForm,
            'D', UnificationEntry(plateDense, EglinSteel),
            'P', ELECTRIC_PISTON_IV,
            'U', ELECTRIC_PUMP_IV,
            'X', UnificationEntry(circuit, Tier.IV),
            'W', UnificationEntry(cableGtSingle, Platinum))

        // Large Cryogenic Reactor
        ModHandler.addShapedRecipe(true, "large_cryogenic_reactor", LARGE_CRYOGENIC_REACTOR.stackForm,
            "UPU", "CXA", "WDW",
            'C', CRYOGENIC_REACTOR[IV]!!.stackForm,
            'A', BATH_CONDENSER[IV]!!.stackForm,
            'D', UnificationEntry(plateDense, StainlessSteel),
            'P', ELECTRIC_PISTON_IV,
            'U', ELECTRIC_PUMP_IV,
            'X', UnificationEntry(circuit, Tier.IV),
            'W', UnificationEntry(cableGtSingle, Platinum))

        // Electric Implosion Compressor
        ModHandler.addShapedRecipe(true, "electric_implosion_compressor", ELECTRIC_IMPLOSION_COMPRESSOR.stackForm,
            "DXD", "PCP", "WSW",
            'C', IMPLOSION_COMPRESSOR.stackForm,
            'D', UnificationEntry(plateDense, Osmium),
            'S', UnificationEntry(screw, Rhodium),
            'P', ELECTRIC_PISTON_IV,
            'X', UnificationEntry(circuit, Tier.LuV),
            'W', UnificationEntry(cableGtSingle, TungstenSteel))

        // Alloy Blast Smelter
        ModHandler.addShapedRecipe(true, "alloy_blast_smelter", ALLOY_BLAST_SMELTER.stackForm,
            "DBD", "XAX", "GWG",
            'A', ALLOY_SMELTER[IV].stackForm,
            'B', UnificationEntry(rotor, TantalumCarbide),
            'X', UnificationEntry(circuit, Tier.IV),
            'G', UnificationEntry(gear, TungstenCarbide),
            'D', UnificationEntry(plateDense, Staballoy),
            'W', UnificationEntry(cableGtSingle, Platinum))

        // Volcanus
        ModHandler.addShapedRecipe(true, "volcanus", VOLCANUS.stackForm,
            "GXG", "ECE", "WXW",
            'E', ELECTRIC_BLAST_FURNACE.stackForm,
            'C', MetalCasing.HASTELLOY_C276.stack,
            'W', UnificationEntry(cableGtSingle, Platinum),
            'X', UnificationEntry(circuit, Tier.IV),
            'G', UnificationEntry(gear, HastelloyN))

        // Cryogenic Freezer
        ModHandler.addShapedRecipe(true, "cryogenic_freezer", CRYOGENIC_FREEZER.stackForm,
            "GXG", "VBV", "WXW",
            'V', VACUUM_FREEZER.stackForm,
            'B', MetalCasing.HASTELLOY_X.stack,
            'W', UnificationEntry(cableGtSingle, Platinum),
            'X', UnificationEntry(circuit, Tier.IV),
            'G', UnificationEntry(gear, IncoloyMA956))

        // Chemical Plant
        ModHandler.addShapedRecipe(true, "chemical_plant", CHEMICAL_PLANT.stackForm,
            "PXP", "CFC", "PWP",
            'F', UnificationEntry(frameGt, Polybenzimidazole),
            'C', LARGE_CHEMICAL_REACTOR.stackForm,
            'W', UnificationEntry(cableGtSingle, Platinum),
            'X', UnificationEntry(circuit, Tier.LuV),
            'P', ELECTRIC_PUMP_IV)

        // Industrial Coke Oven
        ModHandler.addShapedRecipe(true, "industrial_coke_oven", INDUSTRIAL_COKE_OVEN.stackForm,
            "DXW", "PCQ", "WXS",
            'P', ELECTRIC_PISTON_HV,
            'Q', ELECTRIC_PUMP_HV,
            'X', UnificationEntry(circuit, Tier.HV),
            'C', PYROLYSE_OVEN.stackForm,
            'S', UnificationEntry(spring, MolybdenumDisilicide),
            'D', UnificationEntry(plateDouble, AluminiumBronze),
            'W', UnificationEntry(cableGtDouble, Silver))

        // Large Mass Fabricator
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(MASS_FABRICATOR[LuV], 16)
            .input(plateDouble, Californium, 8)
            .input(plateDouble, Tritanium, 8)
            .input(ENERGY_LAPOTRONIC_ORB, 4)
            .input(EMITTER_LuV, 16)
            .input(SENSOR_LuV, 16)
            .input(ELECTRIC_PUMP_LuV, 16)
            .input(gear, Berkelium, 3)
            .input(gearSmall, Einsteinium, 6)
            .input(foil, Plutonium244, 24)
            .input(wireGtSingle, IndiumTinBariumTitaniumCuprate, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(64000))
            .fluidInputs(VanadiumGallium.getFluid(L * 16))
            .fluidInputs(Thulium.getFluid(L * 4))
            .output(LARGE_MASS_FABRICATOR)
            .EUt(VA[ZPM])
            .duration(2 * MINUTE)
            .scannerResearch {
                it.researchStack(MASS_FABRICATOR[LuV]!!.stackForm)
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // Large Replicator
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(REPLICATOR[ZPM], 16)
            .input(plateDouble, Nobelium, 32)
            .input(plateDouble, Darmstadtium, 32)
            .input(TOOL_DATA_MODULE, 4)
            .input(EMITTER_ZPM, 16)
            .input(SENSOR_ZPM, 16)
            .input(FIELD_GENERATOR_ZPM, 16)
            .input(spring, Mendelevium, 12)
            .input(NANO_PIC_CHIP, 48)
            .input(wireGtSingle, UraniumRhodiumDinaquadide, 32)
            .input(bolt, Neptunium, 24)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(64000))
            .fluidInputs(YttriumBariumCuprate.getFluid(L * 16))
            .fluidInputs(Promethium.getFluid(L * 4))
            .output(LARGE_REPLICATOR)
            .EUt(VA[UV])
            .duration(2 * MINUTE)
            .stationResearch {
                it.researchStack(REPLICATOR[ZPM]!!.stackForm)
                    .EUt(VA[ZPM])
                    .CWUt(8)
            }
            .buildAndRegister()

        // Circuit Assembly Line
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(CIRCUIT_ASSEMBLER[LuV])
            .input(ROBOT_ARM_LuV, 4)
            .input(ELECTRIC_MOTOR_LuV, 4)
            .input(FIELD_GENERATOR_LuV, 1)
            .input(EMITTER_LuV, 1)
            .input(SENSOR_LuV, 1)
            .input(plate, RhodiumPlatedPalladium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CIRCUIT_ASSEMBLY_LINE)
            .EUt(VA[LuV])
            .duration(MINUTE)
            .scannerResearch {
                it.researchStack(CIRCUIT_ASSEMBLER[LuV].stackForm)
                    .EUt(VA[IV])
                    .duration(30 * SECOND)
            }
            .buildAndRegister()

        // Large Food Processor
        ModHandler.addShapedRecipe(true, "large_food_processor", LARGE_FOOD_PROCESSOR.stackForm,
            "RPS", "AHB", "WXW",
            'H', HULL[MV].stackForm,
            'A', FOOD_PROCESSOR[MV]!!.stackForm,
            'B', MULTICOOKER[MV]!!.stackForm,
            'R', ROBOT_ARM_MV.stackForm,
            'S', SENSOR_MV.stackForm,
            'P', ELECTRIC_PUMP_MV.stackForm,
            'W', UnificationEntry(cableGtSingle, Cupronickel),
            'X', UnificationEntry(circuit, Tier.MV))

        // CVD Unit
        ModHandler.addShapedRecipe(true, "cvd_unit", CVD_UNIT.stackForm,
            "PKP", "CHC", "ESE",
            'P', UnificationEntry(plate, BlueSteel),
            'K', UnificationEntry(cableGtSingle, Aluminium),
            'C', UnificationEntry(circuit, Tier.EV),
            'H', HULL[EV].stackForm,
            'S', SENSOR_EV.stackForm,
            'E', EMITTER_EV.stackForm)

        // Crystallization Crucible
        ModHandler.addShapedRecipe(true, "crystallization_crucible", CRYSTALLIZATION_CRUCIBLE.stackForm,
            "CMC", "LHL", "PCP",
            'C', UnificationEntry(circuit, Tier.IV),
            'M', UnificationEntry(plateDouble, MolybdenumDisilicide),
            'L', UnificationEntry(pipeNormalFluid, Titanium),
            'H', HULL[EV].stackForm,
            'P', UnificationEntry(plate, Titanium))

        // Nanoscale Fabricator
        ModHandler.addShapedRecipe(true, "nanoscale_fabricator", NANOSCALE_FABRICATOR.stackForm,
            "KSK", "EHE", "CFC",
            'K', UnificationEntry(cableGtSingle, Platinum),
            'S', SENSOR_IV.stackForm,
            'E', EMITTER_IV.stackForm,
            'H', HULL[IV].stackForm,
            'C', UnificationEntry(circuit, Tier.IV),
            'F', UnificationEntry(plate, TitaniumCarbide))

        // Sonicator
        ModHandler.addShapedRecipe(true, "sonicator", SONICATOR.stackForm,
            "LFL", "PHP", "CPC",
            'L', UnificationEntry(pipeLargeFluid, Naquadah),
            'F', FIELD_GENERATOR_LuV.stackForm,
            'P', ELECTRIC_PUMP_LuV.stackForm,
            'H', HULL[LuV].stackForm,
            'C', UnificationEntry(circuit, Tier.ZPM))

        // Laser-Induced CVD Unit
        ModHandler.addShapedRecipe(true, "laser_induced_cvd_unit", LASER_INDUCED_CVD_UNIT.stackForm,
            "EAE", "XHX", "PPP",
            'E', EMITTER_ZPM.stackForm,
            'A', UnificationEntry(plate, GSTGlass),
            'H', HULL[ZPM].stackForm,
            'X', UnificationEntry(circuit, Tier.UV),
            'P', UnificationEntry(plate, Darmstadtium))

        // Plasma-Enhanced CVD Unit
        ModHandler.addShapedRecipe(true, "plasma_enhanced_cvd_unit", PLASMA_ENHANCED_CVD_UNIT.stackForm,
            "PKP", "CHC", "ESE",
            'P', UnificationEntry(plate, Vibranium),
            'K', UnificationEntry(cableGtSingle, Europium),
            'C', UnificationEntry(circuit, Tier.UEV),
            'H', HULL[UHV].stackForm,
            'E', EMITTER_UHV,
            'S', SENSOR_UHV)

        // Bedrock Drilling Rig
        ModHandler.addShapedRecipe(true, "bedrock_drilling_rig", BEDROCK_DRILLING_RIG.stackForm,
            "PKP", "CHC", "MMM",
            'P', ELECTRIC_PISTON_UV.stackForm,
            'K', UnificationEntry(cableGtQuadruple, YttriumBariumCuprate),
            'C', UnificationEntry(circuit, Tier.UHV),
            'H', HULL[UV].stackForm,
            'M', ELECTRIC_MOTOR_UV.stackForm)

        // Fusion Reactor Computer MK4
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(FusionCoil.ADVANCED.stack)
            .input(circuit, Tier.UEV, 4)
            .input(plateDouble, MetastableFlerovium)
            .input(plateDouble, Dubnium)
            .input(FIELD_GENERATOR_UV, 2)
            .input(FEMTO_PIC_CHIP, 64)
            .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Europium.getFluid(L * 8))
            .output(FUSION_REACTOR_MK4)
            .EUt(VA[UV])
            .duration(2 * MINUTE)
            .stationResearch {
                it.researchStack(FUSION_REACTOR[2].stackForm)
                    .EUt(VA[UHV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Fusion Reactor Computer MK5
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(FusionCoil.ULTIMATE.stack)
            .input(circuit, Tier.UIV, 4)
            .input(plateDouble, MetastableHassium)
            .input(plateDouble, Meitnerium)
            .input(FIELD_GENERATOR_UHV, 2)
            .input(ATTO_PIC_CHIP, 64)
            .input(wireGtSingle, VibraniumTritaniumActiniumIronSuperhydride, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 8))
            .fluidInputs(Seaborgium.getFluid(L * 8))
            .output(FUSION_REACTOR_MK5)
            .EUt(VA[UHV])
            .duration(2 * MINUTE + 30 * SECOND)
            .stationResearch {
                it.researchStack(FUSION_REACTOR_MK4.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Advanced Fusion Reactor
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(FUSION_REACTOR[0], 32)
            .input(circuit, Tier.ZPM, 16)
            .input(plateDouble, Duranium, 8)
            .input(plateDouble, Europium, 8)
            .input(ELECTRIC_PUMP_LuV, 4)
            .input(FIELD_GENERATOR_LuV, 4)
            .input(VOLTAGE_COIL_LuV, 8)
            .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER, 16)
            .input(gear, LithiumTitanate, 4)
            .input(wireGtDouble, IndiumTinBariumTitaniumCuprate, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .fluidInputs(Trinaquadalloy.getFluid(L * 4))
            .fluidInputs(ReneN5.getFluid(L * 4))
            .output(ADVANCED_FUSION_REACTOR)
            .EUt(VA[LuV])
            .duration(2 * MINUTE)
            .scannerResearch {
                it.researchStack(OreDictUnifier.get(block, Duranium))
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // Component Assembly Line (CoAL)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ASSEMBLY_LINE, 4)
            .inputs(GTMultiblockCasing.ASSEMBLY_LINE_CASING.getStack(8))
            .inputs(GTMultiblockCasing.ASSEMBLY_CONTROL.getStack(16))
            .input(ROBOT_ARM_UV, 8)
            .input(CONVEYOR_MODULE_UV, 8)
            .input(plateDouble, EnrichedNaquadahAlloy, 6)
            .input(plateDouble, Taranium, 12)
            .input(gear, Bedrockium, 3)
            .input(gearSmall, Bedrockium, 6)
            .input(TOOL_CASTER[EV], 8)
            .input(foil, BariumStrontiumTitanate, 12)
            .input(foil, FranciumCaesiumCadmiumBromide, 12)
            .input(circuit, Tier.UHV, 4)
            .input(wireGtQuadruple, EnrichedNaquadahAlloy, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 12))
            .fluidInputs(Lubricant.getFluid(5000))
            .fluidInputs(Naquadria.getFluid(L * 8))
            .fluidInputs(Trinaquadalloy.getFluid(L * 4))
            .output(COMPONENT_ASSEMBLY_LINE)
            .EUt(VA[UV])
            .duration(5 * MINUTE)
            .stationResearch {
                it.researchStack(GTMultiblockCasing.ASSEMBLY_LINE_CASING.stack)
                    .EUt(VA[ZPM])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Cosmic Ray Detector (CRD)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(SCANNER[UHV], 4)
            .inputs(MultiblockCasing.REFLECTIVE_SURFACE_CASING.getStack(ConfigHolder.recipes.casingsPerCraft * 4))
            .input(EMITTER_UHV, 6)
            .input(SENSOR_UHV, 6)
            .input(plateDouble, TitanSteel, 8)
            .input(circuit, Tier.UEV, 2)
            .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 18))
            .fluidInputs(UUMatter.getFluid(16000))
            .fluidInputs(FreeElectronGas.getFluid(8000))
            .fluidInputs(Trinaquadalloy.getFluid(L * 8))
            .output(COSMIC_RAY_DETECTOR)
            .EUt(VA[UHV])
            .duration(2 * MINUTE + 30 * SECOND)
            .stationResearch {
                it.researchStack(SCANNER[UHV].stackForm)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // Stellar Forge
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(NANO_FORGE, 16)
            .inputs(MultiblockCasing.STELLAR_CONTAINMENT_CASING.getStack(16))
            .inputs(MultiblockCasing.THERMAL_ENERGY_TRANSMISSION_CASING.getStack(16))
            .input(ELECTRIC_PUMP_UHV, 8)
            .input(ROBOT_ARM_UHV, 8)
            .input(FIELD_GENERATOR_UHV, 8)
            .input(plateDouble, Tairitsium, 4)
            .input(circuit, Tier.UEV, 12)
            .input(foil, EnrichedNaquadahAlloy, 24)
            .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 36))
            .fluidInputs(UUMatter.getFluid(64000))
            .fluidInputs(TitanSteel.getFluid(L * 8))
            .fluidInputs(HDCS.getFluid(L * 4))
            .output(STELLAR_FORGE)
            .EUt(VA[UHV])
            .duration(2 * MINUTE + 30 * SECOND)
            .stationResearch {
                it.researchStack(VOLCANUS.stackForm)
                    .EUt(VA[UV])
                    .CWUt(36)
            }
            .buildAndRegister()

        // Nuclear Reactor
        ModHandler.addShapedRecipe(true, "nuclear_reactor", NUCLEAR_REACTOR.stackForm,
            "QBQ", "RHR", "PBP",
            'H', HULL[EV].stackForm,
            'R', ROBOT_ARM_EV,
            'Q', ELECTRIC_PUMP_EV,
            'P', UnificationEntry(plate, Zircaloy4),
            'B', UnificationEntry(block, Thorium))

        // Large Hot Coolant Turbine
        ModHandler.addShapedRecipe(true, "large_hot_coolant_turbine", HOT_COOLANT_TURBINE.stackForm,
            "XGX", "GHG", "PGP",
            'X', UnificationEntry(circuit, Tier.IV),
            'G', UnificationEntry(gear, Titanium),
            'H', HULL[EV].stackForm,
            'P', UnificationEntry(pipeLargeFluid, Titanium))

        // Large Supercritical Fluid Turbine
        ModHandler.addShapedRecipe(true, "large_supercritical_fluid_turbine", SUPERCRITICAL_FLUID_TURBINE.stackForm,
            "XGX", "GHG", "PGP",
            'X', UnificationEntry(circuit, Tier.ZPM),
            'G', UnificationEntry(gear, RhodiumPlatedPalladium),
            'H', HULL[LuV].stackForm,
            'P', UnificationEntry(pipeLargeFluid, RhodiumPlatedPalladium))

        // Large Rocket Engine
        ModHandler.addShapedRecipe(true, "large_rocket_engine", LARGE_ROCKET_ENGINE.stackForm,
            "SXS", "RHR", "WXW",
            'R', ROCKET_ENGINE[2].stackForm,
            'H', HULL[IV].stackForm,
            'S', UnificationEntry(spring, Nitinol60),
            'W', UnificationEntry(cableGtSingle, Platinum),
            'X', UnificationEntry(circuit, Tier.LuV))

        // Large Naquadah Reactor
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(NUCLEAR_REACTOR, 64)
            .input(NUCLEAR_REACTOR, 64)
            .input(NUCLEAR_REACTOR, 64)
            .input(NUCLEAR_REACTOR, 64)
            .input(NAQUADAH_REACTOR[3], 16)
            .input(ELECTRIC_PUMP_UV, 8)
            .input(ROBOT_ARM_UV, 8)
            .input(circuit, Tier.UHV, 16)
            .input(plateDense, Pikyonium64B, 6)
            .input(plateDense, Naquadria, 6)
            .input(screw, Trinaquadalloy, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 160))
            .fluidInputs(Bedrockium.getFluid(L * 40))
            .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 20))
            .fluidInputs(PreciousMetalAlloy.getFluid(L * 10))
            .output(LARGE_NAQUADAH_REACTOR)
            .EUt(VA[UHV])
            .duration(5 * MINUTE)
            .stationResearch {
                it.researchStack(NAQUADAH_REACTOR[3].stackForm)
                    .EUt(VA[UV])
                    .CWUt(16)
            }
            .buildAndRegister()

        // Nano Forge
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(VOLCANUS, 8)
            .input(nanite, Carbon, 16)
            .input(circuit, Tier.UV, 4)
            .input(ROBOT_ARM_ZPM, 8)
            .input(CONVEYOR_MODULE_ZPM, 8)
            .input(plateDense, Duranium, 6)
            .input(plateDense, Trinium, 6)
            .input(gear, Naquadria, 4)
            .input(gearSmall, EnrichedNaquadahAlloy, 12)
            .input(wireGtSingle, UraniumRhodiumDinaquadide, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 64))
            .fluidInputs(UUMatter.getFluid(64000))
            .fluidInputs(Osmiridium.getFluid(L * 16))
            .output(NANO_FORGE)
            .EUt(VA[ZPM])
            .duration(2 * MINUTE + 30 * SECOND)
            .stationResearch {
                it.researchStack(OreDictUnifier.get(nanite, Carbon))
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // PCB Factory
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, RhodiumPlatedPalladium, 4)
            .input(CIRCUIT_ASSEMBLER[LuV], 4)
            .input(plate, Osmiridium, 4)
            .input(circuit, Tier.LuV, 16)
            .input(gear, Ruridit, 2)
            .input(ROBOT_ARM_LuV, 8)
            .input(cableGtSingle, NiobiumTitanium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .output(PCB_FACTORY)
            .EUt(VA[LuV])
            .duration(1 * MINUTE)
            .scannerResearch {
                it.researchStack(WETWARE_CIRCUIT_BOARD.stackForm)
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // Quantum Force Transformer
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(LARGE_MASS_FABRICATOR)
            .inputs(MultiblockCasing.PARTICLE_EXCITATION_WIRE_COIL.stack)
            .input(circuit, Tier.UEV, 8)
            .input(ELECTRIC_PUMP_UEV, 4)
            .input(FIELD_GENERATOR_UEV, 4)
            .input(QUANTUM_ANOMALY)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 10))
            .fluidInputs(Pikyonium64B.getFluid(L * 32))
            .output(QUANTUM_FORCE_TRANSFORMER)
            .EUt(VA[UEV])
            .duration(1 * MINUTE)
            .stationResearch {
                it.researchStack(MultiblockCasing.PARTICLE_EXCITATION_WIRE_COIL.stack)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Antimatter Forge
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(FUSION_REACTOR_MK5)
            .input(frameGt, Infinity, 16)
            .inputs(Manipulator.COSMIC_FABRIC.getStack(16))
            .inputs(MultiblockCasing.STELLAR_CONTAINMENT_CASING.getStack(16))
            .input(wireFine, Hypogen, 64)
            .input(wireFine, Infinity, 64)
            .input(circuit, Tier.UIV, 16)
            .input(ELECTRIC_PUMP_UEV, 16)
            .input(plateDense, CosmicNeutronium, 4)
            .input(plateDense, MetastableHassium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
            .fluidInputs(Bedrockium.getFluid(L * 64))
            .fluidInputs(Protomatter.getFluid(16000))
            .output(ANTIMATTER_FORGE)
            .EUt(VA[UEV])
            .duration(10 * MINUTE)
            .stationResearch {
                it.researchStack(ADVANCED_FUSION_REACTOR.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Antimatter Generator
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(LARGE_NAQUADAH_REACTOR)
            .input(LASER_OUTPUT_HATCH_1048576[UEV - IV], 4)
            .inputs(MultiblockCasing.ANTIMATTER_ANNIHILATION_MATRIX.getStack(16))
            .input(circuit, Tier.UIV, 16)
            .input(EMITTER_UEV, 32)
            .input(plateDense, Livermorium, 4)
            .input(rotor, Infinity, 16)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 64)
            .input(wireFine, Hypogen, 64)
            .input(wireFine, Rhugnor, 64)
            .input(wireFine, CosmicNeutronium, 64)
            .fluidInputs(HalkoniteSteel.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(64000))
            .fluidInputs(ResonantStrangeMeson.getFluid(8000))
            .fluidInputs(Antimatter.getFluid(16000))
            .output(ANTIMATTER_GENERATOR)
            .EUt(VA[UEV])
            .duration(10 * MINUTE)
            .stationResearch {
                it.researchStack(LARGE_NAQUADAH_REACTOR.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // Space Elevator
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.getStack(8))
            .input(frameGt, Neutronium, 16)
            .input(FIELD_GENERATOR_UV, 4)
            .input(circuit, Tier.UHV, 8)
            .input(circuit, Tier.UV, 16)
            .input(plateDouble, Vibranium, 32)
            .input(plateDouble, Bedrockium, 32)
            .input(PICO_PIC_CHIP, 64)
            .input(ELECTRIC_MOTOR_UV, 8)
            .input(screw, Adamantium, 24)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(16000))
            .fluidInputs(Lubricant.getFluid(32000))
            .fluidInputs(Iridium.getFluid(L * 10))
            .output(SPACE_ELEVATOR)
            .EUt(VA[UHV])
            .duration(2 * MINUTE)
            .stationResearch {
                it.researchStack(MINING_DRONE_AIRPORT.stackForm)
                    .EUt(VA[UV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Space Assembler Module MK1
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ASSEMBLER[UHV], 4)
            .input(CIRCUIT_ASSEMBLER[UHV], 4)
            .input(gear, CosmicNeutronium, 8)
            .input(gearSmall, CosmicNeutronium, 16)
            .input(ROBOT_ARM_UHV, 8)
            .input(CONVEYOR_MODULE_UHV, 16)
            .input(circuit, Tier.UHV, 8)
            .input(circuit, Tier.UV, 16)
            .input(frameGt, Neutronium, 8)
            .input(screw, Neutronium, 32)
            .fluidInputs(SolderingAlloy.getFluid(L * 9))
            .fluidInputs(Naquadria.getFluid(L * 9))
            .fluidInputs(Lubricant.getFluid(16000))
            .output(SPACE_ASSEMBLER_MK1)
            .EUt(VA[UHV])
            .duration(1 * MINUTE)
            .stationResearch {
                it.researchStack(ASSEMBLER[UHV].stackForm)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Space Assembler Module MK2
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ASSEMBLER[UEV], 4)
            .input(CIRCUIT_ASSEMBLER[UEV], 4)
            .input(gear, Infinity, 8)
            .input(gearSmall, Infinity, 16)
            .input(ROBOT_ARM_UEV, 8)
            .input(CONVEYOR_MODULE_UEV, 16)
            .input(circuit, Tier.UEV, 8)
            .input(circuit, Tier.UHV, 16)
            .input(frameGt, Vibranium, 8)
            .input(screw, Vibranium, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 9))
            .fluidInputs(Taranium.getFluid(L * 9))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .output(SPACE_ASSEMBLER_MK2)
            .EUt(VA[UEV])
            .duration(2 * MINUTE)
            .stationResearch {
                it.researchStack(SPACE_ASSEMBLER_MK1.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Space Assembler Module MK3
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ASSEMBLER[UIV], 4)
            .input(CIRCUIT_ASSEMBLER[UIV], 4)
            .input(gear, SpaceTime, 8)
            .input(gearSmall, SpaceTime, 16)
            .input(ROBOT_ARM_UIV, 8)
            .input(CONVEYOR_MODULE_UIV, 16)
            .input(circuit, Tier.UIV, 8)
            .input(circuit, Tier.UEV, 16)
            .input(frameGt, Shirabon, 8)
            .input(screw, Shirabon, 32)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 9))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 9))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .output(SPACE_ASSEMBLER_MK3)
            .EUt(VA[UIV])
            .duration(5 * MINUTE)
            .stationResearch {
                it.researchStack(SPACE_ASSEMBLER_MK2.stackForm)
                    .EUt(VA[UIV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Space Pump Module MK1
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ADVANCED_FLUID_DRILLING_RIG)
            .input(COVER_ENDER_FLUID_LINK, 2)
            .input(frameGt, Neutronium, 4)
            .input(circuit, Tier.UHV, 4)
            .input(ELECTRIC_PUMP_UV, 2)
            .input(gear, Neutronium, 4)
            .input(screw, Bedrockium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 9))
            .fluidInputs(Adamantium.getFluid(L * 4))
            .output(SPACE_PUMP_MK1)
            .EUt(VA[UV])
            .duration(1 * MINUTE)
            .stationResearch {
                it.researchStack(ADVANCED_FLUID_DRILLING_RIG.stackForm)
                    .EUt(VA[UV])
                    .CWUt(16)
            }
            .buildAndRegister()

        // Space Pump Module MK2
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(SPACE_PUMP_MK1, 4)
            .input(frameGt, Vibranium, 8)
            .input(circuit, Tier.UEV, 16)
            .input(ELECTRIC_PUMP_UHV, 8)
            .input(gear, Vibranium, 8)
            .input(screw, Taranium, 64)
            .input(plateDouble, CosmicNeutronium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 32))
            .output(SPACE_PUMP_MK2)
            .EUt(VA[UHV])
            .duration(2 * MINUTE)
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ADVANCED_FLUID_DRILLING_RIG, 4)
            .input(COVER_ENDER_FLUID_LINK, 8)
            .input(frameGt, Vibranium, 4)
            .input(circuit, Tier.UEV, 16)
            .input(ELECTRIC_PUMP_UHV, 8)
            .input(gear, Vibranium, 4)
            .input(screw, Taranium, 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 32))
            .fluidInputs(CosmicNeutronium.getFluid(L * 16))
            .output(SPACE_PUMP_MK2)
            .EUt(VA[UHV])
            .duration(2 * MINUTE)
            .stationResearch {
                it.researchStack(SPACE_PUMP_MK1.stackForm)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // Space Pump Module MK3
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(SPACE_PUMP_MK2, 4)
            .input(frameGt, Infinity, 8)
            .input(circuit, Tier.UIV, 16)
            .input(ELECTRIC_PUMP_UEV, 8)
            .input(gear, Infinity, 8)
            .input(screw, Magnetium, 64)
            .input(plateDouble, Rhugnor, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .output(SPACE_PUMP_MK3)
            .EUt(VA[UEV])
            .duration(5 * MINUTE)
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ADVANCED_FLUID_DRILLING_RIG, 16)
            .input(COVER_ENDER_FLUID_LINK, 32)
            .input(frameGt, Infinity, 4)
            .input(circuit, Tier.UIV, 16)
            .input(ELECTRIC_PUMP_UEV, 8)
            .input(gear, Infinity, 4)
            .input(screw, Magnetium, 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(Rhugnor.getFluid(L * 32))
            .output(SPACE_PUMP_MK3)
            .EUt(VA[UEV])
            .duration(5 * MINUTE)
            .stationResearch {
                it.researchStack(SPACE_PUMP_MK2.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Energy Infuser
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ACTIVE_TRANSFORMER)
            .inputs(ScienceCasing.MOLECULAR_COIL.getStack(8))
            .inputs(GTComputerCasing.HIGH_POWER_CASING.getStack(8))
            .input(screw, NeodymiumMagnetic, 16)
            .fluidInputs(Electrum.getFluid(L * 18))
            .fluidInputs(Europium.getFluid(L * 13))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .output(ENERGY_INFUSER)
            .EUt(100_000) // ZPM
            .duration(5 * MINUTE)
            .stationResearch {
                it.researchStack(CHARGER[ZPM].stackForm)
                    .EUt(VA[ZPM])
                    .CWUt(12)
            }
            .buildAndRegister()

        // Entrodynamically Phase Changer
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ANTIMATTER_FORGE, 4)
            .input(STELLAR_FORGE, 16)
            .input(VOLCANUS, 64)
            .input(ULTIMATE_BATTERY, 2)
            .input(plateDense, SuperheavyAlloyA, 4)
            .input(plateDense, SuperheavyAlloyB, 4)
            .input(plateDense, Abyssalloy, 4)
            .input(plateDense, CinobiteA243, 4)
            .input(ELECTRIC_PUMP_UIV, 16)
            .input(ELECTRIC_PISTON_UIV, 16)
            .input(SUBSTATION_ENERGY_INPUT_HATCH[UIV], 8)
            .input(POWER_TRANSFORMER[UIV], 8)
            .input(wireGtHex, FullereneSuperconductor, 16)
            .input(VOLTAGE_COIL_UIV, 32)
            .input(circuit, Tier.UIV, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 100))
            .fluidInputs(CosmicNeutronium.getFluid(L * 400))
            .fluidInputs(HalkoniteSteel.getFluid(L * 80))
            .fluidInputs(Shirabon.getFluid(L * 40))
            .output(ENTRODYNAMICALLY_PHASE_CHANGER)
            .EUt(VA[UIV])
            .duration(5 * MINUTE)
            .stationResearch {
                it.researchStack(ANTIMATTER_GENERATOR.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Large Acid Generator
        ModHandler.addShapedRecipe(true, "large_acid_generator", LARGE_ACID_GENERATOR.stackForm,
            "SDS", "PAP", "WQW",
            'A', ACID_GENERATOR[MV].stackForm,
            'D', UnificationEntry(plateDense, CobaltBrass),
            'W', UnificationEntry(cableGtSingle, BlackSteel),
            'S', UnificationEntry(spring, BlackBronze),
            'P', ELECTRIC_PUMP_EV,
            'Q', UnificationEntry(pipeNormalFluid, Tungsten))

        // Large Transformer
        ModHandler.addShapedRecipe(true, "large_transformer", LARGE_TRANSFORMER.stackForm,
            "VXV", "VTV", "VXV",
            'T', POWER_TRANSFORMER[HV].stackForm,
            'V', VOLTAGE_COIL_HV,
            'X', UnificationEntry(circuit, Tier.HV))

        // Hydraulic Fracker
        ModHandler.addShapedRecipe(true, "hydraulic_fracker", HYDRAULIC_FRACKER.stackForm,
            "CLC", "GHG", "PPP",
            'C', UnificationEntry(circuit, Tier.EV),
            'L', UnificationEntry(pipeLargeFluid, Titanium),
            'G', UnificationEntry(gear, TantalumCarbide),
            'H', HULL[EV].stackForm,
            'P', ELECTRIC_PUMP_EV)

        // Plasma Arc Transmitter
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(ARC_FURNACE[UHV], 16)
            .input(ALLOY_SMELTER[UHV], 16)
            .inputs(WireCoil.ADAMANTIUM.getStack(32))
            .input(LARGE_ARC_FURNACE, 64)
            .input(plateDense, MetastableOganesson, 4)
            .input(plateDense, MetastableFlerovium, 4)
            .input(plateDense, Taranium, 4)
            .input(ELECTRIC_PUMP_UHV, 8)
            .input(CONVEYOR_MODULE_UHV, 8)
            .input(EMITTER_UHV, 8)
            .input(VOLTAGE_COIL_UHV, 32)
            .input(circuit, Tier.UEV, 16)
            .input(foil, ThalliumBariumCalciumCuprate, 48)
            .input(wireGtQuadruple, RutheniumTriniumAmericiumNeutronate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(Bedrockium.getFluid(L * 16))
            .fluidInputs(Oxygen.getPlasma(64000))
            .fluidInputs(Nitrogen.getPlasma(64000))
            .output(PLASMA_ARC_TRANSMITTER)
            .EUt(VA[UHV])
            .duration(5 * MINUTE)
            .stationResearch {
                it.researchStack(ARC_FURNACE[UHV])
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

    }

    // @formatter:on

}
