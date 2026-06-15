package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import kotlin.streams.toList

open class AdditionalStructureManager<T: ExtendableMultiblockBase<T>>(protected val base: ExtendableMultiblockBase<T>)
{
    protected val structures: Object2ObjectOpenHashMap<BlockPos, AdditionalMultiblockBase<T>> = Object2ObjectOpenHashMap()

    fun add(additionalMultiblockBase: AdditionalMultiblockBase<T>)
    {
        structures[additionalMultiblockBase.pos] = additionalMultiblockBase
    }

    fun get(metaTileEntityId: ResourceLocation): Collection<AdditionalMultiblockBase<T>> = structures.values.stream().filter { it.metaTileEntityId.equals(metaTileEntityId) }.toList()

    fun get(pos: BlockPos): AdditionalMultiblockBase<T>? = structures[pos]

    fun remove(pos: BlockPos) = structures.remove(pos)

    fun serialize(): NBTTagCompound {
        val nbt = NBTTagCompound()
        val list = NBTTagList()
        structures.keys.forEach {
            val pos = NBTTagCompound()
            pos.setInteger("X", it.x)
            pos.setInteger("Y", it.y)
            pos.setInteger("Z", it.z)
            list.appendTag(pos)
        }
        nbt.setTag("BlockPoses", list)
        return nbt
    }

    fun deserialize(nbt: NBTTagCompound): List<BlockPos> {
        return nbt.getTagList("BlockPoses", 10).filterIsInstance<NBTTagCompound>().map {
            BlockPos(
                it.getInteger("X"),
                it.getInteger("Y"),
                it.getInteger("Z")
            )
        }
    }

}