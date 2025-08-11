package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.WIREMILL_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Polycaprolactam
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.SECOND
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object WiremillRecipes
{

    // @formatter:off

    fun init()
    {

        // Let Polycaprolactam also used same int circuit configuration like another wire.
        GTRecipeHandler.removeRecipesByInputs(WIREMILL_RECIPES,
            OreDictUnifier.get(ingot, Polycaprolactam))

        WIREMILL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(ingot, Polycaprolactam)
            .outputs(ItemStack(Items.STRING, 32))
            .EUt(48) // MV
            .duration(4 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}