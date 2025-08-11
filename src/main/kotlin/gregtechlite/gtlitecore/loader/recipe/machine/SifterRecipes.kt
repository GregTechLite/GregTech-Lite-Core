package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.common.items.MetaItems.PLANT_BALL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ARTICHOKE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AUBERGINE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BASIL_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BEAN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_PEPPER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CORN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COTTON_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CUCUMBER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GARLIC_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSERADISH_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ONION_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OREGANO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEA_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOY_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOMATO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_GRAPE_SEED
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object SifterRecipes
{

    // @formatter:off

    fun init()
    {
        // Planet ball split.
        SIFTER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(PLANT_BALL)
            .chancedOutput(ItemStack(Items.WHEAT_SEEDS), 4500, 750)
            .chancedOutput(ItemStack(Items.PUMPKIN_SEEDS), 3750, 600)
            .chancedOutput(ItemStack(Items.MELON_SEEDS), 3250, 550)
            .chancedOutput(ItemStack(Items.BEETROOT_SEEDS), 2850, 450)
            .chancedOutput(COFFEE_SEED.stackForm, 2700, 425)
            .chancedOutput(TOMATO_SEED.stackForm, 2650, 400)
            .chancedOutput(ONION_SEED.stackForm, 2450, 375)
            .chancedOutput(CUCUMBER_SEED.stackForm, 2300, 325)
            .chancedOutput(GRAPE_SEED.stackForm, 2250, 275)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        SIFTER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(PLANT_BALL)
            .chancedOutput(SOY_SEED.stackForm, 3500, 750)
            .chancedOutput(BEAN_SEED.stackForm, 3350, 700)
            .chancedOutput(PEA_SEED.stackForm, 3200, 675)
            .chancedOutput(OREGANO_SEED.stackForm, 3150, 650)
            .chancedOutput(HORSERADISH_SEED.stackForm, 3000, 600)
            .chancedOutput(GARLIC_SEED.stackForm, 2750, 550)
            .chancedOutput(BASIL_SEED.stackForm, 2550, 475)
            .chancedOutput(AUBERGINE_SEED, 2400, 425)
            .chancedOutput(CORN_SEED.stackForm, 2250, 350)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        SIFTER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(PLANT_BALL)
            .chancedOutput(ARTICHOKE_SEED.stackForm, 3500, 750)
            .chancedOutput(BLACK_PEPPER_SEED.stackForm, 3250, 600)
            .chancedOutput(RICE_SEED.stackForm, 3000, 550)
            .chancedOutput(WHITE_GRAPE_SEED.stackForm, 2750, 475)
            .chancedOutput(COTTON_SEED.stackForm, 2500, 350)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}