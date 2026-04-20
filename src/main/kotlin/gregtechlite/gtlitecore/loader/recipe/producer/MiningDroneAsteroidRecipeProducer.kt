package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Alunite
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.Asbestos
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barite
import gregtech.api.unification.material.Materials.BasalticMineralSand
import gregtech.api.unification.material.Materials.Bastnasite
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
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Cassiterite
import gregtech.api.unification.material.Materials.CassiteriteSand
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.CetaneBoostedDiesel
import gregtech.api.unification.material.Materials.Chalcocite
import gregtech.api.unification.material.Materials.Chalcopyrite
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Chromite
import gregtech.api.unification.material.Materials.Cinnabar
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.CobaltBrass
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.Cooperite
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Diatomite
import gregtech.api.unification.material.Materials.Diesel
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Electrotine
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.FullersEarth
import gregtech.api.unification.material.Materials.Galena
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetSand
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Gasoline
import gregtech.api.unification.material.Materials.GlauconiteSand
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.GraniticMineralSand
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.Gypsum
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HeavyFuel
import gregtech.api.unification.material.Materials.HighOctaneGasoline
import gregtech.api.unification.material.Materials.Ilmenite
import gregtech.api.unification.material.Materials.Iridium
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
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.NetherQuartz
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Oilsands
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Pentlandite
import gregtech.api.unification.material.Materials.Pitchblende
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Pollucite
import gregtech.api.unification.material.Materials.Powellite
import gregtech.api.unification.material.Materials.Pyrite
import gregtech.api.unification.material.Materials.Pyrochlore
import gregtech.api.unification.material.Materials.Pyrolusite
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Realgar
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
import gregtech.api.unification.material.Materials.Sphalerite
import gregtech.api.unification.material.Materials.Spodumene
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Stibnite
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.Tantalite
import gregtech.api.unification.material.Materials.Tetrahedrite
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.TricalciumPhosphate
import gregtech.api.unification.material.Materials.Trona
import gregtech.api.unification.material.Materials.Tungstate
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Ultimet
import gregtech.api.unification.material.Materials.Uraninite
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.VanadiumMagnetite
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.Wulfenite
import gregtech.api.unification.material.Materials.YellowLimonite
import gregtech.api.unification.material.Materials.Zeolite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.ore
import gregtech.api.unification.ore.OrePrefix.oreEndstone
import gregtech.api.unification.ore.OrePrefix.oreNetherrack
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MINING_DRONE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aegirine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Albite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Anorthite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Augite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azurite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Baddeleyite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bytownite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Celestine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Clinochlore
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cryolite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cuprite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DenseHydrazineRocketFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dolomite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ferrosilite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Firestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorapatite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Forsterite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jade
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jasper
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kaolinite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Labradorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lizardite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylhydrazineNitrateRocketFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Muscovite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Nephelite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Oligoclase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Orpiment
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phlogopite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Picotite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Prasiolite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RP1RocketFuel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Strontianite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tanzanite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tenorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Wollastonite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_EV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_HV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_LuV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_MV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_ZPM

internal object MiningDroneAsteroidRecipeProducer
{

    // @formatter:off

    // region Mining Drone Fuels

    // LV-HV
    private val fuelBasic = arrayOf(
        LightFuel.getFluid(16000), HeavyFuel.getFluid(14000),
        Diesel.getFluid(12000), BioDiesel.getFluid(12000),
        Gasoline.getFluid(10000),
        CetaneBoostedDiesel.getFluid(8000),
        HighOctaneGasoline.getFluid(6000), RocketFuel.getFluid(6000), RP1RocketFuel.getFluid(6000),
        DenseHydrazineRocketFuel.getFluid(2000), MethylhydrazineNitrateRocketFuel.getFluid(2000))

    // MV-EV
    private val fuelAdvanced = arrayOf( // +8000
        // LightFuel.getFluid(24000), HeavyFuel.getFluid(22000),
        Diesel.getFluid(20000), BioDiesel.getFluid(20000),
        Gasoline.getFluid(18000),
        CetaneBoostedDiesel.getFluid(16000),
        HighOctaneGasoline.getFluid(14000), RocketFuel.getFluid(14000), RP1RocketFuel.getFluid(14000),
        DenseHydrazineRocketFuel.getFluid(10000), MethylhydrazineNitrateRocketFuel.getFluid(10000))

