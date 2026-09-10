package gregtechlite.gtlitecore.api.capability.handler

import gregtechlite.gtlitecore.api.extension.copy
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTankInfo
import net.minecraftforge.fluids.IFluidTank

/**
 * A dynamic instance for [net.minecraftforge.fluids.FluidTank].
 *
 * Every read / write is forwarded to `cells[index]`.
 *
 * @param cells    The backing stack list.
 * @param index    The cell this tank is bound to.
 * @param capacity The capacity of this tank.
 */
class DynamicFluidTank(private val cells: MutableList<FluidStack?>,
                       private val index: Int,
                       private val capacity: Int) : IFluidTank
{
    private fun cell(): FluidStack? = if (index in cells.indices) cells[index] else null

    private fun setCell(stack: FluidStack?)
    {
        if (index in cells.indices)
            cells[index] = stack
    }

    override fun getFluid(): FluidStack? = cell()

    override fun getFluidAmount(): Int = cell()?.amount ?: 0

    override fun getCapacity(): Int = capacity

    override fun getInfo(): FluidTankInfo = FluidTankInfo(cell(), capacity)

    override fun fill(resource: FluidStack?, doFill: Boolean): Int
    {
        if (resource == null || resource.amount <= 0)
            return 0

        val currentStack = cell()
        if (currentStack != null && !currentStack.isFluidEqual(resource))
            return 0

        val currentAmount = currentStack?.amount ?: 0
        val acceptedAmount = minOf(capacity - currentAmount, resource.amount)
        if (acceptedAmount <= 0)
            return 0

        if (doFill)
        {
            if (currentStack == null)
                setCell(resource.copy())
            else
                currentStack.amount += acceptedAmount
        }
        return acceptedAmount
    }

    override fun drain(maxDrain: Int, doDrain: Boolean): FluidStack?
    {
        if (maxDrain <= 0)
            return null

        val currentStack = cell() ?: return null
        val drainedAmount = minOf(maxDrain, currentStack.amount)
        if (drainedAmount <= 0)
            return null

        val result = currentStack.copy(drainedAmount)
        if (doDrain)
        {
            if (drainedAmount >= currentStack.amount)
                setCell(null)
            else
                currentStack.amount -= drainedAmount
        }
        return result
    }

    fun drain(resource: FluidStack?, doDrain: Boolean): FluidStack?
    {
        if (resource == null || resource.amount <= 0)
            return null
        val current = cell() ?: return null
        if (!current.isFluidEqual(resource))
            return null
        return drain(resource.amount, doDrain)
    }
}
