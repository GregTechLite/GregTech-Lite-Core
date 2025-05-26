package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.common.items.MetaItems.PLANT_BALL
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ARTICHOKE_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.AUBERGINE_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BASIL_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BEAN_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BLACK_PEPPER_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COFFEE_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CORN_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COTTON_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CUCUMBER_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GARLIC_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GRAPE_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.HORSERADISH_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ONION_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OREGANO_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PEA_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RICE_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SOY_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.TOMATO_SEED
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WHITE_GRAPE_SEED
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class SifterRecipes
{

    companion object
    {

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
                .EUt(VA[LV].toLong())
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
                .EUt(VA[LV].toLong())
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
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}