package gregtechlite.gtlitecore.api.block

import gregtech.api.block.UnlistedStringProperty
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.property.IUnlistedProperty

/**
 * Registry of all [ReTexturableCasing]s.
 *
 * Only casings of the dimension which is currently resolved are kept, the registry
 * will fill on the client side by resolving structure of every formed multiblock
 * controller (via corresponding resolver).
 */
object ReTexturableCasingRegistry
{
    @JvmField
    val BASE_TEXTURE: IUnlistedProperty<String> = UnlistedStringProperty("casing_texture")

    private var dimension: Int? = null

    private var textures: Map<BlockPos, String> = emptyMap()

    fun get(pos: BlockPos?): String?
    {
        if (pos == null)
            return null
        return textures[pos]
    }

    fun replaceAll(dimension: Int, casings: Map<BlockPos, String>): Collection<BlockPos>?
    {
        if (this.dimension == dimension && textures == casings)
            return null

        val previousDim = if (this.dimension == dimension) textures else emptyMap()
        val changedPoses = hashSetOf<BlockPos>()

        previousDim.forEach { (pos, texture) ->
            if (casings[pos] != texture)
                changedPoses.add(pos)
        }
        casings.forEach { (pos, texture) ->
            if (previousDim[pos] != texture)
                changedPoses.add(pos)
        }

        this.dimension = dimension
        this.textures = if (casings.isEmpty()) emptyMap() else HashMap(casings)

        return changedPoses.ifEmpty { null }
    }

    fun clear()
    {
        dimension = null
        textures = emptyMap()
    }
}