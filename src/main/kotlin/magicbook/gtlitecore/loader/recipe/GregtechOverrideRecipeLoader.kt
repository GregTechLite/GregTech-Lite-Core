package magicbook.gtlitecore.loader.recipe

import gregtech.api.recipes.ModHandler
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.ingot

@Suppress("MISSING_DEPENDENCY_CLASS")
class GregtechOverrideRecipeLoader
{

    companion object
    {

        fun init()
        {

            // Let ingotIron can smelt to ingotWroughtIron just like nuggetIron -> nuggetWroughtIron.
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Iron),
                OreDictUnifier.get(ingot, WroughtIron))

            // TODO Simplified clay? electrolysis water?

        }

    }

}