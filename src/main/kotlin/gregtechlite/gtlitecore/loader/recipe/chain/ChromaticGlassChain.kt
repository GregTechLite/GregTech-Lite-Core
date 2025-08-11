package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.POLISHER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromaticGlass
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY

internal object ChromaticGlassChain
{

    // @formatter:off

    fun init()
    {
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(QUANTUM_ANOMALY)
            .input(dust, Glass, 64)
            .output(dust, ChromaticGlass)
            .EUt(VA[UHV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        POLISHER_RECIPES.recipeBuilder()
            .input(block, ChromaticGlass)
            .outputs(GlassCasing.CHROMATIC.getStack(2))
            .output(dust, ChromaticGlass)
            .EUt(VA[LuV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}