    // HV-IV
    private val fuelElite = arrayOf( // +8000
        // LightFuel.getFluid(32000), HeavyFuel.getFluid(30000),
        // Diesel.getFluid(28000), BioDiesel.getFluid(28000),
        Gasoline.getFluid(26000),
        CetaneBoostedDiesel.getFluid(24000),
        HighOctaneGasoline.getFluid(22000), RocketFuel.getFluid(22000), RP1RocketFuel.getFluid(22000),
        DenseHydrazineRocketFuel.getFluid(18000), MethylhydrazineNitrateRocketFuel.getFluid(18000))

    // EV-LuV
    private val fuelUltimate = arrayOf( // +8000
        // LightFuel.getFluid(40000), HeavyFuel.getFluid(38000),
        // Diesel.getFluid(36000), BioDiesel.getFluid(36000),
        // Gasoline.getFluid(34000),
        CetaneBoostedDiesel.getFluid(32000),
        HighOctaneGasoline.getFluid(30000), RocketFuel.getFluid(30000), RP1RocketFuel.getFluid(30000),
        DenseHydrazineRocketFuel.getFluid(26000), MethylhydrazineNitrateRocketFuel.getFluid(26000))

    // IV-ZPM
    private val fuelEpic = arrayOf( // +8000
        // LightFuel.getFluid(48000), HeavyFuel.getFluid(46000),
        // Diesel.getFluid(44000), BioDiesel.getFluid(44000),
        // Gasoline.getFluid(42000),
        // CetaneBoostedDiesel.getFluid(40000),
        HighOctaneGasoline.getFluid(38000), RocketFuel.getFluid(38000), RP1RocketFuel.getFluid(38000),
        DenseHydrazineRocketFuel.getFluid(32000), MethylhydrazineNitrateRocketFuel.getFluid(32000))

    // LuV-UV
    private val fuelLegendary = arrayOf( // +8000
        // LightFuel.getFluid(56000), HeavyFuel.getFluid(54000),
        // Diesel.getFluid(52000), BioDiesel.getFluid(52000),
        // Gasoline.getFluid(50000),
        // CetaneBoostedDiesel.getFluid(48000),
        // HighOctaneGasoline.getFluid(46000), RocketFuel.getFluid(46000), RP1RocketFuel.getFluid(46000),
        DenseHydrazineRocketFuel.getFluid(40000), MethylhydrazineNitrateRocketFuel.getFluid(40000))

    // endregion

    // region Asteroid Tier Configuration

    /**
     * Configuration for a single rank within an asteroid tier.
     *
     * Each asteroid tier (1-6) has 3 ranks, each rank defines drone, EUt, duration, chance and materials.
     *
     * @param droneTier     The tier for the mining drone, used `(tier + rank - 1)` as default.
     * @param voltageTier   The voltage tier in EUt usage for the asteroid mining recipes.
     * @param duration      The duration for the asteroid mining recipes.
     * @param baseChance    The base output chance.
     *                      - Rank 1: 3000 (30%)
     *                      - Rank 2: 6000 (60%)
     *                      - Rank 3: 9000 (90%)
     * @param multiplier    The output multiplier for the asteroid mining recipes (1x, 4x, 16x).
     * @param drillMaterial The drill material usage in the asteroid mining recipes.
     * @param stickMaterial The stick material usage in the asteroid mining recipes.
     * @param orePrefix     The ore variant for output ores for the asteroid mining recipes.
     */
    private data class AsteroidRank(val droneTier: Int, val voltageTier: Int,
                                    val duration: Int, val baseChance: Int, val multiplier: IntArray,
                                    val drillMaterial: Material, val stickMaterial: Material,
                                    val orePrefix: OrePrefix)
    {
        override fun equals(other: Any?): Boolean
        {
            if (this === other)
                return true
            if (javaClass != other?.javaClass)
                return false

            other as AsteroidRank

            if (droneTier != other.droneTier)
                return false
            if (voltageTier != other.voltageTier)
                return false
            if (duration != other.duration)
                return false
            if (baseChance != other.baseChance)
                return false
            if (!multiplier.contentEquals(other.multiplier))
                return false
            if (drillMaterial != other.drillMaterial)
                return false
            if (stickMaterial != other.stickMaterial)
                return false
            if (orePrefix != other.orePrefix)
                return false

            return true
        }

        override fun hashCode(): Int
        {
            var result = droneTier
            result = 31 * result + voltageTier
            result = 31 * result + duration
            result = 31 * result + baseChance
            result = 31 * result + multiplier.contentHashCode()
            result = 31 * result + drillMaterial.hashCode()
            result = 31 * result + stickMaterial.hashCode()
            result = 31 * result + orePrefix.hashCode()
            return result
        }
    }

