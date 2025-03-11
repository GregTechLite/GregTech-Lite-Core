package magicbook.gtlitecore.core

import com.cleanroommc.modularui.ModularUIConfig
import gregtech.api.GTValues
import gregtech.common.ConfigHolder

@Suppress("UnstableApiUsage")
class GTLiteConfigModifier
{

    companion object
    {

        @JvmStatic
        fun init()
        {
            // Disabled ModularUI testing if working environment is not development environment.
            if (!GTValues.isDeobfEnvironment())
                ModularUIConfig.enableTestGuis = false

            // GregTech configurations.

            // Add low quality gems because we add a high quality gems, this is a fine
            // balancing about gem sifting outputs.
            ConfigHolder.recipes.generateLowQualityGems = true

        }

    }

}