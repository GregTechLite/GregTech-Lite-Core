package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Mercury
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ACID_GENERATOR_FUELS

internal object AcidGeneratorRecipes
{

    // @formatter:off

    fun init()
    {
        ACID_GENERATOR_FUELS.recipeBuilder()
            .fluidInputs(Redstone.getFluid(L / 4))
            .EUt(V[LV])
            .duration(1 * SECOND + 15 * TICK)
            .buildAndRegister()

        ACID_GENERATOR_FUELS.recipeBuilder()
            .fluidInputs(Mercury.getFluid(500))
            .EUt(VA[MV])
            .duration(3 * SECOND + 10 * TICK)
            .buildAndRegister()

        ACID_GENERATOR_FUELS.recipeBuilder()
            .fluidInputs(SulfuricAcid.getFluid(250))
            .EUt(VA[MV])
            .duration(6 * SECOND + 10 * TICK)
            .buildAndRegister()

        ACID_GENERATOR_FUELS.recipeBuilder()
            .fluidInputs(HydrochloricAcid.getFluid(500))
            .EUt(VA[MV])
            .duration(4 * SECOND + 15 * TICK)
            .buildAndRegister()

        ACID_GENERATOR_FUELS.recipeBuilder()
            .fluidInputs(NitricAcid.getFluid(125))
            .EUt(VA[MV])
            .duration(6 * SECOND + 15 * TICK)
            .buildAndRegister()

        // TODO Organic Acids and other recipes.
    }

    // @formatter:on

}