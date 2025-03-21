package magicbook.gtlitecore.core

import gregtech.common.ConfigHolder

@Suppress("UnstableApiUsage")
class GTLiteConfigModifier
{

    companion object
    {

        @JvmStatic
        fun init()
        {
            // GregTech configurations.

            // Basic configuration for high tier contents.
            ConfigHolder.machines.highTierContent = true
            // Add low quality gems because we add a high quality gems, this is a fine
            // balancing about gem sifting outputs.
            ConfigHolder.recipes.generateLowQualityGems = true
            // Disabled universal collection ore settings because we add unique stones.
            ConfigHolder.worldgen.allUniqueStoneTypes = true
            // Disabled paper recipes nerf.
            ConfigHolder.recipes.nerfPaperCrafting = false

        }

    }

}