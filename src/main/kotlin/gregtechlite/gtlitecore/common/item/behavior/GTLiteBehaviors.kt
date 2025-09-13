package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.util.RandomPotionEffect
import gregtech.common.creativetab.GTCreativeTabs
import gregtech.common.items.MetaItems
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import gregtechlite.gtlitecore.common.worldgen.generator.plant.WorldGeneratorBerryManager
import gregtechlite.gtlitecore.common.worldgen.generator.plant.WorldGeneratorCropManager
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemStack

object GTLiteBehaviors
{

    fun addBehaviors()
    {

        // region Crop Seeds

        GTLiteMetaItems.COFFEE_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.COFFEE_CROP,
            GTLiteMetaItems.COFFEE_SEED, GTLiteMetaItems.COFFEE_CHERRY))

        GTLiteMetaItems.TOMATO_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.TOMATO_CROP,
            GTLiteMetaItems.TOMATO_SEED, GTLiteMetaItems.TOMATO))

        GTLiteMetaItems.ONION_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.ONION_CROP,
            GTLiteMetaItems.ONION_SEED, GTLiteMetaItems.ONION))

        GTLiteMetaItems.CUCUMBER_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.CUCUMBER_CROP,
            GTLiteMetaItems.CUCUMBER_SEED, GTLiteMetaItems.CUCUMBER))

        GTLiteMetaItems.GRAPE_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.GRAPE_CROP,
            GTLiteMetaItems.GRAPE_SEED, GTLiteMetaItems.GRAPE))

        GTLiteMetaItems.SOY_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.SOY_CROP,
            GTLiteMetaItems.SOY_SEED, GTLiteMetaItems.SOYBEAN))

        GTLiteMetaItems.BEAN_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.BEAN_CROP,
            GTLiteMetaItems.BEAN_SEED, GTLiteMetaItems.BEAN))

        GTLiteMetaItems.PEA_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.PEA_CROP,
            GTLiteMetaItems.PEA_SEED, GTLiteMetaItems.PEA))

        GTLiteMetaItems.OREGANO_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.OREGANO_CROP,
            GTLiteMetaItems.OREGANO_SEED, GTLiteMetaItems.OREGANO))

        GTLiteMetaItems.HORSERADISH_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.HORSERADISH_CROP,
            GTLiteMetaItems.HORSERADISH_SEED, GTLiteMetaItems.HORSERADISH))

        GTLiteMetaItems.GARLIC_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.GARLIC_CROP,
            GTLiteMetaItems.GARLIC_SEED, GTLiteMetaItems.GARLIC_BULB))

        GTLiteMetaItems.BASIL_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.BASIL_CROP,
            GTLiteMetaItems.BASIL_SEED, GTLiteMetaItems.BASIL))

        GTLiteMetaItems.AUBERGINE_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.AUBERGINE_CROP,
            GTLiteMetaItems.AUBERGINE_SEED, GTLiteMetaItems.AUBERGINE))

        GTLiteMetaItems.CORN_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.CORN_CROP,
            GTLiteMetaItems.CORN_SEED, GTLiteMetaItems.CORN))

        GTLiteMetaItems.ARTICHOKE_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.ARTICHOKE_CROP,
            GTLiteMetaItems.ARTICHOKE_SEED, GTLiteMetaItems.ARTICHOKE))

        GTLiteMetaItems.BLACK_PEPPER_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.BLACK_PEPPER_CROP,
            GTLiteMetaItems.BLACK_PEPPER_SEED, GTLiteMetaItems.BLACK_PEPPER))

        GTLiteMetaItems.RICE_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.RICE_CROP,
            GTLiteMetaItems.RICE_SEED, GTLiteMetaItems.RICE))

        GTLiteMetaItems.WHITE_GRAPE_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.WHITE_GRAPE_CROP,
            GTLiteMetaItems.WHITE_GRAPE_SEED, GTLiteMetaItems.WHITE_GRAPE))

        GTLiteMetaItems.COTTON_SEED.addComponents(CropSeedBehavior(WorldGeneratorCropManager.COTTON_CROP,
            GTLiteMetaItems.COTTON_SEED, GTLiteMetaItems.COTTON))

        GTLiteMetaItems.BLUEBERRY.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_BLUEBERRY,
            GTLiteMetaItems.BLUEBERRY, GTLiteMetaItems.BLUEBERRY))

        GTLiteMetaItems.BLACKBERRY.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_BLACKBERRY,
            GTLiteMetaItems.BLACKBERRY, GTLiteMetaItems.BLACKBERRY))

        GTLiteMetaItems.RASPBERRY.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_RASPBERRY,
            GTLiteMetaItems.RASPBERRY, GTLiteMetaItems.RASPBERRY))

        GTLiteMetaItems.STRAWBERRY.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_STRAWBERRY,
            GTLiteMetaItems.STRAWBERRY, GTLiteMetaItems.STRAWBERRY))

        GTLiteMetaItems.RED_CURRANT.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_RED_CURRANT,
            GTLiteMetaItems.RED_CURRANT, GTLiteMetaItems.RED_CURRANT))

        GTLiteMetaItems.BLACK_CURRANT.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_BLACK_CURRANT,
            GTLiteMetaItems.BLACK_CURRANT, GTLiteMetaItems.BLACK_CURRANT))

        GTLiteMetaItems.WHITE_CURRANT.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_WHITE_CURRANT,
            GTLiteMetaItems.WHITE_CURRANT, GTLiteMetaItems.WHITE_CURRANT))

        GTLiteMetaItems.LINGONBERRY.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_LINGONBERRY,
            GTLiteMetaItems.LINGONBERRY, GTLiteMetaItems.LINGONBERRY))

        GTLiteMetaItems.ELDERBERRY.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_ELDERBERRY,
            GTLiteMetaItems.ELDERBERRY,  GTLiteMetaItems.ELDERBERRY))

        GTLiteMetaItems.CRANBERRY.addComponents(BerryCropSeedBehavior(WorldGeneratorBerryManager.BUSH_CRANBERRY,
            GTLiteMetaItems.CRANBERRY, GTLiteMetaItems.CRANBERRY))

        // endregion

        // region Additional Foods
        MetaItems.BOTTLE_PURPLE_DRINK.addComponents(FoodBehavior(3, 0.2F, true, true,
            ItemStack(Items.GLASS_BOTTLE),
            RandomPotionEffect(MobEffects.HASTE, 800, 1, 10),
            RandomPotionEffect(MobEffects.WITHER, 800, 5, 10)))

        // endregion

        // Set compressed clay be visitable.
        MetaItems.COMPRESSED_CLAY.setCreativeTabs(GTCreativeTabs.TAB_GREGTECH as CreativeTabs)

    }

}