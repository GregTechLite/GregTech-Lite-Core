package gregtechlite.gtlitecore.loader

import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ARTICHOKE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AUBERGINE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BASIL_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BEAN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_PEPPER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CORN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COTTON_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CUCUMBER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GARLIC_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSERADISH_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ONION_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OREGANO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEA_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOY_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOMATO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_GRAPE_SEED
import net.minecraftforge.common.MinecraftForge

internal object VanillaDropsLoader
{

    // @formatter:off

    @JvmStatic
    fun addSeedDrops()
    {
        MinecraftForge.addGrassSeed(COFFEE_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(TOMATO_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(ONION_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(CUCUMBER_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(GRAPE_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(SOY_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(BEAN_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(PEA_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(OREGANO_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(HORSERADISH_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(GARLIC_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(BASIL_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(AUBERGINE_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(CORN_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(ARTICHOKE_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(BLACK_PEPPER_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(RICE_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(WHITE_GRAPE_SEED.stackForm, 1)
        MinecraftForge.addGrassSeed(COTTON_SEED.stackForm, 1)
    }

    // @formatter:on

}