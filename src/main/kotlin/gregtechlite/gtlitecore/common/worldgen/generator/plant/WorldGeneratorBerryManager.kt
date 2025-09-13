package gregtechlite.gtlitecore.common.worldgen.generator.plant

import gregtechlite.gtlitecore.api.worldgen.condition.ClimateCondition
import gregtechlite.gtlitecore.common.block.GTLiteBerryBushBlock

object WorldGeneratorBerryManager
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

    lateinit var BLUEBERRY_BUSH: WorldGeneratorBerryBase
    lateinit var BLACKBERRY_BUSH: WorldGeneratorBerryBase
    lateinit var RASPBERRY_BUSH: WorldGeneratorBerryBase
    lateinit var STRAWBERRY_BUSH: WorldGeneratorBerryBase
    lateinit var RED_CURRANT_BUSH: WorldGeneratorBerryBase
    lateinit var BLACK_CURRANT_BUSH: WorldGeneratorBerryBase
    lateinit var WHITE_CURRANT_BUSH: WorldGeneratorBerryBase
    lateinit var LINGONBERRY_BUSH: WorldGeneratorBerryBase
    lateinit var ELDERBERRY_BUSH: WorldGeneratorBerryBase
    lateinit var CRANBERRY_BUSH: WorldGeneratorBerryBase

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
        BLUEBERRY_BUSH = WorldGeneratorBerryBase(1000, BUSH_BLUEBERRY)
            .addCondition(ClimateCondition(3, 1.2, 0.7, 0.5, 0.5))
        BLACKBERRY_BUSH = WorldGeneratorBerryBase(1001, BUSH_BLACKBERRY)
            .addCondition(ClimateCondition(3, 1.2, 0.5, 0.4, 0.5))
        RASPBERRY_BUSH = WorldGeneratorBerryBase(1002, BUSH_RASPBERRY)
            .addCondition(ClimateCondition(3, 1.2, 0.5, 0.5, 0.4))
        STRAWBERRY_BUSH = WorldGeneratorBerryBase(1003, BUSH_STRAWBERRY)
            .addCondition(ClimateCondition(3, 1.2, 0.7, 0.8, 0.5))
        RED_CURRANT_BUSH = WorldGeneratorBerryBase(1004, BUSH_RED_CURRANT)
            .addCondition(ClimateCondition(3, 0.9, 0.3, 0.75, 0.5))
        BLACK_CURRANT_BUSH = WorldGeneratorBerryBase(1005, BUSH_BLACK_CURRANT)
            .addCondition(ClimateCondition(3, 0.9, 0.3, 0.75, 0.5))
        WHITE_CURRANT_BUSH = WorldGeneratorBerryBase(1006, BUSH_WHITE_CURRANT)
            .addCondition(ClimateCondition(3, 0.9, 0.3, 0.75, 0.5))
        LINGONBERRY_BUSH = WorldGeneratorBerryBase(1007, BUSH_LINGONBERRY)
            .addCondition(ClimateCondition(3, 0.9, 0.25, 0.7, 0.6))
        ELDERBERRY_BUSH = WorldGeneratorBerryBase(1008, BUSH_ELDERBERRY)
            .addCondition(ClimateCondition(3, 0.9, 0.2, 0.4, 0.6))
        CRANBERRY_BUSH = WorldGeneratorBerryBase(1009, BUSH_CRANBERRY)
            .addCondition(ClimateCondition(3, 1.2, 0.2, 0.4, 0.6))
    }

}