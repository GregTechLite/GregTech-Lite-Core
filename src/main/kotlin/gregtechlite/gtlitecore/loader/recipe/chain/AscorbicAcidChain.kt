package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.spring
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AscorbicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DehydroascorbicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sorbose
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object AscorbicAcidChain
{

    // @formatter:off

    fun init()
    {
        // C6H12O6 -> C6H8O6
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .notConsumable(spring, Platinum)
            .input(dust, Sorbose, 24)
            .fluidOutputs(AscorbicAcid.getFluid(1000))
            .EUt(VA[HV])
            .duration(14 * SECOND)
            .buildAndRegister()

        // C6H6O6 + 2H -> C6H8O6
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .notConsumable(spring, Nickel)
            .fluidInputs(DehydroascorbicAcid.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(2000))
            .fluidOutputs(AscorbicAcid.getFluid(1000))
            .EUt(VA[HV])
            .duration(7 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}