    /**
     * Variant configurations for 1x, 4x, and 16x stack sizes.
     *
     * Each variant specifies the stack size and corresponding drone form.
     *
     * @param count      The count of mining drone usage in the asteroid mining recipes (1, 4, 16).
     * @param multiplier The output multiplier for the asteroid mining recipes (1x, 4x, 16x).
     */
    private data class DroneVariant(val count: Int, val multiplier: Int)

    private val droneTiers = arrayOf(MINING_DRONE_LV, MINING_DRONE_MV, MINING_DRONE_HV, MINING_DRONE_EV,
        MINING_DRONE_IV, MINING_DRONE_LuV, MINING_DRONE_ZPM, MINING_DRONE_UV)

    private val variants = arrayOf(DroneVariant(1, 1), DroneVariant(4, 4), DroneVariant(16, 16))

    /**
     * | Rank Tier | 1x | 4x | 16x |
     * |-----------|----|----|-----|
     * | Rank 0    | 1  | 4  | 16  |
     * | Rank 1    | 4  | 16 | 64  |
     * | Rank 2    | 16 | 64 | 256 |
     */
    private val multipliers = arrayOf(intArrayOf(1, 4, 16), intArrayOf(4, 16, 64), intArrayOf(16, 64, 256))

    /**
     * | Drone Tier         | 1x  | 4x  | 16x |
     * |--------------------|-----|-----|-----|
     * | Tier 1 (Basic)     | LV  | MV  | HV  |
     * | Tier 2 (Advanced)  | MV  | HV  | EV  |
     * | Tier 3 (Elite)     | HV  | EV  | IV  |
     * | Tier 4 (Ultimate)  | EV  | IV  | LuV |
     * | Tier 5 (Epic)      | IV  | LuV | ZPM |
     * | Tier 6 (Legendary) | LuV | ZPM | UV  |
     */
    private val asteroidTiers = arrayOf(
        // Tier 1 (Basic) LV-HV
        arrayOf(
            AsteroidRank(1, 1, 2 * MINUTE, 3000, multipliers[0], Iron, Bronze, ore),
            AsteroidRank(2, 2, 1 * MINUTE, 6000, multipliers[1], WroughtIron, Steel, ore),
            AsteroidRank(3, 3, 30 * SECOND, 9000, multipliers[2], Aluminium, CobaltBrass, ore)),

        // Tier 2 (Advanced) MV-EV
        arrayOf(
            AsteroidRank(2, 2, 2 * MINUTE, 3000, multipliers[0], WroughtIron, Steel, ore),
            AsteroidRank(3, 3, 1 * MINUTE, 6000, multipliers[1], Aluminium, CobaltBrass, ore),
            AsteroidRank(4, 4, 30 * SECOND, 9000, multipliers[2], VanadiumSteel, BlueSteel, oreNetherrack)),

        // Tier 3 (Elite) HV-IV
        arrayOf(
            AsteroidRank(3, 3, 2 * MINUTE, 3000, multipliers[0], Aluminium, CobaltBrass, ore),
            AsteroidRank(4, 4, 1 * MINUTE, 6000, multipliers[1], VanadiumSteel, BlueSteel, oreNetherrack),
            AsteroidRank(5, 5, 30 * SECOND, 9000, multipliers[2], Titanium, Ultimet, oreNetherrack)),

        // Tier 4 (Ultimate) EV-LuV
        arrayOf(
            AsteroidRank(4, 4, 2 * MINUTE, 3000, multipliers[0], VanadiumSteel, BlueSteel, oreNetherrack),
            AsteroidRank(5, 5, 1 * MINUTE, 6000, multipliers[1], Titanium, Ultimet, oreNetherrack),
            AsteroidRank(6, 6, 30 * SECOND, 9000, multipliers[2], TungstenSteel, Naquadah, oreEndstone)),

        // Tier 5 (Epic) IV-ZPM
        arrayOf(
            AsteroidRank(5, 5, 2 * MINUTE, 3000, multipliers[0], Titanium, Ultimet, oreNetherrack),
            AsteroidRank(6, 6, 1 * MINUTE, 6000, multipliers[1], TungstenSteel, Naquadah, oreEndstone),
            AsteroidRank(7, 7, 30 * SECOND, 9000, multipliers[2], Iridium, HSSE, oreEndstone)),

        // Tier 6 (Legendary) LuV-UV
        arrayOf(
            AsteroidRank(6, 6, 2 * MINUTE, 3000, multipliers[0], TungstenSteel, Naquadah, oreEndstone),
            AsteroidRank(7, 7, 1 * MINUTE, 6000, multipliers[1], Iridium, HSSE, oreEndstone),
            AsteroidRank(8, 8, 30 * SECOND, 9000, multipliers[2], Duranium, Neutronium, oreEndstone)))

