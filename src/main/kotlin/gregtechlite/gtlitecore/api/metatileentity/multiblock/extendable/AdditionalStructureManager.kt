package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.value.sync.PanelSyncManager
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

    fun get(metaTileEntityId: ResourceLocation): MutableList<AdditionalMultiblockBase<T>>
        = structures.values.filter { it.metaTileEntityId.equals(metaTileEntityId) }.toMutableList()

    fun get(pos: BlockPos): AdditionalMultiblockBase<T>? = structures[pos]

    fun remove(pos: BlockPos) = structures.remove(pos)

    fun getWidgets(panelSyncManager: PanelSyncManager): List<IWidget> {
        return structures.values.map { it.getButton(panelSyncManager) }.toList()
    }

    fun <A> getAbilities(ability: MultiblockAbility<A>): MutableList<A> {
        val abilities = ArrayList<A>()
        structures.values.forEach { it.getAbilities<A>(ability).also { ab -> abilities.addAll(ab) } }
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