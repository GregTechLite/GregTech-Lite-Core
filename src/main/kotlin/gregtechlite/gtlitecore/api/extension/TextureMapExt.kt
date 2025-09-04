package gregtechlite.gtlitecore.api.extension

import gregtechlite.gtlitecore.GTLiteMod
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation

fun TextureMap.registerSprite(path: String): TextureAtlasSprite = registerSprite(GTLiteMod.id(path))

fun TextureMap.registerSprite(namespace: String, path: String): TextureAtlasSprite = registerSprite(ResourceLocation(namespace, path))