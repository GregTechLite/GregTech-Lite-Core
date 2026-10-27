package gregtechlite.gtlitecore.integration.appeng.recipe

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.NetherQuartz
import gregtech.api.unification.ore.OrePrefix.gem
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeCategories
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BLACKHOLE_FORMING_RECIPES
import gregtechlite.gtlitecore.api.t
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE_FINE
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.QUARTZ_FIBER

internal object AEBlackholeFormerRecipes
{
    // @formatter:off

    fun init()
    {
        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_WIRE_FINE)
            input(gem, NetherQuartz)
            outputs(QUARTZ_FIBER.getStack(8))
            EUt(VA[ULV])
            duration(12.t)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }
    }

    // @formatter:on
}