package magicbook.gtlitecore.loader.recipe

import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.blocks.BlockSteamCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems
import gregtech.loaders.recipe.CraftingComponent
import gregtech.loaders.recipe.MetaTileEntityLoader
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHEMICAL_DEHYDRATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LAMINATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LOOM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.POLISHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SLICER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.STEAM_VULCANIZING_PRESS
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TOOL_CASTER
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

        }

    }

}