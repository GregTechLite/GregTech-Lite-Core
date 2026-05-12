@file:JvmName("MoreIteratorsKt")
package gregtechlite.gtlitecore.api.collection

import com.google.common.collect.Iterators

// region Guava: Iterators

fun <F, T> Iterator<F>.transform(f: (F?) -> T): MutableIterator<T> = Iterators.transform(this, f)

fun <T> Iterator<Iterator<T>>.concat(): MutableIterator<T> = Iterators.concat(this)

// endregion