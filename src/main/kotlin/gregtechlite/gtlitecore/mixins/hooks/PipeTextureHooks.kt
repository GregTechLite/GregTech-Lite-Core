package gregtechlite.gtlitecore.mixins.hooks

import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase
import gregtech.api.pipenet.tile.IPipeTile
import gregtech.api.unification.material.info.MaterialIconSet
import gregtech.api.util.GTUtility.gregtechId
import gregtech.common.pipelike.fluidpipe.FluidPipeType
import gregtech.common.pipelike.itempipe.ItemPipeType
import gregtechlite.gtlitecore.api.collection.hashTableOf
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
@Hook
object PipeTextureHooks
{
    private val cableWireTextures = openHashMapOf<MaterialIconSet, TextureAtlasSprite>()

    private val fluidPipeTextures = hashTableOf<FluidPipeType, MaterialIconSet, TextureAtlasSprite>()
    private val fluidPipeSideTextures = openHashMapOf<MaterialIconSet, TextureAtlasSprite>()

    private val itemPipeTextures = hashTableOf<ItemPipeType, MaterialIconSet, TextureAtlasSprite>()
    private val itemPipeSideTextures = openHashMapOf<MaterialIconSet, TextureAtlasSprite>()

    @JvmStatic
    fun registerCableIcons(map: TextureMap)
    {
        cableWireTextures[MaterialIconSet.DULL] = map.registerSprite(gregtechId("blocks/cable/wire"))
        for (iconSet in MaterialIconSet.ICON_SETS.values)
        {
            cableWireTextures[iconSet] = map.registerSprite(
                gregtechId("blocks/material_sets/${iconSet.name.lowercase()}/wire"))
        }
    }

    @JvmStatic
    fun registerFluidPipeIcons(map: TextureMap)
    {
        for (iconSet in MaterialIconSet.ICON_SETS.values)
        {
            val base = "blocks/material_sets/${iconSet.name.lowercase()}"
            fluidPipeTextures.put(FluidPipeType.TINY     , iconSet, map.registerSprite(gregtechId("$base/pipe_tiny_in"     )))
            fluidPipeTextures.put(FluidPipeType.SMALL    , iconSet, map.registerSprite(gregtechId("$base/pipe_small_in"    )))
            fluidPipeTextures.put(FluidPipeType.NORMAL   , iconSet, map.registerSprite(gregtechId("$base/pipe_normal_in"   )))
            fluidPipeTextures.put(FluidPipeType.LARGE    , iconSet, map.registerSprite(gregtechId("$base/pipe_large_in"    )))
            fluidPipeTextures.put(FluidPipeType.HUGE     , iconSet, map.registerSprite(gregtechId("$base/pipe_huge_in"     )))
            fluidPipeTextures.put(FluidPipeType.QUADRUPLE, iconSet, map.registerSprite(gregtechId("$base/pipe_quadruple_in")))
            fluidPipeTextures.put(FluidPipeType.NONUPLE  , iconSet, map.registerSprite(gregtechId("$base/pipe_nonuple_in"  )))
            fluidPipeSideTextures[iconSet] = map.registerSprite(gregtechId("$base/pipe_side"))
        }
    }

    @JvmStatic
    fun registerItemPipeIcons(map: TextureMap)
    {
        for (iconSet in MaterialIconSet.ICON_SETS.values)
        {
            val base = "blocks/material_sets/${iconSet.name.lowercase()}"
            val small  = map.registerSprite(gregtechId("$base/pipe_small_in" ))
            val normal = map.registerSprite(gregtechId("$base/pipe_normal_in"))
            val large  = map.registerSprite(gregtechId("$base/pipe_large_in" ))
            val huge   = map.registerSprite(gregtechId("$base/pipe_huge_in"  ))

            itemPipeTextures.put(ItemPipeType.SMALL , iconSet, small )
            itemPipeTextures.put(ItemPipeType.NORMAL, iconSet, normal)
            itemPipeTextures.put(ItemPipeType.LARGE , iconSet, large )
            itemPipeTextures.put(ItemPipeType.HUGE  , iconSet, huge  )

            itemPipeTextures.put(ItemPipeType.RESTRICTIVE_SMALL, iconSet, small)
            itemPipeTextures.put(ItemPipeType.RESTRICTIVE_NORMAL, iconSet, normal)
            itemPipeTextures.put(ItemPipeType.RESTRICTIVE_LARGE, iconSet, large)
            itemPipeTextures.put(ItemPipeType.RESTRICTIVE_HUGE, iconSet, huge)

            itemPipeSideTextures[iconSet] = map.registerSprite(gregtechId("$base/pipe_side"))
        }
    }

    @JvmStatic
    fun cableWireTexture(iconSet: MaterialIconSet?): TextureAtlasSprite?
        = cableWireTextures[iconSet] ?: cableWireTextures[MaterialIconSet.DULL]

    @JvmStatic
    fun cableWireTextureForPipe(pipeTile: IPipeTile<*, *>): TextureAtlasSprite?
        = cableWireTexture((pipeTile as? TileEntityMaterialPipeBase<*, *>)?.pipeMaterial?.materialIconSet)

    @JvmStatic
    fun fluidPipeTexture(pipeType: FluidPipeType, iconSet: MaterialIconSet): TextureAtlasSprite?
        = fluidPipeTextures.get(pipeType, iconSet)

    @JvmStatic
    fun fluidPipeSideTexture(iconSet: MaterialIconSet): TextureAtlasSprite?
        = fluidPipeSideTextures[iconSet]

    @JvmStatic
    fun itemPipeTexture(pipeType: ItemPipeType, iconSet: MaterialIconSet): TextureAtlasSprite?
        = itemPipeTextures.get(pipeType, iconSet)

    @JvmStatic
    fun itemPipeSideTexture(iconSet: MaterialIconSet): TextureAtlasSprite?
        = itemPipeSideTextures[iconSet]
}
