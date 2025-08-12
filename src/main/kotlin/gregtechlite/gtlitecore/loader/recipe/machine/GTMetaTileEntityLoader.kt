package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalItem
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.blocks.BlockSteamCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_EV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_EV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH_16A
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH_4A
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH_16A
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH_4A
import gregtech.common.metatileentities.MetaTileEntities.HI_AMP_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.common.metatileentities.MetaTileEntities.POWER_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.SUBSTATION_ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.SUBSTATION_ENERGY_OUTPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.TRANSFORMER
import gregtech.loaders.recipe.CraftingComponent
import gregtech.loaders.recipe.MetaTileEntityLoader
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_EMPTY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UXV
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BATH_CONDENSER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BIO_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BIO_SIMULATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BUFFER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.BURNER_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CHEMICAL_DEHYDRATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CHROME_DRUM
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.COPPER_CRATE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.COPPER_DRUM
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.CRYOGENIC_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.DIAMOND_CRATE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.FOOD_PROCESSOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.GOLD_CRATE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.GREENHOUSE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.INVENTORY_BRIDGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.INVENTORY_EXTENDER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.INVENTORY_TANK_BRIDGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.INVENTORY_TANK_EXTENDER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.IRIDIUM_DRUM
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.IRON_CRATE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.IRON_DRUM
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LAMINATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LEAD_DRUM
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LOOM
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.MASS_FABRICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.MOB_EXTRACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.MULTICOOKER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NAQUADAH_REACTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.POLISHER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.REPLICATOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ROASTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.ROCKET_ENGINE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SAP_COLLECTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SILVER_CRATE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.SLICER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.STEAM_ROASTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.STEAM_SAP_COLLECTOR
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.STEAM_VACUUM_CHAMBER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.STEAM_VULCANIZING_PRESS
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.TANK_BRIDGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.TANK_EXTENDER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.TOOL_CASTER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.TUNGSTEN_DRUM
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.UNIVERSAL_BRIDGE
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.UNIVERSAL_EXTENDER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.VACUUM_CHAMBER
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.VULCANIZING_PRESS
import gregtechlite.gtlitecore.loader.recipe.component.CraftingComponents
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

internal object GTMetaTileEntityLoader
{

    // @formatter:off

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
        ModHandler.addShapedRecipe(true, "vulcanizing_press.bronze", STEAM_VULCANIZING_PRESS[0].stackForm,
            "DSG", "QHQ", "PPP",
            'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL),
            'S', UnificationEntry(springSmall, Iron),
            'P', UnificationEntry(pipeNormalFluid, Bronze),
            'D', UnificationEntry(stick, Iron),
            'G', ItemStack(Blocks.GLASS),
            'Q', ItemStack(Blocks.PISTON))

