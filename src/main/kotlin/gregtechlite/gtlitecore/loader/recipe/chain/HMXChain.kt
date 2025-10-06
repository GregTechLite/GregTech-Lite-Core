package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CyclotetramethyleneTetranitroamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Formaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexamethylenetetramine
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.extension.EUt

internal object HMXChain
{

    // @formatter:off

    fun init()
    {
        // 6CH2O + 4NH3 -> (CH2)6N4 + 6H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Formaldehyde.getFluid(6000))
            .fluidInputs(Ammonia.getFluid(4000))
            .output(dust, Hexamethylenetetramine, 22)
            .fluidOutputs(Steam.getFluid(6 * SU))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // (CH2)6N4 + 2N2O4 -> C4H8N8O8 + C2H4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Hexamethylenetetramine, 22)
            .fluidInputs(DinitrogenTetroxide.getFluid(2000))
            .fluidOutputs(CyclotetramethyleneTetranitroamine.getFluid(1000))
            .fluidOutputs(Ethylene.getFluid(1000))
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}