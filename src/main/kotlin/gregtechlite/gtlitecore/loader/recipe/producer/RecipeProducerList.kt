package gregtechlite.gtlitecore.loader.recipe.producer

import com.morphismmc.morphismlib.integration.Mods
import gregtechlite.gtlitecore.core.GTLiteConfigHolder
import gregtechlite.gtlitecore.integration.appliedenergistics2.recipe.AppEngCALRecipeProducer

internal object RecipeProducerList
{

    // @formatter:off

    fun init()
    {
        StoneVariantRecipeProducer.produce()
        DisposableToolRecipeProducer.produce()
        MassReplicationRecipeProducer.produce()

        CircuitAssemblyLineRecipeProducer.produce()

        if (Mods.AppliedEnergistics2.isActive&& GTLiteConfigHolder.compat.ae2Compat.addProcessorCALRecipes)
            AppEngCALRecipeProducer.produce()

        WrapItemRecipeProducer.produce()
        GreenhouseRecipeProducer.produce()
        MiningDroneAsteroidRecipeProducer.produce()
        ComponentAssemblyLineRecipeProducer.produce()
        NuclearFissionRecipeProducer.produce()
        PCBFactoryRecipeProducer.produce()
        AdvancedFusionRecipeProducer.produce()
    }

    // @formatter:on

}