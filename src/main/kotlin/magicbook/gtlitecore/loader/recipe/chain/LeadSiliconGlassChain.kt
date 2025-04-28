package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.material.Materials.Massicot
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01

@Suppress("MISSING_DEPENDENCY_CLASS")
class LeadSiliconGlassChain
{

    companion object
    {

        fun init()
        {
            // PbO + SiO2 -> Pb-Si Glass
            ROASTER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .input(dust, Massicot, 2)
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(Glass.getFluid(L))
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.LEAD_SILICON))
                .EUt(VA[EV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

        }

    }

}