package gregtechlite.gtlitecore.core

import gregtech.common.ConfigHolder

internal object GTLiteConfigModifier
{

    fun init()
    {

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