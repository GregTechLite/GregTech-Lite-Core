package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.ore.OrePrefix.block
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HighEnergyQuarkGluonPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PreciousMetalAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuarkGluonPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import net.minecraft.item.ItemStack

internal object ShirabonChain
{

    // @formatter:off

    fun init()
    {
        // Tier 1
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(block, Iron, 8)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .fluidInputs(CosmicNeutronium.getFluid(L * 2))
            .fluidInputs(MetastableOganesson.getFluid(L * 8))
            .fluidInputs(PreciousMetalAlloy.getFluid(L * 16))
            .fluidInputs(QuarkGluonPlasma.getFluid(5000))
            .output(block, Neutronium, 8)
            .fluidOutputs(Shirabon.getFluid(L * 4))
            .EUt(VA[UEV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(block, Iron, 8)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .fluidInputs(CosmicNeutronium.getFluid(L * 2))
            .fluidInputs(MetastableOganesson.getFluid(L * 8))
            .fluidInputs(PreciousMetalAlloy.getFluid(L * 16))
            .fluidInputs(HighEnergyQuarkGluonPlasma.getFluid(500))
            .output(block, Neutronium, 8)
            .fluidOutputs(Shirabon.getFluid(L * 4))
            .EUt(VA[UEV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // Tier 2
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(CosmicNeutronium.getFluid(L * 2 * 16))
            .fluidInputs(MetastableOganesson.getFluid(L * 8 * 16))
            .fluidInputs(PreciousMetalAlloy.getFluid(L * 16 * 16))
            .fluidInputs(QuarkGluonPlasma.getFluid(5000 * 16))
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .fluidOutputs(Shirabon.getFluid(L * 4 * 16))
            .EUt(VA[UIV])
            .duration(15 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(CosmicNeutronium.getFluid(L * 2 * 16))
            .fluidInputs(MetastableOganesson.getFluid(L * 8 * 16))
            .fluidInputs(PreciousMetalAlloy.getFluid(L * 16 * 16))
            .fluidInputs(HighEnergyQuarkGluonPlasma.getFluid(500 * 16))
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .fluidOutputs(Shirabon.getFluid(L * 4 * 16))
            .EUt(VA[UIV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // Tier 3
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(CosmicNeutronium.getFluid(L * 2 * 32))
            .fluidInputs(MetastableOganesson.getFluid(L * 8 * 32))
            .fluidInputs(PreciousMetalAlloy.getFluid(L * 16 * 32))
            .fluidInputs(QuarkGluonPlasma.getFluid(5000 * 32))
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .fluidOutputs(Shirabon.getFluid(L * 4 * 32))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .input(block, Iron, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(CosmicNeutronium.getFluid(L * 2 * 32))
            .fluidInputs(MetastableOganesson.getFluid(L * 8 * 32))
            .fluidInputs(PreciousMetalAlloy.getFluid(L * 16 * 32))
            .fluidInputs(HighEnergyQuarkGluonPlasma.getFluid(500 * 32))
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .output(block, Neutronium, 64)
            .fluidOutputs(Shirabon.getFluid(L * 4 * 32))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}