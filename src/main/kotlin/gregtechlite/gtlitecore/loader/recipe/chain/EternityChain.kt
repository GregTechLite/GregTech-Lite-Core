package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.ore.OrePrefix.block
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Antimatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Protomatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAVITON_SHARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TIMEPIECE

internal object EternityChain
{

    // @formatter:off

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
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Advanced recipes to breed more Graviton Shard.
        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(block, SpaceTime)
            .fluidInputs(Eternity.getFluid(L))
            .output(GRAVITON_SHARD)
            .fluidOutputs(Infinity.getFluid(L * 4))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}