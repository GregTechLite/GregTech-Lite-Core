package gregtechlite.gtlitecore.common.worldgen.generator.plant

import gregtechlite.gtlitecore.common.block.GTLiteCropBlock
import gregtechlite.gtlitecore.common.block.GTLiteRootCropBlock

object WorldGeneratorCropManager
{

    lateinit var COFFEE_CROP: GTLiteCropBlock
    lateinit var TOMATO_CROP: GTLiteCropBlock
    lateinit var ONION_CROP: GTLiteCropBlock
    lateinit var CUCUMBER_CROP: GTLiteCropBlock
    lateinit var GRAPE_CROP: GTLiteCropBlock
    lateinit var SOY_CROP: GTLiteCropBlock
    lateinit var BEAN_CROP: GTLiteCropBlock
    lateinit var PEA_CROP: GTLiteCropBlock
    lateinit var OREGANO_CROP: GTLiteCropBlock
    lateinit var HORSERADISH_CROP: GTLiteCropBlock
    lateinit var GARLIC_CROP: GTLiteCropBlock
    lateinit var BASIL_CROP: GTLiteCropBlock
    lateinit var AUBERGINE_CROP: GTLiteCropBlock
    lateinit var CORN_CROP: GTLiteCropBlock
    lateinit var ARTICHOKE_CROP: GTLiteCropBlock
    lateinit var BLACK_PEPPER_CROP: GTLiteCropBlock
    lateinit var RICE_CROP: GTLiteCropBlock
    lateinit var WHITE_GRAPE_CROP: GTLiteCropBlock
    lateinit var COTTON_CROP: GTLiteCropBlock

    internal fun init()
    {
        COFFEE_CROP = GTLiteCropBlock.create("coffee")
        TOMATO_CROP = GTLiteCropBlock.create("tomato")
        ONION_CROP = GTLiteRootCropBlock.create("onion")
        CUCUMBER_CROP = GTLiteCropBlock.create("cucumber")
        GRAPE_CROP = GTLiteCropBlock.create("grape")
        SOY_CROP = GTLiteCropBlock.create("soy")
        BEAN_CROP = GTLiteCropBlock.create("bean")
        PEA_CROP = GTLiteCropBlock.create("pea")
        OREGANO_CROP = GTLiteCropBlock.create("oregano")
        HORSERADISH_CROP = GTLiteRootCropBlock.create("horseradish")
        GARLIC_CROP = GTLiteCropBlock.create("garlic")
        BASIL_CROP = GTLiteCropBlock.create("basil")
        AUBERGINE_CROP = GTLiteCropBlock.create("aubergine")
        CORN_CROP = GTLiteCropBlock.create("corn")
        ARTICHOKE_CROP = GTLiteCropBlock.create("artichoke")
        BLACK_PEPPER_CROP = GTLiteCropBlock.create("black_pepper")
        RICE_CROP = GTLiteCropBlock.create("rice")
        WHITE_GRAPE_CROP = GTLiteCropBlock.create("white_grape")
        COTTON_CROP = GTLiteCropBlock.create("cotton")
    }

}