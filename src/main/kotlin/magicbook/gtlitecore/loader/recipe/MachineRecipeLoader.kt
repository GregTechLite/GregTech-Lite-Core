package magicbook.gtlitecore.loader.recipe

import gregtech.loaders.recipe.CraftingComponent
import gregtech.loaders.recipe.MetaTileEntityLoader
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.POLISHER
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SLICER
import magicbook.gtlitecore.loader.recipe.component.CraftingComponents

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

        }

    }

}