        ModHandler.addShapedRecipe(true, "vulcanizing_press.steel", STEAM_VULCANIZING_PRESS[1].stackForm,
            "SXS", "PVP", "QQQ",
            'V', STEAM_VULCANIZING_PRESS[0].stackForm,
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
        ModHandler.addShapedRecipe(true, "vacuum_chamber.bronze", STEAM_VACUUM_CHAMBER[0].stackForm,
            "SGS", "PHP", "QQQ",
            'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
            'P', UnificationEntry(pipeNormalFluid, Bronze),
            'Q', UnificationEntry(plate, WroughtIron),
            'S', UnificationEntry(spring, Iron),
            'G', UnificationEntry(gearSmall, Bronze))

        ModHandler.addShapedRecipe(true, "vacuum_chamber.steel", STEAM_VACUUM_CHAMBER[1].stackForm,
            "GGG", "SHS", "PPP",
            'H', STEAM_VACUUM_CHAMBER[0].stackForm,
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
        ModHandler.addShapedRecipe(true, "sap_collector.bronze", STEAM_SAP_COLLECTOR[0].stackForm,
            "SDX", "TRT", "WHW",
            'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_HULL),
            'W', UnificationEntry(pipeTinyFluid, Bronze),
            'T', ItemStack(Blocks.GLASS),
            'R', UnificationEntry(rotor, WroughtIron),
            'S', UnificationEntry(springSmall, Iron),
            'D', OreDictUnifier.get(toolHeadDrill, WroughtIron),
            'X', UnificationEntry(gem, Diamond))

        ModHandler.addShapedRecipe(true, "sap_collector.steel", STEAM_SAP_COLLECTOR[1].stackForm,
            "SQS", "GRG", "PHP",
            'H', STEAM_SAP_COLLECTOR[0].stackForm,
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
        ModHandler.addShapedRecipe(true, "roaster.bronze", STEAM_ROASTER[0].stackForm,
            "PRP", "PQP", "PHP",
            'H', MetaBlocks.STEAM_CASING.getItemVariant(BlockSteamCasing.SteamCasingType.BRONZE_BRICKS_HULL),
            'P', UnificationEntry(pipeSmallFluid, Bronze),
            'Q', UnificationEntry(plate, Bronze),
            'R', UnificationEntry(rotor, Bronze))

        ModHandler.addShapedRecipe(true, "roaster.steel", STEAM_ROASTER[1].stackForm,
            "AAA", "QHQ", "PPP",
            'H', STEAM_ROASTER[0].stackForm,
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

        // -------------------------------------------------------------------------------------------------------------

        // EV Buffer
        ModHandler.addShapedRecipe(true, "buffer_ev", BUFFER[0].stackForm,
            "HP ", "XC ", "   ",
            'H', HULL[EV].stackForm,
            'P', ELECTRIC_PUMP_EV,
            'C', CONVEYOR_MODULE_EV,
            'X', UnificationEntry(circuit, Tier.LV))

        // IV Buffer
        ModHandler.addShapedRecipe(true, "buffer_iv", BUFFER[1].stackForm,
            "HP ", "XC ", "   ",
            'H', HULL[IV].stackForm,
            'P', ELECTRIC_PUMP_IV,
            'C', CONVEYOR_MODULE_IV,
            'X', UnificationEntry(circuit, Tier.LV))

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
            .EUt(VH[LV])
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
            .EUt(VH[LV])
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
            .EUt(VH[LV])
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
            'X', UnificationEntry(circuit, Tier.LV))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(HULL[MV])
            .input(circuit, Tier.LV)
            .input(rotor, Aluminium)
            .input(gear, Aluminium)
            .input(spring, Aluminium)
            .input(pipeNormalFluid, Aluminium, 2)
            .input(pipeNormalItem, Electrum, 2)
            .output(UNIVERSAL_BRIDGE)
            .EUt(VH[MV])
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
            .EUt(VH[LV])
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
            .EUt(VH[LV])
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
            .EUt(VH[LV])
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
            'X', UnificationEntry(circuit, Tier.LV))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(HULL[MV])
            .input(circuit, Tier.LV)
            .input(rotor, Aluminium)
            .input(gear, Aluminium)
            .input(spring, Aluminium)
            .input(pipeNormalFluid, Aluminium, 2)
            .input(pipeNormalItem, Electrum, 2)
            .output(UNIVERSAL_EXTENDER)
            .EUt(VH[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Iron Drum
        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_iron",
            IRON_DRUM.stackForm, IRON_DRUM.stackForm)

        ModHandler.addShapedRecipe(true, "iron_drum", IRON_DRUM.stackForm,
            " h ", "PSP", "PSP",
            'P', UnificationEntry(plate, Iron),
            'S', UnificationEntry(stickLong, Iron))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(stickLong, Iron, 2)
            .input(plate, Iron, 4)
            .output(IRON_DRUM)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Copper Drum
        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_copper",
            COPPER_DRUM.stackForm, COPPER_DRUM.stackForm)

        ModHandler.addShapedRecipe(true, "copper_drum", COPPER_DRUM.stackForm,
            " h ", "PSP", "PSP",
            'P', UnificationEntry(plate, Copper),
            'S', UnificationEntry(stickLong, Copper))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(stickLong, Copper, 2)
            .input(plate, Copper, 4)
            .output(COPPER_DRUM)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Lead Drum
        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_lead",
            LEAD_DRUM.stackForm, LEAD_DRUM.stackForm)

        ModHandler.addShapedRecipe(true, "lead_drum", LEAD_DRUM.stackForm,
            " h ", "PSP", "PSP",
            'P', UnificationEntry(plate, Lead),
            'S', UnificationEntry(stickLong, Lead))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(stickLong, Lead, 2)
            .input(plate, Lead, 4)
            .output(LEAD_DRUM)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Chrome Drum
        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_chrome",
            CHROME_DRUM.stackForm, CHROME_DRUM.stackForm)

