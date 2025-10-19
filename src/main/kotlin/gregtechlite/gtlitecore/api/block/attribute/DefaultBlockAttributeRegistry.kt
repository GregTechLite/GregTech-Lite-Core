package gregtechlite.gtlitecore.api.block.attribute

import gregtechlite.gtlitecore.api.collection.fastObjectHashMapOf
import gregtechlite.gtlitecore.api.collection.treeMapOf
import net.minecraft.block.state.IBlockState
import java.util.*

class DefaultBlockAttributeRegistry<T>(override val name: String, comparator: Comparator<in T>) : BlockAttributeRegistry<T>
{

    private val attributeLookup: MutableMap<IBlockState, T> = fastObjectHashMapOf()
    private val blockLookup: NavigableMap<T, MutableList<IBlockState>> = treeMapOf(comparator)

    private var _ascendingBlocks: LazyValue<List<IBlockState>> = LazyValue { blockLookup.values.flatten() }
    private var _descendingBlocks: LazyValue<List<IBlockState>> = LazyValue { blockLookup.values.reversed().flatten() }

    override val ascendingBlocks: List<IBlockState>
        get() = _ascendingBlocks.get()
    override val descendingBlocks: List<IBlockState>
        get() = _descendingBlocks.get()

    override val blockSize: Int
        get() = attributeLookup.size
    override val attributeSize: Int
        get() = blockLookup.size

    override fun register(state: IBlockState, attribute: T)
    {
        attributeLookup[state] = attribute
        blockLookup.computeIfAbsent(attribute) { mutableListOf() }.add(state)
        invalidate()
    }

    override fun registerAll(vararg pairs: Pair<IBlockState, T>)
    {
        pairs.forEach { register(it.first, it.second) }
    }

    override fun unregister(state: IBlockState)
    {
        attributeLookup.remove(state)?.also {
            blockLookup[it]?.remove(state)
        }
    }

    override fun getAttribute(state: IBlockState): T?
    {
        return attributeLookup[state]
    }

    override fun getBlock(attribute: T): List<IBlockState>?
    {
        return blockLookup[attribute]
    }

    private fun invalidate()
    {
        _ascendingBlocks.invalidate()
        _descendingBlocks.invalidate()
    }

}