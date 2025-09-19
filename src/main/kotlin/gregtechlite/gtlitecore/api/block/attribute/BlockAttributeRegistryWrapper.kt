package gregtechlite.gtlitecore.api.block.attribute

import net.minecraft.block.state.IBlockState

class BlockAttributeRegistryWrapper<T>(override val name: String, private val registry: MutableMap<IBlockState, T>, private val comparator: Comparator<in T>) :
    BlockAttributeRegistry<T>
{

    override val ascendingBlocks: List<IBlockState>
        get() = registry.asIterable()
            .sortedWith { a, b -> comparator.compare(a.value, b.value) }
            .map { it.key }
    override val descendingBlocks: List<IBlockState>
        get() = ascendingBlocks.asReversed()

    override val blockSize: Int
        get() = registry.size
    override val attributeSize: Int
        get() = registry.values.distinct().size

    override fun register(state: IBlockState, attribute: T)
    {
        registry[state] = attribute
    }

    override fun unregister(state: IBlockState)
    {
        registry -= state
    }

    override fun getAttribute(state: IBlockState) = registry[state]

    override fun getBlock(attribute: T): List<IBlockState> = registry.filter { it.value == attribute }.map { it.key }
}