package gregtechlite.gtlitecore.api.capability.handler

import it.unimi.dsi.fastutil.Hash
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import java.math.BigInteger

/**
 * @param T             An opaque type for contents, must be `ItemStack` or `FluidStack`.
 * @param maxDistinct   A fixed number for maximum distinct content slots.
 * @param totalCapacity The total capacity of all content slots which has.
 * @param strategy      Hash strategy for content equality; must ignore stack size.
 * @param copyType      Copies a content type, so callers never own a stored instance.
 */
class QuantumStorageHandler<T>(maxDistinct: Int,
                               totalCapacity: BigInteger,
                               private val strategy: Hash.Strategy<T>,
                               private val copyType: (T) -> T,
                               private val writeType: (NBTTagCompound, T) -> Unit,
                               private val readType: (NBTTagCompound) -> T)
{
    var maxDistinct: Int = maxDistinct.coerceAtLeast(0)
    var totalCapacity: BigInteger = totalCapacity.max(BigInteger.ZERO)

    private var slotCapacity = computeSlotCapacity(this.maxDistinct, this.totalCapacity)
    private val contents = LinkedHashMap<HashKey<T>, BigInteger>()
    private var storedTotal: BigInteger = BigInteger.ZERO

    fun rebuild(newMaxDistinct: Int, newTotalCapacity: BigInteger): Boolean
    {
        maxDistinct = newMaxDistinct.coerceAtLeast(0)
        totalCapacity = newTotalCapacity.max(BigInteger.ZERO)
        slotCapacity = computeSlotCapacity(maxDistinct, totalCapacity)

        val iterator = contents.entries.iterator()
        var isRemoved = false
        while (iterator.hasNext())
        {
            val content = iterator.next()
            if (contents.size > maxDistinct || content.value > slotCapacity)
            {
                iterator.remove()
                isRemoved = true
            }
        }
        if (isRemoved) recomputeTotal()
        return isRemoved
    }

    fun insert(type: T, amount: BigInteger): BigInteger
    {
        if (amount.signum() <= 0 || slotCapacity.signum() <= 0)
            return BigInteger.ZERO

        val key = HashKey(type, strategy)
        val currentAmount = contents[key]
        if (currentAmount != null)
        {
            val remainingAmount = slotCapacity.subtract(currentAmount)
            if (remainingAmount.signum() <= 0)
                return BigInteger.ZERO

            val acceptedAmount = minOf(amount, remainingAmount)
            if (acceptedAmount.signum() > 0)
            {
                contents[key] = currentAmount.add(acceptedAmount)
                storedTotal = storedTotal.add(acceptedAmount)
            }
            return acceptedAmount
        }

        if (contents.size >= maxDistinct)
            return BigInteger.ZERO

        val acceptedAmount = minOf(amount, slotCapacity)
        if (acceptedAmount.signum() > 0)
        {
            contents[key] = acceptedAmount
            storedTotal = storedTotal.add(acceptedAmount)
        }
        return acceptedAmount
    }

    fun extract(type: T, amount: BigInteger): BigInteger
    {
        if (amount.signum() <= 0) return BigInteger.ZERO

        val key = HashKey(type, strategy)
        val currentAmount = contents[key] ?: return BigInteger.ZERO
        val removedAmount = minOf(amount, currentAmount)

        val remainingAmount = currentAmount.subtract(removedAmount)
        if (remainingAmount.signum() <= 0)
            contents.remove(key)
        else
            contents[key] = remainingAmount
        storedTotal = storedTotal.subtract(removedAmount)
        return removedAmount
    }

    fun canInsert(type: T): Boolean
        = slotCapacity.signum() > 0 && (contents.containsKey(HashKey(type, strategy)) || contents.size < maxDistinct)

    fun maxInsertable(type: T): BigInteger
    {
        if (slotCapacity.signum() <= 0)
            return BigInteger.ZERO

        val currentAmount = contents[HashKey(type, strategy)]
        return if (currentAmount != null)
        {
            val remainingAmount = slotCapacity.subtract(currentAmount)
            if (remainingAmount.signum() < 0) BigInteger.ZERO else remainingAmount
        }
        else if (contents.size < maxDistinct) slotCapacity
        else BigInteger.ZERO
    }

    fun currentAmount(type: T): BigInteger = contents[HashKey(type, strategy)] ?: BigInteger.ZERO

    fun distinctSlots(): Int = contents.size

    fun isEmpty(): Boolean = contents.isEmpty()

    fun isFull(): Boolean = contents.size >= maxDistinct

    fun entries(): List<Pair<T, BigInteger>> = contents.map { (key, amount) -> copyType(key.type) to amount }

    fun totalStored(): BigInteger = storedTotal

    fun serialize(): NBTTagCompound
    {
        val tag = NBTTagCompound()
        val list = NBTTagList()
        for ((key, amount) in contents)
        {
            val slot = NBTTagCompound()
            writeType(slot, key.type)
            slot.setString("amount", amount.toString())
            list.appendTag(slot)
        }
        tag.setTag("contents", list)
        return tag
    }

    fun deserialize(tag: NBTTagCompound)
    {
        contents.clear()
        val list = tag.getTagList("contents", 10)
        for (i in 0 until list.tagCount())
        {
            val slot = list.getCompoundTagAt(i)
            val amount = BigInteger(slot.getString("amount"))
            if (amount.signum() > 0) contents[HashKey(readType(slot), strategy)] = amount
        }
        recomputeTotal()
    }

    private fun recomputeTotal()
    {
        var total = BigInteger.ZERO
        for (amount in contents.values) total = total.add(amount)
        storedTotal = total
    }

    private fun computeSlotCapacity(slots: Int, capacity: BigInteger): BigInteger
        = if (slots <= 0 || capacity.signum() <= 0) BigInteger.ZERO else capacity.divide(BigInteger.valueOf(slots.toLong()))

    private class HashKey<T>(val type: T, private val strategy: Hash.Strategy<T>)
    {
        private val hash = strategy.hashCode(type)

        override fun hashCode(): Int = hash

        override fun equals(other: Any?): Boolean
        {
            if (other !is HashKey<*>) return false
            if (strategy !== other.strategy) return false
            @Suppress("UNCHECKED_CAST")
            return strategy.equals(type, other.type as T)
        }
    }
}
