package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.items.OreDictNames
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.PCBCoolant
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.RTMAlloy
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Ultimet
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.Zircaloy4
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.pipeNonupleFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeQuadrupleFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockComputerCasing
import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.BlockMachineCasing
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_EV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_HV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_MV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_EV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_HV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_MV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.EMITTER_EV
import gregtech.common.items.MetaItems.EMITTER_HV
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LV
import gregtech.common.items.MetaItems.EMITTER_MV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UIV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.NEUTRON_REFLECTOR
import gregtech.common.items.MetaItems.ROBOT_ARM_EV
import gregtech.common.items.MetaItems.ROBOT_ARM_HV
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LV
import gregtech.common.items.MetaItems.ROBOT_ARM_MV
import gregtech.common.items.MetaItems.SENSOR_EV
import gregtech.common.items.MetaItems.SENSOR_HV
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LV
import gregtech.common.items.MetaItems.SENSOR_MV
import gregtech.common.items.MetaItems.TOOL_DATA_STICK
import gregtech.common.items.MetaItems.VACUUM_TUBE
import gregtech.common.metatileentities.MetaTileEntities.ALUMINIUM_CRATE
import gregtech.common.metatileentities.MetaTileEntities.ALUMINIUM_DRUM
import gregtech.common.metatileentities.MetaTileEntities.BRONZE_CRATE
import gregtech.common.metatileentities.MetaTileEntities.BRONZE_DRUM
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH_4A
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH_4A
import gregtech.common.metatileentities.MetaTileEntities.FLUID_EXPORT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.FLUID_IMPORT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.HI_AMP_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.common.metatileentities.MetaTileEntities.ITEM_EXPORT_BUS
import gregtech.common.metatileentities.MetaTileEntities.ITEM_IMPORT_BUS
import gregtech.common.metatileentities.MetaTileEntities.LASER_INPUT_HATCH_1024
import gregtech.common.metatileentities.MetaTileEntities.LASER_INPUT_HATCH_256
import gregtech.common.metatileentities.MetaTileEntities.LASER_INPUT_HATCH_4096
import gregtech.common.metatileentities.MetaTileEntities.LASER_OUTPUT_HATCH_1024
import gregtech.common.metatileentities.MetaTileEntities.LASER_OUTPUT_HATCH_256
import gregtech.common.metatileentities.MetaTileEntities.LASER_OUTPUT_HATCH_4096
import gregtech.common.metatileentities.MetaTileEntities.POWER_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_CHEST
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_TANK
import gregtech.common.metatileentities.MetaTileEntities.STAINLESS_STEEL_CRATE
import gregtech.common.metatileentities.MetaTileEntities.STAINLESS_STEEL_DRUM
import gregtech.common.metatileentities.MetaTileEntities.STEEL_CRATE
import gregtech.common.metatileentities.MetaTileEntities.STEEL_DRUM
import gregtech.common.metatileentities.MetaTileEntities.TITANIUM_CRATE
import gregtech.common.metatileentities.MetaTileEntities.TITANIUM_DRUM
import gregtech.common.metatileentities.MetaTileEntities.TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.TUNGSTENSTEEL_CRATE
import gregtech.common.metatileentities.MetaTileEntities.TUNGSTENSTEEL_DRUM
import gregtech.loaders.recipe.CraftingComponent
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VACUUM_CHAMBER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Abyssalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmorphousBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BerylliumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlackDwarfMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTellurideMagnetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicFabric
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Creon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedNaquadahAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullereneSuperconductor
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GSTGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HSLASteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HafniumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX78
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyLeptonMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HighEnergyQuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hypogen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Inconel625
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Magnetium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Mellion
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Periodicium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polymethylmethacrylate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PreciousMetalAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumchromodynamicallyConfinedMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SiliconCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SpaceTime
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumTungstenCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TranscendentMetal
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trinaquadalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VibraniumTritaniumActiniumIronSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WhiteDwarfMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZirconiumCarbide
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.nanite
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.copy
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.GTRecipeUtility.Companion.addIOHatchRecipes
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01
import magicbook.gtlitecore.common.block.blocks.BlockBoilerCasing01
import magicbook.gtlitecore.common.block.blocks.BlockComponentAssemblyCasing
import magicbook.gtlitecore.common.block.blocks.BlockComputerCasing01
import magicbook.gtlitecore.common.block.blocks.BlockConveyorCasing
import magicbook.gtlitecore.common.block.blocks.BlockCrucible
import magicbook.gtlitecore.common.block.blocks.BlockEmitterCasing
import magicbook.gtlitecore.common.block.blocks.BlockFieldGenCasing
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
import magicbook.gtlitecore.common.block.blocks.BlockManipulator
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import magicbook.gtlitecore.common.block.blocks.BlockMotorCasing
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.block.blocks.BlockPistonCasing
import magicbook.gtlitecore.common.block.blocks.BlockProcessorCasing
import magicbook.gtlitecore.common.block.blocks.BlockPumpCasing
import magicbook.gtlitecore.common.block.blocks.BlockRobotArmCasing
import magicbook.gtlitecore.common.block.blocks.BlockSensorCasing
import magicbook.gtlitecore.common.block.blocks.BlockShieldingCore
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing01
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing02
import magicbook.gtlitecore.common.block.blocks.BlockWireCoils
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CIRCUIT_PATTERN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MAGNETRON
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MICA_INSULATOR_FOIL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_EV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_HV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_IV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_LV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_MV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NAQUADRIA_SUPERSOLID
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.QUANTUM_ANOMALY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.STABLE_ADHESIVE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPERCONDUCTOR_COMPOSITE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_OpV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UXV
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHROME_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COPPER_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COPPER_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.DIAMOND_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.DYNAMO_HATCH_16A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.DYNAMO_HATCH_4A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ENERGY_HATCH_16A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ENERGY_HATCH_4A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.GOLD_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRIDIUM_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRON_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRON_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_INPUT_HATCH_1048576
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_INPUT_HATCH_16384
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_INPUT_HATCH_262144
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_INPUT_HATCH_65536
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_OUTPUT_HATCH_1048576
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_OUTPUT_HATCH_16384
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_OUTPUT_HATCH_262144
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LASER_OUTPUT_HATCH_65536
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LEAD_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_EXPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_IMPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_EXPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_IMPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SILVER_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SUBSTATION_DYNAMO_HATCH_64A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SUBSTATION_ENERGY_HATCH_64A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TUNGSTEN_DRUM
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class AssemblerRecipes
{

    companion object
    {

        fun init()
        {
            drumsRecipes()
            cratesRecipes()
            ////////////////////////////////
            machineCasingRecipes()
            machineHullRecipes()
            pipeCasingRecipes()
            turbineCasingRecipes()
            nuclearCasingRecipes()
            motorCasingRecipes()
            pistonCasingRecipes()
            pumpCasingRecipes()
            conveyorCasingRecipes()
            robotArmCasingRecipes()
            emitterCasingRecipes()
            sensorCasingRecipes()
            fieldGenCasingRecipes()
            processorCasingRecipes()
            coALCasingRecipes()
            qftCasingsRecipes()
            ////////////////////////////////
            itemHatchesRecipes()
            fluidHatchesRecipes()
            energyHatchesRecipes()
            dynamoHatchesRecipes()
            hiAmpEnergyHatchesRecipes()
            hiAmpDynamoHatchesRecipes()
            transformersRecipes()
            laserHatchesRecipes()
            ////////////////////////////////
            wireCoilRecipes()
            crucibleRecipes()
            ////////////////////////////////
            voltageCoilRecipes()
            miningDroneRecipes()
            miscItemsRecipes()
            ////////////////////////////////
            vanillaChangingRecipes()
        }

        private fun drumsRecipes()
        {
            // Iron Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Iron, 2)
                .input(plate, Iron, 4)
                .output(IRON_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Copper Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Copper, 2)
                .input(plate, Copper, 4)
                .output(COPPER_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Lead Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Lead, 2)
                .input(plate, Lead, 4)
                .output(LEAD_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Chrome Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Chrome, 2)
                .input(plate, Chrome, 4)
                .output(CHROME_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tungsten Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Tungsten, 2)
                .input(plate, Tungsten, 4)
                .output(TUNGSTEN_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Iridium Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Iridium, 2)
                .input(plate, Iridium, 4)
                .output(IRIDIUM_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun cratesRecipes()
        {
            // Iron Crate
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, Iron, 4)
                .input(plate, Iron, 4)
                .output(IRON_CRATE)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Copper Crate
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, Copper, 4)
                .input(plate, Copper, 4)
                .output(COPPER_CRATE)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Silver Crate
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, Silver, 4)
                .input(plate, Silver, 4)
                .output(SILVER_CRATE)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Gold Crate
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, Gold, 4)
                .input(plate, Gold, 4)
                .output(GOLD_CRATE)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Diamond Crate
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, Diamond, 4)
                .input(plate, Diamond, 4)
                .output(DIAMOND_CRATE)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun machineCasingRecipes()
        {
            // UEV Machine Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(plate, Vibranium, 8)
                .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UIV Machine Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(plate, Shirabon, 8)
                .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UXV Machine Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(plate, Creon, 8)
                .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // OpV Machine Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(plate, BlackDwarfMatter, 8)
                .outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.OpV))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun machineHullRecipes()
        {
            // UHV Machine Hull
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV),
                    OreDictUnifier.get(cableGtSingle, Europium, 2)),
                arrayOf(Polybenzimidazole.getFluid(L * 2)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV))
                .input(cableGtSingle, Europium, 2)
                .fluidInputs(Kevlar.getFluid(L * 2))
                .output(HULL[UHV])
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UEV Machine Hull
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV))
                .input(cableGtSingle, Seaborgium, 2)
                .fluidInputs(Kevlar.getFluid(L * 2))
                .output(HULL[UEV])
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UIV Machine Hull
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV))
                .input(cableGtSingle, SuperheavyAlloyA, 2)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
                .output(HULL[UIV])
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UXV Machine Hull
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV))
                .input(cableGtSingle, SuperheavyAlloyB, 2)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
                .output(HULL[UXV])
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            //  OpV Machine Hull
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.OpV))
                .input(cableGtSingle, Periodicium, 2)
                .fluidInputs(CosmicFabric.getFluid(L * 2))
                .output(HULL[OpV])
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun pipeCasingRecipes()
        {
            // Bronze pipe casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(plate, Bronze, 4)
                .input(pipeNormalFluid, Bronze, 4)
                .input(frameGt, Bronze)
                .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.BRONZE_PIPE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Steel pipe casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(plate, Steel, 4)
                .input(pipeNormalFluid, Steel, 4)
                .input(frameGt, Steel)
                .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Titanium pipe casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(plate, Titanium, 4)
                .input(pipeNormalFluid, Titanium, 4)
                .input(frameGt, Titanium)
                .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Tungsten Steel pipe casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(plate, TungstenSteel, 4)
                .input(pipeNormalFluid, TungstenSteel, 4)
                .input(frameGt, TungstenSteel)
                .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // PTFE pipe casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(plate, Polytetrafluoroethylene, 4)
                .input(pipeNormalFluid, Polytetrafluoroethylene, 4)
                .input(frameGt, Polytetrafluoroethylene)
                .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // PBI pipe casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(plate, Polybenzimidazole, 4)
                .input(pipeNormalFluid, Polybenzimidazole, 4)
                .input(frameGt, Polybenzimidazole)
                .outputs(GTLiteMetaBlocks.BOILER_CASING_01.getItemVariant(BlockBoilerCasing01.BoilerCasingType.POLYBENZIMIDAZOLE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun turbineCasingRecipes()
        {
            // Rhodium Plated Palladium Turbine Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .inputs(MetaBlocks.TURBINE_CASING.getItemVariant(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING))
                .input(plate, RhodiumPlatedPalladium, 6)
                .outputs(GTLiteMetaBlocks.TURBINE_CASING_01.getItemVariant(BlockTurbineCasing01.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_TURBINE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Rhodium Plated Palladium Gearbox Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(plate, RhodiumPlatedPalladium, 4)
                .input(gear, RhodiumPlatedPalladium, 2)
                .input(frameGt, RhodiumPlatedPalladium)
                .outputs(GTLiteMetaBlocks.TURBINE_CASING_02.getItemVariant(BlockTurbineCasing02.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_GEARBOX, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun nuclearCasingRecipes()
        {
            // Nuclear Temperature Controller
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .inputs(GTLiteMetaBlocks.METAL_CASING_03.getItemVariant(BlockMetalCasing03.MetalCasingType.INCONEL_718))
                .inputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE))
                .input(wireFine, Nichrome, 4)
                .input(screw, Inconel718, 2)
                .fluidInputs(ZirconiumCarbide.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getItemVariant(BlockActiveUniqueCasing01.UniqueCasingType.TEMPERATURE_CONTROLLER))
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun motorCasingRecipes()
        {
            val motorCasings = arrayOf(
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.LV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.MV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.HV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.EV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.IV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.LuV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.ZPM),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UHV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UEV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UIV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UXV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.OpV)) // TODO MAX

            for (i in motorCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(ring, Steel, 2)
                    .inputs(CraftingComponent.MOTOR.getIngredient(i + 1) as ItemStack)
                    .outputs(motorCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun pistonCasingRecipes()
        {
            val pistonCasings = arrayOf(
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.LV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.MV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.HV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.EV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.IV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.LuV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.ZPM),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UHV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UEV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UIV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UXV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.OpV)) // TODO MAX

            for (i in pistonCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(gearSmall, Steel, 2)
                    .inputs(CraftingComponent.PISTON.getIngredient(i + 1) as ItemStack)
                    .outputs(pistonCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun pumpCasingRecipes()
        {
            val pumpCasings = arrayOf(
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.LV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.MV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.HV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.EV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.IV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.LuV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.ZPM),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UHV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UEV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UIV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UXV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.OpV)) // TODO MAX

            for (i in pumpCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(rotor, Steel, 2)
                    .inputs(CraftingComponent.PUMP.getIngredient(i + 1) as ItemStack)
                    .outputs(pumpCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun conveyorCasingRecipes()
        {
            val conveyorCasings = arrayOf(
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.LV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.MV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.HV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.EV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.IV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.LuV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.ZPM),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UHV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UEV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UIV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UXV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.OpV)) // TODO MAX

            for (i in conveyorCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(round, Steel, 2)
                    .inputs(CraftingComponent.CONVEYOR.getIngredient(i + 1) as ItemStack)
                    .outputs(conveyorCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun robotArmCasingRecipes()
        {
            val robotArmCasings = arrayOf(
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.LV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.MV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.HV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.EV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.IV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.LuV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.ZPM),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UHV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UEV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UIV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UXV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.OpV)) // TODO MAX

            for (i in robotArmCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(gear, Steel, 2)
                    .inputs(CraftingComponent.ROBOT_ARM.getIngredient(i + 1) as ItemStack)
                    .outputs(robotArmCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun emitterCasingRecipes()
        {
            val emitterCasings = arrayOf(
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.LV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.MV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.HV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.EV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.IV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.LuV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.ZPM),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UHV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UEV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UIV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UXV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.OpV)) // TODO MAX

            for (i in emitterCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(foil, Steel, 2)
                    .inputs(CraftingComponent.EMITTER.getIngredient(i + 1) as ItemStack)
                    .outputs(emitterCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun sensorCasingRecipes()
        {
            val sensorCasings = arrayOf(
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.LV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.MV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.HV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.EV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.IV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.LuV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.ZPM),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UHV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UEV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UIV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UXV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.OpV)) // TODO MAX

            for (i in sensorCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(wireFine, Steel, 2)
                    .inputs(CraftingComponent.SENSOR.getIngredient(i + 1) as ItemStack)
                    .outputs(sensorCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun fieldGenCasingRecipes()
        {
            val fieldGenCasings = arrayOf(
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.LV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.MV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.HV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.EV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.IV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.LuV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.ZPM),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UHV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UEV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UIV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UXV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.OpV)) // TODO MAX

            for (i in fieldGenCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(wireGtSingle, Steel, 2)
                    .inputs(CraftingComponent.FIELD_GENERATOR.getIngredient(i + 1) as ItemStack)
                    .outputs(fieldGenCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }

        private fun processorCasingRecipes()
        {
            val processorCasings = arrayOf(
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.LV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.MV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.HV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.EV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.IV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.LuV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.ZPM),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UHV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UEV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UIV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UXV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.OpV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.MAX))

            // For my testing, used GTLiteUtility#getCircuitByTier() will caused
            // circuit cannot be circuitX, so...

            // LV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.LV)
                .outputs(processorCasings[0])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // MV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.MV)
                .outputs(processorCasings[1])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // HV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.HV)
                .outputs(processorCasings[2])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // EV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.EV)
                .outputs(processorCasings[3])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // IV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.IV)
                .outputs(processorCasings[4])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // LuV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .outputs(processorCasings[5])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // ZPM Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .outputs(processorCasings[6])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.UV)
                .outputs(processorCasings[7])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UHV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .outputs(processorCasings[8])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UEV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.UEV)
                .outputs(processorCasings[9])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UIV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.UIV)
                .outputs(processorCasings[10])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UXV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.UXV)
                .outputs(processorCasings[11])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // OpV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.OpV)
                .outputs(processorCasings[12])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // MAX Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.MAX)
                .outputs(processorCasings[13])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun coALCasingRecipes()
        {
            // LV CoAL Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, Steel)
                .input(plateDense, Steel, 4)
                .input(ROBOT_ARM_LV, 4)
                .input(ELECTRIC_PISTON_LV, 8)
                .input(ELECTRIC_MOTOR_LV, 10)
                .input(gear, Steel, 4)
                .input(wireGtQuadruple, Tin, 6)
                .input(circuit, MarkerMaterials.Tier.LV, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.LV, 4))
                .EUt(VA[LV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // MV CoAL Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, Aluminium)
                .input(plateDense, Aluminium, 4)
                .input(ROBOT_ARM_MV, 4)
                .input(ELECTRIC_PISTON_MV, 8)
                .input(ELECTRIC_MOTOR_MV, 10)
                .input(gear, Aluminium, 4)
                .input(wireGtQuadruple, Copper, 6)
                .input(circuit, MarkerMaterials.Tier.MV, 8)
                .input(circuit, MarkerMaterials.Tier.LV, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.MV, 4))
                .EUt(VA[MV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // HV CoAL Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, StainlessSteel)
                .input(plateDense, StainlessSteel, 4)
                .input(ROBOT_ARM_HV, 4)
                .input(ELECTRIC_PISTON_HV, 8)
                .input(ELECTRIC_MOTOR_HV, 10)
                .input(gear, StainlessSteel, 4)
                .input(wireGtQuadruple, Gold, 6)
                .input(circuit, MarkerMaterials.Tier.HV, 8)
                .input(circuit, MarkerMaterials.Tier.MV, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.HV, 4))
                .EUt(VA[HV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // EV CoAL Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, Titanium)
                .input(plateDense, Titanium, 4)
                .input(ROBOT_ARM_EV, 4)
                .input(ELECTRIC_PISTON_EV, 8)
                .input(ELECTRIC_MOTOR_EV, 10)
                .input(gear, Titanium, 4)
                .input(wireGtQuadruple, Aluminium, 6)
                .input(circuit, MarkerMaterials.Tier.EV, 8)
                .input(circuit, MarkerMaterials.Tier.HV, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.EV, 4))
                .EUt(VA[EV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // IV CoAL Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, TungstenSteel)
                .input(plateDense, TungstenSteel, 4)
                .input(ROBOT_ARM_IV, 4)
                .input(ELECTRIC_PISTON_IV, 8)
                .input(ELECTRIC_MOTOR_IV, 10)
                .input(gear, TungstenSteel, 4)
                .input(wireGtQuadruple, Tungsten, 6)
                .input(circuit, MarkerMaterials.Tier.IV, 8)
                .input(circuit, MarkerMaterials.Tier.EV, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.IV, 4))
                .EUt(VA[IV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

        }

        private fun qftCasingsRecipes()
        {
            // Molecular Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.HIGH_POWER_CASING))
                .input(plateDense, Osmiridium, 6)
                .input(foil, Trinium, 12)
                .input(screw, TungstenSteel, 24)
                .input(ring, TungstenSteel, 24)
                .input(FIELD_GENERATOR_IV)
                .fluidInputs(Osmium.getFluid(L * 9))
                .outputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.MOLECULAR_CASING, 2))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Quantum Glass
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.MOLECULAR_CASING))
                .inputs(MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.LAMINATED_GLASS))
                .fluidInputs(Trinium.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.QUANTUM))
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Force Field Glass
            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.QUANTUM))
                .input(FIELD_GENERATOR_ZPM)
                .input(stickLong, PreciousMetalAlloy, 6)
                .input(plate, Polymethylmethacrylate, 6)
                .fluidInputs(QuantumAlloy.getFluid(L * 6))
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.FORCE_FIELD))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Particle Containment Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(frameGt, TantalumCarbide)
                .inputs(GTLiteMetaBlocks.METAL_CASING_01.getItemVariant(BlockMetalCasing01.MetalCasingType.IRIDIUM))
                .input(circuit, MarkerMaterials.Tier.ZPM, 16)
                .input(screw, Inconel625, 32)
                .input(bolt, HafniumCarbide, 12)
                .input(plate, Zeron100, 8)
                .fluidInputs(Trinaquadalloy.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.PARTICLE_CONTAINMENT_CASING, 2))
                .EUt(VA[ZPM].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Hollow Casing
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.MOLECULAR_CASING))
                .inputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE, 2))
                .input(plate, Europium, 2)
                .input(plateDouble, Plutonium239, 4)
                .input(plateDouble, Lead, 8)
                .input(plate, Uranium238, 16)
                .input(screw, Uranium235, 16)
                .fluidInputs(Trinium.getFluid(L * 9))
                .fluidInputs(Osmium.getFluid(L * 9))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
                .fluidInputs(Argon.getFluid(1000))
                .outputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.HOLLOW_CASING, 4))
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.MOLECULAR_CASING))
                        .EUt(VA[UV].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // Molecular Coil
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.HOLLOW_CASING))
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL, 2))
                .inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRINIUM, 2))
                .input(wireFine, Europium, 64)
                .input(foil, Europium, 64)
                .fluidInputs(GSTGlass.getFluid(L * 16))
                .fluidInputs(SiliconeRubber.getFluid(L * 13))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
                .fluidInputs(Trinium.getFluid(L * 9))
                .outputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.MOLECULAR_COIL, 8))
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.HOLLOW_CASING))
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

            // Particle Excitation Wire Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoils.WireCoilType.INFINITY))
                .inputs(GTLiteMetaBlocks.COMPUTER_CASING_01.getItemVariant(BlockComputerCasing01.ComputerCasingType.MOLECULAR_COIL, 2))
                .input(plateDouble, Seaborgium, 4)
                .input(screw, Abyssalloy, 8)
                .outputs(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.PARTICLE_EXCITATION_WIRE_COIL, 2))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Neutron Pulse Manipulator (T1)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.FORCE_FIELD))
                .input(nanite, Carbon, 4)
                .input(EMITTER_UV, 4)
                .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 8)
                .input(QUANTUM_ANOMALY)
                .input(plate, CosmicNeutronium, 2)
                .fluidInputs(Thulium.getFluid(L * 10))
                .fluidInputs(HeavyLeptonMixture.getFluid(5000))
                .fluidInputs(Neptunium.getPlasma(500))
                .fluidInputs(Fermium.getPlasma(500))
                .outputs(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.NEUTRON_PULSE, 16))
                .EUt(VA[UEV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.FORCE_FIELD))
                        .EUt(VA[UEV].toLong())
                        .CWUt(64)
                }
                .buildAndRegister()

            // Cosmic Fabric Manipulator (T2)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.FORCE_FIELD, 2))
                .input(nanite, Carbon, 8)
                .input(EMITTER_UHV, 4)
                .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 8)
                .input(QUANTUM_ANOMALY)
                .input(plate, DegenerateRhenium, 4)
                .input(STABLE_ADHESIVE, 4)
                .fluidInputs(Thulium.getFluid(L * 12))
                .fluidInputs(QuarkGluonPlasma.getFluid(5000))
                .fluidInputs(Neptunium.getPlasma(2500))
                .fluidInputs(Fermium.getPlasma(2500))
                .outputs(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.COSMIC_FABRIC, 16))
                .EUt(VA[UIV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.NEUTRON_PULSE))
                        .EUt(VA[UIV].toLong())
                        .CWUt(96)
                }
                .buildAndRegister()

            // Infinity Infused Manipulator (T3)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.FORCE_FIELD, 4))
                .input(nanite, Carbon, 16)
                .input(EMITTER_UEV, 4)
                .input(wireGtHex, FullereneSuperconductor, 8)
                .input(QUANTUM_ANOMALY)
                .input(plate, Hypogen, 8)
                .input(SUPERCONDUCTOR_COMPOSITE, 4)
                .fluidInputs(Thulium.getFluid(L * 15))
                .fluidInputs(HighEnergyQuarkGluonPlasma.getFluid(5000))
                .fluidInputs(Neptunium.getPlasma(10000))
                .fluidInputs(Fermium.getPlasma(10000))
                .outputs(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.INFINITY_INFUSED, 16))
                .EUt(VA[UXV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.COSMIC_FABRIC))
                        .EUt(VA[UXV].toLong())
                        .CWUt(128)
                }
                .buildAndRegister()

            // Spacetime Continuum Ripper (T4)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.FORCE_FIELD, 8))
                .input(nanite, Carbon, 32)
                .input(EMITTER_UIV, 4)
                .input(wireGtHex, SuperheavyAlloyB, 8) // TODO UXV Superconductor
                .input(QUANTUM_ANOMALY)
                .input(plate, TranscendentMetal, 16)
                .input(NAQUADRIA_SUPERSOLID, 4)
                .fluidInputs(Thulium.getFluid(L * 20))
                .fluidInputs(SpaceTime.getFluid(5000))
                .fluidInputs(Neptunium.getPlasma(20000))
                .fluidInputs(Fermium.getPlasma(20000))
                .outputs(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.SPACETIME_CONTINUUM_RIPPER, 16))
                .EUt(VA[OpV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.INFINITY_INFUSED))
                        .EUt(VA[OpV].toLong())
                        .CWUt(160)
                }
                .buildAndRegister()

            // Neutron Shielding Core (T1)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, QuantumAlloy)
                .input(plateDense, EnrichedNaquadahAlloy, 4)
                .input(plateDense, Neutronium, 2)
                .input(FIELD_GENERATOR_UV)
                .input(screw, HastelloyX78, 16)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 10))
                .outputs(GTLiteMetaBlocks.SHIELDING_CORE.getItemVariant(BlockShieldingCore.ShieldingCoreType.NEUTRON, 16))
                .EUt(VA[UEV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getItemVariant(BlockMultiblockCasing01.MultiblockCasingType.PARTICLE_CONTAINMENT_CASING))
                        .EUt(VA[UEV].toLong())
                        .CWUt(64)
                }
                .buildAndRegister()

            // Cosmic Fabric Shielding Core (T2)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, QuantumAlloy, 2)
                .input(plateDense, PreciousMetalAlloy, 4)
                .input(plateDense, CosmicNeutronium, 2)
                .input(FIELD_GENERATOR_UHV)
                .input(screw, HastelloyK243, 16)
                .input(STABLE_ADHESIVE, 4)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 20))
                .outputs(GTLiteMetaBlocks.SHIELDING_CORE.getItemVariant(BlockShieldingCore.ShieldingCoreType.COSMIC_FABRIC, 16))
                .EUt(VA[UIV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.SHIELDING_CORE.getItemVariant(BlockShieldingCore.ShieldingCoreType.NEUTRON))
                        .EUt(VA[UIV].toLong())
                        .CWUt(96)
                }
                .buildAndRegister()

            // Infinity Infused Shielding Core (T3)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, QuantumAlloy, 4)
                .input(plateDense, ArceusAlloy2B, 4)
                .input(plateDense, Hypogen, 2)
                .input(FIELD_GENERATOR_UEV)
                .input(screw, HalkoniteSteel, 16)
                .input(SUPERCONDUCTOR_COMPOSITE, 4)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
                .outputs(GTLiteMetaBlocks.SHIELDING_CORE.getItemVariant(BlockShieldingCore.ShieldingCoreType.INFINITY_INFUSED, 16))
                .EUt(VA[UXV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.SHIELDING_CORE.getItemVariant(BlockShieldingCore.ShieldingCoreType.COSMIC_FABRIC))
                        .EUt(VA[UXV].toLong())
                        .CWUt(128)
                }
                .buildAndRegister()

            // Spacetime Bending Core (T4)
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, QuantumAlloy, 8)
                .input(plateDense, Shirabon, 4)
                .input(plateDense, SpaceTime, 2)
                .input(FIELD_GENERATOR_UIV)
                .input(screw, TranscendentMetal, 16)
                .input(NAQUADRIA_SUPERSOLID, 4)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
                .outputs(GTLiteMetaBlocks.SHIELDING_CORE.getItemVariant(BlockShieldingCore.ShieldingCoreType.SPACETIME_BENDING_CORE, 16))
                .EUt(VA[OpV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.SHIELDING_CORE.getItemVariant(BlockShieldingCore.ShieldingCoreType.INFINITY_INFUSED))
                        .EUt(VA[OpV].toLong())
                        .CWUt(160)
                }
                .buildAndRegister()

        }

        private fun itemHatchesRecipes()
        {
            // Remove original recipes for UHV Item Import/Export Bus.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(HULL[UHV].stackForm, QUANTUM_CHEST[LV].stackForm,
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Polybenzimidazole.getFluid(L * 5)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(HULL[UHV].stackForm, QUANTUM_CHEST[LV].stackForm,
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Polybenzimidazole.getFluid(L * 5)))

            // Add recipes for UHV-OpV Item Import/Export Bus, and add high-tier plastics recipes for
            // the original recipes.
            addIOHatchRecipes(ULV, ITEM_IMPORT_BUS[ULV], ITEM_EXPORT_BUS[ULV], OreDictNames.chestWood.toString())
            addIOHatchRecipes(LV,  ITEM_IMPORT_BUS[LV],  ITEM_EXPORT_BUS[LV],  OreDictNames.chestWood.toString())
            addIOHatchRecipes(MV,  ITEM_IMPORT_BUS[MV],  ITEM_EXPORT_BUS[MV],  BRONZE_CRATE.stackForm)
            addIOHatchRecipes(HV,  ITEM_IMPORT_BUS[HV],  ITEM_EXPORT_BUS[HV],  STEEL_CRATE.stackForm)
            addIOHatchRecipes(EV,  ITEM_IMPORT_BUS[EV],  ITEM_EXPORT_BUS[EV],  ALUMINIUM_CRATE.stackForm)
            addIOHatchRecipes(IV,  ITEM_IMPORT_BUS[IV],  ITEM_EXPORT_BUS[IV],  STAINLESS_STEEL_CRATE.stackForm)
            addIOHatchRecipes(LuV, ITEM_IMPORT_BUS[LuV], ITEM_EXPORT_BUS[LuV], TITANIUM_CRATE.stackForm)
            addIOHatchRecipes(ZPM, ITEM_IMPORT_BUS[ZPM], ITEM_EXPORT_BUS[ZPM], TUNGSTENSTEEL_CRATE.stackForm)
            addIOHatchRecipes(UV,  ITEM_IMPORT_BUS[UV],  ITEM_EXPORT_BUS[UV],  QUANTUM_CHEST[ULV].stackForm)
            addIOHatchRecipes(UHV, ITEM_IMPORT_BUS[UHV], ITEM_EXPORT_BUS[UHV], QUANTUM_CHEST[LV].stackForm)
            addIOHatchRecipes(UEV, ITEM_IMPORT_BUS[UEV], ITEM_EXPORT_BUS[UEV], QUANTUM_CHEST[MV].stackForm)
            addIOHatchRecipes(UIV, ITEM_IMPORT_BUS[UIV], ITEM_EXPORT_BUS[UIV], QUANTUM_CHEST[HV].stackForm)
            addIOHatchRecipes(UXV, ITEM_IMPORT_BUS[UXV], ITEM_EXPORT_BUS[UXV], QUANTUM_CHEST[EV].stackForm)
            addIOHatchRecipes(OpV, ITEM_IMPORT_BUS[OpV], ITEM_EXPORT_BUS[OpV], QUANTUM_CHEST[IV].stackForm)
        }

        private fun fluidHatchesRecipes()
        {
            // Remove original recipes for UHV Fluid Import/Export Hatch.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(HULL[UHV].stackForm, QUANTUM_TANK[LV].stackForm,
                    IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(Polybenzimidazole.getFluid(L * 5)))

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(HULL[UHV].stackForm, QUANTUM_TANK[LV].stackForm,
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Polybenzimidazole.getFluid(L * 5)))

            // Add recipes for UHV-OpV Fluid Import/Export Hatch, and add high-tier plastics recipes for
            // the original recipes.
            addIOHatchRecipes(ULV, FLUID_IMPORT_HATCH[ULV], FLUID_EXPORT_HATCH[ULV], ItemStack(Blocks.GLASS))
            addIOHatchRecipes(LV,  FLUID_IMPORT_HATCH[LV],  FLUID_EXPORT_HATCH[LV],  ItemStack(Blocks.GLASS))
            addIOHatchRecipes(MV,  FLUID_IMPORT_HATCH[MV],  FLUID_EXPORT_HATCH[MV],  BRONZE_DRUM.stackForm)
            addIOHatchRecipes(HV,  FLUID_IMPORT_HATCH[HV],  FLUID_EXPORT_HATCH[HV],  STEEL_DRUM.stackForm)
            addIOHatchRecipes(EV,  FLUID_IMPORT_HATCH[EV],  FLUID_EXPORT_HATCH[EV],  ALUMINIUM_DRUM.stackForm)
            addIOHatchRecipes(IV,  FLUID_IMPORT_HATCH[IV],  FLUID_EXPORT_HATCH[IV],  STAINLESS_STEEL_DRUM.stackForm)
            addIOHatchRecipes(LuV, FLUID_IMPORT_HATCH[LuV], FLUID_EXPORT_HATCH[LuV], TITANIUM_DRUM.stackForm)
            addIOHatchRecipes(ZPM, FLUID_IMPORT_HATCH[ZPM], FLUID_EXPORT_HATCH[ZPM], TUNGSTENSTEEL_DRUM.stackForm)
            addIOHatchRecipes(UV,  FLUID_IMPORT_HATCH[UV],  FLUID_EXPORT_HATCH[UV],  QUANTUM_TANK[ULV].stackForm)
            addIOHatchRecipes(UHV, FLUID_IMPORT_HATCH[UHV], FLUID_EXPORT_HATCH[UHV], QUANTUM_TANK[LV].stackForm)
            addIOHatchRecipes(UEV, FLUID_IMPORT_HATCH[UEV], FLUID_EXPORT_HATCH[UEV], QUANTUM_TANK[MV].stackForm)
            addIOHatchRecipes(UIV, FLUID_IMPORT_HATCH[UIV], FLUID_EXPORT_HATCH[UIV], QUANTUM_TANK[HV].stackForm)
            addIOHatchRecipes(UXV, FLUID_IMPORT_HATCH[UXV], FLUID_EXPORT_HATCH[UXV], QUANTUM_TANK[EV].stackForm)
            addIOHatchRecipes(OpV, FLUID_IMPORT_HATCH[OpV], FLUID_EXPORT_HATCH[OpV], QUANTUM_TANK[IV].stackForm)

            // ULV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[ULV])
                .input(pipeQuadrupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(1000))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // LV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[LV])
                .input(pipeQuadrupleFluid, Copper)
                .fluidInputs(Glue.getFluid(2000))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // MV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[MV])
                .input(pipeQuadrupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // HV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[HV])
                .input(pipeQuadrupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // ULV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[ULV])
                .input(pipeQuadrupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(1000))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // LV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[LV])
                .input(pipeQuadrupleFluid, Copper)
                .fluidInputs(Glue.getFluid(2000))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // MV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[MV])
                .input(pipeQuadrupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // HV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[HV])
                .input(pipeQuadrupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // ULV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[ULV])
                .input(pipeNonupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(2250))
                .output(NONUPLE_FLUID_IMPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // LV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[LV])
                .input(pipeNonupleFluid, Copper)
                .fluidInputs(Glue.getFluid(4500))
                .output(NONUPLE_FLUID_IMPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // MV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[MV])
                .input(pipeNonupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_IMPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // HV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[HV])
                .input(pipeNonupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_IMPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // ULV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[ULV])
                .input(pipeNonupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(2250))
                .output(NONUPLE_FLUID_EXPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // LV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[LV])
                .input(pipeNonupleFluid, Copper)
                .fluidInputs(Glue.getFluid(4500))
                .output(NONUPLE_FLUID_EXPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // MV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[MV])
                .input(pipeNonupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_EXPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // HV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[HV])
                .input(pipeNonupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_EXPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()
        }

        private fun energyHatchesRecipes()
        {
            // ...
        }

        private fun dynamoHatchesRecipes()
        {
            // ...
        }

        private fun hiAmpEnergyHatchesRecipes()
        {
            // ULV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[ULV])
                .input(wireGtQuadruple, RedAlloy, 2)
                .input(plate, WroughtIron, 2)
                .output(ENERGY_HATCH_4A[0])
                .EUt(1L)
                .duration(5 * SECOND)
                .buildAndRegister()

            // LV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[LV])
                .input(wireGtQuadruple, Tin, 2)
                .input(plate, Steel, 2)
                .output(ENERGY_HATCH_4A[1])
                .EUt(VA[ULV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // MV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[MV])
                .input(wireGtQuadruple, Copper, 2)
                .input(plate, Aluminium, 2)
                .output(ENERGY_HATCH_4A[2])
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // HV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[HV])
                .input(wireGtQuadruple, Gold, 2)
                .input(plate, StainlessSteel, 2)
                .output(ENERGY_HATCH_4A[3])
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // ULV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[ULV])
                .input(ENERGY_HATCH_4A[0])
                .input(wireGtOctal, RedAlloy, 2)
                .input(plate, WroughtIron, 4)
                .output(ENERGY_HATCH_16A[0])
                .EUt(1L)
                .duration(10 * SECOND)
                .buildAndRegister()

            // LV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[LV])
                .input(ENERGY_HATCH_4A[1])
                .input(wireGtOctal, Tin, 2)
                .input(plate, Steel, 4)
                .output(ENERGY_HATCH_16A[1])
                .EUt(VA[ULV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // MV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[MV])
                .input(ENERGY_HATCH_4A[2])
                .input(wireGtOctal, Copper, 2)
                .input(plate, Aluminium, 4)
                .output(ENERGY_HATCH_16A[2])
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // HV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[HV])
                .input(ENERGY_HATCH_4A[3])
                .input(wireGtOctal, Gold, 2)
                .input(plate, StainlessSteel, 4)
                .output(ENERGY_HATCH_16A[3])
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // EV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[EV])
                .input(ENERGY_INPUT_HATCH_4A[0])
                .input(wireGtOctal, Aluminium, 2)
                .input(plate, Titanium, 4)
                .output(ENERGY_HATCH_16A[4])
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // ULV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[ULV])
                .input(ENERGY_HATCH_16A[0])
                .input(wireGtHex, RedAlloy, 2)
                .input(plate, WroughtIron, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[0])
                .EUt(1L)
                .duration(20 * SECOND)
                .buildAndRegister()

            // LV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[LV])
                .input(ENERGY_HATCH_16A[1])
                .input(wireGtHex, Tin, 2)
                .input(plate, Steel, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[1])
                .EUt(VA[ULV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // MV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[MV])
                .input(ENERGY_HATCH_16A[2])
                .input(wireGtHex, Copper, 2)
                .input(plate, Aluminium, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[2])
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // HV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[HV])
                .input(ENERGY_HATCH_16A[3])
                .input(wireGtHex, Gold, 2)
                .input(plate, StainlessSteel, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[3])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // EV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[EV])
                .input(ENERGY_HATCH_16A[4])
                .input(wireGtHex, Aluminium, 2)
                .input(plate, Titanium, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[4])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()
        }

        private fun hiAmpDynamoHatchesRecipes()
        {
            // ULV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[ULV])
                .input(wireGtQuadruple, RedAlloy, 2)
                .input(plate, WroughtIron, 2)
                .output(DYNAMO_HATCH_4A[0])
                .EUt(1L)
                .duration(5 * SECOND)
                .buildAndRegister()

            // LV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[LV])
                .input(wireGtQuadruple, Tin, 2)
                .input(plate, Steel, 2)
                .output(DYNAMO_HATCH_4A[1])
                .EUt(VA[ULV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // MV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[MV])
                .input(wireGtQuadruple, Copper, 2)
                .input(plate, Aluminium, 2)
                .output(DYNAMO_HATCH_4A[2])
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // HV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[HV])
                .input(wireGtQuadruple, Gold, 2)
                .input(plate, StainlessSteel, 2)
                .output(DYNAMO_HATCH_4A[3])
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // ULV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[ULV])
                .input(DYNAMO_HATCH_4A[0])
                .input(wireGtOctal, RedAlloy, 2)
                .input(plate, WroughtIron, 4)
                .output(DYNAMO_HATCH_16A[0])
                .EUt(1L)
                .duration(10 * SECOND)
                .buildAndRegister()

            // LV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[LV])
                .input(DYNAMO_HATCH_4A[1])
                .input(wireGtOctal, Tin, 2)
                .input(plate, Steel, 4)
                .output(DYNAMO_HATCH_16A[1])
                .EUt(VA[ULV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // MV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[MV])
                .input(DYNAMO_HATCH_4A[2])
                .input(wireGtOctal, Copper, 2)
                .input(plate, Aluminium, 4)
                .output(DYNAMO_HATCH_16A[2])
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // HV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[HV])
                .input(DYNAMO_HATCH_4A[3])
                .input(wireGtOctal, Gold, 2)
                .input(plate, StainlessSteel, 4)
                .output(DYNAMO_HATCH_16A[3])
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // EV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[EV])
                .input(ENERGY_OUTPUT_HATCH_4A[0])
                .input(wireGtOctal, Aluminium, 2)
                .input(plate, Titanium, 4)
                .output(DYNAMO_HATCH_16A[4])
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // ULV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[ULV])
                .input(DYNAMO_HATCH_16A[0])
                .input(wireGtHex, RedAlloy, 2)
                .input(plate, WroughtIron, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[0])
                .EUt(1L)
                .duration(20 * SECOND)
                .buildAndRegister()

            // LV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[LV])
                .input(DYNAMO_HATCH_16A[1])
                .input(wireGtHex, Tin, 2)
                .input(plate, Steel, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[1])
                .EUt(VA[ULV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // MV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[MV])
                .input(DYNAMO_HATCH_16A[2])
                .input(wireGtHex, Copper, 2)
                .input(plate, Aluminium, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[2])
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // HV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[HV])
                .input(DYNAMO_HATCH_16A[3])
                .input(wireGtHex, Gold, 2)
                .input(plate, StainlessSteel, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[3])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // EV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[EV])
                .input(DYNAMO_HATCH_16A[4])
                .input(wireGtHex, Aluminium, 2)
                .input(plate, Titanium, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[4])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()
        }

        private fun transformersRecipes()
        {
            // UHV Adjustable Transformer
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(HI_AMP_TRANSFORMER[UHV])
                .input(ELECTRIC_PUMP_IV)
                .input(cableGtOctal, Seaborgium)
                .input(cableGtOctal, Europium, 2)
                .input(springSmall, Europium)
                .input(spring, Seaborgium)
                .fluidInputs(Lubricant.getFluid(2000))
                .output(POWER_TRANSFORMER[UHV])
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun laserHatchesRecipes()
        {
            // Add recipes for original laser hatches in GTCEu.
            for (tier in UHV..UXV) // TODO OpV-MAX recipes
            {
                val actualTier = tier - IV // Because laser hatch start at IV stage.
                // 256A Laser Target Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(HULL[tier])
                    .input(lens, Diamond)
                    .inputs(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack)
                    .inputs(CraftingComponent.PUMP.getIngredient(tier) as ItemStack)
                    .input(cableGtSingle, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_INPUT_HATCH_256[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(15 * SECOND)
                    .buildAndRegister()

                // 256A Laser Source Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(HULL[tier])
                    .input(lens, Diamond)
                    .inputs(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack)
                    .inputs(CraftingComponent.PUMP.getIngredient(tier) as ItemStack)
                    .input(cableGtSingle, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_OUTPUT_HATCH_256[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(15 * SECOND)
                    .buildAndRegister()

                // 1024A Laser Target Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(HULL[tier])
                    .input(lens, Diamond, 2)
                    .inputs(copy(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack, 2))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 2))
                    .input(cableGtDouble, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_INPUT_HATCH_1024[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // 1024A Laser Source Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(HULL[tier])
                    .input(lens, Diamond, 2)
                    .inputs(copy(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack, 2))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 2))
                    .input(cableGtDouble, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_OUTPUT_HATCH_1024[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // 4096A Laser Target Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(HULL[tier])
                    .input(lens, Diamond, 4)
                    .inputs(copy(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack, 4))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 4))
                    .input(cableGtQuadruple, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_INPUT_HATCH_4096[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // 4096A Large Source Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(HULL[tier])
                    .input(lens, Diamond, 4)
                    .inputs(copy(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack, 4))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 4))
                    .input(cableGtQuadruple, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_OUTPUT_HATCH_4096[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()
            }
            // Advanced laser hatches consists of 16384A-1048576A, these recipes is added
            // for these parts.
            for (tier in IV..UXV) // TODO OpV-MAX recipes
            {
                val actualTier = tier - IV // Because laser hatch start at IV stage.
                // 16384A Laser Target Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(HULL[tier])
                    .input(lens, Diamond, 8)
                    .inputs(copy(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack, 8))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 8))
                    .input(cableGtOctal, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_INPUT_HATCH_16384[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // 16384A Laser Source Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(HULL[tier])
                    .input(lens, Diamond, 8)
                    .inputs(copy(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack, 8))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 8))
                    .input(cableGtOctal, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_OUTPUT_HATCH_16384[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // 65536A Laser Target Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(5)
                    .input(HULL[tier])
                    .input(lens, Diamond, 16)
                    .inputs(copy(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack, 16))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 16))
                    .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_INPUT_HATCH_65536[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(4 * MINUTE)
                    .buildAndRegister()

                // 65536A Laser Source Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(5)
                    .input(HULL[tier])
                    .input(lens, Diamond, 16)
                    .inputs(copy(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack, 16))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 16))
                    .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                    .output(LASER_OUTPUT_HATCH_65536[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(4 * MINUTE)
                    .buildAndRegister()

                // 262144A Laser Target Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .input(HULL[tier])
                    .input(lens, Diamond, 32)
                    .inputs(copy(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack, 32))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 32))
                    .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 8)
                    .output(LASER_INPUT_HATCH_262144[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(8 * MINUTE)
                    .buildAndRegister()

                // 262144A Laser Source Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .input(HULL[tier])
                    .input(lens, Diamond, 32)
                    .inputs(copy(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack, 32))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 32))
                    .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 8)
                    .output(LASER_OUTPUT_HATCH_262144[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(8 * MINUTE)
                    .buildAndRegister()

                // 1048576A Laser Target Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(7)
                    .input(HULL[tier])
                    .input(lens, Diamond, 64)
                    .inputs(copy(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack, 64))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 64))
                    .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 16)
                    .output(LASER_INPUT_HATCH_1048576[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(16 * MINUTE)
                    .buildAndRegister()

                // 1048576A Laser Source Hatch
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(7)
                    .input(HULL[tier])
                    .input(lens, Diamond, 64)
                    .inputs(copy(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack, 64))
                    .inputs(copy(CraftingComponent.PUMP.getIngredient(tier) as ItemStack, 64))
                    .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 16)
                    .output(LASER_OUTPUT_HATCH_1048576[actualTier])
                    .EUt(VA[tier].toLong())
                    .duration(16 * MINUTE)
                    .buildAndRegister()
            }

        }

        private fun wireCoilRecipes()
        {
            // Make wire coils easier than original recipes, these new recipes do not
            // need metal foils and instead of mica insulator foils.

            // Cupronickel Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Cupronickel, 8),
                    OreDictUnifier.get(foil, Bronze, 8)),
                arrayOf(TinAlloy.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Cupronickel, 8)
                .input(MICA_INSULATOR_FOIL, 8)
                .fluidInputs(TinAlloy.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL, 1))
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Kanthal Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Kanthal, 8),
                    OreDictUnifier.get(foil, Aluminium, 8)),
                arrayOf(Copper.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Kanthal, 8)
                .input(MICA_INSULATOR_FOIL, 12)
                .fluidInputs(Copper.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL, 1))
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Nichrome Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Nichrome, 8),
                    OreDictUnifier.get(foil, StainlessSteel, 8)),
                arrayOf(Aluminium.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Nichrome, 8)
                .input(MICA_INSULATOR_FOIL, 16)
                .fluidInputs(Aluminium.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME, 1))
                .EUt(VA[HV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // RTM Alloy Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, RTMAlloy, 8),
                    OreDictUnifier.get(foil, VanadiumSteel, 8)),
                arrayOf(Nichrome.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, RTMAlloy, 8)
                .input(MICA_INSULATOR_FOIL, 20)
                .fluidInputs(Titanium.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.RTM_ALLOY, 1))
                .EUt(VA[EV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // HSS-G Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, HSSG, 8),
                    OreDictUnifier.get(foil, TungstenCarbide, 8)),
                arrayOf(Tungsten.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, HSSG, 8)
                .input(MICA_INSULATOR_FOIL, 24)
                .fluidInputs(Tungsten.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G, 1))
                .EUt(VA[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Naquadah Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Naquadah, 8),
                    OreDictUnifier.get(foil, Osmium, 8)),
                arrayOf(TungstenSteel.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Naquadah, 8)
                .input(MICA_INSULATOR_FOIL, 28)
                .fluidInputs(TungstenSteel.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH, 1))
                .EUt(VA[LuV].toLong())
                .duration(35 * SECOND)
                .buildAndRegister()

            // Trinium Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Trinium, 8),
                    OreDictUnifier.get(foil, NaquadahEnriched, 8)),
                arrayOf(Naquadah.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Trinium, 8)
                .input(MICA_INSULATOR_FOIL, 32)
                .fluidInputs(Naquadah.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRINIUM, 1))
                .EUt(VA[ZPM].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Tritanium Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Tritanium, 8),
                    OreDictUnifier.get(foil, Naquadria, 8)),
                arrayOf(Trinium.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Tritanium, 8)
                .input(MICA_INSULATOR_FOIL, 36)
                .fluidInputs(NaquadahEnriched.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRITANIUM, 1))
                .EUt(VA[UV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Adamantium Wire Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Adamantium, 8)
                .input(MICA_INSULATOR_FOIL, 48)
                .fluidInputs(Naquadria.getFluid(L))
                .outputs(GTLiteMetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoils.WireCoilType.ADAMANTIUM))
                .EUt(VA[UHV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()
        }

        private fun crucibleRecipes()
        {
            // Bronze Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Bronze, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.BRONZE_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Invar Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Invar, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.INVAR_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Quartzite Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Quartzite, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.QUARTZ_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Chrome Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Chrome, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.CHROME_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Vanadium Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Vanadium, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.VANADIUM_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Niobium Titanium Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, NiobiumTitanium, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.NIOBIUM_TITANIUM_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Iridium Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Iridium, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.IRIDIUM_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Molybdenum Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Molybdenum, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.MOLYBDENUM_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Tungsten Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Tungsten, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.TUNGSTEN_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Osmium Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Osmium, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.OSMIUM_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Graphite Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, Graphene, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.GRAPHITE_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

            // Boron Nitride Crucible
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(plate, AmorphousBoronNitride, 7)
                .outputs(GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.BORON_NITRIDE_CRUCIBLE))
                .EUt(4) // ULV
                .duration(35 * SECOND)
                .buildAndRegister()

        }

        private fun voltageCoilRecipes()
        {
            // UHV Voltage Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, ChromiumGermaniumTellurideMagnetic)
                .input(wireFine, Taranium, 16)
                .output(VOLTAGE_COIL_UHV)
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // UEV Voltage Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, ChromiumGermaniumTellurideMagnetic)
                .input(wireFine, MetastableOganesson, 16)
                .output(VOLTAGE_COIL_UEV)
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // UIV Voltage Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, Magnetium)
                .input(wireFine, QuantumchromodynamicallyConfinedMatter, 16)
                .output(VOLTAGE_COIL_UIV)
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // UXV Voltage Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, Magnetium)
                .input(wireFine, Mellion, 16)
                .output(VOLTAGE_COIL_UXV)
                .EUt(VA[UXV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // OpV Voltage Coil
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, Magnetium)
                .input(wireFine, WhiteDwarfMatter, 16)
                .output(VOLTAGE_COIL_OpV)
                .EUt(VA[OpV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun miningDroneRecipes()
        {
            // LV Mining Drone
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(circuit, MarkerMaterials.Tier.LV)
                .input(toolHeadDrill, Steel, 2)
                .input(EMITTER_LV)
                .input(SENSOR_LV)
                .input(rotor, Steel)
                .input(cableGtSingle, Tin, 2)
                .fluidInputs(TinAlloy.getFluid(L * 4))
                .output(MINING_DRONE_LV)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // MV Mining Drone
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(frameGt, Aluminium)
                .input(plate, Aluminium, 4)
                .input(circuit, MarkerMaterials.Tier.MV)
                .input(toolHeadDrill, Aluminium, 2)
                .input(EMITTER_MV)
                .input(SENSOR_MV)
                .input(rotor, Aluminium)
                .input(cableGtSingle, Copper, 2)
                .fluidInputs(Kanthal.getFluid(L * 4))
                .output(MINING_DRONE_MV)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // HV Mining Drone
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(frameGt, StainlessSteel)
                .input(plate, StainlessSteel, 6)
                .input(circuit, MarkerMaterials.Tier.HV)
                .input(toolHeadDrill, StainlessSteel, 4)
                .input(EMITTER_HV)
                .input(SENSOR_HV)
                .input(rotor, StainlessSteel, 2)
                .input(cableGtSingle, Gold, 2)
                .fluidInputs(Nichrome.getFluid(L * 4))
                .output(MINING_DRONE_HV)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // EV Mining Drone
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(frameGt, Titanium)
                .input(plate, Titanium, 6)
                .input(circuit, MarkerMaterials.Tier.EV)
                .input(toolHeadDrill, Titanium, 4)
                .input(EMITTER_EV)
                .input(SENSOR_EV)
                .input(rotor, Titanium, 2)
                .input(cableGtSingle, Aluminium, 2)
                .fluidInputs(TungstenCarbide.getFluid(L * 4))
                .output(MINING_DRONE_EV)
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // IV Mining Drone
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(frameGt, TungstenSteel)
                .input(plate, TungstenSteel, 8)
                .input(circuit, MarkerMaterials.Tier.IV)
                .input(toolHeadDrill, TungstenSteel, 6)
                .input(EMITTER_IV)
                .input(SENSOR_IV)
                .input(rotor, TungstenSteel, 4)
                .input(cableGtSingle, Platinum, 2)
                .fluidInputs(Ultimet.getFluid(L * 4))
                .output(MINING_DRONE_IV)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun miscItemsRecipes()
        {
            // Circuit Pattern
            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .input(TOOL_DATA_STICK)
                .input(foil, Indium, 2)
                .input(wireFine, SiliconCarbide, 8)
                .input(screw, Zircaloy4, 6)
                .fluidInputs(Iridium.getFluid(L * 8))
                .fluidInputs(PCBCoolant.getFluid(500))
                .output(CIRCUIT_PATTERN)
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Magnetron
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ring, BerylliumOxide, 64)
                .input(ring, BerylliumOxide, 64)
                .input(plate, HSLASteel, 6)
                .input(VACUUM_TUBE)
                .output(MAGNETRON)
                .EUt(VA[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Advanced recipe for Neutron Reflector. TODO Should we add a more higher advanced recipes?
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Osmiridium)
                .input(plateDouble, BerylliumOxide, 4)
                .input(plateDouble, TitaniumTungstenCarbide, 2)
                .fluidInputs(TinAlloy.getFluid(L * 16))
                .output(NEUTRON_REFLECTOR, 4)
                .EUt(VA[EV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

        }

        private fun vanillaChangingRecipes()
        {
            // Elytra (vanilla)
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Polytetrafluoroethylene, 2)
                .input(plate, StainlessSteel)
                .input(ring, StainlessSteel, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(ItemStack(Items.ELYTRA))
                .EUt(VH[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}