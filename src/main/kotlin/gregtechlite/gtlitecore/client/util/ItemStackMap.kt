/**
 * Copyright (c) 2022 GTNH Team, glee8e, Code Chicken
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 2.1 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package gregtechlite.gtlitecore.client.util

import com.google.common.collect.Iterators
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.OreDictionary

/**
 * The map for [ItemStack]s with wildcard damage or nbt, optimised for look up.
 *
 * The [ItemStackMap] do not support null keys/values, null will be silently ignored. It is a hooks
 *
 * This class is originally part of NotEnoughItem (NEI) which wrote by Code Chicken, and it
 * is adapted to implement the standard map by glee8e. The author of kotlin port of this class
 * and enhanced works is Magic_Sweepy and Gate Guardian.
 *
 * @author CodeChicken, glee8e, mitchej123
 */
@Suppress("unused", "SameParameterValue")
class ItemStackMap<T>(private val nbtSensitive: Boolean) : AbstractMap<ItemStack, T>()
{

    constructor() : this(false)

    companion object
    {
        val WILDCARD_TAG = NBTTagCompound().apply { setBoolean("*", true) }

        private fun isWildcard(damage: Int) = damage == OreDictionary.WILDCARD_VALUE
        private fun isWildcard(tag: NBTTagCompound?) = tag == null || tag === WILDCARD_TAG

        private fun actualDamage(key: ItemStack) = if (isWildcard(key.itemDamage))
            OreDictionary.WILDCARD_VALUE else key.itemDamage

        private fun wildcard(item: Item, nbtSensitive: Boolean): ItemStack
        {
            return newItemStack(item, 1, OreDictionary.WILDCARD_VALUE, if (nbtSensitive) WILDCARD_TAG else null)
        }

        private fun newItemStack(item: Item, count: Int, damage: Int, tag: NBTTagCompound?): ItemStack
        {
            return ItemStack(item, count, damage).apply { tagCompound = tag }
        }
    }

    private val itemMap = HashMap<Item, DetailMap>()
    override var size = 0
    override val entries: AbstractSet<MutableMap.MutableEntry<ItemStack, T>> = SetView()

    override fun get(key: ItemStack): T?
    {
        val detailMap = itemMap[key.item] ?: return null
        return detailMap.get(key)
    }

    fun put(key: ItemStack, value: T): T?
    {
        if (key.isEmpty || value == null) return null
        val detailMap = itemMap.computeIfAbsent(key.item) { DetailMap(nbtSensitive) }
        val oldValue = detailMap.put(key, value)
        if (oldValue == null) size++
        return oldValue
    }

    fun remove(key: Any?): T?
    {
        if (key !is ItemStack) return null
        val detailMap = itemMap[key.item] ?: return null
        val oldValue = detailMap.remove(key)
        if (oldValue != null) size--
        return oldValue
    }

    override fun containsKey(key: ItemStack): Boolean
    {
        val detailMap = itemMap[key.item] ?: return false
        return detailMap.get(key) != null
    }

    fun computeIfAbsent(key: ItemStack, mapper: (ItemStack) -> T): T?
    {
        if (key.isEmpty) return null
        val detailMap = itemMap.computeIfAbsent(key.item) { DetailMap(nbtSensitive) }
        val oldSize = size
        val result = detailMap.computeIfAbsent(key, mapper)
        if (size == oldSize && result != null) size++
        return result
    }

    @Suppress("UNCHECKED_CAST")
    fun merge(key: ItemStack, value: T, remapper: (T, T) -> T): T?
    {
        if (key.isEmpty || value == null) return null
        val detailMap = itemMap.computeIfAbsent(key.item) { DetailMap(nbtSensitive) }
        val oldSize = size
        val result = detailMap.merge(key, value, remapper) as T?
        if (size == oldSize && result != null) size++
        return result
    }

    fun clear()
    {
        itemMap.clear()
        size = 0
    }

