package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.RecipeMaps.PLASMA_GENERATOR_FUELS
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Tritium
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Zinc
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class FusionReactorRecipes
{

    companion object
    {

        fun init()
        {
            // ---------------------------------------------------------------------------------------------------------
            // MK2 Fusion Reactions

            // Co + Si -> Nb (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Cobalt.getFluid(L))
                .fluidInputs(Silicon.getFluid(L))
                .fluidOutputs(Niobium.getPlasma(L * 2))
                .EUt(49152) // ZPM
                .duration(16 * TICK)
                .EUToStart(200_000_000L) // 200M EU, MK2
                .buildAndRegister()

            addPlasmaFuelRecipe(Niobium, 5 * SECOND + 4 * TICK);
            addPlasmaCoolantRecipe(Niobium, 2 * SECOND + 16 * TICK, true)

            // Cu + T -> Zn (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Copper.getFluid(L / 2))
                .fluidInputs(Tritium.getFluid(250))
                .fluidOutputs(Zinc.getPlasma(L))
                .EUt(49152) // ZPM
                .duration(16 * TICK)
                .EUToStart(180_000_000L) // 180M EU, MK2
                .buildAndRegister()

            addPlasmaFuelRecipe(Zinc, 4 * SECOND + 18 * TICK);
            addPlasmaCoolantRecipe(Zinc, 2 * SECOND + 9 * TICK, true)

            // Nb (plasma) + Zn (plasma) -> Kr (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Niobium.getPlasma(L))
                .fluidInputs(Zinc.getPlasma(L))
                .fluidOutputs(Krypton.getPlasma(500))
                .EUt(V[ZPM])
                .duration(1 * SECOND + 12 * TICK)
                .EUToStart(300_000_000L) // 300M EU, MK2
                .buildAndRegister()

            addPlasmaFuelRecipe(Krypton, 7 * SECOND + 4 * TICK)
            addPlasmaCoolantRecipe(Krypton, 3 * SECOND + 18 * TICK)

            // Pu241 + Ne -> Rf
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Plutonium241.getFluid(16))
                .fluidInputs(Neon.getFluid(125))
                .fluidOutputs(Rutherfordium.getFluid(L))
                .EUt(VA[LuV].toLong())
                .duration(2 * SECOND)
                .EUToStart(250_000_000L) // 250M EU, MK2
                .buildAndRegister()

            // ---------------------------------------------------------------------------------------------------------
            // MK3 Fusion Reactions

            // Pu241 + He -> Cm
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Plutonium241.getFluid(L))
                .fluidInputs(Helium.getFluid(1000))
                .fluidOutputs(Curium.getFluid(L))
                .EUt(98304) // ZPM
                .duration(4 * SECOND + 16 * TICK)
                .EUToStart(500_000_000L) // 500M EU, MK3
                .buildAndRegister()

            // Cm + Am (plasma) -> Xe (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Curium.getFluid(L))
                .fluidInputs(Americium.getPlasma(L))
                .fluidOutputs(Xenon.getPlasma(500))
                .EUt(VA[UV].toLong())
                .duration(16 * TICK)
                .EUToStart(500_000_000L) // 500M EU, MK3
                .buildAndRegister()

            addPlasmaFuelRecipe(Xenon, 17 * SECOND + 8 * TICK)
            addPlasmaCoolantRecipe(Xenon, 10 * SECOND)

            // Ir + F -> Rn (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Iridium.getFluid(L))
                .fluidInputs(Fluorine.getFluid(500))
                .fluidOutputs(Radon.getPlasma(1000))
                .EUt(98304) // ZPM
                .duration(1 * SECOND + 12 * TICK)
                .EUToStart(450_000_000L) // 450M EU, MK3
                .buildAndRegister()

            addPlasmaFuelRecipe(Radon, 16 * SECOND + 4 * TICK)
            addPlasmaCoolantRecipe(Radon, 12 * SECOND)

            // Am + Ne -> Db
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Americium.getFluid(16))
                .fluidInputs(Neon.getFluid(125))
                .fluidOutputs(Dubnium.getFluid(L))
                .EUt(VA[ZPM].toLong())
                .duration(4 * SECOND)
                .EUToStart(380_000_000L) // 380M EU, MK3
                .buildAndRegister()

            // Add plasma coolant recipes to vacuum freezer for original plasmas.
            addPlasmaCoolantRecipe(Americium, 8 * SECOND, true)
            addPlasmaCoolantRecipe(Argon, 2 * SECOND + 8 * TICK, false)
            addPlasmaCoolantRecipe(Helium, 1 * SECOND, false)
            addPlasmaCoolantRecipe(Iron, 2 * SECOND + 16 * TICK, true)
            addPlasmaCoolantRecipe(Nickel, 4 * SECOND + 16 * TICK, true)
            addPlasmaCoolantRecipe(Nitrogen, 1 * SECOND + 16 * TICK, false)
            addPlasmaCoolantRecipe(Oxygen, 1 * SECOND + 4 * TICK, false)
            addPlasmaCoolantRecipe(Tin, 3 * SECOND + 4 * TICK, true)

        }

        private fun addPlasmaFuelRecipe(material: Material, duration: Int)
        {
            PLASMA_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(material.getPlasma(1))
                .fluidInputs(material.getFluid(1))
                .EUt(V[EV])
                .duration(duration)
                .buildAndRegister()
        }

        private fun addPlasmaCoolantRecipe(material: Material, duration: Int)
        {
            VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(material.getPlasma(1000))
                .fluidOutputs(material.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(duration)
                .buildAndRegister()
        }

        private fun addPlasmaCoolantRecipe(material: Material, duration: Int,
                                           isMetallic: Boolean)
        {
            if (isMetallic)
            {
                VACUUM_RECIPES.recipeBuilder()
                    .fluidInputs(material.getPlasma(L))
                    .fluidOutputs(material.getFluid(L))
                    .EUt(VA[MV].toLong())
                    .duration(duration)
                    .buildAndRegister()
            }
            else
            {
                addPlasmaCoolantRecipe(material, duration)
            }
        }

    }

}