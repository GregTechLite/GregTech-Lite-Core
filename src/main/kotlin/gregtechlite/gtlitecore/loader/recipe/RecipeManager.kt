package gregtechlite.gtlitecore.loader.recipe

import gregtechlite.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend
import gregtechlite.gtlitecore.loader.recipe.chain.ChemistryRecipeList
import gregtechlite.gtlitecore.loader.recipe.circuit.CircuitRecipeList
import gregtechlite.gtlitecore.loader.recipe.component.ComponentRecipeList
import gregtechlite.gtlitecore.loader.recipe.foodprocessing.FoodProcessingList
import gregtechlite.gtlitecore.loader.recipe.machine.GTMetaTileEntityLoader
import gregtechlite.gtlitecore.loader.recipe.machine.MachineRecipeList
import gregtechlite.gtlitecore.loader.recipe.machine.MachineRecipeLoader
import gregtechlite.gtlitecore.loader.recipe.machine.casing.MachineCasingRecipeList
import gregtechlite.gtlitecore.loader.recipe.oreprocessing.OreProcessingList
import gregtechlite.gtlitecore.loader.recipe.producer.RecipeProducerList

internal object RecipeManager
{

    // @formatter:off

    fun init()
    {
        // Hand-Crafting Recipes and GTCEu Wood Recipes.
        CraftingRecipeLoader.init()
        GTWoodRecipeLoader.init()

        // Recipe Producers.
        RecipeProducerList.init()

        // Single and Multiblock Machines and Machine Casings Recipes.
        GTMetaTileEntityLoader.init()
        MachineRecipeLoader.init()
        MachineCasingRecipeList.init()

        // Chemistry, Ore and Food Processings Recipes.
        ChemistryRecipeList.init()
        OreProcessingList.init()
        FoodProcessingList.init()

        // Components, Crafting Components and Machine Recipes.
        ComponentRecipeList.init()
        MachineRecipeList.init()

        // Circuit Recipes.
        CircuitRecipeList.init()

        // Post Recipe Producers.
        RecipeProducerList.postInit()

        // Override Recipes and Recipe Conflicts Resolver.
        OverrideRecipeLoader.init()
        RecipeConflicts.init()

        // Pseudo Recipes (Recipe Frontends).
        SpacePumpRecipeFrontend.init()
    }

    // @formatter:on

}