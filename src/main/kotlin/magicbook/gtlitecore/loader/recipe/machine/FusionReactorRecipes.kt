package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.RecipeMaps.PLASMA_GENERATOR_FUELS
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Tritium
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Zinc
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OganessonBreedingBase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RadiumRadonMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ScandiumTitaniumMixture
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class FusionReactorRecipes
{

    companion object
    {

        fun init()
        {
            // MK1 Fusion Reactions

            // He (plasma) + Li -> B (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Helium.getPlasma(125))
                .fluidInputs(Lithium.getFluid(L))
                .fluidOutputs(Boron.getPlasma(L))
                .EUt(VA[LuV] / 3L)
                .duration(12 * SECOND)
                .EUToStart(50_000_000L) // 50M EU, MK1
                .buildAndRegister()

            addPlasmaFuelRecipe(Boron, 4 * SECOND)
            addPlasmaCoolantRecipe(Boron,1  * SECOND, true)

            // Mg + O -> Ca (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Magnesium.getFluid(L))
                .fluidInputs(Oxygen.getFluid(128))
                .fluidOutputs(Calcium.getPlasma(16))
                .EUt(VA[IV].toLong())
                .duration(6 * SECOND + 8 * TICK)
                .EUToStart(120_000_000L) // 120M EU, MK1
                .buildAndRegister()

            addPlasmaFuelRecipe(Calcium, 3 * SECOND + 16 * TICK)
            addPlasmaCoolantRecipe(Calcium, 2 * SECOND, true)

            // B (plasma) + Ca (plasma) -> Ne (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Boron.getPlasma(L))
                .fluidInputs(Calcium.getPlasma(16))
                .fluidOutputs(Neon.getPlasma(1000))
                .EUt(VA[LuV] / 3L)
                .duration(3 * SECOND + 4 * TICK)
                .EUToStart(100_000_000L) // 100M EU, MK1
                .buildAndRegister()

            addPlasmaFuelRecipe(Neon, 8 * SECOND + 10 * TICK)
            addPlasmaCoolantRecipe(Neon, 4 * SECOND + 5 * TICK)

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

            // Ne (plasma) + Co -> Rb (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Cobalt.getFluid(L))
                .fluidInputs(Neon.getPlasma(100))
                .fluidOutputs(Rubidium.getPlasma(L * 2))
                .EUt(VHA[LuV].toLong())
                .duration(3 * SECOND)
                .EUToStart(240_000_000L) // 240M EU, MK2
                .buildAndRegister()

            addPlasmaFuelRecipe(Rubidium, 12 * SECOND + 13 * TICK)
            addPlasmaCoolantRecipe(Rubidium, 6 * SECOND + 8 * TICK, true)

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

            // Au + O -> Fr
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Gold.getFluid(L / 4))
                .fluidInputs(Oxygen.getFluid(500))
                .fluidOutputs(Francium.getFluid(L / 2))
                .EUt(VHA[ZPM].toLong())
                .duration(4 * SECOND + 5 * TICK)
                .EUToStart(180_000_000L) // 180M EU, MK2
                .buildAndRegister()

            // ---------------------------------------------------------------------------------------------------------
            // MK3 Fusion Reactions

            // Advanced recipe of Ca (plasma), original recipes of Ca (plasma) is MK1,
            // and this recipe is MK3 recipe. N (plasma) + Al -> Ca (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Nitrogen.getPlasma(125))
                .fluidInputs(Aluminium.getFluid(L))
                .fluidOutputs(Calcium.getPlasma(L * 2))
                .EUt(VA[ZPM] / 2L)
                .duration(16 * TICK)
                .EUToStart(360_000_000) // 360M, MK3
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

            // Ta + Zn (plasma) -> Bi (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Tantalum.getFluid(L))
                .fluidInputs(Zinc.getPlasma(L / 2))
                .fluidOutputs(Bismuth.getPlasma(L))
                .EUt(98304) // ZPM
                .duration(16 * TICK)
                .EUToStart(350_000_000L) // MK3
                .buildAndRegister()

            addPlasmaFuelRecipe(Bismuth, 9 * SECOND + 4 * TICK)
            addPlasmaCoolantRecipe(Bismuth, 5 * SECOND + 8 * TICK, true)

            // Pu241 + He -> Cm
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Plutonium241.getFluid(L))
                .fluidInputs(Helium.getFluid(1000))
                .fluidOutputs(Curium.getFluid(L))
                .EUt(98304) // ZPM
                .duration(4 * SECOND + 16 * TICK)
                .EUToStart(500_000_000L) // 500M EU, MK3
                .buildAndRegister()

            // Am + Ne -> Db
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Americium.getFluid(16))
                .fluidInputs(Neon.getFluid(125))
                .fluidOutputs(Dubnium.getFluid(L))
                .EUt(VA[ZPM].toLong())
                .duration(4 * SECOND)
                .EUToStart(380_000_000L) // 380M EU, MK3
                .buildAndRegister()

            // Pu239 + Be -> Cf
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Plutonium239.getFluid(48))
                .fluidInputs(Beryllium.getFluid(48))
                .fluidOutputs(Californium.getFluid(48))
                .EUt(49152) // ZPM
                .duration(12 * SECOND)
                .EUToStart(480_000_000) // 480M EU, MK3
                .buildAndRegister()

            // ---------------------------------------------------------------------------------------------------------
            // Mark 4 Fusion Reactions

            // Rn (plasma) + N (plasma) -> Np (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Radon.getPlasma(100))
                .fluidInputs(Nitrogen.getPlasma(100))
                .fluidOutputs(Neptunium.getPlasma(L))
                .EUt(VA[UHV] / 2L)
                .duration(2 * SECOND + 18 * TICK)
                .EUToStart(940_000_000L) // 940M EU, MK4
                .buildAndRegister()

            addPlasmaFuelRecipe(Neptunium, 34 * SECOND)
            addPlasmaCoolantRecipe(Neptunium, 17 * SECOND, true)

            // Am (plasma) + B (plasma) -> Fm (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Americium.getPlasma(L / 2))
                .fluidInputs(Boron.getPlasma(L / 2))
                .fluidOutputs(Fermium.getPlasma(L))
                .EUt(VA[UHV] / 2L)
                .duration(2 * SECOND + 18 * TICK)
                .EUToStart(960_000_000L) // 960M EU, MK4
                .buildAndRegister()

            addPlasmaCoolantRecipe(Fermium, 38 * SECOND + 14 * TICK)
            addPlasmaCoolantRecipe(Fermium, 19 * SECOND, true)

            // Se + F -> Tc (plasma)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Selenium.getFluid(L))
                .fluidInputs(Fluorine.getFluid(250))
                .fluidOutputs(Technetium.getPlasma(L * 2))
                .EUt(VA[UHV].toLong())
                .duration(3 * SECOND + 4 * TICK)
                .EUToStart(650_000_000L) // 650M EU, MK4
                .buildAndRegister()

            addPlasmaFuelRecipe(Technetium, 38 * SECOND + 5 * TICK)
            addPlasmaCoolantRecipe(Technetium, 19 * SECOND, true)

            // Pu239 + Ca -> Sg
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Plutonium239.getFluid(L))
                .fluidInputs(Calcium.getFluid(L))
                .fluidOutputs(Seaborgium.getFluid(L * 2))
                .EUt(VA[UHV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .EUToStart(800_000_000L) // 800M EU, MK4
                .buildAndRegister()

            // Cm + Na -> Bh
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Curium.getFluid(L / 2))
                .fluidInputs(Sodium.getFluid(L * 2))
                .fluidOutputs(Bohrium.getFluid(L * 2))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .EUToStart(850_000_000L) // 850M EU, MK4
                .buildAndRegister()

            // Cf + Cm -> Og
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(OganessonBreedingBase.getFluid(L))
                .fluidInputs(Curium.getFluid(36))
                .fluidOutputs(MetastableOganesson.getPlasma(L))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .EUToStart(1_100_000_000L) // 1100M EU, MK4
                .buildAndRegister()

            // ScTi + RaRn -> Hs
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(ScandiumTitaniumMixture.getFluid(L))
                .fluidInputs(RadiumRadonMixture.getFluid(L * 2))
                .fluidOutputs(MetastableHassium.getPlasma(L * 4))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .EUToStart(1_200_000_000L) // 1200M EU, MK4
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
                .fluidOutputs(material.getFluid(1))
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