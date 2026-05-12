@file:JvmName("MoreCollectionsKt")
@file:Suppress("unused", "UnusedReceiverParameter")
package gregtechlite.gtlitecore.api.collection

import com.google.common.collect.HashBasedTable
import com.google.common.collect.HashBiMap
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import com.google.gson.internal.LinkedTreeMap
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
import it.unimi.dsi.fastutil.objects.Object2ObjectMap
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import it.unimi.dsi.fastutil.objects.ObjectList
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
import it.unimi.dsi.fastutil.objects.ObjectSet
import java.util.TreeMap

// region Guava: ImmutableSet

fun <E> immutableSetOf(): ImmutableSet<E> = ImmutableSet.of()

fun <E> immutableSetOf(vararg elements: E): ImmutableSet<E> = ImmutableSet.copyOf(elements)

fun <E> Collection<E>.toImmutableSet(): ImmutableSet<E> = ImmutableSet.copyOf(this)

fun <E> Iterable<E>.toImmutableSet(): ImmutableSet<E> = ImmutableSet.copyOf(this)

// endregion

// region FastUtil: ObjectSet & ObjectOpenHashSet

fun <E> openHashSetOf(): ObjectSet<E> = ObjectOpenHashSet()

fun <E> openHashSetOf(vararg elements: E): ObjectSet<E> = ObjectOpenHashSet(elements)

fun <E> Collection<E>.toOpenHashSet(): ObjectSet<E> = ObjectOpenHashSet(this)

fun <E> Iterator<E>.toOpenHashSet(): ObjectSet<E> = ObjectOpenHashSet(this)

// endregion

// region Guava: ImmutableList

fun <E> immutableListOf(): ImmutableList<E> = ImmutableList.of()

fun <E> immutableListOf(vararg elements: E): ImmutableList<E> = ImmutableList.copyOf(elements)

fun <E> Collection<E>.toImmutableList(): ImmutableList<E> = ImmutableList.copyOf(this)

fun <E> Iterable<E>.toImmutableList(): ImmutableList<E> = ImmutableList.copyOf(this)

// endregion

// region FastUtil: ObjectList & ObjectArrayList

fun <E> openArrayListOf(): ObjectList<E> = ObjectArrayList()

fun <E> openArrayListOf(vararg elements: E): ObjectList<E> = ObjectArrayList(elements)

fun <E> Collection<E>.toOpenArrayList(): ObjectList<E> = ObjectArrayList(this)

fun <E> Iterator<E>.toOpenArrayList(): ObjectList<E> = ObjectArrayList(this)

// endregion

// region Java: TreeMap

fun <K, V> treeMapOf(): TreeMap<K, V> = TreeMap()

fun <K, V> treeMapOf(comparator: Comparator<in K>): TreeMap<K, V> = TreeMap(comparator)

fun <K, V> treeMapOf(map: Map<K, V>): TreeMap<K, V> = TreeMap(map)

fun <K, V> treeMapOf(vararg pairs: Pair<K, V>): TreeMap<K, V> = TreeMap<K, V>().apply {
    pairs.forEach { put(it.first, it.second) }
}

fun <K, V> Map<K, V>.toTreeMap(): TreeMap<K, V> = treeMapOf(this)

// endregion

// region Gson: LinkedTreeMap

fun <K, V> mutableTreeMapOf(): LinkedTreeMap<K, V> = LinkedTreeMap()

fun <K, V> mutableTreeMapOf(comparator: Comparator<in K>): LinkedTreeMap<K, V> = LinkedTreeMap(comparator)

fun <K, V> mutableTreeMapOf(map: Map<K, V>) = LinkedTreeMap<K, V>().apply {
    map.forEach { put(it.key, it.value) }
}

fun <K, V> mutableTreeMapOf(vararg pairs: Pair<K, V>) = LinkedTreeMap<K, V>().apply {
    pairs.forEach { put(it.first, it.second) }
}

