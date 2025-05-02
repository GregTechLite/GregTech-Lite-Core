package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fructose
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Glucose
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OxalicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VanadiumPentoxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class SugarChain
{

    companion object
    {

        fun init()
        {
            // Sugar (C2(H2O)5O25) -> Glucose (C6H12O6) + Fructose (C6H12O6)
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, Sugar, 2) // TODO Amount 2 or 48?
                .fluidInputs(Water.getFluid(1000))
                .output(dust, Glucose, 24)
                .output(dust, Fructose, 24)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // C6H12O6 + 9O -> 3C2H2O4 + 3H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, VanadiumPentoxide)
                .input(dust, Glucose, 24)
                .fluidInputs(Oxygen.getFluid(9000))
                .fluidOutputs(OxalicAcid.getFluid(3000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

    }

}