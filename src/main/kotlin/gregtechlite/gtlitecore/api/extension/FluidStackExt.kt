package gregtechlite.gtlitecore.api.extension

import net.minecraftforge.fluids.FluidStack

/**
 * Copies the [FluidStack] with new amount.
 *
 * @param amount The new amount of the [FluidStack].
 */
fun FluidStack.copy(amount: Int = 1000): FluidStack
{
    val stack = copy()
    stack.amount = amount
    return stack
}