package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Epichlorohydrin
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SeedOil
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AminooxyaceticAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BeanPhospholipid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FoodOilPhaseMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GlycidylStearate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lecithin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Monoglyceride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OliveOil
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumStearate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SoybeanOil
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StearicAcid

internal object AdditivesProcessing
{

    // @formatter:off

    fun init()
    {
        aminooxyaceticAcidProcess()
        gmsProcess()
    }

    private fun aminooxyaceticAcidProcess()
    {
        // C2H4O2 + HNO3 -> C2H5NO3 + 2O
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(AceticAcid.getFluid(1000))
            fluidInputs(NitricAcid.getFluid(1000))
            fluidOutputs(AminooxyaceticAcid.getFluid(1000))
            fluidOutputs(Oxygen.getFluid(2000))
            EUt(VA[HV])
            duration(10 * SECOND)
        }
    }

    private fun gmsProcess()
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

        // Food Oil Phase Mixture
        for (oil in arrayOf(SeedOil, OliveOil, SoybeanOil, Butter))
        {
            LARGE_MIXER_RECIPES.addRecipe {
                fluidInputs(oil.getFluid(2000))
                fluidInputs(BeanPhospholipid.getFluid(100))
                fluidInputs(Monoglyceride.getFluid(100))
                fluidOutputs(FoodOilPhaseMixture.getFluid(2000))
                EUt(VA[EV])
                duration(2 * SECOND + 10 * TICK)
            }

            LARGE_MIXER_RECIPES.addRecipe {
                fluidInputs(oil.getFluid(2000))
                fluidInputs(Lecithin.getFluid(50))
                fluidInputs(Monoglyceride.getFluid(100))
                fluidOutputs(FoodOilPhaseMixture.getFluid(2000))
                EUt(VA[EV])
                duration(2 * SECOND + 10 * TICK)
            }
        }
    }

    // @formatter:on

}