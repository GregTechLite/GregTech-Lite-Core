package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Water
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.t
import net.minecraft.init.Blocks.DEADBUSH
import net.minecraft.init.Blocks.TALLGRASS

internal object ChemicalDehydratorRecipes
{
    // @formatter:off

    fun init()
    {
        // Dead Bush
        for (i in 1..2)
        {
            CHEMICAL_DEHYDRATOR_RECIPES.addRecipe {
                inputs(TALLGRASS.getStack(meta = i))
                outputs(DEADBUSH.stack())
                fluidOutputs(Water.getFluid(25))
                EUt(VA[ULV])
                duration(2.t)
            }
        }
    }

    // @foratter:on
}