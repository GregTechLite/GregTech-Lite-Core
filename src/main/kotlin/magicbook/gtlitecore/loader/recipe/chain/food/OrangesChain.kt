package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CitricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Etirps
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonLimeMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonLimeSodaSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LimeExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OrangeExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumCarbonate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ETIRPS
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.LEMON
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.LIME
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ORANGE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ORANGE_JUICE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PLASTIC_BOTTLE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPARKLING_WATER
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.ZEST_DUST
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class OrangesChain
{

    companion object
    {

        fun init()
        {

            ModHandler.addShapedRecipe(true, "zest_dust_lemon", ZEST_DUST.itemStack,
                "mA ",
                'A', LEMON)

            EXTRACTOR_RECIPES.recipeBuilder()
                .input(LEMON)
                .outputs(ZEST_DUST.itemStack)
                .fluidOutputs(LemonExtract.getFluid(100))
                .EUt(5) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()

            ModHandler.addShapedRecipe(true, "zest_dust_lime", ZEST_DUST.itemStack,
                "mA ",
                'A', LIME)

            EXTRACTOR_RECIPES.recipeBuilder()
                .input(LIME)
                .outputs(ZEST_DUST.itemStack)
                .fluidOutputs(LimeExtract.getFluid(100))
                .EUt(5) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()

            ModHandler.addShapedRecipe(true, "zest_dust_orange", ZEST_DUST.itemStack,
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
                .EUt(VA[MV].toLong())
                .duration(4 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Lemon Extract + Lime Extract -> Lemon-Lime Mixture
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(LemonExtract.getFluid(500))
                .fluidInputs(LimeExtract.getFluid(500))
                .fluidOutputs(LemonLimeMixture.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}