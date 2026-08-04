package gregtechlite.gtlitecore.core.module

import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.module.CustomModule
import net.minecraft.util.ResourceLocation

abstract class BaseModule : CustomModule
{
    override val dependencyUids: Set<ResourceLocation> = mutableSetOf(GTLiteMod.id("core"))
}