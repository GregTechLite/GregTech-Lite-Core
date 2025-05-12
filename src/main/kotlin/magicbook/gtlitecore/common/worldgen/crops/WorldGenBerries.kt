package magicbook.gtlitecore.common.worldgen.crops

import magicbook.gtlitecore.common.block.blocks.GTLiteBerryBushVariantBlock
import magicbook.gtlitecore.common.worldgen.features.TemperatureRainfallCondition

class WorldGenBerries
{

    companion object
    {
        val BUSH_BLUEBERRY = GTLiteBerryBushVariantBlock.create("blueberry")
        val BUSH_BLACKBERRY = GTLiteBerryBushVariantBlock.create("blackberry")
        val BUSH_RASPBERRY = GTLiteBerryBushVariantBlock.create("raspberry")
        val BUSH_STRAWBERRY = GTLiteBerryBushVariantBlock.create("strawberry")
        val BUSH_RED_CURRANT = GTLiteBerryBushVariantBlock.create("red_currant")
        val BUSH_BLACK_CURRANT = GTLiteBerryBushVariantBlock.create("black_currant")
        val BUSH_WHITE_CURRANT = GTLiteBerryBushVariantBlock.create("white_currant")
        val BUSH_LINGONBERRY = GTLiteBerryBushVariantBlock.create("lingonberry")
        val BUSH_ELDERBERRY = GTLiteBerryBushVariantBlock.create("elderberry")
        val BUSH_CRANBERRY = GTLiteBerryBushVariantBlock.create("cranberry")

        val BLUEBERRY_BUSH = WorldGenBerry(1000, BUSH_BLUEBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.7, 0.5, 0.5))
        val BLACKBERRY_BUSH = WorldGenBerry(1001, BUSH_BLACKBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.5, 0.4, 0.5))
        val RASPBERRY_BUSH = WorldGenBerry(1002, BUSH_RASPBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.5, 0.5, 0.4))
        val STRAWBERRY_BUSH = WorldGenBerry(1003, BUSH_STRAWBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.7, 0.8, 0.5))
        val RED_CURRANT_BUSH = WorldGenBerry(1004, BUSH_RED_CURRANT)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5))
        val BLACK_CURRANT_BUSH = WorldGenBerry(1005, BUSH_BLACK_CURRANT)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5))
        val WHITE_CURRANT_BUSH = WorldGenBerry(1006, BUSH_WHITE_CURRANT)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5))
        val LINGONBERRY_BUSH = WorldGenBerry(1007, BUSH_LINGONBERRY)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.25, 0.7, 0.6))
        val ELDERBERRY_BUSH = WorldGenBerry(1008, BUSH_ELDERBERRY)
            .addCondition(TemperatureRainfallCondition(3, 0.9, 0.2, 0.4, 0.6))
        val CRANBERRY_BUSH = WorldGenBerry(1009, BUSH_CRANBERRY)
            .addCondition(TemperatureRainfallCondition(3, 1.2, 0.2, 0.4, 0.6))

        @JvmStatic
        fun init()
        {
            BUSH_BLACKBERRY.setThorny(true)
            BUSH_RASPBERRY.setThorny(true)
        }

    }

}