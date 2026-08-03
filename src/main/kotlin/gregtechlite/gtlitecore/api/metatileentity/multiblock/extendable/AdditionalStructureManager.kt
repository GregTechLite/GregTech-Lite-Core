package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.collection.openArrayListOf
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos

open class AdditionalStructureManager<T: ExtendableMultiblock<T>>(protected val base: ExtendableMultiblock<T>)
{
    protected val structures = openHashMapOf<BlockPos, AdditionalMultiblockBase<T>>()
    private val snapshotConnections = openArrayListOf<BlockPos>()

    fun add(additionalMultiblockBase: AdditionalMultiblockBase<T>)
    {
        structures[additionalMultiblockBase.pos] = additionalMultiblockBase
        snapshotConnections.remove(additionalMultiblockBase.pos)
    }

    fun addSnapshotConnection(pos: BlockPos)
    {
        if (pos !in structures.keys && pos !in snapshotConnections)
            snapshotConnections.add(pos)
    }

    operator fun get(metaTileEntityId: ResourceLocation): MutableList<AdditionalMultiblockBase<T>>
    {
        tryInsertSnapshotConnections()
        return structures.values.filter { it.metaTileEntityId.equals(metaTileEntityId) }.toMutableList()
    }

    operator fun get(pos: BlockPos): AdditionalMultiblockBase<T>?
    {
        tryInsertSnapshotConnections()
        return structures[pos]
    }

    fun remove(pos: BlockPos)
    {
        structures.remove(pos)
        snapshotConnections.remove(pos)
    }

    fun <A> getAbilities(ability: MultiblockAbility<A>): MutableList<A>
    {
        tryInsertSnapshotConnections()
        val abilities = arrayListOf<A>()
        structures.values.forEach { it.getAbilities<A>(ability).also { ability -> abilities.addAll(ability) } }
        return abilities
    }

    private fun tryInsertSnapshotConnections()
    {
        if (snapshotConnections.isEmpty()) return
        val world = (base as? MetaTileEntity)?.world ?: return
        val snapshot = snapshotConnections.toList()
        for (pos in snapshot)
        {
            val mte = GTUtility.getMetaTileEntity(world, pos)
            if (mte is AdditionalMultiblockBase<*>)
            {
                @Suppress("UNCHECKED_CAST")
                (mte as AdditionalMultiblockBase<T>).connect(base)
            }
        }
    }

    fun serialize(): NBTTagCompound
    {
        val nbt = NBTTagCompound()
        val list = NBTTagList()
        val allPoses = linkedSetOf<BlockPos>()
        allPoses.addAll(structures.keys)
        allPoses.addAll(snapshotConnections)
        allPoses.forEach {
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