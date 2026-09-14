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
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ACID_GENERATOR_FUELS
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.api.t

internal object AcidGeneratorRecipes
{

    // @formatter:off

    fun init()
    {
        ACID_GENERATOR_FUELS.addRecipe {
            fluidInputs(Redstone.getFluid(L / 4))
            EUt(V[LV])
            duration(1.s + 15.t)
        }

        ACID_GENERATOR_FUELS.addRecipe {
            fluidInputs(Mercury.getFluid(500))
            EUt(VA[MV])
            duration(3.s + 10.t)
        }

        ACID_GENERATOR_FUELS.addRecipe {
            fluidInputs(SulfuricAcid.getFluid(250))
            EUt(VA[MV])
            duration(6.s + 10.t)
        }

        ACID_GENERATOR_FUELS.addRecipe {
            fluidInputs(HydrochloricAcid.getFluid(500))
            EUt(VA[MV])
            duration(4.s + 15.t)
        }

        ACID_GENERATOR_FUELS.addRecipe {
            fluidInputs(NitricAcid.getFluid(125))
            EUt(VA[MV])
            duration(6.s + 15.t)
        }

        // TODO: Organic Acids and other recipes.
    }

    // @formatter:on

}