    private inner class DetailMap(val nbtSensitive: Boolean)
    {
        var hasWildcard = false
        var wildcard: T? = null
        var damageMap: HashMap<Int, T>? = null
        var tagMap: HashMap<NBTTagCompound, T>? = null
        var metaMap: HashMap<StackMetaKey, T>? = null
        var size = 0

        fun get(key: ItemStack): T?
        {
            if (wildcard != null) return wildcard
            damageMap?.get(actualDamage(key))?.let { return it }
            tagMap?.get(key.tagCompound)?.let { return it }
            return metaMap?.get(StackMetaKey(key))
        }

        fun put(key: ItemStack, value: T): T? {
            when (getKeyType(actualDamage(key), key.tagCompound)) {
                KeyType.NotWildcard -> {
                    if (metaMap == null) metaMap = HashMap()
                    val oldValue = metaMap!!.put(StackMetaKey(key), value)
                    if (oldValue == null) size++
                    return oldValue
                }
                KeyType.WildcardMeta -> {
                    if (tagMap == null) tagMap = HashMap()
                    val oldValue = key.tagCompound?.let { tagMap!!.put(it, value) }
                    if (oldValue == null) size++
                    return oldValue
                }
                KeyType.WildcardTag -> {
                    if (damageMap == null) damageMap = HashMap()
                    val oldValue = damageMap!!.put(actualDamage(key), value)
                    if (oldValue == null) size++
                    return oldValue
                }
                KeyType.WildcardAll -> {
                    val oldValue = wildcard
                    wildcard = value
                    if (!hasWildcard) {
                        hasWildcard = true
                        size++
                    }
                    return oldValue
                }
            }
        }

        fun remove(key: ItemStack): T?
        {
            when (getKeyType(actualDamage(key), key.tagCompound))
            {
                KeyType.NotWildcard -> {
                    val oldValue = metaMap?.remove(StackMetaKey(key))
                    if (oldValue != null) size--
                    return oldValue
                }
                KeyType.WildcardMeta -> {
                    val oldValue = tagMap?.remove(key.tagCompound)
                    if (oldValue != null) size--
                    return oldValue
                }
                KeyType.WildcardTag -> {
                    val oldValue = damageMap?.remove(actualDamage(key))
                    if (oldValue != null) size--
                    return oldValue
                }
                KeyType.WildcardAll -> {
                    val oldValue = wildcard
                    wildcard = null
                    if (hasWildcard) {
                        hasWildcard = false
                        size--
                    }
                    return oldValue
                }
            }
        }

        fun computeIfAbsent(key: ItemStack,
                            mapper: (ItemStack) -> T) = when (getKeyType(actualDamage(key), key.tagCompound))
        {
            KeyType.NotWildcard -> {
                if (metaMap == null) metaMap = HashMap()
                metaMap!!.computeIfAbsent(StackMetaKey(key)) { mapper(key) }
            }
            KeyType.WildcardMeta -> {
                if (tagMap == null) tagMap = HashMap()
                key.tagCompound?.let { tagMap!!.computeIfAbsent(it) { mapper(key) } }
            }
            KeyType.WildcardTag -> {
                if (damageMap == null) damageMap = HashMap()
                damageMap!!.computeIfAbsent(actualDamage(key)) { mapper(key) }
            }
            KeyType.WildcardAll -> {
                wildcard ?: mapper(key).also {
                    wildcard = it
                    hasWildcard = true
                }
            }
        }


        fun merge(key: ItemStack, value: T,
                  remapper: (T, T) -> T) = when (getKeyType(actualDamage(key), key.tagCompound))
        {
            KeyType.NotWildcard -> {
                if (metaMap == null) metaMap = HashMap()
                value?.let { metaMap!!.merge(StackMetaKey(key), it, remapper) }
            }
            KeyType.WildcardMeta -> {
                if (tagMap == null) tagMap = HashMap()
                key.tagCompound?.let { value?.let { it1 ->
                    tagMap!!.merge(it, it1, remapper) } }
            }
            KeyType.WildcardTag -> {
                if (damageMap == null) damageMap = HashMap()
                value?.let { damageMap!!.merge(actualDamage(key), it, remapper) }
            }
            KeyType.WildcardAll -> {
                if (wildcard == null)
                {
                    wildcard = value
                    hasWildcard = true
                    null
                }
                else
                {
                    wildcard = remapper(wildcard!!, value).also { wildcard = it }
                }
            }
        }


        private fun getKeyType(damage: Int, tag: NBTTagCompound?): KeyType
        {
            var i = if (isWildcard(damage)) KeyType.WildcardMeta else KeyType.NotWildcard
            if (!nbtSensitive || isWildcard(tag))
                i = i.withWildcardTag()
            return i
        }

    }

