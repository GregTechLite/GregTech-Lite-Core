package magicbook.gtlitecore.loader

import magicbook.gtlitecore.common.item.GTLiteMetaItems
import net.minecraftforge.common.MinecraftForge

@Suppress("MISSING_DEPENDENCY_CLASS")
class VanillaDropsLoader
{

    companion object
    {

        @JvmStatic
        fun addSeedDrops()
        {
            MinecraftForge.addGrassSeed(GTLiteMetaItems.COFFEE_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.TOMATO_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.ONION_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.CUCUMBER_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.GRAPE_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.SOY_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.BEAN_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.PEA_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.OREGANO_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.HORSERADISH_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.GARLIC_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.BASIL_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.AUBERGINE_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.CORN_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.ARTICHOKE_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.BLACK_PEPPER_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.RICE_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.WHITE_GRAPE_SEED.stackForm, 1)
            MinecraftForge.addGrassSeed(GTLiteMetaItems.COTTON_SEED.stackForm, 1)
        }

    }

}