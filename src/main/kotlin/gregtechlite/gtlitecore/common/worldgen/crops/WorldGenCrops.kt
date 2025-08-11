package gregtechlite.gtlitecore.common.worldgen.crops

import gregtechlite.gtlitecore.common.block.base.GTLiteCropVariantBlock
import gregtechlite.gtlitecore.common.block.base.GTLiteRootCropVariantBlock

object WorldGenCrops
{

    lateinit var COFFEE_CROP: GTLiteCropVariantBlock
    lateinit var TOMATO_CROP: GTLiteCropVariantBlock
    lateinit var ONION_CROP: GTLiteRootCropVariantBlock
    lateinit var CUCUMBER_CROP: GTLiteCropVariantBlock
    lateinit var GRAPE_CROP: GTLiteCropVariantBlock
    lateinit var SOY_CROP: GTLiteCropVariantBlock
    lateinit var BEAN_CROP: GTLiteCropVariantBlock
    lateinit var PEA_CROP: GTLiteCropVariantBlock
    lateinit var OREGANO_CROP: GTLiteCropVariantBlock
    lateinit var HORSERADISH_CROP: GTLiteRootCropVariantBlock
    lateinit var GARLIC_CROP: GTLiteCropVariantBlock
    lateinit var BASIL_CROP: GTLiteCropVariantBlock
    lateinit var AUBERGINE_CROP: GTLiteCropVariantBlock
    lateinit var CORN_CROP: GTLiteCropVariantBlock
    lateinit var ARTICHOKE_CROP: GTLiteCropVariantBlock
    lateinit var BLACK_PEPPER_CROP: GTLiteCropVariantBlock
    lateinit var RICE_CROP: GTLiteCropVariantBlock
    lateinit var WHITE_GRAPE_CROP: GTLiteCropVariantBlock
    lateinit var COTTON_CROP: GTLiteCropVariantBlock

    internal fun init()
    {
        COFFEE_CROP = GTLiteCropVariantBlock.create("coffee")
        TOMATO_CROP = GTLiteCropVariantBlock.create("tomato")
        ONION_CROP = GTLiteRootCropVariantBlock.create("onion")
        CUCUMBER_CROP = GTLiteCropVariantBlock.create("cucumber")
        GRAPE_CROP = GTLiteCropVariantBlock.create("grape")
        SOY_CROP = GTLiteCropVariantBlock.create("soy")
        BEAN_CROP = GTLiteCropVariantBlock.create("bean")
        PEA_CROP = GTLiteCropVariantBlock.create("pea")
        OREGANO_CROP = GTLiteCropVariantBlock.create("oregano")
        HORSERADISH_CROP = GTLiteRootCropVariantBlock.create("horseradish")
        GARLIC_CROP = GTLiteCropVariantBlock.create("garlic")
        BASIL_CROP = GTLiteCropVariantBlock.create("basil")
        AUBERGINE_CROP = GTLiteCropVariantBlock.create("aubergine")
        CORN_CROP = GTLiteCropVariantBlock.create("corn")
        ARTICHOKE_CROP = GTLiteCropVariantBlock.create("artichoke")
        BLACK_PEPPER_CROP = GTLiteCropVariantBlock.create("black_pepper")
        RICE_CROP = GTLiteCropVariantBlock.create("rice")
        WHITE_GRAPE_CROP = GTLiteCropVariantBlock.create("white_grape")
        COTTON_CROP = GTLiteCropVariantBlock.create("cotton")
    }

}