    private class DetailIterator<T>(private val owner: Item,
                                    private val backing: ItemStackMap<T>.DetailMap,
                                    private val damageIterator: MutableIterator<MutableMap.MutableEntry<Int, T>>?,
                                    private val tagIterator: MutableIterator<MutableMap.MutableEntry<NBTTagCompound, T>>?,
                                    private val metaIterator: MutableIterator<MutableMap.MutableEntry<StackMetaKey, T>>?)
        : MutableIterator<MutableMap.MutableEntry<ItemStack, T>>
    {

        constructor(input: Map.Entry<Item, ItemStackMap<T>.DetailMap>) : this(input.key, input.value,
                                                                              input.value.damageMap?.entries?.iterator(), input.value.tagMap?.entries?.iterator(),
                                                                              input.value.metaMap?.entries?.iterator())

        private var state = State.NOT_STARTED
        private var isRemoved = false

        override fun hasNext() = nextState() != State.DONE

        override fun next(): MutableMap.MutableEntry<ItemStack, T>
        {
            val nextState = nextState()
            if (nextState == State.DONE) throw NoSuchElementException()
            state = nextState
            isRemoved = false
            return nextState.get(this)
        }

        override fun remove()
        {
            if (isRemoved) throw IllegalStateException()
            state.remove(this)
            isRemoved = true
        }

        private fun nextState() = when (state)
        {
            State.NOT_STARTED -> if (backing.hasWildcard) State.WILDCARD else State.DAMAGE
            State.WILDCARD -> State.DAMAGE
            State.DAMAGE -> if (damageIterator?.hasNext() == true) State.DAMAGE else State.TAG
            State.TAG -> if (tagIterator?.hasNext() == true) State.TAG else State.META
            State.META -> if (metaIterator?.hasNext() == true) State.META else State.DONE
            State.DONE -> State.DONE
        }

        private enum class State
        {
            NOT_STARTED {

                override fun <T> get(iterator: DetailIterator<T>): MutableMap.MutableEntry<ItemStack, T>
                {
                    throw AssertionError("Should not call get on NOT_STARTED")
                }

                override fun <T> remove(iterator: DetailIterator<T>)
                {
                    throw AssertionError("Should not call remove on NOT_STARTED")
                }

            },
            WILDCARD {

                override fun <T> get(iterator: DetailIterator<T>): MutableMap.MutableEntry<ItemStack, T>
                {
                    return object : MutableMap.MutableEntry<ItemStack, T>
                    {

                        override val key: ItemStack = wildcard(iterator.owner,
                                                               iterator.backing.nbtSensitive)

                        override var value: T = iterator.backing.wildcard!!

                        override fun setValue(newValue: T): T
                        {
                            iterator.backing.wildcard = newValue
                            value = newValue
                            return newValue
                        }

                    }
                }

                override fun <T> remove(iterator: DetailIterator<T>)
                {
                    check(iterator.backing.hasWildcard)
                    iterator.backing.wildcard = null
                    iterator.backing.hasWildcard = false
                }

            },
            DAMAGE {

                override fun <T> get(iterator: DetailIterator<T>): MutableMap.MutableEntry<ItemStack, T>
                {
                    val entry = checkNotNull(iterator.damageIterator).next()
                    return java.util.AbstractMap.SimpleEntry(newItemStack(iterator.owner,
                                                                          1,
                                                                          entry.key,
                                                                          if (iterator.backing.nbtSensitive) WILDCARD_TAG else null),
                                                             entry.value)
                }

                override fun <T> remove(iterator: DetailIterator<T>)
                {
                    checkNotNull(iterator.damageIterator).remove()
                }

            },
            TAG {

                override fun <T> get(iterator: DetailIterator<T>): MutableMap.MutableEntry<ItemStack, T>
                {
                    val entry = checkNotNull(iterator.tagIterator).next()
                    return java.util.AbstractMap.SimpleEntry(newItemStack(iterator.owner,
                                                                          1,
                                                                          OreDictionary.WILDCARD_VALUE,
                                                                          entry.key), entry.value)
                }

                override fun <T> remove(iterator: DetailIterator<T>)
                {
                    checkNotNull(iterator.tagIterator).remove()
                }

            },
            META {

                override fun <T> get(iterator: DetailIterator<T>): MutableMap.MutableEntry<ItemStack, T>
                {
                    val entry = checkNotNull(iterator.metaIterator).next()
                    return java.util.AbstractMap.SimpleEntry(newItemStack(iterator.owner,
                                                                          1,
                                                                          entry.key.damage,
                                                                          entry.key.tag), entry.value)
                }

                override fun <T> remove(iterator: DetailIterator<T>)
                {
                    checkNotNull(iterator.metaIterator).remove()
                }

            },
            DONE {

                override fun <T> get(iterator: DetailIterator<T>): MutableMap.MutableEntry<ItemStack, T>
                {
                    throw AssertionError("Should not call get on DONE")
                }

                override fun <T> remove(iterator: DetailIterator<T>)
                {
                    throw AssertionError("Should not call remove on DONE")
                }

            };

            abstract fun <T> get(iterator: DetailIterator<T>): MutableMap.MutableEntry<ItemStack, T>

            abstract fun <T> remove(iterator: DetailIterator<T>)

        }

    }

