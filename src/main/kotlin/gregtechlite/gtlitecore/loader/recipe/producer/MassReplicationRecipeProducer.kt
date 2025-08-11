package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.V
import gregtech.api.recipes.RecipeMaps.MASS_FABRICATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.REPLICATOR_RECIPES
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BosonicUUMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FermionicUUMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.material.info.MaterialInfoCache
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK

/**
 * Raw formula: UUM = "Bosonic" + "Fermionic" + electron.
 *
 * In reality world, for $d$-dim particle state space $X$, let $Y$ be a new state space which split it with all single
 * quantum state with several particles, then when $d > 2$, $Y$ is simply connected. The basic group of $X$ is
 * isomorphic of group action, and then:
 * - "Bosonic" is the trivial representation, wave function is invariant;
 * - "Fermionic" is even/odd with ±1, wave function get a negative sign.
 *
 * This is an interesting pun: "half-integer" (spin number) and "mass % 2", we ensure bosonic and fermionic by mass
 * properties of elements... ^^
 */
internal object MassReplicationRecipeProducer
{

    // @formatter:off

    fun produce()
    {
        val t = 37 * SECOND + 10 * TICK // Time unit of replications.
        for (material in MaterialInfoCache.periodicTableMaterials)
        {
            val mass = material.mass.toInt()
            val uuMatter = if (mass % 2 == 0)
                BosonicUUMatter.getFluid(mass)
            else
                FermionicUUMatter.getFluid(mass)

            if (material.hasProperty(PropertyKey.FLUID))
            {
                val amount = if (material.hasProperty(PropertyKey.INGOT)) L else 1000

                // Element -> UU Matter + Free Electron Gas
                MASS_FABRICATOR_RECIPES.recipeBuilder()
                    .fluidInputs(material.getFluid(amount))
                    .fluidOutputs(uuMatter)
                    .fluidOutputs(FreeElectronGas.getFluid(mass))
                    .EUt(V[LV])
                    .duration(mass * t)
                    .buildAndRegister()

                // UU Matter + Free Electron Gas -> Element
                REPLICATOR_RECIPES.recipeBuilder()
                    .notConsumable(material.getFluid(amount))
                    .fluidInputs(uuMatter)
                    .fluidInputs(FreeElectronGas.getFluid(mass))
                    .fluidOutputs(material.getFluid(amount))
                    .EUt(V[LV])
                    .duration(mass * t)
                    .buildAndRegister()

                REPLICATOR_RECIPES.recipeBuilder()
                    .notConsumable(material.getFluid(amount))
                    .fluidInputs(UUMatter.getFluid(mass))
                    .fluidOutputs(material.getFluid(amount))
                    .EUt(V[LV])
                    .duration(mass * t)
                    .buildAndRegister()

            }
            else
            {
                if (material.hasProperty(PropertyKey.DUST))
                {
                    // Element -> UU Matter + Free Electron Gas
                    MASS_FABRICATOR_RECIPES.recipeBuilder()
                        .input(dust, material)
                        .fluidOutputs(uuMatter)
                        .fluidOutputs(FreeElectronGas.getFluid(mass))
                        .EUt(V[LV])
                        .duration(mass * t)
                        .buildAndRegister()

                    // UU Matter + Free Electron Gas -> Element
                    REPLICATOR_RECIPES.recipeBuilder()
                        .notConsumable(dust, material)
                        .fluidInputs(uuMatter)
                        .fluidInputs(FreeElectronGas.getFluid(mass))
                        .output(dust, material)
                        .EUt(V[LV])
                        .duration(mass * t)
                        .buildAndRegister()

                    REPLICATOR_RECIPES.recipeBuilder()
                        .notConsumable(dust, material)
                        .fluidInputs(UUMatter.getFluid(mass))
                        .output(dust, material)
                        .EUt(V[LV])
                        .duration(mass * t)
                        .buildAndRegister()
                }
            }

        }
    }

    // @formatter:on

}