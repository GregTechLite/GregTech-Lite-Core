package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.ore.OrePrefix.block
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Antimatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Eternity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetohydrodynamicallyConstrainedStarMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Protomatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SpaceTime
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GRAVITON_SHARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.TIMEPIECE

@Suppress("MISSING_DEPENDENCY_CLASS")
class EternityChain
{

    companion object
    {

        fun init()
        {
            // The first recipes to get Graviton Shard, and then should use Graviton Shard in QFT.
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, SpaceTime, 8)
                .input(TIMEPIECE)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 2))
                .fluidInputs(Antimatter.getFluid(10_000))
                .output(GRAVITON_SHARD)
                .fluidOutputs(Protomatter.getFluid(L * 4))
                .fluidOutputs(Helium.getPlasma(L * 4))
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Advanced recipes to breed more Graviton Shard.
            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(block, SpaceTime)
                .fluidInputs(Eternity.getFluid(L))
                .output(GRAVITON_SHARD)
                .fluidOutputs(Infinity.getFluid(L * 4))
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}