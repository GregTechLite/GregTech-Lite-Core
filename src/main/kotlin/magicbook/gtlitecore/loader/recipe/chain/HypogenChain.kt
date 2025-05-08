package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.unification.material.Materials.Neutronium
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hypogen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Rhugnor
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class HypogenChain
{

    companion object
    {

        fun init()
        {
            // Nt + QuantumAlloy -> Fs
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Neutronium.getFluid(L))
                .fluidInputs(QuantumAlloy.getFluid(L))
                .fluidOutputs(Rhugnor.getFluid(L * 2))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .EUToStart(980_000_000L) // 980M EU, MK4
                .buildAndRegister()

            // Fs + If -> Hy
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Rhugnor.getFluid(L * 2))
                .fluidInputs(Infinity.getFluid(L))
                .fluidOutputs(Hypogen.getFluid(L / 4))
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE)
                .EUToStart(1_600_000_000L) // 1600M EU, MK5
                .buildAndRegister()

            // When player get 144L Hypogen, then they can copy Hypogen by Stellar Forge.

            // Tier 1: x10
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(ItemStack(LEPTONIC_CHARGE))
                .fluidInputs(Hypogen.getFluid(L))
                .fluidInputs(Neutronium.getFluid(L * 40))
                .fluidInputs(QuantumAlloy.getFluid(L * 40))
                .fluidInputs(Infinity.getFluid(L * 10))
                .fluidOutputs(Hypogen.getFluid(L * 11))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Tier 2: x160
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Hypogen.getFluid(L * 16))
                .fluidInputs(Neutronium.getFluid(L * 40 * 16))
                .fluidInputs(QuantumAlloy.getFluid(L * 40 * 16))
                .fluidInputs(Infinity.getFluid(L * 10 * 16))
                .fluidOutputs(Hypogen.getFluid(L * 11 * 16))
                .EUt(VA[UXV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tier 3: x640
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Hypogen.getFluid(L * 64))
                .fluidInputs(Neutronium.getFluid(L * 40 * 64))
                .fluidInputs(QuantumAlloy.getFluid(L * 40 * 64))
                .fluidInputs(Infinity.getFluid(L * 10 * 64))
                .fluidOutputs(Hypogen.getFluid(L * 11 * 64))
                .EUt(VA[OpV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

        }

    }

}