    // Fuel arrays indexed by asteroid tier (1-6)
    private val tierFuels = arrayOf(null, fuelBasic, fuelAdvanced, fuelElite, fuelUltimate, fuelEpic, fuelLegendary)

    // endregion

    fun produce()
    {
        // region Basic Asteroids (6) LV-HV

        // Carbon Group Asteroid (Coal-Lignite-Graphite-Diamond)
        addAsteroid(1, 0, Coal, Lignite, Graphite, Diamond)
        // Iron Asteroid (Iron-BandedIron-BrownLimonite-YellowLimonite-Pyrite-Magnetite-VanadiumMagnetite-Gold-BasalticMineralSand-GraniticMineralSand)
        addAsteroid(1, 1, Iron, BandedIron, BrownLimonite, YellowLimonite, Pyrite, Magnetite, VanadiumMagnetite, Gold, BasalticMineralSand, GraniticMineralSand)
        // Copper Asteroid (Copper-Chalcopyrite-Malachite-Cuprite-Azurite-Tenorite-Bornite-Tetrahedrite)
        addAsteroid(1, 2, Copper, Chalcopyrite, Chalcocite, Malachite, Cuprite, Azurite, Tenorite, Bornite, Tetrahedrite)
        // Tin-Lead Asteroid (Cassiterite-CassiteriteSand-Tin-Lead-Galena-Silver-Stibnite-Zeolite-Realgar-FullersEarth-Gypsum)
        addAsteroid(1, 3, Cassiterite, CassiteriteSand, Tin, Lead, Galena, Silver, Stibnite, Zeolite, Realgar, FullersEarth, Gypsum)
        // Salt-Sulfur Asteroid (Salt-RockSalt-Saltpeter-Sulfur-Sphalerite-Lepidolite-Spodumene-Asbestos-Diatomite-OilSands)
        addAsteroid(1, 4, Salt, RockSalt, Saltpeter, Sulfur, Sphalerite, Lepidolite, Spodumene, Asbestos, Diatomite, Oilsands)
        // Misc Gems Asteroid (Albite-Oligoclase-Prasiolite-Labradorite-Bytownite-Firestone-Cinnabar)
        addAsteroid(1, 5, Albite, Oligoclase, Prasiolite, Labradorite, Bytownite, Firestone, Cinnabar)

        // endregion

        // region Advanced Asteroids (8) MV-EV

        // Mica Asteroid (Mica-Kyanite-Bauxite-Pollucite-Muscovite-Phlogopite-Biotite-Lepidolite)
        addAsteroid(2, 6, Mica, Kyanite, Bauxite, Pollucite, Muscovite, Phlogopite, Biotite, Lepidolite)
        // Kaolinite-Dolomite Asteroid (Kaolinite-Bentonite-Augite-Ferrosilite-Spodumene-Dolomite-Wollastonite-Trona)
        addAsteroid(2, 7, Kaolinite, Bentonite, Augite, Ferrosilite, Spodumene, Dolomite, Wollastonite, Trona)
        // Nickel-Cobalt Asteroid (Nickel-Garnierite-Cobalt-Cobaltite-Pyrite-Pentlandite-Soapstone-Talc-GlauconiteSand)
        addAsteroid(2, 8, Nickel, Garnierite, Cobalt, Cobaltite, Pyrite, Pentlandite, Soapstone, Talc, GlauconiteSand)
        // Manganese-Tantalum Asteroid (Pyrolusite-Tantalite-Pyrochlore-Grossular-Spessartine-Forsterite)
        addAsteroid(2, 9, Pyrolusite, Tantalite, Pyrochlore, Grossular, Spessartine, Forsterite)
        // Redstone-Garnet Asteroid (Redstone-Ruby-Realgar-Orpiment-GarnetRed-GarnetYellow-Amethyst-Opal)
        addAsteroid(2, 10, Redstone, Ruby, Realgar, Orpiment, GarnetRed, GarnetYellow, Amethyst, Opal)
        // Various Gems Asteroid (Diamond-Ruby-Emerald-Olivine-Sapphire-GreenSapphire-Lapis-Lazurite-Sodalite-CertusQuartz-Topaz-BlueTopaz-Amethyst-Opal)
        addAsteroid(2, 11, Diamond, Ruby, Emerald, Olivine, Sapphire, GreenSapphire, Lapis, Lazurite, Sodalite, CertusQuartz, Topaz, BlueTopaz, Amethyst, Opal)
        // Various Gems Asteroid (Almandine-Andradite-Grossular-Pyrope-Spessartine-Uvarovite-Tanzanite-Quartzite-Realgar-Orpiment-Apatite-GarnetRed-GarnetYellow-Zircon)
        addAsteroid(2, 12, Almandine, Andradite, Grossular, Pyrope, Spessartine, Uvarovite, Tanzanite, Quartzite, Realgar, Orpiment, Apatite, GarnetRed, GarnetYellow, Zircon)
        // Aluminium-Chrome Asteroid (Aluminium-Bauxite-Ilmenite-Cryolite-Chromite-Picotite-Jade-Jasper-Anorthite-NetherQuartz-Barite)
        addAsteroid(2, 13, Aluminium, Bauxite, Ilmenite, Cryolite, Chromite, Picotite, Jade, Jasper, Anorthite, NetherQuartz, Barite)

        // endregion

        // region Elite Asteroids (5) HV-IV

        // Platinum Group Asteroid (Platinum-Palladium-Cooperite-Chalcopyrite-Chalcocite-Bornite-Tetrahedrite-Pentlandite-Azurite-Tenorite-Cuprite)
        addAsteroid(3, 14, Platinum, Palladium, Cooperite, Chalcopyrite, Chalcocite, Bornite, Tetrahedrite, Pentlandite, Azurite, Tenorite, Cuprite)
        // Molybdenum-Tungsten Asteroid (Molybdenum-Molybdenite-Powellite-Wulfenite-Scheelite-Tungstate-Lithium)
        addAsteroid(3, 15, Molybdenum, Molybdenite, Powellite, Wulfenite, Scheelite, Tungstate, Lithium)
        // Zircon Asteroid (Zircon-Baddeleyite-Nephelite-Aegirine-Lizardite-Calcite-Uvarovite-Clinochlore-Magnesite)
        addAsteroid(3, 16, Zircon, Baddeleyite, Nephelite, Aegirine, Lizardite, Calcite, Uvarovite, Clinochlore, Magnesite)
        // Radioactive Asteroid (Naquadah-Pitchblende-Uraninite-Thorium-Plutonium-239-Beryllium-Saltpeter-Diatomite-Electrotine-Alunite)
        addAsteroid(3, 17, Naquadah, Pitchblende, Uraninite, Thorium, Plutonium239, Beryllium, Saltpeter, Diatomite, Electrotine, Alunite)
        // Strontium Asteroid (Celestine-Strontianite-Calcite-Quartzite-GarnetSand)
        addAsteroid(3, 18, Celestine, Strontianite, Calcite, Calcite, Quartzite, Quartzite, GarnetSand)

        // endregion

        // region Ultimate Asteroids (4) EV-LuV

        // Rare Earth Asteroid (Neodymium-Monazite-Bastnasite-Fluorite-Apatite-Fluorapatite-TricalciumPhosphate)
        addAsteroid(4, 19, Neodymium, Monazite, Bastnasite, Fluorite, Apatite, Fluorapatite, TricalciumPhosphate)
        // Various Advanced Metal Asteroid (Chrome-Molybdenum-Cadmium-Platinum-Palladium)
        addAsteroid(4, 20, Chrome, Chrome, Molybdenum, Molybdenum, Cadmium, Cadmium, Platinum, Platinum, Palladium, Palladium)
        // Various Radioactive Metal Asteroid (Uranium-Thorium-Plutonium239-Plutonium241)
        addAsteroid(4, 21, Uranium, Uranium, Thorium, Thorium, Plutonium239, Plutonium239, Plutonium241, Plutonium241)
        // Various Vanilla Ores Asteroid (Iron-Gold-Silver-Lead-Nickel-Diamond-Redstone-Emerald)
        addAsteroid(4, 22, Iron, Iron, Gold, Gold, Silver, Silver, Lead, Lead, Nickel, Nickel, Diamond, Diamond, Redstone, Redstone, Emerald, Emerald)

        // endregion

        // TODO: Epic Asteroid (.) IV-ZPM

        // TODO: Legendary Asteroid (.) LuV-UV, Rhenium? Advanced Platinum Group Asteroid?

    }

