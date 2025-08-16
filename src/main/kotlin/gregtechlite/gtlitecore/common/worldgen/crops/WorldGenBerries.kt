package gregtechlite.gtlitecore.common.worldgen.crops

import gregtechlite.gtlitecore.common.block.GTLiteBerryBushBlock
import gregtechlite.gtlitecore.common.worldgen.features.TemperatureRainfallCondition

object WorldGenBerries
{

    lateinit var BUSH_BLUEBERRY: GTLiteBerryBushBlock
    lateinit var BUSH_BLACKBERRY: GTLiteBerryBushBlock
    lateinit var BUSH_RASPBERRY: GTLiteBerryBushBlock
    lateinit var BUSH_STRAWBERRY: GTLiteBerryBushBlock
    lateinit var BUSH_RED_CURRANT: GTLiteBerryBushBlock
    lateinit var BUSH_BLACK_CURRANT: GTLiteBerryBushBlock
    lateinit var BUSH_WHITE_CURRANT: GTLiteBerryBushBlock
    lateinit var BUSH_LINGONBERRY: GTLiteBerryBushBlock
    lateinit var BUSH_ELDERBERRY: GTLiteBerryBushBlock
    lateinit var BUSH_CRANBERRY: GTLiteBerryBushBlock

    lateinit var BLUEBERRY_BUSH: WorldGenBerry
    lateinit var BLACKBERRY_BUSH: WorldGenBerry
    lateinit var RASPBERRY_BUSH: WorldGenBerry
    lateinit var STRAWBERRY_BUSH: WorldGenBerry
    lateinit var RED_CURRANT_BUSH: WorldGenBerry
    lateinit var BLACK_CURRANT_BUSH: WorldGenBerry
    lateinit var WHITE_CURRANT_BUSH: WorldGenBerry
    lateinit var LINGONBERRY_BUSH: WorldGenBerry
    lateinit var ELDERBERRY_BUSH: WorldGenBerry
    lateinit var CRANBERRY_BUSH: WorldGenBerry

    internal fun init()
    {
        // Blocks
        BUSH_BLUEBERRY = GTLiteBerryBushBlock.create("blueberry")
        BUSH_BLACKBERRY = GTLiteBerryBushBlock.create("blackberry").setThorny(true)
        BUSH_RASPBERRY = GTLiteBerryBushBlock.create("raspberry").setThorny(true)
        BUSH_STRAWBERRY = GTLiteBerryBushBlock.create("strawberry")
        BUSH_RED_CURRANT = GTLiteBerryBushBlock.create("red_currant")
        BUSH_BLACK_CURRANT = GTLiteBerryBushBlock.create("black_currant")
        BUSH_WHITE_CURRANT = GTLiteBerryBushBlock.create("white_currant")
        BUSH_LINGONBERRY = GTLiteBerryBushBlock.create("lingonberry")
        BUSH_ELDERBERRY = GTLiteBerryBushBlock.create("elderberry")
        BUSH_CRANBERRY = GTLiteBerryBushBlock.create("cranberry")

        // Worldgens
        BLUEBERRY_BUSH = WorldGenBerry(1000, BUSH_BLUEBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.7, 0.5, 0.5))
        BLACKBERRY_BUSH = WorldGenBerry(1001, BUSH_BLACKBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.5, 0.4, 0.5))
        RASPBERRY_BUSH = WorldGenBerry(1002, BUSH_RASPBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.5, 0.5, 0.4))
        STRAWBERRY_BUSH = WorldGenBerry(1003, BUSH_STRAWBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.7, 0.8, 0.5))
        RED_CURRANT_BUSH = WorldGenBerry(1004, BUSH_RED_CURRANT)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5))
        BLACK_CURRANT_BUSH = WorldGenBerry(1005, BUSH_BLACK_CURRANT)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5))
        WHITE_CURRANT_BUSH = WorldGenBerry(1006, BUSH_WHITE_CURRANT)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5))
        LINGONBERRY_BUSH = WorldGenBerry(1007, BUSH_LINGONBERRY)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.25, 0.7, 0.6))
        ELDERBERRY_BUSH = WorldGenBerry(1008, BUSH_ELDERBERRY)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.2, 0.4, 0.6))
        CRANBERRY_BUSH = WorldGenBerry(1009, BUSH_CRANBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.2, 0.4, 0.6))
    }

}