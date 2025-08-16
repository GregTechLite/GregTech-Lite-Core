package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.unification.material.Materials.Neutronium
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import net.minecraft.item.ItemStack

internal object HypogenChain
{

    // @formatter:off

    fun init()
    {
        // Nt + QuantumAlloy -> Fs
        FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Neutronium.getFluid(L))
            .fluidInputs(QuantumAlloy.getFluid(L))
            .fluidOutputs(Rhugnor.getFluid(L * 2))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .EUToStart(980_000_000L) // 980M EU, MK4
            .buildAndRegister()

        // Fs + If -> Hy
        FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Rhugnor.getFluid(L * 2))
            .fluidInputs(Infinity.getFluid(L))
            .fluidOutputs(Hypogen.getFluid(L / 4))
            .EUt(VA[UEV])
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
            .EUt(VA[UIV])
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
            .EUt(VA[UXV])
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
            .EUt(VA[OpV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}