        ModHandler.addShapedRecipe(true, "chrome_drum", CHROME_DRUM.stackForm,
            " h ", "PSP", "PSP",
            'P', UnificationEntry(plate, Chrome),
            'S', UnificationEntry(stickLong, Chrome))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(stickLong, Chrome, 2)
            .input(plate, Chrome, 4)
            .output(CHROME_DRUM)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Tungsten Drum
        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_tungsten",
            TUNGSTEN_DRUM.stackForm, TUNGSTEN_DRUM.stackForm)

        ModHandler.addShapedRecipe(true, "tungsten_drum", TUNGSTEN_DRUM.stackForm,
            " h ", "PSP", "PSP",
            'P', UnificationEntry(plate, Tungsten),
            'S', UnificationEntry(stickLong, Tungsten))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(stickLong, Tungsten, 2)
            .input(plate, Tungsten, 4)
            .output(TUNGSTEN_DRUM)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Iridium Drum
        ModHandler.addShapelessNBTClearingRecipe("drum_nbt_iridium",
            IRIDIUM_DRUM.stackForm, IRIDIUM_DRUM.stackForm)

        ModHandler.addShapedRecipe(true, "iridium_drum", IRIDIUM_DRUM.stackForm,
            " h ", "PSP", "PSP",
            'P', UnificationEntry(plate, Iridium),
            'S', UnificationEntry(stickLong, Iridium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(stickLong, Iridium, 2)
            .input(plate, Iridium, 4)
            .output(IRIDIUM_DRUM)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Iron Crate
        ModHandler.addShapedRecipe(true, "iron_crate", IRON_CRATE.stackForm,
            "SPS", "PhP", "SPS",
            'P', UnificationEntry(plate, Iron),
            'S', UnificationEntry(stickLong, Iron))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(stickLong, Iron, 4)
            .input(plate, Iron, 4)
            .output(IRON_CRATE)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Copper Crate
        ModHandler.addShapedRecipe(true, "copper_crate", COPPER_CRATE.stackForm,
            "SPS", "PhP", "SPS",
            'P', UnificationEntry(plate, Copper),
            'S', UnificationEntry(stickLong, Copper))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(stickLong, Copper, 4)
            .input(plate, Copper, 4)
            .output(COPPER_CRATE)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Silver Crate
        ModHandler.addShapedRecipe(true, "silver_crate", SILVER_CRATE.stackForm,
            "SPS", "PhP", "SPS",
            'P', UnificationEntry(plate, Silver),
            'S', UnificationEntry(stickLong, Silver))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(stickLong, Silver, 4)
            .input(plate, Silver, 4)
            .output(SILVER_CRATE)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Gold Crate
        ModHandler.addShapedRecipe(true, "gold_crate", GOLD_CRATE.stackForm,
            "SPS", "PhP", "SPS",
            'P', UnificationEntry(plate, Gold),
            'S', UnificationEntry(stickLong, Gold))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(stickLong, Gold, 4)
            .input(plate, Gold, 4)
            .output(GOLD_CRATE)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Diamond Crate
        ModHandler.addShapedRecipe(true, "diamond_crate", DIAMOND_CRATE.stackForm,
            "SPS", "PhP", "SPS",
            'P', UnificationEntry(plate, Diamond),
            'S', UnificationEntry(stickLong, Diamond))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(stickLong, Diamond, 4)
            .input(plate, Diamond, 4)
            .output(DIAMOND_CRATE)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // UHV Transformer
        ModHandler.addShapedRecipe(true, "transformer_uhv", TRANSFORMER[UHV].stackForm,
            "PUU", "WH ", "PUU",
            'H', HULL[UHV].stackForm,
            'P', NANO_PIC_CHIP,
            'W', UnificationEntry(cableGtSingle, Seaborgium),
            'U', UnificationEntry(cableGtSingle, Europium))

        // UEV Transformer
        ModHandler.addShapedRecipe(true, "transformer_uev", TRANSFORMER[UEV].stackForm,
            "PUU", "WH ", "PUU",
            'H', HULL[UEV].stackForm,
            'P', NANO_PIC_CHIP,
            'W', UnificationEntry(cableGtSingle, SuperheavyAlloyA),
            'U', UnificationEntry(cableGtSingle, Seaborgium))

        // UIV Transformer
        ModHandler.addShapedRecipe(true, "transformer_uiv", TRANSFORMER[UIV].stackForm,
            "PUU", "WH ", "PUU",
            'H', HULL[UIV].stackForm,
            'P', PICO_PIC_CHIP,
            'W', UnificationEntry(cableGtSingle, SuperheavyAlloyB),
            'U', UnificationEntry(cableGtSingle, SuperheavyAlloyA))

        // UXV Transformer
        ModHandler.addShapedRecipe(true, "transformer_uxv", TRANSFORMER[UXV].stackForm,
            "PUU", "WH ", "PUU",
            'H', HULL[UXV].stackForm,
            'P', PICO_PIC_CHIP,
            'W', UnificationEntry(cableGtSingle, Periodicium),
            'U', UnificationEntry(cableGtSingle, SuperheavyAlloyB))

        // TODO OpV Transformer

        // UHV Hi-Amp Transformer
        ModHandler.addShapedRecipe(true, "hi_amp_transformer_uhv", HI_AMP_TRANSFORMER[UHV].stackForm,
            "CUU", "WV ", "CUU",
            'C', VOLTAGE_COIL_UHV,
            'V', TRANSFORMER[UHV].stackForm,
            'W', UnificationEntry(cableGtQuadruple, Seaborgium),
            'U', UnificationEntry(cableGtQuadruple, Europium))

        // UEV Hi-Amp Transformer
        ModHandler.addShapedRecipe(true, "hi_amp_transformer_uev", HI_AMP_TRANSFORMER[UEV].stackForm,
            "CUU", "WV ", "CUU",
            'C', VOLTAGE_COIL_UEV,
            'V', TRANSFORMER[UEV].stackForm,
            'W', UnificationEntry(cableGtQuadruple, SuperheavyAlloyA),
            'U', UnificationEntry(cableGtQuadruple, Seaborgium))

        // UIV Hi-Amp Transformer
        ModHandler.addShapedRecipe(true, "hi_amp_transformer_uiv", HI_AMP_TRANSFORMER[UIV].stackForm,
            "CUU", "WV ", "CUU",
            'C', VOLTAGE_COIL_UIV,
            'V', TRANSFORMER[UIV].stackForm,
            'W', UnificationEntry(cableGtQuadruple, SuperheavyAlloyB),
            'U', UnificationEntry(cableGtQuadruple, SuperheavyAlloyA))

        // UXV Hi-Amp Transformer
        ModHandler.addShapedRecipe(true, "hi_amp_transformer_uxv", HI_AMP_TRANSFORMER[UXV].stackForm,
            "CUU", "WV ", "CUU",
            'C', VOLTAGE_COIL_UXV,
            'V', TRANSFORMER[UXV].stackForm,
            'W', UnificationEntry(cableGtQuadruple, Periodicium),
            'U', UnificationEntry(cableGtQuadruple, SuperheavyAlloyB))

        // TODO OpV Hi-Amp Transformer

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
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // UEV Adjustable Transformer
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UEV])
            .input(ELECTRIC_PUMP_LuV)
            .input(cableGtOctal, SuperheavyAlloyA)
            .input(cableGtOctal, Seaborgium, 2)
            .input(springSmall, Seaborgium)
            .input(spring, SuperheavyAlloyA)
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(2000))
            .output(POWER_TRANSFORMER[UEV])
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // UIV Adjustable Transformer
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UIV])
            .input(ELECTRIC_PUMP_LuV)
            .input(cableGtOctal, SuperheavyAlloyB)
            .input(cableGtOctal, SuperheavyAlloyA, 2)
            .input(springSmall, SuperheavyAlloyA)
            .input(spring, SuperheavyAlloyB)
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(2000))
            .output(POWER_TRANSFORMER[UIV])
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // UXV Adjustable Transformer
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UXV])
            .input(ELECTRIC_PUMP_ZPM)
            .input(cableGtOctal, Periodicium)
            .input(cableGtOctal, SuperheavyAlloyB, 2)
            .input(springSmall, SuperheavyAlloyB)
            .input(spring, Periodicium)
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(2000))
            .output(POWER_TRANSFORMER[UXV])
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // TODO OpV Adjustable Transformer

        // 4A UEV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_INPUT_HATCH[UEV])
            .input(wireGtQuadruple, Seaborgium, 2)
            .input(plate, Vibranium, 2)
            .output(ENERGY_INPUT_HATCH_4A[UEV])
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 4A UIV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_INPUT_HATCH[UIV])
            .input(wireGtQuadruple, SuperheavyAlloyA, 2)
            .input(plate, Shirabon, 2)
            .output(ENERGY_INPUT_HATCH_4A[UIV])
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 4A UXV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_INPUT_HATCH[UXV])
            .input(wireGtQuadruple, SuperheavyAlloyB, 2)
            .input(plate, Creon, 2)
            .output(ENERGY_INPUT_HATCH_4A[UXV])
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 4A OpV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_INPUT_HATCH[OpV])
            .input(wireGtQuadruple, Periodicium, 2)
            .input(plate, BlackDwarfMatter, 2)
            .output(ENERGY_INPUT_HATCH_4A[OpV])
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 16A UHV Energy Hatch
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            *arrayOf(HI_AMP_TRANSFORMER[UV].stackForm, ENERGY_INPUT_HATCH_4A[UHV].getStackForm(2),
                    OreDictUnifier.get(wireGtOctal, Europium, 2),
                    OreDictUnifier.get(plate, Neutronium, 4)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UHV])
            .input(ENERGY_INPUT_HATCH_4A[UHV], 2)
            .input(wireGtOctal, Europium, 2)
            .input(plate, Neutronium, 4)
            .output(ENERGY_INPUT_HATCH_16A[UHV])
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A UEV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UEV])
            .input(ENERGY_INPUT_HATCH_4A[UEV], 2)
            .input(wireGtOctal, Seaborgium, 2)
            .input(plate, Vibranium, 4)
            .output(ENERGY_INPUT_HATCH_16A[UEV])
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A UIV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UIV])
            .input(ENERGY_INPUT_HATCH_4A[UIV], 2)
            .input(wireGtOctal, SuperheavyAlloyA, 2)
            .input(plate, Shirabon, 4)
            .output(ENERGY_INPUT_HATCH_16A[UIV])
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A UXV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UXV])
            .input(ENERGY_INPUT_HATCH_4A[UXV], 2)
            .input(wireGtOctal, SuperheavyAlloyB, 2)
            .input(plate, Creon, 4)
            .output(ENERGY_INPUT_HATCH_16A[UXV])
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A OpV Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[OpV])
            .input(ENERGY_INPUT_HATCH_4A[OpV], 2)
            .input(wireGtOctal, Periodicium, 2)
            .input(plate, BlackDwarfMatter, 4)
            .output(ENERGY_INPUT_HATCH_16A[OpV])
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 64A UHV Substation Energy Hatch
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            *arrayOf(POWER_TRANSFORMER[UV].stackForm, ENERGY_INPUT_HATCH_16A[UHV].stackForm,
                     OreDictUnifier.get(wireGtHex, Europium, 2),
                     OreDictUnifier.get(plate, Neutronium, 6)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UHV])
            .input(ENERGY_INPUT_HATCH_16A[UHV])
            .input(wireGtHex, Europium, 2)
            .input(plate, Neutronium, 6)
            .output(SUBSTATION_ENERGY_INPUT_HATCH[UHV])
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A UEV Substation Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UEV])
            .input(ENERGY_INPUT_HATCH_16A[UEV])
            .input(wireGtHex, Seaborgium, 2)
            .input(plate, Vibranium, 6)
            .output(SUBSTATION_ENERGY_INPUT_HATCH[UEV])
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A UIV Substation Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UIV])
            .input(ENERGY_INPUT_HATCH_16A[UIV])
            .input(wireGtHex, SuperheavyAlloyA, 2)
            .input(plate, Shirabon, 6)
            .output(SUBSTATION_ENERGY_INPUT_HATCH[UIV])
            .EUt(VA[UEV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A UXV Substation Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UXV])
            .input(ENERGY_INPUT_HATCH_16A[UXV])
            .input(wireGtHex, SuperheavyAlloyB, 2)
            .input(plate, Creon, 6)
            .output(SUBSTATION_ENERGY_INPUT_HATCH[UXV])
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A OpV Substation Energy Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[OpV])
            .input(ENERGY_INPUT_HATCH_16A[OpV])
            .input(wireGtHex, Periodicium, 2)
            .input(plate, BlackDwarfMatter, 6)
            .output(SUBSTATION_ENERGY_INPUT_HATCH[OpV])
            .EUt(VA[UXV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 4A UEV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_OUTPUT_HATCH[UEV])
            .input(wireGtQuadruple, Seaborgium, 2)
            .input(plate, Vibranium, 2)
            .output(ENERGY_OUTPUT_HATCH_4A[UEV])
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 4A UIV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_OUTPUT_HATCH[UIV])
            .input(wireGtQuadruple, SuperheavyAlloyA, 2)
            .input(plate, Shirabon, 2)
            .output(ENERGY_OUTPUT_HATCH_4A[UIV])
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 4A UXV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_OUTPUT_HATCH[UXV])
            .input(wireGtQuadruple, SuperheavyAlloyB, 2)
            .input(plate, Creon, 2)
            .output(ENERGY_OUTPUT_HATCH_4A[UXV])
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 4A OpV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(ENERGY_OUTPUT_HATCH[OpV])
            .input(wireGtQuadruple, Periodicium, 2)
            .input(plate, BlackDwarfMatter, 2)
            .output(ENERGY_OUTPUT_HATCH_4A[OpV])
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 16A UHV Dynamo Hatch
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            *arrayOf(HI_AMP_TRANSFORMER[UV].stackForm, ENERGY_OUTPUT_HATCH_4A[UHV].stackForm,
                     OreDictUnifier.get(wireGtOctal, Europium, 2),
                     OreDictUnifier.get(plate, Neutronium, 4)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UHV])
            .input(ENERGY_OUTPUT_HATCH_4A[UHV])
            .input(wireGtOctal, Europium, 2)
            .input(plate, Neutronium, 4)
            .output(ENERGY_OUTPUT_HATCH_16A[UHV])
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A UEV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UEV])
            .input(ENERGY_OUTPUT_HATCH_4A[UEV])
            .input(wireGtOctal, Seaborgium, 2)
            .input(plate, Vibranium, 4)
            .output(ENERGY_OUTPUT_HATCH_16A[UEV])
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A UIV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UIV])
            .input(ENERGY_OUTPUT_HATCH_4A[UIV])
            .input(wireGtOctal, SuperheavyAlloyA, 2)
            .input(plate, Shirabon, 4)
            .output(ENERGY_OUTPUT_HATCH_16A[UIV])
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A UXV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[UXV])
            .input(ENERGY_OUTPUT_HATCH_4A[UXV])
            .input(wireGtOctal, SuperheavyAlloyB, 2)
            .input(plate, Creon, 4)
            .output(ENERGY_OUTPUT_HATCH_16A[UXV])
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 16A OpV Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HI_AMP_TRANSFORMER[OpV])
            .input(ENERGY_OUTPUT_HATCH_4A[OpV])
            .input(wireGtOctal, Periodicium, 2)
            .input(plate, BlackDwarfMatter, 4)
            .output(ENERGY_OUTPUT_HATCH_16A[OpV])
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 64A UHV Substation Dynamo Hatch
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            *arrayOf(POWER_TRANSFORMER[UV].stackForm, ENERGY_OUTPUT_HATCH_16A[UHV].stackForm,
                     OreDictUnifier.get(wireGtHex, Europium, 2),
                     OreDictUnifier.get(plate, Neutronium, 6)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UHV])
            .input(ENERGY_OUTPUT_HATCH_16A[UHV])
            .input(wireGtHex, Europium, 2)
            .input(plate, Neutronium, 6)
            .output(SUBSTATION_ENERGY_OUTPUT_HATCH[UHV])
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A UEV Substation Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UEV])
            .input(ENERGY_OUTPUT_HATCH_16A[UEV])
            .input(wireGtHex, Seaborgium, 2)
            .input(plate, Vibranium, 6)
            .output(SUBSTATION_ENERGY_OUTPUT_HATCH[UEV])
            .EUt(VA[UHV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A UIV Substation Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UIV])
            .input(ENERGY_OUTPUT_HATCH_16A[UIV])
            .input(wireGtHex, SuperheavyAlloyA, 2)
            .input(plate, Shirabon, 6)
            .output(SUBSTATION_ENERGY_OUTPUT_HATCH[UIV])
            .EUt(VA[UEV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A UXV Substation Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UXV])
            .input(ENERGY_OUTPUT_HATCH_16A[UXV])
            .input(wireGtHex, SuperheavyAlloyB, 2)
            .input(plate, Creon, 6)
            .output(SUBSTATION_ENERGY_OUTPUT_HATCH[UXV])
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // 64A OpV Substation Dynamo Hatch
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[OpV])
            .input(ENERGY_OUTPUT_HATCH_16A[OpV])
            .input(wireGtHex, Periodicium, 2)
            .input(plate, BlackDwarfMatter, 6)
            .output(SUBSTATION_ENERGY_OUTPUT_HATCH[OpV])
            .EUt(VA[UXV])
            .duration(20 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}