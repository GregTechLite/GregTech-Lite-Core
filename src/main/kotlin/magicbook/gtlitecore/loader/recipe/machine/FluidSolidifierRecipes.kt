package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.unification.material.Materials.EnderPearl
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class FluidSolidifierRecipes
{

    companion object
    {

        fun init()
        {
            // Ender Pearl Solidification.
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BALL)
                .fluidInputs(EnderPearl.getFluid(L))
                .outputs(ItemStack(Items.ENDER_PEARL))
                .EUt(7) // ULV
                .duration(10 * TICK)
                .buildAndRegister()
        }

    }

}