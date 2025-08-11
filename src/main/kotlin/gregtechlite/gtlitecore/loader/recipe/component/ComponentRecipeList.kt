package gregtechlite.gtlitecore.loader.recipe.component

internal object ComponentRecipeList
{

    // @formatter:off

    fun init()
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