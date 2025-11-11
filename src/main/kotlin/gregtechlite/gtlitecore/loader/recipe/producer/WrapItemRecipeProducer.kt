package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.recipe.util.wrapItems
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_EV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_HV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_LuV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_MV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_ULV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CIRCUIT_ZPM
import net.minecraft.item.ItemStack

internal object WrapItemRecipeProducer
{

    // @formatter:off

    fun produce()
    {

        // Add all Wrap Items.
        wrapItems.forEach { item, wrapItem -> addRecipe(item, wrapItem) }

        // Circuits
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.ULV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_ULV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.LV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_LV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.MV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_MV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.HV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_HV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.EV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_EV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.IV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_IV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.LuV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_LuV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.ZPM, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_ZPM)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.UV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_UV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.UHV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_UHV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.UEV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_UEV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.UIV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_UIV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.UXV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_UXV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.OpV, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_OpV)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .input(circuit, Tier.MAX, 16)
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .output(WRAP_CIRCUIT_MAX)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun addRecipe(item: ItemStack, wrapItem: ItemStack)
    {
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(16)
            .inputs(item.copy(16))
            .fluidInputs(Polyethylene.getFluid(L / 2))
            .outputs(wrapItem)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}