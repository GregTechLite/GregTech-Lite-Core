package magicbook.gtlitecore.loader.recipe

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
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.items.OreDictNames
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Brass
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.GlycerylTrinitrate
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.MercuryBariumCalciumCuprate
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Palladium
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
import gregtech.api.unification.material.Materials.TinAlloy
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
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.material.Materials.Zircaloy4
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.pipeHugeItem
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeLargeItem
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalItem
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.pipeSmallItem
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockCleanroomCasing
import gregtech.common.blocks.BlockFireboxCasing
import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.BlockSteamCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems
import gregtech.common.items.MetaItems.COMPONENT_GRINDER_TUNGSTEN
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_EV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_HV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LuV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_MV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UEV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UIV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_EV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_EV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_HV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_EV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_HV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_MV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
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
import gregtech.common.items.MetaItems.NEUTRON_REFLECTOR
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
import gregtech.common.items.MetaItems.SMART_FILTER
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER
import gregtech.common.items.MetaItems.VOLTAGE_COIL_LuV
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import gregtech.common.metatileentities.MetaTileEntities.ALLOY_SMELTER
import gregtech.common.metatileentities.MetaTileEntities.ARC_FURNACE
import gregtech.common.metatileentities.MetaTileEntities.ASSEMBLER
import gregtech.common.metatileentities.MetaTileEntities.ASSEMBLY_LINE
import gregtech.common.metatileentities.MetaTileEntities.AUTOCLAVE
import gregtech.common.metatileentities.MetaTileEntities.BENDER
import gregtech.common.metatileentities.MetaTileEntities.BREWERY
import gregtech.common.metatileentities.MetaTileEntities.CENTRIFUGE
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
import gregtech.common.metatileentities.MetaTileEntities.PRIMITIVE_BLAST_FURNACE
import gregtech.common.metatileentities.MetaTileEntities.PYROLYSE_OVEN
import gregtech.common.metatileentities.MetaTileEntities.ROCK_BREAKER
import gregtech.common.metatileentities.MetaTileEntities.SCANNER
import gregtech.common.metatileentities.MetaTileEntities.SIFTER
import gregtech.common.metatileentities.MetaTileEntities.STEAM_ALLOY_SMELTER_BRONZE
import gregtech.common.metatileentities.MetaTileEntities.STEAM_COMPRESSOR_BRONZE
import gregtech.common.metatileentities.MetaTileEntities.THERMAL_CENTRIFUGE
import gregtech.common.metatileentities.MetaTileEntities.VACUUM_FREEZER
import gregtech.common.metatileentities.MetaTileEntities.WIREMILL
import gregtech.loaders.recipe.CraftingComponent
import gregtech.loaders.recipe.MetaTileEntityLoader
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumBronze
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Antimatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BabbitAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumStrontiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CaesiumCeriumCobaltIndium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CyclotetramethyleneTetranitroamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimensionallyShiftedSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedNaquadahAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FranciumCaesiumCadmiumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FreeElectronGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GSTGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Grisium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HDCS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HadronicResonantGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyN
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX78
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyLeptonMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkDegenerateMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hexanitrohexaaxaisowurtzitane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hypogen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA956
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Inconel625
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MaragingSteel250
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MolybdenumDisilicide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nitinol60
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Octaazacubane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pikyonium64B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Plutonium244
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PreciousMetalAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Protomatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumchromodynamicallyConfinedMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ReneN5
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ResonantStrangeMeson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Rhugnor
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RubidiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SpaceTime
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Staballoy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StableBaryonicMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tairitsium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Talonite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumHafniumSeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitanSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumTungstenCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trinaquadalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tumbaga
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VibraniumTritaniumActiniumIronSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WatertightSteel
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.nanite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.NAQUADRIA_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.TARANIUM_CHARGE
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01
import magicbook.gtlitecore.common.block.blocks.BlockComputerCasing01
import magicbook.gtlitecore.common.block.blocks.BlockFusionCasing01
import magicbook.gtlitecore.common.block.blocks.BlockFusionCasing02
import magicbook.gtlitecore.common.block.blocks.BlockFusionCasing03
import magicbook.gtlitecore.common.block.blocks.BlockManipulator
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.block.blocks.BlockPrimitiveCasing
import magicbook.gtlitecore.common.block.blocks.BlockSpaceElevatorCasing
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ATTO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.FEMTO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_LV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.QUANTUM_ANOMALY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UHV
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ADVANCED_FUSION_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ALLOY_BLAST_SMELTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ANTIMATTER_FORGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ANTIMATTER_GENERATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BATH_CONDENSER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BEDROCK_DRILLING_RIG
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BIO_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BIO_SIMULATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BUFFER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BURNER_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CATALYTIC_REFORMER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHEMICAL_DEHYDRATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHEMICAL_PLANT
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CIRCUIT_ASSEMBLY_LINE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COAGULATION_TANK
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COMPONENT_ASSEMBLY_LINE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COSMIC_RAY_DETECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CRYOGENIC_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CRYSTALLIZATION_CRUCIBLE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CVD_UNIT
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ELECTRIC_IMPLOSION_COMPRESSOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.FOOD_PROCESSOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.FUSION_REACTOR_MK4
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.FUSION_REACTOR_MK5
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.GREENHOUSE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.HOT_COOLANT_TURBINE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.INDUSTRIAL_COKE_OVEN
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.INDUSTRIAL_PRIMITIVE_BLAST_FURNACE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.INVENTORY_BRIDGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.INVENTORY_EXTENDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.INVENTORY_TANK_BRIDGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.INVENTORY_TANK_EXTENDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LAMINATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ARC_FURNACE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ASSEMBLER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_AUTOCLAVE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_BENDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_BIO_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_BREWERY
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_BURNER_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_CENTRIFUGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_CRYOGENIC_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_CUTTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_DISTILLERY
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ELECTROLYZER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ELECTROMAGNET
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_EXTRACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_EXTRUDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_FLUID_SOLIDIFIER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_FOOD_PROCESSOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_FORGE_HAMMER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_GAS_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_LASER_ENGRAVER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_MACERATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_MASS_FABRICATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_MIXER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_NAQUADAH_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ORE_WASHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_PACKER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_REPLICATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ROCKET_ENGINE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ROCK_BREAKER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_SIFTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_WIREMILL
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_INDUCED_CVD_UNIT
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_OUTPUT_HATCH_1048576
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LOOM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.MASS_FABRICATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.MINING_DRONE_AIRPORT
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.MOB_EXTRACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.MULTICOOKER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NANOSCALE_FABRICATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NANO_FORGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NAQUADAH_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NUCLEAR_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.PCB_FACTORY
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.PLASMA_ENHANCED_CVD_UNIT
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.POLISHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.QUANTUM_FORCE_TRANSFORMER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.REPLICATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ROASTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ROCKET_ENGINE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SAP_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SLICER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SONICATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SPACE_ASSEMBLER_MK1
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SPACE_ASSEMBLER_MK2
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SPACE_ASSEMBLER_MK3
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SPACE_ELEVATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_ALLOY_SMELTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_COMPRESSOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_ENGINE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_ROASTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_SAP_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_VACUUM_CHAMBER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_VULCANIZING_PRESS
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STELLAR_FORGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SUPERCRITICAL_FLUID_TURBINE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TANK_BRIDGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TANK_EXTENDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TOOL_CASTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.UNIVERSAL_BRIDGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.UNIVERSAL_EXTENDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.VACUUM_CHAMBER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.VOLCANUS
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.VULCANIZING_PRESS
import magicbook.gtlitecore.loader.recipe.component.CraftingComponents
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class MachineRecipeLoader
{

    companion object
    {

        fun init()
        {

            // Polisher
            MetaTileEntityLoader.registerMachineRecipe(true, POLISHER,
                "GCf", "MHM", "WXW",
                'H', CraftingComponent.HULL,
                'M', CraftingComponent.MOTOR,
                'C', CraftingComponent.CONVEYOR,
                'G', CraftingComponents.GEAR,
                'X', CraftingComponent.CIRCUIT,
                'W', CraftingComponent.CABLE)

            // Slicer
            MetaTileEntityLoader.registerMachineRecipe(true, SLICER,
                "PCA", "SHC", "LOA",
                'H', CraftingComponent.HULL,
                'P', CraftingComponent.PISTON,
                'O', CraftingComponent.CONVEYOR,
                'S', CraftingComponent.SAWBLADE,
                'L', CraftingComponents.PLATE_DENSE,
                'A', CraftingComponent.CABLE,
                'C', CraftingComponent.CIRCUIT)

            // Tool Caster
            MetaTileEntityLoader.registerMachineRecipe(true, TOOL_CASTER,
                "QXW", "GHP", "WMX",
                'H', CraftingComponent.HULL,
                'M', CASTING_MOLD_EMPTY,
                'X', CraftingComponent.CIRCUIT,
                'G', CraftingComponent.GLASS,
                'P', CraftingComponent.PUMP,
                'Q', CraftingComponent.PISTON,
                'W', CraftingComponent.CABLE)

            // Loom
            MetaTileEntityLoader.registerMachineRecipe(true, LOOM,
                "xSM", "CSI", "WHW",
                'H', CraftingComponent.HULL,
                'W', CraftingComponent.CABLE,
                'S', CraftingComponents.STICK_LONG,
                'C', CraftingComponent.CONVEYOR,
                'M', CraftingComponent.MOTOR,
                'I', MetaItems.ITEM_FILTER.stackForm)

            // Laminator
            MetaTileEntityLoader.registerMachineRecipe(true, LAMINATOR,
                "XGW", "PCS", "WHX",
                'H', CraftingComponent.HULL,
                'X', CraftingComponent.CIRCUIT,
                'W', CraftingComponent.CABLE,
                'P', CraftingComponent.PUMP,
                'C', CraftingComponent.CONVEYOR,
                'S', CraftingComponents.STICK,
                'G', CraftingComponents.GEAR_SMALL)

            // Chemical Dehydrator
            MetaTileEntityLoader.registerMachineRecipe(true, CHEMICAL_DEHYDRATOR,
                "WCW", "SHS", "WCW",
                'W', CraftingComponent.CABLE,
                'C', CraftingComponent.CIRCUIT,
                'S', CraftingComponent.SPRING,
                'H', CraftingComponent.HULL)

            // Steam Vulcanizing Presses
            ModHandler.addShapedRecipe(true, "vulcanizing_press.bronze", STEAM_VULCANIZING_PRESS[0]!!.stackForm,
                "DSG", "QHQ", "PPP",
                'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL),
                'S', UnificationEntry(springSmall, Iron),
                'P', UnificationEntry(pipeNormalFluid, Bronze),
                'D', UnificationEntry(stick, Iron),
                'G', ItemStack(Blocks.GLASS),
                'Q', ItemStack(Blocks.PISTON))

            ModHandler.addShapedRecipe(true, "vulcanizing_press.steel", STEAM_VULCANIZING_PRESS[1]!!.stackForm,
                "SXS", "PVP", "QQQ",
                'V', STEAM_VULCANIZING_PRESS[0]!!.stackForm,
                'P', UnificationEntry(plate, Steel),
                'Q', UnificationEntry(pipeNormalFluid, TinAlloy),
                'S', UnificationEntry(springSmall, WroughtIron),
                'X', UnificationEntry(gearSmall, Steel))

            // Vulcanizing Press
            MetaTileEntityLoader.registerMachineRecipe(true, VULCANIZING_PRESS,
                "CSR", "PHQ", "WXW",
                'H', CraftingComponent.HULL,
                'X', CraftingComponent.CIRCUIT,
                'W', CraftingComponent.CABLE,
                'P', CraftingComponent.PISTON,
                'Q', CraftingComponent.PUMP,
                'S', CraftingComponents.SPRING_SMALL,
                'C', CraftingComponent.CONVEYOR,
                'R', CraftingComponent.PIPE_REACTOR)

            // Steam Vacuum Chamber
            ModHandler.addShapedRecipe(true, "vacuum_chamber.bronze", STEAM_VACUUM_CHAMBER[0]!!.stackForm,
                "SGS", "PHP", "QQQ",
                'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                'P', UnificationEntry(pipeNormalFluid, Bronze),
                'Q', UnificationEntry(plate, WroughtIron),
                'S', UnificationEntry(spring, Iron),
                'G', UnificationEntry(gearSmall, Bronze))

            ModHandler.addShapedRecipe(true, "vacuum_chamber.steel", STEAM_VACUUM_CHAMBER[1]!!.stackForm,
                "GGG", "SHS", "PPP",
                'H', STEAM_VACUUM_CHAMBER[0]!!.stackForm,
                'S', UnificationEntry(spring, TinAlloy),
                'P', UnificationEntry(plate, Steel),
                'G', ItemStack(Blocks.GLASS))

            // Vacuum Chamber
            MetaTileEntityLoader.registerMachineRecipe(true, VACUUM_CHAMBER,
                "GCG", "PHP", "GWG",
                'W', CraftingComponent.CABLE,
                'C', CraftingComponent.CIRCUIT,
                'P', CraftingComponent.PUMP,
                'G', CraftingComponent.GLASS,
                'H', CraftingComponent.HULL)

            // Steam Sap Collector
            ModHandler.addShapedRecipe(true, "sap_collector.bronze", STEAM_SAP_COLLECTOR[0]!!.stackForm,
                "SDX", "TRT", "WHW",
                'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
                'W', UnificationEntry(pipeTinyFluid, Bronze),
                'T', ItemStack(Blocks.GLASS),
                'R', UnificationEntry(rotor, WroughtIron),
                'S', UnificationEntry(springSmall, Iron),
                'D', OreDictUnifier.get(toolHeadDrill, WroughtIron),
                'X', UnificationEntry(gem, Diamond))

            ModHandler.addShapedRecipe(true, "sap_collector.steel", STEAM_SAP_COLLECTOR[1]!!.stackForm,
                "SQS", "GRG", "PHP",
                'H', STEAM_SAP_COLLECTOR[0]!!.stackForm,
                'P', UnificationEntry(plateDouble, WroughtIron),
                'R', UnificationEntry(rotor, TinAlloy),
                'G', ItemStack(Blocks.GLASS),
                'S', UnificationEntry(springSmall, WroughtIron),
                'Q', UnificationEntry(pipeNormalFluid, TinAlloy))

            // Sap Collector
            MetaTileEntityLoader.registerMachineRecipe(true, SAP_COLLECTOR,
                "SDX", "TRT", "WHW",
                'H', CraftingComponent.HULL,
                'W', CraftingComponent.CABLE,
                'T', CraftingComponent.PIPE_REACTOR,
                'R', CraftingComponent.ROTOR,
                'D', OreDictUnifier.get(toolHeadDrill, Steel),
                'S', CraftingComponents.SPRING_SMALL,
                'X', CraftingComponent.CIRCUIT)

            // Greenhouse
            MetaTileEntityLoader.registerMachineRecipe(true, GREENHOUSE,
                "GGG", "PHR", "WXW",
                'G', CraftingComponent.GLASS,
                'H', CraftingComponent.HULL,
                'P', CraftingComponent.PUMP,
                'R', CraftingComponent.ROBOT_ARM,
                'X', CraftingComponent.CIRCUIT,
                'W', CraftingComponent.CABLE)

            // Bio Reactor
            MetaTileEntityLoader.registerMachineRecipe(true, BIO_REACTOR,
                "PXX", "QHQ", "PMW",
                'H', CraftingComponent.HULL,
                'P', CraftingComponent.PUMP,
                'Q', CraftingComponent.PIPE_NORMAL,
                'X', CraftingComponent.CIRCUIT,
                'W', CraftingComponent.CABLE,
                'M', CraftingComponent.MOTOR)

            // Steam Roaster
            ModHandler.addShapedRecipe(true, "roaster.bronze", STEAM_ROASTER[0]!!.stackForm,
                "PRP", "PQP", "PHP",
                'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL),
                'P', UnificationEntry(pipeSmallFluid, Bronze),
                'Q', UnificationEntry(plate, Bronze),
                'R', UnificationEntry(rotor, Bronze))

            ModHandler.addShapedRecipe(true, "roaster.steel", STEAM_ROASTER[1]!!.stackForm,
                "AAA", "QHQ", "PPP",
                'H', STEAM_ROASTER[0]!!.stackForm,
                'P', UnificationEntry(pipeSmallFluid, TinAlloy),
                'Q', UnificationEntry(plate, Steel),
                'A', UnificationEntry(plate, WroughtIron))

            // Roaster
            MetaTileEntityLoader.registerMachineRecipe(true, ROASTER,
                "SRS", "CHC", "KMK",
                'K', CraftingComponent.CABLE,
                'S', CraftingComponent.SPRING,
                'C', CraftingComponent.CIRCUIT,
                'H', CraftingComponent.HULL,
                'R', CraftingComponent.ROTOR,
                'M', CraftingComponent.MOTOR)

            // Burner Reactor
            MetaTileEntityLoader.registerMachineRecipe(true, BURNER_REACTOR,
                "XMX", "RIR", "WHW",
                'H', CraftingComponent.HULL,
                'W', CraftingComponent.CABLE,
                'I', CraftingComponent.PIPE_LARGE,
                'R', CraftingComponent.ROTOR,
                'M', CraftingComponent.MOTOR,
                'X', CraftingComponent.CIRCUIT)

            // Bath Condenser
            MetaTileEntityLoader.registerMachineRecipe(true, BATH_CONDENSER,
                "GXG", "PHF", "GWG",
                'H', CraftingComponent.HULL,
                'G', CraftingComponent.GLASS,
                'P', CraftingComponent.PUMP,
                'W', CraftingComponent.CABLE,
                'X', CraftingComponent.CIRCUIT,
                'F', CraftingComponents.FLUID_REGULATOR)

            // Cryogenic Reactor
            MetaTileEntityLoader.registerMachineRecipe(true, CRYOGENIC_REACTOR,
                "DXP", "RHR", "WXD",
                'H', CraftingComponent.HULL,
                'R', CraftingComponent.PIPE_REACTOR,
                'D', CraftingComponent.DOUBLE_PLATE,
                'W', CraftingComponent.CABLE,
                'X', CraftingComponent.CIRCUIT,
                'P', CraftingComponent.PUMP)

            // Mass Fabricator
            MetaTileEntityLoader.registerMachineRecipe(true, MASS_FABRICATOR,
                "XFX", "WHW", "XFX",
                'X', CraftingComponent.BETTER_CIRCUIT,
                'F', CraftingComponent.FIELD_GENERATOR,
                'H', CraftingComponent.HULL,
                'W', CraftingComponent.CABLE_QUAD)

            // Replicator
            MetaTileEntityLoader.registerMachineRecipe(true, REPLICATOR,
                "EFE", "XHX", "EWE",
                'E', CraftingComponent.EMITTER,
                'F', CraftingComponent.FIELD_GENERATOR,
                'H', CraftingComponent.HULL,
                'X', CraftingComponent.BETTER_CIRCUIT,
                'W', CraftingComponent.CABLE_QUAD)

            // Food Processor
            MetaTileEntityLoader.registerMachineRecipe(true, FOOD_PROCESSOR,
                "AOC", "RHR", "AOC",
                'C', CraftingComponent.CIRCUIT,
                'A', CraftingComponent.CABLE,
                'R', CraftingComponent.ROBOT_ARM,
                'H', CraftingComponent.HULL,
                'O', CraftingComponent.CONVEYOR)

            // Multicooker
            MetaTileEntityLoader.registerMachineRecipe(true, MULTICOOKER,
                "CGC", "GHG", "WMW",
                'G', CraftingComponent.GLASS,
                'H', CraftingComponent.HULL,
                'C', CraftingComponent.CIRCUIT,
                'W', CraftingComponent.COIL_HEATING_DOUBLE,
                'M', CraftingComponent.MOTOR)

            // Mob Extractor
            MetaTileEntityLoader.registerMachineRecipe(true, MOB_EXTRACTOR,
                "BCE", "PME", "WCW",
                'M', CraftingComponent.HULL,
                'E', CraftingComponent.PISTON,
                'P', CraftingComponent.PUMP,
                'C', CraftingComponent.CIRCUIT,
                'W', CraftingComponent.CABLE,
                'B', CraftingComponent.SAWBLADE)

            // Bio Simulator
            MetaTileEntityLoader.registerMachineRecipe(true, BIO_SIMULATOR,
                "WAW", "OHO", "WAW",
                'A', CraftingComponent.SENSOR,
                'W', CraftingComponent.CABLE,
                'H', CraftingComponent.HULL,
                'O', CraftingComponent.BETTER_CIRCUIT)

            // Rocket Engine
            MetaTileEntityLoader.registerMachineRecipe(true, ROCKET_ENGINE,
                "PXP", "MHM", "DWD",
                'P', CraftingComponent.PISTON,
                'X', CraftingComponent.CIRCUIT,
                'M', CraftingComponent.MOTOR,
                'H', CraftingComponent.HULL,
                'D', CraftingComponent.DOUBLE_PLATE,
                'W', CraftingComponent.CABLE)

            // Naquadah Reactor
            MetaTileEntityLoader.registerMachineRecipe(true, NAQUADAH_REACTOR,
                "RCR", "FHF", "WCW",
                'R', CraftingComponent.STICK_RADIOACTIVE,
                'C', CraftingComponent.CIRCUIT,
                'F', CraftingComponent.FIELD_GENERATOR,
                'W', CraftingComponent.CABLE,
                'H', CraftingComponent.HULL)

            // =========================================================================================================

            // Coagulation Tank
            ModHandler.addShapedRecipe(true, "coagulation_tank", COAGULATION_TANK.stackForm,
                "PRP", "sQh", "PSP",
                'P', UnificationEntry(plate, TreatedWood),
                'Q', UnificationEntry(pipeLargeFluid, TreatedWood),
                'R', UnificationEntry(rotor, Steel),
                'S', UnificationEntry(screw, Steel))

            // Reinforced Treated Wood Wall
            ModHandler.addShapedRecipe(true, "reinforced_treated_wood_wall", GTLiteMetaBlocks.PRIMITIVE_CASING.getItemVariant(BlockPrimitiveCasing.PrimitiveCasingType.REINFORCED_TREATED_WOOD_WALL, 2),
                "PhP", "QFQ", "PwP",
                'P', UnificationEntry(plate, TreatedWood),
                'Q', UnificationEntry(plate, Steel),
                'F', UnificationEntry(frameGt, TreatedWood))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, TreatedWood, 4)
                .input(plate, Steel, 2)
                .input(frameGt, TreatedWood)
                .outputs(GTLiteMetaBlocks.PRIMITIVE_CASING.getItemVariant(BlockPrimitiveCasing.PrimitiveCasingType.REINFORCED_TREATED_WOOD_WALL, 2))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Steam Compressor
            ModHandler.addShapedRecipe(true, "steam_compressor", STEAM_COMPRESSOR.stackForm,
                "CPC", "GFG", "CPC",
                'C', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS),
                'P', OreDictNames.craftingPiston,
                'F', STEAM_COMPRESSOR_BRONZE.stackForm,
                'G', UnificationEntry(gear, Potin))

            // Steam Alloy Smelter
            ModHandler.addShapedRecipe(true, "steam_alloy_smelter", STEAM_ALLOY_SMELTER.stackForm,
                "PGP", "CFC", "PGP",
                'F', STEAM_ALLOY_SMELTER_BRONZE.stackForm,
                'G', UnificationEntry(gear, Brass),
                'P', UnificationEntry(pipeNormalFluid, Bronze),
                'C', MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS))

            // Steam Engine
            ModHandler.addShapedRecipe(true, "steam_engine", STEAM_ENGINE.stackForm,
                "FPF", "PCP", "SGS",
                'C', GTLiteMetaBlocks.METAL_CASING_02.getItemVariant(BlockMetalCasing02.MetalCasingType.BRASS),
                'S', UnificationEntry(gearSmall, Bronze),
                'G', UnificationEntry(gear, Steel),
                'F', UnificationEntry(pipeSmallFluid, Potin),
                'P', UnificationEntry(plate, Brass))

            // Industrial Primitive Blast Furnace
            ModHandler.addShapedRecipe(true, "industrial_primitive_blast_furnace", INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.stackForm,
                "FBF", "BAB", "FBF",
                'F', PRIMITIVE_BLAST_FURNACE.stackForm,
                'B', MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(BlockFireboxCasing.FireboxCasingType.STEEL_FIREBOX),
                'A', OreDictUnifier.get(frameGt, Steel))

            // Mining Drone Airport
            ModHandler.addShapedRecipe(true, "mining_drone_airport", MINING_DRONE_AIRPORT.stackForm,
                "RDE", "CHC", "XWX",
                'H', HULL[LV].stackForm,
                'D', MINING_DRONE_LV,
                'R', ROBOT_ARM_LV,
                'E', EMITTER_LV,
                'C', CONVEYOR_MODULE_LV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LV),
                'W', UnificationEntry(cableGtSingle, Tin))

            // Catalytic Reformer
            ModHandler.addShapedRecipe(true, "catalytic_reformer", CATALYTIC_REFORMER.stackForm,
                "MCM", "PHP", "MKM",
                'M', UnificationEntry(pipeNormalFluid, StainlessSteel),
                'C', UnificationEntry(circuit, MarkerMaterials.Tier.HV),
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
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.MV))

            // Large Bender
            ModHandler.addShapedRecipe(true, "large_bender", LARGE_BENDER.stackForm,
                "GXG", "BHF", "PWP",
                'B', BENDER[EV].stackForm,
                'F', FORMING_PRESS[EV].stackForm,
                'G', UnificationEntry(gear, Titanium),
                'H', HULL[EV].stackForm,
                'P', UnificationEntry(plate, Titanium),
                'W', UnificationEntry(cableGtSingle, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.EV))

            // Large Cutter
            ModHandler.addShapedRecipe(true, "large_cutter", LARGE_CUTTER.stackForm,
                "WGW", "CHL", "WXW",
                'C', CUTTER[EV].stackForm,
                'G', UnificationEntry(gear, MaragingSteel250),
                'H', HULL[EV].stackForm,
                'L', LATHE[EV].stackForm,
                'W', UnificationEntry(cableGtSingle, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Extruder
            ModHandler.addShapedRecipe(true, "large_extruder", LARGE_EXTRUDER.stackForm,
                "AXA", "PEP", "AXA",
                'A', UnificationEntry(plate, Inconel625),
                'E', EXTRUDER[IV].stackForm,
                'P', ELECTRIC_PISTON_IV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Wiremill
            ModHandler.addShapedRecipe(true, "large_wiremill", LARGE_WIREMILL.stackForm,
                "MGM", "XWX", "MCM",
                'C', UnificationEntry(cableGtSingle, Platinum),
                'G', UnificationEntry(gear, BlueSteel),
                'M', ELECTRIC_MOTOR_IV,
                'W', WIREMILL[IV].stackForm,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Mixer
            ModHandler.addShapedRecipe(true, "large_mixer", LARGE_MIXER.stackForm,
                "PGP", "MHM", "WXW",
                'G', UnificationEntry(gear, Staballoy),
                'H', HULL[EV].stackForm,
                'M', MIXER[EV].stackForm,
                'P', UnificationEntry(plate, Staballoy),
                'W', UnificationEntry(cableGtSingle, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Extractor
            ModHandler.addShapedRecipe(true, "large_extractor", LARGE_EXTRACTOR.stackForm,
                "GXG", "PHI", "GEG",
                'E', EXTRACTOR[IV].stackForm,
                'G', UnificationEntry(gear, Talonite),
                'H', HULL[IV].stackForm,
                'I', ELECTRIC_PUMP_IV,
                'P', ELECTRIC_PISTON_IV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Assembler
            ModHandler.addShapedRecipe(true, "large_assembler", LARGE_ASSEMBLER.stackForm,
                "EXS", "RAR", "CXC",
                'A', ASSEMBLER[IV].stackForm,
                'C', CONVEYOR_MODULE_IV,
                'E', EMITTER_IV,
                'R', ROBOT_ARM_IV,
                'S', SENSOR_IV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Laser Engraver
            ModHandler.addShapedRecipe(true, "large_laser_engraver", LARGE_LASER_ENGRAVER.stackForm,
                "EXE", "LHL", "WXW",
                'E', EMITTER_IV,
                'H',HULL[IV].stackForm,
                'L',LASER_ENGRAVER[IV].stackForm,
                'W', UnificationEntry(cableGtSingle, Platinum),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Fluid Solidifier
            ModHandler.addShapedRecipe(true, "large_fluid_solidifier", LARGE_FLUID_SOLIDIFIER.stackForm,
                "AXA", "PFP", "GXG",
                'A', UnificationEntry(plate, Steel),
                'F', FLUID_SOLIDIFIER[MV].stackForm,
                'G', UnificationEntry(gear, Steel),
                'P', ELECTRIC_PUMP_MV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.MV))

            // Large Brewery
            ModHandler.addShapedRecipe(true, "large_brewery", LARGE_BREWERY.stackForm,
                "PXP", "BHF", "MWM",
                'B', BREWERY[EV].stackForm,
                'F', FERMENTER[EV].stackForm,
                'H', HULL[EV].stackForm,
                'M', ELECTRIC_MOTOR_EV,
                'P', ELECTRIC_PUMP_EV,
                'W', UnificationEntry(cableGtSingle, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Autoclave
            ModHandler.addShapedRecipe(true, "large_autoclave", LARGE_AUTOCLAVE.stackForm,
                "GXG", "PAP", "WVW",
                'A', AUTOCLAVE[IV].stackForm,
                'G', UnificationEntry(gear, WatertightSteel),
                'P', ELECTRIC_PUMP_IV,
                'V', VACUUM_CHAMBER[IV]!!.stackForm,
                'W', UnificationEntry(cableGtSingle, Platinum),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV))

            // Large Arc Furnace
            ModHandler.addShapedRecipe(true, "large_arc_furnace", LARGE_ARC_FURNACE.stackForm,
                "PXP", "FUA", "GWG",
                'P', UnificationEntry(plateDense, Invar),
                'G', UnificationEntry(plate, Graphite),
                'F', ARC_FURNACE[HV].stackForm,
                'A', ALLOY_SMELTER[HV].stackForm,
                'U', ELECTRIC_PUMP_HV,
                'W', UnificationEntry(cableGtSingle, Gold),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.EV))

            // Large Macerator
            ModHandler.addShapedRecipe(true, "large_macerator", LARGE_MACERATOR.stackForm,
                "PXP", "CMC", "DWD",
                'C', ELECTRIC_MOTOR_EV,
                'D', UnificationEntry(plate, TungstenCarbide),
                'M', MACERATOR[EV].stackForm,
                'P', ELECTRIC_PISTON_EV,
                'W', UnificationEntry(cableGtSingle, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.EV))

            // Large Centrifuge
            ModHandler.addShapedRecipe(true, "large_centrifuge", LARGE_CENTRIFUGE.stackForm,
                "DXD", "CHT", "WGW",
                'C', CENTRIFUGE[HV].stackForm,
                'T', THERMAL_CENTRIFUGE[HV].stackForm,
                'H', HULL[HV].stackForm,
                'G', UnificationEntry(gear, RedSteel),
                'W', UnificationEntry(cableGtSingle, Gold),
                'D', UnificationEntry(plateDense, Tumbaga),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.HV))

            // Large Sifter
            ModHandler.addShapedRecipe(true, "large_sifter", LARGE_SIFTER.stackForm,
                "PXP", "WSW", "GXG",
                'S', SIFTER[HV].stackForm,
                'W', UnificationEntry(cableGtSingle, Gold),
                'P', UnificationEntry(plate, EglinSteel),
                'G', UnificationEntry(gear, EglinSteel),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.HV))

            // Large Electrolyzer
            ModHandler.addShapedRecipe(true, "large_electrolyzer", LARGE_ELECTROLYZER.stackForm,
                "DXD", "PEP", "WHW",
                'E', ELECTROLYZER[HV].stackForm,
                'H', UnificationEntry(pipeHugeFluid, Potin),
                'W', UnificationEntry(cableGtSingle, Gold),
                'P', ELECTRIC_PUMP_HV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.HV),
                'D', UnificationEntry(plateDense, Potin))

            // Large Ore Washer
            ModHandler.addShapedRecipe(true, "large_ore_washer", LARGE_ORE_WASHER.stackForm,
                "GXG", "POP", "WRW",
                'O', ORE_WASHER[EV].stackForm,
                'P', ELECTRIC_PUMP_EV,
                'R', UnificationEntry(rotor, Talonite),
                'W', UnificationEntry(cableGtSingle, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.EV),
                'G', MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))

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
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'W', UnificationEntry(cableGtSingle, Platinum),
                'P', ELECTRIC_PUMP_IV)

            // Large Bio Reactor
            ModHandler.addShapedRecipe(true, "large_bio_reactor", LARGE_BIO_REACTOR.stackForm,
                "UGU", "XRX", "WPW",
                'R', BIO_REACTOR[IV]!!.stackForm,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
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
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.HV),
                'C', CONVEYOR_MODULE_HV,
                'R', ROBOT_ARM_HV)

            // Large Gas Collector
            ModHandler.addShapedRecipe(true, "large_gas_collector", LARGE_GAS_COLLECTOR.stackForm,
                "SXS", "PGP", "WXW",
                'G', GAS_COLLECTOR[EV].stackForm,
                'P', ELECTRIC_PUMP_EV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'S', UnificationEntry(spring, Tungsten),
                'W', UnificationEntry(cableGtSingle, Aluminium))

            // Large Rock Breaker
            ModHandler.addShapedRecipe(true, "large_rock_breaker", LARGE_ROCK_BREAKER.stackForm,
                "PXP", "RHR", "WXW",
                'P', ELECTRIC_PISTON_HV,
                'R', ROCK_BREAKER[HV].stackForm,
                'H', HULL[HV].stackForm,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.HV),
                'W', UnificationEntry(cableGtDouble, Electrum))

            // Large Burner Reactor
            ModHandler.addShapedRecipe(true, "large_burner_reactor", LARGE_BURNER_REACTOR.stackForm,
                "PUP", "BXR", "WDW",
                'B', BURNER_REACTOR[IV]!!.stackForm,
                'R', ROASTER[IV]!!.stackForm,
                'D', UnificationEntry(plateDense, EglinSteel),
                'P', ELECTRIC_PISTON_IV,
                'U', ELECTRIC_PUMP_IV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'W', UnificationEntry(cableGtSingle, Platinum))

            // Large Cryogenic Reactor
            ModHandler.addShapedRecipe(true, "large_cryogenic_reactor", LARGE_CRYOGENIC_REACTOR.stackForm,
                "UPU", "CXA", "WDW",
                'C', CRYOGENIC_REACTOR[IV]!!.stackForm,
                'A', BATH_CONDENSER[IV]!!.stackForm,
                'D', UnificationEntry(plateDense, StainlessSteel),
                'P', ELECTRIC_PISTON_IV,
                'U', ELECTRIC_PUMP_IV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'W', UnificationEntry(cableGtSingle, Platinum))

            // Electric Implosion Compressor
            ModHandler.addShapedRecipe(true, "electric_implosion_compressor", ELECTRIC_IMPLOSION_COMPRESSOR.stackForm,
                "DXD", "PCP", "WSW",
                'C', IMPLOSION_COMPRESSOR.stackForm,
                'D', UnificationEntry(plateDense, Osmium),
                'S', UnificationEntry(screw, Rhodium),
                'P', ELECTRIC_PISTON_IV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LuV),
                'W', UnificationEntry(cableGtSingle, TungstenSteel))

            // Alloy Blast Smelter
            ModHandler.addShapedRecipe(true, "alloy_blast_smelter", ALLOY_BLAST_SMELTER.stackForm,
                "DBD", "XAX", "GWG",
                'A', ALLOY_SMELTER[IV].stackForm,
                'B', UnificationEntry(rotor, TantalumCarbide),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'G', UnificationEntry(gear, TungstenCarbide),
                'D', UnificationEntry(plateDense, Staballoy),
                'W', UnificationEntry(cableGtSingle, Platinum))

            // Heat Vent
            ModHandler.addShapedRecipe(true, "heat_vent", GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getItemVariant(
                BlockActiveUniqueCasing01.UniqueCasingType.HEAT_VENT, ConfigHolder.recipes.casingsPerCraft),
                "PDP", "RLR", "PDP",
                'P', UnificationEntry(plate, TantalumCarbide),
                'D', UnificationEntry(plateDouble, MolybdenumDisilicide),
                'R', UnificationEntry(rotor, Titanium),
                'L', UnificationEntry(stickLong, MolybdenumDisilicide))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, TantalumCarbide, 4)
                .input(plateDouble, MolybdenumDisilicide, 2)
                .input(rotor, Titanium, 2)
                .input(stickLong, MolybdenumDisilicide, 1)
                .outputs(GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getItemVariant(BlockActiveUniqueCasing01.UniqueCasingType.HEAT_VENT, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // EV Buffer
            ModHandler.addShapedRecipe(true, "buffer_ev", BUFFER[0]!!.stackForm,
                "HP ", "XC ", "   ",
                'H', HULL[EV].stackForm,
                'P', ELECTRIC_PUMP_EV,
                'C', CONVEYOR_MODULE_EV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LV))

            // IV Buffer
            ModHandler.addShapedRecipe(true, "buffer_iv", BUFFER[1]!!.stackForm,
                "HP ", "XC ", "   ",
                'H', HULL[IV].stackForm,
                'P', ELECTRIC_PUMP_IV,
                'C', CONVEYOR_MODULE_IV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LV))

            // Inventory Bridge
            ModHandler.addShapedRecipe(true, "inventory_bridge", INVENTORY_BRIDGE.stackForm,
                "hP ", " H ", " Pw",
                'H', HULL[LV].stackForm,
                'P', UnificationEntry(pipeNormalItem, Nickel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(HULL[LV])
                .input(pipeNormalItem, Nickel, 2)
                .output(INVENTORY_BRIDGE)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tank Bridge
            ModHandler.addShapedRecipe(true, "tank_bridge", TANK_BRIDGE.stackForm,
                "h  ", "PHP", "  w",
                'H', HULL[LV].stackForm,
                'P', UnificationEntry(pipeNormalFluid, Steel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(HULL[LV])
                .input(pipeNormalFluid, Steel, 2)
                .output(TANK_BRIDGE)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Inventory Tank Bridge
            ModHandler.addShapedRecipe(true, "inventory_tank_bridge", INVENTORY_TANK_BRIDGE.stackForm,
                "hP ", "QHQ", " Pw",
                'H', HULL[LV].stackForm,
                'P', UnificationEntry(pipeNormalItem, Nickel),
                'Q', UnificationEntry(pipeNormalFluid, Steel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(HULL[LV])
                .input(pipeNormalFluid, Steel, 2)
                .input(pipeNormalItem, Nickel, 2)
                .output(INVENTORY_TANK_BRIDGE)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Universal Bridge
            ModHandler.addShapedRecipe(true, "universal_bridge", UNIVERSAL_BRIDGE.stackForm,
                "SPR", "QHQ", "XPG",
                'H', HULL[MV].stackForm,
                'P', UnificationEntry(pipeNormalItem, Electrum),
                'Q', UnificationEntry(pipeNormalFluid, Aluminium),
                'S', UnificationEntry(spring, Aluminium),
                'R', UnificationEntry(rotor, Aluminium),
                'G', UnificationEntry(gear, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LV))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(HULL[MV])
                .input(circuit, MarkerMaterials.Tier.LV)
                .input(rotor, Aluminium)
                .input(gear, Aluminium)
                .input(spring, Aluminium)
                .input(pipeNormalFluid, Aluminium, 2)
                .input(pipeNormalItem, Electrum, 2)
                .output(UNIVERSAL_BRIDGE)
                .EUt(VH[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Inventory Extender
            ModHandler.addShapedRecipe(true, "inventory_extender", INVENTORY_EXTENDER.stackForm,
                " hP", " H ", "Pw ",
                'H', HULL[LV].stackForm,
                'P', UnificationEntry(pipeNormalItem, Nickel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(HULL[LV])
                .input(pipeNormalItem, Nickel, 2)
                .output(INVENTORY_EXTENDER)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tank Extender
            ModHandler.addShapedRecipe(true, "tank_extender", TANK_EXTENDER.stackForm,
                "Ph ", " H ", " wP",
                'H', HULL[LV].stackForm,
                'P', UnificationEntry(pipeNormalFluid, Steel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(HULL[LV])
                .input(pipeNormalFluid, Steel, 2)
                .output(TANK_EXTENDER)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Inventory Tank Extender
            ModHandler.addShapedRecipe(true, "inventory_tank_extender", INVENTORY_TANK_EXTENDER.stackForm,
                "PhQ", " H ", "QwP",
                'H', HULL[LV].stackForm,
                'P', UnificationEntry(pipeNormalFluid, Steel),
                'Q', UnificationEntry(pipeNormalItem, Nickel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(HULL[LV])
                .input(pipeNormalFluid, Steel, 2)
                .input(pipeNormalItem, Nickel, 2)
                .output(INVENTORY_TANK_EXTENDER)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Universal Extender
            ModHandler.addShapedRecipe(true, "universal_extender", UNIVERSAL_EXTENDER.stackForm,
                "PRQ", "XHG", "QSP",
                'H', HULL[MV].stackForm,
                'P', UnificationEntry(pipeNormalFluid, Aluminium),
                'Q', UnificationEntry(pipeNormalItem, Electrum),
                'R', UnificationEntry(rotor, Aluminium),
                'G', UnificationEntry(gear, Aluminium),
                'S', UnificationEntry(spring, Aluminium),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LV))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(HULL[MV])
                .input(circuit, MarkerMaterials.Tier.LV)
                .input(rotor, Aluminium)
                .input(gear, Aluminium)
                .input(spring, Aluminium)
                .input(pipeNormalFluid, Aluminium, 2)
                .input(pipeNormalItem, Electrum, 2)
                .output(UNIVERSAL_EXTENDER)
                .EUt(VH[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Volcanus
            ModHandler.addShapedRecipe(true, "volcanus", GTLiteMetaTileEntities.VOLCANUS.stackForm,
                "GXG", "ECE", "WXW",
                'E', ELECTRIC_BLAST_FURNACE.stackForm,
                'C', GTLiteMetaBlocks.METAL_CASING_02.getItemVariant(BlockMetalCasing02.MetalCasingType.HASTELLOY_C276),
                'W', UnificationEntry(cableGtSingle, Platinum),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'G', UnificationEntry(gear, HastelloyN))

            // Cryogenic Freezer
            ModHandler.addShapedRecipe(true, "cryogenic_freezer", GTLiteMetaTileEntities.CRYOGENIC_FREEZER.stackForm,
                "GXG", "VBV", "WXW",
                'V', VACUUM_FREEZER.stackForm,
                'B', GTLiteMetaBlocks.METAL_CASING_02.getItemVariant(BlockMetalCasing02.MetalCasingType.HASTELLOY_X),
                'W', UnificationEntry(cableGtSingle, Platinum),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'G', UnificationEntry(gear, IncoloyMA956))

            // Chemical Plant
            ModHandler.addShapedRecipe(true, "chemical_plant", CHEMICAL_PLANT.stackForm,
                "PXP", "CFC", "PWP",
                'F', UnificationEntry(frameGt, Polybenzimidazole),
                'C', LARGE_CHEMICAL_REACTOR.stackForm,
                'W', UnificationEntry(cableGtSingle, Platinum),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LuV),
                'P', ELECTRIC_PUMP_IV)

            // Industrial Coke Oven
            ModHandler.addShapedRecipe(true, "industrial_coke_oven", INDUSTRIAL_COKE_OVEN.stackForm,
                "DXW", "PCQ", "WXS",
                'P', ELECTRIC_PISTON_HV,
                'Q', ELECTRIC_PUMP_HV,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.HV),
                'C', PYROLYSE_OVEN.stackForm,
                'S', UnificationEntry(spring, MolybdenumDisilicide),
                'D', UnificationEntry(plateDouble, AluminiumBronze),
                'W', UnificationEntry(cableGtDouble, Silver))

            // Large Mass Fabricator
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MASS_FABRICATOR[LuV]!!, 16)
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
                .EUt(VA[ZPM].toLong())
                .duration(2 * MINUTE)
                .scannerResearch { r ->
                    r.researchStack(MASS_FABRICATOR[LuV]!!.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // Large Replicator
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(REPLICATOR[ZPM]!!, 16)
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
                .EUt(VA[UV].toLong())
                .duration(2 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(REPLICATOR[ZPM]!!.stackForm)
                        .EUt(VA[ZPM].toLong())
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
                .EUt(VA[LuV].toLong())
                .duration(MINUTE)
                .scannerResearch { r ->
                    r.researchStack(CIRCUIT_ASSEMBLER[LuV].stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(30 * SECOND) }
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
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.MV))


            // Substrate Casing
            ModHandler.addShapedRecipe(true, "substrate_casing", GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.SUBSTRATE_CASING, ConfigHolder.recipes.casingsPerCraft),
                "PPP", "RFR", "R R",
                'P', UnificationEntry(plate, Palladium),
                'R', UnificationEntry(stick, RedSteel),
                'F', UnificationEntry(frameGt, BlueSteel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(frameGt, BlueSteel)
                .input(plate, Palladium, 3)
                .input(stick, RedSteel, 4)
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.SUBSTRATE_CASING, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Advanced Substrate Casing
            ModHandler.addShapedRecipe(true, "advanced_substrate_casing", GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.ADVANCED_SUBSTRATE_CASING, ConfigHolder.recipes.casingsPerCraft),
                "PPP", "RFR", "R R",
                'P', UnificationEntry(plate, Ruridit),
                'R', UnificationEntry(stick, Duranium),
                'F', UnificationEntry(frameGt, NaquadahAlloy));

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(frameGt, NaquadahAlloy)
                .input(plate, Ruridit, 3)
                .input(stick, Duranium, 4)
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.ADVANCED_SUBSTRATE_CASING, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // CVD Unit
            ModHandler.addShapedRecipe(true, "cvd_unit", CVD_UNIT.stackForm,
                "PKP", "CHC", "ESE",
                'P', UnificationEntry(plate, BlueSteel),
                'K', UnificationEntry(cableGtSingle, Aluminium),
                'C', UnificationEntry(circuit, MarkerMaterials.Tier.EV),
                'H', HULL[EV].stackForm,
                'S', SENSOR_EV.stackForm,
                'E', EMITTER_EV.stackForm)

            // Crystallization Crucible
            ModHandler.addShapedRecipe(true, "crystallization_crucible", CRYSTALLIZATION_CRUCIBLE.stackForm,
                "CMC", "LHL", "PCP",
                'C', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
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
                'C', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'F', UnificationEntry(plate, TitaniumCarbide))

            // Sonicator
            ModHandler.addShapedRecipe(true, "sonicator", SONICATOR.stackForm,
                "LFL", "PHP", "CPC",
                'L', UnificationEntry(pipeLargeFluid, Naquadah),
                'F', FIELD_GENERATOR_LuV.stackForm,
                'P', ELECTRIC_PUMP_LuV.stackForm,
                'H', HULL[LuV].stackForm,
                'C', UnificationEntry(circuit, MarkerMaterials.Tier.ZPM))

            // Laser-Induced CVD Unit
            ModHandler.addShapedRecipe(true, "laser_induced_cvd_unit", LASER_INDUCED_CVD_UNIT.stackForm,
                "EAE", "XHX", "PPP",
                'E', EMITTER_ZPM.stackForm,
                'A', UnificationEntry(plate, GSTGlass),
                'H', HULL[ZPM].stackForm,
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.UV),
                'P', UnificationEntry(plate, Darmstadtium))

            // Plasma-Enhanced CVD Unit
            ModHandler.addShapedRecipe(true, "plasma_enhanced_cvd_unit", PLASMA_ENHANCED_CVD_UNIT.stackForm,
                "PKP", "CHC", "ESE",
                'P', UnificationEntry(plate, Vibranium),
                'K', UnificationEntry(cableGtSingle, Europium),
                'C', UnificationEntry(circuit, MarkerMaterials.Tier.UEV),
                'H', HULL[UHV].stackForm,
                'E', EMITTER_UHV,
                'S', SENSOR_UHV)

            // Bedrock Drilling Rig
            ModHandler.addShapedRecipe(true, "bedrock_drilling_rig", BEDROCK_DRILLING_RIG.stackForm,
                "PKP", "CHC", "MMM",
                'P', ELECTRIC_PISTON_UV.stackForm,
                'K', UnificationEntry(cableGtQuadruple, YttriumBariumCuprate),
                'C', UnificationEntry(circuit, MarkerMaterials.Tier.UHV),
                'H', HULL[UV].stackForm,
                'M', ELECTRIC_MOTOR_UV.stackForm)

            // Drill Head
            ModHandler.addShapedRecipe(true, "drill_head", GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.DRILL_HEAD),
                "PGP", "MHM", "SSS",
                'P', ELECTRIC_PISTON_UV.stackForm,
                'G', UnificationEntry(gear, Tritanium),
                'M', ELECTRIC_MOTOR_UV.stackForm,
                'H', HULL[UV].stackForm,
                'S', COMPONENT_GRINDER_TUNGSTEN.stackForm)

            // Fusion Reactor Computer MK4
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.ADVANCED_FUSION_COIL))
                .input(circuit, MarkerMaterials.Tier.UEV, 4)
                .input(plateDouble, MetastableFlerovium)
                .input(plateDouble, Dubnium)
                .input(FIELD_GENERATOR_UV, 2)
                .input(FEMTO_PIC_CHIP, 64)
                .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Europium.getFluid(L * 8))
                .output(FUSION_REACTOR_MK4)
                .EUt(VA[UV].toLong())
                .duration(2 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(FUSION_REACTOR[2].stackForm)
                        .EUt(VA[UHV].toLong())
                        .CWUt(48)
                }
                .buildAndRegister()

            // Fusion Machine Casing MK4
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(HULL[UHV])
                .inputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.ADVANCED_FUSION_COIL))
                .input(VOLTAGE_COIL_UHV, 2)
                .input(FIELD_GENERATOR_UV)
                .input(plate, Dubnium, 6)
                .fluidInputs(Kevlar.getFluid(L * 8))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK4, 2))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced Fusion Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL))
                .input(FIELD_GENERATOR_LuV, 2)
                .input(ELECTRIC_PUMP_LuV)
                .input(NEUTRON_REFLECTOR, 4)
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .input(pipeSmallFluid, Europium, 4)
                .input(plate, Americium, 4)
                .fluidInputs(YttriumBariumCuprate.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.ADVANCED_FUSION_COIL))
                .EUt(VA[UV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Fusion Reactor Computer MK5
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.ULTIMATE_FUSION_COIL))
                .input(circuit, MarkerMaterials.Tier.UIV, 4)
                .input(plateDouble, MetastableHassium)
                .input(plateDouble, Meitnerium)
                .input(FIELD_GENERATOR_UHV, 2)
                .input(ATTO_PIC_CHIP, 64)
                .input(wireGtSingle, VibraniumTritaniumActiniumIronSuperhydride, 32)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 8))
                .fluidInputs(Seaborgium.getFluid(L * 8))
                .output(FUSION_REACTOR_MK5)
                .EUt(VA[UHV].toLong())
                .duration(2 * MINUTE + 30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(FUSION_REACTOR_MK4.stackForm)
                        .EUt(VA[UEV].toLong())
                        .CWUt(64)
                }
                .buildAndRegister()

            // Fusion Machine Casing MK5
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(HULL[UEV])
                .inputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.ULTIMATE_FUSION_COIL))
                .input(VOLTAGE_COIL_UEV, 2)
                .input(FIELD_GENERATOR_UHV)
                .input(plate, Bohrium, 6)
                .fluidInputs(Kevlar.getFluid(L * 16))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK5, 2))
                .EUt(VA[UEV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Ultimate Fusion Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.ADVANCED_FUSION_COIL))
                .input(FIELD_GENERATOR_ZPM, 2)
                .input(ELECTRIC_PUMP_ZPM)
                .input(NEUTRON_REFLECTOR, 8)
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .input(pipeSmallFluid, Duranium, 4)
                .input(plate, Dubnium, 4)
                .fluidInputs(Europium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.ULTIMATE_FUSION_COIL))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced Fusion Reactor
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(FUSION_REACTOR[0], 32)
                .input(circuit, MarkerMaterials.Tier.ZPM, 16)
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
                .EUt(VA[LuV].toLong())
                .duration(2 * MINUTE)
                .scannerResearch { r ->
                    r.researchStack(OreDictUnifier.get(block, Duranium))
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // Cryostat MK1
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(13)
                .input(frameGt, TitaniumCarbide)
                .input(plate, TungstenSteel, 6)
                .input(springSmall, MolybdenumDisilicide, 2)
                .input(ELECTRIC_PUMP_LuV)
                .input(pipeSmallFluid, Inconel718, 2)
                .input(wireFine, Naquadah, 16)
                .input(screw, LithiumTitanate, 4)
                .fluidInputs(Duranium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK1, 4))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Cryostat MK2
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(13)
                .input(frameGt, TungstenCarbide)
                .input(plate, RhodiumPlatedPalladium, 6)
                .input(springSmall, Talonite, 2)
                .input(ELECTRIC_PUMP_ZPM)
                .input(pipeNormalFluid, Inconel718, 2)
                .input(wireFine, NaquadahEnriched, 16)
                .input(screw, BariumTitanate, 4)
                .fluidInputs(Francium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK2, 4))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Cryostat MK3
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(13)
                .input(frameGt, TitaniumTungstenCarbide)
                .input(plate, NaquadahAlloy, 6)
                .input(springSmall, HastelloyN, 2)
                .input(ELECTRIC_PUMP_UV)
                .input(pipeLargeFluid, Inconel718, 2)
                .input(wireFine, Naquadria, 16)
                .input(screw, RubidiumTitanate, 4)
                .fluidInputs(Meitnerium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK3, 4))
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Cryostat MK4
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(13)
                .input(frameGt, TitanSteel)
                .input(plate, Darmstadtium, 6)
                .input(springSmall, Trinaquadalloy, 2)
                .input(ELECTRIC_PUMP_UHV)
                .input(pipeHugeFluid, Inconel718, 2)
                .input(wireFine, Adamantium, 16)
                .input(screw, BariumStrontiumTitanate, 4)
                .fluidInputs(Copernicium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_01.getItemVariant(BlockFusionCasing01.FusionCasingType.CRYOSTAT_MK4, 4))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Cryostat MK5
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(13)
                .input(frameGt, TantalumHafniumSeaborgiumCarbide)
                .input(plate, Neutronium, 6)
                .input(springSmall, Tairitsium, 2)
                .input(ELECTRIC_PUMP_UEV)
                .input(pipeHugeFluid, Inconel718, 4)
                .input(wireFine, Vibranium, 16)
                .input(screw, CaesiumCeriumCobaltIndium, 4)
                .fluidInputs(Moscovium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.CRYOSTAT_MK5, 4))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Divertor MK1
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(14)
                .input(frameGt, TitaniumCarbide)
                .input(plate, TungstenSteel, 2)
                .input(rotor, TungstenSteel)
                .input(CONVEYOR_MODULE_LuV)
                .input(pipeSmallItem, MaragingSteel250, 2)
                .input(wireFine, Naquadah, 16)
                .input(screw, LithiumTitanate, 4)
                .fluidInputs(Duranium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK1, 4))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Divertor MK2
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(14)
                .input(frameGt, TungstenCarbide)
                .input(plate, RhodiumPlatedPalladium, 4)
                .input(rotor, RhodiumPlatedPalladium)
                .input(CONVEYOR_MODULE_ZPM)
                .input(pipeNormalItem, MaragingSteel250, 2)
                .input(wireFine, NaquadahEnriched, 16)
                .input(screw, BariumTitanate, 4)
                .fluidInputs(Francium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK2, 4))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Divertor MK3
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(14)
                .input(frameGt, TitaniumTungstenCarbide)
                .input(plate, NaquadahAlloy, 4)
                .input(rotor, NaquadahAlloy)
                .input(CONVEYOR_MODULE_UV)
                .input(pipeLargeItem, MaragingSteel250, 2)
                .input(wireFine, Naquadria, 16)
                .input(screw, RubidiumTitanate, 4)
                .fluidInputs(Meitnerium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK3, 4))
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Divertor MK4
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(14)
                .input(frameGt, TitanSteel)
                .input(plate, Darmstadtium, 4)
                .input(rotor, Darmstadtium)
                .input(CONVEYOR_MODULE_UHV)
                .input(pipeHugeItem, MaragingSteel250, 2)
                .input(wireFine, Adamantium, 16)
                .input(screw, BariumStrontiumTitanate, 4)
                .fluidInputs(Copernicium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK4, 4))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Divertor MK5
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(14)
                .input(frameGt, TantalumHafniumSeaborgiumCarbide)
                .input(plate, Neutronium, 4)
                .input(rotor, Neutronium)
                .input(CONVEYOR_MODULE_UEV)
                .input(pipeHugeItem, MaragingSteel250, 4)
                .input(wireFine, Vibranium, 16)
                .input(screw, CaesiumCeriumCobaltIndium, 4)
                .fluidInputs(Moscovium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.DIVERTOR_MK5, 4))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Vacuum MK1
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(frameGt, TitaniumCarbide)
                .input(plateDouble, TungstenSteel, 2)
                .input(gearSmall, HSSG, 3)
                .input(ELECTRIC_PISTON_LUV)
                .input(NEUTRON_REFLECTOR)
                .input(wireFine, Naquadah, 16)
                .input(screw, LithiumTitanate, 4)
                .fluidInputs(Duranium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.VACUUM_MK1, 4))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Vacuum MK2
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(frameGt, TungstenCarbide)
                .input(plateDouble, RhodiumPlatedPalladium, 2)
                .input(gearSmall, HSSE, 3)
                .input(ELECTRIC_PISTON_ZPM)
                .input(NEUTRON_REFLECTOR, 2)
                .input(wireFine, NaquadahEnriched, 16)
                .input(screw, BariumTitanate, 4)
                .fluidInputs(Francium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_02.getItemVariant(BlockFusionCasing02.FusionCasingType.VACUUM_MK2, 4))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Vacuum MK3
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(frameGt, TitaniumTungstenCarbide)
                .input(plateDouble, NaquadahAlloy, 2)
                .input(gearSmall, HSSS, 3)
                .input(ELECTRIC_PISTON_UV)
                .input(NEUTRON_REFLECTOR, 4)
                .input(wireFine, Naquadria, 16)
                .input(screw, RubidiumTitanate, 4)
                .fluidInputs(Meitnerium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_03.getItemVariant(BlockFusionCasing03.FusionCasingType.VACUUM_MK3, 4))
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Vacuum MK4
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(frameGt, TitanSteel)
                .input(plateDouble, Darmstadtium, 2)
                .input(gearSmall, HDCS, 3)
                .input(ELECTRIC_PISTON_UHV)
                .input(NEUTRON_REFLECTOR, 8)
                .input(wireFine, Adamantium, 16)
                .input(screw, BariumStrontiumTitanate, 4)
                .fluidInputs(Copernicium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_03.getItemVariant(BlockFusionCasing03.FusionCasingType.VACUUM_MK4, 4))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Vacuum MK5
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(frameGt, TantalumHafniumSeaborgiumCarbide)
                .input(plateDouble, Neutronium, 2)
                .input(gearSmall, HastelloyK243, 3)
                .input(ELECTRIC_PISTON_UEV)
                .input(NEUTRON_REFLECTOR, 16)
                .input(wireFine, Vibranium, 16)
                .input(screw, CaesiumCeriumCobaltIndium, 4)
                .fluidInputs(Moscovium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.FUSION_CASING_03.getItemVariant(BlockFusionCasing03.FusionCasingType.VACUUM_MK5, 4))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Component Assembly Line (CoAL)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ASSEMBLY_LINE, 4)
                .inputs(MetaBlocks.MULTIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING, 8))
                .inputs(MetaBlocks.MULTIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL, 16))
                .input(ROBOT_ARM_UV, 8)
                .input(CONVEYOR_MODULE_UV, 8)
                .input(plateDouble, EnrichedNaquadahAlloy, 6)
                .input(plateDouble, Taranium, 12)
                .input(gear, Bedrockium, 3)
                .input(gearSmall, Bedrockium, 6)
                .input(TOOL_CASTER[EV], 8)
                .input(foil, BariumStrontiumTitanate, 12)
                .input(foil, FranciumCaesiumCadmiumBromide, 12)
                .input(circuit, MarkerMaterials.Tier.UHV, 4)
                .input(wireGtQuadruple, EnrichedNaquadahAlloy, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 12))
                .fluidInputs(Lubricant.getFluid(5000))
                .fluidInputs(Naquadria.getFluid(L * 8))
                .fluidInputs(Trinaquadalloy.getFluid(L * 4))
                .output(COMPONENT_ASSEMBLY_LINE)
                .EUt(VA[UV].toLong())
                .duration(5 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(MetaBlocks.MULTIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING))
                        .EUt(VA[ZPM].toLong())
                        .CWUt(32)
                }
                .buildAndRegister()

            // Advanced Filter Casing
            ModHandler.addShapedRecipe(true, "advanced_filter_casing", GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.ADVANCED_FILTER_CASING, ConfigHolder.recipes.casingsPerCraft),
                "PDP", "SCG", "MFR",
                'C', MetaBlocks.CLEANROOM_CASING.getItemVariant(BlockCleanroomCasing.CasingType.FILTER_CASING),
                'S', SMART_FILTER,
                'M', ELECTRIC_MOTOR_UV,
                'D', CONVEYOR_MODULE_UV,
                'F', UnificationEntry(frameGt, Iridium),
                'R', UnificationEntry(rotor, Duranium),
                'G', UnificationEntry(gearSmall, HastelloyN),
                'P', UnificationEntry(pipeLargeItem, Osmiridium))

            // Cosmic Ray Detector (CRD)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(SCANNER[UHV], 4)
                .inputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.REFLECTIVE_SURFACE_CASING, ConfigHolder.recipes.casingsPerCraft * 4))
                .input(EMITTER_UHV, 6)
                .input(SENSOR_UHV, 6)
                .input(plateDouble, TitanSteel, 8)
                .input(circuit, MarkerMaterials.Tier.UEV, 2)
                .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 18))
                .fluidInputs(UUMatter.getFluid(16000))
                .fluidInputs(FreeElectronGas.getFluid(8000))
                .fluidInputs(Trinaquadalloy.getFluid(L * 8))
                .output(COSMIC_RAY_DETECTOR)
                .EUt(VA[UHV].toLong())
                .duration(2 * MINUTE + 30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SCANNER[UHV].stackForm)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

            // Reflective Surface Casing
            ModHandler.addShapedRecipe(true, "reflective_surface_casing", GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.REFLECTIVE_SURFACE_CASING, ConfigHolder.recipes.casingsPerCraft * 4),
                "EEE", "RQR", "SFS",
                'E', EMITTER_UHV,
                'R', NEUTRON_REFLECTOR,
                'F', UnificationEntry(frameGt, HDCS),
                'Q', GTLiteMetaBlocks.METAL_CASING_01.getItemVariant(BlockMetalCasing01.MetalCasingType.TALONITE),
                'S', UnificationEntry(screw, Pikyonium64B))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(frameGt, HDCS)
                .inputs(GTLiteMetaBlocks.METAL_CASING_01.getItemVariant(BlockMetalCasing01.MetalCasingType.TALONITE))
                .input(EMITTER_UHV, 3)
                .input(NEUTRON_REFLECTOR, 2)
                .input(screw, Pikyonium64B, 2)
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.REFLECTIVE_SURFACE_CASING, ConfigHolder.recipes.casingsPerCraft * 4))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Stellar Forge
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(NANO_FORGE, 16)
                .inputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.STELLAR_CONTAINMENT_CASING, 16))
                .inputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.THERMAL_ENERGY_TRANSMISSION_CASING, 16))
                .input(ELECTRIC_PUMP_UHV, 8)
                .input(ROBOT_ARM_UHV, 8)
                .input(FIELD_GENERATOR_UHV, 8)
                .input(plateDouble, Tairitsium, 4)
                .input(circuit, MarkerMaterials.Tier.UEV, 12)
                .input(foil, EnrichedNaquadahAlloy, 24)
                .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 36))
                .fluidInputs(UUMatter.getFluid(64000))
                .fluidInputs(TitanSteel.getFluid(L * 8))
                .fluidInputs(HDCS.getFluid(L * 4))
                .output(STELLAR_FORGE)
                .EUt(VA[UHV].toLong())
                .duration(2 * MINUTE + 30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(VOLCANUS.stackForm)
                        .EUt(VA[UV].toLong())
                        .CWUt(36)
                }
                .buildAndRegister()

            // Naquadria Charge
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(frameGt, Naquadria)
                .input(dust, Hexanitrohexaaxaisowurtzitane)
                .input(plate, NaquadahEnriched, 2)
                .input(plate, Mendelevium, 4)
                .input(foil, NaquadahAlloy, 8)
                .fluidInputs(GlycerylTrinitrate.getFluid(16000))
                .outputs(ItemStack(NAQUADRIA_CHARGE))
                .EUt(VA[UV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Taranium Charge
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .inputs(ItemStack(NAQUADRIA_CHARGE))
                .input(dust, DegenerateRhenium)
                .input(plate, Taranium, 2)
                .input(plate, Lawrencium, 4)
                .input(foil, Tritanium, 8)
                .fluidInputs(CyclotetramethyleneTetranitroamine.getFluid(4000))
                .outputs(ItemStack(TARANIUM_CHARGE))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Leptonic Charge
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .inputs(ItemStack(TARANIUM_CHARGE))
                .input(dust, Octaazacubane)
                .input(plate, MetastableOganesson, 2)
                .input(plate, Dubnium, 4)
                .input(foil, Vibranium, 8)
                .fluidInputs(HeavyLeptonMixture.getFluid(1000))
                .outputs(ItemStack(LEPTONIC_CHARGE))
                .EUt(VA[UEV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Quantum Chromodynamic Charge
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .inputs(ItemStack(LEPTONIC_CHARGE))
                .input(dust, ActiniumSuperhydride)
                .input(plate, HeavyQuarkDegenerateMatter, 2)
                .input(plate, Bohrium, 4)
                .input(foil, ArceusAlloy2B, 8)
                .fluidInputs(HadronicResonantGas.getFluid(500))
                .outputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .EUt(VA[UIV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Stellar Containment Casing
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, QuantumAlloy, 4)
                .input(frameGt, Tairitsium, 4)
                .input(frameGt, TitanSteel, 4)
                .input(frameGt, EnrichedNaquadahAlloy, 4)
                .input(plateDouble, HDCS, 8)
                .input(plateDouble, Neutronium, 8)
                .input(EMITTER_UHV, 2)
                .input(SENSOR_UHV, 2)
                .input(ELECTRIC_PUMP_UHV, 2)
                .input(NEUTRON_REFLECTOR, 4)
                .input(screw, TitanSteel, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 32))
                .fluidInputs(UUMatter.getFluid(4000))
                .fluidInputs(Bedrockium.getFluid(L * 16))
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.STELLAR_CONTAINMENT_CASING, 16))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.METAL_CASING_03.getItemVariant(BlockMetalCasing03.MetalCasingType.QUANTUM_ALLOY))
                        .EUt(VA[UHV].toLong())
                        .CWUt(32)
                }
                .buildAndRegister()

            // Thermal Energy Transmissive Casing
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.HOLLOW_CASING, 4))
                .input(frameGt, Bedrockium, 16)
                .input(plateDouble, MetastableOganesson, 8)
                .input(pipeLargeFluid, HastelloyX78, 2)
                .input(gear, CosmicNeutronium, 3)
                .input(gearSmall, CosmicNeutronium, 6)
                .input(ELECTRIC_PUMP_UHV)
                .input(VOLTAGE_COIL_UHV, 8)
                .input(foil, Tairitsium, 12)
                .input(bolt, HDCS, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(MercuryBariumCalciumCuprate.getFluid(L * 16))
                .fluidInputs(StableBaryonicMatter.getFluid(4000))
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.THERMAL_ENERGY_TRANSMISSION_CASING, 32))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                        .EUt(VA[UHV].toLong())
                        .CWUt(32)
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
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'G', UnificationEntry(gear, Titanium),
                'H', HULL[EV].stackForm,
                'P', UnificationEntry(pipeLargeFluid, Titanium))

            // Large Supercritical Fluid Turbine
            ModHandler.addShapedRecipe(true, "large_supercritical_fluid_turbine", SUPERCRITICAL_FLUID_TURBINE.stackForm,
                "XGX", "GHG", "PGP",
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.ZPM),
                'G', UnificationEntry(gear, RhodiumPlatedPalladium),
                'H', HULL[LuV].stackForm,
                'P', UnificationEntry(pipeLargeFluid, RhodiumPlatedPalladium))

            // Large Rocket Engine
            ModHandler.addShapedRecipe(true, "large_rocket_engine", LARGE_ROCKET_ENGINE.stackForm,
                "SXS", "RHR", "WXW",
                'R', ROCKET_ENGINE[2]!!.stackForm,
                'H', HULL[IV].stackForm,
                'S', UnificationEntry(spring, Nitinol60),
                'W', UnificationEntry(cableGtSingle, Platinum),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LuV))

            // Large Naquadah Reactor
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(NUCLEAR_REACTOR, 64)
                .input(NUCLEAR_REACTOR, 64)
                .input(NUCLEAR_REACTOR, 64)
                .input(NUCLEAR_REACTOR, 64)
                .input(NAQUADAH_REACTOR[3]!!, 16)
                .input(ELECTRIC_PUMP_UV, 8)
                .input(ROBOT_ARM_UV, 8)
                .input(circuit, MarkerMaterials.Tier.UHV, 16)
                .input(plateDense, Pikyonium64B, 6)
                .input(plateDense, Naquadria, 6)
                .input(screw, Trinaquadalloy, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 160))
                .fluidInputs(Bedrockium.getFluid(L * 40))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 20))
                .fluidInputs(PreciousMetalAlloy.getFluid(L * 10))
                .output(LARGE_NAQUADAH_REACTOR)
                .EUt(VA[UHV].toLong())
                .duration(5 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(NAQUADAH_REACTOR[3]!!.stackForm)
                        .EUt(VA[UV].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // Nano Forge
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(VOLCANUS, 8)
                .input(nanite, Carbon, 16)
                .input(circuit, MarkerMaterials.Tier.UV, 4)
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
                .EUt(VA[ZPM].toLong())
                .duration(2 * MINUTE + 30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(OreDictUnifier.get(nanite, Carbon))
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // PCB Factory
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, RhodiumPlatedPalladium, 4)
                .input(CIRCUIT_ASSEMBLER[LuV], 4)
                .input(plate, Osmiridium, 4)
                .input(circuit, MarkerMaterials.Tier.LuV, 16)
                .input(gear, Ruridit, 2)
                .input(ROBOT_ARM_LuV, 8)
                .input(cableGtSingle, NiobiumTitanium, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .output(PCB_FACTORY)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE)
                .scannerResearch { r ->
                    r.researchStack(WETWARE_CIRCUIT_BOARD.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // Infinity Cooling Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(frameGt, Hypogen)
                .input(rotor, Infinity, 2)
                .input(plate, Thulium, 6)
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.INFINITY_COOLING_CASING, 2))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Quantum Force Transformer
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(LARGE_MASS_FABRICATOR)
                .inputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.PARTICLE_EXCITATION_WIRE_COIL))
                .input(circuit, MarkerMaterials.Tier.UEV, 8)
                .input(ELECTRIC_PUMP_UEV, 4)
                .input(FIELD_GENERATOR_UEV, 4)
                .input(QUANTUM_ANOMALY)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 10))
                .fluidInputs(Pikyonium64B.getFluid(L * 32))
                .output(QUANTUM_FORCE_TRANSFORMER)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.PARTICLE_EXCITATION_WIRE_COIL))
                        .EUt(VA[UEV].toLong())
                        .CWUt(48)
                }
                .buildAndRegister()

            // Antimatter Forge
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(FUSION_REACTOR_MK5)
                .input(frameGt, Infinity, 16)
                .inputs(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.COSMIC_FABRIC, 16))
                .inputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.STELLAR_CONTAINMENT_CASING, 16))
                .input(wireFine, Hypogen, 64)
                .input(wireFine, Infinity, 64)
                .input(circuit, MarkerMaterials.Tier.UIV, 16)
                .input(ELECTRIC_PUMP_UEV, 16)
                .input(plateDense, CosmicNeutronium, 4)
                .input(plateDense, MetastableHassium, 4)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
                .fluidInputs(Bedrockium.getFluid(L * 64))
                .fluidInputs(Protomatter.getFluid(16000))
                .output(ANTIMATTER_FORGE)
                .EUt(VA[UEV].toLong())
                .duration(10 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(ADVANCED_FUSION_REACTOR.stackForm)
                        .EUt(VA[UEV].toLong())
                        .CWUt(64)
                }
                .buildAndRegister()

            // Antimatter Generator
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(LARGE_NAQUADAH_REACTOR)
                .input(LASER_OUTPUT_HATCH_1048576[UEV - IV], 4)
                .inputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.ANTIMATTER_ANNIHILATION_MATRIX, 16))
                .input(circuit, MarkerMaterials.Tier.UIV, 16)
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
                .EUt(VA[UEV].toLong())
                .duration(10 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(LARGE_NAQUADAH_REACTOR.stackForm)
                        .EUt(VA[UEV].toLong())
                        .CWUt(128)
                }
                .buildAndRegister()

            // Space Elevator
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getItemVariant(BlockSpaceElevatorCasing.CasingType.BASE_CASING, 8))
                .input(frameGt, Neutronium, 16)
                .input(FIELD_GENERATOR_UV, 4)
                .input(circuit, MarkerMaterials.Tier.UHV, 8)
                .input(circuit, MarkerMaterials.Tier.UV, 16)
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
                .EUt(VA[UHV].toLong())
                .duration(2 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(MINING_DRONE_AIRPORT.stackForm)
                        .EUt(VA[UV].toLong())
                        .CWUt(32)
                }
                .buildAndRegister()

            // Space Assembler Module MK1
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getItemVariant(BlockSpaceElevatorCasing.CasingType.BASE_CASING))
                .input(ASSEMBLER[UHV], 4)
                .input(CIRCUIT_ASSEMBLER[UHV], 4)
                .input(gear, CosmicNeutronium, 8)
                .input(gearSmall, CosmicNeutronium, 16)
                .input(ROBOT_ARM_UHV, 8)
                .input(CONVEYOR_MODULE_UHV, 16)
                .input(circuit, MarkerMaterials.Tier.UHV, 8)
                .input(circuit, MarkerMaterials.Tier.UV, 16)
                .input(frameGt, Neutronium, 8)
                .input(screw, Neutronium, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .fluidInputs(Lubricant.getFluid(16000))
                .output(SPACE_ASSEMBLER_MK1)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(ASSEMBLER[UHV].stackForm)
                        .EUt(VA[UHV].toLong())
                        .CWUt(32)
                }
                .buildAndRegister()

            // Space Assembler Module MK2
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getItemVariant(BlockSpaceElevatorCasing.CasingType.BASE_CASING))
                .input(ASSEMBLER[UEV], 4)
                .input(CIRCUIT_ASSEMBLER[UEV], 4)
                .input(gear, Infinity, 8)
                .input(gearSmall, Infinity, 16)
                .input(ROBOT_ARM_UEV, 8)
                .input(CONVEYOR_MODULE_UEV, 16)
                .input(circuit, MarkerMaterials.Tier.UEV, 8)
                .input(circuit, MarkerMaterials.Tier.UHV, 16)
                .input(frameGt, Vibranium, 8)
                .input(screw, Vibranium, 32)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 9))
                .fluidInputs(Taranium.getFluid(L * 9))
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
                .output(SPACE_ASSEMBLER_MK2)
                .EUt(VA[UEV].toLong())
                .duration(2 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(SPACE_ASSEMBLER_MK1.stackForm)
                        .EUt(VA[UEV].toLong())
                        .CWUt(48)
                }
                .buildAndRegister()

            // Space Assembler Module MK3
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getItemVariant(BlockSpaceElevatorCasing.CasingType.BASE_CASING))
                .input(ASSEMBLER[UIV], 4)
                .input(CIRCUIT_ASSEMBLER[UIV], 4)
                .input(gear, SpaceTime, 8)
                .input(gearSmall, SpaceTime, 16)
                .input(ROBOT_ARM_UIV, 8)
                .input(CONVEYOR_MODULE_UIV, 16)
                .input(circuit, MarkerMaterials.Tier.UIV, 8)
                .input(circuit, MarkerMaterials.Tier.UEV, 16)
                .input(frameGt, Shirabon, 8)
                .input(screw, Shirabon, 32)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 9))
                .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 9))
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
                .output(SPACE_ASSEMBLER_MK3)
                .EUt(VA[UIV].toLong())
                .duration(5 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(SPACE_ASSEMBLER_MK2.stackForm)
                        .EUt(VA[UIV].toLong())
                        .CWUt(64)
                }
                .buildAndRegister()

        }

    }

}