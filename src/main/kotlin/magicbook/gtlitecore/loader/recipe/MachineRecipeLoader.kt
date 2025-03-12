package magicbook.gtlitecore.loader.recipe

import gregtech.common.items.MetaItems
import gregtech.loaders.recipe.CraftingComponent
import gregtech.loaders.recipe.MetaTileEntityLoader
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LAMINATOR
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LOOM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.POLISHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SLICER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TOOL_CASTER
import magicbook.gtlitecore.loader.recipe.component.CraftingComponents

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

        }

    }

}