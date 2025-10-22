package gregtechlite.gtlitecore.loader.recipe

import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend
import gregtechlite.gtlitecore.loader.recipe.chain.ChemistryRecipeList
import gregtechlite.gtlitecore.loader.recipe.circuit.CircuitRecipeList
import gregtechlite.gtlitecore.loader.recipe.component.ConveyorRecipes
import gregtechlite.gtlitecore.loader.recipe.component.EmitterRecipes
import gregtechlite.gtlitecore.loader.recipe.component.FieldGenRecipes
import gregtechlite.gtlitecore.loader.recipe.component.MotorRecipes
import gregtechlite.gtlitecore.loader.recipe.component.PistonRecipes
import gregtechlite.gtlitecore.loader.recipe.component.PumpRecipes
import gregtechlite.gtlitecore.loader.recipe.component.RobotArmRecipes
import gregtechlite.gtlitecore.loader.recipe.component.SensorRecipes
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
        GTLiteLog.logger.debug("Starting to load all Crafting recipes...")
        CraftingRecipeLoader.init()
        GTWoodRecipeLoader.init()

        GTLiteLog.logger.debug("Starting to load all RecipeProducers...")
        RecipeProducerList.init()

        GTLiteLog.logger.debug("Starting to load all MetaTileEntity recipes...")
        GTMetaTileEntityLoader.init()
        MachineRecipeLoader.init()
        MachineCasingRecipeList.init()

        GTLiteLog.logger.debug("Starting to load all Chemistry, Ore and Food Processings...")
        ChemistryRecipeList.init()
        OreProcessingList.init()
        FoodProcessingList.init()

        // Components, Crafting Components and Machine Recipes.
        componentRecipes()
        MachineRecipeList.init()

        GTLiteLog.logger.debug("Starting to load all Circuit recipes...")
        CircuitRecipeList.init()

        GTLiteLog.logger.debug("Starting to load all Override recipes and resolve all Recipe Conflicts...")
        OverrideRecipeLoader.init()
        RecipeConflicts.init()

        GTLiteLog.logger.debug("Starting to load all Pseudo Recipes...")
        SpacePumpRecipeFrontend.init()
    }

    private fun componentRecipes()
    {
        MotorRecipes.init()
        PistonRecipes.init()
        PumpRecipes.init()
        ConveyorRecipes.init()
        RobotArmRecipes.init()
        EmitterRecipes.init()
        SensorRecipes.init()
        FieldGenRecipes.init()
    }

    // @formatter:on

}