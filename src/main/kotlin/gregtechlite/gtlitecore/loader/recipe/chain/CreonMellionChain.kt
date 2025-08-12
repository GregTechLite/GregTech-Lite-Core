package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import net.minecraft.item.ItemStack

internal object CreonMellionChain
{

    // @formatter:off

    fun init()
    {
        // Creon plasma
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Fermium.getPlasma(L * 4))
            .fluidInputs(Thorium.getPlasma(L * 8))
            .fluidInputs(Technetium.getPlasma(L * 12))
            .fluidInputs(Calcium.getPlasma(L * 36))
            .fluidOutputs(Creon.getPlasma(L * 18))
            .EUt(VA[UXV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Fermium.getPlasma(L * 4 * 16))
            .fluidInputs(Thorium.getPlasma(L * 8 * 16))
            .fluidInputs(Technetium.getPlasma(L * 12 * 16))
            .fluidInputs(Calcium.getPlasma(L * 36 * 16))
            .fluidOutputs(Creon.getPlasma(L * 18 * 16))
            .EUt(VA[OpV])
            .duration(20 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Fermium.getPlasma(L * 4 * 64))
            .fluidInputs(Thorium.getPlasma(L * 8 * 64))
            .fluidInputs(Technetium.getPlasma(L * 12 * 64))
            .fluidInputs(Calcium.getPlasma(L * 36 * 64))
            .fluidOutputs(Creon.getPlasma(L * 18 * 64))
            .EUt(VA[MAX])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Mellion
        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            OreDictUnifier.get(dust, Mellion),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Mellion),
                IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Krypton.getFluid(10)))

        GTRecipeHandler.removeRecipesByInputs(TOPOLOGICAL_ORDER_CHANGING_RECIPES,
            OreDictUnifier.get(dust, Mellion),
            IntCircuitIngredient.getIntegratedCircuit(1))

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Mellion)
            .fluidInputs(Creon.getPlasma(L))
            .output(ingot, Mellion)
            .fluidOutputs(Creon.getFluid(L))
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .blastFurnaceTemp(22000)
            .buildAndRegister()

        // Creon
        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            OreDictUnifier.get(dust, Creon),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Creon),
            IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Krypton.getFluid(10)))

    }

    // @formatter:on

}