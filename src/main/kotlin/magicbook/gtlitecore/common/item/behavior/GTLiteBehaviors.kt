package magicbook.gtlitecore.common.item.behavior

import gregtech.api.util.RandomPotionEffect
import gregtech.common.items.MetaItems
import magicbook.gtlitecore.common.item.GTLiteMetaItems
import magicbook.gtlitecore.common.worldgen.crops.WorldGenCrops
import net.minecraft.init.Items
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteBehaviors
{

    companion object
    {

        @JvmStatic
        fun addBehaviors()
        {
            GTLiteMetaItems.COFFEE_SEED.addComponents(CropSeedBehavior(WorldGenCrops.COFFEE_CROP,
                GTLiteMetaItems.COFFEE_SEED, GTLiteMetaItems.COFFEE_CHERRY))

            GTLiteMetaItems.TOMATO_SEED.addComponents(CropSeedBehavior(WorldGenCrops.TOMATO_CROP,
                GTLiteMetaItems.TOMATO_SEED, GTLiteMetaItems.TOMATO))

            GTLiteMetaItems.ONION_SEED.addComponents(CropSeedBehavior(WorldGenCrops.ONION_CROP,
                GTLiteMetaItems.ONION_SEED, GTLiteMetaItems.ONION))

            GTLiteMetaItems.CUCUMBER_SEED.addComponents(CropSeedBehavior(WorldGenCrops.CUCUMBER_CROP,
                GTLiteMetaItems.CUCUMBER_SEED, GTLiteMetaItems.CUCUMBER))

            GTLiteMetaItems.GRAPE_SEED.addComponents(CropSeedBehavior(WorldGenCrops.GRAPE_CROP,
                GTLiteMetaItems.GRAPE_SEED, GTLiteMetaItems.GRAPE))

            GTLiteMetaItems.SOY_SEED.addComponents(CropSeedBehavior(WorldGenCrops.SOY_CROP,
                GTLiteMetaItems.SOY_SEED, GTLiteMetaItems.SOYBEAN))

            GTLiteMetaItems.BEAN_SEED.addComponents(CropSeedBehavior(WorldGenCrops.BEAN_CROP,
                GTLiteMetaItems.BEAN_SEED, GTLiteMetaItems.BEAN))

            GTLiteMetaItems.PEA_SEED.addComponents(CropSeedBehavior(WorldGenCrops.PEA_CROP,
                GTLiteMetaItems.PEA_SEED, GTLiteMetaItems.PEA))

            GTLiteMetaItems.OREGANO_SEED.addComponents(CropSeedBehavior(WorldGenCrops.OREGANO_CROP,
                GTLiteMetaItems.OREGANO_SEED, GTLiteMetaItems.OREGANO))

            GTLiteMetaItems.HORSERADISH_SEED.addComponents(CropSeedBehavior(WorldGenCrops.HORSERADISH_CROP,
                GTLiteMetaItems.HORSERADISH_SEED, GTLiteMetaItems.HORSERADISH))

            GTLiteMetaItems.GARLIC_SEED.addComponents(CropSeedBehavior(WorldGenCrops.GARLIC_CROP,
                GTLiteMetaItems.GARLIC_SEED, GTLiteMetaItems.GARLIC_BULB))

            GTLiteMetaItems.BASIL_SEED.addComponents(CropSeedBehavior(WorldGenCrops.BASIL_CROP,
                GTLiteMetaItems.BASIL_SEED, GTLiteMetaItems.BASIL))

            GTLiteMetaItems.AUBERGINE_SEED.addComponents(CropSeedBehavior(WorldGenCrops.AUBERGINE_CROP,
                GTLiteMetaItems.AUBERGINE_SEED, GTLiteMetaItems.AUBERGINE))

            GTLiteMetaItems.CORN_SEED.addComponents(CropSeedBehavior(WorldGenCrops.CORN_CROP,
                GTLiteMetaItems.CORN_SEED, GTLiteMetaItems.CORN))

            GTLiteMetaItems.ARTICHOKE_SEED.addComponents(CropSeedBehavior(WorldGenCrops.ARTICHOKE_CROP,
                GTLiteMetaItems.ARTICHOKE_SEED, GTLiteMetaItems.ARTICHOKE))

            GTLiteMetaItems.BLACK_PEPPER_SEED.addComponents(CropSeedBehavior(WorldGenCrops.BLACK_PEPPER_CROP,
                GTLiteMetaItems.BLACK_PEPPER_SEED, GTLiteMetaItems.BLACK_PEPPER))

            GTLiteMetaItems.RICE_SEED.addComponents(CropSeedBehavior(WorldGenCrops.RICE_CROP,
                GTLiteMetaItems.RICE_SEED, GTLiteMetaItems.RICE))

            GTLiteMetaItems.WHITE_GRAPE.addComponents(CropSeedBehavior(WorldGenCrops.WHITE_GRAPE_CROP,
                GTLiteMetaItems.WHITE_GRAPE_SEED, GTLiteMetaItems.WHITE_GRAPE))

            GTLiteMetaItems.COTTON_SEED.addComponents(CropSeedBehavior(WorldGenCrops.COTTON_CROP,
                GTLiteMetaItems.COTTON_SEED, GTLiteMetaItems.COTTON))

            // ---------------------------------------------------------------------------------------------------------
            MetaItems.BOTTLE_PURPLE_DRINK.addComponents(FoodBehavior(3, 0.2F, true, true,
                ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.HASTE, 800, 1, 10),
                RandomPotionEffect(MobEffects.WITHER, 800, 5, 10)))

        }

    }

}