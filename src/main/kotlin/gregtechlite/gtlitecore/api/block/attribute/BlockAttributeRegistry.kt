package gregtechlite.gtlitecore.api.block.attribute

import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import net.minecraft.block.state.IBlockState
import kotlin.reflect.KClass

interface BlockAttributeRegistry<T>
{
    companion object
    {
        fun <T : P, P : Comparable<P>> create(name: String): BlockAttributeRegistry<T>
        {
            return DefaultBlockAttributeRegistry(name) { a, b -> a.compareTo(b) }
        }
    }

    val name: String

    val ascendingBlocks: List<IBlockState>
    val descendingBlocks: List<IBlockState>

    val blockSize: Int
    val attributeSize: Int

    fun register(state: IBlockState, attribute: T)

    fun registerAll(vararg pairs: Pair<IBlockState, T>)
    {
        pairs.forEach { register(it.first, it.second) }
    }

    fun unregister(state: IBlockState)

    fun getAttribute(state: IBlockState): T?

    fun getBlock(attribute: T): List<IBlockState>?
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