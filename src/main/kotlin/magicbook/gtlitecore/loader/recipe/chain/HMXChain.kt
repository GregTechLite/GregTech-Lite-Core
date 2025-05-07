package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CyclotetramethyleneTetranitroamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Formaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hexamethylenetetramine
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class HMXChain
{

    companion object
    {

        fun init()
        {
            // 6CH2O + 4NH3 -> (CH2)6N4 + 6H2O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Formaldehyde.getFluid(6000))
                .fluidInputs(Ammonia.getFluid(4000))
                .output(dust, Hexamethylenetetramine, 22)
                .fluidOutputs(Steam.getFluid(6000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // (CH2)6N4 + 2N2O4 -> C4H8N8O8 + C2H4
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Hexamethylenetetramine, 22)
                .fluidInputs(DinitrogenTetroxide.getFluid(2000))
                .fluidOutputs(CyclotetramethyleneTetranitroamine.getFluid(1000))
                .fluidOutputs(Ethylene.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}