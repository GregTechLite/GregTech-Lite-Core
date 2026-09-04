package gregtechlite.gtlitecore.api.capability.handler

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import java.math.BigInteger

/**
 * Content types are opaque type [T], it must be [net.minecraft.item.ItemStack] or [net.minecraftforge.fluids.FluidStack].
 *
 * @param maxDistinct   A fixed number for maximum distinct content slots.
 * @param totalCapacity The total capacity of all content slots which has.
 */
class QuantumStorageHandler<T>(maxDistinct: Int,
                               totalCapacity: BigInteger,
                               private val isSameType: (T, T) -> Boolean,
                               private val writeType: (NBTTagCompound, T) -> Unit,
                               private val readType: (NBTTagCompound) -> T)
{
    var maxDistinct: Int = maxDistinct.coerceAtLeast(0)
    var totalCapacity: BigInteger = totalCapacity.max(BigInteger.ZERO)

    private var slotCapacity = computeSlotCapacity(this.maxDistinct, this.totalCapacity)
    private val contents = linkedMapOf<T, BigInteger>()

    fun rebuild(newMaxDistinct: Int, newTotalCapacity: BigInteger): Boolean
    {
        maxDistinct = newMaxDistinct.coerceAtLeast(0)
        totalCapacity = newTotalCapacity.max(BigInteger.ZERO)
        slotCapacity = computeSlotCapacity(maxDistinct, totalCapacity)

        val _contents = contents.entries.iterator()
        var isRemoved = false
        while (_contents.hasNext())
        {
            val content = _contents.next()
            if (contents.size > maxDistinct || content.value > slotCapacity)
            {
                _contents.remove()
                isRemoved = true
            }
        }
        return isRemoved
    }

    fun insert(type: T, amount: BigInteger): BigInteger
    {
        if (amount.signum() <= 0 || slotCapacity.signum() <= 0)
            return BigInteger.ZERO

        val existingKey = findKey(type)
        existingKey?.let {
            val currentAmount = contents.getValue(it)

            val remainingAmount = slotCapacity.subtract(currentAmount)
            if (remainingAmount.signum() <= 0)
                return BigInteger.ZERO

            val acceptedAmount = minOf(amount, remainingAmount)
            if (acceptedAmount.signum() > 0)
                contents[it] = currentAmount.add(acceptedAmount)
            return acceptedAmount
        }

        if (contents.size >= maxDistinct)
            return BigInteger.ZERO

        val acceptedAmount = minOf(amount, slotCapacity)
        if (acceptedAmount.signum() > 0)
            contents[type] = acceptedAmount
        return acceptedAmount
    }

    fun extract(type: T, amount: BigInteger): BigInteger
    {
        val key = findKey(type) ?: return BigInteger.ZERO
        val currentAmount = contents.getValue(key)
        val removedAmount = minOf(amount, currentAmount)

        val remainingAmount = currentAmount.subtract(removedAmount)
        if (remainingAmount.signum() <= 0)
            contents.remove(key)
        else
            contents[key] = remainingAmount
        return removedAmount
    }

    fun canInsert(type: T): Boolean = slotCapacity.signum() > 0 && (findKey(type) != null || contents.size < maxDistinct)

    fun maxInsertable(type: T): BigInteger
    {
        if (slotCapacity.signum() <= 0)
            return BigInteger.ZERO

        val existingKey = findKey(type)
        return if (existingKey != null)
        {
            val remainingAmount = slotCapacity.subtract(contents.getValue(existingKey))
            if (remainingAmount.signum() < 0)
                BigInteger.ZERO
            else
                remainingAmount
        }
        else if (contents.size < maxDistinct) slotCapacity
        else BigInteger.ZERO
    }

    fun currentAmount(type: T): BigInteger = findKey(type)?.let { contents.getValue(it) } ?: BigInteger.ZERO

    fun distinctSlots(): Int = contents.size

    fun isEmpty(): Boolean = contents.isEmpty()

    fun isFull(): Boolean = contents.size >= maxDistinct

    fun entries(): Set<Map.Entry<T, BigInteger>> = contents.entries

    fun totalStored(): BigInteger = contents.values.fold(BigInteger.ZERO) { acc, amount -> acc.add(amount) }

    private fun findKey(type: T): T? = contents.keys.firstOrNull { isSameType(it, type) }

    fun serialize(): NBTTagCompound
    {
        val tag = NBTTagCompound()
        val list = NBTTagList()
        contents.forEach { (type, amount) ->
            val slot = NBTTagCompound()
            writeType(slot, type)
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
            val type = readType(slot)
            val amount = BigInteger(slot.getString("amount"))
            if (amount.signum() > 0) contents[type] = amount
        }
    }

    private fun computeSlotCapacity(slots: Int, capacity: BigInteger): BigInteger
        = if (slots <= 0 || capacity.signum() <= 0) BigInteger.ZERO else capacity.divide(BigInteger.valueOf(slots.toLong()))
}