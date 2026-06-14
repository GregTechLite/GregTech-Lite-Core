package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Epichlorohydrin
import gregtech.api.unification.material.Materials.FishOil
import gregtech.api.unification.material.Materials.Gelatin
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SeedOil
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SONICATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AscorbicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BeanPhospholipid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Biotin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaneSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GlycidylStearate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Monoglyceride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NutrientPasteCrudeEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NutrientPasteEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NutrientPasteOilPhaseMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NutrientPasteWaterPhaseMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OliveOil
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichNutrientPasteCrudeEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichNutrientPasteEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichNutrientPasteWaterPhaseMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumStearate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SoybeanOil
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StearicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SterilizedNutrientPasteEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SterilizedRichNutrientPasteEmulsion
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NUTRIENT_PASTE_MEAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PVC_CAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICH_NUTRIENT_PASTE_MEAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.PLANT_PROTEIN

internal object NutrientPasteMealProcessing
{

    // @formatter:off

    fun init()
    {
        monoglycerideProcess()
        commonProcess()
    }

    private fun monoglycerideProcess()
    {
        // C3H5ClO + C18H35O2Na -> C21H40O3 + NaCl
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(Epichlorohydrin.getFluid(1000))
            fluidInputs(SodiumStearate.getFluid(1000))
            output(dust, Salt, 2)
            fluidOutputs(GlycidylStearate.getFluid(1000))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // C21H40O3 + H2O -> C21H42O4
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(GlycidylStearate.getFluid(1000))
            fluidInputs(Water.getFluid(1000))
            fluidOutputs(Monoglyceride.getFluid(1000))
            EUt(VA[LV])
            duration(5 * SECOND)
        }

        // Advanced recipe for C21H42O4

        // NaOH + C3H8O3 + C18H36O2 -> C21H42O4 + O (drop)
        VACUUM_CHAMBER_RECIPES.addRecipe {
            notConsumable(dust, SodiumHydroxide)
            fluidInputs(StearicAcid.getFluid(1000))
            fluidInputs(Glycerol.getFluid(1000))
            fluidOutputs(Monoglyceride.getFluid(1000))
            EUt(VA[EV])
            duration(5 * SECOND)
        }
    }

    private fun commonProcess()
    {
        // Nutrient Paste Water Phase Mixture
        MIXER_RECIPES.addRecipe {
            circuitMeta(5)
            inputs(PLANT_PROTEIN.stack())
            input(dust, Salt)
            input(dust, Gelatin, 2)
            fluidInputs(CaneSyrup.getFluid(200))
            fluidInputs(DistilledWater.getFluid(400))
            fluidOutputs(NutrientPasteWaterPhaseMixture.getFluid(1000))
            EUt(VA[HV])
            duration(4 * SECOND + 16 * TICK)
            cleanroom()
        }

        // Rich Nutrient Paste Water Phase Mixture
        LARGE_MIXER_RECIPES.addRecipe {
            circuitMeta(7)
            inputs(PLANT_PROTEIN.stack())
            input(dust, Salt, 2)
            input(dust, Gelatin, 4)
            input(dust, Biotin, 8)
            fluidInputs(CaneSyrup.getFluid(800))
            fluidInputs(AscorbicAcid.getFluid(1000))
            fluidInputs(DistilledWater.getFluid(1600))
            fluidOutputs(RichNutrientPasteWaterPhaseMixture.getFluid(1000))
            EUt(VA[IV])
            duration(2 * SECOND + 8 * TICK)
            cleanroom()
        }

        // Nutrient Paste Oil Phase Mixture
        for (oil in arrayOf(SeedOil, FishOil, OliveOil, SoybeanOil))
        {
            LARGE_MIXER_RECIPES.addRecipe {
                fluidInputs(oil.getFluid(1000))
                fluidInputs(BeanPhospholipid.getFluid(1000))
                fluidInputs(Monoglyceride.getFluid(1000))
                fluidOutputs(NutrientPasteOilPhaseMixture.getFluid(3000))
                EUt(VA[EV])
                duration(2 * SECOND + 10 * TICK)
            }
        }

        // Nutrient Paste Water Phase Mixture + Oil Phase Mixture -> Nutrient Paste Crude Emulsion
        MIXER_RECIPES.addRecipe {
            fluidInputs(NutrientPasteWaterPhaseMixture.getFluid(1000))
            fluidInputs(NutrientPasteOilPhaseMixture.getFluid(3000))
            fluidOutputs(NutrientPasteCrudeEmulsion.getFluid(4000))
            EUt(VA[EV])
            duration(4 * SECOND)
        }

        // Rich Nutrient Paste Water Phase Mixture + Nutrient Paste Oil Phase Mixture -> Rich Nutrient Paste Crude Emulsion
        MIXER_RECIPES.addRecipe {
            fluidInputs(RichNutrientPasteWaterPhaseMixture.getFluid(1000))
            fluidInputs(NutrientPasteOilPhaseMixture.getFluid(3000))
            fluidOutputs(RichNutrientPasteCrudeEmulsion.getFluid(4000))
            EUt(VA[IV])
            duration(2 * SECOND)
        }

        // Nutrient Paste Crude Emulsion -> Nutrient Paste Emulsion
        SONICATION_RECIPES.addRecipe {
            fluidInputs(NutrientPasteCrudeEmulsion.getFluid(2000))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidOutputs(NutrientPasteEmulsion.getFluid(1000))
            EUt(VA[IV])
            duration(10 * SECOND)
        }

        // Rich Nutrient Paste Crude Emulsion -> Rich Nutrient Paste Emulsion
        SONICATION_RECIPES.addRecipe {
            fluidInputs(RichNutrientPasteCrudeEmulsion.getFluid(2000))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidOutputs(RichNutrientPasteEmulsion.getFluid(1000))
            EUt(VA[LuV])
            duration(10 * SECOND)
        }

        // Nutrient Paste Emulsion -> Sterilized Nutrient Paste Emulsion
        FLUID_HEATER_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(NutrientPasteEmulsion.getFluid(250))
            fluidOutputs(SterilizedNutrientPasteEmulsion.getFluid(100))
            EUt(VA[HV])
            duration(8 * SECOND)
            cleanroom()
        }

        // Rich Nutrient Paste Emulsion -> Sterilized Rich Nutrient Paste Emulsion
        FLUID_HEATER_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(RichNutrientPasteEmulsion.getFluid(500))
            fluidOutputs(SterilizedRichNutrientPasteEmulsion.getFluid(200))
            EUt(VA[HV])
            duration(8 * SECOND)
            cleanroom()
        }

        // Nutrient Paste Meal
        CANNER_RECIPES.addRecipe {
            input(PVC_CAN)
            fluidInputs(SterilizedNutrientPasteEmulsion.getFluid(100))
            output(NUTRIENT_PASTE_MEAL)
            EUt(4) // ULV
            duration(10 * TICK)
        }

        // Rich Nutrient Paste Meal
        CANNER_RECIPES.addRecipe {
            input(PVC_CAN)
            fluidInputs(SterilizedRichNutrientPasteEmulsion.getFluid(200))
            output(RICH_NUTRIENT_PASTE_MEAL)
            EUt(4) // ULV
            duration(10 * TICK)
        }
    }

    // @formatter:on

}
