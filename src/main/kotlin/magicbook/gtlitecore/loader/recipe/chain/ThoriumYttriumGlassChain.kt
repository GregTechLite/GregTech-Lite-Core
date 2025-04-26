package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThoriumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumOxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01

@Suppress("MISSING_DEPENDENCY_CLASS")
class ThoriumYttriumGlassChain
{

    companion object
    {

        fun init()
        {
            // Y2O3 + ThO2 -> Th-Y Glass
            ROASTER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .input(dustSmall, YttriumOxide, 2)
                .input(dustSmall, ThoriumDioxide, 2)
                .fluidInputs(Glass.getFluid(L))
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.THORIUM_YTTRIUM))
                .EUt(VA[IV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()
        }

    }

}