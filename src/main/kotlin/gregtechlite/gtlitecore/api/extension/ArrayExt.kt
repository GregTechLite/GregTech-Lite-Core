package gregtechlite.gtlitecore.api.extension

import gregtechlite.magicbook.util.Unchecks

/**
 * Copies a existed array and add a given element at the end of this array.
 *
 * This method has same function with Apache `ArrayUtils.add` function.
 *
 * @param element The object to added (nullable).
 * @return        A new array copied all old objects and add new object as the last.
 *
 * @see org.apache.commons.lang3.ArrayUtils.add
 */
inline fun <reified T> Array<T>.add(element: T): Array<T>
{
    val arr = copyOf(size + 1)
    arr[size] = element
    return Unchecks.cast(arr)
}