package gregtechlite.gtlitecore.core.module

import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.module.IModule
import net.minecraft.util.ResourceLocation

abstract class BaseModule : IModule
{

    @get:JvmName("_dependencyUids")
    @get:JvmSynthetic
    val dependencyUids = mutableSetOf(GTLiteMod.id("core"))

    override fun getDependencyUids(): MutableSet<ResourceLocation> = dependencyUids

}