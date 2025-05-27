package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ScheelesGreen
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01

@Suppress("MISSING_DEPENDENCY_CLASS")
class ChemicalBathRecipes
{

    companion object
    {

        fun init()
        {
            // Greenhouse Glass
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
                .fluidInputs(ScheelesGreen.getFluid(L * 4))
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.GREENHOUSE))
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

    }

}