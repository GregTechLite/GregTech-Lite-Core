package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScheelesGreen
import gregtechlite.gtlitecore.common.block.variant.GlassCasing

internal object ChemicalBathRecipes
{

    // @formatter:off

    fun init()
    {
        // Greenhouse Glass
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
            .fluidInputs(ScheelesGreen.getFluid(L * 4))
            .outputs(GlassCasing.GREENHOUSE.stack)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}