@file:JvmName("GuavaMaps")

package gregtechlite.gtlitecore.api.collection

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import org.spongepowered.include.com.google.common.collect.ImmutableCollection

// region Immutable Map

fun <K, V> immutableMapOf(): ImmutableMap<K, V>
    = ImmutableMap.of()

fun <K, V> immutableMapOf(vararg pairs: Pair<K, V>): ImmutableMap<K, V>
    = ImmutableMap.copyOf(mapOf(*pairs))

fun <K, V> Map<K, V>.toImmutableMap(): ImmutableMap<K, V>
    = ImmutableMap.copyOf(this)

fun <K, V> Iterable<Pair<K, V>>.toImmutableMap(): ImmutableMap<K, V>
    = ImmutableMap.copyOf(toMap())

// endregion

// region Immutable List

fun <E> immutableListOf(): ImmutableList<E>
    = ImmutableList.of()

fun <E> immutableListOf(vararg elements: E): ImmutableList<E>
    = ImmutableList.copyOf(elements)

fun <E> Collection<E>.toImmutableList(): ImmutableList<E>
    = ImmutableList.copyOf(this)

fun <E> Iterable<E>.toImmutableList(): ImmutableList<E>
    = ImmutableList.copyOf(this)

// endregion

// region Immutable Set

fun <E> immutableSetOf(): ImmutableSet<E>
    = ImmutableSet.of()

fun <E> immutableSetOf(vararg elements: E): ImmutableSet<E>
    = ImmutableSet.copyOf(elements)

fun <E> Collection<E>.toImmutableSet(): ImmutableSet<E>
    = ImmutableSet.copyOf(this)

fun <E> Iterable<E>.toImmutableSet(): ImmutableSet<E>
    = ImmutableSet.copyOf(this)

// endregion