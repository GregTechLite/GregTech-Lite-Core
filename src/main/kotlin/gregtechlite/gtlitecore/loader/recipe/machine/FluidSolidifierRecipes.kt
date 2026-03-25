package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.unification.material.Materials.Blaze
import gregtech.api.unification.material.Materials.EnderPearl
import gregtech.api.unification.material.Materials.Glue
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROD
import gregtech.common.items.MetaItems.STICKY_RESIN
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resin
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object FluidSolidifierRecipes
{

    // @formatter:off

    fun init()
    {
        // Resin Solidification.
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BALL)
            .fluidInputs(Resin.getFluid(250))
            .output(STICKY_RESIN)
            .EUt(2) // ULV
            .duration(1 * SECOND)
            .buildAndRegister()

        // Ender Pearl Solidification.
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BALL)
            .fluidInputs(EnderPearl.getFluid(L))
            .outputs(ItemStack(Items.ENDER_PEARL))
            .EUt(7) // ULV
            .duration(10 * TICK)
            .buildAndRegister()

        // Slime Ball Solidification.
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BALL)
            .fluidInputs(Glue.getFluid(250))
            .outputs(ItemStack(Items.SLIME_BALL))
            .EUt(7) // ULV
            .duration(10 * TICK)
            .buildAndRegister()

        // Fix incorrect solidified amount for blaze rod.
        GTRecipeHandler.removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES,
            arrayOf(SHAPE_MOLD_ROD.stack()), arrayOf(Blaze.getFluid(L / 2)))

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_ROD)
            .fluidInputs(Blaze.getFluid(L * 4))
            .outputs(ItemStack(Items.BLAZE_ROD))
            .EUt(VA[LV])
            .duration(7 * SECOND + 5 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}