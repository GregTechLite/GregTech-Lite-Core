package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos

open class AdditionalStructureManager<T: ExtendableMultiblock<T>>(protected val base: ExtendableMultiblock<T>)
{
    protected val structures = openHashMapOf<BlockPos, AdditionalMultiblockBase<T>>()

    fun add(additionalMultiblockBase: AdditionalMultiblockBase<T>)
    {
        structures[additionalMultiblockBase.pos] = additionalMultiblockBase
    }

    operator fun get(metaTileEntityId: ResourceLocation): MutableList<AdditionalMultiblockBase<T>>
        = structures.values.filter { it.metaTileEntityId.equals(metaTileEntityId) }.toMutableList()

    operator fun get(pos: BlockPos): AdditionalMultiblockBase<T>? = structures[pos]

    fun remove(pos: BlockPos) = structures.remove(pos)

    fun <A> getAbilities(ability: MultiblockAbility<A>): MutableList<A>
    {
        val abilities = arrayListOf<A>()
        structures.values.forEach { it.getAbilities<A>(ability).also { ability -> abilities.addAll(ability) } }
        return abilities
    }

    fun serialize(): NBTTagCompound
    {
        val nbt = NBTTagCompound()
        val list = NBTTagList()
        structures.keys.forEach {
            val pos = NBTTagCompound().apply {
                setInteger("X", it.x)
                setInteger("Y", it.y)
                setInteger("Z", it.z)
            }
            list.appendTag(pos)
        }
        nbt.setTag("BlockPoses", list)
        return nbt
    }

    fun deserialize(nbt: NBTTagCompound): List<BlockPos>
        = nbt.getTagList("BlockPoses", 10).filterIsInstance<NBTTagCompound>()
            .map { BlockPos(it.getInteger("X"), it.getInteger("Y"), it.getInteger("Z")) }
}