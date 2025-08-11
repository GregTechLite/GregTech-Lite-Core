package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ozone
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object OzoneChain
{

    // @formatter:off

    fun init()
    {
        // 3O -> O3
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(craftingLens, MarkerMaterials.Color.White)
            .fluidInputs(Oxygen.getFluid(3000))
            .fluidOutputs(Ozone.getFluid(1000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}