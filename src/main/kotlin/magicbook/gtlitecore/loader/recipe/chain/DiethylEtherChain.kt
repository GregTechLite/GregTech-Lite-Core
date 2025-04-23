package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Ethylene
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VACUUM_CHAMBER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DiethylEther
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class DiethylEtherChain
{

    companion object
    {

        fun init()
        {
            // C2H6O + C2H4 -> (C2H5)2O
            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(DiethylEther.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(14 * SECOND)
                .buildAndRegister()
        }

    }

}