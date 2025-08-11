package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BOTTLE
import gregtech.common.items.MetaItems.SHAPE_MOLD_BOTTLE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CERAMIC_BOWL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CERAMIC_CUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLAY_BOWL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLAY_CUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIRTY_CERAMIC_BOWL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PLASTIC_BOTTLE
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object TablewareProcessing
{

    // @formatter:off

    fun init()
    {
        // Clay Bowl
        ModHandler.addShapedRecipe(true, "clay_bowl", CLAY_BOWL.stackForm,
            "k p", "C C", "CCC",
            'C', ItemStack(Items.CLAY_BALL))

        // Clay Cup
        ModHandler.addShapedRecipe(true, "clay_cup", CLAY_CUP.getStackForm(2),
            "pr ", "RCC", "kCC",
            'C', ItemStack(Items.CLAY_BALL),
            'R', UnificationEntry(stick, Clay))

        // Ceramic Bowl
        ModHandler.addSmeltingRecipe(CLAY_BOWL.stackForm, CERAMIC_BOWL.stackForm)

        // Ceramic Cup
        ModHandler.addSmeltingRecipe(CLAY_CUP.stackForm, CERAMIC_CUP.stackForm)

        // Dirty Ceramic Bowl -> Ceramic Bowl
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(DIRTY_CERAMIC_BOWL)
            .fluidInputs(Water.getFluid(100))
            .output(CERAMIC_BOWL)
            .EUt(VA[LV])
            .duration(1 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Plastic Bottle
        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_BOTTLE)
            .input(ingot, Polyethylene)
            .output(PLASTIC_BOTTLE)
            .EUt(VA[LV])
            .duration(1 * SECOND + 12 * TICK)
            .buildAndRegister()

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_BOTTLE)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(PLASTIC_BOTTLE)
            .EUt(4) // ULV
            .duration(12 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}