    /**
     * Add an asteroid to mining drone airport.
     *
     * It will add some grouped recipes, each asteroid has a tier which means its lowest mining drone tier requirement,
     * and it will use its [tier] and ([tier]+1,[tier]+2) mining drone in recipes.
     *
     * @param tier        The tier of asteroid, 1 means basic, 2 means advanced, e.t.c. For these tiers, basic used LV-HV
     *                    mining drone, advanced used MV-HV mining drones, e.t.c.
     * @param circuitMeta Used to declared asteroid types, different asteroids used different value.
     * @param outputs     Output ores, i.e. the components of this asteroid.
     *
     * @throws IllegalArgumentException  When [tier] is an incorrect value.
     * @throws IndexOutOfBoundsException When [circuitMeta] is out bounds of int circuit values (<0 or >32).
     * @throws IndexOutOfBoundsException When [outputs] is out bounds of recipe output slots (>16).
     */
    private fun addAsteroid(tier: Int, circuitMeta: Int, vararg outputs: Material)
    {
        if (!arrayOf(1, 2, 3, 4, 5, 6).contains(tier))
            throw IllegalArgumentException("Incorrect tier value!")
        if (circuitMeta < 0 || circuitMeta > 32)
            throw IndexOutOfBoundsException("Integrated Circuit Number cannot be less than 0 and more than 32")
        if (outputs.size > 16)
            throw IndexOutOfBoundsException("Asteroid cannot has more than 16 components")

        val rankConfigs = asteroidTiers[tier - 1]
        val fuels = tierFuels[tier]

        for (fuel in fuels!!)
        {
            for ((_, rankConfig) in rankConfigs.withIndex())
            {
                val droneTierIndex = rankConfig.droneTier
                val droneItem = droneTiers[droneTierIndex - 1]

                for ((variantIndex, variant) in variants.withIndex())
                {
                    val multiplier = rankConfig.multiplier[variantIndex]
                    val droneStackForm = if (variant.count == 1) droneItem.stack() else droneItem.getStack(variant.count)

                    val builder = MINING_DRONE_RECIPES.recipeBuilder()
                        .circuitMeta(circuitMeta)
                        .notConsumable(droneStackForm)
                        .notConsumable(toolHeadDrill, rankConfig.drillMaterial, 4)
                        .notConsumable(stick, rankConfig.stickMaterial, 4)
                        .fluidInputs(fuel)

                    for (output in outputs)
                        builder.chancedOutput(rankConfig.orePrefix, output, multiplier, rankConfig.baseChance, 0)

                    builder.EUt(VA[rankConfig.voltageTier].toLong())
                        .duration(rankConfig.duration)
                        .buildAndRegister()
                }
            }
        }
    }

    // @formatter:on

}
