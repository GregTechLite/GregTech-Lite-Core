package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.TreatedWood
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.blocks.BlockSteamCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems
import gregtech.loaders.recipe.CraftingComponent
import gregtech.loaders.recipe.MetaTileEntityLoader
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockPrimitiveCasing
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHEMICAL_DEHYDRATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COAGULATION_TANK
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.GREENHOUSE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LAMINATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LOOM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.POLISHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SAP_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SLICER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_SAP_COLLECTOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_VACUUM_CHAMBER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_VULCANIZING_PRESS
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TOOL_CASTER
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

        }

    }

}