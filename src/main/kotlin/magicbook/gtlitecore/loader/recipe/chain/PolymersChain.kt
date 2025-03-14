package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Isoprene
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polyisoprene
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class PolymersChain
{

    companion object
    {

        fun init()
        {
            polyisopreneProcessing()
        }

        private fun polyisopreneProcessing()
        {

            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Air.getFluid(1000))
                .fluidInputs(Isoprene.getFluid(L))
                .fluidOutputs(Polyisoprene.getFluid(L))
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Isoprene.getFluid(L))
                .fluidOutputs(Polyisoprene.getFluid(L + L / 2))
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .fluidInputs(Air.getFluid(7500))
                .fluidInputs(Isoprene.getFluid(L * 15))
                .fluidInputs(TitaniumTetrachloride.getFluid(100))
                .fluidOutputs(Polyisoprene.getFluid(L * 22 + L / 2))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .fluidInputs(Oxygen.getFluid(7500))
                .fluidInputs(Isoprene.getFluid(L * 15))
                .fluidInputs(TitaniumTetrachloride.getFluid(100))
                .fluidOutputs(Polyisoprene.getFluid(L * 30))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

        }

    }

}