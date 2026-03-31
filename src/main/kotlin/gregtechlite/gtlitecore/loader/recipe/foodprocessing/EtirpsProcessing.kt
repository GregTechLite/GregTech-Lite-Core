package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CranberryEtirps
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CranberryExtract
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CranberrySodaSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Etirps
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LemonLimeMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LemonLimeSodaSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumCarbonateSolution
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRANBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRANBERRY_ETIRPS
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ETIRPS
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PLASTIC_BOTTLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPARKLING_WATER

internal object EtirpsProcessing
{

    // @formatter:off

    fun init()
    {
        // Sparkling Water
        CANNER_RECIPES.addRecipe {
            input(PLASTIC_BOTTLE)
            fluidInputs(SodiumCarbonateSolution.getFluid(250))
            output(SPARKLING_WATER)
            EUt(4) // ULV
            duration(10 * TICK)
        }

        // Lemon-Lime Soda Syrup
        MIXER_RECIPES.addRecipe {
            input(dust, Sugar, 9)
            fluidInputs(LemonLimeMixture.getFluid(500))
            fluidInputs(SodiumCarbonateSolution.getFluid(1000))
            fluidOutputs(LemonLimeSodaSyrup.getFluid(2000))
            EUt(VA[MV])
            duration(10 * SECOND)
        }

        // Etirps™
        FLUID_HEATER_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(LemonLimeSodaSyrup.getFluid(100))
            fluidOutputs(Etirps.getFluid(100))
            EUt(VA[LV])
            duration(15 * TICK)
        }

        CANNER_RECIPES.addRecipe {
            input(PLASTIC_BOTTLE)
            fluidInputs(Etirps.getFluid(250))
            output(ETIRPS)
            EUt(4) // ULV
            duration(10 * TICK)
        }

        // Cranberry Extract
        EXTRACTOR_RECIPES.addRecipe {
            input(CRANBERRY)
            fluidOutputs(CranberryExtract.getFluid(25))
            EUt(5) // ULV
            duration(5 * SECOND)
        }

        // Cranberry Soda Syrup
        MIXER_RECIPES.addRecipe {
            input(dust, Sugar, 9)
            fluidInputs(CranberryExtract.getFluid(500))
            fluidInputs(SodiumCarbonateSolution.getFluid(1000))
            fluidOutputs(CranberrySodaSyrup.getFluid(2000))
            EUt(VA[MV])
            duration(10 * SECOND)
        }

        FLUID_HEATER_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(CranberrySodaSyrup.getFluid(100))
            fluidOutputs(CranberryEtirps.getFluid(100))
            EUt(VA[LV])
            duration(15 * TICK)
        }

        // Cranberry Etirps™
        CANNER_RECIPES.addRecipe {
            input(PLASTIC_BOTTLE)
            fluidInputs(CranberryEtirps.getFluid(250))
            output(CRANBERRY_ETIRPS)
            EUt(4) // ULV
            duration(10 * TICK)
        }

    }

    // @formatter:on

}