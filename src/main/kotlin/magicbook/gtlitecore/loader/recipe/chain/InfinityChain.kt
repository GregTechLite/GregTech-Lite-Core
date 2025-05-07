package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.ore.OrePrefix.block
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Gluons
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.TARANIUM_CHARGE
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class InfinityChain
{

    companion object
    {

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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UXV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

    }

}