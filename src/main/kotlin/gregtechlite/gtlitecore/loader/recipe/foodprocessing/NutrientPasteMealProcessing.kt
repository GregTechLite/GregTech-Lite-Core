package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Gelatin
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SONICATION_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AscorbicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Biotin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaneSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FoodOilPhaseMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NutrientPasteCrudeEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NutrientPasteEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NutrientPasteWaterPhaseMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichNutrientPasteCrudeEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichNutrientPasteEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichNutrientPasteWaterPhaseMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SterilizedNutrientPasteEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SterilizedRichNutrientPasteEmulsion
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NUTRIENT_PASTE_MEAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PVC_CAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICH_NUTRIENT_PASTE_MEAL

internal object NutrientPasteMealProcessing
{

    // @formatter:off

    fun init()
    {
        // Nutrient Paste Water Phase Mixture
        MIXER_RECIPES.addRecipe {
            circuitMeta(5)
            input("dustSmallProtein", 8)
            input(dust, Salt, 2)
            input(dust, Gelatin, 2)
            fluidInputs(CaneSyrup.getFluid(1000))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidOutputs(NutrientPasteWaterPhaseMixture.getFluid(2000))
            EUt(VA[HV])
            duration(4 * SECOND + 16 * TICK)
            cleanroom()
        }

        // Rich Nutrient Paste Water Phase Mixture
        LARGE_MIXER_RECIPES.addRecipe {
            circuitMeta(7)
            input("dustSmallProtein", 8)
            input(dust, Salt, 2)
            input(dust, Gelatin, 2)
            input(dust, Biotin)
            fluidInputs(AscorbicAcid.getFluid(250))
            fluidInputs(CaneSyrup.getFluid(1000))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidOutputs(RichNutrientPasteWaterPhaseMixture.getFluid(2000))
            EUt(VA[HV])
            duration(4 * SECOND + 16 * TICK)
            cleanroom()
        }

        // Nutrient Paste Water Phase Mixture + Food Oil Phase Mixture -> Nutrient Paste Crude Emulsion
        MIXER_RECIPES.addRecipe {
            fluidInputs(NutrientPasteWaterPhaseMixture.getFluid(2000))
            fluidInputs(FoodOilPhaseMixture.getFluid(1000))
            fluidOutputs(NutrientPasteCrudeEmulsion.getFluid(3000))
            EUt(VA[EV])
            duration(8 * SECOND)
        }

        // Rich Nutrient Paste Water Phase Mixture + Food Oil Phase Mixture -> Rich Nutrient Paste Crude Emulsion
        MIXER_RECIPES.addRecipe {
            fluidInputs(RichNutrientPasteWaterPhaseMixture.getFluid(2000))
            fluidInputs(FoodOilPhaseMixture.getFluid(1000))
            fluidOutputs(RichNutrientPasteCrudeEmulsion.getFluid(3000))
            EUt(VA[EV])
            duration(8 * SECOND)
        }

        // Nutrient Paste Crude Emulsion -> Nutrient Paste Emulsion
        SONICATION_RECIPES.addRecipe {
            fluidInputs(NutrientPasteCrudeEmulsion.getFluid(3000))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidOutputs(NutrientPasteEmulsion.getFluid(4000))
            EUt(VA[EV])
            duration(30 * SECOND)
        }

        // Rich Nutrient Paste Crude Emulsion -> Rich Nutrient Paste Emulsion
        SONICATION_RECIPES.addRecipe {
            fluidInputs(RichNutrientPasteCrudeEmulsion.getFluid(3000))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidOutputs(RichNutrientPasteEmulsion.getFluid(4000))
            EUt(VA[IV])
            duration(30 * SECOND)
        }

        // Nutrient Paste Emulsion -> Sterilized Nutrient Paste Emulsion
        FLUID_HEATER_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(NutrientPasteEmulsion.getFluid(250))
            fluidOutputs(SterilizedNutrientPasteEmulsion.getFluid(250))
            EUt(VA[HV])
            duration(1 * SECOND + 10 * TICK)
            cleanroom()
        }

        // Rich Nutrient Paste Emulsion -> Sterilized Rich Nutrient Paste Emulsion
        FLUID_HEATER_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(RichNutrientPasteEmulsion.getFluid(250))
            fluidOutputs(SterilizedRichNutrientPasteEmulsion.getFluid(250))
            EUt(VA[HV])
            duration(1 * SECOND + 10 * TICK)
            cleanroom()
        }

        // Nutrient Paste Meal
        CANNER_RECIPES.addRecipe {
            input(PVC_CAN)
            fluidInputs(SterilizedNutrientPasteEmulsion.getFluid(250))
            output(NUTRIENT_PASTE_MEAL)
            EUt(V[ULV])
            duration(10 * TICK)
        }

        // Rich Nutrient Paste Meal
        CANNER_RECIPES.addRecipe {
            input(PVC_CAN)
            fluidInputs(SterilizedRichNutrientPasteEmulsion.getFluid(250))
            output(RICH_NUTRIENT_PASTE_MEAL)
            EUt(V[ULV])
            duration(10 * TICK)
        }
    }

    // @formatter:on

}
