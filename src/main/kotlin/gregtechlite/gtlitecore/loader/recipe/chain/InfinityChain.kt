package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.ore.OrePrefix.block
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Gluons
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.TARANIUM_CHARGE
import net.minecraft.item.ItemStack

internal object InfinityChain
{

    // @formatter:off

    fun init()
    {
        // Tier 1: 9x Nether Star -> 4x Infinity
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(block, NetherStar)
            .inputs(ItemStack(TARANIUM_CHARGE))
            .fluidInputs(Neutronium.getFluid(L * 16))
            .fluidInputs(Gluons.getFluid(4000))
            .fluidOutputs(Infinity.getFluid(L * 4))
            .EUt(VA[UEV])
            .duration(40 * SECOND)
            .buildAndRegister()

        // Tier 2: 9x 16x Nether Star -> 4x 16x Infinity
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(block, NetherStar, 16)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .fluidInputs(Neutronium.getFluid(L * 16 * 16))
            .fluidInputs(Gluons.getFluid(4000 * 16))
            .fluidOutputs(Infinity.getFluid(L * 4 * 16))
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Tier 3: 9x 64x Nether Star -> 4x 64x Infinity
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(block, NetherStar, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Neutronium.getFluid(L * 16 * 64))
            .fluidInputs(Gluons.getFluid(4000 * 64))
            .fluidOutputs(Infinity.getFluid(L * 4 * 64))
            .EUt(VA[UXV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}