@file:JvmName("FastutilMapsKt")

package gregtechlite.gtlitecore.api.collection

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap

// region Object -> Object Open Hash Map

fun <K, V> fastObjectHashMapOf(): Object2ObjectOpenHashMap<K, V>
    = Object2ObjectOpenHashMap()

fun <K, V> fastObjectHashMapOf(map: Map<K, V>): Object2ObjectOpenHashMap<K, V>
    = Object2ObjectOpenHashMap(map)

fun <K, V> fastObjectHashMapOf(vararg pairs: Pair<K, V>): Object2ObjectOpenHashMap<K, V>
    = Object2ObjectOpenHashMap<K, V>().apply { pairs.forEach { put(it.first, it.second) } }

fun <K, V> Map<K, V>.toFastObjectHashMap(): Object2ObjectOpenHashMap<K, V>
    = Object2ObjectOpenHashMap(this)

// endregion