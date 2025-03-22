package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Potin
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TreatedWood
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalItem
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
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
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.BlockSteamCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_EV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_HV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_MV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_EV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_EV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_HV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_EV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_HV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_MV
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_HV
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.metatileentities.MetaTileEntities.ALLOY_SMELTER
import gregtech.common.metatileentities.MetaTileEntities.ARC_FURNACE
import gregtech.common.metatileentities.MetaTileEntities.ASSEMBLER
import gregtech.common.metatileentities.MetaTileEntities.AUTOCLAVE
import gregtech.common.metatileentities.MetaTileEntities.BENDER
import gregtech.common.metatileentities.MetaTileEntities.BREWERY
import gregtech.common.metatileentities.MetaTileEntities.CENTRIFUGE
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
import gregtech.common.metatileentities.MetaTileEntities.PYROLYSE_OVEN
import gregtech.common.metatileentities.MetaTileEntities.ROCK_BREAKER
import gregtech.common.metatileentities.MetaTileEntities.SIFTER
import gregtech.common.metatileentities.MetaTileEntities.THERMAL_CENTRIFUGE
import gregtech.common.metatileentities.MetaTileEntities.VACUUM_FREEZER
import gregtech.common.metatileentities.MetaTileEntities.WIREMILL
import gregtech.loaders.recipe.CraftingComponent
import gregtech.loaders.recipe.MetaTileEntityLoader
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumBronze
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BabbitAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EglinSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Grisium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyN
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA956
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Inconel625
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MaragingSteel250
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MolybdenumDisilicide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Staballoy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Talonite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tumbaga
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WatertightSteel
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import magicbook.gtlitecore.common.block.blocks.BlockPrimitiveCasing
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ALLOY_BLAST_SMELTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BATH_CONDENSER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BIO_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BUFFER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.BURNER_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CATALYTIC_REFORMER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHEMICAL_DEHYDRATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHEMICAL_PLANT
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COAGULATION_TANK
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CRYOGENIC_REACTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ELECTRIC_IMPLOSION_COMPRESSOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.GREENHOUSE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.INDUSTRIAL_COKE_OVEN
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
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_FORGE_HAMMER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_GAS_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_LASER_ENGRAVER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_MACERATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_MIXER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ORE_WASHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_PACKER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_ROCK_BREAKER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_SIFTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LARGE_WIREMILL
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LOOM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.POLISHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ROASTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SAP_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SLICER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_ROASTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_SAP_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_VACUUM_CHAMBER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_VULCANIZING_PRESS
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TANK_BRIDGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TANK_EXTENDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TOOL_CASTER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.UNIVERSAL_BRIDGE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.UNIVERSAL_EXTENDER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.VACUUM_CHAMBER
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

        }

    }

}