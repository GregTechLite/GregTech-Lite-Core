package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CitricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LemonExtract
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LemonLimeMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LimeExtract
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OrangeExtract
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LEMON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LIME
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ORANGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ORANGE_JUICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.ZEST_DUST
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object OrangesProcessing
{

    // @formatter:off

    fun init()
    {

        ModHandler.addShapedRecipe(false, "zest_dust_lemon", ZEST_DUST.itemStack,
            "mA ",
            'A', LEMON)

        EXTRACTOR_RECIPES.recipeBuilder()
            .input(LEMON)
            .outputs(ZEST_DUST.itemStack)
            .fluidOutputs(LemonExtract.getFluid(100))
            .EUt(5) // ULV
            .duration(5 * SECOND)
            .buildAndRegister()

        ModHandler.addShapedRecipe(false, "zest_dust_lime", ZEST_DUST.itemStack,
            "mA ",
            'A', LIME)

        EXTRACTOR_RECIPES.recipeBuilder()
            .input(LIME)
            .outputs(ZEST_DUST.itemStack)
            .fluidOutputs(LimeExtract.getFluid(100))
            .EUt(5) // ULV
            .duration(5 * SECOND)
            .buildAndRegister()

        ModHandler.addShapedRecipe(false, "zest_dust_orange", ZEST_DUST.itemStack,
            "mA ",
            'A', ORANGE)

        EXTRACTOR_RECIPES.recipeBuilder()
            .input(ORANGE)
            .outputs(ZEST_DUST.itemStack)
            .fluidOutputs(OrangeExtract.getFluid(100))
            .EUt(5) // ULV
            .duration(5 * SECOND)
            .buildAndRegister()

        // Orange Juice
        CANNER_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.GLASS_BOTTLE))
            .fluidInputs(OrangeExtract.getFluid(250))
            .output(ORANGE_JUICE)
            .EUt(4) // ULV
            .duration(10 * TICK)
            .buildAndRegister()

        // Lemon Extract -> Citric Acid
        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(LemonExtract.getFluid(1000))
            .fluidOutputs(CitricAcid.getFluid(100))
            .EUt(VA[MV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Lemon Extract + Lime Extract -> Lemon-Lime Mixture
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(LemonExtract.getFluid(500))
            .fluidInputs(LimeExtract.getFluid(500))
            .fluidOutputs(LemonLimeMixture.getFluid(1000))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}