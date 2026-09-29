package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.RecipeMaps.PLASMA_GENERATOR_FUELS
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copernicium
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
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Livermorium
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Tennessine
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tritium
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.Zinc
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.api.t
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OganessonBreedingBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RadiumRadonMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScandiumTitaniumMixture

internal object FusionReactorRecipes
{

    // @formatter:off

    fun init()
    {
        // region MK1 Fusion

        // He (plasma) + Li -> B (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Helium.getPlasma(125))
            fluidInputs(Lithium.getFluid(L))
            fluidOutputs(Boron.getPlasma(L))
            EUt(VA[LuV] / 3L)
            duration(12.s)
            EUToStart(50_000_000L) // 50M EU (MK1)
        }

        addPlasmaFuelRecipe(Boron, 4.s)
        addPlasmaCoolantRecipe(Boron,1.s, true)

        // Mg + O -> Ca (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Magnesium.getFluid(L))
            fluidInputs(Oxygen.getFluid(128))
            fluidOutputs(Calcium.getPlasma(16))
            EUt(VA[IV].toLong())
            duration(6.s + 8.t)
            EUToStart(120_000_000L) // 120M EU (MK1)
        }

        addPlasmaFuelRecipe(Calcium, 3.s + 16.t)
        addPlasmaCoolantRecipe(Calcium, 2.s, true)

        // B (plasma) + Ca (plasma) -> Ne (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Boron.getPlasma(L))
            fluidInputs(Calcium.getPlasma(16))
            fluidOutputs(Neon.getPlasma(1000))
            EUt(VA[LuV] / 3L)
            duration(3.s + 4.t)
            EUToStart(100_000_000L) // 100M EU (MK1)
        }

        addPlasmaFuelRecipe(Neon, 8.s + 10.t)
        addPlasmaCoolantRecipe(Neon, 4.s + 5.t)

        // endregion

        // region MK2 Fusion

        // Al + Li -> S (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Aluminium.getFluid(16))
            fluidInputs(Lithium.getFluid(16))
            fluidOutputs(Sulfur.getPlasma(L))
            EUt(VA[LuV] / 3L)
            duration(1.s + 12.t)
            EUToStart(240_000_000L) // 240M EU (MK2)
        }

        addPlasmaFuelRecipe(Sulfur, 5.s + 12.t)
        addPlasmaCoolantRecipe(Sulfur, 2.s + 18.t, true)

        // Co + Si -> Nb (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Cobalt.getFluid(L))
            fluidInputs(Silicon.getFluid(L))
            fluidOutputs(Niobium.getPlasma(L * 2))
            EUt(49152) // ZPM
            duration(16.t)
            EUToStart(200_000_000L) // 200M EU (MK2)
        }

        addPlasmaFuelRecipe(Niobium, 5.s + 4.t)
        addPlasmaCoolantRecipe(Niobium, 2.s + 16.t, true)

        // Cu + T -> Zn (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Copper.getFluid(L / 2))
            fluidInputs(Tritium.getFluid(250))
            fluidOutputs(Zinc.getPlasma(L))
            EUt(49152) // ZPM
            duration(16.t)
            EUToStart(180_000_000L) // 180M EU (MK2)
        }

        addPlasmaFuelRecipe(Zinc, 4.s + 18.t)
        addPlasmaCoolantRecipe(Zinc, 2.s + 9.t, true)

        // Al + F -> Ti (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Aluminium.getFluid(L))
            fluidInputs(Fluorine.getFluid(L))
            fluidOutputs(Titanium.getPlasma(L))
            EUt(49152) // ZPM
            duration(8.s)
            EUToStart(300_000_000L) // 300M EU (MK2)
        }

        addPlasmaFuelRecipe(Titanium, 7.s + 12.t)
        addPlasmaCoolantRecipe(Titanium, 3.s + 6.t, true)

        // Ne (plasma) + Co -> Rb (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Cobalt.getFluid(L))
            fluidInputs(Neon.getPlasma(100))
            fluidOutputs(Rubidium.getPlasma(L * 2))
            EUt(VHA[LuV].toLong())
            duration(3.s)
            EUToStart(240_000_000L) // 240M EU (MK2)
        }

        addPlasmaFuelRecipe(Rubidium, 12.s + 13.t)
        addPlasmaCoolantRecipe(Rubidium, 6.s + 8.t, true)

        // Nb (plasma) + Zn (plasma) -> Kr (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Niobium.getPlasma(L))
            fluidInputs(Zinc.getPlasma(L))
            fluidOutputs(Krypton.getPlasma(500))
            EUt(V[ZPM])
            duration(1.s + 12.t)
            EUToStart(300_000_000L) // 300M EU (MK2)
        }

        addPlasmaFuelRecipe(Krypton, 7.s + 4.t)
        addPlasmaCoolantRecipe(Krypton, 3.s + 18.t)

        // Pu241 + Ne -> Rf
        FUSION_RECIPES.addRecipe {
            fluidInputs(Plutonium241.getFluid(16))
            fluidInputs(Neon.getFluid(125))
            fluidOutputs(Rutherfordium.getFluid(L))
            EUt(VA[LuV].toLong())
            duration(2.s)
            EUToStart(250_000_000L) // 250M EU (MK2)
        }

        // Au + O -> Fr
        FUSION_RECIPES.addRecipe {
            fluidInputs(Gold.getFluid(L / 4))
            fluidInputs(Oxygen.getFluid(500))
            fluidOutputs(Francium.getFluid(L / 2))
            EUt(VHA[ZPM].toLong())
            duration(4.s + 5.t)
            EUToStart(180_000_000L) // 180M EU (MK2)
        }

        // endregion

        // region MK3 Fusion

        // Advanced recipe of Ca (plasma), original recipes of Ca (plasma) is MK1,
        // and this recipe is MK3 recipe. N (plasma) + Al -> Ca (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Nitrogen.getPlasma(125))
            fluidInputs(Aluminium.getFluid(L))
            fluidOutputs(Calcium.getPlasma(L * 2))
            EUt(VA[ZPM] / 2L)
            duration(16.t)
            EUToStart(360_000_000) // 360M (MK3)
        }

        // Cm + Am (plasma) -> Xe (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Curium.getFluid(L))
            fluidInputs(Americium.getPlasma(L))
            fluidOutputs(Xenon.getPlasma(500))
            EUt(VA[UV])
            duration(16.t)
            EUToStart(500_000_000L) // 500M EU (MK3)
        }

        addPlasmaFuelRecipe(Xenon, 17.s + 8.t)
        addPlasmaCoolantRecipe(Xenon, 10.s)

        // Ir + F -> Rn (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Iridium.getFluid(L))
            fluidInputs(Fluorine.getFluid(500))
            fluidOutputs(Radon.getPlasma(1000))
            EUt(98304) // ZPM
            duration(1.s + 12.t)
            EUToStart(450_000_000L) // 450M EU (MK3)
        }

        addPlasmaFuelRecipe(Radon, 16.s + 4.t)
        addPlasmaCoolantRecipe(Radon, 12.s)

        // Ta + Zn (plasma) -> Bi (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Tantalum.getFluid(L))
            fluidInputs(Zinc.getPlasma(L / 2))
            fluidOutputs(Bismuth.getPlasma(L))
            EUt(98304) // ZPM
            duration(16.t)
            EUToStart(350_000_000L) // 350M EU (MK3)
        }

        addPlasmaFuelRecipe(Bismuth, 9.s + 4.t)
        addPlasmaCoolantRecipe(Bismuth, 5.s + 8.t, true)

        // Au + As -> Ag (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Gold.getFluid(L))
            fluidInputs(Arsenic.getFluid(L))
            fluidOutputs(Silver.getPlasma(L))
            EUt(49152) // ZPM
            duration(16.t)
            EUToStart(350_000_000L) // 360M EU (MK3)
        }

        addPlasmaFuelRecipe(Silver, 8.s + 8.t)
        addPlasmaCoolantRecipe(Silver, 4.s + 4.t, true)

        // Pu241 + He -> Cm
        FUSION_RECIPES.addRecipe {
            fluidInputs(Plutonium241.getFluid(L))
            fluidInputs(Helium.getFluid(1000))
            fluidOutputs(Curium.getFluid(L))
            EUt(98304) // ZPM
            duration(4.s + 16.t)
            EUToStart(500_000_000L) // 500M EU (MK3)
        }

        // Am + Ne -> Db
        FUSION_RECIPES.addRecipe {
            fluidInputs(Americium.getFluid(16))
            fluidInputs(Neon.getFluid(125))
            fluidOutputs(Dubnium.getFluid(L))
            EUt(VA[ZPM])
            duration(4.s)
            EUToStart(380_000_000L) // 380M EU (MK3)
        }

        // Pu239 + Be -> Cf
        FUSION_RECIPES.addRecipe {
            fluidInputs(Plutonium239.getFluid(48))
            fluidInputs(Beryllium.getFluid(48))
            fluidOutputs(Californium.getFluid(48))
            EUt(49152) // ZPM
            duration(12.s)
            EUToStart(480_000_000) // 480M EU (MK3)
        }

        // Po + Cr -> Mt
        FUSION_RECIPES.addRecipe {
            fluidInputs(Polonium.getFluid(L * 2))
            fluidInputs(Chrome.getFluid(L * 2))
            fluidOutputs(Meitnerium.getFluid(L * 4))
            EUt(VA[UV] / 3)
            duration(4.s + 8.t)
            EUToStart(400_000_000L) // 400M EU (MK3)
        }

        // endregion

        // region MK4 Fusion

        // Rn (plasma) + N (plasma) -> Np (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Radon.getPlasma(100))
            fluidInputs(Nitrogen.getPlasma(100))
            fluidOutputs(Neptunium.getPlasma(L))
            EUt(VA[UHV] / 2)
            duration(2.s + 18.t)
            EUToStart(940_000_000L) // 940M EU (MK4)
        }

        addPlasmaFuelRecipe(Neptunium, 34.s)
        addPlasmaCoolantRecipe(Neptunium, 17.s, true)

        // Am (plasma) + B (plasma) -> Fm (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Americium.getPlasma(L / 2))
            fluidInputs(Boron.getPlasma(L / 2))
            fluidOutputs(Fermium.getPlasma(L))
            EUt(VA[UHV] / 2)
            duration(2.s + 18.t)
            EUToStart(960_000_000L) // 960M EU (MK4)
        }

        addPlasmaFuelRecipe(Fermium, 38.s + 14.t)
        addPlasmaCoolantRecipe(Fermium, 19.s, true)

        // Se + F -> Tc (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Selenium.getFluid(L))
            fluidInputs(Fluorine.getFluid(250))
            fluidOutputs(Technetium.getPlasma(L * 2))
            EUt(VA[UHV])
            duration(3.s + 4.t)
            EUToStart(650_000_000L) // 650M EU (MK4)
        }

        addPlasmaFuelRecipe(Technetium, 38.s + 5.t)
        addPlasmaCoolantRecipe(Technetium, 19.s, true)

        // Ra + V -> Rg
        FUSION_RECIPES.addRecipe {
            fluidInputs(Radium.getFluid(L * 2))
            fluidInputs(Vanadium.getFluid(L * 2))
            fluidOutputs(Roentgenium.getFluid(L * 4))
            EUt(VA[UHV] / 2)
            duration(5.s + 12.t)
            EUToStart(460_000_000L) // 460M EU (MK4)
        }

        // Pu239 + Ca -> Sg
        FUSION_RECIPES.addRecipe {
            fluidInputs(Plutonium239.getFluid(L))
            fluidInputs(Calcium.getFluid(L))
            fluidOutputs(Seaborgium.getFluid(L * 2))
            EUt(VA[UHV])
            duration(2.s + 5.t)
            EUToStart(800_000_000L) // 800M EU (MK4)
        }

        // Cm + Na -> Bh
        FUSION_RECIPES.addRecipe {
            fluidInputs(Curium.getFluid(L / 2))
            fluidInputs(Sodium.getFluid(L * 2))
            fluidOutputs(Bohrium.getFluid(L * 2))
            EUt(VA[UHV])
            duration(5.s)
            EUToStart(850_000_000L) // 850M EU (MK4)
        }

        // ScTi + RaRn -> Hs
        FUSION_RECIPES.addRecipe {
            fluidInputs(ScandiumTitaniumMixture.getFluid(L))
            fluidInputs(RadiumRadonMixture.getFluid(L * 2))
            fluidOutputs(MetastableHassium.getPlasma(L * 4))
            EUt(VA[UHV])
            duration(5.s)
            EUToStart(1_200_000_000L) // 1,200M EU (MK4)
        }

        // Zn + Ni -> Cn
        FUSION_RECIPES.addRecipe {
            fluidInputs(Zinc.getFluid(L * 4))
            fluidInputs(Nickel.getFluid(L * 4))
            fluidOutputs(Copernicium.getFluid(L * 2))
            EUt(VA[UHV] / 2)
            duration(5.s + 6.t)
            EUToStart(850_000_000L) // 850M EU (MK4)
        }

        // Cf + Cm -> Og
        FUSION_RECIPES.addRecipe {
            fluidInputs(OganessonBreedingBase.getFluid(L))
            fluidInputs(Curium.getFluid(36))
            fluidOutputs(MetastableOganesson.getPlasma(L))
            EUt(VA[UHV])
            duration(5.s)
            EUToStart(1_100_000_000L) // 1,100M EU (MK4)
        }

        // endregion

        // region MK5 Fusion

        // Os + Si -> Th (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Osmium.getFluid(L))
            fluidInputs(Silicon.getFluid(L))
            fluidOutputs(Thorium.getPlasma(L))
            EUt(VH[UEV] / 2)
            duration(13.s + 10.t)
            EUToStart(1_800_000_000L) // 1,800M EU (MK5)
        }

        addPlasmaFuelRecipe(Thorium, 52.s + 4.t)
        addPlasmaCoolantRecipe(Thorium, 26.s + 2.t, true)

        // Te + Zn -> Pb (plasma)
        FUSION_RECIPES.addRecipe {
            fluidInputs(Tellurium.getFluid(L))
            fluidInputs(Zinc.getFluid(L))
            fluidOutputs(Lead.getPlasma(L))
            EUt(VA[UEV])
            duration(12.s)
            EUToStart(1_800_000_000L) // 1,800M EU (MK5)
        }

        addPlasmaFuelRecipe(Lead, 46.s + 8.t)
        addPlasmaCoolantRecipe(Lead, 18.s + 4.t, true)

        // At + Ni -> Nh
        FUSION_RECIPES.addRecipe {
            fluidInputs(Astatine.getFluid(L))
            fluidInputs(Nickel.getFluid(L * 2))
            fluidOutputs(Nihonium.getFluid(L / 2))
            EUt(VA[UEV] / 3)
            duration(2.s + 14.t)
            EUToStart(1_300_000_000L) // 1,300M EU (MK5)
        }

        // Np + Ti -> Mc
        FUSION_RECIPES.addRecipe {
            fluidInputs(Nickel.getFluid(L))
            fluidInputs(Titanium.getFluid(L))
            fluidOutputs(Moscovium.getFluid(L / 4))
            EUt(VA[UEV])
            duration(4.s + 8.t)
            EUToStart(1_600_000_000L) // 1,600M EU (MK5)
        }

        // Pa + Mn -> Lv
        FUSION_RECIPES.addRecipe {
            fluidInputs(Protactinium.getFluid(L))
            fluidInputs(Manganese.getFluid(L))
            fluidOutputs(Livermorium.getFluid(L))
            EUt(VA[UEV] / 2)
            duration(9.s + 6.t)
            EUToStart(1_800_000_000L) // 1,800M EU (MK5)
        }

        // Bk + Ca -> Ts
        FUSION_RECIPES.addRecipe {
            fluidInputs(Berkelium.getFluid(L))
            fluidInputs(Calcium.getFluid(L * 4))
            fluidOutputs(Tennessine.getFluid(L))
            EUt(VA[UEV])
            duration(5.s + 15.t)
            EUToStart(2_000_000_000L) // 2,000M EU (MK5)
        }

        // endregion

        // Add plasma coolant recipes to vacuum freezer for original plasmas.
        addPlasmaCoolantRecipe(Americium, 8.s, true)
        addPlasmaCoolantRecipe(Argon, 2.s + 8.t, false)
        addPlasmaCoolantRecipe(Helium, 1.s, false)
        addPlasmaCoolantRecipe(Iron, 2.s + 16.t, true)
        addPlasmaCoolantRecipe(Nickel, 4.s + 16.t, true)
        addPlasmaCoolantRecipe(Nitrogen, 1.s + 16.t, false)
        addPlasmaCoolantRecipe(Oxygen, 1.s + 4.t, false)
        addPlasmaCoolantRecipe(Tin, 3.s + 4.t, true)

    }

    private fun addPlasmaFuelRecipe(material: Material, duration: Int)
    {
        PLASMA_GENERATOR_FUELS.addRecipe {
            fluidInputs(material.getPlasma(1))
            fluidOutputs(material.getFluid(1))
            EUt(V[EV])
            duration(duration)
        }
    }

    private fun addPlasmaCoolantRecipe(material: Material, duration: Int)
    {
        VACUUM_RECIPES.addRecipe {
            fluidInputs(material.getPlasma(1000))
            fluidOutputs(material.getFluid(1000))
            EUt(VA[MV])
            duration(duration)
        }
    }

    private fun addPlasmaCoolantRecipe(material: Material, duration: Int, isMetallic: Boolean)
    {
        if (isMetallic)
        {
            VACUUM_RECIPES.addRecipe {
                fluidInputs(material.getPlasma(L))
                fluidOutputs(material.getFluid(L))
                EUt(VA[MV])
                duration(duration)
            }
        }
        else
        {
            addPlasmaCoolantRecipe(material, duration)
        }
    }

    // @formatter:on

}