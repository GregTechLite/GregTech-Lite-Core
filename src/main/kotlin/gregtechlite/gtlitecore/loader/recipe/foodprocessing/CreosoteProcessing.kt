package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.material.Materials.BisphenolA
import gregtech.api.unification.material.Materials.Creosote
import gregtech.api.unification.material.Materials.Ethane
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Wood
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creosol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Guaiacol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydroquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methoxycreosol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resorcinol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Xylenol
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bitumen

internal object CreosoteProcessing
{

    // @formatter:off

    fun init()
    {
        // Redo creosote distillations.
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES, Creosote.getFluid(24))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Creosote.getFluid(24)))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(Creosote.getFluid(1000))
            .chancedOutput(dustTiny, Bitumen, 1500, 0)
            .fluidOutputs(Xylenol.getFluid(600)) // C8H10O
            .fluidOutputs(Creosol.getFluid(400)) // C7H8O
            .fluidOutputs(Phenol.getFluid(200)) // C6H6O
            .fluidOutputs(Methoxycreosol.getFluid(350)) // C8H10O2
            .fluidOutputs(Guaiacol.getFluid(150)) // C7H8O2
            .fluidOutputs(Lubricant.getFluid(100))
            .EUt(VA[MV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // C8H10O + C7H8O2 -> C15H16O2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Xylenol.getFluid(1000))
            .fluidInputs(Guaiacol.getFluid(1000))
            .fluidOutputs(BisphenolA.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(16 * SECOND)
            .buildAndRegister()

        // C7H8O + C2H6 + 3CO -> 2C6H6O2 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Creosol.getFluid(1000))
            .fluidInputs(Ethane.getFluid(1000))
            .fluidOutputs(Resorcinol.getFluid(2000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[IV])
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

        // C8H10O2 -> C6H4(OH)2 + C2H4
        CENTRIFUGE_RECIPES.recipeBuilder()
            .fluidInputs(Methoxycreosol.getFluid(1000))
            .fluidOutputs(Hydroquinone.getFluid(1000))
            .fluidOutputs(Ethylene.getFluid(1000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}