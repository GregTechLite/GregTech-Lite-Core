package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.BREWING_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Magnetite
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RichAmmoniaMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RichNitrogenMixture
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class AmmoniaChain
{

    companion object
    {

        fun init()
        {
            // CH4 + N -> CH4N
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Air.getFluid(1500))
                .fluidOutputs(RichNitrogenMixture.getFluid(2500))
                .EUt(VA[MV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidOutputs(RichNitrogenMixture.getFluid(2500))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // CH4N + 2H2O -> NH4 + CH4 (cycle) + O2 (lost)
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Chrome)
                .fluidInputs(RichNitrogenMixture.getFluid(2500))
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(RichAmmoniaMixture.getFluid(1000))
                .fluidOutputs(Methane.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // NH4 -> NH3 + H (lost)
            BREWING_RECIPES.recipeBuilder()
                .notConsumable(dust, Magnetite)
                .fluidInputs(RichAmmoniaMixture.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

        }

    }

}