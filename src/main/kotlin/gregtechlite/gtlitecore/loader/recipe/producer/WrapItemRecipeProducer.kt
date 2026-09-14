package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.recipe.util.TierBridge
import gregtechlite.gtlitecore.api.recipe.util.wrapItems
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.loader.recipe.component.CraftingComponents
import net.minecraft.item.ItemStack

internal object WrapItemRecipeProducer
{
    fun produce()
    {
        wrapItems.forEach { item, wrapItem -> addRecipe(item, wrapItem) }

        for (tier in ULV..MAX)
        {
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(16)
                input(circuit, TierBridge.materialOf(tier), 16)
                fluidInputs(Polyethylene.getFluid(L / 2))
                outputs(CraftingComponents.WRAP_CIRCUIT.getIngredient(tier) as ItemStack)
                EUt(VA[LV])
                duration(5.s)
            }
        }
    }

    private fun addRecipe(item: ItemStack, wrapItem: ItemStack)
    {
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(16)
            inputs(item.copy(16))
            fluidInputs(Polyethylene.getFluid(L / 2))
            outputs(wrapItem)
            EUt(VA[LV])
            duration(5.s)
        }
    }
}