package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Alunite
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.BasalticMineralSand
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Bentonite
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.BioDiesel
import gregtech.api.unification.material.Materials.Biotite
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.BlueTopaz
import gregtech.api.unification.material.Materials.Bornite
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.BrownLimonite
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Cassiterite
import gregtech.api.unification.material.Materials.CassiteriteSand
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.CetaneBoostedDiesel
import gregtech.api.unification.material.Materials.Chalcocite
import gregtech.api.unification.material.Materials.Chalcopyrite
import gregtech.api.unification.material.Materials.Chromite
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.CobaltBrass
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.Cooperite
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Diatomite
import gregtech.api.unification.material.Materials.Diesel
import gregtech.api.unification.material.Materials.Electrotine
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.Ferrosilite
import gregtech.api.unification.material.Materials.Galena
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Gasoline
import gregtech.api.unification.material.Materials.GlauconiteSand
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.GraniticMineralSand
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.HeavyFuel
import gregtech.api.unification.material.Materials.HighOctaneGasoline
import gregtech.api.unification.material.Materials.Ilmenite
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kyanite
import gregtech.api.unification.material.Materials.Lapis
import gregtech.api.unification.material.Materials.Lazurite
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lepidolite
import gregtech.api.unification.material.Materials.LightFuel
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Magnetite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Pentlandite
import gregtech.api.unification.material.Materials.Pitchblende
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Pollucite
import gregtech.api.unification.material.Materials.Powellite
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Pyrochlore
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.RocketFuel
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.Scheelite
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Soapstone
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.Spodumene
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.Tantalite
import gregtech.api.unification.material.Materials.Tetrahedrite
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.Trona
import gregtech.api.unification.material.Materials.Tungstate
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.Ultimet
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.VanadiumMagnetite
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.Wulfenite
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.material.Materials.Zeolite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.ore.OrePrefix.ore
import gregtech.api.unification.ore.OrePrefix.oreNetherrack
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MINING_DRONE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aegirine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Anorthite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Augite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Baddeleyite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Clinochlore
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cryolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DenseHydrazineRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dolomite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jade
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jasper
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kaolinite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lignite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lizardite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylhydrazineNitrateRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Nephelite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Orpiment
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phlogopite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Picotite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RP1RocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tanzanite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Wollastonite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_EV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_HV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_IV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_LV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_MV

@Suppress("MISSING_DEPENDENCY_CLASS")
class MiningDroneAsteroidRecipeProducer
{

