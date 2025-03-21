package magicbook.gtlitecore.loader.recipe.producer

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
import magicbook.gtlitecore.api.item.GTLiteToolHelper
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.TOOL_CASTER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_BUTCHERY_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_CROWBAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_FILE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_HARD_HAMMER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_MORTAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_ROLLING_PIN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SAW
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SCREWDRIVER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SOFT_MALLET
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_WIRE_CUTTER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_WRENCH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_BUTCHERY_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_CROWBAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_FILE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_HARD_HAMMER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_MORTAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_ROLLING_PIN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_SAW
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_SCREWDRIVER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_SOFT_MALLET
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_WIRE_CUTTER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DISPOSABLE_WRENCH
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class DisposableToolRecipeProducer
{

    companion object
    {

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
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            TOOL_CASTER_RECIPES.recipeBuilder()
                .notConsumable(CASTING_MOLD_SOFT_MALLET)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(DISPOSABLE_SOFT_MALLET, 64)
                .output(DISPOSABLE_SOFT_MALLET, 20)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            TOOL_CASTER_RECIPES.recipeBuilder()
                .notConsumable(CASTING_MOLD_SOFT_MALLET)
                .fluidInputs(Polytetrafluoroethylene.getFluid(L))
                .output(DISPOSABLE_SOFT_MALLET, 64)
                .output(DISPOSABLE_SOFT_MALLET, 64)
                .output(DISPOSABLE_SOFT_MALLET, 40)
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

        private fun processHardToolRecipe(toolStack: MetaItem<*>.MetaValueItem,
                                           material: Material, cost: Int)
        {
            if (material.hasProperty(PropertyKey.TOOL)
                && material.hasProperty(PropertyKey.FLUID))
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
                        .EUt(VA[MV].toLong())
                        .duration(10 * SECOND)
                        .buildAndRegister()
                }
            }
        }

        private fun getCastingMoldByToolStack(stack: ItemStack): ItemStack
        {

            return when
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

        }

    }

}