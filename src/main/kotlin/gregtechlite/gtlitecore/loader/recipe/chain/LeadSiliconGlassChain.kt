package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.GlassCasing

internal object LeadSiliconGlassChain
{

    // @formatter:off

    fun init()
    {
        // PbO + SiO2 -> Pb-Si Glass
        ROASTER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BLOCK)
            .input(dust, Massicot, 2)
            .input(dust, SiliconDioxide, 3)
            .fluidInputs(Glass.getFluid(L))
            .outputs(GlassCasing.LEAD_SILICON.stack)
            .EUt(VA[EV])
            .duration(30 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}