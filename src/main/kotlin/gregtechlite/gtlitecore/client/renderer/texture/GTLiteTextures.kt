package gregtechlite.gtlitecore.client.renderer.texture

import codechicken.lib.texture.TextureUtils
import gregtechlite.gtlitecore.api.extension.registerSprite
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap

object GTLiteTextures : TextureUtils.IIconRegister
{

    // region Atlas Sprite Textures
    lateinit var HALO: TextureAtlasSprite
    lateinit var HALO_NOISE: TextureAtlasSprite
    lateinit var COSMIC: Array<TextureAtlasSprite>
    lateinit var COSMIC_0: TextureAtlasSprite
    lateinit var COSMIC_1: TextureAtlasSprite
    lateinit var COSMIC_2: TextureAtlasSprite
    lateinit var COSMIC_3: TextureAtlasSprite
    lateinit var COSMIC_4: TextureAtlasSprite
    lateinit var COSMIC_5: TextureAtlasSprite
    lateinit var COSMIC_6: TextureAtlasSprite
    lateinit var COSMIC_7: TextureAtlasSprite
    lateinit var COSMIC_8: TextureAtlasSprite
    lateinit var COSMIC_9: TextureAtlasSprite

    lateinit var FORCE_FIELD: TextureAtlasSprite

    // endregion

    @JvmStatic
    fun preInit()
    {
        TextureUtils.addIconRegister(this)
    }

    override fun registerIcons(textureMap: TextureMap)
    {
        HALO = textureMap.registerSprite("shaders/halo")
        HALO_NOISE = textureMap.registerSprite("shaders/halo_noise")
        COSMIC_0 = textureMap.registerSprite("shaders/cosmic_0")
        COSMIC_1 = textureMap.registerSprite("shaders/cosmic_1")
        COSMIC_2 = textureMap.registerSprite("shaders/cosmic_2")
        COSMIC_3 = textureMap.registerSprite("shaders/cosmic_3")
        COSMIC_4 = textureMap.registerSprite("shaders/cosmic_4")
        COSMIC_5 = textureMap.registerSprite("shaders/cosmic_5")
        COSMIC_6 = textureMap.registerSprite("shaders/cosmic_6")
        COSMIC_7 = textureMap.registerSprite("shaders/cosmic_7")
        COSMIC_8 = textureMap.registerSprite("shaders/cosmic_8")
        COSMIC_9 = textureMap.registerSprite("shaders/cosmic_9")
        FORCE_FIELD = textureMap.registerSprite("shaders/force_field")

        COSMIC = arrayOf(
            COSMIC_0, COSMIC_1, COSMIC_2, COSMIC_3, COSMIC_4,
            COSMIC_5, COSMIC_6, COSMIC_7, COSMIC_8, COSMIC_9
        )
    }

}