@file:JvmName("JavaMapsKt")

package gregtechlite.gtlitecore.api.collection

import com.google.gson.internal.LinkedTreeMap
import java.util.TreeMap

// region Tree Map

fun <K, V> treeMapOf(): TreeMap<K, V>
    = TreeMap()

fun <K, V> treeMapOf(comparator: Comparator<in K>): TreeMap<K, V>
    = TreeMap(comparator)

fun <K, V> treeMapOf(map: Map<K, V>): TreeMap<K, V>
    = TreeMap(map)

fun <K, V> mutableTreeMapOf(): LinkedTreeMap<K, V>
    = LinkedTreeMap()

fun <K, V> mutableTreeMapOf(comparator: Comparator<in K>): LinkedTreeMap<K, V>
    = LinkedTreeMap(comparator)

fun <K, V> Map<K, V>.toTreeMap(): TreeMap<K, V>
    = treeMapOf(this)

// endregion