fun <K, V> Map<K, V>.toMutableTreeMap(): LinkedTreeMap<K, V> = mutableTreeMapOf(this)

// endregion

// region Guava: ImmutableMap

fun <K, V> immutableMapOf(): ImmutableMap<K, V> = ImmutableMap.of()

fun <K, V> immutableMapOf(vararg pairs: Pair<K, V>): ImmutableMap<K, V> = ImmutableMap.copyOf(mapOf(*pairs))

fun <K, V> Map<K, V>.toImmutableMap(): ImmutableMap<K, V> = ImmutableMap.copyOf(this)

fun <K, V> Iterable<Pair<K, V>>.toImmutableMap(): ImmutableMap<K, V> = ImmutableMap.copyOf(toMap())

// endregion

// region Guava: BiMap & HashBiMap

fun <K, V> hashBiMapOf(): HashBiMap<K, V> = HashBiMap.create()

fun <K, V> hashBiMapOf(vararg pairs: Pair<K, V>): HashBiMap<K, V> = HashBiMap.create<K, V>().apply {
    pairs.forEach { put(it.first, it.second) }
}

fun <K, V> Map<K, V>.toBiMap(): HashBiMap<K, V> = HashBiMap.create<K, V>().apply {
    forEach { put(it.key, it.value) }
}

fun <K, V> Iterable<Pair<K, V>>.toBiMap(): HashBiMap<K, V> = HashBiMap.create<K, V>().apply {
    forEach { put(it.key, it.value) }
}

// endregion

// region Guava: Table & HashBasedTable

fun <R, C, V> hashTableOf(): HashBasedTable<R, C, V> = HashBasedTable.create()

fun <R, C, V> hashTableOf(vararg triples: Triple<R, C, V>): HashBasedTable<R, C, V> = HashBasedTable.create<R, C, V>().apply {
    triples.forEach { put(it.first!!, it.second!!, it.third!!) }
}

fun <R, C, V> Triple<R, C, V>.toTable(): HashBasedTable<R, C, V> = HashBasedTable.create<R, C, V>().apply {
    put(first!!, second!!, third!!)
}

fun <R, C, V> Iterable<Triple<R, C, V>>.toTable(): HashBasedTable<R, C, V> = HashBasedTable.create<R, C, V>().apply {
    forEach { put(it.first!!, it.second!!, it.third!!) }
}

// endregion

// region FastUtil: Object2ObjectMap & Object2ObjectOpenHashMap

fun <K, V> openHashMapOf(): Object2ObjectMap<K, V> = Object2ObjectOpenHashMap()

fun <K, V> openHashMapOf(map: Map<K, V>): Object2ObjectMap<K, V> = Object2ObjectOpenHashMap(map)

fun <K, V> openHashMapOf(vararg pairs: Pair<K, V>): Object2ObjectMap<K, V> = Object2ObjectOpenHashMap<K, V>().apply {
    pairs.forEach { put(it.first, it.second) }
}

fun <K, V> Map<K, V>.toOpenHashMap(): Object2ObjectMap<K, V> = Object2ObjectOpenHashMap(this)

// endregion

// region FastUtil: Object2ObjectMap & Object2ObjectArrayMap

fun <K, V> openArrayMapOf(): Object2ObjectMap<K, V> = Object2ObjectArrayMap()

fun <K, V> openArrayMapOf(map: Map<K, V>): Object2ObjectMap<K, V> = Object2ObjectArrayMap(map)

fun <K, V> openArrayMapOf(vararg pairs: Pair<K, V>): Object2ObjectMap<K, V> = Object2ObjectArrayMap<K, V>().apply {
    pairs.forEach { put(it.first, it.second) }
}

fun <K, V> Map<K, V>.toOpenArrayMap(): Object2ObjectMap<K, V> = Object2ObjectArrayMap<K, V>(this)

// endregion