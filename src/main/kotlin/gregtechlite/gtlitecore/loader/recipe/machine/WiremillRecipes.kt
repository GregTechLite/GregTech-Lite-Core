package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.recipes.RecipeMaps.WIREMILL_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Polycaprolactam
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.extension.removeRecipe
import net.minecraft.init.Items.STRING

internal object WiremillRecipes
{

    // @formatter:off

    fun init()
    {
        // Let Polycaprolactam also used same int circuit configuration like another wire.
        WIREMILL_RECIPES.removeRecipe(OreDictUnifier.get(ingot, Polycaprolactam))
        WIREMILL_RECIPES.addRecipe {
            circuitMeta(1)
            input(ingot, Polycaprolactam)
            outputs(STRING, 32)
            EUt(48) // MV
            duration(4 * SECOND)
        }
    }

    // @formatter:on

}