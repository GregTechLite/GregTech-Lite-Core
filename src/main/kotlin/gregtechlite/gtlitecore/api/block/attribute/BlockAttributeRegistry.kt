package gregtechlite.gtlitecore.api.block.attribute

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import java.util.NavigableMap
import java.util.TreeMap
import kotlin.reflect.KClass

class BlockAttributeRegistry<T>(val name: String, comparator: Comparator<in T>)
{

    companion object
    {

        fun <T : P, P : Comparable<P>> create(name: String): BlockAttributeRegistry<T>
        {
            return BlockAttributeRegistry(name) { a, b -> a.compareTo(b) }
        }

        @JvmName("registerBlockVariants")
        fun <T> BlockAttributeRegistry<in T>.registerBlockVariants(enum: KClass<T>) where T : Enum<T>, T : BlockVariant
        {
            registerBlockVariants(enum) { it }
        }

        @JvmName("registerBlockVariantsTiered")
        fun <T> BlockAttributeRegistry<Int>.registerBlockVariants(enum: KClass<T>) where T : Enum<T>, T : BlockVariant, T : StateTier
        {
            registerBlockVariants(enum) { it.tier }
        }

        fun <V, T> BlockAttributeRegistry<in T>.registerBlockVariants(
            enum: KClass<V>,
            extractor: (V) -> T
        ) where V : Enum<V>, V : BlockVariant
        {
            for (variant in enum.java.enumConstants)
            {
                register(variant.state, extractor(variant))
            }
        }
    }

    private val attributeLookup: MutableMap<IBlockState, T> = Object2ObjectOpenHashMap()
    private val blockLookup: NavigableMap<T, MutableList<IBlockState>> = TreeMap(comparator)

    private var _ascendingBlocks: LazyValue<List<IBlockState>> = LazyValue { blockLookup.values.flatten() }
    private var _descendingBlocks: LazyValue<List<IBlockState>> = LazyValue { blockLookup.values.reversed().flatten() }

    val ascendingBlocks: List<IBlockState>
        get() = _ascendingBlocks.get()
    val descendingBlocks: List<IBlockState>
        get() = _descendingBlocks.get()

    val blockSize: Int
        get() = attributeLookup.size
    val attributeSize: Int
        get() = blockLookup.size

    fun register(state: IBlockState, attribute: T)
    {
        attributeLookup[state] = attribute
        blockLookup.computeIfAbsent(attribute) { mutableListOf() }.add(state)
        invalidate()
    }

    fun registerAll(vararg pairs: Pair<IBlockState, T>)
    {
        pairs.forEach { register(it.first, it.second) }
        invalidate()
    }

    fun unregister(state: IBlockState)
    {
        attributeLookup.remove(state)?.also {
            blockLookup[it]?.remove(state)
        }
    }

    fun getAttribute(state: IBlockState): T?
    {
        return attributeLookup[state]
    }

    fun getBlock(attribute: T): List<IBlockState>?
    {
        return blockLookup[attribute]
    }

    private fun invalidate()
    {
        _ascendingBlocks.invalidate()
        _descendingBlocks.invalidate()
    }

}