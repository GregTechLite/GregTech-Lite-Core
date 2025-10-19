@file:JvmName("JavaMapsKt")

package gregtechlite.gtlitecore.api.collection

import com.google.gson.internal.LinkedTreeMap as MutableTreeMap
import java.util.TreeMap

// region Tree Map

fun <K, V> treeMapOf(): TreeMap<K, V>
    = TreeMap()

fun <K, V> treeMapOf(comparator: Comparator<in K>): TreeMap<K, V>
    = TreeMap(comparator)

fun <K, V> treeMapOf(map: Map<K, V>): TreeMap<K, V>
    = TreeMap(map)

fun <K, V> mutableTreeMapOf(): MutableTreeMap<K, V>
    = MutableTreeMap()

fun <K, V> mutableTreeMapOf(comparator: Comparator<in K>): MutableTreeMap<K, V>
    = MutableTreeMap(comparator)

fun <K, V> Map<K, V>.toTreeMap(): TreeMap<K, V>
    = treeMapOf(this)

// endregion
