package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.ore.OrePrefix.block
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HighEnergyQuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PreciousMetalAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class ShirabonChain
{

    companion object
    {

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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UIV].toLong())
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
                .EUt(VA[UXV].toLong())
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
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}