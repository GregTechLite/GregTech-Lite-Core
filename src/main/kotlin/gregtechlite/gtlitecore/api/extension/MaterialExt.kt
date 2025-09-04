package gregtechlite.gtlitecore.api.extension

import gregtech.api.unification.material.Material
import net.minecraftforge.fluids.FluidStack

fun Material.getFluid(amount: Double): FluidStack = FluidStack(fluid, amount.toInt())