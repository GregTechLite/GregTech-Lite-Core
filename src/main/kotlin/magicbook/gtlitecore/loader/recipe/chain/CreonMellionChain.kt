package magicbook.gtlitecore.loader.recipe.chain

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Creon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Mellion
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class CreonMellionChain
{

    companion object
    {

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
                .EUt(VA[UXV].toLong())
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
                .EUt(VA[OpV].toLong())
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
                .EUt(VA[MAX].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // dustMellion -> ingotHotMellion
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Mellion),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Krypton.getFluid(10)))

            // ingotHotMellion -> ingotMellion
            VACUUM_RECIPES.recipeBuilder()
                .input(ingotHot, Mellion, 9)
                .fluidInputs(Creon.getPlasma(L * 9))
                .output(ingot, Mellion, 9)
                .fluidOutputs(Creon.getFluid(L * 9))
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

    }

}