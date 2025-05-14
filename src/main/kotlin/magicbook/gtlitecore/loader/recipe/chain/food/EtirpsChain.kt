package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CranberryEtirps
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CranberryExtract
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CranberrySodaSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Etirps
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonLimeMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LemonLimeSodaSyrup
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumCarbonate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CRANBERRY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CRANBERRY_ETIRPS
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ETIRPS
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PLASTIC_BOTTLE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPARKLING_WATER

@Suppress("MISSING_DEPENDENCY_CLASS")
class EtirpsChain
{

    companion object
    {

        fun init()
        {
            // Sparkling Water
            CANNER_RECIPES.recipeBuilder()
                .input(PLASTIC_BOTTLE)
                .fluidInputs(SodiumCarbonate.getFluid(250))
                .output(SPARKLING_WATER)
                .EUt(4) // ULV
                .duration(10 * TICK)
                .buildAndRegister()

            // Lemon-Lime Soda Syrup
            MIXER_RECIPES.recipeBuilder()
                .input(dust, Sugar, 9)
                .fluidInputs(LemonLimeMixture.getFluid(500))
                .fluidInputs(SodiumCarbonate.getFluid(1000))
                .fluidOutputs(LemonLimeSodaSyrup.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Etirps™
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(LemonLimeSodaSyrup.getFluid(100))
                .fluidOutputs(Etirps.getFluid(100))
                .EUt(VA[LV].toLong())
                .duration(15 * TICK)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .input(PLASTIC_BOTTLE)
                .fluidInputs(Etirps.getFluid(250))
                .output(ETIRPS)
                .EUt(4) // ULV
                .duration(10 * TICK)
                .buildAndRegister()

            // Cranberry Extract
            EXTRACTOR_RECIPES.recipeBuilder()
                .input(CRANBERRY)
                .fluidOutputs(CranberryExtract.getFluid(25))
                .EUt(5) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()

            // Cranberry Soda Syrup
            MIXER_RECIPES.recipeBuilder()
                .input(dust, Sugar, 9)
                .fluidInputs(CranberryExtract.getFluid(500))
                .fluidInputs(SodiumCarbonate.getFluid(1000))
                .fluidOutputs(CranberrySodaSyrup.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(CranberrySodaSyrup.getFluid(100))
                .fluidOutputs(CranberryEtirps.getFluid(100))
                .EUt(VA[LV].toLong())
                .duration(15 * TICK)
                .buildAndRegister()

            // Cranberry Etirps™
            CANNER_RECIPES.recipeBuilder()
                .input(PLASTIC_BOTTLE)
                .fluidInputs(CranberryEtirps.getFluid(250))
                .output(CRANBERRY_ETIRPS)
                .EUt(4) // ULV
                .duration(10 * TICK)
                .buildAndRegister()

        }

    }

}