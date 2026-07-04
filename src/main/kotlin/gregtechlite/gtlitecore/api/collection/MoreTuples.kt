@file:JvmName("MoreTuplesKt")
package gregtechlite.gtlitecore.api.collection

import org.apache.commons.lang3.tuple.Pair as ApachePair
import org.apache.commons.lang3.tuple.Triple as ApacheTriple

// region Apache: Pair & Triple

infix fun <A, B> A.to(that: B): ApachePair<A, B> = ApachePair.of(this, that)

fun <T> ApachePair<T, T>.toList(): List<T> = listOf(left, right)

fun <T> ApacheTriple<T, T, T>.toList(): List<T> = listOf(left, middle, right)

// endregion