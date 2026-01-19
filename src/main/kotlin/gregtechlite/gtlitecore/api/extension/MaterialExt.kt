package gregtechlite.gtlitecore.api.extension

import com.morphismmc.morphismlib.util.I18nUtil
import gregtech.api.fluids.FluidBuilder
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.FluidProperty
import gregtech.api.unification.material.properties.IngotProperty
import gregtech.api.unification.material.properties.PropertyKey
import net.minecraftforge.fluids.FluidStack

// region Regular Convert Methods

fun Material.getFluid(amount: Double): FluidStack = FluidStack(fluid, amount.toInt())

// endregion

// region Short Circuit Properties Setters

fun Material.addIngot()
{
    setProperty(PropertyKey.INGOT, IngotProperty())
}

fun Material.addDust()
{
    setProperty(PropertyKey.DUST, DustProperty())
}

fun Material.addLiquid()
{
    setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))
}

fun Material.addGas()
{
    setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.GAS, FluidBuilder()))
}

fun Material.addPlasma()
{
    getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())
}

fun Material.addLiquidAndPlasma()
{
    setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.LIQUID, FluidBuilder()))
    getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())
}

fun Material.addGasAndPlasma()
{
    setProperty(PropertyKey.FLUID, FluidProperty(FluidStorageKeys.GAS, FluidBuilder()))
    getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, FluidBuilder())
}

fun Material.setFormula(formulaKey: String, defaultKey: String = formulaKey): Material
{
    setFormula(I18nUtil.format(formulaKey, defaultKey))
    return this
}

// endregion