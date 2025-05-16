package magicbook.gtlitecore.core

import gregtech.common.ConfigHolder
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
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
            // Enabled IV-UV solar panels.
            ConfigHolder.machines.enableHighTierSolars = true
            // Add low quality gems because we add a high quality gems, this is a fine
            // balancing about gem sifting outputs.
            ConfigHolder.recipes.generateLowQualityGems = true
            // Disabled paper recipes nerf.
            ConfigHolder.recipes.nerfPaperCrafting = false

        }

    }

}