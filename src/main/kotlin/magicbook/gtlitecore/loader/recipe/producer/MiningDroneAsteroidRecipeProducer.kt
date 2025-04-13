package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.BasalticMineralSand
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Bentonite
import gregtech.api.unification.material.Materials.BioDiesel
import gregtech.api.unification.material.Materials.Biotite
import gregtech.api.unification.material.Materials.BrownLimonite
import gregtech.api.unification.material.Materials.Cassiterite
import gregtech.api.unification.material.Materials.CassiteriteSand
import gregtech.api.unification.material.Materials.CetaneBoostedDiesel
import gregtech.api.unification.material.Materials.Chalcopyrite
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Diesel
import gregtech.api.unification.material.Materials.Ferrosilite
import gregtech.api.unification.material.Materials.Galena
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Gasoline
import gregtech.api.unification.material.Materials.GlauconiteSand
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.GraniticMineralSand
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.HeavyFuel
import gregtech.api.unification.material.Materials.HighOctaneGasoline
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kyanite
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lepidolite
import gregtech.api.unification.material.Materials.LightFuel
import gregtech.api.unification.material.Materials.Magnetite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Pentlandite
import gregtech.api.unification.material.Materials.Pollucite
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Pyrochlore
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.RocketFuel
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Soapstone
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.Spodumene
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.Tantalite
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Trona
import gregtech.api.unification.material.Materials.VanadiumMagnetite
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.material.Materials.Zeolite
import gregtech.api.unification.ore.OrePrefix.ore
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MINING_DRONE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Augite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DenseHydrazineRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dolomite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kaolinite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lignite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylhydrazineNitrateRocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phlogopite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RP1RocketFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Wollastonite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_EV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_HV
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
            // Basic Asteroids/Virtual Veins (LV-HV)
            for (fuel in fuelBasic)
            {
                // T1: Carbon Group Asteroid (Coal-Lignite-Graphite-Diamond)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Coal, 40, 6000, 0)
                    .chancedOutput(ore, Lignite, 32, 4000, 0)
                    .chancedOutput(ore, Graphite, 16, 3000, 0)
                    .chancedOutput(ore, Diamond, 4, 1000, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Carbon Group Asteroid (Coal-Lignite-Graphite-Diamond)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Coal, 64, 7500, 0)
                    .chancedOutput(ore, Coal, 24, 7500, 0)
                    .chancedOutput(ore, Lignite, 64, 6000, 0)
                    .chancedOutput(ore, Lignite, 12, 6000, 0)
                    .chancedOutput(ore, Graphite, 48, 4800, 0)
                    .chancedOutput(ore, Diamond, 24, 3000, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Carbon Group Asteroid (Coal-Lignite-Graphite-Diamond)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Coal, 64, 8000, 0)
                    .chancedOutput(ore, Coal, 64, 8000, 0)
                    .chancedOutput(ore, Coal, 32, 8000, 0)
                    .chancedOutput(ore, Lignite, 64, 7000, 0)
                    .chancedOutput(ore, Lignite, 64, 7000, 0)
                    .chancedOutput(ore, Lignite, 24, 7000, 0)
                    .chancedOutput(ore, Graphite, 64, 5500, 0)
                    .chancedOutput(ore, Graphite, 48, 5500, 0)
                    .chancedOutput(ore, Diamond, 32, 4000, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Iron Asteroid 1 (Iron-BandedIron-BrownLimonite-YellowLimonite-Pyrite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Iron, 32, 5800, 0)
                    .chancedOutput(ore, BandedIron, 24, 5200, 0)
                    .chancedOutput(ore, BrownLimonite, 18, 4400, 0)
                    .chancedOutput(ore, YellowLimonite, 18, 4400, 0)
                    .chancedOutput(ore, Pyrite, 12, 3600, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Iron Asteroid 1 (Iron-BandedIron-BrownLimonite-YellowLimonite-Pyrite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Iron, 64, 7000, 0)
                    .chancedOutput(ore, BandedIron, 48, 6300, 0)
                    .chancedOutput(ore, BrownLimonite, 36, 5500, 0)
                    .chancedOutput(ore, YellowLimonite, 36, 5500, 0)
                    .chancedOutput(ore, Pyrite, 24, 4000, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Iron Asteroid 1 (Iron-BandedIron-BrownLimonite-YellowLimonite-Pyrite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Iron, 64, 7700, 0)
                    .chancedOutput(ore, Iron, 32, 7700, 0)
                    .chancedOutput(ore, BandedIron, 64, 7200, 0)
                    .chancedOutput(ore, BandedIron, 24, 7200, 0)
                    .chancedOutput(ore, BrownLimonite, 64, 6400, 0)
                    .chancedOutput(ore, BrownLimonite, 12, 6400, 0)
                    .chancedOutput(ore, YellowLimonite, 64, 6400, 0)
                    .chancedOutput(ore, YellowLimonite, 12, 6400, 0)
                    .chancedOutput(ore, Pyrite, 48, 5500, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Iron Asteroid 2 (Magnetite-VanadiumMagnetite-Gold-BasalticMineralSand-GraniticMineralSand)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Magnetite, 40, 5000, 0)
                    .chancedOutput(ore, VanadiumMagnetite, 26, 4800, 0)
                    .chancedOutput(ore, Gold, 18, 4300, 0)
                    .chancedOutput(ore, BasalticMineralSand, 12, 3000, 0)
                    .chancedOutput(ore, GraniticMineralSand, 12, 3000, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Iron Asteroid 2 (Magnetite-VanadiumMagnetite-Gold-BasalticMineralSand-GraniticMineralSand)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Magnetite, 64, 6500, 0)
                    .chancedOutput(ore, Magnetite, 20, 6500, 0)
                    .chancedOutput(ore, VanadiumMagnetite, 52, 5400, 0)
                    .chancedOutput(ore, Gold, 36, 5000, 0)
                    .chancedOutput(ore, BasalticMineralSand, 24, 4000, 0)
                    .chancedOutput(ore, GraniticMineralSand, 24, 4000, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Iron Asteroid 2 (Magnetite-VanadiumMagnetite-Gold-BasalticMineralSand-GraniticMineralSand)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Magnetite, 64, 8000, 0)
                    .chancedOutput(ore, Magnetite, 48, 8000, 0)
                    .chancedOutput(ore, VanadiumMagnetite, 64, 6500, 0)
                    .chancedOutput(ore, VanadiumMagnetite, 24, 6500, 0)
                    .chancedOutput(ore, Gold, 64, 5500, 0)
                    .chancedOutput(ore, BasalticMineralSand, 48, 5000, 0)
                    .chancedOutput(ore, GraniticMineralSand, 48, 5000, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Copper Asteroid 1 (Copper-Chalcopyrite-Malachite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Copper, 36, 5800, 0)
                    .chancedOutput(ore, Chalcopyrite, 24, 4400, 0)
                    .chancedOutput(ore, Malachite, 12, 3200, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Copper Asteroid 1 (Copper-Chalcopyrite-Malachite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Copper, 64, 7000, 0)
                    .chancedOutput(ore, Copper, 12, 7000, 0)
                    .chancedOutput(ore, Chalcopyrite, 48, 6000, 0)
                    .chancedOutput(ore, Malachite, 32, 4800, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Copper Asteroid 1 (Copper-Chalcopyrite-Malachite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Copper, 64, 8000, 0)
                    .chancedOutput(ore, Copper, 48, 8000, 0)
                    .chancedOutput(ore, Chalcopyrite, 64, 7600, 0)
                    .chancedOutput(ore, Chalcopyrite, 36, 7600, 0)
                    .chancedOutput(ore, Malachite, 64, 6000, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Copper Asteroid 2 (Cuprite-Azurite-Tenorite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(5)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Cuprite, 36, 5500, 0)
                    .chancedOutput(ore, Azurite, 28, 4000, 0)
                    .chancedOutput(ore, Tenorite, 14, 3000, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Copper Asteroid 2 (Cuprite-Azurite-Tenorite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(5)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Cuprite, 64, 7200, 0)
                    .chancedOutput(ore, Cuprite, 12, 7200, 0)
                    .chancedOutput(ore, Azurite, 64, 6000, 0)
                    .chancedOutput(ore, Tenorite, 32, 4800, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Copper Asteroid 2 (Cuprite-Azurite-Tenorite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(5)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Cuprite, 64, 8000, 0)
                    .chancedOutput(ore, Cuprite, 48, 8000, 0)
                    .chancedOutput(ore, Azurite, 64, 7200, 0)
                    .chancedOutput(ore, Azurite, 32, 7200, 0)
                    .chancedOutput(ore, Tenorite, 64, 6400, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Tin Asteroid (Cassiterite-CassiteriteSand-Tin-Zeolite-Realgar)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Cassiterite, 36, 5800, 0)
                    .chancedOutput(ore, CassiteriteSand, 24, 4700, 0)
                    .chancedOutput(ore, Tin, 18, 4200, 0)
                    .chancedOutput(ore, Zeolite, 12, 3600, 0)
                    .chancedOutput(ore, Realgar, 6, 2400, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Tin Asteroid (Cassiterite-CassiteriteSand-Tin-Zeolite-Realgar)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Cassiterite, 64, 6600, 0)
                    .chancedOutput(ore, Cassiterite, 12, 6600, 0)
                    .chancedOutput(ore, CassiteriteSand, 48, 5800, 0)
                    .chancedOutput(ore, Tin, 36, 5200, 0)
                    .chancedOutput(ore, Zeolite, 24, 4500, 0)
                    .chancedOutput(ore, Realgar, 16, 3500, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Tin Asteroid (Cassiterite-CassiteriteSand-Tin-Zeolite-Realgar)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Cassiterite, 64, 7500, 0)
                    .chancedOutput(ore, Cassiterite, 48, 7500, 0)
                    .chancedOutput(ore, CassiteriteSand, 64, 6800, 0)
                    .chancedOutput(ore, CassiteriteSand, 32, 6800, 0)
                    .chancedOutput(ore, Tin, 64, 5900, 0)
                    .chancedOutput(ore, Tin, 24, 5900, 0)
                    .chancedOutput(ore, Zeolite, 64, 5300, 0)
                    .chancedOutput(ore, Realgar, 48, 4800, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Salt Asteroid (Salt-RockSalt-Saltpeter-Lepidolite-Spodumene)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(7)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Salt, 48, 6000, 0)
                    .chancedOutput(ore, RockSalt, 48, 6000, 0)
                    .chancedOutput(ore, Saltpeter, 32, 5000, 0)
                    .chancedOutput(ore, Lepidolite, 24, 4000, 0)
                    .chancedOutput(ore, Spodumene, 12, 3000, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Salt Asteroid (Salt-RockSalt-Saltpeter-Lepidolite-Spodumene)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(7)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Salt, 64, 7000, 0)
                    .chancedOutput(ore, Salt, 32, 7000, 0)
                    .chancedOutput(ore, RockSalt, 64, 7000, 0)
                    .chancedOutput(ore, RockSalt, 64, 7000, 0)
                    .chancedOutput(ore, Saltpeter, 64, 6000, 0)
                    .chancedOutput(ore, Lepidolite, 48, 5000, 0)
                    .chancedOutput(ore, Spodumene, 24, 4000, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Salt Asteroid (Salt-RockSalt-Saltpeter-Lepidolite-Spodumene)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(7)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Salt, 64, 8000, 0)
                    .chancedOutput(ore, Salt, 64, 8000, 0)
                    .chancedOutput(ore, Salt, 32, 8000, 0)
                    .chancedOutput(ore, RockSalt, 64, 8000, 0)
                    .chancedOutput(ore, RockSalt, 64, 8000, 0)
                    .chancedOutput(ore, RockSalt, 32, 8000, 0)
                    .chancedOutput(ore, Saltpeter, 64, 7000, 0)
                    .chancedOutput(ore, Saltpeter, 64, 7000, 0)
                    .chancedOutput(ore, Lepidolite, 64, 6000, 0)
                    .chancedOutput(ore, Lepidolite, 24, 6000, 0)
                    .chancedOutput(ore, Spodumene, 64, 5000, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Lead Asteroid (Lead-Galena-Silver-Antimony)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(8)
                    .notConsumable(MINING_DRONE_LV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Lead, 36, 4400, 0)
                    .chancedOutput(ore, Galena, 28, 3800, 0)
                    .chancedOutput(ore, Silver, 14, 2400, 0)
                    .chancedOutput(ore, Antimony, 6, 1200, 0)
                    .EUt(VA[LV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Lead Asteroid (Lead-Galena-Silver-Antimony)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(8)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Lead, 64, 6000, 0)
                    .chancedOutput(ore, Lead, 12, 6000, 0)
                    .chancedOutput(ore, Galena, 56, 5200, 0)
                    .chancedOutput(ore, Silver, 32, 4800, 0)
                    .chancedOutput(ore, Antimony, 16, 2400, 0)
                    .EUt(VA[MV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Lead Asteroid (Lead-Galena-Silver-Antimony)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(8)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Lead, 64, 7400, 0)
                    .chancedOutput(ore, Lead, 32, 7400, 0)
                    .chancedOutput(ore, Galena, 64, 6300, 0)
                    .chancedOutput(ore, Galena, 24, 6300, 0)
                    .chancedOutput(ore, Silver, 64, 5500, 0)
                    .chancedOutput(ore, Antimony, 48, 3800, 0)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()
            }

            // =========================================================================================================
            // Advanced Asteroids/Virtual Veins (MV-EV)
            for (fuel in fuelAdvanced)
            {
                // T1: Mica Asteroid (Mica-Kyanite-Bauxite-Pollucite-Muscovite-Phlogopite-Biotite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Mica, 25, 6400, 0)
                    .chancedOutput(ore, Kyanite, 22, 5500, 0)
                    .chancedOutput(ore, Bauxite, 18, 4600, 0)
                    .chancedOutput(ore, Pollucite, 16, 4000, 0)
                    .chancedOutput(ore, Muscovite, 12, 3600, 0)
                    .chancedOutput(ore, Phlogopite, 8, 2400, 0)
                    .chancedOutput(ore, Biotite, 4, 1200, 0)
                    .EUt(VA[MV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Mica Asteroid (Mica-Kyanite-Bauxite-Pollucite-Muscovite-Phlogopite-Biotite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Mica, 64, 8000, 0)
                    .chancedOutput(ore, Kyanite, 44, 7100, 0)
                    .chancedOutput(ore, Bauxite, 36, 6200, 0)
                    .chancedOutput(ore, Pollucite, 32, 5400, 0)
                    .chancedOutput(ore, Muscovite, 24, 4000, 0)
                    .chancedOutput(ore, Phlogopite, 16, 3600, 0)
                    .chancedOutput(ore, Biotite, 8, 2400, 0)
                    .EUt(VA[HV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Mica Asteroid (Mica-Kyanite-Bauxite-Pollucite-Muscovite-Phlogopite-Biotite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .notConsumable(MINING_DRONE_EV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Mica, 64, 9000, 0)
                    .chancedOutput(ore, Mica, 48, 9000, 0)
                    .chancedOutput(ore, Bauxite, 64, 8400, 0)
                    .chancedOutput(ore, Bauxite, 32, 8400, 0)
                    .chancedOutput(ore, Pollucite, 64, 7500, 0)
                    .chancedOutput(ore, Pollucite, 24, 7500, 0)
                    .chancedOutput(ore, Muscovite, 64, 6300, 0)
                    .chancedOutput(ore, Phlogopite, 48, 5600, 0)
                    .chancedOutput(ore, Biotite, 32, 4800, 0)
                    .EUt(VA[EV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Kaolinite-Dolomite Asteroid (Kaolinite-Bentonite-Augite-Ferrosilite-Spodumene-Dolomite-Wollastonite-Trona)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(10)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Kaolinite, 30, 6000, 0)
                    .chancedOutput(ore, Bentonite, 24, 5500, 0)
                    .chancedOutput(ore, Augite, 18, 4700, 0)
                    .chancedOutput(ore, Ferrosilite, 16, 4200, 0)
                    .chancedOutput(ore, Spodumene, 12, 3000, 0)
                    .chancedOutput(ore, Dolomite, 8, 2400, 0)
                    .chancedOutput(ore, Wollastonite, 6, 1800, 0)
                    .chancedOutput(ore, Trona, 4, 1000, 0)
                    .EUt(VA[MV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Kaolinite-Dolomite Asteroid (Kaolinite-Bentonite-Augite-Ferrosilite-Spodumene-Dolomite-Wollastonite-Trona)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(10)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Kaolinite, 64, 7200, 0)
                    .chancedOutput(ore, Bentonite, 48, 6400, 0)
                    .chancedOutput(ore, Augite, 36, 5500, 0)
                    .chancedOutput(ore, Ferrosilite, 32, 4800, 0)
                    .chancedOutput(ore, Spodumene, 24, 4200, 0)
                    .chancedOutput(ore, Dolomite, 18, 3600, 0)
                    .chancedOutput(ore, Wollastonite, 12, 2900, 0)
                    .chancedOutput(ore, Trona, 8, 2400, 0)
                    .EUt(VA[HV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Kaolinite-Dolomite Asteroid (Kaolinite-Bentonite-Augite-Ferrosilite-Spodumene-Dolomite-Wollastonite-Trona)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(10)
                    .notConsumable(MINING_DRONE_EV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Kaolinite, 64, 8600, 0)
                    .chancedOutput(ore, Kaolinite, 64, 8600, 0)
                    .chancedOutput(ore, Augite, 64, 7800, 0)
                    .chancedOutput(ore, Augite, 48, 7800, 0)
                    .chancedOutput(ore, Ferrosilite, 64, 6400, 0)
                    .chancedOutput(ore, Ferrosilite, 32, 6400, 0)
                    .chancedOutput(ore, Spodumene, 64, 5700, 0)
                    .chancedOutput(ore, Spodumene, 24, 5700, 0)
                    .chancedOutput(ore, Dolomite, 64, 4900, 0)
                    .chancedOutput(ore, Dolomite, 12, 4900, 0)
                    .chancedOutput(ore, Wollastonite, 64, 4200, 0)
                    .chancedOutput(ore, Trona, 48, 3600, 0)
                    .EUt(VA[EV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Nickel-Cobalt Asteroid (Nickel-Garnierite-Cobalt-Cobaltite-Pyrite-Pentlandite-Soapstone-Talc-GlauconiteSand)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(11)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Nickel, 25, 7500, 0)
                    .chancedOutput(ore, Garnierite, 22, 6800, 0)
                    .chancedOutput(ore, Cobalt, 18, 6400, 0)
                    .chancedOutput(ore, Cobaltite, 15, 5000, 0)
                    .chancedOutput(ore, Pyrite, 12, 4300, 0)
                    .chancedOutput(ore, Pentlandite, 10, 3600, 0)
                    .chancedOutput(ore, Soapstone, 8, 2700, 0)
                    .chancedOutput(ore, Talc, 6, 1900, 0)
                    .chancedOutput(ore, GlauconiteSand, 4, 800, 0)
                    .EUt(VA[MV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Nickel-Cobalt Asteroid (Nickel-Garnierite-Cobalt-Cobaltite-Pyrite-Pentlandite-Soapstone-Talc-GlauconiteSand)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(11)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Nickel, 52, 8600, 0)
                    .chancedOutput(ore, Garnierite, 48, 7800, 0)
                    .chancedOutput(ore, Cobalt, 36, 7200, 0)
                    .chancedOutput(ore, Cobaltite, 32, 6500, 0)
                    .chancedOutput(ore, Pyrite, 28, 5400, 0)
                    .chancedOutput(ore, Pentlandite, 24, 4800, 0)
                    .chancedOutput(ore, Soapstone, 18, 4000, 0)
                    .chancedOutput(ore, Talc, 12, 3600, 0)
                    .chancedOutput(ore, GlauconiteSand, 8, 2700, 0)
                    .EUt(VA[HV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Nickel-Cobalt Asteroid (Nickel-Garnierite-Cobalt-Cobaltite-Pyrite-Pentlandite-Soapstone-Talc-GlauconiteSand)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(11)
                    .notConsumable(MINING_DRONE_EV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Nickel, 64, 9000, 0)
                    .chancedOutput(ore, Nickel, 52, 9000, 0)
                    .chancedOutput(ore, Garnierite, 64, 8500, 0)
                    .chancedOutput(ore, Garnierite, 48, 8500, 0)
                    .chancedOutput(ore, Cobalt, 64, 8000, 0)
                    .chancedOutput(ore, Cobalt, 36, 8000, 0)
                    .chancedOutput(ore, Cobaltite, 64, 7400, 0)
                    .chancedOutput(ore, Cobaltite, 24, 7400, 0)
                    .chancedOutput(ore, Pyrite, 64, 6400, 0)
                    .chancedOutput(ore, Pentlandite, 48, 5600, 0)
                    .chancedOutput(ore, Soapstone, 36, 4800, 0)
                    .chancedOutput(ore, Talc, 24, 3700, 0)
                    .chancedOutput(ore, GlauconiteSand, 18, 3200, 0)
                    .EUt(VA[EV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------
                // T1: Manganese-Tantalum Asteroid (Pyrolusite-Tantalite-Pyrochlore-Grossular-Spessartine-Forsterite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(12)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Pyrolusite, 25, 6700, 0)
                    .chancedOutput(ore, Tantalite, 22, 5400, 0)
                    .chancedOutput(ore, Pyrochlore, 18, 4300, 0)
                    .chancedOutput(ore, Grossular, 12, 3500, 0)
                    .chancedOutput(ore, Spessartine, 8, 2200, 0)
                    .chancedOutput(ore, Forsterite, 4, 1000, 0)
                    .EUt(VA[MV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // T2: Manganese-Tantalum Asteroid (Pyrolusite-Tantalite-Pyrochlore-Grossular-Spessartine-Forsterite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(12)
                    .notConsumable(MINING_DRONE_HV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Pyrolusite, 48, 8000, 0)
                    .chancedOutput(ore, Tantalite, 36, 7400, 0)
                    .chancedOutput(ore, Pyrochlore, 32, 6500, 0)
                    .chancedOutput(ore, Grossular, 24, 5000, 0)
                    .chancedOutput(ore, Spessartine, 16, 4100, 0)
                    .chancedOutput(ore, Forsterite, 8, 2900, 0)
                    .EUt(VA[HV].toLong())
                    .duration(1 * MINUTE)
                    .buildAndRegister()

                // T3: Manganese-Tantalum Asteroid (Pyrolusite-Tantalite-Pyrochlore-Grossular-Spessartine-Forsterite)
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(12)
                    .notConsumable(MINING_DRONE_EV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Pyrolusite, 64, 9000, 0)
                    .chancedOutput(ore, Pyrolusite, 48, 9000, 0)
                    .chancedOutput(ore, Tantalite, 64, 8500, 0)
                    .chancedOutput(ore, Tantalite, 32, 8500, 0)
                    .chancedOutput(ore, Pyrochlore, 64, 7600, 0)
                    .chancedOutput(ore, Grossular, 48, 6600, 0)
                    .chancedOutput(ore, Spessartine, 32, 5400, 0)
                    .chancedOutput(ore, Forsterite, 24, 4800, 0)
                    .EUt(VA[EV].toLong())
                    .duration(30 * SECOND)
                    .buildAndRegister()

                // -----------------------------------------------------------------------------------------------------

                // T1: Redstone Asteroid

                // T2: Redstone Asteroid

                // T3: Redstone Asteroid

                // -----------------------------------------------------------------------------------------------------

                // T1: Various Gems Asteroid 1 (Diamond-Ruby-Emerald-Olivine-Sapphire-GreenSapphire-Lapis-Lazurite-Sodalite-CertusQuartz-Topaz-BlueTopaz-Amethyst-Opal)

                // T2: Various Gems Asteroid 1 (Diamond-Ruby-Emerald-Olivine-Sapphire-GreenSapphire-Lapis-Lazurite-Sodalite-CertusQuartz-Topaz-BlueTopaz-Amethyst-Opal)

                // T3: Various Gems Asteroid 1 (Diamond-Ruby-Emerald-Olivine-Sapphire-GreenSapphire-Lapis-Lazurite-Sodalite-CertusQuartz-Topaz-BlueTopaz-Amethyst-Opal)

                // -----------------------------------------------------------------------------------------------------

                // T1: Various Gems Asteroid 2 (Almandine-Andradite-Grossular-Pyrope-Spessartine-Uvarovite-Tanzanite-Quartzite-Realgar-Opriment-Apatite-GarnetRed-GarnetYellow-Zircon)

                // T2: Various Gems Asteroid 2 (Almandine-Andradite-Grossular-Pyrope-Spessartine-Uvarovite-Tanzanite-Quartzite-Realgar-Opriment-Apatite-GarnetRed-GarnetYellow-Zircon)

                // T3: Various Gems Asteroid 3 (Almandine-Andradite-Grossular-Pyrope-Spessartine-Uvarovite-Tanzanite-Quartzite-Realgar-Opriment-Apatite-GarnetRed-GarnetYellow-Zircon)

                // -----------------------------------------------------------------------------------------------------

                // T1: Aluminium-Chrome Asteroid

                // T2: Aluminium-Chrome Asteroid

                // T3: Aluminium-Chrome Asteroid

            }

            // Elite Asteroids (HV-IV)
            for (fuel in fuelElite)
            {
                // T1: Molybdenum Asteroid

                // T2: Molybdenum Asteroid

                // T3: Molybdenum Asteroid

                // -----------------------------------------------------------------------------------------------------
                // T1: Titanium Asteroid

                // T2: Titanium Asteroid

                // T3: Titanium Asteroid

                // -----------------------------------------------------------------------------------------------------
                // T1: Tungsten Asteroid

                // T1: Zirconium Asteroid
            }

            // Ultimate Asteroids (EV-LuV)
            for (fuel in fuelUltimate)
            {
                // ...
            }

            // Epic Asteroids (IV-ZPM)
            for (fuel in fuelEpic)
            {
                // ...
            }

            // Legendary Asteroids (LuV-UV)
            for (fuel in fuelLegendary)
            {
                // ...
            }

        }

    }

}
