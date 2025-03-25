package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Asbestos
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.BasalticMineralSand
import gregtech.api.unification.material.Materials.BioDiesel
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
import gregtech.api.unification.material.Materials.GlauconiteSand
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.GraniticMineralSand
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.HeavyFuel
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lepidolite
import gregtech.api.unification.material.Materials.LightFuel
import gregtech.api.unification.material.Materials.Magnetite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Pentlandite
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.RocketFuel
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Spodumene
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Trona
import gregtech.api.unification.material.Materials.VanadiumMagnetite
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.material.Materials.Zeolite
import gregtech.api.unification.ore.OrePrefix.ore
import gregtech.common.items.MetaItems.PROCESSOR_MV
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MINING_DRONE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Anorthite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Augite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bytownite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dolomite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Labradorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lignite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Oligoclase
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Wollastonite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_EV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_HV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_LV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_MV
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class MiningDroneAsteroidRecipeProducer
{

    companion object
    {

        // TT is the rank tier, T is the recipe tier for all asteroids.
        fun produce() {

            // TT1 Asteroids
            for (fuel in arrayOf(
                LightFuel.getFluid(16000),
                HeavyFuel.getFluid(14000),
                Diesel.getFluid(12000),
                BioDiesel.getFluid(12000),
                CetaneBoostedDiesel.getFluid(8000),
                RocketFuel.getFluid(6000),
                // RP1RocketFuel.getFluid(6000),
                // DenseHydrazineRocketFuel.getFluid(2000),
                // MethylhydrazineNitrateRocketFuel.getFluid(2000)
            ))
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

            // TT2 Asteroids
            for (fuel in arrayOf(
                LightFuel.getFluid(24000),
                HeavyFuel.getFluid(22000),
                Diesel.getFluid(20000),
                BioDiesel.getFluid(20000),
                CetaneBoostedDiesel.getFluid(16000),
                RocketFuel.getFluid(14000),
                // RP1RocketFuel.getFluid(14000),
                // DenseHydrazineRocketFuel.getFluid(10000),
                // MethylhydrazineNitrateRocketFuel.getFluid(10000)
            ))
            {
                // T1: Mica Asteroid

                // T2: Mica Asteroid

                // T3: Mica Asteroid

                // -----------------------------------------------------------------------------------------------------

                // T1: Augite Asteroid

                // T2: Augite Asteroid

                // T3: Augite Asteroid

                // -----------------------------------------------------------------------------------------------------

                // T1: Nickel-Cobalt Asteroid

                // T2: Nickel-Cobalt Asteroid

                // T3: Nickel-Cobalt Asteroid

                // -----------------------------------------------------------------------------------------------------

                // T1: Manganese-Tantalum Asteroid

                // T2: Manganese-Tantalum Asteroid

                // T3: Manganese-Tantalum Asteroid

                // -----------------------------------------------------------------------------------------------------

                // T1: Redstone Asteroid

                // T2: Redstone Asteroid

                // T3: Redstone Asteroid

                // -----------------------------------------------------------------------------------------------------

                // T1: Various Gems Asteroid 1

                // T2: Various Gems Asteroid 1

                // T3: Various Gems Asteroid 1

                // -----------------------------------------------------------------------------------------------------

                // T1: Various Gems Asteroid 2

                // T2: Various Gems Asteroid 2

                // T3: Various Gems Asteroid 3

                // -----------------------------------------------------------------------------------------------------

                // T1: Aluminium-Chrome Asteroid

                // T2: Aluminium-Chrome Asteroid

                // T3: Aluminium-Chrome Asteroid

            }

            // TT3 Asteroids
            for (fuel in arrayOf(
                LightFuel.getFluid(32000),
                HeavyFuel.getFluid(30000),
                Diesel.getFluid(28000),
                BioDiesel.getFluid(28000),
                CetaneBoostedDiesel.getFluid(24000),
                RocketFuel.getFluid(22000),
                // RP1RocketFuel.getFluid(22000),
                // DenseHydrazineRocketFuel.getFluid(18000),
                // MethylhydrazineNitrateRocketFuel.getFluid(18000)
            ))
            {
                // T1: Molybdenum

                // T1: Titanium

                // T1: Tungsten

                // T1: Zirconium
            }

        }

    }

}

/*

                // MINING_DRONE_RECIPES.recipeBuilder()
                //     .circuitMeta(2)
                //     .notConsumable(MINING_DRONE_LV)
                //     .fluidInputs(fuel)
                //     .chancedOutput(ore, Augite, 18, 3000, 0)
                //     .chancedOutput(ore, Ferrosilite, 16, 2800, 0)
                //     .chancedOutput(ore, Spodumene, 14, 2600, 0)
                //     .chancedOutput(ore, Lepidolite, 12, 2400, 0)
                //     .chancedOutput(ore, Dolomite, 10, 2200, 0)
                //     .chancedOutput(ore, Trona, 8, 2000, 0)
                //     .chancedOutput(ore, Wollastonite, 6, 1500, 0)
                //     .chancedOutput(ore, Andradite, 4, 1000, 0)
                //     .EUt(VA[LV].toLong())
                //     .duration(2 * MINUTE)
                //     .buildAndRegister()



                // Mica Asteroid
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Mica, 32, 4800, 0)
                    .chancedOutput(ore, Asbestos, 28, 3600, 0)
                    .chancedOutput(ore, Lepidolite, 24, 3200, 0)
                    .chancedOutput(ore, Anorthite, 18, 2900)
                    .chancedOutput(ore, Oligoclase, 12, 2400)
                    .chancedOutput(ore, Labradorite, 8, 2200)
                    .chancedOutput(ore, Bytownite, 4, 1600)
                    .EUt(VA[MV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()

                // Nickel-Cobalt Asteroid
                MINING_DRONE_RECIPES.recipeBuilder()
                    .circuitMeta(7)
                    .notConsumable(MINING_DRONE_MV)
                    .fluidInputs(fuel)
                    .chancedOutput(ore, Nickel, 42, 5000, 0)
                    .chancedOutput(ore, Cobalt, 36, 4200, 0)
                    .chancedOutput(ore, Pentlandite, 32, 3800)
                    .chancedOutput(ore, Garnierite, 26, 3400)
                    .chancedOutput(ore, Cobaltite, 24, 2800)
                    .chancedOutput(ore, GlauconiteSand, 10, 1200)
                    .EUt(VA[MV].toLong())
                    .duration(2 * MINUTE)
                    .buildAndRegister()


                    .chancedOutputs(ore, Lead, )
            }


 */