    companion object
    {
        // LV-HV
        private var fuelBasic = arrayOf(
            LightFuel.getFluid(16000), HeavyFuel.getFluid(14000),
            Diesel.getFluid(12000), BioDiesel.getFluid(12000),
            Gasoline.getFluid(10000),
            CetaneBoostedDiesel.getFluid(8000),
            HighOctaneGasoline.getFluid(6000), RocketFuel.getFluid(6000), RP1RocketFuel.getFluid(6000),
            DenseHydrazineRocketFuel.getFluid(2000), MethylhydrazineNitrateRocketFuel.getFluid(2000))
        // MV-EV
        private var fuelAdvanced = arrayOf( // +8000
            // LightFuel.getFluid(24000), HeavyFuel.getFluid(22000),
            Diesel.getFluid(20000), BioDiesel.getFluid(20000),
            Gasoline.getFluid(18000),
            CetaneBoostedDiesel.getFluid(16000),
            HighOctaneGasoline.getFluid(14000), RocketFuel.getFluid(14000), RP1RocketFuel.getFluid(14000),
            DenseHydrazineRocketFuel.getFluid(10000), MethylhydrazineNitrateRocketFuel.getFluid(10000))
        // HV-IV
        private var fuelElite = arrayOf( // +8000
            // LightFuel.getFluid(32000), HeavyFuel.getFluid(30000),
            // Diesel.getFluid(28000), BioDiesel.getFluid(28000),
            Gasoline.getFluid(26000),
            CetaneBoostedDiesel.getFluid(24000),
            HighOctaneGasoline.getFluid(22000), RocketFuel.getFluid(22000), RP1RocketFuel.getFluid(22000),
            DenseHydrazineRocketFuel.getFluid(18000), MethylhydrazineNitrateRocketFuel.getFluid(18000))

        // EV-LuV
        private var fuelUltimate = arrayOf( // +8000
            // LightFuel.getFluid(40000), HeavyFuel.getFluid(38000),
            // Diesel.getFluid(36000), BioDiesel.getFluid(36000),
            // Gasoline.getFluid(34000),
            CetaneBoostedDiesel.getFluid(32000),
            HighOctaneGasoline.getFluid(30000), RocketFuel.getFluid(30000), RP1RocketFuel.getFluid(30000),
            DenseHydrazineRocketFuel.getFluid(26000), MethylhydrazineNitrateRocketFuel.getFluid(26000))

        // IV-ZPM
        private var fuelEpic = arrayOf( // +8000
            // LightFuel.getFluid(48000), HeavyFuel.getFluid(46000),
            // Diesel.getFluid(44000), BioDiesel.getFluid(44000),
            // Gasoline.getFluid(42000),
            // CetaneBoostedDiesel.getFluid(40000),
            HighOctaneGasoline.getFluid(38000), RocketFuel.getFluid(38000), RP1RocketFuel.getFluid(38000),
            DenseHydrazineRocketFuel.getFluid(32000), MethylhydrazineNitrateRocketFuel.getFluid(32000))

        // LuV-UV
        private var fuelLegendary = arrayOf( // +8000
            // LightFuel.getFluid(56000), HeavyFuel.getFluid(54000),
            // Diesel.getFluid(52000), BioDiesel.getFluid(52000),
            // Gasoline.getFluid(50000),
            // CetaneBoostedDiesel.getFluid(48000),
            // HighOctaneGasoline.getFluid(46000), RocketFuel.getFluid(46000), RP1RocketFuel.getFluid(46000),
            DenseHydrazineRocketFuel.getFluid(40000), MethylhydrazineNitrateRocketFuel.getFluid(40000))

        fun produce()
        {
            // Basic Asteroids (5) LV-HV

            // Carbon Group Asteroid (Coal-Lignite-Graphite-Diamond)
            addAsteroid(1, 0, Coal, Lignite, Graphite, Diamond)
            // Iron Asteroid (Iron-BandedIron-BrownLimonite-YellowLimonite-Pyrite-Magnetite-VanadiumMagnetite-Gold-BasalticMineralSand-GraniticMineralSand)
            addAsteroid(1, 1, Iron, BandedIron, BrownLimonite, YellowLimonite, Pyrite, Magnetite, VanadiumMagnetite, Gold, BasalticMineralSand, GraniticMineralSand)
            // Copper Asteroid (Copper-Chalcopyrite-Malachite-Cuprite-Azurite-Tenorite-Bornite-Tetrahedrite)
            addAsteroid(1, 2, Copper, Chalcopyrite, Chalcocite, Malachite, Cuprite, Azurite, Tenorite, Bornite, Tetrahedrite)
            // Tin-Lead Asteroid (Cassiterite-CassiteriteSand-Tin-Lead-Galena-Silver-Antimony-Zeolite-Realgar)
            addAsteroid(1, 3, Cassiterite, CassiteriteSand, Tin, Lead, Galena, Silver, Antimony, Zeolite, Realgar)
            // Salt Asteroid (Salt-RockSalt-Saltpeter-Lepidolite-Spodumene)
            addAsteroid(1, 4, Salt, RockSalt, Saltpeter, Lepidolite, Spodumene)

            // Advanced Asteroids (8) MV-EV

            // Mica Asteroid (Mica-Kyanite-Bauxite-Pollucite-Muscovite-Phlogopite-Biotite-Lepidolite)
            addAsteroid(2, 5, Mica, Kyanite, Bauxite, Pollucite, Muscovite, Phlogopite, Biotite, Lepidolite)
            // Kaolinite-Dolomite Asteroid (Kaolinite-Bentonite-Augite-Ferrosilite-Spodumene-Dolomite-Wollastonite-Trona)
            addAsteroid(2, 6, Kaolinite, Bentonite, Augite, Ferrosilite, Spodumene, Dolomite, Wollastonite, Trona)
            // Nickel-Cobalt Asteroid (Nickel-Garnierite-Cobalt-Cobaltite-Pyrite-Pentlandite-Soapstone-Talc-GlauconiteSand)
            addAsteroid(2, 7, Nickel, Garnierite, Cobalt, Cobaltite, Pyrite, Pentlandite, Soapstone, Talc, GlauconiteSand)
            // Manganese-Tantalum Asteroid (Pyrolusite-Tantalite-Pyrochlore-Grossular-Spessartine-Forsterite)
            addAsteroid(2, 8, Pyrolusite, Tantalite, Pyrochlore, Grossular, Spessartine, Forsterite)
            // Redstone-Garnet Asteroid (Redstone-Ruby-Realgar-Orpiment-GarnetRed-GarnetYellow-Amethyst-Opal)
            addAsteroid(2, 9, Redstone, Ruby, Realgar, Orpiment, GarnetRed, GarnetYellow, Amethyst, Opal)
            // Various Gems Asteroid (Diamond-Ruby-Emerald-Olivine-Sapphire-GreenSapphire-Lapis-Lazurite-Sodalite-CertusQuartz-Topaz-BlueTopaz-Amethyst-Opal)
            addAsteroid(2, 10, Diamond, Ruby, Emerald, Olivine, Sapphire, GreenSapphire, Lapis, Lazurite, Sodalite, CertusQuartz, Topaz, BlueTopaz, Amethyst, Opal)
            // Various Gems Asteroid (Almandine-Andradite-Grossular-Pyrope-Spessartine-Uvarovite-Tanzanite-Quartzite-Realgar-Orpiment-Apatite-GarnetRed-GarnetYellow-Zircon)
            addAsteroid(2, 11, Almandine, Andradite, Grossular, Pyrope, Spessartine, Uvarovite, Tanzanite, Quartzite, Realgar, Orpiment, Apatite, GarnetRed, GarnetYellow, Zircon)
            // Aluminium-Chrome Asteroid (Aluminium-Bauxite-Ilmenite-Cryolite-Chromite-Picotite-Jade-Jasper-Anorthite)
            addAsteroid(2, 12, Aluminium, Bauxite, Ilmenite, Cryolite, Chromite, Picotite, Jade, Jasper, Anorthite)

            // Elite Asteroids (4) HV-IV

            // Platinum Group Asteroid (Platinum-Palladium-Cooperite-Chalcopyrite-Chalcocite-Bornite-Tetrahedrite-Pentlandite-Azurite-Tenorite-Cuprite)
            addAsteroid(3, 13, Platinum, Palladium, Cooperite, Chalcopyrite, Chalcocite, Bornite, Tetrahedrite, Pentlandite, Azurite, Tenorite, Cuprite)
            // Molybdenum-Tungsten Asteroid (Molybdenum-Molybdenite-Powellite-Wulfenite-Scheelite-Tungstate-Lithium)
            addAsteroid(3, 14, Molybdenum, Molybdenite, Powellite, Wulfenite, Scheelite, Tungstate, Lithium)
            // Zircon Asteroid (Zircon-Baddeleyite-Nephelite-Aegirine-Lizardite-Calcite-Uvarovite-Clinochlore-Magnesite)
            addAsteroid(3, 15, Zircon, Baddeleyite, Nephelite, Aegirine, Lizardite, Calcite, Uvarovite, Clinochlore, Magnesite)
            // Radioactive Asteroid (Naquadah-Pitchblende-Uraninite-Thorium-Plutonium-239-Beryllium-Saltpeter-Diatomite-Electrotine-Alunite)
            addAsteroid(3, 16, Naquadah, Pitchblende, Uraninite, Thorium, Plutonium239, Beryllium, Saltpeter, Diatomite, Electrotine, Alunite)

            // Ultimate Asteroids (.) EV-LuV

            // Rare Earth Asteroid


            // Epic Asteroid (.) IV-ZPM

            // Legendary Asteroid (.) LuV-UV

        }

        /**
         * @param tier        The tiered token of asteroids, 1 for basic, 2 for advanced, e.t.c.
         * @param circuitMeta Used to declared asteroid types, different asteroids used different value.
         * @param outputs     Ore outputs of this asteroid.
         */
        private fun addAsteroid(tier: Int, circuitMeta: Int,
                                vararg outputs: Material)
        {
            if (tier == 1) // Basic
            {
                for (fuel in fuelBasic)
                {
                    // Rank 1 Asteroid Mining
                    val builder1 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_LV)
                        .notConsumable(toolHeadDrill, Iron)
                        .notConsumable(stick, Iron)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1.chancedOutput(ore, output, 3000, 0)

                    builder1.EUt(VA[LV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // Rank 1 Asteroid Mining 4x
                    val builder1x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_LV.getStackForm(4))
                        .notConsumable(toolHeadDrill, Bronze)
                        .notConsumable(stick, Bronze)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1x4.chancedOutput(ore, output, 4, 3000, 0)

                    builder1x4.EUt(VA[LV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // Rank 1 Asteroid Mining 16x
                    val builder1x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_LV.getStackForm(16))
                        .notConsumable(toolHeadDrill, Invar)
                        .notConsumable(stick, Invar)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1x16.chancedOutput(ore, output, 16, 3000, 0)

                    builder1x16.EUt(VA[LV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // -------------------------------------------------------------------------------------------------
                    // Rank 2 Asteroid Mining
                    val builder2 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_MV)
                        .notConsumable(toolHeadDrill, WroughtIron)
                        .notConsumable(stick, WroughtIron)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2.chancedOutput(ore, output, 4, 6000, 0)

                    builder2.EUt(VA[MV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // Rank 2 Asteroid Mining 4x
                    val builder2x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_MV.getStackForm(4))
                        .notConsumable(toolHeadDrill, Steel)
                        .notConsumable(stick, Steel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2x4.chancedOutput(ore, output, 16, 6000, 0)

                    builder2x4.EUt(VA[MV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // Rank 2 Asteroid Mining 16x
                    val builder2x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV.getStackForm(16))
                        .notConsumable(toolHeadDrill, Diamond)
                        .notConsumable(stick, Diamond)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2x16.chancedOutput(ore, output, 64, 6000, 0)

                    builder2x16.EUt(VA[MV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // -------------------------------------------------------------------------------------------------
                    // Rank 3 Asteroid Mining
                    val builder3 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV)
                        .notConsumable(toolHeadDrill, Aluminium)
                        .notConsumable(stick, Aluminium)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3.chancedOutput(ore, output, 16, 9000, 0)

                    builder3.EUt(VA[HV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()

                    // Rank 3 Asteroid Mining 4x
                    val builder3x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV.getStackForm(4))
                        .notConsumable(toolHeadDrill, CobaltBrass)
                        .notConsumable(stick, CobaltBrass)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3x4.chancedOutput(ore, output, 64, 9000, 0)

                    builder3x4.EUt(VA[HV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()

                    // Rank 3 Asteroid Mining 16x
                    val builder3x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV.getStackForm(16))
                        .notConsumable(toolHeadDrill, StainlessSteel)
                        .notConsumable(stick, StainlessSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3x16.chancedOutput(ore, output, 256, 9000, 0)

                    builder3x16.EUt(VA[HV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()
                }
            }
            // =========================================================================================================
            if (tier == 2) // Advanced
            {
                for (fuel in fuelAdvanced)
                {
                    // Rank 1 Asteroid Mining
                    val builder1 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_MV)
                        .notConsumable(toolHeadDrill, WroughtIron)
                        .notConsumable(stick, WroughtIron)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1.chancedOutput(ore, output, 3000, 0)

                    builder1.EUt(VA[MV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // Rank 1 Asteroid Mining 4x
                    val builder1x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_MV.getStackForm(4))
                        .notConsumable(toolHeadDrill, Steel)
                        .notConsumable(stick, Steel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1x4.chancedOutput(ore, output, 4, 3000, 0)

                    builder1x4.EUt(VA[MV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // Rank 1 Asteroid Mining 16x
                    val builder1x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_MV.getStackForm(16))
                        .notConsumable(toolHeadDrill, Diamond)
                        .notConsumable(stick, Diamond)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1x16.chancedOutput(ore, output, 16, 3000, 0)

                    builder1x16.EUt(VA[MV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // -------------------------------------------------------------------------------------------------
                    // Rank 2 Asteroid Mining
                    val builder2 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV)
                        .notConsumable(toolHeadDrill, Aluminium)
                        .notConsumable(stick, Aluminium)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2.chancedOutput(ore, output, 4, 6000, 0)

                    builder2.EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // Rank 2 Asteroid Mining 4x
                    val builder2x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV.getStackForm(4))
                        .notConsumable(toolHeadDrill, CobaltBrass)
                        .notConsumable(stick, CobaltBrass)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2x4.chancedOutput(ore, output, 16, 6000, 0)

                    builder2x4.EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // Rank 2 Asteroid Mining 16x
                    val builder2x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV.getStackForm(16))
                        .notConsumable(toolHeadDrill, StainlessSteel)
                        .notConsumable(stick, StainlessSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2x16.chancedOutput(ore, output, 64, 6000, 0)

                    builder2x16.EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // -------------------------------------------------------------------------------------------------
                    // Rank 3 Asteroid Mining
                    val builder3 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_EV)
                        .notConsumable(toolHeadDrill, VanadiumSteel)
                        .notConsumable(stick, VanadiumSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3.chancedOutput(oreNetherrack, output, 16, 9000, 0)

                    builder3.EUt(VA[EV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()

                    // Rank 3 Asteroid Mining 4x
                    val builder3x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_EV.getStackForm(4))
                        .notConsumable(toolHeadDrill, BlueSteel)
                        .notConsumable(stick, BlueSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3x4.chancedOutput(oreNetherrack, output, 64, 9000, 0)

                    builder3x4.EUt(VA[EV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()

                    // Rank 3 Asteroid Mining 16x
                    val builder3x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_EV.getStackForm(16))
                        .notConsumable(toolHeadDrill, RedSteel)
                        .notConsumable(stick, RedSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3x16.chancedOutput(oreNetherrack, output, 256, 9000, 0)

                    builder3x16.EUt(VA[EV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()
                }
            }
            // =========================================================================================================
            if (tier == 3) // Elite
            {
                for (fuel in fuelAdvanced)
                {
                    // Rank 1 Asteroid Mining
                    val builder1 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV)
                        .notConsumable(toolHeadDrill, Aluminium)
                        .notConsumable(stick, Aluminium)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1.chancedOutput(ore, output, 3000, 0)

                    builder1.EUt(VA[HV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // Rank 1 Asteroid Mining 4x
                    val builder1x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV.getStackForm(4))
                        .notConsumable(toolHeadDrill, CobaltBrass)
                        .notConsumable(stick, CobaltBrass)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1x4.chancedOutput(ore, output, 4, 3000, 0)

                    builder1x4.EUt(VA[HV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // Rank 1 Asteroid Mining 16x
                    val builder1x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_HV.getStackForm(16))
                        .notConsumable(toolHeadDrill, StainlessSteel)
                        .notConsumable(stick, StainlessSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder1x16.chancedOutput(ore, output, 16, 3000, 0)

                    builder1x16.EUt(VA[HV].toLong())
                        .duration(2 * MINUTE)
                        .buildAndRegister()

                    // -------------------------------------------------------------------------------------------------
                    // Rank 2 Asteroid Mining
                    val builder2 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_EV)
                        .notConsumable(toolHeadDrill, VanadiumSteel)
                        .notConsumable(stick, VanadiumSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2.chancedOutput(oreNetherrack, output, 4, 6000, 0)

                    builder2.EUt(VA[EV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // Rank 2 Asteroid Mining 4x
                    val builder2x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_EV.getStackForm(4))
                        .notConsumable(toolHeadDrill, BlueSteel)
                        .notConsumable(stick, BlueSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2x4.chancedOutput(oreNetherrack, output, 16, 6000, 0)

                    builder2x4.EUt(VA[EV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // Rank 2 Asteroid Mining 16x
                    val builder2x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_EV.getStackForm(16))
                        .notConsumable(toolHeadDrill, RedSteel)
                        .notConsumable(stick, RedSteel)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder2x16.chancedOutput(oreNetherrack, output, 64, 6000, 0)

                    builder2x16.EUt(VA[EV].toLong())
                        .duration(1 * MINUTE)
                        .buildAndRegister()

                    // -------------------------------------------------------------------------------------------------
                    // Rank 3 Asteroid Mining
                    val builder3 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_IV)
                        .notConsumable(toolHeadDrill, Titanium)
                        .notConsumable(stick, Titanium)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3.chancedOutput(oreNetherrack, output, 16, 9000, 0)

                    builder3.EUt(VA[IV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()

                    // Rank 3 Asteroid Mining 4x
                    val builder3x4 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_IV.getStackForm(4))
                        .notConsumable(toolHeadDrill, Ultimet)
                        .notConsumable(stick, Ultimet)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3x4.chancedOutput(oreNetherrack, output, 64, 9000, 0)

                    builder3x4.EUt(VA[IV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()

                    // Rank 3 Asteroid Mining 16x
                    val builder3x16 = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(MINING_DRONE_IV.getStackForm(16))
                        .notConsumable(toolHeadDrill, TungstenCarbide)
                        .notConsumable(stick, TungstenCarbide)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder3x16.chancedOutput(oreNetherrack, output, 256, 9000, 0)

                    builder3x16.EUt(VA[IV].toLong())
                        .duration(30 * SECOND)
                        .buildAndRegister()
                }
            }
            // =========================================================================================================
            if (tier == 4) // Ultimate
            {

            }

            // =========================================================================================================
            if (tier == 5) // Epic
            {

            }

            // =========================================================================================================
            if (tier == 6) // Legendary
            {

            }

        }

    }

}
