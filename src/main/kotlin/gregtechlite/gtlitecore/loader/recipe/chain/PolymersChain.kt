package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Caprolactam
import gregtech.api.unification.material.Materials.Isoprene
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Polycaprolactam
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyisoprene

internal object PolymersChain
{

    // @formatter:off

    fun init()
    {
        polycaprolactamProcess()
        polyisopreneProcess()
    }

    private fun polycaprolactamProcess()
    {
        // Deleted EBF usage for polymerization process.
        BLAST_RECIPES.removeRecipe(
            arrayOf(OreDictUnifier.get(dust, Caprolactam)),
            arrayOf(Nitrogen.getFluid(1000)))

        BURNER_REACTOR_RECIPES.addRecipe {
            input(dust, Caprolactam)
            fluidInputs(Nitrogen.getFluid(1000))
            output(ingot, Polycaprolactam)
            EUt(VA[MV])
            duration(7 * SECOND + 10 * TICK)
        }
    }

    private fun polyisopreneProcess()
    {
        CHEMICAL_RECIPES.addRecipe {
            circuitMeta(2)
            fluidInputs(Air.getFluid(1000))
            fluidInputs(Isoprene.getFluid(L))
            fluidOutputs(Polyisoprene.getFluid(L))
            EUt(VA[MV])
            duration(10 * SECOND)
        }

        CHEMICAL_RECIPES.addRecipe {
            circuitMeta(2)
            fluidInputs(Oxygen.getFluid(1000))
            fluidInputs(Isoprene.getFluid(L))
            fluidOutputs(Polyisoprene.getFluid(L + L / 2))
            EUt(VA[MV])
            duration(10 * SECOND)
        }

        LARGE_CHEMICAL_RECIPES.addRecipe {
            circuitMeta(3)
            fluidInputs(Air.getFluid(7500))
            fluidInputs(Isoprene.getFluid(L * 15))
            fluidInputs(TitaniumTetrachloride.getFluid(100))
            fluidOutputs(Polyisoprene.getFluid(L * 22 + L / 2))
            EUt(VA[MV])
            duration(50 * SECOND)
        }

        LARGE_CHEMICAL_RECIPES.addRecipe {
            circuitMeta(3)
            fluidInputs(Oxygen.getFluid(7500))
            fluidInputs(Isoprene.getFluid(L * 15))
            fluidInputs(TitaniumTetrachloride.getFluid(100))
            fluidOutputs(Polyisoprene.getFluid(L * 30))
            EUt(VA[MV])
            duration(50 * SECOND)
        }
    }

    // @formatter:on

}