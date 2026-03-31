package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.DilutedSulfuricAcid
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.NitrationMixture
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GrapheneOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hydrazine
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETRON

internal object GrapheneChain
{

    // @formatter:off

    fun init()
    {
        // Remove original graphene mixing recipes.
        GTLiteRecipeHandler.removeMixerRecipes(
            OreDictUnifier.get(dust, Graphite),
            OreDictUnifier.get(dust, Carbon, 4),
            OreDictUnifier.get(dust, Silicon),
            IntCircuitIngredient.getIntegratedCircuit(1))

        // C8 + 2(HNO3)(H2SO4) -> C8O + (H2SO4)2(H2O) + HNO3
        CHEMICAL_RECIPES.addRecipe {
            notConsumable(dust, SodiumHydroxide)
            input(dust, Graphite)
            fluidInputs(NitrationMixture.getFluid(2000))
            output(dust, GrapheneOxide)
            fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
            fluidOutputs(NitricAcid.getFluid(1000))
            EUt(VA[HV])
            duration(5 * SECOND)
        }

        // C8O + N2H + Ar -> C8
        CHEMICAL_RECIPES.addRecipe {
            input(dust, GrapheneOxide)
            fluidInputs(Hydrazine.getFluid(100))
            fluidInputs(Argon.getFluid(50))
            output(dust, Graphene)
            EUt(VA[HV])
            duration(5 * SECOND)
        }

        // One-step recipe from Graphite to Graphene.
        CHEMICAL_RECIPES.addRecipe {
            notConsumable(MAGNETRON)
            input(dust, Graphite)
            fluidInputs(NitrationMixture.getFluid(2000))
            output(dust, Graphene)
            fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
            fluidOutputs(NitricAcid.getFluid(1000))
            EUt(VA[HV])
            duration(5 * SECOND)
        }
    }

    // @formatter:on

}