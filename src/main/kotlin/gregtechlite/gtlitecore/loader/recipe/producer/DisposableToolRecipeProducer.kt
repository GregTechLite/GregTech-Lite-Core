package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GregTechAPI
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.properties.PropertyKey
import gregtechlite.gtlitecore.api.item.GTLiteToolHelper
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOOL_CASTER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_BUTCHERY_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_CROWBAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_FILE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_HARD_HAMMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_MORTAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_ROLLING_PIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SAW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SCREWDRIVER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SOFT_MALLET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WIRE_CUTTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WRENCH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_BUTCHERY_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_CROWBAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_FILE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_HARD_HAMMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_MORTAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_ROLLING_PIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_SAW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_SCREWDRIVER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_SOFT_MALLET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_WIRE_CUTTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DISPOSABLE_WRENCH
import net.minecraft.item.ItemStack

internal object DisposableToolRecipeProducer
{

    // @formatter:off

    fun produce()
    {
        for (material in GregTechAPI.materialManager.registeredMaterials)
        {
            processHardToolRecipe(DISPOSABLE_SAW, material, 2)
            processHardToolRecipe(DISPOSABLE_HARD_HAMMER, material, 6)
            processHardToolRecipe(DISPOSABLE_WRENCH, material, 4)
            processHardToolRecipe(DISPOSABLE_FILE, material, 2)
            processHardToolRecipe(DISPOSABLE_CROWBAR, material, 2)
            processHardToolRecipe(DISPOSABLE_SCREWDRIVER, material, 1)
            processHardToolRecipe(DISPOSABLE_MORTAR, material, 2)
            processHardToolRecipe(DISPOSABLE_WIRE_CUTTER, material, 4)
            processHardToolRecipe(DISPOSABLE_KNIFE, material, 1)
            processHardToolRecipe(DISPOSABLE_BUTCHERY_KNIFE, material, 4)
            processHardToolRecipe(DISPOSABLE_ROLLING_PIN, material, 2)
        }

        // Soft Mallet recipes.
        TOOL_CASTER_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_SOFT_MALLET)
            .fluidInputs(Rubber.getFluid(L))
            .output(DISPOSABLE_SOFT_MALLET, 42)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        TOOL_CASTER_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_SOFT_MALLET)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(DISPOSABLE_SOFT_MALLET, 64)
            .output(DISPOSABLE_SOFT_MALLET, 20)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        TOOL_CASTER_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_SOFT_MALLET)
            .fluidInputs(Polytetrafluoroethylene.getFluid(L))
            .output(DISPOSABLE_SOFT_MALLET, 64)
            .output(DISPOSABLE_SOFT_MALLET, 64)
            .output(DISPOSABLE_SOFT_MALLET, 40)
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    private fun processHardToolRecipe(toolStack: MetaItem<*>.MetaValueItem, material: Material, cost: Int)
    {
        if (material.hasProperty(PropertyKey.TOOL) && material.hasProperty(PropertyKey.FLUID))
        {
            var count = GTLiteToolHelper.getMaxCraftingDurability(material) / cost
            val outputStacks = mutableListOf<ItemStack>()

            var i = count
            while (i > 64)
            {
                outputStacks.add(toolStack.getStackForm(64))
                count -= 64
                i -= 64
            }
            outputStacks.add(toolStack.getStackForm(count))

            if (outputStacks.size < 9)
            {
                TOOL_CASTER_RECIPES.recipeBuilder()
                    .notConsumable(getCastingMoldByToolStack(toolStack.stackForm))
                    .fluidInputs(material.getFluid(L))
                    .outputs(outputStacks)
                    .EUt(VA[MV])
                    .duration(10 * SECOND)
                    .buildAndRegister()
            }
        }
    }

    private fun getCastingMoldByToolStack(stack: ItemStack): ItemStack = when
    {
        (stack.isItemEqual(DISPOSABLE_SAW.stackForm)) -> CASTING_MOLD_SAW.stackForm
        (stack.isItemEqual(DISPOSABLE_HARD_HAMMER.stackForm)) -> CASTING_MOLD_HARD_HAMMER.stackForm
        (stack.isItemEqual(DISPOSABLE_SOFT_MALLET.stackForm)) -> CASTING_MOLD_SOFT_MALLET.stackForm
        (stack.isItemEqual(DISPOSABLE_WRENCH.stackForm)) -> CASTING_MOLD_WRENCH.stackForm
        (stack.isItemEqual(DISPOSABLE_FILE.stackForm)) -> CASTING_MOLD_FILE.stackForm
        (stack.isItemEqual(DISPOSABLE_CROWBAR.stackForm)) -> CASTING_MOLD_CROWBAR.stackForm
        (stack.isItemEqual(DISPOSABLE_SCREWDRIVER.stackForm)) -> CASTING_MOLD_SCREWDRIVER.stackForm
        (stack.isItemEqual(DISPOSABLE_MORTAR.stackForm)) -> CASTING_MOLD_MORTAR.stackForm
        (stack.isItemEqual(DISPOSABLE_WIRE_CUTTER.stackForm)) -> CASTING_MOLD_WIRE_CUTTER.stackForm
        (stack.isItemEqual(DISPOSABLE_KNIFE.stackForm)) -> CASTING_MOLD_KNIFE.stackForm
        (stack.isItemEqual(DISPOSABLE_BUTCHERY_KNIFE.stackForm)) -> CASTING_MOLD_BUTCHERY_KNIFE.stackForm
        (stack.isItemEqual(DISPOSABLE_ROLLING_PIN.stackForm)) -> CASTING_MOLD_ROLLING_PIN.stackForm
        else -> throw IllegalArgumentException("Cannot get Casting Mold ItemStack by a invalid Disposable Tool ItemStack")
    }

    // @formatter:on

}