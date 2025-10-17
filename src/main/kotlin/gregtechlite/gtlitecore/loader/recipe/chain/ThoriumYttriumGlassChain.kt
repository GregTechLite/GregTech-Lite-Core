package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThoriumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YttriumOxide
import gregtechlite.gtlitecore.common.block.variant.GlassCasing

internal object ThoriumYttriumGlassChain
{

    // @formatter:off

    fun init()
    {
        // Y2O3 + ThO2 -> Th-Y Glass
        ROASTER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BLOCK)
            .input(dustSmall, YttriumOxide, 2)
            .input(dustSmall, ThoriumDioxide, 2)
            .fluidInputs(Glass.getFluid(L))
            .outputs(GlassCasing.THORIUM_YTTRIUM.stack)
            .EUt(VA[IV])
            .duration(40 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}