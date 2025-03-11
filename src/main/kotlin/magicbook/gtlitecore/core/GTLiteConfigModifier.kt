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

            // Add low quality gems because we add a high quality gems, this is a fine
            // balancing about gem sifting outputs.
            ConfigHolder.recipes.generateLowQualityGems = true

        }

    }

}