    private inner class SetView : AbstractSet<MutableMap.MutableEntry<ItemStack, T>>()
    {

        override fun iterator(): Iterator<MutableMap.MutableEntry<ItemStack, T>> = Iterators.concat(
            Iterators.transform(itemMap.entries.iterator()) { input ->
                DetailIterator(input!!) })

        override val size: Int
            get() = this@ItemStackMap.size

        override fun contains(element: MutableMap.MutableEntry<ItemStack, T>) = get(element.key) == element.value

        fun add(element: MutableMap.MutableEntry<ItemStack, T>): Boolean {
            return element.value != null && put(element.key, element.value) == null
        }

        fun remove(element: Any?): Boolean
        {
            if (element !is Map.Entry<*, *>) return false
            return this@ItemStackMap.remove(element.key) as Boolean
        }

    }

    private class StackMetaKey(val damage: Int, val tag: NBTTagCompound?)
    {

        constructor(key: ItemStack) : this(actualDamage(key), key.tagCompound)

        override fun hashCode(): Int
        {
            var result = damage
            result = 31 * result + (tag?.hashCode() ?: 0)
            return result
        }

        override fun equals(other: Any?): Boolean
        {
            if (this === other) return true
            if (other !is StackMetaKey) return false
            return damage == other.damage && tag == other.tag
        }

    }

    private enum class KeyType
    {
        NotWildcard,
        WildcardMeta,
        WildcardTag,
        WildcardAll;

        fun withWildcardMeta() = entries[ordinal or 1]
        fun withWildcardTag() = entries[ordinal or 2]
    }

}